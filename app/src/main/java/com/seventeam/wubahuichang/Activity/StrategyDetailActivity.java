package com.seventeam.wubahuichang.Activity;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.seventeam.wubahuichang.R;

public class StrategyDetailActivity extends FragmentActivity implements View.OnClickListener {

    private TextView tv_Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strategy_detail);

        initView();
        initListener();
    }

    private void initView() {
        tv_Back = (TextView) findViewById(R.id.tv_Back);
    }

    private void initListener() {
        tv_Back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_Back:
                finish();
                break;
        }
    }
}
