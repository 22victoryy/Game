package com.example.game.level3.world;

import android.view.MotionEvent;

import com.example.game.level3.core.Interactable;
import com.example.game.level3.core.Playable;
import com.example.game.level3.world.entities.GlideStrategy;
import com.example.game.level3.world.entities.SugarGlider;

/**
 * This class handles touch input to the screen.
 */
class GameTouchEventHandler implements Playable {

    /**
     * Handle touch input.
     *
     * @param gameWorld   the GameWorld instance.
     * @param motionEvent class that encapsulates touch input.
     */
    static void handleTouchEvent(GameWorld gameWorld, MotionEvent motionEvent) {
        int screenWidth = gameWorld.getScreenWidth();
        int screenHeight = gameWorld.getScreenHeight();
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        if (gameWorld.getGameState() == GameState.START) {
            if (y > 0.9 * screenHeight && x < 0.7 * screenWidth) {
                gameWorld.quit();
            } else if (y > 0.75 * screenHeight && x < 0.7 * screenWidth) {
                gameWorld.setGameState(GameState.CUSTOMIZE);
            } else if (y > 0.75 * screenHeight && x >= 0.7 * screenWidth) {
                gameWorld.toggleLaunchToNextLevel();
            } else {
                gameWorld.setTapCount(0);
                gameWorld.setGameState(GameState.LIVE);
            }
        } else if (gameWorld.getGameState() == GameState.CUSTOMIZE) {
            SugarGlider sugarGlider = gameWorld.getSugarGlider();
            if (y < 0.33 * screenHeight) {
                sugarGlider.setGlideStrategy(GlideStrategy.JUMP);
            } else if (y < 0.66 * screenHeight) {
                sugarGlider.setGlideStrategy(GlideStrategy.FALL);
            } else {
                sugarGlider.setGlideStrategy(GlideStrategy.FLY);
            }
            gameWorld.setGameState(GameState.START);
        } else if (gameWorld.getGameState() == GameState.LIVE) {
            if (y > 0.8F * screenHeight && x < 0.5F * screenWidth) {
                gameWorld.setGameState(GameState.PAUSE);
            } else {
                gameWorld.addToTapCount(1);
                for (Interactable interactive : gameWorld.getInteractableObjects()) {
                    interactive.onTouchEvent(gameWorld.getDeltaTime(), motionEvent);
                }
            }
        } else if (gameWorld.getGameState() == GameState.PAUSE) {
            if (y > 0.8F * screenHeight && x < 0.5F * screenWidth) {
                gameWorld.setGameState(GameState.LIVE);
            }
        } else if (gameWorld.getGameState() == GameState.GAMEOVER) {
            if (gameWorld.getOnDeathTimer() > 1.0) {
                if (gameWorld.getTapCount() != 0 && gameWorld.getScore() > 5) {
                    gameWorld.addToBonusPoints(
                            (10 * gameWorld.getScore()) / gameWorld.getTapCount());
                }
                gameWorld.setTryCount(gameWorld.getTryCount() + 1);
                GameWorldBuilder.build(gameWorld);
            }
        }
    }

    /**
     * Override the <code>getGameState</code> method from the Playable interface.
     *
     * @return null.
     */
    @Override
    public GameState getGameState() {
        return null;
    }

    /**
     * Setter for the game state.
     *
     * @param gameState set gameState.
     */
    @Override
    public void setGameState(GameState gameState) {
    }

    /**
     * Override the <code>onTouchEvent</code> method from the Playable interface.
     *
     * @param deltaTime   - interval between each time frame.
     * @param motionEvent - class that encapsulates touch input
     * @return false.
     */
    @Override
    public boolean onTouchEvent(double deltaTime, MotionEvent motionEvent) {
        return false;
    }
}

