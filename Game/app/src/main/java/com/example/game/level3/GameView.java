package com.example.game.level3;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.os.SystemClock;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.Stack;

import com.example.game.level3.core.AbstractGameWorld;

import com.example.game.level3.world.GameWorld;

import com.example.game.statistics.StatisticsManager;

/**
 * Portions of this code are borrowed from FishTankView.java from the fish tank assignment.
 * This is allowed; please see the Discourse question 'Copying files from FishTank'.
 * Note that some of the original comments have been left in.
 * <p>
 * Both the original FishTankView class and this GameView class inherit SurfaceView.
 * The SurfaceView class contains the onTouchEvent(MotionEvent motionEvent) which can be overridden
 * to so that it is possible to respond to motionEvent instances which encapsulate touch input.
 * So then the only things needed to create interactive android app are a SurfaceView class to
 * inherit from, the BonusThread class to manage the SurfaceView,
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
 * (question by StackOverflow user RyoxSinfar,
 * answers by Dariusz Bacinski [https://stackoverflow.com/a/3143190],
 * MSA and Suragch [https://stackoverflow.com/a/17316038],
 * Someone Somewhere [https://stackoverflow.com/a/5808272],
 * and Rohit Shrivastava [https://stackoverflow.com/a/50055991]),
 * As well as
 * https://developer.android.com/reference/android/view/MotionEvent.html
 * <p>
 * This class also manages time and the time interval between each frame.
 * In order to find these the following stack exchange question was consulted:
 * https://stackoverflow.com/questions/16516888/how-to-get-current-date-time-in-milliseconds-in-android
 * which led to another site, which says that SystemClock.elapsedRealtime() is the best solution
 * (credit goes to Sangsoo Nam):
 * http://sangsoonam.github.io/2017/03/01/do-not-use-curenttimemillis-for-time-interval.html
 */

class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private Stack<AbstractGameWorld> gameWorldStack;
    private Paint paint;
    private MainThread thread;
    private Context context;
    private double lastTime;
    private double time;
    private Intent intent;
    private double deltaTime = 1.0 / 60.0;

    /**
     * Constructor for GameView.
     *
     * @param context The context of the current activity.
     * @param intent  The intent for the next activity.
     */
    public GameView(Context context, Intent intent) {
        super(context);
        gameWorldStack = new Stack<>();
        this.context = context;
        this.intent = intent;
        this.paint = new Paint();
        lastTime = 0.0;
        time = SystemClock.elapsedRealtime();
        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
        GameWorld gameWorld = new GameWorld(screenWidth, screenHeight);
        gameWorldStack.push(gameWorld);
        getHolder().addCallback(this);
        thread = new MainThread(this.getHolder(), this);
        setFocusable(true);
    }

    /**
     * Respond to the surface being changed.
     *
     * @param holder instance of <code>SurfaceHolder</code>
     * @param format the format
     * @param width  the width
     * @param height the height
     */
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    /**
     * Respond to the surface being destroyed.
     *
     * @param holder instance of <code>SurfaceHolder</code>
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
     * Respond to the surface being created.
     *
     * @param holder instance of <code>SurfaceHolder</code>
     */
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new MainThread(getHolder(), this);
        thread.setRunning(true);
        thread.start();
    }

    /**
     * Draw the canvas.
     *
     * @param canvas draw the canvas.
     */
    @Override
    public void draw(Canvas canvas) {
        if (canvas != null && !gameWorldStack.empty()) {
            super.draw(canvas);
            gameWorldStack.peek().draw(canvas, paint);
        }
    }

    /**
     * Update the GameView.
     */
    public void update() {
        if (!gameWorldStack.empty()) {
            gameWorldStack.peek().update(this.deltaTime);
            lastTime = time;
            // idea to use SystemClock.elapsedRealtime() method from
            // https://sangsoonam.github.io/2017/03/01/
            // do-not-use-curenttimemillis-for-time-interval.html
            time = SystemClock.elapsedRealtime();
            this.deltaTime = (time - lastTime) / 1000;
        }
    }

    /**
     * Handle touch input from the user.
     *
     * @param motionEvent object that encapsulates touch input to the screen.
     * @return boolean to notify what was done with the touch input.
     */
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!gameWorldStack.empty()) {
            boolean retVal = gameWorldStack.peek().onTouchEvent(this.deltaTime, motionEvent);
            if (gameWorldStack.peek().isQuit()) {
                this.end(gameWorldStack.pop());
                if (gameWorldStack.empty()) {
                    if (this.intent != null) {
                        context.startActivity(intent);
                    } else {
                        this.surfaceDestroyed(this.getHolder());
                    }
                }
                return false;
            } else if (gameWorldStack.peek().isLaunchToNextLevel()) {
                gameWorldStack.peek().toggleLaunchToNextLevel();
                gameWorldStack.push(gameWorldStack.peek().getNextLevel());
            }
            return retVal;
        } else {
            return false;
        }
    }


    /**
     * This method ends a game level.
     */
    public void end(AbstractGameWorld gameWorld) {
        if (intent != null) {
            /*
             * To get stuff from intent look at
             * https://stackoverflow.com/a/14333555
             * question by Bias Tegaralaga (https://stackoverflow.com/users/696259/bias-tegaralaga)
             * answer by ρяσѕρєя K (https://stackoverflow.com/users/1202025)
             * and Peter Mortensen (https://stackoverflow.com/users/63550)
             */
            Bundle bundle = intent.getExtras();
            StatisticsManager statisticsManager;
            if ((statisticsManager = (StatisticsManager) bundle.getSerializable(
                    "statisticsManager")) != null) {
                statisticsManager.addScore(gameWorld.getHighScore());
                statisticsManager.addTries(gameWorld.getTryCount());
                statisticsManager.addBonusPoints(gameWorld.getBonusPoints());
            } else {
                Log.wtf(null, "Intent should never be null.");
            }
        }
    }

}


