package it.uniroma3.diadia;

import java.io.FileNotFoundException;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
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

	/*
	 * private boolean processaIstruzione(String istruzione) { Comando
	 * comandoDaEseguire = new Comando(istruzione);
	 * 
	 * if (comandoDaEseguire.getNome().equals("fine")) { this.fine(); return true; }
	 * else if (comandoDaEseguire.getNome().equals("vai"))
	 * this.vai(comandoDaEseguire.getParametro()); else if
	 * (comandoDaEseguire.getNome().equals("aiuto")) this.aiuto(); else if
	 * (comandoDaEseguire.getNome().equals("prendi"))
	 * this.prendi(comandoDaEseguire.getParametro()); else if
	 * (comandoDaEseguire.getNome().equals("posa"))
	 * this.posa(comandoDaEseguire.getParametro()); else
	 * this.stampe.mostraMessaggio("Comando sconosciuto");
	 * 
	 * if (this.partita.vinta()) { this.stampe.mostraMessaggio("Hai vinto!"); return
	 * true; } if(this.partita.isFinita() ) {
	 * this.stampe.mostraMessaggio("Hai perso!"); return true; }else return false; }
	 * 
	 */

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */



	/**
	 * Comando "Fine".
	 */
	private void fine() {
		this.stampe.mostraMessaggio("Grazie di aver giocato!"); // si desidera smettere
	}

	private void prendi(String nome) {
		if (nome != null) {
			Attrezzo att = partita.getStanzaCorrente().getAttrezzo(nome);
			if (att != null) {
				if (partita.getGiocatore().getBorsa().addAttrezzo(att)) {
					partita.getStanzaCorrente().removeAttrezzo(att);
					this.stampe.mostraMessaggio("Hai preso: " + nome);
				} else {
					this.stampe.mostraMessaggio("Borsa piena");
				}
			} else {
				this.stampe.mostraMessaggio("Niente " + nome);
			}
		}
	}

	private void posa(String nome) {
		if (nome != null) {
			if (!partita.getStanzaCorrente().isPiena()) {
				Attrezzo att = partita.getGiocatore().getBorsa().removeAttrezzo(nome);
				if (att != null) {
					partita.getStanzaCorrente().addAttrezzo(att);
					this.stampe.mostraMessaggio("Hai posato: " + nome);
				} else {
					this.stampe.mostraMessaggio("Niente " + nome);
				}
			} else {
				this.stampe.mostraMessaggio("La stanza è piena");
			}
		}
	}

	public static void main(String[] argc) throws FileNotFoundException, FormatoFileNonValidoException {
		IO io = new IOConsole();
		CaricatoreLabirinto CarLab= new CaricatoreLabirinto("labirinti/Lab.txt");
		CarLab.carica();
		LabirintoBuilder builder = new LabirintoBuilder(CarLab);
		Labirinto labirinto=builder.getLabirinto();
		//Labirinto labirinto = LabirintoBuilder.labirintoBase().getLabirinto();
		DiaDia gioco = new DiaDia(labirinto, io);
		gioco.gioca();

	}
}