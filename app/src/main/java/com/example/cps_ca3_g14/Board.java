package com.example.cps_ca3_g14;

import android.content.res.Resources;
import android.util.Pair;

import java.util.Random;

public class Board {
    private int width;
    private int height;

    public Board() {
        this.width = Resources.getSystem().getDisplayMetrics().widthPixels;
        this.height = Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Pair<Integer, Integer> getRandomPoint() {
        return new Pair<>(randInt(width, 0), randInt(height, 0));
    }

    public int randInt(int max, int min) {
        return new Random().nextInt(max - min + 1) + min;
    }
}