package com.example.game.level3.core;

import android.view.MotionEvent;

/**
 * <code>Interactable</code> interface.
 * This interface is meant to make a derived
 * class respond to taps on the screen
 */
public interface Interactable {
    /**
     * Respond to touch input.
     *
     * @param deltaTime   - interval between each time frame.
     * @param motionEvent - class that encapsulates touch input
     * @return boolean value to notify what was done with touch input.
     */
    boolean onTouchEvent(double deltaTime, MotionEvent motionEvent);
}
