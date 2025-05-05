import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaMagica;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class testStanzaMagica {

	private Stanza stanza;
	@BeforeEach
	void setUp() {
		this.stanza=new StanzaMagica("test", 3);
	}
	
	
	@Test
	void testMagia() {
		Attrezzo attrezzo=new Attrezzo("alucard", 5);
		this.stanza.addAttrezzo(attrezzo);
		assertNotNull(this.stanza.getAttrezzo("alucard"));
		this.stanza.removeAttrezzo(attrezzo);
		this.stanza.addAttrezzo(attrezzo);
		assertNotNull(this.stanza.getAttrezzo("alucard"));
		this.stanza.removeAttrezzo(attrezzo);
		this.stanza.addAttrezzo(attrezzo);
		assertNotNull(this.stanza.getAttrezzo("alucard"));
		this.stanza.removeAttrezzo(attrezzo);
		this.stanza.addAttrezzo(attrezzo);
		assertNull(this.stanza.getAttrezzo("alucard"));
		assertNotNull(this.stanza.getAttrezzo("dracula"));
		assertEquals(10, this.stanza.getAttrezzo("dracula").getPeso());
		Attrezzo attrezzo2= this.stanza.getAttrezzo("dracula");
		this.stanza.removeAttrezzo(attrezzo2);
		this.stanza.addAttrezzo(attrezzo2);
		assertNull(this.stanza.getAttrezzo("dracula"));
		assertNotNull(this.stanza.getAttrezzo("alucard"));
		assertEquals(20, this.stanza.getAttrezzo("alucard").getPeso());
		
	}
	
}

