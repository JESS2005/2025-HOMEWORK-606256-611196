import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class testStanzaBuia {

	private Stanza stanza;
	@BeforeEach
	void setUp() {
		this.stanza=new StanzaBuia("test", "lanterna");
	}
	
	
	@Test
	void testBuio() {
		assertEquals(this.stanza.getDescrizione(),"qui c'è buio pesto, devi portare: lanterna");
	}
	
	@Test
	void testNonBuio() {
		this.stanza.addAttrezzo(new Attrezzo("lanterna",5));
		assertNotEquals(this.stanza.getDescrizione(),"qui c'è buio pesto, devi portare: lanterna");
	}
}

