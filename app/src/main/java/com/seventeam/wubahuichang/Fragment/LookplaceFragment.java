package com.seventeam.wubahuichang.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

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
public class LookplaceFragment extends Fragment implements ViewPager.OnPageChangeListener {

    public static final int UPDATE_PAGE = 1;

    private EditText etLookPlaceSearch;
    public ViewPager vpLookPlace;
    private RecyclerView rvLookPlaceList;
    private View vDot1;
    private View vDot2;
    private View vDot3;

    private SparseArray<View> viewArray;
    private List<LookplaceItemBean> imageArray;
    private LookplacePagerAdapter pagerAdapter;
    private LookplaceListAdapter listAdapter;
    private Timer timer;
    private int autoCurrIndex = 0;

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
        rvLookPlaceList = (RecyclerView) view.findViewById(R.id.rv_LookPlaceList);
        vDot1 = view.findViewById(R.id.v_Dot1);
        vDot2 = view.findViewById(R.id.v_Dot2);
        vDot3 = view.findViewById(R.id.v_Dot3);
    }


    private void initData() {
        handler = new MessageHandler(new WeakReference<Fragment>(this), vpLookPlace);
        viewArray = new SparseArray<>();
        imageArray = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            viewArray.put(i, LayoutInflater.from(getActivity()).inflate(R.layout.pager_layout, vpLookPlace, false));
            imageArray.add(new LookplaceItemBean("aaa", R.mipmap.ic_launcher));
        }

        pagerAdapter = new LookplacePagerAdapter(viewArray);
        listAdapter = new LookplaceListAdapter(imageArray);
        vpLookPlace.setAdapter(pagerAdapter);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvLookPlaceList.setLayoutManager(llm);
        rvLookPlaceList.setAdapter(listAdapter);

        // 实现轮播
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message msg = Message.obtain();
                msg.what = UPDATE_PAGE;

                msg.arg1 = (++autoCurrIndex) % 3;
                msg.arg1 = 1;
                handler.sendMessage(msg);

            }
        }, 2000, 2000);
    }

    private void initListenter() {
        vpLookPlace.addOnPageChangeListener(this);
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
                vDot1.setBackgroundColor(Color.parseColor("#000000"));
                vDot2.setBackgroundColor(Color.parseColor("#22000000"));
                vDot3.setBackgroundColor(Color.parseColor("#22000000"));
                break;
            case 1:
                vDot1.setBackgroundColor(Color.parseColor("#22000000"));
                vDot2.setBackgroundColor(Color.parseColor("#000000"));
                vDot3.setBackgroundColor(Color.parseColor("#22000000"));
                break;
            case 2:
                vDot1.setBackgroundColor(Color.parseColor("#22000000"));
                vDot2.setBackgroundColor(Color.parseColor("#22000000"));
                vDot3.setBackgroundColor(Color.parseColor("#000000"));
                break;
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

}
