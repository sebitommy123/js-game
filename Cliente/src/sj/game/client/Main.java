package sj.game.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import sj.game.common.C;
import sj.game.common.ClientTextMessage;
import sj.game.common.ServerTextMessage;

public class Main {

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
				System.out.println("No server found, try again later ):");
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
				
				boolean running = false;
				
				while(running){
					
					try {
						ObjectInputStream ots = new ObjectInputStream(s.getInputStream());
						System.out.println("Message recieved");
						try {
							ServerTextMessage stm = (ServerTextMessage) ots.readObject();
							System.out.println(stm);
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							System.out.println("Message not string");
						}
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
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
