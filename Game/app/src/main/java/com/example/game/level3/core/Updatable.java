package com.example.game.level3.core;

/**
 * Updatable interface. By updatable
 * we mean that derived classes change
 * at each step in time.
 * <p>
 * In this class and all of its derivatives, the update method takes delta time as a parameter,
 * which is the time interval between each frame in seconds.
 * This to ensure that the objects are updated in proportion to the time passed,
 * and not dependent on the number of frames per second. A good
 * blog post on this topic by Drew Campbell is
 * <a href="https://medium.com/@dr3wc/understanding-delta-time-b53bf4781a03"> found here</a>.
 */
public interface Updatable {
    /**
     * Update this object, given a time interval.
     *
     * @param deltaTime time interval between each time frame.
     */
    void update(double deltaTime);

}