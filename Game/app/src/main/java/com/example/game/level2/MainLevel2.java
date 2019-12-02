package com.example.game.level2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.game.statistics.StatisticsManager;

/**
 * MainLevel3 of Level 2
 */
public class MainLevel2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.
                FLAG_FULLSCREEN);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(new GameView(this, (StatisticsManager)
                getIntent().getSerializableExtra("statisticsManager"),
                getIntent().getBooleanExtra("difficulty", false)));
    }
}
