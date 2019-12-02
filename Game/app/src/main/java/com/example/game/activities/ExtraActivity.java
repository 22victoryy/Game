package com.example.game.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.game.extragamelevel.MainExtraLevel;
import com.example.game.R;

public class ExtraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra);

        Button bonusStage = findViewById(R.id.BonusStage);

        Button skipToLevel2 = findViewById(R.id.Skip);


        bonusStage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExtraActivity.this, MainExtraLevel.class);
                intent.putExtra("statisticsManager", getIntent().getSerializableExtra(
                        "statisticsManager"));
                startActivity(intent);
            }
        });

        skipToLevel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExtraActivity.this, Activity2.class);
                intent.putExtra("statisticsManager", getIntent().getSerializableExtra(
                        "statisticsManager"));
                startActivity(intent);
            }
        });
    }
}
