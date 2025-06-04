package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio {
	private static final String MESSAGGIO_MORSO = "Il cane ti rapina, ti prende un CFU, te ne rimangono ";
	public Cane(String nome, String presentazione) {
		super(nome, presentazione);
	}

	@Override
	public String agisci(Partita partita) {
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
		return MESSAGGIO_MORSO+partita.getGiocatore().getCfu();
	}
	
	@Override
	public String riceviRegalo (Attrezzo attrezzo, Partita partita) {
		if(attrezzo.getNome().equals("osso")) {
			Attrezzo nuovo=new Attrezzo("palla", 3);
			partita.getStanzaCorrente().addAttrezzo(nuovo);
			return "chip chip";
		} else {
			int cfu=partita.getGiocatore().getCfu();
			partita.getGiocatore().setCfu(--cfu);
			return "ROOOAAARR!!!";
		}
	}
}
