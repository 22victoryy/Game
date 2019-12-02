package com.example.game.level3.world.entities;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.example.game.level3.core.Drawable;
import com.example.game.level3.collision.CollidableObject;
import com.example.game.level3.collision.CollisionInfo;
import com.example.game.level3.collision.CollisionShape;
import com.example.game.level3.core.Updatable;
import com.example.game.level3.core.Vector;

/**
 * Class that encapsulates a natural tree. The sugar glider must dodge trees as it is gliding.
 */
public class Tree extends CollidableObject
        implements Drawable, Updatable {
    private RectF branch;
    private double width;
    private double height;
    private double screenWidth;

    /**
     * Construct a Tree instance
     *
     * @param position    initial position of the tree
     * @param velocity    initial velocity of the tree
     * @param width       the width of the tree
     * @param height      the height of the tree
     * @param up          the orientation of the tree. If up, the tree points up. If not, down.
     * @param screenWidth the width of the screen.
     */
    Tree(Vector position,
         Vector velocity, double width, double height, boolean up, double screenWidth) {
        this.width = width;
        this.height = height;
        this.setVelocity(velocity);
        this.screenWidth = screenWidth;
        this.setCollisionShape(new CollisionShape(-this.width / 2, this.width / 2,
                -this.height / 2, this.height / 2));
        this.branch = new RectF();
        if (up) {
            this.setPosition(position.subtract(new Vector(0.0, this.height / 2)));
        } else {
            this.setPosition(position.add(new Vector(0.0, this.height / 2)));
        }
        this.updateTree();
    }

    private void updateTree() {
        Vector position = this.getPosition();
        if (position.getX() < -this.screenWidth) {
            position.setX(this.screenWidth * 4 + position.getX());
        }
        this.branch.top = (float) (height / 2 + position.getY());
        this.branch.bottom = (float) (-height / 2 + position.getY());
        this.branch.left = (float) (-width / 2 + position.getX());
        this.branch.right = (float) (width / 2 + position.getX());
    }

    /**
     * Handle what occurs when the tree collides with other objects.
     *
     * @param collisionInfo information about the collision.
     */
    @Override
    public void onCollision(CollisionInfo collisionInfo) {
    }

    /**
     * Update where the tree appears on screen.
     *
     * @param deltaTime time interval between each time frame.
     */
    @Override
    public void update(double deltaTime) {
        Vector position = this.getPosition();
        Vector velocity = this.getVelocity();
        position = position.add(velocity.multiply(deltaTime));
        this.setPosition(position);
        this.updateTree();
    }

    /**
     * Draw the tree.
     *
     * @param canvas - instance of Canvas that contains methods for drawing
     *               shapes and text to the screen.
     * @param paint  - instance of Paint containing methods that specifies the colour and text size
     */
    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.argb(
                255, 100, 0, 50));
        canvas.drawRect(branch, paint);
    }
}
