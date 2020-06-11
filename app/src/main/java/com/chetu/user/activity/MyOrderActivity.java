package com.chetu.user.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.chetu.user.R;
import com.chetu.user.base.BaseActivity;
import com.chetu.user.model.Fragment2Model;
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
 * Created by zyz on 2020/5/27.
 * 我的订单
 */
public class MyOrderActivity extends BaseActivity {
    int type = 1;
    int page = 0;
    private RecyclerView recyclerView;
    List<Fragment2Model.ListBean> list = new ArrayList<>();
    CommonAdapter<Fragment2Model.ListBean> mAdapter;

    TextView tv_type1,tv_type2,tv_type3,tv_type4,tv_type5,tv_type6,tv_type7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorder);
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
                params.put("g_state", type + "");
                params.put("u_token", localUserInfo.getToken());
                Request(params);
            }

            @Override
            public void onLoadmore() {
                page++;
                Map<String, String> params = new HashMap<>();
                params.put("u_token", localUserInfo.getToken());
                params.put("page", page + "");
                params.put("g_state", type + "");
                RequestMore(params);
            }
        });

        recyclerView = findViewByID_My(R.id.recyclerView);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLinearLayoutManager);

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
        OkhttpUtil.okHttpPost(URLs.MyOrder, params, headerMap, new CallBackUtil<Fragment2Model>() {
            @Override
            public Fragment2Model onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                showEmptyPage();
//                myToast(err);
            }

            @Override
            public void onResponse(Fragment2Model response) {
                hideProgress();
                list = response.getList();
                if (list.size() > 0) {
                    showContentPage();
                    mAdapter = new CommonAdapter<Fragment2Model.ListBean>
                            (MyOrderActivity.this, R.layout.item_myorder, list) {
                        @Override
                        protected void convert(ViewHolder holder, Fragment2Model.ListBean model, int position) {
                       /* TextView tv1 = holder.getView(R.id.tv1);
                        TextView tv2 = holder.getView(R.id.tv2);
                        LinearLayout ll = holder.getView(R.id.ll);
                        tv1.setText(model.getName());
                        tv2.setText(model.getName());

                        if (item == position) {
                            ll.setVisibility(View.VISIBLE);
                            tv1.setVisibility(View.GONE);
                        } else {
                            ll.setVisibility(View.GONE);
                            tv1.setVisibility(View.VISIBLE);
                        }*/

                        }
                    };
                    mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            Bundle bundle = new Bundle();
                            switch (type) {
                                case 1:
                                    //待接车
                                    bundle.putString("id", list.get(i).getId());
                                    CommonUtil.gotoActivityWithData(MyOrderActivity.this, DaiJieCheActivity.class, bundle, false);
                                    break;
                                case 2:
                                    //待分配
                                    bundle.putString("id", list.get(i).getId());
                                    CommonUtil.gotoActivityWithData(MyOrderActivity.this, DaiFenPeiActivity.class, bundle, false);
                                    break;
                                case 3:
                                    //待施工
                                    bundle.putString("id", list.get(i).getId());
                                    CommonUtil.gotoActivityWithData(MyOrderActivity.this, DaiShiGongActivity.class, bundle, false);
                                    break;
                                case 4:
                                    //进行中
                                    bundle.putString("id", list.get(i).getId());
                                    CommonUtil.gotoActivityWithData(MyOrderActivity.this, JinXingZhongActivity.class, bundle, false);
                                    break;
                                case 5:
                                    //待复检
                                    bundle.putString("id", list.get(i).getId());
                                    CommonUtil.gotoActivityWithData(MyOrderActivity.this, DaiFuJianActivity.class, bundle, false);
                                    break;
                                case 6:
                                    //已完工
                                    bundle.putString("id", list.get(i).getId());
                                    CommonUtil.gotoActivityWithData(MyOrderActivity.this, YiWanGongActivity.class, bundle, false);
                                    break;
                                case 7:
                                    //已提车
                                    bundle.putString("id", list.get(i).getId());
                                    CommonUtil.gotoActivityWithData(MyOrderActivity.this, YiTiCheActivity.class, bundle, false);
                                    break;
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
        OkhttpUtil.okHttpPost(URLs.Fragment3, params, headerMap, new CallBackUtil<Fragment2Model>() {
            @Override
            public Fragment2Model onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
                page--;
            }

            @Override
            public void onResponse(Fragment2Model response) {
                hideProgress();
                List<Fragment2Model.ListBean> list1 = new ArrayList<>();
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
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.tv_type1:
                //待接车
                type = 1;
                tv_type1.setBackgroundResource(R.mipmap.bg_myorder_tab1_1);
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
                tv_type1.setBackgroundResource(R.mipmap.bg_myorder_tab1_0);
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
                tv_type1.setBackgroundResource(R.mipmap.bg_myorder_tab1_0);
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
                tv_type1.setBackgroundResource(R.mipmap.bg_myorder_tab1_0);
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
                tv_type1.setBackgroundResource(R.mipmap.bg_myorder_tab1_0);
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
                tv_type1.setBackgroundResource(R.mipmap.bg_myorder_tab1_0);
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
                tv_type1.setBackgroundResource(R.mipmap.bg_myorder_tab1_0);
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
}
