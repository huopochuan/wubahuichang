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
import android.widget.Toast;

import com.seventeam.wubahuichang.Adapter.LookplaceListAdapter;
import com.seventeam.wubahuichang.Adapter.LookplacePagerAdapter;
import com.seventeam.wubahuichang.Adapter.PlaceItemDecoration;
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
public class StrategyFragment extends Fragment {


    public StrategyFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_strategy, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
        initData();
        initListenter();
    }

    private void initView(View view) {
    }


    private void initData() {
    }

    private void initListenter() {
    }

}
