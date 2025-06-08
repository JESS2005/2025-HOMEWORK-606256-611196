import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.ambienti.Labirinto;

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
		DiaDia gioco = new DiaDia(Labirinto.LabirintoBuilder.labirintoTest().getLabirinto(),io);
		gioco.gioca();

		String[] expected= {"ignora",
				"Aula N10\n"+ "Uscite: nord est ovest\n" + "Attrezzi nella stanza: lanterna (3kg)",
				"Hai preso: lanterna",
				"Atrio\n"+ "Uscite: nord est sud ovest\n"+ "Attrezzi nella stanza: osso (1kg)",
				"Hai preso: osso",
				"Hai posato: lanterna",
				"ignora",
				"Hai vinto!"};
		int i=0;
		while (io.getLetti()[i]!=null && expected[i]!=null) {
			
			if (!("ignora".equals(expected[i])))
				assertEquals(expected[i],io.getLetti()[i],i+" ");
			i++;
		}
	}
	
	@Test
	void testCane() {
		String[] comandi= {"prendi osso","vai sud","prendi lanterna","saluta","regala osso","interagisci","regala lanterna","fine"};
		IOSimulator io= new IOSimulator(comandi,40);
		DiaDia gioco = new DiaDia(Labirinto.LabirintoBuilder.labirintoBase().getLabirinto(),io);
		gioco.gioca();

		String[] expected= {"ignora",
				"Hai preso: osso",
				"Aula N10\n"+ "Uscite: nord est ovest\n" + "Attrezzi nella stanza: lanterna (3kg)\n"+"Vedi un Cane",
				"Hai preso: lanterna",
				"Ciao, io sono cane magico. Miao",
				"chip chip :)",
				"Il cane ti rapina, ti prende un CFU, te ne rimangono 18",
				"ROOOAAARR!!!",
				"Grazie di aver giocato!"};
		int i=0;
		while (io.getLetti()[i]!=null && expected[i]!=null) {
			
			if (!("ignora".equals(expected[i]))) 
				assertEquals(expected[i],io.getLetti()[i],i+" ");
			i++;
		}
	}
	
	@Test
	void testMago() {
		String[] comandi= {"prendi osso","vai ovest","saluta","regala osso","interagisci","guarda","fine"};
		IOSimulator io= new IOSimulator(comandi,40);
		DiaDia gioco = new DiaDia(Labirinto.LabirintoBuilder.labirintoBase().getLabirinto(),io);
		gioco.gioca();

		String[] expected= {"ignora",
				"Hai preso: osso",
				"Laboratorio Campus\n"+ "Uscite: est ovest\n" + "Attrezzi nella stanza:\n"+"Vedi un Mago",
				"Ciao, io sono Merlone il mago burlone. Ciao",
				"Il tuo osso fa schifo!!!",
				"Sei un vero simpaticone, con una mia magica azione, troverai un nuovo oggetto per il tuo borsone!",
				"Laboratorio Campus\n"+ "Uscite: est ovest\n" + "Attrezzi nella stanza: osso (0kg) bastone (2kg)\n"+"Vedi Merlone il mago burlone\n" + "CFU: 19\n" + "Borsa vuota",
				"Grazie di aver giocato!"};
		int i=0;
		while (io.getLetti()[i]!=null && expected[i]!=null) {
			
			if (!("ignora".equals(expected[i]))) 
				assertEquals(expected[i],io.getLetti()[i],i+" ");
			i++;
		}
	}
	
	@Test
	void testStrega() {
		String[] comandi= {"prendi osso","vai est","saluta","regala osso","interagisci","guarda","fine"};
		IOSimulator io= new IOSimulator(comandi,40);
		DiaDia gioco = new DiaDia(Labirinto.LabirintoBuilder.labirintoBase().getLabirinto(),io);
		gioco.gioca();

		String[] expected= {"ignora",
				"Hai preso: osso",
				"qui c'è buio pesto, devi portare: lanterna",
				"Ciao, io sono Amelia la strega. 你好",
				"AAAAAHAHAHAGHAGSHAGHOHOHOHOHOOH!!!!!!",
				"ignora",
				"Laboratorio Campus\n"+ "Uscite: est ovest\n" + "Attrezzi nella stanza:\n"+"Vedi un Mago\n" + "CFU: 19\n" + "Borsa vuota",
				"Grazie di aver giocato!"};
		int i=0;
		while (io.getLetti()[i]!=null && expected[i]!=null) {
			
			if (!("ignora".equals(expected[i]))) 
				assertEquals(expected[i],io.getLetti()[i],i+" ");
			i++;
		}
	}
	
	@Test
	void testConChiusa() {
		String[] comandi= {"prendi osso","vai sud","vai ovest","posa osso","vai ovest", "fine"};
		IOSimulator io= new IOSimulator(comandi,40);
		DiaDia gioco = new DiaDia(Labirinto.LabirintoBuilder.labirintoTest().getLabirinto(),io);
		gioco.gioca();

		String[] expected= {"ignora",
				"ignora",
				"ignora",
				"Aula N10\n"+ "Uscite: nord est ovest\n" + "Attrezzi nella stanza: lanterna (3kg)",
				"Hai posato: osso",
				"Laboratorio Campus\n"+ "Uscite: est ovest\n"+ "Attrezzi nella stanza:",
				"Grazie di aver giocato!"};
		int i=0;
		while (io.getLetti()[i]!=null && expected[i]!=null) {
			
			if (!("ignora".equals(expected[i])))
				assertEquals(expected[i],io.getLetti()[i],i+" ");
			i++;
		}
	}
	
	@Test
	void testConBuia() {
		String[] comandi= {"vai sud","prendi lanterna","vai est","posa lanterna","guarda", "fine"};
		IOSimulator io= new IOSimulator(comandi,40);
		DiaDia gioco = new DiaDia(Labirinto.LabirintoBuilder.labirintoTest().getLabirinto(),io);
		gioco.gioca();
	
		String[] expected= {"ignora",
				"ignora",
				"ignora",
				"qui c'è buio pesto, devi portare: lanterna",
				"ignora",
				"Aula N11\n"+ "Uscite: est ovest\n"+ "Attrezzi nella stanza: lanterna (3kg)\n"+"CFU: 18\n"+"Borsa vuota",
				"Grazie di aver giocato!"};
		int i=0;
		while (io.getLetti()[i]!=null && expected[i]!=null) {
			
			if (!("ignora".equals(expected[i])))
				assertEquals(expected[i],io.getLetti()[i],i+" ");
			i++;
		}
	}		

	@Test
	void testConMagica() {
		String[] comandi= {"vai sud","prendi lanterna","vai est","vai est","posa lanterna","prendi lanterna","posa lanterna","prendi lanterna","posa lanterna","prendi lanterna","posa lanterna","prendi lanterna","prendi anretnal", "fine"};
		IOSimulator io= new IOSimulator(comandi,40);
		DiaDia gioco = new DiaDia(Labirinto.LabirintoBuilder.labirintoTest().getLabirinto(),io);
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
				assertEquals(expected[i],io.getLetti()[i],i+" ");
			i++;
		}
	}
}
