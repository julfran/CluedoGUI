package Cells;

import Cards.RoomType;
import CluedoGame.Player;
import CluedoGame.Room;

public class RoomCell implements Cell {

	private int xPos;
	private int yPos;
	private Player player;
	private RoomType roomType;
	private boolean door = false;
	private Room room;

	public RoomCell(int x, int y, Room r){
		xPos =x;
		yPos =y;
		room = r;
		roomType =r.getType();
	}


	// Door variant constructor
    public RoomCell(int x, int y, Room r, boolean b) {
		xPos =x;
		yPos =y;
		room = r;
		roomType =r.getType();
		if (b) {
			door = true;
		}
	}

    public boolean isDoor() {
    	return door;
    }
    
    public Room getRoom() {
    	return room;
    }

	public RoomType getRoomType(){
		return roomType;
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
		player = p;
	}

	@Override
	public Player getPlayer() {
		return player;
	}

	public boolean canTakePlayer() {
		return true;
	}
}
