package de.swheeler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.alamos.fe2.external.enums.EAlarmDataEntries;
import de.alamos.fe2.external.interfaces.IAlarmExtractor;

public class HarburgPocsag implements IAlarmExtractor {
	
	String pattern1 = "\\d\\d:\\d\\d \\d\\d.\\d\\d.\\d\\d\\d\\d[A-Z]Einsatzalarm:";

	/**
	 * Format: street house * city - city_abbr * epn * keyword-ident (keyword)-
	 *
	 * @param input
	 * @return
	 */
	@Override
	public Map<String, String> extract(String input) {
		Map<String, String> data = new HashMap<>();
		
		//19:19 07.02.1901AEinsatzalarm: 
		String message = input.replaceAll(pattern1, "").trim();
		
		//Split ENR Number
		String enr = message.substring(0, message.indexOf(" ")).replaceFirst("ENr:", "");
		data.put("enr", enr);
		System.out.println(enr);
		
		message = message.replaceFirst("ENr:", "").replaceFirst(enr, "").trim();
		
		//Split Keyword
		String keyword = message.substring(0, message.indexOf(" ")).replaceFirst("SoSi-", "");
		data.put(EAlarmDataEntries.KEYWORD.getKey(), keyword);
		System.out.println(keyword);
		
		message = message.replaceFirst("SoSi-", "").replaceFirst(keyword, "").trim();
		
		//Split keyword_description
		//We have to check if there is a description (it is located in brackets)
		int indexOfKeywordBrackets = message.indexOf("(");
		int indexOfStartCity = message.indexOf("in ");
		
		String keyword_description = "";
		
		if (indexOfKeywordBrackets >= 0 && indexOfKeywordBrackets < indexOfStartCity) {
			keyword_description = message.substring(indexOfKeywordBrackets, indexOfStartCity).replaceFirst("\\(", "").replaceFirst("\\)", "").trim();
			System.out.println(keyword_description);
			message = message.replace(keyword_description, "").replaceFirst("\\(", "").replaceFirst("\\)", "").trim();
		}

		data.put(EAlarmDataEntries.KEYWORD_DESCRIPTION.getKey(), keyword_description);
		message = message.replaceFirst("in ", "").trim();
		
		//City
		String city = message.substring(0, message.indexOf("-"));
		data.put(EAlarmDataEntries.CITY.getKey(), city);
		System.out.println(city);
		
		message = message.replaceFirst(city, "").replaceFirst("-", "").trim();
		
		//City-Abbr
		String city_abbr = message.substring(0, message.indexOf(" "));
		data.put("city_abbr", city_abbr);
		System.out.println(city_abbr);

		message = message.replaceFirst(city_abbr, "").trim();
		
		//Street and House
		//We have to check, if there is an object in the text (indicated by brackets) and if not get the string until the comma
		
		int indexOfBracket = message.indexOf("(");
		int indexOfComma = message.indexOf(",");
		if (indexOfBracket >= 0) {
			//We have an object, get it into the map
			String building = message.substring(indexOfBracket, indexOfComma).replaceFirst("\\(", "").replaceFirst("\\)", "").trim();

			System.out.println(building);
			data.put("building_name", building);

			message = message.replace("("+building+")", "");
		}

		indexOfComma = message.indexOf(",");
		String street = message.substring(0, indexOfComma).trim();
		data.put(EAlarmDataEntries.STREET.getKey(), street);
		
		message = message.replaceFirst(street, "").replaceFirst(",", "").replace("es rücken aus:", "").trim();

		System.out.println(street);
		
		//Einsatzmittel

		String einsatzmittel = "";
		String[] tmp = message.split(",");
		
		for (String fahrzeug : tmp) {
			einsatzmittel = einsatzmittel + fahrzeug + "\n" ;
			message = message.replace(fahrzeug, "");
		}

		data.put("einsatzmittel", einsatzmittel.substring(0, einsatzmittel.length()-1));
		data.put("vehicles", einsatzmittel.substring(0, einsatzmittel.length()-1));

		System.out.println(einsatzmittel);
		message = message.replace(",", "");
		
		System.out.println(message);

		return data;
	}
}
