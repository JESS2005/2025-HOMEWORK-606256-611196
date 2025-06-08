package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai extends AbstractComando {
	@Override
	public void esegui (Partita partita, IO stampe) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Stanza prossimaStanza = null;
		if(nome==null)
			stampe.mostraMessaggio("Dove vuoi andare ?");
		//Direzione.enumConstantDirectory().get("A");
		//prossimaStanza = partita.getStanzaCorrente().getStanzaAdiacente(Direzione.valueOf(nome.toUpperCase()));
		try {
			prossimaStanza = partita.getStanzaCorrente().getStanzaAdiacente(Direzione.valueOf(nome.toUpperCase()));
		} catch (IllegalArgumentException e) {
			stampe.mostraMessaggio("Direzione inesistente");
			return;
		}
		if (prossimaStanza == null)
			stampe.mostraMessaggio("Direzione inesistente");
		else {
			partita.setStanzaCorrente(prossimaStanza);
			int cfu = partita.getGiocatore().getCfu();
			//this.stampe.mostraMessaggio(partita.getGiocatore().getCfu());
			partita.getGiocatore().setCfu(--cfu);
		}
		stampe.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}
	
}
