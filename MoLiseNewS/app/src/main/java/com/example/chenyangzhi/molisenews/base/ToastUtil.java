package com.example.chenyangzhi.molisenews.base;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by chenyangzhi on 2016/9/2.
 */
public class ToastUtil {
        static Toast toast=null;
    public static void showToast(Context context,String text){
        if(toast==null)
        toast=Toast.makeText(context,text,Toast.LENGTH_SHORT);
        else{
            toast.setText(text);
        }
        toast.show();
    }
}
