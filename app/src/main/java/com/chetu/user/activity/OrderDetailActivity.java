package com.chetu.user.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chetu.user.R;
import com.chetu.user.base.BaseActivity;
import com.chetu.user.model.OrderDetailModel;
import com.chetu.user.net.URLs;
import com.chetu.user.okhttp.CallBackUtil;
import com.chetu.user.okhttp.OkhttpUtil;
import com.chetu.user.utils.CommonUtil;
import com.cy.cyflowlayoutlibrary.FlowLayout;
import com.cy.cyflowlayoutlibrary.FlowLayoutAdapter;
import com.liaoinstan.springview.widget.SpringView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    String y_order_id = "", longitude = "", latitude = "";
    OrderDetailModel model;

    private LinearLayout linearLayout1, linearLayout2, linearLayout3, ll_fuwu, ll_jiance, ll_beizhu;
    private TextView textView1, textView2, textView3;
    private View view1, view2, view3;

    TextView tv_storename, tv_addr, tv_juli, tv_jiecheren, tv_wanchengtime, tv_carname, tv_carcontent,
            tv_beizhu, tv_servicenum, tv_goodsnum, tv_allmoney1,
            tv_servicemoney, tv_jiancemoney, tv_allmoney;
    FlowLayout flowLayout1;
    ImageView iv_storelogo, iv_carlogo;

    RecyclerView rv_service, rv_other;
    List<OrderDetailModel.OrderServiceListBean> list_service = new ArrayList<>();
    CommonAdapter<OrderDetailModel.OrderServiceListBean> mAdapter_service;
    List<OrderDetailModel.VOrderGoodsListBean> list_other = new ArrayList<>();
    CommonAdapter<OrderDetailModel.VOrderGoodsListBean> mAdapter_other;

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

        flowLayout1 = findViewByID_My(R.id.flowLayout1);
        rv_service = findViewByID_My(R.id.rv_service);
        rv_service.setLayoutManager(new LinearLayoutManager(this));
        rv_other = findViewByID_My(R.id.rv_other);
        rv_other.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void initData() {
        y_order_id = getIntent().getStringExtra("y_order_id");
        g_state = getIntent().getIntExtra("g_state", 0);
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
                /*if (model.getTechn_sedan_info() != null) {
                    tv_name.setVisibility(View.VISIBLE);
                    tv_name.setText("接车人：" + model.get);
                } else {
                    tv_name.setVisibility(View.GONE);
                }*/
                //时间
                tv_wanchengtime.setText("预约时间：" + model.getOrder_info().getAppoinTime());

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

//                tv_beizhu.setText(model.);//备注

                /**
                 * 服务和商品
                 */
                //商品列表
                list_service = response.getOrder_service_list();
                mAdapter_service = new CommonAdapter<OrderDetailModel.OrderServiceListBean>
                        (OrderDetailActivity.this, R.layout.item_orderdetail_service, list_service) {
                    @Override
                    protected void convert(ViewHolder holder, OrderDetailModel.OrderServiceListBean model, int position) {
                        //信息
                        /*holder.setText(R.id.title, model.getStore_service_info().getYStateValue());
                        holder.setText(R.id.money, "¥" + model.getStore_service_info().getSPrice());
                        //商品列表
                        RecyclerView rv = holder.getView(R.id.rv);
                        LinearLayoutManager llm1 = new LinearLayoutManager(OrderDetailActivity.this);
                        llm1.setOrientation(LinearLayoutManager.VERTICAL);// 设置 recyclerview 布局方式为横向布局
                        rv.setLayoutManager(llm1);
//                        int[] num = new int[model.getGoods_cart_list().size()];
                        CommonAdapter<ConfirmOrderModel.ServiceListBean.GoodsCartListBean> ca =
                                new CommonAdapter<ConfirmOrderModel.ServiceListBean.GoodsCartListBean>
                                        (OrderDetailActivity.this, R.layout.item_confirmorder_goods, model.getGoods_cart_list()) {
                                    @Override
                                    protected void convert(ViewHolder holder, ConfirmOrderModel.ServiceListBean.GoodsCartListBean model1, int p) {
                                        ImageView iv = holder.getView(R.id.iv);
                                        Glide.with(OrderDetailActivity.this).load(URLs.IMGHOST + model1.getGoods_info().getGImg())
//                            .centerCrop()
//                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                                                .placeholder(R.mipmap.loading)//加载站位图
                                                .error(R.mipmap.zanwutupian)//加载失败
                                                .into(iv);//加载图片
                                        holder.setText(R.id.tv_title, model1.getGoods_info().getGName());
                                        holder.setText(R.id.tv_tab, model1.getGoodsValue());
//                                        num = model1.getGNum();
                                        holder.setText(R.id.tv_num, model1.getGNum() + "");
                                        holder.setText(R.id.tv_money, "" + model1.getVPrice() * model1.getGNum());

                                    }
                                };
                        rv.setAdapter(ca);*/

                    }
                };
                rv_service.setAdapter(mAdapter_service);

                //其他商品
                list_other = response.getV_order_goods_list();

                mAdapter_other = new CommonAdapter<OrderDetailModel.VOrderGoodsListBean>
                        (OrderDetailActivity.this, R.layout.item_confirmorder_goods, list_other) {
                    @Override
                    protected void convert(ViewHolder holder, OrderDetailModel.VOrderGoodsListBean model1, int p) {
                        /*ImageView iv = holder.getView(R.id.iv);
                        Glide.with(OrderDetailActivity.this).load(URLs.IMGHOST + model1.getGoods_info().getGImg())
//                            .centerCrop()
//                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                                .placeholder(R.mipmap.loading)//加载站位图
                                .error(R.mipmap.zanwutupian)//加载失败
                                .into(iv);//加载图片
                        holder.setText(R.id.tv_title, model1.getGoods_info().getGName());
                        holder.setText(R.id.tv_tab, model1.getGoodsValue());
//                                        num = model1.getGNum();
                        holder.setText(R.id.tv_num, model1.getGNum() + "");
                        holder.setText(R.id.tv_money, "" + model1.getVPrice() * model1.getGNum());*/
                    }
                };
                rv_other.setAdapter(mAdapter_other);

                /**
                 * 统计
                 */
                tv_servicemoney.setText("¥" + (model.getOrder_service_total_price() + model.getV_order_goods_total_price()));//商品和服务总价格
//                tv_jiancemoney.setText("¥" + model.get);//检测总价格
                tv_allmoney.setText("¥" + model.getOrder_price());//服务和商品总价格


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
                String url = URLs.KFHOST+"/#/pages/chetu-kf/chetu-kf?token="+localUserInfo.getToken()+
                        "&kf_userHash="+model.getOrder_info().getKf_user_info().getUserHash();
                Bundle bundle = new Bundle();
                bundle.putString("url", url);
                CommonUtil.gotoActivityWithData(OrderDetailActivity.this, WebContentActivity.class, bundle, false);
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
        switch (g_state) {
            case 0:
                //待接车
                titleView.setTitle("待接车");
                tv_jiecheren.setVisibility(View.GONE);
                break;
            case 1:
                //待分配
                titleView.setTitle("待分配");
                break;
            case 2:
                //待施工
                titleView.setTitle("待施工");
                break;
            case 3:
                //进行中
                titleView.setTitle("进行中");
                break;
            case 4:
                //待复检
                titleView.setTitle("待复检");
                break;
            case 5:
                //已完工
                titleView.setTitle("已完工");
                break;
            case 6:
                //已提车
                titleView.setTitle("已提车");
                break;
        }
    }
}
