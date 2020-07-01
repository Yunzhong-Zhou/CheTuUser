package com.chetu.user.activity;

import android.os.Bundle;
import android.view.View;

import com.chetu.user.R;
import com.chetu.user.base.BaseActivity;
import com.chetu.user.model.StoreDetailModel_WenDa;
import com.chetu.user.net.URLs;
import com.chetu.user.okhttp.CallBackUtil;
import com.chetu.user.okhttp.OkhttpUtil;
import com.liaoinstan.springview.widget.SpringView;
import com.zhy.adapter.recyclerview.CommonAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by zyz on 2020/7/1.
 * 提问列表
 */
public class TiWenListActivity extends BaseActivity {
    int page = 0;
    String y_store_id = "";

    RecyclerView recyclerView1;
    List<StoreDetailModel_WenDa.ListBean> list1 = new ArrayList<>();
    CommonAdapter<StoreDetailModel_WenDa.ListBean> mAdapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiwenlist);
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
                params.put("y_store_id", y_store_id);
                params.put("u_token", localUserInfo.getToken());
                Request(params);
            }

            @Override
            public void onLoadmore() {
                page++;
                Map<String, String> params = new HashMap<>();
                params.put("page", page + "");
                params.put("y_store_id", y_store_id);
                params.put("u_token", localUserInfo.getToken());
                RequestMore(params);
            }
        });
        recyclerView1 = findViewByID_My(R.id.recyclerView1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initData() {
        y_store_id = getIntent().getStringExtra("y_store_id");
        requestServer();
    }
    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        page = 0;
        Map<String, String> params = new HashMap<>();
        params.put("page", page + "");
        params.put("y_store_id", y_store_id);
        params.put("u_token", localUserInfo.getToken());
        Request(params);
    }

    private void Request(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.StoreDetail_WenDa, params, headerMap, new CallBackUtil<StoreDetailModel_WenDa>() {
            @Override
            public StoreDetailModel_WenDa onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
//                showEmptyPage();
//                myToast(err);
            }

            @Override
            public void onResponse(StoreDetailModel_WenDa response) {
                hideProgress();
                list1 = response.getList();
                if (list1.size() > 0) {
                    showContentPage();
                    /*mAdapter1 = new CommonAdapter<ProductListModel.ListBean>
                            (ProductListActivity.this, R.layout.item_productlist, list1) {
                        @Override
                        protected void convert(ViewHolder holder, ProductListModel.ListBean model, int position) {
                            //logo
                            ImageView imageView = holder.getView(R.id.imageView);
                            Glide.with(ProductListActivity.this).load(URLs.IMGHOST + model.getGImg())
                                    .centerCrop()
                                    .apply(RequestOptions.bitmapTransform(new
                                            RoundedCorners(CommonUtil.dip2px(ProductListActivity.this,5))))
                                    .placeholder(R.mipmap.loading)//加载站位图
                                    .error(R.mipmap.zanwutupian)//加载失败
                                    .into(imageView);//加载图片

                            holder.setText(R.id.textView1, model.getGName());
                            holder.setText(R.id.textView2, model.getGPrice() + "");
                            TextView textView3 = holder.getView(R.id.textView3);
                            textView3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //购买
                                }
                            });
                        }
                    };
                    mAdapter1.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            Bundle bundle = new Bundle();
                            bundle.putString("y_goods_id", list1.get(i).getYGoodsId());
                            CommonUtil.gotoActivityWithData(ProductListActivity.this, ProductDetailActivity.class, bundle, false);
                        }

                        @Override
                        public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            return false;
                        }
                    });
                    recyclerView1.setAdapter(mAdapter1);*/
                } else {
                    showEmptyPage();
                }
            }
        });
    }

    /**
     * 加载更多
     *
     * @param params
     */
    private void RequestMore(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.StoreDetail_WenDa, params, headerMap, new CallBackUtil<StoreDetailModel_WenDa>() {
            @Override
            public StoreDetailModel_WenDa onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
//                myToast(err);
                myToast(getString(R.string.app_nomore));
                page--;
            }

            @Override
            public void onResponse(StoreDetailModel_WenDa response) {
                hideProgress();
                List<StoreDetailModel_WenDa.ListBean> list_1 = new ArrayList<>();
                list_1 = response.getList();
                if (list_1.size() == 0) {
                    page--;
                    myToast(getString(R.string.app_nomore));
                } else {
                    list1.addAll(list_1);
                    mAdapter1.notifyDataSetChanged();
                }
            }
        });
    }
    @Override
    protected void updateView() {
        titleView.setTitle("门店问答");
        titleView.showRightTextview("发起提问", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
