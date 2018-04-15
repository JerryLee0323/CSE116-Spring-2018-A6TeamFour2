package Gui.EventHandlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Driver.Driver;
import Gui.GUI;
import code.Board;

public class CountActionHandler implements ActionListener{

	private GUI g;
	private Driver d;
	private Board b;
	
	public CountActionHandler(GUI GUI, Driver run, Board board) {
		g = GUI;
		d = run;
		b = board;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if (b.getCount() == 0) {
			g.teamSwitch();
		}
	}
	
	
}
