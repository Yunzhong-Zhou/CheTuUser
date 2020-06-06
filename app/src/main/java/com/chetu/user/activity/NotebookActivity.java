package com.chetu.user.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chetu.user.R;
import com.chetu.user.base.BaseActivity;
import com.chetu.user.model.NotebookModel;
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
 * Created by zyz on 2020/5/26.
 * 记事本
 */
public class NotebookActivity extends BaseActivity {
    int page = 0;
    private RecyclerView recyclerView;
    List<NotebookModel.ListBean> list = new ArrayList<>();
    CommonAdapter<NotebookModel.ListBean> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notebook);
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

    String year_temp = "";

    private void Request(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Notebook, params, headerMap, new CallBackUtil<NotebookModel>() {
            @Override
            public NotebookModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                showEmptyPage();
                myToast(err);
            }

            @Override
            public void onResponse(NotebookModel response) {
                hideProgress();
                list = response.getList();
                if (list.size() > 0) {
                    showContentPage();
                    mAdapter = new CommonAdapter<NotebookModel.ListBean>
                            (NotebookActivity.this, R.layout.item_notebook, list) {
                        @Override
                        protected void convert(ViewHolder holder, NotebookModel.ListBean model, int position) {
                            //拆分日期  年     月日    \n    时分秒
                            String[] strArr1 = model.getCreateDate().split(" ");//拆分空格把日期和时间分开
                            String[] strArr2 = strArr1[0].split("-");//拆分日期 得到年月日
                            String year = strArr2[0];//年
                            String day = strArr2[1] + "月" + strArr2[2] + "日";//提取月日
                            String time = strArr1[1];//时间
                            holder.setText(R.id.tv_time, day + "\n" + time);
                            //是否显示年
                            LinearLayout ll_year = holder.getView(R.id.ll_year);
                            TextView tv_year = holder.getView(R.id.tv_year);
                            //如果是第0个那么一定显示#号
                            if (position == 0) {
                                year_temp = year;
                                ll_year.setVisibility(View.VISIBLE);
                                tv_year.setText(year);
                            } else {
                                //如果和上一个item的首字母不同，则认为是新分类的开始
                                if (!year.equals(year_temp)) {
                                    ll_year.setVisibility(View.VISIBLE);
                                    tv_year.setText(year);

                                    year_temp = year;
                                } else {
                                    ll_year.setVisibility(View.GONE);
                                }
                            }
                            holder.setText(R.id.tv_title, model.getYTitle());
                            holder.setText(R.id.tv_content, model.getIMsg());
                            holder.setText(R.id.tv_moeny, "¥"+model.getVMoney());
                        }
                    };
                    mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {

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
        OkhttpUtil.okHttpPost(URLs.Notebook, params, headerMap, new CallBackUtil<NotebookModel>() {
            @Override
            public NotebookModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
                page--;
            }

            @Override
            public void onResponse(NotebookModel response) {
                hideProgress();
                List<NotebookModel.ListBean> list1 = new ArrayList<>();
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
        titleView.setTitle("记事本");
        titleView.setRightBtn(R.mipmap.ic_add_blue, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtil.gotoActivity(NotebookActivity.this, AddNotebookActivity.class, false);
            }
        });
    }
}
