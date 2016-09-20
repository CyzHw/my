package com.example.chenyangzhi.molisenews.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenyangzhi on 2016/9/2.
 */
public class BaseActivity extends Activity{
    List<Activity> list=new ArrayList<>();
    protected void jumpTo(Class<?> targetClass){
        Intent intent=new Intent(this,targetClass);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list.add(this);
    }
    protected void finishAll(){
        for(Activity a:list){
            a.finish();
        }
    }
    protected void showToast(String text){
        ToastUtil.showToast(this,text);
    }
}
