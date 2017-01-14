package sj.game.common;

import java.awt.Color;
import java.awt.Graphics;

public class Player {
	private String name;
	private int x;
	private int y;
	
	public Player(int x, int y, String name) {
		this.setX(x);
		this.setY(y);
		this.setName(name);
	}
	public void paint(Graphics g){
		g.setColor(Color.RED);
		g.fillOval(x, y, 100, 100);
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
}