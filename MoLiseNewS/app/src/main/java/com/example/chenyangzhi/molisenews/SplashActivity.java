package com.example.chenyangzhi.molisenews;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.example.chenyangzhi.molisenews.base.BaseActivity;

/**
 * Created by chenyangzhi on 2016/9/5.
 */
public class SplashActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                jumpTo(MainActivity.class);
            }
        },2000);
    }
}
