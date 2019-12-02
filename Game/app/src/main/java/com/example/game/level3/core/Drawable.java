package com.example.game.level3.core;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Drawable interface that allows a
 * derived object to be drawn to screen,
 * using Canvas and Paint.
 */
public interface Drawable {
    /**
     * draw method for the Drawable interface
     *
     * @param canvas - instance of Canvas that contains methods for drawing
     *               shapes and text to the screen.
     * @param paint  - instance of Paint containing methods that specifies the colour and text size
     *               for the canvas to use.
     */
    void draw(Canvas canvas, Paint paint);
}
