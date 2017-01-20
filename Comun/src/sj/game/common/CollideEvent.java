package sj.game.common;

public class CollideEvent extends ClientMessage{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1601660434349221895L;
	private String him;
	private String you;
	public CollideEvent(String him, String you){
		setHim(him);
		setYou(you);
	}
	public String getHim() {
		return him;
	}
	public void setHim(String him) {
		this.him = him;
	}
	public String getYou() {
		return you;
	}
	public void setYou(String you) {
		this.you = you;
	}
}
