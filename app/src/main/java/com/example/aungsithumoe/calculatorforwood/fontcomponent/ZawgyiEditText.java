package com.example.aungsithumoe.calculatorforwood.fontcomponent;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;

public class ZawgyiEditText extends AppCompatEditText {
    public ZawgyiEditText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(),"fonts/zawgyi.ttf"));
    }
}
