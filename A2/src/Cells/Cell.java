package Cells;

import CluedoGame.Player;

public interface Cell {

    public int getXPos();

    public int getYPos();

    public default void putPlayer(Player player){}

    public default Player getPlayer(){
        return null;
    }


}
