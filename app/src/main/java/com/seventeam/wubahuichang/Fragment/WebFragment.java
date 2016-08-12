package com.seventeam.wubahuichang.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.seventeam.wubahuichang.ExpandView.HybridWebView;
import com.seventeam.wubahuichang.R;


public class WebFragment extends Fragment {

    private HybridWebView  mWebView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_web, container, false);
        mWebView=(HybridWebView)v.findViewById(R.id.webview);
        mWebView.loadUrl("file:///android_asset/list.html");
        return v;
    }
}
