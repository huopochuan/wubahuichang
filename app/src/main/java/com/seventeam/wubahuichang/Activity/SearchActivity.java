package com.seventeam.wubahuichang.Activity;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
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

    private ImageView img_back;
    private TextView tv_title;
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
        img_back=(ImageView)this.findViewById(R.id.img_back);
        tv_title=(TextView)this.findViewById(R.id.title);
        tv_title.setText("发现场地");
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchActivity.this.finish();
            }
        });


        mListView.setOnItemClickListener(new OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> var1, View var2, int var3, long var4){
                Intent intent=new Intent(SearchActivity.this,LinkActivity.class);
                intent.putExtra("link","detail.html");
                SearchActivity.this.startActivity(intent);
            }
        });
    }

    private void initVaule() {

        mViewArray.add(mSelect1);
        mViewArray.add(mSelect2);
        mViewArray.add(mSelect3);
        mViewArray.add(mSelect4);
        mSelect2.setData(new String[]{"1~200","200~500","500~1000","1000~5000","5000元以上"});
        mSelect3.setData(new String[]{"聚会paty","展会展览","公司团建","创意空间"});
        mSelect4.setData(new String[]{"距离","评价","价格","类型"});
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
        bean.setResid(R.drawable.cd1);
        bean.setTitle("北京私享文化中心");
        bean.setContent("北京海淀区学院路");
        bean.setType("经济型");
        bean.setDistance("<800");

        ListBean bean1=new ListBean();
        bean1.setResid(R.drawable.cd2);
        bean1.setTitle("北京晚风亭");
        bean1.setContent("北京通州区九棵树");
        bean1.setType("四合院");
        bean1.setDistance("1.5km");

        ListBean bean2=new ListBean();
        bean2.setResid(R.drawable.cd3);
        bean2.setTitle("北京游溪商务空间");
        bean2.setContent("北京鼓楼大街");
        bean2.setType("LOFT");
        bean2.setDistance("3.5km");

        ListBean bean3=new ListBean();
        bean3.setResid(R.drawable.cd4);
        bean3.setTitle("北京竹亭宴会厅");
        bean3.setContent("北京朝阳区望京西街");
        bean3.setType("经济型");
        bean3.setDistance("4km");

        ListBean bean4=new ListBean();
        bean4.setResid(R.drawable.cd5);
        bean4.setTitle("北京星空轰趴馆");
        bean4.setContent("五棵松地铁站旁");
        bean4.setType("日租型");
        bean4.setDistance("4km");

        mData.add(bean);
        mData.add(bean1);
        mData.add(bean2);
        mData.add(bean3);
        mData.add(bean4);
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
