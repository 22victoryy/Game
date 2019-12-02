package com.example.game.level3.bonus.world;

import java.util.ArrayList;
import com.example.game.level3.core.Vector;

/**
 * This class builds Easter eggs.
 */
class EasterEggBuilder {

    /**
     * Get a list of Easter eggs.
     * @param screenWidth width of the screen
     * @param screenHeight height of the screen
     * @return list of easter eggs.
     */
    static ArrayList<EasterEgg> getEasterEggs(int screenWidth, int screenHeight) {
        int numberOfEggs = 10;
        ArrayList <EasterEgg> easterEggs = new ArrayList<> ();
        for (int i = 0; i < numberOfEggs; i++) {
            double x = Math.random()*screenWidth;
            double y = -3.0*(1.0 - (double)i/(double)numberOfEggs)*screenHeight;
            easterEggs.add(new EasterEgg(
                    new Vector(x, y),
                    screenWidth, screenHeight));
        }
        return easterEggs;
    }
}