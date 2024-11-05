package com.example.aungsithumoe.calculatorforwood.fontcomponent;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatButton;

/**
 * Created by aungsithumoe on 12/14/17.
 */

public class ZawgyiButton extends AppCompatButton {
    public ZawgyiButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(),"fonts/zawgyi.ttf"));
    }
}
