package com.chetu.user.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chetu.user.R;
import com.chetu.user.base.BaseActivity;
import com.chetu.user.model.ConfirmOrderModel;
import com.chetu.user.net.URLs;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by zyz on 2020/6/16.
 * 选择门店
 */
public class SelectStore_ServiceActivity extends BaseActivity {
    int type = 0;
    ConfirmOrderModel confirmOrderModel;
    //数据
    private RecyclerView recyclerView;
    List<ConfirmOrderModel.StoreListBean> list = new ArrayList<>();
    CommonAdapter<ConfirmOrderModel.StoreListBean> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectstore_service);
    }
    @Override
    protected void initView() {
        recyclerView = findViewByID_My(R.id.recyclerView);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLinearLayoutManager);
    }

    @Override
    protected void initData() {
        type = getIntent().getIntExtra("type",0);
        confirmOrderModel = (ConfirmOrderModel) getIntent().getSerializableExtra("ConfirmOrderModel");
        list = confirmOrderModel.getStore_list();
        if (list.size() > 0) {
            showContentPage();
            mAdapter = new CommonAdapter<ConfirmOrderModel.StoreListBean>
                    (SelectStore_ServiceActivity.this, R.layout.item_selectstore, list) {
                @Override
                protected void convert(ViewHolder holder, ConfirmOrderModel.StoreListBean model, int position) {
                    ImageView imageView1 = holder.getView(R.id.imageView1);
                    Glide.with(SelectStore_ServiceActivity.this)
                            .load(URLs.IMGHOST + model.getPicture())
                            .centerCrop()
                            .placeholder(R.mipmap.loading)//加载站位图
                            .error(R.mipmap.zanwutupian)//加载失败
                            .into(imageView1);//加载图片
                    holder.setText(R.id.tv_name, model.getVName());//店名
                    holder.setText(R.id.tv_pingfen, model.getReview());//评分
                    holder.setText(R.id.tv_addr, model.getAddress());//地址
//                    holder.setText(R.id.tv_juli, model.getDistance() + "m");//距离

                    /*MyLogger.i(">>>>>>"+ model.getStore_service_list().size());
                    if (model.getStore_service_list().size() > 0) {
                        //标签
                        FlowLayoutAdapter<Fragment3Model.ListBean.StoreServiceListBean> flowLayoutAdapter1 =
                                new FlowLayoutAdapter<Fragment3Model.ListBean.StoreServiceListBean>
                                        (model.getStore_service_list()) {
                                    @Override
                                    public void bindDataToView(ViewHolder holder, int position,
                                                               Fragment3Model.ListBean.StoreServiceListBean bean) {
//                                holder.setText(R.id.tv,bean);
                                        TextView tv = holder.getView(R.id.tv);
                                        tv.setText(bean.getYStateValue());
                                    *//*tv.setTextColor(getResources().getColor(R.color.black1));
                                    tv.setBackgroundResource(R.drawable.yuanjiao_3_huise);*//*
                                    }

                                    @Override
                                    public void onItemClick(int position, Fragment3Model.ListBean.StoreServiceListBean bean) {
//                        showToast("点击" + position);
                                    }

                                    @Override
                                    public int getItemLayoutID(int position, Fragment3Model.ListBean.StoreServiceListBean bean) {
                                        return R.layout.item_fragment3_flowlayout1;
                                    }
                                };
                        //服务
                        FlowLayoutAdapter<Fragment3Model.ListBean.StoreServiceListBean> flowLayoutAdapter2 =
                                new FlowLayoutAdapter<Fragment3Model.ListBean.StoreServiceListBean>
                                        (model.getStore_service_list()) {
                                    @Override
                                    public void bindDataToView(ViewHolder holder, int position,
                                                               Fragment3Model.ListBean.StoreServiceListBean bean) {
                                        TextView tv1 = holder.getView(R.id.tv1);
                                        tv1.setText(bean.getYStateValue() + "：");
                                        TextView tv2 = holder.getView(R.id.tv2);
                                        View view = holder.getView(R.id.view);
                                        if (bean.getYState() == 0) {
                                            //空闲
                                            tv2.setText("空闲");
                                            tv2.setTextColor(getResources().getColor(R.color.green));
                                            view.setBackgroundResource(R.drawable.yuanxing_lvse);
                                        } else {
                                            //忙碌
                                            tv2.setText("忙碌");
                                            tv2.setTextColor(getResources().getColor(R.color.red));
                                            view.setBackgroundResource(R.drawable.yuanxing_hongse);
                                        }
                                    *//*
                                    tv.setBackgroundResource(R.drawable.yuanjiao_3_huise);*//*
                                    }

                                    @Override
                                    public void onItemClick(int position, Fragment3Model.ListBean.StoreServiceListBean bean) {
//                        showToast("点击" + position);
                                    }

                                    @Override
                                    public int getItemLayoutID(int position, Fragment3Model.ListBean.StoreServiceListBean bean) {
                                        return R.layout.item_fragment3_flowlayout2;
                                    }
                                };
                        ((FlowLayout) holder.getView(R.id.flowLayout1)).setAdapter(flowLayoutAdapter1);
                        ((FlowLayout) holder.getView(R.id.flowLayout2)).setAdapter(flowLayoutAdapter2);
                    }*/

                }
            };


            mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                    if (type == 10003){
                        Intent resultIntent = new Intent();
                        Bundle bundle = new Bundle();
                        bundle.putString("y_store_id", list.get(i).getYStoreId());
//                                bundle.putString("y_store_service_id", list.get(i).getStore_service_list().get(i).getYStoreServiceId());
                        bundle.putString("store_name", list.get(i).getVName());
                        resultIntent.putExtras(bundle);
                        SelectStore_ServiceActivity.this.setResult(RESULT_OK, resultIntent);
                        finish();
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
    @Override
    protected void updateView() {
        titleView.setTitle("选择门店");
    }
}
