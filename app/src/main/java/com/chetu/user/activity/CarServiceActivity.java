package com.chetu.user.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chetu.user.R;
import com.chetu.user.base.BaseActivity;
import com.chetu.user.model.UpFileModel;
import com.chetu.user.net.URLs;
import com.chetu.user.okhttp.CallBackUtil;
import com.chetu.user.okhttp.OkhttpUtil;
import com.chetu.user.utils.MyLogger;
import com.chetu.user.view.pictureselector.FullyGridLayoutManager;
import com.chetu.user.view.pictureselector.GlideCacheEngine;
import com.chetu.user.view.pictureselector.GlideEngine;
import com.chetu.user.view.pictureselector.GridImageAdapter;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.decoration.GridSpacingItemDecoration;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;
import com.luck.picture.lib.tools.ScreenUtils;
import com.luck.picture.lib.tools.SdkVersionUtils;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by zyz on 2020/6/7.
 * 车辆服务查询
 */
public class CarServiceActivity extends BaseActivity {
    //tab
    LinearLayout ll_tab1, ll_tab2;
    View view1, view2;
    //车辆信息
    String y_user_sedan_id = "";
    ImageView iv_carlogo;
    TextView tv_carname,tv_carnum,tv_cardetail;

    //发布询价

    ImageView imageView1, imageView2, imageView3, imageView4,
            iv_del1, iv_del2, iv_del3, iv_del4;
    String imgType = "";
    String license_plate = "", frame_no = "", full_name = "", telephone = "", t_number = "", i_company = "",
            v_insure_city = "", license_img1 = "", license_img2 = "", number_img1 = "", number_img2 = "";



    //发布救援
    EditText editText1, editText2, editText3, editText4;

    /**
     * 选择图片
     */
    RecyclerView rv_addimg;
    GridImageAdapter mAdapter;
    int maxSelectNum = 6;//选择最多图片数量
    int spanCount = 3;//一行显示张数
    ArrayList<File> listFiles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carservice);
        mImmersionBar.reset()
                .statusBarColor(R.color.background)
                .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();

        /**
         * 选择图片
         */
        rv_addimg = findViewByID_My(R.id.rv_addimg);
        FullyGridLayoutManager manager = new FullyGridLayoutManager(this,
                spanCount, GridLayoutManager.VERTICAL, false);
        rv_addimg.setLayoutManager(manager);
        rv_addimg.addItemDecoration(new GridSpacingItemDecoration(spanCount,
                ScreenUtils.dip2px(this, 5), false));//中间值是设置间距
        mAdapter = new GridImageAdapter(CarServiceActivity.this, onAddPicClickListener);
        if (savedInstanceState != null && savedInstanceState.getParcelableArrayList("selectorList") != null) {
            mAdapter.setList(savedInstanceState.getParcelableArrayList("selectorList"));
        }
        mAdapter.setSelectMax(maxSelectNum);
        rv_addimg.setAdapter(mAdapter);
    }

    @Override
    protected void initView() {
        ll_tab1 = findViewByID_My(R.id.ll_tab1);
        ll_tab2 = findViewByID_My(R.id.ll_tab2);
        view1 = findViewByID_My(R.id.view1);
        view2 = findViewByID_My(R.id.view2);

        //车辆信息
        iv_carlogo = findViewByID_My(R.id.iv_carlogo);
        tv_carname = findViewByID_My(R.id.tv_carname);
        tv_carnum = findViewByID_My(R.id.tv_carnum);
        tv_cardetail = findViewByID_My(R.id.tv_cardetail);

        //车险询价
        editText1 = findViewByID_My(R.id.editText1);
        editText2 = findViewByID_My(R.id.editText2);
        editText3 = findViewByID_My(R.id.editText3);
        editText4 = findViewByID_My(R.id.editText4);
        imageView1 = findViewByID_My(R.id.imageView1);
        imageView2 = findViewByID_My(R.id.imageView2);
        imageView3 = findViewByID_My(R.id.imageView3);
        imageView4 = findViewByID_My(R.id.imageView4);
        iv_del1 = findViewByID_My(R.id.iv_del1);
        iv_del2 = findViewByID_My(R.id.iv_del2);
        iv_del3 = findViewByID_My(R.id.iv_del3);
        iv_del4 = findViewByID_My(R.id.iv_del4);

    }

    @Override
    protected void initData() {
        /*HashMap<String, String> params2 = new HashMap<>();
        params2.put("i_cy", "2");//1为普通保险 2为交强险
        RequestBaoXian(params2, 2);

        HashMap<String, String> params1 = new HashMap<>();
        params1.put("i_cy", "1");//1为普通保险 2为交强险
        RequestBaoXian(params1, 1);*/
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.ll_car:
                //选择车辆
                Intent intent1 = new Intent(CarServiceActivity.this, MyGarageActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putInt("type", 10001);
                intent1.putExtras(bundle1);
                startActivityForResult(intent1, 10001, bundle1);
                break;
            case R.id.linearLayout1:
                //发布询价
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.INVISIBLE);
                ll_tab1.setVisibility(View.VISIBLE);
                ll_tab2.setVisibility(View.GONE);
                break;
            case R.id.linearLayout2:
                //发布救援
                view1.setVisibility(View.INVISIBLE);
                view2.setVisibility(View.VISIBLE);
                ll_tab1.setVisibility(View.GONE);
                ll_tab2.setVisibility(View.VISIBLE);
                break;

            case R.id.tv_upload1:
                //提交
                if (match()) {
                    showProgress(true, getString(R.string.app_loading1));
                    Map<String, String> params = new HashMap<>();
                    params.put("license_plate", license_plate);
                    params.put("frame_no", frame_no);
                    params.put("full_name", full_name);
                    params.put("telephone", telephone);
                    params.put("t_number", t_number);
                    params.put("i_company", i_company);
                    params.put("v_insure_city", v_insure_city);
                    params.put("license_img", license_img1 + "||" + license_img2);
                    params.put("number_img", number_img1 + "||" + number_img2);
                    params.put("u_token", localUserInfo.getToken());
                    RequestUpData(params);
                }
                break;
        }
    }

    private boolean match() {
        license_plate = editText1.getText().toString().trim();
        if (TextUtils.isEmpty(license_plate)) {
            myToast("请输入车牌");
            return false;
        }
        frame_no = editText2.getText().toString().trim();
        if (TextUtils.isEmpty(frame_no)) {
            myToast("请输入车架号");
            return false;
        }
        full_name = editText3.getText().toString().trim();
        if (TextUtils.isEmpty(full_name)) {
            myToast("请输入姓名");
            return false;
        }
        telephone = editText4.getText().toString().trim();
        if (TextUtils.isEmpty(telephone)) {
            myToast("请输入电话");
            return false;
        }
        /*t_number = editText5.getText().toString().trim();
        if (TextUtils.isEmpty(t_number)) {
            myToast("请输入身份证号");
            return false;
        }
        i_company = editText6.getText().toString().trim();
        if (TextUtils.isEmpty(i_company)) {
            myToast("请输入商业保险公司名称");
            return false;
        }
        v_insure_city = editText7.getText().toString().trim();
        if (TextUtils.isEmpty(v_insure_city)) {
            myToast("请输入输入投保城市");
            return false;
        }*/
        if (TextUtils.isEmpty(license_img1)) {
            myToast("请上传行驶证主页");
            return false;
        }
        if (TextUtils.isEmpty(license_img2)) {
            myToast("请上传行驶证副页");
            return false;
        }
        if (TextUtils.isEmpty(number_img1)) {
            myToast("请上传身份证正面");
            return false;
        }
        if (TextUtils.isEmpty(number_img2)) {
            myToast("请上传身份证反面");
            return false;
        }
        return true;
    }

    private void RequestUpData(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.CarInsurance, params, headerMap, new CallBackUtil<Object>() {
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
                myToast("提交成功");
                finish();
            }
        });
    }

    @Override
    protected void updateView() {
        titleView.setTitle("发布");
        titleView.setBackground(R.color.background);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 10001:
                //选择车辆
                if (data != null) {
                    Bundle bundle1 = data.getExtras();
                    y_user_sedan_id = bundle1.getString("car_id");
                    tv_carname.setText(bundle1.getString("carname") );
                    tv_carnum.setText(bundle1.getString("carnum"));
                    tv_cardetail.setText(bundle1.getString("cardetail"));
                    Glide.with(CarServiceActivity.this).load(URLs.IMGHOST + bundle1.getString("carlogo"))
                            .centerCrop()
                            .into(iv_carlogo);//加载图片
                }
                break;
        }

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
                hideProgress();
//                myToast("头像修改成功");
                for (String s : response.getList()) {
                    switch (imgType) {
                        case "license_img1":
                            license_img1 = s;
                            iv_del1.setVisibility(View.VISIBLE);
                            Glide.with(CarServiceActivity.this).load(URLs.IMGHOST + s)
                                    .centerCrop()
                                    .into(imageView1);//加载图片
                            break;
                        case "license_img2":
                            license_img2 = s;
                            iv_del2.setVisibility(View.VISIBLE);
                            Glide.with(CarServiceActivity.this).load(URLs.IMGHOST + s)
                                    .centerCrop()
                                    .into(imageView2);//加载图片
                            break;
                        case "number_img1":
                            number_img1 = s;
                            iv_del3.setVisibility(View.VISIBLE);
                            Glide.with(CarServiceActivity.this).load(URLs.IMGHOST + s)
                                    .centerCrop()
                                    .into(imageView3);//加载图片
                            break;
                        case "number_img2":
                            number_img2 = s;
                            iv_del4.setVisibility(View.VISIBLE);
                            Glide.with(CarServiceActivity.this).load(URLs.IMGHOST + s)
                                    .centerCrop()
                                    .into(imageView4);//加载图片
                            break;
                    }
                }
            }
        });
    }

    /**
     * *****************************************选择图片********************************************
     */
    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            // 进入相册
            PictureSelector.create(CarServiceActivity.this)
                    .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                    .imageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项
                    .theme(R.style.picture_default_style)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style v2.3.3后 建议使用setPictureStyle()动态方式
                    .isWeChatStyle(false)// 是否开启微信图片选择风格
                    .isUseCustomCamera(false)// 是否使用自定义相机
//                        .setLanguage(language)// 设置语言，默认中文
                    .isPageStrategy(true)// 是否开启分页策略 & 每页多少条；默认开启
                    .isWithVideoImage(true)// 图片和视频是否可以同选,只在ofAll模式下有效
                    .isMaxSelectEnabledMask(true)// 选择数到了最大阀值列表是否启用蒙层效果
                    .loadCacheResourcesCallback(GlideCacheEngine.createCacheEngine())// 获取图片资源缓存，主要是解决华为10部分机型在拷贝文件过多时会出现卡的问题，这里可以判断只在会出现一直转圈问题机型上使用
//                        .setOutputCameraPath()// 自定义相机输出目录，只针对Android Q以下，例如 Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) +  File.separator + "Camera" + File.separator;
                    //.setButtonFeatures(CustomCameraView.BUTTON_STATE_BOTH)// 设置自定义相机按钮状态
                    .maxSelectNum(maxSelectNum)// 最大图片选择数量
                    .minSelectNum(1)// 最小选择数量
                    //.maxVideoSelectNum(1) // 视频最大选择数量
                    //.minVideoSelectNum(1)// 视频最小选择数量
                    .imageSpanCount(4)// 每行显示个数
                    .isReturnEmpty(false)// 未选择数据时点击按钮是否可以返回
                    .closeAndroidQChangeWH(true)//如果图片有旋转角度则对换宽高,默认为true
                    .closeAndroidQChangeVideoWH(!SdkVersionUtils.checkedAndroid_Q())// 如果视频有旋转角度则对换宽高,默认为false
                    //.isAndroidQTransform(false)// 是否需要处理Android Q 拷贝至应用沙盒的操作，只针对compress(false); && .isEnableCrop(false);有效,默认处理
                    .setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)// 设置相册Activity方向，不设置默认使用系统
                    .isOriginalImageControl(false)// 是否显示原图控制按钮，如果设置为true则用户可以自由选择是否使用原图，压缩、裁剪功能将会失效
                    .selectionMode(PictureConfig.MULTIPLE)// 多选PictureConfig.MULTIPLE or 单选 PictureConfig.SINGLE
                    .isSingleDirectReturn(true)// 单选模式下是否直接返回，PictureConfig.SINGLE模式下有效
                    .isPreviewImage(true)// 是否可预览图片
                    .isPreviewVideo(true)// 是否可预览视频
                    //.querySpecifiedFormatSuffix(PictureMimeType.ofJPEG())// 查询指定后缀格式资源
                    .isCamera(true)// 是否显示拍照按钮
                    .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                    //.imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg,Android Q使用PictureMimeType.PNG_Q
                    .isEnableCrop(false)// 是否裁剪
                    .isCompress(true)// 是否压缩
                    .compressQuality(60)// 图片压缩后输出质量 0~ 100
                    .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                    .synOrAsy(true)//同步true或异步false 压缩 默认同步
                    //.queryMaxFileSize(10)// 只查多少M以内的图片、视频、音频  单位M
                    //.compressSavePath(getPath())//压缩图片保存地址
                    //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效 注：已废弃
                    //.glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度 注：已废弃
                    .withAspectRatio(1, 1)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                    .hideBottomControls(false)// 是否显示uCrop工具栏，默认不显示
                    .isGif(true)// 是否显示gif图片
                    .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                    .circleDimmedLayer(false)// 是否圆形裁剪
                    //.setCircleDimmedBorderColor(ContextCompat.getColor(getApplicationContext(), R.color.app_color_white))// 设置圆形裁剪边框色值
                    //.setCircleStrokeWidth(3)// 设置圆形裁剪边框粗细
                    .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                    .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                    .isOpenClickSound(false)// 是否开启点击声音
                    .selectionData(mAdapter.getData())// 是否传入已选图片
                    .isDragFrame(true)// 是否可拖动裁剪框(固定)
                    //.videoMinSecond(10)// 查询多少秒以内的视频
                    //.videoMaxSecond(15)// 查询多少秒以内的视频
                    //.recordVideoSecond(10)//录制视频秒数 默认60s
                    .cutOutQuality(90)// 裁剪输出质量 默认100
                    .minimumCompressSize(100)// 小于多少kb的图片不压缩
                    //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                    //.cropImageWideHigh()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                    .rotateEnabled(true) // 裁剪是否可旋转图片
                    .scaleEnabled(true)// 裁剪是否可放大缩小图片
                    .forResult(new MyResultCallback(mAdapter));
        }
    };

    /**
     * 返回结果回调
     */
    private class MyResultCallback implements OnResultCallbackListener<LocalMedia> {
        private WeakReference<GridImageAdapter> mAdapterWeakReference;

        public MyResultCallback(GridImageAdapter adapter) {
            super();
            this.mAdapterWeakReference = new WeakReference<>(adapter);
        }

        @Override
        public void onResult(List<LocalMedia> result) {
            listFiles.clear();
            for (LocalMedia media : result) {
                MyLogger.i(">>>>>>压缩地址：" + media.getCompressPath());
                File file = new File(media.getCompressPath());
                listFiles.add(file);
                // TODO 可以通过PictureSelectorExternalUtils.getExifInterface();方法获取一些额外的资源信息，如旋转角度、经纬度等信息
            }
            if (mAdapterWeakReference.get() != null) {
                mAdapterWeakReference.get().setList(result);
                mAdapterWeakReference.get().notifyDataSetChanged();
            }
        }

        @Override
        public void onCancel() {
//            Log.i(TAG, "图片选择取消");
        }
    }
}
