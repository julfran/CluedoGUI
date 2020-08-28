package Cells;

import Cards.RoomType;
import CluedoGame.Player;

public class DoorCell implements Cell {

	private int xPos;
	private int yPos;
	private RoomType room;
	private Player player;

	
	public DoorCell(int x, int y) {
		this.xPos = x;
		this.yPos = y;
	}

	/**
	 * @returns the room of that particular door.
	 * */
	public RoomType getRoomDoor(){
		return room;
	}
	/**
	 * @returns set the room of that
	 * particular door to the allocated room.
	 * */
	public void setRoomDoor(RoomType rooms){
		room = rooms;
	}



	@Override
	public int getXPos() {
		return xPos;
	}

	@Override
	public int getYPos() {
		return yPos;
	}

	@Override
	public void putPlayer(Player p) {
		player =p;
	}

	public Player getPlayer() {
		return player;
	}

	public boolean canTakePlayer() {
		return false;
	}


}
