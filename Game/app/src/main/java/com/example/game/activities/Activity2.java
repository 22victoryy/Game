package com.example.game.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.R;
import com.example.game.level2.ChooseDifficulty;

/**
 * Class leading to level 2
 */
public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        Intent intent = new Intent(Activity2.this, ChooseDifficulty.class);
        intent.putExtra("statisticsManager", getIntent().getSerializableExtra("statisticsManager"));
        startActivity(intent);
    }
}
