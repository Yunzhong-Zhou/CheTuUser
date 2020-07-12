package com.chetu.user.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chetu.user.R;
import com.chetu.user.base.BaseActivity;
import com.chetu.user.model.OrderDetailModel;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
import per.wsj.library.AndRatingBar;

/**
 * Created by zyz on 2020/7/1.
 * 发布评论-单个技师
 */
public class AddPingLunActivity extends BaseActivity {
    OrderDetailModel orderDetailModel;

    AndRatingBar ratingbar1, ratingbar2;
    EditText editText1, editText2;
    RecyclerView rv_addimg1, rv_addimg2;
    TextView tv_name, tv_confirm1, tv_confirm2;
    ImageView imageView;

    JSONArray jsonArray = new JSONArray();
    String y_store_id1 = "", y_order_id1 = "", y_goods_id1 = "", user_hash1 = "", star_c1 = "", y_msg1 = "", v_cy1 = "", imgstr1 = "";
    String y_store_id2 = "", y_order_id2 = "", y_goods_id2 = "", user_hash2 = "", star_c2 = "", y_msg2 = "", v_cy2 = "", imgstr2 = "";

    /**
     * 选择图片
     */
    GridImageAdapter mAdapter1;
    int maxSelectNum1 = 6;//选择最多图片数量
    int spanCount1 = 3;//一行显示张数
    ArrayList<File> listFiles1 = new ArrayList<>();
    /**
     * 选择图片
     */
    GridImageAdapter mAdapter2;
    int maxSelectNum2 = 6;//选择最多图片数量
    int spanCount2 = 3;//一行显示张数
    ArrayList<File> listFiles2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpinglun);
        /**
         * 选择图片
         */
        rv_addimg1 = findViewByID_My(R.id.rv_addimg1);
        FullyGridLayoutManager manager1 = new FullyGridLayoutManager(this,
                spanCount1, GridLayoutManager.VERTICAL, false);
        rv_addimg1.setLayoutManager(manager1);
        rv_addimg1.addItemDecoration(new GridSpacingItemDecoration(spanCount1,
                ScreenUtils.dip2px(this, 5), false));//中间值是设置间距
        mAdapter1 = new GridImageAdapter(AddPingLunActivity.this, onAddPicClickListener1);
        if (savedInstanceState != null && savedInstanceState.getParcelableArrayList("selectorList") != null) {
            mAdapter1.setList(savedInstanceState.getParcelableArrayList("selectorList"));
        }
        mAdapter1.setSelectMax(maxSelectNum1);
        rv_addimg1.setAdapter(mAdapter1);
        /**
         * 选择图片
         */
        rv_addimg2 = findViewByID_My(R.id.rv_addimg2);
        FullyGridLayoutManager manager2 = new FullyGridLayoutManager(this,
                spanCount2, GridLayoutManager.VERTICAL, false);
        rv_addimg2.setLayoutManager(manager2);
        rv_addimg2.addItemDecoration(new GridSpacingItemDecoration(spanCount2,
                ScreenUtils.dip2px(this, 5), false));//中间值是设置间距
        mAdapter2 = new GridImageAdapter(AddPingLunActivity.this, onAddPicClickListener2);
        if (savedInstanceState != null && savedInstanceState.getParcelableArrayList("selectorList") != null) {
            mAdapter2.setList(savedInstanceState.getParcelableArrayList("selectorList"));
        }
        mAdapter2.setSelectMax(maxSelectNum2);
        rv_addimg2.setAdapter(mAdapter2);
    }

    @Override
    protected void initView() {
        ratingbar1 = findViewByID_My(R.id.ratingbar1);
        ratingbar2 = findViewByID_My(R.id.ratingbar2);
        editText1 = findViewByID_My(R.id.editText1);
        editText2 = findViewByID_My(R.id.editText2);
        tv_name = findViewByID_My(R.id.tv_name);
        tv_confirm1 = findViewByID_My(R.id.tv_confirm1);
        tv_confirm2 = findViewByID_My(R.id.tv_confirm2);
        imageView = findViewByID_My(R.id.imageView);

    }

    @Override
    protected void initData() {
        orderDetailModel = (OrderDetailModel) getIntent().getSerializableExtra("OrderDetailModel");
        /*Glide.with(AddPingLunActivity.this).load(orderDetailModel.)
                .centerCrop()
//                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                .placeholder(R.mipmap.loading)//加载站位图
                .error(R.mipmap.zanwutupian)//加载失败
                .into(imageView);//加载图片*/

        tv_name.setText(orderDetailModel.getOrder_info().getKf_user_info().getUserName());


    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_confirm:
                //发表
                showProgress(true, getString(R.string.app_loading1));
                if (match1()) {
                    Map<String, String> params = new HashMap<>();
                    params.put("sn", "773EDB6D2715FACF9C93354CAC5B1A3372872DC4D5AC085867C7490E9984D33E");
                    RequestUpFile1(params, listFiles1, "picture");
                }
                break;
        }
    }

    private boolean match1() {
        y_store_id1 = orderDetailModel.getOrder_info().getYStoreId();
        y_order_id1 = orderDetailModel.getOrder_info().getYOrderId();
        y_goods_id1 = "0";
        user_hash1 = "";
        star_c1 = ratingbar1.getRating() + "";
        v_cy1 = "2";//1是技师  2门店
        y_msg1 = editText1.getText().toString().trim();
        if (TextUtils.isEmpty(y_msg1)) {
            showToast("请输入门店评价内容");
            return false;
        }
        if (listFiles1.size() == 0) {
            showToast("请上传门店评价图片");
            return false;
        }
        return true;
    }

    private boolean match2() {
        y_store_id2 = orderDetailModel.getOrder_info().getYStoreId();
        y_order_id2 = orderDetailModel.getOrder_info().getYOrderId();
        y_goods_id2 = "0";
        user_hash2 = orderDetailModel.getOrder_info().getKf_user_info().getUserHash();
        star_c2 = ratingbar2.getRating() + "";
        v_cy2 = "1";//1是技师  2门店
        y_msg2 = editText2.getText().toString().trim();
        if (TextUtils.isEmpty(y_msg2)) {
            showToast("请输入技师评价内容");
            return false;
        }
        if (listFiles2.size() == 0) {
            showToast("请上传技师评价图片");
            return false;
        }
        return true;
    }

    //上传图片
    private void RequestUpFile1(Map<String, String> params, List<File> fileList, String fileKey) {
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
//                hideProgress();
//                myToast("上传图片成功");
                for (String s : response.getList()) {
                    imgstr1 += s + "||";
                }
                if (!imgstr1.equals("")) {
                    imgstr1 = imgstr1.substring(0, imgstr1.length() - 2);
                }
                try {
                    JSONObject object1 = new JSONObject();
                    object1.put("y_store_id", y_store_id1);
                    object1.put("y_order_id", y_order_id1);
                    object1.put("y_goods_id", y_goods_id1);
                    object1.put("user_hash", user_hash1);
                    object1.put("star_c", star_c1);
                    object1.put("y_msg", y_msg1);
                    object1.put("v_cy", v_cy1);
                    object1.put("imgstr", imgstr1);
                    jsonArray.put(object1);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (match2()) {
                    Map<String, String> params = new HashMap<>();
                    params.put("sn", "773EDB6D2715FACF9C93354CAC5B1A3372872DC4D5AC085867C7490E9984D33E");
                    RequestUpFile2(params, listFiles2, "picture");
                }

            }
        });
    }

    //上传图片
    private void RequestUpFile2(Map<String, String> params, List<File> fileList, String fileKey) {
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
//                hideProgress();
//                myToast("上传图片成功");
                for (String s : response.getList()) {
                    imgstr2 += s + "||";
                }
                if (!imgstr2.equals("")) {
                    imgstr2 = imgstr2.substring(0, imgstr2.length() - 2);
                }
                try {
                    JSONObject object2 = new JSONObject();
                    object2.put("y_store_id", y_store_id2);
                    object2.put("y_order_id", y_order_id2);
                    object2.put("y_goods_id", y_goods_id2);
                    object2.put("user_hash", user_hash2);
                    object2.put("star_c", star_c2);
                    object2.put("y_msg", y_msg2);
                    object2.put("v_cy", v_cy2);
                    object2.put("imgstr", imgstr2);
                    jsonArray.put(object2);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Map<String, String> params = new HashMap<>();
                params.put("u_token", localUserInfo.getToken());
                params.put("jsonstr", jsonArray.toString());
                RequestUpData(params);
                /*Map<String, String> params = new HashMap<>();
                params.put("u_token", localUserInfo.getToken());
                params.put("y_store_id", y_store_id);
                params.put("y_order_id", y_order_id);
                params.put("y_goods_id", y_goods_id);
                params.put("user_hash", user_hash);
                params.put("star_c", star_c);
                params.put("y_msg", y_msg);
                params.put("v_cy", v_cy);
                params.put("imgstr", imgstr);
                RequestUpData(params);*/

            }
        });
    }

    //提交意见-图片用|连接
    private void RequestUpData(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.AddPingJia, params, headerMap, new CallBackUtil() {
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
                hideProgress();
                showToast("评价提交成功", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        finish();
                    }
                });

            }
        });
    }

    @Override
    protected void updateView() {
        titleView.setTitle("发布评价");
    }

    /**
     * *****************************************选择图片********************************************
     */
    private GridImageAdapter.onAddPicClickListener onAddPicClickListener1 = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            // 进入相册
            PictureSelector.create(AddPingLunActivity.this)
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
                    .maxSelectNum(maxSelectNum1)// 最大图片选择数量
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
                    .selectionData(mAdapter1.getData())// 是否传入已选图片
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
                    .forResult(new MyResultCallback1(mAdapter1));
        }
    };

    /**
     * 返回结果回调
     */
    private class MyResultCallback1 implements OnResultCallbackListener<LocalMedia> {
        private WeakReference<GridImageAdapter> mAdapterWeakReference;

        public MyResultCallback1(GridImageAdapter adapter) {
            super();
            this.mAdapterWeakReference = new WeakReference<>(adapter);
        }

        @Override
        public void onResult(List<LocalMedia> result) {
            listFiles1.clear();
            for (LocalMedia media : result) {
                MyLogger.i(">>>>>>压缩地址：" + media.getCompressPath());
                File file = new File(media.getCompressPath());
                listFiles1.add(file);
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

    /**
     * *****************************************选择图片********************************************
     */
    private GridImageAdapter.onAddPicClickListener onAddPicClickListener2 = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            // 进入相册
            PictureSelector.create(AddPingLunActivity.this)
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
                    .maxSelectNum(maxSelectNum2)// 最大图片选择数量
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
                    .selectionData(mAdapter2.getData())// 是否传入已选图片
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
                    .forResult(new MyResultCallback2(mAdapter2));
        }
    };

    /**
     * 返回结果回调
     */
    private class MyResultCallback2 implements OnResultCallbackListener<LocalMedia> {
        private WeakReference<GridImageAdapter> mAdapterWeakReference;

        public MyResultCallback2(GridImageAdapter adapter) {
            super();
            this.mAdapterWeakReference = new WeakReference<>(adapter);
        }

        @Override
        public void onResult(List<LocalMedia> result) {
            listFiles2.clear();
            for (LocalMedia media : result) {
                MyLogger.i(">>>>>>压缩地址：" + media.getCompressPath());
                File file = new File(media.getCompressPath());
                listFiles2.add(file);
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
