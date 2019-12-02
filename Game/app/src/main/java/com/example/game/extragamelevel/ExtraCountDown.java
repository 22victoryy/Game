package com.example.game.extragamelevel;

import android.os.CountDownTimer;

class ExtraCountDown {
    //Data from mainExtraLevel
    private MainExtraLevel main;

    //countdown
    private final long STARTTIMER = 10000;
    private long timeLast = STARTTIMER;
    private CountDownTimer myCountDownTimer;

    /**
     * ExtraCountDown initialized. Act as a count down timer class.
     *
     * @param main takes MainExrraLevel class data.
     */

    ExtraCountDown(MainExtraLevel main) {
        this.main = main;
        setMyCountDownTimer();
    }

    /**
     * setMyCountDownTimer starts and sets the timer.
     */

    private void setMyCountDownTimer() {
        myCountDownTimer = new CountDownTimer(timeLast, 1000) {

            @Override
            public void onTick(long l) {
                update(l);
            }

            @Override
            public void onFinish() {
                myCountDownTimer.cancel();
                main.updateIsRunning(false);
                main.updateEnding();
            }
        }.start();
        main.updateIsRunning(true);
    }

    /**
     * resetTimer resets the timer back to original.
     */

    private void resetTimer() {
        timeLast = STARTTIMER;
        myCountDownTimer.cancel();
        String countdown = "Count Down: 10";
        main.updateCountdown(countdown);
    }

    /**
     * update updates the timer every second.
     */

    private void update(long l) {
        timeLast = l;
        int seconds = (int) timeLast / 1000;
        String timeLeft = "Count Down:" + seconds;
        main.updateCountdown(timeLeft);
    }

    /**
     * setReset calls the resetTimer method.
     */

    void setReset() {
        resetTimer();
    }

    /**
     * stopTimer calls the cancellation of timer.
     */

    void stopTimer() {
        myCountDownTimer.cancel();
    }
}

