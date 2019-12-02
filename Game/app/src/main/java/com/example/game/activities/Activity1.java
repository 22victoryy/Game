package com.example.game.activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.game.R;
import com.example.game.level1.accessories.MusicPlayer;
import com.example.game.level1.accessories.TriviaGameMusicPlayer;
import com.example.game.level1.questionbanks.EnglishQuestions;
import com.example.game.level1.questionbanks.FrenchQuestions;
import com.example.game.level1.questionbanks.MathQuestions;
import com.example.game.level1.questionbanks.ScienceQuestions;
import com.example.game.level1.PlayerPersonalTrivia;
import com.example.game.level1.TriviaGame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * Class leading to level 1
 */
public class Activity1 extends AppCompatActivity {

    private MusicPlayer musicPlayer;

    /**
     * What is created once the activity starts
     * @param savedInstanceState - the bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //initializing the music player
        musicPlayer = new TriviaGameMusicPlayer(this);
    }

    /**
     * When the activity is created, the options menu is too
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity1_menu, menu);
        return true;
    }

    /**
     * What happens when the user selects and item from the menu
     *
     * @param item - the item from the menu that the user selects
     * @return boolean
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        if (item.getItemId() == R.id.playerTrivia) { //if the user selects the settings button
            Intent intent = new Intent(this, PlayerPersonalTrivia.class);
            startActivity(intent);
        }

        if (item.getItemId() == R.id.musicIcon) { //if the user selects the music icon
            musicPlayer.update(item);
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Determines what happens when you click the 'Math' button. AKA you go to the math game
     *
     * @param view - the view
     */
    public void mathGame(View view) {
        startGame(new MathQuestions().getSubjectID());
    }

    /**
     * The science game
     *
     * @param view - the view
     */
    public void scienceGame(View view) {
        startGame(new ScienceQuestions().getSubjectID());
    }

    /**
     * The English game
     *
     * @param view - this view
     */
    public void englishGame(View view) {
        startGame(new EnglishQuestions().getSubjectID());
    }

    /**
     * The French Game
     *
     * @param view - this view
     */
    public void frenchGame(View view) {
        startGame(new FrenchQuestions().getSubjectID());
    }

    /**
     * Starting the game based on the subject chosen
     *
     * @param subject - the subject that the user chose
     */
    public void startGame(String subject) {
        Intent intent = new Intent(this, TriviaGame.class);
        intent.putExtra("Subject", subject);
        intent.putExtra("statisticsManager", getIntent().getSerializableExtra(
                "statisticsManager"));
        startActivity(intent);
    }

    /**
     * Determines what happens when you click the 'Skip to Next Level' button. AKA you go to the
     * second game
     *
     * @param view - the view
     */
    public void toLevel2(View view) {
        Intent intent = new Intent(this, Activity2.class);
        intent.putExtra("statisticsManager", getIntent().getSerializableExtra(
                "statisticsManager"));
        startActivity(intent);
    }

    /**
     * Ending the Game
     *
     * @param view - this view
     */
    public void endGame(View view) {
        Intent intent = new Intent(this, GameStart.class);
        intent.putExtra("statisticsManager", getIntent().getSerializableExtra(
                "statisticsManager"));
        startActivity(intent);
    }
}
