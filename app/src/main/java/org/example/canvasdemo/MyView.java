package org.example.canvasdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MyView extends View {

    ArrayList<GoldCoin> coins = new ArrayList<GoldCoin>();
    ArrayList<Ghost> ghostst = new ArrayList<Ghost>();
    MainActivity activity;

    public void setActivity(MainActivity activity) {
        this.activity = activity;
    }

    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pacman);
    Bitmap coin = BitmapFactory.decodeResource(getResources(), R.drawable.goldcoin);
    Bitmap ghost = BitmapFactory.decodeResource(getResources(), R.drawable.ghost);
    int pacx = 50;
    int pacy = 400;
    int h, w; //used for storing our height and width
    int level = 0;
    static boolean newGame = true;


    public void moveRight(int x) {
        //still within our boundaries?
        if (pacx + x + bitmap.getWidth() < w)
            pacx = pacx + x;
        invalidate(); //redraw everything - this ensures onDraw() is called.
    }


    public void moveLeft(int x) {
        //still within our boundaries?
        if (pacx + x + bitmap.getWidth() >= w / 9) // vis jeg siger - flytter den sig ikke og vis jeg / med pacx crasher appen
            pacx = pacx - x;

        invalidate(); //redraw everything - this ensures onDraw() is called.


    }

    public void moveUp(int y) {
        //still within our boundaries?
        if (pacy - y - bitmap.getWidth() <= h * pacy)
            pacy = pacy - y;
        invalidate(); //redraw everything - this ensures onDraw() is called.
    }


    public void moveDown(int y) {
        //still within our boundaries?
        if (pacy + y + bitmap.getWidth() <= h - pacy + 900)
            pacy = pacy + y;
        invalidate(); //redraw everything - this ensures onDraw() is called.
    }


    /* The next 3 constructors are needed for the Android view system,
	when we have a custom view.
	 */
    public MyView(Context context) {
        super(context);

    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


/*    public void reset()
    {
        int pacx = 50;
        int pacy = 400;
        newGame = true;
        invalidate();
    }*/


    //In the onDraw we put all our code that should be
    //drawn whenever we update the screen.
    @Override
    protected void onDraw(Canvas canvas) {


        //Here we get the height and weight
        h = canvas.getHeight();
        w = canvas.getWidth();
        System.out.println("h = " + h + ", w = " + w);
        //Making a new paint object
        Paint paint = new Paint();
        //setting the color
        paint.setColor(Color.RED);
        canvas.drawColor(Color.WHITE); //clear entire canvas to white color


        canvas.drawBitmap(bitmap, pacx, pacy, paint);

        if (newGame) {
            newGame = false;



            if(level >= 2){

                activity.counter = 2;
                activity.updatelife();
            }

            if(level >= 3){

                activity.counter = 1;
                activity.updatelife();
            }

            Random rand = new Random(System.currentTimeMillis());
            coins = new ArrayList<>();
            ghostst = new ArrayList<>();
            for (int i = 0; i <= 10; i++) {
                int coinx = rand.nextInt(w - 52);
                int coiny = rand.nextInt(h - 51);
                GoldCoin newCoin = new GoldCoin(coinx, coiny);
                coins.add(newCoin);

            }


                for (int a = 1; a <= 2;) {
                    int gosx = rand.nextInt(w - 52);
                    int gosy = rand.nextInt(h - 51);
                    Ghost newGost = new Ghost(gosx, gosy);
                    ghostst.add(newGost);

                        a++;

                }



            } //end of newgame


            for (Ghost gost : ghostst) {

                ghostinfo(gost);

                 if (level > 0) {
                         if (!gost.getdead()) {
                             canvas.drawBitmap(ghost, gost.getX(), gost.getY(), paint);
                         }

                 }


            }

            for (GoldCoin goldCoin : coins) {

                goldtakken(goldCoin);


                if (!goldCoin.getTakken()) {
                    canvas.drawBitmap(coin, goldCoin.getX(), goldCoin.getY(), paint);
                }


            }


            super.onDraw(canvas);


        }


    public void goldtakken(GoldCoin goldCoins) {

        double res = Math.sqrt((pacx - goldCoins.getX()) * (pacx - goldCoins.getX()) + (pacy - goldCoins.getY()) * (pacy - goldCoins.getY()));


        if (res < 60) {

            if (goldCoins.getTakken() == false) {
                activity.updatepoint(goldCoins);
            }
            goldCoins.setTakken(true);

            if(level > 1){

                newGame = true;

            }


        }

    }

    public void ghostinfo(Ghost gost){

        Random ghostran = new Random(System.currentTimeMillis());

        if(!gost.getdead()) {

            if (gost.getX() <= w) {

                int x = ghostran.nextInt(w);

            }


            if (gost.getX() >= w / 9) {

                int x = ghostran.nextInt(w);

            }


            if (gost.getY() <= h * gost.getY()) {

                int x = ghostran.nextInt(w);

            }


            if (gost.getY() <= h - gost.getY()) {

                int x = ghostran.nextInt(w);

            }


            double res = Math.sqrt((pacx - gost.getX()) * (pacx - gost.getX()) + (pacy - gost.getY()) * (pacy - gost.getY()));


            if (res < 60) {

                if (gost.getdead() == false) {
                    activity.hitgohst(gost);
                }
                gost.setdead(true);

            }
            activity.updateghost(gost);
        }


    }

/*    public void Levelup(Ghost ghost){

        Random rand = new Random(System.currentTimeMillis());

        for (int a = 0; a <= level; a++) {
            int gosx = rand.nextInt(w - 52);
            int gosy = rand.nextInt(h - 51);
            Ghost newGost = new Ghost(gosx, gosy);
            ghostst.add(newGost);
        }

    }
*/


}



