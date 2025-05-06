import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.IOSimulator;

class testInteraPartita {

	@Test
	void test() {
		String[] comandi= {"vai sud","prendi lanterna","vai nord","prendi osso","posa lanterna", "vai nord"};
		IOSimulator io= new IOSimulator(comandi,40);
		DiaDia gioco = new DiaDia(io);
		gioco.gioca();
		int i=0;
		while (io.getLetti()[i]!=null) {
			System.out.println(io.getLetti()[i]);
			i++;
		}
	}

}
