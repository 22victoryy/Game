package com.example.game.level3.world;

import com.example.game.level3.core.Playable;
import com.example.game.level3.core.Updatable;

/**
 * Class to update the objects in the game.
 */
class GameUpdater {
    /**
     * Update objects.
     * @param gameWorld - the game world instance.
     * @param deltaTime - time interval between each frame.
     */
    static void updateGameWorld(GameWorld gameWorld, double deltaTime) {
        if (gameWorld.getGameState() == Playable.GameState.LIVE) {
            gameWorld.setDeltaTime(deltaTime);
            gameWorld.addToTotalTime(deltaTime);
            gameWorld.setFramesPerSecond(1.0 / deltaTime);
            gameWorld.addToTotalDistance(gameWorld.getSugarGliderSpeed() * deltaTime);
            gameWorld.setGameState(
                    gameWorld.getSugarGlider().getGameState());
            for (Updatable u : gameWorld.getUpdatableObjects()) {
                u.update(deltaTime);
            }
            gameWorld.getCollisionHandler().processCollisions();
        } else if (gameWorld.getGameState() == Playable.GameState.GAMEOVER) {
            gameWorld.setGameState(
                    gameWorld.getSugarGlider().getGameState());
            for (Updatable u : gameWorld.getUpdatableObjects()) {
                u.update(deltaTime);
            }
            gameWorld.getCollisionHandler().processCollisions();
            gameWorld.addToOnDeathTimer(deltaTime);
        }
    }
}