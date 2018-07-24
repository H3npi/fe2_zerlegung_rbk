package de.firemergency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddressCodeGenerator {

	/**
	 * Format: street house * city - city_abbr * epn * keyword-ident (keyword)-
	 *
	 * @param input
	 * @return
	 */
	public static Map<String, Object> getAddress(String input) {
		Map<String, Object> data = new HashMap<>();

		List<String> parameters = new ArrayList<>();
		String[] arr = input.split("\\*");
		for (String s : arr) {
			parameters.add(s);
		}

		// Street
		String street = parameters.get(0);
		if (street.contains("  ")) {
			String[] streetArr = street.split("  ");
			street = streetArr[0];
			if (streetArr.length > 1) {
				data.put("stockwerk", streetArr[1]);
			}
		}
		data.put("street", street);
		String ort = parameters.get(1);
		// Stettenhofen - Langw
		if (ort.contains(" - ")) {
			String[] tmpCity = ort.split(" - ");
			data.put("city", tmpCity[0]);
			data.put("city_abbr", tmpCity[1]);
		} else if (ort.contains("-A ")) {
			// Oberhausen-A
			data.put("city", "Augsburg");
			data.put("city_abbr", ort.replaceFirst("-A", ""));
		} else {
			// Normal
			data.put("city", ort);
		}

		data.put("epn", parameters.get(2));

		String keyword = parameters.get(3).trim();
		String abek = keyword.substring(0, 5);
		String stichwort = keyword.substring(5, keyword.indexOf("("));

		keyword = keyword.replaceFirst(".*\\(", "");
		keyword = keyword.replaceFirst("\\).*", "");

		data.put("stichwort", stichwort);
		data.put("schlagwort", keyword);

		data.put("abek", abek);

		for (String key : data.keySet()) {
			data.put(key, ((String) data.get(key)).trim());
		}

		return data;
	}
}
