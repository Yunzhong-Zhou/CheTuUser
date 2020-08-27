package com.chetu.user.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.chetu.user.R;
import com.chetu.user.base.BaseActivity;
import com.chetu.user.model.ProductListModel;
import com.chetu.user.model.Product_FenLeiModel;
import com.chetu.user.net.URLs;
import com.chetu.user.okhttp.CallBackUtil;
import com.chetu.user.okhttp.OkhttpUtil;
import com.chetu.user.utils.CommonUtil;
import com.chetu.user.utils.MyLogger;
import com.liaoinstan.springview.widget.SpringView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by zyz on 2020/6/10.
 * 商品列表
 */
public class ProductListActivity extends BaseActivity {
    String keyws = "", y_classify_id = "", v_sort = "";
    int page = 0;

    RecyclerView recyclerView1;
    List<ProductListModel.ListBean> list1 = new ArrayList<>();
    CommonAdapter<ProductListModel.ListBean> mAdapter1;

    RecyclerView rv_fenlei;
    List<Product_FenLeiModel.ListBean> list_fenlei = new ArrayList<>();
    CommonAdapter<Product_FenLeiModel.ListBean> mAdapter_fenlei;
    int i = -1;
    EditText et_search;
    ImageView iv_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productlist);
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
                params.put("keyws", keyws);
                params.put("y_classify_id", y_classify_id);
                params.put("v_sort", v_sort);
//                params.put("u_token", localUserInfo.getToken());
                Request(params);
            }

            @Override
            public void onLoadmore() {
                page++;
                Map<String, String> params = new HashMap<>();
//                params.put("u_token", localUserInfo.getToken());
                params.put("page", page + "");
                params.put("keyws", keyws);
                params.put("y_classify_id", y_classify_id);
                params.put("v_sort", v_sort);
                RequestMore(params);
            }
        });
        recyclerView1 = findViewByID_My(R.id.recyclerView1);
        recyclerView1.setLayoutManager(new GridLayoutManager(this, 2));


        rv_fenlei = findViewByID_My(R.id.rv_fenlei);
        rv_fenlei.setLayoutManager(new GridLayoutManager(this, 4));

        iv_delete = findViewByID_My(R.id.iv_delete);
        et_search = findViewByID_My(R.id.editText_search);
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                /*ArrayList<Contact> temp = new ArrayList<>();
                for (Contact data : datas) {
                    if (data.getName().contains(s) || data.getPinyin().contains(s)) {
                        temp.add(data);
                    }
                }
                if (mAdapter != null) {
                    mAdapter = new ContactAdapter(AddCarModelActivity.this, temp);
                    mListView.setAdapter(mAdapter);

                    //再次获取焦点
                    mSearchInput.setFocusable(true);
                    mSearchInput.setFocusableInTouchMode(true);
                    mSearchInput.requestFocus();
                    mSearchInput.findFocus();
                }*/


            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    iv_delete.setVisibility(View.GONE);
                } else {
                    iv_delete.setVisibility(View.VISIBLE);
                }
            }
        });
        iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_search.setText("");
                keyws = "";
                //再次获取焦点
                et_search.setFocusable(true);
                et_search.setFocusableInTouchMode(true);
                et_search.requestFocus();
                et_search.findFocus();
                CommonUtil.showInput(ProductListActivity.this, et_search);//弹出键盘
            }
        });
        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    MyLogger.i(">>>>>>>>输入后：" + et_search.getText().toString().trim());
                    //关闭软键盘
                    CommonUtil.hideInput(ProductListActivity.this);
                    //do something
                    if (!et_search.getText().toString().trim().equals("")) {
                        keyws = et_search.getText().toString().trim();
                        requestServer();
                    } else {
                        myToast("请输入需要搜索的内容");
                    }
                    return false;   //返回true，保留软键盘;false，隐藏软键盘
                }
                return false;
            }
        });
    }

    @Override
    protected void initData() {
        y_classify_id = getIntent().getStringExtra("id");

        //获取商品分类
        Map<String, String> params = new HashMap<>();
        requestFenLei(params);

        requestServer();
    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        page = 0;
        Map<String, String> params = new HashMap<>();
        params.put("page", page + "");
        params.put("keyws", keyws);
        params.put("y_classify_id", y_classify_id);
        params.put("v_sort", v_sort);
        Request(params);
    }

    private void Request(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.ProductList, params, headerMap, new CallBackUtil<ProductListModel>() {
            @Override
            public ProductListModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                showEmptyPage();
//                myToast(err);
            }

            @Override
            public void onResponse(ProductListModel response) {
                hideProgress();
                list1 = response.getList();
                if (list1.size() > 0) {
                    showContentPage();
                    mAdapter1 = new CommonAdapter<ProductListModel.ListBean>
                            (ProductListActivity.this, R.layout.item_productlist, list1) {
                        @Override
                        protected void convert(ViewHolder holder, ProductListModel.ListBean model, int position) {
                            //logo
                            ImageView imageView = holder.getView(R.id.imageView);
                            Glide.with(ProductListActivity.this).load(URLs.IMGHOST + model.getGImg())
                                    .centerCrop()
                                    .apply(RequestOptions.bitmapTransform(new
                                            RoundedCorners(CommonUtil.dip2px(ProductListActivity.this, 5))))
                                    .placeholder(R.mipmap.loading)//加载站位图
                                    .error(R.mipmap.zanwutupian)//加载失败
                                    .into(imageView);//加载图片

                            holder.setText(R.id.textView1, model.getGName());
                            holder.setText(R.id.textView2, model.getGPrice() + "");
                            /*TextView textView3 = holder.getView(R.id.textView3);
                            textView3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //购买

                                }
                            });*/
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
        OkhttpUtil.okHttpPost(URLs.ProductList, params, headerMap, new CallBackUtil<ProductListModel>() {
            @Override
            public ProductListModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
//                myToast(err);
                page--;
            }

            @Override
            public void onResponse(ProductListModel response) {
                hideProgress();
                List<ProductListModel.ListBean> list_1 = new ArrayList<>();
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
     * 商品分类
     */
    private void requestFenLei(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.ProductList_FenLei, params, headerMap, new CallBackUtil<Product_FenLeiModel>() {
            @Override
            public Product_FenLeiModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
//                myToast(err);
            }

            @Override
            public void onResponse(Product_FenLeiModel response) {
                list_fenlei = response.getList();
                mAdapter_fenlei = new CommonAdapter<Product_FenLeiModel.ListBean>
                        (ProductListActivity.this, R.layout.item_productlist_fenlei, list_fenlei) {
                    @Override
                    protected void convert(ViewHolder holder, Product_FenLeiModel.ListBean model, int position) {
                        TextView textView1 = holder.getView(R.id.textView1);
                        textView1.setText(model.getYName());
                        if (i == position) {
                            textView1.setBackgroundResource(R.drawable.yuanjiao_5_lanse);
                            textView1.setTextColor(getResources().getColor(R.color.white));
                        } else {
                            textView1.setBackgroundResource(R.drawable.yuanjiao_5_baise);
                            textView1.setTextColor(getResources().getColor(R.color.black));
                        }

                    }
                };
                mAdapter_fenlei.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int item) {
                        i = item;
                        y_classify_id = list_fenlei.get(i).getYClassifyId();
                        mAdapter_fenlei.notifyDataSetChanged();
                        requestServer();
                    }

                    @Override
                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        return false;
                    }
                });
                rv_fenlei.setAdapter(mAdapter_fenlei);
            }
        });
    }

    @Override
    protected void updateView() {
        titleView.setTitle("商品列表");
    }
}
