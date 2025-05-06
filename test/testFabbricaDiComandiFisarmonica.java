import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.ComandoVai;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;

public class testFabbricaDiComandiFisarmonica {

	FabbricaDiComandiFisarmonica factory;
	@BeforeEach
	void setUp() {
		factory= new FabbricaDiComandiFisarmonica();
	}
	
	
	@Test
	void testAiuto() {
		Comando comando=factory.costruisciComando("aiuto");
		assertEquals(comando.getNome(),"Aiuto");
	}
	
	@Test
	void testFine() {
		Comando comando=factory.costruisciComando("fine");
		assertEquals(comando.getNome(),"Fine");
	}
	
	@Test
	void testGuarda() {
		Comando comando=factory.costruisciComando("guarda");
		assertEquals(comando.getNome(),"Guarda");
	}
	
	@Test
	void testNonValido() {
		Comando comando=factory.costruisciComando("");
		assertEquals(comando.getNome(),"NonValido");
	}
	
	@Test
	void testNonValido2() {
		Comando comando=factory.costruisciComando("pino daniele");
		assertEquals(comando.getNome(),"NonValido");
	}
	
	@Test
	void testPosa() {
		Comando comando=factory.costruisciComando("posa fiasco");
		assertEquals(comando.getNome(),"Posa");
		assertEquals(comando.getParametro(),"fiasco");
	}
	
	@Test
	void testPrendi() {
		Comando comando=factory.costruisciComando("prendi fuoco");
		assertEquals(comando.getNome(),"Prendi");
		assertEquals(comando.getParametro(),"fuoco");
	}
	
	@Test
	void testVai() {
		Comando comando=factory.costruisciComando("vai luca");
		assertEquals(comando.getNome(),"Vai");
		assertEquals(comando.getParametro(),"luca");
	}
}