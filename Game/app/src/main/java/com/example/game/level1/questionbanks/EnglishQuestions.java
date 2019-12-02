package com.example.game.level1.questionbanks;

/**
 * A subclass of Questions. This class contains questions related to the subject of English
 */
public class EnglishQuestions extends Questions {

    public EnglishQuestions(){
        setSubjectID("english");
    }

    /**
     *Adding questions to the list of questions.
     */
    @Override
    void addQuestions() {
        addOneQuestion(new TriviaQuestion("What is a synonym for small", "monolith",
                "pungent", "minuscule", "massive", 3));
        addOneQuestion(new TriviaQuestion("What is an antonym of 'happy'",
                "jubilant", "melancholy", "reprehensible", "ugly",
                2));
        addOneQuestion(new TriviaQuestion("Which Shakespeare play is the line 'To be or" +
                " not to be' from",
                "Macbeth", "King Lear", "Romeo and Juliet",
                "Hamlet", 4));
        addOneQuestion(new TriviaQuestion("Which one of the following is a palindrome",
                "racecar", "midnight", "shoosh", "poo",
                1));
        addOneQuestion(new TriviaQuestion("What does the Greek root 'poly' mean",
                "one", "two", "many", "girl",
                3));
    }
}
