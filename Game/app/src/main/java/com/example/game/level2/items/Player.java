package com.example.game.level2.items;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import com.example.game.level2.core.Drawable;

/**
 * Class Player for player
 */
public class Player implements Drawable {

    private Rect rect; // player's rectangle
    private int color; // player's colour

    /**
     * Constructor for class Player
     * @param rect player's rectangle
     * @param color player's colour
     */
    public Player(Rect rect, int color) {
        this.rect = rect;
        this.color = color;
    }

    /**
     * Get player's rectangle
     * @return rect
     */
    public Rect getRect() {
        return rect;
    }

    /**
     * Draw the player
     * @param canvas canvas
     */
    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);

        canvas.drawRect(rect, paint);
    }

    /**
     * Update player's rectangle coordinates
     * @param point Coordinate point
     */
    public void updateRectCoord(Point point) {
        rect.set(point.x - rect.width()/2, point.y - rect.height()/2,
                point.x + rect.width()/2, point.y + rect.height()/2);
    }
}
