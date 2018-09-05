package it.polito.bticino.lib;

import java.util.*;

import it.polito.bticino.connessione.BTicinoSocket;

public class TestIlluminazione {
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
	
		BTicinoSocket sock = new BTicinoSocket();
		sock.sendMessage("*99*0##");
		List<Light>  luci = new ArrayList<Light>();
		
		//Tutte where = 1;
		Light lA = new Light(1, "lights", sock);
		
		Light lB = new Light(11, "light1", sock);
		//Illuminazione lC = new Illuminazione(12);
		//Illuminazione lD = new Illuminazione(13);
	
		lA.TurnOn();
	}
}
