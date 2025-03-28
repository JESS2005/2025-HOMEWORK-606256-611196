import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class testPartita {

	private Partita partita;
	@BeforeEach
	void setUp() {
		this.partita=new Partita();
	}
	
	
	@Test
	void testSetGetStanza() {
		Stanza TestSt=new Stanza("test");
		this.partita.setStanzaCorrente(TestSt);
		assertTrue(partita.getStanzaCorrente()==TestSt);
	}
	
	@Test 
	void testFinita() {
		assertFalse(partita.isFinita());
		this.partita.setFinita();
		assertTrue(partita.isFinita());
	}
	
	@Test 
	void testVinta() {
		Stanza TestSt=new Stanza("test");
		this.partita.setStanzaCorrente(TestSt);
		assertFalse(partita.vinta());
		this.partita.setStanzaCorrente(partita.getLabirinto().getStanzaVincente());
		assertTrue(partita.vinta());
	}
}
