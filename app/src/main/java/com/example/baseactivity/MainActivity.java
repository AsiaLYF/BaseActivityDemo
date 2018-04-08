package com.example.baseactivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button btn_next;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        setTitleText("主页");
        setBack().setOnClickListener(this);

    }

    @Override
    protected void initData() {
        btn_next = findViewById(R.id.btn_next);
        btn_next.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_next:
                Intent intent = new Intent(this,SecondActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_back:
                finish();
                break;
        }

    }
}
