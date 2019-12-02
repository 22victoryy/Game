package com.example.game.level1.accessories;

import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.game.R;

import java.io.Serializable;


public class TriviaGameMusicPlayer extends MusicPlayer implements Serializable {

    /**
     * The music player constructor for specifically for trivia games
     * @param activity - the activity that this music player is for
     */
    public TriviaGameMusicPlayer(AppCompatActivity activity) {
        super(activity);
        setMusicFile(R.raw.moonlightsonata);
        createMusicPlayer();
    }

    /**
     * To update the music and item depending on if the user wants the music on or off
     * @param item - the MenuItem that needs to be updated
     */
    @Override
    public void update(MenuItem item) {
        setMusicOn(!isMusicOn());//to change whether the music was on or off
        if (isMusicOn()) { //every odd click turns the music on
            getMusicPlayer().start();
            item.setIcon(R.drawable.ic_music_on);
            displayToast("Music is on");
        } else { //every even click turns the music off
            getMusicPlayer().pause();
            displayToast("Music is off");
            item.setIcon(R.drawable.ic_music_off);
        }
    }

    /**
     * To display if the music is on or off
     * @param message - what the toast should display
     */
    private void displayToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }
}