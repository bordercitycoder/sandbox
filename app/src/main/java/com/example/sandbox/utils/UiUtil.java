package com.example.sandbox.utils;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;

import com.example.sandbox.R;

public class UiUtil {

    public static void shakeEditText(EditText editText) {
        Animation shake = AnimationUtils.loadAnimation(editText.getContext(), R.anim.shake);
        editText.startAnimation(shake);
    }

}
