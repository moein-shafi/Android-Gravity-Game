package com.example.cps_ca3_g14;

import android.widget.ImageView;

public class Ball {
    float x0, y0;
    float vx0, vy0;
    float mass;

    int radius;
    ImageView imageView;

    public Ball(float x0, float y0, float vx0, float vy0, float mass, int radius, ImageView imageView) {
        this.x0 = x0;
        this.y0 = y0;
        this.vx0 = vx0;
        this.vy0 = vy0;
        this.mass = mass;
        this.imageView = imageView;
        this.radius = radius;
        this.imageView.getLayoutParams().width = 2 * this.radius;
        this.imageView.getLayoutParams().height = 2 * this.radius;
//        this.updateImageLocation();
    }

//    public void move()
//    {
////        this.calcLoc();
////        this.updateImageLocation();
//    }

    public void gravityUpdate(float xGravity, float yGravity, float zGravity, float deltaT)
    {
        this.checkCollision();

        float fn = zGravity * this.mass;
        float sFriction = fn * Board.STATIC_FRICTION;
        float kFriction = fn * Board.KINETIC_FRICTION;

        boolean canMoveX = true;
        boolean canMoveY = true;

        float xFriction = 0;
        float yFriction = 0;

        if (x0 == 0 && vx0 == 0 && xGravity <= 0) {
            // 3
            if (vy0 == 0 && Math.abs(yGravity * this.mass) <= Math.abs(sFriction)) {
                canMoveY = false;
            } else if (vy0 < 0) {
                yFriction = kFriction;
            } else if (vy0 > 0) {
                yFriction = -kFriction;
            }
        }

        if (x0 + 2 * this.radius == Board.width && vx0 == 0 && xGravity >= 0) {
            // 1
            if (vy0 == 0 && Math.abs(yGravity * this.mass) <= Math.abs(sFriction)) {
                canMoveY = false;
            } else if (vy0 < 0) {
                yFriction = kFriction;
            } else if (vy0 > 0) {
                yFriction = -kFriction;
            }
        }

        if (y0 + 2 * this.radius == Board.height && vy0 == 0 && yGravity >= 0) {
            // 2
            if (vx0 == 0 && Math.abs(xGravity * this.mass) <= Math.abs(sFriction)) {
                canMoveX = false;
            } else if (vx0 < 0) {
                xFriction = kFriction;
            } else if (vx0 > 0) {
                xFriction = -kFriction;
            }
        }

        if (y0 == 0 && vy0 == 0 && yGravity <= 0) {
            // 0
            if (vx0 == 0 && Math.abs(xGravity * this.mass) <= Math.abs(sFriction)) {
                canMoveX = false;
            } else if (vx0 < 0) {
                xFriction = kFriction;
            } else if (vx0 > 0) {
                xFriction = -kFriction;
            }
        }

        float ax = (xGravity + (xFriction / this.mass)) * MainActivity.ACCELERATION_SCALE;
        float ay = (yGravity + (yFriction / this.mass)) * MainActivity.ACCELERATION_SCALE;

        if (canMoveX) {
            this.x0 += ax * Math.pow(deltaT, 2) / 2 + this.vx0 * deltaT;
            this.vx0 = ax * deltaT + vx0;
        }

        if (canMoveY) {
            this.y0 += ay * Math.pow(deltaT, 2) / 2 + this.vy0 * deltaT;
            this.vy0 = ay * deltaT + vy0;
        }

        this.normalizeValues();
    }

    private void checkCollision() {
        if (this.y0 < 0) {
            this.vy0 = (float) (-this.vy0 * Math.sqrt(0.9));
        }
        if (this.y0 + 2 * this.radius > Board.height) {
            this.vy0 = (float) (-this.vy0 * Math.sqrt(0.9));
        }
        if (this.x0 < 0) {
            this.vx0 = (float) (-this.vx0 * Math.sqrt(0.9));
        }
        if (this.x0 + 2 * this.radius > Board.width) {
            this.vx0 = (float) (-this.vx0 * Math.sqrt(0.9));
        }
    }

    private void normalizeValues() {
        if (Math.abs(vx0) < 1) {
            vx0 = 0;
        }
        if (Math.abs(vy0) < 1) {
            vy0 = 0;
        }
        if (Math.abs(x0) < 1) {
            x0 = 0;
        }
        if (Math.abs(y0) < 1) {
            y0 = 0;
        }
    }

    private void calcLoc() {
//        this.x += this.xAcceleration * Math.pow(MainActivity.TIME_INTERVAL_SECONDS, 2) / 2 +
//                this.xVelocity * MainActivity.TIME_INTERVAL_SECONDS;
//        this.y += this.yAcceleration * Math.pow(MainActivity.TIME_INTERVAL_SECONDS, 2) / 2 +
//                this.yVelocity * MainActivity.TIME_INTERVAL_SECONDS;
    }

    public void updateImageLocation() {
        imageView.setX(this.x0);
        imageView.setY(this.y0);
    }

    public float getX0() { return this.x0; }

    public float getY0() { return this.y0; }

    public void setX0(float x0) {
        this.x0 = x0;
    }

    public void setY0(float y0) {
        this.y0 = y0;
    }

    public void resetV() {
        this.vy0 = 0;
        this.vx0 = 0;
    }


//    public boolean doesStartMoving(double fX, double fY, double N) {
//        return N * Board.STATIC_FRICTION < Math.sqrt(Math.pow(fX, 2) + Math.pow(fY, 2));
//    }

//    public void calcAcc(double angleX, double angleY, double angleZ) {
//        double fX = this.mass * Board.G * Math.sin(angleX);
//        double fY = this.mass * Board.G * Math.sin(angleY);
//        double N = this.mass * Board.G * Math.cos(Math.atan(euclideanNorm(Math.sin(angleX), Math.sin(angleY)) / (Math.cos(angleX) + Math.cos(angleY))));
//    }
}