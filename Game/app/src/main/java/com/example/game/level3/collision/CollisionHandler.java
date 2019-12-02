package com.example.game.level3.collision;

import java.util.List;
import java.util.ArrayList;

import com.example.game.level3.core.Vector;

/**
 * This class is responsible for handling all of the collisions
 * in the game world. It iterates over each <code>CollidableObject</code> and it
 * checks to see if their collision shape overlaps. If they do
 * overlap, this tells the <code>CollidableObject</code> to respond to the collision.
 * <p>
 * I had previous experience in coding collision detection in assignment 2 (the assignment is called
 * 'Tag You're It' and it involves witting code for various games of tag) from
 * CSC148 in the 2019 summer session (taught by Misha Schwartz).
 * The main point of the assignment was implementing QuadTrees and 2DTrees
 * (link to assignment: <a href="https://q.utoronto.ca/courses/95184/assignments/161802">
 * https://q.utoronto.ca/courses/95184/assignments/161802</a>.
 * <p>
 * These were difficult to implement so instead we use a regular for loop
 * and some if statements to check if each object is colliding with another.
 */
public class CollisionHandler {
    private List<CollidableObject> collidableObjects;

    /**
     * Constructor for <code>CollisionHandler</code>
     */
    public CollisionHandler(List<CollidableObject> collidableObjects) {
        this.collidableObjects = collidableObjects;
    }

    /**
     * Add collision objects to the collision handler.
     *
     * @param collidableObjects List of objects that participate in collisions.
     */
    public void addCollidableObjects(List<CollidableObject> collidableObjects) {
        this.collidableObjects.addAll(collidableObjects);
    }

    /**
     * Remove collision objects from the collision handler.
     */
    public void clearCollidableObjects() {
        this.collidableObjects = new ArrayList<>();
    }

    private CollisionInfo collide(CollidableObject a, CollidableObject b) {
        Vector point;
        List<Vector> edges = b.getCollisionShape().getEdges();
        for (int i = 0; i < edges.size(); i++) {
            point = edges.get(i);
            point.setX(point.getX() + b.getPosition().getX() - a.getPosition().getX());
            point.setY(point.getY() + b.getPosition().getY() - a.getPosition().getY());
        }
        for (int j = 0; j < edges.size(); j++) {
            if (a.getCollisionShape().pointInside(edges.get(j))) {
                return new CollisionInfo(a.getVelocity(), b.getVelocity());
            }
        }
        return null;
    }

    /**
     * Process collisions between all of the objects that can participate in collisions.
     */
    public void processCollisions() {
        CollisionInfo collisionInfo;
        for (int i = 0; i < collidableObjects.size(); i++) {
            for (int j = 0; j < i; j++) {
                collisionInfo = collide(collidableObjects.get(i), collidableObjects.get(j));
                if (collisionInfo != null) {
                    collidableObjects.get(i).onCollision(collisionInfo);
                    collisionInfo.swap();
                    collidableObjects.get(j).onCollision(collisionInfo);
                }
            }
        }
    }
}
