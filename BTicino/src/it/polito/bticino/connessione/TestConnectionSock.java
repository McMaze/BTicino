package it.polito.bticino.connessione;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TestConnectionSock {
	
	private static String hostIP = "192.168.0.35";
	private static int port = 20000;

	public static void main(String[] args) {
		Socket sock = new Socket();
		try {
			sock.connect(new InetSocketAddress(hostIP, port), 0);
			
			 if (!sock.isConnected())
		    		System.err.println("Non connesso");
		    else {
		    		PrintWriter outToServer = new PrintWriter(sock.getOutputStream());
		    		InputStreamReader inputStreamReader = new InputStreamReader(sock.getInputStream());
		    		BufferedReader bf = new BufferedReader(inputStreamReader);
		    		
		    		// ack di apertura connessione
		    		char[] cbs = new char[1024];
		    		int resp = bf.read(cbs);
		    		System.out.println("apertura connessione " + resp + " - " + String.copyValueOf(cbs));
		    		
		    		if(resp != -1) {
		    			outToServer.write("*99*9##");
		    			outToServer.flush();
		    			resp = bf.read(cbs);
		    			System.out.println("apertura sessione " + resp + " - " + String.copyValueOf(cbs));
		    			
		    			if(resp != -1) {
			    			outToServer.write("*1*0*1##");
			    			outToServer.flush();
			    			resp = bf.read(cbs);
			    			System.out.println("luci " + resp + " - " + String.copyValueOf(cbs));
			    		}
		    		}
		    		
		    		outToServer.close();
		    		bf.close();
		    		inputStreamReader.close();
		    		sock.close();
		    }
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	 
	}

}
