package com.example.game.level3.world.entities;

import com.example.game.level3.core.Drawable;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Canvas;

/**
 * Class that encapsulates the ground.
 */
public class Ground implements Drawable {
    private double left, right, top, bottom;

    /**
     * Make the ground.
     *
     * @param left   - left most edge of the ground
     * @param right  - right most edge of the ground
     * @param top    - top most edge of the ground
     * @param bottom - bottom most edge of the ground
     */
    public Ground(double left, double right, double top, double bottom) {
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
    }

    /**
     * Draw the ground.
     *
     * @param canvas - instance of Canvas that contains methods for drawing
     *               shapes and text to the screen.
     * @param paint  - instance of Paint containing methods that specifies the colour and text size
     */
    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.argb(255, 31, 145, 25));
        canvas.drawRect((float) left, (float) top, (float) right, (float) bottom, paint);
    }
}
