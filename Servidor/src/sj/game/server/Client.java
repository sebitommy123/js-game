package sj.game.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import sj.game.common.ClientMessage;
import sj.game.common.ClientTextMessage;
import sj.game.common.LoginRequest;
import sj.game.common.LoginResponse;
import sj.game.common.Message;
import sj.game.common.ServerTextMessage;

public class Client {

	private Socket socket;
	private ObjectOutputStream objectOutputStream;
	public Client(Socket client) {
		this.setSocket(client);
		try {
			setObjectOutputStream(new ObjectOutputStream(client.getOutputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void listen() {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					ObjectInputStream ois = new ObjectInputStream(getSocket().getInputStream());
					while(getSocket().isConnected()){
						ClientMessage response = (ClientMessage) ois.readObject();
						System.out.println("[INFO] Received message from client at "+getSocket().getInetAddress()+":"+getSocket().getLocalPort());
						if(response instanceof LoginRequest){
							LoginRequest lr = (LoginRequest) response;
							if(Server.users.containsKey(lr.getUsername())){
								if(Server.users.get(lr.getUsername()).equals(lr.getPassword())){
									sendMessage(new LoginResponse(lr, true));
								}else{
									sendMessage(new LoginResponse(lr, false));
								}
							}else{
								sendMessage(new LoginResponse(lr, false));
							}
						}
						if(response instanceof ClientTextMessage){
							ClientTextMessage tm = (ClientTextMessage) response;
							System.out.println("[MESSAGE] \""+tm.getText()+"\" from "+getSocket().getInetAddress()+":"+getSocket().getLocalPort());
							sendMessage(new ServerTextMessage("Hola"));
						}
					}
				} catch (ClassCastException | ClassNotFoundException e) {
					System.err.println("[ERROR] Class corrupted!");
				} catch (IOException e) {
					System.out.println("[INFO] Client at "+getSocket().getInetAddress()+":"+getSocket().getLocalPort()+" disconnected!");
				}


			}
		});
		t.start();

	}

	protected void sendMessage(Message message) {
		try {
			objectOutputStream.writeObject(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public ObjectOutputStream getObjectOutputStream() {
		return objectOutputStream;
	}

	public void setObjectOutputStream(ObjectOutputStream objectOutputStream) {
		this.objectOutputStream = objectOutputStream;
	}

}
