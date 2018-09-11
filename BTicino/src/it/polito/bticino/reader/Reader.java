package it.polito.bticino.reader;

import it.polito.bticino.lib.Model;
import java.util.*;

public class Reader {
	
	private static final String ACK = "*#*1##";
	private static final String NACK = "*#*0##";
	private static final String LUCION = "*1*1*1##";
	private static final String LUCIOFF = "*1*0*1##";
	private static final String LUCE1ON = "*1*1*11##";
	private static final String LUCE1OFF = "*1*0*11##";
	private static final String LUCE2ON = "*1*1*12##";
	private static final String LUCE2OFF = "*1*0*12##";
	private static final String LUCE3ON = "*1*1*13##";
	private static final String LUCE3OFF = "*1*0*13##";
	private static final String TAPPARELLAABBASSATA = "*2*2*21##";
	private static final String TAPPARELLAALZATA  = "*2*1*21##";
	private static final String TAPPARELLASTOP = "*2*0*21##";
	
	
	public Model model;
	
	public enum EventType{
		ACK, NACK, 
		LUCIACCESE, LUCISPENTE,
		LUCE1ACCESA, LUCE1SPENTA,
		LUCE2ACCESA, LUCE2SPENTA,
		LUCE3ACCESA, LUCE3SPENTA,
		TAPPARELLASU, TAPPARELLAGIU, TAPPARELLASTOP; 
	}
	public Reader() {
	}
	
	public Reader(Model model) {
		this.model = model;
	}

	
	public List<EventType> readMessage(String message) {
		
		//List<String> inputs = new ArrayList<String> (this.scomponiMessaggio(message));
		List<EventType> eventi = new ArrayList<EventType>();
		
		EventType etype = this.interpretaMessagio(message);
		eventi.add(etype);
		
		return eventi;
	}

	public List<String> scomponiMessaggio(String message){
		List<String> lista = new ArrayList<String>();
		
		StringTokenizer st = new StringTokenizer(message, "*");
		
		String input = "";
		String prova = "";
		try {
			while((prova = st.nextToken() ) !=null) {
				input += "*"+prova;
				if (input.contains("##")) {
					lista.add(input);
					System.out.println(input);
					input = "";
				}
			}
		} catch (NoSuchElementException e) {
			
		}
		return lista;
	}
	
	public EventType interpretaMessagio (String m) {
		String message = m.trim();
		if (message.compareTo(ACK)==0)
			return EventType.ACK;
		if (message.compareTo(NACK)==0)
			return EventType.NACK;  
		
		if (message.compareTo(LUCION)==0)
			return EventType.LUCIACCESE;  
		if (message.compareTo(LUCIOFF)==0)
			return EventType.LUCISPENTE;  
		
		if (message.compareTo(LUCE1ON)==0)
			return EventType.LUCE1ACCESA;  
		if (message.compareTo(LUCE1OFF)==0)
			return EventType.LUCE1SPENTA;  
		
		
		if (message.compareTo(LUCE2ON)==0)
			return EventType.LUCE2ACCESA;  
		if (message.compareTo(LUCE2OFF)==0)
			return EventType.LUCE2SPENTA;  
		
		if (message.compareTo(LUCE3ON)==0)
			return EventType.LUCE3ACCESA;  
		if (message.compareTo(LUCE3OFF)==0)
			return EventType.LUCE3SPENTA;
		
		
		if (message.compareTo(TAPPARELLAALZATA)==0)
			return EventType.TAPPARELLASU;  
		if (message.compareTo(TAPPARELLAABBASSATA)==0)
			return EventType.TAPPARELLAGIU;
		if (message.compareTo(TAPPARELLASTOP)==0)
			return EventType.TAPPARELLASTOP;
		
	
	return null;
	}
	
	
	
}
