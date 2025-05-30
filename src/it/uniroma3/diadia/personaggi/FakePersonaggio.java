package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;

public class FakePersonaggio extends AbstractPersonaggio {

	public FakePersonaggio(String nome, String presentazione) {
		super(nome, presentazione);
	}

	@Override
	public String agisci(Partita partita) {
		return "done";
	}

}
