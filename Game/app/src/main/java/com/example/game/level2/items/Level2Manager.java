package com.example.game.level2.items;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.game.statistics.StatisticsManager;
import com.example.game.level2.core.Drawable;
import com.example.game.level2.core.Updatable;

import java.util.ArrayList;
import static java.lang.Math.toIntExact;

/**
 * Class Level2Manager to manage blockades, players, and game score
 */
public class Level2Manager implements Drawable, Updatable {

    private ArrayList<Blockade> Blockades; // Arraylist of blockades

    private int playerGap; // gap of player
    private int blockadeGap; // gap of blockades
    private int blockadeHeight; // height of blockades
    private int color; // color
    private int speed; // game speed
    private int scoreCounter;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    private long startTime; // continue to increment start time

    private StatisticsManager statisticsManager;

    private boolean isHardMode;

    /**
     * Constructor for Level2Manager
     * @param playerGap Gap of player
     * @param blockadeGap Gap of blockade
     * @param blockadeHeight Height of blockade
     * @param color Color
     * @param statisticsManager statisticsManager
     * @param isHardMode Difficulty
     */
    public Level2Manager(int playerGap, int blockadeGap, int blockadeHeight,
                         int color, StatisticsManager statisticsManager, boolean isHardMode){
        startTime = System.currentTimeMillis();

        Blockades = new ArrayList<>();

        this.blockadeHeight = blockadeHeight;
        this.blockadeGap = blockadeGap;
        this.color = color;
        this.playerGap = playerGap;
        this.scoreCounter = 0;
        this.statisticsManager = statisticsManager;
        this.isHardMode = isHardMode;

        createBlockades();
    }

    /**
     * Collision checker
     * @param player player
     * @return true if collided else false
     */
    public boolean isCollide(Player player) {
        for (Blockade b: Blockades) {
            if (b.isBlockadeIntersect(player)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Create Blockades
     */
    private void createBlockades() {
        // higher index --> lower screen value
        int currHeight = -5 * screenHeight/4;
        while (currHeight < 0) {
            int initWidth = (int) (Math.random() * (screenWidth - playerGap));
            Blockades.add(new Blockade(blockadeHeight, color, initWidth, currHeight, playerGap));
            currHeight += blockadeHeight + blockadeGap;
        }
    }

    /**
     * Get gameSpeed depending on difficulty
     */
    private void setSpeed() {
        if (isHardMode) {
            speed = 5;
        }
        else {
            speed = 2;
        }
    }

    /**
     * Getter for speed
     * @return speed
     */
    private int getSpeed() {
        return speed;
    }

    /**
     * Draw all the blockades and display score
     * @param canvas canvas
     */
    public void draw(Canvas canvas) {
        for (Blockade b: Blockades) {
            b.draw(canvas);
        }

        Paint paint = new Paint();
        paint.setTextSize(100);
        paint.setColor(Color.GREEN);
        canvas.drawText("" + statisticsManager.getScore(), 80,
                75
                , paint);
    }

    /**
     * Update the score and blockade generation
     */
    public void update() {
        int timePassed = toIntExact(System.currentTimeMillis() - startTime);
        startTime = System.currentTimeMillis();

        setSpeed();

        float valToSpeedUp = getSpeed(); // speed up depending on difficulty

        float speedUpRate = valToSpeedUp * (screenHeight/ 10000.0f);

        for (Blockade b: Blockades) {
            b.increaseHeight(speedUpRate * timePassed);
        }

        // add / removal of blockades
        if (Blockades.get(Blockades.size() - 1).getRect().top >= screenHeight) {
            int initWidth = (int) (Math.random() * (screenWidth - playerGap));

            Blockades.add(0, new Blockade(blockadeHeight, color, initWidth,
                    Blockades.get(0).getRect().top - blockadeHeight - blockadeGap,
                    playerGap));
            Blockades.remove(Blockades.size() - 1);
        }
        // update score
        if (scoreCounter == 99) {
            scoreCounter = 0;
            statisticsManager.addScore();
            if (isHardMode)
                statisticsManager.addBonusPoints();
        }
        else
            scoreCounter++;
    }
}
