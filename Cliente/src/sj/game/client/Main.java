package sj.game.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JPanel;

import sj.game.common.C;
import sj.game.common.ClientTextMessage;
import sj.game.common.ServerMessage;
import sj.game.common.ServerTextMessage;

public class Main{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		boolean foundServer = false; 
		
		
		
		while(!foundServer){
			//sent output to server
			try {
				System.out.println("Attempting connection at " + C.HOST + ":" + C.PORT);
				Socket s = new Socket(C.HOST, C.PORT);
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				oos.writeObject(new ClientTextMessage("Hello my friend"));
				foundServer = true;
				connected(s);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				System.out.println("No server found, trying again");
			}
		}
		
		
		
		
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void connected(Socket s){
		System.out.println("Server found! Starting input connections");
		
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				boolean running = true;
				
				while(running){
					
					try {
						ObjectInputStream ots = new ObjectInputStream(s.getInputStream());
						System.out.println("Message recieved");
						try {
							ServerMessage o = (ServerMessage) ots.readObject();
							if(o instanceof ServerTextMessage){
								ServerTextMessage stm = (ServerTextMessage) o;
								System.out.println("Message: " + stm.getText());
							}
							
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						System.out.println("Server closed");
						System.exit(0);
					}
					
				}
			}
		});
		
		t.run();
		
		//create thread to wait for server inputs
		
		
		System.out.println("Server found! Starting game mechanics");
		
		//run game
		
		boolean playing = true;
		
		while(playing){
			
			
			
		}
		
	}

}
