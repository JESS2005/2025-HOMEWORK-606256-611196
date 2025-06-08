package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.CaricatoreProprieta;

public class Giocatore {
	private int cfu;
	private Borsa borsa;
	public Giocatore(int cfu) {
		this.cfu=cfu;
		if (CaricatoreProprieta.getPBorsa()==0) {
			borsa= new Borsa();
		} else {
			borsa= new Borsa(CaricatoreProprieta.getPBorsa());
		}
	}
	
	public int getCfu() {
		return this.cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;		
	}
	
	public Borsa getBorsa() {
		return this.borsa;
	}
}
