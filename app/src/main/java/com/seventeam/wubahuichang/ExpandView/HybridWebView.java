package com.seventeam.wubahuichang.ExpandView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by 58 on 2016/8/11.
 */
public class HybridWebView extends WebView{

    public HybridWebView(Context context) {
        this(context,null);
    }

    public HybridWebView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public HybridWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        WebSettings setting = this.getSettings();
        setting.setJavaScriptEnabled(true);//支持js
        this.addJavascriptInterface(new JsObject(),"action");
    }
    public static class JsObject{
        @JavascriptInterface
        public void jsCallMethod(String actionJson) {
        }
    }
}
