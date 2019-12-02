package com.example.game.level2.core;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Abstract gameState class for game states
 */
public abstract class gameState {

    /**
     * Abstract draw method to draw the items in the game state
     * @param canvas Canvas
     */
    public abstract void draw(Canvas canvas);

    /**
     * Abstract update method to update the items in the game state
     */
    public void update() {}

    /**
     * Abstract atTouch method to do actions depending on MotionEvent
     * @param event MotionEvent
     */
    public abstract void atTouch(MotionEvent event);

    /**
     * Abstract getter getGameOver method to get the game state's game over status
     * @return Boolean isGameOver
     */
    public abstract boolean getGameOver();

    /**
     * Abstract setter setGameOver to set the game state's game over status
     */
    public abstract void setGameOver();
}

