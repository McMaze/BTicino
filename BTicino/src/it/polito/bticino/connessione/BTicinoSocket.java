package it.polito.bticino.connessione;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;

public class BTicinoSocket extends Socket{
	
	private Socket sock ;
	private String hostIP = "192.168.0.35";
	private int port = 20000;

	
	/**
	 * BTicinoSocket apre la connessione, solo per inviare messaggi
	 */
	public BTicinoSocket() {
		try 
		{ 
			sock = new Socket(hostIP, port);
		    if (!sock.isConnected())
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

	
	
	public boolean sendMessage(String message) {
		
		// Crea il messaggio da inviare al server BTicino
		String msg= message;
		
		try {
			// Viene aperto il socket con indirizzo IP e la relativa porta per connettersi al server
			 if (!sock.isConnected())
				 sock = new BTicinoSocket();
			
			// Viene aperto un output stream connesso alla socket
			DataOutputStream outToServer = new DataOutputStream(sock.getOutputStream());
			
			// Viene creato un input strem connesso alla socket
			BufferedReader bf = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			
			// Viene settato il messaggio/riga da inviare al server
			outToServer.writeBytes(msg);
			
			// Viene stampato l'input stream dal server
			String messageModified = bf.readLine();
			
			System.out.println(messageModified);
			InputReader ir = new InputReader();
			boolean risposta = ir.readMessage(messageModified);
			
			
			return risposta;
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	
	}
	
	/**
	 * Metodo per inviare un messaggio tramite BTicinoSocket
	 * 
	 * @param who, a chi inviarlo
	 * @param what, cosa deve fare
	 * @param where, che oggetto deve eseguirlo
	 * @return
	 */
	public boolean sendMessage(int who, Integer what, int where) {
		
		// Crea il messaggio da inviare al server BTicino
		String message= "*"+who+"*"+what+"*"+where+"##";
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
			InputReader ir = new InputReader();
			boolean risposta = ir.readMessage(messageModified);
			
			return risposta;
			
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	
	}
	
	
	/**
	 * Metodo per interrogare sullo stato di tutti gli oggetti
	 * @param who
	 * @return
	 */
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
			sock.close();
			//risultato = this.readInput(messageModified);
		
		} catch (UnknownHostException e) {
			e.printStackTrace();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		return risultato;
	}
		

	
		

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
