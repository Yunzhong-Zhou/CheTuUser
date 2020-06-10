package com.chetu.user.activity;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chetu.user.R;
import com.chetu.user.adapter.ImageAdapter;
import com.chetu.user.base.BaseActivity;
import com.chetu.user.model.ProductDetailModel;
import com.chetu.user.net.URLs;
import com.chetu.user.okhttp.CallBackUtil;
import com.chetu.user.okhttp.OkhttpUtil;
import com.chetu.user.utils.CommonUtil;
import com.chetu.user.view.TopSmoothScroller;
import com.liaoinstan.springview.widget.SpringView;
import com.youth.banner.Banner;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.listener.OnPageChangeListener;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by zyz on 2020/6/10.
 * 商品详情
 */
public class ProductDetailActivity extends BaseActivity {
    String y_goods_id = "";

    RecyclerView recyclerView;
    List<ProductDetailModel.SpecificListBean> list = new ArrayList<>();
    CommonAdapter<ProductDetailModel.SpecificListBean> mAdapter;
    LinearLayoutManager mLinearLayoutManager;
    HeaderAndFooterWrapper mHeaderAndFooterWrapper;

    TextView tv_pinglun;

    int type = 1;
    TextView textView1, textView2, textView3;

    View headerView1;
    Banner banner;
    TextView banner_indicator;
    List<String> images = new ArrayList<>();
    TextView head1_tv1, head1_tv2, head1_tv3, head1_tv4, head1_tv5;

    View headerView2;
    LinearLayout head2_ll_add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productdetail);
    }

    @Override
    protected void initView() {
        //刷新
        setSpringViewMore(false);//不需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                Map<String, String> params = new HashMap<>();
                params.put("y_goods_id", y_goods_id);
//                params.put("u_token", localUserInfo.getToken());
                Request(params);
            }

            @Override
            public void onLoadmore() {
            }
        });

        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
        tv_pinglun = findViewByID_My(R.id.tv_pinglun);

        //头部布局1
        headerView1 = View.inflate(ProductDetailActivity.this, R.layout.head_productdetail1, null);
        banner = headerView1.findViewById(R.id.banner);
        banner_indicator = headerView1.findViewById(R.id.banner_indicator);

        head1_tv1 = headerView1.findViewById(R.id.head1_tv1);
        head1_tv3 = headerView1.findViewById(R.id.head1_tv3);
        head1_tv4 = headerView1.findViewById(R.id.head1_tv4);
        head1_tv5 = headerView1.findViewById(R.id.head1_tv5);

        head1_tv2 = headerView1.findViewById(R.id.head1_tv2);
        head1_tv2.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);//中划线
        head1_tv2.getPaint().setAntiAlias(true); //去掉锯齿

        //头部布局2
        headerView2 = View.inflate(ProductDetailActivity.this, R.layout.head_productdetail2, null);
        head2_ll_add = headerView2.findViewById(R.id.head2_ll_add);

        //列表
        recyclerView = findViewByID_My(R.id.recyclerView);
        mLinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        //添加头部必须先设置Adapter
        mAdapter = new CommonAdapter<ProductDetailModel.SpecificListBean>
                (ProductDetailActivity.this, R.layout.item_productdetail, list) {
            @Override
            protected void convert(ViewHolder holder, ProductDetailModel.SpecificListBean model, int position) {
                //用户评论
                TextView pinglun = holder.getView(R.id.pinglun);
                pinglun.setText("用户评论（" + list.size() + "）");
                if (position == 2)
                    pinglun.setVisibility(View.VISIBLE);
                else
                    pinglun.setVisibility(View.GONE);


                //横向图片
                RecyclerView rv = holder.getView(R.id.rv);
                LinearLayoutManager llm1 = new LinearLayoutManager(ProductDetailActivity.this);
                llm1.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
                rv.setLayoutManager(llm1);
                CommonAdapter<String> ca = new CommonAdapter<String>
                        (ProductDetailActivity.this, R.layout.item_img_80_60, images) {
                    @Override
                    protected void convert(ViewHolder holder, String model, int position) {
                        ImageView iv = holder.getView(R.id.iv);
                        Glide.with(ProductDetailActivity.this).load(model)
//                            .centerCrop()
//                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
//                            .placeholder(R.mipmap.headimg)//加载站位图
//                            .error(R.mipmap.headimg)//加载失败
                                .into(iv);//加载图片
                    }
                };
                rv.setAdapter(ca);
            }
        };
        mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(mAdapter);
        mHeaderAndFooterWrapper.addHeaderView(headerView1);
        mHeaderAndFooterWrapper.addHeaderView(headerView2);
        recyclerView.setAdapter(mHeaderAndFooterWrapper);

        //滑动监听
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (mLinearLayoutManager.findFirstVisibleItemPosition() == 0) {
                    //头部一
                    type = 1;
                    changeUI();
                } else if (mLinearLayoutManager.findFirstVisibleItemPosition() == 1) {
                    //头部二
                    type = 2;
                    changeUI();
                } else if (mLinearLayoutManager.findFirstVisibleItemPosition() >= 2) {
                    //列表
                    type = 3;
                    changeUI();
                }
            }
        });
    }

    @Override
    protected void initData() {
        y_goods_id = getIntent().getStringExtra("y_goods_id");
        requestServer();
    }

    @Override
    public void requestServer() {
        super.requestServer();
//        this.showLoadingPage();
        showProgress(true, getString(R.string.app_loading));
        Map<String, String> params = new HashMap<>();
        params.put("y_goods_id", y_goods_id);
//                params.put("u_token", localUserInfo.getToken());
        Request(params);
    }

    private void Request(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.ProductDetail, params, headerMap, new CallBackUtil<ProductDetailModel>() {
            @Override
            public ProductDetailModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                showEmptyPage();
//                myToast(err);
            }

            @Override
            public void onResponse(ProductDetailModel response) {
                hideProgress();
                showContentPage();
                /**
                 * 第一页
                 */
                //banner
                images.clear();
                images.add("http://file02.16sucai.com/d/file/2014/0825/dcb017b51479798f6c60b7b9bd340728.jpg");
                images.add("http://file02.16sucai.com/d/file/2014/0825/dcb017b51479798f6c60b7b9bd340728.jpg");
                images.add("http://file02.16sucai.com/d/file/2014/0825/dcb017b51479798f6c60b7b9bd340728.jpg");
                images.add("http://file02.16sucai.com/d/file/2014/0825/dcb017b51479798f6c60b7b9bd340728.jpg");
                images.add("http://file02.16sucai.com/d/file/2014/0825/dcb017b51479798f6c60b7b9bd340728.jpg");
                images.add("http://file02.16sucai.com/d/file/2014/0825/dcb017b51479798f6c60b7b9bd340728.jpg");
                images.add("http://file02.16sucai.com/d/file/2014/0825/dcb017b51479798f6c60b7b9bd340728.jpg");
                images.add("http://file02.16sucai.com/d/file/2014/0825/dcb017b51479798f6c60b7b9bd340728.jpg");
                /*for (String s : response.getInfo().getImgArr()) {
                    images.add(URLs.IMGHOST + s);
                }*/
                if (images.size() > 0) {
                    banner_indicator.setText("1/" + images.size());
                } else {
                    banner_indicator.setText("0/" + images.size());
                }
                banner.addBannerLifecycleObserver(ProductDetailActivity.this)//添加生命周期观察者
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
                        banner_indicator.setText((position + 1) + "/" + images.size());
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
                banner.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(Object data, int position) {

                    }
                });
                head1_tv1.setText("¥" + response.getInfo().getGPrice());
                head1_tv2.setText("¥" + response.getInfo().getOrPrice());
                head1_tv3.setText(response.getInfo().getGName());
                head1_tv4.setText(response.getInfo().getGDetails());

                /**
                 * 第二页
                 */
                head2_ll_add.removeAllViews();
                for (String s : images) {
                    //添加数据
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    LayoutInflater inflater = LayoutInflater.from(ProductDetailActivity.this);
                    View view = inflater.inflate(R.layout.item_img_add, null, false);
                    view.setLayoutParams(lp);

                    ImageView imageView = view.findViewById(R.id.imageView);
                    ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
                    lp.width = CommonUtil.getScreenWidth(ProductDetailActivity.this);//屏幕宽度
                    lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    imageView.setLayoutParams(lp);
                    imageView.setMaxWidth(lp.width);
                    imageView.setMaxHeight(lp.height);
                    /*Glide.with(ProductDetailActivity.this).load(URLs.IMGHOST + s)
                            .centerCrop()
//                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
//                            .placeholder(R.mipmap.headimg)//加载站位图
//                            .error(R.mipmap.headimg)//加载失败
                            .into(imageView);//加载图片*/
                    Glide.with(ProductDetailActivity.this).load(s)
//                            .centerCrop()
//                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
//                            .placeholder(R.mipmap.headimg)//加载站位图
//                            .error(R.mipmap.headimg)//加载失败
                            .into(imageView);//加载图片

                    head2_ll_add.addView(view);
                }

                /**
                 * 第三页
                 */
                for (String s : images) {
                    list.add(new ProductDetailModel.SpecificListBean());
                }
//                list = response.getSpecific_list();
                tv_pinglun.setText("用户评论（" + list.size() + "）");
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.textView1:
                //商品
                LinearSmoothScroller s1 = new TopSmoothScroller(ProductDetailActivity.this);
                s1.setTargetPosition(0);
                mLinearLayoutManager.startSmoothScroll(s1);

                type = 1;
                changeUI();
                break;
            case R.id.textView2:
                //详情
                LinearSmoothScroller s2 = new TopSmoothScroller(ProductDetailActivity.this);
                s2.setTargetPosition(1);
                mLinearLayoutManager.startSmoothScroll(s2);

                type = 2;
                changeUI();
                break;
            case R.id.textView3:
                //评价
                LinearSmoothScroller s3 = new TopSmoothScroller(ProductDetailActivity.this);
                s3.setTargetPosition(2);
                mLinearLayoutManager.startSmoothScroll(s3);

                type = 3;
                changeUI();
                break;
        }
    }

    private void changeUI() {
        switch (type) {
            case 1:
                textView1.setTextColor(getResources().getColor(R.color.blue));
                textView2.setTextColor(getResources().getColor(R.color.black2));
                textView3.setTextColor(getResources().getColor(R.color.black2));
                tv_pinglun.setVisibility(View.GONE);
                break;
            case 2:
                textView1.setTextColor(getResources().getColor(R.color.black2));
                textView2.setTextColor(getResources().getColor(R.color.blue));
                textView3.setTextColor(getResources().getColor(R.color.black2));
                tv_pinglun.setVisibility(View.GONE);
                break;
            case 3:
                textView1.setTextColor(getResources().getColor(R.color.black2));
                textView2.setTextColor(getResources().getColor(R.color.black2));
                textView3.setTextColor(getResources().getColor(R.color.blue));

                tv_pinglun.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    protected void updateView() {
        titleView.setVisibility(View.GONE);
    }

}
