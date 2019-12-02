package com.example.game.level1.core;

import android.os.Handler;
import android.view.View;
import android.widget.RadioButton;

import com.example.game.level1.questionbanks.Questions;
import com.example.game.level1.questionbanks.TriviaQuestion;
import com.example.game.level1.TriviaGame;

/**
 * Updates the questions and options on the trivia game screen
 */
public class TriviaGameQuestionsOptionsUpdater implements QuestionsOptionsUpdater {

    private Questions questions;
    private int questionNumber = 0;
    private TriviaQuestion currentQuestion;
    private TriviaGame triviaGame;

    /**
     * @param questions - the questions
     * @param triviaGame - the trivia game
     */
    public TriviaGameQuestionsOptionsUpdater(Questions questions, TriviaGame triviaGame){
        this.questions = questions;
        this.triviaGame = triviaGame;
    }

    /**
     * Return what the current question of the game is. This is the question that the user should
     * be trying to solve.
     * @return currentQuestion - what the current question is
     */
    public TriviaQuestion getCurrentQuestion() {
        return currentQuestion;
    }

    /**
     * Update the questions and options showing on the trivia game screen. This occurs when the
     * player
     * moves on to the next question
     */
    public void update() {
        questionNumber ++;
        if (questionNumber == questions.numOfQuestions()+1) { //if game is over
            triviaGame.endGame();
        } else {
            setCurrentQuestion();
            showQuestionsAndOptions();
        }
    }

    /**
     * Update the questions/answers/options after a delay
     * @param view - the view
     * @param lengthOfDelay - how long the delay is
     */
    public void updateAfterDelay(final View view, long lengthOfDelay){
        final Handler handler = new Handler();
        //code from https://stackoverflow.com/questions/15874117/how-to-set-delay-in-android
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                updateOptionButtons(view);
                update();
            }
        }, lengthOfDelay);
    }

    /**
     *Set the current question according to what question number the player is on
     */
    public void setCurrentQuestion() {
        this.currentQuestion = questions.getQuestions().get(questionNumber -1);
    }

    /**
     *Sets the text of the question and options placeholders to be the question and options of the
     * trivia question, respectively
     */
    public void showQuestionsAndOptions() {
        triviaGame.setQuestionText("Question " + questionNumber + ": " +
                currentQuestion.getQuestion() + "?");
        triviaGame.setOption1Text(currentQuestion.getOption1());
        triviaGame.setOption2Text(currentQuestion.getOption2());
        triviaGame.setOption3Text(currentQuestion.getOption3());
        triviaGame.setOption4Text(currentQuestion.getOption4());
    }

    /**
     *Reset the option button after the user clicks it so that the text is back to the
     * default colour, and the option is unchecked
     * @param view - the view
     */
    public void updateOptionButtons(View view) {
        ((RadioButton) view).setChecked(false);
        ((RadioButton) view).setTextColor(0xffff8800);
    }
}
