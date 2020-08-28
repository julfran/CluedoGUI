package CluedoGame;
import Cards.Card;
import Cards.CharacterCard;
import Cards.RoomCard;
import Cards.WeaponCard;
import Cells.Cell;
import GUI.GUIStateType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Player {
	private ArrayList<Card> myCardList = new ArrayList<>();
	private CharacterType myCharacterType;
	private Cell currentLocation;
	private String playerName;
	private boolean isEliminated = false;
	private boolean isPlaying = false;
	private int numSteps;


	/**Constructor
	 * @param c- players chosen character type
	 * @param name- players name input for that character
	 * */
	public Player(CharacterType c, String name, GUIStateType state){
		this.myCharacterType = c;
		this.playerName = name;
		isEliminated = false;
		myCardList = new HashSet<Card>();
	}

	/**
	 * getCharacterType returns the player's type of the character
	 * @return myCharacterType
	 * */
	public CharacterType getCharacterType (){
		return myCharacterType;
	}

	/**
	 * getName returns the name of the player's character
	 * @return playerName
	 * */
	public String getPlayerName(){
		return playerName;
	}

	/**
	 * getCell returns the cell location of the player
	 * */
	public Cell getCell(){
		return currentLocation;
	}

	/**
	 * @return Set the player to isEliminated equal to true
	 * */
	public boolean eliminatePlayer(){
		return this.isEliminated = true;
	}

	/**
	 * @return get current players set of cards
	 * */
	public ArrayList <Card> getHand(){
		return myCardList;
	}

	/**
	 * Add cards to Player's hand
	 * @param c cards to be added to hand
	 * */
	public void addHand(Card c){
		if((c instanceof RoomCard || c instanceof CharacterCard
				|| c instanceof WeaponCard)){
			myCardList.add(c);
		}
	}

	/**
	 * Sets current player's amount of steps it can make.
	 * This value is retrieved from the dice sum value.
	 * */
	public void setNumStep(int steps){
		this.numSteps = steps;
	}

	/**
	 * Gets current player's amount of steps it can make
	 * This value is retrieved from the dice sum value.
	 * */
	public int getNumSteps(){
		return numSteps;
	}
	
	/**
	 * Implements the accuse strategy where it checks if the accused
	 * elements al match the murder set.
	 * */
	public boolean doAccusation(Board b, List<Player> players, WeaponType w, RoomType r, CharacterType c,
								WeaponCard murderWeapon, RoomCard murderRoom, CharacterCard murderCharacter){

		Player p = b.getCurrentPlayer(); //get current players

		//If player successfully guesses murder weapons right
		if(murderCharacter.characters == c &&
				murderWeapon.weapon == w && murderRoom.rooms == r){
					return true;
				}
		//else game proceeds
		//current player is elimenated
		else {
			p.eliminatePlayer();
		}
		return false;
	}


	//method to check if move is possible

	//------------
	//SUGGESTION





	//------------
	//REFUTE


//	private CharacterType playersCharacter;
//    private RoomType room;
//    private boolean isStillPlaying;
//    private ArrayList <Card> hand = new ArrayList<>();
//    private Cell cell;
//	int x = 0;
//	int y = 0;
//
//
//
//    /* Constructor for the Player object */
//    public Player(CharacterType character){
//        this.playersCharacter = character;
//        isStillPlaying = true;
//    }
//
//    /* Helper methods */
//
//    public void addToHand(Card c) {
//    	hand.add(c);
//    }
//
//    public int getX() {
//    	return x;
//  	}
//
//  	public int getY() {
//  		return y;
//  	}
//
//  	public void getCell(Cell c) {
//    	cell = c;
//  	}
//
//  	public RoomType getRoom() {
//  		return room;
//  	}
//
//	public void move(Cell c) {
//    	if (c.getClass() == RoomCell.class) {
//    		room = ((RoomCell) c).getType();
//    	} else {
//    		room = null;
//    	}
//    	cell = c;
//    }
//
//    public CharacterType getName(){
//        return this.playersCharacter;
//    }
//
//    public ArrayList<Card> getHand() {
//    	return hand;
//    }
//
//     public String toString() {
//    	if (playersCharacter == CharacterType.COLONELMUSTARD) {
//    		return "Colonel Mustard";
//    	}
//    	if (playersCharacter == CharacterType.MISSSCARLETT) {
//    		return "Miss Scarlet";
//    	}
//    	if (playersCharacter == CharacterType.MRSWHITE) {
//    		return "Mrs White";
//    	}
//    	if (playersCharacter == CharacterType.MRGREEN) {
//    		return "Mr Green";
//    	}
//    	if (playersCharacter == CharacterType.MRSPEACOCK) {
//    		return "Mrs Peacock";
//    	}
//    	if (playersCharacter == CharacterType.PROFESSORPLUM) {
//    		return "Professor Plum";
//    	}
//    	return "[Player name not found]";
//    }
//    //Not needed but will remain just in case
//
//    /**
//     * Used for removing a player from the game loop when an accusation is incorrect
//     * @param isStillPlaying
//     */
//    public void isStillPlaying(boolean isStillPlaying){
//    	this.isStillPlaying = isStillPlaying;
//    }
//
//    public boolean getIsStillPlaying(){
//    	return this.isStillPlaying;
//    }

}
