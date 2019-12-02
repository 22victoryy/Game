package com.example.game.level3.world;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.example.game.level3.bonus.world.BonusGameWorld;
import com.example.game.level3.core.AbstractGameWorld;
import com.example.game.level3.world.entities.SugarGlider;


/**
 * The GameWorld Class manages all items in the game.
 */
public class GameWorld extends AbstractGameWorld {

    private int screenWidth;
    private int screenHeight;
    private SugarGlider sugarGlider;
    private GameState gameState;
    private double onDeathTimer = 0.0;

    /**
     * Constructor for GameWorld.
     *
     * @param screenWidth  width of the screen.
     * @param screenHeight height of the screen.
     */
    public GameWorld(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        GameWorldBuilder.build(this);
    }

    /**
     * Getter for the sugar glider.
     *
     * @return instance of <code>SugarGlider</code>.
     */
    SugarGlider getSugarGlider() {
        return this.sugarGlider;
    }

    /**
     * Setter for the sugar glider.
     *
     * @param sugarGlider instance of <code>SugarGlider</code>.
     */
    void setSugarGlider(SugarGlider sugarGlider) {
        this.sugarGlider = sugarGlider;
    }


    /**
     * Getter for the speed of the sugar glider.
     *
     * @return speed of the sugar glider.
     */
    double getSugarGliderSpeed() {
        double sugarGliderSpeed = 200.0;
        return sugarGliderSpeed;
    }

    /**
     * Getter for the countdown that occurs on a game over.
     * This countdown is so that the game over screen is shown for at least a second.
     *
     * @return countdown before the player is allowed to start a new game.
     */
    double getOnDeathTimer() {
        return this.onDeathTimer;
    }

    /**
     * Getter for the countdown that occurs on a game over.
     * This countdown is so that the game over screen is shown for at least a second.
     *
     * @param onDeathTimer countdown before the player is allowed to start a new game.
     */
    void setOnDeathTimer(double onDeathTimer) {
        this.onDeathTimer = onDeathTimer;
    }

    /**
     * Adder to increment the countdown after a game over.
     *
     * @param time to increment the countdown.
     */
    void addToOnDeathTimer(double time) {
        this.onDeathTimer += time;
    }

    /**
     * Getter for the screen width.
     *
     * @return the screen width.
     */
    public int getScreenWidth() {
        return this.screenWidth;
    }

    /**
     * Getter for the screen height.
     *
     * @return the screen height.
     */
    public int getScreenHeight() {
        return this.screenHeight;
    }

    /**
     * Update the game world.
     *
     * @param deltaTime time interval between each frame.
     */
    @Override
    public void update(double deltaTime) {
        GameUpdater.updateGameWorld(this, deltaTime);
    }

    /**
     * Get a touch event from the user and send it to all those objects that can handle touch.
     *
     * @param deltaTime   - interval between each time frame.
     * @param motionEvent - class that encapsulates touch input
     * @return false.
     */
    @Override
    public boolean onTouchEvent(double deltaTime, MotionEvent motionEvent) {
        GameTouchEventHandler.handleTouchEvent(this, motionEvent);
        return false;
    }

    /**
     * Draw all objects in the GameWorld.
     *
     * @param canvas - canvas to paint on.
     * @param paint  - paint to paint on the canvas.
     */
    @Override
    public void draw(Canvas canvas, Paint paint) {
        GameDrawHandler.draw(this, canvas, paint);
    }

    /**
     * Getter for the <code>GameState</code> enum.
     *
     * @return <code>GameState</code> enum.
     */
    @Override
    public GameState getGameState() {
        return gameState;
    }

    /**
     * Setter for the <code>GameState</code> enum.
     *
     * @param gameState set the <code>GameState</code> enum.
     */
    @Override
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    /**
     * Getter for the next game.
     *
     * @return the next level.
     */
    @Override
    public AbstractGameWorld getNextLevel() {
        return new BonusGameWorld(this.screenWidth, this.screenHeight);
    }
}
