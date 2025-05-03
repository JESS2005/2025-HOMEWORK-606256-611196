package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi implements Comando {
	private String nome;

	@Override
	public void esegui(Partita partita, IOConsole stampe) {
		if (nome != null) {
			Attrezzo att = partita.getStanzaCorrente().getAttrezzo(nome);
			if (att != null) {
				if (partita.getGiocatore().getBorsa().addAttrezzo(att)) {
					partita.getStanzaCorrente().removeAttrezzo(att);
					stampe.mostraMessaggio("Hai preso: " + nome);
				} else {
					stampe.mostraMessaggio("Borsa piena");
				}
			} else {
				stampe.mostraMessaggio("Niente " + nome);
			}
		}
	}

	@Override
	public void setParametro(String parametro) {
		this.nome = parametro;
	}

}
