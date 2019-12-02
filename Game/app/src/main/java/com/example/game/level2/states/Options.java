package com.example.game.level2.states;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.example.game.activities.Activity3;
import com.example.game.activities.GameStart;
import com.example.game.statistics.StatisticsManager;
import com.example.game.level2.core.Changeable;
import com.example.game.level2.core.gameState;
import com.example.game.level2.MainLevel2;

/**
 * Class Options for options after encountering game over
 */
public class Options extends gameState implements Changeable {

    private InstanceManager manager; // instance manager

    private Context context; // context of current screen

    private int tryAgainScreenY; // Try again Screen dimensions of Y coordinate
    private int quitScreenY; // Quit Screen dimensions of Y coordinate
    private int nextLvlScreenY; // nextLvlScreen dimensions of Y coordinate
    private int triesScreenY; // Number of tries Screen dimensions of Y coordinate
    private int allScreensX; // Screen dimensions of X coordinate for all screens

    private boolean isGameOver;

    private StatisticsManager statisticsManager;

    /**
     * Constructor for Options
     * @param manager InstanceManager
     * @param context Context of options
     * @param statisticsManager statisticsManager
     */
    Options(InstanceManager manager, Context context, StatisticsManager statisticsManager){

        // initialise the context, statisticsManager, InstanceManager
        this.context = context;
        this.statisticsManager = statisticsManager;
        this.manager = manager;
        int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
        int allScreensY = screenHeight / 2;
        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;

        // set the screens
        allScreensX = screenWidth / 2;
        triesScreenY = allScreensY - 450;
        tryAgainScreenY = allScreensY - 200;
        quitScreenY = allScreensY + 50;
        nextLvlScreenY = allScreensY + 300;
    }

    /**
     * Change the state to game playing screen
     */
    public void changeState() {
        manager.setGameState(0);
    }

    /**
     * Depending on user's clicking choice, they will either play the level again, quit, or move
     * to the next level game.
     * @param event MotionEvent
     */
    @Override
    public void atTouch(MotionEvent event) {

        int y = Math.round(event.getY());

        // to replay the level
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (y <= tryAgainScreenY) {
                Intent intent = new Intent(context, MainLevel2.class);
                intent.putExtra("statisticsManager", manager.getStatisticsManager());
                intent.putExtra("difficulty", manager.getHardMode());
                changeState();
                statisticsManager.addTries();
                if (getGameOver()) {
                    setGameOver();
                }
            }
            // to quit
            else if (y <= quitScreenY) {
                Intent intent = new Intent(context, GameStart.class);
                intent.putExtra("statisticsManager", manager.getStatisticsManager());
                context.startActivity(intent);
            }
            // to go to level 2
            else if (y <= nextLvlScreenY)
            {
                Intent intent = new Intent(context, Activity3.class);
                intent.putExtra("statisticsManager", manager.getStatisticsManager());
                context.startActivity(intent);
            }
        }
    }

    /**
     * Getter for current game over status
     * @return Boolean isGameOver
     */
    @Override
    public boolean getGameOver() {
        return isGameOver;
    }

    /**
     * Setter for isGameOver to current game over status retrieved from current game state
     */
    @Override
    public void setGameOver() {
        isGameOver = manager.getGameStates().get(manager.getCurrentState()).getGameOver();
        if (isGameOver) {
            isGameOver = false;
        }
    }

    /**
     * Draw the options and number of tries attempted
     * @param canvas Canvas
     */
    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.RED);

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(80);
        paint.setTextAlign(Paint.Align.CENTER);

        canvas.drawText("TRIES:" + statisticsManager.getTries(), allScreensX,
                triesScreenY, paint);

        canvas.drawText("TRY AGAIN?", allScreensX,
                tryAgainScreenY, paint);

        canvas.drawText("QUIT?", allScreensX, quitScreenY, paint);

        canvas.drawText("NEXT LEVEL?", allScreensX,
                nextLvlScreenY, paint);
    }
}
