package sj.game.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import sj.game.common.ClientMessage;
import sj.game.common.LoginResponse;
import sj.game.common.RegisterResponse;
import sj.game.common.ServerMessage;
import sj.game.common.ServerResponse;

public class Server {

	private ObjectOutputStream oos;
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
