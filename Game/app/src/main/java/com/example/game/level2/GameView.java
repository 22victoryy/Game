package com.example.game.level2;

import android.content.Context;
import android.graphics.Canvas;

import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import com.example.game.level2.MainThread;
import com.example.game.statistics.StatisticsManager;
import com.example.game.level2.states.InstanceManager;

/**
 * Portions of this code are borrowed from FishTankView.java from the fish tank assignment, and
 * modified for design fit for level 2.
 * This is allowed; please see the Discourse question 'Copying files from FishTank'.
 * <p>
 * Both the original FishTankView class and this GameView class inherit SurfaceView.
 * The SurfaceView class contains the onTouchEvent(MotionEvent motionEvent) which can be overridden
 * to so that it is possible to respond to motionEvent instances which encapsulate touch input.
 * So then the only things needed to create interactive android app are a SurfaceView class to
 * inherit from, the MainThread class to manage the SurfaceView,
 * an Activity class to launch the SurfaceView,
 * canvas and paint objects and their methods to draw shapes and text to screen, and
 * the motionEvent object that comes from the onTouchEvent method in SurfaceView.
 * <p>
 * For knowing how the SurfaceView class works, the code in FishTankView.java was studied,
 * as well as the following references:
 * https://developer.android.com/reference/android/view/SurfaceView
 * https://developer.android.com/reference/android/view/View.html
 * <p>
 * For being able to use paint and canvas, the code for fish tank was studied, as well as
 * the following references:
 * https://developer.android.com/reference/android/graphics/Canvas
 * https://developer.android.com/reference/android/graphics/Paint
 * <p>
 * For being able to respond to touch input, the following StackOverflow question and answers
 * were consulted:
 * https://stackoverflow.com/questions/3142670/how-do-i-detect-touch-input-on-the-android
 * (question by StackOverflow user RyoxSinfar
 * answers by Dariusz Bacinski [https://stackoverflow.com/a/3143190],
 * https://developer.android.com/reference/android/view/MotionEvent.html
 * /
 * <p>
 * /**
 * Class GameView for game container
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread thread;

    private InstanceManager manager;

    /**
     * Constructor for Level 2 GameView
     *
     * @param context           Context
     * @param statisticsManager StatisticsManager
     * @param isHardMode        Difficulty
     */
    public GameView(Context context, StatisticsManager statisticsManager, boolean isHardMode) {
        super(context);

        getHolder().addCallback(this);

        this.manager = new InstanceManager(context, statisticsManager, isHardMode);

        thread = new MainThread(getHolder(), this);

        setFocusable(true);
    }

    /**
     * surfaceChanged
     *
     * @param holder SurfaceHolder
     * @param format format
     * @param width  Width
     * @param height Height
     */
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    /**
     * SurfaceCreated
     *
     * @param holder SurfaceHolder
     */
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new MainThread(getHolder(), this);

        thread.setRunning(true);
        thread.start();
    }

    /**
     * Implements SurfaceDestroyed
     *
     * @param holder SurfaceHolder
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    /**
     * onTouch event to execute certain actions
     *
     * @param event MotionEvent
     * @return true
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        manager.atTouch(event);
        return true;
    }

    /**
     * Update all the items in the game
     */
    public void update() {
        manager.update();
    }

    /**
     * Draw all the items of the game
     *
     * @param canvas canvas
     */
    public void draw(Canvas canvas) {
        if (canvas != null) {
            super.draw(canvas);
            manager.draw(canvas);
        }
    }
}

