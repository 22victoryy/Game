package com.example.game.level3.bonus.world;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.example.game.level3.core.*;
import com.example.game.level3.collision.*;

/**
 * Class to collect easter eggs. This is what the user plays as.
 */
class EasterEggCollector extends CollidableObject implements Drawable,
        Updatable, Playable, StatisticsTrackable {

    private double height = 200;
    private double width = 200;
    private int screenWidth;
    private GameState gameState = GameState.START;
    private StatisticsTracker statisticsTracker;

    /**
     * Constructor.
     *
     * @param screenWidth  width of the screen.
     * @param screenHeight height of the screen.
     */
    EasterEggCollector(int screenWidth, int screenHeight) {
        Vector position = new Vector();
        Vector velocity = new Vector();
        position.setX(screenWidth * 0.5);
        position.setY(screenHeight * 0.85);
        this.setPosition(position);
        this.setVelocity(velocity);
        // this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.setCollisionShape(new CollisionShape(
                -width / 2, width / 2, -height / 2, height / 2));
    }

    /**
     * Handle collision with other objects.
     * If there is a collision with the Easter egg,
     * meaning the Easter egg was caught, increment the bonus points.
     *
     * @param collisionInfo information about the collision.
     */
    @Override
    public void onCollision(CollisionInfo collisionInfo) {
        this.updateStatistics();
    }

    /**
     * Draw the Easter egg collector.
     *
     * @param canvas - instance of Canvas that contains methods for drawing
     *               shapes and text to the screen.
     * @param paint  - instance of Paint containing methods that specifies the colour and text size
     */
    @Override
    public void draw(Canvas canvas, Paint paint) {
        Vector position = this.getPosition();
        paint.setColor(Color.BLACK);
        canvas.drawRect((float) (position.getX() - width / 2),
                (float) (position.getY() - height / 2),
                (float) (position.getX() + width / 2),
                (float) (position.getY() + height / 2), paint);
    }

    /**
     * Update the Easter egg collector's position and velocity.
     *
     * @param deltaTime time interval between each time frame.
     */
    @Override
    public void update(double deltaTime) {
        Vector position = this.getPosition();
        Vector velocity = this.getVelocity();
        if (position.getX() > screenWidth || position.getX() < 0.0) {
            velocity.setX(-velocity.getX());
        }
        position.setX(position.getX() + velocity.getX() * deltaTime);
        position.setY(position.getY() + velocity.getY() * deltaTime);
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
        double x = motionEvent.getX();
        this.getPosition().setX(x);
        return false;
    }

    /**
     * Set the statistics tracker.
     *
     * @param statisticsTracker the <code>StatisticsTracker</code>.
     */
    @Override
    public void setStatisticsTracker(StatisticsTracker statisticsTracker) {
        this.statisticsTracker = statisticsTracker;
    }

    /**
     * Update the bonus points upon catching an Easter egg.
     */
    @Override
    public void updateStatistics() {
        if (statisticsTracker != null) {
            statisticsTracker.addToBonusPoints(1);
        }
    }

    /**
     * Getter for the current game state.
     *
     * @return <code>GameState</code> enum object.
     */
    @Override
    public GameState getGameState() {
        return this.gameState;
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
