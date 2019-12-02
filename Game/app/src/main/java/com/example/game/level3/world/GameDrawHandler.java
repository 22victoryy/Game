package com.example.game.level3.world;

import android.graphics.Color;

import com.example.game.level3.core.Drawable;
import com.example.game.level3.core.Playable;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * This class is responsible drawing game objects.
 */
class GameDrawHandler {

    /**
     * Draw objects in the game.
     *
     * @param gameWorld GameWorld instance.
     * @param canvas    canvas to paint on.
     * @param paint     paint on the canvas.
     */
    static void draw(GameWorld gameWorld, Canvas canvas, Paint paint) {
        int screenWidth = gameWorld.getScreenWidth();
        int screenHeight = gameWorld.getScreenHeight();
        canvas.drawColor(Color.argb(
                255, 51, 204, 255)); // This is blue.
        for (Drawable d : gameWorld.getDrawableObjects()) {
            d.draw(canvas, paint);
        }
        paint.setTextSize(60);
        if (gameWorld.getGameState() == Playable.GameState.START) {
            start(canvas, paint, screenWidth, screenHeight);
        } else if (gameWorld.getGameState() == Playable.GameState.CUSTOMIZE) {
            customize(canvas, paint, screenWidth, screenHeight);
        } else if (gameWorld.getGameState() == Playable.GameState.LIVE) {
            live(gameWorld, canvas, paint, screenWidth, screenHeight);
        } else if (gameWorld.getGameState() == Playable.GameState.PAUSE) {
            pause(canvas, paint, screenWidth, screenHeight);
        } else if (gameWorld.getGameState() == Playable.GameState.GAMEOVER) {
            gameOver(gameWorld, canvas, paint, screenWidth, screenHeight);
        }
    }

    private static void start(Canvas canvas, Paint paint, int screenWidth, int screenHeight) {
        paint.setTextSize(80);
        paint.setColor(Color.argb(255, 252, 127, 3)); //Orange
        canvas.drawText("GLIDY SUGAR GLIDER",
                screenWidth * 0.1F, screenHeight * 0.2F, paint);
        paint.setTextSize(60);
        canvas.drawText("TAP HERE TO BEGIN",
                screenWidth * 0.2F, screenHeight * 0.5F, paint);
        paint.setColor(Color.WHITE);
        canvas.drawText("Tap here to customize",
                screenWidth * 0.05F, screenHeight * 0.8F, paint);
        canvas.drawText("your gliding strategy",
                screenWidth * 0.05F, screenHeight * 0.85F, paint);
        canvas.drawText("(Tap here to QUIT)",
                screenWidth * 0.05F, screenHeight * 0.95F, paint);
        paint.setColor(Color.MAGENTA);
        canvas.drawOval((screenWidth * 0.85F - screenHeight * 0.06F),
                (screenHeight * 0.88F - screenHeight * 0.07F),
                (screenWidth * 0.85F + screenHeight * 0.06F),
                (screenHeight * 0.88F + screenHeight * 0.07F), paint);
    }

    private static void customize(Canvas canvas, Paint paint, int screenWidth, int screenHeight) {
        paint.setColor(Color.argb(150, 5, 5, 5));
        canvas.drawRect(0.0F, 0.0F, (float) screenWidth,
                (float) screenHeight, paint);
        paint.setColor(Color.WHITE);
        canvas.drawText("Normal Mode",
                screenWidth * 0.05F, screenHeight * 0.15F, paint);
        canvas.drawText("Helium Mode",
                screenWidth * 0.05F, screenHeight * 0.5F, paint);
        canvas.drawText("Fly Mode",
                screenWidth * 0.05F, screenHeight * 0.85F, paint);
        paint.setColor(Color.argb(255, 252, 127, 3)); //Orange
        paint.setTextSize(40);
        canvas.drawText("The sugar glider will glide to the ground.",
                screenWidth * 0.05F, screenHeight * 0.2F, paint);
        canvas.drawText("Tap to go up.",
                screenWidth * 0.05F, screenHeight * 0.25F, paint);
        canvas.drawText("The sugar glider will rise into the air.",
                screenWidth * 0.05F, screenHeight * 0.55F, paint);
        canvas.drawText("Tap to go down.",
                screenWidth * 0.05F, screenHeight * 0.60F, paint);
        canvas.drawText("The sugar glider flies straight into the air.",
                screenWidth * 0.05F, screenHeight * 0.9F, paint);
        canvas.drawText("Tap up or down to fly upwards or downwards.",
                screenWidth * 0.05F, screenHeight * 0.95F, paint);
    }

    private static void live(GameWorld gameWorld, Canvas canvas, Paint paint,
                             int screenWidth, int screenHeight) {
        paint.setColor(Color.WHITE);
        canvas.drawText("PAUSE", screenWidth * 0.1F, screenHeight * 0.9F, paint);
        if (gameWorld.getDeltaTime() != 0.0) {
            paint.setColor(Color.argb(255, 252, 127, 3)); //Orange
            gameWorld.setScore(((int) (2 * gameWorld.getTotalDistance() / screenWidth)));
            canvas.drawText("SCORE: " + gameWorld.getScore(),
                    screenWidth * 0.1F, screenHeight * 0.15F, paint);
            paint.setTextSize(30);
            paint.setColor(Color.argb(155, 252, 127, 3)); //Orange
            canvas.drawText("frames per second: " +
                            (int) gameWorld.getFramesPerSecond(),
                    screenWidth * 0.1F, screenHeight * 0.18F, paint);
            canvas.drawText("total time elapsed: "
                            + (int) gameWorld.getTotalTime() + " s",
                    screenWidth * 0.1F, screenHeight * 0.21F, paint);
        }
    }

    private static void pause(Canvas canvas, Paint paint, int screenWidth, int screenHeight) {
        paint.setColor(Color.WHITE);
        canvas.drawText("RESUME",
                screenWidth * 0.1F, screenHeight * 0.9F, paint);
    }

    private static void gameOver(GameWorld gameWorld, Canvas canvas, Paint paint,
                                 int screenWidth, int screenHeight) {
        paint.setColor(Color.argb(150, 155, 5, 5));
        canvas.drawRect(0.0F, 0.0F, (float) screenWidth,
                (float) screenHeight, paint);
        paint.setColor(Color.RED);
        canvas.drawText("GAME OVER",
                screenWidth * 0.3F, screenHeight * 0.4F, paint);
        paint.setColor(Color.argb(255, 252, 127, 3)); //Orange
        canvas.drawText("Your Score: " + gameWorld.getScore(),
                screenWidth * 0.3F, screenHeight * 0.5F, paint);
        if (gameWorld.getScore() > gameWorld.getHighScore()) {
            gameWorld.setHighScore(gameWorld.getScore());
        }
        canvas.drawText("Your High Score: " + gameWorld.getHighScore(),
                screenWidth * 0.3F,
                screenHeight * 0.55F, paint);
        paint.setTextSize(40);
        canvas.drawText("Number of Glides: " + gameWorld.getTapCount(),
                screenWidth * 0.3F,
                screenHeight * 0.62F, paint);
        int bonusPoints = 0;
        if (gameWorld.getTapCount() != 0 && gameWorld.getScore() > 5) {
            bonusPoints = (10 * gameWorld.getScore()) / gameWorld.getTapCount();
        }
        if (gameWorld.getOnDeathTimer() > 0.33) {
            canvas.drawText("Bonus Points earned: " + bonusPoints,
                    screenWidth * 0.3F,
                    screenHeight * 0.64F, paint);
        }
        if (gameWorld.getOnDeathTimer() > 0.66) {
            if (gameWorld.getTryCount() > 0) {
                canvas.drawText("Number of Retries: " + gameWorld.getTryCount(),
                        screenWidth * 0.3F,
                        screenHeight * 0.77F, paint);
            }
        }
        if (gameWorld.getOnDeathTimer() > 1.0) {
            paint.setColor(Color.WHITE);
            paint.setTextSize(50);
            canvas.drawText("TAP FOR NEW GAME",
                    screenWidth * 0.3F, screenHeight * 0.85F, paint);
        }
    }
}