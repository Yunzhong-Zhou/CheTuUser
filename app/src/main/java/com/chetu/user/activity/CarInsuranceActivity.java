package com.chetu.user.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chetu.user.R;
import com.chetu.user.base.BaseActivity;
import com.chetu.user.model.BaoXianModel;
import com.chetu.user.net.URLs;
import com.chetu.user.okhttp.CallBackUtil;
import com.chetu.user.okhttp.OkhttpUtil;
import com.zhy.adapter.recyclerview.CommonAdapter;
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
        editText2 = findViewByID_My(R.id.editText2);
        editText3 = findViewByID_My(R.id.editText3);
        editText4 = findViewByID_My(R.id.editText4);
        editText5 = findViewByID_My(R.id.editText5);
        editText6 = findViewByID_My(R.id.editText6);
        editText7 = findViewByID_My(R.id.editText7);
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
                holder.setText(R.id.textView,model.getVNameNature());
                holder.setText(R.id.textView1,model.getVName());
                holder.setText(R.id.textView2,model.getTelephone());
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
                                        intent.setData(Uri.parse("tel:" +  model.getTelephone()));
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
                for (BaoXianModel.ListBean bean : response.getList()){
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
        }
    }

    @Override
    protected void updateView() {
        titleView.setTitle("车险");
        titleView.setBackground(R.color.background);
    }
}
