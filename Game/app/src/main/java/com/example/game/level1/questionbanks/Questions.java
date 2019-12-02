package com.example.game.level1.questionbanks;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The Question Bank class. It controls the Trivia Questions
 */
public abstract class Questions {

    private ArrayList<TriviaQuestion> questions = new ArrayList<>();

    private String subjectID;

    Questions() {
        addQuestions();
        randomizeQuestions();
    }

    /**
     * get the list of trivia questions
     * @return questions - the list of Trivia Questions
     */
    public ArrayList<TriviaQuestion> getQuestions() {
        return questions;
    }

    /**
     * Randomize the order of the questions
     */
    private void randomizeQuestions() {
        Collections.shuffle(questions); //to randomize the question order
    }

    /**
     * Add one trivia question to the list of questions
     * @param question - a TriviaQuestion
     */
    void addOneQuestion(TriviaQuestion question) {
        questions.add(question);
    }

    /**
     * Set the id of the subject
     * @param subjectID - the subject id
     */
    void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    /**
     * Get the id of the subject
     * @return the id of the subject
     */
    public String getSubjectID() {
        return subjectID;
    }

    /**
     * @return the number of questions there are
     */
    public int numOfQuestions() {
        return questions.size();
    }

    /**
     * Adding questions to the list of questions. This is where the questions are hardcoded
     */
    abstract void addQuestions();
}

