package com.example.game.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.game.R;

/**
 * This is the activity that summarizes the points that the player got in the hidden activity,
 * and allows them to go to the next level
 */
public class HiddenBonusActivityEnd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hidden_bonus_end);

        Intent intent = this.getIntent();
        String numBonusPoints = intent.getStringExtra("NumBonusPoints");

        TextView bonusPointsEarned = findViewById(R.id.numBonusPoints);
        String bonusPoints = getResources().getString(R.string.bonus_points) + numBonusPoints;
        bonusPointsEarned.setText(bonusPoints);
    }

    /**
     * To go to the next level
     * @param view - the view (the button pressed)
     */
    public void goToNextLevel(View view) {
        Intent intent = new Intent(HiddenBonusActivityEnd.this, Activity2.class);
        intent.putExtra("statisticsManager", getIntent().getSerializableExtra("statisticsManager"));
        startActivity(intent);
    }
}
