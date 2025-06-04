package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoRegala extends AbstractComando {

	
	@Override
    public void esegui(Partita partita, IO stampe) {
		if(nome!=null) {
			if (partita.getStanzaCorrente().getPersonaggio()!=null) {
				Attrezzo att=partita.getGiocatore().getBorsa().removeAttrezzo(nome);
				if (att!=null) {
					stampe.mostraMessaggio(partita.getStanzaCorrente().getPersonaggio().riceviRegalo(att, partita));
				} else {
					stampe.mostraMessaggio("Niente "+nome);
				}
			} else {
				stampe.mostraMessaggio("Non c'è nessuno");
			}
		}
    }
    
    

}
