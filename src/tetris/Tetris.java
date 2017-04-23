package tetris;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import engine.*;

public class Tetris extends Game {
	private int x = 0, y = 0, bSize = 25, nextBlock = 3;
	private int[][] field;
	private boolean busy;
	private Blok blok = null, nextBlockO = null;
	
	public Tetris() {
		title = "Tetris";
		height = 550;
		width = 425;
		delay = 400;
		delay2 = 10;
		field = new int[20][10];
	}
	
	public static void main(String arg[]) {
		GameApplication.start(new Tetris());
	}

	@Override
	/**
	 * Right Arrow: keycode 39
	 * Left Arrow: keycode 37
	 */
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == 37) {
			if (tryMove(-1)) {
				x--;
			}
		} 
		else if(e.getKeyCode() == 39) {
			if (tryMove(1)) {
				x++;
			}
		} else if(e.getKeyCode() == 40) {
			if (tryMove(0)) {
				y++;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		if(!busy) {
			int random = nextBlock;
			nextBlock = (int) (Math.random()*7);
			System.out.println(random);
			blok = new Blok(random);
			y = 1;
			x = 3;
			busy = true;
		}
		if (!tryMove(0)){
			for (int i = 0; i<4; i++){
				field[y+blok.getY(i)][x+blok.getX(i)] = 1;
			}
			busy = false;
		} else {
			y++;
		}
		scanLine();
	}
	
	@Override
	public void update2() {
	}

	@Override
	public void draw(Graphics2D g) {
		g.fillRect(0, 0, width, height);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, 250, 500);
		g.fillRect(275, 0, 150, 150);
		
		
		g.setColor(Color.black);
		for (int i = 0; i<20; i++){
			for (int j = 0; j<10; j++){
				if (field[i][j]>0) {
					g.fillRect(j*25,i*25,bSize,bSize);
				}	
			}
		}
		
		if(busy) {
			for (int i = 0; i<4; i++){
				g.fillRect((x+blok.getX(i))*25,(y+blok.getY(i))*25,bSize,bSize);
			}
		}
	}
	
	public boolean tryMove(int a) {
		boolean b = true;
		if (a == -1){
			for(int i = 0; i<4;i++){
				if (x <= 0 || field[y+blok.getY(i)][x-1+blok.getX(i)] != 0){
					b = false; break;
				}
			}
		} else if (a == 0){
			for (int i = 0; i<4; i++){
				if (y >= 19 || field[y+1+blok.getY(i)][x+blok.getX(i)] != 0){
					b = false; break;
				}
			}		
			
		} else if (a == 1){
			for (int i = 0; i<4; i++){
				if (x+blok.getX(i) >= (9) || field[y+blok.getY(i)][blok.getX(i)+1] != 0){
					b = false; break;
				}
			}
		} 
		return b;
	}
	
	public void scanLine(){
		for (int i = 0; i<20; i++){
			boolean vol = true;
			for (int j = 0; j<10; j++){
				if (field[i][j]==0) {
					vol = false;
				}
			}
			if (vol) {
				removeLine(i);
			}
		}
	}
	public void removeLine(int a){
		for (int i = a; i>0; i--){
			for (int j = 0; j<10; j++){
				field[i][j] = field[i-1][j];
			}
		}
	}
}
