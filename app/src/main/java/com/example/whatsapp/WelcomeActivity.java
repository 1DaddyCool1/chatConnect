package com.example.whatsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class WelcomeActivity extends AppCompatActivity {

    private View agreeBtn;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        agreeBtn = findViewById(R.id.agree_button);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();


        agreeBtn.setOnClickListener((v) -> {
                Intent loginIntent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(loginIntent);
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(currentUser != null)
        {
            Intent myIntent = new Intent(WelcomeActivity.this, MainActivity.class);
            startActivity(myIntent);
        }
    }
}