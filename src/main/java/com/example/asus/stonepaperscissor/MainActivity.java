package com.example.asus.stonepaperscissor;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    InterstitialAd mInterstitialAd;
    private InterstitialAd interstitial;
    Button stone,paper,scissor;
    TextView score,hand;
    ImageView humanchoice,computerchoice;
    int HumanScore=0,ComputerScore=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        //mAdView.loadAd(adRequest);*/

// Prepare the Interstitial Ad
        interstitial = new InterstitialAd(MainActivity.this);
// Insert the Ad Unit ID
        interstitial.setAdUnitId(getString(R.string.admob_interstitial_id));

        interstitial.loadAd(adRequest);
// Prepare an Interstitial Ad Listener
        interstitial.setAdListener(new AdListener() {
            public void onAdLoaded() {
                // Call displayInterstitial() function
                displayInterstitial();
            }
        });

        stone=(Button)findViewById(R.id.stone);
        hand=(TextView)findViewById(R.id.hand2);
        paper=(Button)findViewById(R.id.paper);
        scissor=(Button)findViewById(R.id.scissor);
        humanchoice=(ImageView)findViewById(R.id.humpic);
        computerchoice=(ImageView)findViewById(R.id.compic);
        score=(TextView)findViewById(R.id.score);


        stone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                humanchoice.setImageResource(R.drawable.right2);
                String message=play_turn("stone");
                SharedPreferences myscore=getSharedPreferences("myawsomescore",0);
                SharedPreferences.Editor editor=myscore.edit();
                editor.putInt("score",HumanScore);
                editor.commit();

                Toast.makeText(MainActivity.this,message,Toast.LENGTH_SHORT).show();
                score.setText("Score: Human: " + Integer.toString(HumanScore) + " Computer: " + Integer.toString(ComputerScore));
            }
        });

        paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                humanchoice.setImageResource(R.drawable.right1);
                String message=play_turn("paper");
                SharedPreferences myscore=getSharedPreferences("myawsomescore",0);
                SharedPreferences.Editor editor=myscore.edit();
                editor.putInt("score",HumanScore);
                editor.commit();

                Toast.makeText(MainActivity.this,message,Toast.LENGTH_SHORT).show();
                score.setText("Score: Human: " + Integer.toString(HumanScore) + " Computer: " + Integer.toString(ComputerScore));
            }
        });

        scissor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                humanchoice.setImageResource(R.drawable.right3);
                String message=play_turn("scissor");
                SharedPreferences myscore=getSharedPreferences("myawsomescore",0);
                SharedPreferences.Editor editor=myscore.edit();
                editor.putInt("score",HumanScore);
                editor.commit();

                Toast.makeText(MainActivity.this,message,Toast.LENGTH_SHORT).show();
                score.setText("Score: Human: " + Integer.toString(HumanScore) + " Computer: " + Integer.toString(ComputerScore));
            }
        });
    }

    public String play_turn(String player_choice){
        String computer_choice="";
        Random r=new Random();
        int computer_choice_number=r.nextInt(3)+1;
        if(computer_choice_number==1){
            computer_choice="stone";
        }else if (computer_choice_number==2){
            computer_choice="paper";
        }else if (computer_choice_number==3){
            computer_choice="scissor";
        }

        if (computer_choice=="stone"){
            computerchoice.setImageResource(R.drawable.left2);
        }else if (computer_choice=="paper"){
            computerchoice.setImageResource(R.drawable.left1);
        }else if (computer_choice=="scissor"){
            computerchoice.setImageResource(R.drawable.left3);
        }

        if (computer_choice==player_choice){
            return "Draw. Nobody Won";
        }else if (player_choice=="stone" && computer_choice=="scissor"){
            HumanScore++;
            return "stone crushes scissor. Congrats! You Won";
        }else if (player_choice=="scissor" && computer_choice=="paper"){
            HumanScore++;
            return "scissor cut paper. Congrats! You Won";
        }else if (player_choice=="paper" && computer_choice=="stone"){
            HumanScore++;
            return "paper covers stone. Congrats! You Won";
        }else if (player_choice=="stone" && computer_choice=="paper"){
            ComputerScore++;
            return "paper covers stone. Computer Won";
        }else if (player_choice=="paper" && computer_choice=="scissor"){
            ComputerScore++;
            return "paper cuts scissor. Computer Won";
        }else if (player_choice=="scissor" && computer_choice=="stone"){
            ComputerScore++;
            return "stone crushes scissor. Congrats! You Won";
        }else
            return "not sure";

    }
    public void displayInterstitial() {
// If Ads are loaded, show Interstitial else show nothing.
        if (interstitial.isLoaded()) {
            interstitial.show();
        }
    }
}
