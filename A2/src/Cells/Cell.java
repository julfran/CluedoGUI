package Cells;

import CluedoGame.Player;

public interface Cell {

	// Returns the cell's x value
    public int getXPos();

    // Returns the cell's y value
    public int getYPos();

    // Places a player in the cell
    public void putPlayer(Player player);

    // Returns the player currently occupying the cell
    public Player getPlayer();

    // Returns true if the cell can accept a new player
    public boolean canTakePlayer();
}
