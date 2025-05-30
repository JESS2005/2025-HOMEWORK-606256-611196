import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;

class testInteraPartita {
	
	private void printArr(String[] Arr) {
		int i=0;
		while (Arr[i]!=null) {
			System.out.println(Arr[i]);
			System.out.println("");
			i++;
		}
	}
	

	@Test
	void test1() {
		String[] comandi= {"vai sud","prendi lanterna","vai nord","prendi osso","posa lanterna", "vai nord"};
		IOSimulator io= new IOSimulator(comandi,40);
		DiaDia gioco = new DiaDia(LabirintoBuilder.labirintoBase().getLabirinto(),io);
		gioco.gioca();

		String[] expected= {"ignora",
				"Aula N10\n"+ "Uscite: nord est ovest\n" + "Attrezzi nella stanza: lanterna (3kg)\n"+"Vedi un Cane",
				"Hai preso: lanterna",
				"Atrio\n"+ "Uscite: nord sud est ovest\n"+ "Attrezzi nella stanza: osso (1kg)",
				"Hai preso: osso",
				"Hai posato: lanterna",
				"ignora",
				"Hai vinto!"};
		int i=0;
		while (io.getLetti()[i]!=null && expected[i]!=null) {
			
			if (!("ignora".equals(expected[i])))
				assertEquals(expected[i],io.getLetti()[i]);
			i++;
		}
	}
	
	@Test
	void testConChiusa() {
		String[] comandi= {"prendi osso","vai sud","vai ovest","posa osso","vai ovest", "fine"};
		IOSimulator io= new IOSimulator(comandi,40);
		DiaDia gioco = new DiaDia(LabirintoBuilder.labirintoBase().getLabirinto(),io);
		gioco.gioca();

		String[] expected= {"ignora",
				"ignora",
				"ignora",
				"Aula N10\n"+ "Uscite: nord est ovest\n" + "Attrezzi nella stanza: lanterna (3kg)\n"+"Vedi un Cane",
				"Hai posato: osso",
				"Laboratorio Campus\n"+ "Uscite: est ovest\n"+ "Attrezzi nella stanza:\nVedi un Mago",
				"Grazie di aver giocato!"};
		int i=0;
		while (io.getLetti()[i]!=null && expected[i]!=null) {
			
			if (!("ignora".equals(expected[i])))
				assertEquals(expected[i],io.getLetti()[i]);
			i++;
		}
	}
	
	@Test
	void testConBuia() {
		String[] comandi= {"vai sud","prendi lanterna","vai est","posa lanterna","guarda", "fine"};
		IOSimulator io= new IOSimulator(comandi,40);
		DiaDia gioco = new DiaDia(LabirintoBuilder.labirintoBase().getLabirinto(),io);
		gioco.gioca();
	
		String[] expected= {"ignora",
				"ignora",
				"ignora",
				"qui c'è buio pesto, devi portare: lanterna",
				"ignora",
				"Aula N11\n"+ "Uscite: est ovest\n"+ "Attrezzi nella stanza: lanterna (3kg)\n"+"Vedi un Strega\n"+"CFU: 18\n"+"Borsa vuota",
				"Grazie di aver giocato!"};
		int i=0;
		while (io.getLetti()[i]!=null && expected[i]!=null) {
			
			if (!("ignora".equals(expected[i])))
				assertEquals(expected[i],io.getLetti()[i]);
			i++;
		}
	}		

	@Test
	void testConMagica() {
		String[] comandi= {"vai sud","prendi lanterna","vai est","vai est","posa lanterna","prendi lanterna","posa lanterna","prendi lanterna","posa lanterna","prendi lanterna","posa lanterna","prendi lanterna","prendi anretnal", "fine"};
		IOSimulator io= new IOSimulator(comandi,40);
		DiaDia gioco = new DiaDia(LabirintoBuilder.labirintoBase().getLabirinto(),io);
		gioco.gioca();
		
		String[] expected= {"ignora",
				"ignora",
				"ignora",
				"qui c'è buio pesto, devi portare: lanterna",
				"ignora",
				"Hai posato: lanterna",
				"Hai preso: lanterna",
				"Hai posato: lanterna",
				"Hai preso: lanterna",
				"Hai posato: lanterna",
				"Hai preso: lanterna",
				"Hai posato: lanterna",
				"Niente lanterna",
				"Hai preso: anretnal",
				"Grazie di aver giocato!"};
		int i=0;
		while (io.getLetti()[i]!=null && expected[i]!=null) {
			
			if (!("ignora".equals(expected[i])))
				assertEquals(expected[i],io.getLetti()[i]);
			i++;
		}
	}
}
