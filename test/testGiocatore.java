import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.giocatore.Giocatore;


public class testGiocatore {
	private Giocatore giocatore;
	
	@BeforeEach
	void setUp() {
		giocatore=new Giocatore(20);
	}
	
	@Test
	void testGetCfu() {
		assertEquals(this.giocatore.getCfu(),20);
	}
	
	@Test
	void testSetCfu() {
		this.giocatore.setCfu(10);
		assertEquals(this.giocatore.getCfu(),10);
	}
	
	@Test
	void testBorsa() {
		assertNotNull(this.giocatore.getBorsa());
	}
}
