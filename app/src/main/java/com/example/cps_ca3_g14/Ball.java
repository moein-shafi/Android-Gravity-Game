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
        this.xVelocity = 0;
        this.yVelocity = 0;
        this.imageView = imageView;

    }

    public void updateLocation()
    {
        /// TODO: update x and y.
        this.x += 1;    // just for test
        this.y += 2;    // just for test
        this.updateImageLocation();
    }

    private void updateImageLocation() {
        imageView.setX(this.x);
        imageView.setY(this.y);
    }

}