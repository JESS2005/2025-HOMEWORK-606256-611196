package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza {
	
	final static private String NOME_ATTREZZO_DEFAULT="chiave";
	final static private Direzione NOME_DIREZIONE_DEFAULT=Direzione.NORD;
	private String attrezzo;
	private Direzione direzione;
	
	public StanzaBloccata (String nome, String attrezzo,Direzione direzione) {		
		super(nome);
		this.attrezzo=attrezzo;
		this.direzione=direzione;
	}
	
	public StanzaBloccata (String nome) {
		this(nome,NOME_ATTREZZO_DEFAULT,NOME_DIREZIONE_DEFAULT);
	}
	
	@Override
	public Stanza getStanzaAdiacente(Direzione direzione) {
		if(this.direzione.equals(direzione)) {
			if(!hasAttrezzo(attrezzo)) {
				return this;
			}
		}
        return super.getStanzaAdiacente(direzione);
        
    }
	
}
