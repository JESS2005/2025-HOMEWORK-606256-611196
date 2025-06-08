package it.uniroma3.diadia;

import java.io.FileNotFoundException;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author docente di POO (da un'idea di Michael Kolling and David J. Barnes)
 * 
 * @version base
 */

public class DiaDia {

	private IO stampe;

	static final private String MESSAGGIO_BENVENUTO = ""
			+ "Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n"
			+ "Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"
			+ "I locali sono popolati da strani personaggi, " + "alcuni amici, altri... chissa!\n"
			+ "Ci sono attrezzi che potrebbero servirti nell'impresa:\n"
			+ "puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n"
			+ "o regalarli se pensi che possano ingraziarti qualcuno.\n\n"
			+ "Per conoscere le istruzioni usa il comando 'aiuto'.";

	
	private Partita partita;
	private FabbricaDiComandi factory;

	public DiaDia(IO io) {
		this.partita = new Partita();
		this.stampe = io;
		this.factory = new FabbricaDiComandiRiflessiva();
	}

	public DiaDia(Labirinto labirinto, IO io) {
		this.partita = new Partita(labirinto);
		this.stampe = io;
		this.factory = new FabbricaDiComandiRiflessiva();
	}

	public void gioca() {
		String istruzione;

		this.stampe.mostraMessaggio(MESSAGGIO_BENVENUTO);
		do
			istruzione = stampe.leggiRiga();
		while (!processaIstruzione(istruzione));
	}

	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	
	private boolean processaIstruzione (String istruzione) {
		try {
		Comando comando = this.factory.costruisciComando(istruzione);
		comando.esegui(partita, stampe);
		if (this.partita.vinta()) {
			this.stampe.mostraMessaggio("Hai vinto!");
			return true;
		} if(this.partita.isFinitaComando() ) {
			return true;
		} if(this.partita.isFinita() ) {
			this.stampe.mostraMessaggio("Hai perso!");
			return true;
		}else
			return false;
		} catch (Exception e) {
			this.stampe.mostraMessaggio("Comando non valido");
			return false;
		}
		
		
	}



	public static void main(String[] argc) throws FileNotFoundException, FormatoFileNonValidoException {
		IOConsole ioC=new IOConsole();
		IO io = ioC;
		CaricatoreLabirinto CarLab= new CaricatoreLabirinto("diadia.labirinth");
		new CaricatoreProprieta("diadia.properties").carica();
		CarLab.carica();
		Labirinto.LabirintoBuilder builder = new Labirinto.LabirintoBuilder(CarLab);
		Labirinto labirinto=builder.getLabirinto();
		//Labirinto labirinto = LabirintoBuilder.labirintoBase().getLabirinto();
		DiaDia gioco = new DiaDia(labirinto, io);
		gioco.gioca();
		ioC.close();

	}
}