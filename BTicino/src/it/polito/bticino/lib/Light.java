package it.polito.bticino.lib;

import java.util.*;
import it.polito.bticino.connessione.BTicinoSocket;


public class Light implements ObjectBTicino{

	public enum LightStatus {
		ON, OFF, P60;
	}
	
	
	private final int who = 1;
	private Map<LightStatus, Integer> what;
	private int where;
	
	private LightStatus status;
	
	public Light(int where) {
		what= new HashMap<LightStatus, Integer>();
		what.put(LightStatus.ON,1);
		what.put(LightStatus.OFF,0);
		what.put(LightStatus.P60, 6);
		
		this.where=where;
	}
	

	@Override
	public int getWho() {
		return who;
	}
	

	@Override
	public int getWhere() {
		return where;
	}
	
	public LightStatus getStato() {
		return status;
	}
	

	public void setStato(LightStatus status) {
		this.status=status;
	}
	
	public void TurnOn() { 
		BTicinoSocket sock = new BTicinoSocket();
		boolean on = sock.sendMessage(who, what.get(LightStatus.ON), where);
		if (on == true) {
			this.setStato(LightStatus.ON);
		}
		sock.close();
	}
	
	public void TurnOff() {
		BTicinoSocket sock = new BTicinoSocket();
		boolean off = sock.sendMessage(who, what.get(LightStatus.OFF), where);
		if (off == true) {
			this.setStato(LightStatus.OFF);
		}
		sock.close();
	}

	public Map<LightStatus, Integer> getWhat() {
		return what;
	}

	
	
	
	
}
