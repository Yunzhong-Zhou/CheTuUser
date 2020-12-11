package com.chetu.user.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chetu.user.R;
import com.chetu.user.base.BaseActivity;
import com.chetu.user.model.WaitingReleaseModel;
import com.chetu.user.model.WaitingReleaseModel_JiuYuan;
import com.chetu.user.net.URLs;
import com.chetu.user.okhttp.CallBackUtil;
import com.chetu.user.okhttp.OkhttpUtil;
import com.chetu.user.popupwindow.PhotoShowDialog;
import com.chetu.user.utils.CommonUtil;
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
 * 询价订单
 */
public class WaitingReleaseActivity extends BaseActivity {
    int type = 1, is_ok = 0;// 0、待发布  1、已发布  2、全部
    LinearLayout linearLayout1, linearLayout2, linearLayout3, linearLayout4;
    TextView textView1, textView2, textView3, textView4;
    View view1, view2, view3, view4;
    int page = 0;
    private RecyclerView recyclerView;
    List<WaitingReleaseModel.ListBean> list = new ArrayList<>();
    CommonAdapter<WaitingReleaseModel.ListBean> mAdapter;

    //救援数据
    List<WaitingReleaseModel_JiuYuan.ListBean> list_JiuYuan = new ArrayList<>();
    CommonAdapter<WaitingReleaseModel_JiuYuan.ListBean> mAdapter_JiuYuan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waitingrelease);
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
                if (type == 4) {
                    params.put("is_wo", "1");
                    RequestJiuYuan(params);
                } else {
                    params.put("is_ok", is_ok + "");
                    Request(params);
                }
            }

            @Override
            public void onLoadmore() {
                page++;
                Map<String, String> params = new HashMap<>();
                params.put("page", page + "");
                params.put("u_token", localUserInfo.getToken());
                if (type == 4) {
                    params.put("is_wo", "1");
                    RequestJiuYuanMore(params);
                } else {
                    params.put("is_ok", is_ok + "");
                    RequestMore(params);
                }
            }
        });

        recyclerView = findViewByID_My(R.id.recyclerView);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLinearLayoutManager);

        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
        textView4 = findViewByID_My(R.id.textView4);

        view1 = findViewByID_My(R.id.view1);
        view2 = findViewByID_My(R.id.view2);
        view3 = findViewByID_My(R.id.view3);
        view4 = findViewByID_My(R.id.view4);
    }

    @Override
    protected void initData() {
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
        if (type == 4) {
            params.put("is_wo", "1");
            RequestJiuYuan(params);
        } else {
            params.put("is_ok", is_ok + "");
            Request(params);
        }
    }

    private void Request(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.WaitingRelease, params, headerMap, new CallBackUtil<WaitingReleaseModel>() {
            @Override
            public WaitingReleaseModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                showEmptyPage();
//                myToast(err);
            }

            @Override
            public void onResponse(WaitingReleaseModel response) {
                hideProgress();
                list = response.getList();
                if (list.size() > 0) {
                    showContentPage();
                    mAdapter = new CommonAdapter<WaitingReleaseModel.ListBean>
                            (WaitingReleaseActivity.this, R.layout.item_waitingrelease, list) {
                        @Override
                        protected void convert(ViewHolder holder, WaitingReleaseModel.ListBean model, int position) {
                            holder.setText(R.id.tv_title, model.getUser_sedan_info().getSName());
                            holder.setText(R.id.tv_num, model.getUser_sedan_info().getSNumber());
                            holder.setText(R.id.tv_content, model.getServiceName());
                            holder.setText(R.id.tv_time, model.getCreateDate());
                            holder.setText(R.id.tv_qingkuangshuoming,"情况说明："+model.getvMsg());

                            TextView tv_delete = holder.getView(R.id.tv_delete);
                            TextView tv_fabu = holder.getView(R.id.tv_fabu);
                            TextView tv_baojia = holder.getView(R.id.tv_baojia);
                            switch (model.getIsOk()) {
                                case 0:
                                    //待发布
                                    tv_delete.setVisibility(View.VISIBLE);
                                    tv_fabu.setVisibility(View.VISIBLE);
                                    tv_baojia.setVisibility(View.VISIBLE);
                                    break;
                                case 1:
                                    //已发布
                                    tv_delete.setVisibility(View.VISIBLE);
                                    tv_fabu.setVisibility(View.GONE);
                                    tv_baojia.setVisibility(View.VISIBLE);
                                    break;
                            }
                            tv_delete.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //删除
                                    showToast("确认删除该询价吗？", "确认", "取消", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            dialog.dismiss();
                                            showProgress(true, "正在删除...");
                                            HashMap<String, String> params2 = new HashMap<>();
                                            params2.put("y_inquiry_demand_id", model.getYInquiryDemandId());
                                            params2.put("u_token", localUserInfo.getToken());
                                            RequestDelete(params2);
                                        }
                                    }, new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            dialog.dismiss();
                                        }
                                    });
                                }
                            });
                            tv_fabu.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    showToast("确认发布该询价吗？", "确认", "取消", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            dialog.dismiss();
                                            showProgress(true, "正在发布...");
                                            HashMap<String, String> params2 = new HashMap<>();
                                            params2.put("y_inquiry_demand_id", model.getYInquiryDemandId());
                                            params2.put("u_token", localUserInfo.getToken());
                                            RequestFaBu(params2);
                                        }
                                    }, new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            dialog.dismiss();
                                        }
                                    });
                                }
                            });
                            tv_baojia.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //报价
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("detail", model);
                                    CommonUtil.gotoActivityWithData(WaitingReleaseActivity.this, QuotedPriceActivity.class, bundle, false);
                                }
                            });

                        }
                    };
                    mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("detail", list.get(i));
                            CommonUtil.gotoActivityWithData(WaitingReleaseActivity.this, QuotedPriceActivity.class, bundle, false);
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
        OkhttpUtil.okHttpPost(URLs.WaitingRelease, params, headerMap, new CallBackUtil<WaitingReleaseModel>() {
            @Override
            public WaitingReleaseModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
                page--;
            }

            @Override
            public void onResponse(WaitingReleaseModel response) {
                hideProgress();
                List<WaitingReleaseModel.ListBean> list1 = new ArrayList<>();
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
     * 救援数据
     */
    private void RequestJiuYuan(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.WaitingRelease_JiuYuan, params, headerMap, new CallBackUtil<WaitingReleaseModel_JiuYuan>() {
            @Override
            public WaitingReleaseModel_JiuYuan onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                showEmptyPage();
//                myToast(err);
            }

            @Override
            public void onResponse(WaitingReleaseModel_JiuYuan response) {
                hideProgress();
                list_JiuYuan = response.getList();
                if (list_JiuYuan.size() > 0) {
                    showContentPage();
                    mAdapter_JiuYuan = new CommonAdapter<WaitingReleaseModel_JiuYuan.ListBean>
                            (WaitingReleaseActivity.this, R.layout.item_waitingrelease_jiuyuan, list_JiuYuan) {
                        @Override
                        protected void convert(ViewHolder holder, WaitingReleaseModel_JiuYuan.ListBean model, int position) {
                            holder.setText(R.id.tv_carnum, model.getUser_sedan_info().getSNumber());//车牌
                            holder.setText(R.id.tv_name, model.getFullName());//昵称
                            holder.setText(R.id.tv_type, "类型：" + model.getMType());//类型
                            holder.setText(R.id.tv_content, "描述：" + model.getCarCondition());//描述
                            holder.setText(R.id.tv_addr, "地址：" + model.getAddress());//地址

                            ArrayList<String> images = new ArrayList<>();
                            for (String s : model.getImgArr()) {
                                images.add(URLs.IMGHOST + s);
                            }
                            RecyclerView rv = holder.getView(R.id.rv);
                            LinearLayoutManager llm1 = new LinearLayoutManager(WaitingReleaseActivity.this);
                            llm1.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
                            rv.setLayoutManager(llm1);
                            CommonAdapter<String> ca = new CommonAdapter<String>
                                    (WaitingReleaseActivity.this, R.layout.item_img_80_80, images) {
                                @Override
                                protected void convert(ViewHolder holder, String s, int item) {
                                    ImageView iv = holder.getView(R.id.iv);
                                    Glide.with(WaitingReleaseActivity.this).load(s)
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
                                    PhotoShowDialog photoShowDialog = new PhotoShowDialog(WaitingReleaseActivity.this, images, i);
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
                    mAdapter_JiuYuan.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("jiuyuan", list_JiuYuan.get(i));
                            CommonUtil.gotoActivityWithData(WaitingReleaseActivity.this, JiuYuanActivity.class, bundle, false);
                        }

                        @Override
                        public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            return false;
                        }
                    });
                    recyclerView.setAdapter(mAdapter_JiuYuan);

                } else {
                    showEmptyPage();
                }
            }
        });
    }

    private void RequestJiuYuanMore(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.WaitingRelease_JiuYuan, params, headerMap, new CallBackUtil<WaitingReleaseModel_JiuYuan>() {
            @Override
            public WaitingReleaseModel_JiuYuan onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
                page--;
            }

            @Override
            public void onResponse(WaitingReleaseModel_JiuYuan response) {
                hideProgress();
                List<WaitingReleaseModel_JiuYuan.ListBean> list1 = new ArrayList<>();
                list1 = response.getList();
                if (list1.size() == 0) {
                    page--;
                    myToast(getString(R.string.app_nomore));
                } else {
                    list_JiuYuan.addAll(list1);
                    mAdapter_JiuYuan.notifyDataSetChanged();
                }
            }
        });
    }
    /**
     * 删除
     *
     * @param params
     */
    private void RequestDelete(HashMap<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.DeleteXunJia, params, headerMap, new CallBackUtil<Object>() {
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
                myToast("删除成功");
                requestServer();
            }
        });
    }


    /**
     * 发布
     *
     * @param params
     */
    private void RequestFaBu(HashMap<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.FaBuXunJia, params, headerMap, new CallBackUtil<Object>() {
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
                myToast("发布成功");
                requestServer();
            }
        });
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.linearLayout1:
                //全部
                type = 1;
                is_ok = 2;
                changeUI();
                break;
            case R.id.linearLayout2:
                //待发布
                type = 2;
                is_ok = 0;
                changeUI();
                break;
            case R.id.linearLayout3:
                //已发布
                type = 3;
                is_ok = 1;
                changeUI();
                break;
            case R.id.linearLayout4:
                //救援列表
                type = 4;
                is_ok = 4;
                changeUI();
                break;
        }
    }

    private void changeUI() {
        switch (type) {
            case 1:
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.INVISIBLE);
                view3.setVisibility(View.INVISIBLE);
                view4.setVisibility(View.INVISIBLE);
                requestServer();
                break;
            case 2:
                view1.setVisibility(View.INVISIBLE);
                view2.setVisibility(View.VISIBLE);
                view3.setVisibility(View.INVISIBLE);
                view4.setVisibility(View.INVISIBLE);
                requestServer();
                break;
            case 3:
                view1.setVisibility(View.INVISIBLE);
                view2.setVisibility(View.INVISIBLE);
                view3.setVisibility(View.VISIBLE);
                view4.setVisibility(View.INVISIBLE);
                requestServer();
                break;
            case 4:
                view1.setVisibility(View.INVISIBLE);
                view2.setVisibility(View.INVISIBLE);
                view3.setVisibility(View.INVISIBLE);
                view4.setVisibility(View.VISIBLE);
                requestServer();
                break;
        }
    }

    @Override
    protected void updateView() {
        titleView.setTitle("询价订单");
    }
}
