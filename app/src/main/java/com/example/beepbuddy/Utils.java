package com.example.beepbuddy;

import android.text.TextUtils;
import android.util.Patterns;

/**
 * BeepBuddy created by caitlinrush
 * student ID : 991534296
 * on 2019-11-24
 */
public class Utils {
    public static boolean isValidEmail(String target){
        return (!TextUtils.isEmpty(target) &&
                Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
