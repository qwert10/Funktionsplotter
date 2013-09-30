import static org.junit.Assert.*;

import org.junit.Test;


public class FktBerechnungenTest {

	@Test
	public void testDoppelteZeichen() {
		FktBerechnungen f = new FktBerechnungen();
		assertTrue(f.doppelteZeichen("(("));
		assertFalse(f.doppelteZeichen("++"));
		assertFalse(f.doppelteZeichen("-/"));
		assertTrue(f.doppelteZeichen("  "));
		assertTrue(f.doppelteZeichen("+ "));
	}

}
