package com.example.game.statistics;

import java.io.Serializable;

/**
 * A class which is instantiated at the start to keep track of statistics across games
 */
public class StatisticsManager implements Serializable {
    private int score;
    private int tries;
    private int bonusPoints;

    /**
     * create a statistics manager with all statistics set to 0
     */
    public StatisticsManager() {
        resetValues();
    }

    /**
     * @param score amount to be added to the score
     */
    public void addScore(int score) {
        this.score += score;
    }

    /**
     * increment the score
     */
    public void addScore() {
        score++;
    }

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @return the number of tries
     */
    public int getTries() { return tries; }

    /**
     * increment the number of tries
     */
    public void addTries() {
        tries++;
    }

    /**
     * @param tries amount to be added to the number of tries
     */
    public void addTries(int tries) {
        this.tries += tries;
    }

    /**
     * @param bonusPoints amount to be added to bonus points
     */
    public void addBonusPoints(int bonusPoints) {
        this.bonusPoints += bonusPoints;
    }

    /**
     * increment bonus points
     */
    public void addBonusPoints() { bonusPoints++; }

    /**
     * @return bonus points
     */
    public int getBonusPoints() {
        return this.bonusPoints;
    }

    /**
     * reset all statistics to 0
     */
    public void resetValues() {
        score = 0;
        tries = 0;
        bonusPoints = 0;
    }
}
