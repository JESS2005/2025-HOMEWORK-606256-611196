package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa implements Comando {
	private String nome;

	@Override
	public void esegui(Partita partita, IO stampe) {
		if(nome!=null) {
			if (!partita.getStanzaCorrente().isPiena()) {
				Attrezzo att=partita.getGiocatore().getBorsa().removeAttrezzo(nome);
				if (att!=null) {
					partita.getStanzaCorrente().addAttrezzo(att);
					stampe.mostraMessaggio("Hai posato: "+nome);
				} else {
					stampe.mostraMessaggio("Niente "+nome);
				}
			} else {
				stampe.mostraMessaggio("La stanza è piena");
			}
		}
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
