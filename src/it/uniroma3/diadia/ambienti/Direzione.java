package it.uniroma3.diadia.ambienti;

public enum Direzione {
	NORD, EST, SUD, OVEST;
	
	public Direzione opposta() {
		int opp=(this.ordinal()+2)%4;
		return Direzione.values()[opp];
	}
}
