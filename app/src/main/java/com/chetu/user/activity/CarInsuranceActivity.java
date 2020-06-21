package com.chetu.user.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chetu.user.R;
import com.chetu.user.base.BaseActivity;
import com.chetu.user.model.BaoXianModel;
import com.chetu.user.model.UpFileModel;
import com.chetu.user.net.URLs;
import com.chetu.user.okhttp.CallBackUtil;
import com.chetu.user.okhttp.OkhttpUtil;
import com.chetu.user.utils.MyChooseImages;
import com.chetu.user.utils.MyLogger;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import id.zelory.compressor.Compressor;
import okhttp3.Call;
import okhttp3.Response;

import static com.chetu.user.utils.MyChooseImages.REQUEST_CODE_CAPTURE_CAMEIA;
import static com.chetu.user.utils.MyChooseImages.REQUEST_CODE_PICK_IMAGE;

/**
 * Created by zyz on 2020/6/7.
 * 车险
 */
public class CarInsuranceActivity extends BaseActivity {
    //tab
    LinearLayout ll_tab1, ll_tab2;
    View view1, view2;
    //车险询价
    EditText editText1, editText2, editText3, editText4, editText5, editText6, editText7;
    ImageView imageView1, imageView2, imageView3, imageView4,
            iv_del1, iv_del2, iv_del3, iv_del4;
    String imgType = "";
    String license_plate = "", frame_no = "", full_name = "", telephone = "", t_number = "", i_company = "",
            v_insure_city = "", license_img1 = "", license_img2 = "", number_img1 = "", number_img2 = "";
    ArrayList<File> listFiles = new ArrayList<>();
    //车险报案
    RecyclerView recyclerView;
    List<BaoXianModel.ListBean> list = new ArrayList<>();
    CommonAdapter<BaoXianModel.ListBean> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carinsurance);
        mImmersionBar.reset()
                .statusBarColor(R.color.background)
                .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
    }

    @Override
    protected void initView() {
        ll_tab1 = findViewByID_My(R.id.ll_tab1);
        ll_tab2 = findViewByID_My(R.id.ll_tab2);
        view1 = findViewByID_My(R.id.view1);
        view2 = findViewByID_My(R.id.view2);

        //车险询价
        editText1 = findViewByID_My(R.id.editText1);
        editText1.setText(localUserInfo.getCarnum());

        editText2 = findViewByID_My(R.id.editText2);
        editText3 = findViewByID_My(R.id.editText3);
        editText3.setText(localUserInfo.getNickname());

        editText4 = findViewByID_My(R.id.editText4);
        editText4.setText(localUserInfo.getPhonenumber());

        editText5 = findViewByID_My(R.id.editText5);
        editText6 = findViewByID_My(R.id.editText6);
        editText7 = findViewByID_My(R.id.editText7);
        editText7.setText(localUserInfo.getCityname());

        imageView1 = findViewByID_My(R.id.imageView1);
        imageView2 = findViewByID_My(R.id.imageView2);
        imageView3 = findViewByID_My(R.id.imageView3);
        imageView4 = findViewByID_My(R.id.imageView4);
        iv_del1 = findViewByID_My(R.id.iv_del1);
        iv_del2 = findViewByID_My(R.id.iv_del2);
        iv_del3 = findViewByID_My(R.id.iv_del3);
        iv_del4 = findViewByID_My(R.id.iv_del4);

        //车险报案
        recyclerView = findViewByID_My(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CommonAdapter<BaoXianModel.ListBean>
                (CarInsuranceActivity.this, R.layout.item_carinsurance_baoxian, list) {
            @Override
            protected void convert(ViewHolder holder, BaoXianModel.ListBean model, int position) {
                holder.setText(R.id.textView, model.getVNameNature());
                holder.setText(R.id.textView1, model.getVName());
                holder.setText(R.id.textView2, model.getTelephone());
                TextView tv_call1 = holder.getView(R.id.tv_call1);
                tv_call1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showToast("确认拨打 " + model.getTelephone() + " 吗？", "确认", "取消",
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                        //创建打电话的意图
                                        Intent intent = new Intent();
                                        //设置拨打电话的动作
                                        intent.setAction(Intent.ACTION_CALL);//直接拨出电话
//                                                                        intent.setAction(Intent.ACTION_DIAL);//只调用拨号界面，不拨出电话
                                        //设置拨打电话的号码
                                        intent.setData(Uri.parse("tel:" + model.getTelephone()));
                                        //开启打电话的意图
                                        startActivity(intent);
                                    }
                                }, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                    }
                                });
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        HashMap<String, String> params2 = new HashMap<>();
        params2.put("i_cy", "2");//1为普通保险 2为交强险
        RequestBaoXian(params2, 2);

        HashMap<String, String> params1 = new HashMap<>();
        params1.put("i_cy", "1");//1为普通保险 2为交强险
        RequestBaoXian(params1, 1);
    }

    /**
     * 获取保险列表
     *
     * @param params
     */
    private void RequestBaoXian(Map<String, String> params, int type) {
        OkhttpUtil.okHttpPost(URLs.BaoXian, params, headerMap, new CallBackUtil<BaoXianModel>() {
            @Override
            public BaoXianModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(BaoXianModel response) {
                hideProgress();
                for (BaoXianModel.ListBean bean : response.getList()) {
                    list.add(bean);
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.linearLayout1:
                //车险询价
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.INVISIBLE);
                ll_tab1.setVisibility(View.VISIBLE);
                ll_tab2.setVisibility(View.GONE);
                break;
            case R.id.linearLayout2:
                //车险询价
                view1.setVisibility(View.INVISIBLE);
                view2.setVisibility(View.VISIBLE);
                ll_tab1.setVisibility(View.GONE);
                ll_tab2.setVisibility(View.VISIBLE);
                break;

            case R.id.imageView1:
                //主页
                imgType = "license_img1";
                MyChooseImages.showPhotoDialog(this);
                break;
            case R.id.imageView2:
                //副页
                imgType = "license_img2";
                MyChooseImages.showPhotoDialog(this);
                break;
            case R.id.imageView3:
                //正面
                imgType = "number_img1";
                MyChooseImages.showPhotoDialog(this);
                break;
            case R.id.imageView4:
                //反面
                imgType = "number_img2";
                MyChooseImages.showPhotoDialog(this);
                break;
            case R.id.iv_del1:
                iv_del1.setVisibility(View.GONE);
                license_img1 = "";
                imageView1.setImageResource(R.mipmap.ic_add_image1);
                break;
            case R.id.iv_del2:
                iv_del2.setVisibility(View.GONE);
                license_img2 = "";
                imageView2.setImageResource(R.mipmap.ic_add_image1);
                break;
            case R.id.iv_del3:
                iv_del3.setVisibility(View.GONE);
                number_img1 = "";
                imageView3.setImageResource(R.mipmap.ic_add_image1);
                break;
            case R.id.iv_del4:
                iv_del4.setVisibility(View.GONE);
                number_img2 = "";
                imageView4.setImageResource(R.mipmap.ic_add_image1);
                break;
            case R.id.tv_upload:
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
        t_number = editText5.getText().toString().trim();
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
        }
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
        titleView.setTitle("车险");
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
                /*BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 2;
                Bitmap bitmap = BitmapFactory.decodeFile(imagePath, options);
                imageView1.setImageBitmap(bitmap);
                imageView1.setScaleType(ImageView.ScaleType.CENTER_CROP);*/

                File file1 = new File(imagePath);
                listFiles = new ArrayList<>();
                File newFile = null;
                try {
                    newFile = new Compressor(this).compressToFile(file1);
                    listFiles.add(newFile);
//                    MyLogger.i(">>>>>选择图片结果>>>>>>>>>" + listFileNames.toString() + ">>>>>>" + listFiles.toString());
                    showProgress(true, "正在上传图片，请稍候...");
//                    Map<String, File> fileMap = new HashMap<>();
//                    fileMap.put("picture", newFile);
                    Map<String, String> params = new HashMap<>();
                    params.put("sn", "773EDB6D2715FACF9C93354CAC5B1A3372872DC4D5AC085867C7490E9984D33E");
//                    RequestUpFile(fileMap, params);
                    RequestUpFile(params, listFiles, "picture");//key不需要变

                } catch (IOException e) {
                    e.printStackTrace();
                    myToast(getString(R.string.app_imgerr));
                }
            }
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
                            Glide.with(CarInsuranceActivity.this).load(URLs.IMGHOST + s)
                                    .centerCrop()
                                    .into(imageView1);//加载图片
                            break;
                        case "license_img2":
                            license_img2 = s;
                            iv_del2.setVisibility(View.VISIBLE);
                            Glide.with(CarInsuranceActivity.this).load(URLs.IMGHOST + s)
                                    .centerCrop()
                                    .into(imageView2);//加载图片
                            break;
                        case "number_img1":
                            number_img1 = s;
                            iv_del3.setVisibility(View.VISIBLE);
                            Glide.with(CarInsuranceActivity.this).load(URLs.IMGHOST + s)
                                    .centerCrop()
                                    .into(imageView3);//加载图片
                            break;
                        case "number_img2":
                            number_img2 = s;
                            iv_del4.setVisibility(View.VISIBLE);
                            Glide.with(CarInsuranceActivity.this).load(URLs.IMGHOST + s)
                                    .centerCrop()
                                    .into(imageView4);//加载图片
                            break;
                    }
                }
            }
        });
    }
}
