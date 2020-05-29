package com.chetu.user.activity;

import android.os.Bundle;

import com.chetu.user.R;
import com.chetu.user.base.BaseActivity;

/**
 * Created by zyz on 2020/5/29.
 * 添加车辆
 */
public class AddCarActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectcar);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void updateView() {
        titleView.setTitle("添加车辆");
    }
}
