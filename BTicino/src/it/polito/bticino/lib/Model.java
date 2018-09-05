package it.polito.bticino.lib;

import java.util.*;

import it.polito.bticino.connessione.*;
import it.polito.bticino.lib.Who.WhoName;

public class Model {

	
	private static BTicinoSocket sock;
	private Map<WhoName, Who> who;
	
	private Light luceAll;
	private Light luce1;
	private Light luce2;
	private Light luce3;
	private Automation tapparella;
	
	
	public Model() {
		
		sock = new BTicinoSocket();
		sock.sendMessage("*99*0##");
		who = new TreeMap<>();
		//aggiungo impianto luci
		who.put(WhoName.LIGHTING, new Who(WhoName.LIGHTING, 1));
		//aggiungo impianto automazione
		who.put(WhoName.AUTOMATION, new Who(WhoName.AUTOMATION, 2));
		
		luceAll = new Light(1, "lights", sock);
		luce1 = new Light(1, "light1", sock);
		luce2 = new Light(1, "light2", sock);
		luce3 = new Light(1, "light3", sock);
		
		tapparella = new Automation(21, "tapparella", sock);
		
	}
	
	
	/*public void getStati() {
		List<String> stati = new ArrayList<String>(sock.getStati());
	}*/


	public Light getLuceAll() {
		return luceAll;
	}


	public Light getLuce1() {
		return luce1;
	}


	public Light getLuce2() {
		return luce2;
	}


	public Light getLuce3() {
		return luce3;
	}


	public Automation getTapparella() {
		return tapparella;
	}


	public Map<WhoName, Who> getWho() {
		return who;
	}




	
	

	
}
