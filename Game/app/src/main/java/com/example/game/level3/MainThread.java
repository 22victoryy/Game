package com.example.game.level3;


import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * This code is mostly taken from BonusThread.java from the fish tank assignment.
 * This is allowed; please see the Discourse question 'Copying files from FishTank'.
 * Some of the comments and coding style from the original have been retained.
 * <p>
 * Hacky way to manage threading and updates.
 */
public class MainThread extends Thread {

    private GameView gameView;
    private final SurfaceHolder surfaceHolder;
    private boolean isRunning;


    /**
     * Construct the thread.
     *
     * @param surfaceHolder the canvas container.
     * @param view          where the game view items are drawn.
     */
    MainThread(SurfaceHolder surfaceHolder, GameView view) {
        this.surfaceHolder = surfaceHolder;
        this.gameView = view;
    }

    /**
     * Run the thread.
     */
    @Override
    public void run() {
        while (isRunning) {
            Canvas canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    this.gameView.update();
                    this.gameView.draw(canvas);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            try {
                sleep(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Set the thread as running.
     *
     * @param isRunning whether the thread is running or not.
     */
    void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }
}
