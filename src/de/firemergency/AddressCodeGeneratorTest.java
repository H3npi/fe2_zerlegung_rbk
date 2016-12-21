package de.firemergency;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;

public class AddressCodeGeneratorTest {

	@Test
	public void test() {

		Map<String, Object> map = AddressCodeGenerator.getAddress("Stra�e : Bahnhofstra�e Haus-Nr.: 26 Abschnitt : Bahnhofstra�e [HsNr: 1-51 / 2-52] Ort : 89423 Gundelfingen a.d.Donau - Gundelfingen a d Donau Gundelfingen a d Donau Objekt = Kreuzung : Koordinate: 4379862.08 / 5380333.07");

		for (String k : map.keySet()) {
			System.out.println(String.format("%s <> %s", k, map.get(k) + ""));
		}
		assertEquals("Bahnhofstra�e", map.get("street"));
		assertEquals("26", map.get("house"));
		assertEquals("Gundelfingen a.d.Donau", map.get("city"));

		map = AddressCodeGenerator.getAddress("Stra�e : >> Kicklingen Haus-Nr.=  Abschnitt :  Ort : 89407 Dillingen a.d.Donau - Dillingen a d Donau Dillingen a d Donau Objekt :  Kreuzung =  Koordinate: 4389773.75 / 5382158.51");

		for (String k : map.keySet()) {
			System.out.println(String.format("%s <> %s", k, map.get(k) + ""));
		}
		assertEquals("Kicklingen", map.get("street"));
		assertEquals("", map.get("house"));
		assertEquals("Dillingen a.d.Donau", map.get("city"));

		map = AddressCodeGenerator.getAddress("		Stra�e : Gartnerstra�e Haus-Nr.: 20		Abschnitt : Gartnerstra�e [HsNr: 1-13 / 2-26]		Ort : 89423 Gundelfingen a.d.Donau - Gundelfingen a d Donau Gundelfingen		a d Donau		Objekt = 7.1.4 DLG Gartner Gundelfingen		Kreuzung :		Koordinate: 4380592.69 / 5380297.28");

		for (String k : map.keySet()) {
			System.out.println(String.format("%s <> %s", k, map.get(k) + ""));
		}
		assertEquals("Gartnerstra�e", map.get("street"));
		assertEquals("20", map.get("house"));
		assertEquals("Gundelfingen a.d.Donau", map.get("city"));
		assertEquals("Gartner Gundelfingen", map.get("building"));

		map = AddressCodeGenerator.getAddress("		Stra�e : Rudolf-Diesel-Stra�e Haus-Nr.: 3 Abschnitt : Rudolf-Diesel-Stra�e (HsNr: 1-9 / 2-10) Ort : 89407 Dillingen a.d.Donau - Dillingen a d Donau Dillingen a d Donau Objekt : Kreuzung : Koordinate: 4390191.59 / 5384155.33");

		for (String k : map.keySet()) {
			System.out.println(String.format("%s <> %s", k, map.get(k) + ""));
		}
		assertEquals("Rudolf-Diesel-Stra�e", map.get("street"));
		assertEquals("3", map.get("house"));
		assertEquals("Dillingen a.d.Donau", map.get("city"));

	}

}
