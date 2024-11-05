package com.example.aungsithumoe.calculatorforwood;

import android.app.Application;

import me.myatminsoe.mdetect.MDetect;

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MDetect.INSTANCE.init(this);
    }
}
