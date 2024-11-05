package com.example.aungsithumoe.calculatorforwood.fontcomponent;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * Created by aungsithumoe on 12/13/17.
 */

public class ZawgyiTextView extends AppCompatTextView {
    public ZawgyiTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(),"fonts/zawgyi.ttf"));
    }

}
