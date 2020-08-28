package Cells;

import CluedoGame.Player;

public class MurderCell implements Cell {
    private int xPos;
    private int yPos;

    public MurderCell(int x, int y){
        this.xPos = x;
        this.yPos= y;
    }


    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }


	public void putPlayer(Player player) {
		// TODO Auto-generated method stub
		
	}


	public Player getPlayer() {
		return null;
	}


	public boolean canTakePlayer() {
		return false;
	}

}
