package com.example.game.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.game.R;
import com.example.game.statistics.StatisticsManager;

/**
 * Class that leads to bonus game that can only be accessed in level 1
 */
public class HiddenBonusActivity extends AppCompatActivity {

    private TextView randomNumber;
    private RandomNumberGenerator randomNumberGenerator;
    private int numBonusPoints;

    /**
     *What is created once the activity starts
     * @param savedInstanceState - a bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hidden_bonus);
        randomNumber = findViewById(R.id.randomNumberText);
        randomNumberGenerator = new RandomNumberGenerator(2, 9);
    }

    /**
     * Set the text of the number on the screen
     * @param number - the number to set the random number text to
     */
    void setRandomNumberText(String number){
        randomNumber.setText(number);
    }

    /**
     * What happens when the player clicks generate a random number
     * @param view - the view
     */
    public void generateRandomNumber(View view) {
        randomNumberGenerator.generateRandomNumber();
        numBonusPoints = randomNumberGenerator.getRandomNumber();
        setRandomNumberText(Integer.toString(numBonusPoints));
        ((StatisticsManager)getIntent().getSerializableExtra("statisticsManager"))
                .addBonusPoints(numBonusPoints);
        goToNextActivity();
    }

    /**
     * After the user finishes generating a random number, they go to another activity screen.
     */
    private void goToNextActivity() {
        Intent intent = new Intent(HiddenBonusActivity.this,
                HiddenBonusActivityEnd.class);
        intent.putExtra("NumBonusPoints", Integer.toString(numBonusPoints));
        intent.putExtra("statisticsManager", getIntent().getSerializableExtra(
                "statisticsManager"));
        startActivity(intent);
    }
}
