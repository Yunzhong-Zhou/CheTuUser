package com.chetu.user.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chetu.user.R;
import com.chetu.user.base.BaseActivity;
import com.chetu.user.model.WaitingReleaseModel;
import com.chetu.user.net.URLs;
import com.chetu.user.okhttp.CallBackUtil;
import com.chetu.user.okhttp.OkhttpUtil;
import com.chetu.user.popupwindow.PhotoShowDialog;
import com.chetu.user.utils.CommonUtil;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by zyz on 2020/6/19.
 * 已报价
 */
public class QuotedPriceActivity extends BaseActivity {
    WaitingReleaseModel.ListBean response;
    ImageView iv_carlogo;
    TextView tv_carname, tv_carnum, tv_cardetail, tv_qingkuangshuoming;

    RecyclerView recyclerView;
    CommonAdapter<WaitingReleaseModel.ListBean.SelectStorelistBean> mAdapter;

    String storename_temp = "";

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
        tv_qingkuangshuoming = findViewByID_My(R.id.tv_qingkuangshuoming);
    }

    @Override
    protected void initData() {
        response = (WaitingReleaseModel.ListBean) getIntent().getSerializableExtra("detail");

        tv_carname.setText(response.getUser_sedan_info().getBrandInfo().getSeriesName());
        tv_carnum.setText(response.getUser_sedan_info().getSNumber());
        tv_cardetail.setText(response.getUser_sedan_info().getSName());
        Glide.with(this).load(URLs.IMGHOST + response.getUser_sedan_info().getSLogo())
                .centerCrop()
                .into(iv_carlogo);//加载图片
        tv_qingkuangshuoming.setText("情况说明：" + response.getVMsg());


        if (response.getSelectStorelist() != null) {
            showContentPage();
            mAdapter = new CommonAdapter<WaitingReleaseModel.ListBean.SelectStorelistBean>
                    (QuotedPriceActivity.this, R.layout.item_quotedprice, response.getSelectStorelist()) {
                @Override
                protected void convert(ViewHolder holder, WaitingReleaseModel.ListBean.SelectStorelistBean model, int position) {
                    LinearLayout ll_store = holder.getView(R.id.ll_store);
                    TextView tv_storename = holder.getView(R.id.tv_storename);
                    tv_storename.setText(model.getStore_info().getVName());
                    //第一个直接显示
                    /*if (position == 0) {
                        storename_temp = model.getStore_info().getVName();
                        ll_store.setVisibility(View.VISIBLE);
                        tv_storename.setText(model.getStore_info().getVName());
                    } else {
                        //如果和上一个item的不同，则认为是新分类的开始
                        if (!model.getStore_info().getVName().equals(storename_temp)) {
                            ll_store.setVisibility(View.VISIBLE);
                            tv_storename.setText(model.getStore_info().getVName());
                            storename_temp = model.getStore_info().getVName();
                        } else {
                            ll_store.setVisibility(View.GONE);
                        }
                    }*/

                    //是否有报价
                    RecyclerView rv_baojia = holder.getView(R.id.rv_baojia);
                    rv_baojia.setLayoutManager(new LinearLayoutManager(QuotedPriceActivity.this));
                    if (model.getProject_offer_list() != null && model.getProject_offer_list().size() > 0) {//有报价
                        CommonAdapter<WaitingReleaseModel.ListBean.SelectStorelistBean.ProjectOfferListBean> mAdapter_baojia
                                = new CommonAdapter<WaitingReleaseModel.ListBean.SelectStorelistBean.ProjectOfferListBean>
                                (QuotedPriceActivity.this, R.layout.item_quotedprice_baojia, model.getProject_offer_list()) {
                            @Override
                            protected void convert(ViewHolder holder, WaitingReleaseModel.ListBean.SelectStorelistBean.ProjectOfferListBean bean, int position) {
                                holder.setText(R.id.tv_title, bean.getUdemand_project_info().getVTitle());
                                holder.setText(R.id.tv_baojia, bean.getVPrice());
                                if (bean.getUdemand_project_info().getVMsg() != null && !bean.getUdemand_project_info().getVMsg().equals("#")) {
                                    holder.setText(R.id.tv_qingkuangshuoming, "项目说明：" + bean.getUdemand_project_info().getVMsg());
                                } else {
                                    holder.setText(R.id.tv_qingkuangshuoming, "项目说明：暂无项目说明");
                                }

                                //选择
                                ImageView iv_xuanze = holder.getView(R.id.iv_xuanze);
                                if (bean.getUdemand_project_info().isIsxuanze()) {
                                    iv_xuanze.setImageResource(R.mipmap.ic_xuanzhong);
                                } else {
                                    iv_xuanze.setImageResource(R.mipmap.ic_weixuan);
                                }
                                iv_xuanze.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (bean.getUdemand_project_info().isIsxuanze()) {
                                            bean.getUdemand_project_info().setIsxuanze(false);
                                        } else {
                                            bean.getUdemand_project_info().setIsxuanze(true);
                                        }
                                        mAdapter.notifyDataSetChanged();
                                    }
                                });

                                //是否有图片
                                RecyclerView rv_tupian = holder.getView(R.id.rv_tupian);
                                if (bean.getUdemand_project_info().getImgArr() != null && bean.getUdemand_project_info().getImgArr().size() > 0) {
                                    rv_tupian.setVisibility(View.VISIBLE);

                                    LinearLayoutManager llm1 = new LinearLayoutManager(QuotedPriceActivity.this);
                                    llm1.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
                                    rv_tupian.setLayoutManager(llm1);

                                    ArrayList<String> images = new ArrayList<>();
                                    for (String s : bean.getUdemand_project_info().getImgArr()) {
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
                            }
                        };
                        rv_baojia.setAdapter(mAdapter_baojia);

                    }

                    //下单
                    holder.getView(R.id.tv_xiadan).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showToast("确认选择的服务下单到该店铺吗？", "确认", "取消", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                    showProgress(true, getString(R.string.app_loading1));
                                    JSONArray jsonArray = new JSONArray();
                                    for (WaitingReleaseModel.ListBean.SelectStorelistBean.ProjectOfferListBean m : model.getProject_offer_list()) {
                                        if (m.getUdemand_project_info().isIsxuanze() == true) {
                                            try {
                                                JSONObject object1 = new JSONObject();
                                                object1.put("y_store_id", model.getYStoreId());
                                                object1.put("service_str", m.getUdemand_project_info().getVTitle());
                                                jsonArray.put(object1);
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                    Map<String, String> params = new HashMap<>();
                                    params.put("u_token", localUserInfo.getToken());
                                    params.put("service_json_str", jsonArray.toString());
                                    RequestXiaDan(params, model.getStore_info().getYStoreId());
                                }
                            }, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                }
                            });

                        }
                    });


                }
            };
            recyclerView.setAdapter(mAdapter);
        } else {
            showEmptyPage();
        }

    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        showEmptyPage();
    }

    /**
     * 下单-添加购物车
     *
     * @param params
     */
    private void RequestXiaDan(Map<String, String> params, String y_store_id) {
        OkhttpUtil.okHttpPost(URLs.ADDShop, params, headerMap, new CallBackUtil<Object>() {
            @Override
            public Object onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
            }

            @Override
            public void onResponse(Object response) {
                hideProgress();
                Bundle bundle2 = new Bundle();
//                bundle2.putSerializable("XuanZeFuWuModel", (Serializable) list_xuanze);
                bundle2.putString("y_store_id", y_store_id);
                bundle2.putString("longitude", localUserInfo.getLongitude());
                bundle2.putString("latitude", localUserInfo.getLatitude());
                CommonUtil.gotoActivityWithData(QuotedPriceActivity.this, ConfirmOrderActivity.class, bundle2, false);
            }
        });
    }

    @Override
    protected void updateView() {
        titleView.setTitle("报价详情");
        titleView.setBackground(R.color.background);
    }
}
