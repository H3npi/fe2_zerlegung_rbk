package address.augsburg;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;

import de.firemergency.AddressCodeGenerator;

public class AddressCodeGeneratorTest {

	@Test
	public void test() {

		String msg = "M�hlstra�e 10 * Stettenhofen - Langw * * B1510PKW (B 2)-";
		Map<String, Object> map = AddressCodeGenerator.getAddress(msg);
		assertEquals("B1510", map.get("abek"));
		assertEquals("PKW", map.get("stichwort"));
		assertEquals("B 2", map.get("schlagwort"));
		assertEquals("M�hlstra�e 10", map.get("street"));
		assertEquals("Stettenhofen", map.get("city"));

		msg = "Pater-Roth-Stra�e 31  OG 3 *  PferseeN-A *  * R3020CPR/HLW (THL RETTUNGSKORB, RD 2)-";
		map = AddressCodeGenerator.getAddress(msg);
		assertEquals("R3020", map.get("abek"));
		assertEquals("CPR/HLW", map.get("stichwort"));
		assertEquals("THL RETTUNGSKORB, RD 2", map.get("schlagwort"));
		assertEquals("Pater-Roth-Stra�e 31", map.get("street"));
		assertEquals("OG 3", map.get("stockwerk"));
		assertEquals("Augsburg", map.get("city"));
		assertEquals("PferseeN", map.get("city_abbr"));

		msg = "G�gginger Stra�e 73   *  Antonsv-A * EPN 056 * B1710BMA (B BMA)-";
		map = AddressCodeGenerator.getAddress(msg);
		assertEquals("B1710", map.get("abek"));
		assertEquals("BMA", map.get("stichwort"));
		assertEquals("B BMA", map.get("schlagwort"));
		assertEquals("EPN 056", map.get("epn"));
		assertEquals("G�gginger Stra�e 73", map.get("street"));
		assertEquals("Augsburg", map.get("city"));
		assertEquals("Antonsv", map.get("city_abbr"));

		msg = "A8 M�nchen > Stuttga 50   *   *  * B1511PKW auf BAB (B 2 PERSON)-";
		map = AddressCodeGenerator.getAddress(msg);
		assertEquals("B1511", map.get("abek"));
		assertEquals("PKW auf BAB", map.get("stichwort"));
		assertEquals("B 2 PERSON", map.get("schlagwort"));
		assertEquals("A8 M�nchen > Stuttga 50", map.get("street"));

		msg = "Fr�lichstra�e 2   *  Stadtj�gerv-A * EPN 651 * T2727Insekt P i Gfr (THL 1)-";
		map = AddressCodeGenerator.getAddress(msg);
		assertEquals("T2727", map.get("abek"));
		assertEquals("Insekt P i Gfr", map.get("stichwort"));
		assertEquals("THL 1", map.get("schlagwort"));
		assertEquals("EPN 651", map.get("epn"));
		assertEquals("Fr�lichstra�e 2", map.get("street"));
		assertEquals("Augsburg", map.get("city"));
		assertEquals("Stadtj�gerv", map.get("city_abbr"));

		msg = "Luther-King-Stra�e 4 a  *  Kriegshaber-A * EPN 578 * B1716BMA-P (B 3 PERSON)-";
		map = AddressCodeGenerator.getAddress(msg);
		assertEquals("B1716", map.get("abek"));
		assertEquals("BMA-P", map.get("stichwort"));
		assertEquals("B 3 PERSON", map.get("schlagwort"));
		assertEquals("EPN 578", map.get("epn"));
		assertEquals("Luther-King-Stra�e 4 a", map.get("street"));
		assertEquals("Augsburg", map.get("city"));
		assertEquals("Kriegshaber", map.get("city_abbr"));

		msg = "Herrenbachstra�e 31 d  *  Wolfr. u Herrenb.-A *  * R2010Atmung vB (RD 2)-";
		map = AddressCodeGenerator.getAddress(msg);
		assertEquals("R2010", map.get("abek"));
		assertEquals("Atmung vB", map.get("stichwort"));
		assertEquals("RD 2", map.get("schlagwort"));
		assertEquals("Herrenbachstra�e 31 d", map.get("street"));
		assertEquals("Augsburg", map.get("city"));
		assertEquals("Wolfr. u Herrenb.", map.get("city_abbr"));

		msg = "Louis-Braille-Stra�e 9  3.OG *  A Sch�fflerbach-A *  * R1010Bewusstsein vB (RD 2, THL RETTUNGSKORB)-";

		msg = "Viktoriastra�e 1   *  Bhf.uBismarckv-A * EPN 190 * T2728Rettung Kl Tier (THL 1)-";

		msg = "G�gginger Stra�e 119   *  G�ggingenNO-A * EPN 018 * R6010verletzt vB (RD 2)-";

		msg = "Am Eiskanal 30 a  *  Spickel - Augsburg * EPN 078 * R7000<12 J Erkrankt (RD 1)-";
		map = AddressCodeGenerator.getAddress(msg);
		assertEquals("R7000", map.get("abek"));
		assertEquals("<12 J Erkrankt", map.get("stichwort"));
		assertEquals("RD 1", map.get("schlagwort"));
		assertEquals("Am Eiskanal 30 a", map.get("street"));
		assertEquals("Spickel", map.get("city"));
		assertEquals("Augsburg", map.get("city_abbr"));

		msg = "G�gginger Stra�e 119   *  G�ggingenNO-A * EPN 018 * R6010verletzt vB (THL 1, RD 2)-";

		msg = "Thyssenstra�e 43 * Gersthofen * * R6020Unfall vB (RD 2)-";

		msg = "Kappeneck 30   *  Jakobervorst.S-A *  * T2711Baum auf Stra�e (THL 1)-";

		msg = "Am Backofenwall 13   *  Georgs- u Kreuzv - A *  * T2719Stra�e reinigen (THL 1)-";

		msg = "Georgstra�e 22 a * Bachern - Friedberg * * R6010verletzt vB (RD 2)-";

		msg = "Imhofstra�e 12  10/2016 *  Antonsv-A * EPN 113 * T2011P springt (THL P RETTUNG H / T)-";

		msg = "Am Mittleren Moos 60   *  Hammerschm-A * EPN 187 * B1710BMA (B BMA)-";

		msg = "Bautzener Stra�e 7 h  *  LechhausenO-A *  * T2728Rettung Kl Tier (THL 1)-";

		msg = "Schlo�gartenstra�e 23  EFH *  B�renkeller-A *  * T2722Wasser i Geb�ud (THL 1)-";
	}

}
