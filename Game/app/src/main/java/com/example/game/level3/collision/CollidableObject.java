package com.example.game.level3.collision;

import com.example.game.level3.core.MovingObject;

/**
 * Abstract class to encapsulate objects that can collide with one another.
 */
public abstract class CollidableObject extends MovingObject {
    private CollisionShape collisionShape;

    /**
     * Constructor for the <code>CollidableObject</code>.
     */
    protected CollidableObject() {
        collisionShape = new CollisionShape(-1.0, 1.0, -1.0, 1.0);
    }

    /**
     * Getter for the collision shape
     *
     * @return the collision shape
     */
    CollisionShape getCollisionShape() {
        return this.collisionShape;
    }

    /**
     * Setter for the collision shape
     *
     * @param collisionShape change the <code>collisionShape</code> member
     */
    protected void setCollisionShape(CollisionShape collisionShape) {
        this.collisionShape = collisionShape;
    }

    /**
     * Do something to the object when it collides with another object,
     *
     * @param collisionInfo information about the collision.
     */
    public abstract void onCollision(CollisionInfo collisionInfo);
}
