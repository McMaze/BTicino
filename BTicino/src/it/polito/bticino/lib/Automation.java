package it.polito.bticino.lib;

import java.util.*;
import it.polito.bticino.lib.AutomationStatus.AutomationStatusName;

public class Automation {
	
	private final int who= 2;
	
	private Map<AutomationStatusName, AutomationStatus> what;
	private int where;
	private String name;
	private AutomationStatus status;
	private Model model;
	
	
	public Automation(int where, String name , Model model) {
		
		
		what = new TreeMap<AutomationStatusName, AutomationStatus>();
		what.put(AutomationStatusName.UP, new AutomationStatus(AutomationStatusName.UP, 1));
		what.put(AutomationStatusName.DOWN, new AutomationStatus(AutomationStatusName.DOWN, 2));
		what.put(AutomationStatusName.STOP, new AutomationStatus(AutomationStatusName.STOP, 0));
		
		this.where = where;
		this.name = name;
		this.model = model;
	}
	

	public int getWho() {
		return who;
	}
	

	public int getWhere() {
		return where;
	}
	
	public String getAutomationName() {
		return name;
	}
	
	public AutomationStatus getStato() {
		return this.status;
	}

	public void setStato(AutomationStatus status) {
		this.status=status;
	}
	
	
	public void up() {
		model.getSocket().sendMessage(who, what.get(AutomationStatusName.UP).getNumber(), where);
		
	}
	
	public void down() {
		model.getSocket().sendMessage(who, what.get(AutomationStatusName.DOWN).getNumber(), where);
	}

	public void stop() {
		model.getSocket().sendMessage(who, what.get(AutomationStatusName.STOP).getNumber(), where);
	}

	public Map<AutomationStatusName, AutomationStatus> getWhat() {
		return what;
	}

	
	

	
}
