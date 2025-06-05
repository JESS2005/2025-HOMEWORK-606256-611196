package it.uniroma3.diadia.ambienti;

import java.util.Map;

import it.uniroma3.diadia.CaricatoreLabirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

public class LabirintoBuilder {
	
	private Labirinto labirinto;
	private Stanza stanzaUltima;
	
	public LabirintoBuilder() {
		this.labirinto=new Labirinto();
	}
	
	public LabirintoBuilder(CaricatoreLabirinto car) {
		this.labirinto=new Labirinto();
		this.labirinto.setStanzaIniziale(car.getStanzaIniziale());
		this.labirinto.setStanzaVincente(car.getStanzaVincente());
		this.labirinto.setMappaStanze(car.getMappaStanze());
	}
	
	public LabirintoBuilder addStanza(String stanza) {
		Stanza newstanza=new Stanza(stanza);
		this.labirinto.addStanza(newstanza);
		this.stanzaUltima=newstanza;
		return this;
	}
	
	public LabirintoBuilder addStanzaVincente(String stanza) {
		if(labirinto.getMappa().containsKey(stanza)) {
			labirinto.setStanzaVincente(labirinto.getMappa().get(stanza));
		} else {
			Stanza newstanzaw=new Stanza(stanza);
			this.labirinto.addStanza(newstanzaw);
			labirinto.setStanzaVincente(newstanzaw);
			this.stanzaUltima=newstanzaw;
		}
		return this;
	}
	
	public LabirintoBuilder addStanzaIniziale(String stanza) {
		if(labirinto.getMappa().containsKey(stanza)) {
			labirinto.setStanzaIniziale(labirinto.getMappa().get(stanza));
		} else {
			Stanza newstanzai=new Stanza(stanza);
			this.labirinto.addStanza(newstanzai);
			labirinto.setStanzaIniziale(newstanzai);
			this.stanzaUltima=newstanzai;
		}
		return this;
	}
	
	public LabirintoBuilder addAttrezzo(String nome, int peso) {
		Attrezzo attrezzo=new Attrezzo(nome,peso);
		stanzaUltima.addAttrezzo(attrezzo);
		return this;
	}
	
	public LabirintoBuilder addAdiacenza(String da, String a, Direzione direzione) {
		labirinto.getMappa().get(da).impostaStanzaAdiacente(direzione, labirinto.getMappa().get(a));
		return this;
	}
	
	public LabirintoBuilder addAdiacenza(String a, Direzione direzione) {
		stanzaUltima.impostaStanzaAdiacente(direzione, labirinto.getMappa().get(a));
		return this;
	}
	
	public Labirinto getLabirinto() {
		return this.labirinto;
	}
	

	public LabirintoBuilder addStanzaBuia(String stanza, String attrezzo) {
		Stanza newstanza=new StanzaBuia(stanza, attrezzo);
		this.labirinto.addStanza(newstanza);
		this.stanzaUltima=newstanza;
		return this;
	}
	
	public LabirintoBuilder addStanzaBloccata(String stanza, Direzione ovest, String attrezzo) {
		Stanza newstanza=new StanzaBloccata(stanza, attrezzo, ovest);
		this.labirinto.addStanza(newstanza);
		this.stanzaUltima=newstanza;
		return this;
	}
	
	public LabirintoBuilder addStanzaMagica(String stanza,int numero) {
		Stanza newstanza=new StanzaMagica(stanza,numero);
		this.labirinto.addStanza(newstanza);
		this.stanzaUltima=newstanza;
		return this;
	}
	
	public LabirintoBuilder addPersonaggio(AbstractPersonaggio pers) {
		stanzaUltima.setPersonaggio(pers);
		return this;
	}
	
	public Map<String, Stanza> getMappaStanze() {
		return this.labirinto.getMappa();
	}
	
	public static LabirintoBuilder labirintoTest() {
		LabirintoBuilder lab=new LabirintoBuilder();
		lab.addStanzaIniziale("Atrio").addAttrezzo("osso",1).addStanzaMagica("Laboratorio Campus",3)
		.addStanzaBloccata("Aula N10",Direzione.OVEST,"osso").addAttrezzo("lanterna",3).addStanzaVincente("Biblioteca").addStanzaBuia("Aula N11","lanterna")
		.addAdiacenza("Atrio", "Biblioteca", Direzione.NORD).addAdiacenza("Atrio", "Aula N11", Direzione.EST).addAdiacenza("Atrio", "Aula N10", Direzione.SUD).addAdiacenza("Atrio", "Laboratorio Campus", Direzione.OVEST)
		.addAdiacenza("Aula N11", "Laboratorio Campus", Direzione.EST).addAdiacenza("Aula N11", "Atrio", Direzione.OVEST)
		.addAdiacenza("Aula N10", "Aula N11", Direzione.EST).addAdiacenza("Aula N10", "Laboratorio Campus", Direzione.OVEST).addAdiacenza("Aula N10", "Atrio", Direzione.NORD)
		.addAdiacenza("Laboratorio Campus","Atrio", Direzione.EST).addAdiacenza("Laboratorio Campus","Aula N11", Direzione.OVEST)
		.addAdiacenza("Biblioteca","Atrio", Direzione.SUD);

		return lab;
	}
	
	public static LabirintoBuilder labirintoBase() {
		LabirintoBuilder lab=new LabirintoBuilder();
		lab.addStanzaIniziale("Atrio").addAttrezzo("osso",1).addStanzaMagica("Laboratorio Campus",3).addPersonaggio(new Mago("Merlone il mago burlone","Ciao",new Attrezzo("Bastone",2)))
		.addStanzaBloccata("Aula N10",Direzione.OVEST,"osso").addPersonaggio(new Cane("cane magico","Miao","Osso",new Attrezzo("Palla",3))).addAttrezzo("lanterna",3).addStanzaVincente("Biblioteca").addStanzaBuia("Aula N11","lanterna").addPersonaggio(new Strega("Amelia la strega","你好"))
		.addAdiacenza("Atrio", "Biblioteca", Direzione.NORD).addAdiacenza("Atrio", "Aula N11", Direzione.EST).addAdiacenza("Atrio", "Aula N10", Direzione.SUD).addAdiacenza("Atrio", "Laboratorio Campus", Direzione.OVEST)
		.addAdiacenza("Aula N11", "Laboratorio Campus", Direzione.EST).addAdiacenza("Aula N11", "Atrio", Direzione.OVEST)
		.addAdiacenza("Aula N10", "Aula N11", Direzione.EST).addAdiacenza("Aula N10", "Laboratorio Campus", Direzione.OVEST).addAdiacenza("Aula N10", "Atrio", Direzione.NORD)
		.addAdiacenza("Laboratorio Campus","Atrio", Direzione.EST).addAdiacenza("Laboratorio Campus","Aula N11", Direzione.OVEST)
		.addAdiacenza("Biblioteca","Atrio", Direzione.SUD);

		return lab;
	}
	
}
