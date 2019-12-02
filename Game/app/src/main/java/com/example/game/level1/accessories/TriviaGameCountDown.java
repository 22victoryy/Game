package com.example.game.level1.accessories;

import android.os.CountDownTimer;
import com.example.game.level1.TriviaGame;

/**
 * This class is responsible for the countdown of the Trivia Game
 */
public class TriviaGameCountDown implements CountDown {

    private TriviaGame triviaGame;
    private long timeToPlay;
    private CountDownTimer countDownTimer;

    /**
     * The constructor for the TriviaGameCountDown object
     * @param triviaGame - the triviaGame activity that this CountDown is for
     * @param timeToPlay - the amount of time there is to play the game
     */
    public TriviaGameCountDown(TriviaGame triviaGame, long timeToPlay){
        this.triviaGame = triviaGame;
        this.timeToPlay = timeToPlay;
    }

    /**
     *Starting the countdown
     */
    public void startCountDownTimer() {
        countDownTimer = new CountDownTimer(timeToPlay, 1000) {
            public void onTick(long millisUntilFinished) {
                triviaGame.setCountDownText("Seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                triviaGame.setCountDownText("Times Up!");
                triviaGame.endGame();
            }
        }.start();
    }

    /**
     * Ending the countdown
     */
    @Override
    public void stopCountDownTimer() {
        countDownTimer.cancel();
    }
}
