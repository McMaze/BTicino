package it.polito.bticino.lib;

import java.util.*;

import it.polito.bticino.connessione.*;
import it.polito.bticino.lib.AutomationStatus.AutomationStatusName;
import it.polito.bticino.lib.LightStatus.LightStatusName;
import it.polito.bticino.lib.Who.WhoName;
import it.polito.bticino.main.BTicinoController;
import it.polito.bticino.reader.Reader;
import it.polito.bticino.reader.Reader.EventType;


public class Model {

	
	public BTicinoSocket sock;
	public BTicinoSocketMonitor sockMonitor;
	public Reader reader;
	public BTicinoController controller;
	
	private Map<WhoName, Who> who;
	private Light luceGenerale;
	private Light luce1;
	private Light luce2;
	private Light luce3;
	private Automation tapparella;
	
	
	public Model(BTicinoController controller) {
		
		this.controller = controller;
		reader = new Reader(this);
		boolean isSockConnected = this.creaSocket(reader, this);
		boolean isSockMonConnected = this.creaSocketMonitor(reader, this);
		
		if (isSockConnected == false ) {
			System.err.println("Impossibile stabilire una sessione di comandi");
			
		}
		if (isSockMonConnected == false) {
			System.err.println("Impossibile stabilire una sessione di eventi");
		} else {
			this.creaOggetti();
			sock.getStati();
		}
		
			
	}
	

	public boolean creaSocket(Reader reader, Model model) {
		sock = new BTicinoSocket(reader, model);
		
		//Invio il messaggio per stabilire una sessione di comandi
		boolean sessioneComandi = sock.apriSessioneComandi();
		
		if (sessioneComandi == true)
			return true;
		
		return false;
	}


	public boolean creaSocketMonitor(Reader reader, Model model) {
		sockMonitor = new BTicinoSocketMonitor(reader, model);
		
		//Invio il messaggio per stabilire una sessione di comandi
		boolean sessioneEventi = sockMonitor.apriSessioneEventi();
		
		if (sessioneEventi == true) {
			return true;
		}
		
		return false;
	}
	
	public void creaOggetti() {
		who = new TreeMap<>();
		
		//aggiungo impianto luci
		who.put(WhoName.LIGHTING, new Who(WhoName.LIGHTING, 1));
		
		luceGenerale = new Light(1, "luceGenerale", this);
		luce1 = new Light(11, "luce1", this);
		luce2 = new Light(12, "luce2", this);
		luce3 = new Light(13, "luce3", this);
		/*who.get(WhoName.LIGHTING).getOggetti().put("luceGenerale", luceGenerale);
		who.get(WhoName.LIGHTING).getOggetti().put("luce1", luce1);
		who.get(WhoName.LIGHTING).getOggetti().put("luce2", luce2);
		who.get(WhoName.LIGHTING).getOggetti().put("luce3", luce3);*/
		
		//aggiungo impianto automazione
		who.put(WhoName.AUTOMATION, new Who(WhoName.AUTOMATION, 2));

		tapparella = new Automation(21, "tapparella", this);
		/*who.get(WhoName.AUTOMATION).getOggetti().put("tapparella", tapparella);*/
		
	}
	

	public void setStatoOggetto(EventType stato) {
			
			if (stato== EventType.ACK) {
				
			}
			if(stato == EventType.NACK) {
				
			}
			
			if(stato == EventType.LUCIACCESE) {
				this.getLuceGenerale().setStato(this.getLuceGenerale().getWhat().get(LightStatusName.ON));
				this.getLuce1().setStato(this.getLuce1().getWhat().get(LightStatusName.ON));
				this.getLuce2().setStato(this.getLuce2().getWhat().get(LightStatusName.ON));
				this.getLuce3().setStato(this.getLuce3().getWhat().get(LightStatusName.ON));
			}
			if (stato == EventType.LUCISPENTE) {
				this.getLuceGenerale().setStato(this.getLuceGenerale().getWhat().get(LightStatusName.OFF));
				this.getLuce1().setStato(this.getLuce1().getWhat().get(LightStatusName.OFF));
				this.getLuce2().setStato(this.getLuce2().getWhat().get(LightStatusName.OFF));
				this.getLuce3().setStato(this.getLuce3().getWhat().get(LightStatusName.OFF));
			}
			if (stato == EventType.LUCE1ACCESA){
				this.getLuce1().setStato(this.getLuce1().getWhat().get(LightStatusName.ON));
			}
			if (stato == EventType.LUCE1SPENTA){
				this.getLuce1().setStato(this.getLuce1().getWhat().get(LightStatusName.OFF));
			}
			if (stato == EventType.LUCE2ACCESA){
				this.getLuce2().setStato(this.getLuce2().getWhat().get(LightStatusName.ON));
			}
			if (stato == EventType.LUCE2SPENTA){
				this.getLuce2().setStato(this.getLuce2().getWhat().get(LightStatusName.OFF));
			}
			if (stato == EventType.LUCE3ACCESA){
				this.getLuce3().setStato(this.getLuce3().getWhat().get(LightStatusName.ON));
			}
			if (stato == EventType.LUCE3SPENTA){
				this.getLuce3().setStato(this.getLuce3().getWhat().get(LightStatusName.OFF));
			}
			if (stato == EventType.TAPPARELLASU) {
				this.getTapparella().setStato(this.getTapparella().getWhat().get(AutomationStatusName.UP));
			}
			if (stato == EventType.TAPPARELLAGIU) {
				this.getTapparella().setStato(this.getTapparella().getWhat().get(AutomationStatusName.DOWN));
			}
			if (stato == EventType.TAPPARELLASTOP) {
				this.getTapparella().setStato(this.getTapparella().getWhat().get(AutomationStatusName.STOP));
			}
				
	}
	
	

	public void setStatoLuceGenerale() {
		if (this.getLuce1().getStato()!=null && this.getLuce2().getStato()!=null && this.getLuce3().getStato()!=null) {
			if ( this.getLuce1().getStato().getStatusName() == LightStatusName.ON &&
				this.getLuce2().getStato().getStatusName() == LightStatusName.ON	&&
				this.getLuce2().getStato().getStatusName() == LightStatusName.ON	) {
				
				this.getLuceGenerale().setStato(this.luceGenerale.getWhat().get(LightStatusName.ON));
			}
			else {
				this.getLuceGenerale().setStato(this.luceGenerale.getWhat().get(LightStatusName.OFF));
			}
				
		}
	}	
	
	public void readSockMonitor() {
		sockMonitor.readInput();
	}
	

	public Light getLuceGenerale() {
		return luceGenerale;
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


	public Map<WhoName, Who> getWho() {
		return who;
	}

	public BTicinoSocket getSocket() {
		return this.sock;
	}

	public BTicinoSocketMonitor getSocketMonitor() {
		return this.sockMonitor;
	}


	public void close() {
		//sock.close();
		sockMonitor.close();
		
	}
	
}
