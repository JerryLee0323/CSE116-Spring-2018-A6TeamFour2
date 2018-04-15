package Gui.EventHandlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Driver.Driver;
import Gui.GUI;
import code.Board;

public class NewGameActionHandler implements ActionListener {
	private Board b;
	private JPanel jp;
	private GUI gui;
	private JPanel m;
	private JPanel gp;
	
	public NewGameActionHandler(Board a, JPanel p, GUI g, JPanel main, JPanel gamePanel) {
		b = a;
		jp = p;
		gui = g;
		m = main;
		gp = gamePanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		b.newGame();
		jp.removeAll();
		gui.update();
		m.add(jp);
		m.add(gp);
		gui.update();
	}

}
