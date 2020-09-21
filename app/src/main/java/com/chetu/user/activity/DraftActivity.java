package com.chetu.user.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.chetu.user.R;
import com.chetu.user.base.BaseActivity;
import com.chetu.user.model.DraftListModel;
import com.chetu.user.net.URLs;
import com.chetu.user.okhttp.CallBackUtil;
import com.chetu.user.okhttp.OkhttpUtil;
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
 * 草稿
 */
public class DraftActivity extends BaseActivity {
    private RecyclerView recyclerView;
    List<DraftListModel.ListBean> list = new ArrayList<>();
    CommonAdapter<DraftListModel.ListBean> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draft);
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
                Map<String, String> params = new HashMap<>();
                params.put("u_token", localUserInfo.getToken());
                Request(params);
            }

            @Override
            public void onLoadmore() {
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
        Map<String, String> params = new HashMap<>();
        params.put("u_token", localUserInfo.getToken());
        Request(params);
    }

    private void Request(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.DraftList, params, headerMap, new CallBackUtil<DraftListModel>() {
            @Override
            public DraftListModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                showEmptyPage();
                myToast(err);
            }

            @Override
            public void onResponse(DraftListModel response) {
                hideProgress();
                list = response.getList();
                if (list.size() > 0) {
                    showContentPage();
                    mAdapter = new CommonAdapter<DraftListModel.ListBean>
                            (DraftActivity.this, R.layout.item_draft, list) {
                        @Override
                        protected void convert(ViewHolder holder, DraftListModel.ListBean model, int position) {
                            holder.setText(R.id.tv_tabs, model.getVStrs());
                            String[] strArr = model.getVStrs().split("\\|\\|");
                            holder.setText(R.id.tv_yixuan, "已选：" + strArr.length + "项");

                            List<String> arrList = new ArrayList<>();
                            for (String s : strArr) {
                                arrList.add(s);
                            }
                            RecyclerView rv = holder.getView(R.id.rv);
                            rv.setLayoutManager(new LinearLayoutManager(DraftActivity.this));
                            CommonAdapter<String> ca = new CommonAdapter<String>
                                    (DraftActivity.this, R.layout.item_draft_item, arrList) {
                                @Override
                                protected void convert(ViewHolder holder, String s, int i) {
                                    holder.setText(R.id.title, s);
                                    /*holder.getView(R.id.delete).setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            arrList.remove(i);
                                            ca.notifyDataSetChanged();
                                        }
                                    });*/
                                }
                            };
                            rv.setAdapter(ca);

                            ImageView iv_tab = holder.getView(R.id.iv_tab);
                            iv_tab.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (rv.getVisibility() == View.VISIBLE){
                                        rv.setVisibility(View.GONE);
                                        iv_tab.setImageResource(R.mipmap.ic_next_black);
                                    }else {
                                        rv.setVisibility(View.VISIBLE);
                                        iv_tab.setImageResource(R.mipmap.ic_down_black);
                                    }
                                }
                            });

                            holder.getView(R.id.tv_delete).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //删除
                                    showToast("确认删除该草稿吗？", "确认", "取消", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            dialog.dismiss();
                                            showProgress(true, "正在删除，请稍候...");
                                            Map<String, String> params = new HashMap<>();
                                            params.put("u_token", localUserInfo.getToken());
                                            params.put("y_draft_id", model.getYDraftId());
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
                            holder.getView(R.id.tv_pipei).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //匹配商家
                                    Intent intent1 = new Intent(DraftActivity.this, SelectStoreActivity.class);
                                    Bundle bundle1 = new Bundle();
                                    bundle1.putInt("type", 0);
                                    bundle1.putString("service_name", model.getVStrs());
                                    intent1.putExtras(bundle1);
                                    startActivityForResult(intent1, 10001, bundle1);
                                }
                            });
                        }
                    };
                    mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            finish();
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
     * 删除草稿
     *
     * @param params
     */
    private void RequestDelete(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.DeleteDraft, params, headerMap, new CallBackUtil() {
            @Override
            public Object onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                if (!err.equals("")) {
                    showToast(err);
                }
            }

            @Override
            public void onResponse(Object response) {
                hideProgress();
                myToast("删除成功");
                requestServer();
            }
        });
    }

    @Override
    protected void updateView() {
        titleView.setTitle("草稿");
        titleView.setBackground(R.color.background);
    }
}
