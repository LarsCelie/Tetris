package tetris;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import engine.*;

public class Tetris extends Game {
	public static final int MAX_WINDOW_HEIGHT = 550;
	public static final int MAX_WINDOW_WIDTH = 425;
	public static final int BLOCK_COUNT_WINDOW_HEIGHT = 20;
	public static final int BLOCK_COUNT_WINDOW_WIDTH = 10;
	public static final int KEY_LEFT = 37;
	public static final int KEY_RIGHT = 39;
	public static final int KEY_DOWN = 40;
	public static final int LEFT = -1;
	public static final int RIGHT = 1;
	public static final int DOWN = 0;
	public static final int BLOK_SIZE_IN_BLOCKS = 4;
	public static final int POSITION_FILLED = 1;
	public static final int POSITION_EMPTY = 0;
	private int currentBlockPositionX = 0, currentBlockPositionY = 0, blockSizeInPixels = 25, nextBlockType = 3;
	private int[][] PlayingField;
	private boolean isBlockAlreadyFalling;
	private Blok blok = null;

	public Tetris() {
		title = "Tetris";
		height = MAX_WINDOW_HEIGHT;
		width = MAX_WINDOW_WIDTH;
		delay = 400;
		delay2 = 10;
		PlayingField = new int[BLOCK_COUNT_WINDOW_HEIGHT][BLOCK_COUNT_WINDOW_WIDTH];
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
		int keyCode = e.getKeyCode();
		if(keyCode == KEY_LEFT) {
			if (tryMoveBlok(LEFT)) {
				currentBlockPositionX--;
			}
		} 
		else if(keyCode == KEY_RIGHT) {
			if (tryMoveBlok(RIGHT)) {
				currentBlockPositionX++;
			}
		} else if(keyCode == KEY_DOWN) {
			if (tryMoveBlok(DOWN)) {
				currentBlockPositionY++;
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
		if(!isBlockAlreadyFalling) {
			CreateNewBlock();
			ResetToStartingValues();
		}
		if (!tryMoveBlok(DOWN)){
			for (int i = 0; i< BLOK_SIZE_IN_BLOCKS; i++){
				PlayingField[currentBlockPositionY + blok.getY(i)][currentBlockPositionX +blok.getX(i)] = POSITION_FILLED;
			}
			isBlockAlreadyFalling = false;
		} else {
			currentBlockPositionY++;
		}
		scanLine();
	}

	private void ResetToStartingValues() {
		currentBlockPositionY = 1;
		currentBlockPositionX = 3;
		isBlockAlreadyFalling = true;
	}

	private void CreateNewBlock() {
		int random = nextBlockType;
		nextBlockType = (int) (Math.random()*7);
		System.out.println(random);
		blok = new Blok(random);
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
				if (PlayingField[i][j]> POSITION_EMPTY) {
					g.fillRect(j*25,i*25, blockSizeInPixels, blockSizeInPixels);
				}	
			}
		}
		
		if(isBlockAlreadyFalling) {
			for (int i = 0; i<4; i++){
				g.fillRect((currentBlockPositionX +blok.getX(i))*25,(currentBlockPositionY +blok.getY(i))*25, blockSizeInPixels, blockSizeInPixels);
			}
		}
	}
	
	public boolean tryMoveBlok(int direction) {
		boolean isAllowedToMove = true;
		if (direction == LEFT){
			isAllowedToMove = isAllowedToMoveLeft(isAllowedToMove);
		} else if (direction == DOWN){
			isAllowedToMove = isAllowedToMoveDown(isAllowedToMove);
		} else if (direction == RIGHT){
			isAllowedToMove = isAllowedToMoveRight(isAllowedToMove);
		} 
		return isAllowedToMove;
	}

	private boolean isAllowedToMoveRight(boolean b) {
		for (int i = 0; i<BLOK_SIZE_IN_BLOCKS; i++){
            if (hasBlockReachedWidthLimit(i) || PlayingField[currentBlockPositionY +blok.getY(i)][blok.getX(i)+1] != POSITION_EMPTY){
                b = false; break;
            }
        }
		return b;
	}

	private boolean hasBlockReachedWidthLimit(int i) {
		return currentBlockPositionX +blok.getX(i) >= (9);
	}

	private boolean isAllowedToMoveDown(boolean b) {
		for (int i = 0; i<BLOK_SIZE_IN_BLOCKS; i++){
            if (hasBlockReachedHeightLimit() || PlayingField[currentBlockPositionY +1+blok.getY(i)][currentBlockPositionX +blok.getX(i)] != POSITION_EMPTY){
                b = false; break;
            }
        }
		return b;
	}

	private boolean hasBlockReachedHeightLimit() {
		return currentBlockPositionY >= 19;
	}

	private boolean isAllowedToMoveLeft(boolean b) {
		for(int i = 0; i<BLOK_SIZE_IN_BLOCKS;i++){
            if (hasBlockReachedWidthLimitOnLeftSide() || PlayingField[currentBlockPositionY +blok.getY(i)][currentBlockPositionX -1+blok.getX(i)] != POSITION_EMPTY){
                b = false; break;
            }
        }
		return b;
	}

	private boolean hasBlockReachedWidthLimitOnLeftSide() {
		return currentBlockPositionX <= 0;
	}

	public void scanLine(){
		for (int i = 0; i<BLOCK_COUNT_WINDOW_HEIGHT; i++){
			boolean lineFilledWithBlocks = true;
			for (int j = 0; j<BLOCK_COUNT_WINDOW_WIDTH; j++){
				if (PlayingField[i][j]==POSITION_EMPTY) {
					lineFilledWithBlocks = false;
				}
			}
			if (lineFilledWithBlocks) {
				removeLineAndCascade(i);
			}
		}
	}
	public void removeLineAndCascade(int lineNumber){
		for (int i = lineNumber; i>0; i--){
			for (int j = 0; j<BLOCK_COUNT_WINDOW_WIDTH; j++){
				PlayingField[i][j] = PlayingField[i-1][j];
			}
		}
	}
}
