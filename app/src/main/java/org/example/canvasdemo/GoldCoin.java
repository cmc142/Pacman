package org.example.canvasdemo;

/**
 * Created by user on 23-03-2017.
 */

public class GoldCoin{

    private int x,y;
    private boolean takken = false;

    public int getX() { return x;}
    public int getY() {return y;}
    public boolean getTakken() {return takken;}
    public void setTakken(boolean takken2){takken = takken2;}

    public GoldCoin(int x,int y)
    {
        this.x = x;
        this.y = y;
    }

}
