package it.uniroma3.diadia;

import java.io.*;
import java.util.*;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.ambienti.StanzaMagica;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

public class CaricatoreLabirinto {

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	private static final String STANZE_MARKER = "Stanze:";

	private static final String STANZEBUIE_MARKER = "Stanze buie:";

	private static final String STANZEMAGICHE_MARKER = "Stanze magiche:";

	private static final String STANZEBLOCCATE_MARKER = "Stanze bloccate:";

	private static final String PERSONAGGI_MARKER = "Personaggi:";

	/* prefisso di una singola riga contenente il nome della stanza iniziale */
	private static final String STANZA_INIZIALE_MARKER = "Inizio:";

	/* prefisso della riga contenente il nome stanza vincente */
	private static final String STANZA_VINCENTE_MARKER = "Vincente:";

	/*
	 * prefisso della riga contenente le specifiche degli attrezzi da collocare nel
	 * formato <nomeAttrezzo> <peso> <nomeStanza>
	 */
	private static final String ATTREZZI_MARKER = "Attrezzi:";

	/*
	 * prefisso della riga contenente le specifiche dei collegamenti tra stanza nel
	 * formato <nomeStanzaDa> <direzione> <nomeStanzaA>
	 */
	private static final String USCITE_MARKER = "Uscite:";

	/*
	 * Esempio di un possibile file di specifica di un labirinto (vedi
	 * POO-26-eccezioni-file.pdf)
	 * 
	 * Stanze: biblioteca, N10, N11 Inizio: N10 Vincente: N11 Attrezzi: martello 10
	 * biblioteca, pinza 2 N10 Uscite: biblioteca nord N10, biblioteca sud N11
	 * 
	 */
	private LineNumberReader reader;

	private Map<String, Stanza> nome2stanza;

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;

	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
		this.nome2stanza = new HashMap<String, Stanza>();
		this.reader = new LineNumberReader(new FileReader(nomeFile));
	}

	public void carica() throws FormatoFileNonValidoException {
		try {
			this.leggiECreaStanze();
			this.leggiECreaStanzeBuie();
			this.leggiECreaStanzeMagiche();
			this.leggiECreaStanzeBloccate();
			this.leggiInizialeEvincente();
			this.leggiECollocaAttrezzi();
			this.leggiECreaPersonaggi();
			this.leggiEImpostaUscite();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}

	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			check(riga.startsWith(marker), "era attesa una riga che cominciasse per " + marker);
			return riga.substring(marker.length());
		} catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	private void leggiECreaStanze() throws FormatoFileNonValidoException {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
		for (String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			Stanza stanza = new Stanza(nomeStanza);
			this.nome2stanza.put(nomeStanza, stanza);
		}
	}

	private void leggiECreaStanzeBuie() throws FormatoFileNonValidoException {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZEBUIE_MARKER);
		for (String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			String[] p2 = nomeStanza.split(" ");
			Stanza stanza = new StanzaBuia(p2[0], p2[1]);
			this.nome2stanza.put(p2[0], stanza);
		}
	}

	private void leggiECreaStanzeMagiche() throws FormatoFileNonValidoException {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZEMAGICHE_MARKER);
		for (String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			String[] p2 = nomeStanza.split(" ");
			Stanza stanza = new StanzaMagica(p2[0], Integer.parseInt(p2[1]));
			this.nome2stanza.put(p2[0], stanza);
		}
	}

	private void leggiECreaStanzeBloccate() throws FormatoFileNonValidoException {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZEBLOCCATE_MARKER);
		for (String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			String[] p2 = nomeStanza.split(" ");
			Stanza stanza = new StanzaBloccata(p2[0], p2[1], Direzione.valueOf(p2[2].toUpperCase()));
			this.nome2stanza.put(p2[0], stanza);
		}
	}

	private void leggiECreaPersonaggi() throws FormatoFileNonValidoException  {
		String nomiPersonaggi = this.leggiRigaCheCominciaPer(PERSONAGGI_MARKER);
		for(String nomePersonaggio : separaStringheAlleVirgole(nomiPersonaggi)) {
			String[] p2 = nomePersonaggio.split("-");
			AbstractPersonaggio personaggio = null;
			if(p2[0].equals("Mago")) {
				personaggio= new Mago(p2[2], p2[3], new Attrezzo(p2[4],Integer.parseInt(p2[5])));
				
			} else if(p2[0].equals("Strega")) {
				personaggio= new Strega(p2[2], p2[3]);				
			} else if(p2[0].equals("Cane")) {
				personaggio= new Cane(p2[2], p2[3],p2[4], new Attrezzo(p2[5],Integer.parseInt(p2[6])));
			}
			this.nome2stanza.get(p2[1]).setPersonaggio(personaggio);
			//Stanza Personaggio= new StanzaBloccata(p2[0], p2[1], Direzione.valueOf(p2[2].toUpperCase()));
			//this.nome2stanza.put(p2[0], stanza);
		}

	}

	private List<String> separaStringheAlleVirgole(String string) {
		/*
		 * List<String> result = new LinkedList<>(); Scanner scanner = new
		 * Scanner(string); scanner.useDelimiter(","); try (Scanner scannerDiParole =
		 * scanner) { result.add(scannerDiParole.next()); } return result;
		 */
		String[] parti = string.split(",");
		return new LinkedList<String>(Arrays.asList(parti));
	}

	private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = null;
		nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER);
		check(this.isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale + " non definita");
		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER);
		check(this.isStanzaValida(nomeStanzaVincente), nomeStanzaVincente + " non definita");
		this.stanzaIniziale = this.nome2stanza.get(nomeStanzaIniziale);
		this.stanzaVincente = this.nome2stanza.get(nomeStanzaVincente);
	}

	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);

		for (String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null;
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				check(scannerLinea.hasNext(), msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(), msgTerminazionePrecoce("il peso dell'attrezzo " + nomeAttrezzo + "."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(), msgTerminazionePrecoce(
						"il nome della stanza in cui collocare l'attrezzo " + nomeAttrezzo + "."));
				nomeStanza = scannerLinea.next();
			}
			posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
		}
	}

	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza)
			throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo);
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
			check(isStanzaValida(nomeStanza),
					"Attrezzo " + nomeAttrezzo + " non collocabile: stanza " + nomeStanza + " inesistente");
			this.nome2stanza.get(nomeStanza).addAttrezzo(attrezzo);
		} catch (NumberFormatException e) {
			check(false, "Peso attrezzo " + nomeAttrezzo + " non valido");
		}
	}

	private boolean isStanzaValida(String nomeStanza) {
		return this.nome2stanza.containsKey(nomeStanza);
	}

	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
		String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);
		/*
		 * try (Scanner scannerDiLinea = new Scanner(specificheUscite)) {
		 * 
		 * while (scannerDiLinea.hasNext()) { check(scannerDiLinea.hasNext(),
		 * msgTerminazionePrecoce("le uscite di una stanza.")); String stanzaPartenza =
		 * scannerDiLinea.next(); check(scannerDiLinea.hasNext(),
		 * msgTerminazionePrecoce("la direzione di una uscita della stanza "
		 * +stanzaPartenza)); String dir = scannerDiLinea.next();
		 * check(scannerDiLinea.hasNext(),
		 * msgTerminazionePrecoce("la destinazione di una uscita della stanza "
		 * +stanzaPartenza+" nella direzione "+dir)); String stanzaDestinazione =
		 * scannerDiLinea.next();
		 * 
		 * impostaUscita(stanzaPartenza, dir, stanzaDestinazione); } }
		 */

		for (String p : separaStringheAlleVirgole(specificheUscite)) {
			String[] p2 = p.split(" ");
			String stanzaPartenza = p2[0];
			String dir = p2[1];
			String stanzaDestinazione = p2[2];
			impostaUscita(stanzaPartenza, dir, stanzaDestinazione);
		}
	}

	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere " + msg;
	}

	private void impostaUscita(String stanzaDa, String dir, String nomeA) throws FormatoFileNonValidoException {
		check(isStanzaValida(stanzaDa), "Stanza di partenza sconosciuta " + stanzaDa);
		check(isStanzaValida(nomeA), "Stanza di destinazione sconosciuta " + nomeA);
		Stanza partenzaDa = this.nome2stanza.get(stanzaDa);
		Stanza arrivoA = this.nome2stanza.get(nomeA);
		partenzaDa.impostaStanzaAdiacente(Direzione.valueOf(dir.toUpperCase()), arrivoA);
	}

	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore)
			throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException(
					"Formato file non valido [" + this.reader.getLineNumber() + "] " + messaggioErrore);
	}

	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}

	public Map<String, Stanza> getMappaStanze() {
		return nome2stanza;
	};
}