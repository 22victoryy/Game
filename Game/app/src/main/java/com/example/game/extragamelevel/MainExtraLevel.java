package com.example.game.extragamelevel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.activities.Activity2;
import com.example.game.R;
import com.example.game.statistics.StatisticsManager;

import java.util.Random;

public class MainExtraLevel extends AppCompatActivity {

    private String wantedItem;
    private String[] quest = {"Pumpkin", "Apple Candy", "Chocolate Bar", "Bucket", "Poison Cooking Pot"};

    //countdown
    private TextView countDown;
    private ExtraCountDown myCountDown;

    private boolean timeIsRunnning;

    //Initialize Image buttons
    private TextView questTitle;
    private TextView remainingLife;
    private Button mapButton;

    //Image buttons for correct and wrong
    private ImageButton correct;
    private ImageButton cross;

    //Record Life
    private int numOfLives = 3;
    private int turns = 0;

    //record Statistics
    private StatisticsManager statisticsManager;

    /**
     * onCreate method creates the layout the game.
     * @param savedInstanceState initialize the game.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.extra_level_layout);

        mapButton = findViewById(R.id.map_button);
        Button items = findViewById(R.id.items);
        final Button start = findViewById(R.id.start);

        statisticsManager = (StatisticsManager)getIntent().getSerializableExtra (
                "statisticsManager");

        questTitle = findViewById(R.id.quest);
        remainingLife = findViewById(R.id.life);
        ImageButton pumpkin = findViewById(R.id.pumpkin);
        ImageButton candy1 = findViewById(R.id.candy1);
        ImageButton candy2 = findViewById(R.id.candy2);
        ImageButton candy3 = findViewById(R.id.candy3);
        ImageButton candy4 = findViewById(R.id.candy4);
        countDown = findViewById(R.id.countdown);

        final ImageButton[] quests = { pumpkin, candy1, candy2, candy3, candy4 };

        pumpkin.setOnClickListener(clicked);
        candy1.setOnClickListener(clicked);
        candy2.setOnClickListener(clicked);
        candy3.setOnClickListener(clicked);
        candy4.setOnClickListener(clicked);

        mapButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                restart();
            }
        });

        items.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                skip();
            }
        });

        start.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                for (int i = 0; 5 > i; i++) {
                    quests[i].setVisibility(View.VISIBLE);
                }
                startRound();
            }
        });
    }

    /**
     * clicked is activated when 5different buttons are pressed.
     * (pumpkin,candy1,candy2,candy3,candy4)
     */
    private Button.OnClickListener clicked = new Button.OnClickListener() {

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.pumpkin) {
                if (wantedItem.equals("Pumpkin") && timeIsRunnning) {
                    noMistake();
                } else {
                    miss();
                }
            } else if (view.getId() == R.id.candy1) {
                if (wantedItem.equals("Apple Candy") && timeIsRunnning) {
                    noMistake();
                } else {
                    miss();
                }
            } else if (view.getId() == R.id.candy2) {
                if (wantedItem.equals("Chocolate Bar") && timeIsRunnning) {
                    noMistake();
                } else {
                    miss();
                }
            } else if (view.getId() == R.id.candy3) {
                if (wantedItem.equals("Bucket") && timeIsRunnning) {
                    noMistake();
                } else {
                    miss();
                }
            } else if (view.getId() == R.id.candy4) {
                if (wantedItem.equals("Poison Cooking Pot") && timeIsRunnning) {
                    noMistake();
                } else {
                    miss();
                }
            }
        }
    };

    /**
     * noMistake act as a check to see if the player satisfied the condition of winning.
     */
    private void noMistake() {
        correct();
        myCountDown.setReset();
        setCountDown();
        turns++;
        toNextLevel();
    }

    /**
     * startRound is activated when the player press the start button. Start the game.
     */
    private void startRound() {
        mapButton.setVisibility(View.VISIBLE);
        changeName();
        String livesRemainingHeader = getResources().getString(R.string.remaining_life) +
                numOfLives;
        remainingLife.setText(livesRemainingHeader);
        remainingLife.setVisibility(View.VISIBLE);
        setCountDown();
    }

    /**
     * changeName is activated when instruction(find which button) needs to be gamed.
     */
    private void changeName() {
        wantedItem = random();
        String findItem = getResources().getString(R.string.find) + wantedItem;
        questTitle.setText(findItem);
        questTitle.setVisibility(View.VISIBLE);
    }

    /**
     * miss is activated when the player press the wrong button. Countdown will not be reset.
     */
    private void miss() {
        numOfLives = numOfLives - 1;
        gameOver();
        cross = findViewById(R.id.cross);
        cross.setVisibility(View.VISIBLE);
        final String livesRemainingHeader = getResources().getString(R.string.remaining_life) +
                numOfLives;
        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cross.setVisibility(View.GONE);
                remainingLife.setText(livesRemainingHeader);
            }
        });
    }

    /**
     * correct is activated when the player press the correct button. Countdown will be reset.
     */
    private void correct() {
        statisticsManager.addBonusPoints();
        correct = findViewById(R.id.correct_button);
        correct.setVisibility(View.VISIBLE);
        correct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                correct.setVisibility(View.GONE);
                wantedItem = random();
                String questTitleIndicator = getResources().getString(R.string.find) +
                        wantedItem;
                TextView quesTitle = findViewById(R.id.quest);
                quesTitle.setText(questTitleIndicator);
            }
        });
    }

    /**
     * random generates random button(for finding which button) in the instruction.
     */
    private String random() {
        int len = new Random().nextInt(quest.length);
        String s = quest[len];
        return s;
    }


    /**
     * gameOver is activated when the player has 0 lives and the count down reaches to 0.
     */
    private void gameOver() {
        if (numOfLives == 0 || !timeIsRunnning) {
            myCountDown.stopTimer();
            Intent intent = new Intent(this, GameOver.class);
            intent.putExtra("statisticsManager", statisticsManager);
            startActivity(intent);
        }
    }

    /**
     * toNextLevel is activated when the player successfully completed the level. Lead player
     * to next.class.
     */
    private void toNextLevel() {
        if (turns == 5) {
            myCountDown.stopTimer();
            Intent intent = new Intent(MainExtraLevel.this, Next.class);
            intent.putExtra("statisticsManager", statisticsManager);
            startActivity(intent);
        }
    }

    /**
     * restart is activated when restart button is pressed. Restart the counter and the instruction.
     */
    private void restart() {
        myCountDown.setReset();
        changeName();
        setCountDown();
    }

    /**
     * skip is activated when skip button is pressed. Skip this bonus level. Lead player to
     * GameStart.class
     */
    private void skip() {
        Intent intent = new Intent(this, Activity2.class);
        intent.putExtra("statisticsManager", statisticsManager);
        startActivity(intent);
    }
    /**
     * setCountDown is activated once start button is pressed. Activate Count Down from another
     * class ExtraCountDown
     */
    private void setCountDown() {
        myCountDown=new ExtraCountDown(this);
    }
    /**
     * updateCountdown is used to show the player the timer.
     * @param time sets the time.
     */
    public void updateCountdown(String time){
        countDown.setText(time);
    }

    /**
     * updateIsRunning is used to change the status of boolean running
     */

    public void updateIsRunning(boolean runnning){
        timeIsRunnning=runnning;
    }
    /**
     * updateEnding is used when countdown is over. Lead player to GameOver.class
     */

    public void updateEnding(){
        gameOver();
    }
}

