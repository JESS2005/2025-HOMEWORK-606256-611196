package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoFine implements Comando {
	private String nome;
	@Override
	public void esegui(Partita partita, IO stampe) {
		stampe.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
		partita.setFinita();
	}
	
	@Override
	public void setParametro(String parametro) {
		this.nome = parametro;
	}
	
	@Override
	public String getParametro() {
		return this.nome;
	}
	
	@Override
	public String getNome() {
		return this.getClass().getName().substring(getClass().getName().indexOf("Comando")+7);
	}

}
