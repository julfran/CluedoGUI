package CluedoGame;
import Cards.Card;
import Cards.CharacterCard;
import Cards.RoomCard;
import Cards.RoomType;
import Cards.WeaponCard;
import Cards.WeaponType;
import Cells.Cell;
import Cells.RoomCell;
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
	private Room room;

	/**Constructor
	 * @param c- players chosen character type
	 * @param name- players name input for that character
	 * */
	public Player(CharacterType c, String name){
		this.myCharacterType = c;
		this.playerName = name;
		isEliminated = false;
		myCardList = new ArrayList<Card>();
	}

	public void putInRoom(Room r) {
		room = r;
	}
	
	public void takeOutOfRoom() {
		room = null;
	}
	
	public RoomType getRoomType() {
		if (room != null) {
			return room.getType();
		}
		return null;
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
	
	public void setCell(Cell cell) {
		currentLocation = cell;
	}

	/**
	 * getCell returns the cell location of the player
	 * */
	public Cell getCell(){
		return currentLocation;
	}
	
	public int getX() {
		return currentLocation.getXPos();
	}
	
	public int getY() {
		return currentLocation.getYPos();
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
	 * Implement the suggest strategy where it get currentPlayer's
	 * Room type and record the suggestion set.
	 * */
	public boolean doSuggestion(Board b, ArrayList <Player> playerList, RoomType suggestedRoom,
								CharacterType suggestedCharacter, WeaponType suggestedWeapon){
		Player p = b.getCurrentPlayer();

		//players suggests room they've entered, choose a weapon they want to suggest and who did it
		suggestedCharacter = p.getCharacterType();
		suggestedRoom = p.getRoomType();
		//suggestedWeapon 			//this one later when gui happens??

		//Check if player has the right Roomcell location then set to suggestedRoom
		for(Player player: playerList){
			if(player.getCharacterType() == suggestedCharacter &&
					player.getCell() instanceof RoomCell){
					Cell temp = player.getCell();
					if(temp.getClass() == RoomCell.class){
						suggestedRoom = ((Room) temp).getType();
					}
			}
		}

		//Check if any players want to refute
		//Ask all other players if they want to refute the suggestion

		return false;

	}
}
