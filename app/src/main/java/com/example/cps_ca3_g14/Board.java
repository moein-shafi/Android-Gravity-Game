package com.example.cps_ca3_g14;

import android.content.res.Resources;
import android.util.Pair;

import java.util.Random;

public class Board {
    public static final float KINETIC_FRICTION = 0.07f;
    public static final float STATIC_FRICTION = 0.15f;
    public static final int G = 10;

    public static int width;
    public static int height;

    public Board() {
        width = Resources.getSystem().getDisplayMetrics().widthPixels;
        height = Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public Pair<Integer, Integer> getRandomPoint() {
        return new Pair<>(randInt(width, 0), randInt(height, 0));
    }

    public int randInt(int max, int min) {
        return new Random().nextInt(max - min + 1) + min;
    }
}
