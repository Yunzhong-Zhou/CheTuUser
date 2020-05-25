package com.chetu.user.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chetu.user.R;
import com.chetu.user.activity.MainActivity;
import com.chetu.user.activity.SetUpActivity;
import com.chetu.user.base.BaseFragment;
import com.chetu.user.model.Fragment4Model;
import com.chetu.user.net.URLs;
import com.chetu.user.okhttp.CallBackUtil;
import com.chetu.user.okhttp.OkhttpUtil;
import com.chetu.user.utils.CommonUtil;
import com.liaoinstan.springview.widget.SpringView;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

import static com.chetu.user.net.URLs.IMGHOST;

/**
 * Created by fafukeji01 on 2016/1/6.
 * 我的
 */
public class Fragment4 extends BaseFragment {
    ImageView iv_shezhi, iv_jinbi, imageView1;
    RelativeLayout rl_xiaoxi;
    TextView textView1, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9,
            tv_xiaoxinum;
    LinearLayout linearLayout1, linearLayout2, linearLayout3, linearLayout4, linearLayout5, linearLayout6,
            linearLayout7, linearLayout8, linearLayout9, linearLayout10, linearLayout11, linearLayout12,
            linearLayout13, linearLayout14, linearLayout15;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment4, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (MainActivity.item == 3) {
            requestServer();
        }

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        /*if (MainActivity.item == 3) {
            requestServer();
        }*/
    }

    @Override
    protected void initView(View view) {
//        CommonUtil.setMargins(findViewByID_My(R.id.headView),0, (int) CommonUtil.getStatusBarHeight(getActivity()),0,0);
        findViewByID_My(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(getActivity()), 0, 0);
        setSpringViewMore(false);//不需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                Map<String, String> params = new HashMap<>();
                params.put("u_token", localUserInfo.getToken());
                requestCenter(params);
            }

            @Override
            public void onLoadmore() {

            }
        });
        iv_shezhi = findViewByID_My(R.id.iv_shezhi);
        iv_shezhi.setOnClickListener(this);
        iv_jinbi = findViewByID_My(R.id.iv_jinbi);
        iv_jinbi.setOnClickListener(this);
        rl_xiaoxi = findViewByID_My(R.id.rl_xiaoxi);
        rl_xiaoxi.setOnClickListener(this);
        tv_xiaoxinum = findViewByID_My(R.id.tv_xiaoxinum);

        imageView1 = findViewByID_My(R.id.imageView1);
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
        textView4 = findViewByID_My(R.id.textView4);
        textView5 = findViewByID_My(R.id.textView5);
        textView6 = findViewByID_My(R.id.textView6);
        textView7 = findViewByID_My(R.id.textView7);
        textView8 = findViewByID_My(R.id.textView8);
        textView9 = findViewByID_My(R.id.textView9);
        if (!localUserInfo.getNickname().equals("")) {
            textView3.setText(localUserInfo.getNickname());
        }
        textView4.setText(localUserInfo.getPhonenumber());

        if (!localUserInfo.getUserImage().equals(""))
            Glide.with(getActivity())
                    .load(IMGHOST + localUserInfo.getUserImage())
                    .centerCrop()
//                    .placeholder(R.mipmap.headimg)//加载站位图
//                    .error(R.mipmap.headimg)//加载失败
                    .into(imageView1);//加载图片

        linearLayout1 = findViewByID_My(R.id.linearLayout1);
        linearLayout2 = findViewByID_My(R.id.linearLayout2);
        linearLayout3 = findViewByID_My(R.id.linearLayout3);
        linearLayout4 = findViewByID_My(R.id.linearLayout4);
        linearLayout5 = findViewByID_My(R.id.linearLayout5);
        linearLayout6 = findViewByID_My(R.id.linearLayout6);
        linearLayout7 = findViewByID_My(R.id.linearLayout7);
        linearLayout8 = findViewByID_My(R.id.linearLayout8);
        linearLayout9 = findViewByID_My(R.id.linearLayout9);
        linearLayout10 = findViewByID_My(R.id.linearLayout10);
        linearLayout11 = findViewByID_My(R.id.linearLayout11);
        linearLayout12 = findViewByID_My(R.id.linearLayout12);
        linearLayout13 = findViewByID_My(R.id.linearLayout13);
        linearLayout14 = findViewByID_My(R.id.linearLayout14);
        linearLayout15 = findViewByID_My(R.id.linearLayout15);

        linearLayout1.setOnClickListener(this);
        linearLayout2.setOnClickListener(this);
        linearLayout3.setOnClickListener(this);
        linearLayout4.setOnClickListener(this);
        linearLayout5.setOnClickListener(this);
        linearLayout6.setOnClickListener(this);
        linearLayout7.setOnClickListener(this);
        linearLayout8.setOnClickListener(this);
        linearLayout9.setOnClickListener(this);
        linearLayout10.setOnClickListener(this);
        linearLayout11.setOnClickListener(this);
        linearLayout12.setOnClickListener(this);
        linearLayout13.setOnClickListener(this);
        linearLayout14.setOnClickListener(this);
        linearLayout15.setOnClickListener(this);
    }

    @Override
    protected void initData() {
//        requestServer();
    }

    private void requestCenter(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Fragment4, params, headerMap, new CallBackUtil<Fragment4Model>() {
            @Override
            public Fragment4Model onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(Fragment4Model response) {
                hideProgress();
                //头像
                /*localUserInfo.setUserImage(response.getUser_info().);
                if (!response.getHead().equals("") && getActivity() != null)
                    Glide.with(getActivity()).load(IMGHOST + response.getHead())
                            .centerCrop()
//                            .placeholder(R.mipmap.headimg)//加载站位图
//                            .error(R.mipmap.headimg)//加载失败
                            .into(imageView1);//加载图片*/

            }
        });
    }

    @Override
    public void requestServer() {
        super.requestServer();
//        this.showLoadingPage();
        showProgress(true, getString(R.string.app_loading));
        Map<String, String> params = new HashMap<>();
        params.put("u_token", localUserInfo.getToken());
        requestCenter(params);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_shezhi:
                //设置
                CommonUtil.gotoActivity(getActivity(), SetUpActivity.class);
                break;
            case R.id.iv_jinbi:
                //金币
//                CommonUtil.gotoActivity(getActivity(), .class);
                break;
            case R.id.rl_xiaoxi:
                //消息
//                CommonUtil.gotoActivity(getActivity(), .class);
                break;
            case R.id.linearLayout1:
                //个人资料
//                CommonUtil.gotoActivity(getActivity(), .class);
                break;
            case R.id.linearLayout2:
                //违章
//                CommonUtil.gotoActivity(getActivity(), .class);
                break;
            case R.id.linearLayout3:
                //保养
//                CommonUtil.gotoActivity(getActivity(), .class);
                break;
            case R.id.linearLayout4:
                //保险（交）
//                CommonUtil.gotoActivity(getActivity(), .class);
                break;
            case R.id.linearLayout5:
                //保险（商）
//                CommonUtil.gotoActivity(getActivity(), .class);
                break;
            case R.id.linearLayout6:
                //年检
//                CommonUtil.gotoActivity(getActivity(), .class);
                break;

            case R.id.linearLayout7:
                //我的订单
//                CommonUtil.gotoActivity(getActivity(), .class);
                break;
            case R.id.linearLayout8:
                //优惠券
//                CommonUtil.gotoActivity(getActivity(), .class);
                break;
            case R.id.linearLayout9:
                //待发布
//                CommonUtil.gotoActivity(getActivity(), .class);
                break;
            case R.id.linearLayout10:
                //记事本
//                CommonUtil.gotoActivity(getActivity(), .class);
                break;
            case R.id.linearLayout11:
                //足迹
//                CommonUtil.gotoActivity(getActivity(), .class);
                break;
            case R.id.linearLayout12:
                //我的车库
//                CommonUtil.gotoActivity(getActivity(), .class);
                break;
            case R.id.linearLayout13:
                //分享有礼
//                CommonUtil.gotoActivity(getActivity(), .class);
                break;
            case R.id.linearLayout14:
                //申请加盟
//                CommonUtil.gotoActivity(getActivity(), .class);
                break;
            case R.id.linearLayout15:
                //客服中心
//                CommonUtil.gotoActivity(getActivity(), .class);
                break;
        }
    }

    @Override
    protected void updateView() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}