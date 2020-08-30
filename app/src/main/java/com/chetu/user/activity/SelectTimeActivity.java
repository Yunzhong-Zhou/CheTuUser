package com.chetu.user.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chetu.user.R;
import com.chetu.user.base.BaseActivity;
import com.chetu.user.model.SelectTimeModel;
import com.chetu.user.net.URLs;
import com.chetu.user.okhttp.CallBackUtil;
import com.chetu.user.okhttp.OkhttpUtil;
import com.chetu.user.utils.DateUtils;
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
 * Created by zyz on 2020/7/7.
 */
public class SelectTimeActivity extends BaseActivity {
    String v_date = "", y_store_id = "", select_time = "";
    RecyclerView rv_day;
    List<String> list_day = new ArrayList<>();
    CommonAdapter<String> mAdapter_day;

    RecyclerView rv_time;
    List<SelectTimeModel.ListBean> list_time = new ArrayList<>();
    CommonAdapter<SelectTimeModel.ListBean> mAdapter_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecttime);
    }

    @Override
    protected void initView() {
        rv_day = findViewByID_My(R.id.rv_day);
        rv_day.setLayoutManager(new LinearLayoutManager(this));

        rv_time = findViewByID_My(R.id.rv_time);
        rv_time.setLayoutManager(new GridLayoutManager(this, 2));
    }

    @Override
    protected void initData() {
        y_store_id = getIntent().getStringExtra("y_store_id");
        //获取从今往后7天日期数据
        list_day = DateUtils.get7date();
        v_date = list_day.get(0);
        requestServer();
        mAdapter_day = new CommonAdapter<String>(SelectTimeActivity.this, R.layout.item_selecttime_day, list_day) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                View view = holder.getView(R.id.view);
                TextView tv_day = holder.getView(R.id.tv_day);
                tv_day.setText(s);
                if (v_date.equals(s)) {
                    view.setVisibility(View.VISIBLE);
                    tv_day.setBackgroundResource(R.color.white);
                    tv_day.setTextColor(getResources().getColor(R.color.blue));
                } else {
                    view.setVisibility(View.INVISIBLE);
                    tv_day.setBackgroundResource(R.color.bg_gray);
                    tv_day.setTextColor(getResources().getColor(R.color.black));
                }
            }
        };
        mAdapter_day.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                v_date = list_day.get(i);
                mAdapter_day.notifyDataSetChanged();
                requestServer();
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                return false;
            }
        });
        rv_day.setAdapter(mAdapter_day);

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
        params.put("v_date", v_date);
        params.put("y_store_id", y_store_id);
        Request(params);
    }

    private void Request(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.SelectTime, params, headerMap, new CallBackUtil<SelectTimeModel>() {
            @Override
            public SelectTimeModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                showEmptyPage();
//                myToast(err);
            }

            @Override
            public void onResponse(SelectTimeModel response) {
                hideProgress();
                list_time = response.getList();
                if (list_time.size()>0){
                    showContentPage();
                    mAdapter_time = new CommonAdapter<SelectTimeModel.ListBean>(SelectTimeActivity.this, R.layout.item_selecttime_time, list_time) {
                        @Override
                        protected void convert(ViewHolder holder, SelectTimeModel.ListBean model, int position) {
                            LinearLayout linearLayout = holder.getView(R.id.linearLayout);
                            TextView tv_time = holder.getView(R.id.tv_time);
                            TextView tv_type = holder.getView(R.id.tv_type);
                            tv_time.setText(model.getTTime());

                            switch (model.getVState()){
                                case 1:
                                    //已预约
                                    tv_type.setText("已预约");
                                    linearLayout.setBackgroundResource(R.drawable.yuanjiao_5_huise2);
                                    break;
                                case 2:
                                    //已满
                                    tv_type.setText("已满");
                                    linearLayout.setBackgroundResource(R.drawable.yuanjiao_5_huise2);
                                    break;
                                case 3:
                                    //显示剩余数量
                                    tv_type.setText("剩余:"+model.getVNum());
                                    linearLayout.setBackgroundResource(R.drawable.yuanjiaobiankuang_5_huise);
                                    break;
                            }

                            if (select_time.equals(model.getTTime())) {
                                linearLayout.setBackgroundResource(R.drawable.yuanjiao_5_lanse);
                                tv_time.setTextColor(getResources().getColor(R.color.white));
                                tv_type.setTextColor(getResources().getColor(R.color.white));
                            } else {
                                tv_time.setTextColor(getResources().getColor(R.color.black));
                                tv_type.setTextColor(getResources().getColor(R.color.black));
                            }
                        }
                    };
                    mAdapter_time.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            if (list_time.get(i).getVState()==3){
                                select_time = list_time.get(i).getTTime();
                                mAdapter_time.notifyDataSetChanged();
                            }else {
                                myToast("请预约其他时段");
                            }
                        }

                        @Override
                        public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            return false;
                        }
                    });
                    rv_time.setAdapter(mAdapter_time);
                }else {
                    showEmptyPage();
                }

            }
        });

    }

    @Override
    protected void updateView() {
        titleView.setTitle("选择时间");
        titleView.showRightTextview("确认", R.color.blue, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!select_time.equals("")){
                    Intent resultIntent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putString("appoin_time", v_date+" "+select_time);
                    resultIntent.putExtras(bundle);
                    SelectTimeActivity.this.setResult(RESULT_OK, resultIntent);
                    finish();
                }else {
                    myToast("请选择时间");
                }
            }
        });
    }
}
