
package it.uniroma3.diadia;

import java.util.Scanner;

public class IOConsole implements IO{
	
	private Scanner scanner;
	
	public IOConsole() {
		scanner= new Scanner(System.in);
	}
	@Override
	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}

	@Override
	public String leggiRiga() {
		String riga = scanner.nextLine();
		//scannerDiLinee.close();
		return riga;
	}
	
    public void close() {
        scanner.close();
    }
}