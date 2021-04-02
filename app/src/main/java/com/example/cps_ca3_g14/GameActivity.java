package com.example.cps_ca3_g14;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {
    private boolean usingGyroscope = false;
    private Board board;
    private Ball ball;
    final int ballRadius = 40;
    private SensorManager sensorManager;
    private Sensor sensor;

    private long t0;

    private float tethaX = 0;
    private float tethaY = 0;
    private float tethaZ = 0;

    private SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            long t1 = System.currentTimeMillis();
            float deltaT = (float) (t1 - t0) / 1000;

            if(usingGyroscope) {
                float newTethaX = sensorEvent.values[1] * deltaT + tethaX;
                float newTethaY = sensorEvent.values[0] * deltaT + tethaY;
                float newTethaZ = sensorEvent.values[2] * deltaT + tethaZ;

                float newXGravity = Board.G * (float)Math.sin(newTethaX);
                float newYGravity = Board.G * (float)Math.sin(newTethaY);
                float newZGravity = Board.G * (float)Math.cos(newTethaZ);

                tethaX = newTethaX;
                tethaY = newTethaY;
                tethaZ = newTethaZ;

                ball.gravityUpdate(newXGravity, newYGravity, newZGravity, deltaT);
            }
            else {
                ball.gravityUpdate(-sensorEvent.values[0], sensorEvent.values[1], sensorEvent.values[2] , deltaT);
            }
            t0 = t1;
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO: barrier token has not been posted or has already been removed
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.SENSOR_TYPE);

        if (Build.VERSION.SDK_INT < 19) {       // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        }
        else {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
            decorView.setSystemUiVisibility(uiOptions);
        }


        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        if (message.equals(MainActivity.GYROSCOPE)) {
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
            this.usingGyroscope = true;
        } else {
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        }
        sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_FASTEST);

        this.board = new Board();
        Pair<Integer, Integer> startingPoint = board.getRandomPoint();

        ImageView ballImage = findViewById(R.id.ball);
        this.ball = new Ball(
                startingPoint.first.floatValue(),
                startingPoint.second.floatValue(),
                0,
                0,
                0.01f,
                this.ballRadius,
                ballImage
        );

        TextView textView = findViewById(R.id.using_sensor);
        textView.setText(message);

        t0 = System.currentTimeMillis();
    }

    public void startGame(View view) {
        findViewById(R.id.start_button).setVisibility(View.INVISIBLE);
        findViewById(R.id.shoot_ball).setVisibility(View.VISIBLE);
//        ball.move();

        Timer t = new Timer();
        t.schedule(new TimerTask() {
                @Override
                public void run() {
                    ball.updateImageLocation();
//                    checkCollision();
                }
            }, 0, 20);
    }

    public void shootTheBall(View view) {
        Pair<Integer, Integer> randomPoint = board.getRandomPoint();
        this.ball.setX0(randomPoint.first);
        this.ball.setY0(randomPoint.second);
        this.ball.resetV();
    }

//    private void checkCollision() {
//        /// DONE: in case of collision, reduce the velocity.
//        this.checkCeilingCollision();
//        this.checkFloorCollision();
//        this.checkLeftWallCollision();
//        this.checkRightWallCollision();
//    }

//    private void checkCeilingCollision() {
//        if (this.ball.getY() <= 0)
//            this.ball.reverseYVelocity();
//    }
//
//    private void checkFloorCollision() {
//        if (this.ball.getY() + 2 * this.ballRadius >= Board.height)
//            this.ball.reverseYVelocity();
//    }
//
//    private void checkLeftWallCollision() {
//        if (this.ball.getX() <= 0)
//            this.ball.reverseXVelocity();
//    }
//
//    private void checkRightWallCollision() {
//        if (this.ball.getX() + 2 * this.ballRadius >= Board.width)
//            this.ball.reverseXVelocity();
//    }

}