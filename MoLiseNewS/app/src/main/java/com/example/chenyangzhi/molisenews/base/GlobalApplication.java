package com.example.chenyangzhi.molisenews.base;

import android.app.Application;

/**
 * Created by chenyangzhi on 2016/9/2.
 */
public class GlobalApplication extends Application {
    private  static  GlobalApplication app;
    @Override
    public void onCreate() {
        super.onCreate();
        app=this;
    }
    public static GlobalApplication getContext(){
        return app;
    }
}
