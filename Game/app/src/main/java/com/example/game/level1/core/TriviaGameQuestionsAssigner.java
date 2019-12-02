package com.example.game.level1.core;


import com.example.game.level1.questionbanks.EnglishQuestions;
import com.example.game.level1.questionbanks.FrenchQuestions;
import com.example.game.level1.questionbanks.MathQuestions;
import com.example.game.level1.questionbanks.Questions;
import com.example.game.level1.questionbanks.ScienceQuestions;

/**
 * Assign the questions of the Trivia Game based on what subject the player chose. This class can
 * be extended to accommodate more subjects
 */
public class TriviaGameQuestionsAssigner implements QuestionsAssigner{

    private String subject;
    private Questions questions;

    /**
     * Create a TriviaGameQuestionsAssigner Object and assign the questions based on the subject
     * @param subject - what the subject of the questions are
     */
    public TriviaGameQuestionsAssigner(String subject){
        this.subject = subject;
        assignQuestions();
    }

    /**
     *Get the questions
     * @return the list of questions
     */
    public Questions getQuestions() {
        return questions;
    }

    /**
     *assign a question list to the questions variable based on the subject chosen.
     * This list may be extended to include more subjects.
     */
    public void assignQuestions() {
        if (subject.equals(new MathQuestions().getSubjectID())) {
            //if it matches the id of the math class
            this.questions = new MathQuestions();
        }

        if (subject.equals(new ScienceQuestions().getSubjectID())){
            this.questions = new ScienceQuestions();
        }

        if (subject.equals(new EnglishQuestions().getSubjectID())){
            this.questions = new EnglishQuestions();
        }

        if (subject.equals(new FrenchQuestions().getSubjectID())){
            this.questions = new FrenchQuestions();
        }
    }
}


