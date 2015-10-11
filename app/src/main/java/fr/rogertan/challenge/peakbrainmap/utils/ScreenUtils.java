package fr.rogertan.challenge.peakbrainmap.utils;

import android.content.Context;

/**
 * Created by rogertan on 09/10/15.
 */
public class ScreenUtils {
    public static int dpToPx(Context context, float dp) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dp * scale + 0.5f);
    }
}
