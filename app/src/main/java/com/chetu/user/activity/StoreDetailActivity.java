package com.chetu.user.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.chetu.user.R;
import com.chetu.user.adapter.ImageAdapter;
import com.chetu.user.base.BaseActivity;
import com.chetu.user.utils.MyLogger;
import com.liaoinstan.springview.widget.SpringView;
import com.youth.banner.Banner;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.listener.OnPageChangeListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zyz on 2020/5/31.
 * 门店详情
 */
public class StoreDetailActivity extends BaseActivity {
    String id = "";

    Banner banner;
    TextView banner_indicator;
    List<String> images = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storedetail);
    }

    @Override
    protected void initView() {
        //刷新
        setSpringViewMore(false);//不需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                /*page1 = 0;
                page2 = 0;
                Map<String, String> params = new HashMap<>();
                params.put("page", page1 + "");
                params.put("y_parent_id", y_parent_id);
                params.put("y_service_id", y_service_id);
                params.put("longitude", longitude);
                params.put("latitude", latitude);
                Request(params);*/
            }

            @Override
            public void onLoadmore() {
            }
        });


        banner = findViewByID_My(R.id.banner);
        banner_indicator = findViewByID_My(R.id.banner_indicator);
        //banner
        images.add("http://file02.16sucai.com/d/file/2014/0825/dcb017b51479798f6c60b7b9bd340728.jpg");
        images.add("http://file02.16sucai.com/d/file/2014/0825/dcb017b51479798f6c60b7b9bd340728.jpg");
        images.add("http://file02.16sucai.com/d/file/2014/0825/dcb017b51479798f6c60b7b9bd340728.jpg");
        images.add("http://file02.16sucai.com/d/file/2014/0825/dcb017b51479798f6c60b7b9bd340728.jpg");
        if (images.size() > 0) {
            banner_indicator.setText("1/" + images.size());
        } else {
            banner_indicator.setText("0/" + images.size());
        }
        /*images.clear();
        for (int i = 0; i < response.getBanner().size(); i++) {
            images.add(OkHttpClientManager.IMGHOST+response.getBanner().get(i).getUrl());
        }*/
        banner.addBannerLifecycleObserver(this)//添加生命周期观察者
                .setDelayTime(3000)//设置轮播时间
//                .setBannerGalleryEffect(10, 10)//为banner添加画廊效果
                .setAdapter(new ImageAdapter(images))
//                .setIndicator(new RectangleIndicator(StoreDetailActivity.this))
//                .setIndicator(new BaseIndicator(StoreDetailActivity.this))
                .setIndicatorGravity(IndicatorConfig.Direction.RIGHT)
                .start();
        banner.addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                MyLogger.i(">>>>" + (position + 1));
                banner_indicator.setText((position + 1) + "/" + images.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(Object data, int position) {
                /*Bundle bundle = new Bundle();
                bundle.putInt("type", response.getBanner().get(position).getType());
                CommonUtil.gotoActivityWithData(JiFenShangChengActivity.this, JiFenLieBiaoActivity.class, bundle, false);*/
            }
        });
    }

    @Override
    protected void initData() {
        id = getIntent().getStringExtra("id");
    }

    @Override
    protected void updateView() {
        titleView.setTitle("门店详情");
        titleView.setRightBtn(R.mipmap.ic_message_blue, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
