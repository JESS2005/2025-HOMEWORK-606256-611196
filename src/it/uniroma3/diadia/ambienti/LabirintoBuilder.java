package it.uniroma3.diadia.ambienti;

import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder {
	
	private Labirinto labirinto;
	private Stanza stanzaUltima;
	
	public LabirintoBuilder() {
		this.labirinto=new Labirinto();
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
	
	public LabirintoBuilder addAdiacenza(String stanza1, String stanza2, String direzione) {
		labirinto.getMappa().get(stanza1).impostaStanzaAdiacente(direzione, labirinto.getMappa().get(stanza2));
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
	
	public LabirintoBuilder addStanzaBloccata(String stanza, String attrezzo, String direzione) {
		Stanza newstanza=new StanzaBloccata(stanza, attrezzo, direzione);
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
	
	public Map<String, Stanza> getMappaStanze() {
		return this.labirinto.getMappa();
		}
	
}
