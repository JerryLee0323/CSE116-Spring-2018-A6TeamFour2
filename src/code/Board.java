package code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;


public class Board{
	private ArrayList<Location> locations; //

	//---------------------------------------Jerry's Code------------------------------------

	//team 1 = Red and team 2 = Blue
	private int teamTurn;
	private int _size;
	private int redTeamRevealed;
	private int blueTeamRevealed;
	private int assassinRevealed;
	private ArrayList<Observer> observers;
	private String[] listForCodenames;
	private String[] listForRoles;
	private int count;
	private boolean redTurn;
	private boolean blueTurn;
	private boolean spyTurn;
	

	/**
	 * Constructs a Board class that generates codenames and creates a board with 25 locations.
	 * it also sets _size to the size of the board and
	 * starts the game at red team's turn. (teamTurn = 1 is Red and teamTurn = 2 is Blue)
	 * 
	 */
	public Board() {
		selectCodenames();
		generateAssignments();
		locations = new ArrayList<Location>();
		for(int i = 0; i < 25; i++) {
			locations .add(new Location(listForCodenames[i], listForRoles[i]));
		}
		teamTurn = 0;
		_size = locations.size();
		observers = new ArrayList<Observer>();
		 redTeamRevealed = 0;
		 blueTeamRevealed = 0;
		 assassinRevealed = 0;
	}

	public void newGame() {
		selectCodenames();
		generateAssignments();
		locations = new ArrayList<Location>();
		for(int i = 0; i < 25; i++) {
			locations .add(new Location(listForCodenames[i], listForRoles[i]));
		}
		teamTurn = 0;
		_size = locations.size();
		 redTeamRevealed = 0;
		 blueTeamRevealed = 0;
		 assassinRevealed = 0;
		 count = 1;
		 notifyObservers();
	}
	
	
	// getTurn() returns the current team's turn. 1 is Red team and 2 is Blue team
	public String getTurn() {
		if(teamTurn == 1) {
			return "Red Team";
		}
		if (teamTurn == 0) {
			return "Red Spymaster";
		}
		else if(teamTurn == 2) {
			return "Blue Spymaster";
		}
		else return "Blue Team";
	}
	
	/**
	 * Returns an ArrayList of Strings from GameWords.txt. This method will take
	 * each line from GameWords.txt and place them in a different index in the ArrayList.
	 * 
	 * @return      the ArrayList of strings in GameWords.txt
	 */
	public ArrayList<String> readcodename() {
		ArrayList<String> list=new ArrayList<String>();
		try {
			for(String line : Files.readAllLines(Paths.get("src\\Text\\GameWords.txt"))){
				list.add(line);
			}
		}
		catch (IOException e) {

		}
		return list;
	}


	public void updatepoints() {
		for(int i = 0; i < locations.size(); i++){
			if(locations.get(i).getRevealed() && locations.get(i).getTeam().equals("Red Agent")){
				redTeamRevealed++;
			}
			if(locations.get(i).getRevealed() && locations.get(i).getTeam().equals("Blue Agent")){
				blueTeamRevealed++;
			}
			if(locations.get(i).getRevealed() && locations.get(i).getTeam().equals("Assassin")){
				assassinRevealed++;
			}
	}
	}
	
	public String winningTeam() {
		if (blueTeamRevealed == 8) {
			return "Blue Team won";
		}// If BlueAgent revealed 8 BlueAgents, they win.
		if (redTeamRevealed == 9) {
			return "Red Team won";
		}
		if(assassinRevealed == 1) {
			return AssassinMethod();
		}
		return "GJ guys you found a bug.";
	}
	

	/**Method defined which correctly returns whether or not the Board is in one of the winning states
	 * */
	public boolean winning() {
			if (blueTeamRevealed == 8) {
				return true;
			}// If BlueAgent revealed 8 BlueAgents, they win.
			if (redTeamRevealed == 9) {
				return true;
			}// If RedAgent revealed 9 RedAgents, they win.

			if (assassinRevealed == 1) {
				return true;
			}//If BlueAgent or RedAgent revealed assassin, The other team wins.
			return false;
	}

	/**Method defined which correctly returns which team did not lose (i.e., win) when the Assassin was revealed
	 * */	
	public String AssassinMethod(){
		if(teamTurn == 1 && assassinRevealed == 1){
			return "Blue Team Wins";
		}
		else if(teamTurn == 3 && assassinRevealed == 1){
			return "Red Team Wins";
		}
		else{
			return "Assassin is not revealed";
		}
	}

	
	/**
	 * selectCodenames() will store the array listForCodenames[] with codenames in GameWords.txt
	 * by calling the readcodenames() method. The codenames will also be randomized using the Random
	 * class.
	 */
	public void selectCodenames(){
		ArrayList<String> readcodename = readcodename();
		ArrayList<String> x = new ArrayList<String>();
		for(int i=0;i<readcodename.size();i++) {
			x.add(readcodename.get(i));
		}

		listForCodenames = new String[25];

		Random rand = new Random();
		int a = rand.nextInt(x.size());
		for(int i=0;i<25;i++) {
			a = rand.nextInt(x.size());
			listForCodenames[i]= x.get(a);
			x.remove(a);


		}
	}	

	/**
	 *  generateAssignments() will store the array listForRoles[] with
	 *  either "Red Agent". "Blue Agent", "Innocent Bystander", or "Assassin"
	 *  and randomizes the order in the array.
	 */
	public void generateAssignments(){

		listForRoles = new String[25];

		String [] y = new String[25];
		for(int i=0;i<9;i++) 
		{
			y[i]="Red Agent";
		}
		for(int i=9;i<17;i++) 
		{
			y[i]="Blue Agent";
		}
		for(int i=17;i<24;i++) 
		{
			y[i]="Innocent Bystander";
		}
		y[24]= "Assassin";

		ArrayList<Integer> x = new ArrayList<Integer>();
		for(int i=0;i<25;i++) {
			x.add(i);
		}
		Random rand = new Random();
		int a = rand.nextInt(x.size());
		for(int i=0;i<25;i++) {
			a = rand.nextInt(x.size());
			listForRoles[i]= y[x.get(a)];
			x.remove(a);

		}

	}


	//might have to add on if statement for substring.
	/**
	 * Decrements the count, updates a Location when the Location's codename was 
	 * selected, and returns if the Location contained the current team's Agent
	 * 
	 * @param  clue  the clue the super agent gives to each player
	 * @return true if the clue is legal.
	 *         false if the clue is illegial
	 */
	/*public boolean checkLegalClue(String clue) {
		for (int i = 0; i<listForCodenames.length; i++) {
			String codeName = locations.get(i).getPerson();
			if (codeName.equals(clue)) { 
				return false;
			} 
		}
		notifyObservers();
		return true;
	}*/
	
	
	//-----------might use later-----
	public boolean checkLegalClue(String clue) {
		boolean clues = true;
		for(int i = 0; i < locations.size(); i++) {
			if(locations.get(i).getPerson().equals(clue)) {
				clues = false;
			}
			if(locations.get(i).getRevealed()) {
				clues = true;
			}
		}
		notifyObservers();
		return clues;
	}
	
	
	public void setCount(int count) {
		this.count = count;
	}

	/**Method defined which decrements the count, 
	* updates a Location when the Location's codename was selected,
	* and ****** returns if the Location contained the current team's Agent 
	* 
	* @param selectedCodename the codename that the player selected
	* @return true if the codename is on the current turn's team
	          false otherwise
	 */
	//try array instead of arraylist

	public boolean updateLocation(String selectedCodename) {
		boolean loc = false;	
		for (int i = 0; i < locations.size(); i++) {
			if(locations.get(i).getPerson().equals(selectedCodename)) {
				locations.get(i).reveal();
				if((locations.get(i).getTeam().equals("Red Agent") && teamTurn == 1) 
						|| (locations.get(i).getTeam().equals("Blue Agent") && teamTurn == 3)){
					count--;
					loc = true;
				}
			}
		}
		notifyObservers();
		return loc;
	}
	
	public void addObserver(Observer obs) {
		observers.add(obs);
		notifyObservers();
	}

	public void notifyObservers() {
		for(Observer ob:observers) {
			ob.update();
		}
	}
	
	// getSize() returns the size of the board
		public int getSize() {
			return _size;
		}
		// getLocation() returns an ArrayList of Locations containing the entire board of locations.
		public ArrayList<Location> getLocation(){
			return locations;
		}	
		// setTurn(int x) sets current turn to x (1 or 2) -- (red or blue)
		public void setTurn(int x ) {
			teamTurn = x;
		}
		public String[] getListForCodenames() {
			return listForCodenames;
		}

		public String[] getListForRoles() {
			return listForRoles;
		}
		
		public void switchTeamMethod() {
			if (teamTurn == 0) {
				teamTurn = 1;
			}
			else if (teamTurn == 1) {
				teamTurn = 2;
			}	
			else if (teamTurn == 2) {
				teamTurn = 3;
			}
			else if(teamTurn == 3) {
				teamTurn = 0;
			}
			notifyObservers();
		}
		
		public int getTeamTurn() {
				return teamTurn;
			}
			
		
		public int getCount() {
			return count;
		}
		
		
}

