package com.example.game.level3;


import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.content.Intent;

import com.example.game.activities.GameStart;

/**
 * The Main Activity for level3.
 */
public class MainLevel3 extends Activity {

    /**
     * Instantiate a new activity.
     *
     * @param savedInstanceState a bundle of a saved instance state.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Want to make activity3 fullscreen.
        //         Credit to StackOverflow user Christian and mrroboaat.
        //         https://stackoverflow.com/a/2868052
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Intent intent = new Intent(this, GameStart.class);
        intent.putExtra("statisticsManager",
                getIntent().getSerializableExtra("statisticsManager"));
        this.setContentView(new GameView(this, intent));
    }
}


