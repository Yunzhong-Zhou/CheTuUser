package com.chetu.user.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chetu.user.R;
import com.chetu.user.base.BaseActivity;
import com.chetu.user.model.CodeModel;
import com.chetu.user.model.LoginModel;
import com.chetu.user.model.UpgradeModel;
import com.chetu.user.net.URLs;
import com.chetu.user.okhttp.CallBackUtil;
import com.chetu.user.okhttp.OkhttpUtil;
import com.chetu.user.utils.CommonUtil;
import com.chetu.user.utils.MyLogger;
import com.chetu.user.utils.permission.PermissionsActivity;
import com.chetu.user.utils.permission.PermissionsChecker;
import com.cy.dialog.BaseDialog;
import com.maning.updatelibrary.InstallUtils;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AlertDialog;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by fafukeji01 on 2017/4/25.
 * 登录
 */
public class LoginActivity extends BaseActivity {
    String code = "";
    private EditText editText1, editText2;
    private TextView textView1, textView2;
    private ImageView image_wechat;

    private String phonenum = "", password = "";

    private TimeCount time;

    //更新
    UpgradeModel model_up;

    private int REQUEST_CODE = 0; // 请求码
    // 所需的全部权限
    static final String[] PERMISSIONS = new String[]{
            Manifest.permission.CALL_PHONE,
            Manifest.permission.CAMERA,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            //定位
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
//            android.Manifest.permission.ACCESS_BACKGROUND_LOCATION,
            /*//录音权限
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.VIBRATE*/
            /* //网络
             Manifest.permission.INTERNET
             Manifest.permission.ACCESS_NETWORK_STATE,
             Manifest.permission.WAKE_LOCK,
             Manifest.permission.CHANGE_WIFI_STATE,
             Manifest.permission.ACCESS_WIFI_STATE,
             Manifest.permission.WRITE_SETTINGS,
             Manifest.permission.VIBRATE*/
    };
    private PermissionsChecker mPermissionsChecker; // 权限检测器

    IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mImmersionBar.reset().init();
        //        findViewById(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(this), 0, 0);
//        CommonUtil.setMargins(findViewByID_My(R.id.headView),0, (int) CommonUtil.getStatusBarHeight(this), 0, 0);


        setSwipeBackEnable(false); //主 activity 可以调用该方法，禁止滑动删除

        mPermissionsChecker = new PermissionsChecker(this);

        //注册微信api
        api = WXAPIFactory.createWXAPI(this, "wx79d0350178a9ff3a", false);
        api.registerApp("wx79d0350178a9ff3a");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (time != null)
            time.cancel();
    }

    @Override
    protected void initView() {
        time = new TimeCount(60000, 1000);//构造CountDownTimer对象

        editText1 = findViewByID_My(R.id.editText1);
        editText2 = findViewByID_My(R.id.editText2);

        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);

        image_wechat = findViewByID_My(R.id.image_wechat);
    }

    @Override
    protected void initData() {
//        RequestUpgrade("?app_type=" + 1
//                + "&type=" + "owner");//检查更新//"driver 司机 owner货主端"

        /*byte[] mBytes = null;
        String mString = "{阿达大as家阿sdf什顿附asd件好久}";
        AES mAes = new AES();
        try {
            mBytes = mString.getBytes("UTF-8");
        } catch (Exception e) {
            Log.i("qing", "MainActivity----catch");
        }
        String enString = mAes.encrypt(mBytes);
        MyLogger.i("加密后：" + enString);
        String deString = mAes.decrypt("P9ezA6lsRKVID383Rg5mwQ==");
        MyLogger.i("解密后：" + deString);*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textView1:
                //获取验证码
                phonenum = editText1.getText().toString().trim();
                if (TextUtils.isEmpty(phonenum)) {
                    myToast("请输入手机号");
                } else {
                    showProgress(true, "正在获取短信验证码...");
                    textView1.setClickable(false);
                    HashMap<String, String> params = new HashMap<>();
                    params.put("user_phone", phonenum);
//                    params.put("type", "1");
                    RequestCode(params);//获取验证码
                }
                break;

            case R.id.textView2:
                //确认登录
                if (match()) {
                    textView2.setClickable(false);
                    this.showProgress(true, "正在登录，请稍候...");
                    params.put("user_phone", phonenum);
                    params.put("vcode", password);
                    params.put("t_token", "");
                    params.put("action", "1");//1为验证码登陆 2为第三方登陆
                    /*//测试数据
                    params.put("user_phone", "18203048656");
                    params.put("vcode", "155119");
                    params.put("t_token", "");
                    params.put("action", "1");//1为验证码登陆 2为第三方登陆*/
                    RequestLogin(params);//登录
                }
//                CommonUtil.gotoActivity(LoginActivity.this, MainActivity.class, true);
                break;
            case R.id.image_wechat:
                //微信登录
                if (!api.isWXAppInstalled()) {
                    myToast("您的设备未安装微信客户端");
                } else {
                    SendAuth.Req req = new SendAuth.Req();
                    req.scope = "snsapi_userinfo";
                    req.state = "wechat_sdk_demo_test";
                    api.sendReq(req);
                    finish();
                }
                break;
        }
    }

    //登录
    private void RequestLogin(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Login, params, headerMap, new CallBackUtil<LoginModel>() {
            @Override
            public LoginModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                textView2.setClickable(true);
//                myToast("密码错误，请重新输入");
                if (!err.equals("")) {
                    myToast(err);
                }
            }

            @Override
            public void onResponse(LoginModel response) {
                textView2.setClickable(true);
                localUserInfo.setUserId(response.getUser_info().getUserHash());
                //保存Token
                localUserInfo.setToken(response.getUser_info().getUToken());
                //保存电话号码
//                localUserInfo.setPhoneNumber(response.getMobile());
                //保存是否认证
//                localUserInfo.setIsVerified(response.getIs_certification() + "");//1 认证 2 未认证
                //保存昵称
                localUserInfo.setNickname(response.getUser_info().getUserName());

                hideProgress();
                CommonUtil.gotoActivity(LoginActivity.this, MainActivity.class, true);
            }
        });
    }
    private void RequestCode(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Code, params, headerMap, new CallBackUtil<CodeModel>() {
            @Override
            public CodeModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                textView1.setClickable(true);
                if (!err.equals("")) {
                    showToast(err);
                }
            }

            @Override
            public void onResponse(CodeModel response) {
                hideProgress();
                textView1.setClickable(true);
                time.start();//开始计时
                myToast(getString(R.string.app_sendcode_hint));
                editText2.setText(response.getV_code());
            }
        });
    }

    private boolean match() {
        phonenum = editText1.getText().toString().trim();
        if (TextUtils.isEmpty(phonenum)) {
            myToast("请输入手机号");
            return false;
        }
        password = editText2.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            myToast("请输入验证码");
            return false;
        }

        return true;
    }

    @Override
    protected void updateView() {
        titleView.setVisibility(View.GONE);
    }

    //获取验证码倒计时
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {//计时完毕时触发
            textView1.setText(getString(R.string.app_reacquirecode));
            textView1.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {//计时过程显示
            textView1.setClickable(false);
            textView1.setText(millisUntilFinished / 1000 + getString(R.string.app_codethen));
        }
    }
    /*//屏蔽返回键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK)
            return true;//不执行父类点击事件
        return super.onKeyDown(keyCode, event);//继续执行父类其他点击事件
    }*/

    private void RequestUpgrade(String string) {
        /*OkHttpClientManager.getAsyn(LoginActivity.this, URLs.Upgrade + string, new OkHttpClientManager.ResultCallback<UpgradeModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
//                hideProgress();
            }

            @Override
            public void onResponse(UpgradeModel response) {
                MyLogger.i(">>>>>>>>>更新" + response);
//                hideProgress();
                model_up = response;
                if (Integer.valueOf(CommonUtil.getVersionCode(LoginActivity.this)) < Integer.valueOf(response.getVersion_code())) {
//                    handler1.sendEmptyMessage(0);
                    showUpdateDialog();
                } else {
//                    showToast("已经是最新版，无需更新");
                }
            }
        });*/
    }

    //显示是否要更新的对话框
    private void showUpdateDialog() {
        dialog.contentView(R.layout.dialog_upgrade)
                .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT))
                .animType(BaseDialog.AnimInType.CENTER)
                .canceledOnTouchOutside(true)
                .dimAmount(0.8f)
                .show();
        TextView textView1 = dialog.findViewById(R.id.textView1);
        TextView textView2 = dialog.findViewById(R.id.textView2);
        TextView textView3 = dialog.findViewById(R.id.textView3);
        TextView textView4 = dialog.findViewById(R.id.textView4);
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                        /*Intent intent = new Intent();
                        intent.setAction("android.intent.action.VIEW");
                        Uri content_url = Uri.parse(model_up.getUrl());
                        intent.setData(content_url);
                        startActivity(intent);*/
                if (Environment.getExternalStorageState().equals(
                        Environment.MEDIA_MOUNTED)) {
                    final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);    //进度条，在下载的时候实时更新进度，提高用户友好度
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressDialog.setCancelable(false);//点击外部不消失，返回键没用
//        progressDialog.setCanceledOnTouchOutside(false);//点击外部不消失，返回键有用
                    progressDialog.setTitle(getString(R.string.update_hint3));
                    progressDialog.setMessage(getString(R.string.update_hint4));
                    progressDialog.setProgress(0);
                    progressDialog.show();

                    //下载APK
                    InstallUtils.with(LoginActivity.this)
                            //必须-下载地址
                            .setApkUrl(model_up.getUrl())
                            //非必须-下载保存的文件的完整路径+/name.apk，使用自定义路径需要获取读写权限
//                                    .setApkPath(Constants.APK_SAVE_PATH)
                            //非必须-下载回调
                            .setCallBack(new InstallUtils.DownloadCallBack() {
                                @Override
                                public void onStart() {
                                    //下载开始
                                }

                                @Override
                                public void onComplete(final String path) {
                                    progressDialog.cancel();
                                    //下载完成
                                    //先判断有没有安装权限---适配8.0
                                    //如果不想用封装好的，可以自己去实现8.0适配
                                    InstallUtils.checkInstallPermission(LoginActivity.this, new InstallUtils.InstallPermissionCallBack() {
                                        @Override
                                        public void onGranted() {
                                            //去安装APK
                                            //一加手机8.0碰到了安装解析失败问题请添加下面判断
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                                //先获取是否有安装未知来源应用的权限
                                                boolean haveInstallPermission = LoginActivity.this.getPackageManager().canRequestPackageInstalls();
                                                if (!haveInstallPermission) {
                                                    //跳转设置开启允许安装
                                                    Uri packageURI = Uri.parse("package:" + LoginActivity.this.getPackageName());
                                                    Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, packageURI);
                                                    startActivityForResult(intent, 1000);
                                                    return;
                                                }
                                            }
                                            InstallUtils.installAPK(LoginActivity.this, path, new InstallUtils.InstallCallBack() {
                                                @Override
                                                public void onSuccess() {
                                                    myToast(getString(R.string.update_hint5));
                                                }

                                                @Override
                                                public void onFail(Exception e) {
                                                    myToast(getString(R.string.update_hint6) + e.toString());
                                                }
                                            });
                                        }

                                        @Override
                                        public void onDenied() {
                                            //弹出弹框提醒用户
                                            AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this)
                                                    .setTitle(getString(R.string.update_hint7))
                                                    .setMessage(getString(R.string.update_hint8))
                                                    .setNegativeButton(getString(R.string.app_cancel), null)
                                                    .setPositiveButton(getString(R.string.update_hint9), new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            //打开设置页面
                                                            InstallUtils.openInstallPermissionSetting(LoginActivity.this, new InstallUtils.InstallPermissionCallBack() {
                                                                @Override
                                                                public void onGranted() {
                                                                    //去安装APK
                                                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                                                        //先获取是否有安装未知来源应用的权限
                                                                        boolean haveInstallPermission = LoginActivity.this.getPackageManager().canRequestPackageInstalls();
                                                                        if (!haveInstallPermission) {
                                                                            //跳转设置开启允许安装
                                                                            Uri packageURI = Uri.parse("package:" + LoginActivity.this.getPackageName());
                                                                            Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, packageURI);
                                                                            startActivityForResult(intent, 1000);
                                                                            return;
                                                                        }
                                                                    }
                                                                    InstallUtils.installAPK(LoginActivity.this, path, new InstallUtils.InstallCallBack() {
                                                                        @Override
                                                                        public void onSuccess() {
                                                                            myToast(getString(R.string.update_hint5));
                                                                        }

                                                                        @Override
                                                                        public void onFail(Exception e) {
                                                                            myToast(getString(R.string.update_hint6) + e.toString());
                                                                        }
                                                                    });
                                                                }

                                                                @Override
                                                                public void onDenied() {
                                                                    //还是不允许咋搞？
                                                                    finish();
//                                                                            Toast.makeText(MainActivity.this, "不允许安装咋搞？强制更新就退出应用程序吧！", Toast.LENGTH_SHORT).show();
                                                                }
                                                            });
                                                        }
                                                    })
                                                    .create();
                                            alertDialog.show();
                                        }
                                    });

                                }

                                @Override
                                public void onLoading(long total, long current) {
                                    //下载中
                                    progressDialog.setMax((int) total);
                                    progressDialog.setProgress((int) current);
                                }

                                @Override
                                public void onFail(Exception e) {
                                    //下载失败
                                }

                                @Override
                                public void cancle() {
                                    //下载取消
                                }
                            })
                            //开始下载
                            .startDownload();
                } else {
                    Toast.makeText(LoginActivity.this, getString(R.string.update_hint1),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialog.findViewById(R.id.dismiss).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 缺少权限时, 进入权限配置页面
        if (mPermissionsChecker.lacksPermissions(PERMISSIONS)) {
            startPermissionsActivity();
        }
        code = getIntent().getStringExtra("code");
        MyLogger.i(">>>>>>>" + code);
        if (code != null && !code.equals("")) {
            /*this.showProgress(true, "正在获取微信登录参数，请稍候...");
            String url = "https://api.weixin.qq.com/sns/oauth2/access_token"
                    + "?appid="+"wx79d0350178a9ff3a"
                    +"&secret="+"77c539b9b3375eca54641a12b35b463b"
                    +"&code="+code
                    +"&grant_type=authorization_code";
            requestWeChat1(url);*/

            getIntent().removeExtra("code");
            this.showProgress(true, "正在登录，请稍候...");
            Map<String, String> params = new HashMap<>();
            params.put("user_phone", phonenum);
            params.put("vcode", password);
            params.put("t_token", code);
            params.put("action", "2");//1为验证码登陆 2为第三方登陆
            RequestWeChatLogin(params);//微信登录
        }
    }

    private void startPermissionsActivity() {
        PermissionsActivity.startActivityForResult(this, REQUEST_CODE, PERMISSIONS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 拒绝时, 关闭页面, 缺少主要权限, 无法运行
        if (requestCode == REQUEST_CODE && resultCode == PermissionsActivity.PERMISSIONS_DENIED) {
            finish();
        }
    }

    //微信登录
    private void RequestWeChatLogin(Map<String, String> params) {
        /*OkHttpClientManager.postAsyn(LoginActivity.this, URLs.Login1, params, new OkHttpClientManager.ResultCallback<WeChatLoginModel>() {
            @Override
            public void onError(final Request request, String info, Exception e) {
                hideProgress();
                if (!info.equals("")) {
                    myToast(info);
                }
            }

            @Override
            public void onResponse(final WeChatLoginModel response) {
                MyLogger.i(">>>>>>>>>登录" + response);
//                localUserInfo.setTime(System.currentTimeMillis() + "");
                if (response.getThird_id().equals("0")) {//登录通过
                    //保存Token
                    localUserInfo.setToken(response.getLogin_data().getFresh_token());
                    //保存电话号码
                    localUserInfo.setPhoneNumber(response.getLogin_data().getMobile());
                    //保存是否认证
                    localUserInfo.setIsVerified(response.getLogin_data().getIs_certification() + "");//1 认证 2 未认证
                    //保存昵称
                    localUserInfo.setNickname(response.getLogin_data().getNickname());
                    //保存环信ID
                    localUserInfo.setHxid(response.getLogin_data().getHx_username());
                } else {//未完善资料
                    hideProgress();
                    Bundle bundle = new Bundle();
                    bundle.putString("third_id", response.getThird_id());
                    CommonUtil.gotoActivityWithData(LoginActivity.this, RegisteredActivity.class, bundle, false);
                }

            }
        }, false);*/

    }

    //获取微信数据1
    private void requestWeChat1(String string) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(string)
                .get()//默认就是GET请求，可以不写
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String responseInfo = response.body().string();
                MyLogger.i(">>>>>>>>>微信数据1:\n" + responseInfo);
                String access_token = "";
                String openId = "";
                String refresh_token = "";
                try {
                    JSONObject jsonObject = new JSONObject(responseInfo);
                    refresh_token = jsonObject.getString("refresh_token");
                    access_token = jsonObject.getString("access_token");
                    openId = jsonObject.getString("openid");

                    String url = "https://api.weixin.qq.com/sns/userinfo"
                            + "?access_token=" + access_token
                            + "&openid=" + openId;

                    requestWeChat2(url);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                hideProgress();
            }
        });

    }

    //获取微信数据2
    private void requestWeChat2(String string) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(string)
                .get()//默认就是GET请求，可以不写
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String responseInfo = response.body().string();
                MyLogger.i(">>>>>>>>>微信数据2:\n" + responseInfo);
				/*
				{
  "openid": "OPENID",
  "nickname": "NICKNAME",
  "sex": 1,
  "province": "PROVINCE",
  "city": "CITY",
  "country": "COUNTRY",
  "headimgurl": "http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0",
  "privilege": ["PRIVILEGE1", "PRIVILEGE2"],
  "unionid": " o6_bmasdasdsad6_2sgVt7hMZOPfL"
}
				* */

                String openid = "";
                String nickname = "";
                String sex = "";
                String city = "";
                String headimgurl = "";
                try {
                    JSONObject jsonObject = new JSONObject(responseInfo);
                    openid = jsonObject.getString("openid");
                    nickname = jsonObject.getString("nickname");
                    headimgurl = jsonObject.getString("headimgurl");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                hideProgress();
            }
        });

    }
}
