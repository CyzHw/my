package com.example.chenyangzhi.molisenews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MyDetailActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private WebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_detail);
        mWebView=(WebView) findViewById(R.id.webView);
        WebSettings setting=mWebView.getSettings();
        setting.setUseWideViewPort(true);
        String url=getIntent().getStringExtra("url");
        int position=getIntent().getIntExtra("number",0);

        if(position!=7) {
            String newUrl = "http://i" + getType(url) + ".ifeng.com/" + getNumber(url) + "/news.shtml";
            mWebView.loadUrl(newUrl);
        }
        else {
            mWebView.loadUrl(url);
        }
        mToolbar= (Toolbar) findViewById(R.id.toolBar);
        getSupportActionBar().hide();
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private String getNumber(String url){
        int startIndex=url.lastIndexOf("/");
        int endIndex=url.lastIndexOf("_");
        return url.substring(startIndex,endIndex);
    }
    private String getType(String url){
        int endIndexof=url.indexOf(".");
        return url.substring(7,endIndexof);
    }
}
