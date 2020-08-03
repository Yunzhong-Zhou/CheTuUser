package com.chetu.user.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.chetu.user.R;
import com.chetu.user.base.BaseActivity;
import com.chetu.user.model.CarInsuranceListModel;
import com.chetu.user.net.URLs;
import com.chetu.user.okhttp.CallBackUtil;
import com.chetu.user.okhttp.OkhttpUtil;
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
 * Created by Mr.X on 2020/7/14.
 * 车险查询记录
 */
public class CarInsuranceListActivity extends BaseActivity {
    int page = 0;
    private RecyclerView recyclerView;
    List<CarInsuranceListModel.ListBean> list = new ArrayList<>();
    CommonAdapter<CarInsuranceListModel.ListBean> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carillegallist);
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
        setSpringViewMore(true);//不需要加载更多
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
        OkhttpUtil.okHttpPost(URLs.CarInsuranceList, params, headerMap, new CallBackUtil<CarInsuranceListModel>() {
            @Override
            public CarInsuranceListModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                showEmptyPage();
//                myToast(err);
            }

            @Override
            public void onResponse(CarInsuranceListModel response) {
                hideProgress();
                list = response.getList();
                if (list.size() > 0) {
                    showContentPage();
                    mAdapter = new CommonAdapter<CarInsuranceListModel.ListBean>
                            (CarInsuranceListActivity.this, R.layout.item_carillegallist, list) {
                        @Override
                        protected void convert(ViewHolder holder, CarInsuranceListModel.ListBean model, int position) {
                            ImageView iv_carlogo = holder.getView(R.id.iv_carlogo);
                           /* Glide.with(CarInsuranceListActivity.this)
                                    .load(URLs.IMGHOST + model.getUser_sedan_info().getSLogo())
                                    .centerCrop()
                                    .placeholder(R.mipmap.loading)//加载站位图
                                    .error(R.mipmap.zanwutupian)//加载失败
                                    .into(iv_carlogo);//加载图片*/
                            holder.setText(R.id.tv_carname, model.getFullName());
//                            holder.setText(R.id.tv_carnum, model.getUser_sedan_info().getSNumber());
                            holder.setText(R.id.tv_carnum, model.getLicensePlate());

                            holder.setText(R.id.tv_time, model.getCreateDate());
                            holder.setText(R.id.tv_addr, model.getVInsureCity());

                        }
                    };
                    mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            Bundle bundle = new Bundle();
                            bundle.putString("url", URLs.HOST + "/single/h5/violation_closing?y_vio_regulat_id=" + list.get(i).getYInquiryId());
                            CommonUtil.gotoActivityWithData(CarInsuranceListActivity.this, WebContentActivity.class, bundle, false);
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
        OkhttpUtil.okHttpPost(URLs.CarInsuranceList, params, headerMap, new CallBackUtil<CarInsuranceListModel>() {
            @Override
            public CarInsuranceListModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
                page--;
            }

            @Override
            public void onResponse(CarInsuranceListModel response) {
                hideProgress();
                List<CarInsuranceListModel.ListBean> list1 = new ArrayList<>();
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
        titleView.setTitle("车险询价列表");
        titleView.setBackground(R.color.background);
    }
}
