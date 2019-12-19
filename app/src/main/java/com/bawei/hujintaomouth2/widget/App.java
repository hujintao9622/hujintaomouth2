package com.bawei.hujintaomouth2.widget;

import android.app.Application;

/**
 * 功能:  全局
 * 作者:  胡锦涛
 * 时间:  2019/12/19 0019 下午 3:57
 */
public class App extends Application {
    public static App app;

    @Override
    public void onCreate() {
        super.onCreate();
        app=this;
        //全局捕获异常
        Thread.setDefaultUncaughtExceptionHandler(new MyCaughtException());
    }
}
