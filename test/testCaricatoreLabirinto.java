 
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.StringReader;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.CaricatoreLabirinto;
import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.FakePersonaggio;


public class testCaricatoreLabirinto {
	@BeforeEach
	void setUp() {
	}

	@Test
	void testThrowing() {
		String lab1="Stanze:biblioteca\n" +
                "Stanze buie:\n" +
                "Stanze magiche:\n" +
                "Stanze bloccate:\n" +
                "Inizio:biblioteca";
                
        String lab2="\n" +
                "Vincente:biblioteca\n" +
                "Attrezzi:\n" +
                "Personaggi:\n" +
                "Uscite:";

		
		assertThrows(
				ArrayIndexOutOfBoundsException.class,
		        () -> { 
		        	CaricatoreLabirinto lab=new CaricatoreLabirinto(new StringReader("Stanze: biblioteca"));
		        	lab.carica();
		        }
		    );
		
		assertThrows(
				FormatoFileNonValidoException.class,
		        () -> { 
		        	CaricatoreLabirinto lab=new CaricatoreLabirinto(new StringReader(lab1+"A"+lab2));
		        	lab.carica();
		        }
		    );
		
		assertDoesNotThrow(() -> {
			CaricatoreLabirinto lab=new CaricatoreLabirinto(new StringReader(lab1+lab2));
        	lab.carica();
	    });
	}
	
	
	@Test
	void testStanze() {
		String labS="Stanze:biblioteca,atrio\n" +
                "Stanze buie:\n" +
                "Stanze magiche:\n" +
                "Stanze bloccate:\n" +
                "Inizio:atrio\n" +
                "Vincente:biblioteca\n" +
                "Attrezzi:osso-2-atrio\n" +
                "Personaggi:\n" +
                "Uscite:,atrio-ovest-biblioteca,";
		CaricatoreLabirinto lab=null;
		try {
			lab=new CaricatoreLabirinto(new StringReader(labS));
		} catch(FileNotFoundException e) {
			
		}
		try {
			lab.carica();
		} catch(FormatoFileNonValidoException e) {
			
		}
		Labirinto labirinto=new Labirinto.LabirintoBuilder(lab).getLabirinto();
		assertEquals(2,labirinto.getMappa().size());
		assertEquals(new Attrezzo("osso",2),labirinto.getMappa().get("atrio").getAttrezzo("osso"));
		assertEquals("OVEST",labirinto.getMappa().get("atrio").getDirezioni().getFirst());
	}
}
