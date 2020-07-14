package com.chetu.user.activity;

import android.os.Bundle;

import com.chetu.user.R;
import com.chetu.user.base.BaseActivity;

/**
 * Created by Mr.X on 2020/7/14.
 * 查询记录
 */
public class CarIllegalListActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carillegallist);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void updateView() {
        titleView.setTitle("查询记录");
    }
}
