package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoRegala extends AbstractComando {

	
	@Override
    public void esegui(Partita partita, IO stampe) {
    	System.out.println("regalato!!!");
    }
    
    

}
