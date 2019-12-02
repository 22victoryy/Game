package com.example.game.level3.core;

/**
 * Playable interface.
 * This interface allows a derived
 * class to be able respond to taps on the screen.
 */
public interface Playable extends Interactable {

    /**
     * enum for each game state.
     * <p>
     * START: Shown before each play.
     * LIVE: the user is currently playing the player.
     * GAMEOVER: the user has entered a game over state.
     * PAUSE: game is paused.
     * CUSTOMIZE: customize the playable character.
     */
    enum GameState {
        START,
        LIVE,
        GAMEOVER,
        PAUSE,
        CUSTOMIZE
    }

    /**
     * return an instance of the GameState.
     *
     * @return GameState instance.
     */
    GameState getGameState();

    /**
     * Setter for <code>gameState</code>.
     *
     * @param gameState set gameState.
     */
    void setGameState(GameState gameState);
}