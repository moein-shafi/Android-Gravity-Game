package com.example.cps_ca3_g14;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void useGyroscope(View view) {
        Intent intent = new Intent(this, GyroscopeActivity.class);
        startActivity(intent);
    }
    public void useGravity(View view) {
        Intent intent = new Intent(this, GravityActivity.class);
        startActivity(intent);
    }

}