package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza {
	
	final static private String NOME_ATTREZZO_DEFAULT="lanterna";
	private String attrezzo;
	
	public StanzaBuia (String nome, String attrezzo) {		
		super(nome);
		this.attrezzo=attrezzo;
	}
	
	public StanzaBuia (String nome) {
		this(nome,NOME_ATTREZZO_DEFAULT);
	}
	
	@Override
	public String getDescrizione() {
		if(!hasAttrezzo(attrezzo)) {
			return "qui c'è buio pesto, devi portare: "+attrezzo;
		}
		
        return this.toString();
        
    }
	
}
