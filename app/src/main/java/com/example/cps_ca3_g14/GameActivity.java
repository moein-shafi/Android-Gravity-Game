package com.example.cps_ca3_g14;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

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
                50,
                50,
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
        ball.move();
        Timer t = new Timer();
        t.schedule(new TimerTask() {
                @Override
                public void run() {
                    ball.move();
                    checkCollision();
                }
            }, (int) MainActivity.TIME_INTERVAL_SECONDS * 1000, 1);
    }

    public void shootTheBall(View view) {
        Pair<Integer, Integer> randomPoint = board.getRandomPoint();
        this.ball.setX(randomPoint.first);
        this.ball.setY(randomPoint.second);
        /// TODO: reset ball velocity and acceleration.
    }

    private void checkCollision() {
        /// TODO: in case of collision, reduce the velocity.
        this.checkCeilingCollision();
        this.checkFloorCollision();
        this.checkLeftWallCollision();
        this.checkRightWallCollision();
    }

    private void checkCeilingCollision() {
        if (this.ball.getY() <= 0)
            this.ball.reverseYVelocity();
    }

    private void checkFloorCollision() {
        if (this.ball.getY() + 2 * this.ballRadius >= this.board.getHeight())
            this.ball.reverseYVelocity();
    }

    private void checkLeftWallCollision() {
        if (this.ball.getX() <= 0)
            this.ball.reverseXVelocity();
    }

    private void checkRightWallCollision() {
        if (this.ball.getX() + 2 * this.ballRadius >= this.board.getWidth())
            this.ball.reverseXVelocity();
    }

}