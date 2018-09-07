package it.polito.bticino.connessione;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class BTicinoSocketMonitor extends BTicinoSocket {

	
	private Socket sockMonitor;
	private final String hostIP = "192.168.0.35";
	private final int port = 20000;
	
	private PrintWriter outToServer;
	private InputStreamReader inputStreamReader;
	private BufferedReader bf;
	private char[] cbs;
	private boolean sessioneEventi;
	
	
	/**
	 * Il SocketMonitor apre una connessione Socket con BTicino e la mantiene aperta per recepire tutti
	 * i cambiamenti degli oggetti analizzati.
	 */
	public BTicinoSocketMonitor() {
		super();
		try {
			this.sockMonitor = new Socket();
			this.sockMonitor.connect(new InetSocketAddress(hostIP, port), 0);
			
			outToServer = new PrintWriter(sockMonitor.getOutputStream());
			inputStreamReader = new InputStreamReader(sockMonitor.getInputStream());
			bf = new BufferedReader(inputStreamReader);
			sessioneEventi = false;
			
			 if (!sockMonitor.isConnected())
		    		System.err.println("Non connesso");
			 else {
				cbs = new char[1024];
		    		bf.read(cbs);
		    		System.out.println("Connessione : "+ String.copyValueOf(cbs));
			 }
		} catch ( java.net.UnknownHostException e ) {
		// Il nome dell'host non e' valido    
		    System.out.println("Can't find host."); 
		} 
		catch ( java.io.IOException e ){ 
		// Si e' verificato un errore di connessione
		    System.out.println("Error connecting to host."); 
		} 
			
	} 

	
	public int sendMessage(String message) {
		
		// Crea il messaggio da inviare al server BTicino
	
		try {
			
			// Viene aperto il socket con indirizzo IP e la relativa porta per connettersi al server
			 if (!sockMonitor.isConnected())
				 sockMonitor = new BTicinoSocketMonitor();
			
			// Viene settato il messaggio/riga da inviare al server
			outToServer.write(message);
			outToServer.flush();
			
			// Viene stampato l'input stream dal server
			int risposta = bf.read(cbs);
			String dalServer = String.copyValueOf(cbs);
			System.out.println("messaggio: " +dalServer);
			if (risposta != -1 ) {
				sessioneEventi = true;
			}
			return risposta;
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	
	public void readInput() {
		if (!sockMonitor.isConnected()) {
			sockMonitor = new BTicinoSocketMonitor();
		}
		while(sessioneEventi == true) {
			try {
				int risp = bf.read(cbs);
	    			System.out.println("messaggio dal server : " + risp + " - " + String.copyValueOf(cbs));
	    			
	    			if (risp == -1) {
	    				this.outToServer.close();
	    				this.inputStreamReader.close();
	    				this.bf.close();
	    				this.close();
	    			}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
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
