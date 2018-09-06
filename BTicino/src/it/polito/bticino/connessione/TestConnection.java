package it.polito.bticino.connessione;

public class TestConnection {

	public static void main(String[] args) {
	
		BTicinoSocket sock = new BTicinoSocket();
		boolean sessioneComandi = sock.sendMessage("*99*0##");
		if (sessioneComandi == true) {
			System.out.println("Sessione di comandi stabilita");
		}
		else {
			System.out.println("Impossibile stabilire una sessione di comandi");
		}
		
		sock.close();
	}

}
