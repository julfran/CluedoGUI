package CluedoGame;
import Cards.RoomType;
import Cells.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Board {
	public final int BOARD_WIDTH = 22;
	public final int BOARD_HEIGHT= 22;
	private Cell[][] cell = new Cell[22][22];
	private int numOfPlayers ;
	private ArrayList <Player> players;
	private HashMap <CharacterType, Player> currentPlayers = new HashMap<CharacterType, Player>();
	private HashMap<RoomType, RoomCell> initCells= new HashMap<RoomType, RoomCell>();
	private HashMap<RoomType, List<DoorCell>> roomDoor = new HashMap<RoomType, List<DoorCell>>();
	private CharacterType currentPlayer;
	private ArrayList<Room> rooms;
	
	

	public Board(ArrayList<Player> players){

		this.players = players;
		
		this.rooms = new ArrayList<Room>();
		this.rooms.add(new Room(RoomType.BALLROOM));
		this.rooms.add(new Room(RoomType.CONSERVATORY));
		this.rooms.add(new Room(RoomType.BILLIARDROOM));
		this.rooms.add(new Room(RoomType.LIBRARY));
		this.rooms.add(new Room(RoomType.STUDY));
		this.rooms.add(new Room(RoomType.HALL));
		this.rooms.add(new Room(RoomType.LOUNGE));
		this.rooms.add(new Room(RoomType.DININGROOM));
		this.rooms.add(new Room(RoomType.KITCHEN));
		
		String initialCellBoard =
					"KKKKKK...WAAA....CCCCC\n" +
					"KKKKKK..AAAAAAA..CCCCC\n"+
					"KKKKKK..AAAAAAA..+CCCC\n"+
					"KKKKKK..AAAAAAA...CCCE\n"+
					"KKKK+K..+AAAAA+.......\n"+
					"G.......A+AAA+A.......\n"+
					".................BBB+B\n"+
					"DDDDD............+BBBB\n"+
					"DDDDDDDD..+|||+..BBBBB\n"+
					"DDDDDDD+..|||||..BBBBB\n"+
					"DDDDDDDD..|||||..BBBB+\n"+
					"DDDDDDDD..|||||.......\n"+
					"DDDDDDDD..|||||..LL+LL\n"+
					"DDDD+DDD..|||||.LLLLLL\n"+
					"M.........|||||.+LLLLL\n"+
					"................LLLLLL\n"+
					".........HH++HH..LLLLL\n"+
					"OOOOOO+..HHHHHH.......\n"+
					"OOOOOOO..HHHHH+......P\n"+
					"OOOOOOO..HHHHHH.S+SSSS\n"+
					"OOOOOOO..HHHHHH.SSSSSS\n"+
					"OOOOOOO.RHHHHHH.SSSSSS\n";

		int x = 0;
		int y = 0;
		for (char c : initialCellBoard.toCharArray()) {
			// Newline
			if (c== '\n') {
				x = 0;
				y ++;
			} else {
				// DoorCell
				if(c == '+'){
					cell[x][y] = new DoorCell(x,y);
				}
				//HallwayCell
				else if(c == '.'){
					cell[x][y] = new HallwayCell(x,y);
				}
				// Hallway variants (player start locations)
				else if(c == 'W'){
					cell[x][y] = new HallwayCell(x,y);
					for (Player p : players) {
						if (p.getCharacterType() == CharacterType.MRSWHITE) {
							movePlayer(p, cell[x][y]);
						}
					}
				}
				else if(c == 'G'){
					cell[x][y] = new HallwayCell(x,y);
					for (Player p : players) {
						if (p.getCharacterType() == CharacterType.MRGREEN) {
							movePlayer(p, cell[x][y]);
						}
					}
				}
				else if(c == 'M'){
					cell[x][y] = new HallwayCell(x,y);
					for (Player p : players) {
						if (p.getCharacterType() == CharacterType.COLONELMUSTARD) {
							movePlayer(p, cell[x][y]);
						}
					}
				}
				else if(c == 'R'){
					cell[x][y] = new HallwayCell(x,y);
					for (Player p : players) {
						if (p.getCharacterType() == CharacterType.MISSSCARLETT) {
							movePlayer(p, cell[x][y]);
						}
					}
				}
				else if(c == 'P'){
					cell[x][y] = new HallwayCell(x,y);
					for (Player p : players) {
						if (p.getCharacterType() == CharacterType.PROFESSORPLUM) {
							movePlayer(p, cell[x][y]);
						}
					}
				}
				else if(c == 'E'){
					cell[x][y] = new HallwayCell(x,y);
					for (Player p : players) {
						if (p.getCharacterType() == CharacterType.MRSPEACOCK) {
							movePlayer(p, cell[x][y]);
						}
					}
				}
				//MidCell/MurderCell
				else if(c == '|'){
					cell[x][y] = new MurderCell(x,y);
				}
				// Various rooms
				else if(c == 'K'){
					cell[x][y] = new RoomCell(x,y,RoomType.KITCHEN);
				}
				else if(c == 'A'){
					cell[x][y] = new RoomCell(x,y,RoomType.CONSERVATORY);
				}
				else if(c == 'D'){
					cell[x][y] = new RoomCell(x,y,RoomType.DININGROOM);
				}
				else if(c == 'B'){
					cell[x][y] = new RoomCell(x,y,RoomType.BILLIARDROOM);
				}
				else if(c == 'L'){
					cell[x][y] = new RoomCell(x,y,RoomType.LIBRARY);
				}
				else if(c == 'O'){
					cell[x][y] = new RoomCell(x,y,RoomType.LOUNGE);
				}
				else if(c == 'H'){
					cell[x][y] = new RoomCell(x,y,RoomType.HALL);
				}
				else if(c == 'S'){
					cell[x][y] = new RoomCell(x,y,RoomType.STUDY);
				}
				x ++;
			}
		}
	}

	// Removes player from previous position and moves them to a new one
	// Returns true if the player moved
	// False if they didn't
	public boolean movePlayer(Player p, Cell c) {
		// TODO: Need to remove player from last location
		if (c.canTakePlayer()) {
			if (p.getCell() != null) {
				if (p.getCell().getClass() == RoomCell.class) {
					for (Room r : rooms) {
						if (r.type == ((RoomCell) c).getRoomType()) {
							cell[r.getCharacterCell(p.getCharacterType()).x()][r.getCharacterCell(p.getCharacterType()).y()].putPlayer(p);
							p.setCell(cell[r.getCharacterCell(p.getCharacterType()).x()][r.getCharacterCell(p.getCharacterType()).y()]);
							r.addPlayer(p);
						}
					}
				}
				if (p.getCell().getClass() == DoorCell.class) {
					for (Room r : rooms) {
						if (r.type == ((DoorCell) c).getRoomDoor()) {
							cell[r.getCharacterCell(p.getCharacterType()).x()][r.getCharacterCell(p.getCharacterType()).y()].putPlayer(p);
							p.setCell(cell[r.getCharacterCell(p.getCharacterType()).x()][r.getCharacterCell(p.getCharacterType()).y()]);
							r.addPlayer(p);
						}
					}
				}
				if (p.getCell().getClass() == HallwayCell.class) {
					c.putPlayer(p);
					p.setCell(c);
				}
			}
			return true;
		} else {
			return false;
		}
		
	}
	
	
	public ArrayList<Room> getRooms() {
		return rooms;
	}
	
	/**
	 * Get player that currently has its turn
	 * */
	public Player getCurrentPlayer(){
		return currentPlayers.get(currentPlayer);
	}

	/**
	 * Get any cell in a board with given
	 * x and y coordinates
	 * */
	public Cell getCell(int xPos, int yPos){
		return cell[xPos][yPos];
	}

	/**
	 * Sets up the board cell information
	 * Doors, Room cells, Murder Cell(unreachable)
	 * */
	private void setupBoardCell(){

	}

	/**
	 * getRoomDoors returns a list of doors for that room
	 * */
	public List<DoorCell> getRoomDoors (RoomType room){
		return Collections.unmodifiableList(roomDoor.get(room));
	}

	public boolean checkPath(Player movingPlayer, Coord coord, int roll) {
		Cell target = cell[coord.x()][coord.y()];
		return false;
	}



	//


}




//import java.util.ArrayList;
//import java.util.PriorityQueue;
//
//public class Board {
//	public Cell[][] board;
//	Room ballroom;
//	Room conservatory;
//	Room billiard;
//	Room library;
//	Room study;
//	Room hall;
//	Room lounge;
//	Room dining;
//	Room kitchen;
//	ArrayList<Room> rooms;
//
//	String initialCellBoard =
//
//					"........................\n" +
//					"KKKKKK....AAAA....CCCCCC\n" +
//					"KKKKKK..AAAAAAAA..CCCCCC\n" +
//					"KKKKKK..AAAAAAAA..CCCCCC\n"+
//					"KKKKKK..AAAAAAAA..+CCCCC\n"+
//					"KKKKKK..+AAAAAA+...CCCC.\n"+
//					".KKKK+..AAAAAAAA........\n"+
//					"........A+AAAA+A........\n"+
//					"..................BBBBBB\n"+
//					"DDDDDDDD..........+BBBBB\n"+
//					"DDDDDDDD..........BBBBBB\n"+
//					"DDDDDDD+..........BBBBBB\n"+
//					"DDDDDDDD..........BBBB+B\n"+
//					"DDDDDDDD................\n"+
//					"DDDDDDDD..........LL+LL.\n"+
//					"DDDDDD+D.........LLLLLLL\n"+
//					".................+LLLLLL\n"+
//					".................LLLLLLL\n"+
//					".........HH++HH...LLLLL.\n"+
//					"OOOOOO+..HHHHHH.........\n"+
//					"OOOOOOO..HHHHH+.........\n"+
//					"OOOOOOO..HHHHHH..+SSSSSS\n"+
//					"OOOOOOO..HHHHHH..SSSSSSS\n"+
//					"OOOOOOO..HHHHHH..SSSSSSS\n"+
//					"OOOOOO...HHHHHH...SSSSSS\n";
//
//	public final String draw() {
//		return null;
//	}
//
//	public Board() {
//		ballroom = new Room(RoomType.BALLROOM);
//		conservatory = new Room(RoomType.CONSERVATORY);
//		billiard = new Room(RoomType.BILLIARDROOM);
//		library = new Room(RoomType.LIBRARY);
//		study = new Room(RoomType.STUDY);
//		hall = new Room(RoomType.HALL);
//		lounge = new Room(RoomType.LOUNGE);
//		dining = new Room(RoomType.DININGROOM);
//		kitchen = new Room(RoomType.KITCHEN);
//		rooms = new ArrayList<Room>();
//		rooms.add(ballroom);
//		rooms.add(conservatory);
//		rooms.add(billiard);
//		rooms.add(library);
//		rooms.add(study);
//		rooms.add(hall);
//		rooms.add(lounge);
//		rooms.add(dining);
//		rooms.add(kitchen);
//		board = parseBoard(initialCellBoard);
//		int x = 0;
//		int y = 0;
//		while (y < 25) {
//			x = 0;
//			while (x < 24) {
//				x++;		//MAKE SURE TO
//				if (board[x][y].getClass() == DoorCell.class) {
//					Cell door = board[x][y];
//					RoomType room = RoomType.HALL;
//					if (board[x-1][y].getClass() == RoomCell.class) {
//						room = board[x-1][y].getType();
//					}
//					if (board[x+1][y].getClass() == RoomCell.class) {
//						room = board[x+1][y].getType();
//					}
//					if (board[x][y-1].getClass() == RoomCell.class) {
//						room = board[x][y-1].getType();
//					}
//					if (board[x][y+1].getClass() == RoomCell.class) {
//						room = board[x][y+1].getType();
//					}
//					if (room == RoomType.BALLROOM) {
//						ballroom.addDoor((DoorCell) door);
//					}
//					if (room == RoomType.CONSERVATORY) {
//						conservatory.addDoor((DoorCell) door);
//					}
//					if (room == RoomType.BILLIARDROOM) {
//						billiard.addDoor((DoorCell) door);
//					}
//					if (room == RoomType.LIBRARY) {
//						library.addDoor((DoorCell) door);
//					}
//					if (room == RoomType.STUDY) {
//						study.addDoor((DoorCell) door);
//					}
//					if (room == RoomType.HALL) {
//						hall.addDoor((DoorCell) door);
//					}
//					if (room == RoomType.LOUNGE) {
//						lounge.addDoor((DoorCell) door);
//					}
//					if (room == RoomType.DININGROOM) {
//						dining.addDoor((DoorCell) door);
//					}
//					if (room == RoomType.KITCHEN) {
//						kitchen.addDoor((DoorCell) door);
//					}
//				}
//				x ++;
//			}
//			y ++;
//		}
//
//	}
//
//	// Parser code:
//	//
//	// A = Ballroom
//	// C = Conservatory
//	// B = Billiard Room
//	// L = Library
//	// S = Study
//	// H = Hall
//	// O = Lounge
//	// D = Dining Room
//	// K = Kitchen
//	// U = Cellar
//	// . = Hallways
//	// + = Door
//	// \n = Newline
//
//	public Cell[][] parseBoard(String textBoard) {
//		char[] text = textBoard.toCharArray();
//		Cell board[][] = new Cell[25][26];
//		int x = 0;
//		int y = 0;
//		for (char c : text) {
//			if (c == '\n') {
//				y ++;
//				x = 0;
//			}
//			board[x][y] = parseCell(c, x, y);
//			x ++;
//		}
//		return board;
//	}
//
//	public Cell parseCell(char cell, int x, int y) {
//		if (cell == 'A') {
//			return parseBallroom(x, y);
//		}
//		if (cell == 'C') {
//			return parseConservatory(x, y);
//		}
//		if (cell == 'B') {
//			return parseBilliard(x, y);
//		}
//		if (cell == 'L') {
//			return parseLibrary(x, y);
//		}
//		if (cell == 'S') {
//			return parseStudy(x, y);
//		}
//		if (cell == 'H') {
//			return parseHall(x, y);
//		}
//		if (cell == 'O') {
//			return parseLounge(x, y);
//		}
//		if (cell == 'D') {
//			return parseDining(x, y);
//		}
//		if (cell == 'K') {
//			return parseKitchen(x, y);
//		}
//		if (cell == 'U') {
//			return parseCellar(x, y);
//		}
//		if (cell == '.') {
//			return parseHallway(x, y);
//		}
//		if (cell == '+') {
//			return parseDoor(x, y);
//		}
//		return null;
//	}
//
//	private Cell parseDoor(int x, int y) {
//		return new DoorCell(x, y);
//	}
//
//	private Cell parseHallway(int x, int y) {
//		return new HallwayCell(x, y);
//	}
//
//	private Cell parseKitchen(int x, int y) {
//		return new RoomCell(x, y, RoomType.KITCHEN, this);
//	}
//
//	private Cell parseDining(int x, int y) {
//		return new RoomCell(x, y, RoomType.DININGROOM, this);
//	}
//
//	private Cell parseLounge(int x, int y) {
//		return new RoomCell(x, y, RoomType.LOUNGE, this);
//	}
//
//	private Cell parseHall(int x, int y) {
//		return new RoomCell(x, y, RoomType.HALL, this);
//	}
//
//	private Cell parseStudy(int x, int y) {
//		return new RoomCell(x, y, RoomType.STUDY, this);
//	}
//
//	private Cell parseLibrary(int x, int y) {
//		return new RoomCell(x, y, RoomType.LIBRARY, this);
//	}
//
//	private Cell parseBilliard(int x, int y) {
//		return new RoomCell(x, y, RoomType.BILLIARDROOM, this);
//	}
//
//	private Cell parseConservatory(int x, int y) {
//		return new RoomCell(x, y, RoomType.CONSERVATORY, this);
//	}
//
//	private Cell parseBallroom(int x, int y) {
//		return new RoomCell(x, y, RoomType.BALLROOM, this);
//	}
//
//	private Cell parseCellar(int x, int y) {
//		return new XCell(x, y);
//	}
//
//	public Cell getCell(int x, int y) {
//		return board[x][y];
//	}
//
//	// Checks if a player can make their desired move
//	// player is the player wishing to move. Their position on the board is taken from this input
//	// finish is the cell they wish to end their move on
//	// roll is the number they rolled on the dice
//	public Boolean canMove(Player player, Cell finish, int roll) {
//		// Step one: Determine the player's starting cell
//		// Default case: hallway
//		Cell pos = player.getCell();
//		// Alt: Room
//		if (player.getRoom() != null) {
//			// Step 1: Figure out which room
//			RoomType room = player.getRoom();
//			// Step 2: Determine which door to leave from
//			pos = closestDoor(room, finish);
//		}
//		// Step Two: Determine the actual finish cell
//		// Default case: Hallway
//		Cell target = finish;
//		// Alt: Room/Door
//		if (finish.getClass() != HallwayCell.class) {
//			// Choose the closest door to the chosen room
//			target = closestDoor(finish.getType(), pos);
//		}
//		// Step Three: find path
//		// Best case scenario: corner room to opposing corner room
//		// Checked here since it counts as a whole move, so the player's dice roll can't have had any affect yet
//		if (pos.getClass() == RoomCell.class || pos.getClass() == DoorCell.class) {
//			if (target.getClass() == RoomCell.class || target.getClass() == DoorCell.class) {
//				if (pos.getType() == RoomType.KITCHEN && target.getType() == RoomType.LIBRARY) {
//					return true;
//				}
//				if (target.getType() == RoomType.KITCHEN && pos.getType() == RoomType.LIBRARY) {
//					return true;
//				}
//				if (pos.getType() == RoomType.STUDY && target.getType() == RoomType.BILLIARDROOM) {
//					return true;
//				}
//				if (target.getType() == RoomType.STUDY && pos.getType() == RoomType.BILLIARDROOM) {
//					return true;
//				}
//			}
//		}
//		// Alt: Target cell is an occupied Hallway Cell
//		if (target.getClass() == HallwayCell.class && target.getPlayer() != null) {
//			return false;
//		}
//		// Alt: Path finding
//		// Fringe is a priority queue based on the number of steps taken
//		PriorityQueue<Fringe> fringe = new PriorityQueue<Fringe>();
//		fringe.add(new Fringe(pos));
//		while (!fringe.isEmpty()) {
//			Cell current = fringe.peek().cell;
//			// Expand the fringe by looking for movable cells in the four cardinal directions
//			// x - 1
//			if (current.getX() > 0) {
//				if (board[current.getX() - 1][current.getY()].equals(target)) {
//					if (fringe.peek().steps + 1 <= roll) {
//						return true;
//					}
//				}
//				if (board[current.getX() - 1][current.getY()].getClass() == HallwayCell.class) {
//					fringe.add(new Fringe(fringe.peek(), board[current.getX() - 1][current.getY()]));
//				}
//
//			}
//			// x + 1
//			if (current.getX() < 24) {
//				if (board[current.getX() + 1][current.getY()].equals(target)) {
//					if (fringe.peek().steps + 1 <= roll) {
//						return true;
//					}
//				}
//				if (board[current.getX() + 1][current.getY()].getClass() == HallwayCell.class) {
//					fringe.add(new Fringe(fringe.peek(), board[current.getX() + 1][current.getY()]));
//				}
//
//			}
//			// y - 1
//			if (current.getY() > 0) {
//				if (board[current.getX()][current.getY() - 1].equals(target)) {
//					if (fringe.peek().steps + 1 <= roll) {
//						return true;
//					}
//				}
//				if (board[current.getX()][current.getY() - 1].getClass() == HallwayCell.class) {
//					fringe.add(new Fringe(fringe.peek(), board[current.getX()][current.getY() - 1]));
//				}
//
//			}
//			// y + 1
//			if (current.getY() < 23) {
//				if (board[current.getX()][current.getY() + 1].equals(target)) {
//					if (fringe.peek().steps + 1 <= roll) {
//						return true;
//					}
//				}
//				if (board[current.getX()][current.getY() + 1].getClass() == HallwayCell.class) {
//					fringe.add(new Fringe(fringe.peek(), board[current.getX()][current.getY() + 1]));
//				}
//
//			}
//			fringe.poll();
//		}
//		// Went through entire fringe without finding the target, move is invalid
//		return false;
//	}
//
//	// Should contain the coords of the doors of each room, and calculates which of those is closer to the given target cell
//	public Cell closestDoor(RoomType room, Cell target) {
//		int lowestDirectCost = 999999; //Higher than possible
//		DoorCell closest = null;
//		if (room == RoomType.BALLROOM) {
//			for (DoorCell c : ballroom.getDoors()) {
//				int cost = Math.abs(c.getX() - target.getX()) + Math.abs(c.getY() - target.getY());
//				if (cost < lowestDirectCost) {
//					lowestDirectCost = cost;
//					closest = c;
//				}
//			}
//		}
//		if (room == RoomType.CONSERVATORY) {
//			for (DoorCell c : conservatory.getDoors()) {
//				int cost = Math.abs(c.getX() - target.getX()) + Math.abs(c.getY() - target.getY());
//				if (cost < lowestDirectCost) {
//					lowestDirectCost = cost;
//					closest = c;
//				}
//			}
//		}
//		if (room == RoomType.BILLIARDROOM) {
//			for (DoorCell c : billiard.getDoors()) {
//				int cost = Math.abs(c.getX() - target.getX()) + Math.abs(c.getY() - target.getY());
//				if (cost < lowestDirectCost) {
//					lowestDirectCost = cost;
//					closest = c;
//				}
//			}
//		}
//		if (room == RoomType.LIBRARY) {
//			for (DoorCell c : library.getDoors()) {
//				int cost = Math.abs(c.getX() - target.getX()) + Math.abs(c.getY() - target.getY());
//				if (cost < lowestDirectCost) {
//					lowestDirectCost = cost;
//					closest = c;
//				}
//			}
//		}
//		if (room == RoomType.STUDY) {
//			for (DoorCell c : study.getDoors()) {
//				int cost = Math.abs(c.getX() - target.getX()) + Math.abs(c.getY() - target.getY());
//				if (cost < lowestDirectCost) {
//					lowestDirectCost = cost;
//					closest = c;
//				}
//			}
//		}
//		if (room == RoomType.HALL) {
//			for (DoorCell c : hall.getDoors()) {
//				int cost = Math.abs(c.getX() - target.getX()) + Math.abs(c.getY() - target.getY());
//				if (cost < lowestDirectCost) {
//					lowestDirectCost = cost;
//					closest = c;
//				}
//			}
//		}
//		if (room == RoomType.LOUNGE) {
//			for (DoorCell c : lounge.getDoors()) {
//				int cost = Math.abs(c.getX() - target.getX()) + Math.abs(c.getY() - target.getY());
//				if (cost < lowestDirectCost) {
//					lowestDirectCost = cost;
//					closest = c;
//				}
//			}
//		}
//		if (room == RoomType.DININGROOM) {
//			for (DoorCell c : dining.getDoors()) {
//				int cost = Math.abs(c.getX() - target.getX()) + Math.abs(c.getY() - target.getY());
//				if (cost < lowestDirectCost) {
//					lowestDirectCost = cost;
//					closest = c;
//				}
//			}
//		}
//		if (room == RoomType.KITCHEN) {
//			for (DoorCell c : kitchen.getDoors()) {
//				int cost = Math.abs(c.getX() - target.getX()) + Math.abs(c.getY() - target.getY());
//				if (cost < lowestDirectCost) {
//					lowestDirectCost = cost;
//					closest = c;
//				}
//			}
//		}
//		return closest;
//	}
//
//	public void moveCharacter(CharacterType c, RoomType r) {
//		for (Room room : rooms) {
//			room.removeCharacter(c);
//		}
//		if (r == RoomType.BALLROOM) {
//			ballroom.addCharacter(c);
//		}
//		if (r == RoomType.CONSERVATORY) {
//			conservatory.addCharacter(c);
//		}
//		if (r == RoomType.BILLIARDROOM) {
//			billiard.addCharacter(c);
//		}
//		if (r == RoomType.LIBRARY) {
//			library.addCharacter(c);
//		}
//		if (r == RoomType.STUDY) {
//			study.addCharacter(c);
//		}
//		if (r == RoomType.HALL) {
//			hall.addCharacter(c);
//		}
//		if (r == RoomType.LOUNGE) {
//			lounge.addCharacter(c);
//		}
//		if (r == RoomType.DININGROOM) {
//			dining.addCharacter(c);
//		}
//		if (r == RoomType.KITCHEN) {
//			kitchen.addCharacter(c);
//		}
//	}
//
//	public void movePlayer(Player p, RoomType r) {
//		// Remove the player from any room they may already be in
//		if (p.getRoom() == RoomType.BALLROOM) {
//			ballroom.removePlayer(p);
//		}
//		if (p.getRoom() == RoomType.CONSERVATORY) {
//			conservatory.removePlayer(p);
//		}
//		if (p.getRoom() == RoomType.BILLIARDROOM) {
//			billiard.removePlayer(p);
//		}
//		if (p.getRoom() == RoomType.LIBRARY) {
//			library.removePlayer(p);
//		}
//		if (p.getRoom() == RoomType.STUDY) {
//			study.removePlayer(p);
//		}
//		if (p.getRoom() == RoomType.HALL) {
//			hall.removePlayer(p);
//		}
//		if (p.getRoom() == RoomType.LOUNGE) {
//			lounge.removePlayer(p);
//		}
//		if (p.getRoom() == RoomType.DININGROOM) {
//			dining.removePlayer(p);
//		}
//		if (p.getRoom() == RoomType.KITCHEN) {
//			kitchen.removePlayer(p);
//		}
//		// Add the player to their new room
//		if (r == RoomType.BALLROOM) {
//			ballroom.addPlayer(p);
//			p.move(ballroom.getCells().get(0));
//		}
//		if (r == RoomType.CONSERVATORY) {
//			conservatory.addPlayer(p);
//			p.move(conservatory.getCells().get(0));
//		}
//		if (r == RoomType.BILLIARDROOM) {
//			billiard.addPlayer(p);
//			p.move(billiard.getCells().get(0));
//		}
//		if (r == RoomType.LIBRARY) {
//			library.addPlayer(p);
//			p.move(library.getCells().get(0));
//		}
//		if (r == RoomType.STUDY) {
//			study.addPlayer(p);
//			p.move(study.getCells().get(0));
//		}
//		if (r == RoomType.HALL) {
//			hall.addPlayer(p);
//			p.move(hall.getCells().get(0));
//		}
//		if (r == RoomType.LOUNGE) {
//			lounge.addPlayer(p);
//			p.move(lounge.getCells().get(0));
//		}
//		if (r == RoomType.DININGROOM) {
//			dining.addPlayer(p);
//			p.move(dining.getCells().get(0));
//		}
//		if (r == RoomType.KITCHEN) {
//			kitchen.addPlayer(p);
//			p.move(kitchen.getCells().get(0));
//		}
//	}
//
//	public void movePlayer(Player p, Cell c) {
//		// Remove the player from any room they may already be in
//		if (p.getRoom() == RoomType.BALLROOM) {
//			ballroom.removePlayer(p);
//		}
//		if (p.getRoom() == RoomType.CONSERVATORY) {
//			conservatory.removePlayer(p);
//		}
//		if (p.getRoom() == RoomType.BILLIARDROOM) {
//			billiard.removePlayer(p);
//		}
//		if (p.getRoom() == RoomType.LIBRARY) {
//			library.removePlayer(p);
//		}
//		if (p.getRoom() == RoomType.STUDY) {
//			study.removePlayer(p);
//		}
//		if (p.getRoom() == RoomType.HALL) {
//			hall.removePlayer(p);
//		}
//		if (p.getRoom() == RoomType.LOUNGE) {
//			lounge.removePlayer(p);
//		}
//		if (p.getRoom() == RoomType.DININGROOM) {
//			dining.removePlayer(p);
//		}
//		if (p.getRoom() == RoomType.KITCHEN) {
//			kitchen.removePlayer(p);
//		}
//		// Move the player
//		p.move(c);
//		// If they're in a room now, add them to it
//		if (c.getClass() == RoomCell.class || c.getClass() == DoorCell.class) {
//			if (c.getType() == RoomType.BALLROOM) {
//				ballroom.addPlayer(p);
//			}
//			if (c.getType() == RoomType.CONSERVATORY) {
//				conservatory.addPlayer(p);
//			}
//			if (c.getType() == RoomType.BILLIARDROOM) {
//				billiard.addPlayer(p);
//			}
//			if (c.getType() == RoomType.LIBRARY) {
//				library.addPlayer(p);
//			}
//			if (c.getType() == RoomType.STUDY) {
//				study.addPlayer(p);
//			}
//			if (c.getType() == RoomType.HALL) {
//				hall.addPlayer(p);
//			}
//			if (c.getType() == RoomType.LOUNGE) {
//				lounge.addPlayer(p);
//			}
//			if (c.getType() == RoomType.DININGROOM) {
//				dining.addPlayer(p);
//			}
//			if (c.getType() == RoomType.KITCHEN) {
//				kitchen.addPlayer(p);
//			}
//		} else {
//			((HallwayCell) c).placePlayer(p);
//		}
//	}
//	/**
//	 * toString method to print out board text
//	 **/
//	public String toString() {
//		String base = "........................\n" +//done
//				"KKKKKK....AAAA....CCCCCC\n" +//done
//				"KKKKKK..AAAAAAAA..CCCCCC\n" +//done
//				"KKKKKK..AAAAAAAA..CCCCCC\n"+//done
//				"KKKKKK..AAAAAAAA..+CCCCC\n"+//done
//				"KKKKKK..+AAAAAA+...CCCC.\n"+//done
//				".KKKK+..AAAAAAAA........\n"+//done
//				"........A+AAAA+A........\n"+//done
//				"..................BBBBBB\n"+//done
//				"DDDDDDDD..........+BBBBB\n"+//done
//				"DDDDDDDD..........BBBBBB\n"+//done
//				"DDDDDDD+..........BBBBBB\n"+//done
//				"DDDDDDDD..........BBBB+B\n"+
//				"DDDDDDDD................\n"+
//				"DDDDDDDD..........LL+LL.\n"+
//				"DDDDDD+D.........LLLLLLL\n"+
//				".................+LLLLLL\n"+
//				".................LLLLLLL\n"+
//				".........HH++HH...LLLLL.\n"+
//				"OOOOOO+..HHHHHH.........\n"+
//				"OOOOOOO..HHHHH+.........\n"+
//				"OOOOOOO..HHHHHH..+SSSSSS\n"+
//				"OOOOOOO..HHHHHH..SSSSSSS\n"+
//				"OOOOOOO..HHHHHH..SSSSSSS\n"+
//				"OOOOOO...HHHHHH...SSSSSS\n";
//		StringBuilder output = new StringBuilder();
//		// Board row 1
//		int x = 0;
//		while (x < 24) {
//			output.append(board[x][0].toString());
//			x ++;
//		}
//		output.append("\n");
//		// Board row 2
//		output.append("KKKKKK");
//		x = 6;
//		while (x < 10) {
//			output.append(board[x][1].toString());
//			x ++;
//		}
//		output.append("AAAA");
//		x = 14;
//		while (x < 18) {
//			output.append(board[x][1].toString());
//			x ++;
//		}
//		output.append("CCCCCC\n");
//		// Board row 3
//		output.append(kitchen.toString(2));
//		x = 6;
//		while (x < 8) {
//			output.append(board[x][2].toString());
//			x ++;
//		}
//		output.append(ballroom.toString(2));
//		x = 16;
//		while (x < 18) {
//			output.append(board[x][2].toString());
//			x ++;
//		}
//		output.append(conservatory.toString(2));
//		output.append("\n");
//		// Board row 4
//		output.append(kitchen.toString(3));
//		x = 6;
//		while (x < 8) {
//			output.append(board[x][3].toString());
//			x ++;
//		}
//		output.append(ballroom.toString(3));
//		x = 16;
//		while (x < 18) {
//			output.append(board[x][3].toString());
//			x ++;
//		}
//		output.append(conservatory.toString(3));
//		output.append("\n");
//		// Board row 5
//		output.append("K    K");
//		x = 6;
//		while (x < 8) {
//			output.append(board[x][4].toString());
//			x ++;
//		}
//		output.append("A      A");
//		x = 16;
//		while (x < 18) {
//			output.append(board[x][4].toString());
//			x ++;
//		}
//		output.append("+    C\n");
//		// Board row 6
//		output.append("K    K");
//		x = 6;
//		while (x < 8) {
//			output.append(board[x][5].toString());
//			x ++;
//		}
//		output.append("+      +");
//		x = 16;
//		while (x < 19) {
//			output.append(board[x][5].toString());
//			x ++;
//		}
//		output.append("CCCC");
//		output.append(board[23][5].toString());
//		output.append("\n");
//		// Board row 7
//		output.append(board[0][6].toString());
//		output.append("KKKK+");
//		x = 6;
//		while (x < 8) {
//			output.append(board[x][6].toString());
//			x ++;
//		}
//		output.append("A      A");
//		x = 16;
//		while (x < 24) {
//			output.append(board[x][6].toString());
//			x ++;
//		}
//		output.append("\n");
//		// Board row 8
//		x = 0;
//		while (x < 8) {
//			output.append(board[x][7].toString());
//			x ++;
//		}
//		output.append("A+AAAA+A");
//		x = 16;
//		while (x < 24) {
//			output.append(board[x][7].toString());
//			x ++;
//		}
//		output.append("\n");
//		// Board row 9
//		x = 0;
//		while (x < 18) {
//			output.append(board[x][8].toString());
//			x ++;
//		}
//		output.append("BBBBBB\n");
//		// Board row 10
//		output.append("DDDDDDDD");
//		x = 8;
//		while (x < 18) {
//			output.append(board[x][9].toString());
//			x ++;
//		}
//		output.append("+    B");
//		output.append("\n");
//		// Board row 11
//		output.append("D      D");
//		x = 8;
//		while (x < 18) {
//			output.append(board[x][10].toString());
//			x ++;
//		}
//		output.append("B    B");
//		output.append("\n");
//		// Board row 12
//		output.append("D      +");
//		x = 8;
//		while (x < 18) {
//			output.append(board[x][11].toString());
//			x ++;
//		}
//		output.append("B    B");
//		output.append("\n");
//		// Board row 13
//		output.append("D      D");
//		x = 8;
//		while (x < 18) {
//			output.append(board[x][12].toString());
//			x ++;
//		}
//		output.append("BBBB+B");
//		output.append("\n");
//		// Board row 14
//		output.append("D      D");
//		x = 8;
//		while (x < 24) {
//			output.append(board[x][13].toString());
//			x ++;
//		}
//		output.append("\n");
//		// Board row 15
//		output.append("D      D");
//		x = 8;
//		while (x < 18) {
//			output.append(board[x][14].toString());
//			x ++;
//		}
//		output.append("LL+LL");
//		output.append(board[23][14].toString());
//		output.append("\n");
//		// Board row 16
//		output.append("DDDDDD+D");
//		x = 8;
//		while (x < 17) {
//			output.append(board[x][15].toString());
//			x ++;
//		}
//		output.append("L     L");
//		output.append("\n");
//		// Board row 17
//		x = 0;
//		while (x < 17) {
//			output.append(board[x][16].toString());
//			x ++;
//		}
//		output.append("+     L");
//		output.append("\n");
//		// Board row 18
//		x = 0;
//		while (x < 17) {
//			output.append(board[x][17].toString());
//			x ++;
//		}
//		output.append("L     L");
//		output.append("\n");
//		// Board row 19
//		x = 0;
//		while (x < 9) {
//			output.append(board[x][18].toString());
//			x ++;
//		}
//		output.append("HH++HH");
//		x = 15;
//		while (x < 18) {
//			output.append(board[x][18].toString());
//			x ++;
//		}
//		output.append("LLLLL");
//		output.append(board[x][24].toString());
//		output.append("\n");
//		// Board row 20
//		output.append("OOOOOO+");
//		output.append(board[8][19].toString());
//		output.append(board[9][19].toString());
//		output.append("H    H");
//		x = 15;
//		while (x < 24) {
//			output.append(board[x][19].toString());
//			x ++;
//		}
//		output.append("\n");
//		// Board row 21
//		output.append("O     O");
//		output.append(board[8][20].toString());
//		output.append(board[9][20].toString());
//		output.append("H    +");
//		x = 15;
//		while (x < 24) {
//			output.append(board[x][20].toString());
//			x ++;
//		}
//		output.append("\n");
//		// Board row 22
//		output.append("O     O");
//		output.append(board[8][21].toString());
//		output.append(board[9][21].toString());
//		output.append("H    H");
//		x = 15;
//		while (x < 17) {
//			output.append(board[x][21].toString());
//			x ++;
//		}
//		output.append("+SSSSSS");
//		output.append("\n");
//		// Board row 23
//		output.append("O     O");
//		output.append(board[8][22].toString());
//		output.append(board[9][22].toString());
//		output.append("H    H");
//		x = 15;
//		while (x < 17) {
//			output.append(board[x][22].toString());
//			x ++;
//		}
//		output.append("S     S");
//		output.append("\n");
//		// Board row 24
//		output.append("O     O");
//		output.append(board[8][23].toString());
//		output.append(board[9][23].toString());
//		output.append("H    H");
//		x = 15;
//		while (x < 17) {
//			output.append(board[x][23].toString());
//			x ++;
//		}
//		output.append("S     S");
//		output.append("\n");
//		// Board row 25
//		output.append("OOOOOO");
//		x = 6;
//		while (x < 9) {
//			output.append(board[x][24].toString());
//			x ++;
//		}
//		output.append("HHHHHH");
//		x = 15;
//		while (x < 18) {
//			output.append(board[x][24].toString());
//			x ++;
//		}
//		output.append("SSSSSS");
//
//		return output.toString();
//
//	}
//
//}
