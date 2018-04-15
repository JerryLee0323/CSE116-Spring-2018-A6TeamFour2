package Driver;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import Gui.GUI;
import code.Board;

public class Driver implements Runnable {
	
	private Board _board;
	private JFrame _window;
	
	public Driver(Board b) {
		_board = b;
	}
	
	public static void main(String[] args) {
		Board b=new Board();
		SwingUtilities.invokeLater(new Driver(b));
	}
	public void run() {
		_board=new Board();
		_window=new JFrame();
		new GUI(_board,_window,this);
		_window.setVisible(true);
		_window.pack();
		_window.setSize(900,500);
		_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public void updateJFrame() {
		_window.pack();
		_window.repaint();
	}
}
