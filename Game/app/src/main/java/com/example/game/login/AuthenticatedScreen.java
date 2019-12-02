package com.example.game.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.game.activities.GameStart;
import com.example.game.R;
import com.google.firebase.auth.FirebaseAuth;
import androidx.appcompat.app.AppCompatActivity;


public class AuthenticatedScreen extends AppCompatActivity {

    private FirebaseAuth dbAuth;

    /**
     * Implementation of method to sign out of saved instance of user or continue
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        dbAuth = FirebaseAuth.getInstance();

        Button resume = findViewById(R.id.cont);

        Button logout = findViewById(R.id.btnlogout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbAuth.signOut();
                finish();
                startActivity(new Intent(AuthenticatedScreen.this, MainActivity.class));
            }
        });

        resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AuthenticatedScreen.this, GameStart.class);
                intent.putExtra("statisticsManager", getIntent().getSerializableExtra("statisticsManager"));
                startActivity(intent);
            }
        });
    }
}
