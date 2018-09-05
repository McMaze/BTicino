package it.polito.bticino.lib;

public class LightStatus {

	public enum LightStatusName {
		ON, OFF;
	}
	
	private LightStatusName statusName;
	private int number;
	
	public LightStatus(LightStatusName statusName, int number) {
		this.statusName = statusName;
		this.number = number;
	}

	public LightStatusName getStatusName() {
		return statusName;
	}

	public int getNumber() {
		return number;
	}
	
	
}
