package sj.game.common;

public class ServerTextMessage extends ServerMessage{
	private static final long serialVersionUID = -8862042733844219469L;
	
	private final String text;

	public ServerTextMessage(String text){
		this.text = text;
	}

	public String getText() {
		return text;
	}

}
