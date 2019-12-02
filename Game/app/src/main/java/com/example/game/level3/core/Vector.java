package com.example.game.level3.core;

/**
 * Vector in 2D
 */
public class Vector {

    private double x = 0.0;
    private double y = 0.0;

    /**
     * Empty constructor for instantiate.
     */
    public Vector() {
    }

    /**
     * Overloading vector constructor
     *
     * @param x Set the x value,
     * @param y Set the y value.
     */
    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Setter for the x-direction of the vector.
     *
     * @param x set the x data member.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Setter for the y-direction of the vector.
     *
     * @param y set the y data member.
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Getter for the x-direction of the vector.
     *
     * @return the x data member.
     */
    public double getX() {
        return x;
    }

    /**
     * Getter for the y-direction of the vector.
     *
     * @return the y data member.
     */
    public double getY() {
        return y;
    }

    /**
     * Add two vectors together to obtain a new vector.
     *
     * @param v the vector to add.
     * @return the resulting vector after the addition operation.
     */
    public Vector add(Vector v) {
        return new Vector(this.x + v.x, this.y + v.y);
    }

    /**
     * Subtract vectors together to obtain a new vector.
     *
     * @param v the vector to subtract.
     * @return the resulting vector.
     */
    public Vector subtract(Vector v) {
        return new Vector(this.x - v.x, this.y - v.y);
    }

    /**
     * Multiply a vector by a scalar value.
     *
     * @param scalar the scalar value to multiply the vector by.
     * @return The scalar multiplied vector.
     */
    public Vector multiply(double scalar) {
        return new Vector(scalar * this.x, scalar * this.y);
    }
}
