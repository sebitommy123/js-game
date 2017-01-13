package sj.game.common;

import java.io.Serializable;

public abstract class Message implements Serializable{
	private static final long serialVersionUID = 5254455707233425685L;
	private static long nextId = 1;

	private final long messageId;
	
	public Message(){
		messageId = nextId++;
	}

	public long getMessageId() {
		return messageId;
	}

}
