package com.chetu.user.activity;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chetu.user.R;
import com.chetu.user.adapter.ImageAdapter;
import com.chetu.user.base.BaseActivity;
import com.chetu.user.model.PingJiaModel;
import com.chetu.user.model.ProductDetailModel;
import com.chetu.user.net.URLs;
import com.chetu.user.okhttp.CallBackUtil;
import com.chetu.user.okhttp.OkhttpUtil;
import com.chetu.user.popupwindow.PhotoShowDialog;
import com.chetu.user.utils.CommonUtil;
import com.chetu.user.utils.MyLogger;
import com.chetu.user.view.TopSmoothScroller;
import com.cy.cyflowlayoutlibrary.FlowLayout;
import com.cy.cyflowlayoutlibrary.FlowLayoutAdapter;
import com.cy.dialog.BaseDialog;
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
public class ProductDetailActivity_1 extends BaseActivity {
    ProductDetailModel model;
    String y_goods_id = "";

    int page = 0;
    RecyclerView recyclerView;
    List<PingJiaModel.ListBean> list = new ArrayList<>();
    CommonAdapter<PingJiaModel.ListBean> mAdapter;
    LinearLayoutManager mLinearLayoutManager;
    HeaderAndFooterWrapper mHeaderAndFooterWrapper;

    TextView tv_pinglun, textView_num, textView_moeny;

    int type = 1;
    TextView textView1, textView2, textView3;

    //头部一
    View headerView1;
    Banner banner;
    TextView banner_indicator;
    ArrayList<String> images = new ArrayList<>();
    TextView head1_tv1, head1_tv2, head1_tv3, head1_tv4, head1_tv5;
    ImageView iv_xihuan;
    boolean isShouChange = false;
    String y_user_collection_id = "";

    //头部二
    View headerView2;
    LinearLayout head2_ll_add;


    //规格
    CommonAdapter<ProductDetailModel.SpecificListBeanX> adapter;
    FlowLayoutAdapter<String> flowLayoutAdapter;
    List<Integer> selects = new ArrayList<>();
    int g_num = 1;
    long allmoney = 0;
    String y_goods_specific_id = "", s_value = "", y_store_id = "", y_store_service_id = "0";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productdetail_1);
    }

    @Override
    protected void initView() {
        //刷新
        setSpringViewMore(true);//不需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                Map<String, String> params = new HashMap<>();
                params.put("y_goods_id", y_goods_id);
                params.put("u_token", localUserInfo.getToken());
                Request(params);

            }

            @Override
            public void onLoadmore() {
                page++;
                Map<String, String> params = new HashMap<>();
                params.put("y_goods_id", y_goods_id);
                params.put("y_store_id", y_store_id);
                params.put("page", page + "");
//                params.put("u_token", localUserInfo.getToken());
                RequestPingJiaMore(params);
            }
        });

        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
        tv_pinglun = findViewByID_My(R.id.tv_pinglun);
        textView_num = findViewByID_My(R.id.textView_num);
        textView_moeny = findViewByID_My(R.id.textView_moeny);

        //头部布局1
        headerView1 = View.inflate(ProductDetailActivity_1.this, R.layout.view_productdetail1, null);
        banner = headerView1.findViewById(R.id.banner);
        banner_indicator = headerView1.findViewById(R.id.banner_indicator);

        head1_tv1 = headerView1.findViewById(R.id.head1_tv1);
        head1_tv3 = headerView1.findViewById(R.id.head1_tv3);
        head1_tv4 = headerView1.findViewById(R.id.head1_tv4);
        head1_tv5 = headerView1.findViewById(R.id.head1_tv5);

        iv_xihuan = headerView1.findViewById(R.id.iv_xihuan);

        head1_tv2 = headerView1.findViewById(R.id.head1_tv2);
        head1_tv2.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);//中划线
        head1_tv2.getPaint().setAntiAlias(true); //去掉锯齿

        //头部布局2
        headerView2 = View.inflate(ProductDetailActivity_1.this, R.layout.view_productdetail2, null);
        head2_ll_add = headerView2.findViewById(R.id.head2_ll_add);

        //评论列表
        recyclerView = findViewByID_My(R.id.recyclerView);
        mLinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        //添加头部必须先设置Adapter
        mAdapter = new CommonAdapter<PingJiaModel.ListBean>
                (ProductDetailActivity_1.this, R.layout.item_productdetail, list) {
            @Override
            protected void convert(ViewHolder holder, PingJiaModel.ListBean model, int position) {
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
                MyLogger.i("》》》》》》" + mLinearLayoutManager.findFirstVisibleItemPosition());
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
        params.put("u_token", localUserInfo.getToken());
        Request(params);


    }

    /**
     * 获取详情数据
     *
     * @param params
     */
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
                model = response;
//                hideProgress();
                showContentPage();

                y_store_id = response.getInfo().getYStoreId();
                y_store_service_id = "0";//商品不用填,下单服务需要填
                y_goods_id = response.getInfo().getYGoodsId();

                allmoney = (long) response.getInfo().getGPrice();
                textView_moeny.setText("¥" + allmoney);
                g_num = 1;
                textView_num.setText(g_num + "");

                /*for (int i = 0; i < model.getSpecific_list().size(); i++) {
                    y_goods_specific_id += model.getSpecific_list().get(i).getYGoodsSpecificId() + ",";
                    String[] strArr = model.getSpecific_list().get(i).getSValue().split("\\|\\|");
                    for (int j = 0; j < strArr.length; j++) {
                        if (j == 0) {
                            s_value += strArr[j] + ",";
                        }
                    }
                }
                y_goods_specific_id = y_goods_specific_id.substring(0, y_goods_specific_id.length() - 1);
                s_value = s_value.substring(0, s_value.length() - 1);
                MyLogger.i(">>>>>>" + y_goods_specific_id +">>>>>>"+ s_value);*/

                /**
                 * 第一页-商品信息
                 */
                //banner
                images.clear();
                /*images.add("http://file02.16sucai.com/d/file/2014/0825/dcb017b51479798f6c60b7b9bd340728.jpg");
                images.add("http://file02.16sucai.com/d/file/2014/0825/dcb017b51479798f6c60b7b9bd340728.jpg");
                images.add("http://file02.16sucai.com/d/file/2014/0825/dcb017b51479798f6c60b7b9bd340728.jpg");
                images.add("http://file02.16sucai.com/d/file/2014/0825/dcb017b51479798f6c60b7b9bd340728.jpg");
                images.add("http://file02.16sucai.com/d/file/2014/0825/dcb017b51479798f6c60b7b9bd340728.jpg");
                images.add("http://file02.16sucai.com/d/file/2014/0825/dcb017b51479798f6c60b7b9bd340728.jpg");
                images.add("http://file02.16sucai.com/d/file/2014/0825/dcb017b51479798f6c60b7b9bd340728.jpg");
                images.add("http://file02.16sucai.com/d/file/2014/0825/dcb017b51479798f6c60b7b9bd340728.jpg");*/
                for (String s : response.getInfo().getImgArr()) {
                    images.add(URLs.IMGHOST + s);
                }
                if (images.size() > 0) {
                    banner_indicator.setText("1/" + images.size());
                } else {
                    banner_indicator.setText("0/" + images.size());
                }
                //TODO 父控件滑动时，banner切换会获取焦点，然后自动全部显示。不想让banner获取焦点可以给父控件加上：
                // android:focusable="true"
                // android:focusableInTouchMode="true"
                banner.addBannerLifecycleObserver(ProductDetailActivity_1.this)//添加生命周期观察者
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
                        /*ZoomIMGPopupWindow popupwindow = new ZoomIMGPopupWindow(ProductDetailActivity.this,
                                URLs.IMGHOST + response.getInfo().getStore_step_two().getImg_positive());*/
//                        ViewPagerPhotoViewActivity.startThisActivity(images, position, ProductDetailActivity.this);
                        PhotoShowDialog photoShowDialog = new PhotoShowDialog(ProductDetailActivity_1.this, images, position);
                        photoShowDialog.show();
                    }
                });
                head1_tv1.setText("¥" + response.getInfo().getGPrice());
                head1_tv2.setText("¥" + response.getInfo().getOrPrice());
                head1_tv3.setText(response.getInfo().getGName());
                head1_tv4.setText(response.getInfo().getGDetails());
                //是否评论
                if (response.getCollection_info() != null && !response.getCollection_info().getYUserCollectionId().equals("")) {
                    y_user_collection_id = response.getCollection_info().getYUserCollectionId();
                    isShouChange = true;
                    iv_xihuan.setImageResource(R.mipmap.ic_xin_yixuan);
                } else {
                    y_user_collection_id = "";
                    isShouChange = false;
                    iv_xihuan.setImageResource(R.mipmap.ic_xin_weixuan);
                }
                /**
                 * 第二页-详情图片
                 */
                head2_ll_add.removeAllViews();
                for (String s : images) {
                    //添加数据
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    LayoutInflater inflater = LayoutInflater.from(ProductDetailActivity_1.this);
                    View view = inflater.inflate(R.layout.item_img_add, null, false);
                    view.setLayoutParams(lp);

                    ImageView imageView = view.findViewById(R.id.imageView);
                    ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
                    lp.width = CommonUtil.getScreenWidth(ProductDetailActivity_1.this);//屏幕宽度
                    lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    imageView.setLayoutParams(lp);
                    imageView.setMaxWidth(lp.width);
                    imageView.setMaxHeight(lp.height);
                    Glide.with(ProductDetailActivity_1.this).load(URLs.IMGHOST + s)
                            .centerCrop()
//                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
//                            .placeholder(R.mipmap.headimg)//加载站位图
//                            .error(R.mipmap.headimg)//加载失败
                            .into(imageView);//加载图片
                    head2_ll_add.addView(view);
                }

                /**
                 * 第三页-评论
                 */
                Map<String, String> params = new HashMap<>();
                params.put("y_goods_id", y_goods_id);
                params.put("y_store_id", y_store_id);
                params.put("page", page + "");
//                params.put("u_token", localUserInfo.getToken());
                RequestPingJia(params);
            }
        });
    }

    /**
     * 获取评价
     *
     * @param params
     */
    private void RequestPingJia(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.PingJiaList, params, headerMap, new CallBackUtil<PingJiaModel>() {
            @Override
            public PingJiaModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
//                myToast(err);
            }

            @Override
            public void onResponse(PingJiaModel response) {
                hideProgress();
                list = response.getList();

                tv_pinglun.setText("用户评论（" + list.size() + "）");


                mAdapter = new CommonAdapter<PingJiaModel.ListBean>
                        (ProductDetailActivity_1.this, R.layout.item_productdetail, list) {
                    @Override
                    protected void convert(ViewHolder holder, PingJiaModel.ListBean model, int position) {
                        //用户评论
                        TextView pinglun = holder.getView(R.id.pinglun);
                        pinglun.setText("用户评论（" + list.size() + "）");
                        if (position == 2)
                            pinglun.setVisibility(View.VISIBLE);
                        else
                            pinglun.setVisibility(View.GONE);

                        //信息
                        holder.setText(R.id.tv_name, model.getY_user().getUserName());
                        holder.setText(R.id.tv_time, model.getCreateDate());
                        holder.setText(R.id.tv_content, model.getYMsg());
                        RatingBar ratingbar = holder.getView(R.id.ratingbar);
                        ratingbar.setRating(Float.valueOf(model.getStarC()));
                        ImageView iv = holder.getView(R.id.iv);
                        Glide.with(ProductDetailActivity_1.this).load(model)
                                .centerCrop()
                                .placeholder(R.mipmap.loading)//加载站位图
                                .error(R.mipmap.zanwutupian)//加载失败
                                .into(iv);

                        //横向图片
                        RecyclerView rv = holder.getView(R.id.rv);
                        LinearLayoutManager llm1 = new LinearLayoutManager(ProductDetailActivity_1.this);
                        llm1.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
                        rv.setLayoutManager(llm1);
                        CommonAdapter<String> ca = new CommonAdapter<String>
                                (ProductDetailActivity_1.this, R.layout.item_img_80_60, images) {
                            @Override
                            protected void convert(ViewHolder holder, String model, int position) {
                                ImageView iv = holder.getView(R.id.iv);
                                Glide.with(ProductDetailActivity_1.this).load(model)
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
                mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(mAdapter);
                mHeaderAndFooterWrapper.addHeaderView(headerView1);
                mHeaderAndFooterWrapper.addHeaderView(headerView2);
                recyclerView.setAdapter(mHeaderAndFooterWrapper);
//                mAdapter.notifyDataSetChanged();
            }
        });
    }


    /**
     * 获取评价=更多
     *
     * @param params
     */
    private void RequestPingJiaMore(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.PingJiaList, params, headerMap, new CallBackUtil<PingJiaModel>() {
            @Override
            public PingJiaModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
                page--;
            }

            @Override
            public void onResponse(PingJiaModel response) {
                hideProgress();
                List<PingJiaModel.ListBean> list1 = new ArrayList<>();
                list1 = response.getList();
                if (list1.size() == 0) {
                    page--;
                    myToast(getString(R.string.app_nomore));
                } else {
                    list.addAll(list1);
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.left_btn:
                finish();
                break;
            case R.id.iv_xihuan:
                //收藏
                isShouChange = !isShouChange;
                if (isShouChange) {
                    iv_xihuan.setImageResource(R.mipmap.ic_xin_yixuan);
                    Map<String, String> params = new HashMap<>();
                    params.put("u_token", localUserInfo.getToken());
                    params.put("y_id", y_goods_id);
                    params.put("category", "1");//1为商品收藏 2为商家收藏 3为论坛收藏
                    RequestShouChang(params);
                } else {
                    iv_xihuan.setImageResource(R.mipmap.ic_xin_weixuan);
                    Map<String, String> params = new HashMap<>();
                    params.put("u_token", localUserInfo.getToken());
                    params.put("y_user_collection_id", y_user_collection_id);
                    RequestQuXiaoShouChang(params);
                }
                break;
            case R.id.head1_tv5:
                //选择规格
                showDialog();
                break;
            case R.id.textView1:
                //商品
                LinearSmoothScroller s1 = new TopSmoothScroller(ProductDetailActivity_1.this);
                s1.setTargetPosition(0);
                mLinearLayoutManager.startSmoothScroll(s1);

                type = 1;
                changeUI();
                break;
            case R.id.textView2:
                //详情
                LinearSmoothScroller s2 = new TopSmoothScroller(ProductDetailActivity_1.this);
                s2.setTargetPosition(1);
                mLinearLayoutManager.startSmoothScroll(s2);

                type = 2;
                changeUI();
                break;
            case R.id.textView3:
                //评价
                LinearSmoothScroller s3 = new TopSmoothScroller(ProductDetailActivity_1.this);
                s3.setTargetPosition(2);
                mLinearLayoutManager.startSmoothScroll(s3);

                type = 3;
                changeUI();
                break;

            case R.id.textView_goumai:
                if (match()) {
                    HashMap<String, String> params = new HashMap<>();
                    params.put("u_token", localUserInfo.getToken());
                    params.put("y_store_id", y_store_id);
                    params.put("y_store_service_id", y_store_service_id);
                    params.put("y_goods_id", y_goods_id);
                    params.put("is_service", "3");//1为服务  2为服务下边的商品 3为独立商品
                    params.put("g_num", g_num + "");
                    params.put("y_goods_specific_id", y_goods_specific_id);
                    params.put("s_value", s_value);
                    RequestAdd(params);
                }
                break;
            default:

                break;
        }
    }

    /**
     * 下单
     *
     * @param params
     */
    private void RequestAdd(HashMap<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.ADDShop, params, headerMap, new CallBackUtil<Object>() {
            @Override
            public Object onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(Object response) {
                hideProgress();
                showToast("加入购物车成功", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        finish();
                    }
                });

            }
        });
    }

    /**
     * 显示规格弹窗
     */
    private void showDialog() {
        BaseDialog dialog1 = new BaseDialog(ProductDetailActivity_1.this);
        dialog1.contentView(R.layout.dialog_productdetail)
                .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT))
                .animType(BaseDialog.AnimInType.BOTTOM)
                .canceledOnTouchOutside(false)
                .gravity(Gravity.BOTTOM)
                .dimAmount(0.7f)
                .show();

        TextView tv_title = dialog1.findViewById(R.id.tv_title);
        tv_title.setText(model.getInfo().getGName());
        TextView tv_money = dialog1.findViewById(R.id.tv_money);
        tv_money.setText("¥" + model.getInfo().getGPrice());
        ImageView iv = dialog1.findViewById(R.id.iv);
        Glide.with(ProductDetailActivity_1.this).load(URLs.IMGHOST + model.getInfo().getGImg())
                .centerCrop()
//                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                .placeholder(R.mipmap.loading)//加载站位图
                .error(R.mipmap.zanwutupian)//加载失败
                .into(iv);//加载图片

        TextView tv_tab = dialog1.findViewById(R.id.tv_tab);
        TextView tv_mendian = dialog1.findViewById(R.id.tv_mendian);
        TextView tv_num = dialog1.findViewById(R.id.tv_num);
        tv_num.setText(g_num + "");

        RecyclerView rv = dialog1.findViewById(R.id.rv);
        selects.clear();
        for (ProductDetailModel.SpecificListBeanX bean : model.getSpecific_list()) {
            selects.add(0);
            rv.setLayoutManager(new LinearLayoutManager(ProductDetailActivity_1.this));
            adapter = new CommonAdapter<ProductDetailModel.SpecificListBeanX>
                    (ProductDetailActivity_1.this, R.layout.item_dialog_guige, model.getSpecific_list()) {
                @Override
                protected void convert(ViewHolder holder, ProductDetailModel.SpecificListBeanX model, int position) {
                    holder.setText(R.id.tv, model.getSName());
//                    String[] strArr = model.getSValue().split("\\|\\|");
                    List<String> tabs = new ArrayList<>();
                    for (ProductDetailModel.SpecificListBeanX.SpecificListBean s : model.getSpecific_List()) {
                        tabs.add(s.getPName());
                    }
                    flowLayoutAdapter = new FlowLayoutAdapter<String>(tabs) {
                        @Override
                        public void bindDataToView(ViewHolder holder, int i, String bean) {
                            TextView tv = holder.getView(R.id.tv);
                            tv.setText(bean);
                            if (selects.get(position) == i) {
                                tv.setTextColor(getResources().getColor(R.color.blue));
                                tv.setBackgroundResource(R.drawable.yuanjiaobiankuang_15_lanse);
                            } else {
                                tv.setTextColor(getResources().getColor(R.color.black));
                                tv.setBackgroundResource(R.drawable.yuanjiao_15_huise1);
                            }
                        }

                        @Override
                        public void onItemClick(int i, String bean) {
                            selects.set(position, i);
                            adapter.notifyDataSetChanged();
                            //计算及显示
                            addView(tv_tab, tv_money, g_num);
                        }

                        @Override
                        public int getItemLayoutID(int position, String bean) {
                            return R.layout.item_guige_flowlayout;
                        }
                    };
                    ((FlowLayout) holder.getView(R.id.flowLayout)).setAdapter(flowLayoutAdapter);

                }
            };
            rv.setAdapter(adapter);
        }
        dialog1.findViewById(R.id.tv_jian).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //减号
                if (g_num > 1) {
                    g_num--;
                    tv_num.setText(g_num + "");
                    addView(tv_tab, tv_money, g_num);
                }
            }
        });
        dialog1.findViewById(R.id.tv_jia).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //加号
//                        if (num < 100) {
                g_num++;
                tv_num.setText(g_num + "");

                addView(tv_tab, tv_money, g_num);
//                        }
            }
        });
        tv_mendian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //门店

            }
        });
        dialog1.findViewById(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.dismiss();
            }
        });
        addView(tv_tab, tv_money, g_num);
    }

    /**
     * 计算
     *
     * @param tv_tab
     * @param tv_money
     * @param num
     */
    private void addView(TextView tv_tab, TextView tv_money, int num) {
        y_goods_specific_id = "";
        s_value = "";
        for (int i = 0; i < model.getSpecific_list().size(); i++) {
//            String[] strArr = model.getSpecific_list().get(i).getSValue().split("\\|\\|");
            for (int j = 0; j <  model.getSpecific_list().get(i).getSpecific_List().size(); j++) {
                if (selects.get(i) == j) {
                    s_value += model.getSpecific_list().get(i).getSpecific_List().get(j).getPName() + "||";
                    y_goods_specific_id += model.getSpecific_list().get(i).getSpecific_List().get(j).getYGoodsSpecificId() + "||";
                }
            }
        }
        y_goods_specific_id = y_goods_specific_id.substring(0, y_goods_specific_id.length() - 1);
        MyLogger.i(">>>>>>" + y_goods_specific_id);
        s_value = s_value.substring(0, s_value.length() - 1);
        tv_tab.setText(s_value);

        allmoney = (long) (model.getInfo().getGPrice() * num);
        tv_money.setText("" + allmoney);

        textView_moeny.setText("¥" + allmoney);
        textView_num.setText(g_num + "");
        head1_tv5.setText(s_value);
    }

    private boolean match() {
        if (y_goods_specific_id.equals("")) {
            myToast("请先选择规格");
            return false;
        }

        return true;
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
                requestServer();
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
        titleView.setVisibility(View.GONE);
    }

}
