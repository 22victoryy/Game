package com.example.game.level2.states;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;

import com.example.game.level2.core.Changeable;
import com.example.game.level2.core.gameState;
import com.example.game.level2.items.Player;
import com.example.game.level2.items.Level2Manager;

/**
 * Class currentState for current game playing state
 */
public class currentState extends gameState implements Changeable {

    private boolean isMovingPlayer;
    private boolean isGameOver;

    private Player player;
    private Point playerPoint; // player coordinates (points)

    private Level2Manager level2Manager;

    private InstanceManager manager;

    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    /**
     * Constructor for class currentState
     * @param manager InstanceManager
     */
    currentState(InstanceManager manager){

        this.manager = manager; // instantiate by passing parameter

//        screenSpecs = new ScreenSpecs();

        // initialise player
        player = new Player(new Rect(100, 100, 200, 200), Color.CYAN);

        // initialise position of player rect
        playerPoint = new Point(screenWidth / 2,
                (3 * screenHeight) / 4);
        player.updateRectCoord(playerPoint);

        // initialise blockades through level2manager
        level2Manager = new Level2Manager(200, 350, 75,
                Color.YELLOW, manager.getStatisticsManager(), manager.getHardMode());
    }

    /**
     * Getter for boolean value to check if game is over
     * @return isGameOver
     */
    public boolean getGameOver(){
        return isGameOver;
    }

    /**
     * Setter for boolean value to check if game is over
     */
    public void setGameOver(){
        if (!isGameOver) {
            player.updateRectCoord(playerPoint);
            level2Manager.update();
            if (level2Manager.isCollide(player)) {
                isGameOver = true;
            }
        }
    }

    /**
     * Change the state to the options screen which should come after encountering game over
     */
    public void changeState() {
        manager.setGameState(1);
    }

    /**
     * Reset the game through reinitialisation of the items in the game
     */
    private void gameSet() {

        // re-initialise player position
        playerPoint = new Point (screenWidth / 2, (3 * screenHeight) / 4);
        player.updateRectCoord(playerPoint);

        // re-initialise blockades
        level2Manager = new Level2Manager(200,
                350, 75, Color.YELLOW, manager.getStatisticsManager(),
                manager.getHardMode());

        isMovingPlayer = false; // so that player does not move when the game is set
    }

    /**
     * Puts constraints on player movement depending on touch event or game state
     * @param event MotionEvent
     */
    @Override
    public void atTouch(MotionEvent event) {
        int x = Math.round(event.getX());
        int y = Math.round(event.getY());

        if (event.getAction() == MotionEvent.ACTION_DOWN){
            if (!isGameOver && player.getRect().contains(x, y)) {
                isMovingPlayer = true;
            }
            if (isGameOver) {
                gameSet();
                changeState();
                isGameOver = false;
            }
        }
        else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            if (!isGameOver && isMovingPlayer) {
                playerPoint.set(x, y);
            }

        }
        else if (event.getAction() == MotionEvent.ACTION_UP) {
            isMovingPlayer = false;
        }
    }

    /**
     * Draw the player, blockades, Game Over
     * @param canvas Canvas
     */
    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);

        player.draw(canvas);
        level2Manager.draw(canvas);

        // draw GAME OVER if game over
        if (isGameOver) {
            Paint paint = new Paint();
            paint.setColor(Color.RED);
            paint.setTextSize(80);
            paint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText("GAME OVER", screenWidth/2,
                    screenHeight/2, paint);
        }
    }

    /**
     * Updates so that game over state
     */
    @Override
    public void update() {
        setGameOver();
    }
}
