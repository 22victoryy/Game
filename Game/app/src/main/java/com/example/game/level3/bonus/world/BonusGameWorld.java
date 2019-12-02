package com.example.game.level3.bonus.world;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Color;
import android.view.MotionEvent;

import java.util.List;

import com.example.game.level3.core.Drawable;
import com.example.game.level3.core.Interactable;
import com.example.game.level3.core.Updatable;
import com.example.game.level3.core.AbstractGameWorld;

/**
 * Class for the BonusGameWorld.
 */
public class BonusGameWorld extends AbstractGameWorld {
    private int screenWidth;
    private int screenHeight;
    private GameState gameState = GameState.START;

    public BonusGameWorld(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        BonusGameWorldBuilder.build(this);
    }

    /**
     * Getter for the screen width.
     *
     * @return screen width.
     */
    public int getScreenWidth() {
        return this.screenWidth;
    }

    /**
     * Getter for the screen height.
     *
     * @return screen height.
     */
    public int getScreenHeight() {
        return this.screenHeight;
    }

    /**
     * Draw entities in the bonus game world.
     *
     * @param canvas - instance of Canvas that contains methods for drawing
     *               shapes and text to the screen.
     * @param paint  - instance of Paint containing methods that specifies the colour and text size
     */
    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.WHITE);
        canvas.drawRect(0.0F, 0.0F, this.getScreenWidth(), this.getScreenHeight(), paint);
        if (gameState == GameState.START) {
            paint.setColor(Color.BLACK);
            paint.setTextSize(50);
            canvas.drawText("Catch the Easter Eggs!",
                    0.1F * screenHeight, 0.1F * screenWidth, paint);
        } else {
            paint.setColor(Color.BLACK);
            paint.setTextSize(50);
            canvas.drawText("(Go back to Main Level)",
                    0.1F * screenHeight, 0.1F * screenWidth, paint);
            canvas.drawText("Bonus Points: " + this.getBonusPoints(),
                    0.1F * screenHeight, 0.2F * screenWidth, paint);
        }
        List<Drawable> drawables = this.getDrawableObjects();
        for (Drawable d : drawables) {
            d.draw(canvas, paint);
        }
    }

    /**
     * Update all entities in the bonus game world.
     *
     * @param deltaTime time interval between each time frame.
     */
    @Override
    public void update(double deltaTime) {
        if (gameState != GameState.START) {
            List<Updatable> updatables = this.getUpdatableObjects();
            for (Updatable u : updatables) {
                u.update(deltaTime);
            }
            this.getCollisionHandler().processCollisions();
        }
    }

    /**
     * Handle touch inputs from the screen.
     *
     * @param deltaTime   - interval between each time frame.
     * @param motionEvent - class that encapsulates touch input
     * @return boolean that notifies what was done with the touch input.
     */
    @Override
    public boolean onTouchEvent(double deltaTime, MotionEvent motionEvent) {
        if (gameState == GameState.START) {
            this.setGameState(GameState.LIVE);
        } else {
            if (motionEvent.getY() < screenHeight * 0.3) {
                if (motionEvent.getY() < screenHeight * 0.1) {
                    this.quit();
                }
                return false;
            }
            else {
                List<Interactable> interactables = this.getInteractableObjects();
                for (Interactable i : interactables) {
                    i.onTouchEvent(deltaTime, motionEvent);
                }
                return true;
            }
        }
        return true;
    }

    /**
     * Getter for the game state.
     *
     * @return the game state.
     */
    @Override
    public GameState getGameState() {
        return this.gameState;
    }

    /**
     * Setter for the game state.
     *
     * @param gameState set gameState.
     */
    @Override
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
}


