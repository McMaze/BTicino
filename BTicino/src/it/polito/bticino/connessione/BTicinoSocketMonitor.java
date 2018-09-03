package it.polito.bticino.connessione;

import java.io.IOException;
import java.net.Socket;

public class BTicinoSocketMonitor extends BTicinoSocket {

	
	private Socket sockMonitor;
	private final String hostIP = "192.168.0.35";
	private final int port = 20000;
	
	
	/**
	 * Il SocketMonitor apre una connessione Socket con BTicino e la mantiene aperta per recepire tutti
	 * i cambiamenti degli oggetti analizzati.
	 */
	public BTicinoSocketMonitor() {
		super();
		try {
			this.sockMonitor = new Socket(hostIP, port);
			 if (!sockMonitor.isConnected())
		    		System.err.println("Non connesso");
		    
		} catch ( java.net.UnknownHostException e ) {
		// Il nome dell'host non e' valido    
		    System.out.println("Can't find host."); 
		} 
		catch ( java.io.IOException e ){ 
		// Si e' verificato un errore di connessione
		    System.out.println("Error connecting to host."); 
		} 
			
	} 

	
	/**
	 * Metodo che chiude la connessione del SocketMonitor
	 */
	public void close() {
		if (this.sockMonitor.isConnected()) {
			try {
				sockMonitor.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
