package sj.game.common;

public class ClientResponse extends ClientMessage implements Ack{
	private static final long serialVersionUID = -2746743466342535505L;
	private final long serverMessageId;

	public ClientResponse(ServerMessage m){
		this.serverMessageId = m.getMessageId();
	}

	public long getServerMessageId() {
		return serverMessageId;
	}
	
}
