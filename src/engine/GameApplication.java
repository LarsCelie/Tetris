package engine;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class GameApplication {
	
	public static void start(final Game game) {
		SwingUtilities.invokeLater(new Runnable(){
			@Override 
			public void run() {
				JFrame frame = new JFrame(game.getTitle());
				frame.setSize(game.getWidth(), game.getHeight());
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		    	Canvas c = new Canvas(game);
		    	frame.add(c);
		    	frame.setVisible(true);
		    	c.requestFocus();
		    	
		    	new Engine(game, c).start();
			}
		});
	}
}