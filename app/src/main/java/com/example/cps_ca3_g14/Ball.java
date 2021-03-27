package com.example.cps_ca3_g14;

import android.widget.ImageView;

public class Ball {
    float x, y;
    float xVelocity, yVelocity;
    float xAcceleration, yAcceleration;
    float mass;
    ImageView imageView;

    public Ball(float x, float y, float xVelocity, float yVelocity, float xAcceleration,
                float yAcceleration, float mass, ImageView imageView) {
        this.x = x;
        this.y = y;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        this.xAcceleration = xAcceleration;
        this.yAcceleration = yAcceleration;
        this.mass = mass;
        this.imageView = imageView;
        this.updateImageLocation();
    }

    public void move()
    {
        this.calcLoc();
        this.updateImageLocation();
    }

    private void calcLoc() {
        this.x += this.xAcceleration * Math.pow(MainActivity.TIME_INTERVAL_SECONDS, 2) / 2 +
                this.xVelocity * MainActivity.TIME_INTERVAL_SECONDS;
        this.y += this.yAcceleration * Math.pow(MainActivity.TIME_INTERVAL_SECONDS, 2) / 2 +
                this.yVelocity * MainActivity.TIME_INTERVAL_SECONDS;
    }

    private void updateImageLocation() {
        imageView.setX(this.x);
        imageView.setY(this.y);
    }

}