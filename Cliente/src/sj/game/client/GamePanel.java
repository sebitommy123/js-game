package sj.game.client;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.JPanel;

import sj.game.common.Player;
import sj.game.common.PlayerUpdate;

public class GamePanel extends JPanel{
	/**
	 * 
	 */
	public static ConcurrentLinkedQueue<Player> players = new ConcurrentLinkedQueue<Player>();
	private float viewInWidth;
	private float viewInHeight;
	private Server server;
	private Player me;
	private static final long serialVersionUID = 6243721024941741092L;

	@SuppressWarnings("deprecation")
	public GamePanel() {
		setVisible(true);
		
		viewInWidth = 1000;
		viewInHeight = (float)this.getHeight()/this.getWidth()*1000;
		
		server = Main.server;
		setMe(new Player(0, 0, Main.userField.getText()));
		
		players.add(getMe());
		server.send(new PlayerUpdate(getMe()));
		
		
		
		
	}
	
	float pxtc(int i){
		return i * ((float)this.getWidth()/viewInWidth);
	}
	
	float pytc(int i){
		return i * ((float)this.getHeight()/viewInHeight);
	}
	
	void updatePlayerInServer(){
		try {
			server.oos.reset();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Oh oh. Looks like something just died.");
		}
		server.send(new PlayerUpdate(getMe()));
		System.out.println(getMe().getX() + ", " + getMe().getY());
	}
	
	@Override
	public void paint(Graphics g) {
		g.clearRect(0, 0, getWidth(), getHeight());
		for(Player p : players){
			p.paint(g);
			
		}
		repaint();
	}

	public Player getMe() {
		return me;
	}

	public void setMe(Player me) {
		this.me = me;
	}				
	
		
}
