import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class testStanza {

	private Stanza stanza;
	@BeforeEach
	void setUp() {
		this.stanza=new Stanza("test");
		this.stanza.impostaStanzaAdiacente("est", new Stanza("est"));
		this.stanza.addAttrezzo(new Attrezzo("testatt", 2));
	}
	
	
	@Test
	void testNome() {
		assertTrue(this.stanza.getNome().equals("test"));
	}
	
	@Test 
	void testGetDirezioni() {
		String[] dirtest = this.stanza.getDirezioni();
		assertNotNull(dirtest);
		assertTrue(dirtest.length==1);
		assertEquals(dirtest[0],"est");
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
}
