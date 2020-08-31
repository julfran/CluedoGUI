package CluedoGame;

import Cards.*;
import Cells.RoomCell;
import GUI.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class Cluedo extends GUI {

	// Cell Details
	public final int CELL_WIDTH = 27;
	public final int CELL_HEIGHT = 27;
	
	// Movement fields
	private boolean waitingForClick = false;
	private int roll;
	
	// Turn variables
	private boolean gameRunning = false;
    private Player activePlayer;
    private boolean hasMoved;
    private boolean hasAccused;
    private boolean hasSuggested;
	
    private static boolean isGameOver;
    public int numOfSteps;
    private static Board board;
    private boolean gameWon = false;
    private ArrayList<Player> players;
    private ArrayList<Player> remainingPlayers;
    private ArrayList<Card> solutionSet = new ArrayList<>();
	private ArrayList<CharacterType> npcs; // The characters that aren't being played
	private ArrayList<WeaponType> weapons;
    
	// Image files
	private Image background;
	// Tokens:
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
	// Cards
	// Characters:
	private Image cardScarlett;
	private Image cardMustard;
	private Image cardWhite;
	private Image cardGreen;
	private Image cardPeacock;
	private Image cardPlum;
	// Weapons:
	private Image cardCandle;
	private Image cardDagger;
	private Image cardPipe;
	private Image cardRevolver;
	private Image cardRope;
	private Image cardSpanner;
	// Rooms:
	private Image cardBallroom;
	private Image cardConservatory;
	private Image cardBilliardRoom;
	private Image cardLibrary;
	private Image cardStudy;
	private Image cardHall;
	private Image cardLounge;
	private Image cardDiningRoom;
	private Image cardKitchen;
	
    RoomCard solutionRoom;
    WeaponCard solutionWeapon;
    CharacterCard solutionCharacter;

    public Cluedo() {
    	// Load the images
    	try {
    		//Tokens
    		//Characters
    		background = ImageIO.read(new File("assets/boardPic.png"));
    		tokenScarlett = ImageIO.read(new File("assets/tokenScarlett.png"));
    		tokenMustard = ImageIO.read(new File("assets/tokenMustard.png"));
    		tokenWhite = ImageIO.read(new File("assets/tokenWhite.png"));
    		tokenGreen = ImageIO.read(new File("assets/tokenGreen.png"));
    		tokenPeacock = ImageIO.read(new File("assets/tokenPeacock.png"));
    		tokenPlum = ImageIO.read(new File("assets/tokenPlum.png"));
    		//Weapons
    		tokenCandle = ImageIO.read(new File("assets/tokenCandle.png"));
    		tokenDagger = ImageIO.read(new File("assets/tokenDagger.png"));
    		tokenPipe = ImageIO.read(new File("assets/tokenPipe.png"));
    		tokenRevolver = ImageIO.read(new File("assets/tokenRevolver.png"));
    		tokenRope = ImageIO.read(new File("assets/tokenRope.png"));
    		tokenSpanner = ImageIO.read(new File("assets/tokenSpanner.png"));
    		//Cards
    		// Characters:
    		cardScarlett = ImageIO.read(new File("assets/cardScarlett.png"));
    		cardMustard = ImageIO.read(new File("assets/cardMustard.png"));
    		cardWhite = ImageIO.read(new File("assets/cardWhite.png"));
    		cardGreen = ImageIO.read(new File("assets/cardGreen.png"));
    		cardPeacock = ImageIO.read(new File("assets/cardPeacock.png"));
    		cardPlum = ImageIO.read(new File("assets/cardPlum.png"));
    		// Weapons:
    		cardCandle = ImageIO.read(new File("assets/cardCandle.png"));
    		cardDagger = ImageIO.read(new File("assets/cardDagger.png"));
    		cardPipe = ImageIO.read(new File("assets/cardPipe.png"));
    		cardRevolver = ImageIO.read(new File("assets/cardRevolver.png"));
    		cardRope = ImageIO.read(new File("assets/cardRope.png"));
    		cardSpanner = ImageIO.read(new File("assets/cardSpanner.png"));
    		// Rooms:
    		cardBallroom = ImageIO.read(new File("assets/cardBallroom.png"));
    		cardConservatory = ImageIO.read(new File("assets/cardConservatory.png"));
    		cardBilliardRoom = ImageIO.read(new File("assets/cardBilliards.png"));
    		cardLibrary = ImageIO.read(new File("assets/cardLibrary.png"));
    		cardStudy = ImageIO.read(new File("assets/cardStudy.png"));
    		cardHall = ImageIO.read(new File("assets/cardHall.png"));
    		cardLounge = ImageIO.read(new File("assets/cardLounge.png"));
    		cardDiningRoom = ImageIO.read(new File("assets/cardDining.png"));
    		cardKitchen = ImageIO.read(new File("assets/cardKitchen.png"));
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    public static void main(String args[]) {
    	Cluedo game = new Cluedo();
    }
    
    public void startGame(ActionEvent e, ArrayList<Player> p) {
    	players = p;
    	remainingPlayers = new ArrayList<Player>(p);
    	npcs = new ArrayList<CharacterType>();
    	for (CharacterType c : CharacterType.values()) {
    		boolean npc = true;
    		for (Player player : remainingPlayers) {
    			if (player.getCharacterType() == c) {
    				npc = false;
    			}
    		}
    		if (npc) {
    			npcs.add(c);
    		}
    	}
    	System.out.println("Players:");
    	for (Player player : players) {
    		System.out.println(player.getPlayerName() + "is playing " + player.getCharacterType());
    	}
    	board = new Board(players);
    	gameRunning = true;
    	activePlayer = players.get(0);
    	System.out.println(activePlayer.getPlayerName() + "'s turn.");
    	hasMoved = false;
    	dealCards();
    	redraw();
    }
    
    public void move(ActionEvent e) {
    	if (!hasMoved && gameRunning && !hasSuggested) {
    		roll = (int) (Math.random() * 6 + 1) + (int) (Math.random() * 6 + 1);
    		System.out.println("You rolled a: " + roll);
    		waitingForClick = true;
        	hasMoved = true;
    	} else {
    		if (hasMoved) {
			System.out.println("------------------------------");
    			System.out.println("You've already moved, " + activePlayer.getPlayerName());
    		}
    		if (!gameRunning) {
    			System.out.println("You can't move, there's no game running.");
    		}
    		if (hasSuggested) {
			ystem.out.println("-------------------------------");
    			System.out.println("You can't move after making a suggestion.");
    		}
    	}
    }
    
	public void endTurn(ActionEvent e) {
    	endTurn();
    }

	public void endTurn() {
		int x = remainingPlayers.indexOf(activePlayer);
    	if (x + 1 < remainingPlayers.size()) {
    		activePlayer = remainingPlayers.get(x + 1);
    	} else {
    		activePlayer = remainingPlayers.get(0);
    	}
	System.out.println("-------------------------");	
    	System.out.println(activePlayer.getPlayerName() + "'s turn.");
    	hasMoved = false;
    	hasAccused = false;
    	hasSuggested = false;
    	redraw();
	}
	
    public void suggest(ActionEvent e) {
    	if (!hasSuggested) {
    		WeaponType cw = null;
        	RoomType cr = activePlayer.getRoomType();
        	CharacterType cc = null;
        	String chosenWeapon = null;
        	String chosenCharacter = null;
        	
        	//repeat until chosen items aren't null
        	while(cw==null&&cr==null&&cc==null&&chosenWeapon==null&&chosenCharacter==null) {

        		//setup drop down boxes to choose final accusation
        		String[] weaponChoices = {"Candle", "Dagger", "Lead Pipe", "Revolver", "Rope", "Spanner"};
        		chosenWeapon = (String) JOptionPane.showInputDialog(frame,"Choose the weapon","Customized Dialog",JOptionPane.PLAIN_MESSAGE,null,weaponChoices,"weapons");
        		String[] characterChoices = {"Miss Scarlet", "Colonel Mustard", "Mrs. White", "Mr. Green", "Mrs. Peacock", "Professor Plum"};
        		chosenCharacter = (String) JOptionPane.showInputDialog(frame,"Choose the character","Customized Dialog",JOptionPane.PLAIN_MESSAGE,null,characterChoices,"characters");

        		//Weapons
        		if(chosenWeapon.equals(WeaponType.CANDLESTICK.toString())) {
        			cw = WeaponType.CANDLESTICK;
        		}
        		if(chosenWeapon.equals(WeaponType.DAGGER.toString())) {
        			cw = WeaponType.DAGGER;
        		}
        		if(chosenWeapon.equals(WeaponType.LEADPIPE.toString())) {
        			cw = WeaponType.LEADPIPE;
        		}
        		if(chosenWeapon.equals(WeaponType.REVOLVER.toString())) {
        			cw = WeaponType.REVOLVER;
        		}
        		if(chosenWeapon.equals(WeaponType.ROPE.toString())) {
        			cw = WeaponType.ROPE;
        		}
        		if(chosenWeapon.equals(WeaponType.SPANNER.toString())) {
        			cw = WeaponType.SPANNER;
        		}
        		
        		
        		//Characters
        		if(chosenCharacter.equals("Miss Scarlet")) {
        			chosenCharacter = "R";
        		}
        		if(chosenCharacter.equals("Colonel Mustard")) {
        			chosenCharacter = "M";
        		}
        		if(chosenCharacter.equals("Mrs. White")) {
        			chosenCharacter = "W";
        		}
        		if(chosenCharacter.equals("Mr. Green")) {
        			chosenCharacter = "G";
        		}
        		if(chosenCharacter.equals("Mrs. Peacock")) {
        			chosenCharacter = "E";
        		}
        		if(chosenCharacter.equals("Professor Plum")) {
        			chosenCharacter = "P";
        		}
        		
        		if(chosenCharacter.equals(CharacterType.MISSSCARLETT.toString())) {
        			cc = CharacterType.MISSSCARLETT;
        		}
        		if(chosenCharacter.equals(CharacterType.COLONELMUSTARD.toString())) {
        			cc = CharacterType.COLONELMUSTARD;
        		}
        		if(chosenCharacter.equals(CharacterType.MRSWHITE.toString())) {
        			cc = CharacterType.MRSWHITE;
        		}
        		if(chosenCharacter.equals(CharacterType.MRGREEN.toString())) {
        			cc = CharacterType.MRGREEN;
        		}
        		if(chosenCharacter.equals(CharacterType.MRSPEACOCK.toString())) {
        			cc = CharacterType.MRSPEACOCK;
        		}
        		if(chosenCharacter.equals(CharacterType.PROFESSORPLUM.toString())) {
        			cc = CharacterType.PROFESSORPLUM;
        		}
        	
        	}
        	
        	hasSuggested = true;
        	//call suggestion on chosen items
        	doSuggestion(board, players, cr, cc, cw, frame);
    	} else {
    		System.out.println("You can't suggest more than once a turn.");
    	}
    }
    
    public void accuse(ActionEvent e) {
    	if (!hasSuggested) {
    		hasAccused = true;
        	WeaponType cw = null;
        	RoomType cr = null;
        	CharacterType cc = null;
        	String chosenWeapon = null;
        	String chosenRoom = null;
        	String chosenCharacter = null;
        	
        	//repeat until chosen items aren't null
        	while(cw==null&&cr==null&&cc==null&&chosenWeapon==null&&chosenRoom==null&&chosenCharacter==null) {

        		//setup drop down boxes to choose final accusation
        		String[] weaponChoices = {"Candle", "Dagger", "Lead Pipe", "Revolver", "Rope", "Spanner"};
        		chosenWeapon = (String) JOptionPane.showInputDialog(frame,"Choose the weapon","Customized Dialog",JOptionPane.PLAIN_MESSAGE,null,weaponChoices,"weapons");
        		String[] roomChoices = {"Kitchen", "Ball Room", "Conservatory", "Dining Room", "Billiard Room", "Library", "Lounge", "Hall", "Study"};
        		chosenRoom = (String) JOptionPane.showInputDialog(frame,"Choose the room","Customized Dialog",JOptionPane.PLAIN_MESSAGE,null,roomChoices,"rooms");
        		String[] characterChoices = {"Miss Scarlet", "Colonel Mustard", "Mrs. White", "Mr. Green", "Mrs. Peacock", "Professor Plum"};
        		chosenCharacter = (String) JOptionPane.showInputDialog(frame,"Choose the character","Customized Dialog",JOptionPane.PLAIN_MESSAGE,null,characterChoices,"characters");

        		//Weapons
        		if(chosenWeapon.equals(WeaponType.CANDLESTICK.toString())) {
        			cw = WeaponType.CANDLESTICK;
        		}
        		if(chosenWeapon.equals(WeaponType.DAGGER.toString())) {
        			cw = WeaponType.DAGGER;
        		}
        		if(chosenWeapon.equals(WeaponType.LEADPIPE.toString())) {
        			cw = WeaponType.LEADPIPE;
        		}
        		if(chosenWeapon.equals(WeaponType.REVOLVER.toString())) {
        			cw = WeaponType.REVOLVER;
        		}
        		if(chosenWeapon.equals(WeaponType.ROPE.toString())) {
        			cw = WeaponType.ROPE;
        		}
        		if(chosenWeapon.equals(WeaponType.SPANNER.toString())) {
        			cw = WeaponType.SPANNER;
        		}
        		
        		//Rooms
        		if(chosenRoom.equals("Ball Room")) {
        			chosenRoom = "BallRoom";
        		}
        		if(chosenRoom.equals("Dining Room")) {
        			chosenRoom = "DiningRoom";
        		}
        		if(chosenRoom.equals("Billiard Room")) {
        			chosenRoom = "BilliardRoom";
        		}
        		
        		if(chosenRoom.equals(RoomType.BALLROOM.toString())) {
        			cr = RoomType.BALLROOM;
        		}
        		if(chosenRoom.equals(RoomType.CONSERVATORY.toString())) {
        			cr = RoomType.CONSERVATORY;
        		}
        		if(chosenRoom.equals(RoomType.BILLIARDROOM.toString())) {
        			cr = RoomType.BILLIARDROOM;
        		}
        		if(chosenRoom.equals(RoomType.LIBRARY.toString())) {
        			cr = RoomType.LIBRARY;
        		}
        		if(chosenRoom.equals(RoomType.STUDY.toString())) {
        			cr = RoomType.STUDY;
        		}
        		if(chosenRoom.equals(RoomType.HALL.toString())) {
        			cr = RoomType.HALL;
        		}
        		if(chosenRoom.equals(RoomType.LOUNGE.toString())) {
        			cr = RoomType.LOUNGE;
        		}
        		if(chosenRoom.equals(RoomType.DININGROOM.toString())) {
        			cr = RoomType.DININGROOM;
        		}
        		if(chosenRoom.equals(RoomType.KITCHEN.toString())) {
        			cr = RoomType.KITCHEN;
        		}
        		
        		//Characters
        		if(chosenCharacter.equals("Miss Scarlet")) {
        			chosenCharacter = "R";
        		}
        		if(chosenCharacter.equals("Colonel Mustard")) {
        			chosenCharacter = "M";
        		}
        		if(chosenCharacter.equals("Mrs. White")) {
        			chosenCharacter = "W";
        		}
        		if(chosenCharacter.equals("Mr. Green")) {
        			chosenCharacter = "G";
        		}
        		if(chosenCharacter.equals("Mrs. Peacock")) {
        			chosenCharacter = "E";
        		}
        		if(chosenCharacter.equals("Professor Plum")) {
        			chosenCharacter = "P";
        		}
        		
        		if(chosenCharacter.equals(CharacterType.MISSSCARLETT.toString())) {
        			cc = CharacterType.MISSSCARLETT;
        		}
        		if(chosenCharacter.equals(CharacterType.COLONELMUSTARD.toString())) {
        			cc = CharacterType.COLONELMUSTARD;
        		}
        		if(chosenCharacter.equals(CharacterType.MRSWHITE.toString())) {
        			cc = CharacterType.MRSWHITE;
        		}
        		if(chosenCharacter.equals(CharacterType.MRGREEN.toString())) {
        			cc = CharacterType.MRGREEN;
        		}
        		if(chosenCharacter.equals(CharacterType.MRSPEACOCK.toString())) {
        			cc = CharacterType.MRSPEACOCK;
        		}
        		if(chosenCharacter.equals(CharacterType.PROFESSORPLUM.toString())) {
        			cc = CharacterType.PROFESSORPLUM;
        		}
        	
        	}
        	
        	
        	//call accusation on chosen items
        	if(doAccusation(cw, cr, cc)) {
        		System.out.println(activePlayer.getPlayerName() + " has found the culprit. You win!");
			int reply = JOptionPane.showConfirmDialog(frame,activePlayer.getPlayerName() + " has won the game!!!",
        				"Game Over", JOptionPane.YES_NO_OPTION);
        		if(reply == JOptionPane.YES_OPTION){
        			System.exit(0);
        		}
        		if (reply == JOptionPane.NO_OPTION) {
        			System.exit(0);
        		}
        		else{
        			frame.getRootPane().requestFocus();
        		}
        	}
        	else { 
        		System.out.println(activePlayer.getPlayerName() + " has lost and can no longer play");
        		remainingPlayers.remove(activePlayer);
        		npcs.add(activePlayer.getCharacterType());
        		System.out.println("The solution was: " + solutionCharacter + " in the " + solutionRoom + " with the " + solutionWeapon + ".");
            	endTurn();
        	}
    	} else {
    		System.out.println("You can't suggest and accuse in the same turn.");
    	}
    	
    }
    
    @Override
	protected void redraw() {
		frame.repaint();
		
	}
    
    @Override
	protected void redraw(Graphics g) {
		draw(g);
	}
    
    @Override
	protected void draw(Graphics g) {
    	g.drawImage(background, 0,0,580,605,null);
    	// Draw the players:
    	if (remainingPlayers != null) {
    		for (Player p : remainingPlayers) {
    			if (p.getCell() != null) {
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
    	}
    	if (board != null) {
    		for (Room r : board.getRooms()) {
        		// Draw the weapon and npc tokens in each room
    			if (weapons != null) {
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
    			}
        		if (npcs != null) {
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
		
	//Draw player hand
    	ArrayList<Card> currentPlayerHand = new ArrayList<Card>(); 
    	ArrayList<Image> playerHandImages = new ArrayList<Image>(); 
    	int x = 580;
    	int y = 605;
    	
    	if(activePlayer!=null) {
    	
    		currentPlayerHand = activePlayer.getHand();
    		int numberOfCards = currentPlayerHand.size();
    	

    	for(Card i:currentPlayerHand) {
    		//Weapons
    		if(i.equals(WeaponType.CANDLESTICK)) {
    			playerHandImages.add(cardCandle);
    		}
    		if(i.equals(WeaponType.DAGGER)) {
    			playerHandImages.add(cardDagger);
    		}
    		if(i.equals(WeaponType.LEADPIPE)) {
    			playerHandImages.add(cardPipe);
    		}
    		if(i.equals(WeaponType.REVOLVER)) {
    			playerHandImages.add(cardRevolver);
    		}
    		if(i.equals(WeaponType.ROPE)) {
    			playerHandImages.add(cardRope);
    		}
    		if(i.equals(WeaponType.SPANNER)) {
    			playerHandImages.add(cardSpanner);
    		}
    		//Rooms
    		if(i.equals(RoomType.BALLROOM)) {
    			playerHandImages.add(cardBallroom);
    		}
    		if(i.equals(RoomType.CONSERVATORY)) {
    			playerHandImages.add(cardConservatory);
    		}
    		if(i.equals(RoomType.BILLIARDROOM)) {
    			playerHandImages.add(cardBilliardRoom);
    		}
    		if(i.equals(RoomType.LIBRARY)) {
    			playerHandImages.add(cardLibrary);
    		}
    		if(i.equals(RoomType.STUDY)) {
    			playerHandImages.add(cardStudy);
    		}
    		if(i.equals(RoomType.HALL)) {
    			playerHandImages.add(cardHall);
    		}
    		if(i.equals(RoomType.LOUNGE)) {
    			playerHandImages.add(cardLounge);
    		}
    		if(i.equals(RoomType.DININGROOM)) {
    			playerHandImages.add(cardDiningRoom);
    		}
    		if(i.equals(RoomType.KITCHEN)) {
    			playerHandImages.add(cardKitchen);
    		}
    		//Characters
    		if(i.equals(CharacterType.MISSSCARLETT)) {
    			playerHandImages.add(cardScarlett);
    		}
    		if(i.equals(CharacterType.COLONELMUSTARD)) {
    			playerHandImages.add(cardMustard);
    		}
    		if(i.equals(CharacterType.MRSWHITE)) {
    			playerHandImages.add(cardWhite);
    		}
    		if(i.equals(CharacterType.MRGREEN)) {
    			playerHandImages.add(cardGreen);
    		}
    		if(i.equals(CharacterType.MRSPEACOCK)) {
    			playerHandImages.add(cardPeacock);
    		}
    		if(i.equals(CharacterType.PROFESSORPLUM)) {
    			playerHandImages.add(cardPlum);
    		}
    	
    	}
    	}
    	
    	//draw cards on the window pane
    	for(Image i:playerHandImages) {
    		g.drawImage(i, x, y, null);
    		x = x + 85;
    		if(x>270) {
    			y = y + 110;
    			x = 580;
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
				if (board.checkPath(activePlayer, new Coord(x, y), roll)) {
					if (board.movePlayer(activePlayer, board.getCell(x, y))) {
						waitingForClick = false;
					} else {
						System.out.println("Unknown error occured. Failed to move.");
					}
				} else {
					System.out.println("Failed to find valid path to given point, please double check your roll.");
				}
			}
		}
		redraw();
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
	 * Implements the accuse strategy where it checks if the accused
	 * elements al match the murder set.
	 * */
	public boolean doAccusation(WeaponType w, RoomType r, CharacterType c) {

		//If player successfully guesses murder weapons right
		if(solutionCharacter.characters == c &&
				solutionWeapon.weapon == w && solutionRoom.rooms == r){
					return true;
				}
		//else game proceeds
		//current player is eliminated
		return false;
	}

	/**
	 * Implement the suggest strategy where it get currentPlayer's
	 * Room type and record the suggestion set.
	 * */
	public String doSuggestion(Board b, ArrayList <Player> playerList, RoomType suggestedRoom,
								CharacterType suggestedCharacter, WeaponType suggestedWeapon, JFrame frame){

		Player p = b.getCurrentPlayer();
		System.out.println(activePlayer.getPlayerName() + " has suggested " + suggestedRoom + ", " + suggestedCharacter + ", and " + suggestedWeapon + ".");
		//Check if any players want to refute
		for(Player pt: playerList){
			if (!pt.equals(activePlayer)) {
				String ref = refute(pt.myCardList, pt, suggestedRoom,
						suggestedCharacter, suggestedWeapon, frame);
				if(ref != null){
					return ref;
				}
			}
		}
		String message = "No one has refuted this suggestion";
		JOptionPane.showMessageDialog(null,message);
		return message;
	}

	/**
	 * This handles the refuting process when the opposing
	 * player wants to refuse the suggested set of the current player
	 * via getting a card in their own deck
	 *
	 * @param hand	the opposing players deck of cards
	 *
	 * @param room	suggested room
	 *
	 * @param character	suggested character
	 *
	 * @param weapon	suggested weapon
	 *
	 * @param frame		fame it is currently being displayed
	 *
	 * @return opposing player's name and its refuting card
	 *
	 * */
	private String refute(ArrayList<Card> hand, Player p, RoomType room,
						  CharacterType character, WeaponType weapon, JFrame frame){

		ArrayList<String> refuteList = new ArrayList<>();
		String refutedCard = "";
		boolean refuteCharacter = false;
		boolean refuteRoom = false;
		boolean refuteWeapon = false;

		//Check players hand if they have the suggested set/one of the suggested cards
		for(Card c: hand){
			if(c instanceof CharacterCard && ((CharacterCard)c).characters == character){
				refuteCharacter = true;
				refuteList.add(character.toString());
			}
			else if(c instanceof RoomCard && ((RoomCard)c).rooms == room){
				refuteRoom = true;
				refuteList.add(room.toString());
			}
			else if(c instanceof WeaponCard && ((WeaponCard)c).weapon == weapon){
				refuteWeapon = true;
				refuteList.add(weapon.toString());
			}
		}

		//Player cannot refute as they do not have any of the cards
		if(!(refuteCharacter || refuteRoom || refuteWeapon)){
			return null;
		}


		Object refuted = JOptionPane.showInputDialog(frame, "Select your refute:",
				this.toString() + ":", JOptionPane.DEFAULT_OPTION, null,
				refuteList.toArray(), "0");
		if(refuted != null){
			refutedCard = refuted.toString();
		}

		System.out.println("Player" + p.getPlayerName() + "has the " + refutedCard  + "card");
		
		return "Player" + p.getPlayerName() + "has the " + refutedCard  + "card";
	}

	

}
