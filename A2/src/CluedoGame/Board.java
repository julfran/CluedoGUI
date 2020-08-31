package CluedoGame;
import Cards.RoomType;
import Cards.WeaponType;
import Cells.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Board {
	public final int BOARD_WIDTH = 22;
	public final int BOARD_HEIGHT= 22;
	private Cell[][] cell = new Cell[22][22];
	private int numOfPlayers;
	private ArrayList <Player> players;
	private HashMap <CharacterType, Player> currentPlayers = new HashMap<CharacterType, Player>();
	private HashMap<RoomType, RoomCell> initCells= new HashMap<RoomType, RoomCell>();
	private HashMap<RoomType, List<DoorCell>> roomDoor = new HashMap<RoomType, List<DoorCell>>();
	private CharacterType currentPlayer;
	
	// Rooms
	private Room kitchen = new Room(RoomType.KITCHEN);;
	private Room diningRoom = new Room(RoomType.DININGROOM);
	private Room lounge = new Room(RoomType.LOUNGE);
	private Room hall = new Room(RoomType.HALL);
	private Room study = new Room(RoomType.STUDY);
	private Room library = new Room(RoomType.LIBRARY);
	private Room billiardRoom = new Room(RoomType.BILLIARDROOM);
	private Room conservatory = new Room(RoomType.CONSERVATORY);
	private Room ballroom = new Room(RoomType.BALLROOM);
	private ArrayList<Room> rooms;
	
	

	public Board(ArrayList<Player> players){

		this.players = players;
		
		this.rooms = new ArrayList<Room>();
		this.rooms.add(ballroom);
		this.rooms.add(conservatory);
		this.rooms.add(billiardRoom);
		this.rooms.add(library);
		this.rooms.add(study);
		this.rooms.add(hall);
		this.rooms.add(lounge);
		this.rooms.add(diningRoom);
		this.rooms.add(kitchen);
		
		String initialCellBoard =
					"KKKKKK...WAAA....CCCCC\n" +
					"KKKKKK..AAAAAAA..CCCCC\n"+
					"KKKKKK..AAAAAAA..3CCCC\n"+
					"KKKKKK..AAAAAAA...CCCE\n"+
					"KKKK1K..2AAAAA2.......\n"+
					"G.......A2AAA2A.......\n"+
					".................BBB5B\n"+
					"DDDDD............5BBBB\n"+
					"DDDDDDDD..|||||..BBBBB\n"+
					"DDDDDDD4..|||||..BBBBB\n"+
					"DDDDDDDD..|||||..BBBB5\n"+
					"DDDDDDDD..|||||.......\n"+
					"DDDDDDDD..|||||..LL6LL\n"+
					"DDDD4DDD..|||||.LLLLLL\n"+
					"M.........|||||.6LLLLL\n"+
					"................LLLLLL\n"+
					".........HH88HH..LLLLL\n"+
					"OOOOOO7..HHHHHH.......\n"+
					"OOOOOOO..HHHHH8......P\n"+
					"OOOOOOO..HHHHHH.S9SSSS\n"+
					"OOOOOOO..HHHHHH.SSSSSS\n"+
					"OOOOOOO.RHHHHHH.SSSSSS\n";
		//1 - Kitchen door
		//2 - Ballroom door
		//3 - Conservatory door
		//4 - Dining room door
		//5 - Billiard room door
		//6 - Library door
		//7 - Lounge door
		//8 - Hall door
		//9 - Study door

		int x = 0;
		int y = 0;
		for (char c : initialCellBoard.toCharArray()) {
			// Newline
			if (c== '\n') {
				x = 0;
				y ++;
			} else {
				//HallwayCell
				if(c == '.'){
					cell[x][y] = new HallwayCell(x,y);
				}
				// Hallway variants (player start locations)
				else if(c == 'W'){
					cell[x][y] = new HallwayCell(x,y);
					for (Player p : players) {
						if (p.getCharacterType() == CharacterType.MRSWHITE) {
							p.setCell(cell[x][y]);
						}
					}
				}
				else if(c == 'G'){
					cell[x][y] = new HallwayCell(x,y);
					for (Player p : players) {
						if (p.getCharacterType() == CharacterType.MRGREEN) {
							p.setCell(cell[x][y]);
						}
					}
				}
				else if(c == 'M'){
					cell[x][y] = new HallwayCell(x,y);
					for (Player p : players) {
						if (p.getCharacterType() == CharacterType.COLONELMUSTARD) {
							p.setCell(cell[x][y]);
						}
					}
				}
				else if(c == 'R'){
					cell[x][y] = new HallwayCell(x,y);
					for (Player p : players) {
						if (p.getCharacterType() == CharacterType.MISSSCARLETT) {
							p.setCell(cell[x][y]);
						}
					}
				}
				else if(c == 'P'){
					cell[x][y] = new HallwayCell(x,y);
					for (Player p : players) {
						if (p.getCharacterType() == CharacterType.PROFESSORPLUM) {
							p.setCell(cell[x][y]);
						}
					}
				}
				else if(c == 'E'){
					cell[x][y] = new HallwayCell(x,y);
					for (Player p : players) {
						if (p.getCharacterType() == CharacterType.MRSPEACOCK) {
							p.setCell(cell[x][y]);
						}
					}
				}
				//MidCell/MurderCell
				else if(c == '|'){
					cell[x][y] = new MurderCell(x,y);
				}
				// Various rooms
				else if(c == 'K'){
					cell[x][y] = new RoomCell(x,y,kitchen);
				}
				else if(c == 'A'){
					cell[x][y] = new RoomCell(x,y,ballroom);
				}
				else if(c == 'D'){
					cell[x][y] = new RoomCell(x,y,diningRoom);
				}
				else if(c == 'B'){
					cell[x][y] = new RoomCell(x,y,billiardRoom);
				}
				else if(c == 'L'){
					cell[x][y] = new RoomCell(x,y,library);
				}
				else if(c == 'O'){
					cell[x][y] = new RoomCell(x,y,lounge);
				}
				else if(c == 'H'){
					cell[x][y] = new RoomCell(x,y,hall);
				}
				else if(c == 'S'){
					cell[x][y] = new RoomCell(x,y,study);
				}
				else if(c == 'C'){
					cell[x][y] = new RoomCell(x,y,conservatory);
				}
				// Door variants
				else if (c == '1') {
					cell[x][y] = new RoomCell(x,y,kitchen, true);
					kitchen.addDoor((RoomCell) cell[x][y]);
				}
				else if (c == '2') {
					cell[x][y] = new RoomCell(x,y,ballroom, true);
					ballroom.addDoor((RoomCell) cell[x][y]);
				}
				else if (c == '3') {
					cell[x][y] = new RoomCell(x,y,conservatory, true);
					conservatory.addDoor((RoomCell) cell[x][y]);
				}
				else if (c == '4') {
					cell[x][y] = new RoomCell(x,y,diningRoom, true);
					diningRoom.addDoor((RoomCell) cell[x][y]);
				}
				else if (c == '5') {
					cell[x][y] = new RoomCell(x,y,billiardRoom, true);
					billiardRoom.addDoor((RoomCell) cell[x][y]);
				}
				else if (c == '6') {
					cell[x][y] = new RoomCell(x,y,library, true);
					library.addDoor((RoomCell) cell[x][y]);
				}
				else if (c == '7') {
					cell[x][y] = new RoomCell(x,y,lounge, true);
					lounge.addDoor((RoomCell) cell[x][y]);
				}
				else if (c == '8') {
					cell[x][y] = new RoomCell(x,y,hall, true);
					hall.addDoor((RoomCell) cell[x][y]);
				}
				else if (c == '9') {
					cell[x][y] = new RoomCell(x,y,study, true);
					study.addDoor((RoomCell) cell[x][y]);
				}
				x ++;
			}
		}
	}
	
	public void moveWeapon(WeaponType w, RoomType r) {
		for (Room room : rooms) {
			if (room.contains(w)) {
				room.remove(w);
			}
		}
		switch (r) {
		case BALLROOM:
			ballroom.addWeapon(w);
			break;
		case BILLIARDROOM:
			billiardRoom.addWeapon(w);
			break;
		case CONSERVATORY:
			conservatory.addWeapon(w);
			break;
		case DININGROOM:
			diningRoom.addWeapon(w);
			break;
		case HALL:
			hall.addWeapon(w);
			break;
		case KITCHEN:
			kitchen.addWeapon(w);
			break;
		case LIBRARY:
			library.addWeapon(w);
			break;
		case LOUNGE:
			lounge.addWeapon(w);
			break;
		case STUDY:
			study.addWeapon(w);
			break;
		default:
			break;}
	}
	
	public void moveCharacter(CharacterType c, RoomType r) {
		for (Room room : rooms) {
			if (room.contains(c)) {
				room.remove(c);
			}
		}
		switch (r) {
		case BALLROOM:
			ballroom.addCharacter(c);
			break;
		case BILLIARDROOM:
			billiardRoom.addCharacter(c);
			break;
		case CONSERVATORY:
			conservatory.addCharacter(c);
			break;
		case DININGROOM:
			diningRoom.addCharacter(c);
			break;
		case HALL:
			hall.addCharacter(c);
			break;
		case KITCHEN:
			kitchen.addCharacter(c);
			break;
		case LIBRARY:
			library.addCharacter(c);
			break;
		case LOUNGE:
			lounge.addCharacter(c);
			break;
		case STUDY:
			study.addCharacter(c);
			break;
		default:
			break;}
	}
	
	public void movePlayer(Player p, RoomType rt) {
		p.getCell().removePlayer();
		p.takeOutOfRoom();
		for (Room r : rooms) {
			if (r.getType() == rt) {
				cell[r.getCharacterCell(p.getCharacterType()).x()][r.getCharacterCell(p.getCharacterType()).y()].putPlayer(p);
				p.setCell(cell[r.getCharacterCell(p.getCharacterType()).x()][r.getCharacterCell(p.getCharacterType()).y()]);
				r.addPlayer(p);
				p.putInRoom(r);
				System.out.println(p.getPlayerName() + " got moved to the " + r.getType() + " by a suggestion or accusation.");
			}
		}
	}

	// Removes player from previous position and moves them to a new one
	// Returns true if the player moved
	// False if they didn't
	public boolean movePlayer(Player p, Cell c) {
		// TODO: Need to remove player from last location
		boolean wasInRoom = false;
		for (Room r : rooms) {
			if (r.contains(p)) {
				r.remove(p);
				wasInRoom = true;
			}
		}
		p.getCell().removePlayer();
		if (c.canTakePlayer()) {
			p.takeOutOfRoom();
			if (p.getCell() != null) {
				if (c.getClass() == RoomCell.class) {
					for (Room r : rooms) {
						if (r.getType() == ((RoomCell) c).getRoomType()) {
							cell[r.getCharacterCell(p.getCharacterType()).x()][r.getCharacterCell(p.getCharacterType()).y()].putPlayer(p);
							p.setCell(cell[r.getCharacterCell(p.getCharacterType()).x()][r.getCharacterCell(p.getCharacterType()).y()]);
							r.addPlayer(p);
							p.putInRoom(r);
							System.out.println("You are now in the " + r.getType() + ", you may now make suggestions.");
						}
					}
				}
				if (c.getClass() == DoorCell.class) {
					for (Room r : rooms) {
						if (r.getType() == ((DoorCell) c).getRoomDoor()) {
							cell[r.getCharacterCell(p.getCharacterType()).x()][r.getCharacterCell(p.getCharacterType()).y()].putPlayer(p);
							p.setCell(cell[r.getCharacterCell(p.getCharacterType()).x()][r.getCharacterCell(p.getCharacterType()).y()]);
							r.addPlayer(p);
							p.putInRoom(r);
						}
					}
				}
				if (c.getClass() == HallwayCell.class) {
					c.putPlayer(p);
					p.setCell(c);
					if (wasInRoom) {
						System.out.println("You are no longer in a room, so you can no longer make suggestions.");
					}
				}
			}
			return true;
		} else {
			return false;
		}
		
	}
	
	// finish this in the morning
	public boolean checkPath(Player movingPlayer, Coord coord, int roll) {
		// Calculate the actual start and end points of the move, after taking rooms into account
		Cell origin;
		if (movingPlayer.getCell().getClass() == RoomCell.class) {
			Room room = ((RoomCell) movingPlayer.getCell()).getRoom();
			RoomCell closestDoor = room.getDoors().get(0);
			int lowestDif = 9999;
			for (RoomCell d : room.getDoors()) {
				int xDif = Math.abs(d.getXPos() - coord.x());
				int yDif = Math.abs(d.getYPos() - coord.y());
				int totalDist = xDif + yDif;
				if (totalDist < lowestDif) {
					closestDoor = d;
				}
			}
			origin = closestDoor;
		} else {
			origin = movingPlayer.getCell();
		}
		Cell target = cell[coord.x()][coord.y()];
		if (target.getClass() == RoomCell.class) {
			Room room = ((RoomCell) target).getRoom();
			RoomCell closestDoor = room.getDoors().get(0);
			int lowestDif = 9999;
			for (RoomCell d : room.getDoors()) {
				int xDif = Math.abs(d.getXPos() - origin.getXPos());
				int yDif = Math.abs(d.getYPos() - origin.getYPos());
				int totalDist = xDif + yDif;
				if (totalDist < lowestDif) {
					closestDoor = d;
				}
			}
			target = closestDoor;
		}
		// Check if there's a valid path between the origin and target that's short enough to reach
		// Case: There's a secret passage between the origin and target
		if (target.getClass() == RoomCell.class && origin.getClass() == RoomCell.class) {
			// Kitchen to Study
			if (((RoomCell) origin).getRoomType() == RoomType.KITCHEN && ((RoomCell) target).getRoomType() == RoomType.STUDY) {
				return true;
			}
			// Study to Kitchen
			if (((RoomCell) target).getRoomType() == RoomType.KITCHEN && ((RoomCell) origin).getRoomType() == RoomType.STUDY) {
				return true;
			}
			// Conservatory to lounge
			if (((RoomCell) origin).getRoomType() == RoomType.CONSERVATORY && ((RoomCell) target).getRoomType() == RoomType.LOUNGE) {
				return true;
			}
			// Lounge to conservatory
			if (((RoomCell) target).getRoomType() == RoomType.CONSERVATORY && ((RoomCell) origin).getRoomType() == RoomType.LOUNGE) {
				return true;
			}
		}
		// Case: the target is not a valid cell
		if (target.getClass() == MurderCell.class) {
			return false;
		}
		// Case: the target is an occupied hallway cell
		if (target.getClass() == HallwayCell.class) {
			if (!target.canTakePlayer()) {
				return false;
			}
		}
		// Final case: pathfinding required
		Stack<Fringe> fringe = new Stack<Fringe>();
		Fringe root = new Fringe(origin, 0, null);
		fringe.add(root);
		while (!fringe.isEmpty()) {
			Fringe top = fringe.pop();
			if (top.dist <= roll) {
				if (top.cell.equals(target)) {
					return true;
				}
				if (top.cell.getXPos() > 0) {
					Cell left  = cell[top.cell.getXPos() - 1][top.cell.getYPos()];
					if (left != null && left.getClass() == HallwayCell.class) {
						fringe.push(new Fringe(left, top.dist + 1, top));
					} else if (left != null && left.getClass() == RoomCell.class && ((RoomCell) left).isDoor()) {
						fringe.push(new Fringe(left, top.dist + 1, top));
					}
				}
				if (top.cell.getXPos() < 21) {
					Cell right = cell[top.cell.getXPos() + 1][top.cell.getYPos()];
					if (right != null && right.getClass() == HallwayCell.class) {
						fringe.push(new Fringe(right, top.dist + 1, top));
					} else if (right != null && right.getClass() == RoomCell.class && ((RoomCell) right).isDoor()) {
						fringe.push(new Fringe(right, top.dist + 1, top));
					}
				}
				if (top.cell.getYPos() > 0) {
					Cell up = cell[top.cell.getXPos()][top.cell.getYPos() - 1];
					if (up != null && up.getClass() == HallwayCell.class) {
						fringe.push(new Fringe(up, top.dist + 1, top));
					} else if (up != null && up.getClass() == RoomCell.class && ((RoomCell) up).isDoor()) {
						fringe.push(new Fringe(up, top.dist + 1, top));
					}
				}
				if (top.cell.getYPos() < 21) {
					Cell down = cell[top.cell.getXPos()][top.cell.getYPos() + 1];
					if (down != null && down.getClass() == HallwayCell.class) {
						fringe.push(new Fringe(down, top.dist + 1, top));
					} else if (down != null && down.getClass() == RoomCell.class && ((RoomCell) down).isDoor()) {
						fringe.push(new Fringe(down, top.dist + 1, top));
					}
				}
			}
		}
		return false;
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

	



	//


}