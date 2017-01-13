package sj.game.client;

import java.io.IOException;
import java.net.Socket;

import javax.swing.JOptionPane;
import sj.game.common.C;
import sj.game.common.ClientTextMessage;
import sj.game.common.LoginRequest;

public class Main{
		
	public static boolean respondedToCurrentQuery = false;
	public static long sinceCurrentQuery = 0;
	public static Object currentQueryResponse = null;
	private static Server server;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		boolean foundServer = false; 
		
		
		
		while(!foundServer){
			//sent output to server
			try {
				System.out.println("Attempting connection at " + C.HOST + ":" + C.PORT);
				Socket s = new Socket(C.HOST, C.PORT);
				server = new Server(s);
				
				server.send(new ClientTextMessage("Hello my friend"));
				foundServer = true;
				connected();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				System.err.println("[ERROR] No server found, trying again");
			}
		}
		
		
		
		
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static boolean login(String user, String pass) {
		server.send(new LoginRequest(user, pass));
		
		sinceCurrentQuery = System.currentTimeMillis();
		
		while(!respondedToCurrentQuery){
			
			if(System.currentTimeMillis() - sinceCurrentQuery > 5 * 1000){
				return false;
				
			}
			
		}
		
		return true;
	}

	private static void register(String user, String pass) {
		// TODO Auto-generated method stub
		
	}

	public static void connected(){
		String rl = JOptionPane.showInputDialog("(L) for login and (R) for register");
		if(rl.equals("L")){
			String user = JOptionPane.showInputDialog("Username: ");
			String pass = JOptionPane.showInputDialog("Password: ");
				if(login(user,pass)){
					
					
				}else{
					System.out.println("USERNAME INCORRECT");
					System.exit(0);
					
				}
		}else{
			String user = JOptionPane.showInputDialog("Username: ");
			String pass = JOptionPane.showInputDialog("Password: ");
			String pass2 = JOptionPane.showInputDialog("Password: ");
			if(pass.equals(pass2)){
				register(user, pass);
			}
		}
		
		System.out.println("Server found! Starting input connections");
		
		
		
		//create thread to wait for server inputs
		
		
		System.out.println("Server found! Starting game mechanics");
		
		//run game
		
		boolean playing = true;
		
		while(playing){
			
			
			
		}
		
	}

}
