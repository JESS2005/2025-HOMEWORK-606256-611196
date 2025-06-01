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
import it.uniroma3.diadia.comandi.ComandoPrendi;
import it.uniroma3.diadia.comandi.ComandoVai;

public class testComandoPrendi {

	Partita partita;
	Comando comando = null;
	IO io;
	@BeforeEach
	void setUp() {
		partita=new Partita(LabirintoBuilder.labirintoTest().getLabirinto());
		comando = new ComandoPrendi();
		io=new IOConsole();
	}
	
	@Test
	void testPrendi() {
		comando.setParametro("osso");
		comando.esegui(partita, io);
		assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo("osso"));
		
	}	
	
	@Test
	void testNonPrendi() {
		comando.setParametro("GIORGIO");
		comando.esegui(partita, io);
		assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo("GIORGIO"));
		
	}	
}