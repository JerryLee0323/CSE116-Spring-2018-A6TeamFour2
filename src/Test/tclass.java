package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import code.Board;
import code.Location;

import org.junit.Test;

public class tclass {
	
	@Test
	public void readcodenametest() {
		Board x=new Board();
		ArrayList<String> expected=new ArrayList<String>();
		try {
			for(String line : Files.readAllLines(Paths.get("src\\Text\\GameWords.txt"))){
			    expected.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		ArrayList<String> actual=x.readcodename();
		
		assertTrue(new File("src\\Text\\GameWords.txt").isFile());
		
		int counter=0;
		for(int i=0;i<expected.size();i++) {
			if(expected.get(i).equals(actual.get(i))) {
				counter++;
			}
		}
		assertTrue(counter==expected.size());

		assertTrue(expected.get(4).equals(actual.get(4)));
		assertTrue(expected.get(15).equals(actual.get(15)));
	}
								//----------Xiangwei' test method------------

	@Test
	public void BlueAgentWinningTest() {
	Board x = new Board();
	ArrayList<Location> Blue8 =(x.getLocation());
	x.setTurn(2);
	for (int i=0;i<Blue8.size();i++) {
		if(Blue8.get(i).getTeam().equals("Blue Agent"))
		Blue8.get(i).reveal();
		}
	assertTrue(x.winning());
	}
	 // the method is test on if Blue team is wining, expect method return true 
	@Test
	public void RedAgentWinningTest() {
	Board x = new Board();
	ArrayList<Location> Red9 =(x.getLocation());
	x.setTurn(1);
	for (int i=0;i<Red9.size();i++) {
		if(Red9.get(i).getTeam().equals("Red Agent"))
		Red9.get(i).reveal();
		}
	assertTrue(x.winning());
	}
	 // the method is test on if Red team is wining, expect method return true 
	@Test
	public void AssassintWinningTest() {
		Board x = new Board();
		ArrayList<Location> Assassin1 =(x.getLocation());
		x.setTurn(1);
		for (int i=0;i<Assassin1.size();i++) {
			if(Assassin1.get(i).getTeam().equals("Assassin"))
			Assassin1.get(i).reveal();
			}
		assertTrue(x.winning());
	} // the method is test on if one of two teams is wining, because other team targeting the assassin, expect method return true 
	
	@Test
	public void NooneWinningTest() {
		Board x = new Board();
		ArrayList<Location> Assassin1 = x.getLocation();
	/*	for(int i =0; i<Assassin1.size();i++) {
			if(Assassin1.get(i).equals("Red Agent")){
				Assassin1.get(0).reveal();
			} 
		}*/
		assertFalse(x.winning());
	}// the method is test on none of two teams is wining, expect method return false
	
	// Below tests is for second method(assassin)----------------------------------------------------------------------------------------------
	
	@Test
	public void AssassinRevealedBlueAgentWins() {
		String x = "BlueAgentWins";
		Board y = new Board();
		ArrayList<Location> Assassin1 = y.getLocation();
		y.setTurn(1);
		for (int i=0;i<Assassin1.size();i++) {
			if(Assassin1.get(i).getTeam().equals("Assassin")) {
				Assassin1.get(i).reveal();
			}
		}
		assertEquals(x,y.AssassinMethod());
	} // RedAgent reveals Assassin at round 1, Blue Agent Wins.
	@Test
	
	public void AssassinRevealedRedAgentWins() {
		String x = "RedAgentWins";
		Board y = new Board();
		ArrayList<Location> Assassin1 = y.getLocation();
		y.setTurn(2);
		for (int i=0;i<Assassin1.size();i++) {
			if(Assassin1.get(i).getTeam().equals("Assassin"));
			Assassin1.get(i).reveal();
		}
		
		assertEquals(x,y.AssassinMethod());
	} // BlueAgent reveals Assassin at round 2, Red Agent Wins.
	
	@Test
	public void AssassinIsNotRevealed() {
		String x = "Assassin is not revealed";
		Board y = new Board();
		assertEquals(x,y.AssassinMethod());
	} // Assassin is not revealed, none wins.
	
	
	
	
	
	
	
	/* ------------------------------------------------ YeQian Lin ------------------------------------------------
	  --------------------------------------------------Two Test Methods in Total-----------------------------------*/
	@Test 
	public void selectCodenamestest() {
		Board a = new Board();
		
		a.selectCodenames();
		
		assertEquals(25,a.getListForCodenames().length);
	}
	
	@Test
	public void generateAssignmentstest() {
		
		int Ra,Ba,Ib,A;
		Ra = 0;
		Ba =0;
		Ib =0;
		A =0;
		
	Board a = new Board();
	a.generateAssignments();
	
	for(int i=0; i<a.getListForRoles().length;i++) {
		if(a.getListForRoles()[i] != null && a.getListForRoles()[i].equals("Red Agent")) {
			Ra+= 1;
		}
		else if(a.getListForRoles()[i] != null && a.getListForRoles()[i].equals("Blue Agent")) {
			Ba+=1;
		}
		else if(a.getListForRoles()[i] != null && a.getListForRoles()[i].equals("Innocent Bystander")) {
			Ib+=1;
		}
		else {
			A+=1;
		}
	}
	
	assertEquals(25,a.getListForRoles().length);
	assertEquals(9,Ra);
	assertEquals(8,Ba);
	assertEquals(7,Ib);
	assertEquals(1,A);
	
	}
	//--------------------------Johnathan's Code--------------------------
	@Test
	public void legalClueTest() {
		Board method = new Board();
		ArrayList<String> clues = new ArrayList<String>();
		String clue = "arfasd";
		clues.add(clue);
		assertTrue(method.checkLegalClue(clue));
	}
		
	//make my own list of 25 words, test one word not in it.
	@Test
	public void illegalClueTest() {
		Board method = new Board();
		String clue = "area";
			assertFalse(method.checkLegalClue(clue));
		}
	
	@Test //finish test, null, different teams, 
	public void updateLocationNullTest() {
		Board method = new Board();
		String x = null;
		assertFalse(method.updateLocation(x));
	}
		
/*	@Test
	public void updateLocationTest() {
		Board method = new Board();
		String test = "home";
		assertTrue(method.updateLocation(test)	);
	}   */
	//------------------------------Jerry's Code-----------------------
	
	@Test
	public void creatingBoardTest() {
		Board board = new Board();
		assertEquals(1, board.getTurn());
		assertEquals(25, board.getSize());
		//assertEquals(0, board.getRevealed());
	}
		
	
	
}

