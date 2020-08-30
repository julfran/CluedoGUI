package CluedoGame;

import Cards.*;
import GUI.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class Cluedo extends GUI {

	// Cell Details
	public final int CELL_WIDTH = 27;
	public final int CELL_HEIGHT = 27;
	
	// Movement fields
	private boolean waitingForClick = false;
	private Player movingPlayer;
	private int roll;
	
    private static boolean isGameOver;
    public int numOfSteps;
    private static Board board;
    private Player player;
    private boolean gameWon = false;
    private ArrayList<Player> players;
    private ArrayList<Card> solutionSet = new ArrayList<>();
	private ArrayList<CharacterType> npcs; // The characters that aren't being played
	private ArrayList<WeaponType> weapons;
    
	// Image files
	private Image background;
	// Characters:
	private Image tokenScarlett;
	private Image tokenMustard;
	private Image tokenWhite;
	private Image tokenGreen;
	private Image tokenPeacock;
	private Image tokenPlum;
	// Weapons:
	private Image tokenCandle;
	private Image tokenDagger;
	private Image tokenPipe;
	private Image tokenRevolver;
	private Image tokenRope;
	private Image tokenSpanner;
	
    RoomCard solutionRoom;
    WeaponCard solutionWeapon;
    CharacterCard solutionCharacter;

    public Cluedo() {
    	// Load the images
    	try {
    		background = ImageIO.read(new File("assets/boardPic.png"));
    		tokenScarlett = ImageIO.read(new File("assets/tokenScarlett.png"));
    		tokenMustard = ImageIO.read(new File("assets/tokenMustard.png"));
    		tokenWhite = ImageIO.read(new File("assets/tokenWhite.png"));
    		tokenGreen = ImageIO.read(new File("assets/tokenGreen.png"));
    		tokenPeacock = ImageIO.read(new File("assets/tokenPeacock.png"));
    		tokenPlum = ImageIO.read(new File("assets/tokenPlum.png"));
    		tokenCandle = ImageIO.read(new File("assets/tokenCandle.png"));
    		tokenDagger = ImageIO.read(new File("assets/tokenDagger.png"));
    		tokenPipe = ImageIO.read(new File("assets/tokenPipe.png"));
    		tokenRevolver = ImageIO.read(new File("assets/tokenRevolver.png"));
    		tokenRope = ImageIO.read(new File("assets/tokenRope.png"));
    		tokenSpanner = ImageIO.read(new File("assets/tokenSpanner.png"));
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
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
    	g.drawImage(background, 0,0,580,605,null);
    	// Draw the players:
    	if (players != null) {
    		for (Player p : players) {
        		if (p.getCharacterType() == CharacterType.MISSSCARLETT) {
        			g.drawImage(tokenScarlett, p.getX()*CELL_WIDTH, p.getY()*CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT, null);
        		}
        		if (p.getCharacterType() == CharacterType.COLONELMUSTARD) {
        			g.drawImage(tokenMustard, p.getX()*CELL_WIDTH, p.getY()*CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT, null);
        		}
        		if (p.getCharacterType() == CharacterType.MRSWHITE) {
        			g.drawImage(tokenWhite, p.getX()*CELL_WIDTH, p.getY()*CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT, null);
        		}
        		if (p.getCharacterType() == CharacterType.MRGREEN) {
        			g.drawImage(tokenGreen, p.getX()*CELL_WIDTH, p.getY()*CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT, null);
        		}
        		if (p.getCharacterType() == CharacterType.MRSPEACOCK) {
        			g.drawImage(tokenPeacock, p.getX()*CELL_WIDTH, p.getY()*CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT, null);
        		}
        		if (p.getCharacterType() == CharacterType.PROFESSORPLUM) {
        			g.drawImage(tokenPlum, p.getX()*CELL_WIDTH, p.getY()*CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT, null);
        		}
        	}
    	}
    	if (board != null) {
    		for (Room r : board.getRooms()) {
        		// Draw the weapon and npc tokens in each room
        		for (WeaponType w : weapons) {
        			if (r.contains(w)) {
        				switch (w) {
    					case CANDLESTICK:
    						g.drawImage(tokenCandle, r.getWeaponCell(w).x()*CELL_WIDTH, r.getWeaponCell(w).y()*CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT, null);
    						break;
    					case DAGGER:
    						g.drawImage(tokenDagger, r.getWeaponCell(w).x()*CELL_WIDTH, r.getWeaponCell(w).y()*CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT, null);
    						break;
    					case LEADPIPE:
    						g.drawImage(tokenPipe, r.getWeaponCell(w).x()*CELL_WIDTH, r.getWeaponCell(w).y()*CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT, null);
    						break;
    					case REVOLVER:
    						g.drawImage(tokenRevolver, r.getWeaponCell(w).x()*CELL_WIDTH, r.getWeaponCell(w).y()*CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT, null);
    						break;
    					case ROPE:
    						g.drawImage(tokenRope, r.getWeaponCell(w).x()*CELL_WIDTH, r.getWeaponCell(w).y()*CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT, null);
    						break;
    					case SPANNER:
    						g.drawImage(tokenSpanner, r.getWeaponCell(w).x()*CELL_WIDTH, r.getWeaponCell(w).y()*CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT, null);
    						break;
    					default:
    						break;
    					}
        			}
        		}
        		for (CharacterType c : npcs) {
        			if (r.contains(c)) {
        				switch (c) {
    					case COLONELMUSTARD:
    						g.drawImage(tokenMustard, r.getCharacterCell(c).x()*CELL_WIDTH, r.getCharacterCell(c).y()*CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT, null);
    						break;
    					case MISSSCARLETT:
    						g.drawImage(tokenScarlett, r.getCharacterCell(c).x()*CELL_WIDTH, r.getCharacterCell(c).y()*CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT, null);
    						break;
    					case MRGREEN:
    						g.drawImage(tokenGreen, r.getCharacterCell(c).x()*CELL_WIDTH, r.getCharacterCell(c).y()*CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT, null);
    						break;
    					case MRSPEACOCK:
    						g.drawImage(tokenPeacock, r.getCharacterCell(c).x()*CELL_WIDTH, r.getCharacterCell(c).y()*CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT, null);
    						break;
    					case MRSWHITE:
    						g.drawImage(tokenWhite, r.getCharacterCell(c).x()*CELL_WIDTH, r.getCharacterCell(c).y()*CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT, null);
    						break;
    					case PROFESSORPLUM:
    						g.drawImage(tokenPlum, r.getCharacterCell(c).x()*CELL_WIDTH, r.getCharacterCell(c).y()*CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT, null);
    						break;
    					default:
    						break;
    					}
        			}
        		}
        	}
    	}	
	}
	
	/**
	 * Function called on mouse click in graphics pane
	 * 
	 **/
	protected void onClick(MouseEvent e) {
		if (waitingForClick) {
			int x = e.getX();
			int y = e.getY();
			x = x / CELL_WIDTH;
			y = y / CELL_HEIGHT;
			if (x >= 0 && x <= 21 && y >= 0 && y <= 21) {
				if (board.checkPath(movingPlayer, new Coord(x, y), roll)) {
					if (board.movePlayer(movingPlayer, board.getCell(x, y))) {
						waitingForClick = false;
					} else {
						System.out.println("Unknown error occured. Failed to move.");
					}
				} else {
					System.out.println("Failed to find valid path to given point.");
				}
			}
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

	

}
