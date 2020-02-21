package com.example.whackamole;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.gridlayout.widget.GridLayout;

import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public GridLayout grid;
    public Drawable moleImage;
    public ImageView[] imageViews;
    public int moleLocation;
    public Random rand;
    public boolean begin;
    public Handler handler;
    public Button click;
    public MoleMove move;
    private int score;
    private int time;
    public TextView points;
    public TextView timer;
    public TextView win;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        grid = findViewById(R.id.grid);
        moleImage = getDrawable(R.drawable.mole);
        click = findViewById(R.id.start);
        points = findViewById(R.id.points);
        timer = findViewById(R.id.timer);
        win = findViewById(R.id.win);
        handler = new Handler();
        begin = false;
        imageViews = new ImageView[16];
        rand = new Random();
        move = new MoleMove();
        score = 0;
        time = 0;
        moleLocation = rand.nextInt(16);
        for(int i=0; i<16; i++) {
            imageViews[i] = (ImageView) getLayoutInflater().inflate(R.layout.mole_view, null);
            imageViews[i].setMinimumHeight(270);
            imageViews[i].setMinimumWidth(270);
            if (i == moleLocation) imageViews[i].setImageDrawable(moleImage);
            grid.addView(imageViews[i]);
        }
    }

    public void play(View v){
        if (begin){
            begin = false;
            click.setText("START");
            handler.removeCallbacks(move);
        }else{
            begin = true;
            win.setText(null);
            time = 0;
            timer.setText(time + "");
            points.setText(score + "");
            handler.removeCallbacks(move);
            click.setText("QUIT");
            handler.postDelayed(move, 1000);

        }
    }

    public void whacked(View v){
            if (v.equals(imageViews[moleLocation])) {
                score++;
                points.setText(score + "");
                win.setText("WHACKED!");
            } else {
                win.setText("Missed!");
            }
    }

    private class MoleMove implements Runnable{

        public void run() {
            time++;
            timer.setText(time + "");
            int previous;
            previous = moleLocation;
            moleLocation = rand.nextInt(16);
            imageViews[moleLocation].setImageDrawable(moleImage);
            imageViews[previous].setImageDrawable(null);
            handler.postDelayed(move, 1000);
        }
    }
}