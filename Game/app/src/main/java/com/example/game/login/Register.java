package com.example.game.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.game.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;


public class Register extends AppCompatActivity {

    private EditText userNameText, userPasswordText, userEmailText;
    private Button regBtn;
    private TextView proceedLoginView;
    private FirebaseAuth dbAuth;

    /**
     * Redirects user after registration. Database needs to be implemented with confirmation.
     * @param savedInstanceState Register state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setupUI();

        dbAuth = FirebaseAuth.getInstance();

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateUser()) {
                    String initEmail = userEmailText.getText().toString();
                    String initPass = userPasswordText.getText().toString();

                    dbAuth.createUserWithEmailAndPassword(initEmail, initPass).
                            addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    // if successful
                                    if (task.isSuccessful()){
                                        Toast.makeText(Register.this, "Successfully "
                                                + "Registered", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(Register.this,
                                                MainActivity.class));
                                    }
                                    else {
                                        // throw exception if registration failed
                                        FirebaseAuthException e = (FirebaseAuthException )task.
                                                getException();
                                        Toast.makeText(Register.this, "Failed " +
                                                "Registration: "+e.getMessage(), Toast.LENGTH_SHORT)
                                                .show();
                                    }
                                }
                            });
                }
            }
        });

        // proceed to logging in
        proceedLoginView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, MainActivity.class));
            }
        });
    }

    /**
     * Gets data from layout
     */
    private void setupUI() {
        userNameText = findViewById(R.id.resName);
        userPasswordText = findViewById(R.id.resPass);
        regBtn = findViewById(R.id.register);
        proceedLoginView = findViewById(R.id.proceedLogin);
        userEmailText = findViewById(R.id.resEmail);
    }

    /**
     * Validates user registration if all the fields are entered.
     * @return Boolean
     */
    private Boolean validateUser() {
        boolean authenticate = false;

        String name = userNameText.getText().toString();
        String password = userPasswordText.getText().toString();
        String email = userEmailText.getText().toString();

        // Ensure all fields are entered
        if (name.isEmpty() || password.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Please enter all the fields!",
                    Toast.LENGTH_SHORT).show();
        } else {
            authenticate = true;
        }
        return authenticate;
    }
}
