package com.example.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Random;

public class UserProfile extends MainActivity{
    Button button;
    TextView textView;
    FirebaseAuth auth;
    FirebaseUser user;
    Button btn_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        auth = FirebaseAuth.getInstance();
        button=findViewById(R.id.logout);
        btn_main=findViewById(R.id.btn_main);
        textView=findViewById(R.id.user_details);
        user = auth.getCurrentUser();

        if(user == null){
            Intent i = new Intent(getApplicationContext(), Login.class);
            startActivity(i);
            finish();

        }else {
            textView.setText(user.getEmail());
        }

        btn_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UserProfile.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(getApplicationContext(), Register.class);
                startActivity(i);
                finish();
            }
        });
    }

}

