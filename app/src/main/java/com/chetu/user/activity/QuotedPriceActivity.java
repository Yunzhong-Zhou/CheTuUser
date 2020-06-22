package com.chetu.user.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chetu.user.R;
import com.chetu.user.base.BaseActivity;
import com.chetu.user.model.WaitingReleaseModel;
import com.chetu.user.net.URLs;
import com.chetu.user.popupwindow.PhotoShowDialog;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by zyz on 2020/6/19.
 * 已报价
 */
public class QuotedPriceActivity extends BaseActivity {
    WaitingReleaseModel.ListBean model;
    ImageView iv_carlogo;
    TextView tv_carname, tv_carnum, tv_cardetail;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotedprice);
        mImmersionBar.reset()
                .statusBarColor(R.color.background)
                .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
    }

    @Override
    protected void initView() {
        iv_carlogo = findViewByID_My(R.id.iv_carlogo);
        tv_carname = findViewByID_My(R.id.tv_carname);
        tv_carnum = findViewByID_My(R.id.tv_carnum);
        tv_cardetail = findViewByID_My(R.id.tv_cardetail);
        recyclerView = findViewByID_My(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initData() {
        model = (WaitingReleaseModel.ListBean) getIntent().getSerializableExtra("detail");

        tv_carname.setText(model.getUser_sedan_info().getBrandInfo().getSeriesName());
        tv_carnum.setText(model.getUser_sedan_info().getSNumber());
        tv_cardetail.setText(model.getUser_sedan_info().getSName());
        Glide.with(this).load(URLs.IMGHOST + model.getUser_sedan_info().getSLogo())
                .centerCrop()
                .into(iv_carlogo);//加载图片

        CommonAdapter<WaitingReleaseModel.ListBean.ProjectListBean> mAdapter = new CommonAdapter<WaitingReleaseModel.ListBean.ProjectListBean>
                (QuotedPriceActivity.this, R.layout.item_quotedprice, model.getProject_list()) {
            @Override
            protected void convert(ViewHolder holder, WaitingReleaseModel.ListBean.ProjectListBean model, int position) {
                holder.setText(R.id.tv_title, model.getVTitle());
                TextView tv_daibaojia = holder.getView(R.id.tv_daibaojia);
                //是否有图片
                RecyclerView rv_tupian = holder.getView(R.id.rv_tupian);
                if (model.getImgArr() != null && model.getImgArr().size() > 0) {
                    rv_tupian.setVisibility(View.VISIBLE);

                    LinearLayoutManager llm1 = new LinearLayoutManager(QuotedPriceActivity.this);
                    llm1.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
                    rv_tupian.setLayoutManager(llm1);

                    ArrayList<String> images = new ArrayList<>();
                    for (String s : model.getImgArr()) {
                        images.add(URLs.IMGHOST + s);
                    }
                    CommonAdapter<String> mAdapter_tupian = new CommonAdapter<String>
                            (QuotedPriceActivity.this, R.layout.item_img_110_110, images) {
                        @Override
                        protected void convert(ViewHolder holder, String model, int position) {
                            ImageView iv = holder.getView(R.id.iv);
                            Glide.with(QuotedPriceActivity.this)
                                    .load(model)
                                    .centerCrop()
                                    .placeholder(R.mipmap.loading)//加载站位图
                                    .error(R.mipmap.zanwutupian)//加载失败
                                    .into(iv);//加载图片
                        }
                    };
                    mAdapter_tupian.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {

                            PhotoShowDialog photoShowDialog = new PhotoShowDialog(QuotedPriceActivity.this, images, i);
                            photoShowDialog.show();
                        }

                        @Override
                        public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            return false;
                        }
                    });
                    rv_tupian.setAdapter(mAdapter_tupian);

                } else {
                    rv_tupian.setVisibility(View.GONE);
                }

                //是否有报价
                RecyclerView rv_baojia = holder.getView(R.id.rv_baojia);
                if (model.getOffer_list() != null && model.getOffer_list().size() > 0) {//有报价
                    tv_daibaojia.setVisibility(View.GONE);
                    rv_baojia.setVisibility(View.VISIBLE);

                    rv_baojia.setLayoutManager(new GridLayoutManager(QuotedPriceActivity.this, 2));
                    CommonAdapter<WaitingReleaseModel.ListBean.ProjectListBean.OfferListBean> mAdapter_baojia
                            = new CommonAdapter<WaitingReleaseModel.ListBean.ProjectListBean.OfferListBean>
                            (QuotedPriceActivity.this, R.layout.item_quotedprice_baojia, model.getOffer_list()) {
                        @Override
                        protected void convert(ViewHolder holder, WaitingReleaseModel.ListBean.ProjectListBean.OfferListBean model, int position) {
                            holder.setText(R.id.title, model.getStore_info().getVName());
                            holder.setText(R.id.money, "¥" + model.getVPrice());
                        }
                    };
                    rv_baojia.setAdapter(mAdapter_baojia);


                } else {
                    //无报价
                    tv_daibaojia.setVisibility(View.VISIBLE);
                    rv_baojia.setVisibility(View.GONE);
                }

            }
        };
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void updateView() {
        titleView.setTitle("报价详情");
        titleView.setBackground(R.color.background);
    }
}
