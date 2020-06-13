package com.chetu.user.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.chetu.user.R;
import com.chetu.user.base.BaseActivity;
import com.chetu.user.utils.CommonUtil;
import com.cy.cyflowlayoutlibrary.FlowLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by zyz on 2020/6/1.
 * 搜索
 */
public class SearchActivity extends BaseActivity {
    String keys = "";
    EditText et_search;
    FlowLayout flowLayout;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mImmersionBar.reset()
                .statusBarColor(R.color.background)
                .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
    }

    @Override
    protected void initView() {
        flowLayout = findViewByID_My(R.id.flowLayout);
        recyclerView = findViewByID_My(R.id.recyclerView);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLinearLayoutManager);

        et_search = findViewByID_My(R.id.et_search);
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
            }
        });
        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //关闭软键盘
                    CommonUtil.hideInput(SearchActivity.this);
                    //do something
                    //doSearch();
                    /*MyLogger.i(">>>>>>>>输入后：" + et_addr.getText().toString().trim());
                    if (!et_addr.getText().toString().trim().equals("")) {
                        //第二个参数传入null或者“”代表在全国进行检索，否则按照传入的city进行检索
                        InputtipsQuery inputquery = new InputtipsQuery(et_addr.getText().toString().trim(), city);
                        inputquery.setCityLimit(false);//限制在当前城市
                        Inputtips inputTips = new Inputtips(SelectAddressActivity.this, inputquery);
                        inputTips.setInputtipsListener(new Inputtips.InputtipsListener() {
                            @Override
                            public void onGetInputtips(List<Tip> list, int i) {
                                if (list.size() > 0) {
                                    //显示弹窗
//                                    showPopupWindow1(et_addr, list);
                                    showMapAddr(list);
                                }
                            }
                        });
                        inputTips.requestInputtipsAsyn();
                    } else {
                        recyclerView_addr.setVisibility(View.GONE);
                    }*/
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void initData() {
        keys = getIntent().getStringExtra("keys");
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.left_btn:
                finish();
                break;
            case R.id.right_btn:

                break;
        }
    }

    @Override
    protected void updateView() {
        titleView.setVisibility(View.GONE);
    }


}
