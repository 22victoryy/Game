package com.example.game.level1;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.game.activities.Activity2;
import com.example.game.activities.GameStart;
import com.example.game.R;
import com.example.game.activities.HiddenBonusActivity;
import com.example.game.statistics.StatisticsManager;
import com.example.game.level1.accessories.CountDown;
import com.example.game.level1.accessories.TriviaGameCountDown;
import com.example.game.level1.core.AnswerChecker;
import com.example.game.level1.core.QuestionsAssigner;
import com.example.game.level1.core.QuestionsOptionsUpdater;
import com.example.game.level1.core.TriviaGameAnswerChecker;
import com.example.game.level1.core.TriviaGameQuestionsAssigner;
import com.example.game.level1.core.TriviaGameQuestionsOptionsUpdater;
import com.example.game.level1.questionbanks.Questions;

public class TriviaGame extends AppCompatActivity {

    private TextView questionText;
    private TextView option1Text;
    private TextView option2Text;
    private TextView option3Text;
    private TextView option4Text;

    private TextView pointsText;
    private TextView countDownText;

    private QuestionsOptionsUpdater questionsOptionsUpdater;
    private AnswerChecker answerChecker;

    private StatisticsManager statisticsManager;
    private CountDown countDown;


    /**
     * What is created once the activity begins
     *
     * @param savedInstanceState - a bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_trivia_game);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //get the subject the player wanted
        Intent intent = this.getIntent();
        String subject = intent.getStringExtra("Subject");

        //initializing variables
        questionText = findViewById(R.id.question);
        option1Text = findViewById(R.id.questionOption1);
        option2Text = findViewById(R.id.questionOption2);
        option3Text = findViewById(R.id.questionOption3);
        option4Text = findViewById(R.id.questionOption4);
        pointsText = findViewById(R.id.numberOfPoints);
        countDownText = findViewById(R.id.countDownText);

        QuestionsAssigner questionsAssigner = new TriviaGameQuestionsAssigner(subject);
        Questions questions = questionsAssigner.getQuestions();
        questionsOptionsUpdater = new TriviaGameQuestionsOptionsUpdater(questions, this);
        answerChecker = new TriviaGameAnswerChecker(questionsOptionsUpdater);

        long TIME_TO_PLAY = 30000; //can modify
        countDown = new TriviaGameCountDown(this, TIME_TO_PLAY);

        statisticsManager = (StatisticsManager) intent.getSerializableExtra(
                "statisticsManager");

        setPointsText(statisticsManager.getScore());

        //displaying questionText with options
        questionsOptionsUpdater.update();

        //starting the countdown
        countDown.startCountDownTimer();
    }

    /**
     * Add items to the action bar
     *
     * @param menu - the menu
     * @return boolean
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.trivia_game_menu, menu);
        return true;
    }

    /**
     * Handle action bar item clicks.
     *
     * @param item - the item in the menu
     * @return boolean
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        Intent intent;
        if (item.getItemId() == R.id.quitGameButton) { //if the user clicks the quit game button
            intent = new Intent(this, GameStart.class);
        } else { //if the id is the next game button
            intent = new Intent(this, Activity2.class);
        }

        intent.putExtra("statisticsManager", statisticsManager);
        startActivity(intent);

        return super.onOptionsItemSelected(item);
    }

    /**
     * Set the text of the questionText to be a trivia question
     *
     * @param question - the trivia questionText
     */
    public void setQuestionText(String question) {
        this.questionText.setText(question);
    }

    /**
     * Set the text of option1Text to be the first answer option (option1)
     *
     * @param option1 - the first answer option of the questionText
     */
    public void setOption1Text(String option1) {
        this.option1Text.setText(option1);
    }

    /**
     * Set the text of option2Text to be the second answer option (option2)
     *
     * @param option2 - the second answer option of the questionText
     */
    public void setOption2Text(String option2) {
        this.option2Text.setText(option2);
    }

    /**
     * Set the text of option3Text to be the third answer option (option3)
     *
     * @param option3 - the third answer option of the questionText
     */
    public void setOption3Text(String option3) {
        this.option3Text.setText(option3);
    }

    /**
     * Set the text of option4Text to be the fourth answer option (option4)
     *
     * @param option4 - the fourth answer option of the questionText
     */
    public void setOption4Text(String option4) {
        this.option4Text.setText(option4);
    }

    /**
     * Set the text on the screen based on the number of points the player has
     *
     * @param numOfPoints - the number of points the player has
     */
    public void setPointsText(int numOfPoints) {
        String points = getResources().getString(R.string.points) + numOfPoints;
        pointsText.setText(points);
    }

    /**
     * Set the text that displays the amount of time remaining before the game ends
     *
     * @param timeRemaining - the amount of time that is remaining before the game ends
     */
    public void setCountDownText(String timeRemaining) {
        countDownText.setText(timeRemaining);

    }

    /**
     * This method is executed if the player clicks an option of the questionText
     *
     * @param view - the view (which option answer the user selected)
     */
    public void answerSelected(View view) {
        answerChecker.checkAnswer(view);
        if (answerChecker.isPointEarned()) {
            statisticsManager.addScore();
            setPointsText(statisticsManager.getScore());
            Toast.makeText(this, "+1 Point", Toast.LENGTH_SHORT).show();
        }

        //a delay before the questionText and options update so that the player has time to see if
        // their answer was right or wrong
        long lengthOfTimeBetweenQuestions = 500;  //can modify
        questionsOptionsUpdater.updateAfterDelay(view, lengthOfTimeBetweenQuestions);
    }

    /**
     * This method is executed when the game ends.
     */
    public void endGame() {
        countDown.stopCountDownTimer();
        Intent intent = new Intent(this, TriviaGameEndActivity.class);
        intent.putExtra("Final Point Count", Integer.toString(statisticsManager.getScore()));
        //so that the next screen can display points
        intent.putExtra("statisticsManager", statisticsManager);
        startActivity(intent);
    }

    /**
     * Takes the player to the hidden bonus activity wants they click this corresponding button
     * (view)
     *
     * @param view - the view
     */
    public void goToHiddenActivity(View view) {
        countDown.stopCountDownTimer();
        Intent intent = new Intent(this, HiddenBonusActivity.class);
        intent.putExtra("statisticsManager", getIntent().getSerializableExtra(
                "statisticsManager"));
        startActivity(intent);
    }
}
