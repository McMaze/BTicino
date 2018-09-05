package it.polito.bticino.lib;

import java.util.Map;
import java.util.TreeMap;

public class Who {
	
	public enum WhoName {
		LIGHTING, AUTOMATION;
	}
	
	private WhoName name;
	private int number;
	private Map<String, Integer> oggetti;
	
	public Who(WhoName name, int number ) {
		this.name = name;
		this.number = number;
		this.oggetti = new TreeMap<String, Integer>();
	}

	public WhoName getName() {
		return name;
	}

	public int getNumber() {
		return number;
	}

	public Map<String, Integer> getOggetti() {
		return oggetti;
	}
	
	

}
