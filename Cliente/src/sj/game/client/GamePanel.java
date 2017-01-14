package sj.game.client;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import sj.game.common.Player;

public class GamePanel extends JPanel{
	/**
	 * 
	 */
	ArrayList<Player> players = new ArrayList<Player>();
	private float viewInWidth;
	private float viewInHeight;
	private Server server;
	
	private static final long serialVersionUID = 6243721024941741092L;

	public GamePanel() {
		setVisible(true);
		
		viewInWidth = 1000;
		viewInHeight = (float)this.getHeight()/this.getWidth()*1000;
		
		server = Main.server;
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		for(Player p : players){
			p.paint(g);
			
		}
		repaint();
	}				
	
		
}
