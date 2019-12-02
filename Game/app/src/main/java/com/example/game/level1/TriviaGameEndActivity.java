package com.example.game.level1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.activities.Activity1;
import com.example.game.activities.Activity2;
import com.example.game.activities.GameStart;
import com.example.game.R;

public class TriviaGameEndActivity extends AppCompatActivity {

    /**
     * What is created once this activity starts
     *
     * @param savedInstanceState - the bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia_game_end);

        //getting the points from the previous activity to display on this activity view
        Intent intent = this.getIntent();
        TextView finalPointsText = findViewById(R.id.finalPointsText);
        String pointsString = getResources().getString(R.string.points) +
                intent.getStringExtra("Final Point Count");

//        finalPointsText.setText("Points: " + intent.getStringExtra("Final Point Count"));
        finalPointsText.setText(pointsString);
    }

    /**
     * What happens when the player clicks the next level button (the view)
     *
     * @param view - the view
     */
    public void goToNextLevel(View view) {
        Intent intent = new Intent(this, Activity2.class);
        intent.putExtra("statisticsManager", getIntent().getSerializableExtra(
                "statisticsManager"));
        startActivity(intent);
    }

    /**
     * What happens when the player selects the play again button (the view)
     *
     * @param view - the view
     */
    public void playAgain(View view) {
        Intent intent = new Intent(this, Activity1.class);
        intent.putExtra("statisticsManager", getIntent().getSerializableExtra(
                "statisticsManager"));
        startActivity(intent);
    }

    /**
     * What happens when the player selects the quit button (the view)
     *
     * @param view - the view
     */
    public void quit(View view) {
        Intent intent = new Intent(this, GameStart.class);
        intent.putExtra("statisticsManager", getIntent().getSerializableExtra(
                "statisticsManager"));
        startActivity(intent);
    }
}
