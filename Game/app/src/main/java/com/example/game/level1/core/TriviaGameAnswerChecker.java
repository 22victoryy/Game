package com.example.game.level1.core;

import android.graphics.Color;
import android.view.View;
import android.widget.RadioButton;


/**
 * checks if the answer that the player has selected is correct.
 */
public class TriviaGameAnswerChecker implements AnswerChecker{

    private QuestionsOptionsUpdater questionsOptionsUpdater;

    private boolean pointEarned = false;

    /**
     *The constructor for this class. It uses the information from a QuestionsOptionsUpdater class
     *  to determine what the question was, and what the correct answer was
     * @param questionsOptionsUpdater - an instance of the QuestionsOptionsUpdater class
     */
    public TriviaGameAnswerChecker(QuestionsOptionsUpdater questionsOptionsUpdater){
        this.questionsOptionsUpdater = questionsOptionsUpdater;
    }

    /**
     *Determine if a player earns a point.
     * @return boolean - is the point earned or not?
     */
    public boolean isPointEarned() {
        return pointEarned;
    }

    /**
     *Check if the option (the view) that the player selects is the right answer to the question
     * @param view - the view
     */
    public void checkAnswer(View view) {

        if (((RadioButton) view).getText() == questionsOptionsUpdater.getCurrentQuestion().
                getAnswer()) {
            ((RadioButton) view).setTextColor(Color.GREEN);
            //if the player got the answer right, the text of the option will be green
            pointEarned = true;
        } else {
            ((RadioButton) view).setTextColor(Color.RED);
            //if the player got the answer wrong, the text of the option will be red
            pointEarned = false;
        }
    }
}
