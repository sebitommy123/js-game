package sj.game.server;

import java.net.ServerSocket;

public class Server {
	private ServerSocket ss;

	public Server() {
		setSs(new ServerSocket(4443212346721));
	}

	public ServerSocket getSs() {
		return ss;
	}

	public void setSs(ServerSocket ss) {
		this.ss = ss;
	}
}
