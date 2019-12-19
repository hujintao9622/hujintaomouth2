package com.bawei.hujintaomouth2.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.bawei.hujintaomouth2.R;
import com.bawei.hujintaomouth2.base.BaseActivity;

public class SecondActivity extends BaseActivity{

    private WebView web;
    private Button bt;

    @Override
    protected void initData() {
        web.loadUrl("file:///android_asset/Baibai.html");
    }

    @Override
    protected void initView() {
        web = findViewById(R.id.web);
        web.getSettings().setJavaScriptEnabled(true);
        web.setWebViewClient(new WebViewClient());
        web.setWebChromeClient(new WebChromeClient());
        web.addJavascriptInterface(new JsToAndroid(),"android");
        bt = findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                web.loadUrl("javascript:jsFunction1()");
            }
        });
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_second;
    }
    public class JsToAndroid{
        @JavascriptInterface
        public void getAndroidData(){
            Toast.makeText(SecondActivity.this, "这是无参方法", Toast.LENGTH_SHORT).show();
            Log.e("tag","这是一个无参方法");
        }
        @JavascriptInterface
        public void getAndroidXxxData(String data){
            Toast.makeText(SecondActivity.this, "这是有参方法"+data, Toast.LENGTH_SHORT).show();
            Log.e("tag","这是一个无参方法"+data);
        }
    }
}
