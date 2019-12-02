package com.example.game.level3.core;


/**
 * Abstract class for a moving object.
 * This contains a position vector and a velocity vector
 */
public abstract class MovingObject implements Updatable {

    private Vector position;
    private Vector velocity;

    /**
     * Getter for position.
     *
     * @return position vector.
     */
    public final Vector getPosition() {
        return position;
    }

    /**
     * Setter for position.
     *
     * @param position set a new position for the moving object.
     */
    protected final void setPosition(Vector position) {
        this.position = position;
    }

    /**
     * Getter for velocity.
     *
     * @return the velocity of the moving object.
     */
    public final Vector getVelocity() {
        return velocity;
    }

    /**
     * Setter for velocity.
     *
     * @param velocity set the velocity of the moving object.
     */
    protected final void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }
}
