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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + where;
		result = prime * result + who;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Automation other = (Automation) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (where != other.where)
			return false;
		if (who != other.who)
			return false;
		return true;
	}

	
	

	
}
