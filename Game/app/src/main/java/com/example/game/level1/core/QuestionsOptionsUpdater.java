package com.example.game.level1.core;

import android.view.View;

import com.example.game.level1.questionbanks.TriviaQuestion;

/**
 * an interface to update questions and options
 */
public interface QuestionsOptionsUpdater {

    /**
     * Return what the current question of the game is. This is the question that the user should
     * be trying to solve.
     * @return currentQuestion - what the current question is
     */
    TriviaQuestion getCurrentQuestion();

    /**
     * Update the questions and options.
     */
    void update();

    /**
     * Update the questions/answers/options after a delay
     * @param view          - the view
     * @param lengthOfDelay - how long the delay is
     */
    void updateAfterDelay(final View view, long lengthOfDelay);

    /**
     * Set the current question
     */
    void setCurrentQuestion();

    /**
     * Sets the text of the question and options placeholders to be the question and options of the
     * trivia question, respectively
     */
    void showQuestionsAndOptions();

    /**
     * Reset the option button after the user clicks it so that the text is back to the
     * default colour, and the option is unchecked
     * @param view - the view
     */
    void updateOptionButtons(View view);
}
