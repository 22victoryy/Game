package com.example.game.level3.collision;

import com.example.game.level3.core.Vector;

import java.util.List;
import java.util.ArrayList;

/**
 * The collision shape that is used for collision detection.
 * This was supposed to be a general abstract shape that concrete collision shapes
 * inherit from. This was found to be too complex, so this is just a box.
 */
public class CollisionShape {
    private double[] boundBox = new double[4];


    /**
     * Specify the dimensions of the collision shape.
     *
     * @param minX the leftmost edge
     * @param maxX the rightmost edge
     * @param minY the highest edge
     * @param maxY the lowest edge
     */
    public CollisionShape(double minX, double maxX, double minY, double maxY) {
        this.boundBox[0] = minX;
        this.boundBox[1] = maxX;
        this.boundBox[2] = minY;
        this.boundBox[3] = maxY;
    }


    /**
     * return the edges of the collision shape.
     *
     * @return list of points as represented by vectors for each of the edges
     */
    List<Vector> getEdges() {
        List<Vector> edges = new ArrayList<>();
        edges.add(new Vector(boundBox[1], boundBox[3]));
        edges.add(new Vector(boundBox[0], boundBox[3]));
        edges.add(new Vector(boundBox[0], boundBox[2]));
        edges.add(new Vector(boundBox[1], boundBox[2]));
        return edges;
    }

    /**
     * Determine if a point is inside a collision shape.
     *
     * @param p The point is represented by a vector.
     * @return a boolean value to notify if the point is inside the collision shape.
     */
    boolean pointInside(Vector p) {
        return (p.getX() >= boundBox[0] && p.getX() <= boundBox[1] &&
                p.getY() >= boundBox[2] && p.getY() <= boundBox[3]);
    }
}
