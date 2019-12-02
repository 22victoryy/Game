package com.example.game.extragamelevel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.activities.ExtraActivity;
import com.example.game.R;
import com.example.game.level2.ChooseDifficulty;

public class GameOver extends AppCompatActivity {

    // Initialize instance next
    Button next;

    /**
     * onCreate method creates the layout the game. A game over screen.
     * @param savedInstanceState initialize the game over screen layout.
     */

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over_layout);

        next = findViewById(R.id.next_button);

        //when next button is pressed, lead the player back to ExtraActivty.class
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameOver.this, ChooseDifficulty.class);
                intent.putExtra("statisticsManager", getIntent().getSerializableExtra
                        ("statisticsManager"));
                startActivity(intent);
            }
        });
    }
}
