package engine;

import java.awt.Graphics2D;
import java.awt.event.KeyListener;

public abstract class Game implements KeyListener {
	protected boolean isOver;
	protected String title = "My Game";
	protected int height = 300, width = 300, delay = 30, delay2 = 30;
	
	
	public Game() {
		isOver = false;
	}
	
	public boolean isOver() {
		return isOver;
	}
	
	public abstract void update();
	public abstract void update2();
	public abstract void draw(Graphics2D g);
	
	public String getTitle() { return title; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public int getDelay() { return delay; }
	public int getDelay2() { return delay2; }
	public void resize(int width, int height) {}
	

}
