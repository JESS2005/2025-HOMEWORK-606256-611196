package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;


public class ComandoGuarda extends AbstractComando {

	@Override
	public void esegui (Partita partita, IO stampe) {
		stampe.mostraMessaggio(partita.getStanzaCorrente().getDescrizione()+"\nCFU: "+partita.getGiocatore().getCfu()+"\n"+partita.getGiocatore().getBorsa().toString());
		
	}
	


}
