package com.example.mouri.spacefighter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by mouri on 21/1/17.
 */

public class Player {
    //bitmap to get character from image
    private Bitmap bitmap;

    //coordinates
    private int x,y;

    //motion speed of character
    private int speed=0;

    //boolean variable to track ship is boosting or not
    private boolean boosting;

    //gravity value to add gravity effect to the ship
    private final int GRAVITY=-1;

    //controlling y-coordinate so that ship won't go outside of screen
    int maxY,minY;

    //limit bounds of ship's speed
    private final int MIN_SPEED=-10;
    private final int MAX_SPEED=200;

    public Player(Context context, int screenX, int screenY){
        x=75;
        y=250;
        speed=1;

        //getting bitmap from drawable resource
        bitmap= BitmapFactory.decodeResource(context.getResources(),R.drawable.player);

        //calculating maxY
        maxY=screenY-bitmap.getHeight();

        //top edge's y point is 0, so minY wil always be 0
        minY=0;
        //setting boosting to false initially
        boosting=false;
    }

    //setting boosting true
    public void setBoosting(){
        boosting=true;
    }

    //setting boosting to false
    public void stopBoosting(){
        boosting=false;
    }
    //method to update coordinate of character
    public void update(){
        //if ship is boosting
        if(boosting)speed+=2;//speeding up ship
        else speed-=5;//slowing down if not boosting

        //controlling top speed
        if(speed>MAX_SPEED)speed=MAX_SPEED;

        //if speed is less than min speed, controlling it so that it won't stop completely
        if(speed<MIN_SPEED)speed=MIN_SPEED;

        //moving the ship down
        y-=speed+GRAVITY;

        //but controlling it also so it won't go off the screen
        if(y<minY){
            y=minY;
        }
        if(y>maxY){
            y=maxY;
        }
        //updating x coordinate
        //x++;
    }

    //getters
    public Bitmap getBitmap(){
        return bitmap;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getSpeed(){
        return speed;
    }
}
