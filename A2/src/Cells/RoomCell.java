package Cells;

import Cards.RoomType;
import CluedoGame.Player;

public class RoomCell implements Cell {

	private int xPos;
	private int yPos;
	private Player player;
	private RoomType room;

	public RoomCell(int x, int y, RoomType r){
		xPos =x;
		yPos =y;
		room =r;
	}



    public RoomType getRoomType(){
		return room;
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

//	private int x;
//	private int y;
//	private Board board;
//	private Player player = null;
//	private RoomType type;
//
//	public RoomCell(int x, int y, RoomType type) {
//		this.x = x;
//		this.y = y;
//		this.board = board;
//		this.type = type;
//	}
//
//	public int getX() {
//		return x;
//	}
//
//	public int getY() {
//		return y;
//	}
//
//	public RoomType getType() {
//		return type;
//	}
//
//	public boolean isOccupied() {
//		if (player != null) {
//			return true;
//		} else {
//			return false;
//		}
//	}
//
//	public Player getPlayer() {
//		if (isOccupied()) {
//			return player;
//		} else {
//			return null;
//		}
//	}
//
//	public boolean placePlayer(Player p) {
//		if (!isOccupied()) {
//			player = p;
//			return true;
//		} else {
//			return false;
//		}
//	}
//
//	public void removePlayer() {
//		player = null;
//	}



}
