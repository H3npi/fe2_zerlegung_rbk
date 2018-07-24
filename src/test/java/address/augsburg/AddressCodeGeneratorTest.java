package address.augsburg;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;

import de.firemergency.AddressCodeGenerator;

public class AddressCodeGeneratorTest {

	@Test
	public void test() {

		String msg = "Mühlstraße 10 * Stettenhofen - Langw * * B1510PKW (B 2)-";
		Map<String, Object> map = AddressCodeGenerator.getAddress(msg);
		assertEquals("B1510", map.get("abek"));
		assertEquals("PKW", map.get("stichwort"));
		assertEquals("B 2", map.get("schlagwort"));
		assertEquals("Mühlstraße 10", map.get("street"));
		assertEquals("Stettenhofen", map.get("city"));

		msg = "Pater-Roth-Straße 31  OG 3 *  PferseeN-A *  * R3020CPR/HLW (THL RETTUNGSKORB, RD 2)-";
		map = AddressCodeGenerator.getAddress(msg);
		assertEquals("R3020", map.get("abek"));
		assertEquals("CPR/HLW", map.get("stichwort"));
		assertEquals("THL RETTUNGSKORB, RD 2", map.get("schlagwort"));
		assertEquals("Pater-Roth-Straße 31", map.get("street"));
		assertEquals("OG 3", map.get("stockwerk"));
		assertEquals("Augsburg", map.get("city"));
		assertEquals("PferseeN", map.get("city_abbr"));

		msg = "Gögginger Straße 73   *  Antonsv-A * EPN 056 * B1710BMA (B BMA)-";
		map = AddressCodeGenerator.getAddress(msg);
		assertEquals("B1710", map.get("abek"));
		assertEquals("BMA", map.get("stichwort"));
		assertEquals("B BMA", map.get("schlagwort"));
		assertEquals("EPN 056", map.get("epn"));
		assertEquals("Gögginger Straße 73", map.get("street"));
		assertEquals("Augsburg", map.get("city"));
		assertEquals("Antonsv", map.get("city_abbr"));

		msg = "A8 München > Stuttga 50   *   *  * B1511PKW auf BAB (B 2 PERSON)-";
		map = AddressCodeGenerator.getAddress(msg);
		assertEquals("B1511", map.get("abek"));
		assertEquals("PKW auf BAB", map.get("stichwort"));
		assertEquals("B 2 PERSON", map.get("schlagwort"));
		assertEquals("A8 München > Stuttga 50", map.get("street"));

		msg = "Frölichstraße 2   *  Stadtjägerv-A * EPN 651 * T2727Insekt P i Gfr (THL 1)-";
		map = AddressCodeGenerator.getAddress(msg);
		assertEquals("T2727", map.get("abek"));
		assertEquals("Insekt P i Gfr", map.get("stichwort"));
		assertEquals("THL 1", map.get("schlagwort"));
		assertEquals("EPN 651", map.get("epn"));
		assertEquals("Frölichstraße 2", map.get("street"));
		assertEquals("Augsburg", map.get("city"));
		assertEquals("Stadtjägerv", map.get("city_abbr"));

		msg = "Luther-King-Straße 4 a  *  Kriegshaber-A * EPN 578 * B1716BMA-P (B 3 PERSON)-";
		map = AddressCodeGenerator.getAddress(msg);
		assertEquals("B1716", map.get("abek"));
		assertEquals("BMA-P", map.get("stichwort"));
		assertEquals("B 3 PERSON", map.get("schlagwort"));
		assertEquals("EPN 578", map.get("epn"));
		assertEquals("Luther-King-Straße 4 a", map.get("street"));
		assertEquals("Augsburg", map.get("city"));
		assertEquals("Kriegshaber", map.get("city_abbr"));

		msg = "Herrenbachstraße 31 d  *  Wolfr. u Herrenb.-A *  * R2010Atmung vB (RD 2)-";
		map = AddressCodeGenerator.getAddress(msg);
		assertEquals("R2010", map.get("abek"));
		assertEquals("Atmung vB", map.get("stichwort"));
		assertEquals("RD 2", map.get("schlagwort"));
		assertEquals("Herrenbachstraße 31 d", map.get("street"));
		assertEquals("Augsburg", map.get("city"));
		assertEquals("Wolfr. u Herrenb.", map.get("city_abbr"));

		msg = "Louis-Braille-Straße 9  3.OG *  A Schäfflerbach-A *  * R1010Bewusstsein vB (RD 2, THL RETTUNGSKORB)-";

		msg = "Viktoriastraße 1   *  Bhf.uBismarckv-A * EPN 190 * T2728Rettung Kl Tier (THL 1)-";

		msg = "Gögginger Straße 119   *  GöggingenNO-A * EPN 018 * R6010verletzt vB (RD 2)-";

		msg = "Am Eiskanal 30 a  *  Spickel - Augsburg * EPN 078 * R7000<12 J Erkrankt (RD 1)-";
		map = AddressCodeGenerator.getAddress(msg);
		assertEquals("R7000", map.get("abek"));
		assertEquals("<12 J Erkrankt", map.get("stichwort"));
		assertEquals("RD 1", map.get("schlagwort"));
		assertEquals("Am Eiskanal 30 a", map.get("street"));
		assertEquals("Spickel", map.get("city"));
		assertEquals("Augsburg", map.get("city_abbr"));

		msg = "Gögginger Straße 119   *  GöggingenNO-A * EPN 018 * R6010verletzt vB (THL 1, RD 2)-";

		msg = "Thyssenstraße 43 * Gersthofen * * R6020Unfall vB (RD 2)-";

		msg = "Kappeneck 30   *  Jakobervorst.S-A *  * T2711Baum auf Straße (THL 1)-";

		msg = "Am Backofenwall 13   *  Georgs- u Kreuzv - A *  * T2719Straße reinigen (THL 1)-";

		msg = "Georgstraße 22 a * Bachern - Friedberg * * R6010verletzt vB (RD 2)-";

		msg = "Imhofstraße 12  10/2016 *  Antonsv-A * EPN 113 * T2011P springt (THL P RETTUNG H / T)-";

		msg = "Am Mittleren Moos 60   *  Hammerschm-A * EPN 187 * B1710BMA (B BMA)-";

		msg = "Bautzener Straße 7 h  *  LechhausenO-A *  * T2728Rettung Kl Tier (THL 1)-";

		msg = "Schloßgartenstraße 23  EFH *  Bärenkeller-A *  * T2722Wasser i Gebäud (THL 1)-";
	}

}
