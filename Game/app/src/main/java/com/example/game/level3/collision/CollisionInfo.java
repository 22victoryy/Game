package com.example.game.level3.collision;

import com.example.game.level3.core.Vector;


/**
 * Class that encapsulates collision information.
 */
public class CollisionInfo {
    private Vector velocityOfThis;
    private Vector velocityOfOther;

    /**
     * Constructor.
     *
     * @param collisionVector1 the velocity of the first object during collision
     * @param collisionVector2 the velocity of the second object during collision
     */
    CollisionInfo(Vector collisionVector1, Vector collisionVector2) {
        velocityOfThis = collisionVector1;
        velocityOfOther = collisionVector2;
    }

    /**
     * Swap collision velocity vectors.
     */
    void swap() {
        Vector temp = new Vector(velocityOfThis.getX(), velocityOfThis.getY());
        this.velocityOfThis = this.velocityOfOther;
        this.velocityOfOther = temp;
    }
}