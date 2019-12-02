package com.example.game.level2.states;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;

import com.example.game.statistics.StatisticsManager;
import com.example.game.level2.core.gameState;

import java.util.ArrayList;

/**
 * Class InstanceManager for game states management
 */
public class InstanceManager extends gameState {

    private ArrayList<gameState> gameStates = new ArrayList<>(); // array of game states

    private int currentState; // at 0 leads to currentState and at 1 leads to options

    private boolean isGameOver;
    private boolean isHardMode;

    private StatisticsManager statisticsManager;

    /**
     * Constructor for game states manager
     * @param context Context
     * @param statisticsManager statisticsManager to track score
     * @param isHardMode Difficulty
     */
    public InstanceManager(Context context, StatisticsManager statisticsManager,
                           boolean isHardMode) {
        this.statisticsManager = statisticsManager; //
        this.isHardMode = isHardMode; //initialise difficulty

        // add the states
        gameStates.add(new currentState(this));
        gameStates.add(new Options(this, context, statisticsManager));
    }

    /**
     * Getter for current game state
     * @return currentState of the game
     */
    int getCurrentState() {
        return currentState;
    }

    /**
     * Setter for current game state
     * @param currentState currentState of the game
     */
    void setGameState(int currentState){
        this.currentState = currentState;
    }

    /**
     * Gets the array of game states
     * @return Array of game states
     */
    ArrayList<gameState> getGameStates() {
        return gameStates;
    }

    /**
     * Triggers effects at touch for every game state
     * @param event MotionEvent
     */
    public void atTouch(MotionEvent event){
        gameStates.get(currentState).atTouch(event);

    }

    /**
     * Draw the current game state items
     * @param canvas Canvas
     */
    @Override
    public void draw(Canvas canvas) {
        gameStates.get(currentState).draw(canvas);
    }

    /**
     * Getter if game is over
     * @return Boolean isGameOver
     */
    public boolean getGameOver(){
        return isGameOver;
    }

    /**
     * Setter for the game over boolean value to corresponding game state's current boolean value
     */
    @Override
    public void setGameOver() {
        isGameOver = gameStates.get(currentState).getGameOver();
    }

    /**
     * Update all the items in the current game states
     */
    @Override
    public void update() {
        gameStates.get(currentState).update();
    }

    /**
     * Getter for statisticsManager
     * @return statisticsManager
     */
    public StatisticsManager getStatisticsManager() {
        return statisticsManager;
    }

    /**
     * Getter for game difficulty
     * @return isHardMode
     */
    boolean getHardMode() { return isHardMode; }
}
