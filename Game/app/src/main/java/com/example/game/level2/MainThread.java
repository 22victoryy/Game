package com.example.game.level2;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Class to keep the game running.
 */
public class MainThread extends Thread {

    private SurfaceHolder surfaceHolder;
    private GameView gameView;
    private boolean isRunning;
    private Canvas canvas;

    /**
     * Constructor for MainThread
     * @param surfaceHolder surfaceHolder
     * @param gameView gameView
     */
    MainThread(SurfaceHolder surfaceHolder, GameView gameView) {
        this.surfaceHolder = surfaceHolder;
        this.gameView = gameView;
    }

    /**
     * Getter for canvas
     * @return Canvas
     */
    public Canvas getCanvas() {
        return canvas;
    }

    /**
     * Set the game to keep running
     * @param isRunning isRunning
     */
    void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    /**
     * Keep the game running
     */
    @Override
    public void run() {
        while(isRunning) {
            canvas = null;
            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder)
                {
                    this.gameView.update();
                    this.gameView.draw(canvas);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
