package com.chetu.user.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chetu.user.R;
import com.chetu.user.base.BaseActivity;
import com.chetu.user.net.URLs;
import com.chetu.user.okhttp.CallBackUtil;
import com.chetu.user.okhttp.OkhttpUtil;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by zyz on 2020/7/1.
 */
public class AddTiWenActivity extends BaseActivity {
    String y_store_id = "";
    EditText editText;
    TextView tv_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtiwen);

    }

    @Override
    protected void initView() {
        editText = findViewByID_My(R.id.editText);
        tv_confirm = findViewByID_My(R.id.tv_confirm);
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editText.getText().toString().trim().equals("")) {
                    showProgress(true, getString(R.string.app_loading1));
                    Map<String, String> params = new HashMap<>();
                    params.put("msg", editText.getText().toString().trim());
                    params.put("y_parent_id", "0");
                    params.put("y_store_id", y_store_id);
                    params.put("u_token", localUserInfo.getToken());
                    RequestUpData(params);
                } else {
                    myToast("请输入问题");
                }
            }
        });
    }

    private void RequestUpData(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.HuiDa, params, headerMap, new CallBackUtil() {
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
                showToast("问题提交成功", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        finish();
                    }
                });
            }
        });
    }

    @Override
    protected void initData() {
        y_store_id = getIntent().getStringExtra("y_store_id");
    }

    @Override
    protected void updateView() {
        titleView.setTitle("发起提问");
    }
}
