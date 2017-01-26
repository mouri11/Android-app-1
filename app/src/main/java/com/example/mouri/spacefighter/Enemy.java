package com.example.mouri.spacefighter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import java.util.Random;

/**
 * Created by mouri on 24/1/17.
 */

public class Enemy {
    //bitmap for the enemy we have already pasted in the drawable folder
    private Bitmap bitmap;

    //x and y coordinates
    private int x,y;

    //enemy speed
    private int speed=1;

    //max and min coordinates to keep enemy inside screen
    private int maxX,maxY,minX,minY;

    //creating a rect collision
    private Rect detectCollision;

    public Enemy(Context context, int screenX, int screenY){
        //getting bitmap from drawable resource
        bitmap= BitmapFactory.decodeResource(context.getResources(),R.drawable.enemy);

        //initializing max and min coordinates
        maxX=screenX;
        maxY=screenY;
        minX=minY=0;

        //generating a Random coordinate to add enemy
        Random generator=new Random();
        speed=generator.nextInt(6)+10;
        x=screenX;
        y=generator.nextInt(maxY)-bitmap.getHeight();

        //initializing rect object
        detectCollision=new Rect(x,y,bitmap.getWidth(),bitmap.getHeight());
    }

    public void update(int playerSpeed){
        //decreasing x coordinate so that enemy will move from right to left
        x-=playerSpeed;
        x-=speed;
        //if enemy reaches the left edge
        if (x<minX-bitmap.getWidth()){
            //adding enemy again to right edge
            Random generator=new Random();
            speed=generator.nextInt(10)+10;
            x=maxX;
            y=generator.nextInt(maxY)-bitmap.getHeight();

            //adding the top,left,bottom and right of the rect object
            detectCollision.top=y;
            detectCollision.left=x;
            detectCollision.right=x+bitmap.getWidth();
            detectCollision.bottom=y+bitmap.getHeight();
        }
    }

    //adding a setter to x coordinate so we can change it after collision
    public void setX(int x){
        this.x=x;
    }

    //one more getter for getting rect object
    public Rect getDetectCollision(){
        return detectCollision;
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
