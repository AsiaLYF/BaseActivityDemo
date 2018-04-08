package com.example.baseactivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

/**
 * 功能描述：
 * Created by AsiaLYF on 2018/4/8.
 */

public class SecondActivity extends BaseActivity implements View.OnClickListener {

    private Button btn_next;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_second;
    }

    @Override
    protected void initView() {
//        cancelTime();
        setTitleText("第二页");
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
                Intent intent = new Intent(this,ThirdActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_back:
                finish();
                break;
        }

    }
}
