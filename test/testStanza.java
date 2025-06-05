import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.SortedSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class testStanza {

	private Stanza stanza;
	@BeforeEach
	void setUp() {
		this.stanza=new Stanza("test");
		this.stanza.impostaStanzaAdiacente(Direzione.EST, new Stanza("est"));
		this.stanza.addAttrezzo(new Attrezzo("testatt", 2));
	}
	
	
	@Test
	void testNome() {
		assertTrue(this.stanza.getNome().equals("test"));
	}
	
	@Test 
	void testGetDirezioni() {
		List<String> dirtest = this.stanza.getDirezioni();
		assertNotNull(dirtest);
		assertTrue(dirtest.size()==1);
		assertEquals(dirtest.get(0),"est");
	}
	
	@Test
	void testGetAttrezzo() {
		assertNotNull(this.stanza.getAttrezzo("testatt"));
		assertNull(this.stanza.getAttrezzo("ciao"));
	}
	
	@Test
	void testRemoveAttrezzo() {
		Attrezzo a=this.stanza.getAttrezzo("testatt");
		assertTrue(this.stanza.removeAttrezzo(a));
		assertFalse(this.stanza.hasAttrezzo("testatt"));
	}
	
	@Test
	void testAddAttrezzo() {
		assertTrue(this.stanza.addAttrezzo(new Attrezzo("testatt2", 5)));
		assertNotNull(this.stanza.hasAttrezzo("testatt2"));
	}
	
	@Test
	void testTantiAttrezzi() {
		for (int i=1;i<10;i++) {
			//uno gia presente dal setup
			assertTrue(this.stanza.addAttrezzo(new Attrezzo("testatt"+i, i)));
		}
		assertFalse(this.stanza.addAttrezzo(new Attrezzo("testatt10",1)));
		for (int i=4;i<7;i++) {
			assertTrue(this.stanza.removeAttrezzo(this.stanza.getAttrezzo("testatt"+i)));
		}
		for (int i=4;i<7;i++) {
			assertTrue(this.stanza.addAttrezzo(new Attrezzo("testatt"+i, i)));
		}
		assertFalse(this.stanza.addAttrezzo(new Attrezzo("testatt10",1)));
	}
	
	@Test
	void testSortAdiacenti() {
		Stanza ov=new Stanza("ovest");
		this.stanza.impostaStanzaAdiacente(Direzione.OVEST, ov);
		ov.addAttrezzo(new Attrezzo("a",1));
		ov.addAttrezzo(new Attrezzo("b",1));
		Stanza n=new Stanza("nord");
		this.stanza.impostaStanzaAdiacente(Direzione.NORD, n);
		n.addAttrezzo(new Attrezzo("c",1));
		SortedSet<Stanza> ad=this.stanza.getStanzeAdiacentiPerAttrezzi();
		assertEquals(new Stanza("est"),ad.first());
		assertEquals(new Stanza("ovest"),ad.last());
	}
}
