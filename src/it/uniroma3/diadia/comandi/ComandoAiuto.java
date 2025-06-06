package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando {

	@Override
	public void esegui (Partita partita, IO stampe) {
		String niente = "";
		final String[] elencoComandi = {"vai", "aiuto", "fine","prendi","posa","guarda", "regala", "interagisci","saluta"};
		for(int i=0; i< elencoComandi.length; i++) 
			niente += (elencoComandi[i]+" ");
		stampe.mostraMessaggio(niente);
	}
	
	/*
	@Override
	public void esegui (Partita partita, IO stampe) {
		String niente = "";
		final String[] elencoComandi = {"vai", "aiuto", "fine","prendi","posa","guarda", "regala"};
		for(int i=0; i< elencoComandi.length; i++) 
			niente += (elencoComandi[i]+" ");
		stampe.mostraMessaggio(niente);
	}
	
	*/
	
	
	@Override
	public String getNome() {
		return this.getClass().getName().substring(getClass().getName().indexOf("Comando")+7);
	}
	
}
