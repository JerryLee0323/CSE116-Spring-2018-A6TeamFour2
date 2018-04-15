package Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import com.sun.xml.internal.bind.v2.runtime.Location;

import Driver.Driver;
import Gui.EventHandlers.CountActionHandler;
import Gui.EventHandlers.EndTurnActionHandler;
import Gui.EventHandlers.EnterActionHandler;
import Gui.EventHandlers.ExitActionHandler;
import Gui.EventHandlers.NewGameActionHandler;
import Gui.EventHandlers.codeWordsActionHandler;
//import Gui.EventHandlers.SubmitActionHandler;
import code.Board;
import code.Observer;

public class GUI implements Observer	{

	private Board board;
	private JPanel button;
	private JFrame UI;
	private JLabel messageBoard;
	private Driver d;
	private JPanel gamePanel;
	private JButton submit;
	private JButton endTurn;
	private JMenuBar menu;
	private JMenu File;
	private JPanel main;
	private JTextField textfield;
	private JTextField countTextField;
	private JLabel currentMessage;
	private JLabel enterCount;
	private boolean hint;
	private int count;
	//	private JPanel main;


	public GUI(Board x, JFrame ui, Driver driver) {
		// create GUI application with menu and menu items.
		d = driver;
		board = x;
		board.newGame();
		UI = ui;
		menu = new JMenuBar();
		File = new JMenu("File");
		JMenuItem newgame = new JMenuItem("New Game");
		JMenuItem exit = new JMenuItem("Exit");
		main = new JPanel();
		main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
		button = new JPanel();
		gamePanel = new JPanel();
		
		File.add(newgame);
		File.add(exit);
		menu.add(File);
		UI.setJMenuBar(menu);
		exit.addActionListener(new ExitActionHandler());
		newgame.addActionListener(new NewGameActionHandler(board, button, this, main, gamePanel));
		// create 25 Words 
		
		board.newGame();
		board.addObserver(this);
		

		hint = true;
		count = 0;
		currentMessage = new JLabel("Enter a clue: ");
		enterCount = new JLabel("   Enter a count: ");
		submit = new JButton("Enter");
		endTurn = new JButton("End Turn");
		submit.addActionListener(new EnterActionHandler(board, textfield, countTextField, this));
		
		messageBoard = new JLabel("   It is currently " + board.getTurn() + "'s turn:                        ");
		textfield = new JTextField(5);
		endTurn.addActionListener(new EndTurnActionHandler(this));
		endTurn.setVisible(false);
		countTextField = new JTextField(5);
		
		
		endTurn.addActionListener(new EndTurnActionHandler(this));
		endTurn.setVisible(false);
	}


	public void update() {
		
		board.updatepoints();
		
		button.removeAll();
		gamePanel.removeAll();
		
		button.setLayout(new GridLayout(5, 5));	
		gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.X_AXIS));
		
		
		messageBoard = new JLabel("   It is currently " + board.getTurn() + "'s turn:                        ");
		textfield = new JTextField(5);
		countTextField = new JTextField(5);
		submit = new JButton("Enter");
		endTurn = new JButton("End Turn");
		
		endTurn.addActionListener(new EndTurnActionHandler(this));
		submit.addActionListener(new EnterActionHandler(board, textfield, countTextField, this));
		endTurn.setVisible(false);
		
		if(hint) {
			currentMessage = new JLabel("Enter a clue: ");
			hint = true;
		}else {
			currentMessage = new JLabel("Invalid clue");
		}
		if(board.getCount() > 0 && board.getCount() <= 25) {
			enterCount = new JLabel("   Enter a count: ");
		} else {
			enterCount = new JLabel ("  Invalid count");
		}
		endTurn = new JButton("End Turn");
		endTurn.addActionListener(new EndTurnActionHandler(this));
		endTurn.setVisible(false);
		
		gamePanel.add(messageBoard, JLabel.CENTER);
		gamePanel.add(currentMessage);
		gamePanel.add(textfield);
		gamePanel.add(enterCount);
		gamePanel.add(countTextField);
		gamePanel.add(submit);
		gamePanel.add(endTurn);
		
		
		
		
		//turn 0 = spymaster's turn
		//turn 1 = rest of team
		//only increase/decrease turn # at the end of the turn. aka when the team picks the wrong location.
		
		
		
		
		if(board.getTeamTurn() == 0 || board.getTeamTurn() == 2) {
			for(int i = 0; i < 25; i++) {
				String text = String.valueOf(board.getLocation().get(i).getPerson());
				String role = board.getLocation().get(i).getTeam();
				if(board.getLocation().get(i).getRevealed()) {
					JButton Button = new JButton(role);
					Button.setPreferredSize(new Dimension(150,80));
					if(board.getLocation().get(i).getTeam().equals("Red Agent")) {
						Button.setBackground(Color.red);
					} else if(board.getLocation().get(i).getTeam().equals("Blue Agent")) {
						Button.setBackground(Color.blue);
					} else if(board.getLocation().get(i).getTeam().equals("Innocent Bystander")) {
						Button.setBackground(Color.white);
					} else Button.setBackground(Color.MAGENTA);

					button.add(Button);
				}else{
					JButton Button = new JButton(text + " --- " + role);
					Button.setPreferredSize(new Dimension(150, 80));

					button.add(Button);
				}	
			}	
		}		




		//rest of team's turn
		else if(board.getTeamTurn() == 1 || board.getTeamTurn() == 3){
			messageBoard.setText("   It is currently " + board.getTurn() + "'s turn: You have: " + board.getCount() + " counts          ");
			for(int i = 0; i < 25; i++) {
				String text = String.valueOf(board.getLocation().get(i).getPerson());
				String role = board.getLocation().get(i).getTeam();
				
				if(board.getLocation().get(i).getRevealed()) {
					JButton Button = new JButton(role);
					Button.setPreferredSize(new Dimension(150,80));
					if(board.getLocation().get(i).getTeam().equals("Red Agent")) {
						Button.setBackground(Color.red);
					} else if(board.getLocation().get(i).getTeam().equals("Blue Agent")) {
						Button.setBackground(Color.blue);
					} else if(board.getLocation().get(i).getTeam().equals("Innocent Bystander")) {
						Button.setBackground(Color.white);
					} else Button.setBackground(Color.MAGENTA);
					button.add(Button);
				}
				else {
					JButton Button = new JButton(text);
					Button.setPreferredSize(new Dimension(150, 80));
					Button.addActionListener(new codeWordsActionHandler(board,this));
					button.add(Button);
					
				}
			}
			endTurn.setVisible(true);
			
		}

		if(board.winning()) {
			JOptionPane.showMessageDialog(null, board.winningTeam());
			messageBoard.setText(board.winningTeam()); //make something in board to determine which team won.
		}
		
		UI.add(main);
		UI.pack(); 
		UI.setLocationRelativeTo(null);
		
		
		
		updateJFrameIfNotHeadless();

	}

	public void updateJFrameIfNotHeadless() {
		if(d != null) {
			d.updateJFrame();
		}
	}


	

	public void teamSwitch() {
		//switch teams, then message that teams switched
	//	board.switchTeamMethod();
		messageSwitchTeam();
		board.setCount(1);
	}

	public void messageSwitchTeam() {
		String team = "";
		String nextTeam = "";
			team = board.getTurn() + " ";
			board.switchTeamMethod();
			nextTeam = board.getTurn() + " ";

		JOptionPane.showMessageDialog(null, team + "'s turn has ended. Now it is "
				+ nextTeam + "'s turn.");
	}
	//first remove all, if unrevealed then leave name, if revea;ed wjem 
	/*public void restOfTurn() {
		gamePanel.removeAll();

	}*/

		
	//check if spy turn, then switch turn, reset board, 
	public void endSpyTurn() {
		if (board.getTurn().equals("Spy Turn")) {
			board.updatepoints();
	//		teamTurnUpdate();
	//		main.add(gamePanel);
		}

	}
	
	public void setHint(boolean h) {
		hint = h;
	}
}
