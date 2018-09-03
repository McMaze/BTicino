package it.polito.bticino.lib;

import java.util.*;

public class TestIlluminazione {
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
	
		List<Light>  luci = new ArrayList<Light>();
		
		//Tutte where = 1;
		Light lA = new Light(1);
		
		Light lB = new Light(11);
		//Illuminazione lC = new Illuminazione(12);
		//Illuminazione lD = new Illuminazione(13);
		
		luci.add(lA);
		
		//luci.add(lB);
		//luci.add(lC);
		
		lA.TurnOff();
	}
}
