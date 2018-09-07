package it.polito.bticino.connessione;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TestConnectionSockMonitor {

	private static String hostIP = "192.168.0.35";
	private static int port = 20000;

	public static void main(String[] args) {
	
		Socket sockM = new Socket();
		try {
			sockM.connect(new InetSocketAddress(hostIP, port));

			 if (!sockM.isConnected())
		    		System.err.println("Non connesso");
		    else {
		    		PrintWriter outToServer = new PrintWriter(sockM.getOutputStream());
		    		InputStreamReader inputStreamReader = new InputStreamReader(sockM.getInputStream());
		    		BufferedReader bf = new BufferedReader(inputStreamReader);
		    		
		    		// ack di apertura connessione
		    		char[] cbs = new char[1024];
		    		int resp = bf.read(cbs);
		    		System.out.println("apertura connessione " + resp + " - " + String.copyValueOf(cbs));
		    		
		    		if(resp != -1) {
		    			outToServer.write("*99*1##");
		    			outToServer.flush();
		    			resp = bf.read(cbs);
		    			System.out.println("apertura sessione " + resp + " - " + String.copyValueOf(cbs));
		    			
		    			if (resp != -1) {
		    				while(sockM.isConnected()) {
		    					resp = bf.read(cbs);
				    			System.out.println("messaggio dal server : " + resp + " - " + String.copyValueOf(cbs));
				    			
				    			if(resp == -1 ) {
				    				bf.close();
				    				outToServer.close();
				    				inputStreamReader.close();
				    				sockM.close();
				    				
				    				
				    			}
		    				}
		    			}
		    			
		    		}
		    		
		    }
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
