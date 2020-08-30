package CluedoGame;

import java.util.ArrayList;

import Cards.RoomType;
import Cards.WeaponType;

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
}
