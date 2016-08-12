package com.seventeam.wubahuichang.Fragment;

import android.content.Intent;
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

import com.seventeam.wubahuichang.Activity.SearchActivity;
import com.seventeam.wubahuichang.Activity.StrategyDetailActivity;
import com.seventeam.wubahuichang.Adapter.LookplaceListAdapter;
import com.seventeam.wubahuichang.Adapter.LookplacePagerAdapter;
import com.seventeam.wubahuichang.Adapter.PlaceItemDecoration;
import com.seventeam.wubahuichang.Adapter.StrategyListAdapter;
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
public class StrategyFragment extends Fragment implements StrategyListAdapter.OnItemClickListener, View.OnClickListener {

    private EditText etLookPlaceSearch;
    private RecyclerView rvStrategyList;
    private StrategyListAdapter adapter;
    private SparseArray<String> array;
    private EditText etStrategySearch;

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
        etLookPlaceSearch = (EditText) view.findViewById(R.id.et_LookPlaceSearch);
        rvStrategyList = (RecyclerView) view.findViewById(R.id.rv_StrategyList);
        etStrategySearch = (EditText) view.findViewById(R.id.et_StrategySearch);
    }

    private void initData() {
        array = new SparseArray<>();
        for (int i = 0; i < 20; i++) {
            array.put(i, i+"");
        }
        adapter = new StrategyListAdapter(array);


        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvStrategyList.setLayoutManager(llm);
        rvStrategyList.setAdapter(adapter);

    }

    private void initListenter() {
        adapter.setOnItemClickListener(this);
        etStrategySearch.setOnClickListener(this);
    }

    @Override
    public void onItemClick(View view, int position) {
        startActivity(new Intent(getActivity(), StrategyDetailActivity.class));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.et_StrategySearch:
                startActivity(new Intent(getActivity(), SearchActivity.class));
                break;
        }
    }
}
