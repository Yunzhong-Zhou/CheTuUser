package com.chetu.user.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.chetu.user.R;
import com.chetu.user.base.BaseActivity;
import com.chetu.user.fragment.Fragment1;
import com.chetu.user.fragment.Fragment2;
import com.chetu.user.fragment.Fragment3;
import com.chetu.user.fragment.Fragment4;
import com.chetu.user.model.FristAppModel;
import com.chetu.user.model.UpgradeModel;
import com.chetu.user.net.URLs;
import com.chetu.user.okhttp.CallBackUtil;
import com.chetu.user.okhttp.OkhttpUtil;
import com.chetu.user.utils.CommonUtil;
import com.chetu.user.utils.MyLogger;
import com.cretin.tools.fanpermission.FanPermissionListener;
import com.cretin.tools.fanpermission.FanPermissionUtils;
import com.cy.dialog.BaseDialog;
import com.hjm.bottomtabbar.BottomTabBar;
import com.maning.updatelibrary.InstallUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import androidx.appcompat.app.AlertDialog;
import okhttp3.Call;
import okhttp3.Response;

public class MainActivity extends BaseActivity {
    public static BottomTabBar mBottomTabBar;
    public static int item = 0;
    int isShowAd = 0;//是否显示弹窗
    //更新
    UpgradeModel model_up;

    //定位
    //声明AMapLocationClient类对象
    private AMapLocationClient mLocationClient = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSwipeBackEnable(false); //主 activity 可以调用该方法，禁止滑动删除

        mImmersionBar.reset()
                .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();

        //初始化定位
        mLocationClient = new AMapLocationClient(this);
        AMapLocationClientOption option = new AMapLocationClientOption();
        //设置定位场景，目前支持三种场景（签到、出行、运动，默认无场景）
        option.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.Transport);

        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。AMapLocationMode.Battery_Saving，低功耗模式。AMapLocationMode.Device_Sensors，仅设备模式。
        option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
        //获取一次定位结果：默认为false。
        option.setOnceLocation(true);
        //获取最近3s内精度最高的一次定位结果：
        //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        option.setOnceLocationLatest(true);
        //设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。
        option.setInterval(5 * 1000);
        //设置是否返回地址信息（默认返回地址信息）
        option.setNeedAddress(true);
        //设置是否允许模拟位置,默认为true，允许模拟位置
        option.setMockEnable(true);
        //单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
        option.setHttpTimeOut(30000);
        //是否开启定位缓存机制
        option.setLocationCacheEnable(false);

        mLocationClient.setLocationOption(option);

        //设置定位回调监听
        mLocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation != null) {
                    if (aMapLocation.getErrorCode() == 0) {

                        MyLogger.i("定位信息", "\n纬度：" + aMapLocation.getLatitude()
                                + "\n经度:" + aMapLocation.getLongitude()
                                + "\n地址:" + aMapLocation.getAddress());

                        localUserInfo.setCityname(aMapLocation.getCity());
                        localUserInfo.setLongitude(aMapLocation.getLongitude()+ "");
                        localUserInfo.setLatitude(aMapLocation.getLatitude() + "");

                    } else {
                        //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                        MyLogger.e("定位失败：", "location Error, ErrCode:"
                                + aMapLocation.getErrorCode() + ", errInfo:"
                                + aMapLocation.getErrorInfo());
                        myToast("" + aMapLocation.getErrorInfo());
                    }
                }
            }
        });

        //设置场景模式后最好调用一次stop，再调用start以保证场景模式生效
//        mLocationClient.stopLocation();
        // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
//        mLocationClient.startLocation();

        FanPermissionUtils.with(MainActivity.this)
                //添加所有你需要申请的权限
                //.permission(Permission.REQUEST_INSTALL_PACKAGES)// 申请安装包权限
                //.permission(Permission.SYSTEM_ALERT_WINDOW)// 申请悬浮窗权限
                //.permission(Permission.NOTIFICATION_SERVICE) // 申请通知栏权限
                //.permission(Permission.WRITE_SETTINGS)  // 申请系统设置权限
                .addPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)//写入
                .addPermissions(Manifest.permission.READ_EXTERNAL_STORAGE)//读取
                .addPermissions(Manifest.permission.ACCESS_COARSE_LOCATION)//定位
                .addPermissions(Manifest.permission.ACCESS_FINE_LOCATION)//定位
                .addPermissions(Manifest.permission.CALL_PHONE)//拨打电话
                .addPermissions(Manifest.permission.READ_PHONE_STATE)//读取手机状态
//                .addPermissions(Manifest.permission.ACCESS_WIFI_STATE)//访问WiFi状态
                .addPermissions(Manifest.permission.CAMERA)//相机

                //添加权限申请回调监听 如果申请失败 会返回已申请成功的权限列表，用户拒绝的权限列表和用户点击了不再提醒的永久拒绝的权限列表
                .setPermissionsCheckListener(new FanPermissionListener() {
                    @Override
                    public void permissionRequestSuccess() {
                        //所有权限授权成功才会回调这里
//                        mLocationClient.startLocation();
                    }
                    @Override
                    public void permissionRequestFail(String[] grantedPermissions, String[] deniedPermissions, String[] forceDeniedPermissions) {
                        //当有权限没有被授权就会回调这里
                        //会返回已申请成功的权限列表（grantedPermissions）
                        //用户拒绝的权限列表（deniedPermissions）
                        //用户点击了不再提醒的永久拒绝的权限列表（forceDeniedPermissions）
                    }
                })
                //生成配置
                .createConfig()
                //配置是否强制用户授权才可以使用，当设置为true的时候，如果用户拒绝授权，会一直弹出授权框让用户授权
                .setForceAllPermissionsGranted(true)
                //配置当用户点击了不再提示的时候，会弹窗指引用户去设置页面授权，这个参数是弹窗里面的提示内容
                .setForceDeniedPermissionTips("请前往设置->应用->【" + FanPermissionUtils.getAppName(MainActivity.this) + "】->权限中打开相关权限，否则功能无法正常运行！")
                //构建配置并生效
                .buildConfig()
                //开始授权
                .startCheckPermission();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        item = 0;
    }

    @Override
    protected void initView() {
        mBottomTabBar = findViewByID_My(R.id.bottom_tab_bar);

        mBottomTabBar
                .init(getSupportFragmentManager())//初始化方法，必须第一个调用；传入参数为V4包下的FragmentManager
//                .setImgSize(50,50)//设置ICON图片的尺寸
//                .setFontSize(8)//设置文字的尺寸
//                .setTabPadding(4,6,10)//设置ICON图片与上部分割线的间隔、图片与文字的间隔、文字与底部的间隔
                .setChangeColor(getResources().getColor(R.color.tab_blue), getResources().getColor(R.color.tab_black))//设置选中的颜色、未选中的颜色
                .addTabItem(getString(R.string.fragment1), R.mipmap.tab1_1, R.mipmap.tab1_0, Fragment1.class)//设置文字、选中图片、未选中图片、fragment
                .addTabItem(getString(R.string.fragment2), R.mipmap.tab2_1, R.mipmap.tab2_0, Fragment2.class)//设置文字、选中图片、未选中图片、fragment
                .addTabItem(getString(R.string.fragment3), R.mipmap.tab3_1, R.mipmap.tab3_0, Fragment3.class)//设置文字、选中图片、未选中图片、fragment
                .addTabItem(getString(R.string.fragment4), R.mipmap.tab4_1, R.mipmap.tab4_0, Fragment4.class)//设置文字、选中图片、未选中图片、fragment
//                .addTabItem(getString(R.string.fragment5), R.mipmap.tab4_1, R.mipmap.tab4_0, Fragment5.class)//设置文字、选中图片、未选中图片、fragment

                .isShowDivider(false)//设置是否显示分割线
                .setTabBarBackgroundColor(Color.WHITE)//设置底部导航栏颜色
//                .setTabBarBackgroundResource(R.drawable.yuanjiao_10_baise_top)//设置底部导航栏的背景图片【与设置底部导航栏颜色方法不能同时使用，否则会覆盖掉前边设置的颜色】
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name, View view) {
                        Log.i("TGA", "位置：" + position + "      选项卡的文字内容：" + name);
                        /*if (position == 1)
                            mBottomTabBar.setSpot(1, false);*/
                        switch (position) {
                            case 0:
                                MainActivity.item = 0;
//                                mImmersionBar.getTag("common").init();
                                mImmersionBar.reset()
                                        .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题
                                        .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                                        .init();
                                break;
                            case 1:
                                MainActivity.item = 1;
//                                mImmersionBar.getTag("common").init();
                                mImmersionBar.reset()
//                                        .statusBarColor(R.color.red)
                                        .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题
                                        .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                                        .init();
                                break;
                            case 2:
                                MainActivity.item = 2;
//                                mImmersionBar.getTag("common").init();
                                mImmersionBar.reset()
//                                        .statusBarColor(R.color.white)
                                        .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题
                                        .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                                        .init();
                                break;
                            case 3:
                                MainActivity.item = 3;
                                mImmersionBar.reset()
//                                        .statusBarColor(R.color.blue)
//                                        .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                                        .init();
                                break;
                            /*case 4:
                                MainActivity.item = 4;
                                mImmersionBar.reset()
//                                        .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                                        .init();
                                break;*/
                            default:
                                break;
                        }
                    }
                })
                .setCurrentTab(item);//设置当前选中的Tab，从0开始
    }

    @Override
    protected void initData() {
/*isShowAd = getIntent().getIntExtra("isShowAd", 0);
        if (isShowAd == 1) {
            //弹出广告窗 - 去完成
            final BaseDialog dialog = new BaseDialog(MainActivity.this);
            dialog.config(R.layout.dialog_showad1, 0.8f, Gravity.CENTER,
                    BaseDialog.AnimInType.CENTER, WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.WRAP_CONTENT, true).show();
            dialog.findViewById(R.id.textView).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    MainActivity.mBottomTabBar.setCurrentTab(1);//跳转到借款
//                    CommonUtil.gotoActivity(MainActivity.this, CommissionAccountActivity.class, false);
                }
            });
            dialog.findViewById(R.id.dismiss).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }*/
        /*if (localUserInfo.getIsVerified().equals("2")){
            showToast("您暂未完成认证，确定前往认证？",
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            CommonUtil.gotoActivity(MainActivity.this,Auth_CheZhuActivity.class,false);
                        }
                    });
        }*/
        //第一次启动获取数据
        RequestFrist(params);
        //更新
        Map<String, String> params = new HashMap<>();
        params.put("type","1");
        RequestUpgrade(params);//检查更新

//        RequestQianDao("?token=" + localUserInfo.getToken());//签到
    }

    @Override
    protected void updateView() {
        titleView.setVisibility(View.GONE);
    }

    /**
     * 双击退出函数
     */
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            exitBy2Click();
            return false;
        } else {
            return super.dispatchKeyEvent(event);
        }
    }

    private Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, getString(R.string.app_out), Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            //退出
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
    /**
     * 第一次启动需要获取的数据
     *
     * @param params
     */
    private void RequestFrist(Map<String, String> params) {
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
                localUserInfo.setKfuserhash(response.getConf_info().getKf_info().getUserHash());
                localUserInfo.setKfhead(URLs.IMGHOST+response.getConf_info().getKf_info().getHeadPortrait());
                localUserInfo.setKfname(response.getConf_info().getKf_info().getUserName());
            }
        });
    }
    private void RequestUpgrade(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Upgrade, params, headerMap, new CallBackUtil<UpgradeModel>() {
            @Override
            public UpgradeModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
//                hideProgress();
//                myToast(err);
            }

            @Override
            public void onResponse(UpgradeModel response) {
//                hideProgress();
                model_up = response;
                if (Integer.valueOf(CommonUtil.getVersionCode(MainActivity.this)) < Integer.valueOf(response.getVersion_code())) {
//                    handler1.sendEmptyMessage(0);
                    showUpdateDialog();
                } else {
//                    showToast("已经是最新版，无需更新");
                }
            }
        });
    }

    private void RequestQianDao(String string) {
        /*OkHttpClientManager.getAsyn(MainActivity.this, URLs.QianDao + string, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, String info, Exception e) {
//                hideProgress();
            }

            @Override
            public void onResponse(String response) {
                MyLogger.i(">>>>>>>>>签到" + response);
//                hideProgress();
                dialog = new BaseDialog(MainActivity.this);
                dialog.contentView(R.layout.dialog_qiandao)
                        .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT))
                        .animType(BaseDialog.AnimInType.CENTER)
                        .canceledOnTouchOutside(true)
                        .dimAmount(0.8f)
                        .show();

                dialog.findViewById(R.id.dismiss).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
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
                    final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);    //进度条，在下载的时候实时更新进度，提高用户友好度
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressDialog.setCancelable(false);//点击外部不消失，返回键没用
//        progressDialog.setCanceledOnTouchOutside(false);//点击外部不消失，返回键有用
                    progressDialog.setTitle(getString(R.string.update_hint3));
                    progressDialog.setMessage(getString(R.string.update_hint4));
                    progressDialog.setProgress(0);
                    progressDialog.show();

                    //下载APK
                    InstallUtils.with(MainActivity.this)
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
                                    InstallUtils.checkInstallPermission(MainActivity.this, new InstallUtils.InstallPermissionCallBack() {
                                        @Override
                                        public void onGranted() {
                                            //去安装APK
                                            //一加手机8.0碰到了安装解析失败问题请添加下面判断
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                                //先获取是否有安装未知来源应用的权限
                                                boolean haveInstallPermission = MainActivity.this.getPackageManager().canRequestPackageInstalls();
                                                if (!haveInstallPermission) {
                                                    //跳转设置开启允许安装
                                                    Uri packageURI = Uri.parse("package:" + MainActivity.this.getPackageName());
                                                    Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, packageURI);
                                                    startActivityForResult(intent, 1000);
                                                    return;
                                                }
                                            }
                                            InstallUtils.installAPK(MainActivity.this, path, new InstallUtils.InstallCallBack() {
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
                                            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                                                    .setTitle(getString(R.string.update_hint7))
                                                    .setMessage(getString(R.string.update_hint8))
                                                    .setNegativeButton(getString(R.string.app_cancel), null)
                                                    .setPositiveButton(getString(R.string.update_hint9), new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            //打开设置页面
                                                            InstallUtils.openInstallPermissionSetting(MainActivity.this, new InstallUtils.InstallPermissionCallBack() {
                                                                @Override
                                                                public void onGranted() {
                                                                    //去安装APK
                                                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                                                        //先获取是否有安装未知来源应用的权限
                                                                        boolean haveInstallPermission = MainActivity.this.getPackageManager().canRequestPackageInstalls();
                                                                        if (!haveInstallPermission) {
                                                                            //跳转设置开启允许安装
                                                                            Uri packageURI = Uri.parse("package:" + MainActivity.this.getPackageName());
                                                                            Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, packageURI);
                                                                            startActivityForResult(intent, 1000);
                                                                            return;
                                                                        }
                                                                    }
                                                                    InstallUtils.installAPK(MainActivity.this, path, new InstallUtils.InstallCallBack() {
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
                    Toast.makeText(MainActivity.this, getString(R.string.update_hint1),
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
}
