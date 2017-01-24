package com.example.mouri.spacefighter;

/**
 * Created by mouri on 21/1/2017
 */

import android.content.Context;
//import android.content.pm.PackageInfo;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class GameView extends SurfaceView implements Runnable{

    //boolean variable to track if game is playing or not
    volatile boolean playing;

    //the game thread
    private Thread gameThread=null;

    //adding player to this class
    private Player player;

    //these objects will be used for drawing
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;

    //adding a stars list
    private ArrayList<Star> stars=new ArrayList<Star>();

    public GameView(Context context,int screenX, int screenY){
        super(context);

        //initializing player object, this time passing screen size to player constructor
        player=new Player(context,screenX,screenY);

        //initializing drawing objects
        surfaceHolder=getHolder();
        paint=new Paint();

        //adding 100 stars
        int starNums=100;
        for(int i=0;i<starNums;i++){
            Star s=new Star(screenX,screenY);
            stars.add(s);
        }
    }
    @Override
    public void run(){
        while(playing){
            //to update the frame
            update();

            //to draw the frame
            draw();

            //to control
            control();
        }
    }

    private void update(){
        //updating player position
        player.update();

        //updating stars with player speed
        for(Star s:stars){
            s.update(player.getSpeed());
        }
    }

    private void draw(){
        //checking if surface is valid
        if(surfaceHolder.getSurface().isValid()){
            //locking the canvas
            canvas=surfaceHolder.lockCanvas();
            //drawing a background colour for canvas
            canvas.drawColor(Color.BLACK);

            //setting paint color to white to draw stars
            paint.setColor(Color.WHITE);

            //for drawing all stars
            for(Star s:stars){
                paint.setStrokeWidth(s.getStarWidth());
                canvas.drawPoint(s.getX(),s.getY(),paint);
            }

            //drawing the player
            canvas.drawBitmap(
                    player.getBitmap(),
                    player.getX(),
                    player.getY(),
                    paint);
            //unlocking the canvas
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void control(){
        try {
            gameThread.sleep(17);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void pause(){
        //when game is paused, setting the variable to false
        playing=false;
        try{
            gameThread.join();
        }
        catch (InterruptedException e){
            System.out.println();
        }
    }

    public void resume(){
        //when game is resumed starting the thread again
        playing=true;
        gameThread=new Thread(this);
        gameThread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent){
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK){
            case MotionEvent.ACTION_UP:
                //when the user presses on the screen we will do something here
                player.stopBoosting();
                break;
            case MotionEvent.ACTION_DOWN:
                //when user releases the screen do something here
                player.setBoosting();
                break;
        }
        return true;
    }
}
