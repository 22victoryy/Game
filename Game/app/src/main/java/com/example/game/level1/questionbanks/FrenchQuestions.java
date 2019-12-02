package com.example.game.level1.questionbanks;

/**
 * A subclass of Questions. This class contains questions related to the subject of French
 */
public class FrenchQuestions extends Questions {

    public FrenchQuestions(){
        setSubjectID("french");
    }

    /**
     *Adding questions to the list of questions. This is where the questions are hardcoded
     */
    @Override
    void addQuestions() {
        addOneQuestion(new TriviaQuestion("What is 'chat' in English",
                "cat", "dog", "talk", "bath",
                1));
        addOneQuestion(new TriviaQuestion("What is 'pencil' in French", "stylo",
                "crayon", "gomme", "table", 2));
        addOneQuestion(new TriviaQuestion("How do you say 'My name is Zara' in French",
                "Je m'appelle Zara", "Je suis Zara",
                "Zara est fantastic", "nom de Zara", 1));
        addOneQuestion(new TriviaQuestion("How would the verb 'etre' be conjugated with " +
                "'je'", "est", "sommes", "sont", "suis",
                4));
        addOneQuestion(new TriviaQuestion("In which of the following is French not " +
                "an official language", "Canada", "Haiti",
                "Sweden", "Switzerland", 3));
    }
}
