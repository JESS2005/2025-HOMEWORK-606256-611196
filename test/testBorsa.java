import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;


public class testBorsa {
	private Borsa borsa;
	@BeforeEach
	void setUp() {
		this.borsa=new Borsa(12);
	}
	
	@Test
	void testPesoMax() {
		this.borsa=new Borsa(15);
		assertEquals(this.borsa.getPesoMax(),15);
	}
	
	@Test
	void testAddAttrezzoEmpty() {
		assertTrue(this.borsa.isEmpty());
		this.borsa.addAttrezzo(new Attrezzo("Test",2));
		assertFalse(this.borsa.isEmpty());
	}
	
	@Test
	void testPeso() {
		this.borsa.addAttrezzo(new Attrezzo("Test",2));
		assertEquals(this.borsa.getPeso(),2);
	}
	
	@Test
	void testPesoSupero() {
		this.borsa.addAttrezzo(new Attrezzo("Test",12));
		this.borsa.addAttrezzo(new Attrezzo("Test2",1));
		assertNotNull(this.borsa.getAttrezzo("Test"));
		assertNull(this.borsa.getAttrezzo("Test2"));
	}
	
	@Test
	void testHasAttrezzo() {
		this.borsa.addAttrezzo(new Attrezzo("Test",2));
		assertNotNull(this.borsa.getAttrezzo("Test"));
		assertNull(this.borsa.getAttrezzo("NotTest"));
	}
	
	@Test
	void testRemoveAttrezzo() {
		this.borsa.addAttrezzo(new Attrezzo("Test",2));
		assertNotNull(this.borsa.getAttrezzo("Test"));
		this.borsa.removeAttrezzo("Test");
		assertNull(this.borsa.getAttrezzo("Test"));
	}
}
