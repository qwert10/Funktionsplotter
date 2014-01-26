package model;
import static org.junit.Assert.*;

import org.junit.Test;


public class FktBerechnungenTest {

	Eingabepruefung f = new Eingabepruefung();
	@Test
	public void testDoppelteZeichen() {
		assertTrue(f.doppelteZeichen("(("));
		assertFalse(f.doppelteZeichen("++"));
		assertFalse(f.doppelteZeichen("-/"));
		assertTrue(f.doppelteZeichen("  "));
		assertTrue(f.doppelteZeichen("+ "));
		assertFalse(f.doppelteZeichen("y=5x^^3"));
		assertFalse(f.doppelteZeichen("=="));
		assertFalse(f.doppelteZeichen("-="));
		assertFalse(f.doppelteZeichen("-^"));
		assertTrue(f.doppelteZeichen("^-"));
	}


}
