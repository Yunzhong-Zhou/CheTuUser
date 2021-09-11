package com.chetu.user.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bumptech.glide.Glide;
import com.chetu.user.R;
import com.chetu.user.base.BaseActivity;
import com.chetu.user.model.ConfirmOrderModel;
import com.chetu.user.model.ConfirmOrderNumModel;
import com.chetu.user.net.URLs;
import com.chetu.user.okhttp.CallBackUtil;
import com.chetu.user.okhttp.OkhttpUtil;
import com.chetu.user.utils.CommonUtil;
import com.liaoinstan.springview.widget.SpringView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by zyz on 2020/7/3.
 * 确认订单
 */
public class ConfirmOrderActivity extends BaseActivity {
    ConfirmOrderModel confirmOrderModel;
    String y_user_sedan_id = "", y_store_id = "", longitude = "", latitude = "", appoin_time = "",
            is_pick = "0", is_delivery = "0", delivery_time = "", delivery_address = "", send_address = "";
    //车辆信息
    ImageView imageView1;
    TextView tv_carname, tv_carnum, tv_cardetail, tv_name, tv_phone;
    //店铺信息
    ImageView imageView2;
    TextView tv_title, tv_addr, tv_juli;
    //订单列表
    RecyclerView recyclerView;
    List<ConfirmOrderModel.ServiceListBean> list = new ArrayList<>();
    CommonAdapter<ConfirmOrderModel.ServiceListBean> mAdapter;

    //其他商品
    RecyclerView rv_other;
    List<ConfirmOrderModel.GoodsCartListBean> list_other = new ArrayList<>();
    CommonAdapter<ConfirmOrderModel.GoodsCartListBean> mAdapter_other;

    //接车到店、送车到家
    LinearLayout ll_jiechedaodian, ll_yuyuedaodian, ll_songchedaojia, ll_songcheaddr, ll_jiechetime, ll_jiecheaddr, ll_yuyuetime,ll_daodianpaidui;
    ImageView iv_jiechedaodian, iv_yuyuedaodian, iv_songchedaojia,iv_daodianpaidui;
    TextView tv_jiechetime, tv_yuyuetime, tv_jiecheaddr, tv_songcheaddr;
    TimePickerView pvTime1;
    //下单
    boolean isYuYue = false;
    TextView tv_time, tv_money, tv_yuyuedaodian, tv_daodianshigong;

    EditText  et_beizhu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmorder);
        mImmersionBar.reset()
                .statusBarColor(R.color.blue)
                .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题
//                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestServer();
    }

    @Override
    protected void initView() {
        setSpringViewMore(false);//不需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
//                page = 0;
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
        //车辆信息
        imageView1 = findViewByID_My(R.id.imageView1);
        tv_carname = findViewByID_My(R.id.tv_carname);
        tv_carnum = findViewByID_My(R.id.tv_carnum);
        tv_cardetail = findViewByID_My(R.id.tv_cardetail);
        tv_name = findViewByID_My(R.id.tv_name);
        tv_phone = findViewByID_My(R.id.tv_phone);
        if (!localUserInfo.getCarname().equals("")) {
            y_user_sedan_id = localUserInfo.getCarid();
            tv_carname.setText(localUserInfo.getCarname());
            tv_carnum.setText(localUserInfo.getCarnum());
            tv_cardetail.setText(localUserInfo.getCardetail());
            Glide.with(this).load(URLs.IMGHOST + localUserInfo.getCarlogo())
                    .centerCrop()
                    .into(imageView1);//加载图片
        }
        tv_name.setText(localUserInfo.getNickname());
        tv_phone.setText(localUserInfo.getPhonenumber());

        //店铺信息
        imageView2 = findViewByID_My(R.id.imageView2);
        tv_title = findViewByID_My(R.id.tv_title);
        tv_addr = findViewByID_My(R.id.tv_addr);
        tv_juli = findViewByID_My(R.id.tv_juli);

        //接车到店、送车到家
        ll_daodianpaidui = findViewByID_My(R.id.ll_daodianpaidui);
        iv_daodianpaidui = findViewByID_My(R.id.iv_daodianpaidui);
        ll_jiechedaodian = findViewByID_My(R.id.ll_jiechedaodian);
        ll_yuyuedaodian = findViewByID_My(R.id.ll_yuyuedaodian);
        ll_songchedaojia = findViewByID_My(R.id.ll_songchedaojia);
        iv_jiechedaodian = findViewByID_My(R.id.iv_jiechedaodian);
        iv_yuyuedaodian = findViewByID_My(R.id.iv_yuyuedaodian);
        iv_songchedaojia = findViewByID_My(R.id.iv_songchedaojia);
        ll_songcheaddr = findViewByID_My(R.id.ll_songcheaddr);

        ll_jiechetime = findViewByID_My(R.id.ll_jiechetime);
        tv_yuyuetime = findViewByID_My(R.id.tv_yuyuetime);
        ll_jiecheaddr = findViewByID_My(R.id.ll_jiecheaddr);
        tv_jiechetime = findViewByID_My(R.id.tv_jiechetime);
        tv_jiecheaddr = findViewByID_My(R.id.tv_jiecheaddr);
        tv_songcheaddr = findViewByID_My(R.id.tv_songcheaddr);

        ll_yuyuetime = findViewByID_My(R.id.ll_yuyuetime);

        //订单列表
        recyclerView = findViewByID_My(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //其他商品
        rv_other = findViewByID_My(R.id.rv_other);
        rv_other.setLayoutManager(new LinearLayoutManager(this));

        //下单
        tv_time = findViewByID_My(R.id.tv_time);
        tv_money = findViewByID_My(R.id.tv_money);
        tv_yuyuedaodian = findViewByID_My(R.id.tv_yuyuedaodian);
        tv_daodianshigong = findViewByID_My(R.id.tv_daodianshigong);

        et_beizhu = findViewByID_My(R.id.et_beizhu);

    }

    @Override
    protected void initData() {
        y_store_id = getIntent().getStringExtra("y_store_id");
        longitude = getIntent().getStringExtra("longitude");
        latitude = getIntent().getStringExtra("latitude");

    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
//        page = 0;
        //获取订单信息
//        showProgress(true, getString(R.string.app_loading));
        Map<String, String> params = new HashMap<>();
        params.put("u_token", localUserInfo.getToken());
        params.put("longitude", longitude);
        params.put("latitude", latitude);
        params.put("y_store_id", y_store_id);
        Request(params);
    }

    /**
     * 订单信息
     *
     * @param params
     */
    private void Request(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.ConfirmOrder, params, headerMap, new CallBackUtil<ConfirmOrderModel>() {
            @Override
            public ConfirmOrderModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                showEmptyPage();
                myToast(err);
            }

            @Override
            public void onResponse(ConfirmOrderModel response) {
                hideProgress();
                showContentPage();
                confirmOrderModel = response;

                Glide.with(ConfirmOrderActivity.this)
                        .load(URLs.IMGHOST + response.getStore_info().getPicture())
                        .centerCrop()
                        .placeholder(R.mipmap.loading)//加载站位图
                        .error(R.mipmap.zanwutupian)//加载失败
                        .into(imageView2);//加载图片
                tv_title.setText(response.getStore_info().getVName());
                tv_addr.setText(response.getStore_info().getAddress());
                tv_juli.setText(response.getStore_info().getDistance() + "m");

                //商品列表
                list = response.getService_list();
                mAdapter = new CommonAdapter<ConfirmOrderModel.ServiceListBean>
                        (ConfirmOrderActivity.this, R.layout.item_confirmorder, list) {
                    @Override
                    protected void convert(ViewHolder holder, ConfirmOrderModel.ServiceListBean model, int position) {
                        //信息
                        holder.setText(R.id.title, model.getStore_service_info().getYStateValue());
                        holder.setText(R.id.money, "¥" + model.getStore_service_info().getSPrice());
                        //商品列表
                        RecyclerView rv = holder.getView(R.id.rv);
                        LinearLayoutManager llm1 = new LinearLayoutManager(ConfirmOrderActivity.this);
                        llm1.setOrientation(LinearLayoutManager.VERTICAL);// 设置 recyclerview 布局方式为横向布局
                        rv.setLayoutManager(llm1);
//                        int[] num = new int[model.getGoods_cart_list().size()];
                        CommonAdapter<ConfirmOrderModel.ServiceListBean.GoodsCartListBean> ca =
                                new CommonAdapter<ConfirmOrderModel.ServiceListBean.GoodsCartListBean>
                                        (ConfirmOrderActivity.this, R.layout.item_confirmorder_goods, model.getGoods_cart_list()) {
                                    @Override
                                    protected void convert(ViewHolder holder, ConfirmOrderModel.ServiceListBean.GoodsCartListBean model1, int p) {
                                        ImageView iv = holder.getView(R.id.iv);
                                        Glide.with(ConfirmOrderActivity.this).load(URLs.IMGHOST + model1.getGoods_info().getGImg())
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
                                        holder.getView(R.id.iv_delete).setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                //删除
                                                showToast("确认删除该商品吗？", "确认", "取消", new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        dialog.dismiss();
                                                        Map<String, String> params = new HashMap<>();
                                                        params.put("u_token", localUserInfo.getToken());
                                                        params.put("y_cart_id", model1.getYCartId());
                                                        RequestDelete(params);
                                                    }
                                                }, new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        dialog.dismiss();
                                                    }
                                                });

                                            }
                                        });
                                        holder.getView(R.id.tv_jian).setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                //减
                                                if (model1.getGNum() > 1) {
                                                    holder.setText(R.id.tv_num, model1.getGNum() - 1 + "");
                                                    holder.setText(R.id.tv_money, "" + model1.getVPrice() * (model1.getGNum() - 1));
                                                    showProgress(true, getString(R.string.app_loading));
                                                    Map<String, String> params = new HashMap<>();
                                                    params.put("u_token", localUserInfo.getToken());
                                                    params.put("y_cart_id", model1.getYCartId());
                                                    params.put("g_num", (model1.getGNum() - 1) + "");
                                                    RequestChage(params);
                                                }

                                            }
                                        });
                                        holder.getView(R.id.tv_jia).setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                //加
                                                holder.setText(R.id.tv_num, (model1.getGNum() + 1) + "");
                                                holder.setText(R.id.tv_money, "" + model1.getVPrice() * (model1.getGNum() + 1));
                                                showProgress(true, getString(R.string.app_loading));
                                                Map<String, String> params = new HashMap<>();
                                                params.put("u_token", localUserInfo.getToken());
                                                params.put("y_cart_id", model1.getYCartId());
                                                params.put("g_num", (model1.getGNum() + 1) + "");
                                                RequestChage(params);
                                            }
                                        });
                                    }
                                };
                        rv.setAdapter(ca);

                        holder.getView(R.id.delete).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //删除
                                showToast("该服务下有" + model.getGoods_cart_list().size() + "个商品，确认一起删除吗？", "确认", "取消", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                        Map<String, String> params = new HashMap<>();
                                        params.put("u_token", localUserInfo.getToken());
                                        params.put("y_cart_id", model.getYCartId());
                                        RequestDelete(params);
                                    }
                                }, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                    }
                                });
                            }
                        });
                        holder.getView(R.id.add).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //添加商品
                                Bundle bundle = new Bundle();
                                bundle.putString("y_store_id", y_store_id);
                                bundle.putString("y_store_service_id", model.getYStoreServiceId());
                                CommonUtil.gotoActivityWithData(ConfirmOrderActivity.this, SelectGoodsActivity.class, bundle, false);
                            }
                        });
                    }
                };
                recyclerView.setAdapter(mAdapter);

                //其他商品
                list_other = response.getGoods_cart_list();

                mAdapter_other = new CommonAdapter<ConfirmOrderModel.GoodsCartListBean>
                        (ConfirmOrderActivity.this, R.layout.item_confirmorder_goods, list_other) {
                    @Override
                    protected void convert(ViewHolder holder, ConfirmOrderModel.GoodsCartListBean model1, int p) {
                        ImageView iv = holder.getView(R.id.iv);
                        Glide.with(ConfirmOrderActivity.this).load(URLs.IMGHOST + model1.getGoods_info().getGImg())
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
                        holder.getView(R.id.iv_delete).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //删除
                                showToast("确认删除该商品吗？", "确认", "取消", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                        Map<String, String> params = new HashMap<>();
                                        params.put("u_token", localUserInfo.getToken());
                                        params.put("y_cart_id", model1.getYCartId());
                                        RequestDelete(params);
                                    }
                                }, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                    }
                                });

                            }
                        });
                        holder.getView(R.id.tv_jian).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //减
                                if (model1.getGNum() > 1) {
                                    holder.setText(R.id.tv_num, model1.getGNum() - 1 + "");
                                    holder.setText(R.id.tv_money, "" + model1.getVPrice() * (model1.getGNum() - 1));
                                    showProgress(true, getString(R.string.app_loading));
                                    Map<String, String> params = new HashMap<>();
                                    params.put("u_token", localUserInfo.getToken());
                                    params.put("y_cart_id", model1.getYCartId());
                                    params.put("g_num", (model1.getGNum() - 1) + "");
                                    RequestChage(params);
                                }

                            }
                        });
                        holder.getView(R.id.tv_jia).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //加
                                holder.setText(R.id.tv_num, (model1.getGNum() + 1) + "");
                                holder.setText(R.id.tv_money, "" + model1.getVPrice() * (model1.getGNum() + 1));
                                showProgress(true, getString(R.string.app_loading));
                                Map<String, String> params = new HashMap<>();
                                params.put("u_token", localUserInfo.getToken());
                                params.put("y_cart_id", model1.getYCartId());
                                params.put("g_num", (model1.getGNum() + 1) + "");
                                RequestChage(params);
                            }
                        });
                    }
                };
                rv_other.setAdapter(mAdapter_other);


                //计算总价格
                double allmoney = 0;
                for (ConfirmOrderModel.ServiceListBean listBean : list) {
                    allmoney += listBean.getVPrice();
                    for (ConfirmOrderModel.ServiceListBean.GoodsCartListBean bean : listBean.getGoods_cart_list()) {
                        allmoney += bean.getGNum() * bean.getVPrice();
                    }
                }
                //计算其他商品价格
                for (ConfirmOrderModel.GoodsCartListBean otherBean : list_other) {
                    allmoney += otherBean.getGNum() * otherBean.getVPrice();
                }
                tv_money.setText("¥" + allmoney);

            }
        });

    }

    /**
     * 修改商品的数量
     *
     * @param params
     */
    private void RequestChage(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.ChageGoodsNum, params, headerMap, new CallBackUtil<Object>() {
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
//                hideProgress();
                requestServer();
                /*showToast("修改成功", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        requestServer();
                    }
                });*/
            }
        });
    }

    /**
     * 删除服务或商品
     *
     * @param params
     */
    private void RequestDelete(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.DeleteServiceOrGoods, params, headerMap, new CallBackUtil<Object>() {
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
                /*showToast("删除成功", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        requestServer();
                    }
                });*/
                myToast("删除成功");
                requestServer();
            }
        });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.linearLayout1:
                //选择车辆
                Intent intent1 = new Intent(ConfirmOrderActivity.this, MyGarageActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putInt("type", 10001);
                intent1.putExtras(bundle1);
                startActivityForResult(intent1, 10001, bundle1);
                break;
            case R.id.ll_store:
                //选择店铺
               /* Intent intent3 = new Intent(ConfirmOrderActivity.this, XuQiuOrderActivity.class);
                Bundle bundle3 = new Bundle();
                bundle3.putInt("type", 10003);
                intent3.putExtras(bundle3);
                startActivityForResult(intent3, 10003, bundle3);*/
                Intent intent3 = new Intent(ConfirmOrderActivity.this, SelectStore_ServiceActivity.class);
                Bundle bundle3 = new Bundle();
                bundle3.putInt("type", 10003);
                bundle3.putSerializable("ConfirmOrderModel", confirmOrderModel);
                intent3.putExtras(bundle3);
                startActivityForResult(intent3, 10003);
                break;
            case R.id.tv_addother:
                //添加其他商品
                Bundle bundle = new Bundle();
                bundle.putString("y_store_id", y_store_id);
                bundle.putString("y_store_service_id", "");
                CommonUtil.gotoActivityWithData(ConfirmOrderActivity.this, SelectGoodsActivity.class, bundle, false);
                break;
            case R.id.ll_jiechedaodian:
                //接车到店
                if (is_pick.equals("1")) {
                    is_pick = "0";
                    iv_jiechedaodian.setImageResource(R.mipmap.ic_weixuan);

//                    ll_jiechetime.setVisibility(View.GONE);
                    ll_jiecheaddr.setVisibility(View.GONE);
                } else {
                    is_pick = "1";
                    iv_jiechedaodian.setImageResource(R.mipmap.ic_xuanzhong);

//                    ll_jiechetime.setVisibility(View.VISIBLE);
                    ll_jiecheaddr.setVisibility(View.VISIBLE);
                }
                break;

            case R.id.ll_jiechetime:
            case R.id.tv_jiechetime:
                //接车时间
                setDate1("请选择接车时间", tv_jiechetime, tv_jiechetime.getText().toString().trim());
                break;
            case R.id.ll_jiecheaddr:
            case R.id.tv_jiecheaddr:
                //接车地址
                Intent intent4 = new Intent(ConfirmOrderActivity.this, SelectAddressActivity.class);
                Bundle bundle4 = new Bundle();
                bundle4.putInt("type", 10004);
                intent4.putExtras(bundle4);
                startActivityForResult(intent4, 10004, bundle4);
                break;

            case R.id.ll_songchedaojia:
                //送车到家
                if (is_delivery.equals("1")) {
                    is_delivery = "0";
                    iv_songchedaojia.setImageResource(R.mipmap.ic_weixuan);

                    ll_songcheaddr.setVisibility(View.GONE);
                } else {
                    is_delivery = "1";
                    iv_songchedaojia.setImageResource(R.mipmap.ic_xuanzhong);

                    ll_songcheaddr.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.ll_songcheaddr:
            case R.id.tv_songcheaddr:
                //接车地址
                Intent intent5 = new Intent(ConfirmOrderActivity.this, SelectAddressActivity.class);
                Bundle bundle5 = new Bundle();
                bundle5.putInt("type", 10005);
                intent5.putExtras(bundle5);
                startActivityForResult(intent5, 10005, bundle5);
                break;
            case R.id.ll_yuyuedaodian:
                //预约到店
                isYuYue = !isYuYue;
                if (isYuYue) {
                    iv_daodianpaidui.setImageResource(R.mipmap.ic_weixuan);

                    ll_yuyuetime.setVisibility(View.VISIBLE);
                    iv_yuyuedaodian.setImageResource(R.mipmap.ic_xuanzhong);

                    is_pick = "0";
                    iv_jiechedaodian.setImageResource(R.mipmap.ic_weixuan);
                    ll_jiechedaodian.setVisibility(View.VISIBLE);
//                    ll_jiechetime.setVisibility(View.VISIBLE);
                    ll_jiecheaddr.setVisibility(View.GONE);

                    is_delivery = "0";
                    iv_songchedaojia.setImageResource(R.mipmap.ic_weixuan);
                    ll_songchedaojia.setVisibility(View.VISIBLE);
                    ll_songcheaddr.setVisibility(View.GONE);

                    Intent intent2 = new Intent(ConfirmOrderActivity.this, SelectTimeActivity.class);
                    Bundle bundle2 = new Bundle();
                    bundle2.putInt("type", 10002);
                    bundle2.putString("y_store_id", y_store_id);
                    intent2.putExtras(bundle2);
                    startActivityForResult(intent2, 10002, bundle2);


                } else {
                    iv_daodianpaidui.setImageResource(R.mipmap.ic_xuanzhong);

                    ll_yuyuetime.setVisibility(View.GONE);
                    iv_yuyuedaodian.setImageResource(R.mipmap.ic_weixuan);

                    is_pick = "0";
                    ll_jiechedaodian.setVisibility(View.GONE);
//                    ll_jiechetime.setVisibility(View.GONE);
                    ll_jiecheaddr.setVisibility(View.GONE);

                    is_delivery = "0";
                    ll_songchedaojia.setVisibility(View.GONE);
                    ll_songcheaddr.setVisibility(View.GONE);
                }

                break;
            case R.id.ll_daodianpaidui:
                //到店排队
                iv_daodianpaidui.setImageResource(R.mipmap.ic_xuanzhong);

                isYuYue = false;
                ll_yuyuetime.setVisibility(View.GONE);
                iv_yuyuedaodian.setImageResource(R.mipmap.ic_weixuan);

                is_pick = "0";
                ll_jiechedaodian.setVisibility(View.GONE);
//                ll_jiechetime.setVisibility(View.GONE);
                ll_jiecheaddr.setVisibility(View.GONE);

                is_delivery = "0";
                ll_songchedaojia.setVisibility(View.GONE);
                ll_songcheaddr.setVisibility(View.GONE);

                break;
            case R.id.tv_time:
            case R.id.ll_yuyuetime:
            case R.id.tv_yuyuetime:
                //预约时间
                Intent intent2 = new Intent(ConfirmOrderActivity.this, SelectTimeActivity.class);
                Bundle bundle2 = new Bundle();
                bundle2.putInt("type", 10002);
                bundle2.putString("y_store_id", y_store_id);
                intent2.putExtras(bundle2);
                startActivityForResult(intent2, 10002, bundle2);
                break;
            case R.id.tv_yuyuedaodian:
                //预约到店
                /*isYuYue = true;
                tv_yuyuedaodian.setBackgroundResource(R.drawable.yuanjiao_5_lanse_left);
                tv_yuyuedaodian.setTextColor(getResources().getColor(R.color.white));
                tv_daodianshigong.setBackgroundResource(R.drawable.yuanjiaobiankuang_5_lanse_right);
                tv_daodianshigong.setTextColor(getResources().getColor(R.color.blue));
                if (match()) {
                    showToast("确认提交订单吗？", "确认", "取消", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            showProgress(true, getString(R.string.app_loading1));
                            Map<String, String> params = new HashMap<>();
                            params.put("u_token", localUserInfo.getToken());
                            params.put("y_store_id", y_store_id);
                            params.put("longitude", longitude);
                            params.put("latitude", latitude);
                            params.put("y_user_sedan_id", y_user_sedan_id);
                            params.put("appoin_time", appoin_time);
                            params.put("is_pick", is_pick);
                            params.put("is_delivery", is_delivery);
                            RequestAdd(params);
                        }
                    }, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                }*/
                break;
            case R.id.tv_daodianshigong:
                //到店施工
//                isYuYue = false;
//                tv_yuyuedaodian.setBackgroundResource(R.drawable.yuanjiaobiankuang_5_lanse_left);
//                tv_yuyuedaodian.setTextColor(getResources().getColor(R.color.blue));
//                tv_daodianshigong.setBackgroundResource(R.drawable.yuanjiao_5_lanse_right);
//                tv_daodianshigong.setTextColor(getResources().getColor(R.color.white));
                if (match()) {
                    showToast("确认提交订单吗？", "确认", "取消", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            showProgress(true, getString(R.string.app_loading1));
                            Map<String, String> params = new HashMap<>();
                            params.put("u_token", localUserInfo.getToken());
                            params.put("y_store_id", y_store_id);
                            params.put("longitude", longitude);
                            params.put("latitude", latitude);
                            params.put("y_user_sedan_id", y_user_sedan_id);
                            if (isYuYue) {
                                params.put("appoin_time", appoin_time);

                                params.put("is_pick", is_pick);
                                params.put("is_delivery", is_delivery);

                                params.put("delivery_time", delivery_time);
                                params.put("delivery_address", delivery_address);
                                params.put("send_address", send_address);
                            } else {
                                params.put("appoin_time", "");

                                params.put("is_pick", "0");
                                params.put("is_delivery", "0");

                                params.put("delivery_time", "");
                                params.put("delivery_address", "");
                                params.put("send_address", "");
                            }

                            params.put("c_msg",et_beizhu.getText().toString().trim());
                            RequestAdd(params);
                        }
                    }, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                }
                break;
        }
    }

    /**
     * 提交订单
     *
     * @param params
     */
    private void RequestAdd(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.ADDOrder, params, headerMap, new CallBackUtil<ConfirmOrderNumModel>() {
            @Override
            public ConfirmOrderNumModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(ConfirmOrderNumModel response) {
                hideProgress();
                /*showToast("订单提交成功", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();

//                        finish();
//                        CommonUtil.gotoActivityWithFinishOtherAll(ConfirmOrderActivity.this, MainActivity.class, true);
                    }
                });*/
                //
                myToast("订单提交成功");
                Bundle bundle = new Bundle();
                bundle.putString("y_order_id", response.getY_order_id());
                CommonUtil.gotoActivityWithData(ConfirmOrderActivity.this, MyOrderActivity.class,bundle, true);
            }
        });
    }

    /**
     * 发布询价
     *
     * @param params
     */
    private void RequestAddXunJia(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.ADDXunJia, params, headerMap, new CallBackUtil<Object>() {
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
                showToast("发布询价成功", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    /**
     * 改变门店
     *
     * @param params
     */
    private void RequestChangeStore(Map<String, String> params, String new_y_store_id) {
        OkhttpUtil.okHttpPost(URLs.ChangeStore, params, headerMap, new CallBackUtil<Object>() {
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
                y_store_id = new_y_store_id;
                requestServer();
            }
        });
    }

    private boolean match() {
        if (isYuYue) {
            if (appoin_time.equals("")) {
                myToast("请选择预约时间");
                return false;
            }
        }

        if (y_user_sedan_id.equals("")) {
            myToast("请选择车辆");
            return false;
        }

        if (is_pick.equals("1")) {
            delivery_time = appoin_time;
//            delivery_time = tv_jiechetime.getText().toString().trim();
            if (delivery_time.equals("")) {
                myToast("请选择预约时间");
                return false;
            }
            delivery_address = tv_jiecheaddr.getText().toString().trim();
            if (delivery_address.equals("")) {
                myToast("请选择接车地址");
                return false;
            }
        } else {
            delivery_time = "";
            delivery_address = "";
        }

        if (is_delivery.equals("1")) {
            send_address = tv_songcheaddr.getText().toString().trim();
            if (send_address.equals("")) {
                myToast("请选择送车地址");
                return false;
            }
        } else {
            send_address = "";
        }
        return true;
    }

    @Override
    protected void updateView() {
        titleView.setTitle("确认订单");
        titleView.setTitleColor(R.color.white);
        titleView.setLeftBtn(R.mipmap.ic_return_white, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleView.setBackground(R.color.blue);
        titleView.showRightTextview("发布询价", R.color.white, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("确认发布询价吗？", "确认", "取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        showProgress(true, getString(R.string.app_loading1));
                        Map<String, String> params = new HashMap<>();
                        params.put("u_token", localUserInfo.getToken());
                        params.put("y_store_id", y_store_id);
                        params.put("y_user_sedan_id", y_user_sedan_id);
                        RequestAddXunJia(params);
                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 10001:
                //选择车辆
                if (data != null) {
                    Bundle bundle1 = data.getExtras();
                    y_user_sedan_id = bundle1.getString("car_id");
                    tv_carname.setText(bundle1.getString("carname"));
                    tv_carnum.setText(bundle1.getString("carnum"));
                    tv_cardetail.setText(bundle1.getString("cardetail"));
                    Glide.with(ConfirmOrderActivity.this).load(URLs.IMGHOST + bundle1.getString("carlogo"))
                            .centerCrop()
                            .into(imageView1);//加载图片
                }
                break;
            case 10002:
                //选择时间
                if (data != null) {
                    Bundle bundle2 = data.getExtras();
                    appoin_time = bundle2.getString("appoin_time");
                    tv_time.setText(appoin_time);
                    tv_yuyuetime.setText(appoin_time);
                }
                break;
            case 10003:
                //选择店铺
                if (data != null) {
                    Bundle bundle3 = data.getExtras();
//                    y_store_id = bundle3.getString("y_store_id");
                   /* longitude = bundle3.getString("longitude");
                    latitude = bundle3.getString("latitude");*/
//                    requestServer();
                    showProgress(true, getString(R.string.app_loading1));
                    Map<String, String> params = new HashMap<>();
                    params.put("u_token", localUserInfo.getToken());
                    params.put("old_y_store_id", y_store_id);
                    params.put("new_y_store_id", bundle3.getString("y_store_id"));
                    RequestChangeStore(params, bundle3.getString("y_store_id"));

                }
                break;
            case 10004:
                //选择地址
                if (data != null) {
                    Bundle bundle3 = data.getExtras();
                    longitude = bundle3.getString("lng");
                    latitude = bundle3.getString("lat");
                    tv_jiecheaddr.setText(bundle3.getString("addr"));
                }
                break;
            case 10005:
                //选择地址
                if (data != null) {
                    Bundle bundle3 = data.getExtras();
//                    longitude = bundle3.getString("lng");
//                    latitude = bundle3.getString("lat");
                    tv_songcheaddr.setText(bundle3.getString("addr"));
                }
                break;
        }

    }

    private void setDate1(String string, TextView textView, String date) {
        //获取当前时间
        Calendar calendar = Calendar.getInstance();
        //年
        int year = calendar.get(Calendar.YEAR);
        //月
        int month = calendar.get(Calendar.MONTH);
        //日
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        //小时
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        //分钟
        int minute = calendar.get(Calendar.MINUTE);
        //秒
        int second = calendar.get(Calendar.SECOND);

        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();

        if (!date.equals("")) {
            String[] strArr1 = date.split(" ");//拆分空格把日期和时间分开
            String[] strArr2 = strArr1[0].split("-");//拆分日期 得到年月日
            String[] strArr3 = strArr1[1].split(":");//拆分日期 得到年月日
            selectedDate.set(Integer.valueOf(strArr2[0]), Integer.valueOf(strArr2[1]) - 1, Integer.valueOf(strArr2[2]),
                    Integer.valueOf(strArr3[0]), Integer.valueOf(strArr3[1]));
        }

        //正确设置方式 原因：注意事项有说明
        startDate.set(year, month, day);
//        startDate.set(1900, 0, 1);

        //当前时间加3天
//        calendar.add(Calendar.YEAR, 100);
        endDate.set(2100, 11, 1);
        /*endDate.set(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));*/


        pvTime1 = new TimePickerBuilder(ConfirmOrderActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                textView.setText(CommonUtil.getTime1(date));
            }
        })
                .setType(new boolean[]{true, true, true, true, true, false})// 默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确定")//确认按钮文字
                .setContentTextSize(15)//滚轮文字大小
                .setTitleSize(16)//标题文字大小
                .setTitleText(string)//标题文字
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(false)//是否循环滚动
                .setTitleColor(getResources().getColor(R.color.black2))//标题文字颜色
                .setSubmitColor(getResources().getColor(R.color.blue))//确定按钮文字颜色
                .setCancelColor(getResources().getColor(R.color.blue))//取消按钮文字颜色
                .setTitleBgColor(getResources().getColor(R.color.black5))//标题背景颜色 Night mode
                .setBgColor(getResources().getColor(R.color.white))//滚轮背景颜色 Night mode
                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                .setRangDate(startDate, endDate)//起始终止年月日设定
                .setLabel("年", "月", "日", "时", "分", "秒")//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(true)//是否显示为对话框样式
                .build();

        Dialog mDialog = pvTime1.getDialog();
        if (mDialog != null) {

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);
            params.leftMargin = 0;
            params.rightMargin = 0;
            pvTime1.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
                dialogWindow.setDimAmount(0.1f);
            }
        }
        pvTime1.show();
    }
}
