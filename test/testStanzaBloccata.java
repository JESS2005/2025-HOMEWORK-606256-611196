import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class testStanzaBloccata {

	private StanzaBloccata stanza;
	@BeforeEach
	void setUp() {
		this.stanza=new StanzaBloccata("test","chiave", "est");
		this.stanza.impostaStanzaAdiacente("est", new Stanza("est"));
	}
	
	@Test
	void testBlocco() {
		assertEquals(this.stanza,this.stanza.getStanzaAdiacente("est"));
	}
	
	
	@Test
	void testNonBlocco() {
		this.stanza.addAttrezzo(new Attrezzo("chiave",5));
		assertNotEquals(this.stanza,this.stanza.getStanzaAdiacente("est"));
	}
}

