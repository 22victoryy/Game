package com.example.game.level1.questionbanks;

/**
 * A subclass of Questions. This class contains questions related to the subject of math
 */

public class MathQuestions extends Questions {

    public MathQuestions() {
        setSubjectID("math");
    }

    /**
     *Adding questions to the list of questions.
     */
    @Override
    void addQuestions() {
        addOneQuestion(new TriviaQuestion("5 + 9", "14", "10",
                "12", "9", 1));
        addOneQuestion(new TriviaQuestion("10 - 2", "7", "8",
                "9", "12", 2));
        addOneQuestion(new TriviaQuestion("4 x 5", "9", "22",
                "25", "20", 4));
        addOneQuestion(new TriviaQuestion("10 + 7", "15", "0",
                "17", " -17", 3));
        addOneQuestion(new TriviaQuestion("216/4", "52", "54",
                "220", "212", 2));
    }
}


