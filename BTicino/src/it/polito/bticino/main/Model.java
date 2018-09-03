package it.polito.bticino.main;

import java.util.*;

import it.polito.bticino.connessione.*;
import it.polito.bticino.lib.*;
import it.polito.bticino.lib.Automation.AutomationStatus;
import it.polito.bticino.lib.Light.LightStatus;


public class Model {

	
	private BTicinoSocket sock;
	private BTicinoSocketMonitor sockMonitor;
	private Map<Integer, ObjectBTicino> oggetti;
	
	private Light luceAll;
	private Light luce1;
	private Light luce2;
	private Light luce3;
	private Automation tapparella;
	
	
	public Model() {
		oggetti = new TreeMap<>();
		luceAll = new Light(1);
		luce1 = new Light(11);
		luce2 = new Light(12);
		luce3 = new Light(13);
		tapparella = new Automation(21);
		
		oggetti.put(luceAll.getWhere(), luceAll);
		oggetti.put(luce1.getWhere(), luce1);
		oggetti.put(luce2.getWhere(), luce2);
		oggetti.put(luce3.getWhere(), luce3);
		oggetti.put(tapparella.getWhere(), tapparella);
		
		sockMonitor = new BTicinoSocketMonitor();
	}
	
	
	public void getStati() {
		sock =  new BTicinoSocket();
		List<String> stati = new ArrayList<String>(sock.getStati());
		this.readAndSetStati(stati);
		sock.close();
		
		/*sock = new BTicinoSocket();
		stato = sock.getStato(luce1.getWho(), luce1.getWhere());
		luce1.setStato(stato);
		sock.close();
		
		sock = new BTicinoSocket();
		stato = sock.getStato(luce2.getWho(), luce2.getWhere());
		luce2.setStato(stato);
		
		sock = new BTicinoSocket();
		stato = sock.getStato(luce3.getWho(), luce3.getWhere());
		luce3.setStato(stato);
		sock.close();*/
		
	}

	
	private void readAndSetStati(List<String> stati) {
		
		for (String stato : stati) {
			String input = "";
			String s = "";
			StringTokenizer st = new StringTokenizer(stato, "*");
			try {
				
			while((s=st.nextToken())!=null) {
				input += "*"+s;
				
				// se sono alla fine della stringa
				if (s.endsWith("##")) {
					
					//guarda se il numero prima esiste tra gli oggetti del model
					String objNumber = s.replace("##", "");
					if (this.getOggetti().get(Integer.parseInt(objNumber))!=null) {
						ObjectBTicino object = this.getOggetti().get(Integer.parseInt(objNumber));
		
						// se è una luce
						if (object instanceof Light){
						
							// se è accesa
							if (input.compareTo( "*"+object.getWho()+"*"+
									+((Light) object).getWhat().get(LightStatus.ON)+"*"+object.getWhere()+"##")==0) {
							input  = null;
							input = "";
							((Light) object).setStato(LightStatus.ON);
							}
							
							// se è spenta
							if (input.compareTo( "*"+object.getWho()+"*"+
									+((Light) object).getWhat().get(LightStatus.OFF)+"*"+object.getWhere()+"##")==0) {
								input = null;
								input = "";
								((Light) object).setStato(LightStatus.OFF);	
							}	
							
							//se il dimmer è al 60%
							if (input.compareTo( "*"+object.getWho()+"*"+
									+((Light) object).getWhat().get(LightStatus.P60)+"*"+object.getWhere()+"##")==0) {
								input = null;
								input = "";
								((Light) object).setStato(LightStatus.P60);	
							}	
						}
						
						
						// se è una tapparella
						if (object instanceof Automation) {
							
							// se è in STOP
							if (input.compareTo("*"+object.getWho()+"*"+
									((Automation) object).getWhat().get(AutomationStatus.STOP)+"*"+object.getWhere()+"##")==0) {
								input = null;
								input = "";
								((Automation) object).setStato(AutomationStatus.STOP);
							}
							
							// se è UP
							if (input.compareTo("*"+object.getWho()+"*"+
									((Automation) object).getWhat().get(AutomationStatus.UP)+"*"+object.getWhere()+"##")==0) {
								input = null;
								input = "";
								((Automation) object).setStato(AutomationStatus.UP);	
							}
							
							// se è DOWN
							if (input.compareTo("*"+object.getWho()+"*"+
									((Automation) object).getWhat().get(AutomationStatus.DOWN)+"*"+object.getWhere()+"##")==0) {
								input = null;
								input = "";
								((Automation) object).setStato(AutomationStatus.DOWN);
								}	
							}
						}
		
					}
				}
			}
			catch(NoSuchElementException e) {
				
			}
		}
	}


	public Light getLuceAll() {
		return luceAll;
	}


	public Light getLuce1() {
		return luce1;
	}


	public Light getLuce2() {
		return luce2;
	}


	public Light getLuce3() {
		return luce3;
	}


	public Automation getTapparella() {
		return tapparella;
	}


	public BTicinoSocketMonitor getSockMonitor() {
		return sockMonitor;
	}


	public Map<Integer, ObjectBTicino> getOggetti() {
		return oggetti;
	}
	

	
}
