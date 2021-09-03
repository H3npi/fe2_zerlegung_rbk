package dev.hvoss

import de.alamos.fe2.external.interfaces.IAlarmExtractor
import java.util.HashMap
import de.alamos.fe2.external.enums.EAlarmDataEntries

class ZerlegungRBK : IAlarmExtractor {

    override fun extract(input: String): Map<String, String> {

        val data: MutableMap<String, String> = HashMap()

        val params = input.split(";")
        try {
            data["alarm_number"] = params[1]
            data[EAlarmDataEntries.KEYWORD.key] = params[2]
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
        } catch (indexOutOfBounds: IndexOutOfBoundsException) {
            System.out.println("Less parameters than expected!")
            System.out.println(indexOutOfBounds)
            System.out.println("Using just text")
            data[EAlarmDataEntries.TEXT.key] = input
        }


        return data
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