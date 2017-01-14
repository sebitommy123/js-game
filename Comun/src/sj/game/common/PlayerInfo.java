package sj.game.common;

public class PlayerInfo extends Message{

	private Player player;
	/**
	 * 
	 */
	private static final long serialVersionUID = 3381724998060717228L;
	
	public PlayerInfo(Player player) {
		this.player = player;
	}
	
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	

}
