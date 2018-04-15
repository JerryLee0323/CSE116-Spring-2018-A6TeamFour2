package Gui.EventHandlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import code.Board;

public class ExitActionHandler implements ActionListener {
//	private Board _b;
//	
//	public ExitActionHandler(Board b){
//		_b=b;	
//	}
	public void actionPerformed (ActionEvent e) {
		System.exit(0);
	}
	

}
