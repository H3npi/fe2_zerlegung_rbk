package dev.hvoss

import de.alamos.fe2.external.ExtractorObject
import de.alamos.fe2.external.enums.EAlarmDataEntries
import de.alamos.splitting.api.AbstractAlarmExtractorV2
import io.jenetics.jpx.GPX

class ZerlegungRBK : AbstractAlarmExtractorV2() {
    override fun extractFromMap(map: MutableMap<String, String>?): ExtractorObject {

        var input = map?.get(EAlarmDataEntries.TEXT.key) ?: ""
        val data: MutableMap<String, String> = HashMap()
        val extractorObject = ExtractorObject()
        if (input.contains(";")) {
            val params = input.split(";")
            try {
                data["alarm_number"] = params[1]
                data["externalId"] = params[1]
                data["v2_Id"] = params[1]
                data[EAlarmDataEntries.KEYWORD.key] = params[2]
                data["alarm_tier"] = params[3]
                data["rtw"] = getNumberOfRTW(params[5])
                data["nef"] = getNumberOfNEF(params[5])
                data["sonderrechte"] = params[6]
                data["district"] = getDistrict(params[8])
                data[EAlarmDataEntries.CITY.key] = getCity(params[7])
                data[EAlarmDataEntries.STREET.key] = params[9]
                data[EAlarmDataEntries.HOUSE.key] = getHouseNumberOrBABKm(params[7], params[10], params[11])
                data["floor"] = params[12]
                data["abschnitt"] = params[13]
                data[EAlarmDataEntries.BUILDING_NAME.key] = params[14]
                data[EAlarmDataEntries.BUILDING_NAME.key + "_sub"] = params[15]
                data["bma_number"] = params[16]
                data["einsatzplan"] = params[17]
                data[EAlarmDataEntries.TEXT.key] = params[18]
                if (data[EAlarmDataEntries.BUILDING_NAME.key]?.startsWith("Rett-Punkt") == true) {
                    val gpx = GPX.reader(GPX.Version.V11).read(javaClass.classLoader.getResourceAsStream("KWF_RP_V2_10_DE.gpx"))
                    val rescuePointString = data[EAlarmDataEntries.BUILDING_NAME.key]?.removePrefix("Rett-Punkt ")
                    rescuePointString?.let { rescuePointString ->
                        try {
                            val listOfMatchingWaypoints = gpx.wayPoints.filter { it.name.get().equals(rescuePointString) }
                            data[EAlarmDataEntries.LAT.key] = listOfMatchingWaypoints.first().latitude.toString()
                            data[EAlarmDataEntries.LNG.key] = listOfMatchingWaypoints.first().longitude.toString()
                        } catch (noSuchElementException: NoSuchElementException) {
                            System.out.println("Could not find rescue point: $rescuePointString ")
                            System.out.println(noSuchElementException)
                        }
                    }
                }
            } catch (indexOutOfBounds: IndexOutOfBoundsException) {
                System.out.println("Less parameters than expected!")
                System.out.println(indexOutOfBounds)
                System.out.println("Using just text")
                indexOutOfBounds.printStackTrace()
                data[EAlarmDataEntries.TEXT.key] += input
                data["error"] = indexOutOfBounds.toString()
            }
        } else if (input.contains("Voralarm", ignoreCase = true)) {
            input = input.trim()
            input = input.replace("null", "")
            input = input.replace("\\s+".toRegex(), " ")
            data[EAlarmDataEntries.TEXT.key] = input
            data["prealarm"] = true.toString()
            data[EAlarmDataEntries.KEYWORD.key] = "Voralarm"
        }


        extractorObject.data = data
        extractorObject.isComplete = true
        return extractorObject
    }

    private fun getHouseNumberOrBABKm(city: String, number: String, additional: String): String {
        if (city.startsWith("BAB")) {
            return "Km $number"
        }
        return number+additional
    }

    private fun getCity(city: String): String {
        if (city.length > 3) {
            return city
        }
        return when(city) {
            "RÖS" -> "Rösrath"
            "KÜR" -> "Kürten"
            "WRM" -> "Wermelskirchen"
            "BGL" -> "Bergisch Gladbach"
            "OVE" -> "Overath"
            else -> city
        }
    }

    private fun getDistrict(s: String): String {
        return if (s.contains("/")) {
            s.split("/").last()
        } else {
            s.ifBlank {
                "NRW"
            }
        }
    }

    private fun getNumberOfNEF(s: String): String {
        if (s.length >= 4) {
            return s.substring(3, 4)
        }
        return ""
    }

    private fun getNumberOfRTW(s: String): String {
        if (s.length >= 2) {
            return s.substring(1, 2)
        }
        return ""
    }
}
