package com.example.game.level1.questionbanks;

/**
 * Trivia Question Generator
 */

public class TriviaQuestion {

    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private int optionNumberOfAnswer;

    /**
     * Creating the Trivia Question
     * @param question             - the question
     * @param option1              - the first answer option
     * @param option2              - the second answer option
     * @param option3              - the third answer option
     * @param option4              - the fourth answer option
     * @param optionNumberOfAnswer - what number between 1 to 4 the answer is.
     * Used an int instead of a String for the answer to prevent errors from typos
     */
    TriviaQuestion(String question, String option1, String option2, String option3, String option4,
                   int optionNumberOfAnswer) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.optionNumberOfAnswer = optionNumberOfAnswer;
    }

    /**
     * Get the trivia question
     * @return the question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Get the first option
     * @return option1 - the first answer option
     */
    public String getOption1() {
        return option1;
    }

    /**
     * Get the second option
     * @return option2 - the second answer option
     */
    public String getOption2() {
        return option2;
    }

    /**
     * Get the third option
     * @return option2 - the third answer option
     */
    public String getOption3() {
        return option3;
    }

    /**
     * Get the fourth option
     * @return option4 - the fourth answer option
     */
    public String getOption4() {
        return option4;
    }

    /**
     * Get the actual option that is the answer of the question
     * @return answer - what the actual answer is. It's a String that the other class can
     * compare to what the user actually selects
     */
    public String getAnswer() {
        return extractAnswer();
    }

    /**
     * Getting the answer String based on what option number the answer was
     * @return the answer so that the other class can check if the player selected the right answer
     */

    private String extractAnswer() {
        if (optionNumberOfAnswer == 1) {
            return getOption1();
        }

        if (optionNumberOfAnswer == 2) {
            return getOption2();
        }

        if (optionNumberOfAnswer == 3) {
            return getOption3();
        }

        if (optionNumberOfAnswer == 4) {
            return getOption4();
        }
        return "Error!"; //if none of the four options are the answer
    }
}
