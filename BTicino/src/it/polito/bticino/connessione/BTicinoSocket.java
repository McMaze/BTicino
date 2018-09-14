package it.polito.bticino.connessione;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import it.polito.bticino.lib.Model;
import it.polito.bticino.main.BTicinoController;
import it.polito.bticino.reader.Reader;
import it.polito.bticino.reader.Reader.EventType;


public class BTicinoSocket extends Socket{
	
	private Socket sock ;
	private final String hostIP = "192.168.0.35";
	private final int port = 20000;
	
	private PrintWriter outToServer;
	private InputStreamReader inputStreamReader;
	private BufferedReader bf;
	private char[] cbs;

	private boolean sessioneComandi;
	private Reader reader;
	private Model model;
	private List<EventType> eventi;
	
	
	/**
	 * BTicinoSocket apre la connessione Socket con il Gateway BTicino,
	 * stabilendo una sessione di comandi. 
	 * I comandi sono quelli che invia l'utente.
	 */
	public BTicinoSocket(Reader reader, Model model) {
		try 
		{ 
			this.sock = new Socket();
			this.sock.connect(new InetSocketAddress(hostIP, port), 0);
			
    			this.outToServer = new PrintWriter(sock.getOutputStream());
    			this.inputStreamReader = new InputStreamReader(sock.getInputStream());
	    		this.bf = new BufferedReader (inputStreamReader);
	    		this.model = model;
	    		
	    		this.sessioneComandi= false;
	    		this.reader = reader;
	    		
	    		
		    if (!sock.isConnected())
		    		System.err.println("Non connesso");
		    else {
		    		cbs = new char[1024];
		    		bf.read(cbs);
		    		String rispDalServer = String.format("%s", String.copyValueOf(cbs));
		    		
		    		EventType connesione = reader.interpretaMessagio(rispDalServer);
		    		System.out.println("Connessione: "+connesione.toString());
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

	
	
	/**
	 * Metodo che apre un sessione di comandi con il Gateway BTicino
	 * @return true, se e` riuscito ad aprire la sessione
	 * @return false, se non riesce ad aprire la sessione
	 */
	public boolean apriSessioneComandi() {
		
		
		// Crea il messaggio da inviare al Gateway BTicino per stailire una sessione di Comandi
		String openSession = "*99*9##";
		
		try {
			// Se non si e` connessi stampa un messaggio di errore, @return false
			 if (!sock.isConnected()) {
				 System.err.println("Connetti prima il socket tramite Model");
				 return false;
			 }
			
			// Viene settato il messaggio/riga da inviare al server
			outToServer.write(openSession);
			outToServer.flush();
			
			// Input dal server
			bf.read(cbs);
			
			// Interpretazione risposta del Gateway
    			String rispDalServer = String.format("%s", String.copyValueOf(cbs));
    			EventType evento = reader.interpretaMessagio(rispDalServer);
			if (evento == EventType.ACK) {
				sessioneComandi = true;
			}
			
			return sessioneComandi ;
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	
	
	}
	
	/**
	 * Metodo per inviare un comando tramite BTicinoSocket
	 * 
	 * @param who, a chi inviarlo
	 * @param what, cosa deve fare
	 * @param where, che oggetto deve eseguirlo
	 * @return
	 */
	public void sendMessage(int who, Integer what, int where) {
		
		// Crea il messaggio da inviare al server BTicino
		String message= "*"+who+"*"+what+"*"+where+"##";
	
		try {
			
			// Se non si e` connessi stampa un messaggio di errore, @return false
			 if (!sock.isConnected()) {
				 System.err.println("Connetti prima il socket tramite Model");
				 //return false;
				 }
			
			if (sessioneComandi == true) {
				// Viene settato il messaggio/riga da inviare al server
				outToServer.write(message);
				outToServer.flush();
				System.out.println("Comando");
				
				
				 // Input dal server
				cbs = new char[1024];
				bf.read(cbs);
				
				// Interpretazione risposta del Gateway
	    			String rispDalServer = String.format("%s", String.copyValueOf(cbs));
	    			List<String> msgscmp = reader.scomponiMessaggio(rispDalServer); 
					for (String stringa: msgscmp) {
						System.out.println("Socket comandi : "+stringa);
						reader.interpretaMessagio(stringa);
					
						/*EventType evento = reader.interpretaMessagio(stringa);
						if (evento == EventType.ACK)*/
							
					}
				
			}
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
			//return false;
		} catch (IOException e) {
			e.printStackTrace();
			//return false;
		}
	}
	
	/**
	 * Metodo per interrogare sullo stato di tutti gli oggetti 
	 * 
	 * @return
	 */
	
	public List<EventType> getStati() { // cambiare a funzione VOID !!
		List<String> stati = new ArrayList<>();
		
		stati.add("*#2*21##");
		stati.add("*#1*1##");
		stati.add("*#1*11##");
		stati.add("*#1*12##");
		stati.add("*#1*13##");
		
		
		eventi = new ArrayList<EventType>();
	
		try {
			
			// Se non si e` connessi stampa un messaggio di errore, @return false
			 if (!sock.isConnected()) {
				 System.err.println("Connetti prima il socket tramite Model");
				 return null;
			 }
			
			 for (String s : stati) {
				 
				// Viene settato il messaggio/riga da inviare al server
				outToServer.write(s);
				outToServer.flush();
				
				// Input dal server
				cbs = new char[1024];
				bf.read(cbs);
				
				//  Interpretazione risposta del Gateway
				String rispDalServer =  String.format("%s", String.copyValueOf(cbs));
				List<String> msgscmp = reader.scomponiMessaggio(rispDalServer); 
				for (String stringa: msgscmp) {
					EventType evento = reader.interpretaMessagio(stringa);
					model.setStatoOggetto(evento);
				}
				
			 }
			 BTicinoController.inizializzazione = true;
		
		} catch (UnknownHostException e) {
			e.printStackTrace();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		return eventi;
	}

		

	/**
	 * Metodo che chiude la connessione del Socket
	 */
	public void close() {
		if (this.sock.isConnected()) {
			try {
				sock.close();
				if (sock.isClosed()) {
					System.out.println("Socket della sessione comandi chiuso con successo.");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}







	


}
