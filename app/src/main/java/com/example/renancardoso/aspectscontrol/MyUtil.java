package com.example.renancardoso.aspectscontrol;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by RenanCardoso on 13/09/2017.
 */

public class MyUtil {
    public static void toast(Context c, CharSequence t) {
        Toast.makeText(c, t, Toast.LENGTH_SHORT).show();
    }
}
