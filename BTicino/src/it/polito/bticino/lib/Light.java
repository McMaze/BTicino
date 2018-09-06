package it.polito.bticino.lib;

import java.util.*;
import it.polito.bticino.lib.LightStatus.LightStatusName;


public class Light{

	private final int who = 1;
	private String name;
	private Map<LightStatusName, LightStatus> what;
	private int where;
	private Model model;
	
	private LightStatus status;
	
	public Light(int where, String name, Model model) {
		what= new HashMap<LightStatusName, LightStatus>();
		what.put(LightStatusName.ON, new LightStatus(LightStatusName.ON, 1));
		what.put(LightStatusName.OFF, new LightStatus(LightStatusName.OFF, 0));
		
		this.where=where;
		this.name = name;
		this.model=model;
	}
	

	public int getWho() {
		return who;
	}
	

	public int getWhere() {
		return where;
	}
	
	
	public String getLightName() {
		return name;
	}
	
	public LightStatus getStato() {
		return status;
	}
	

	public void setStato(LightStatus status) {
		this.status=status;
	}
	
	public void TurnOn() { 
		model.getSocket().sendMessage(who, what.get(LightStatusName.ON).getNumber(), where);
	}
	
	public void TurnOff() {
		model.getSocket().sendMessage(who, what.get(LightStatusName.OFF).getNumber(), where);
		
	}

	public Map<LightStatusName, LightStatus> getWhat() {
		return what;
	}

	
	
	
	
}
