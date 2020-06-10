package com.chetu.user.view;

import android.content.Context;

import androidx.recyclerview.widget.LinearSmoothScroller;

/**
 * Created by zyz on 2020/6/10.
 * recyclerView 滑动指定位置到顶部
 *
 * 使用方法
 *                 LinearSmoothScroller s1 = new TopSmoothScroller(this);
 *                 s1.setTargetPosition(item);
 *                 mLinearLayoutManager.startSmoothScroll(s1);
 *
 */
public class TopSmoothScroller extends LinearSmoothScroller {
    public TopSmoothScroller(Context context) {
        super(context);
    }
    @Override
    protected int getHorizontalSnapPreference() {
        return SNAP_TO_START;//具体见源码注释
    }
    @Override
    protected int getVerticalSnapPreference() {
        return SNAP_TO_START;//具体见源码注释
    }
}
