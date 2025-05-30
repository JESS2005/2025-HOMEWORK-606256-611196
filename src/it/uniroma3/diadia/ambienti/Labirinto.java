package it.uniroma3.diadia.ambienti;
import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Mago;

public class Labirinto {
	private Stanza stanzaVincente;
	private Stanza stanzaIniziale;
	private Map<String, Stanza> stanze = new HashMap<String,Stanza>();
	
	
	public Labirinto() {
		
	}
	
	
	public void addStanza (Stanza stanza) {
		this.stanze.put(stanza.getNome(), stanza);
	}
	
	
	
	public static Labirinto labirintoBase() {

		Labirinto lab=new Labirinto();
		/* crea gli attrezzi */
    	Attrezzo lanterna = new Attrezzo("lanterna",3);
		Attrezzo osso = new Attrezzo("osso",1);
    	
		/* crea stanze del labirinto */
		Stanza atrio = new Stanza("Atrio");
		Stanza aulaN11 = new StanzaBuia("Aula N11");
		Stanza aulaN10 = new StanzaBloccata("Aula N10","osso","ovest");
		Stanza laboratorio = new StanzaMagica("Laboratorio Campus");
		Stanza biblioteca = new Stanza("Biblioteca");
		
		/* collega le stanze */
		atrio.impostaStanzaAdiacente("nord", biblioteca);
		atrio.impostaStanzaAdiacente("est", aulaN11);
		atrio.impostaStanzaAdiacente("sud", aulaN10);
		atrio.impostaStanzaAdiacente("ovest", laboratorio);
		aulaN11.impostaStanzaAdiacente("est", laboratorio);
		aulaN11.impostaStanzaAdiacente("ovest", atrio);
		aulaN10.impostaStanzaAdiacente("nord", atrio);
		aulaN10.impostaStanzaAdiacente("est", aulaN11);
		aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
		laboratorio.impostaStanzaAdiacente("est", atrio);
		laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
		biblioteca.impostaStanzaAdiacente("sud", atrio);
		laboratorio.setPersonaggio(new Mago("Mago","String2",new Attrezzo("Bastone",2)));
		
        /* pone gli attrezzi nelle stanze */
		aulaN10.addAttrezzo(lanterna);
		atrio.addAttrezzo(osso);

		// il gioco comincia nell'atrio
		
        lab.stanzaIniziale = atrio;
		lab.stanzaVincente = biblioteca;
		
		return lab;
    }

	public void setStanzaVincente(Stanza stanza) {
		this.stanzaVincente=stanza;
	}
	
	public void setStanzaIniziale(Stanza stanza) {
		this.stanzaIniziale=stanza;
	}
	
	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}
	public Stanza getStanzaIniziale() {
		return stanzaIniziale;
	}	
	
	public Map<String, Stanza> getMappa() {
		return stanze;
	}
}
