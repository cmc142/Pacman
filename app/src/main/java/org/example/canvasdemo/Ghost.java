package org.example.canvasdemo;

/**
 * Created by user on 28-03-2017.
 */


public class Ghost  {

    private int x,y;
    private boolean dead = false;

    public int getX() { return x;}
    public int getY() {return y;}
    public boolean getdead() {return dead;}
    public void setdead(boolean notalive) {dead = notalive;}

    public Ghost(int x,int y)
    {
        this.x = x;
        this.y = y;
    }




}
