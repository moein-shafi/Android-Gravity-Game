package com.example.cps_ca3_g14;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {
    boolean usingGyroscope = true;
    Board board;
    Ball ball;
    final int ballRadius = 40;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO: barrier token has not been posted or has already been removed
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.SENSOR_TYPE);
        if (!message.equals(MainActivity.GYROSCOPE))
            this.usingGyroscope = false;

        this.board = new Board();
        Pair<Integer, Integer> startingPoint = board.getRandomPoint();

        ImageView ballImage = findViewById(R.id.ball);
        this.ball = new Ball(
                startingPoint.first.floatValue(),
                startingPoint.second.floatValue(),
                10,
                10,
                0,
                0,
                1,
                this.ballRadius,
                ballImage
        );

        TextView textView = findViewById(R.id.using_sensor);
        textView.setText(message);
    }

    public void startGame(View view) {
        findViewById(R.id.start_button).setVisibility(View.INVISIBLE);
        findViewById(R.id.shoot_ball).setVisibility(View.VISIBLE);
        /// TODO: create Sensor instance.
        /// TODO: set random for x, y and others.
        ball.move();
        Timer t = new Timer();
        t.schedule(new TimerTask() {
                @Override
                public void run() { ball.move();
                }
            }, (int) MainActivity.TIME_INTERVAL_SECONDS * 1000, 1);
    }

    public void shootTheBall(View view) {

    }
}