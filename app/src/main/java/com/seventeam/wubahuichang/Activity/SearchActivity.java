package com.seventeam.wubahuichang.Activity;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.seventeam.wubahuichang.ExpandView.ExpandTabView;
import com.seventeam.wubahuichang.ExpandView.ViewLeft;
import com.seventeam.wubahuichang.ExpandView.ViewMiddle;
import com.seventeam.wubahuichang.ExpandView.ViewRight;
import com.seventeam.wubahuichang.R;

import java.util.ArrayList;

public class SearchActivity extends FragmentActivity {
    private ExpandTabView expandTabView;
    private ArrayList<View> mViewArray = new ArrayList<View>();
    private ViewLeft viewLeft;
    private ViewMiddle viewMiddle;
    private ViewRight viewRight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        initVaule();
        initListener();
    }

    private void initView() {

        expandTabView = (ExpandTabView) findViewById(R.id.expandtab_view);
        viewMiddle = new ViewMiddle(this);
        viewLeft = new ViewLeft(this);
        viewRight = new ViewRight(this);

    }

    private void initVaule() {

        mViewArray.add(new ViewMiddle(this));
        mViewArray.add( new ViewRight(this));
        mViewArray.add( new ViewRight(this));
        mViewArray.add( new ViewRight(this));
        ArrayList<String> mTextArray = new ArrayList<String>();
        mTextArray.add("选择区域");
        mTextArray.add("价格区间");
        mTextArray.add("场地用途");
        mTextArray.add("排序");
        expandTabView.setValue(mTextArray, mViewArray);//将三个下拉列表设置进去

    }

    private void initListener() {

        viewLeft.setOnSelectListener(new ViewLeft.OnSelectListener() {

            @Override
            public void getValue(String distance, String showText) {
                onRefresh(viewLeft, showText);
            }
        });

        viewMiddle.setOnSelectListener(new ViewMiddle.OnSelectListener() {

            @Override
            public void getValue(String showText) {

                onRefresh(viewMiddle,showText);

            }
        });

        viewRight.setOnSelectListener(new ViewRight.OnSelectListener() {

            @Override
            public void getValue(String distance, String showText) {
                onRefresh(viewRight, showText);
            }
        });

    }

    private void onRefresh(View view, String showText) {

        expandTabView.onPressBack();
        int position = getPositon(view);
        if (position >= 0 && !expandTabView.getTitle(position).equals(showText)) {
            expandTabView.setTitle(showText, position);
        }
        Toast.makeText(SearchActivity.this, showText, Toast.LENGTH_SHORT).show();

    }

    private int getPositon(View tView) {
        for (int i = 0; i < mViewArray.size(); i++) {
            if (mViewArray.get(i) == tView) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void onBackPressed() {

        if (!expandTabView.onPressBack()) {
            finish();
        }

    }
}
