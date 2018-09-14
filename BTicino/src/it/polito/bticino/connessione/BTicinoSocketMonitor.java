package it.polito.bticino.connessione;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import it.polito.bticino.lib.Model;
import it.polito.bticino.reader.Reader;
import it.polito.bticino.reader.Reader.EventType;


public class BTicinoSocketMonitor extends Socket implements Runnable{

	
	private Socket sockMonitor;
	private final String hostIP = "192.168.0.35";
	private final int port = 20000;
		
	private PrintWriter outToServer;
	private InputStreamReader inputStreamReader;
	private BufferedReader bf;
	private char[] cbs;
	
	private boolean sessioneEventi;
	private Reader reader;
	private Model model;
	
	
	/**
	 * Il SocketMonitor apre una connessione Socket con il Gateway BTicino,
	 * stabilendo una sessione di eventi.
	 * Gli eventi sono i cambiamenti degli oggetti analizzati.
	 */
	public BTicinoSocketMonitor(Reader reader, Model model) {
		try {
			this.sockMonitor = new Socket();
			this.sockMonitor.connect(new InetSocketAddress(hostIP, port), 0);
		
			
			this.outToServer = new PrintWriter(sockMonitor.getOutputStream());
			this.inputStreamReader = new InputStreamReader(sockMonitor.getInputStream());
			this.bf = new BufferedReader(inputStreamReader);
			this.model=model;
			
			this.sessioneEventi = false;
			this.reader = reader;
			
			 if (!sockMonitor.isConnected())
		    		System.err.println("Non connesso");
			 else {
				cbs = new char[1024];
		    		bf.read(cbs);
		    		String rispDalServer = String.copyValueOf(cbs);
		    		
		    		EventType connessione = reader.interpretaMessagio(rispDalServer);
		    		System.out.println("Connessione: "+connessione.toString());
		    		
		 
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
	
	
	@Override
	public void run() {
		this.apriSessioneEventi();
		while(sessioneEventi==true)
			this.readInput();
		
	}
	
	

	public void setSessioneEventi(boolean sessioneEventi) {
		this.sessioneEventi = sessioneEventi;
	}


	/**
	 * Metodo che apre un sessione di eventi con il Gateway BTicino
	 * @return true, se e` riuscito ad aprire la sessione
	 * @return false, se non riesce ad aprire la sessione
	 */	
	public boolean apriSessioneEventi() {
		
		// Crea il messaggio da inviare al Gateway BTicino per stailire una sessione di Eventi
		String openSession =  "*99*1##";
		try {
			// Se non si e` connessi stampa un messaggio di errore, @return false
			 if (!sockMonitor.isConnected()) {
				 System.err.println("Connetti prima il socket tramite Model");
				 return false;
				 }
			
			// Viene settato il messaggio/riga da inviare al server
			outToServer.write(openSession);
			outToServer.flush();
			
			cbs = new char[1024];
			// Input dal server
			bf.read(cbs);
			
			// Interpretazione risposta del Gateway
    			String rispDalServer = String.format("%s", String.copyValueOf(cbs));

				List<String> msgscmp = reader.scomponiMessaggio(rispDalServer); 
				for (String stringa: msgscmp) {
					EventType evento = reader.interpretaMessagio(stringa);
					if (evento == EventType.ACK)
						this.setSessioneEventi(true);
				}
			
			return sessioneEventi;
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Metodo che legge e interpreta cio` che il Gateway invia
	 * Ack, Nack, Eventi.
	 */
	public void readInput() {
		
		// Se non si e` connessi stampa un messaggio di errore, @return false
		if (!sockMonitor.isConnected()) {
			System.err.println("Connetti prima il socket tramite Model");
		}
		while(sessioneEventi==true && !sockMonitor.isClosed() ) {
			
			try {
				cbs = new char[1024];
				// Input dal server
				bf.read(cbs);
				
				// Interpretazione risposta del Gateway
				String rispDalServer = String.format("%s", String.copyValueOf(cbs));
				
				List<String> msgscmp = reader.scomponiMessaggio(rispDalServer); 
				for (String stringa: msgscmp) {
					EventType evento = reader.interpretaMessagio(stringa);
					model.setStatoOggetto(evento);
					model.getController().setStatusLabel();
				}
				
	    			
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		
		if (sessioneEventi == false ) {
			try {
				outToServer.close();
				inputStreamReader.close();
				bf.close();
				this.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
	}

	
	 public void stopRunning(){
		this.setSessioneEventi(false);
	 }
	
	
	/**
	 * Metodo che chiude la connessione del SocketMonitor
	 */
	public void close() {
		if (this.sockMonitor.isConnected()) {
			
			try {
				sockMonitor.close();
				if (sockMonitor.isClosed()) {
					System.out.println("Socket della sessione eventi chiuso con successo.");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	

}
