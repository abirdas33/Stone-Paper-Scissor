package com.example.asus.stonepaperscissor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartingPage extends AppCompatActivity {
    Button play,rate,exit,highscore;
    TextView y;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_page);
        play=(Button)findViewById(R.id.play);
        rate=(Button)findViewById(R.id.rate);
        highscore=(Button)findViewById(R.id.High);
        exit=(Button)findViewById(R.id.exit);
        y=(TextView)findViewById(R.id.welcome);


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartingPage.this,MainActivity.class));
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        highscore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(StartingPage.this,PopUpWindow.class));
                y.setVisibility(View.INVISIBLE);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        y.setVisibility(View.VISIBLE);
    }
}
