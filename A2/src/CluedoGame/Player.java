package CluedoGame;
import Cards.Card;
import Cards.RoomType;
import Cells.Cell;
import Cells.RoomCell;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Player {
	private Set<Card> myCardList;
	private CharacterType myCharacterType;
	private Cell currentLocation;
	private String playerName;
	private boolean isEliminated = false;
	private int stepsAvailable;


	/**Constructor
	 * @param c- players chosen character type
	 * @param name- players name input for that character
	 * */
	public Player(CharacterType c, String name){
		this.myCharacterType = c;
		this.playerName = name;
		isEliminated = false;
		myCardList = new HashSet<Card>();
	}

	//method to get character
	//method to get name of character
	//method to change if player is still in the game
	//method to get location of player
	//method to set location of player
	//method to add card to cardlist
	//method to set the stepsavailable
	//method to get available step
	//method to check if move is possible

	//------------
	//SUGGESTION


	//------------
	//ACCUSE


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
