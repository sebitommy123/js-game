package sj.game.common;

public class ClientTextMessage extends ClientMessage{
	private static final long serialVersionUID = -4261024757923953453L;

	private final String text;

	public ClientTextMessage(String text){
		this.text = text;
	}

	public String getText() {
		return text;
	}
	
}
