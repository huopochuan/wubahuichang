package com.seventeam.wubahuichang.Activity;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.seventeam.wubahuichang.Adapter.ListAdapter;
import com.seventeam.wubahuichang.Bean.ListBean;
import com.seventeam.wubahuichang.ExpandView.ExpandTabView;
import com.seventeam.wubahuichang.ExpandView.ViewMiddle;
import com.seventeam.wubahuichang.ExpandView.ViewRight;
import com.seventeam.wubahuichang.R;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends FragmentActivity {
    private ExpandTabView expandTabView;
    private ArrayList<View> mViewArray = new ArrayList<View>();
    private ViewMiddle mSelect1;
    private ViewRight mSelect2;
    private ViewRight mSelect3;
    private ViewRight mSelect4;
    private ListView mListView;
    private ListAdapter adapter;
    private ArrayList<ListBean> mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        initVaule();
        initListener();

    }

    private void initView() {
        mListView=(ListView)findViewById(R.id.listView);
        expandTabView = (ExpandTabView) findViewById(R.id.expandtab_view);
        mSelect1 = new ViewMiddle(this);
        mSelect2 = new ViewRight(this);
        mSelect3=new ViewRight(this);
        mSelect4=new ViewRight(this);
    }

    private void initVaule() {

        mViewArray.add(mSelect1);
        mViewArray.add(mSelect2);
        mViewArray.add(mSelect3);
        mViewArray.add(mSelect4);
        mSelect2.setData(new String[]{"0~100","100~200","300~400"});
        mSelect3.setData(new String[]{"会议","party","生日宴会"});
        mSelect4.setData(new String[]{"价格"});
        ArrayList<String> mTextArray = new ArrayList<String>();
        mTextArray.add("选择区域");
        mTextArray.add("价格区间");
        mTextArray.add("场地用途");
        mTextArray.add("排序");
        expandTabView.setValue(mTextArray, mViewArray);//将三个下拉列表设置进去

    }

    private void initListener() {

        mSelect1.setOnSelectListener(new ViewMiddle.OnSelectListener() {

            @Override
            public void getValue(String showText) {
                onRefresh(mSelect1,showText);
            }

        });

        mSelect2.setOnSelectListener(new ViewRight.OnSelectListener() {

            @Override
            public void getValue(String distance, String showText) {
                onRefresh(mSelect2,showText);
            }
        });

        mSelect3.setOnSelectListener(new ViewRight.OnSelectListener() {

            @Override
            public void getValue(String distance, String showText) {
                onRefresh(mSelect3, showText);
            }
        });

        mSelect4.setOnSelectListener(new ViewRight.OnSelectListener() {
            @Override
            public void getValue(String distance, String showText) {
                onRefresh(mSelect4,showText);
            }
        });

    }
    private void getData(){

         mData=new ArrayList<ListBean>();
        ListBean bean=new ListBean();
        bean.setResid(R.drawable.cd);
        bean.setTitle("周末聚会来这呀");
        bean.setContent("有各种各样的聚会活动呀");
        mData.add(bean);
    }

    private void onRefresh(View view, String showText) {

        expandTabView.onPressBack();
        int position = getPositon(view);
        if (position >= 0 && !expandTabView.getTitle(position).equals(showText)) {
            expandTabView.setTitle(showText, position);
        }
        getData();
        adapter=new ListAdapter(this,mData);
        if(mListView!=null)
        mListView.setAdapter(adapter);
        //数据加载


//        Toast.makeText(SearchActivity.this, showText, Toast.LENGTH_SHORT).show();

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
