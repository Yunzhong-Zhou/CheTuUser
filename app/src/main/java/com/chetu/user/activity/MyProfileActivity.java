package com.chetu.user.activity;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bumptech.glide.Glide;
import com.chetu.user.R;
import com.chetu.user.base.BaseActivity;
import com.chetu.user.model.CodeModel;
import com.chetu.user.model.Fragment4Model;
import com.chetu.user.model.UpFileModel;
import com.chetu.user.net.URLs;
import com.chetu.user.okhttp.CallBackUtil;
import com.chetu.user.okhttp.OkhttpUtil;
import com.chetu.user.utils.CommonUtil;
import com.chetu.user.utils.MyChooseImages;
import com.chetu.user.utils.MyLogger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
    ImageView imageView1, iv_nan, iv_nv, iv_gongli, iv_nongli;
    TextView textView, textView1, textView2;
    EditText editText1, editText2, editText3;
    private TimeCount time;

    TimePickerView pvTime1;

    String user_phone = "", vcode = "", user_name = "", head_portrait = "", u_gender = "男", birthday = "", lunar_calendar = "公历";

    //    Fragment4Model model;
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

        iv_gongli = findViewByID_My(R.id.iv_gongli);
        iv_nongli = findViewByID_My(R.id.iv_nongli);

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
                head_portrait = response.getUser_info().getHeadPortrait();
                Glide.with(MyProfileActivity.this).load(URLs.IMGHOST + response.getUser_info().getHeadPortrait())
                        .centerCrop()
//                            .placeholder(R.mipmap.headimg)//加载站位图
//                            .error(R.mipmap.headimg)//加载失败
                        .into(imageView1);//加载图片
                //姓名
                editText1.setText(response.getUser_info().getUserName());
                //手机
                editText2.setText(response.getUser_info().getUserPhone());
                //性别
                if (response.getUser_info().getSetup_info().getU_gender() != null) {
                    if (response.getUser_info().getSetup_info().getU_gender().equals("男")) {
                        u_gender = "男";
                        iv_nan.setImageResource(R.mipmap.ic_xuanzhong);
                        iv_nv.setImageResource(R.mipmap.ic_weixuan);
                    } else {
                        u_gender = "女";
                        iv_nan.setImageResource(R.mipmap.ic_weixuan);
                        iv_nv.setImageResource(R.mipmap.ic_xuanzhong);
                    }
                    //生日
                    birthday = response.getUser_info().getSetup_info().getBirthday();
                    if (response.getUser_info().getSetup_info().getLunarCalendar() != null && response.getUser_info().getSetup_info().getLunarCalendar().equals("公历")) {
                        lunar_calendar = "公历";
                        iv_gongli.setImageResource(R.mipmap.ic_xuanzhong);
                        iv_nongli.setImageResource(R.mipmap.ic_weixuan);
                    } else {
                        lunar_calendar = "农历";
                        iv_gongli.setImageResource(R.mipmap.ic_weixuan);
                        iv_nongli.setImageResource(R.mipmap.ic_xuanzhong);
                    }

                    textView2.setText(response.getUser_info().getSetup_info().getBirthday());
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textView:
                //上传资料
                if (match()) {
                    textView.setClickable(false);
                    showProgress(true, "正在提交数据，请稍候...");
                    params.put("u_token", localUserInfo.getToken());
                    params.put("user_name", user_name);
                    params.put("u_gender", u_gender);
                    params.put("birthday", birthday);
                    params.put("lunar_calendar", lunar_calendar);
                    params.put("user_phone", user_phone);
                    params.put("vcode", vcode);
                    params.put("head_portrait", head_portrait);
                    RequestChage(params);//修改
                }
                break;
            case R.id.linearLayout1:
                //头像
                MyChooseImages.showPhotoDialog(this);
                break;
            case R.id.textView1:
                //获取验证码
                user_phone = editText2.getText().toString().trim();
                if (TextUtils.isEmpty(user_phone)) {
                    myToast("请输入手机号");
                } else {
                    showProgress(true, "正在获取短信验证码...");
                    textView1.setClickable(false);
                    HashMap<String, String> params = new HashMap<>();
                    params.put("user_phone", user_phone);
//                    params.put("type", "1");
                    RequestCode(params);//获取验证码
                }
                break;
            case R.id.ll_nan:
                //男
                u_gender = "男";
                iv_nan.setImageResource(R.mipmap.ic_xuanzhong);
                iv_nv.setImageResource(R.mipmap.ic_weixuan);
                break;
            case R.id.ll_nv:
                //女
                u_gender = "女";
                iv_nan.setImageResource(R.mipmap.ic_weixuan);
                iv_nv.setImageResource(R.mipmap.ic_xuanzhong);
                break;
            case R.id.ll_gongli:
                //公历
                lunar_calendar = "公历";
                iv_gongli.setImageResource(R.mipmap.ic_xuanzhong);
                iv_nongli.setImageResource(R.mipmap.ic_weixuan);
                break;
            case R.id.ll_nongli:
                //农历
                lunar_calendar = "农历";
                iv_gongli.setImageResource(R.mipmap.ic_weixuan);
                iv_nongli.setImageResource(R.mipmap.ic_xuanzhong);

                break;
            case R.id.textView2:
                //年龄
                if (lunar_calendar.equals("公历")) {
                    setDate("请选择出生日期(公历)", textView2, textView2.getText().toString().trim(), false);
                } else {
                    setDate("请选择出生日期(农历)", textView2, textView2.getText().toString().trim(), true);
                }

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

    /**
     * 修改信息
     *
     * @param params
     */
    private void RequestChage(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.ChageProfile, params, headerMap, new CallBackUtil<Object>() {
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
                myToast("修改成功");
                finish();
            }
        });
    }

    /**
     * 上传文件 map 方式 暂时不用，用下面list方式
     *
     * @param fileMap
     * @param params
     */
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

    /**
     * 上传文件 list 方式
     *
     * @param params
     * @param fileList
     * @param fileKey
     */
    private void RequestUpFile(Map<String, String> params, List<File> fileList, String fileKey) {
        OkhttpUtil.okHttpUploadListFile(URLs.UpFile, params, fileList, fileKey, "image", headerMap, new CallBackUtil<UpFileModel>() {
            @Override
            public UpFileModel onParseResponse(Call call, Response response) {
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
            public void onResponse(UpFileModel response) {
//                myToast("头像修改成功");
                for (String s : response.getList()) {
                    head_portrait = s;
                }
            }
        });
    }

    private boolean match() {
        user_name = editText1.getText().toString().trim();
        if (TextUtils.isEmpty(user_name)) {
            myToast("请输入姓名");
            return false;
        }
        user_phone = editText2.getText().toString().trim();
        if (TextUtils.isEmpty(user_phone)) {
            myToast("请输入手机号");
            return false;
        }
        vcode = editText3.getText().toString().trim();
        if (TextUtils.isEmpty(vcode)) {
            myToast("请输入验证码");
            return false;
        }
        birthday = textView2.getText().toString().trim();
        if (TextUtils.isEmpty(birthday)) {
            myToast("请选择出生日期");
            return false;
        }
        return true;
    }

    @Override
    protected void updateView() {
        titleView.setTitle("个人资料");
        titleView.setBackground(R.color.background);
    }

    //预约时间
    private void setDate(String string, TextView textView, String date, boolean isNongLi) {
        //获取当前时间
        Calendar calendar = Calendar.getInstance();
        //年
        int year = calendar.get(Calendar.YEAR);
        //月
        int month = calendar.get(Calendar.MONTH);
        //日
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        //小时
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        //分钟
        int minute = calendar.get(Calendar.MINUTE);
        //秒
        int second = calendar.get(Calendar.SECOND);

        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();

        if (!date.equals("")) {
            try {
                String[] strArr = date.split("-");//拆分日期 得到年月日
                selectedDate.set(Integer.valueOf(strArr[0]), Integer.valueOf(strArr[1]) - 1, Integer.valueOf(strArr[2]));
            } catch (IllegalStateException e) {
                // Only fullscreen activities can request orientation
                e.printStackTrace();
            }

        }

        //正确设置方式 原因：注意事项有说明
//        startDate.set(year, month, day);
        startDate.set(1900, 1, 1);

        //当前时间加3天
//        calendar.add(Calendar.YEAR, 100);
        endDate.set(year, month, day);
        /*endDate.set(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));*/


        pvTime1 = new TimePickerBuilder(MyProfileActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                textView.setText(CommonUtil.getTime(date));
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确定")//确认按钮文字
                .setContentTextSize(15)//滚轮文字大小
                .setTitleSize(16)//标题文字大小
                .setTitleText(string)//标题文字
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(false)//是否循环滚动
                .setLunarCalendar(isNongLi)//农历开关
                .setTitleColor(getResources().getColor(R.color.black2))//标题文字颜色
                .setSubmitColor(getResources().getColor(R.color.blue))//确定按钮文字颜色
                .setCancelColor(getResources().getColor(R.color.blue))//取消按钮文字颜色
                .setTitleBgColor(getResources().getColor(R.color.black5))//标题背景颜色 Night mode
                .setBgColor(getResources().getColor(R.color.white))//滚轮背景颜色 Night mode
                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                .setRangDate(startDate, endDate)//起始终止年月日设定
                .setLabel("年", "月", "日", "时", "分", "秒")//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(true)//是否显示为对话框样式
                .build();

        Dialog mDialog = pvTime1.getDialog();
        if (mDialog != null) {

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);
            params.leftMargin = 0;
            params.rightMargin = 0;
            pvTime1.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
                dialogWindow.setDimAmount(0.1f);
            }
        }
        pvTime1.show();
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
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                        cursor = cr.query(uri, null, null, null, null, null);
                        if (cursor != null) {
                            cursor.moveToFirst();
                            try {
                                imagePath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                            } catch (Exception e) {
                                e.printStackTrace();
                                myToast(getString(R.string.app_error));
                            } finally {
                                if (cursor != null)
                                    cursor.close();
                            }
                        }

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

                /*Uri uri1 = Uri.parse("");
                uri1 = Uri.fromFile(new File(imagePath));
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
                    RequestUpFile(params, listFiles, "picture");

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
