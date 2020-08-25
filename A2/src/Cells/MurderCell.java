package Cells;

public class MurderCell implements cluedo.Cell {
    private int xPos;
    private int yPos;

    public MurderCell(int x, int y){
        this.xPos = x;
        this.yPos= y;
    }


    @Override
    public int getXPos() {
        return xPos;
    }

    @Override
    public int getYPos() {
        return yPos;
    }

}
