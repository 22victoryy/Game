package com.example.game.level3.core;

import com.example.game.level3.collision.*;

import java.util.List;

/**
 * AbstractGameWorld class for managing objects in the game.
 */
public abstract class AbstractGameWorld extends StatisticsTracker
        implements Playable, Drawable, Updatable {

    private List<Updatable> updatables;
    private List<Drawable> drawables;
    private List<Interactable> interactables;
    private CollisionHandler collisionHandler;
    private boolean signalQuit = false;
    private boolean signalNextLevel = false;
    private AbstractGameWorld nextLevel = null;

    /**
     * Signal to GameView that the user quits this level
     *
     * @return boolean value that signals whether the user has quit or not.
     */
    public boolean isQuit() {
        return this.signalQuit;
    }

    /**
     * Notify that the the user has quit.
     */
    public void quit() {
        this.signalQuit = true;
    }

    /**
     * Signal to GameView that the user quits this level and wants to play a different game.
     *
     * @return boolean value that signals whether the user has quit or not.
     */
    public boolean isLaunchToNextLevel() {
        return this.signalNextLevel;
    }

    /**
     * Notify that the user wants to play a different game.
     */
    public void toggleLaunchToNextLevel() {
        this.signalNextLevel = !this.signalNextLevel;
    }

    /**
     * Getter for the next AbstractGameWorld object.
     *
     * @return the next AbstractGameWorld object.
     */
    public AbstractGameWorld getNextLevel() {
        return this.nextLevel;
    }

    /**
     * Getter for a list of all <code>Updatable</code> objects in the game world.
     *
     * @return list of <code>Updatable</code> objects.
     */
    public List<Updatable> getUpdatableObjects() {
        return this.updatables;
    }

    /**
     * Setter for a list of <code>Updatable</code> instances.
     *
     * @param updatables list of <code>Updatable</code> instances.
     */
    public void setUpdatableObjects(List<Updatable> updatables) {
        this.updatables = updatables;
    }

    /**
     * Getter for a list of all <code>Drawable</code> objects.
     *
     * @return list of <code>Drawable</code> objects.
     */
    public List<Drawable> getDrawableObjects() {
        return this.drawables;
    }

    /**
     * Setter for a list of all <code>Drawable</code> instances.
     *
     * @param drawables list of <code>Drawable</code> instances.
     */
    public void setDrawableObjects(List<Drawable> drawables) {
        this.drawables = drawables;
    }

    /**
     * Getter for the list of all <code>Interactable</code> objects.
     *
     * @return list of all <code>Interactable</code> objects.
     */
    public List<Interactable> getInteractableObjects() {
        return this.interactables;
    }

    /**
     * Setter for a list of all <code>Interactable</code> instances.
     *
     * @param interactables list of all <code>Interactable</code> instances.
     */
    public void setInteractableObjects(List<Interactable> interactables) {
        this.interactables = interactables;
    }

    /**
     * Getter for the collision handler.
     *
     * @return the <code>CollisionHandler</code> instance.
     */
    public CollisionHandler getCollisionHandler() {
        return this.collisionHandler;
    }

    /**
     * Setter for the collision handler.
     *
     * @param collisionHandler the collision handler instance.
     */
    public void setCollisionHandler(CollisionHandler collisionHandler) {
        this.collisionHandler = collisionHandler;
    }

    /**
     * Setter for a list of all Collidable<code></code> instances.
     *
     * @param collidableObjects list of all Collidable<code></code> instances.
     */
    public void setCollidableObjects(List<CollidableObject> collidableObjects) {
        this.collisionHandler.addCollidableObjects(collidableObjects);
    }
}
