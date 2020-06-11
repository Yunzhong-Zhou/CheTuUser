/*******************************************************************************
 * Copyright 2011, 2012 Chris Banes.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.chetu.user.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.chetu.user.R;
import com.chetu.user.base.BaseActivity;

import java.util.ArrayList;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;


/**
 * Created by zyz on 2015/10/26.
 * description:可滑动放大的图片
 */

public class ViewPagerPhotoViewActivity extends BaseActivity {
    private ViewPager mViewPager;//可滑动放大的图片
    private TextView textView;
    private ArrayList<String> imgList;//图片路径
    private int position;//得到的是第几张
    RelativeLayout relativeLayout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_zoomimg_list);
        mImmersionBar.reset()
                .statusBarColor(R.color.black)
                .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
    }

    @Override
    protected void initView() {
        mViewPager = findViewByID_My(R.id.viewPager);
        textView =findViewByID_My(R.id.textView);
        relativeLayout = findViewByID_My(R.id.pop_layout);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                tv_currentPosition.setText(position + 1 + "/" + imgList.size());
                textView.setText(position + 1 + "/" + imgList.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        /*relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });*/
    }

    @Override
    protected void initData() {
        imgList = getIntent().getStringArrayListExtra("imgList");
        position = getIntent().getIntExtra("position", 0);

        if (imgList != null) {
            textView.setText(position + 1 + "/" + imgList.size());
//            titleView.setText("查看所有图片");
//            ImageViewAdapter adapter = new ImageViewAdapter(this,imgList);
            mViewPager.setAdapter(new PagerAdapter() {
                @Override
                public int getCount() {
                    return imgList.size();
                }
                //instantiateItem和destroyItem中的方法要自己去实现
                @Override
                public View instantiateItem(ViewGroup container, int position) {
                    PhotoView photoView = new PhotoView(ViewPagerPhotoViewActivity.this);
                    photoView.enable();
                    Glide.with(ViewPagerPhotoViewActivity.this).load(imgList.get(position).toString()).fitCenter().into(photoView);
                    // Now just add PhotoView to ViewPager and return it
                    container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    photoView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish();
                        }
                    });
                    return photoView;
                }
                @Override
                public void destroyItem(ViewGroup container, int position, Object object) {
                    container.removeView((View) object);
                }

                @Override
                public boolean isViewFromObject(View view, Object object) {
                    return view == object;
                }

            });

            mViewPager.setCurrentItem(position);
        }

    }

    @Override
    protected void updateView() {
        titleView.setVisibility(View.GONE);
    }

    public static void startThisActivity(ArrayList<String> imgList, int position, Activity context) {
        Intent intent = new Intent(context, ViewPagerPhotoViewActivity.class);
        intent.putStringArrayListExtra("imgList", imgList);
        intent.putExtra("position", position);
        context.startActivity(intent);
    }
    public static void startThisActivity(ArrayList<String> imgList, int position, Context context) {
        Intent intent = new Intent(context, ViewPagerPhotoViewActivity.class);
        intent.putStringArrayListExtra("imgList", imgList);
        intent.putExtra("position", position);
        context.startActivity(intent);
    }
}
