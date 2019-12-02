package com.example.game.level1.questionbanks;


/**
 * A subclass of Questions. This class contains questions related to the subject of science
 */
public class ScienceQuestions extends Questions {

    public ScienceQuestions(){
        setSubjectID("science");
    }

    /**
     *Adding questions to the list of questions.
     */
    @Override
    void addQuestions() {
        addOneQuestion(new TriviaQuestion("Which of the following is a cell organelle",
                "cortisol", "mitochondria", "neuron", "stapes",
                2));
        addOneQuestion(new TriviaQuestion("What hormone is released from the pancreas",
                "adrenaline", "estrogen", "insulin", "prolactin",
                3));
        addOneQuestion(new TriviaQuestion("What is the fourth planet away from the Sun",
                "Earth", "Mercury", "Mars", "Saturn",
                3));
        addOneQuestion(new TriviaQuestion("What is the largest bone in the human body",
                "femur", "incus", "sternum", "mastoid",
                1));
        addOneQuestion(new TriviaQuestion("How many chromosomes does a human have in liver"
                + "cell", "48", "47", "24", "46",
                4));
    }
}
