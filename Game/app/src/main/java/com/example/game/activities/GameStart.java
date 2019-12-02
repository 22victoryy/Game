package com.example.game.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.game.login.AuthenticatedScreen;
import com.example.game.R;
import com.example.game.statistics.StatisticsManager;

/**
 * Class that leads to level selection
 */
public class GameStart extends AppCompatActivity {

    StatisticsManager statisticsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_start);

        // initialise the statistics
        statisticsManager = (StatisticsManager) getIntent().getSerializableExtra(
                "statisticsManager");

        TextView scoreDisplay = findViewById(R.id.scoreDisplay);
        String scorePts = "Score: " + statisticsManager.getScore();

        scoreDisplay.setText(scorePts);

        TextView bonusDisplay = findViewById(R.id.bonusDisplay);
        String bonusPts = "Bonus Points: " + statisticsManager.getBonusPoints();
        bonusDisplay.setText(bonusPts);

        String tries = "Tries: " + statisticsManager.getTries();
        TextView triesDisplay = findViewById(R.id.triesDisplay);
        triesDisplay.setText(tries);
    }

    /**
     * Starts level 1
     * @param view the view
     */
    public void startLevel1(View view) {
        Intent intent = new Intent(this, Activity1.class);
        intent.putExtra("statisticsManager", statisticsManager);
        startActivity(intent);
    }

    /**
     * Starts level 2
     * @param view the view
     */
    public void startLevel2(View view) {
        Intent intent = new Intent(this, Activity2.class);
        intent.putExtra("statisticsManager", statisticsManager);
        startActivity(intent);
    }

    /**
     * Starts level 3
     * @param view the view
     */
    public void startLevel3(View view) {
        Intent intent = new Intent(this, Activity3.class);
        intent.putExtra("statisticsManager", statisticsManager);
        startActivity(intent);
    }

    /**
     * Resets scores and starts the authentication activity
     * @param view the view
     */
    public void endGame(View view) {
        statisticsManager.resetValues();
        Intent intent = new Intent(this, AuthenticatedScreen.class);
        intent.putExtra("statisticsManager", statisticsManager);
        startActivity(intent);
    }
}
