package Gui.EventHandlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Gui.GUI;

public class EndTurnActionHandler implements ActionListener{

	private int teamTurn;
		private GUI gui;

		public EndTurnActionHandler(GUI x) {
			gui = x;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
		//	GUI.teamSwitch();
			gui.teamSwitch();
			// switches team and displays message
			
		}
		
		
		
}
