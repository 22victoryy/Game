package com.example.game.level1.core;

import android.view.View;

/**
 * Interface to check if the answer that the player selects is correct
 */
public interface AnswerChecker {

    /**
     * Implementable method to check if points were earned
     * @return boolean depending on points earned
     */
    boolean isPointEarned();

    /**
     * Implementable checkAnswer
     * @param view view
     */
    void checkAnswer(View view);

}
