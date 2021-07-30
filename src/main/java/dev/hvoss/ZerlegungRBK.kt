package dev.hvoss

import de.alamos.fe2.external.interfaces.IAlarmExtractor
import java.util.HashMap
import de.alamos.fe2.external.enums.EAlarmDataEntries

class ZerlegungRBK : IAlarmExtractor {

    override fun extract(input: String): Map<String, String> {

        val data: MutableMap<String, String> = HashMap()

        val params = input.split(";")
        data["alarm_number"] = params[1]
        data[EAlarmDataEntries.KEYWORD.key] = params[2]
        data["rtw"] = getNumberOfRTW(params[5])
        data["nef"] = getNumberOfNEF(params[5])
        data["sonderrechte"] = params[6]
        data["district"] = getDistrict(params[8])
        data[EAlarmDataEntries.CITY.key] = params[7]
        data[EAlarmDataEntries.STREET.key] = "${params[9]} ${params[10]} ${params[11]}"
        data["address_number"] = params[10]
        data[EAlarmDataEntries.LOCATION_ADDITIOnAL.key] = params[11]
        data["floor"] = params[12]
        data["abschnitt"] = params[13]
        data[EAlarmDataEntries.BUILDING_NAME.key] = params[14]
        data[EAlarmDataEntries.BUILDING_NAME.key + "_sub"] = params[15]
        data["bma_number"] = params[16]
        data["einsatzplan"] = params[17]
        data[EAlarmDataEntries.TEXT.key] = params[18]

        return data
    }

    private fun getDistrict(s: String): String {
        if (s.contains("/")) {
            return s.split("/").last()
        } else {
            return s
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