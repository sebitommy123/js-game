package sj.game.common;

public class CollideResponse extends ServerResponse{
	public CollideResponse(ClientMessage m) {
		super(m);
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 2861364398958633981L;
	private boolean accepted = false;
	
	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}
}
