package it.uniroma3.diadia;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {

	static final private int CFU_INIZIALI = 20;

	private Stanza stanzaCorrente;
	private boolean finita;
	private Labirinto labirinto;
	private Giocatore giocatore;
	
	public Partita(){
		this.labirinto= new Labirinto();
		this.setStanzaCorrente(labirinto.getStanzaIniziale());
		this.finita = false;
		this.giocatore = new Giocatore(CFU_INIZIALI);
	}
	
	public Partita(Labirinto labirinto){
		this.labirinto=labirinto;
		this.setStanzaCorrente(labirinto.getStanzaIniziale());
		this.finita = false;
		this.giocatore = new Giocatore(CFU_INIZIALI);
	}

    /**
     * Crea tutte le stanze e le porte di collegamento
     */
    

	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}
	
	public Giocatore getGiocatore() {
		return this.giocatore;
	}
	
	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.getStanzaCorrente()== labirinto.getStanzaVincente();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || (giocatore.getCfu() == 0);
	}

	public boolean isFinitaComando() {
		return finita;
	}
	
	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}

	public Labirinto getLabirinto() {
		return this.labirinto;
	}
}
