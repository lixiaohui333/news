package com.lxhmmc.news.android.application;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.facebook.stetho.Stetho;

/**
 * Created by lxh on 2018/3/29.
 */

public class MyApplication extends Application {


    private static MyApplication instance;
    //by lxh
    private static int mainTid;
    private static Handler handler;



    public static MyApplication getInstance() {
        return instance;
    }


    public Context getContext() {
        return getInstance();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initializeWithDefaults(this);

        instance = this;

        mainTid = android.os.Process.myTid();
        handler = new Handler();

    }
}
