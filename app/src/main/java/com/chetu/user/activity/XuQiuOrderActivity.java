package com.chetu.user.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chetu.user.R;
import com.chetu.user.base.BaseActivity;
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
 * Created by Mr.Z on 2020/7/30.
 * 需求订单
 */
public class XuQiuOrderActivity extends BaseActivity {
    int type = 0;
    int page = 0;
    private RecyclerView recyclerView;
    List<XuQiuOrderModel.ListBean> list = new ArrayList<>();
    CommonAdapter<XuQiuOrderModel.ListBean> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xuqiuorder);
        mImmersionBar.reset()
                .statusBarColor(R.color.background)
                .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
    }

    @Override
    protected void initView() {
        //刷新
        setSpringViewMore(false);//不需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                page = 0;
                Map<String, String> params = new HashMap<>();
                params.put("page", page + "");
                params.put("u_token", localUserInfo.getToken());
                Request(params);
            }

            @Override
            public void onLoadmore() {
                page++;
                Map<String, String> params = new HashMap<>();
                params.put("u_token", localUserInfo.getToken());
                params.put("page", page + "");
                RequestMore(params);
            }
        });

        recyclerView = findViewByID_My(R.id.recyclerView);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLinearLayoutManager);
    }

    @Override
    protected void initData() {
        type = getIntent().getIntExtra("type",0);
        requestServer();
    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        page = 0;
        Map<String, String> params = new HashMap<>();
        params.put("page", page + "");
        params.put("u_token", localUserInfo.getToken());
        Request(params);
    }

    private void Request(Map<String, String> params) {
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
                list = response.getList();
                if (list.size() > 0) {
                    showContentPage();
                    mAdapter = new CommonAdapter<XuQiuOrderModel.ListBean>
                            (XuQiuOrderActivity.this, R.layout.item_xuqiuorder, list) {
                        @Override
                        protected void convert(ViewHolder holder, XuQiuOrderModel.ListBean model, int position) {
                            ImageView iv_storelogo = holder.getView(R.id.iv_storelogo);
                            Glide.with(XuQiuOrderActivity.this)
                                    .load(URLs.IMGHOST + model.getStore_info().getPicture())
                                    .centerCrop()
                                    .placeholder(R.mipmap.loading)//加载站位图
                                    .error(R.mipmap.zanwutupian)//加载失败
                                    .into(iv_storelogo);//加载图片
                            holder.setText(R.id.tv_storename, model.getStore_info().getVName());
                            ImageView iv_carlogo = holder.getView(R.id.iv_carlogo);
                            Glide.with(XuQiuOrderActivity.this)
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
                                            CommonUtil.gotoActivityWithData(XuQiuOrderActivity.this, ConfirmOrderActivity.class, bundle2, false);
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
                    mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            if (type == 10003){
                                //保存
                                Intent resultIntent = new Intent();
                                Bundle bundle = new Bundle();
                                bundle.putString("y_store_id", list.get(i).getYStoreId());
                                bundle.putString("longitude",  localUserInfo.getLongitude());
                                bundle.putString("latitude",localUserInfo.getLatitude());
                                resultIntent.putExtras(bundle);
                                XuQiuOrderActivity.this.setResult(RESULT_OK, resultIntent);
                                finish();
                            }else {
                                Bundle bundle2 = new Bundle();
//                bundle2.putSerializable("XuanZeFuWuModel", (Serializable) list_xuanze);
                                bundle2.putString("y_store_id", list.get(i).getYStoreId());
                                bundle2.putString("longitude", localUserInfo.getLongitude());
                                bundle2.putString("latitude", localUserInfo.getLatitude());
                                CommonUtil.gotoActivityWithData(XuQiuOrderActivity.this, ConfirmOrderActivity.class, bundle2, false);
                            }

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

    private void RequestMore(Map<String, String> params) {
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
                    list.addAll(list1);
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
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

    @Override
    protected void updateView() {
        titleView.setTitle("需求订单");
        titleView.setBackground(R.color.background);
    }
}
