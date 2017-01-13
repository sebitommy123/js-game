package sj.game.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import sj.game.common.C;

public class Server {
	private ServerSocket ss;

	public Server() {
		try {
			System.out.println("[INFO] Initializing server at port "+C.PORT+"...");
			setSs(new ServerSocket(C.PORT));
			Thread t = new Thread(new Runnable() {
				
				@Override
				public void run() {
					System.out.println("[INFO] Listening for clients...");
					while(!ss.isClosed()){
						try {
							Socket client = ss.accept();
							System.out.println("[INFO] Client connected from "+client.getInetAddress()+":"+client.getLocalPort()+"! Assigning listener...");
							listen(client);
						} catch (IOException e) {
							System.err.println("[ERROR] Server Socket is closed!");			
						}
					}
					
				}
			});
			t.start();
		} catch (IOException e) {
			System.err.println("[ERROR] Couldn't initialize ServerSocket!");			

		}
		
		
		
	}

	protected void listen(Socket client) {
		Client cml = new Client(client);
		cml.listen();
		System.out.println("[INFO] Listener assigned for "+client.getInetAddress()+":"+client.getLocalPort());			
	}

	public ServerSocket getSs() {
		return ss;
	}

	public void setSs(ServerSocket ss) {
		this.ss = ss;
	}
}
