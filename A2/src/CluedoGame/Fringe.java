package CluedoGame;

import Cells.Cell;

public class Fringe {
	int dist;
	Fringe last;
	Cell cell;
	
	public Fringe(Cell cell, int dist, Fringe last) {
		this.cell = cell;
		this.dist = dist;
		this.last = last;
	}
}
