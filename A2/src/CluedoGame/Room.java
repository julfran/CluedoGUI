package CluedoGame;

import java.util.ArrayList;

import Cards.RoomType;
import Cards.WeaponType;
import Cells.Cell;
import Cells.RoomCell;

public class Room {
	
	RoomType type;
	ArrayList<WeaponType> weapons;
	ArrayList<Player> players;
	ArrayList<CharacterType> npcs;
	
	public Room(RoomType type) {
		this.type = type;
		weapons = new ArrayList<WeaponType>();
		players = new ArrayList<Player>();
		npcs = new ArrayList<CharacterType>();
	}
	
	public void addPlayer(Player p) {
		players.add(p);
	}
	
	// Returns true if the room contains the given weapon
	public boolean contains(WeaponType w) {
		if (weapons.contains(w)) {
			return true;
		} else {
			return false;
		}
	}
	
	// Returns true if the room contains the given npc
	public boolean contains(CharacterType c) {
		if (npcs.contains(c)) {
			return true;
		} else {
			return false;
		}
	}
	
	// Returns the coordinates where a given character should be drawn in the room
	public Coord getCharacterCell(CharacterType character) {
		switch (type) {
			case BALLROOM:
				switch (character) {
					case COLONELMUSTARD:
						return new Coord(8, 1);
					case MISSSCARLETT:
						return new Coord(8, 2);
					case MRGREEN:
						return new Coord(8, 3);
					case MRSPEACOCK:
						return new Coord(8, 4);
					case MRSWHITE:
						return new Coord(8, 5);
					case PROFESSORPLUM:
						return new Coord(9, 1);
					default:
						return null;
				}
			case BILLIARDROOM:
				switch (character) {
					case COLONELMUSTARD:
						return new Coord(17, 6);
					case MISSSCARLETT:
						return new Coord(17, 7);
					case MRGREEN:
						return new Coord(17, 8);
					case MRSPEACOCK:
						return new Coord(17, 9);
					case MRSWHITE:
						return new Coord(17, 10);
					case PROFESSORPLUM:
						return new Coord(18, 6);
					default:
						return null;
				}
			case CONSERVATORY:
				switch (character) {
					case COLONELMUSTARD:
						return new Coord(17, 0);
					case MISSSCARLETT:
						return new Coord(17, 1);
					case MRGREEN:
						return new Coord(17, 2);
					case MRSPEACOCK:
						return new Coord(18, 0);
					case MRSWHITE:
						return new Coord(18, 1);
					case PROFESSORPLUM:
						return new Coord(18, 2);
					default:
						return null;
				}
			case DININGROOM:
				switch (character) {
					case COLONELMUSTARD:
						return new Coord(0, 7);
					case MISSSCARLETT:
						return new Coord(0, 8);
					case MRGREEN:
						return new Coord(0, 9);
					case MRSPEACOCK:
						return new Coord(0, 10);
					case MRSWHITE:
						return new Coord(0, 11);
					case PROFESSORPLUM:
						return new Coord(0, 12);
					default:
						return null;
				}
			case HALL:
				switch (character) {
					case COLONELMUSTARD:
						return new Coord(9, 16);
					case MISSSCARLETT:
						return new Coord(9, 17);
					case MRGREEN:
						return new Coord(9, 18);
					case MRSPEACOCK:
						return new Coord(9, 19);
					case MRSWHITE:
						return new Coord(9, 20);
					case PROFESSORPLUM:
						return new Coord(9, 21);
					default:
						return null;
				}
			case KITCHEN:
				switch (character) {
					case COLONELMUSTARD:
						return new Coord(0, 0);
					case MISSSCARLETT:
						return new Coord(0, 1);
					case MRGREEN:
						return new Coord(0, 2);
					case MRSPEACOCK:
						return new Coord(0, 3);
					case MRSWHITE:
						return new Coord(0, 4);
					case PROFESSORPLUM:
						return new Coord(1, 0);
					default:
						return null;
				}
			case LIBRARY:
				switch (character) {
					case COLONELMUSTARD:
						return new Coord(16, 13);
					case MISSSCARLETT:
						return new Coord(16, 14);
					case MRGREEN:
						return new Coord(16, 15);
					case MRSPEACOCK:
						return new Coord(17, 12);
					case MRSWHITE:
						return new Coord(17, 13);
					case PROFESSORPLUM:
						return new Coord(17, 14);
					default:
						return null;
				}
			case LOUNGE:
				switch (character) {
					case COLONELMUSTARD:
						return new Coord(0, 17);
					case MISSSCARLETT:
						return new Coord(0, 18);
					case MRGREEN:
						return new Coord(0, 19);
					case MRSPEACOCK:
						return new Coord(0, 20);
					case MRSWHITE:
						return new Coord(0, 21);
					case PROFESSORPLUM:
						return new Coord(1, 17);
					default:
						return null;
				}
			case STUDY:
				switch (character) {
					case COLONELMUSTARD:
						return new Coord(16, 19);
					case MISSSCARLETT:
						return new Coord(16, 20);
					case MRGREEN:
						return new Coord(16, 21);
					case MRSPEACOCK:
						return new Coord(17, 19);
					case MRSWHITE:
						return new Coord(17, 20);
					case PROFESSORPLUM:
						return new Coord(17, 21);
					default:
						return null;
				}
			default:
				return null;
		}
	}
	
	// Returns the coordinates where a given weapon should be drawn in the room
	public Coord getWeaponCell(WeaponType weapon) {
		switch (type) {
		case BALLROOM:
			switch (weapon) {
				case CANDLESTICK:
					return new Coord(14, 5);
				case DAGGER:
					return new Coord(14, 4);
				case LEADPIPE:
					return new Coord(14, 3);
				case REVOLVER:
					return new Coord(14, 2);
				case ROPE:
					return new Coord(14, 1);
				case SPANNER:
					return new Coord(13, 5);
				default:
					return null;
				}
		case BILLIARDROOM:
			switch (weapon) {
				case CANDLESTICK:
					return new Coord(21, 10);
				case DAGGER:
					return new Coord(21, 9);
				case LEADPIPE:
					return new Coord(21, 8);
				case REVOLVER:
					return new Coord(21, 7);
				case ROPE:
					return new Coord(21, 6);
				case SPANNER:
					return new Coord(20, 10);
				default:
					return null;
			}
		case CONSERVATORY:
			switch (weapon) {
				case CANDLESTICK:
					return new Coord(21, 2);
				case DAGGER:
					return new Coord(21, 1);
				case LEADPIPE:
					return new Coord(21, 0);
				case REVOLVER:
					return new Coord(20, 3);
				case ROPE:
					return new Coord(20, 2);
				case SPANNER:
					return new Coord(20, 1);
				default:
					return null;
			}
		case DININGROOM:
			switch (weapon) {
				case CANDLESTICK:
					return new Coord(7, 13);
				case DAGGER:
					return new Coord(7, 12);
				case LEADPIPE:
					return new Coord(7, 11);
				case REVOLVER:
					return new Coord(7, 10);
				case ROPE:
					return new Coord(7, 9);
				case SPANNER:
					return new Coord(7, 8);
				default:
					return null;
			}
		case HALL:
			switch (weapon) {
				case CANDLESTICK:
					return new Coord(14, 21);
				case DAGGER:
					return new Coord(14, 20);
				case LEADPIPE:
					return new Coord(14, 19);
				case REVOLVER:
					return new Coord(14, 18);
				case ROPE:
					return new Coord(14, 17);
				case SPANNER:
					return new Coord(14, 16);
				default:
					return null;
			}
		case KITCHEN:
			switch (weapon) {
				case CANDLESTICK:
					return new Coord(5, 4);
				case DAGGER:
					return new Coord(5, 3);
				case LEADPIPE:
					return new Coord(5, 2);
				case REVOLVER:
					return new Coord(5, 1);
				case ROPE:
					return new Coord(5, 0);
				case SPANNER:
					return new Coord(4, 4);
				default:
					return null;
			}
		case LIBRARY:
			switch (weapon) {
				case CANDLESTICK:
					return new Coord(21, 16);
				case DAGGER:
					return new Coord(21, 15);
				case LEADPIPE:
					return new Coord(21, 14);
				case REVOLVER:
					return new Coord(21, 13);
				case ROPE:
					return new Coord(21, 12);
				case SPANNER:
					return new Coord(20, 16);
				default:
					return null;
			}
		case LOUNGE:
			switch (weapon) {
				case CANDLESTICK:
					return new Coord(6, 21);
				case DAGGER:
					return new Coord(6, 20);
				case LEADPIPE:
					return new Coord(6, 19);
				case REVOLVER:
					return new Coord(6, 18);
				case ROPE:
					return new Coord(6, 17);
				case SPANNER:
					return new Coord(5, 21);
				default:
					return null;
			}
		case STUDY:
			switch (weapon) {
				case CANDLESTICK:
					return new Coord(21, 21);
				case DAGGER:
					return new Coord(21, 20);
				case LEADPIPE:
					return new Coord(21, 19);
				case REVOLVER:
					return new Coord(20, 21);
				case ROPE:
					return new Coord(20, 20);
				case SPANNER:
					return new Coord(20, 19);
				default:
					return null;
			}
		default:
			return null;
		
		}
	}
}
