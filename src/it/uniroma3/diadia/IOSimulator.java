package it.uniroma3.diadia;

import java.util.Scanner;

public class IOSimulator implements IO {
	
	private String[] messaggiLetti;
	private String[] messaggiScrivere;
	private int Letti=0;
	private int Scritti=0;
	
	
	public IOSimulator (String[] messaggiScrivere, int LettiMax) {
		this.messaggiScrivere=messaggiScrivere;
		this.messaggiLetti=new String[LettiMax];
	}
	
	@Override
	public void mostraMessaggio(String msg) {
		if (Letti>=messaggiLetti.length) {return; }
		messaggiLetti[Letti]=msg;
		Letti++;
	}

	@Override
	public String leggiRiga() {
		if (Scritti>=messaggiScrivere.length) {return ""; }
		String riga=messaggiScrivere[Scritti];
		Scritti++;
		return riga;
	}

	public String[] getLetti() {
		return messaggiLetti;
	}
}
