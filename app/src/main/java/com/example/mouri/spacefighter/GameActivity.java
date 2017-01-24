package com.example.mouri.spacefighter;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;

public class GameActivity extends AppCompatActivity {

    //declaring gameview
    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //getting display object
        Display display=getWindowManager().getDefaultDisplay();

        //getting the screen resolution into point object
        Point size=new Point();
        display.getSize(size);
        //initializing gameView object;this time we are also passing screen size to game constructor
        gameView=new GameView(this,size.x,size.y);

        //adding it to content view
        setContentView(gameView);
    }

    //pausing game when activity is paused
    @Override
    protected void onPause(){
        super.onPause();
        gameView.pause();
    }

    //running game when activity is resumed
    @Override
    protected void onResume(){
        super.onResume();
        gameView.resume();
    }
}
