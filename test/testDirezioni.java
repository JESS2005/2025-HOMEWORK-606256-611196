 
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;


public class testDirezioni {
	@BeforeEach
	void setUp() {
	}
	
	@Test
	void testOpposto() {
		Direzione dir=Direzione.NORD;
		assertEquals(Direzione.SUD,dir.opposta());
		dir=Direzione.EST;
		assertNotEquals(Direzione.SUD,dir.opposta());
	}

}
