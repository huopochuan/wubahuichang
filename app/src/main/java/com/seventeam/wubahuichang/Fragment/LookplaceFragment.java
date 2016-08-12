package com.seventeam.wubahuichang.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.seventeam.wubahuichang.Activity.SearchActivity;
import com.seventeam.wubahuichang.Adapter.LookplaceListAdapter;
import com.seventeam.wubahuichang.Adapter.LookplacePagerAdapter;
import com.seventeam.wubahuichang.Bean.LookplaceItemBean;
import com.seventeam.wubahuichang.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * create gengjiarong
 */
public class LookplaceFragment extends Fragment implements ViewPager.OnPageChangeListener, LookplaceListAdapter.OnItemClickListener, View.OnClickListener {

    public static final int UPDATE_PAGE = 1;

    private EditText etLookPlaceSearch;
    public ViewPager vpLookPlace;
    //    private RecyclerView rvLookPlaceList;
    private View vDot1;
    private View vDot2;
    private View vDot3;

    private LinearLayout ll_tuanjian;
    private LinearLayout ll_juhui;
    private LinearLayout ll_huiyi;
    private LinearLayout ll_shengri;

    private ImageView iv_bottom1;
    private ImageView iv_bottom2;
    private ImageView iv_bottom3;

    private List<ImageView> viewArray;
    private List<LookplaceItemBean> imageArray;
    private LookplacePagerAdapter pagerAdapter;
    private LookplaceListAdapter listAdapter;
    private Timer timer;
    private int autoCurrIndex = 0;
    private int[] imgs;

    static final class MessageHandler extends Handler {

        public WeakReference<Fragment> fragment;
        public ViewPager viewPager;

        public MessageHandler(WeakReference<Fragment> weakReference, ViewPager vp) {
            viewPager = vp;
            fragment = weakReference;
        }

        @Override
        public void handleMessage(Message msg) {
            Fragment fm = fragment.get();
            if (fm != null) {
                switch (msg.what) {
                    case UPDATE_PAGE:
                        viewPager.setCurrentItem(msg.arg1);
                        break;
                }
            }
        }
    }

    private MessageHandler handler;

    public LookplaceFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lookplace, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
        initData();
        initListenter();
    }

    private void initView(View view) {
        etLookPlaceSearch = (EditText) view.findViewById(R.id.et_LookPlaceSearch);
        vpLookPlace = (ViewPager) view.findViewById(R.id.vp_LookPlace);
//        rvLookPlaceList = (RecyclerView) view.findViewById(R.id.rv_LookPlaceList);
        vDot1 = view.findViewById(R.id.v_Dot1);
        vDot2 = view.findViewById(R.id.v_Dot2);
        vDot3 = view.findViewById(R.id.v_Dot3);

        ll_tuanjian = (LinearLayout) view.findViewById(R.id.ll_tuanjian);
        ll_juhui = (LinearLayout) view.findViewById(R.id.ll_juhui);
        ll_huiyi = (LinearLayout) view.findViewById(R.id.ll_huiyi);
        ll_shengri = (LinearLayout) view.findViewById(R.id.ll_shengri);

        iv_bottom1 = (ImageView) view.findViewById(R.id.iv_bottom1);
        iv_bottom2 = (ImageView) view.findViewById(R.id.iv_bottom2);
        iv_bottom3 = (ImageView) view.findViewById(R.id.iv_bottom3);
    }

    private void initData() {
        handler = new MessageHandler(new WeakReference<Fragment>(this), vpLookPlace);
        viewArray = new ArrayList<>();
        imageArray = new ArrayList<>();

        imgs = new int[]{
                R.mipmap.shouyedingtu,
                R.mipmap.shouyetu1,
                R.mipmap.shouyetu2
        };
        // viewpager 图片初始化
        ImageView imageView;
        for (int i = 0; i < 3; i++) {
            imageView = new ImageView(getActivity());
            imageView.setImageResource(imgs[i]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewArray.add(imageView);
        }

        // 底部recyclerview
        imageArray.add(new LookplaceItemBean("中小型公司", R.mipmap.zhongxiaoxinggongsi));
        imageArray.add(new LookplaceItemBean("小型公司", R.mipmap.xiaoxingtuanti));
        imageArray.add(new LookplaceItemBean("家庭个人", R.mipmap.jiatinggeren));

        pagerAdapter = new LookplacePagerAdapter(viewArray);
//        listAdapter = new LookplaceListAdapter(imageArray);
        vpLookPlace.setAdapter(pagerAdapter);

//        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
//        llm.setOrientation(LinearLayoutManager.VERTICAL);
//        rvLookPlaceList.setLayoutManager(llm);
//        rvLookPlaceList.setAdapter(listAdapter);
//        // 设置分割线
//        rvLookPlaceList.addItemDecoration(new PlaceItemDecoration(getActivity()));

        // 实现轮播
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message msg = Message.obtain();
                msg.what = UPDATE_PAGE;

                msg.arg1 = (++autoCurrIndex) % 3;
                handler.sendMessage(msg);

            }
        }, 5000, 5000);
    }

    private void initListenter() {
        vpLookPlace.addOnPageChangeListener(this);
//        listAdapter.setOnItemClickListener(this);
        etLookPlaceSearch.setOnClickListener(this);
        ll_tuanjian.setOnClickListener(this);
        ll_juhui.setOnClickListener(this);
        ll_huiyi.setOnClickListener(this);
        ll_shengri.setOnClickListener(this);

        iv_bottom1.setOnClickListener(this);
        iv_bottom2.setOnClickListener(this);
        iv_bottom3.setOnClickListener(this);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (hidden) {
            timer.cancel();
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                vDot1.setBackgroundResource(R.mipmap.green_dot);
                vDot2.setBackgroundResource(R.mipmap.white_dot);
                vDot3.setBackgroundResource(R.mipmap.white_dot);
                break;
            case 1:
                vDot1.setBackgroundResource(R.mipmap.white_dot);
                vDot2.setBackgroundResource(R.mipmap.green_dot);
                vDot3.setBackgroundResource(R.mipmap.white_dot);
                break;
            case 2:
                vDot1.setBackgroundResource(R.mipmap.white_dot);
                vDot2.setBackgroundResource(R.mipmap.white_dot);
                vDot3.setBackgroundResource(R.mipmap.green_dot);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public void onItemClick(View view, int position) {
        startActivity(new Intent(getActivity(), SearchActivity.class));
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(getActivity(), SearchActivity.class));
    }
}
