package com.example.game.level2.items;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.game.level2.core.Drawable;

/**
 * Class Blockade to generate blockades in the game
 */
public class Blockade implements Drawable {

    private Rect rect; // rectangle for blockades
    private Rect rect2; // rectangle for blockades

    private int color; // initialise colour

    /**
     * Constructor for class Blockade
     * @param rectHeight Height of rectangle
     * @param color Colour
     * @param initWidth Initialise Width
     * @param initHeight Initialise Height
     * @param playerGap Gap of the player
     */
    Blockade (int rectHeight, int color, int initWidth, int initHeight, int playerGap) {

        rect = new Rect(0, initHeight, initWidth, // initialize rectangle to generate blockades
                initHeight + rectHeight);
        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        rect2 = new Rect(initWidth + playerGap, initHeight, screenWidth,
                initHeight // initialize rectangle to generate blockades
        + rectHeight);
        this.color = color; // initialise colour
    }

    /**
     * Getter for blockade's rectangle
     * @return rect
     */
    Rect getRect() {
        return rect;
    }

    /**
     * Draw the Blockades
     * @param canvas Canvas
     */
    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rect, paint);
        canvas.drawRect(rect2, paint);
    }

    /**
     * Checks if player rectangle and Blockade rectangle intersect
     * @param player player
     * @return Boolean if intersection
     */
    boolean isBlockadeIntersect(Player player) {
        return Rect.intersects(rect, player.getRect()) || Rect.intersects(rect2, player.getRect());
    }

    /**
     * Increase the height of the rectangles
     * @param y Increase y coordinate
     */
    void increaseHeight(float y) {
        rect.top += y;
        rect.bottom += y;
        rect2.top += y;
        rect2.bottom += y;
    }
}
