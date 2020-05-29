package com.chetu.user.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chetu.user.R;
import com.chetu.user.base.BaseActivity;
import com.chetu.user.model.CodeModel;
import com.chetu.user.model.Fragment4Model;
import com.chetu.user.model.MyProfileModel;
import com.chetu.user.net.URLs;
import com.chetu.user.okhttp.CallBackUtil;
import com.chetu.user.okhttp.OkhttpUtil;
import com.chetu.user.utils.MyChooseImages;
import com.chetu.user.utils.MyLogger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import id.zelory.compressor.Compressor;
import okhttp3.Call;
import okhttp3.Response;

import static com.chetu.user.net.URLs.IMGHOST;
import static com.chetu.user.utils.MyChooseImages.REQUEST_CODE_CAPTURE_CAMEIA;
import static com.chetu.user.utils.MyChooseImages.REQUEST_CODE_PICK_IMAGE;


/**
 * Created by fafukeji01 on 2017/5/8.
 * 我的资料
 */

public class MyProfileActivity extends BaseActivity {
    //选择图片及上传
//    ArrayList<String> listFileNames = new ArrayList<>();
    ArrayList<File> listFiles = new ArrayList<>();
    ImageView imageView1, iv_nan, iv_nv;
    TextView textView, textView1, textView2;
    EditText editText1, editText2, editText3;
    private TimeCount time;

    String phonenum = "", code = "", industry = "";

    MyProfileModel model;
    int i1 = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile);
        mImmersionBar.reset()
                .statusBarColor(R.color.background)
                .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
//        findViewByID_My(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(this), 0, 0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (time != null)
            time.cancel();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void initView() {
        imageView1 = findViewByID_My(R.id.imageView1);
        textView = findViewByID_My(R.id.textView);
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        editText1 = findViewByID_My(R.id.editText1);
        editText2 = findViewByID_My(R.id.editText2);
        editText3 = findViewByID_My(R.id.editText3);

        iv_nan = findViewByID_My(R.id.iv_nan);
        iv_nv = findViewByID_My(R.id.iv_nv);

        editText1.setText(localUserInfo.getNickname());
        editText2.setText(localUserInfo.getPhonenumber());
        if (!localUserInfo.getUserImage().equals(""))
            Glide.with(this)
                    .load(IMGHOST + localUserInfo.getUserImage())
                    .centerCrop()
//                    .placeholder(R.mipmap.headimg)//加载站位图
//                    .error(R.mipmap.headimg)//加载失败
                    .into(imageView1);//加载图片

    }

    @Override
    protected void initData() {
        //获取个人信息
        showProgress(true, getString(R.string.app_loading));
        params.put("u_token", localUserInfo.getToken());
        requestInfo(params);
        time = new TimeCount(60000, 1000);//构造CountDownTimer对象
    }

    private void requestInfo(Map<String, String> params) {
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

        /*OkHttpClientManager.getAsyn(MyProfileActivity.this, URLs.Info + string, new OkHttpClientManager.ResultCallback<MyProfileModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }
            }
            @Override
            public void onResponse(MyProfileModel response) {
                MyLogger.i(">>>>>>>>>个人信息" + response);
                model = response;
                //头像
                if (!response.getHead().equals(""))
                    Glide.with(MyProfileActivity.this)
                            .load(OkHttpClientManager.IMGHOST + response.getHead())
                            .centerCrop()
//                            .placeholder(R.mipmap.headimg)//加载站位图
//                            .error(R.mipmap.headimg)//加载失败
                            .into(imageView1);//加载图片
                else
                    imageView1.setImageResource(R.mipmap.headimg);

                //昵称
                textView1.setText(response.getNickname());
                //手机号
                textView2.setText(response.getMobile());
                //行业
                textView3.setText(response.getIndustry());

                //保存是否认证
                localUserInfo.setIsVerified(response.getIs_certification()+"");//1 认证 2 未认证
                //实名认证//1已认证2未认证
                if (response.getIs_certification() == 1) {
                    textView4.setText("已认证");
                } else {
                    textView4.setText("未认证");
                }

                localUserInfo.setPhoneNumber(response.getMobile());
                localUserInfo.setNickname(response.getNickname());
//                localUserInfo.setEmail(response.getEmail());
                localUserInfo.setUserImage(response.getHead());

                hideProgress();
            }
        });*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textView:
                //上传资料
                if (match()) {
                    textView.setClickable(false);
//                    showProgress(true, "正在修改，请稍候...");
//                    params.put("token", localUserInfo.getToken());
//                    params.put("nickname", textView1.getText().toString().trim());
//                    params.put("industry", industry);
//                    RequestChangeProfile(filenames, files, params);//修改
                }
                break;
            case R.id.linearLayout1:
                //头像
                MyChooseImages.showPhotoDialog(this);
                break;
            case R.id.textView1:
                //获取验证码
                phonenum = editText2.getText().toString().trim();
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
            case R.id.ll_nan:
                //男
                iv_nan.setImageResource(R.mipmap.ic_xuanzhong);
                iv_nv.setImageResource(R.mipmap.ic_weixuan);
                break;
            case R.id.ll_nv:
                //女
                iv_nan.setImageResource(R.mipmap.ic_weixuan);
                iv_nv.setImageResource(R.mipmap.ic_xuanzhong);
                break;
            case R.id.textView2:
                //年龄

                break;
        }
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
                editText3.setText(response.getV_code());
            }
        });
    }

    //修改信息
    private void RequestUpFile(Map<String, File> fileMap, Map<String, String> params) {
        OkhttpUtil.okHttpUploadMapFile(URLs.UpFile, fileMap, "image", params, headerMap, new CallBackUtil() {
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
                myToast("头像修改成功");

            }
        });
    }

    private void RequestUpFile(Map<String, String> params, List<File> fileList, String fileKey) {
        OkhttpUtil.okHttpUploadListFile(URLs.UpFile, params, fileList, fileKey, "image", headerMap, new CallBackUtil() {
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
                myToast("头像修改成功");
            }
        });
    }

    private boolean match() {
//        phonenum = textView2.getText().toString().trim();
        /*if (TextUtils.isEmpty(phonenum)) {
            myToast("请输入新手机号码");
            return false;
        }*/
        return true;
    }

    @Override
    protected void updateView() {
        titleView.setTitle("个人资料");
        titleView.setBackground(R.color.background);
    }

    /**
     * *****************************************选择图片********************************************
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Uri uri = null;
            String imagePath = null;
            switch (requestCode) {
                case REQUEST_CODE_CAPTURE_CAMEIA:
                    //相机
                    uri = Uri.parse("");
                    uri = Uri.fromFile(new File(MyChooseImages.imagepath));
                    imagePath = uri.getPath();
                    break;
                case REQUEST_CODE_PICK_IMAGE:
                    //相册
                    uri = data.getData();
                    //处理得到的url
                    ContentResolver cr = this.getContentResolver();
                    Cursor cursor = null;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        cursor = cr.query(uri, null, null, null, null, null);
                    }
                    if (cursor != null) {
                        cursor.moveToFirst();
                        imagePath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                    } else {
                        imagePath = uri.getPath();
                    }
                    break;
            }
            if (uri != null) {
                MyLogger.i(">>>>>>>>>>获取到的图片路径1：" + imagePath);
                //图片过大解决方法
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 2;
                Bitmap bitmap = BitmapFactory.decodeFile(imagePath, options);

                imageView1.setImageBitmap(bitmap);
                imageView1.setScaleType(ImageView.ScaleType.CENTER_CROP);

//                listFileNames = new ArrayList<>();
//                listFileNames.add("head");

                Uri uri1 = Uri.parse("");
                /*uri1 = Uri.fromFile(new File(imagePath));
                File file1 = new File(FileUtil.getPath(this, uri1));*/
                File file1 = new File(imagePath);
                listFiles = new ArrayList<>();
                File newFile = null;
                try {
                    newFile = new Compressor(this).compressToFile(file1);
                    listFiles.add(newFile);
//                    MyLogger.i(">>>>>选择图片结果>>>>>>>>>" + listFileNames.toString() + ">>>>>>" + listFiles.toString());

                    Map<String, File> fileMap = new HashMap<>();
//                    fileMap.put("picture", newFile);
                    Map<String, String> params = new HashMap<>();
                    params.put("sn", "773EDB6D2715FACF9C93354CAC5B1A3372872DC4D5AC085867C7490E9984D33E");
//                    RequestUpFile(fileMap, params);
                    RequestUpFile(params,listFiles,"picture");

                } catch (IOException e) {
                    e.printStackTrace();
                    myToast(getString(R.string.app_imgerr));
                }
            }
        }

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
}
