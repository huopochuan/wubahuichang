package com.seventeam.wubahuichang.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.seventeam.wubahuichang.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etUserName;
    private EditText etPassword;
    private Button btLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        initData();
        initListener();
    }

    private void initView() {
        etUserName = (EditText) findViewById(R.id.et_UserName);
        etPassword = (EditText) findViewById(R.id.et_Password);
        btLogin = (Button) findViewById(R.id.bt_Login);
    }

    private void initListener() {
        btLogin.setOnClickListener(this);
    }

    private void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_Login:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
        }
    }
}
