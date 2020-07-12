package com.chetu.user.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.chetu.user.R;
import com.chetu.user.model.FristAppModel;
import com.chetu.user.net.URLs;
import com.chetu.user.okhttp.CallBackUtil;
import com.chetu.user.okhttp.OkhttpUtil;
import com.chetu.user.utils.LocalUserInfo;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;


/**
 * Created by zyz on 2019-09-27.
 * 启动页
 */
public class HelloActivity extends Activity {
    private static final String SHARE_APP_TAG = "HelloActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*//在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现，建议该方法放在Application的初始化方法中
        SDKInitializer.initialize(getApplicationContext());*/
        //设置无标题
//        requestWindowFeature(Window.FEATURE_NO_TITLE);

        /*//设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);*/

        // 判断是否是第一次开启应用
        SharedPreferences setting = getSharedPreferences(SHARE_APP_TAG, 0);
        Boolean user_first = setting.getBoolean("FIRST", true);

        // 如果是第一次启动，则先进入功能引导页
        /*if (user_first) {
            setting.edit().putBoolean("FIRST", false).commit();
            Intent intent = new Intent(this, GuideActivity.class);
            startActivity(intent);
            finish();
        } else {
            // 如果不是第一次启动app，则正常显示启动屏
            setContentView(R.layout.activity_hello);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    enterHomeActivity();
                }
            }, 2000);
        }*/
        //第一次启动获取数据
        Map<String, String> params = new HashMap<>();
        RequestFrist(params);//检查更新

        setContentView(R.layout.activity_hello);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                enterHomeActivity();
            }
        }, 2000);

    }

    /**
     * 第一次启动需要获取的数据
     *
     * @param params
     */
    private void RequestFrist(Map<String, String> params) {
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("apikey", URLs.APIKEY);
        headerMap.put("hversion", URLs.HVERSION);

        OkhttpUtil.okHttpPost(URLs.FristApp, params, headerMap, new CallBackUtil<FristAppModel>() {
            @Override
            public FristAppModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
//                hideProgress();
//                myToast(err);
            }

            @Override
            public void onResponse(FristAppModel response) {
//                hideProgress();
                LocalUserInfo.getInstance(HelloActivity.this).setKfuserhash(response.getConf_info().getKf_info().getUserHash());
            }
        });
    }

    private void enterHomeActivity() {
//        LocalUserInfo.getInstance(HelloActivity.this).setUserId("");
        if (LocalUserInfo.getInstance(HelloActivity.this).getUserId().equals("")) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        /*Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();*/

        finish();
    }
}
