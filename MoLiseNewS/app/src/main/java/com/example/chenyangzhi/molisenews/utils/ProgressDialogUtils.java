package com.example.chenyangzhi.molisenews.utils;

import android.app.Activity;

import com.example.chenyangzhi.molisenews.view.CommonProgressDialog;

/**
 * Created by chenyangzhi on 2016/9/5.
 */
public class ProgressDialogUtils {
    private static Activity activity;
    private static CommonProgressDialog dialog;
    private static ProgressDialogUtils progressDialogUtils;
    private ProgressDialogUtils(){}
    public static ProgressDialogUtils getIntence(){
        if(progressDialogUtils==null){
            progressDialogUtils=new ProgressDialogUtils();
        }
        return progressDialogUtils;
    }
//    显示方法
    public  void showProgressDialog(Activity activity, String msg){
        this.activity=activity;
        if (dialog== null) {
            dialog=new CommonProgressDialog(activity);
        }
        if(msg==null){
            msg="正在加载";
        }
        dialog.setMessage(msg);
        if(!activity.isFinishing()&&!dialog.isShowing()){
            dialog.show();
        }
    }
    // 关闭方法
    public  void closeProgressDialog(){
        if(!activity.isFinishing()&&dialog.isShowing()){
            dialog.dismiss();
        }
    }
}
