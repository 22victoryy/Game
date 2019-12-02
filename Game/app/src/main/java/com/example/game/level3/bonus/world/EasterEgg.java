package com.example.game.level3.bonus.world;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.game.level3.core.Vector;

import com.example.game.level3.core.*;
import com.example.game.level3.collision.*;

/**
 * Easter Egg class.
 */
class EasterEgg extends CollidableObject implements Drawable, Updatable {

    private double width;
    private double height;
    private int screenWidth;
    private int screenHeight;
    private int color;

    /**
     * Constructor.
     *
     * @param position     Initial position of the Easter egg.
     * @param screenWidth  Width of the screen.
     * @param screenHeight Height of the screen.
     */
    EasterEgg(Vector position, int screenWidth, int screenHeight) {
        this.height = screenHeight * 0.1;
        this.width = screenWidth * 0.14;
        this.setPosition(position);
        this.setVelocity(new Vector(0.0, 0.0));
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.setCollisionShape(new CollisionShape(
                -width / 2, width / 2, -height / 2, height / 2));
        this.color = this.getColor();
        // this.setNewLocation();
    }

    /**
     * Handle collisions with other objects. If the player
     * catches an easter egg and triggers a collision,
     * instead of removing it from
     * the game and create a new Easter egg, recycle it
     * by setting a new location for it.
     *
     * @param collisionInfo information about the collision.
     */
    @Override
    public void onCollision(CollisionInfo collisionInfo) {
        this.setNewLocation();
    }

    /**
     * Draw the Easter egg/
     *
     * @param canvas - instance of Canvas that contains methods for drawing
     *               shapes and text to the screen.
     * @param paint  - instance of Paint containing methods that specifies the colour and text size
     */
    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(color);
        Vector position = this.getPosition();
        canvas.drawOval((float) (position.getX() - width / 2),
                (float) (position.getY() - height / 2),
                (float) (position.getX() + width / 2),
                (float) (position.getY() + height / 2), paint);
    }

    /**
     * Update the position and velocity of the Easter egg.
     *
     * @param deltaTime time interval between each time frame.
     */
    @Override
    public void update(double deltaTime) {
        double gravity = 700;
        Vector position = this.getPosition();
        Vector velocity = this.getVelocity();
        if (position.getY() > 1.1 * screenHeight) {
            this.setNewLocation();
        }
        velocity.setY(velocity.getY() + gravity * deltaTime);
        position.setX(position.getX() + velocity.getX() * deltaTime);
        position.setY(position.getY() + velocity.getY() * deltaTime);
    }

    private void setNewLocation() {
        Vector position = this.getPosition();
        Vector velocity = this.getVelocity();
        velocity = velocity.multiply(0.25);
        this.setVelocity(velocity);
        position.setY(-3 * screenHeight);
        position.setX(Math.random() * screenWidth);
        this.color = this.getColor();
    }

    private int getColor() {
        int color;
        double randNumber = 5.0 * Math.random();
        if (randNumber < 1) {
            color = Color.RED;
        } else if (randNumber < 2) {
            color = Color.BLUE;
        } else if (randNumber < 3) {
            color = Color.GREEN;
        } else if (randNumber < 4) {
            color = Color.CYAN;
        } else {
            color = Color.MAGENTA;
        }
        return color;
    }
}