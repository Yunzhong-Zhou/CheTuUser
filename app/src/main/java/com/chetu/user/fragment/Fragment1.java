package com.chetu.user.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.chetu.user.R;
import com.chetu.user.activity.CarIllegalActivity;
import com.chetu.user.activity.CarInsuranceActivity;
import com.chetu.user.activity.CarServiceActivity;
import com.chetu.user.activity.MainActivity;
import com.chetu.user.activity.MyGarageActivity;
import com.chetu.user.activity.ProductListActivity;
import com.chetu.user.activity.SearchActivity;
import com.chetu.user.activity.StoreDetailActivity;
import com.chetu.user.activity.WebContentActivity;
import com.chetu.user.adapter.CircleImageAdapter;
import com.chetu.user.base.BaseFragment;
import com.chetu.user.model.Fragment1ServiceListModel;
import com.chetu.user.model.Fragment1TabModel;
import com.chetu.user.model.Fragment3Model;
import com.chetu.user.net.URLs;
import com.chetu.user.okhttp.CallBackUtil;
import com.chetu.user.okhttp.OkhttpUtil;
import com.chetu.user.popupwindow.PhotoShowDialog;
import com.chetu.user.utils.CommonUtil;
import com.chetu.user.utils.MyLogger;
import com.cretin.tools.scancode.CaptureActivity;
import com.cretin.tools.scancode.config.ScanConfig;
import com.liaoinstan.springview.widget.SpringView;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Response;

import static android.app.Activity.RESULT_OK;
import static com.chetu.user.activity.MainActivity.mBottomTabBar;


/**
 * Created by fafukeji01 on 2016/1/6.
 * 首页
 */

public class Fragment1 extends BaseFragment {
    RelativeLayout rl_search;
    EditText et_search;

    String longitude = "", latitude = "";

    Banner banner;
    List<String> images = new ArrayList<>();
    int page1 = 0, page2 = 0;

    ImageView tv_scan;
    RelativeLayout rl_xiaoxi, rl_add, rl_addcar;
    TextView tv_xiaoxinum, tv_more1, tv_more2;

    RecyclerView recyclerView1;
    List<Fragment3Model.ListBean> list1 = new ArrayList<>();
    CommonAdapter<Fragment3Model.ListBean> mAdapter1;
    RecyclerView recyclerView2;
    List<Fragment3Model.ListBean> list2 = new ArrayList<>();
    CommonAdapter<Fragment3Model.ListBean> mAdapter2;

    RecyclerView rv_tab;
    List<Fragment1TabModel> list_tab = new ArrayList<>();
    CommonAdapter<Fragment1TabModel> mAdapter_tab;

    //车辆信息
    LinearLayout ll_car;
    TextView tv_carname, tv_carnum;
    ImageView iv_carlogo;

    //定位
    //声明AMapLocationClient类对象
    private AMapLocationClient mLocationClient = null;
    //城市选择
    TextView tv_addr;

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
            if (!localUserInfo.getCarname().equals("")) {
                rl_addcar.setVisibility(View.GONE);
                ll_car.setVisibility(View.VISIBLE);
//                y_user_sedan_id = localUserInfo.getCarid();
                tv_carname.setText(localUserInfo.getCarname());
                tv_carnum.setText(localUserInfo.getCarnum());
                Glide.with(getActivity()).load(URLs.IMGHOST + localUserInfo.getCarlogo())
                        .centerCrop()
                        .into(iv_carlogo);//加载图片
            } else {
                rl_addcar.setVisibility(View.VISIBLE);
                ll_car.setVisibility(View.GONE);
            }
            /*requestServer();
            tv_addr.setText(localUserInfo.getCityname());*/
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (MainActivity.item == 0) {
            if (!localUserInfo.getCarname().equals("")) {
                tv_carname.setText(localUserInfo.getCarname());
                tv_carnum.setText(localUserInfo.getCarnum());
                Glide.with(getActivity()).load(URLs.IMGHOST + localUserInfo.getCarlogo())
                        .centerCrop()
                        .into(iv_carlogo);//加载图片
            }
//            requestServer();
        }
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
                //获取附近活动列表数据
                page1 = 0;
                Map<String, String> params1 = new HashMap<>();
                params1.put("service_name", "");
                params1.put("page", page1 + "");
                params1.put("longitude", longitude);
                params1.put("latitude", latitude);
                params1.put("is_review", "1");
                params1.put("is_index", "1");
                RequestList1(params1);

                //获取口碑商家列表
                page2 = 0;
                Map<String, String> params = new HashMap<>();
                params.put("service_name", "");
                params.put("page", page2 + "");
                params.put("longitude", "");
                params.put("latitude", "");
                params.put("is_review", "1");
                params.put("is_index", "1");
                RequestList2(params);
            }

            @Override
            public void onLoadmore() {
            }
        });
        tv_scan = findViewByID_My(R.id.tv_scan);
        tv_scan.setOnClickListener(this);
        tv_addr = findViewByID_My(R.id.tv_addr);
        tv_addr.setOnClickListener(this);
        rl_xiaoxi = findViewByID_My(R.id.rl_xiaoxi);
        rl_xiaoxi.setOnClickListener(this);
        tv_xiaoxinum = findViewByID_My(R.id.tv_xiaoxinum);
        rl_search = findViewByID_My(R.id.rl_search);
        rl_search.setOnClickListener(this);
        et_search = findViewByID_My(R.id.et_search);
        et_search.setOnClickListener(this);

        rl_add = findViewByID_My(R.id.rl_add);
        rl_add.setOnClickListener(this);

        tv_more1 = findViewByID_My(R.id.tv_more1);
        tv_more1.setOnClickListener(this);
        tv_more2 = findViewByID_My(R.id.tv_more2);
        tv_more2.setOnClickListener(this);

        rv_tab = findViewByID_My(R.id.rv_tab);
        rv_tab.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerView1 = findViewByID_My(R.id.recyclerView1);
        recyclerView1.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView2 = findViewByID_My(R.id.recyclerView2);
        recyclerView2.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        banner = findViewByID_My(R.id.banner);

        rl_addcar = findViewByID_My(R.id.rl_addcar);
        rl_addcar.setOnClickListener(this);
        ll_car = findViewByID_My(R.id.ll_car);
        ll_car.setOnClickListener(this);
        tv_carname = findViewByID_My(R.id.tv_carname);
        tv_carnum = findViewByID_My(R.id.tv_carnum);
        iv_carlogo = findViewByID_My(R.id.iv_carlogo);
    }

    @Override
    protected void initData() {
        longitude = localUserInfo.getLongitude() + "";
        latitude = localUserInfo.getLatitude() + "";
        tv_addr.setText(localUserInfo.getCityname() + "");

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

                        MyLogger.i("定位信息", "\n纬度：" + aMapLocation.getLatitude()
                                + "\n经度:" + aMapLocation.getLongitude()
                                + "\n地址:" + aMapLocation.getAddress());
//                        register_addr = aMapLocation.getAddress();

                        longitude = aMapLocation.getLongitude() + "";
                        latitude = aMapLocation.getLatitude() + "";

                        localUserInfo.setCityname(aMapLocation.getCity());
                        localUserInfo.setLongitude(longitude);
                        localUserInfo.setLatitude(latitude);

                        tv_addr.setText(aMapLocation.getCity() + "");


                        page1 = 0;
                        Map<String, String> params1 = new HashMap<>();
                        params1.put("service_name", "");
                        params1.put("page", page1 + "");
                        params1.put("longitude", longitude);
                        params1.put("latitude", latitude);
                        params1.put("is_review", "1");
                        params1.put("is_index", "1");
                        RequestList1(params1);

                        //获取口碑商家列表
                        page2 = 0;
                        Map<String, String> params = new HashMap<>();
                        params.put("service_name", "");
                        params.put("page", page2 + "");
                        params.put("longitude", longitude);
                        params.put("latitude", longitude);
                        params.put("is_review", "1");
                        params.put("is_index", "1");
                        RequestList2(params);

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

//        if (localUserInfo.getCityname().equals("")) {
//        mLocationClient.startLocation();
//        }

        requestServer();
    }

    @Override
    public void requestServer() {
        super.requestServer();
//        this.showLoadingPage();
        showProgress(true, getString(R.string.app_loading));

        //获取服务项目和banner
        HashMap<String, String> params2 = new HashMap<>();
        params2.put("y_parent_id", "0");
        RequestService(params2, 0);

        //获取附近活动列表数据
        if (!localUserInfo.getCityname().equals("")) {
            page1 = 0;
            Map<String, String> params1 = new HashMap<>();
            params1.put("service_name", "");
            params1.put("page", page1 + "");
            params1.put("longitude", longitude);
            params1.put("latitude", latitude);
            params1.put("is_review", "1");
            params1.put("is_index", "1");
            RequestList1(params1);

            //获取口碑商家列表
            page2 = 0;
            Map<String, String> params = new HashMap<>();
            params.put("service_name", "");
            params.put("page", page2 + "");
            params.put("longitude", longitude);
            params.put("latitude", longitude);
            params.put("is_review", "1");
            params.put("is_index", "1");
            RequestList2(params);
        } else {
            mLocationClient.startLocation();
        }

    }

    /**
     * 获取附近活动列表数据
     *
     * @param params
     */
    private void RequestList1(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Fragment3, params, headerMap, new CallBackUtil<Fragment3Model>() {
            @Override
            public Fragment3Model onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
//                showEmptyPage();
//                myToast(err);
            }

            @Override
            public void onResponse(Fragment3Model response) {
                hideProgress();
                list1 = response.getList();
                mAdapter1 = new CommonAdapter<Fragment3Model.ListBean>
                        (getActivity(), R.layout.item_fragment1_gridview1, list1) {
                    @Override
                    protected void convert(ViewHolder holder, Fragment3Model.ListBean model, int position) {
                        ImageView imageView1 = holder.getView(R.id.imageView1);
                        Glide.with(getActivity())
                                .load(URLs.IMGHOST + model.getPicture())
                                .centerCrop()
                                .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                                .placeholder(R.mipmap.loading)//加载站位图
                                .error(R.mipmap.zanwutupian)//加载失败
                                .into(imageView1);//加载图片
                        holder.setText(R.id.textView1, model.getVLevel());//等级
                        holder.setText(R.id.textView2, model.getVName());//店名
                        holder.setText(R.id.textView3, model.getSlogan());//活动
                        holder.setText(R.id.textView4, model.getAddress());//地址
                    }
                };
                mAdapter1.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        Bundle bundle = new Bundle();
                        bundle.putString("id", list1.get(i).getYStoreId());
                        bundle.putString("longitude", longitude);
                        bundle.putString("latitude", latitude);
                        CommonUtil.gotoActivityWithData(getActivity(), StoreDetailActivity.class, bundle, false);
                    }

                    @Override
                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        return false;
                    }
                });
                recyclerView1.setAdapter(mAdapter1);

                /*if (list1.size() > 0) {
                    showContentPage();
                } else {
                    showEmptyPage();
                }*/
            }
        });
    }

    private void RequestListMore1(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Fragment3, params, headerMap, new CallBackUtil<Fragment3Model>() {
            @Override
            public Fragment3Model onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
//                myToast(err);
                myToast(getString(R.string.app_nomore));
                page1--;
            }

            @Override
            public void onResponse(Fragment3Model response) {
                hideProgress();
                List<Fragment3Model.ListBean> list_1 = new ArrayList<>();
                list_1 = response.getList();
                if (list_1.size() == 0) {
                    page1--;
                    myToast(getString(R.string.app_nomore));
                } else {
                    list1.addAll(list_1);
                    mAdapter1.notifyDataSetChanged();
                }
            }
        });
    }

    /**
     * 获取口碑商家列表数据
     *
     * @param params
     */
    private void RequestList2(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Fragment3, params, headerMap, new CallBackUtil<Fragment3Model>() {
            @Override
            public Fragment3Model onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
//                hideProgress();
//                showEmptyPage();
//                myToast(err);
            }

            @Override
            public void onResponse(Fragment3Model response) {
//                hideProgress();
                list2 = response.getList();
                mAdapter2 = new CommonAdapter<Fragment3Model.ListBean>
                        (getActivity(), R.layout.item_fragment1_gridview2, list2) {
                    @Override
                    protected void convert(ViewHolder holder, Fragment3Model.ListBean model, int position) {
                        ImageView imageView1 = holder.getView(R.id.imageView1);
                        Glide.with(getActivity())
                                .load(URLs.IMGHOST + model.getPicture())
                                .centerCrop()
                                .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                                .placeholder(R.mipmap.loading)//加载站位图
                                .error(R.mipmap.zanwutupian)//加载失败
                                .into(imageView1);//加载图片
                        holder.setText(R.id.textView1, model.getVLevel());//等级
                        holder.setText(R.id.textView2, model.getVName());//店名
                        holder.setText(R.id.textView3, model.getReview());//评分
                        holder.setText(R.id.textView4, model.getAddress());//地址
                    }
                };
                mAdapter2.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        Bundle bundle = new Bundle();
                        bundle.putString("id", list2.get(i).getYStoreId());
                        bundle.putString("longitude", longitude);
                        bundle.putString("latitude", latitude);
                        CommonUtil.gotoActivityWithData(getActivity(), StoreDetailActivity.class, bundle, false);
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

    private void RequestListMore2(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Fragment3, params, headerMap, new CallBackUtil<Fragment3Model>() {
            @Override
            public Fragment3Model onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
//                myToast(err);
                myToast(getString(R.string.app_nomore));
                page2--;
            }

            @Override
            public void onResponse(Fragment3Model response) {
                hideProgress();
                List<Fragment3Model.ListBean> list_1 = new ArrayList<>();
                list_1 = response.getList();
                if (list_1.size() == 0) {
                    page2--;
                    myToast(getString(R.string.app_nomore));
                } else {
                    list2.addAll(list_1);
                    mAdapter2.notifyDataSetChanged();
                }
            }
        });
    }

    /**
     * 获取服务和banner
     *
     * @param params
     * @param type
     */
    private void RequestService(HashMap<String, String> params, int type) {
        OkhttpUtil.okHttpPost(URLs.Fragment1_Service, params, headerMap, new CallBackUtil<Fragment1ServiceListModel>() {
            @Override
            public Fragment1ServiceListModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
//                myToast(err);
            }

            @Override
            public void onResponse(Fragment1ServiceListModel response) {
                //banner
                /*images.add("http://file02.16sucai.com/d/file/2014/0825/dcb017b51479798f6c60b7b9bd340728.jpg");
                images.add("http://file02.16sucai.com/d/file/2014/0825/dcb017b51479798f6c60b7b9bd340728.jpg");
                images.add("http://file02.16sucai.com/d/file/2014/0825/dcb017b51479798f6c60b7b9bd340728.jpg");
                images.add("http://file02.16sucai.com/d/file/2014/0825/dcb017b51479798f6c60b7b9bd340728.jpg");*/
                images.clear();
                for (int i = 0; i < response.getBanner_list().size(); i++) {
                    images.add(URLs.IMGHOST + response.getBanner_list().get(i).getImgurl());
                }
                banner.addBannerLifecycleObserver(getActivity())//添加生命周期观察者
                        .setDelayTime(3000)//设置轮播时间
                        .setBannerGalleryEffect(10, 10)//为banner添加画廊效果
                        .setAdapter(new CircleImageAdapter(images))
                        .setIndicator(new CircleIndicator(getActivity()))
                        .start();
                banner.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(Object data, int position) {
                /*Bundle bundle = new Bundle();
                bundle.putInt("type", response.getBanner().get(position).getType());
                CommonUtil.gotoActivityWithData(JiFenShangChengActivity.this, JiFenLieBiaoActivity.class, bundle, false);*/
                        PhotoShowDialog photoShowDialog = new PhotoShowDialog(getActivity(), images, position);
                        photoShowDialog.show();
                    }
                });

                //tab
                list_tab.clear();
                for (Fragment1ServiceListModel.IndexCustomListBean bean : response.getIndex_custom_list()) {
                    list_tab.add(new Fragment1TabModel(bean.getYServiceId(), bean.getCategory(), bean.getMsg(), bean.getImgurl()));
                }
                for (Fragment1ServiceListModel.IndexServiceListBean bean : response.getIndex_service_list()) {
                    list_tab.add(new Fragment1TabModel(bean.getYServiceId(), -1, bean.getVName(), bean.getVImg()));
                }
                mAdapter_tab = new CommonAdapter<Fragment1TabModel>
                        (getActivity(), R.layout.item_fragment1_tab, list_tab) {
                    @Override
                    protected void convert(ViewHolder holder, Fragment1TabModel model, int position) {
                        ImageView imageView = holder.getView(R.id.imageView);
                        Glide.with(getActivity())
                                .load(URLs.IMGHOST + model.getImg())
                                .centerCrop()
//                    .placeholder(R.mipmap.headimg)//加载站位图
//                    .error(R.mipmap.headimg)//加载失败
                                .into(imageView);//加载图片
                        holder.setText(R.id.tv_name, model.getTitle());
                    }
                };
                mAdapter_tab.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        Bundle bundle = new Bundle();
                        switch (list_tab.get(i).getCategory()) {
                            case 2:
                                //跳转商品列表
                                bundle.putString("id", list_tab.get(i).getId());
                                CommonUtil.gotoActivityWithData(getActivity(), ProductListActivity.class, bundle);
                                break;
                            case 3:
                                //跳转违章查询
                                CommonUtil.gotoActivity(getActivity(), CarIllegalActivity.class);
                                break;
                            case 4:
                                //跳转保险查询
                                CommonUtil.gotoActivity(getActivity(), CarInsuranceActivity.class);
                                break;
                            case 5:
                                //跳转保险查询
                                CommonUtil.gotoActivity(getActivity(), CarInsuranceActivity.class);
                                break;
                            default:
                                //跳转养车页面
                                MainActivity.item = 1;
                                Fragment2.yServiceId = list_tab.get(i).getId();
                                mBottomTabBar.setCurrentTab(MainActivity.item);
                               /* bundle.putString("keys", list_tab.get(i).getTitle());
                                CommonUtil.gotoActivityWithData(getActivity(), SearchActivity.class, bundle);*/

                                break;
                        }
                    }

                    @Override
                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        return false;
                    }
                });
                rv_tab.setAdapter(mAdapter_tab);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_addr:
                //选择地址
//                CommonUtil.gotoActivity(getActivity(), SelectAddressActivity.class);
                break;
            case R.id.rl_search:
            case R.id.et_search:
                //搜索
                CommonUtil.gotoActivity(getActivity(), SearchActivity.class);
                break;
            case R.id.tv_scan:
                //扫一扫
//                startQrCode();
                ScanConfig config = new ScanConfig()
                        .setShowFlashlight(true)//是否需要打开闪光灯
                        .setShowGalary(true)//是否需要打开相册
                        .setNeedRing(true);//是否需要提示音
                //ScanConfig 也可以不配置 默认都是打开
                CaptureActivity.launch(this, config);
                break;
            case R.id.rl_xiaoxi:
                //消息

                /*WebUtilsConfig config1 =
                        new WebUtilsConfig()
                                .setTitleBackgroundColor(R.color.colorPrimary)//设置标题栏背景色
//                                .setBackText("关闭")//设置返回按钮的文案
                                .setBackBtnRes(R.mipmap.ic_return_black)//设置返回按钮的图标
//                                .setMoreBtnRes(R.mipmap.more_web)//设置更多按钮的图标
                                .setShowBackText(false)//设置是否显示返回按钮的文案
                                .setShowMoreBtn(false)//设置是否显示更多按钮
                                .setShowTitleLine(false)//设置是否显示标题下面的分割线
                                .setShowTitleView(true)//设置是否显示标题栏，网页是全屏的时候可以选择隐藏标题栏
//                                .setTitleBackgroundRes(getResources().getColor(R.color.white))//设置标题栏背景资源
                                .setTitleBackgroundColor(R.color.white)
//                                .setBackTextColor(-1)//设置返回按钮的文案颜色
                                .setTitleTextColor(R.color.black)//设置标题文字颜色
                                .setStateBarTextColorDark(true)//设置状态栏文字颜色是否是暗色，如果你设置了标题栏背景颜色为白色，这里需要设置true，否则状态栏看不到文案了
                                .setTitleLineColor(R.color.app_title_color);//设置标题栏下面的分割线的颜色
                OpenWebActivity.openWebView(getActivity(), url, config1);*/

                /*String url = URLs.KFHOST + "/#/pages/chetu-kf/chetu-kf?token=" + localUserInfo.getToken() +
                        "&kf_userHash=" + localUserInfo.getKfuserhash() +
                        "&nickName=" + localUserInfo.getKfname() +
                        "&headerPic=" + localUserInfo.getKfhead();
                Bundle bundle = new Bundle();
                bundle.putString("url", url);
                CommonUtil.gotoActivityWithData(getActivity(), WebContentActivity.class, bundle, false);*/
                String url = URLs.KFHOST + "/#/pages/chetu-kf/chat_list?token=" + localUserInfo.getToken();
                Bundle bundle = new Bundle();
                bundle.putString("url", url);
                CommonUtil.gotoActivityWithData(getActivity(), WebContentActivity.class, bundle, false);
                break;

            case R.id.rl_add:
                //发布需求
                CommonUtil.gotoActivity(getActivity(), CarServiceActivity.class);
                break;
            case R.id.ll_car:
            case R.id.rl_addcar:
                //选择车辆
                Intent intent1 = new Intent(getActivity(), MyGarageActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putInt("type", 10001);
                intent1.putExtras(bundle1);
                startActivityForResult(intent1, 10001, bundle1);
                break;
            case R.id.tv_more1:
                //更多1
                showProgress(true, getString(R.string.app_loading4));
                page1++;
                Map<String, String> params1 = new HashMap<>();
                params1.put("service_name", "");
                params1.put("page", page1 + "");
                params1.put("longitude", longitude);
                params1.put("latitude", latitude);
                params1.put("is_review", "1");
                params1.put("is_index", "1");
                RequestListMore1(params1);
                break;
            case R.id.tv_more2:
                //更多2
                showProgress(true, getString(R.string.app_loading4));
                page2++;
                Map<String, String> params = new HashMap<>();
                params.put("service_name", "");
                params.put("page", page2 + "");
                params.put("longitude", "");
                params.put("latitude", "");
                params.put("is_review", "1");
                params.put("is_index", "1");
                RequestListMore2(params);
                break;
        }
    }

    // 开始扫码
   /* private void startQrCode() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // 申请权限
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, Constant.REQ_PERM_CAMERA);
            return;
        }
        // 二维码扫码
        Intent intent = new Intent(getActivity(), MyCaptureActivity.class);
        startActivityForResult(intent, Constant.REQ_QR_CODE);
    }*/

    @Override
    protected void updateView() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == CaptureActivity.REQUEST_CODE_SCAN) {
            // 扫描二维码回传
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    //获取扫描结果
                    Bundle bundle = data.getExtras();
                    String result = bundle.getString(CaptureActivity.EXTRA_SCAN_RESULT);
                    MyLogger.i("扫码返回", result);
                }
            }
        }
        switch (requestCode) {
            case 10001:
                //选择车辆
                if (data != null) {
                    rl_addcar.setVisibility(View.GONE);
                    ll_car.setVisibility(View.VISIBLE);

                    Bundle bundle1 = data.getExtras();
//                    y_user_sedan_id = bundle1.getString("car_id");
                    tv_carname.setText(bundle1.getString("carname") + "\n" + bundle1.getString("cardetail"));
                    tv_carnum.setText(bundle1.getString("carnum"));
                    Glide.with(getActivity()).load(URLs.IMGHOST + bundle1.getString("carlogo"))
                            .centerCrop()
                            .into(iv_carlogo);//加载图片
                }
                break;
        }
    }

}
