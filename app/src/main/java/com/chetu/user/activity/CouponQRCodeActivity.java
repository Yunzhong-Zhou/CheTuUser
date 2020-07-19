package com.chetu.user.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;

import com.chetu.user.R;
import com.chetu.user.base.BaseActivity;
import com.chetu.user.model.CouponQRCodeModel;
import com.chetu.user.net.URLs;
import com.chetu.user.okhttp.CallBackUtil;
import com.chetu.user.okhttp.OkhttpUtil;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Mr.Z on 2020/7/19.
 * 二维码
 */
public class CouponQRCodeActivity extends BaseActivity {
    String y_user_coupon_id = "";

    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
    }

    @Override
    protected void initView() {
        imageView = findViewByID_My(R.id.imageView);
    }

    @Override
    protected void initData() {
        y_user_coupon_id = getIntent().getStringExtra("y_user_coupon_id");
        Map<String, String> params = new HashMap<>();
        params.put("y_user_coupon_id", y_user_coupon_id);
        Request(params);
    }
    private void Request(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Coupon_QRCode, params, headerMap, new CallBackUtil<CouponQRCodeModel>() {
            @Override
            public CouponQRCodeModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
//                showEmptyPage();
                myToast(err);
            }

            @Override
            public void onResponse(CouponQRCodeModel response) {
                hideProgress();
                byte[] decodedString = Base64.decode(response.getStr(), Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                imageView.setImageBitmap(decodedByte);
            }
        });
    }
    @Override
    protected void updateView() {
        titleView.setTitle("优惠券二维码");
    }
}
