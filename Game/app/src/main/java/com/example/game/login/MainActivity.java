package com.example.game.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import android.widget.ProgressBar;
import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.game.R;
import com.example.game.statistics.StatisticsManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    private EditText emailText;
    private EditText passwordText;
    private TextView infoView;
    private Button loginBtn;

    private int numOfTries;

    private FirebaseAuth dbAuth;
    private ProgressBar progressBar;
    private StatisticsManager statisticsManager = new StatisticsManager();

    /**
     * Login method
     * @param savedInstanceState Login instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emailText = findViewById(R.id.initEmail);
        passwordText = findViewById(R.id.initPassword);
        infoView = findViewById(R.id.error);
        loginBtn = findViewById(R.id.login);
        TextView userRegister = findViewById(R.id.initRegister);

        dbAuth = FirebaseAuth.getInstance();

        FirebaseUser currUser = dbAuth.getCurrentUser();

        String initAttempt = "Number of Incorrect Attempts: " + numOfTries;

        infoView.setText(initAttempt);

        progressBar = findViewById(R.id.progressbar);

        progressBar.setVisibility(View.GONE);

        // this saves the user from re-logging in. will implement logging out.
        if (currUser != null) {
            finish();
            Intent intent = new Intent(MainActivity.this, AuthenticatedScreen.class);
            intent.putExtra("statisticsManager", statisticsManager);
            startActivity(intent);
        }

        // validate user
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Validate(emailText.getText().toString(), passwordText.getText().toString());
            }
        });

        // Navigate to registration
        userRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Register.class));
            }
        });
    }

    /**
     * Validate user and load session
     * @param userEmail userEmail
     * @param userPassword userPassword
     */
    private void Validate(String userEmail, String userPassword) {
        if (isNotEmpty()) {
            dbAuth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(MainActivity.this, "Login Success!", Toast.
                                LENGTH_SHORT)
                                .show();
                        Intent intent = new Intent(MainActivity.this,
                                AuthenticatedScreen.class);
                        intent.putExtra("statisticsManager", statisticsManager);
                        startActivity(intent);
                    }
                    else {
                        FirebaseAuthException e = (FirebaseAuthException)task.
                                getException(); // throw exception if failed
                        // documentation:
                        // https://firebase.google.com/docs/reference/android/com/google/firebase/auth/FirebaseAuthException

                        Toast.makeText(MainActivity.this, "Failed. "
                                + Objects.requireNonNull(e).getMessage(), Toast.LENGTH_SHORT)
                                .show();
                        numOfTries++;

                        String attempts = "Number of attempts: " + numOfTries;
                        infoView.setText(attempts);

                        if (numOfTries == 5) {
                            loginBtn.setEnabled(false); // disable loginBtn if too many attempts
                            String retry = "Too many attempts: Please reopen the app and try " +
                                    "again.";
                            infoView.setText(retry);
                        }
                    }
                }
            });
        }
    }

    /**
     * Checks if all the fields are entered
     * @return true if
     */
    private Boolean isNotEmpty(){
        boolean result = false;

        String authEmail = emailText.getText().toString();
        String authPassword = passwordText.getText().toString();

        // check if empty
        if (emailText != null && authEmail.isEmpty() || passwordText != null && authPassword.isEmpty
                ()) {
            Toast.makeText(this, "All fields must not be blank.", Toast.LENGTH_SHORT)
                    .show();
        } else {
            result = true;
        }
        return result;
    }
}
