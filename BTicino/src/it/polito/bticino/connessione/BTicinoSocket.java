package it.polito.bticino.connessione;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;


public class BTicinoSocket extends Socket{
	
	private Socket sock ;
	private String hostIP = "192.168.0.35";
	private int port = 20000;
	private PrintWriter outToServer;
	private InputStreamReader inputStreamReader;
	private BufferedReader bf;
	private char[] cbs = new char[1024];
	
	//private InputReader inputReader;
	
	
	/**
	 * BTicinoSocket apre la connessione, solo per inviare messaggi
	 */
	public BTicinoSocket() {
		try 
		{ 
			sock = new Socket();
			sock.connect(new InetSocketAddress(hostIP, port), 0);
			
    			outToServer = new PrintWriter(sock.getOutputStream());
    			inputStreamReader = new InputStreamReader(sock.getInputStream());
	    		bf = new BufferedReader (inputStreamReader);
    		
		    if (!sock.isConnected())
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
		String msg= message;
		
		try {
			// Viene aperto il socket con indirizzo IP e la relativa porta per connettersi al server
			 if (!sock.isConnected())
				 sock = new BTicinoSocket();
			
			// Viene settato il messaggio/riga da inviare al server
			outToServer.write(msg);
			outToServer.flush();
			
			// Viene stampato l'input stream dal server
			int risposta = bf.read(cbs);
			String dalServer = String.copyValueOf(cbs);
			System.out.println("sessione: " +dalServer);
			
			
			return risposta ;
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	
	}
	
	/**
	 * Metodo per inviare un messaggio tramite BTicinoSocket
	 * 
	 * @param who, a chi inviarlo
	 * @param what, cosa deve fare
	 * @param where, che oggetto deve eseguirlo
	 * @return
	 */
	public int sendMessage(int who, Integer what, int where) {
		
		// Crea il messaggio da inviare al server BTicino
		String message= "*"+who+"*"+what+"*"+where+"##";
	
		try {
			
			// Viene aperto il socket con indirizzo IP e la relativa porta per connettersi al server
			 if (!sock.isConnected())
				 sock = new BTicinoSocket();
			
			// Viene settato il messaggio/riga da inviare al server
			outToServer.write(message);
			outToServer.flush();
			
			
			// Viene stampato l'input stream dal server
			int risposta = bf.read(cbs);
			String dalServer = String.copyValueOf(cbs);
			System.out.println("messaggio: " +dalServer);
			
			return risposta;
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	/**
	 * Metodo per interrogare sullo stato di tutti gli oggetti 
	 * @param who
	 * @return
	 */
	/*
	public List<String> getStati() {
		String message = "*#1*1##*#2*21##";
		List<String> risultato = new ArrayList<String>();
		try {
			
			// Viene aperto il socket con indirizzo IP e la relativa porta per connettersi al server
			 if (!sock.isConnected())
				 sock = new BTicinoSocket();
			
			// Viene aperto un output stream connesso alla socket
			DataOutputStream outToServer = new DataOutputStream(sock.getOutputStream());
			
			// Viene creato un input strem connesso alla socket
			BufferedReader bf = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			
			// Viene settato il messaggio/riga da inviare al server
			outToServer.writeBytes(message);
			
			// Viene stampato l'input stream dal server
			String messageModified = bf.readLine();
			System.out.println(messageModified);
			//risultato = this.readInput(messageModified);
		
		} catch (UnknownHostException e) {
			e.printStackTrace();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		return risultato;
	}
	*/
		

	/**
	 * Metodo che chiude la connessione del Socket
	 */
	public void close() {
		if (this.sock.isConnected()) {
			try {
				sock.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


}
