package it.polito.bticino.connessione;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class InputReader {
	
	private static String ACK = "*#*1##";
	private static String NACK = "*#*0##";
	private String message;
	
	
	
	//lettura risposte del server, vengono salvati in una lista, comunque questo metodo serve solo a vedere se il server
	//invia una risposta negativa o positiva, non per interpretare gli stati dei dispositivi
	public boolean readMessage(String message) {	
		this.message= message;
		
		boolean risposta = false;
		List<String> inputs = new ArrayList<String>();
		String comando = "";
		String provvisoria = "";
		
		
		StringTokenizer st = new StringTokenizer(message, "*");
		try {
			while((provvisoria=st.nextToken())!=null || st.nextToken().compareTo("#")==0) {
				comando = comando+"*"+provvisoria;
				if(comando.compareTo(ACK)==0) {
					risposta = true;
				}
				if (comando.compareTo(NACK)==0) {
					risposta = false;
				}
				if (comando.startsWith("*") && comando.endsWith("##")) {
					inputs.add(comando);
					comando ="";
				}
				
			}
			
		} catch(NoSuchElementException nsee) {
		}
		return risposta;
	}

	

	public String getMessage() {
		return message;
	}

}
