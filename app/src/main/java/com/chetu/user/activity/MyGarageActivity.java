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
import com.chetu.user.model.MyGarageModel;
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
 * Created by zyz on 2020/5/26.
 * 我的车库
 */
public class MyGarageActivity extends BaseActivity {
    int type = 0;
    int page = 0;
    private RecyclerView recyclerView;
    List<MyGarageModel.ListBean> list = new ArrayList<>();
    CommonAdapter<MyGarageModel.ListBean> mAdapter;

    TextView tv_addcar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mygarage);
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
        //刷新
        setSpringViewMore(false);//不需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
//                page = 0;
                Map<String, String> params = new HashMap<>();
//                params.put("page", page + "");
                params.put("u_token", localUserInfo.getToken());
                Request(params);
            }

            @Override
            public void onLoadmore() {
                /*page++;
                Map<String, String> params = new HashMap<>();
                params.put("u_token", localUserInfo.getToken());
                params.put("page", page + "");
                RequestMore(params);*/
            }
        });

        recyclerView = findViewByID_My(R.id.recyclerView);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLinearLayoutManager);

        tv_addcar = findViewByID_My(R.id.tv_addcar);
        tv_addcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("y_user_sedan_id", "");
                CommonUtil.gotoActivityWithData(MyGarageActivity.this, AddCarActivity.class, bundle, false);
            }
        });
    }

    @Override
    protected void initData() {
        type = getIntent().getIntExtra("type", 0);
    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
//        page = 0;
        Map<String, String> params = new HashMap<>();
//        params.put("page", page + "");
        params.put("u_token", localUserInfo.getToken());
        Request(params);
    }

    private void Request(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.MyCar, params, headerMap, new CallBackUtil<MyGarageModel>() {
            @Override
            public MyGarageModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                showEmptyPage();
//                myToast(err);
            }

            @Override
            public void onResponse(MyGarageModel response) {
                hideProgress();
                list = response.getList();
                if (list.size() > 0) {
                    showContentPage();
                    mAdapter = new CommonAdapter<MyGarageModel.ListBean>
                            (MyGarageActivity.this, R.layout.item_mygarage, list) {
                        @Override
                        protected void convert(ViewHolder holder, MyGarageModel.ListBean model, int position) {
                            //logo
                            ImageView imageView1 = holder.getView(R.id.imageView1);
                            Glide.with(MyGarageActivity.this).load(URLs.IMGHOST + model.getSLogo())
                                    .centerCrop()
//                            .placeholder(R.mipmap.headimg)//加载站位图
//                            .error(R.mipmap.headimg)//加载失败
                                    .into(imageView1);//加载图片


                            /*if (model.getBrandInfo().getGroupName() != null && model.getBrandInfo().getSeriesName() != null){
                                holder.setText(R.id.tv_carname, model.getBrandInfo().getGroupName()
                                        + "-" + model.getBrandInfo().getSeriesName());//车品牌
                            }else {
                                holder.setText(R.id.tv_carname, model.getBrandInfo().getBrandName());//车品牌
                            }*/

                            holder.setText(R.id.tv_carname, model.getSName());//车品牌

                            holder.setText(R.id.tv_carnum, model.getSNumber());//车牌
                            holder.setText(R.id.tv_cardetail, model.getBrandInfo().getSName());//具体型号
                            //是否默认
                            TextView tv_moren = holder.getView(R.id.tv_moren);
                            if (model.getIsF() == 1)
                                tv_moren.setVisibility(View.VISIBLE);
                            else
                                tv_moren.setVisibility(View.GONE);
                            //是否填写保险
                            LinearLayout ll_baoxian = holder.getView(R.id.ll_baoxian);
                            if (model.getPoliceInfo() == null && model.getJpoliceInfo() == null) {
                                ll_baoxian.setVisibility(View.GONE);
                            } else {
                                //商业险
                                if (model.getPoliceInfo() != null) {
                                    holder.setText(R.id.tv_shangyexian, "商业险：" + model.getPoliceInfo().getVName());
                                    holder.setText(R.id.tv_syphone, "电话：" + model.getPoliceInfo().getTelephone());
                                }
                                //交强险
                                if (model.getJpoliceInfo() != null) {
                                    holder.setText(R.id.tv_jiaoqiangxian, "交强险：" + model.getJpoliceInfo().getVName());
                                    holder.setText(R.id.tv_jqphone, "电话：" + model.getJpoliceInfo().getTelephone());
                                }
                            }
                            //归属
                            if (model.getSCy() == 1)
                                holder.setText(R.id.tv_guishu, "归属：个人");
                            else
                                holder.setText(R.id.tv_guishu, "归属：公司");

                            //编辑
                            holder.getView(R.id.tv_edit).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Bundle bundle = new Bundle();
                                    bundle.putString("y_user_sedan_id", model.getYUserSedanId());
                                    CommonUtil.gotoActivityWithData(MyGarageActivity.this, AddCarActivity.class, bundle, false);
                                }
                            });
                            //删除
                            holder.getView(R.id.tv_delete).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    showToast("确认删除该车辆吗？", "确认", "取消", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            dialog.dismiss();
                                            showProgress(true, "正在删除...");
                                            HashMap<String, String> params2 = new HashMap<>();
                                            params2.put("y_user_sedan_id", model.getYUserSedanId());
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

                            holder.getView(R.id.linearLayout1).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (type == 10001) {
                                        //保存
                                       /* localUserInfo.setCarname(model.getBrandInfo().getGroupName()
                                                + "-" + model.getBrandInfo().getSeriesName());*/
//                                        localUserInfo.setCarname(model.getSName());
                                        localUserInfo.setCarname(model.getBrandInfo().getBrandName());
                                        localUserInfo.setCarnum(model.getSNumber());
                                        localUserInfo.setCardetail(model.getBrandInfo().getSName());
                                        localUserInfo.setCarid(model.getYUserSedanId());
                                        localUserInfo.setCarlogo(model.getSLogo());


                                        Intent resultIntent = new Intent();
                                        Bundle bundle = new Bundle();
                                        /*bundle.putString("carname", model.getBrandInfo().getGroupName()
                                                + "-" + model.getBrandInfo().getSeriesName());*/
//                                        bundle.putString("carname", model.getSName());
                                        bundle.putString("carname", model.getBrandInfo().getBrandName());
                                        bundle.putString("carnum", model.getSNumber());
                                        bundle.putString("cardetail", model.getBrandInfo().getSName());
                                        bundle.putString("car_id", model.getYUserSedanId());
                                        bundle.putString("carlogo", model.getSLogo());
                                        resultIntent.putExtras(bundle);
                                        MyGarageActivity.this.setResult(RESULT_OK, resultIntent);
                                        finish();
                                    }

                                }
                            });

                        }
                    };
                    recyclerView.setAdapter(mAdapter);
                } else {
                    showEmptyPage();
                }
            }
        });
    }

    /**
     * 删除车辆
     *
     * @param params
     */
    private void RequestDelete(HashMap<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.DeleteCar, params, headerMap, new CallBackUtil<Object>() {
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
     * 加载更多-暂时无用
     *
     * @param params
     */
    private void RequestMore(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.MyCar, params, headerMap, new CallBackUtil<MyGarageModel>() {
            @Override
            public MyGarageModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
                page--;
            }

            @Override
            public void onResponse(MyGarageModel response) {
                hideProgress();
                List<MyGarageModel.ListBean> list1 = new ArrayList<>();
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
    protected void updateView() {
        titleView.setTitle("我的车库");
        titleView.setTitleColor(R.color.white);
        titleView.setLeftBtn(R.mipmap.ic_return_white, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleView.setBackground(R.color.blue);
        titleView.showRightTextview("编辑", R.color.white, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
