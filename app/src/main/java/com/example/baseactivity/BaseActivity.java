package com.example.baseactivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 功能描述：
 * Created by AsiaLYF on 2018/4/8.
 */

public abstract class BaseActivity extends Activity {

    private LinearLayout ll_back;
    private TextView tv_title;
    private ImageView iv_mode;
    private LinearLayout parentLinearLayout;
    private TextView tvCountTime;
    private MyTimeCount mTimeCount;
    private Class<? extends BaseActivity> clazz;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContentView(R.layout.activity_base);
        findView();

        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
            initView();
            initData();
            AppManager.getAppManager().addActivity(this);
            //获取当前activity的名称
            String className = getClass().getSimpleName();
            clazz = getClass();
        }
    }

    private void startCountTime() {
        mTimeCount = new MyTimeCount(9000, 1000);// 构造CountDownTimer对象
        mTimeCount.start();
    }

    /**
     * 将baseActivity布局添加到parentLinearLayout
     * @param resId
     */
    private void initContentView(int resId) {
        //把parentLinearLayout布局添加到viewGroup里实现布局的关联
        ViewGroup viewGroup = findViewById(android.R.id.content);
        viewGroup.removeAllViews();
        parentLinearLayout = new LinearLayout(this);
        parentLinearLayout.setOrientation(LinearLayout.VERTICAL);
        viewGroup.addView(parentLinearLayout);
        LayoutInflater.from(this).inflate(resId,parentLinearLayout,true);

    }

    private void findView() {
        ll_back = findViewById(R.id.ll_back);
        tv_title = findViewById(R.id.tv_title);
        iv_mode = findViewById(R.id.iv_mode);
        tvCountTime = findViewById(R.id.tv_count_down_time);

    }

    /**
     * 将子Activity布局添加到parentLinearLayout
     *
     */
    @Override
    public void setContentView(int layoutResID) {
        LayoutInflater.from(this).inflate(layoutResID,parentLinearLayout,true);

    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    /**
     * 返回按钮
     *
     * @return
     */
    public LinearLayout setBack() {
        return ll_back;
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitleText(String title) {
        if (null != tv_title) {
            tv_title.setText(title);
        }
    }

    /**
     * 设置右边按钮是否显示
     *
     * @param visible
     */
    public void setModeVisibility(boolean visible) {
        if (null != iv_mode) {
            if (visible) {
                iv_mode.setVisibility(View.VISIBLE);
            } else {
                iv_mode.setVisibility(View.GONE);
            }
        }

    }

    /**
     * 取消定时
     */
    public void cancelTime(){
        mTimeCount.cancel();
        tvCountTime.setVisibility(View.GONE);
    }

    /**
     * 定义一个倒计时类
     *
     * @author user
     */
    class MyTimeCount extends CountDownTimer {

        // 参数依次为总时长,和计时的时间间隔
        public MyTimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        // 计时过程显示
        @Override
        public void onTick(long millisUntilFinished) {
            tvCountTime.setText(millisUntilFinished / 1000 + "");

        }

        // 计时完毕时触发
        @Override
        public void onFinish() {
            tvCountTime.setText("0");
            AppManager.getAppManager().finishActivity(clazz);
        }

    }

    /**
     * 开始计时
     */
    @Override
    protected void onResume() {
        super.onResume();
        startCountTime();
    }

    /**
     * 重置时间
     */
    @Override
    protected void onStop() {
        super.onStop();
        mTimeCount.cancel();
        mTimeCount.onTick(9000);
    }
}
