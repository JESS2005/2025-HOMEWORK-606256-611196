package it.uniroma3.diadia.ambienti;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

/**
 * Classe Stanza - una stanza in un gioco di ruolo. Una stanza e' un luogo
 * fisico nel gioco. E' collegata ad altre stanze attraverso delle uscite. Ogni
 * uscita e' associata ad una direzione.
 * 
 * @author docente di POO
 * @see Attrezzo
 * @version base
 */

public class Stanza {

	static final private int NUMERO_MASSIMO_ATTREZZI = 10;

	private String nome;

	private AbstractPersonaggio personaggio;

	private Map<String, Attrezzo> attrezzi = new HashMap<String, Attrezzo>();

	private Map<String, Stanza> stanzeAdiacenti = new HashMap<String, Stanza>();

	/**
	 * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * 
	 * @param nome il nome della stanza
	 */
	public Stanza(String nome) {
		this.nome = nome;
	}

	/**
	 * Imposta una stanza adiacente.
	 *
	 * @param direzione direzione in cui sara' posta la stanza adiacente.
	 * @param stanza    stanza adiacente nella direzione indicata dal primo
	 *                  parametro.
	 */
	public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
		this.stanzeAdiacenti.put(direzione, stanza);
	}

	/**
	 * Restituisce la stanza adiacente nella direzione specificata
	 * 
	 * @param direzione
	 */
	public Stanza getStanzaAdiacente(String direzione) {
		return this.stanzeAdiacenti.get(direzione);
	}

	/**
	 * Restituisce la nome della stanza.
	 * 
	 * @return il nome della stanza
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce la descrizione della stanza.
	 * 
	 * @return la descrizione della stanza
	 */
	public String getDescrizione() {
		return this.toString();
	}

	/**
	 * Restituisce la collezione di attrezzi presenti nella stanza.
	 * 
	 * @return la collezione di attrezzi nella stanza.
	 */
	public Map<String, Attrezzo> getAttrezzi() {
		return this.attrezzi;
	}

	/**
	 * Mette un attrezzo nella stanza.
	 * 
	 * @param attrezzo l'attrezzo da mettere nella stanza.
	 * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.attrezzi.size() >= NUMERO_MASSIMO_ATTREZZI)
			return false;
		this.attrezzi.put(attrezzo.getNome(), attrezzo);
		return true;
	}

	public boolean isPiena() {
		return this.attrezzi.size() == NUMERO_MASSIMO_ATTREZZI;
	}

	/**
	 * Restituisce una rappresentazione stringa di questa stanza, stampadone la
	 * descrizione, le uscite e gli eventuali attrezzi contenuti
	 * 
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append(this.nome);
		risultato.append("\nUscite:");
		for (Map.Entry<String, Stanza> m : this.stanzeAdiacenti.entrySet())
			risultato.append(" " + m.getKey());
		risultato.append("\nAttrezzi nella stanza:");
		for (Map.Entry<String, Attrezzo> m : this.attrezzi.entrySet())
			risultato.append(" " + m.getValue().toString());
		if (personaggio!=null)
			risultato.append("\nVedi un "+personaggio.getClass().getSimpleName());
		return risultato.toString();
	}

	/**
	 * Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	 * 
	 * @return true se l'attrezzo esiste nella stanza, false altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.containsKey(nomeAttrezzo);
	}

	/**
	 * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * 
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza. null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.get(nomeAttrezzo);
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * 
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public Boolean removeAttrezzo(Attrezzo AttrezzoRim) {
		Attrezzo AttRim = this.attrezzi.remove(AttrezzoRim.getNome());
		return AttRim != null;
	}

	public List<String> getDirezioni() {
		List<String> direzioni = new ArrayList<String>();
		for (Map.Entry<String, Stanza> m : this.stanzeAdiacenti.entrySet())
			direzioni.add(m.getKey());
		return direzioni;
	}

	public void setPersonaggio(AbstractPersonaggio personaggio) {
		this.personaggio = personaggio;
	}

	public AbstractPersonaggio getPersonaggio() {
		return this.personaggio;
	}

	public SortedSet<Stanza> getStanzeAdiacentiPerAttrezzi() {

		class ComparatorePerAtt implements Comparator<Stanza> {
			@Override
			public int compare(Stanza s1, Stanza s2) {
				return s1.getAttrezzi().size()-s2.getAttrezzi().size();
			}
		}

		SortedSet<Stanza> ad=new TreeSet<Stanza>(new ComparatorePerAtt());
		List<Stanza> temp=new ArrayList<Stanza>(stanzeAdiacenti.values());
		Collections.shuffle(temp); //randomizziamo per quando più stanze hanno gli stessi attrezzi
		ad.addAll(temp);
		return ad;
	}
	
	public Map<String, Stanza> getMappaAdiacenti() {
		return stanzeAdiacenti;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null || this.getClass() != obj.getClass())
			return false;

		Stanza altro = (Stanza) obj;

		return this.nome.equals(altro.getNome());
	}

}