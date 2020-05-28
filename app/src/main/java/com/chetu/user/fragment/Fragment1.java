package com.chetu.user.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.chetu.user.R;
import com.chetu.user.activity.MainActivity;
import com.chetu.user.adapter.ImageAdapter;
import com.chetu.user.base.BaseFragment;
import com.chetu.user.model.Fragment1Model;
import com.chetu.user.net.URLs;
import com.chetu.user.okhttp.CallBackUtil;
import com.chetu.user.okhttp.OkhttpUtil;
import com.chetu.user.utils.CommonUtil;
import com.chetu.user.utils.MyLogger;
import com.chetu.user.view.zxing.CaptureActivity;
import com.chetu.user.view.zxing.Constant;
import com.liaoinstan.springview.widget.SpringView;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;
import com.zaaach.citypicker.CityPicker;
import com.zaaach.citypicker.adapter.OnPickListener;
import com.zaaach.citypicker.model.City;
import com.zaaach.citypicker.model.HotCity;
import com.zaaach.citypicker.model.LocateState;
import com.zaaach.citypicker.model.LocatedCity;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Response;


/**
 * Created by fafukeji01 on 2016/1/6.
 * 首页
 */

public class Fragment1 extends BaseFragment {
    String longitude = "", latitude = "", y_parent_id = "0", y_service_id = "0";

    Banner banner;
    List<String> images = new ArrayList<>();
    int page1 = 0, page2 = 0;

    ImageView tv_scan;

    RecyclerView recyclerView1;
    List<Fragment1Model.ListBean> list1 = new ArrayList<>();
    CommonAdapter<Fragment1Model.ListBean> mAdapter1;
    RecyclerView recyclerView2;
    List<Fragment1Model.ListBean> list2 = new ArrayList<>();
    CommonAdapter<Fragment1Model.ListBean> mAdapter2;

    RecyclerView rv_tab;
    List<Fragment1Model.ListBean> list_tab = new ArrayList<>();
    CommonAdapter<Fragment1Model.ListBean> mAdapter_tab;

    //定位
    //声明AMapLocationClient类对象
    private AMapLocationClient mLocationClient = null;
    //城市选择
    TextView tv_addr;
    List<HotCity> hotCities = new ArrayList<>();
    String province = "", city = "", cityCode = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (MainActivity.item == 0) {
            requestServer();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        /*if (MainActivity.item == 0) {
            requestServer();
        }*/
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mLocationClient != null)
            mLocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。
    }

    @Override
    protected void initView(View view) {
        findViewByID_My(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(getActivity()), 0, 0);

        //刷新
        setSpringViewMore(false);//不需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                page1 = 0;
                page2 = 0;
                Map<String, String> params = new HashMap<>();
                params.put("page", page1 + "");
                params.put("y_parent_id", y_parent_id);
                params.put("y_service_id", y_service_id);
                params.put("longitude", longitude);
                params.put("latitude", latitude);
                Request(params);
            }

            @Override
            public void onLoadmore() {
            }
        });
        tv_scan = findViewByID_My(R.id.tv_scan);
        tv_scan.setOnClickListener(this);
        tv_addr = findViewByID_My(R.id.tv_addr);
        tv_addr.setOnClickListener(this);

        rv_tab = findViewByID_My(R.id.rv_tab);
        rv_tab.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerView1 = findViewByID_My(R.id.recyclerView1);
        recyclerView1.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView2 = findViewByID_My(R.id.recyclerView2);
        recyclerView2.setLayoutManager(new GridLayoutManager(getActivity(), 2));


        banner = findViewByID_My(R.id.banner);
        //banner
        images.add("http://file02.16sucai.com/d/file/2014/0825/dcb017b51479798f6c60b7b9bd340728.jpg");
        images.add("http://file02.16sucai.com/d/file/2014/0825/dcb017b51479798f6c60b7b9bd340728.jpg");
        images.add("http://file02.16sucai.com/d/file/2014/0825/dcb017b51479798f6c60b7b9bd340728.jpg");
        images.add("http://file02.16sucai.com/d/file/2014/0825/dcb017b51479798f6c60b7b9bd340728.jpg");
        /*images.clear();
        for (int i = 0; i < response.getBanner().size(); i++) {
            images.add(OkHttpClientManager.IMGHOST+response.getBanner().get(i).getUrl());
        }*/
        banner.addBannerLifecycleObserver(this)//添加生命周期观察者
                .setDelayTime(3000)//设置轮播时间
                .setBannerGalleryEffect(10, 10)//为banner添加画廊效果
                .setAdapter(new ImageAdapter(images))
                .setIndicator(new CircleIndicator(getActivity()))
                .start();
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(Object data, int position) {
                /*Bundle bundle = new Bundle();
                bundle.putInt("type", response.getBanner().get(position).getType());
                CommonUtil.gotoActivityWithData(JiFenShangChengActivity.this, JiFenLieBiaoActivity.class, bundle, false);*/
            }
        });


    }

    @Override
    protected void initData() {
//        requestServer();
        //热门城市
        hotCities.add(new HotCity("北京", "北京", "101010100")); //code为城市代码
        hotCities.add(new HotCity("上海", "上海", "101020100"));
        hotCities.add(new HotCity("广州", "广东", "101280101"));
        hotCities.add(new HotCity("深圳", "广东", "101280601"));
        hotCities.add(new HotCity("杭州", "浙江", "101210101"));
        //初始化定位
        mLocationClient = new AMapLocationClient(getActivity());
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

                        MyLogger.i(">>>>>>>>>>定位信息：\n纬度：" + aMapLocation.getLatitude()
                                + "\n经度:" + aMapLocation.getLongitude()
                                + "\n地址:" + aMapLocation.getAddress());
//                        register_addr = aMapLocation.getAddress();
                        longitude = aMapLocation.getLongitude() + "";
                        latitude = aMapLocation.getLatitude() + "";

                        tv_addr.setText(aMapLocation.getCity() + "");

                        province = aMapLocation.getProvince();//省信息
                        city = aMapLocation.getCity();//城市信息
                        cityCode = aMapLocation.getCityCode();//城市编码

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
        mLocationClient.startLocation();
    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        page1 = 0;
        page2 = 0;
        Map<String, String> params = new HashMap<>();
//        params.put("u_token", localUserInfo.getToken());
        params.put("y_parent_id", y_parent_id);
        params.put("y_service_id", y_service_id);
        params.put("longitude", longitude);
        params.put("latitude", latitude);
        Request(params);
    }

    private void Request(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Fragment3, params, headerMap, new CallBackUtil<Fragment1Model>() {
            @Override
            public Fragment1Model onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                showEmptyPage();
                myToast(err);
            }

            @Override
            public void onResponse(Fragment1Model response) {
                hideProgress();
                list1 = response.getList();
                list2 = response.getList();
                list_tab = response.getList();
                mAdapter_tab = new CommonAdapter<Fragment1Model.ListBean>
                        (getActivity(), R.layout.item_fragment1_tab, list1) {
                    @Override
                    protected void convert(ViewHolder holder, Fragment1Model.ListBean model, int position) {

                    }
                };
                mAdapter_tab.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {

                    }

                    @Override
                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        return false;
                    }
                });
                rv_tab.setAdapter(mAdapter_tab);
                mAdapter1 = new CommonAdapter<Fragment1Model.ListBean>
                        (getActivity(), R.layout.item_fragment1_gridview1, list1) {
                    @Override
                    protected void convert(ViewHolder holder, Fragment1Model.ListBean model, int position) {

                    }
                };
                mAdapter1.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {

                    }

                    @Override
                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        return false;
                    }
                });
                recyclerView1.setAdapter(mAdapter1);
                mAdapter2 = new CommonAdapter<Fragment1Model.ListBean>
                        (getActivity(), R.layout.item_fragment1_gridview2, list2) {
                    @Override
                    protected void convert(ViewHolder holder, Fragment1Model.ListBean model, int position) {

                    }
                };
                mAdapter2.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {

                    }

                    @Override
                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        return false;
                    }
                });
                recyclerView2.setAdapter(mAdapter2);

                /*if (list1.size() > 0) {
                    showContentPage();
                } else {
                    showEmptyPage();
                }*/
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_addr:
                //选择地址
                CityPicker.from(getActivity()) //activity或者fragment
                        .enableAnimation(true)    //启用动画效果，默认无
//                        .setAnimationStyle(anim)	//自定义动画
//                        .setLocatedCity(new LocatedCity("杭州", "浙江", "101210101"))  //APP自身已定位的城市，传null会自动定位（默认）
                        .setHotCities(hotCities)    //指定热门城市
                        .setOnPickListener(new OnPickListener() {
                            @Override
                            public void onPick(int position, City data) {
                                //选择的城市
                                tv_addr.setText(data.getName() + "");
                                city = data.getName();

                            }

                            @Override
                            public void onCancel() {
                                //取消
                                /*Toast.makeText(getApplicationContext(), "取消选择", Toast.LENGTH_SHORT).show();*/
                            }

                            @Override
                            public void onLocate() {
                                //定位接口，需要APP自身实现，这里模拟一下定位
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        //定位完成之后更新数据到城市选择器
                                        CityPicker.from(getActivity()).locateComplete(new LocatedCity(province,
                                                city, cityCode), LocateState.SUCCESS);
                                    }
                                }, 3000);
                            }
                        })
                        .show();
                break;
            case R.id.tv_scan:
                //扫一扫
                startQrCode();
                break;
        }
    }

    // 开始扫码
    private void startQrCode() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // 申请权限
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, Constant.REQ_PERM_CAMERA);
            return;
        }
        // 二维码扫码
        Intent intent = new Intent(getActivity(), CaptureActivity.class);
        startActivityForResult(intent, Constant.REQ_QR_CODE);
    }

    @Override
    protected void updateView() {

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 处理二维码扫描结果
         */
        /*//扫描结果回调
        if (requestCode == Constant.REQ_QR_CODE) {
            if (data != null) {
                Bundle bundle = data.getExtras();
                String scanResult = bundle.getString(Constant.INTENT_EXTRA_KEY_QR_SCAN);
                MyLogger.i(">>>扫码返回>>>>" + scanResult);
                //点击转单-掉接口1-生成二维码
                //扫码-掉接口2-跳转到订单详情
                if (scanResult != null && !scanResult.equals("")) {
                    showToast("确认接受该单吗？", "确认", "取消",
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                    showProgress(true, "正在获取转单信息...");
                                    Map<String, String> params = new HashMap<>();
                                    params.put("token", localUserInfo.getToken());
                                    params.put("t_indent_confirm_id", scanResult);
                                    params.put("type", "7");//转单确认
                                    RequestZhuanDan(params);
                                }
                            }, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                }
                            });
                    *//*Bundle bundle1 = new Bundle();
                    bundle1.putString("id", scanResult);
                    CommonUtil.gotoActivityWithData(getActivity(), OrderDetailsActivity.class, bundle1, false);*//*
                }
            }
        }*/
    }

}
