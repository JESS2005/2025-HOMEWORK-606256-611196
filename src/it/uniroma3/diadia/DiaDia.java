package it.uniroma3.diadia;



import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	private IO stampe;
	
	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine","prendi","posa","guarda"};

	private Partita partita;
    private FabbricaDiComandi factory;
	
	
	public DiaDia(IO io) {
		this.partita = new Partita();
		this.stampe = io;
		this.factory= new FabbricaDiComandiFisarmonica();
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
		
	}
	
	
	/*
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);

		if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine(); 
			return true;
		} else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		else if (comandoDaEseguire.getNome().equals("prendi"))
			this.prendi(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("posa"))
			this.posa(comandoDaEseguire.getParametro());
		else
			this.stampe.mostraMessaggio("Comando sconosciuto");
		
		if (this.partita.vinta()) {
			this.stampe.mostraMessaggio("Hai vinto!");
			return true;
		} if(this.partita.isFinita() ) {
			this.stampe.mostraMessaggio("Hai perso!");
			return true;
		}else
			return false;
	}   
	
	*/

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		String niente = "";
		for(int i=0; i< elencoComandi.length; i++) 
			niente += (elencoComandi[i]+" ");
		this.stampe.mostraMessaggio(niente);
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null)
			this.stampe.mostraMessaggio("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			this.stampe.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getGiocatore().getCfu();
			//this.stampe.mostraMessaggio(partita.getGiocatore().getCfu());
			this.partita.getGiocatore().setCfu(--cfu);
		}
		this.stampe.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		this.stampe.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}
	
	private void prendi(String nome) {
		if(nome!=null) {
			Attrezzo att=partita.getStanzaCorrente().getAttrezzo(nome);
			if (att!=null) {
				if (partita.getGiocatore().getBorsa().addAttrezzo(att)) {
					partita.getStanzaCorrente().removeAttrezzo(att);
					this.stampe.mostraMessaggio("Hai preso: "+nome);
				} else {
					this.stampe.mostraMessaggio("Borsa piena");
				}
			} else {
				this.stampe.mostraMessaggio("Niente "+nome);
			}
		}
	}
	
	private void posa(String nome) {
		if(nome!=null) {
			if (!partita.getStanzaCorrente().isPiena()) {
				Attrezzo att=partita.getGiocatore().getBorsa().removeAttrezzo(nome);
				if (att!=null) {
					partita.getStanzaCorrente().addAttrezzo(att);
					this.stampe.mostraMessaggio("Hai posato: "+nome);
				} else {
					this.stampe.mostraMessaggio("Niente "+nome);
				}
			} else {
				this.stampe.mostraMessaggio("La stanza è piena");
			}
		}
	}

	public static void main(String[] argc) {
		IO io= new IOConsole();
		DiaDia gioco = new DiaDia(io);
		gioco.gioca();
		 
	}
}