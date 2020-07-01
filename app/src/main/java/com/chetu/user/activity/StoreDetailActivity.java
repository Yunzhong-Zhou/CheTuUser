package com.chetu.user.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chetu.user.R;
import com.chetu.user.adapter.ImageAdapter;
import com.chetu.user.base.BaseActivity;
import com.chetu.user.model.PingJiaModel;
import com.chetu.user.model.StoreDetailModel;
import com.chetu.user.model.StoreDetailModel_WenDa;
import com.chetu.user.net.URLs;
import com.chetu.user.okhttp.CallBackUtil;
import com.chetu.user.okhttp.OkhttpUtil;
import com.chetu.user.popupwindow.PhotoShowDialog;
import com.chetu.user.utils.CommonUtil;
import com.chetu.user.view.DiscussionAvatarView.DiscussionAvatarView;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by zyz on 2020/5/31.
 * 门店详情
 */
public class StoreDetailActivity extends BaseActivity {
    int page = 0;
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
    //门店特色
    RecyclerView rv_tese;
    List<String> list_tese = new ArrayList<>();
    CommonAdapter<String> mAdapter_tese;
    //门店技师
    RecyclerView rv_jishi;
    List<StoreDetailModel.StoreTechListBean> list_jishi = new ArrayList<>();
    CommonAdapter<StoreDetailModel.StoreTechListBean> mAdapter_jishi;

    //门店提问
    TextView tv_wenti;
    RecyclerView rv_wenti;
    List<StoreDetailModel_WenDa.ListBean> list_wenti = new ArrayList<>();
    CommonAdapter<StoreDetailModel_WenDa.ListBean> mAdapter_wenti;

    DiscussionAvatarView mDiscussAva;
    ArrayList<String> mDatas = new ArrayList<>();

    //门店评论
    TextView tv_pinglun;
    RecyclerView rv_pinglun;
    List<PingJiaModel.ListBean> list_pinglun = new ArrayList<>();
    CommonAdapter<PingJiaModel.ListBean> mAdapter_pinglun;

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

        //门店特色
        rv_tese = findViewByID_My(R.id.rv_tese);
        rv_tese.setLayoutManager(new GridLayoutManager(this, 2));

        //门店技师
        rv_jishi = findViewByID_My(R.id.rv_jishi);
        LinearLayoutManager llm1 = new LinearLayoutManager(this);
        llm1.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
        rv_jishi.setLayoutManager(llm1);

        //提问
        tv_wenti = findViewByID_My(R.id.tv_wenti);
        rv_wenti = findViewByID_My(R.id.rv_wenti);
        rv_wenti.setLayoutManager(new LinearLayoutManager(this));
        mDiscussAva = findViewByID_My(R.id.mDiscussAva);

        //门店评论
        tv_pinglun = findViewByID_My(R.id.tv_pinglun);
        rv_pinglun = findViewByID_My(R.id.rv_pinglun);
        rv_pinglun.setLayoutManager(new LinearLayoutManager(this));
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
        //获取店铺详情
        showProgress(true, getString(R.string.app_loading));
        Map<String, String> params = new HashMap<>();
        params.put("u_token", localUserInfo.getToken());
        params.put("longitude", longitude);
        params.put("latitude", latitude);
        params.put("y_store_id", y_store_id);
        Request(params);

        //获取提问
        Map<String, String> params1 = new HashMap<>();
        params1.put("u_token", localUserInfo.getToken());
        params1.put("y_store_id", y_store_id);
        RequestWenTi(params1);

        page = 0;
        //获取店铺评论
        Map<String, String> params2 = new HashMap<>();
        params2.put("u_token", localUserInfo.getToken());
        params2.put("y_store_id", y_store_id);
        params2.put("y_goods_id", "0");
        params2.put("page", page + "");
        RequestPingLun(params2);
    }

    /**
     * 店铺详情
     *
     * @param params
     */
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
                        PhotoShowDialog photoShowDialog = new PhotoShowDialog(StoreDetailActivity.this, images, position);
                        photoShowDialog.show();
                    }
                });

                //店铺信息
//                tv_time.setText("营业时间：" + );
                tv_name.setText(response.getInfo().getVName());//店铺名字
                tv_dengji.setText(response.getInfo().getVLevel());//店铺等级
                tv_phone.setText(response.getInfo().getPhone());//店铺电话
                tv_addr.setText(response.getInfo().getAddress());//店铺地址
                tv_juli.setText("距离" + response.getInfo().getDistance() + "m");//距离
                tv_content.setText(response.getInfo().getSlogan());//简介
                tv_pingfen.setText(response.getInfo().getReview());//店铺评分
                tv_dingdan.setText(response.getInfo().getOrderSum() + "");//店铺订单
                tv_jieshao.setText(response.getInfo().getIntroduce());//店铺介绍
                if (response.getInfo().getColle_info() != null
                        && response.getInfo().getColle_info().getYUserCollectionId() != null
                        && !response.getInfo().getColle_info().getYUserCollectionId().equals("")) {
                    y_user_collection_id = response.getInfo().getColle_info().getYUserCollectionId();
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
                                .placeholder(R.mipmap.loading)//加载站位图
                                .error(R.mipmap.zanwutupian)//加载失败
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
                //门店特色
                list_tese = response.getInfo().getCharactArr();
                ArrayList<String> images = new ArrayList<>();
                for (String s : list_tese) {
                    images.add(URLs.IMGHOST + s);
                }
                mAdapter_tese = new CommonAdapter<String>
                        (StoreDetailActivity.this, R.layout.item_img_m_110, images) {
                    @Override
                    protected void convert(ViewHolder holder, String model, int position) {
                        ImageView iv = holder.getView(R.id.iv);
                        Glide.with(StoreDetailActivity.this)
                                .load(model)
                                .centerCrop()
                                .placeholder(R.mipmap.loading)//加载站位图
                                .error(R.mipmap.zanwutupian)//加载失败
                                .into(iv);//加载图片
                    }
                };
                mAdapter_tese.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {

                        PhotoShowDialog photoShowDialog = new PhotoShowDialog(StoreDetailActivity.this, images, i);
                        photoShowDialog.show();
                    }

                    @Override
                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        return false;
                    }
                });
                rv_tese.setAdapter(mAdapter_tese);
                //门店技师
                list_jishi = response.getStore_tech_list();
                mAdapter_jishi = new CommonAdapter<StoreDetailModel.StoreTechListBean>
                        (StoreDetailActivity.this, R.layout.item_storedetail_jishi, list_jishi) {
                    @Override
                    protected void convert(ViewHolder holder, StoreDetailModel.StoreTechListBean model, int position) {
                        ImageView imageView = holder.getView(R.id.imageView);
                        Glide.with(StoreDetailActivity.this)
                                .load(URLs.IMGHOST + model.getHeadPortrait())
                                .centerCrop()
                                .placeholder(R.mipmap.loading)//加载站位图
                                .error(R.mipmap.zanwutupian)//加载失败
                                .into(imageView);//加载图片
                        holder.setText(R.id.tv_name, model.getUserName());

                        RatingBar ratingbar = holder.getView(R.id.ratingbar);
                        ratingbar.setRating(Float.valueOf(model.getTech_info().getStar()));

                        holder.setText(R.id.tv_time, "入驻时间：" + model.getCreateDate());
                        TextView tv_zhuangtai = holder.getView(R.id.tv_zhuangtai);
                        switch (model.getTech_info().getWorking()) {//1为空闲 2为休假  3为忙碌
                            case 1:
                                tv_zhuangtai.setText("休息中");
                                tv_zhuangtai.setBackgroundResource(R.drawable.yuanjiao_5_lanse_right);
                                break;
                            case 2:
                                tv_zhuangtai.setText("休假");
                                tv_zhuangtai.setBackgroundResource(R.drawable.yuanjiao_5_huise_right);
                                break;
                            case 3:
                                tv_zhuangtai.setText("忙碌中");
                                tv_zhuangtai.setBackgroundResource(R.drawable.yuanjiao_5_hongse_right);
                                break;
                        }
                    }
                };
                mAdapter_jishi.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                    }

                    @Override
                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        return false;
                    }
                });
                rv_jishi.setAdapter(mAdapter_jishi);
            }
        });

    }

    /**
     * 店铺问答
     *
     * @param
     */
    private void RequestWenTi(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.StoreDetail_WenDa, params, headerMap, new CallBackUtil<StoreDetailModel_WenDa>() {
            @Override
            public StoreDetailModel_WenDa onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
//                hideProgress();
//                showEmptyPage();
//                myToast(err);

            }

            @Override
            public void onResponse(StoreDetailModel_WenDa response) {
//                hideProgress();

                //取前2条数据
                if (response.getList().size() > 2) {
                    list_wenti = response.getList().subList(0, 2);
                } else {
                    list_wenti = response.getList();
                }

                //头像重叠
                mDatas.clear();
                for (StoreDetailModel_WenDa.ListBean bean : response.getList()) {
//                    if (!bean.getUser_info().getHeadPortrait().trim().equals("")){
                    mDatas.add(URLs.IMGHOST + bean.getUser_info().getHeadPortrait());
//                    }
                }
               /* if (mDatas.size()>=4){
                    mDiscussAva.setMaxCount(4);
                }else {
                    mDiscussAva.setMaxCount(mDatas.size());
                }*/
                mDiscussAva.initDatas(mDatas);


                tv_wenti.setText("提问（" + response.getSum() + "）");

                mAdapter_wenti = new CommonAdapter<StoreDetailModel_WenDa.ListBean>
                        (StoreDetailActivity.this, R.layout.item_storedetail_wenti, list_wenti) {
                    @Override
                    protected void convert(ViewHolder holder, StoreDetailModel_WenDa.ListBean model, int position) {
                        //信息
                        holder.setText(R.id.tv_wenti, model.getMsg());
                        holder.setText(R.id.tv_huida, "" + model.getC_list().size() + "个回答");
                    }
                };
                rv_wenti.setAdapter(mAdapter_wenti);
            }
        });
    }

    /**
     * 店铺评论
     *
     * @param
     */
    private void RequestPingLun(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.PingJiaList, params, headerMap, new CallBackUtil<PingJiaModel>() {
            @Override
            public PingJiaModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
//                hideProgress();
//                showEmptyPage();
//                myToast(err);
            }

            @Override
            public void onResponse(PingJiaModel response) {
//                hideProgress();
//                if (response.getList().size() != 0) {
//                    loading_layout1.showContent();
//                    loading_layout3.showContent();
                //取前2条数据
                if (response.getList().size() > 2) {
                    list_pinglun = response.getList().subList(0, 2);
                } else {
                    list_pinglun = response.getList();
                }

                tv_pinglun.setText("用户评论（" + response.getCount() + "）");

                mAdapter_pinglun = new CommonAdapter<PingJiaModel.ListBean>
                        (StoreDetailActivity.this, R.layout.item_productdetail, list_pinglun) {
                    @Override
                    protected void convert(ViewHolder holder, PingJiaModel.ListBean model, int position) {
                        //信息
                        holder.setText(R.id.tv_name, model.getY_user().getUserName());
                        holder.setText(R.id.tv_time, model.getCreateDate());
                        holder.setText(R.id.tv_content, model.getYMsg());
                        RatingBar ratingbar = holder.getView(R.id.ratingbar);
                        ratingbar.setRating(Float.valueOf(model.getStarC()));
                        ImageView iv = holder.getView(R.id.iv);
                        Glide.with(StoreDetailActivity.this).load(model)
                                .centerCrop()
                                .placeholder(R.mipmap.loading)//加载站位图
                                .error(R.mipmap.zanwutupian)//加载失败
                                .into(iv);

                        //横向图片
                        List<String> list_img = new ArrayList<>();
                        for (String s : model.getImgArr()) {
                            list_img.add(URLs.IMGHOST + s);
                        }
                        RecyclerView rv = holder.getView(R.id.rv);
                        LinearLayoutManager llm1 = new LinearLayoutManager(StoreDetailActivity.this);
                        llm1.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
                        rv.setLayoutManager(llm1);
                        CommonAdapter<String> ca = new CommonAdapter<String>
                                (StoreDetailActivity.this, R.layout.item_img_80_80, list_img) {
                            @Override
                            protected void convert(ViewHolder holder, String model, int position) {
                                ImageView iv = holder.getView(R.id.iv);
                                Glide.with(StoreDetailActivity.this).load(model)
//                            .centerCrop()
//                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                                        .placeholder(R.mipmap.loading)//加载站位图
                                        .error(R.mipmap.zanwutupian)//加载失败
                                        .into(iv);//加载图片
                            }
                        };
                        rv.setAdapter(ca);
                    }
                };
                rv_pinglun.setAdapter(mAdapter_pinglun);

//                } else {
//                    loading_layout1.showEmpty();
//                    loading_layout3.showEmpty();
//                }
            }
        });
    }

    /**
     * 获取评价=更多
     *
     * @param params
     */
    private void RequestPingLunMore(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.PingJiaList, params, headerMap, new CallBackUtil<PingJiaModel>() {
            @Override
            public PingJiaModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
//                hideProgress();
                myToast(err);
                page--;
            }

            @Override
            public void onResponse(PingJiaModel response) {
//                hideProgress();
                List<PingJiaModel.ListBean> list1 = new ArrayList<>();
                list1 = response.getList();
                if (list1.size() == 0) {
                    page--;
                    myToast(getString(R.string.app_nomore));
                } else {
                    list_pinglun.addAll(list1);
                    mAdapter_pinglun.notifyDataSetChanged();
                }
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
            case R.id.ll_tiwen_more:
                //提问更多
                Bundle bundle = new Bundle();
                bundle.putString("y_store_id", y_store_id);
                CommonUtil.gotoActivityWithData(StoreDetailActivity.this, TiWenListActivity.class, bundle, false);
                break;
            case R.id.ll_pinglun_more:
                //获取店铺评论
                Bundle bundle1 = new Bundle();
                bundle1.putString("y_store_id", y_store_id);
                CommonUtil.gotoActivityWithData(StoreDetailActivity.this, PingLunListActivity.class, bundle1, false);
                /*page++;
                Map<String, String> params2 = new HashMap<>();
                params2.put("u_token", localUserInfo.getToken());
                params2.put("y_store_id", y_store_id);
                params2.put("y_goods_id", "");
                params2.put("page", page + "");
                RequestPingLunMore(params2);*/
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
