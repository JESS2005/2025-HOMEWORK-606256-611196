package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;


public class ComandoGuarda implements Comando {
	private String nome;
	@Override
	public void esegui (Partita partita, IOConsole stampe) {
		stampe.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		
	}
	
	
	@Override
	public void setParametro (String parametro) {
		this.nome=parametro;
	}

}
