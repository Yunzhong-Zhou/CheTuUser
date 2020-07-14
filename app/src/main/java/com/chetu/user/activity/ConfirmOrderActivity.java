package com.chetu.user.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chetu.user.R;
import com.chetu.user.base.BaseActivity;
import com.chetu.user.model.ConfirmOrderModel;
import com.chetu.user.net.URLs;
import com.chetu.user.okhttp.CallBackUtil;
import com.chetu.user.okhttp.OkhttpUtil;
import com.chetu.user.utils.CommonUtil;
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
 * Created by zyz on 2020/7/3.
 */
public class ConfirmOrderActivity extends BaseActivity {
    String y_user_sedan_id = "", y_store_id = "", longitude = "", latitude = "", appoin_time = "", is_pick = "1", is_delivery = "0";
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
    LinearLayout ll_jiechedaodian, ll_songchedaojia;
    ImageView iv_jiechedaodian, iv_songchedaojia;

    //下单
    boolean isYuYue = false;
    TextView tv_time, tv_money, tv_yuyuedaodian, tv_daodianshigong;

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
        ll_jiechedaodian = findViewByID_My(R.id.ll_jiechedaodian);
        ll_songchedaojia = findViewByID_My(R.id.ll_songchedaojia);
        iv_jiechedaodian = findViewByID_My(R.id.iv_jiechedaodian);
        iv_songchedaojia = findViewByID_My(R.id.iv_songchedaojia);

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
            }

            @Override
            public void onResponse(Object response) {
                hideProgress();
                showToast("删除成功", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        requestServer();
                    }
                });
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
                } else {
                    is_pick = "1";
                    iv_jiechedaodian.setImageResource(R.mipmap.ic_xuanzhong);
                }
                break;
            case R.id.ll_songchedaojia:
                //送车到家
                if (is_delivery.equals("1")) {
                    is_delivery = "0";
                    iv_songchedaojia.setImageResource(R.mipmap.ic_weixuan);
                } else {
                    is_delivery = "1";
                    iv_songchedaojia.setImageResource(R.mipmap.ic_xuanzhong);
                }
                break;

            case R.id.tv_time:
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
                isYuYue = true;
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
                }
                break;
            case R.id.tv_daodianshigong:
                //到店施工
                isYuYue = false;
                tv_yuyuedaodian.setBackgroundResource(R.drawable.yuanjiaobiankuang_5_lanse_left);
                tv_yuyuedaodian.setTextColor(getResources().getColor(R.color.blue));
                tv_daodianshigong.setBackgroundResource(R.drawable.yuanjiao_5_lanse_right);
                tv_daodianshigong.setTextColor(getResources().getColor(R.color.white));
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
                            params.put("appoin_time", "");
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
        OkhttpUtil.okHttpPost(URLs.ADDOrder, params, headerMap, new CallBackUtil<Object>() {
            @Override
            public Object onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
            }

            @Override
            public void onResponse(Object response) {
                hideProgress();
                showToast("订单提交成功", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        finish();
                    }
                });
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

        if (y_user_sedan_id.equals("")){
            myToast("请选择车辆");
            return false;
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
                }
                break;
        }

    }
}
