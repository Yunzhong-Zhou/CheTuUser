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
import com.chetu.user.R;
import com.chetu.user.activity.DraftActivity;
import com.chetu.user.activity.MainActivity;
import com.chetu.user.activity.MyGarageActivity;
import com.chetu.user.activity.SearchActivity;
import com.chetu.user.activity.StoreDetailActivity;
import com.chetu.user.base.BaseFragment;
import com.chetu.user.model.Fragment3Model;
import com.chetu.user.model.ServiceListModel_All;
import com.chetu.user.net.URLs;
import com.chetu.user.okhttp.CallBackUtil;
import com.chetu.user.okhttp.OkhttpUtil;
import com.chetu.user.utils.CommonUtil;
import com.chetu.user.utils.MyLogger;
import com.cy.cyflowlayoutlibrary.FlowLayout;
import com.cy.cyflowlayoutlibrary.FlowLayoutAdapter;
import com.liaoinstan.springview.widget.SpringView;
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
 * Created by fafukeji01 on 2016/1/6.
 * 养车
 */
public class Fragment2_1 extends BaseFragment {
    public static String yServiceId = "";
    RelativeLayout rl_search;
    EditText et_search;

    int page = 0;
    String longitude = "", latitude = "", service_name = "";
    /**
     * 门店
     */
    private RecyclerView recyclerView;
    List<Fragment3Model.ListBean> list = new ArrayList<>();
    CommonAdapter<Fragment3Model.ListBean> mAdapter;

    TextView tv_caogao;

    //车辆信息
    LinearLayout ll_car;
    TextView tv_carname, tv_carnum;
    ImageView iv_carlogo;

    /**
     * 服务内容
     */
    RecyclerView recyclerView_sv;
    CommonAdapter<String> mAdapter_sv;
    List<String> list_name = new ArrayList<>();
    List<ServiceListModel_All.ListBean> list_sv = new ArrayList<>();
    int i1 = 0;

    /**
     * 服务tab
     */
    LinearLayout ll_tab;
    RecyclerView rv_tab1, rv_tab2;
    CommonAdapter<ServiceListModel_All.ListBean.VListBeanXX> ca_tab1;
    List<ServiceListModel_All.ListBean.VListBeanXX> list_tab1 = new ArrayList<>();
    CommonAdapter<ServiceListModel_All.ListBean.VListBeanXX.VListBeanX> ca_tab2;
    List<ServiceListModel_All.ListBean.VListBeanXX.VListBeanX> list_tab2 = new ArrayList<>();

    /**
     * 悬浮窗
     */
    String v_strs = "";
    LinearLayout ll_xuanfu;
    TextView tv_tabs, tv_yixuan, tv_savecaogao, tv_pipei;


    //定位
    //声明AMapLocationClient类对象
    private AMapLocationClient mLocationClient = null;
    //城市选择
    TextView tv_addr;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2_1, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        StatusBarUtil.setTransparent(getActivity());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mLocationClient != null)
            mLocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。
    }


    @Override
    public void onStart() {
        super.onStart();
        /*if (MainActivity.item == 1) {
            requestServer();
        }*/
    }

    @Override
    public void onResume() {
        super.onResume();
        if (MainActivity.item == 1) {
            if (!localUserInfo.getCarname().equals("")) {
//                y_user_sedan_id = localUserInfo.getCarid();
                tv_carname.setText(localUserInfo.getCarname());
                tv_carnum.setText(localUserInfo.getCarnum());
                Glide.with(getActivity()).load(URLs.IMGHOST + localUserInfo.getCarlogo())
                        .centerCrop()
                        .into(iv_carlogo);//加载图片
            }
            MyLogger.i(">>>>>>>选择的服务id" + yServiceId);
            if (list_sv.size() > 0) {
                for (int i = 0; i < list_sv.size(); i++) {
                    if (yServiceId.equals(list_sv.get(i).getYServiceId())) {
                        i1 = (i + 1);
                        yServiceId = "";
                        mAdapter_sv.notifyDataSetChanged();
                    }
                }
                if (i1 != 0){
                    showSelectService();//显示选择的服务
                }
            } else {
                //获取服务tab
                HashMap<String, String> params2 = new HashMap<>();
                params2.put("y_parent_id", "0");
                RequestService(params2, 0);

            }
            /*requestServer();
            tv_addr.setText(localUserInfo.getCityname());*/
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (MainActivity.item == 1) {
            if (!localUserInfo.getCarname().equals("")) {
                tv_carname.setText(localUserInfo.getCarname());
                tv_carnum.setText(localUserInfo.getCarnum());
                Glide.with(getActivity()).load(URLs.IMGHOST + localUserInfo.getCarlogo())
                        .centerCrop()
                        .into(iv_carlogo);//加载图片
            }
            MyLogger.i(">>>>>>>选择的服务id" + yServiceId);
            if (list_sv.size() > 0) {
                for (int i = 0; i < list_sv.size(); i++) {
                    if (yServiceId.equals(list_sv.get(i).getYServiceId())) {
                        i1 = (i + 1);
                        yServiceId = "";
                        mAdapter_sv.notifyDataSetChanged();
                    }
                }
                if (i1 != 0){
                    showSelectService();//显示选择的服务
                }

            } else {
                //获取服务tab
                HashMap<String, String> params2 = new HashMap<>();
                params2.put("y_parent_id", "0");
                RequestService(params2, 0);
            }
//            requestServer();
        }
    }

    @Override
    protected void initView(View view) {

        findViewByID_My(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(getActivity()), 0, 0);
        //刷新
        setSpringViewMore(true);//不需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                page = 0;
                Map<String, String> params = new HashMap<>();
                params.put("service_name", service_name);
                params.put("page", page + "");
                params.put("longitude", longitude);
                params.put("latitude", latitude);
                params.put("is_review", "1");
                params.put("is_index", "0");//1为首页数据
                Request(params);
            }

            @Override
            public void onLoadmore() {
                page++;
                Map<String, String> params = new HashMap<>();
                params.put("service_name", service_name);
                params.put("page", page + "");
                params.put("longitude", longitude);
                params.put("latitude", latitude);
                params.put("is_review", "1");
                params.put("is_index", "0");//1为首页数据
                RequestMore(params);
            }
        });

        recyclerView = findViewByID_My(R.id.recyclerView);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLinearLayoutManager);

        tv_addr = findViewByID_My(R.id.tv_addr);
        tv_caogao = findViewByID_My(R.id.tv_caogao);
        tv_addr.setOnClickListener(this);
        tv_caogao.setOnClickListener(this);
        rl_search = findViewByID_My(R.id.rl_search);
        rl_search.setOnClickListener(this);
        et_search = findViewByID_My(R.id.et_search);
        et_search.setOnClickListener(this);
        /*et_addr.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //关闭软键盘
                    hideInput();
                    //do something
                    //doSearch();
                    MyLogger.i(">>>>>>>>输入后：" + et_addr.getText().toString().trim());
                    if (!et_addr.getText().toString().trim().equals("")) {
                        //第二个参数传入null或者“”代表在全国进行检索，否则按照传入的city进行检索
                        InputtipsQuery inputquery = new InputtipsQuery(et_addr.getText().toString().trim(), city);
                        inputquery.setCityLimit(false);//限制在当前城市
                        Inputtips inputTips = new Inputtips(SelectAddressActivity.this, inputquery);
                        inputTips.setInputtipsListener(new Inputtips.InputtipsListener() {
                            @Override
                            public void onGetInputtips(List<Tip> list, int i) {
                                if (list.size() > 0) {
                                    //显示弹窗
//                                    showPopupWindow1(et_addr, list);
                                    showMapAddr(list);
                                }
                            }
                        });
                        inputTips.requestInputtipsAsyn();
                    } else {
                        recyclerView_addr.setVisibility(View.GONE);
                    }

                    return true;
                }
                return false;
            }
        });*/

        ll_car = findViewByID_My(R.id.ll_car);
        ll_car.setOnClickListener(this);
        tv_carname = findViewByID_My(R.id.tv_carname);
        tv_carnum = findViewByID_My(R.id.tv_carnum);
        iv_carlogo = findViewByID_My(R.id.iv_carlogo);


        //服务列表
        recyclerView_sv = findViewByID_My(R.id.recyclerView_sv);
        LinearLayoutManager llm1 = new LinearLayoutManager(getActivity());
        llm1.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
        recyclerView_sv.setLayoutManager(llm1);

        //服务tab
        ll_tab = findViewByID_My(R.id.ll_tab);
        ll_tab.setVisibility(View.GONE);
        rv_tab1 = findViewByID_My(R.id.rv_tab1);
        rv_tab1.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_tab2 = findViewByID_My(R.id.rv_tab2);
        rv_tab2.setLayoutManager(new LinearLayoutManager(getActivity()));

        //悬浮窗
        ll_xuanfu = findViewByID_My(R.id.ll_xuanfu);
        tv_tabs = findViewByID_My(R.id.tv_tabs);
        tv_yixuan = findViewByID_My(R.id.tv_yixuan);
        tv_savecaogao = findViewByID_My(R.id.tv_savecaogao);
        tv_savecaogao.setOnClickListener(this);
        tv_pipei = findViewByID_My(R.id.tv_pipei);
        tv_pipei.setOnClickListener(this);
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

                        MyLogger.i("定位信息", "纬度：" + aMapLocation.getLatitude()
                                + "\n经度:" + aMapLocation.getLongitude()
                                + "\n地址:" + aMapLocation.getAddress());
//                        register_addr = aMapLocation.getAddress();
                        longitude = aMapLocation.getLongitude() + "";
                        latitude = aMapLocation.getLatitude() + "";

                        localUserInfo.setCityname(aMapLocation.getCity());
                        localUserInfo.setLongitude(longitude);
                        localUserInfo.setLatitude(latitude);

                        tv_addr.setText(aMapLocation.getCity() + "");

                        //第一次请求数据
                        page = 0;
                        Map<String, String> params = new HashMap<>();
                        params.put("service_name", service_name);
                        params.put("page", page + "");
                        params.put("longitude", longitude);
                        params.put("latitude", latitude);
                        params.put("is_review", "1");
                        params.put("is_index", "0");//1为首页数据
                        Request(params);

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
//        if (localUserInfo.getCityname().equals("")){
//            mLocationClient.startLocation();
//        }

        /*//获取服务tab
        HashMap<String, String> params2 = new HashMap<>();
        params2.put("y_parent_id", "0");
        RequestService(params2, 0);*/

        requestServer();
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
            case R.id.tv_caogao:
                //草稿
                CommonUtil.gotoActivity(getActivity(), DraftActivity.class);
                break;
            case R.id.ll_car:
                //选择车辆
                Intent intent1 = new Intent(getActivity(), MyGarageActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putInt("type", 10001);
                intent1.putExtras(bundle1);
                startActivityForResult(intent1, 10001, bundle1);
                break;

            case R.id.tv_savecaogao:
                //保存草稿
                showProgress(true, "正在保存，请稍候...");
                Map<String, String> params = new HashMap<>();
                params.put("u_token", localUserInfo.getToken());
                params.put("v_strs", v_strs);
                RequestSave(params);

                break;
            case R.id.tv_pipei:
                //匹配商家
                /*Map<String, String> params1 = new HashMap<>();
                params1.put("u_token", localUserInfo.getToken());
                params1.put("v_strs", v_strs);
                RequestPiPei(params1);*/
                i1 = 0;
                ll_tab.setVisibility(View.GONE);
                mAdapter_sv.notifyDataSetChanged();
                service_name = v_strs;
                requestServer();
                tv_pipei.setClickable(false);
                tv_pipei.setText("已匹配");
                tv_pipei.setBackgroundResource(R.drawable.yuanjiao_5_heise);

                break;

        }
    }

    @Override
    protected void updateView() {

    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        if (!longitude.equals("0")) {
            page = 0;
            Map<String, String> params = new HashMap<>();
            params.put("service_name", service_name);
            params.put("page", page + "");
            params.put("longitude", longitude);
            params.put("latitude", latitude);
            params.put("is_review", "1");
            params.put("is_index", "0");//1为首页数据
            Request(params);
        } else {
            mLocationClient.startLocation();
        }
    }

    private void Request(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Fragment3, params, headerMap, new CallBackUtil<Fragment3Model>() {
            @Override
            public Fragment3Model onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                showEmptyPage();
//                myToast(err);
            }

            @Override
            public void onResponse(Fragment3Model response) {
                hideProgress();
                list = response.getList();
                if (list.size() > 0) {
                    showContentPage();
                    mAdapter = new CommonAdapter<Fragment3Model.ListBean>
                            (getActivity(), R.layout.item_fragment3, list) {
                        @Override
                        protected void convert(ViewHolder holder, Fragment3Model.ListBean model, int position) {
                            ImageView imageView1 = holder.getView(R.id.imageView1);
                            Glide.with(getActivity())
                                    .load(URLs.IMGHOST + model.getPicture())
                                    .centerCrop()
                                    .placeholder(R.mipmap.loading)//加载站位图
                                    .error(R.mipmap.zanwutupian)//加载失败
                                    .into(imageView1);//加载图片
                            holder.setText(R.id.tv_name, model.getVName());//店名
                            holder.setText(R.id.tv_pingfen, model.getReview());//评分
                            holder.setText(R.id.tv_dingdan, model.getOrderSum() + "");//订单
                            holder.setText(R.id.tv_addr, model.getAddress());//地址
                            holder.setText(R.id.tv_juli, model.getDistance() + "m");//距离

                            if (model.getStore_service_list().size() > 0) {
                                //标签
                                FlowLayoutAdapter<Fragment3Model.ListBean.StoreServiceListBean> flowLayoutAdapter1 =
                                        new FlowLayoutAdapter<Fragment3Model.ListBean.StoreServiceListBean>
                                                (model.getStore_service_list()) {
                                            @Override
                                            public void bindDataToView(ViewHolder holder, int position,
                                                                       Fragment3Model.ListBean.StoreServiceListBean bean) {
//                                holder.setText(R.id.tv,bean);
                                                TextView tv = holder.getView(R.id.tv);
                                                tv.setText(bean.getYStateValue());
                                    /*tv.setTextColor(getResources().getColor(R.color.black1));
                                    tv.setBackgroundResource(R.drawable.yuanjiao_3_huise);*/
                                            }

                                            @Override
                                            public void onItemClick(int position, Fragment3Model.ListBean.StoreServiceListBean bean) {
//                        showToast("点击" + position);
                                            }

                                            @Override
                                            public int getItemLayoutID(int position, Fragment3Model.ListBean.StoreServiceListBean bean) {
                                                return R.layout.item_fragment3_flowlayout1;
                                            }
                                        };
                                //服务
                                FlowLayoutAdapter<Fragment3Model.ListBean.StoreServiceListBean> flowLayoutAdapter2 =
                                        new FlowLayoutAdapter<Fragment3Model.ListBean.StoreServiceListBean>
                                                (model.getStore_service_list()) {
                                            @Override
                                            public void bindDataToView(ViewHolder holder, int position,
                                                                       Fragment3Model.ListBean.StoreServiceListBean bean) {
                                                TextView tv1 = holder.getView(R.id.tv1);
                                                tv1.setText(bean.getYStateValue() + "：");
                                                TextView tv2 = holder.getView(R.id.tv2);
                                                View view = holder.getView(R.id.view);
                                                if (bean.getYState() == 0) {
                                                    //空闲
                                                    tv2.setText("空闲");
                                                    tv2.setTextColor(getResources().getColor(R.color.green));
                                                    view.setBackgroundResource(R.drawable.yuanxing_lvse);
                                                } else {
                                                    //忙碌
                                                    tv2.setText("忙碌");
                                                    tv2.setTextColor(getResources().getColor(R.color.red));
                                                    view.setBackgroundResource(R.drawable.yuanxing_hongse);
                                                }
                                    /*
                                    tv.setBackgroundResource(R.drawable.yuanjiao_3_huise);*/
                                            }

                                            @Override
                                            public void onItemClick(int position, Fragment3Model.ListBean.StoreServiceListBean bean) {
//                        showToast("点击" + position);
                                            }

                                            @Override
                                            public int getItemLayoutID(int position, Fragment3Model.ListBean.StoreServiceListBean bean) {
                                                return R.layout.item_fragment3_flowlayout2;
                                            }
                                        };
                                ((FlowLayout) holder.getView(R.id.flowLayout1)).setAdapter(flowLayoutAdapter1);
                                ((FlowLayout) holder.getView(R.id.flowLayout2)).setAdapter(flowLayoutAdapter2);
                            }

                        }
                    };


                    mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            Bundle bundle = new Bundle();
                            bundle.putString("id", list.get(i).getYStoreId());
                            bundle.putString("longitude", longitude);
                            bundle.putString("latitude", latitude);
                            CommonUtil.gotoActivityWithData(getActivity(), StoreDetailActivity.class, bundle, false);
                        }

                        @Override
                        public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            return false;
                        }
                    });
                    recyclerView.setAdapter(mAdapter);
                } else {
                    showEmptyPage();
                }
            }
        });
    }

    private void RequestMore(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Fragment3, params, headerMap, new CallBackUtil<Fragment3Model>() {
            @Override
            public Fragment3Model onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
                page--;
            }

            @Override
            public void onResponse(Fragment3Model response) {
                hideProgress();
                List<Fragment3Model.ListBean> list1 = new ArrayList<>();
                list1 = response.getList();
                if (list1.size() == 0) {
                    page--;
                    myToast(getString(R.string.app_nomore));
                } else {
                    list.addAll(list1);
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
    }


    /**
     * 获取服务列表
     *
     * @param params
     * @param type
     */
    private void RequestService(HashMap<String, String> params, int type) {
        OkhttpUtil.okHttpPost(URLs.ServiceList_all, params, headerMap, new CallBackUtil<ServiceListModel_All>() {
            @Override
            public ServiceListModel_All onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
            }

            @Override
            public void onResponse(ServiceListModel_All response) {
                hideProgress();
                list_sv = response.getList();
                /**
                 * 第一级
                 */
                list_name.clear();
                list_name.add("门店");
                for (ServiceListModel_All.ListBean bean : list_sv) {
                    list_name.add(bean.getVName());
                }
                mAdapter_sv = new CommonAdapter<String>
                        (getActivity(), R.layout.item_fragment2_sv, list_name) {
                    @Override
                    protected void convert(ViewHolder holder, String model, int position) {
//                        holder.setText(R.id.tv_tab, model.getVName());
                        TextView tv_tab = holder.getView(R.id.tv_tab);
                        tv_tab.setText(model);
                        if (i1 == position) {
                            tv_tab.setTextColor(getResources().getColor(R.color.blue));
                        } else {
                            tv_tab.setTextColor(getResources().getColor(R.color.black));
                        }

                        if (i1 == 0) {//热门
                            ll_tab.setVisibility(View.GONE);
                            if (list.size() > 0) {
                                showContentPage();
                            }else {
                                showEmptyPage();
                            }
                            service_name = "";
                        } else {
                            showContentPage();
                            ll_tab.setVisibility(View.VISIBLE);
                            service_name = list_sv.get(i1 - 1).getVName();
                            /**
                             * 第二级
                             */
                            list_tab1 = list_sv.get(i1 - 1).getV_list();

                            if (list_tab1.size() == 0) {
                                //第二级没有数据-隐藏第三级
                                rv_tab2.setVisibility(View.INVISIBLE);
                            } else {
                                rv_tab2.setVisibility(View.VISIBLE);
                            }
                            ca_tab1 = new CommonAdapter<ServiceListModel_All.ListBean.VListBeanXX>
                                    (getActivity(), R.layout.item_fragment2_sv_tab1, list_tab1) {
                                @Override
                                protected void convert(ViewHolder holder, ServiceListModel_All.ListBean.VListBeanXX listBean, int item) {
                                    holder.setText(R.id.textView, listBean.getVName());
                                    ImageView imageView = holder.getView(R.id.imageView);
                                    if (listBean.isIsgouxuan()) {
                                        imageView.setImageResource(R.mipmap.ic_yixuan_juxing);
                                    } else {
                                        imageView.setImageResource(R.mipmap.ic_weixuan_juxing);
                                    }
                                }
                            };
                            ca_tab1.setOnItemClickListener(new OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int item) {

                                    /**
                                     * 第三级
                                     */
                                    list_tab2 = list_tab1.get(item).getV_list();
//                                        ca_tab2.notifyDataSetChanged();
                                    ca_tab2 = new CommonAdapter<ServiceListModel_All.ListBean.VListBeanXX.VListBeanX>
                                            (getActivity(), R.layout.item_fragment2_sv_tab2, list_tab2) {
                                        @Override
                                        protected void convert(ViewHolder holder, ServiceListModel_All.ListBean.VListBeanXX.VListBeanX listBean, int item) {
                                            holder.setText(R.id.textView, listBean.getVName());
                                            TextView tianjia = holder.getView(R.id.tianjia);
                                            if (listBean.isIsgouxuan()) {
                                                tianjia.setTextColor(getResources().getColor(R.color.black3));
                                                tianjia.setText("已添加");
                                            } else {
                                                tianjia.setTextColor(getResources().getColor(R.color.blue));
                                                tianjia.setText("添加");
                                            }
                                        }
                                    };
                                    ca_tab2.setOnItemClickListener(new OnItemClickListener() {
                                        @Override
                                        public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                            if (!list_tab2.get(i).isIsgouxuan())
                                                list_tab2.get(i).setIsgouxuan(true);
                                            else
                                                list_tab2.get(i).setIsgouxuan(false);

                                            showSelectService();//显示选择的服务
                                            ca_tab2.notifyDataSetChanged();
                                        }

                                        @Override
                                        public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                            return false;
                                        }
                                    });
                                    rv_tab2.setAdapter(ca_tab2);

                                    //选择
                                    if (!list_tab1.get(item).isIsgouxuan())
                                        list_tab1.get(item).setIsgouxuan(true);
                                    else list_tab1.get(item).setIsgouxuan(false);

                                    showSelectService();//显示选择的服务

                                    ca_tab1.notifyDataSetChanged();
                                }

                                @Override
                                public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                    return false;
                                }
                            });
                            rv_tab1.setAdapter(ca_tab1);
                        }

                    }
                };
                mAdapter_sv.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        if (i != i1) {
                            i1 = i;
                            mAdapter_sv.notifyDataSetChanged();
                            if (i1 != 0){
                                showSelectService();//显示选择的服务
                            }

                        }
                    }

                    @Override
                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        return false;
                    }
                });
                recyclerView_sv.setAdapter(mAdapter_sv);

                for (int i = 0; i < list_sv.size(); i++) {
                    if (yServiceId.equals(list_sv.get(i).getYServiceId())) {
                        i1 = (i + 1);
                        yServiceId = "";
                        mAdapter_sv.notifyDataSetChanged();
                    }
                }
            }
        });
    }

    /**
     * 显示选择的服务
     */
    private void showSelectService() {
        v_strs = "";
        int count = 0;

        //单独加一个一级
        v_strs += list_sv.get(i1-1).getVName()+"||";
        count++;

        for (ServiceListModel_All.ListBean bean1 : list_sv) {//第一级
            for (ServiceListModel_All.ListBean.VListBeanXX bean2 : bean1.getV_list()) {//第二级
                if (bean2.isIsgouxuan()) {
                    count++;
                    v_strs += bean2.getVName() + "||";
                }
                for (ServiceListModel_All.ListBean.VListBeanXX.VListBeanX bean3 : bean2.getV_list()) {//第三级
                    if (bean3.isIsgouxuan()) {
                        count++;
                        v_strs += bean3.getVName() + "||";
                    }
                }
            }
        }

        MyLogger.i(">>>>>>" + v_strs + count);

        if (!v_strs.equals("")) {
            ll_xuanfu.setVisibility(View.VISIBLE);
            v_strs = v_strs.substring(0, v_strs.length() - 2);
            tv_tabs.setText(v_strs);
            tv_yixuan.setText("已选：" + count + "项");

            if (service_name.equals(v_strs)) {
                tv_pipei.setClickable(false);
                tv_pipei.setText("已匹配");
                tv_pipei.setBackgroundResource(R.drawable.yuanjiao_5_heise);
            } else {
                tv_pipei.setClickable(true);
                tv_pipei.setText("匹配商家");
                tv_pipei.setBackgroundResource(R.drawable.yuanjiao_5_lanse);
            }
        } else {
            ll_xuanfu.setVisibility(View.GONE);
        }
    }

    /**
     * 保存草稿
     *
     * @param params
     */
    private void RequestSave(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.SaveDraft, params, headerMap, new CallBackUtil() {
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
                myToast("保存成功");
                for (ServiceListModel_All.ListBean bean1 : list_sv) {//第一级
                    for (ServiceListModel_All.ListBean.VListBeanXX bean2 : bean1.getV_list()) {//第二级
                        if (bean2.isIsgouxuan()) {
                            bean2.setIsgouxuan(false);
                        }
                        for (ServiceListModel_All.ListBean.VListBeanXX.VListBeanX bean3 : bean2.getV_list()) {//第二级
                            if (bean3.isIsgouxuan()) {
                                bean3.setIsgouxuan(false);
                            }
                        }
                    }
                }
                mAdapter_sv.notifyDataSetChanged();
                ca_tab1.notifyDataSetChanged();
                ca_tab2.notifyDataSetChanged();
                showSelectService();
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
