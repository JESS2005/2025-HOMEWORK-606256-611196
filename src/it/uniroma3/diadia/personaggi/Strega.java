package it.uniroma3.diadia.personaggi;

import java.util.SortedSet;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio {
	private static final String MESSAGGIO_TP1 = "Ti ritrovi magicamente in ";
	private static final String MESSAGGIO_TP2 = ", non sei sicuro se sei il tu originale od un clone.";
	public Strega(String nome, String presentazione) {
		super(nome, presentazione);
	}

	@Override
	public String agisci(Partita partita) {
		SortedSet<Stanza> ad=partita.getStanzaCorrente().getStanzeAdiacentiPerAttrezzi();
		Stanza fin=ad.first();
		if (this.haSalutato()) {
			fin=ad.last();
		}
		partita.setStanzaCorrente(fin);
		return MESSAGGIO_TP1+fin.getNome()+MESSAGGIO_TP2;
	}
	
	@Override
	public String riceviRegalo (Attrezzo attrezzo, Partita partita) {
		return "AAAAAHAHAHAGHAGSHAGHOHOHOHOHOOH!!!!!!";
	}
}
