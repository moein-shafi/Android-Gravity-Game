package com.example.cps_ca3_g14;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "UsedSensor";
    public static final String usingGyroscope = "using Gyroscope!";
    public static final String usingGravity = "using Gravity!";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void useGyroscope(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra(EXTRA_MESSAGE, usingGyroscope);
        startActivity(intent);
    }
    public void useGravity(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra(EXTRA_MESSAGE, usingGravity);
        startActivity(intent);
    }

}