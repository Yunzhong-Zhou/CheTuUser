package com.chetu.user.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.chetu.user.R;
import com.chetu.user.base.BaseActivity;
import com.chetu.user.model.NotebookTagModel;
import com.chetu.user.net.URLs;
import com.chetu.user.okhttp.CallBackUtil;
import com.chetu.user.okhttp.OkhttpUtil;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by zyz on 2020/5/26.
 * 添加记事本
 */
public class AddNotebookActivity extends BaseActivity {
    EditText editText1, editText2, editText3;
    String y_user_notepad_id = "", y_title = "", i_msg = "", y_tag = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnotebook);

        mImmersionBar.reset()
                .statusBarColor(R.color.background)
                .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
    }

    @Override
    protected void initView() {
        editText1 = findViewByID_My(R.id.editText1);
        editText2 = findViewByID_My(R.id.editText2);
        editText3 = findViewByID_My(R.id.editText3);

    }

    @Override
    protected void initData() {
        y_user_notepad_id = getIntent().getStringExtra("y_user_notepad_id");
        y_title = getIntent().getStringExtra("y_title");
        editText1.setText(y_title);
        i_msg = getIntent().getStringExtra("i_msg");
        editText3.setText(i_msg);
        y_tag = getIntent().getStringExtra("y_tag");
        editText2.setText(y_tag);

        //获取标签列表
        Map<String, String> params = new HashMap<>();
        params.put("u_token", localUserInfo.getToken());
        Request(params);
    }

    private void Request(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Notebook_tag, params, headerMap, new CallBackUtil<NotebookTagModel>() {
            @Override
            public NotebookTagModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
//                hideProgress();
//                showEmptyPage();
//                myToast(err);
            }

            @Override
            public void onResponse(NotebookTagModel response) {
//                hideProgress();

            }
        });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_add:
                //添加
                if (match()) {
                    showProgress(true, getString(R.string.app_loading1));
                    Map<String, String> params = new HashMap<>();
                    params.put("u_token", localUserInfo.getToken());
                    params.put("y_title", y_title);
                    params.put("i_msg", i_msg);
                    params.put("y_user_notepad_id", y_user_notepad_id);
                    params.put("y_tag", y_tag);
                    RequestUpData(params);//添加
                }
                break;
        }
    }

    /**
     * 添加记录
     *
     * @param params
     */
    private void RequestUpData(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.AddNotebook, params, headerMap, new CallBackUtil<Object>() {
            @Override
            public Object onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(Object response) {
                hideProgress();
                myToast("添加成功");
                finish();
            }
        });
    }

    private boolean match() {
        y_title = editText1.getText().toString().trim();
        if (TextUtils.isEmpty(y_title)) {
            myToast("请输入标题");
            return false;
        }
        y_tag = editText2.getText().toString().trim();
        if (TextUtils.isEmpty(y_tag)) {
            myToast("请输入标签");
            return false;
        }
        i_msg = editText3.getText().toString().trim();
        if (TextUtils.isEmpty(i_msg)) {
            myToast("请输入内容");
            return false;
        }
        return true;
    }

    @Override
    protected void updateView() {
        titleView.setTitle("添加记录");
        titleView.setBackground(R.color.background);
    }
}
