package com.example.mouri.spacefighter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

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
        }
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
