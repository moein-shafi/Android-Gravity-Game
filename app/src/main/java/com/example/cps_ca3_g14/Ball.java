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
        this.updateImageLocation();
    }

    public void gravityUpdate(float xGravity, float yGravity, float zGravity, float deltaT)
    {
        boolean canMoveX = true;
        boolean canMoveY = true;

        float sFriction = 0;
        float kFriction = 0;
        float xFriction = 0;
        float yFriction = 0;

        this.checkCollision();

        if (x0 <= MainActivity.WALL_MINIMUM_THRESHOLD && Math.abs(vx0) < MainActivity.V_MINIMUM_THRESHOLD && xGravity <= 0) {
            // 3
            canMoveX = false;

            sFriction = Math.abs(xGravity * this.mass * Board.STATIC_FRICTION);
            kFriction = Math.abs(xGravity * this.mass * Board.KINETIC_FRICTION);

            if (Math.abs(vy0) < MainActivity.V_MINIMUM_THRESHOLD && Math.abs(yGravity * this.mass) <= Math.abs(sFriction)) {
                canMoveY = false;
            } else if (vy0 < 0) {
                yFriction = kFriction;
            } else if (vy0 > 0) {
                yFriction = -kFriction;
            }
        }

        if (x0 + 2 * this.radius >= Board.width && Math.abs(vx0) - MainActivity.WALL_MINIMUM_THRESHOLD < MainActivity.V_MINIMUM_THRESHOLD && xGravity >= 0) {
            // 1
            canMoveX = false;

            sFriction = Math.abs(xGravity * this.mass * Board.STATIC_FRICTION);
            kFriction = Math.abs(xGravity * this.mass * Board.KINETIC_FRICTION);

            if (Math.abs(vy0) < MainActivity.V_MINIMUM_THRESHOLD && Math.abs(yGravity * this.mass) <= Math.abs(sFriction)) {
                canMoveY = false;
            } else if (vy0 < 0) {
                yFriction = kFriction;
            } else if (vy0 > 0) {
                yFriction = -kFriction;
            }
        }

        if (y0 + 2 * this.radius >= Board.height - MainActivity.WALL_MINIMUM_THRESHOLD && Math.abs(vy0) < MainActivity.V_MINIMUM_THRESHOLD && yGravity >= 0) {
            // 2
            canMoveY = false;

            sFriction = Math.abs(yGravity * this.mass * Board.STATIC_FRICTION);
            kFriction = Math.abs(yGravity * this.mass * Board.KINETIC_FRICTION);

            if (Math.abs(vx0) < MainActivity.V_MINIMUM_THRESHOLD && Math.abs(xGravity * this.mass) <= Math.abs(sFriction)) {
                canMoveX = false;
            } else if (vx0 < 0) {
                xFriction = kFriction;
            } else if (vx0 > 0) {
                xFriction = -kFriction;
            }
        }

        if (y0 <= MainActivity.WALL_MINIMUM_THRESHOLD && Math.abs(vy0) < MainActivity.V_MINIMUM_THRESHOLD && yGravity <= 0) {
            // 0
            canMoveY = false;

            sFriction = Math.abs(yGravity * this.mass * Board.STATIC_FRICTION);
            kFriction = Math.abs(yGravity * this.mass * Board.KINETIC_FRICTION);

            if (Math.abs(vx0) < MainActivity.V_MINIMUM_THRESHOLD && Math.abs(xGravity * this.mass) <= Math.abs(sFriction)) {
                canMoveX = false;
            } else if (vx0 < 0) {
                xFriction = kFriction;
            } else if (vx0 > 0) {
                xFriction = -kFriction;
            }
        }

        if (canMoveX) {
            float ax = (xGravity + (xFriction / this.mass)) * MainActivity.ACCELERATION_SCALE;
            this.x0 += ax * Math.pow(deltaT, 2) / 2 + this.vx0 * deltaT;
            this.vx0 = ax * deltaT + vx0;
        } else {
            this.vx0 = 0;
        }

        if (canMoveY) {
            float ay = (yGravity + (yFriction / this.mass)) * MainActivity.ACCELERATION_SCALE;
            this.y0 += ay * Math.pow(deltaT, 2) / 2 + this.vy0 * deltaT;
            this.vy0 = ay * deltaT + vy0;
        } else {
            this.vy0 = 0;
        }
    }

    private void checkCollision() {
        if (this.y0 <= 0) {
            this.vy0 = (float) (-this.vy0 * Math.sqrt(0.9));
            y0 = 0;
        }
        if (this.y0 + 2 * this.radius >= Board.height) {
            this.vy0 = (float) (-this.vy0 * Math.sqrt(0.9));
            y0 = Board.height - 2 * this.radius;
        }
        if (this.x0 <= 0) {
            this.vx0 = (float) (-this.vx0 * Math.sqrt(0.9));
            x0 = 0;
        }
        if (this.x0 + 2 * this.radius >= Board.width) {
            this.vx0 = (float) (-this.vx0 * Math.sqrt(0.9));
            x0 = Board.width - 2 * this.radius;
        }
    }

    public void updateImageLocation() {
        imageView.setX(this.x0);
        imageView.setY(this.y0);
    }

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
}
