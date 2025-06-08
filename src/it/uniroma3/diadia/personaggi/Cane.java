package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio {
	Attrezzo att;
	String cibo;
	private static final String MESSAGGIO_MORSO = "Il cane ti rapina, ti prende un CFU, te ne rimangono ";
	public Cane(String nome, String presentazione,String cibo,Attrezzo att) {
		super(nome, presentazione);
		this.att=att;
		this.cibo=cibo;
	}

	@Override
	public String agisci(Partita partita) {
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
		return MESSAGGIO_MORSO+partita.getGiocatore().getCfu();
	}
	
	@Override
	public String riceviRegalo (Attrezzo attrezzo, Partita partita) {
		if(attrezzo.getNome().equals(cibo)) {
			partita.getStanzaCorrente().addAttrezzo(att);
			return "chip chip :)";
		} else {
			int cfu=partita.getGiocatore().getCfu();
			partita.getGiocatore().setCfu(--cfu);
			return "ROOOAAARR!!!";
		}
	}
}
