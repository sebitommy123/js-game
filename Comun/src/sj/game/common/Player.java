package sj.game.common;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.io.Serializable;
import java.io.ObjectInputStream.GetField;

import javax.tools.Tool;

public class Player implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2899714442155040185L;
	private String name;
	private int x;
	private int y;
	private int size;
	private Color color;
	
	public Player(int x, int y, String name, int size) {
		this.setX(x);
		this.setY(y);
		this.setName(name);
		this.setSize(size);
		int r = (int)(Math.random()*255);
		int gColor = (int)(Math.random()*255);
		int b = (int)(Math.random()*255);
		color = new Color(r, gColor, b);
		
	}
	public void paint(Graphics g){
		
		g.setColor(color);
		g.fillOval(x, y, size, size);
		g.setColor(Color.BLACK);
		int stringwidth = Toolkit.getDefaultToolkit().getFontMetrics(g.getFont()).stringWidth(name);
		g.drawString(name, x+size/2-stringwidth/2, y+size/2-g.getFont().getSize());
	}
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
}