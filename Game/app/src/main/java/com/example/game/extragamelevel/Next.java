package com.example.game.extragamelevel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.activities.GameStart;
import com.example.game.R;

public class Next extends AppCompatActivity {

    //Initialize button next.
    Button next;

    /**
     * onCreate method creates the layout the game. A screen that leads the player to next level.
     * @param savedInstanceState initialize the next level screen layout.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item);

        next=findViewById(R.id.next_button);

        //when next button is pressed, lead the player to GameStart.class
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Next.this, GameStart.class);
                intent.putExtra("statisticsManager", getIntent().getSerializableExtra
                        ("statisticsManager"));
                startActivity(intent);
            }
        });
    }
}
