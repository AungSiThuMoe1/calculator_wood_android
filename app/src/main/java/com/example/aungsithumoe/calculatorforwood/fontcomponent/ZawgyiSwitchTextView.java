package com.example.aungsithumoe.calculatorforwood.fontcomponent;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.SwitchCompat;

public class ZawgyiSwitchTextView extends SwitchCompat {
    public ZawgyiSwitchTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(),"fonts/zawgyi.ttf"));
    }
}
