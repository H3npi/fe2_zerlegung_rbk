package dev.hvoss.test;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;

import de.alamos.fe2.external.enums.EAlarmDataEntries;
import dev.hvoss.ZerlegungRBK;

public class ZerlegungRBKTest {

	@Test
	public void test() {
		ZerlegungRBK impl = new ZerlegungRBK();

		//BEISPIELDATEN
		String msg = ";F123;F-Wohnung;S3;;R1N0;;BGL;Stadtmitte;Musterstrasse;15;c;4;;;;;;Freitext Informationen;";
		Map<String, String> map = impl.extract(msg);

		assertEquals("F123", map.get("alarm_number"));
		assertEquals("F-Wohnung", map.get("keyword"));
		assertEquals("", map.get("sonderrechte"));
		assertEquals("Bergisch Gladbach", map.get(EAlarmDataEntries.CITY.getKey()));
		assertEquals("Stadtmitte", map.get("district"));
		assertEquals("Musterstrasse", map.get(EAlarmDataEntries.STREET.getKey()));
		assertEquals("15c", map.get(EAlarmDataEntries.HOUSE.getKey()));
		assertEquals("4", map.get("floor"));
		assertEquals("Freitext Informationen", map.get(EAlarmDataEntries.TEXT.getKey()));
		assertEquals("1", map.get("rtw"));
		assertEquals("0", map.get("nef"));

		msg = ";F123;H-P Klemmt (1);S2;;R1N1;;WRM;Zentrum;Musterstrasse;1;;;;;;;;Freitext Informationen;";
		map = impl.extract(msg);
		assertEquals("F123", map.get("alarm_number"));
		assertEquals("H-P Klemmt (1)", map.get("keyword"));
		assertEquals("", map.get("sonderrechte"));
		assertEquals("Wermelskirchen", map.get(EAlarmDataEntries.CITY.getKey()));
		assertEquals("Zentrum", map.get("district"));
		assertEquals("Musterstrasse", map.get(EAlarmDataEntries.STREET.getKey()));
		assertEquals("1", map.get(EAlarmDataEntries.HOUSE.getKey()));
		assertEquals("", map.get("floor"));
		assertEquals("Freitext Informationen", map.get(EAlarmDataEntries.TEXT.getKey()));
		assertEquals("1", map.get("rtw"));
		assertEquals("1", map.get("nef"));

		msg = ";F123;F-Alarmauslösung;S2;;R1N0;;RÖS;Zentrum;Musterstrasse;1;;;;Fa Müller;;900123;;Freitext Informationen;";
		map = impl.extract(msg);
		assertEquals("F123", map.get("alarm_number"));
		assertEquals("F-Alarmauslösung", map.get("keyword"));
		assertEquals("", map.get("sonderrechte"));
		assertEquals("Rösrath", map.get(EAlarmDataEntries.CITY.getKey()));
		assertEquals("Zentrum", map.get("district"));
		assertEquals("Musterstrasse", map.get(EAlarmDataEntries.STREET.getKey()));
		assertEquals("1", map.get(EAlarmDataEntries.HOUSE.getKey()));
		assertEquals("", map.get("floor"));
		assertEquals("Fa Müller", map.get(EAlarmDataEntries.BUILDING_NAME.getKey()));
		assertEquals("900123", map.get("bma_number"));
		assertEquals("Freitext Informationen", map.get(EAlarmDataEntries.TEXT.getKey()));
		assertEquals("1", map.get("rtw"));
		assertEquals("0", map.get("nef"));

		msg = ";F 5505;H-Baum;S1;;;;RÖSRATH;RÖS/Forsbach;Bensberger Straße;243;;; Forsbach;FW Forsbach;;;;;";
		map = impl.extract(msg);
		assertEquals("F 5505", map.get("alarm_number"));
		assertEquals("H-Baum", map.get("keyword"));
		assertEquals("", map.get("sonderrechte"));
		assertEquals("RÖSRATH", map.get(EAlarmDataEntries.CITY.getKey()));
		assertEquals("Forsbach", map.get("district"));
		assertEquals("Bensberger Straße", map.get(EAlarmDataEntries.STREET.getKey()));
		assertEquals("243", map.get(EAlarmDataEntries.HOUSE.getKey()));
		assertEquals("", map.get("floor"));
		assertEquals("FW Forsbach", map.get(EAlarmDataEntries.BUILDING_NAME.getKey()));
		assertEquals("", map.get("bma_number"));
		assertEquals("", map.get(EAlarmDataEntries.TEXT.getKey()));
		assertEquals("", map.get("rtw"));
		assertEquals("", map.get("nef"));

		msg = " ;F 1140;H-Tier in Notlage;S1;;;OHNE SONDERRECHTE;RÖS;Kleineichen;Alte Kölner Straße;243;c;EFH; ;;;;;Schlange im Keller;";
		map = impl.extract(msg);
		assertEquals("F 1140", map.get("alarm_number"));
		assertEquals("H-Tier in Notlage", map.get("keyword"));
		assertEquals("OHNE SONDERRECHTE", map.get("sonderrechte"));
		assertEquals("Rösrath", map.get(EAlarmDataEntries.CITY.getKey()));
		assertEquals("Kleineichen", map.get("district"));
		assertEquals("Alte Kölner Straße", map.get(EAlarmDataEntries.STREET.getKey()));
		assertEquals("243c", map.get(EAlarmDataEntries.HOUSE.getKey()));
		assertEquals("EFH", map.get("floor"));
		assertEquals("", map.get(EAlarmDataEntries.BUILDING_NAME.getKey()));
		assertEquals("", map.get("bma_number"));
		assertEquals("Schlange im Keller", map.get(EAlarmDataEntries.TEXT.getKey()));
		assertEquals("", map.get("rtw"));
		assertEquals("", map.get("nef"));

		msg = ";F 1111;H-VU LKW;S3;;;;BAB 3;;A3->Oberhausen;5;;; Raststätte Königsforst Ost->Königsforst Abfahrt;;;;;;";
		map = impl.extract(msg);
		assertEquals("F 1111", map.get("alarm_number"));
		assertEquals("H-VU LKW", map.get("keyword"));
		assertEquals("", map.get("sonderrechte"));
		assertEquals("BAB 3", map.get(EAlarmDataEntries.CITY.getKey()));
		assertEquals("NRW", map.get("district"));
		assertEquals("A3->Oberhausen", map.get(EAlarmDataEntries.STREET.getKey()));
		assertEquals("Km 5", map.get(EAlarmDataEntries.HOUSE.getKey()));
		assertEquals("", map.get("floor"));
		assertEquals("", map.get(EAlarmDataEntries.BUILDING_NAME.getKey()));
		assertEquals("", map.get("bma_number"));
		assertEquals(" Raststätte Königsforst Ost->Königsforst Abfahrt", map.get("abschnitt"));
		assertEquals("", map.get(EAlarmDataEntries.TEXT.getKey()));
		assertEquals("", map.get("rtw"));
		assertEquals("", map.get("nef"));


		msg = ";F 8797;H-VU;S1;;;;RÖS;;L170;;;;;;;;;;";
		map = impl.extract(msg);
		assertEquals("F 8797", map.get("alarm_number"));
		assertEquals("H-VU", map.get("keyword"));
		assertEquals("", map.get("sonderrechte"));
		assertEquals("Rösrath", map.get(EAlarmDataEntries.CITY.getKey()));
		assertEquals("NRW", map.get("district"));
		assertEquals("L170", map.get(EAlarmDataEntries.STREET.getKey()));
		assertEquals("", map.get(EAlarmDataEntries.HOUSE.getKey()));
		assertEquals("", map.get("floor"));
		assertEquals("", map.get(EAlarmDataEntries.BUILDING_NAME.getKey()));
		assertEquals("", map.get("bma_number"));
		assertEquals("", map.get("abschnitt"));
		assertEquals("", map.get(EAlarmDataEntries.TEXT.getKey()));
		assertEquals("", map.get("rtw"));
		assertEquals("", map.get("nef"));

		msg = ";F 6076;F-Kleinbrand;S1;;;;RÖS;Hasbach;Hasbacher Straße;0;;;;Rett-Punkt RÖS 5326;;;;im Verlauf;";
		map = impl.extract(msg);
		assertEquals("Rett-Punkt RÖS 5326", map.get(EAlarmDataEntries.BUILDING_NAME.getKey()));
		assertEquals("50.87266108895494", map.get(EAlarmDataEntries.LAT.getKey()));
		assertEquals("7.161306381228738", map.get(EAlarmDataEntries.LNG.getKey()));


		msg = "Einsatz abbrechen ";
		map = impl.extract(msg);
		assertEquals("Einsatz abbrechen ", map.get(EAlarmDataEntries.TEXT.getKey()));
	}
}
