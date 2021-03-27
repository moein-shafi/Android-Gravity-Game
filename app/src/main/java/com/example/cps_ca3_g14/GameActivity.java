package com.example.cps_ca3_g14;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {
    boolean usingGyroscope = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        if (!message.equals(MainActivity.usingGyroscope))
            usingGyroscope = false;
        TextView textView = findViewById(R.id.using_sensor);
        textView.setText(message);
    }

    public void startGame(View view) {
        findViewById(R.id.start_button).setVisibility(View.INVISIBLE);
        findViewById(R.id.shoot_ball).setVisibility(View.VISIBLE);
        /// TODO: create Sensor instance.
        /// TODO: set random for x, y and others.
        ImageView ballImage = findViewById(R.id.ball);
        Ball ball = new Ball(10, 10, 5, 5, 2,2,1, ballImage);
        ball.updateLocation();
        Timer t = new Timer();
        t.schedule(new TimerTask() {
                @Override
                public void run() { ball.updateLocation();
                }
            }, 1, 100);
    }

    public void shootTheBall(View view) {

    }
}