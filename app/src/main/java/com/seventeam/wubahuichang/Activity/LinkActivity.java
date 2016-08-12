package com.seventeam.wubahuichang.Activity;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.seventeam.wubahuichang.ExpandView.HybridWebView;
import com.seventeam.wubahuichang.R;

import org.w3c.dom.Text;

public class LinkActivity extends FragmentActivity {

    HybridWebView mWebView;
    private ImageView img_back;
    private TextView tv_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link);
        String page=getIntent().getStringExtra("link");
        String title=getIntent().getStringExtra("title");
        mWebView =(HybridWebView) findViewById(R.id.webview);
        mWebView.loadUrl("file:///android_asset/"+page);
        img_back=(ImageView)this.findViewById(R.id.img_back);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinkActivity.this.finish();
            }
        });
        tv_title=(TextView)this.findViewById(R.id.title);
        if(!TextUtils.isEmpty(title)){
            tv_title.setText(title);
        }
    }
}
