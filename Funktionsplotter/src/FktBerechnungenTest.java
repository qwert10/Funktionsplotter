import static org.junit.Assert.*;

import org.junit.Test;


public class FktBerechnungenTest {

	FktBerechnungen f = new FktBerechnungen();
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
	
	@Test
	public void klammernTest()
	{
		assertTrue(f.klammernPruefen("(())"));
		assertFalse(f.klammernPruefen("("));
		assertFalse(f.klammernPruefen("834jfdifi3(sdsdsds))"));
		
	}

	@Test
	public void variablenTest()
	{
		assertTrue(f.variablenPruefung("x"));
		assertFalse(f.variablenPruefung("xx"));
		assertFalse(f.variablenPruefung("xy"));
		assertTrue(f.variablenPruefung("y=5x^2+3x"));
		assertFalse(f.variablenPruefung("5x+3xy+2"));
		
	}

}
