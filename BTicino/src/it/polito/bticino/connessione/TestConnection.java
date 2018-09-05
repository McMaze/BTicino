package it.polito.bticino.connessione;

public class TestConnection {

	public static void main(String[] args) {
	
		BTicinoSocket sock = new BTicinoSocket();
		sock.sendMessage("*99*0##");
		sock.close();
	}

}
