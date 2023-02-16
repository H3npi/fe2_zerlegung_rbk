package de.zerlegungV2.test;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import dev.hvoss.ZerlegungRBK;
import org.junit.Test;

import de.alamos.fe2.external.ExtractorObject;
import de.alamos.fe2.external.enums.EAlarmDataEntries;

public class ZerlegungV2ExampleTest {
	@Test
	public void testKleinbrand() {
		ZerlegungRBK v2Example = new ZerlegungRBK();

		Map<String, String> map = new HashMap<>();
		map.put(EAlarmDataEntries.TEXT.getKey(), ";F 6076;F-Kleinbrand;S1;;;;RÖS;Hasbach;Hasbacher Straße;0;;;;Rett-Punkt RÖS 5326;;;;im Verlauf;");

		ExtractorObject object = v2Example.extractFromMap(map);
		Map data = object.getData();

		assertEquals("Rett-Punkt RÖS 5326", data.get(EAlarmDataEntries.BUILDING_NAME.getKey()));
		assertEquals("50.87266108895494", data.get(EAlarmDataEntries.LAT.getKey()));
		assertEquals("7.161306381228738", data.get(EAlarmDataEntries.LNG.getKey()));
	}

	@Test
	public void testVU() {
		ZerlegungRBK v2Example = new ZerlegungRBK();

		Map<String, String> map = new HashMap<>();
		map.put(EAlarmDataEntries.TEXT.getKey(), ";F 8797;H-VU;S1;;;;RÖS;;L170;;;;;;;;;;");

		ExtractorObject object = v2Example.extractFromMap(map);
		Map data = object.getData();

		assertEquals("F 8797", data.get("alarm_number"));
		assertEquals("H-VU", data.get("keyword"));
		assertEquals("", data.get("sonderrechte"));
		assertEquals("Rösrath", data.get(EAlarmDataEntries.CITY.getKey()));
		assertEquals("NRW", data.get("district"));
		assertEquals("L170", data.get(EAlarmDataEntries.STREET.getKey()));
		assertEquals("", data.get(EAlarmDataEntries.HOUSE.getKey()));
		assertEquals("", data.get("floor"));
		assertEquals("", data.get(EAlarmDataEntries.BUILDING_NAME.getKey()));
		assertEquals("", data.get("bma_number"));
		assertEquals("", data.get("abschnitt"));
		assertEquals("", data.get(EAlarmDataEntries.TEXT.getKey()));
		assertEquals("", data.get("rtw"));
		assertEquals("", data.get("nef"));
	}

	@Test
	public void testVULKW() {
		ZerlegungRBK v2Example = new ZerlegungRBK();

		Map<String, String> map = new HashMap<>();
		map.put(EAlarmDataEntries.TEXT.getKey(), ";F 1111;H-VU LKW;S3;;;;BAB 3;;A3->Oberhausen;5;;; Raststätte Königsforst Ost->Königsforst Abfahrt;;;;;;");

		ExtractorObject object = v2Example.extractFromMap(map);
		Map data = object.getData();

		assertEquals("F 1111", data.get("alarm_number"));
		assertEquals("H-VU LKW", data.get("keyword"));
		assertEquals("", data.get("sonderrechte"));
		assertEquals("BAB 3", data.get(EAlarmDataEntries.CITY.getKey()));
		assertEquals("NRW", data.get("district"));
		assertEquals("A3->Oberhausen", data.get(EAlarmDataEntries.STREET.getKey()));
		assertEquals("Km 5", data.get(EAlarmDataEntries.HOUSE.getKey()));
		assertEquals("", data.get("floor"));
		assertEquals("", data.get(EAlarmDataEntries.BUILDING_NAME.getKey()));
		assertEquals("", data.get("bma_number"));
		assertEquals(" Raststätte Königsforst Ost->Königsforst Abfahrt", data.get("abschnitt"));
		assertEquals("", data.get(EAlarmDataEntries.TEXT.getKey()));
		assertEquals("", data.get("rtw"));
		assertEquals("", data.get("nef"));
	}

	@Test
	public void testVUTier() {
		ZerlegungRBK v2Example = new ZerlegungRBK();

		Map<String, String> map = new HashMap<>();
		map.put(EAlarmDataEntries.TEXT.getKey(), " ;F 1140;H-Tier in Notlage;S1;;;OHNE SONDERRECHTE;RÖS;Kleineichen;Alte Kölner Straße;243;c;EFH; ;;;;;Schlange im Keller;");

		ExtractorObject object = v2Example.extractFromMap(map);
		Map data = object.getData();

		assertEquals("F 1140", data.get("alarm_number"));
		assertEquals("H-Tier in Notlage", data.get("keyword"));
		assertEquals("OHNE SONDERRECHTE", data.get("sonderrechte"));
		assertEquals("Rösrath", data.get(EAlarmDataEntries.CITY.getKey()));
		assertEquals("Kleineichen", data.get("district"));
		assertEquals("Alte Kölner Straße", data.get(EAlarmDataEntries.STREET.getKey()));
		assertEquals("243c", data.get(EAlarmDataEntries.HOUSE.getKey()));
		assertEquals("EFH", data.get("floor"));
		assertEquals("", data.get(EAlarmDataEntries.BUILDING_NAME.getKey()));
		assertEquals("", data.get("bma_number"));
		assertEquals("Schlange im Keller", data.get(EAlarmDataEntries.TEXT.getKey()));
		assertEquals("", data.get("rtw"));
		assertEquals("", data.get("nef"));
	}

	@Test
	public void testBMA() {
		ZerlegungRBK v2Example = new ZerlegungRBK();

		Map<String, String> map = new HashMap<>();
		map.put(EAlarmDataEntries.TEXT.getKey(), ";F123;F-Alarmauslösung;S2;;R1N0;;RÖS;Zentrum;Musterstrasse;1;;;;Fa Müller;;900123;;Freitext Informationen;");

		ExtractorObject object = v2Example.extractFromMap(map);
		Map data = object.getData();

		assertEquals("F123", data.get("alarm_number"));
		assertEquals("F-Alarmauslösung", data.get("keyword"));
		assertEquals("", data.get("sonderrechte"));
		assertEquals("Rösrath", data.get(EAlarmDataEntries.CITY.getKey()));
		assertEquals("Zentrum", data.get("district"));
		assertEquals("Musterstrasse", data.get(EAlarmDataEntries.STREET.getKey()));
		assertEquals("1", data.get(EAlarmDataEntries.HOUSE.getKey()));
		assertEquals("", data.get("floor"));
		assertEquals("Fa Müller", data.get(EAlarmDataEntries.BUILDING_NAME.getKey()));
		assertEquals("900123", data.get("bma_number"));
		assertEquals("Freitext Informationen", data.get(EAlarmDataEntries.TEXT.getKey()));
		assertEquals("1", data.get("rtw"));
		assertEquals("0", data.get("nef"));
	}

	@Test
	public void testPKlemmt() {
		ZerlegungRBK v2Example = new ZerlegungRBK();

		Map<String, String> map = new HashMap<>();
		map.put(EAlarmDataEntries.TEXT.getKey(), ";F123;H-P Klemmt (1);S2;;R1N1;;WRM;Zentrum;Musterstrasse;1;;;;;;;;Freitext Informationen;");

		ExtractorObject object = v2Example.extractFromMap(map);
		Map data = object.getData();

		assertEquals("F123", data.get("alarm_number"));
		assertEquals("H-P Klemmt (1)", data.get("keyword"));
		assertEquals("", data.get("sonderrechte"));
		assertEquals("Wermelskirchen", data.get(EAlarmDataEntries.CITY.getKey()));
		assertEquals("Zentrum", data.get("district"));
		assertEquals("Musterstrasse", data.get(EAlarmDataEntries.STREET.getKey()));
		assertEquals("1", data.get(EAlarmDataEntries.HOUSE.getKey()));
		assertEquals("", data.get("floor"));
		assertEquals("Freitext Informationen", data.get(EAlarmDataEntries.TEXT.getKey()));
		assertEquals("1", data.get("rtw"));
		assertEquals("1", data.get("nef"));
	}

}