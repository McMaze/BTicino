package it.polito.bticino.lib;

import java.util.*;
import it.polito.bticino.connessione.BTicinoSocket;


public class Automation implements ObjectBTicino{

	public enum AutomationStatus{
		STOP, UP, DOWN;
		
	}
	
	private final int who= 2;
	
	private Map<AutomationStatus, Integer> what;
	private int where;
	
	private BTicinoSocket sock;
	private AutomationStatus status;
	
	
	public Automation(int where) {
		
		
		what = new HashMap<AutomationStatus, Integer>();
		what.put(AutomationStatus.UP, 1);
		what.put(AutomationStatus.DOWN, 2);
		what.put(AutomationStatus.STOP, 0);
		
		this.where = where;
	}
	
	@Override
	public int getWho() {
		return who;
	}
	
	@Override
	public int getWhere() {
		return where;
	}
	
	public AutomationStatus getStato() {
		return this.status;
	}

	public void setStato(AutomationStatus status) {
		this.status=status;
	}
	
	
	public void up() {
		BTicinoSocket sock = new BTicinoSocket();
		boolean up = sock.sendMessage(who, what.get(AutomationStatus.UP), where);
		if (up==true) {
			status = AutomationStatus.UP;
		}
		sock.close();
	}
	
	public void down() {
		BTicinoSocket sock = new BTicinoSocket();
		boolean down = sock.sendMessage(who, what.get(AutomationStatus.DOWN), where);
		if (down ==true) {
			status = AutomationStatus.DOWN;
		}
		sock.close();
	}

	public void stop() {
		sock = new BTicinoSocket();
		boolean stop = sock.sendMessage(who, what.get(AutomationStatus.STOP), where);
		if (stop==true) {
			status = AutomationStatus.STOP;
		}
	}

	public Map<AutomationStatus, Integer> getWhat() {
		return what;
	}

	
	

	
}
