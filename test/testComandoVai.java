import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.ComandoVai;

public class testComandoVai {

	Partita partita;
	Comando comando = null;
	IO io;
	@BeforeEach
	void setUp() {
		partita=new Partita(LabirintoBuilder.labirintoTest().getLabirinto());
		comando = new ComandoVai();
		io=new IOConsole();
	}
	
	
	@Test
	void testVai() {
		comando.setParametro("luca");
		comando.esegui(partita, io);
		assertEquals(partita.getStanzaCorrente(),partita.getLabirinto().getStanzaIniziale());
	}
	
	@Test
	void testNonVai() {
		comando.setParametro("sud");
		comando.esegui(partita, io);
		assertNotEquals(partita.getStanzaCorrente(),partita.getLabirinto().getStanzaIniziale());
	}

}
