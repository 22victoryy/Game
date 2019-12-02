package com.example.game.level3.core;

/**
 * Interface that allows a <code>StatisticsTracker</code> instance
 * to keep track of this object.
 */
public interface StatisticsTrackable {

    /**
     * Setter for the <code>StatisticsTracker</code>.
     * @param statisticsTracker the <code>StatisticsTracker</code>.
     */
    void setStatisticsTracker(StatisticsTracker statisticsTracker);

    /**
     * Update statistics.
     */
    void updateStatistics();
}