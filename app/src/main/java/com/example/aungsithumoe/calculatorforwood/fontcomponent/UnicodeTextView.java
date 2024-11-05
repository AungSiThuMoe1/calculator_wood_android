package com.example.aungsithumoe.calculatorforwood.fontcomponent;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class UnicodeTextView extends AppCompatTextView {
    public UnicodeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(),"fonts/mmrtext.ttf"));
    }
}
