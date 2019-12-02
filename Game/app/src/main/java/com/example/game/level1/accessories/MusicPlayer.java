package com.example.game.level1.accessories;

import android.media.MediaPlayer;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

public abstract class MusicPlayer {

    private AppCompatActivity activity; //so any activity can use this class
    private int musicFile;
    private boolean musicOn = false;
    private MediaPlayer musicPlayer;

    /**
     * The Music Player object constructor
     * @param activity - the activity that the music player is being built in
     */
    MusicPlayer(AppCompatActivity activity){
        this.activity = activity;

    }

//    /**
//     * To get the musicFile
//     * @return the resource id of the music file
//     */
//    public int getMusicFile() {
//        return musicFile;
//    }
    // this function is not used

    /**
     * To set the resource id of the music file if desired. This is the music can be changed
     * @param musicFile - the path of the music file
     */
    void setMusicFile(int musicFile) {
        this.musicFile = musicFile;
    }


    /**
     * Get the status of the music. Is it on or off?
     * @return boolean - if the music is on or off
     */
    boolean isMusicOn() {
        return musicOn;
    }

    /**
     * Set the music to be on or off
     * @param musicOn - is the music on (true)?
     */
    void setMusicOn(boolean musicOn) {
        this.musicOn = musicOn;
    }


    /**
     * Return the activity
     * @return activity - the activity
     */
    public AppCompatActivity getActivity() {
        return activity;
    }

    /**
     * Get the music player object
     * @return musicPlayer - the music player object
     */
    MediaPlayer getMusicPlayer() {
        return musicPlayer;
    }

    /**
     * To update the music and item depending on if the user wants the music on or off
     * @param item - the MenuItem that needs to be updated
     */
    abstract public void update(MenuItem item);

    /**
     * To create the music player
     */
    void createMusicPlayer(){
        musicPlayer = MediaPlayer.create(activity, musicFile);
    }
}
