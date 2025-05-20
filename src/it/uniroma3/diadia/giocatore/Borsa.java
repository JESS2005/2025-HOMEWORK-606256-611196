
package it.uniroma3.diadia.giocatore;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Map<String, Attrezzo> attrezzi = new HashMap<String,Attrezzo>();
	private int pesoMax;

	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}

	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
	}

	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		//this.attrezzi[this.numeroAttrezzi] = attrezzo;
		this.attrezzi.put(attrezzo.getNome(),attrezzo);
		return true;
	}

	public int getPesoMax() {
		return pesoMax;
	}

	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		/*Attrezzo a = null;
		for (int i = 0; i < this.numeroAttrezzi; i++)
			if (this.attrezzi[i].getNome().equals(nomeAttrezzo))
				a = attrezzi[i];

		return a;*/
		return this.attrezzi.get(nomeAttrezzo);
	}

	public int getPeso() {
		int peso = 0;
		/*for (int i = 0; i < this.numeroAttrezzi; i++)
			peso += this.attrezzi[i].getPeso();*/
		
        for (Map.Entry<String,Attrezzo> m : this.attrezzi.entrySet())
            peso+= m.getValue().getPeso();

		return peso;
	}

	public boolean isEmpty() {
		return this.attrezzi.size() == 0;
	}

	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo) != null;
	}

	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.remove(nomeAttrezzo);
	}

	public String toString() {
		StringBuilder s = new StringBuilder();

		if (!this.isEmpty()) {
			s.append("Contenuto borsa (" + this.getPeso() + "kg/" + this.getPesoMax() + "kg):");
			//for (int i = 0; i < this.numeroAttrezzi; i++)
			//	s.append(attrezzi[i].toString() + " ");
	        for (Map.Entry<String,Attrezzo> m : this.attrezzi.entrySet())
	            s.append(" "+m.getKey());
			
		} else
			s.append("Borsa vuota");
		getContenutoOrdinatoPerPeso();
		return s.toString();
	}
	
	public List<Attrezzo> getContenutoOrdinatoPerPeso() {
		List<Attrezzo> contenuto=new ArrayList<Attrezzo>();
		for (Map.Entry<String,Attrezzo> m : this.attrezzi.entrySet()){
			contenuto.add(m.getValue());
		}
		contenuto.sort(Comparator.comparingInt(Attrezzo::getPeso).thenComparing(Attrezzo::getNome));
		return contenuto;
	}
	
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome() {
		Comparator<Attrezzo> compare= Comparator.comparing(Attrezzo::getNome);
		SortedSet<Attrezzo> contenuto=new TreeSet<Attrezzo>(compare);
		for (Map.Entry<String,Attrezzo> m : this.attrezzi.entrySet()){
			contenuto.add(m.getValue());
		}
		return contenuto;
	}
	
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso() {
		Comparator<Attrezzo> compare= Comparator.comparingInt(Attrezzo::getPeso).thenComparing(Attrezzo::getNome);
		SortedSet<Attrezzo> contenuto=new TreeSet<Attrezzo>(compare);
		for (Map.Entry<String,Attrezzo> m : this.attrezzi.entrySet()){
			contenuto.add(m.getValue());
		}
		return contenuto;
	}
	
	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso() {
		Map<Integer,Set<Attrezzo>> contenuto=new HashMap<Integer,Set<Attrezzo>>();
		for (Map.Entry<String,Attrezzo> m : this.attrezzi.entrySet()){
			Attrezzo corrente=m.getValue();
			if (contenuto.containsKey(corrente.getPeso())) {
				contenuto.get(corrente.getPeso()).add(corrente);
			} else {
				Set<Attrezzo> nuovo=new HashSet<Attrezzo>();
				nuovo.add(corrente);
				contenuto.put(corrente.getPeso(), nuovo);
				
			}
		}
		return contenuto;
	}
}
