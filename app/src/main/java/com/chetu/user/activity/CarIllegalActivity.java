package com.chetu.user.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chetu.user.R;
import com.chetu.user.base.BaseActivity;
import com.chetu.user.model.CarIllegalModel;
import com.chetu.user.model.CheXingModel;
import com.chetu.user.net.URLs;
import com.chetu.user.okhttp.CallBackUtil;
import com.chetu.user.okhttp.OkhttpUtil;
import com.chetu.user.utils.CommonUtil;
import com.cy.dialog.BaseDialog;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by zyz on 2020/6/7.
 * 违章查询
 */
public class CarIllegalActivity extends BaseActivity {
    EditText editText1, editText2, editText3, editText4;
    ImageView imageView1, iv_gouxuan;
    TextView tv_carname, tv_chexing;
    String y_user_sedan_id = "", license_plate = "", frame_no = "", engine_no = "", address = "", cartype = "";
    boolean isGouXuan = false;

    List<CheXingModel.ListBean> list = new ArrayList<>();
    int i1 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carillegal);
        mImmersionBar.reset()
                .statusBarColor(R.color.background)
                .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
    }

    @Override
    protected void initView() {
        editText1 = findViewByID_My(R.id.editText1);
        editText2 = findViewByID_My(R.id.editText2);
        editText3 = findViewByID_My(R.id.editText3);
        editText4 = findViewByID_My(R.id.editText4);
        tv_chexing = findViewByID_My(R.id.tv_chexing);

        imageView1 = findViewByID_My(R.id.imageView1);
        iv_gouxuan = findViewByID_My(R.id.iv_gouxuan);

        tv_carname = findViewByID_My(R.id.tv_carname);
        if (!localUserInfo.getCarname().equals("")) {
            y_user_sedan_id = localUserInfo.getCarid();
            tv_carname.setText(localUserInfo.getCarname());
            editText1.setText(localUserInfo.getCarnum());
            Glide.with(this).load(URLs.IMGHOST + localUserInfo.getCarlogo())
                    .centerCrop()
                    .into(imageView1);//加载图片
        }
    }

    @Override
    protected void initData() {
        RequestCheXing(params);
    }

    /**
     * 获取车型列表
     *
     * @param params
     */
    private void RequestCheXing(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.CheXing, params, headerMap, new CallBackUtil<CheXingModel>() {
            @Override
            public CheXingModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(CheXingModel response) {
                hideProgress();
                list = response.getList();

            }
        });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.linearLayout1:
                //选择车辆
                Intent intent1 = new Intent(CarIllegalActivity.this, MyGarageActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putInt("type", 10001);
                intent1.putExtras(bundle1);
                startActivityForResult(intent1, 10001, bundle1);
                break;
            case R.id.tv_chexing:
                BaseDialog dialog1 = new BaseDialog(CarIllegalActivity.this);
                dialog1.contentView(R.layout.dialog_list)
                        .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                CommonUtil.dip2px(CarIllegalActivity.this,500)))
                        .animType(BaseDialog.AnimInType.BOTTOM)
                        .canceledOnTouchOutside(true)
                        .gravity(Gravity.BOTTOM)
                        .dimAmount(0.7f)
                        .show();
                TextView title = dialog1.findViewById(R.id.textView1);
                title.setText("请选择车型");
                RecyclerView rv = dialog1.findViewById(R.id.recyclerView);
                rv.setLayoutManager(new LinearLayoutManager(CarIllegalActivity.this));

                CommonAdapter<CheXingModel.ListBean> adapter = new CommonAdapter<CheXingModel.ListBean>
                        (CarIllegalActivity.this, R.layout.item_dialog_list, list) {
                    @Override
                    protected void convert(ViewHolder holder, CheXingModel.ListBean model, int position) {
                        TextView tv = holder.getView(R.id.textView);
                        ImageView iv = holder.getView(R.id.imageView);
                        tv.setText(model.getVName());
                        if (position == i1) {
                            tv.setTextColor(getResources().getColor(R.color.blue));
                            iv.setImageResource(R.mipmap.ic_xuanzhong);
                        } else {
                            tv.setTextColor(getResources().getColor(R.color.black1));
                            iv.setImageResource(R.mipmap.ic_weixuan);
                        }
                    }
                };
                adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        i1 = i;
                        adapter.notifyDataSetChanged();
//                        dialog1.dismiss();
                    }

                    @Override
                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        return false;
                    }
                });
                rv.setAdapter(adapter);
                dialog1.findViewById(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cartype = list.get(i1).getVCode() + "";
                        tv_chexing.setText(list.get(i1).getVName());
                        dialog1.dismiss();
                    }
                });

                break;
            case R.id.iv_gouxuan:
                //勾选图片
                isGouXuan = !isGouXuan;
                if (isGouXuan) {
                    iv_gouxuan.setImageResource(R.mipmap.ic_xuanzhong);
                } else {
                    iv_gouxuan.setImageResource(R.mipmap.ic_weixuan);
                }
                break;

            case R.id.tv_tiaoli:
                //《违章查缴服务》
                Bundle bundle = new Bundle();
                bundle.putString("url", URLs.HOST + "/single/h5/violation?user_hash=" + localUserInfo.getUserId());
                CommonUtil.gotoActivityWithData(CarIllegalActivity.this, WebContentActivity.class, bundle, false);
                break;
            case R.id.tv_upload:
                //提交
                if (match()) {
                    showProgress(true, getString(R.string.app_loading1));
                    Map<String, String> params = new HashMap<>();
                    params.put("license_plate", license_plate);
                    params.put("frame_no", frame_no);
                    params.put("y_user_sedan_id", y_user_sedan_id);
                    params.put("engine_no", engine_no);
                    params.put("address", address);
                    params.put("u_token", localUserInfo.getToken());
                    params.put("cartype", cartype);
                    RequestUpData(params);
                }
                break;
        }
    }

    private boolean match() {
        if (TextUtils.isEmpty(y_user_sedan_id)) {
            myToast("请选择车辆");
            return false;
        }
        license_plate = editText1.getText().toString().trim();
        if (TextUtils.isEmpty(license_plate)) {
            myToast("请输入车牌号");
            return false;
        }
        frame_no = editText2.getText().toString().trim();
        if (TextUtils.isEmpty(frame_no)) {
            myToast("请输入车架号");
            return false;
        }
        engine_no = editText3.getText().toString().trim();
        if (TextUtils.isEmpty(engine_no)) {
            myToast("请输入车辆发动机号");
            return false;
        }
        address = editText4.getText().toString().trim();
        /*if (TextUtils.isEmpty(address)) {
            myToast("请选择查询地点");
            return false;
        }*/
        if (TextUtils.isEmpty(cartype)) {
            myToast("请选择车型");
            return false;
        }
        if (!isGouXuan) {
            myToast("请阅读并同意《违章查缴服务》");
            return false;
        }
        return true;
    }

    private void RequestUpData(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.CarIllegal, params, headerMap, new CallBackUtil<CarIllegalModel>() {
            @Override
            public CarIllegalModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(CarIllegalModel response) {
                hideProgress();
                showToast("提交成功，请耐心等待查询结果", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        /*Bundle bundle = new Bundle();
                        bundle.putString("url", URLs.HOST + "/single/h5/violation_closing?y_vio_regulat_id="+localUserInfo.getUserId());
                        CommonUtil.gotoActivityWithData(CarIllegalActivity.this, WebContentActivity.class, bundle, false);*/
                    }
                });
//                finish();

            }
        });
    }

    @Override
    protected void updateView() {
        titleView.setTitle("违章查询");
        titleView.setBackground(R.color.background);
        titleView.showRightTextview("查询历史", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtil.gotoActivity(CarIllegalActivity.this, CarIllegalListActivity.class);
            }
        });
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
                    tv_carname.setText(bundle1.getString("carname") + "\n" + bundle1.getString("cardetail"));
                    editText1.setText(bundle1.getString("carnum"));
                    Glide.with(CarIllegalActivity.this).load(URLs.IMGHOST + bundle1.getString("carlogo"))
                            .centerCrop()
                            .into(imageView1);//加载图片
                }
                break;
        }

    }
}
