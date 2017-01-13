package sj.game.common;

public class TextMessage extends Message{
	private static final long serialVersionUID = -8862042733844219469L;
	
	private final String text;

	public TextMessage(String text){
		this.text = text;
	}

	public String getText() {
		return text;
	}

}
