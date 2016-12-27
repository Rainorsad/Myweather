package com.example.a123.myweather.application;

import android.app.Application;
import android.content.Context;

/**
 * Created by 123 on 2016/12/6.
 */

public class AppLica extends Application{
    private Context context;
    public String  keyCode = "197e8fe14585b";

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
