package CluedoGame;

import Cards.*;
import GUI.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.imageio.ImageIO;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Cluedo extends GUI {
    private static boolean isGameOver;
    public int numOfSteps;
    private static Board board;
    private Player player;
    private boolean gameWon = false;
    private ArrayList<Player> players;
    private ArrayList<Card> solutionSet = new ArrayList<>();

    RoomCard solutionRoom;
    WeaponCard solutionWeapon;
    CharacterCard solutionCharacter;

    public Cluedo() {
    	players = new ArrayList<Player>();
    	Board board = new Board(players);
    }
    
    public static void main(String args[]) {
    	Cluedo game = new Cluedo();
    }
    
    @Override
	protected void redraw() {
		// TODO Auto-generated method stub
		
	}
    
    @Override
	protected void redraw(Graphics g) {
		draw(g);
	}
    
    @Override
	protected void draw(Graphics g) {
		// TODO Auto-generated method stub
    	Image image = null;
    	try {
    		image = ImageIO.read(new File("C:\\Users\\jacobsscot\\eclipse-workspace\\images\\boardPic.png"));
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	g.drawImage(image, 0, 0, this);
    	for (Player p : players) {
    		// Draw the players' tokens on the board
    	}
    	for (Room r : rooms) {
    		// Draw the weapon and npc tokens in each room
    	}
	}
    
    /**
     * Deal out the cards when starting the game
     * dealCards shuffles the whole card deck, pick out a
     * murder set, and deals the rest of the cards to all the current players.
     * */
    public void dealCards(){

        //set up list for each type of cards
        ArrayList<WeaponCard> weaponCards = new ArrayList<>();
        ArrayList<CharacterCard> characterCards = new ArrayList<>();
        ArrayList<RoomCard> roomCards = new ArrayList<>();

        //get all cards and put them in their designated list
        for(WeaponType w: WeaponType.values()){
            weaponCards.add(new WeaponCard(w));
        }
        for(CharacterType c: CharacterType.values()){
            characterCards.add(new CharacterCard(c));
        }
        for(RoomType r: RoomType.values()){
            roomCards.add(new RoomCard(r));
        }

        //shuffle cards
        Collections.shuffle(weaponCards);
        Collections.shuffle(characterCards);
        Collections.shuffle(roomCards);

        //randomly select a murder set
        solutionRoom = roomCards.get(0);
        solutionCharacter = characterCards.get(0);
        solutionWeapon = weaponCards.get(0);

        //Remove those murder sets from its designated list
        roomCards.remove(0);
        characterCards.remove(0);
        weaponCards.remove(0);

        //add rest of cards after removing solution
        //Combine remaining cards and shuffle
        ArrayList <Card> netCards = new ArrayList<>();
        netCards.addAll(weaponCards);
        netCards.addAll(characterCards);
        netCards.addAll(roomCards);
        Collections.shuffle(netCards);

        //redistribute to other players
        for(Player p: players){
            if(netCards.isEmpty()){
                break;
            }
            p.addHand(netCards.remove(0));
        }


    }
	
	/**
     * Get player's turn with their
     * chosen state type
     * @param state checks the type of state
     * */
    public void takeTurn(GUIStateType state){
        if(state == GUIStateType.MOVE){

        }
        if(state == GUIStateType.ACCUSE){
            boolean b =currentPlayer.doAccusation(board,accusedWeapon, accusedRoom, accusedCharacter,
                    solutionWeapon, solutionRoom,solutionCharacter);
            if(b == true){
                isGameOver = true;
            }
            //remove player from the list of players on game
            else{
                players.remove(currentPlayer);
            }
        }
        if(state == GUIStateType.SUGGEST){
            //boolean s =currentPlayer.doSuggestion(board,players,);
        }
    }

	

}
