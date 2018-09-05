package it.polito.bticino.lib;

import java.util.*;
import it.polito.bticino.connessione.BTicinoSocket;
import it.polito.bticino.lib.AutomationStatus.AutomationStatusName;


public class Automation {
	
	private final int who= 2;
	
	private Map<AutomationStatusName, AutomationStatus> what;
	private int where;
	private String name;
	private AutomationStatus status;
	private BTicinoSocket sock;
	
	
	public Automation(int where, String name , BTicinoSocket sock) {
		
		
		what = new TreeMap<AutomationStatusName, AutomationStatus>();
		what.put(AutomationStatusName.UP, new AutomationStatus(AutomationStatusName.UP, 1));
		what.put(AutomationStatusName.DOWN, new AutomationStatus(AutomationStatusName.DOWN, 2));
		what.put(AutomationStatusName.STOP, new AutomationStatus(AutomationStatusName.STOP, 0));
		
		this.where = where;
		this.name = name;
		this.sock = sock;
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
		sock.sendMessage(who, what.get(AutomationStatusName.UP).getNumber(), where);
		
	}
	
	public void down() {
		sock.sendMessage(who, what.get(AutomationStatusName.DOWN).getNumber(), where);
	}

	public void stop() {
		sock.sendMessage(who, what.get(AutomationStatusName.STOP).getNumber(), where);
	}

	public Map<AutomationStatusName, AutomationStatus> getWhat() {
		return what;
	}

	
	

	
}
