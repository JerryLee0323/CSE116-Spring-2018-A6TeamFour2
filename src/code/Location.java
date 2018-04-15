package code;


public class Location {

	private String person;
	private boolean revealed;
	private String team;
	
	/**
	 *
	 * This constructor creates a new location storing 1 String and 1 boolean.
	 * The String is the person set on the location and the boolean checks if the
	 * person is revealed or not.
	 * @param  str  name of the person
	 * @param  t    the team of the person
	 */
	public Location(String str, String t){
		person = str;	
		revealed = false;
		team = t;
	}
	
	// getLocation() returns the person at the current location
	public String getPerson(){
		return person;
	}
	
	// setPerson(String str) assigns str as the person on the current location 
	public void setPerson(String str) {
		person = str;
	}
	
	// getRevealed() returns if the character was revealed or not. 
	public boolean getRevealed() {
		return revealed;
	}
	
	// reveal() flips the card to reveal the person
	public void reveal() {
		revealed = true;
	}
	
	// getTeam() returns the team that the person is on.
	public String getTeam() {
		return team;
	}
	
	
	// setTeam(String str) assigns the the person a team.
	public void setTeam(String str) {
		team = str;
	}
}
