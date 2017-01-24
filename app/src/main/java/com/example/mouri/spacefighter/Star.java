package com.example.mouri.spacefighter;

import java.util.Random;

/**
 * Created by mouri on 23/1/17.
 */

public class Star {
    private int x,y,speed,maxX,maxY,minX,minY;

    public Star(int screenX, int screenY){
        maxX=screenX;
        maxY=screenY;
        minX=minY=0;
        Random generator=new Random();
        speed=generator.nextInt(10);
        //generating a random coordinate but keeping it within screen size
        x=generator.nextInt(maxX);
        y=generator.nextInt(maxY);
    }

    public void update(int playerSpeed){
        //animating star horizontally by decreasing x coordinate with playerSpeed
        x-=playerSpeed;
        y-=speed;
        //if the star reached the left edge of the screen
        if(x<0){
            //again starting star from right side, giving infinite scrolling background effect
            x=maxX;
            Random generator=new Random();
            y=generator.nextInt(maxY);
            speed=generator.nextInt(15);
        }
    }

    public float getStarWidth(){
        //making star width random so it will get a real look
        float minX=1.0f;
        float maxX=4.0f;
        Random rand=new Random();
        float finalX=rand.nextFloat()*(maxX-minX)+minX;
        return finalX;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
}
