package de.firemergency;

import java.util.HashMap;
import java.util.Map;

public class AddressCodeGenerator {

	// : Station: Straﬂe: Goethestraﬂe: 59 Kreuzung: : 85055 Ingolstadt
	// Ingolstadt Gemeinde: Ingolstadt a.d. Donau
	public static Map<String, Object> getAddress(String input) {
		Map<String, Object> data = new HashMap<>();

		/*
		 * 01 Station2 Straﬂe3 Goethestraﬂe4 59 Kreuzung56 85055 Ingolstadt Ingolstadt Gemeinde7 Ingolstadt a.d. Donau
		 */

		/*
		 *
		 * 1.3.1 IN Auwaldsee Gastst‰tte Ingolstadt Station Straﬂe: Am Auwaldsee: 20 Kreuzung: : 85057 Ingolstadt Ingolstadt Gemeinde: Ingolstadt a.d. Donau
		 */

		input = input.replaceAll("=", ":").replaceAll("HsNr:", "");
		String[] arr = input.split(":");
		String street = arr[1].replaceAll("Haus(-)?Nr.", "");
		street = street.replaceAll(">", "").trim();
		data.put("street", street);

		String house = arr[2].replaceAll("Abschnitt", "").trim();
		String[] tmp = house.split(" ");
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < tmp.length; i++) {
			String s = tmp[i];
			switch (i) {
			case 0:
				if (s.matches("\\d*")) {
					buf.append(s);
				}
				break;
			case 1:
				if (s.matches("[abcdefghijklmnopqrstuvwqxyz]")) {
					buf.append(s);
				}
				break;
			}
		}
		data.put("house", buf.toString());

		String postalCode = arr[4].replaceAll("\\D", "").trim();
		data.put("postalCode", postalCode);

		String city = arr[4].replaceAll("(\\d){5}", "").trim();

		tmp = city.split("-");
		data.put("city", tmp[0].trim().replaceAll("Objekt.*", ""));

		String building = arr[5].replaceAll("Kreuzung", "");
		building = building.replaceAll("7.1.4 DLG", "").trim();
		data.put("building", building);
		return data;
	}
}
