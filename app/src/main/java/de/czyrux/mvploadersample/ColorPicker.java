package de.czyrux.mvploadersample;

import android.support.annotation.ColorRes;

import java.util.Random;

public class ColorPicker {

    private static final Random RANDOM = new Random();
    private static int[] COLOR_ARRAY = new int[]{R.color.blue, R.color.green, R.color.yellow, R.color.brown, R.color.pink,
            R.color.blue_gray, R.color.deep_purple};

    @ColorRes
    public static int getRandomColor() {
        return COLOR_ARRAY[RANDOM.nextInt(COLOR_ARRAY.length)];
    }
}
