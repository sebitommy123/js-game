package sj.game.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import sj.game.common.ClientMessage;
import sj.game.common.CollideEvent;
import sj.game.common.LoginResponse;
import sj.game.common.Player;
import sj.game.common.PlayerInfo;
import sj.game.common.RegisterResponse;
import sj.game.common.ServerMessage;
import sj.game.common.ServerResponse;

public class Server {

	public ObjectOutputStream oos;
	private ObjectInputStream ots;
	private Socket socket;

	public Server(Socket s) throws IOException {
		this.socket = s;
		oos = new ObjectOutputStream(s.getOutputStream());
		run();


	}

	public void run(){
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				boolean running = true;
				try {
					ots = new ObjectInputStream(socket.getInputStream());

					while(running){

						try {
							ServerMessage o = (ServerMessage) ots.readObject();
							System.out.println("Message recieved");
							if(o instanceof ServerResponse){
								ServerResponse sr = (ServerResponse) o;

								processResponse(sr);

							}else{
								processMessage(o);
							}

						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}


					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					Main.disconnected();
					
				}

			}
		});

		t.start();

	}

	protected void processMessage(ServerMessage o) {
		if(o instanceof PlayerInfo){
			
			PlayerInfo pi = (PlayerInfo) o;
			
			System.out.println("Recieved info about " + pi.getPlayer().getName());
			
			boolean exists = false;
			
			for(Player player : Main.gamePanel.players){
				if(player.getName().equals(pi.getPlayer().getName())){
					exists = true;
					player.setX(pi.getPlayer().getX());
					player.setY(pi.getPlayer().getY());
					
				}
			}
			
			if(!exists){
				Main.gamePanel.players.add(pi.getPlayer());
			}
			
		}
	}

	protected void processResponse(ServerResponse o) {
		if(o instanceof LoginResponse){

			Main.respondedToCurrentQuery = true;
			Main.sinceCurrentQuery = System.currentTimeMillis();
			Main.currentQueryResponse = o;
			System.out.println(((LoginResponse) o).isAccepted());


		}
		
		if(o instanceof RegisterResponse){

			Main.respondedToCurrentQuery = true;
			Main.currentQueryResponse = o;
			System.out.println(((RegisterResponse) o).getResult());

		}
		
		
	}

	public void send(ClientMessage m) {
		try {
			oos.writeObject(m);



		} catch (IOException e) {

			e.printStackTrace();
		}
	}

}
