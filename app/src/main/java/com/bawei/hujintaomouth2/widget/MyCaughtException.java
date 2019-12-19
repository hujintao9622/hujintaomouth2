package com.bawei.hujintaomouth2.widget;

import android.util.Log;

import androidx.annotation.NonNull;

/**
 * 功能:  捕获异常
 * 作者:  胡锦涛
 * 时间:  2019/12/19 0019 下午 3:58
 */
public class MyCaughtException implements Thread.UncaughtExceptionHandler{
    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
        Log.e("tag",e.getMessage());
    }
}
