package sj.game.server;

import java.io.ObjectInputStream;
import java.net.Socket;

import sj.game.common.TextMessage;

public class Client {

	private Socket socket;

	public Client(Socket client) {
		this.setSocket(client);
	}

	public void listen() {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					ObjectInputStream ois = new ObjectInputStream(getSocket().getInputStream());
					while(getSocket().isConnected()){
						Object response = ois.readObject();
						System.out.println("[INFO] Received message from client at "+getSocket().getInetAddress()+":"+getSocket().getLocalPort());
						if(response instanceof TextMessage){
							TextMessage tm = (TextMessage) response;
							System.out.println("[MESSAGE] \""+tm.getText()+"\" from "+getSocket().getInetAddress()+":"+getSocket().getLocalPort());
						}
					}
				} catch (Exception e) {
					System.out.println("[INFO] Client at "+getSocket().getInetAddress()+":"+getSocket().getLocalPort()+" disconnected!");
				}


			}
		});
		t.start();

	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

}
