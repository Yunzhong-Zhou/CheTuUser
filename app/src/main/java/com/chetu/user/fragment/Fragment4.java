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
import com.chetu.user.activity.AddMerchantActivity;
import com.chetu.user.activity.CarIllegalActivity;
import com.chetu.user.activity.CarInsuranceActivity;
import com.chetu.user.activity.CarServiceActivity;
import com.chetu.user.activity.CollectActivity;
import com.chetu.user.activity.CouponActivity;
import com.chetu.user.activity.FootprintActivity;
import com.chetu.user.activity.IntegralActivity;
import com.chetu.user.activity.MainActivity;
import com.chetu.user.activity.MyGarageActivity;
import com.chetu.user.activity.MyOrderActivity;
import com.chetu.user.activity.MyProfileActivity;
import com.chetu.user.activity.NotebookActivity;
import com.chetu.user.activity.ServiceCenterActivity;
import com.chetu.user.activity.SetUpActivity;
import com.chetu.user.activity.WaitingReleaseActivity;
import com.chetu.user.activity.WebContentActivity;
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
    Fragment4Model model;
    ImageView iv_shezhi, iv_jinbi, imageView1;
    RelativeLayout rl_xiaoxi;
    TextView textView1, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9,
            tv_xiaoxinum;
    LinearLayout linearLayout1, linearLayout2, linearLayout3, linearLayout4, linearLayout5, linearLayout6,
            linearLayout7, linearLayout8, linearLayout9, linearLayout10, linearLayout11, linearLayout12,
            linearLayout13, linearLayout14, linearLayout15, linearLayout16, linearLayout17;

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
                    .load(URLs.IMGHOST + localUserInfo.getUserImage())
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
        linearLayout16 = findViewByID_My(R.id.linearLayout16);
        linearLayout17 = findViewByID_My(R.id.linearLayout17);

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
        linearLayout16.setOnClickListener(this);
        linearLayout17.setOnClickListener(this);

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
                model = response;
                //保存头像
                localUserInfo.setUserImage(response.getUser_info().getHeadPortrait());
                if (!response.getUser_info().getHeadPortrait().equals("") && getActivity() != null)
                    Glide.with(getActivity()).load(IMGHOST + localUserInfo.getUserImage())
                            .centerCrop()
//                            .placeholder(R.mipmap.headimg)//加载站位图
//                            .error(R.mipmap.headimg)//加载失败
                            .into(imageView1);//加载图片
                //保存昵称
                localUserInfo.setNickname(response.getUser_info().getUserName());
                if (!localUserInfo.getNickname().equals("")) {
                    textView3.setText(localUserInfo.getNickname());
                }
                //保存电话号码
                localUserInfo.setPhoneNumber(response.getUser_info().getUserPhone());
                textView4.setText(localUserInfo.getPhonenumber());

                //车辆信息
                if (response.getUser_info().getUser_sedan_info().getId() != null) {
                    textView2.setText(response.getUser_info().getUser_sedan_info().getBrandInfo().getGroupName() + "-" +
                            response.getUser_info().getUser_sedan_info().getBrandInfo().getSeriesName());
                    textView6.setText(response.getUser_info().getUser_sedan_info().getCompTime() + "天");//保养
                    textView7.setText(response.getUser_info().getUser_sedan_info().getCompTime() + "天");//交强险
                    textView8.setText(response.getUser_info().getUser_sedan_info().getMaintIime() + "天");//商业险
                    textView9.setText(response.getUser_info().getUser_sedan_info().getAnnualTime() + "天");//保养
                }


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
                //积分
                Bundle bundle2 = new Bundle();
                bundle2.putString("jifen", model.getUser_info().getUserBalance());
                CommonUtil.gotoActivityWithData(getActivity(), IntegralActivity.class, bundle2, false);
                break;
            case R.id.rl_xiaoxi:
                //消息
                String url = URLs.KFHOST + "/#/pages/chetu-kf/chetu-kf?token=" + localUserInfo.getToken() +
                        "&kf_userHash=" + localUserInfo.getKfuserhash() +
                        "&nickName=" + localUserInfo.getKfname() +
                        "&headerPic=" + localUserInfo.getKfhead();
                Bundle bundle = new Bundle();
                bundle.putString("url", url);
                CommonUtil.gotoActivityWithData(getActivity(), WebContentActivity.class, bundle, false);
//                CommonUtil.gotoActivity(getActivity(), .class);
                break;
            case R.id.linearLayout1:
                //个人资料
                CommonUtil.gotoActivity(getActivity(), MyProfileActivity.class);
                break;

            case R.id.linearLayout2:
                //违章
                CommonUtil.gotoActivity(getActivity(), CarIllegalActivity.class);
                break;
            case R.id.linearLayout3:
                //保养
                CommonUtil.gotoActivity(getActivity(), CarServiceActivity.class);
                break;
            case R.id.linearLayout4:
                //保险（交）
                CommonUtil.gotoActivity(getActivity(), CarInsuranceActivity.class);
                break;
            case R.id.linearLayout5:
                //保险（商）
                CommonUtil.gotoActivity(getActivity(), CarInsuranceActivity.class);
                break;
            case R.id.linearLayout6:
                //年检
//                CommonUtil.gotoActivity(getActivity(), .class);
                break;

            case R.id.linearLayout7:
                //我的订单
                CommonUtil.gotoActivity(getActivity(), MyOrderActivity.class);
                break;
            case R.id.linearLayout8:
                //优惠券
                CommonUtil.gotoActivity(getActivity(), CouponActivity.class);
                break;
            case R.id.linearLayout9:
                //待发布
                CommonUtil.gotoActivity(getActivity(), WaitingReleaseActivity.class);
                break;
            case R.id.linearLayout10:
                //记事本
                CommonUtil.gotoActivity(getActivity(), NotebookActivity.class);
                break;
            case R.id.linearLayout11:
                //足迹
                CommonUtil.gotoActivity(getActivity(), FootprintActivity.class);
                break;
            case R.id.linearLayout16:
                //我的收藏
                CommonUtil.gotoActivity(getActivity(), CollectActivity.class);
                break;
            case R.id.linearLayout12:
                //我的车库
                CommonUtil.gotoActivity(getActivity(), MyGarageActivity.class);
                break;
            case R.id.linearLayout13:
                //分享有礼
//                CommonUtil.gotoActivity(getActivity(), .class);
                Intent share_intent1 = new Intent();
                share_intent1.setAction(Intent.ACTION_SEND);
//                    share_intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                share_intent1.setType("text/plain");
//                share_intent1.putExtra(Intent.EXTRA_TEXT, model.getShare().getText() + "\n"
//                        + model.getShare().getUrl());
                share_intent1.putExtra(Intent.EXTRA_TEXT, "我发现一个很好用的APP" + "\n"
                        + "www.xxxxxx.com");
                share_intent1 = Intent.createChooser(share_intent1, "分享");
                startActivity(share_intent1);
                break;
            case R.id.linearLayout14:
                //申请加盟
                CommonUtil.gotoActivity(getActivity(), AddMerchantActivity.class);
                break;
            case R.id.linearLayout15:
                //客服中心
                CommonUtil.gotoActivity(getActivity(), ServiceCenterActivity.class);
                break;
            case R.id.linearLayout17:
                //聊天列表
                String url1 = URLs.KFHOST + "/#/pages/chetu-kf/chat_list?token=" + localUserInfo.getToken();
                Bundle bundle1 = new Bundle();
                bundle1.putString("url", url1);
                CommonUtil.gotoActivityWithData(getActivity(), WebContentActivity.class, bundle1, false);
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
