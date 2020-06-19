package com.chetu.user.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chetu.user.R;
import com.chetu.user.adapter.ImageAdapter;
import com.chetu.user.base.BaseActivity;
import com.chetu.user.model.StoreDetailModel;
import com.chetu.user.net.URLs;
import com.chetu.user.okhttp.CallBackUtil;
import com.chetu.user.okhttp.OkhttpUtil;
import com.liaoinstan.springview.widget.SpringView;
import com.youth.banner.Banner;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.listener.OnPageChangeListener;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by zyz on 2020/5/31.
 * 门店详情
 */
public class StoreDetailActivity extends BaseActivity {
    String y_store_id = "", longitude = "", latitude = "";

    //banner
    Banner banner;
    TextView banner_indicator;
    List<String> images = new ArrayList<>();

    //门店信息
    TextView tv_time, tv_name, tv_dengji, tv_phone, tv_addr, tv_juli, tv_content, tv_pingfen, tv_dingdan, tv_jieshao;
    ImageView iv_xihuan;
    boolean isShouChange = false;
    String y_user_collection_id = "";

    //门店服务
    RecyclerView rv_tab;
    List<StoreDetailModel.StoreServiceListBean> list_tab = new ArrayList<>();
    CommonAdapter<StoreDetailModel.StoreServiceListBean> mAdapter_tab;

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
                Map<String, String> params = new HashMap<>();
                params.put("u_token", localUserInfo.getToken());
                params.put("longitude", longitude);
                params.put("latitude", latitude);
                params.put("y_store_id", y_store_id);
                Request(params);
            }

            @Override
            public void onLoadmore() {
            }
        });

        //banner
        banner = findViewByID_My(R.id.banner);
        banner_indicator = findViewByID_My(R.id.banner_indicator);

        //门店信息
        tv_time = findViewByID_My(R.id.tv_time);
        tv_name = findViewByID_My(R.id.tv_name);
        tv_dengji = findViewByID_My(R.id.tv_dengji);
        tv_phone = findViewByID_My(R.id.tv_phone);
        tv_addr = findViewByID_My(R.id.tv_addr);
        tv_juli = findViewByID_My(R.id.tv_juli);
        tv_content = findViewByID_My(R.id.tv_content);
        tv_pingfen = findViewByID_My(R.id.tv_pingfen);
        tv_dingdan = findViewByID_My(R.id.tv_dingdan);
        tv_jieshao = findViewByID_My(R.id.tv_jieshao);
        iv_xihuan = findViewByID_My(R.id.iv_xihuan);

        //门店服务
        rv_tab = findViewByID_My(R.id.rv_tab);
        rv_tab.setLayoutManager(new GridLayoutManager(this, 3));


    }

    @Override
    protected void initData() {
        y_store_id = getIntent().getStringExtra("id");
        longitude = getIntent().getStringExtra("longitude");
        latitude = getIntent().getStringExtra("latitude");

        requestServer();
    }

    @Override
    public void requestServer() {
        super.requestServer();
//        this.showLoadingPage();
//        page = 0;
        showProgress(true, getString(R.string.app_loading));
        Map<String, String> params = new HashMap<>();
        params.put("u_token", localUserInfo.getToken());
        params.put("longitude", longitude);
        params.put("latitude", latitude);
        params.put("y_store_id", y_store_id);
        Request(params);
    }

    private void Request(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.StoreDetail, params, headerMap, new CallBackUtil<StoreDetailModel>() {
            @Override
            public StoreDetailModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                showEmptyPage();
                myToast(err);
            }

            @Override
            public void onResponse(StoreDetailModel response) {
                hideProgress();
                //banner
                /*images.add("http://file02.16sucai.com/d/file/2014/0825/dcb017b51479798f6c60b7b9bd340728.jpg");
                images.add("http://file02.16sucai.com/d/file/2014/0825/dcb017b51479798f6c60b7b9bd340728.jpg");
                images.add("http://file02.16sucai.com/d/file/2014/0825/dcb017b51479798f6c60b7b9bd340728.jpg");
                images.add("http://file02.16sucai.com/d/file/2014/0825/dcb017b51479798f6c60b7b9bd340728.jpg");*/
                images.clear();
                for (int i = 0; i < response.getInfo().getPictureArr().size(); i++) {
                    images.add(URLs.IMGHOST + response.getInfo().getPictureArr().get(i));
                }
                if (images.size() > 0) {
                    banner_indicator.setText("1/" + images.size());
                } else {
                    banner_indicator.setText("0/" + images.size());
                }

                banner.addBannerLifecycleObserver(StoreDetailActivity.this)//添加生命周期观察者
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
                /*Bundle bundle = new Bundle();
                bundle.putInt("type", response.getBanner().get(position).getType());
                CommonUtil.gotoActivityWithData(JiFenShangChengActivity.this, JiFenLieBiaoActivity.class, bundle, false);*/
                    }
                });

                //店铺信息
//                tv_time.setText("营业时间：" + );
                tv_name.setText(response.getInfo().getVName());//店铺名字
                tv_dengji.setText(response.getInfo().getVLevel());//店铺等级
                tv_phone.setText(response.getInfo().getPhone());//店铺电话
                tv_addr.setText(response.getInfo().getAddress());//店铺地址
                tv_juli.setText("距离" + response.getInfo().getDistance() + "m");//距离
                tv_content.setText(response.getInfo().getIntroduce());//
                tv_pingfen.setText(response.getInfo().getReview());//店铺评分
                tv_dingdan.setText(response.getInfo().getOrderSum() + "");//店铺订单
                tv_jieshao.setText(response.getInfo().getIntroduce());//店铺介绍
                if (response.getInfo().getColle_info() != null && !response.getInfo().getColle_info().getYUserCollectionId().equals("")) {
                    y_user_collection_id = response.getInfo().getColle_info() .getYUserCollectionId();
                    isShouChange = true;
                    iv_xihuan.setImageResource(R.mipmap.ic_xin_yixuan);
                } else {
                    y_user_collection_id = "";
                    isShouChange = false;
                    iv_xihuan.setImageResource(R.mipmap.ic_xin_weixuan);
                }
                //门店服务
                list_tab = response.getStore_service_list();
                mAdapter_tab = new CommonAdapter<StoreDetailModel.StoreServiceListBean>
                        (StoreDetailActivity.this, R.layout.item_storedetail_service, list_tab) {
                    @Override
                    protected void convert(ViewHolder holder, StoreDetailModel.StoreServiceListBean model, int position) {
                        ImageView imageView = holder.getView(R.id.imageView);
                        Glide.with(StoreDetailActivity.this)
                                .load(URLs.IMGHOST + model.getPictureStr())
                                .centerCrop()
//                    .placeholder(R.mipmap.headimg)//加载站位图
//                    .error(R.mipmap.headimg)//加载失败
                                .into(imageView);//加载图片
                        holder.setText(R.id.tv_name, model.getYStateValue());
                        holder.setText(R.id.tv_paidui, "排队:" + model.getLineupSum());
                        View view = holder.getView(R.id.view);
                        if (model.getYState() == 0) {
                            //空闲
                            view.setBackgroundResource(R.drawable.yuanxing_lvse);
                        } else {
                            //忙碌
                            view.setBackgroundResource(R.drawable.yuanxing_hongse);
                        }
                    }
                };
                mAdapter_tab.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        Bundle bundle = new Bundle();
                        /*switch (list_tab.get(i).getCategory()){
                            case 2:
                                //跳转商品列表
                                bundle.putString("id",list_tab.get(i).getId());
                                CommonUtil.gotoActivityWithData(getActivity(), ProductListActivity.class,bundle);
                                break;
                            case 3:
                                //跳转违章查询
                                CommonUtil.gotoActivity(getActivity(), CarIllegalActivity.class);
                                break;
                            case 4:
                                //跳转保险查询
                                CommonUtil.gotoActivity(getActivity(), CarInsuranceActivity.class);
                                break;
                            default:
                                //跳转门店搜索
                                bundle.putString("keys",list_tab.get(i).getTitle());
                                CommonUtil.gotoActivityWithData(getActivity(), SearchActivity.class,bundle);
                                break;
                        }*/
                    }

                    @Override
                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        return false;
                    }
                });
                rv_tab.setAdapter(mAdapter_tab);

                /*list = response.getList();
                if (list.size() > 0) {
                    showContentPage();
                    mAdapter = new CommonAdapter<Fragment3Model.ListBean>
                            (getActivity(), R.layout.item_fragment3, list) {
                        @Override
                        protected void convert(ViewHolder holder, Fragment3Model.ListBean model, int position) {
                            ImageView imageView1 = holder.getView(R.id.imageView1);
                            Glide.with(getActivity())
                                    .load(URLs.IMGHOST + model.getPictureStr())
                                    .centerCrop()
//                    .placeholder(R.mipmap.headimg)//加载站位图
//                    .error(R.mipmap.headimg)//加载失败
                                    .into(imageView1);//加载图片
                            holder.setText(R.id.tv_name,model.getVName());//店名
                            holder.setText(R.id.tv_pingfen,model.getReview());//评分
                            holder.setText(R.id.tv_dingdan,model.getVName());//订单

                        }
                    };
                    mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            Bundle bundle = new Bundle();
                            bundle.putString("id", list.get(i).getYStoreId());
                            bundle.putString("longitude", longitude);
                            bundle.putString("latitude", latitude);
                            CommonUtil.gotoActivityWithData(getActivity(), StoreDetailActivity.class, bundle, false);
                        }

                        @Override
                        public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            return false;
                        }
                    });
                    recyclerView.setAdapter(mAdapter);
                } else {
                    showEmptyPage();
                }*/
            }
        });

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_xihuan:
                //收藏
                isShouChange = !isShouChange;
                if (isShouChange) {
                    iv_xihuan.setImageResource(R.mipmap.ic_xin_yixuan);
                    Map<String, String> params = new HashMap<>();
                    params.put("u_token", localUserInfo.getToken());
                    params.put("y_id", y_store_id);
                    params.put("category", "2");//1为商品收藏 2为商家收藏 3为论坛收藏
                    RequestShouChang(params);
                } else {
                    iv_xihuan.setImageResource(R.mipmap.ic_xin_weixuan);
                    Map<String, String> params = new HashMap<>();
                    params.put("u_token", localUserInfo.getToken());
                    params.put("y_user_collection_id", y_user_collection_id);
                    RequestQuXiaoShouChang(params);
                }
                break;
        }
    }

    /**
     * 收藏
     *
     * @param params
     */
    private void RequestShouChang(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.ShouChang, params, headerMap, new CallBackUtil<Object>() {
            @Override
            public Object onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {

            }

            @Override
            public void onResponse(Object response) {

            }
        });
    }

    /**
     * 取消收藏
     *
     * @param params
     */
    private void RequestQuXiaoShouChang(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.QuXiaoShouChang, params, headerMap, new CallBackUtil<Object>() {
            @Override
            public Object onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {

            }

            @Override
            public void onResponse(Object response) {

            }
        });
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
