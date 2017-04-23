package engine;

public class Engine extends Thread {
	private boolean paused;
	Game game;
	Canvas canvas;

	public Engine(Game g, Canvas c) {
		super();
		game = g;
		canvas = c;
		paused = false;
	}
	
	public void run() {
		while(!game.isOver()) {
			if(!paused) {
				game.update();
				canvas.repaint();
			}
			try {
				Thread.sleep(game.getDelay());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void pauseGame() {
		paused = true;
	}
	
	public void resumeGame() {
		paused = false;
	}

	
	
}
