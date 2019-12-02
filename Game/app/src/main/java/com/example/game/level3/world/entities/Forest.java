package com.example.game.level3.world.entities;

import com.example.game.level3.core.Vector;

import java.util.List;
import java.util.ArrayList;

/**
 * Forest class to construct a list of trees.
 */
public class Forest {
    private List<Tree> trees;
    private double screenWidth;
    private double screenHeight;
    private Vector velocity;

    /**
     * Forest constructor.
     *
     * @param screenWidth  The width of the screen
     * @param screenHeight The height of the screen
     **/
    public Forest(double sugarGliderSpeed, double screenWidth, double screenHeight) {
        this.velocity = new Vector(-sugarGliderSpeed, 0.0);
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        trees = this.makeTrees();
    }

    private List<Tree> makeTrees() {
        trees = new ArrayList<>();
        int density = 2;
        int periodicity = 4;
        double x, y;
        double yPrev = 0.0;
        for (int i = 0; i < density * periodicity; i++) {
            x = i * screenWidth / density - screenWidth * 0.1;
            if (i > 2) {
                // this is a do while loop because we want y to be set first before
                // a condition is checked. This condition check is to ensure that
                // the spacing on each tree is not too far apart.
                do {
                    y = Math.random() * 0.66 * screenHeight + screenHeight * 0.33 * 0.5;
                } while (Math.abs(y - yPrev) > screenHeight * 0.6);
                yPrev = y;
            } else {
                y = Math.random() * 0.33 * screenHeight + screenHeight * 0.33 * 0.5;
            }
            Vector position1 = new Vector(x, y);
            Vector position2 = new Vector(x, y + 0.15 * screenHeight);
            trees.add(new Tree(position1, velocity,
                    this.screenWidth / 10, 2 * this.screenHeight, true,
                    this.screenWidth));
            trees.add(new Tree(position2, velocity,
                    this.screenWidth / 10, 2 * this.screenHeight, false,
                    this.screenWidth));
        }
        return trees;
    }

    /**
     * Getter for getting Tree objects.
     *
     * @return List of tree objects
     */
    public List<Tree> getTrees() {
        return trees;
    }

}