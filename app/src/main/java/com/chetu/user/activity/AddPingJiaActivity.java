package com.chetu.user.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chetu.user.R;
import com.chetu.user.base.BaseActivity;
import com.chetu.user.model.StoreDetailModel;
import com.chetu.user.net.URLs;
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
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by zyz on 2020/7/1.
 */
public class AddPingJiaActivity extends BaseActivity {
    StoreDetailModel storeDetailModel;
    List<StoreDetailModel.StoreTechListBean> list = new ArrayList<>();
    String y_store_id = "";

    LinearLayout ll_add;
   /* RecyclerView recyclerView_list;
    CommonAdapter<StoreDetailModel.StoreTechListBean> mAdapter_list;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpingjia);
    }

    @Override
    protected void initView() {
       /* recyclerView_list = findViewByID_My(R.id.recyclerView_list);
        recyclerView_list.setLayoutManager(new LinearLayoutManager(this));*/
        ll_add = findViewByID_My(R.id.ll_add);
    }

    @Override
    protected void initData() {
        storeDetailModel = (StoreDetailModel) getIntent().getSerializableExtra("storeDetailModel");
        list = storeDetailModel.getStore_tech_list();
        list.add(0, new StoreDetailModel.StoreTechListBean());

        /**
         * 选择图片
         * */
        RecyclerView[] mRecyclerView = new RecyclerView[list.size()];
        GridImageAdapter[] mAdapter = new GridImageAdapter[list.size()];
        int maxSelectNum = 6;//选择最多图片数量
        int spanCount = 3;//一行显示张数
        /**
         * 列表-ADD方式
         */
        ll_add.removeAllViews();
        for (int i = 0; i < list.size(); i++) {
            //添加数据
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            LayoutInflater inflater = LayoutInflater.from(AddPingJiaActivity.this);
            View view = inflater.inflate(R.layout.item_addpingjia, null, false);
            view.setLayoutParams(lp);

            //实例化子页面的控件
            TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
            TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
            RatingBar ratingbar = view.findViewById(R.id.ratingbar);
            ImageView imageView = view.findViewById(R.id.imageView);
            EditText editText = view.findViewById(R.id.editText);

            if (list.get(i).getId() != null) {
                tv_title.setText("技师评价");
                imageView.setVisibility(View.VISIBLE);
                tv_name.setText(list.get(i).getUserName());
                Glide.with(AddPingJiaActivity.this).load(URLs.IMGHOST + list.get(i).getHeadPortrait())
                        .centerCrop()
                        .placeholder(R.mipmap.loading)//加载站位图
                        .error(R.mipmap.zanwutupian)//加载失败
                        .into(imageView);
            } else {
                tv_title.setText("店铺评价");
                imageView.setVisibility(View.GONE);
                tv_name.setText("评分：");
            }


            mRecyclerView[i] = view.findViewById(R.id.rv_addimg);
            FullyGridLayoutManager manager = new FullyGridLayoutManager(this,
                    spanCount, GridLayoutManager.VERTICAL, false);
            mRecyclerView[i].setLayoutManager(manager);
            mRecyclerView[i].addItemDecoration(new GridSpacingItemDecoration(spanCount,
                    ScreenUtils.dip2px(this, 5), false));//中间值是设置间距
            int finalI = i;
            mAdapter[i] = new GridImageAdapter(AddPingJiaActivity.this, new GridImageAdapter.onAddPicClickListener() {
                @Override
                public void onAddPicClick() {
                    PictureSelector.create(AddPingJiaActivity.this)
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
                            .selectionData(mAdapter[finalI].getData())// 是否传入已选图片
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
                            .forResult(new MyResultCallback(mAdapter[finalI]));
                }
            });

            mAdapter[i].setSelectMax(maxSelectNum);
            mRecyclerView[i].setAdapter(mAdapter[i]);


            view.findViewById(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyLogger.i(">>>>>>" + ratingbar.getRating());
                    MyLogger.i(">>>>>>" + editText.getText().toString().trim());

                    ArrayList<File> listFiles = new ArrayList<>();
                    for (LocalMedia media : mAdapter[finalI].getData()) {
                        File file = new File(media.getCompressPath());
                        listFiles.add(file);
                    }
                    MyLogger.i(">>>>>>" +listFiles.size());

                }
            });


            ll_add.addView(view);
        }


        /**
         * 列表方式
         */
        /*mAdapter_list = new CommonAdapter<StoreDetailModel.StoreTechListBean>(
                AddPingJiaActivity.this, R.layout.item_addpingjia, list) {
            @Override
            protected void convert(ViewHolder holder, StoreDetailModel.StoreTechListBean model, int i) {
                ImageView imageView = holder.getView(R.id.imageView);
                if (model.getId() != null) {
                    holder.setText(R.id.tv_title, "技师评价");
                    imageView.setVisibility(View.VISIBLE);
                    holder.setText(R.id.tv_name, model.getUserName());
                    Glide.with(AddPingJiaActivity.this).load(URLs.IMGHOST + model.getHeadPortrait())
                            .centerCrop()
                            .placeholder(R.mipmap.loading)//加载站位图
                            .error(R.mipmap.zanwutupian)//加载失败
                            .into(imageView);

                } else {
                    holder.setText(R.id.tv_title, "店铺评价");
                    imageView.setVisibility(View.GONE);
                    holder.setText(R.id.tv_name, "评分：");
                }
                RatingBar ratingbar = holder.getView(R.id.ratingbar);

                EditText editText = holder.getView(R.id.editText);


                //选择图片
                mRecyclerView[i] = holder.getView(R.id.rv_addimg);
                FullyGridLayoutManager manager = new FullyGridLayoutManager(AddPingJiaActivity.this,
                        spanCount, GridLayoutManager.VERTICAL, false);
                mRecyclerView[i].setLayoutManager(manager);
                mRecyclerView[i].addItemDecoration(new GridSpacingItemDecoration(spanCount,
                        ScreenUtils.dip2px(AddPingJiaActivity.this, 5), false));//中间值是设置间距
                mAdapter[i] = new GridImageAdapter(AddPingJiaActivity.this, new GridImageAdapter.onAddPicClickListener() {
                    @Override
                    public void onAddPicClick() {
                        PictureSelector.create(AddPingJiaActivity.this)
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
                                .selectionData(mAdapter[i].getData())// 是否传入已选图片
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
                                .forResult(new MyResultCallback(mAdapter[i]));
                    }
                });
                mAdapter[i].setSelectMax(maxSelectNum);
                mRecyclerView[i].setAdapter(mAdapter[i]);


                holder.getView(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MyLogger.i(">>>>>>" + ratingbar.getRating());
                        MyLogger.i(">>>>>>" + editText.getText().toString().trim());

                        ArrayList<File> listFiles = new ArrayList<>();
                        for (LocalMedia media : mAdapter[i].getData()) {
                            File file = new File(media.getCompressPath());
                            listFiles.add(file);
                        }
                        MyLogger.i(">>>>>>" + listFiles.size());
                    }
                });


            }
        };
        recyclerView_list.setAdapter(mAdapter_list);*/

    }

    @Override
    protected void updateView() {
        titleView.setTitle("发布评价");
    }

    /**
     * *****************************************选择图片********************************************
     */
    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            // 进入相册

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
            /*listFiles[].clear();
            for (LocalMedia media : result) {
                MyLogger.i(">>>>>>压缩地址：" + media.getCompressPath());
                File file = new File(media.getCompressPath());
                listFiles.add(file);
                // TODO 可以通过PictureSelectorExternalUtils.getExifInterface();方法获取一些额外的资源信息，如旋转角度、经纬度等信息
            }*/
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
