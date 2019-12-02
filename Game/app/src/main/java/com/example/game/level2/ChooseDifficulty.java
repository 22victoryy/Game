package com.example.game.level2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.game.activities.ExtraActivity;
import com.example.game.R;

public class ChooseDifficulty extends AppCompatActivity {

    private boolean isHardMode; // boolean to determine easy or hard mode

    /**
     * OnCreate method to select difficulty
     * @param savedInstanceState Select difficulty screen
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_difficulty);

        Button normalMode = findViewById(R.id.Normal);

        Button hardMode = findViewById(R.id.Hard);

        normalMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isHardMode = false;
                Intent intent = new Intent(ChooseDifficulty.this, MainLevel2.class);
                intent.putExtra("statisticsManager", getIntent().getSerializableExtra
                        ("statisticsManager"));
                intent.putExtra("difficulty", isHardMode);
                startActivity(intent);
            }
        });

        hardMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isHardMode = true;
                Intent intent = new Intent(ChooseDifficulty.this, MainLevel2.class);
                intent.putExtra("statisticsManager", getIntent().getSerializableExtra(
                        "statisticsManager"));
                intent.putExtra("difficulty", isHardMode);
                startActivity(intent);
            }
        });
    }

    /**
     * Go to the bonus activity if the user clicks the easter egg
     * @param view - the view
     */
    public void goToBonusActivity(View view) {
        Intent intent = new Intent(ChooseDifficulty.this, ExtraActivity.class);
        intent.putExtra("statisticsManager", getIntent().getSerializableExtra(
                "statisticsManager"));
        startActivity(intent);
    }
}
