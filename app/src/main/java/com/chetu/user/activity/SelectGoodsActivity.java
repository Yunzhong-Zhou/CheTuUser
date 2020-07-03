package com.chetu.user.activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chetu.user.R;
import com.chetu.user.base.BaseActivity;
import com.chetu.user.model.SelectGoodsModel;
import com.chetu.user.net.URLs;
import com.chetu.user.okhttp.CallBackUtil;
import com.chetu.user.okhttp.OkhttpUtil;
import com.cy.cyflowlayoutlibrary.FlowLayout;
import com.cy.cyflowlayoutlibrary.FlowLayoutAdapter;
import com.cy.dialog.BaseDialog;
import com.liaoinstan.springview.widget.SpringView;
import com.zhy.adapter.recyclerview.CommonAdapter;
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
 * Created by zyz on 2020/7/3.
 * 选择商品
 */
public class SelectGoodsActivity extends BaseActivity {
    int page = 0;
    String y_store_service_id = "", y_store_id = "";
    RecyclerView recyclerView1;
    List<SelectGoodsModel.ListBean> list1 = new ArrayList<>();
    CommonAdapter<SelectGoodsModel.ListBean> mAdapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectgoods);
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
                page = 0;
                Map<String, String> params = new HashMap<>();
                params.put("page", page + "");
                params.put("is_techn", "1");
                params.put("y_store_id", y_store_id);
//                params.put("u_token", localUserInfo.getToken());
                Request(params);
            }

            @Override
            public void onLoadmore() {
                page++;
                Map<String, String> params = new HashMap<>();
//                params.put("u_token", localUserInfo.getToken());
                params.put("page", page + "");
                params.put("is_techn", "1");
                params.put("y_store_id", y_store_id);
                RequestMore(params);
            }
        });
        recyclerView1 = findViewByID_My(R.id.recyclerView1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initData() {
        y_store_service_id = getIntent().getStringExtra("y_store_service_id");
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
        params.put("is_techn", "1");
        params.put("y_store_id", y_store_id);
//        params.put("u_token", localUserInfo.getToken());
        Request(params);
    }

    private void Request(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.SelectGoods, params, headerMap, new CallBackUtil<SelectGoodsModel>() {
            @Override
            public SelectGoodsModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                showEmptyPage();
//                myToast(err);
            }

            @Override
            public void onResponse(SelectGoodsModel response) {
                hideProgress();
                list1 = response.getList();
                if (list1.size() > 0) {
                    showContentPage();
                    mAdapter1 = new CommonAdapter<SelectGoodsModel.ListBean>
                            (SelectGoodsActivity.this, R.layout.item_selectgoods, list1) {
                        @Override
                        protected void convert(ViewHolder holder, SelectGoodsModel.ListBean model, int position) {
                            //logo
                            ImageView imageView = holder.getView(R.id.imageView);
                            Glide.with(SelectGoodsActivity.this).load(URLs.IMGHOST + model.getGImg())
                                    .centerCrop()
//                                    .apply(RequestOptions.bitmapTransform(new
//                                            RoundedCorners(CommonUtil.dip2px(SelectGoodsActivity.this, 5))))
                                    .placeholder(R.mipmap.loading)//加载站位图
                                    .error(R.mipmap.zanwutupian)//加载失败
                                    .into(imageView);//加载图片

                            holder.setText(R.id.tv_title, model.getGName());
                            holder.setText(R.id.tv_money, model.getGPrice() + "");
                            TextView tv_guige = holder.getView(R.id.tv_guige);
                            tv_guige.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //规格
                                    int g_num = 1;
                                    List<Integer> selects = new ArrayList<>();
                                    showDialog(model, selects, g_num);
                                }
                            });
                        }
                    };

                    recyclerView1.setAdapter(mAdapter1);
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
        OkhttpUtil.okHttpPost(URLs.SelectGoods, params, headerMap, new CallBackUtil<SelectGoodsModel>() {
            @Override
            public SelectGoodsModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
//                myToast(err);
                page--;
            }

            @Override
            public void onResponse(SelectGoodsModel response) {
                hideProgress();
                List<SelectGoodsModel.ListBean> list_1 = new ArrayList<>();
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

    /**
     * 显示规格弹窗
     */
    private void showDialog(SelectGoodsModel.ListBean model, List<Integer> selects, int g_num) {
        BaseDialog dialog1 = new BaseDialog(SelectGoodsActivity.this);
        dialog1.contentView(R.layout.dialog_selectgoods)
                .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT))
                .animType(BaseDialog.AnimInType.BOTTOM)
                .canceledOnTouchOutside(false)
                .gravity(Gravity.BOTTOM)
                .dimAmount(0.7f)
                .show();

        TextView tv_title = dialog1.findViewById(R.id.tv_title);
        tv_title.setText(model.getGName());
        TextView tv_money = dialog1.findViewById(R.id.tv_money);
        tv_money.setText("¥" + model.getGPrice());
        ImageView iv = dialog1.findViewById(R.id.iv);
        Glide.with(SelectGoodsActivity.this).load(URLs.IMGHOST + model.getGImg())
                .centerCrop()
//                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                .placeholder(R.mipmap.loading)//加载站位图
                .error(R.mipmap.zanwutupian)//加载失败
                .into(iv);//加载图片

        TextView tv_tab = dialog1.findViewById(R.id.tv_tab);

        TextView tv_num = dialog1.findViewById(R.id.tv_num);
        tv_num.setText(g_num + "");

        TextView tv_anzhuang = dialog1.findViewById(R.id.tv_anzhuang);
        TextView tv_buanzhuang = dialog1.findViewById(R.id.tv_buanzhuang);
        tv_anzhuang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //安装
//                is_install = "1";
                tv_anzhuang.setTextColor(getResources().getColor(R.color.blue));
                tv_anzhuang.setBackgroundResource(R.drawable.yuanjiaobiankuang_15_lanse);
                tv_buanzhuang.setTextColor(getResources().getColor(R.color.black));
                tv_buanzhuang.setBackgroundResource(R.drawable.yuanjiao_15_huise1);

            }
        });
        tv_buanzhuang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //不安装
//                is_install = "2";
                tv_anzhuang.setTextColor(getResources().getColor(R.color.black));
                tv_anzhuang.setBackgroundResource(R.drawable.yuanjiao_15_huise1);
                tv_buanzhuang.setTextColor(getResources().getColor(R.color.blue));
                tv_buanzhuang.setBackgroundResource(R.drawable.yuanjiaobiankuang_15_lanse);

            }
        });

        RecyclerView rv = dialog1.findViewById(R.id.rv);
        selects.clear();
        for (SelectGoodsModel.ListBean.SpecificListBeanX bean : model.getSpecific_list()) {
            selects.add(0);
        }
        rv.setLayoutManager(new LinearLayoutManager(SelectGoodsActivity.this));
        CommonAdapter adapter = new CommonAdapter<SelectGoodsModel.ListBean.SpecificListBeanX>
                (SelectGoodsActivity.this, R.layout.item_dialog_guige, model.getSpecific_list()) {
            @Override
            protected void convert(ViewHolder holder, SelectGoodsModel.ListBean.SpecificListBeanX model1, int position) {
                holder.setText(R.id.tv, model1.getSName());
//                    String[] strArr = model.getSValue().split("\\|\\|");
                List<String> tabs = new ArrayList<>();
                for (SelectGoodsModel.ListBean.SpecificListBeanX.SpecificListBean s : model1.getSpecific_List()) {
                    tabs.add(s.getPName());
                }
                FlowLayoutAdapter flowLayoutAdapter = new FlowLayoutAdapter<String>(tabs) {
                    @Override
                    public void bindDataToView(FlowLayoutAdapter.ViewHolder holder, int i, String bean) {
                        TextView tv = holder.getView(R.id.tv);
                        tv.setText(bean);
                        if (selects.get(position) == i) {
                            tv.setTextColor(getResources().getColor(R.color.blue));
                            tv.setBackgroundResource(R.drawable.yuanjiaobiankuang_15_lanse);
                        } else {
                            tv.setTextColor(getResources().getColor(R.color.black));
                            tv.setBackgroundResource(R.drawable.yuanjiao_15_huise1);
                        }
                    }

                    @Override
                    public void onItemClick(int i, String bean) {
                        selects.set(position, i);
                           /* adapter.notifyDataSetChanged();
                            //计算及显示
                            addView(tv_tab, tv_money, g_num);*/
                    }

                    @Override
                    public int getItemLayoutID(int position, String bean) {
                        return R.layout.item_guige_flowlayout;
                    }
                };
                ((FlowLayout) holder.getView(R.id.flowLayout)).setAdapter(flowLayoutAdapter);

            }
        };
        rv.setAdapter(adapter);
        dialog1.findViewById(R.id.tv_jian).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //减号
                /*if (g_num > 1) {
                    g_num--;
                    tv_num.setText(g_num + "");
//                    addView(tv_tab, tv_money, g_num);
                }*/
            }
        });
        dialog1.findViewById(R.id.tv_jia).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //加号
//                        if (num < 100) {
               /* g_num++;
                tv_num.setText(g_num + "");

                addView(tv_tab, tv_money, g_num);*/
//                        }
            }
        });
        dialog1.findViewById(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.dismiss();
            }
        });
//        addView(tv_tab, tv_money, g_num);
    }
    /**
     * 计算
     *
     * @param tv_tab
     * @param tv_money
     * @param num
     */
    private void addView(TextView tv_tab, TextView tv_money, int num) {
        /*goods_specific_idstr = "";
        s_value = "";
        double tabMoney = 0;
        for (int i = 0; i < model.getSpecific_list().size(); i++) {
//            String[] strArr = model.getSpecific_list().get(i).getSValue().split("\\|\\|");
            for (int j = 0; j < model.getSpecific_list().get(i).getSpecific_List().size(); j++) {
                if (selects.get(i) == j) {
                    s_value += model.getSpecific_list().get(i).getSpecific_List().get(j).getPName() + "||";
                    goods_specific_idstr += model.getSpecific_list().get(i).getSpecific_List().get(j).getYGoodsSpecificId() + "||";
                    tabMoney += model.getSpecific_list().get(i).getSpecific_List().get(j).getSPrice();
                }
            }
        }

        goods_specific_idstr = goods_specific_idstr.substring(0, goods_specific_idstr.length() - 2);
        MyLogger.i(">>>>>>" + goods_specific_idstr);
        s_value = s_value.substring(0, s_value.length() - 2);
        tv_tab.setText(s_value);

        allmoney = (long) ((model.getInfo().getGPrice() + tabMoney) * num);
        tv_money.setText("¥" + allmoney);

        textView_moeny.setText("¥" + allmoney);
        textView_num.setText(g_num + "");
        head1_tv5.setText(s_value);*/
    }

    @Override
    protected void updateView() {
        titleView.setTitle("选择商品");
        titleView.setBackground(R.color.background);
    }
}
