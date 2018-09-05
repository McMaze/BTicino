package it.polito.bticino.lib;


public class AutomationStatus {
	

	public enum AutomationStatusName {
		UP, DOWN, STOP;
	}
	
	private AutomationStatusName statusName;
	private int number;
	
	public AutomationStatus(AutomationStatusName statusName, int number) {
		this.statusName = statusName;
		this.number = number;
	}

	public AutomationStatusName getStatusName() {
		return statusName;
	}

	public int getNumber() {
		return number;
	}
	

}
