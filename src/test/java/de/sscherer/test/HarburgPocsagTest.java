package de.sscherer.test;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;

import de.alamos.fe2.external.enums.EAlarmDataEntries;
import de.swheeler.HarburgPocsag;

public class HarburgPocsagTest {

	@Test
	public void test() {
		HarburgPocsag impl = new HarburgPocsag();

		String msg = "19:19 07.02.1901AEinsatzalarm: ENr:0013579 SoSi-BMA  in Rosengarten-Eckel Buchholzer Straße 2 A (Asylunterkunft Eckel) , es rücken aus: WL 12-11-1 ELW Rosengarten,WL TE FF Eckel voll,WL TE FF Klecken voll,WL TE FF Nenndorf voll,WL GBM Rosengarten";
		Map<String, String> map = impl.extract(msg);
		assertEquals("0013579", map.get("enr"));
		assertEquals("BMA", map.get(EAlarmDataEntries.KEYWORD.getKey()));
		assertEquals("", map.get("keyword_description"));
		assertEquals("Rosengarten", map.get(EAlarmDataEntries.CITY.getKey()));
		assertEquals("Eckel", map.get("city_abbr"));
		assertEquals("Buchholzer Straße 2 A", map.get(EAlarmDataEntries.STREET.getKey()));
		assertEquals("Asylunterkunft Eckel", map.get("building_name"));

		msg = "17:32 16.02.1901AEinsatzalarm: ENr:0016899 SoSi-F2  (Brennt Keller) in Rosengarten-Eckel In der Ohe 9 , es rücken aus: WL 12-11-1 ELW Rosengarten,WL TE FF Eckel voll,WL TE FF Klecken voll,WL GBM Rosengarten";
		map = impl.extract(msg);
		assertEquals("0016899", map.get("enr"));
		assertEquals("F2", map.get(EAlarmDataEntries.KEYWORD.getKey()));
		assertEquals("Brennt Keller", map.get("keyword_description"));
		assertEquals("Rosengarten", map.get(EAlarmDataEntries.CITY.getKey()));
		assertEquals("Eckel", map.get("city_abbr"));
		assertEquals("In der Ohe 9", map.get(EAlarmDataEntries.STREET.getKey()));
	}
}
