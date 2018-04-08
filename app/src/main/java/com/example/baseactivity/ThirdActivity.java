package com.example.baseactivity;

import android.view.View;

/**
 * 功能描述：
 * Created by AsiaLYF on 2018/4/8.
 */

public class ThirdActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_third;
    }

    @Override
    protected void initView() {
//        cancelTime();
        setTitleText("第三页");
        setBack().setOnClickListener(this);

    }

    @Override
    protected void initData() {

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_back:
                finish();
                break;
        }

    }
}
