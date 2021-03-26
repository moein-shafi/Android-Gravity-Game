package com.example.cps_ca3_g14;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class GyroscopeActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyroscope);
    }

    public void startGame(View view) {
        ImageView ballImage = findViewById(R.id.ball);
        /// TODO: set random for x, y and others.
        /// TODO: invisible the start button.
        Ball ball = new Ball(10, 10, 5, 5, 2,2,1, ballImage);
        ball.updateLocation();
        Timer t = new Timer();
        t.schedule(new TimerTask() {
                @Override
                public void run() { ball.updateLocation();
                }
            }, 1, 100);
    }
}