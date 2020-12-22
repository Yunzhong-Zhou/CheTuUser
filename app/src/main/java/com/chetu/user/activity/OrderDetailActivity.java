package com.chetu.user.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.bumptech.glide.Glide;
import com.chetu.user.R;
import com.chetu.user.base.BaseActivity;
import com.chetu.user.model.OrderDetailModel;
import com.chetu.user.model.PayModel;
import com.chetu.user.net.URLs;
import com.chetu.user.okhttp.CallBackUtil;
import com.chetu.user.okhttp.OkhttpUtil;
import com.chetu.user.popupwindow.PhotoShowDialog;
import com.chetu.user.utils.CommonUtil;
import com.chetu.user.utils.MyLogger;
import com.chetu.user.utils.alipay.PayResult;
import com.cy.cyflowlayoutlibrary.FlowLayout;
import com.cy.cyflowlayoutlibrary.FlowLayoutAdapter;
import com.cy.dialog.BaseDialog;
import com.liaoinstan.springview.widget.SpringView;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
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
 * Created by zyz on 2020/7/10.
 * 订单详情
 */
public class OrderDetailActivity extends BaseActivity {
    int type = 1, g_state = 0;
    String y_order_id = "";
    OrderDetailModel model;

    private LinearLayout linearLayout1, linearLayout2, linearLayout3, ll_fuwu, ll_jiance, ll_beizhu,
            ll_other, ll_heji1, ll_heji2, ll_btn;
    private TextView textView1, textView2, textView3;
    private View view1, view2, view3;

    TextView tv_storename, tv_addr, tv_juli, tv_jiecheren, tv_wanchengtime, tv_yujiwanchengtime, tv_dingdannum, tv_carname, tv_carcontent,
            tv_beizhu, tv_servicenum, tv_goodsnum, tv_allmoney1,
            tv_servicemoney, tv_jiancemoney, tv_allmoney, tv_servicemoney2, tv_jiancemoney2, tv_allmoney2,
            tv_fukuan, tv_dashang, tv_pinglun, tv_jiancenum, tv_jiancemoney1;
    FlowLayout flowLayout1;
    ImageView iv_storelogo, iv_carlogo;

    RecyclerView rv_service, rv_other, rv_jiance;
    List<OrderDetailModel.OrderServiceListBean> list_service = new ArrayList<>();
    CommonAdapter<OrderDetailModel.OrderServiceListBean> mAdapter_service;
    List<OrderDetailModel.VOrderGoodsListBean> list_other = new ArrayList<>();
    CommonAdapter<OrderDetailModel.VOrderGoodsListBean> mAdapter_other;
    List<OrderDetailModel.TestingDetailsListBean> list_jiance = new ArrayList<>();
    CommonAdapter<OrderDetailModel.TestingDetailsListBean> mAdapter_jiance;

    int i = 1;
    int payment = 2;//1为支付宝 2为微信支付 3为余额支付
    private static final int SDK_PAY_FLAG = 1;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        MyLogger.i("支付成功" + payResult);
                        showToast("支付成功");
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        MyLogger.i("支付失败" + payResult);
                        showToast("支付失败");
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderdetail);
        mImmersionBar.reset()
                .statusBarColor(R.color.background)
                .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
    }

    @Override
    protected void initView() {
        setSpringViewMore(false);//不需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                Map<String, String> params = new HashMap<>();
                params.put("y_order_id", y_order_id);
                params.put("u_token", localUserInfo.getToken());
                params.put("longitude", localUserInfo.getLongitude());
                params.put("latitude", localUserInfo.getLatitude());
                Request(params);
            }

            @Override
            public void onLoadmore() {
            }
        });
        linearLayout1 = findViewByID_My(R.id.linearLayout1);
        linearLayout2 = findViewByID_My(R.id.linearLayout2);
        linearLayout3 = findViewByID_My(R.id.linearLayout3);
        ll_fuwu = findViewByID_My(R.id.ll_fuwu);
        ll_jiance = findViewByID_My(R.id.ll_jiance);
        ll_beizhu = findViewByID_My(R.id.ll_beizhu);
        ll_other = findViewByID_My(R.id.ll_other);
        ll_heji1 = findViewByID_My(R.id.ll_heji1);
        ll_heji2 = findViewByID_My(R.id.ll_heji2);
        ll_btn = findViewByID_My(R.id.ll_btn);

        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);

        view1 = findViewByID_My(R.id.view1);
        view2 = findViewByID_My(R.id.view2);
        view3 = findViewByID_My(R.id.view3);

        tv_storename = findViewByID_My(R.id.tv_storename);
        tv_addr = findViewByID_My(R.id.tv_addr);
        tv_juli = findViewByID_My(R.id.tv_juli);
        tv_jiecheren = findViewByID_My(R.id.tv_jiecheren);
        tv_wanchengtime = findViewByID_My(R.id.tv_wanchengtime);
        tv_yujiwanchengtime = findViewByID_My(R.id.tv_yujiwanchengtime);
        tv_carname = findViewByID_My(R.id.tv_carname);
        tv_carcontent = findViewByID_My(R.id.tv_carcontent);
        iv_storelogo = findViewByID_My(R.id.iv_storelogo);
        iv_carlogo = findViewByID_My(R.id.iv_carlogo);
        tv_beizhu = findViewByID_My(R.id.tv_beizhu);
        tv_servicenum = findViewByID_My(R.id.tv_servicenum);
        tv_goodsnum = findViewByID_My(R.id.tv_goodsnum);
        tv_allmoney1 = findViewByID_My(R.id.tv_allmoney1);
        tv_servicemoney = findViewByID_My(R.id.tv_servicemoney);
        tv_jiancemoney = findViewByID_My(R.id.tv_jiancemoney);
        tv_allmoney = findViewByID_My(R.id.tv_allmoney);
        tv_servicemoney2 = findViewByID_My(R.id.tv_servicemoney2);
        tv_jiancemoney2 = findViewByID_My(R.id.tv_jiancemoney2);
        tv_allmoney2 = findViewByID_My(R.id.tv_allmoney2);
        tv_fukuan = findViewByID_My(R.id.tv_fukuan);
        tv_dashang = findViewByID_My(R.id.tv_dashang);
        tv_pinglun = findViewByID_My(R.id.tv_pinglun);
        tv_jiancenum = findViewByID_My(R.id.tv_jiancenum);
        tv_jiancemoney1 = findViewByID_My(R.id.tv_jiancemoney1);
        tv_dingdannum = findViewByID_My(R.id.tv_dingdannum);

        flowLayout1 = findViewByID_My(R.id.flowLayout1);
        rv_service = findViewByID_My(R.id.rv_service);
        rv_service.setLayoutManager(new LinearLayoutManager(this));
        rv_other = findViewByID_My(R.id.rv_other);
        rv_other.setLayoutManager(new LinearLayoutManager(this));
        rv_jiance = findViewByID_My(R.id.rv_jiance);
        rv_jiance.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void initData() {
        y_order_id = getIntent().getStringExtra("y_order_id");
        tv_dingdannum.setText("订单编号：" + y_order_id);

//        g_state = getIntent().getIntExtra("g_state", 0);
        requestServer();
    }

    @Override
    public void requestServer() {
        super.requestServer();
//        this.showLoadingPage();
        showProgress(true, getString(R.string.app_loading));
        Map<String, String> params = new HashMap<>();
        params.put("y_order_id", y_order_id);
        params.put("u_token", localUserInfo.getToken());
        params.put("longitude", localUserInfo.getLongitude());
        params.put("latitude", localUserInfo.getLatitude());
        Request(params);
    }

    private void Request(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.OrderDetail, params, headerMap, new CallBackUtil<OrderDetailModel>() {
            @Override
            public OrderDetailModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                showEmptyPage();
                myToast(err);
            }

            @Override
            public void onResponse(OrderDetailModel response) {
                hideProgress();
                model = response;
                /**
                 * 订单信息
                 */
                Glide.with(OrderDetailActivity.this).load(URLs.IMGHOST + model.getStore_info().getPicture())
                        .centerCrop()
                        .placeholder(R.mipmap.loading)//加载站位图
                        .error(R.mipmap.zanwutupian)//加载失败
                        .into(iv_storelogo);
                Glide.with(OrderDetailActivity.this).load(URLs.IMGHOST + model.getUser_sedan_info().getSLogo())
                        .centerCrop()
                        .placeholder(R.mipmap.loading)//加载站位图
                        .error(R.mipmap.zanwutupian)//加载失败
                        .into(iv_carlogo);
                tv_storename.setText(model.getStore_info().getVName());//门店名称
                tv_addr.setText(model.getStore_info().getAddress());//门店地址
                tv_juli.setText(model.getDistance() + "m");//门店距离
                //服务
                if (model.getOrder_service_list().size() > 0) {
                    //标签
                    FlowLayoutAdapter<OrderDetailModel.OrderServiceListBean> flowLayoutAdapter1 =
                            new FlowLayoutAdapter<OrderDetailModel.OrderServiceListBean>
                                    (model.getOrder_service_list()) {
                                @Override
                                public void bindDataToView(FlowLayoutAdapter.ViewHolder holder, int position,
                                                           OrderDetailModel.OrderServiceListBean bean) {
                                    TextView tv = holder.getView(R.id.tv);
                                    tv.setText(bean.getServiceStr());
                                }

                                @Override
                                public void onItemClick(int position, OrderDetailModel.OrderServiceListBean bean) {
                                }

                                @Override
                                public int getItemLayoutID(int position, OrderDetailModel.OrderServiceListBean bean) {
                                    return R.layout.item_fragment3_flowlayout1;
                                }
                            };
                    flowLayout1.setAdapter(flowLayoutAdapter1);
                }

                //接车人
                if (model.getTech_user_info() != null && model.getTech_user_info().getUserName() != null) {
                    tv_jiecheren.setVisibility(View.VISIBLE);
                    tv_jiecheren.setText("接车人：" + model.getTech_user_info().getUserName());
                } else {
                    tv_jiecheren.setVisibility(View.GONE);
                }
                //时间
                switch (model.getOrder_info().getGState()) {
                    case 0:
                        //待接车
                        titleView.setTitle("待接车");
                        tv_jiecheren.setVisibility(View.GONE);
                        ll_heji1.setVisibility(View.VISIBLE);
                        ll_heji2.setVisibility(View.GONE);
                        ll_btn.setVisibility(View.GONE);

                        tv_wanchengtime.setText("预约时间：" + model.getOrder_info().getAppoinTime());//预约时间
                        tv_yujiwanchengtime.setVisibility(View.GONE);
                        break;
                    case 1:
                        //待分配
                        titleView.setTitle("待分配");
                        ll_heji1.setVisibility(View.VISIBLE);
                        ll_heji2.setVisibility(View.GONE);
                        ll_btn.setVisibility(View.GONE);

                        tv_wanchengtime.setText("接车时间：" + model.getTechn_sedan_info().getCreateDate());//预约时间
                        tv_yujiwanchengtime.setVisibility(View.GONE);
                        break;
                    case 2:
                        //待施工
                        titleView.setTitle("待施工");
                        ll_heji1.setVisibility(View.GONE);
                        ll_heji2.setVisibility(View.VISIBLE);
                        ll_btn.setVisibility(View.GONE);


                        tv_wanchengtime.setText("分配时间：" + model.getTechn_sedan_info().getCreateDate());//预约时间
                        tv_yujiwanchengtime.setVisibility(View.GONE);
                        break;
                    case 3:
                        //进行中
                        titleView.setTitle("进行中");
                        ll_heji1.setVisibility(View.GONE);
                        ll_heji2.setVisibility(View.VISIBLE);
                        ll_btn.setVisibility(View.GONE);

                        tv_wanchengtime.setText("施工时间：" + model.getTechn_sedan_info().getCreateDate());//预约时间
                        tv_yujiwanchengtime.setVisibility(View.VISIBLE);
                        tv_yujiwanchengtime.setText("预计完成时间：" + model.getTechn_sedan_info().getEstimateTime());
                        break;
                    case 4:
                        //待复检
                        titleView.setTitle("待复检");
                        ll_heji1.setVisibility(View.GONE);
                        ll_heji2.setVisibility(View.VISIBLE);
                        ll_btn.setVisibility(View.GONE);

                        tv_wanchengtime.setText("完工时间：" + model.getTechn_sedan_info().getCreateDate());//预约时间
                        tv_yujiwanchengtime.setVisibility(View.GONE);
                        break;
                    case 5:
                        //已完工
                        titleView.setTitle("已完工");
                        ll_heji1.setVisibility(View.GONE);
                        ll_heji2.setVisibility(View.VISIBLE);
                        ll_btn.setVisibility(View.VISIBLE);

                        tv_fukuan.setVisibility(View.VISIBLE);
                        tv_dashang.setVisibility(View.VISIBLE);
                        tv_pinglun.setVisibility(View.GONE);

                        tv_wanchengtime.setText("复检时间：" + model.getTechn_sedan_info().getCreateDate());//预约时间
                        tv_yujiwanchengtime.setVisibility(View.GONE);
                        break;
                    case 6:
                        //已提车
                        titleView.setTitle("已提车");
                        ll_heji1.setVisibility(View.GONE);
                        ll_heji2.setVisibility(View.VISIBLE);
                        ll_btn.setVisibility(View.VISIBLE);

                        tv_fukuan.setVisibility(View.GONE);
                        tv_dashang.setVisibility(View.VISIBLE);
                        tv_pinglun.setVisibility(View.VISIBLE);

                        tv_wanchengtime.setText("提车时间：" + model.getTechn_sedan_info().getCreateDate());//预约时间
                        tv_yujiwanchengtime.setVisibility(View.GONE);
                        break;
                }
                //付款按钮
                if (model.getTechn_sedan_info() != null && model.getTechn_sedan_info().getIsPay() > 0) {
                    tv_fukuan.setText("已付款");
                    tv_fukuan.setClickable(false);
                } else {
                    tv_fukuan.setText("付款");
                    tv_fukuan.setClickable(true);
                }

                //打赏按钮
                if (model.getTechn_sedan_info() != null && model.getTechn_sedan_info().getRewardMoney() > 0) {
                    tv_dashang.setText("已打赏（" + model.getTechn_sedan_info().getRewardMoney() + "）");
                    tv_dashang.setTextColor(getResources().getColor(R.color.black3));
                    tv_dashang.setBackgroundResource(R.drawable.yuanjiaobiankuang_5_huise);
                    tv_dashang.setClickable(false);
                } else {
                    tv_dashang.setText("打赏技师");
                    tv_dashang.setTextColor(getResources().getColor(R.color.white));
                    tv_dashang.setBackgroundResource(R.drawable.yuanjiao_5_lanse);
                    tv_dashang.setClickable(true);
                }

                //评论按钮
                if (model.getTechn_sedan_info() != null && model.getTechn_sedan_info().getIsEval() == 1) {
                    tv_pinglun.setText("已评论");
                    tv_pinglun.setTextColor(getResources().getColor(R.color.black3));
                    tv_pinglun.setBackgroundResource(R.drawable.yuanjiaobiankuang_5_huise);
                    tv_pinglun.setClickable(false);
                } else {
                    tv_pinglun.setText("评论");
                    tv_pinglun.setTextColor(getResources().getColor(R.color.blue));
                    tv_pinglun.setBackgroundResource(R.drawable.yuanjiaobiankuang_5_lanse);
                    tv_pinglun.setClickable(true);
                }

                //车辆名称
                tv_carname.setText(model.getUser_sedan_info().getBrandInfo().getGroupName() + "-" + model.getUser_sedan_info().getBrandInfo().getSeriesName());
                //车辆详情
                tv_carcontent.setText(model.getUser_sedan_info().getBrandInfo().getSName());
                tv_servicenum.setText(model.getOrder_service_list().size() + "项");//服务项目个数
                int goodsnum = 0;
                for (OrderDetailModel.VOrderGoodsListBean bean : model.getV_order_goods_list()) {
                    goodsnum += bean.getGNum();
                }
                tv_goodsnum.setText(goodsnum + "");//商品个数
                tv_allmoney1.setText("¥" + (model.getOrder_service_total_price() + model.getV_order_goods_total_price()));//服务和商品总价格

                if (model.getTechn_sedan_info() != null) {
                    tv_beizhu.setText(model.getTechn_sedan_info().getVRemarks());//备注
                } else {
                    tv_beizhu.setText(model.getOrder_info().getcMsg());//备注
                }

                /**
                 * 服务和商品
                 */
                //商品列表
                list_service = response.getOrder_service_list();
                mAdapter_service = new CommonAdapter<OrderDetailModel.OrderServiceListBean>
                        (OrderDetailActivity.this, R.layout.item_orderdetail_service, list_service) {
                    @Override
                    protected void convert(ViewHolder holder, OrderDetailModel.OrderServiceListBean bean, int position) {
                        //信息
                        holder.setText(R.id.servicename, bean.getServiceStr());
                        holder.setText(R.id.servicemoney, "¥" + bean.getGPrice());
                        holder.setText(R.id.serviceallmoney, "¥" + bean.getOrder_service_price());
                        //状态
                        TextView zhuangtai = holder.getView(R.id.zhuangtai);
                        switch (bean.getGState()) {
                            case 1://施工中
                                zhuangtai.setText("施工中");
                                break;
                            case 2://已完成
                                zhuangtai.setText("已完成");
                                break;
                            case 3://已检查
                                zhuangtai.setText("已复检");
                                break;
                            default:
                                zhuangtai.setText("未施工");
                                break;
                        }
                        //商品列表
                        RecyclerView rv_s = holder.getView(R.id.rv_s);
                        LinearLayoutManager llm1 = new LinearLayoutManager(OrderDetailActivity.this);
                        llm1.setOrientation(LinearLayoutManager.VERTICAL);// 设置 recyclerview 布局方式为横向布局
                        rv_s.setLayoutManager(llm1);
                        CommonAdapter<OrderDetailModel.OrderServiceListBean.OrderGoodsListBean> ca_s =
                                new CommonAdapter<OrderDetailModel.OrderServiceListBean.OrderGoodsListBean>
                                        (OrderDetailActivity.this, R.layout.item_orderdetail_goods, bean.getOrder_goods_list()) {
                                    @Override
                                    protected void convert(ViewHolder holder, OrderDetailModel.OrderServiceListBean.OrderGoodsListBean model1, int p) {
                                        ImageView iv = holder.getView(R.id.iv);
                                        Glide.with(OrderDetailActivity.this).load(URLs.IMGHOST + model1.getGoods_info().getGImg())
                                                .centerCrop()
//                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                                                .placeholder(R.mipmap.loading)//加载站位图
                                                .error(R.mipmap.zanwutupian)//加载失败
                                                .into(iv);//加载图片
                                        holder.setText(R.id.goodsname, model1.getGoods_info().getGName());
                                        holder.setText(R.id.goodsguige, "商品规格：" + model1.getGoodsValue());
//                                        num = model1.getGNum();
                                        holder.setText(R.id.goodsnum, "x" + model1.getGNum());
                                        holder.setText(R.id.goodsmoney, "¥" + model1.getGPrice());

                                        //横向图片
                                        List<String> list_img = new ArrayList<>();
                                        for (String s : model1.getGoods_info().getImgArr()) {
                                            list_img.add(URLs.IMGHOST + s);
                                        }
                                        RecyclerView rv = holder.getView(R.id.rv);
                                        LinearLayoutManager llm1 = new LinearLayoutManager(OrderDetailActivity.this);
                                        llm1.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
                                        rv.setLayoutManager(llm1);
                                        CommonAdapter<String> ca = new CommonAdapter<String>
                                                (OrderDetailActivity.this, R.layout.item_img_80_80, list_img) {
                                            @Override
                                            protected void convert(ViewHolder holder, String model, int position) {
                                                ImageView iv = holder.getView(R.id.iv);
                                                Glide.with(OrderDetailActivity.this).load(model)
                                                        .centerCrop()
//                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                                                        .placeholder(R.mipmap.loading)//加载站位图
                                                        .error(R.mipmap.zanwutupian)//加载失败
                                                        .into(iv);//加载图片
                                            }
                                        };
                                        ca.setOnItemClickListener(new OnItemClickListener() {
                                            @Override
                                            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                                PhotoShowDialog photoShowDialog = new PhotoShowDialog(OrderDetailActivity.this, list_img, i);
                                                photoShowDialog.show();
                                            }

                                            @Override
                                            public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                                return false;
                                            }
                                        });
                                        rv.setAdapter(ca);

                                    }
                                };
                        rv_s.setAdapter(ca_s);

                    }
                };
                rv_service.setAdapter(mAdapter_service);

                /**
                 * 其他商品
                 */
                list_other = response.getV_order_goods_list();
                if (list_other.size() > 0) {
                    ll_other.setVisibility(View.VISIBLE);
                    mAdapter_other = new CommonAdapter<OrderDetailModel.VOrderGoodsListBean>
                            (OrderDetailActivity.this, R.layout.item_orderdetail_goods, list_other) {
                        @Override
                        protected void convert(ViewHolder holder, OrderDetailModel.VOrderGoodsListBean model1, int p) {
                            ImageView iv = holder.getView(R.id.iv);
                            Glide.with(OrderDetailActivity.this).load(URLs.IMGHOST + model1.getGoods_info().getGImg())
                                    .centerCrop()
//                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                                    .placeholder(R.mipmap.loading)//加载站位图
                                    .error(R.mipmap.zanwutupian)//加载失败
                                    .into(iv);//加载图片
                            holder.setText(R.id.goodsname, model1.getGoods_info().getGName());
                            holder.setText(R.id.goodsguige, "商品规格：" + model1.getGoodsValue());
//                                        num = model1.getGNum();
                            holder.setText(R.id.goodsnum, "x" + model1.getGNum());
                            holder.setText(R.id.goodsmoney, "¥" + model1.getGPrice());

                            //横向图片
                            List<String> list_img = new ArrayList<>();
                            for (String s : model1.getGoods_info().getImgArr()) {
                                list_img.add(URLs.IMGHOST + s);
                            }
                            RecyclerView rv = holder.getView(R.id.rv);
                            LinearLayoutManager llm1 = new LinearLayoutManager(OrderDetailActivity.this);
                            llm1.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
                            rv.setLayoutManager(llm1);
                            CommonAdapter<String> ca = new CommonAdapter<String>
                                    (OrderDetailActivity.this, R.layout.item_img_80_80, list_img) {
                                @Override
                                protected void convert(ViewHolder holder, String model, int position) {
                                    ImageView iv = holder.getView(R.id.iv);
                                    Glide.with(OrderDetailActivity.this).load(model)
                                            .centerCrop()
//                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                                            .placeholder(R.mipmap.loading)//加载站位图
                                            .error(R.mipmap.zanwutupian)//加载失败
                                            .into(iv);//加载图片
                                }
                            };
                            ca.setOnItemClickListener(new OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                    PhotoShowDialog photoShowDialog = new PhotoShowDialog(OrderDetailActivity.this, list_img, i);
                                    photoShowDialog.show();
                                }

                                @Override
                                public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                    return false;
                                }
                            });
                            rv.setAdapter(ca);
                        }
                    };
                    rv_other.setAdapter(mAdapter_other);
                } else {
                    ll_other.setVisibility(View.GONE);
                }

                /**
                 * 检测项目
                 */
                if (model.getTesting_details_list() != null) {
                    list_jiance = model.getTesting_details_list();
                    tv_jiancenum.setText(list_jiance.size() + "项");
                    tv_jiancemoney1.setText("¥" + model.getTesting_details_total_price());
                    mAdapter_jiance = new CommonAdapter<OrderDetailModel.TestingDetailsListBean>(OrderDetailActivity.this,
                            R.layout.item_orderdetail_jiance, list_jiance) {
                        @Override
                        protected void convert(ViewHolder holder, OrderDetailModel.TestingDetailsListBean jianceBean, int position) {
                            holder.setText(R.id.tv_time, "提交时间:" + jianceBean.getCreateDate());
                            holder.setText(R.id.tv_title, jianceBean.getVTitle());
                            holder.setText(R.id.tv_money, jianceBean.getVPrice() + "");
                            if (jianceBean.getIsReplace() == 0) {
                                holder.setText(R.id.tv_type, "维修");
                            } else {
                                holder.setText(R.id.tv_type, "更换");
                            }
                            //横向图片
//                        String[] strArr = model1.getGoods_info().getImgStr().split("\\|\\|");
                            List<String> list_img = new ArrayList<>();
                            for (String s : jianceBean.getImgArr()) {
                                if (!s.equals("")) {
                                    list_img.add(URLs.IMGHOST + s);
                                }
                            }
                            RecyclerView rv = holder.getView(R.id.rv);
                            LinearLayoutManager llm1 = new LinearLayoutManager(OrderDetailActivity.this);
                            llm1.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
                            rv.setLayoutManager(llm1);
                            CommonAdapter<String> ca = new CommonAdapter<String>
                                    (OrderDetailActivity.this, R.layout.item_img_80_80, list_img) {
                                @Override
                                protected void convert(ViewHolder holder, String model, int position) {
                                    ImageView iv = holder.getView(R.id.iv);
                                    Glide.with(OrderDetailActivity.this).load(model)
                                            .centerCrop()
//                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                                            .placeholder(R.mipmap.loading)//加载站位图
                                            .error(R.mipmap.zanwutupian)//加载失败
                                            .into(iv);//加载图片
                                }
                            };
                            ca.setOnItemClickListener(new OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                    PhotoShowDialog photoShowDialog = new PhotoShowDialog(OrderDetailActivity.this, list_img, i);
                                    photoShowDialog.show();
                                }

                                @Override
                                public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                    return false;
                                }
                            });
                            rv.setAdapter(ca);

                            TextView tv_btn = holder.getView(R.id.tv_btn);
                            TextView tv_btn_fangqi = holder.getView(R.id.tv_btn_fangqi);
                            if (jianceBean.getIsConfirm() == 0) {//待确认
                                tv_btn.setText("确认报告");
                                tv_btn.setBackgroundResource(R.drawable.yuanjiao_5_lanse);
                                tv_btn.setTextColor(getResources().getColor(R.color.white));
                                tv_btn.setClickable(true);
                                tv_btn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        showToast("确认报告吗？", "确认", "取消", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                dialog.dismiss();
                                                Map<String, String> params = new HashMap<>();
                                                params.put("u_token", localUserInfo.getToken());
                                                params.put("y_testing_details_id", jianceBean.getYTestingDetailsId());
                                                RequestConfirm(params);
                                            }
                                        }, new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                dialog.dismiss();
                                            }
                                        });

                                    }
                                });
                                tv_btn_fangqi.setVisibility(View.VISIBLE);
                                tv_btn_fangqi.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        showToast("确认放弃吗？", "确认", "取消", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                dialog.dismiss();
                                                Map<String, String> params = new HashMap<>();
                                                params.put("u_token", localUserInfo.getToken());
                                                params.put("y_testing_details_id", jianceBean.getYTestingDetailsId());
                                                RequestDeleteProject(params);
                                            }
                                        }, new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                dialog.dismiss();
                                            }
                                        });
                                    }
                                });
                            } else {
                                tv_btn.setText("已确认");
                                tv_btn.setBackgroundResource(R.drawable.yuanjiaobiankuang_5_huise);
                                tv_btn.setTextColor(getResources().getColor(R.color.black3));
                                tv_btn.setClickable(false);
                                tv_btn_fangqi.setVisibility(View.GONE);
                            }

                        }
                    };
                    rv_jiance.setAdapter(mAdapter_jiance);
                }

                /**
                 * 统计
                 */
                tv_servicemoney.setText("¥" + (model.getOrder_service_total_price() + model.getV_order_goods_total_price()));//商品和服务总价格

                tv_jiancemoney.setText("¥" + model.getTesting_total_price());//检测总价格
                tv_allmoney.setText("¥" + model.getOrder_price());//服务和商品总价格

                tv_servicemoney2.setText("¥" + (model.getOrder_service_total_price() + model.getV_order_goods_total_price()));//商品和服务总价格
                tv_jiancemoney2.setText("¥" + model.getTesting_total_price());//检测总价格
                tv_allmoney2.setText("¥" + model.getOrder_price());//服务和商品总价格

            }
        });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.linearLayout1:
                type = 1;
                changeUI();
                break;
            case R.id.linearLayout2:
                type = 2;
                changeUI();
                break;
            case R.id.linearLayout3:
                type = 3;
                changeUI();
                break;
            case R.id.iv_call:
                //拨打电话
                showToast("确认拨打 " + model.getStore_info().getPhone() + " 吗？", "确认", "取消",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                //创建打电话的意图
                                Intent intent = new Intent();
                                //设置拨打电话的动作
                                intent.setAction(Intent.ACTION_CALL);//直接拨出电话
//                               intent.setAction(Intent.ACTION_DIAL);//只调用拨号界面，不拨出电话
                                //设置拨打电话的号码
                                intent.setData(Uri.parse("tel:" + model.getStore_info().getPhone()));
                                //开启打电话的意图
                                startActivity(intent);
                            }
                        }, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                break;
            case R.id.iv_message:
                String url = URLs.KFHOST + "/#/pages/chetu-kf/chetu-kf?token=" + localUserInfo.getToken() +
                        "&kf_userHash=" + model.getOrder_info().getKf_user_info().getUserHash() +
                        "&nickName=" + model.getOrder_info().getKf_user_info().getUserName() +
                        "&headerPic=" + URLs.IMGHOST + model.getOrder_info().getKf_user_info().getHeadPortrait();
                Bundle bundle = new Bundle();
                bundle.putString("url", url);
                CommonUtil.gotoActivityWithData(OrderDetailActivity.this, WebContentActivity.class, bundle, false);
                break;
            case R.id.tv_quxiao:
                //取消
                showToast("确认取消该订单吗？", "确认", "取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        showProgress(true, "正在删除订单，请稍候...");
                        Map<String, String> params = new HashMap<>();
                        params.put("u_token", localUserInfo.getToken());
                        params.put("y_order_id", model.getOrder_info().getYOrderId());
                        RequestDelete(params);
                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                break;
            case R.id.tv_fukuan:
                //付款
                showToast("确认付款吗？", "确认", "取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        showToast("请技师端确认收款");
                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                break;

            case R.id.tv_dashang:
                //打赏
                BaseDialog dialog1 = new BaseDialog(OrderDetailActivity.this);
                dialog1.contentView(R.layout.dialog_dashang)
                        .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT))
                        .animType(BaseDialog.AnimInType.BOTTOM)
                        .canceledOnTouchOutside(true)
                        .gravity(Gravity.BOTTOM)
                        .dimAmount(0.7f)
                        .show();
                EditText et_money = dialog1.findViewById(R.id.et_money);
                ImageView ic_wechat = dialog1.findViewById(R.id.ic_wechat);
                ImageView ic_ali = dialog1.findViewById(R.id.ic_ali);
                List<String> abcList = new ArrayList<>();
                abcList.add("1.88");
                abcList.add("8.88");
                abcList.add("18.8");
                abcList.add("88.8");
                abcList.add("98.8");
                RecyclerView rv_carnum1 = dialog1.findViewById(R.id.rv_carnum);
                rv_carnum1.setLayoutManager(new GridLayoutManager(this, 5));
                CommonAdapter<String> adapter_abc = new CommonAdapter<String>
                        (OrderDetailActivity.this, R.layout.item_textview_yuanjiaobiankuang, abcList) {
                    @Override
                    protected void convert(ViewHolder holder, String model, int position) {
                        TextView tv = holder.getView(R.id.tv);
                        tv.setText("¥" + model);
                        if (i == position) {
                            et_money.setText("" + model);
                            tv.setTextColor(getResources().getColor(R.color.red));
                            tv.setBackgroundResource(R.drawable.yuanjiaobiankuang_5_hongse);
                        } else {
                            tv.setTextColor(getResources().getColor(R.color.black));
                            tv.setBackgroundResource(R.drawable.yuanjiaobiankuang_5_huise);
                        }
                    }
                };
                adapter_abc.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int item) {
                        i = item;
//                        et_money.setText(abcList.get(item));
                        adapter_abc.notifyDataSetChanged();
                    }

                    @Override
                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        return false;
                    }
                });
                rv_carnum1.setAdapter(adapter_abc);
                dialog1.findViewById(R.id.ll_wechat).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        payment = 2;//1为支付宝 2为微信支付 3为余额支付
                        ic_wechat.setImageResource(R.mipmap.ic_xuanzhong);
                        ic_ali.setImageResource(R.mipmap.ic_weixuan);
                    }
                });
                dialog1.findViewById(R.id.ll_ali).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        payment = 1;//1为支付宝 2为微信支付 3为余额支付
                        ic_wechat.setImageResource(R.mipmap.ic_weixuan);
                        ic_ali.setImageResource(R.mipmap.ic_xuanzhong);
                    }
                });
                dialog1.findViewById(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!et_money.getText().toString().trim().equals("")) {
                            CommonUtil.hideInput(OrderDetailActivity.this);
                            dialog1.dismiss();
                            //获取支付数据
                            showProgress(true, "正在获取支付数据，请稍候...");
                            Map<String, String> params = new HashMap<>();
                            params.put("u_token", localUserInfo.getToken());
                            params.put("payment", payment + "");
                            params.put("i_classify", "1");//1 为打赏
                            params.put("code_str", model.getTech_user_info().getUserHash());//技师会员 标识
                            params.put("money", et_money.getText().toString().trim());
                            params.put("y_techn_sedan_id", model.getOrder_info().getYTechnSedanId());
                            RequestPay(params);
                        } else {
                            myToast("请输入打赏金额");
                        }

                    }
                });
                break;
            case R.id.tv_pinglun:
                //评论
                Bundle bundle1 = new Bundle();
                bundle1.putSerializable("OrderDetailModel", model);
                CommonUtil.gotoActivityWithData(OrderDetailActivity.this, AddPingLunActivity.class, bundle1, false);
                break;
        }
    }

    private void changeUI() {
        switch (type) {
            case 1:
                //服务
                textView1.setTextColor(getResources().getColor(R.color.blue));
                view1.setVisibility(View.VISIBLE);
                textView2.setTextColor(getResources().getColor(R.color.black));
                view2.setVisibility(View.INVISIBLE);
                textView3.setTextColor(getResources().getColor(R.color.black));
                view3.setVisibility(View.INVISIBLE);
                ll_fuwu.setVisibility(View.VISIBLE);
                ll_jiance.setVisibility(View.GONE);
                ll_beizhu.setVisibility(View.GONE);
                break;
            case 2:
                //检测
                textView1.setTextColor(getResources().getColor(R.color.black));
                view1.setVisibility(View.INVISIBLE);
                textView2.setTextColor(getResources().getColor(R.color.blue));
                view2.setVisibility(View.VISIBLE);
                textView3.setTextColor(getResources().getColor(R.color.black));
                view3.setVisibility(View.INVISIBLE);
                ll_fuwu.setVisibility(View.GONE);
                ll_jiance.setVisibility(View.VISIBLE);
                ll_beizhu.setVisibility(View.GONE);
                break;
            case 3:
                //备注
                textView1.setTextColor(getResources().getColor(R.color.black));
                view1.setVisibility(View.INVISIBLE);
                textView2.setTextColor(getResources().getColor(R.color.black));
                view2.setVisibility(View.INVISIBLE);
                textView3.setTextColor(getResources().getColor(R.color.blue));
                view3.setVisibility(View.VISIBLE);
                ll_fuwu.setVisibility(View.GONE);
                ll_jiance.setVisibility(View.GONE);
                ll_beizhu.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    protected void updateView() {
        titleView.setBackground(R.color.background);
        /*switch (g_state) {
            case 0:
                //待接车
                titleView.setTitle("待接车");
                tv_jiecheren.setVisibility(View.GONE);
                ll_heji1.setVisibility(View.VISIBLE);
                ll_heji2.setVisibility(View.GONE);
                ll_btn.setVisibility(View.GONE);
                break;
            case 1:
                //待分配
                titleView.setTitle("待分配");
                ll_heji1.setVisibility(View.VISIBLE);
                ll_heji2.setVisibility(View.GONE);
                ll_btn.setVisibility(View.GONE);
                break;
            case 2:
                //待施工
                titleView.setTitle("待施工");
                ll_heji1.setVisibility(View.GONE);
                ll_heji2.setVisibility(View.VISIBLE);
                ll_btn.setVisibility(View.VISIBLE);
                tv_fukuan.setVisibility(View.GONE);
                tv_dashang.setVisibility(View.VISIBLE);
                tv_pinglun.setVisibility(View.GONE);
                break;
            case 3:
                //进行中
                titleView.setTitle("进行中");
                ll_heji1.setVisibility(View.GONE);
                ll_heji2.setVisibility(View.VISIBLE);
                ll_btn.setVisibility(View.VISIBLE);
                tv_fukuan.setVisibility(View.GONE);
                tv_dashang.setVisibility(View.VISIBLE);
                tv_pinglun.setVisibility(View.GONE);
                break;
            case 4:
                //待复检
                titleView.setTitle("待复检");
                ll_heji1.setVisibility(View.GONE);
                ll_heji2.setVisibility(View.VISIBLE);
                ll_btn.setVisibility(View.VISIBLE);
                tv_fukuan.setVisibility(View.GONE);
                tv_dashang.setVisibility(View.VISIBLE);
                tv_pinglun.setVisibility(View.GONE);
                break;
            case 5:
                //已完工
                titleView.setTitle("已完工");
                ll_heji1.setVisibility(View.GONE);
                ll_heji2.setVisibility(View.VISIBLE);
                ll_btn.setVisibility(View.VISIBLE);
                tv_fukuan.setVisibility(View.VISIBLE);
                tv_dashang.setVisibility(View.VISIBLE);
                tv_pinglun.setVisibility(View.GONE);
                break;
            case 6:
                //已提车
                titleView.setTitle("已提车");
                ll_heji1.setVisibility(View.GONE);
                ll_heji2.setVisibility(View.VISIBLE);
                ll_btn.setVisibility(View.VISIBLE);
                tv_fukuan.setVisibility(View.VISIBLE);
                tv_dashang.setVisibility(View.VISIBLE);
                tv_pinglun.setVisibility(View.VISIBLE);
                break;
        }*/
    }

    /**
     * 确认检测项目
     *
     * @param params
     */
    private void RequestConfirm(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.ConfirmProject, params, headerMap, new CallBackUtil() {
            @Override
            public Object onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                if (!err.equals("")) {
                    showToast(err);
                }
            }

            @Override
            public void onResponse(Object response) {
                hideProgress();
                myToast("确认成功");
                requestServer();
            }
        });
    }

    /**
     * 获取支付数据
     *
     * @param params
     */
    private void RequestPay(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Pay, params, headerMap, new CallBackUtil<PayModel>() {
            @Override
            public PayModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(PayModel response) {
                hideProgress();
                if (payment == 2) {
                    //微信
                    IWXAPI api = WXAPIFactory.createWXAPI(OrderDetailActivity.this, "wx7ab80a19389dbb09", false);//填写自己的APPID
                    api.registerApp("wx7ab80a19389dbb09");//填写自己的APPID，注册本身APP
                    PayReq req = new PayReq();//PayReq就是订单信息对象
                    //给req对象赋值
                    req.appId = response.getWechat().getAppid();//APPID
                    req.partnerId = response.getWechat().getPartnerid();//    商户号
                    req.prepayId = response.getWechat().getPrepayid();//  预付款ID
                    req.nonceStr = response.getWechat().getNoncestr();//随机数
                    req.timeStamp = response.getWechat().getTimestamp();//时间戳
                    req.packageValue = "Sign=WXPay";//固定值Sign=WXPay
                    req.sign = response.getWechat().getSign();//签名
                    api.sendReq(req);//将订单信息对象发送给微信服务器，即发送支付请求
                } else {
                    //弹出支付宝
                    Runnable payRunnable = new Runnable() {
                        @Override
                        public void run() {
                            PayTask alipay = new PayTask(OrderDetailActivity.this);
                            Map<String, String> result = alipay.payV2(response.getOrderStr(), true);
                            Message msg = new Message();
                            msg.what = SDK_PAY_FLAG;
                            msg.obj = result;
                            mHandler.sendMessage(msg);
                        }
                    };
                    // 必须异步调用
                    Thread payThread = new Thread(payRunnable);
                    payThread.start();
                }
            }
        });
    }

    /**
     * 删除订单
     *
     * @param params
     */
    private void RequestDelete(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.OrderDelete, params, headerMap, new CallBackUtil<String>() {
            @Override
            public String onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(String response) {
                hideProgress();
                myToast("删除成功");
                finish();
            }
        });
    }
    /**
     * 删除项目
     *
     * @param params
     */
    private void RequestDeleteProject(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.DeleteProject, params, headerMap, new CallBackUtil<String>() {
            @Override
            public String onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(String response) {
                hideProgress();
                myToast("放弃成功");
                requestServer();
            }
        });
    }


}
