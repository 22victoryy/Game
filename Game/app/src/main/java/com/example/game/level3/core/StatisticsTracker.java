package com.example.game.level3.core;

/**
 * This class encapsulates and keeps track of statistics information.
 */
abstract public class StatisticsTracker {

    private double totalDistance;
    private double fps;
    private double totalTime;
    private double deltaTime;
    private int score = 0;
    private int highScore = 0;
    private int tryCount = 0;
    private int tapCount = 0;
    private int bonusPoints = 0;

    /**
     * Getter for the number of frames that occur in a given second.
     *
     * @return the number of frames per second
     */
    public double getFramesPerSecond() {
        return this.fps;
    }

    /**
     * Setter for the number of frames that occur in a given second.
     *
     * @param framesPerSecond set the frames per second.
     */
    public void setFramesPerSecond(double framesPerSecond) {
        this.fps = framesPerSecond;
    }

    /**
     * Setter for the total amount of time elapsed, in seconds.
     *
     * @param time set the total amount of time in seconds.
     */
    public void setTotalTime(double time) {
        this.totalTime = time;
    }

    /**
     * Getter for the total amount of time elapsed, in seconds.
     *
     * @return the total amount of time for the user in this session.
     */
    public double getTotalTime() {
        return totalTime;
    }

    /**
     * Adder for the total amount of time elapsed, in seconds.
     *
     * @param time increment the total amount of time in seconds.
     */
    public void addToTotalTime(double time) {
        this.totalTime += time;
    }

    /**
     * Getter for the total distance covered, in meters.
     *
     * @return the total distance covered.
     */
    public double getTotalDistance() {
        return this.totalDistance;
    }

    /**
     * Setter for the total distance covered in meters.
     *
     * @param distance set the total distance travelled.
     */
    public void setTotalDistance(double distance) {
        this.totalDistance = distance;
    }

    /**
     * Adder for the total distance travelled.
     *
     * @param distance increment the total distance covered.
     */
    public void addToTotalDistance(double distance) {
        this.totalDistance += distance;
    }

    /**
     * Setter for the time interval in seconds between each frame.
     *
     * @param deltaTime The time interval in seconds between each frame.
     */
    public void setDeltaTime(double deltaTime) {
        this.deltaTime = deltaTime;
    }

    /**
     * Getter for the time interval in seconds between each frame.
     *
     * @return deltaTime The time interval in seconds between each frame.
     */
    public double getDeltaTime() {
        return this.deltaTime;
    }

    /**
     * Getter for the number of tries.
     *
     * @return the number of tries.
     */
    public int getTryCount() {
        return this.tryCount;
    }

    /**
     * Setter for the number of tries.
     *
     * @param tryCount the number of tries.
     */
    public void setTryCount(int tryCount) {
        this.tryCount = tryCount;
    }


    /**
     * Getter the score for a single game session.
     *
     * @return the score for a single game session.
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Setter for the score in a single game session.
     *
     * @param score the score for a single game session.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Adder for the score in a single game session.
     *
     * @param points increment the score by some points.
     */
    public void addToScore(int points) {
        this.score += points;
    }

    /**
     * Adder for the number of taps to the screen.
     *
     * @param tapCount of taps to the screen.
     */
    public void addToTapCount(int tapCount) {
        this.tapCount += tapCount;
    }

    /**
     * Setter for the number of taps to the screen.
     *
     * @param tapCount of taps to the screen.
     */
    public void setTapCount(int tapCount) {
        this.tapCount = tapCount;
    }

    /**
     * Getter for the number of taps to the screen.
     *
     * @return the number of taps to the screen.
     */
    public int getTapCount() {
        return this.tapCount;
    }

    /**
     * Getter for the bonus points.
     *
     * @return the bonus points.
     */
    public int getBonusPoints() {
        return this.bonusPoints;
    }

    /**
     * Adder for the points.
     *
     * @param points add the additional bonus points.
     */
    public void addToBonusPoints(int points) {
        this.bonusPoints += points;
    }

    /**
     * Getter for the high score.
     *
     * @return the high score of the user.
     */
    public int getHighScore() {
        return highScore;
    }

    /**
     * Setter for the high player score.
     *
     * @param score the high score of the user.
     */
    public void setHighScore(int score) {
        this.highScore = score;
    }
}