import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;


public class testLabirinto {
	private Labirinto labirinto;
	@BeforeEach
	void setUp() {
		this.labirinto=LabirintoBuilder.labirintoTest().getLabirinto();
	}
	
	@Test
	void testStanzaInizale() {
		assertNotNull(this.labirinto.getStanzaIniziale());
	}
	@Test
	void testStanzaFinale() {
		assertNotNull(this.labirinto.getStanzaVincente());
	}
}
