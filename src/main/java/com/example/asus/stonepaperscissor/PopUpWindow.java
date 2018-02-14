package com.example.asus.stonepaperscissor;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

public class PopUpWindow extends AppCompatActivity {
    TextView High_score;
    int LastScore,HumanScore,best1,best2,best3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_window);

        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width=dm.widthPixels;
        int height=dm.heightPixels;
        getWindow().setLayout((int)(width*.8),(int)(height*.8));
        High_score=(TextView)findViewById(R.id.high_score);

        SharedPreferences myscore=getSharedPreferences("myawsomescore",0);
        LastScore=myscore.getInt("score",0);
        best1=myscore.getInt("best1",0);
        best2=myscore.getInt("best2",0);
        best3=myscore.getInt("best3",0);

        if (LastScore>best3){
            best3=LastScore;
            SharedPreferences.Editor editor=myscore.edit();
            editor.putInt("best3",best3);
            editor.commit();
        }
        if (LastScore>best2){
            int temp=best2;
            best2=LastScore;
            best3=temp;
            SharedPreferences.Editor editor=myscore.edit();
            editor.putInt("best3",best3);
            editor.putInt("best2",best2);
            editor.commit();
        }
        if (LastScore>best1){
            int temp=best1;
            best1=LastScore;
            best2=temp;
            SharedPreferences.Editor editor=myscore.edit();
            editor.putInt("best1",best1);
            editor.putInt("best2",best2);
            editor.commit();
        }

        High_score.setText("Lastscore: " + LastScore + "\n"
        + "Best1: " + best1 + "\n"
        + "Best2: " + best2 + "\n"
        + "Best3: " + best3 );
    }
}
