package com.example.haodemo;

import android.app.Application;

import com.wander.baselibrary.BaseTool;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        BaseTool.init(this,BuildConfig.DEBUG);
        
    }
}
