package Cells;


import CluedoGame.Player;

public class HallwayCell implements Cell {

	private int xPos;
	private int yPos;
	private Player player;

	public HallwayCell (int x, int y){
		xPos = x;
		yPos = y;
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
		if (getPlayer() == null) {
			return true;
		} else {
			return false;
		}
	}


//	private int x;
//	private int y;
//	private Player player = null;
//
//	public HallwayCell(int x, int y) {
//		this.x = x;
//		this.y = y;
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
//	public boolean placePlayer(Player p) {
//		if (player == null) {
//			player = p;
//			return true;
//		} else {
//			return false;
//		}
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
//	@Override
//	public RoomType getType() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	/**
//	 * Return a dot if unoccupied
//	 * Return player token if occupied
//	 */
//	public String toString(){
//		if(isOccupied()){
//			return getPlayer().toString();
//		}
//		else{
//			return "";
//		}
//	}

}
