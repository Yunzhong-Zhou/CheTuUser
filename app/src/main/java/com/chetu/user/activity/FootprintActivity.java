package com.chetu.user.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.chetu.user.R;
import com.chetu.user.adapter.FootpriintAdapter;
import com.chetu.user.base.BaseActivity;
import com.chetu.user.model.CollectModel;
import com.chetu.user.model.FootprintModel;
import com.chetu.user.net.URLs;
import com.chetu.user.okhttp.CallBackUtil;
import com.chetu.user.okhttp.OkhttpUtil;
import com.chetu.user.utils.MyLogger;
import com.donkingliang.groupedadapter.adapter.GroupedRecyclerViewAdapter;
import com.donkingliang.groupedadapter.holder.BaseViewHolder;
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
 * Created by zyz on 2020/5/26.
 * 足迹
 */
public class FootprintActivity extends BaseActivity {
    int type = 1, page1 = 0, page2 = 0, category = 1;//1为商品  2为商家
    TextView textView1, textView2;
    private RecyclerView recyclerView;
    List<FootprintModel> list1 = new ArrayList<>();
    //    CommonAdapter<FootprintModel.ListBean> mAdapter1;
    FootpriintAdapter mAdapter1;

    List<CollectModel.ListBean> list2 = new ArrayList<>();
    CommonAdapter<CollectModel.ListBean> mAdapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_footprint);
        mImmersionBar.reset()
                .statusBarColor(R.color.white)
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
                Map<String, String> params = new HashMap<>();
                params.put("u_token", localUserInfo.getToken());
                params.put("category", category + "");
                if (type == 1) {
                    page1 = 0;
                    params.put("page", page1 + "");
                    Request1(params);
                } else {
                    page2 = 0;
                    params.put("page", page2 + "");
                    Request2(params);
                }

            }

            @Override
            public void onLoadmore() {
                Map<String, String> params = new HashMap<>();
                params.put("u_token", localUserInfo.getToken());
                params.put("category", category + "");
                if (type == 1) {
                    page1++;
                    params.put("page", page1 + "");
                    RequestMore1(params);
                } else {
                    page2++;
                    params.put("page", page2 + "");
                    RequestMore2(params);
                }
            }
        });
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);

        recyclerView = findViewByID_My(R.id.recyclerView);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLinearLayoutManager);


        //测试
        list1.add(new FootprintModel());
        list1.add(new FootprintModel());
        list1.add(new FootprintModel());
        list1.add(new FootprintModel());

        mAdapter1 = new FootpriintAdapter(FootprintActivity.this, list1);
        mAdapter1.setOnHeaderClickListener(new GroupedRecyclerViewAdapter.OnHeaderClickListener() {
            @Override
            public void onHeaderClick(GroupedRecyclerViewAdapter adapter, BaseViewHolder holder,
                                      int groupPosition) {
            }
        });


        mAdapter1.setOnChildClickListener(new GroupedRecyclerViewAdapter.OnChildClickListener() {
            @Override
            public void onChildClick(GroupedRecyclerViewAdapter adapter, BaseViewHolder holder,
                                     int groupPosition, int childPosition) {
            }
        });
        recyclerView.setAdapter(mAdapter1);
    }

    @Override
    protected void initData() {
//        requestServer();



        /*mAdapter1 = new CommonAdapter<FootprintModel.ListBean>
                (FootprintActivity.this, R.layout.item_footprint_title, list1) {
            @Override
            protected void convert(ViewHolder holder, FootprintModel.ListBean model, int position) {
                RecyclerView rv = holder.getView(R.id.rv);
                rv.setLayoutManager(new LinearLayoutManager(FootprintActivity.this));
                List<String> list = new ArrayList<>();
                list.add("");
                list.add("");
                list.add("");
                list.add("");
                CommonAdapter<String> mAdapter = new CommonAdapter<String>(FootprintActivity.this, R.layout.item_footprint, list) {
                    @Override
                    protected void convert(ViewHolder holder, String s, int position) {

                    }
                };
                rv.setAdapter(mAdapter);
            }
        };
        mAdapter1.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {

            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                return false;
            }
        });
        recyclerView.setAdapter(mAdapter1);*/

    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        Map<String, String> params = new HashMap<>();
        params.put("u_token", localUserInfo.getToken());
        params.put("category", category + "");
        if (type == 1) {
            page1 = 0;
            params.put("page", page1 + "");
            Request1(params);
        } else {
            page2 = 0;
            params.put("page", page2 + "");
            Request2(params);
        }
    }

    /**
     * 足迹
     *
     * @param params
     */
    private void Request1(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Footprint, params, headerMap, new CallBackUtil<FootprintModel>() {
            @Override
            public FootprintModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                showEmptyPage();
                myToast(err);
            }

            @Override
            public void onResponse(FootprintModel response) {
                hideProgress();
                if (list1.size() > 0) {
                    showContentPage();

                } else {
                    showEmptyPage();
                }
            }
        });

    }

    private void RequestMore1(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Footprint, params, headerMap, new CallBackUtil<FootprintModel>() {
            @Override
            public FootprintModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
                page1--;
            }

            @Override
            public void onResponse(FootprintModel response) {
                hideProgress();
                /*List<FootprintModel> list_1 = new ArrayList<>();
                list_1 = response.getList();
                if (list_1.size() == 0) {
                    page1--;
                    myToast(getString(R.string.app_nomore));
                } else {
                    list1.addAll(list_1);
//                    mAdapter1.notifyDataSetChanged();
                }*/
            }
        });
    }

    /**
     * 收藏
     *
     * @param params
     */
    private void Request2(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Collect, params, headerMap, new CallBackUtil<CollectModel>() {
            @Override
            public CollectModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                showEmptyPage();
                myToast(err);
            }

            @Override
            public void onResponse(CollectModel response) {
                hideProgress();
                list2 = response.getList();
                MyLogger.i(">>>>>>>>" + list2.size());
                if (list2.size() > 0) {
                    showContentPage();
                    mAdapter2 = new CommonAdapter<CollectModel.ListBean>
                            (FootprintActivity.this, R.layout.item_collect, list2) {
                        @Override
                        protected void convert(ViewHolder holder, CollectModel.ListBean model, int position) {

                        }
                    };
                    mAdapter2.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {

                        }

                        @Override
                        public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            return false;
                        }
                    });
                    recyclerView.setAdapter(mAdapter2);
                } else {
                    showEmptyPage();
                }
            }
        });

    }

    private void RequestMore2(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Footprint, params, headerMap, new CallBackUtil<CollectModel>() {
            @Override
            public CollectModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
                page1--;
            }

            @Override
            public void onResponse(CollectModel response) {
                hideProgress();
                List<CollectModel.ListBean> list_1 = new ArrayList<>();
                list_1 = response.getList();
                if (list_1.size() == 0) {
                    page1--;
                    myToast(getString(R.string.app_nomore));
                } else {
                    list2.addAll(list_1);
//                    mAdapter1.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.left_btn:
                finish();
                break;
            case R.id.textView1:
                //足迹
                type = 1;
                textView1.setTextColor(getResources().getColor(R.color.white));
                textView1.setBackgroundResource(R.drawable.yuanjiao_5_lanse_left);
                textView2.setTextColor(getResources().getColor(R.color.black));
                textView2.setBackgroundResource(R.drawable.yuanjiaobiankuang_5_huise_right);
                requestServer();
                break;
            case R.id.textView2:
                //收藏
                type = 2;
                textView1.setTextColor(getResources().getColor(R.color.black));
                textView1.setBackgroundResource(R.drawable.yuanjiaobiankuang_5_huise_left);
                textView2.setTextColor(getResources().getColor(R.color.white));
                textView2.setBackgroundResource(R.drawable.yuanjiao_5_lanse_right);
                requestServer();
                break;
        }
    }

    @Override
    protected void updateView() {
        titleView.setVisibility(View.GONE);
    }
}
