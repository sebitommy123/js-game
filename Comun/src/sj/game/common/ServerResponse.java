package sj.game.common;

public class ServerResponse extends ServerMessage implements Ack{
	private static final long serialVersionUID = -6272669797146289209L;
	private final long clientMessageId;

	public ServerResponse(ClientMessage m) {
		this.clientMessageId = m.getMessageId();
	}

	public long getClientMessageId() {
		return clientMessageId;
	}
	
}
