package com.example.game.level1.core;

import com.example.game.level1.questionbanks.Questions;

/**
 * Interface to assign the question bank
 */
public interface QuestionsAssigner {

    /**
     *Get the questions
     * @return the list of questions
     */
    Questions getQuestions();

    /**
     * assign a question list to the questions variable
     */
    void assignQuestions();

}
