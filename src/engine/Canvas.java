package engine;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JComponent;

public class Canvas extends JComponent implements ComponentListener {
	/**
	 * @author Berend de Groot
	 */
	private static final long serialVersionUID = 1L;
	private Game game;
	
	public Canvas(Game g) {
		game = g;
		addKeyListener(game);
		addComponentListener(this);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		game.draw((Graphics2D)g);
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentResized(ComponentEvent e) {
		//
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

}
