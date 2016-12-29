package com.example.a123.myweather.application;

import android.content.Context;

import com.tencent.tinker.loader.app.TinkerApplication;

/**
 * Created by 123 on 2016/12/6.
 */

public class AppLica extends TinkerApplication{
    private Context context;

    protected AppLica(int tinkerFlags, String delegateClassName, String loaderClassName, boolean tinkerLoadVerifyFlag) {
        super(tinkerFlags, delegateClassName, loaderClassName, tinkerLoadVerifyFlag);
    }

    protected AppLica(int tinkerFlags) {
        super(tinkerFlags);
    }

    protected AppLica(int tinkerFlags, String delegateClassName) {
        super(tinkerFlags, delegateClassName);
    }

}
