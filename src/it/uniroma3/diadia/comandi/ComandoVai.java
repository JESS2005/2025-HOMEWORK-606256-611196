package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai implements Comando {
	private String direzione;
	@Override
	public void esegui (Partita partita, IO stampe) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Stanza prossimaStanza = null;
		if(direzione==null)
			stampe.mostraMessaggio("Dove vuoi andare ?");
		prossimaStanza = partita.getStanzaCorrente().getStanzaAdiacente(direzione);
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
	
	@Override
	public void setParametro (String parametro) {
		this.direzione=parametro;
	}

	@Override
	public String getParametro() {
		return this.direzione;
	}
	
	@Override
	public String getNome() {
		return this.getClass().getName().substring(getClass().getName().indexOf("Comando")+7);
	}
}
