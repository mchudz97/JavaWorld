package javaWorld;
import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;
public class Window extends Canvas{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3506796073001702114L;
	public Window(int width, int height, String title, Game game){
		JFrame frame= new JFrame(title);
		frame.setPreferredSize(new Dimension(width+50, height+50));
		frame.setMaximumSize(new Dimension(width+50, height+50));
		frame.setMinimumSize(new Dimension(width+50, height+50));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		game.start();
	}
}
