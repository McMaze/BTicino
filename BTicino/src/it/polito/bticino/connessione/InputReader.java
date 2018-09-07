package it.polito.bticino.connessione;


public class InputReader {
	
	private String ACK = "*#*1##";
	private String NACK = "*#*0##";
	private String message;
	
	
	
	//lettura risposte del server, vengono salvati in una lista, comunque questo metodo serve solo a vedere se il server
	//invia una risposta negativa o positiva, non per interpretare gli stati dei dispositivi
	public boolean readMessage(String message) {	
		this.message=message.trim();
		boolean risposta = false;
		
		if (message.compareTo(ACK)==0)
			risposta =true;
		if (message.compareTo(NACK)==0)
			risposta = false;
		
		return risposta;
	}

	

	public String getMessage() {
		return message;
	}

}
