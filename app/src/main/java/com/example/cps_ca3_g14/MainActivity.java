package com.example.cps_ca3_g14;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static final String SENSOR_TYPE = "SensorType";
    public static final String GYROSCOPE = "Gyroscope";
    public static final String GRAVITY = "Gravity";
    public static final float TIME_INTERVAL_SECONDS = 0.01f;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void useGyroscope(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra(SENSOR_TYPE, GYROSCOPE);
        startActivity(intent);
    }
    public void useGravity(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra(SENSOR_TYPE, GRAVITY);
        startActivity(intent);
    }

}