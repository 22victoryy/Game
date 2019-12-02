package com.example.game.level3.world.entities;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.example.game.level3.core.Drawable;
import com.example.game.level3.collision.CollidableObject;
import com.example.game.level3.collision.CollisionInfo;
import com.example.game.level3.collision.CollisionShape;
import com.example.game.level3.core.Updatable;
import com.example.game.level3.core.Vector;
import com.example.game.level3.core.Playable;

/**
 * Draw a sugar glider. This is what the player plays as.
 */
public class SugarGlider extends CollidableObject
        implements Drawable, Updatable, Playable {
    private GameState gameState = GameState.LIVE;
    private GlideStrategy glideStrategy = GlideStrategy.JUMP;
    private double width = 100.0;
    private double height = 60.0;
    private double gravity = 700.0;
    private double jumpSpeed = 500.0;
    private double sugarGliderSpeed;
    private int furColor = Color.GRAY;

    /**
     * Construct a SugarGlider instance.
     *
     * @param position Initial position of the sugar glider.
     * @param velocity Initial velocity of the sugar glider.
     **/
    public SugarGlider(double sugarGliderSpeed, Vector position, Vector velocity) {
        this.sugarGliderSpeed = sugarGliderSpeed;
        this.setPosition(position);
        this.setVelocity(velocity);
        this.setCollisionShape(new CollisionShape(
                -width / 2, width / 2, -height / 2, height / 2));
    }

    /**
     * Setter for the gliding strategy, which dictates how the sugar glider glides in the air.
     *
     * @param glideStrategy <code>GlideStrategy</code> enum class.
     */
    public void setGlideStrategy(GlideStrategy glideStrategy) {
        this.glideStrategy = glideStrategy;
    }

    /**
     * Getter for the gliding strategy, which dictates how the sugar glider glides in the air.
     *
     * @return <code>GlideStrategy</code> enum class.
     */
    public GlideStrategy getGlideStrategy() {
        return this.glideStrategy;
    }

    /**
     * Handle collisions between the sugar glider and other objects.
     * When the sugar glider collides with a tree it's game over.
     *
     * @param collisionInfo information about the collision.
     */
    @Override
    public void onCollision(CollisionInfo collisionInfo) {
        gameState = GameState.GAMEOVER;
    }

    /**
     * Update the motion of the sugar glider.
     *
     * @param deltaTime time interval between each time frame.
     */
    @Override
    public void update(double deltaTime) {
        Vector velocity = this.getVelocity();
        Vector position = this.getPosition();
        if (gameState == GameState.LIVE) {
            if (glideStrategy == GlideStrategy.JUMP) {
                velocity.setY(velocity.getY() + gravity * deltaTime);
            } else if (glideStrategy == GlideStrategy.FALL) {
                velocity.setY(velocity.getY() - gravity * deltaTime);
            }
            position.setX(position.getX() + velocity.getX() * deltaTime);
            position.setY(position.getY() + velocity.getY() * deltaTime);
        } else if (gameState == GameState.GAMEOVER) {
            velocity.setY(velocity.getY() + gravity * deltaTime);
            velocity.setX(-sugarGliderSpeed);
            position = position.add(velocity.multiply(deltaTime));
            this.setVelocity(velocity);
            this.setPosition(position);
        }
    }

    /**
     * Draw the sugar glider.
     *
     * @param canvas - instance of Canvas that contains methods for drawing
     *               shapes and text to the screen.
     * @param paint  - instance of Paint containing methods that specifies the colour and text size
     */
    @Override
    public void draw(Canvas canvas, Paint paint) {
        Vector position = this.getPosition();
        drawArmsAndLegs(position, canvas, paint);
        drawBody(position, canvas, paint);
        drawEye(position, canvas, paint);
        drawTail(position, canvas, paint);
    }

    private void drawArmsAndLegs(Vector position, Canvas canvas, Paint paint) {
        paint.setColor(Color.argb(255, 255, 153, 255));
        paint.setStrokeWidth(6.0F);
        canvas.drawLine((float) (position.getX() - 12 * width / 20),
                (float) (position.getY() - 12 * height / 20),
                (float) (position.getX() + 12 * width / 20),
                (float) (position.getY() + 12 * height / 20), paint);
        canvas.drawLine((float) (position.getX() - 12 * width / 20),
                (float) (position.getY() + 12 * height / 20),
                (float) (position.getX() + 12 * width / 20),
                (float) (position.getY() - 12 * height / 20), paint);
    }

    private void drawBody(Vector position, Canvas canvas, Paint paint) {
        paint.setStrokeWidth(0.0F);
        paint.setColor(furColor);
        canvas.drawRect((float) (position.getX() - width / 2),
                (float) (position.getY() + height / 2),
                (float) (position.getX() + width / 2),
                (float) (position.getY() - height / 2), paint);
    }

    private void drawEye(Vector position, Canvas canvas, Paint paint) {
        paint.setColor(Color.BLACK);
        canvas.drawCircle((float) (position.getX() + 0.75 * width / 2.0),
                (float) (position.getY() + 0.5 * height / 2.0), (float) (width / 8.0), paint);
        canvas.drawCircle((float) (position.getX() + 0.75 * width / 2.0),
                (float) (position.getY() - 0.5 * height / 2.0), (float) (width / 8.0), paint);
    }

    private void drawTail(Vector position, Canvas canvas, Paint paint) {
        paint.setColor(furColor);
        paint.setColor(Color.argb(255, 83, 90, 92));
        canvas.drawOval((float) (position.getX() - width / 2),
                (float) (position.getY() - 6 * height / 4),
                (float) (position.getX() - 0.3 * width / 2),
                (float) (position.getY()), paint);
    }

    /**
     * Allow the player to control the sugar glider using touch input.
     *
     * @param deltaTime   - interval between each time frame.
     * @param motionEvent - class that encapsulates touch input
     * @return false.
     */
    @Override
    public boolean onTouchEvent(double deltaTime, MotionEvent motionEvent) {
        Vector position = this.getPosition();
        Vector velocity;
        if (gameState == GameState.LIVE) {
            if (glideStrategy == GlideStrategy.JUMP) {
                velocity = new Vector(0.0, -jumpSpeed);
                this.setVelocity(velocity);
            } else if (glideStrategy == GlideStrategy.FLY) {
                double y = motionEvent.getY();
                if (y < position.getY()) {
                    velocity = new Vector(0.0, -Math.abs(y - position.getY()));
                    this.setVelocity(velocity);
                } else if (y > position.getY()) {
                    velocity = new Vector(0.0, Math.abs(y - position.getY()));
                    this.setVelocity(velocity);
                }
            } else if (glideStrategy == GlideStrategy.FALL) {
                velocity = new Vector(0.0, jumpSpeed);
                this.setVelocity(velocity);
            }
        }
        return false;
    }

    /**
     * Getter for the current game state.
     *
     * @return <code>GameState</code> enum object.
     */
    @Override
    public GameState getGameState() {
        return gameState;
    }

    /**
     * Setter for the current game state.
     *
     * @param gameState the <code>GameState</code> enum object.
     */
    @Override
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
}
