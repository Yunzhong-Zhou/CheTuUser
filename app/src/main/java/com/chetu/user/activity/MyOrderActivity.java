package com.chetu.user.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chetu.user.R;
import com.chetu.user.base.BaseActivity;
import com.chetu.user.model.MyOrderModel;
import com.chetu.user.model.XuQiuOrderModel;
import com.chetu.user.net.URLs;
import com.chetu.user.okhttp.CallBackUtil;
import com.chetu.user.okhttp.OkhttpUtil;
import com.chetu.user.utils.CommonUtil;
import com.cy.cyflowlayoutlibrary.FlowLayout;
import com.cy.cyflowlayoutlibrary.FlowLayoutAdapter;
import com.liaoinstan.springview.widget.SpringView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
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
 * Created by zyz on 2020/5/27.
 * 我的订单
 */
public class MyOrderActivity extends BaseActivity {
    String y_order_id = "";

    int type = 0;
    int page = 0;
    private RecyclerView recyclerView;
    List<MyOrderModel.ListBean> list = new ArrayList<>();
    CommonAdapter<MyOrderModel.ListBean> mAdapter;


    List<XuQiuOrderModel.ListBean> list_xuqiu = new ArrayList<>();
    CommonAdapter<XuQiuOrderModel.ListBean> mAdapter_xuqiu;

    TextView tv_type0, tv_type1, tv_type2, tv_type3, tv_type4, tv_type5, tv_type6, tv_type7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorder);
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestServer();
    }

    @Override
    protected void initView() {
        //刷新
        setSpringViewMore(true);//不需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                page = 0;
                Map<String, String> params = new HashMap<>();
                params.put("page", page + "");
                params.put("u_token", localUserInfo.getToken());
                if (type == 0) {
                    //需求订单
                    RequestXuQiu(params);
                } else {
                    params.put("g_state", (type - 1) + "");
                    Request(params);
                }
            }

            @Override
            public void onLoadmore() {
                page++;
                Map<String, String> params = new HashMap<>();
                params.put("u_token", localUserInfo.getToken());
                params.put("page", page + "");
                if (type == 0) {
                    //需求订单
                    RequestXuQiuMore(params);
                } else {
                    params.put("g_state", (type - 1) + "");
                    RequestMore(params);
                }
            }
        });

        recyclerView = findViewByID_My(R.id.recyclerView);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLinearLayoutManager);

        tv_type0 = findViewByID_My(R.id.tv_type0);
        tv_type1 = findViewByID_My(R.id.tv_type1);
        tv_type2 = findViewByID_My(R.id.tv_type2);
        tv_type3 = findViewByID_My(R.id.tv_type3);
        tv_type4 = findViewByID_My(R.id.tv_type4);
        tv_type5 = findViewByID_My(R.id.tv_type5);
        tv_type6 = findViewByID_My(R.id.tv_type6);
        tv_type7 = findViewByID_My(R.id.tv_type7);


    }

    @Override
    protected void initData() {
        y_order_id = getIntent().getStringExtra("y_order_id");
        if (!y_order_id.equals("")) {
            showToast("下单成功\n订单编号：" + y_order_id, "查看订单", "返回首页", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    Bundle bundle = new Bundle();
                    bundle.putString("y_order_id", y_order_id);
//                            bundle.putInt("g_state", type - 1);
                    CommonUtil.gotoActivityWithData(MyOrderActivity.this, OrderDetailActivity.class, bundle, false);
                }
            }, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    CommonUtil.gotoActivityWithFinishOtherAll(MyOrderActivity.this, MainActivity.class, true);
                }
            });
        }

    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        page = 0;
        Map<String, String> params = new HashMap<>();
        params.put("page", page + "");
        params.put("u_token", localUserInfo.getToken());

        if (type == 0) {
            //需求订单
            RequestXuQiu(params);
        } else {
            params.put("g_state", (type - 1) + "");
            Request(params);
        }

    }

    /**
     * 我的订单
     *
     * @param params
     */
    private void Request(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.MyOrder, params, headerMap, new CallBackUtil<MyOrderModel>() {
            @Override
            public MyOrderModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                showEmptyPage();
//                myToast(err);
            }

            @Override
            public void onResponse(MyOrderModel response) {
                hideProgress();
                list = response.getList();
                if (list.size() > 0) {
                    showContentPage();
                    mAdapter = new CommonAdapter<MyOrderModel.ListBean>
                            (MyOrderActivity.this, R.layout.item_myorder, list) {
                        @Override
                        protected void convert(ViewHolder holder, MyOrderModel.ListBean model, int position) {
                            holder.setText(R.id.tv_ordernum, "订单编号:" + model.getYOrderId());
                            holder.setText(R.id.tv_ordertime, "下单时间:" + model.getCreateDate());
                            holder.setText(R.id.tv_carname, "" + model.getUser_sedan_info().getBrandInfo().getGroupName() + "-" + model.getUser_sedan_info().getBrandInfo().getSeriesName());
                            holder.setText(R.id.tv_carnum, model.getUser_sedan_info().getSNumber());
                            //预约时间
                            TextView tv_time = holder.getView(R.id.tv_time);
                            if (model.getAppoinTime() != null && !model.getAppoinTime().equals("")) {
                                tv_time.setVisibility(View.VISIBLE);
                                tv_time.setText("预约时间:" + model.getAppoinTime());
                            } else {
                                tv_time.setVisibility(View.GONE);
                            }
                            ImageView iv = holder.getView(R.id.iv);
                            Glide.with(MyOrderActivity.this).load(URLs.IMGHOST + model.getStore_info().getPicture())
                                    .centerCrop()
//                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                                    .placeholder(R.mipmap.loading)//加载站位图
                                    .error(R.mipmap.zanwutupian)//加载失败
                                    .into(iv);//加载图片
                            holder.setText(R.id.tv_storename, model.getStore_info().getVName());
                            holder.setText(R.id.tv_addr, model.getStore_info().getAddress());
                            holder.setText(R.id.tv_money, "¥" + model.getGPrice());

                            //接车人
                            TextView tv_name = holder.getView(R.id.tv_name);
                            if (model.getTechn_user_info() != null && model.getTechn_user_info().getUserName() != null) {
                                tv_name.setVisibility(View.VISIBLE);
                                tv_name.setText("接车人：" + model.getTechn_user_info().getUserName());
                            } else {
                                tv_name.setVisibility(View.GONE);
                            }

                            //服务
                            if (model.getOrder_service_list().size() > 0) {
                                //标签
                                FlowLayoutAdapter<MyOrderModel.ListBean.OrderServiceListBean> flowLayoutAdapter1 =
                                        new FlowLayoutAdapter<MyOrderModel.ListBean.OrderServiceListBean>
                                                (model.getOrder_service_list()) {
                                            @Override
                                            public void bindDataToView(FlowLayoutAdapter.ViewHolder holder, int position,
                                                                       MyOrderModel.ListBean.OrderServiceListBean bean) {
//                                holder.setText(R.id.tv,bean);
                                                TextView tv = holder.getView(R.id.tv);
                                                tv.setText(bean.getServiceStr());
                                    /*tv.setTextColor(getResources().getColor(R.color.black1));
                                    tv.setBackgroundResource(R.drawable.yuanjiao_3_huise);*/
                                            }

                                            @Override
                                            public void onItemClick(int position, MyOrderModel.ListBean.OrderServiceListBean bean) {
//                        showToast("点击" + position);
                                               /* Bundle bundle = new Bundle();
                                                bundle.putString("id", model.getYStoreId());
                                                bundle.putString("longitude", longitude);
                                                bundle.putString("latitude", latitude);
                                                CommonUtil.gotoActivityWithData(getActivity(), StoreDetailActivity.class, bundle, false);*/
                                            }

                                            @Override
                                            public int getItemLayoutID(int position, MyOrderModel.ListBean.OrderServiceListBean bean) {
                                                return R.layout.item_fragment3_flowlayout1;
                                            }
                                        };
                                ((FlowLayout) holder.getView(R.id.flowLayout1)).setAdapter(flowLayoutAdapter1);
                            }

                            TextView type1 = holder.getView(R.id.type1);
                            TextView type2 = holder.getView(R.id.type2);
                            TextView type3 = holder.getView(R.id.type3);
                            if (model.getTechn_sedan_info().getIsPay() ==1){
                                type2.setText("已付款");
                            }else {
                                type2.setText("未付款");
                            }
                            switch (model.getGState()) {
                                case 0:
                                    //待接车
                                    type1.setText("待接车");
                                    type3.setVisibility(View.GONE);
                                    break;
                                case 1:
                                    //待分配
                                    type1.setText("待分配");
                                    type3.setVisibility(View.GONE);
                                    break;
                                case 2:
                                    //待施工
                                    type1.setText("待施工");
                                    type3.setVisibility(View.GONE);
                                    break;
                                case 3:
                                    //进行中
                                    type1.setText("进行中");
                                    type3.setVisibility(View.GONE);
                                    break;
                                case 4:
                                    //待复检
                                    type1.setText("待复检");
                                    type3.setVisibility(View.GONE);
                                    break;
                                case 5:
                                    //已完工
                                    type1.setText("已完工");
                                    type3.setVisibility(View.GONE);
                                    break;
                                case 6:
                                    //已提车
                                    type1.setText("已提车");

                                    type3.setVisibility(View.VISIBLE);
                                    if (model.getIsEval() == 1) {
                                        type3.setText("已评论");
                                        type3.setTextColor(getResources().getColor(R.color.black3));
                                        type3.setBackgroundResource(R.drawable.yuanjiaobiankuang_5_huise);
                                    } else {
                                        type3.setText("去评论");
                                        type3.setTextColor(getResources().getColor(R.color.white));
                                        type3.setBackgroundResource(R.drawable.yuanjiao_5_lanse);
                                    }
                                    break;
                            }


                            holder.getView(R.id.iv_call).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
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
                                }
                            });
                            holder.getView(R.id.iv_message).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String url = URLs.KFHOST + "/#/pages/chetu-kf/chetu-kf?token=" + localUserInfo.getToken() +
                                            "&kf_userHash=" + model.getKf_user_info().getUserHash() +
                                            "&nickName=" + model.getKf_user_info().getUserName() +
                                            "&headerPic=" + URLs.IMGHOST + model.getKf_user_info().getHeadPortrait();
                                    Bundle bundle = new Bundle();
                                    bundle.putString("url", url);
                                    CommonUtil.gotoActivityWithData(MyOrderActivity.this, WebContentActivity.class, bundle, false);
                                }
                            });

                        }
                    };
                    mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            Bundle bundle = new Bundle();
                            bundle.putString("y_order_id", list.get(i).getYOrderId());
//                            bundle.putInt("g_state", type - 1);
                            CommonUtil.gotoActivityWithData(MyOrderActivity.this, OrderDetailActivity.class, bundle, false);
                            /*switch (type) {
                                case 1:
                                    //待接车
                                    CommonUtil.gotoActivityWithData(MyOrderActivity.this, DaiJieCheActivity.class, bundle, false);
                                    break;
                                case 2:
                                    //待分配
                                    CommonUtil.gotoActivityWithData(MyOrderActivity.this, DaiFenPeiActivity.class, bundle, false);
                                    break;
                                case 3:
                                    //待施工
                                    CommonUtil.gotoActivityWithData(MyOrderActivity.this, DaiShiGongActivity.class, bundle, false);
                                    break;
                                case 4:
                                    //进行中
                                    CommonUtil.gotoActivityWithData(MyOrderActivity.this, JinXingZhongActivity.class, bundle, false);
                                    break;
                                case 5:
                                    //待复检
                                    CommonUtil.gotoActivityWithData(MyOrderActivity.this, DaiFuJianActivity.class, bundle, false);
                                    break;
                                case 6:
                                    //已完工
                                    CommonUtil.gotoActivityWithData(MyOrderActivity.this, YiWanGongActivity.class, bundle, false);
                                    break;
                                case 7:
                                    //已提车
                                    CommonUtil.gotoActivityWithData(MyOrderActivity.this, YiTiCheActivity.class, bundle, false);
                                    break;
                            }*/
                        }

                        @Override
                        public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            return false;
                        }
                    });
                    recyclerView.setAdapter(mAdapter);
                } else {
                    showEmptyPage();
                }
            }
        });
    }

    /**
     * 我的订单更多
     *
     * @param params
     */
    private void RequestMore(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Fragment3, params, headerMap, new CallBackUtil<MyOrderModel>() {
            @Override
            public MyOrderModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
                page--;
            }

            @Override
            public void onResponse(MyOrderModel response) {
                hideProgress();
                List<MyOrderModel.ListBean> list1 = new ArrayList<>();
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

    /**
     * 需求订单
     *
     * @param params
     */
    private void RequestXuQiu(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.XuQiuOrder, params, headerMap, new CallBackUtil<XuQiuOrderModel>() {
            @Override
            public XuQiuOrderModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                showEmptyPage();
//                myToast(err);
            }

            @Override
            public void onResponse(XuQiuOrderModel response) {
                hideProgress();
                list_xuqiu = response.getList();
                if (list_xuqiu.size() > 0) {
                    showContentPage();
                    mAdapter_xuqiu = new CommonAdapter<XuQiuOrderModel.ListBean>
                            (MyOrderActivity.this, R.layout.item_xuqiuorder, list_xuqiu) {
                        @Override
                        protected void convert(ViewHolder holder, XuQiuOrderModel.ListBean model, int position) {
                            ImageView iv_storelogo = holder.getView(R.id.iv_storelogo);
                            Glide.with(MyOrderActivity.this)
                                    .load(URLs.IMGHOST + model.getStore_info().getPicture())
                                    .centerCrop()
                                    .placeholder(R.mipmap.loading)//加载站位图
                                    .error(R.mipmap.zanwutupian)//加载失败
                                    .into(iv_storelogo);//加载图片
                            holder.setText(R.id.tv_storename, model.getStore_info().getVName());
                            ImageView iv_carlogo = holder.getView(R.id.iv_carlogo);
                            Glide.with(MyOrderActivity.this)
                                    .load(URLs.IMGHOST + localUserInfo.getCarlogo())
                                    .centerCrop()
                                    .placeholder(R.mipmap.loading)//加载站位图
                                    .error(R.mipmap.zanwutupian)//加载失败
                                    .into(iv_carlogo);//加载图片

                            holder.setText(R.id.tv_carname, localUserInfo.getCarname());
                            holder.setText(R.id.tv_carnum, localUserInfo.getCarnum());
                            holder.setText(R.id.tv_cardetail, localUserInfo.getCardetail());

                            FlowLayoutAdapter<XuQiuOrderModel.ListBean.ServiceListBean> flowLayoutAdapter1 =
                                    new FlowLayoutAdapter<XuQiuOrderModel.ListBean.ServiceListBean>
                                            (model.getService_list()) {
                                        @Override
                                        public void bindDataToView(FlowLayoutAdapter.ViewHolder holder, int position,
                                                                   XuQiuOrderModel.ListBean.ServiceListBean bean) {
//                                holder.setText(R.id.tv,bean);
                                            TextView tv = holder.getView(R.id.tv);
                                            tv.setText(bean.getStore_service_info().getYStateValue());
                                    /*tv.setTextColor(getResources().getColor(R.color.black1));
                                    tv.setBackgroundResource(R.drawable.yuanjiao_3_huise);*/
                                        }

                                        @Override
                                        public void onItemClick(int position, XuQiuOrderModel.ListBean.ServiceListBean bean) {
//                        showToast("点击" + position);
                                            Bundle bundle2 = new Bundle();
//                bundle2.putSerializable("XuanZeFuWuModel", (Serializable) list_xuanze);
                                            bundle2.putString("y_store_id", model.getYStoreId());
                                            bundle2.putString("longitude", localUserInfo.getLongitude());
                                            bundle2.putString("latitude", localUserInfo.getLatitude());
                                            CommonUtil.gotoActivityWithData(MyOrderActivity.this, ConfirmOrderActivity.class, bundle2, false);
                                        }

                                        @Override
                                        public int getItemLayoutID(int position, XuQiuOrderModel.ListBean.ServiceListBean bean) {
                                            return R.layout.item_xuqiuorder_flowlayout;
                                        }
                                    };
                            ((FlowLayout) holder.getView(R.id.flowLayout1)).setAdapter(flowLayoutAdapter1);

                            holder.getView(R.id.tv_quxiao).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //取消订单
                                    showToast("确认取消该订单吗？", "确认", "取消", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            dialog.dismiss();
                                            Map<String, String> params = new HashMap<>();
                                            params.put("u_token", localUserInfo.getToken());
                                            params.put("y_store_id", model.getYStoreId());
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
                            holder.getView(R.id.tv_xunjia).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //立即询价
                                    showToast("确认立即询价吗？", "确认", "取消", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            dialog.dismiss();
                                            showProgress(true, getString(R.string.app_loading1));
                                            Map<String, String> params = new HashMap<>();
                                            params.put("u_token", localUserInfo.getToken());
                                            params.put("y_store_id", model.getYStoreId());
                                            params.put("y_user_sedan_id", localUserInfo.getCarid());
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
                            /*holder.getView(R.id.tv_xiadan).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //立即下单
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
                            });*/

                        }
                    };
                    mAdapter_xuqiu.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            /*if (type == 10003) {
                                //保存
                                Intent resultIntent = new Intent();
                                Bundle bundle = new Bundle();
                                bundle.putString("y_store_id", list.get(i).getYStoreId());
                                bundle.putString("longitude", localUserInfo.getLongitude());
                                bundle.putString("latitude", localUserInfo.getLatitude());
                                resultIntent.putExtras(bundle);
                                MyOrderActivity.this.setResult(RESULT_OK, resultIntent);
                                finish();
                            } else {*/
                            Bundle bundle2 = new Bundle();
//                bundle2.putSerializable("XuanZeFuWuModel", (Serializable) list_xuanze);
                            bundle2.putString("y_store_id", list_xuqiu.get(i).getYStoreId());
                            bundle2.putString("longitude", localUserInfo.getLongitude());
                            bundle2.putString("latitude", localUserInfo.getLatitude());
                            CommonUtil.gotoActivityWithData(MyOrderActivity.this, ConfirmOrderActivity.class, bundle2, false);
//                            }

                        }

                        @Override
                        public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            return false;
                        }
                    });
                    recyclerView.setAdapter(mAdapter_xuqiu);
                } else {
                    showEmptyPage();
                }
            }
        });
    }

    /**
     * 需求订单更多
     *
     * @param params
     */
    private void RequestXuQiuMore(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.XuQiuOrder, params, headerMap, new CallBackUtil<XuQiuOrderModel>() {
            @Override
            public XuQiuOrderModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
                page--;
            }

            @Override
            public void onResponse(XuQiuOrderModel response) {
                hideProgress();
                List<XuQiuOrderModel.ListBean> list1 = new ArrayList<>();
                list1 = response.getList();
                if (list1.size() == 0) {
                    page--;
                    myToast(getString(R.string.app_nomore));
                } else {
                    list_xuqiu.addAll(list1);
                    mAdapter_xuqiu.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_type0:
                //需求订单
                type = 0;
                tv_type0.setBackgroundResource(R.mipmap.bg_myorder_tab1_1);
                tv_type0.setTextColor(getResources().getColor(R.color.white));
                tv_type1.setBackgroundResource(R.mipmap.bg_myorder_tab2_0);
                tv_type1.setTextColor(getResources().getColor(R.color.black3));
                tv_type2.setBackgroundResource(R.mipmap.bg_myorder_tab2_0);
                tv_type2.setTextColor(getResources().getColor(R.color.black3));
                tv_type3.setBackgroundResource(R.mipmap.bg_myorder_tab2_0);
                tv_type3.setTextColor(getResources().getColor(R.color.black3));
                tv_type4.setBackgroundResource(R.mipmap.bg_myorder_tab2_0);
                tv_type4.setTextColor(getResources().getColor(R.color.black3));
                tv_type5.setBackgroundResource(R.mipmap.bg_myorder_tab2_0);
                tv_type5.setTextColor(getResources().getColor(R.color.black3));
                tv_type6.setBackgroundResource(R.mipmap.bg_myorder_tab2_0);
                tv_type6.setTextColor(getResources().getColor(R.color.black3));
                tv_type7.setBackgroundResource(R.mipmap.bg_myorder_tab3_0);
                tv_type7.setTextColor(getResources().getColor(R.color.black3));
                break;
            case R.id.tv_type1:
                //待接车
                type = 1;
                tv_type0.setBackgroundResource(R.mipmap.bg_myorder_tab1_0);
                tv_type0.setTextColor(getResources().getColor(R.color.black3));
                tv_type1.setBackgroundResource(R.mipmap.bg_myorder_tab2_1);
                tv_type1.setTextColor(getResources().getColor(R.color.white));
                tv_type2.setBackgroundResource(R.mipmap.bg_myorder_tab2_0);
                tv_type2.setTextColor(getResources().getColor(R.color.black3));
                tv_type3.setBackgroundResource(R.mipmap.bg_myorder_tab2_0);
                tv_type3.setTextColor(getResources().getColor(R.color.black3));
                tv_type4.setBackgroundResource(R.mipmap.bg_myorder_tab2_0);
                tv_type4.setTextColor(getResources().getColor(R.color.black3));
                tv_type5.setBackgroundResource(R.mipmap.bg_myorder_tab2_0);
                tv_type5.setTextColor(getResources().getColor(R.color.black3));
                tv_type6.setBackgroundResource(R.mipmap.bg_myorder_tab2_0);
                tv_type6.setTextColor(getResources().getColor(R.color.black3));
                tv_type7.setBackgroundResource(R.mipmap.bg_myorder_tab3_0);
                tv_type7.setTextColor(getResources().getColor(R.color.black3));
                break;
            case R.id.tv_type2:
                //待分配
                type = 2;
                tv_type0.setBackgroundResource(R.mipmap.bg_myorder_tab1_0);
                tv_type0.setTextColor(getResources().getColor(R.color.black3));
                tv_type1.setBackgroundResource(R.mipmap.bg_myorder_tab2_0);
                tv_type1.setTextColor(getResources().getColor(R.color.black3));
                tv_type2.setBackgroundResource(R.mipmap.bg_myorder_tab2_1);
                tv_type2.setTextColor(getResources().getColor(R.color.white));
                tv_type3.setBackgroundResource(R.mipmap.bg_myorder_tab2_0);
                tv_type3.setTextColor(getResources().getColor(R.color.black3));
                tv_type4.setBackgroundResource(R.mipmap.bg_myorder_tab2_0);
                tv_type4.setTextColor(getResources().getColor(R.color.black3));
                tv_type5.setBackgroundResource(R.mipmap.bg_myorder_tab2_0);
                tv_type5.setTextColor(getResources().getColor(R.color.black3));
                tv_type6.setBackgroundResource(R.mipmap.bg_myorder_tab2_0);
                tv_type6.setTextColor(getResources().getColor(R.color.black3));
                tv_type7.setBackgroundResource(R.mipmap.bg_myorder_tab3_0);
                tv_type7.setTextColor(getResources().getColor(R.color.black3));
                break;
            case R.id.tv_type3:
                //待施工
                type = 3;
                tv_type0.setBackgroundResource(R.mipmap.bg_myorder_tab1_0);
                tv_type0.setTextColor(getResources().getColor(R.color.black3));
                tv_type1.setBackgroundResource(R.mipmap.bg_myorder_tab2_0);
                tv_type1.setTextColor(getResources().getColor(R.color.black3));
                tv_type2.setBackgroundResource(R.mipmap.bg_myorder_tab2_0);
                tv_type2.setTextColor(getResources().getColor(R.color.black3));
                tv_type3.setBackgroundResource(R.mipmap.bg_myorder_tab2_1);
                tv_type3.setTextColor(getResources().getColor(R.color.white));
                tv_type4.setBackgroundResource(R.mipmap.bg_myorder_tab2_0);
                tv_type4.setTextColor(getResources().getColor(R.color.black3));
                tv_type5.setBackgroundResource(R.mipmap.bg_myorder_tab2_0);
                tv_type5.setTextColor(getResources().getColor(R.color.black3));
                tv_type6.setBackgroundResource(R.mipmap.bg_myorder_tab2_0);
                tv_type6.setTextColor(getResources().getColor(R.color.black3));
                tv_type7.setBackgroundResource(R.mipmap.bg_myorder_tab3_0);
                tv_type7.setTextColor(getResources().getColor(R.color.black3));
                break;
            case R.id.tv_type4:
                //进行中
                type = 4;
                tv_type0.setBackgroundResource(R.mipmap.bg_myorder_tab1_0);
                tv_type0.setTextColor(getResources().getColor(R.color.black3));
                tv_type1.setBackgroundResource(R.mipmap.bg_myorder_tab2_0);
                tv_type1.setTextColor(getResources().getColor(R.color.black3));
                tv_type2.setBackgroundResource(R.mipmap.bg_myorder_tab2_0);
                tv_type2.setTextColor(getResources().getColor(R.color.black3));
                tv_type3.setBackgroundResource(R.mipmap.bg_myorder_tab2_0);
                tv_type3.setTextColor(getResources().getColor(R.color.black3));
                tv_type4.setBackgroundResource(R.mipmap.bg_myorder_tab2_1);
                tv_type4.setTextColor(getResources().getColor(R.color.white));
                tv_type5.setBackgroundResource(R.mipmap.bg_myorder_tab2_0);
                tv_type5.setTextColor(getResources().getColor(R.color.black3));
                tv_type6.setBackgroundResource(R.mipmap.bg_myorder_tab2_0);
                tv_type6.setTextColor(getResources().getColor(R.color.black3));
                tv_type7.setBackgroundResource(R.mipmap.bg_myorder_tab3_0);
                tv_type7.setTextColor(getResources().getColor(R.color.black3));
                break;
            case R.id.tv_type5:
                //待复检
                type = 5;
                tv_type0.setBackgroundResource(R.mipmap.bg_myorder_tab1_0);
                tv_type0.setTextColor(getResources().getColor(R.color.black3));
                tv_type1.setBackgroundResource(R.mipmap.bg_myorder_tab2_0);
                tv_type1.setTextColor(getResources().getColor(R.color.black3));
                tv_type2.setBackgroundResource(R.mipmap.bg_myorder_tab2_0);
                tv_type2.setTextColor(getResources().getColor(R.color.black3));
                tv_type3.setBackgroundResource(R.mipmap.bg_myorder_tab2_0);
                tv_type3.setTextColor(getResources().getColor(R.color.black3));
                tv_type4.setBackgroundResource(R.mipmap.bg_myorder_tab2_0);
                tv_type4.setTextColor(getResources().getColor(R.color.black3));
                tv_type5.setBackgroundResource(R.mipmap.bg_myorder_tab2_1);
                tv_type5.setTextColor(getResources().getColor(R.color.white));
                tv_type6.setBackgroundResource(R.mipmap.bg_myorder_tab2_0);
                tv_type6.setTextColor(getResources().getColor(R.color.black3));
                tv_type7.setBackgroundResource(R.mipmap.bg_myorder_tab3_0);
                tv_type7.setTextColor(getResources().getColor(R.color.black3));
                break;
            case R.id.tv_type6:
                //已完工
                type = 6;
                tv_type0.setBackgroundResource(R.mipmap.bg_myorder_tab1_0);
                tv_type0.setTextColor(getResources().getColor(R.color.black3));
                tv_type1.setBackgroundResource(R.mipmap.bg_myorder_tab2_0);
                tv_type1.setTextColor(getResources().getColor(R.color.black3));
                tv_type2.setBackgroundResource(R.mipmap.bg_myorder_tab2_0);
                tv_type2.setTextColor(getResources().getColor(R.color.black3));
                tv_type3.setBackgroundResource(R.mipmap.bg_myorder_tab2_0);
                tv_type3.setTextColor(getResources().getColor(R.color.black3));
                tv_type4.setBackgroundResource(R.mipmap.bg_myorder_tab2_0);
                tv_type4.setTextColor(getResources().getColor(R.color.black3));
                tv_type5.setBackgroundResource(R.mipmap.bg_myorder_tab2_0);
                tv_type5.setTextColor(getResources().getColor(R.color.black3));
                tv_type6.setBackgroundResource(R.mipmap.bg_myorder_tab2_1);
                tv_type6.setTextColor(getResources().getColor(R.color.white));
                tv_type7.setBackgroundResource(R.mipmap.bg_myorder_tab3_0);
                tv_type7.setTextColor(getResources().getColor(R.color.black3));
                break;
            case R.id.tv_type7:
                //已提车
                type = 7;
                tv_type0.setBackgroundResource(R.mipmap.bg_myorder_tab1_0);
                tv_type0.setTextColor(getResources().getColor(R.color.black3));
                tv_type1.setBackgroundResource(R.mipmap.bg_myorder_tab2_0);
                tv_type1.setTextColor(getResources().getColor(R.color.black3));
                tv_type2.setBackgroundResource(R.mipmap.bg_myorder_tab2_0);
                tv_type2.setTextColor(getResources().getColor(R.color.black3));
                tv_type3.setBackgroundResource(R.mipmap.bg_myorder_tab2_0);
                tv_type3.setTextColor(getResources().getColor(R.color.black3));
                tv_type4.setBackgroundResource(R.mipmap.bg_myorder_tab2_0);
                tv_type4.setTextColor(getResources().getColor(R.color.black3));
                tv_type5.setBackgroundResource(R.mipmap.bg_myorder_tab2_0);
                tv_type5.setTextColor(getResources().getColor(R.color.black3));
                tv_type6.setBackgroundResource(R.mipmap.bg_myorder_tab2_0);
                tv_type6.setTextColor(getResources().getColor(R.color.black3));
                tv_type7.setBackgroundResource(R.mipmap.bg_myorder_tab3_1);
                tv_type7.setTextColor(getResources().getColor(R.color.white));
                break;
        }
        requestServer();
    }

    @Override
    protected void updateView() {
        titleView.setTitle("我的订单");
    }

    /**
     * 取消订单
     *
     * @param params
     */
    private void RequestDelete(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.DeleteOrder, params, headerMap, new CallBackUtil<Object>() {
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
                showToast("取消成功", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        requestServer();
                    }
                });
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
            }

            @Override
            public void onResponse(Object response) {
                hideProgress();
                showToast("发布询价成功", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        requestServer();
                    }
                });
            }
        });
    }
}
