package com.example.chenyangzhi.molisenews.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;

import com.example.chenyangzhi.molisenews.R;

/**
 * Created by chenyangzhi on 2016/9/5.
 */
public class CommonProgressDialog extends Dialog {

//加载弹窗样式
    public CommonProgressDialog(Context context) {
        super(context,R.style.commonProgressDialog);
//        显示在屏幕中央
        setContentView(R.layout.aty_commonprogress_dialog);
        getWindow().getAttributes().gravity= Gravity.CENTER;
    }
    public  void setMessage(String message){
        TextView tv=(TextView) this.findViewById(R.id.tvMessage);
        tv.setText(message);
    }
}
