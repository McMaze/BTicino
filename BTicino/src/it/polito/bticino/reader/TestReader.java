package it.polito.bticino.reader;

import it.polito.bticino.reader.Reader.EventType;

public class TestReader {

	public static void main(String[] args) {
		
		Reader r = new Reader();
		
		EventType risposta1 = r.interpretaMessagio("*#*1##");
		EventType risposta2 = r.interpretaMessagio("*#*0##");
		EventType risposta3 = r.interpretaMessagio("*1*1*12##");
		EventType risposta4 = r.interpretaMessagio("*1*0*13##");
		
		System.out.println(risposta1);
		System.out.println(risposta2);
		System.out.println(risposta3);
		System.out.println(risposta4);
		
		System.out.println(r.interpretaMessagio("*#*1##"));

	}

}
