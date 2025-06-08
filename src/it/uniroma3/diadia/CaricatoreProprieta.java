package it.uniroma3.diadia;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class CaricatoreProprieta {
	private static final String CFU_MARKER = "CFUIniziali:";
	private static final String BORSA_MARKER = "PesoBorsa:";
	private static int cfu=0;
	private static int pesoBorsa=0;
	private LineNumberReader reader;
	
	
	public CaricatoreProprieta(String nomeFile) throws FileNotFoundException {
		this.reader = new LineNumberReader(new FileReader(nomeFile));
	}
	
	public CaricatoreProprieta() {
	}
	
	public static int getCFU() {
		return cfu;
	}
	
	public static int getPBorsa() {
		return pesoBorsa;
	}

	public void carica() throws FormatoFileNonValidoException {
		try {
			this.leggiCFU();
			this.leggiPesoBorsa();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}
	
	private void leggiCFU() throws FormatoFileNonValidoException {
		String stringa= this.leggiRigaCheCominciaPer(CFU_MARKER);
		this.cfu = Integer.parseInt(stringa);
	}
	
	private void leggiPesoBorsa() throws FormatoFileNonValidoException {
		String stringa = this.leggiRigaCheCominciaPer(BORSA_MARKER);
		this.pesoBorsa= Integer.parseInt(stringa);
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
	
	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore)
			throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException(
					"Formato file non valido [" + this.reader.getLineNumber() + "] " + messaggioErrore);
	}
	
	private List<String> separaStringheAlleVirgole(String string) {

		String[] parti = string.split(",");
		return new LinkedList<String>(Arrays.asList(parti));
	}
	
}
