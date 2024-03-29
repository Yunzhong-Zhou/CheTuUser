package com.chetu.user.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.chetu.user.R;
import com.chetu.user.base.BaseActivity;
import com.chetu.user.net.URLs;
import com.chetu.user.utils.CommonUtil;

/**
 * Created by zyz on 2020/5/25.
 * 设置
 */
public class SetUpActivity extends BaseActivity {
    TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
    }

    @Override
    protected void initView() {
        textView1 = findViewByID_My(R.id.textView1);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.linearLayout1:
                //清除缓存
                break;
            case R.id.linearLayout2:
                //意见反馈
                CommonUtil.gotoActivity(SetUpActivity.this, FeedBackActivity.class, false);
                break;
            case R.id.linearLayout3:
                //关于我们
                Bundle bundle = new Bundle();
                bundle.putString("url", URLs.HOST + "/single/h5/about?user_hash="+localUserInfo.getUserId());
                CommonUtil.gotoActivityWithData(SetUpActivity.this, WebContentActivity.class, bundle, false);
                break;
            case R.id.linearLayout4:
                //版本说明
//                CommonUtil.gotoActivity(SetUpActivity.this, VersionActivity.class, false);
                Bundle bundle1 = new Bundle();
                bundle1.putString("url", URLs.HOST + "/single/h5/version?user_hash="+localUserInfo.getUserId());
                CommonUtil.gotoActivityWithData(SetUpActivity.this, WebContentActivity.class, bundle1, false);
                break;
            case R.id.tv_out:
                //退出
                showToast("确认退出登录吗？",
                        getString(R.string.app_confirm),
                        getString(R.string.app_cancel), new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                /*showProgress(true, "正在注销登录，请稍候...");
                                requestOut("?token=" + localUserInfo.getToken());*/
                                localUserInfo.setUserId("");
                                localUserInfo.setUserName("");
                                localUserInfo.setToken("");
                                localUserInfo.setPhoneNumber("");
                                localUserInfo.setNickname("");
                                localUserInfo.setWalletaddr("");
                                localUserInfo.setEmail("");
                                localUserInfo.setUserImage("");
                                localUserInfo.setCarname("");
                                CommonUtil.gotoActivityWithFinishOtherAll(SetUpActivity.this, LoginActivity.class, true);
                            }
                        }, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                break;
        }
    }

    @Override
    protected void updateView() {
        titleView.setTitle("系统设置");
    }
}
