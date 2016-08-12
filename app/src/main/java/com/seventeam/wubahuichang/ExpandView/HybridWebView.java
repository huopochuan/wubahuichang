package com.seventeam.wubahuichang.ExpandView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.seventeam.wubahuichang.Activity.LinkActivity;
import com.seventeam.wubahuichang.Activity.SearchActivity;

/**
 * Created by 58 on 2016/8/11.
 */
public class HybridWebView extends WebView{

    private Handler handler=new Handler(Looper.getMainLooper());
    private Context mContext;
    public HybridWebView(Context context) {
        this(context,null);
    }

    public HybridWebView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public HybridWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext=context;
        init();
    }

    private void init(){
        WebSettings setting = this.getSettings();
        setting.setJavaScriptEnabled(true);//支持js
        this.addJavascriptInterface(new JsObject(this),"action");
    }
    public static class JsObject{
        HybridWebView mWebView;
        public JsObject(HybridWebView webView){
            mWebView=webView;
        }
        @JavascriptInterface
        public void jsCallMethod(final String page,final String title) {
//            Log.d("wyc",actionJson);
            mWebView.handler.post(new Runnable() {
                @Override
                public void run() {
                    Intent intent=new Intent(mWebView.getContext(),LinkActivity.class);
                    intent.putExtra("link",page);
                    intent.putExtra("title",title);
                    mWebView.getContext().startActivity(intent);
                }
            });

        }
    }
}
