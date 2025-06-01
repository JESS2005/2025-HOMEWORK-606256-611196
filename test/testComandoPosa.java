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
import it.uniroma3.diadia.comandi.ComandoPosa;
import it.uniroma3.diadia.comandi.ComandoPrendi;
import it.uniroma3.diadia.comandi.ComandoVai;

public class testComandoPosa {

	Partita partita;
	Comando comando1 = null;
	Comando comando2 = null;
	IO io;
	@BeforeEach
	void setUp() {
		partita=new Partita(LabirintoBuilder.labirintoTest().getLabirinto());
		comando1 = new ComandoPrendi();
		comando2 = new ComandoPosa();
		io=new IOConsole();
	}
	
	@Test
	void testPosa() {
		comando1.setParametro("osso");
		comando1.esegui(partita, io);
		comando2.setParametro("osso");
		comando2.esegui(partita, io);
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("osso"));
		
	}	
	
	@Test
	void testNonPosa() {
		comando1.setParametro("osso");
		comando1.esegui(partita, io);
		comando2.setParametro("jojo");
		comando2.esegui(partita, io);
		assertFalse(partita.getStanzaCorrente().hasAttrezzo("jojo"));
		
	}	
}