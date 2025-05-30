 
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.FakePersonaggio;


public class testAbstractPersonaggio {
	private AbstractPersonaggio pers;
	@BeforeEach
	void setUp() {
		this.pers=new FakePersonaggio("a","b");
	}
	
	@Test
	void testNome() {
		assertEquals("a",this.pers.getNome());
	}
	
	@Test
	void testSaluta() {
		assertFalse(this.pers.haSalutato());
		assertEquals("Ciao, io sono a. b",this.pers.saluta());
		assertTrue(this.pers.haSalutato());
	}
	
	@Test
	void testAgisci() {
		assertEquals("done",this.pers.agisci(new Partita()));
	}

}
