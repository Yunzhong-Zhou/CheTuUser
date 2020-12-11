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
import com.chetu.user.activity.ConfirmOrderActivity;
import com.chetu.user.activity.DraftActivity;
import com.chetu.user.activity.MainActivity;
import com.chetu.user.activity.MyGarageActivity;
import com.chetu.user.activity.SearchActivity;
import com.chetu.user.activity.StoreDetailActivity;
import com.chetu.user.activity.WebContentActivity;
import com.chetu.user.adapter.CircleImageAdapter;
import com.chetu.user.base.BaseFragment;
import com.chetu.user.model.Fragment2BannerModel;
import com.chetu.user.model.Fragment3Model;
import com.chetu.user.model.ServiceListModel_All;
import com.chetu.user.net.URLs;
import com.chetu.user.okhttp.CallBackUtil;
import com.chetu.user.okhttp.OkhttpUtil;
import com.chetu.user.popupwindow.PhotoShowDialog;
import com.chetu.user.utils.CommonUtil;
import com.chetu.user.utils.MyLogger;
import com.liaoinstan.springview.widget.SpringView;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Response;


/**
 * Created by fafukeji01 on 2016/1/6.
 * 养车
 */
public class Fragment2_3 extends BaseFragment {
    public static String yServiceId = "";
    public static int isSheet = 0;//是否喷漆
    RelativeLayout rl_search,rl_xiaoxi;
    TextView tv_xiaoxinum;
    EditText et_search;

    int page = 0;
    String longitude = "", latitude = "", service_name = "";

    Banner banner;
    List<String> images = new ArrayList<>();
    /**
     * 门店
     */
    private RecyclerView recyclerView;
    List<Fragment3Model.ListBean> list = new ArrayList<>();
    CommonAdapter<Fragment3Model.ListBean> mAdapter;
    int store_item = -1;

    TextView tv_caogao,tv_pipeimendian;

    //车辆信息
    LinearLayout ll_car;
    TextView tv_carname, tv_carnum;
    ImageView iv_carlogo;

    /**
     * 服务内容
     */
    RecyclerView recyclerView_sv;
    CommonAdapter<ServiceListModel_All.ListBean> mAdapter_sv;
    List<ServiceListModel_All.ListBean> list_sv = new ArrayList<>();
    int i1 = 0;

    /**
     * 服务tab
     */

    LinearLayout ll_tab;
    RecyclerView rv_tab1, rv_tab2, rv_tab3;
    CommonAdapter<ServiceListModel_All.ListBean.VListBeanXX> ca_tab1;
    List<ServiceListModel_All.ListBean.VListBeanXX> list_tab1 = new ArrayList<>();
    CommonAdapter<ServiceListModel_All.ListBean.VListBeanXX.VListBeanX> ca_tab2;
    List<ServiceListModel_All.ListBean.VListBeanXX.VListBeanX> list_tab2 = new ArrayList<>();
    CommonAdapter<ServiceListModel_All.ListBean.VListBeanXX.VListBeanX.VListBean> ca_tab3;
    List<ServiceListModel_All.ListBean.VListBeanXX.VListBeanX.VListBean> list_tab3 = new ArrayList<>();

    /**
     * 悬浮窗
     */
    String v_strs = "", y_draft_id = "0";
    LinearLayout ll_xuanfu;
    TextView tv_tabs, tv_yixuan, tv_savecaogao, tv_pipei, tv_xiugai;
    boolean isXiuGai = true,isShowList = false;
    RecyclerView rv_tabs;
    CommonAdapter<String> ca_tabs;
    /**
     * 喷漆
     */
    boolean isleft = true, isquanche = false;
    LinearLayout ll_penqi, ll_penqi_left, ll_penqi_right;
    ImageView iv_penqi_left, iv_penqi_right;
    TextView tv_penqi_left, tv_penqi_right, tv_quancepenqi, tv_pipei_penqi;
    RecyclerView rv_penqi;
    CommonAdapter<ServiceListModel_All.ListBean.VListBeanXX> ca_penqi;
    CommonAdapter<ServiceListModel_All.ListBean> ca_penqi1;

    //定位
    //声明AMapLocationClient类对象
    private AMapLocationClient mLocationClient = null;
    //城市选择
    TextView tv_addr;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2_3, container, false);
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
           /* if (!localUserInfo.getCarname().equals("")) {
//                y_user_sedan_id = localUserInfo.getCarid();
                tv_carname.setText(localUserInfo.getCarname());
                tv_carnum.setText(localUserInfo.getCarnum());
                Glide.with(getActivity()).load(URLs.IMGHOST + localUserInfo.getCarlogo())
                        .centerCrop()
                        .into(iv_carlogo);//加载图片
            }
            MyLogger.i(">>>>>>>选择的服务id" + yServiceId + ">>>>>是否为喷漆" + isSheet);

            //获取服务tab
            HashMap<String, String> params2 = new HashMap<>();
            if (yServiceId.equals("")) {
                i1 = -1;
                ll_tab.setVisibility(View.GONE);
                params2.put("parent_id", "0");
            } else {
                params2.put("parent_id", yServiceId);
            }
            params2.put("msg", msg);
            RequestService(params2, 0);

            requestServer();*/

            /*if (list_sv.size() > 0) {
                for (int i = 0; i < list_sv.size(); i++) {//循环判断传入的id 与列表id是否一致
                    if (yServiceId.equals(list_sv.get(i).getYServiceId())) {//如一致，保存该下标，设置选中状态
                        i1 = i;
                        yServiceId = "";
                        mAdapter_sv.notifyDataSetChanged();

                        if (list_sv.get(i).getIsSheet() == 1) {//是喷漆
                            ll_tab.setVisibility(View.GONE);
                            ll_penqi.setVisibility(View.VISIBLE);
                        } else {
                            ll_tab.setVisibility(View.VISIBLE);
                            ll_penqi.setVisibility(View.GONE);
                        }
                    }
                }
                showSelectService();//显示选择的服务

                requestServer();
            } else {
                //获取服务tab
                HashMap<String, String> params2 = new HashMap<>();
                params2.put("y_parent_id", "0");
                RequestService(params2, 0);
            }*/
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

            MyLogger.i(">>>>>>>选择的服务id" + yServiceId + ">>>>>是否为喷漆" + isSheet);

            y_draft_id = "0";//清空草稿的id

            //获取服务tab
            HashMap<String, String> params2 = new HashMap<>();
            if (yServiceId.equals("")) {
                i1 = 0;
                ll_tab.setVisibility(View.GONE);
                params2.put("parent_id", "0");
            } else {
                params2.put("parent_id", yServiceId);
            }
            params2.put("keys", "");
            RequestService(params2, 0);
            showSelectService();//显示选择的服务

            requestServer();

            /*if (list_sv.size() > 0) {
                for (int i = 0; i < list_sv.size(); i++) {
                    if (yServiceId.equals(list_sv.get(i).getYServiceId())) {
                        i1 = i;
                        yServiceId = "";
                        mAdapter_sv.notifyDataSetChanged();

                        if (list_sv.get(i).getIsSheet() == 1) {//是喷漆
                            ll_tab.setVisibility(View.GONE);
                            ll_penqi.setVisibility(View.VISIBLE);
                        } else {
                            ll_tab.setVisibility(View.VISIBLE);
                            ll_penqi.setVisibility(View.GONE);
                        }
                    }
                }
                showSelectService();//显示选择的服务

                requestServer();

            } else {
                //获取服务tab
                HashMap<String, String> params2 = new HashMap<>();
                params2.put("y_parent_id", "0");
                RequestService(params2, 0);
            }*/


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
        banner = findViewByID_My(R.id.banner);
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

        rl_xiaoxi = findViewByID_My(R.id.rl_xiaoxi);
        rl_xiaoxi.setOnClickListener(this);
        tv_xiaoxinum = findViewByID_My(R.id.tv_xiaoxinum);

        tv_pipeimendian = findViewByID_My(R.id.tv_pipeimendian);
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
        ll_penqi = findViewByID_My(R.id.ll_penqi);
//        ll_tab.setVisibility(View.GONE);
        rv_tab1 = findViewByID_My(R.id.rv_tab1);
        rv_tab1.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_tab2 = findViewByID_My(R.id.rv_tab2);
        rv_tab2.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_tab3 = findViewByID_My(R.id.rv_tab3);
        rv_tab3.setLayoutManager(new LinearLayoutManager(getActivity()));

        //悬浮窗
        ll_xuanfu = findViewByID_My(R.id.ll_xuanfu);
        tv_tabs = findViewByID_My(R.id.tv_tabs);
        tv_tabs.setOnClickListener(this);
        tv_yixuan = findViewByID_My(R.id.tv_yixuan);
        tv_savecaogao = findViewByID_My(R.id.tv_savecaogao);
        tv_savecaogao.setOnClickListener(this);
        tv_pipei = findViewByID_My(R.id.tv_pipei);
        tv_pipei.setOnClickListener(this);
        tv_xiugai = findViewByID_My(R.id.tv_xiugai);
        tv_xiugai.setOnClickListener(this);
        rv_tabs= findViewByID_My(R.id.rv_tabs);
        rv_tabs.setLayoutManager(new LinearLayoutManager(getActivity()));

        //喷漆
        ll_penqi_left = findViewByID_My(R.id.ll_penqi_left);
        ll_penqi_right = findViewByID_My(R.id.ll_penqi_right);
        ll_penqi_left.setOnClickListener(this);
        ll_penqi_right.setOnClickListener(this);
        iv_penqi_left = findViewByID_My(R.id.iv_penqi_left);
        iv_penqi_right = findViewByID_My(R.id.iv_penqi_right);
        tv_penqi_left = findViewByID_My(R.id.tv_penqi_left);
        tv_penqi_right = findViewByID_My(R.id.tv_penqi_right);
        tv_quancepenqi = findViewByID_My(R.id.tv_quancepenqi);
        tv_pipei_penqi = findViewByID_My(R.id.tv_pipei_penqi);
        tv_quancepenqi.setOnClickListener(this);
        tv_pipei_penqi.setOnClickListener(this);
        rv_penqi = findViewByID_My(R.id.rv_penqi);
        rv_penqi.setLayoutManager(new GridLayoutManager(getActivity(), 4));
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

        //选择的车辆
        if (!localUserInfo.getCarname().equals("")) {
            tv_carname.setText(localUserInfo.getCarname());
            tv_carnum.setText(localUserInfo.getCarnum());
            Glide.with(getActivity()).load(URLs.IMGHOST + localUserInfo.getCarlogo())
                    .centerCrop()
                    .into(iv_carlogo);//加载图片
        }
        //获取服务tab
        HashMap<String, String> params1 = new HashMap<>();
        params1.put("parent_id", "0");
        params1.put("keys", "");
        RequestService(params1, 0);

        //获取banner
        HashMap<String, String> params2 = new HashMap<>();
//        params2.put("y_parent_id", "0");
        RequestBanner(params2);
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
//                CommonUtil.gotoActivity(getActivity(), DraftActivity.class);
                Intent intent2 = new Intent(getActivity(), DraftActivity.class);
                Bundle bundle2 = new Bundle();
                bundle2.putInt("type", 10002);
                intent2.putExtras(bundle2);
                startActivityForResult(intent2, 10002, bundle2);
                break;
            case R.id.rl_xiaoxi:
                //消息
                String url = URLs.KFHOST + "/#/pages/chetu-kf/chat_list?token=" + localUserInfo.getToken();
                Bundle bundle = new Bundle();
                bundle.putString("url", url);
                CommonUtil.gotoActivityWithData(getActivity(), WebContentActivity.class, bundle, false);
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
                MyLogger.i(">>>>>>>"+y_draft_id);
                showProgress(true, "正在保存，请稍候...");
                Map<String, String> params = new HashMap<>();
                params.put("u_token", localUserInfo.getToken());
                params.put("v_strs", v_strs);
                params.put("y_draft_id", y_draft_id);
                RequestSave(params);

                break;
            case R.id.tv_tabs:
                //选择的服务
                isShowList = !isShowList;
                if (isShowList){
                    rv_tabs.setVisibility(View.VISIBLE);
                }else {
                    rv_tabs.setVisibility(View.GONE);
                }
                break;
            case R.id.tv_pipei_penqi:
                //匹配-喷漆
            case R.id.tv_pipei:
                //匹配商家
                /*Map<String, String> params1 = new HashMap<>();
                params1.put("u_token", localUserInfo.getToken());
                params1.put("v_strs", v_strs);
                RequestPiPei(params1);*/

//                i1 = 0;
//                ll_tab.setVisibility(View.GONE);
//                mAdapter_sv.notifyDataSetChanged();

                if (tv_pipei.getText().toString().equals("匹配")) {
                    service_name = v_strs;
                    requestServer();
//                tv_pipei.setClickable(false);
                    tv_pipei.setText("下单");
                    tv_pipei.setBackgroundResource(R.drawable.yuanjiao_5_heise);

                    isXiuGai = false;
                    tv_xiugai.setVisibility(View.VISIBLE);
                } else {
                    //下单
                    if (store_item >= 0) {
                        JSONArray jsonArray = new JSONArray();
                        for (String s : list.get(store_item).getStoreserverMatchingList()) {
                            try {
                                JSONObject object1 = new JSONObject();
                                object1.put("y_store_service_id", s);
                                object1.put("is_service", "1");//1为服务  2为服务下边的商品 3为独立商品
                                object1.put("g_num", "1");
                                jsonArray.put(object1);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        if (jsonArray.length() > 0) {
                            showProgress(true, "正在加入购物车，请稍候...");
                            Map<String, String> params1 = new HashMap<>();
                            params1.put("u_token", localUserInfo.getToken());
                            params1.put("jsonstr", jsonArray.toString());
                            RequestXiaDan(params1, list.get(store_item).getYStoreId());
                        } else {
                       /* Bundle bundle = new Bundle();
                        bundle.putString("id", list.get(i).getYStoreId());
                        bundle.putString("longitude", longitude);
                        bundle.putString("latitude", latitude);
                        CommonUtil.gotoActivityWithData(getActivity(), StoreDetailActivity.class, bundle, false);*/
                        }
                    } else {
                        myToast("请选择门店");
                    }


                }

                break;
            case R.id.tv_xiugai:
                //修改
                isXiuGai = true;
                tv_pipei.setClickable(true);
                tv_pipei.setText("匹配");
                tv_pipei.setBackgroundResource(R.drawable.yuanjiao_5_lanse);

                tv_xiugai.setVisibility(View.GONE);
                break;

            case R.id.ll_penqi_left:
                //喷漆-左侧
                isleft = true;

                ll_penqi_left.setBackgroundResource(R.mipmap.bg_banpen_left_1);
                tv_penqi_left.setTextColor(getResources().getColor(R.color.white));
                iv_penqi_left.setImageResource(R.mipmap.ic_banpen_left_1);

                ll_penqi_right.setBackgroundResource(R.mipmap.bg_banpen_right_0);
                tv_penqi_right.setTextColor(getResources().getColor(R.color.black));
                iv_penqi_right.setImageResource(R.mipmap.ic_banpen_right_0);

                showSelectService();//显示选择的服务
                break;
            case R.id.ll_penqi_right:
                //喷漆-右侧
                isleft = false;
                ll_penqi_left.setBackgroundResource(R.mipmap.bg_banpen_left_0);
                tv_penqi_left.setTextColor(getResources().getColor(R.color.black));
                iv_penqi_left.setImageResource(R.mipmap.ic_banpen_left_0);

                ll_penqi_right.setBackgroundResource(R.mipmap.bg_banpen_right_1);
                tv_penqi_right.setTextColor(getResources().getColor(R.color.white));
                iv_penqi_right.setImageResource(R.mipmap.ic_banpen_right_1);

                showSelectService();//显示选择的服务
                break;
            case R.id.tv_quancepenqi:
                //全车喷漆
                isquanche = !isquanche;
                if (isquanche) {
                    tv_quancepenqi.setTextColor(getResources().getColor(R.color.white));
                    tv_quancepenqi.setBackgroundResource(R.drawable.yuanjiao_5_lanse);
                    for (ServiceListModel_All.ListBean.VListBeanXX bean2 : list_tab1) {//第二级
                        bean2.setIsgouxuan(true);
                    }
                } else {
                    tv_quancepenqi.setTextColor(getResources().getColor(R.color.black));
                    tv_quancepenqi.setBackgroundResource(R.drawable.yuanjiaobiankuang_5_huise);
                    for (ServiceListModel_All.ListBean.VListBeanXX bean2 : list_tab1) {//第二级
                        bean2.setIsgouxuan(false);
                    }
                }
                ca_penqi.notifyDataSetChanged();
                showSelectService();//显示选择的服务
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

    /**
     * 匹配的门店数据
     *
     * @param params
     */
    private void Request(Map<String, String> params) {
        store_item = -1;
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
                            (getActivity(), R.layout.item_fragment2, list) {
                        @Override
                        protected void convert(ViewHolder holder, Fragment3Model.ListBean model, int position) {
                            LinearLayout ll = holder.getView(R.id.ll);
                            if (store_item == position) {
                                ll.setBackgroundResource(R.drawable.yuanjiao_10_baise_lankuang);
                            } else {
                                ll.setBackgroundResource(R.drawable.yuanjiao_10_baise);
                            }

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

                            /*if (model.getStore_service_list().size() > 0) {
                                //标签
                                FlowLayoutAdapter<Fragment3Model.ListBean.StoreServiceListBean> flowLayoutAdapter1 =
                                        new FlowLayoutAdapter<Fragment3Model.ListBean.StoreServiceListBean>
                                                (model.getStore_service_list()) {
                                            @Override
                                            public void bindDataToView(FlowLayoutAdapter.ViewHolder holder, int position,
                                                                       Fragment3Model.ListBean.StoreServiceListBean bean) {
//                                holder.setText(R.id.tv,bean);
                                                TextView tv = holder.getView(R.id.tv);
                                                tv.setText(bean.getYStateValue());
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
                                            public void bindDataToView(FlowLayoutAdapter.ViewHolder holder, int position,
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
                                    *//*
                                    tv.setBackgroundResource(R.drawable.yuanjiao_3_huise);*//*
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
                            }*/

                        }
                    };


                    mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            /*JSONArray jsonArray = new JSONArray();
                            for (String s : list.get(i).getStoreserverMatchingList()) {
                                try {
                                    JSONObject object1 = new JSONObject();
                                    object1.put("y_store_service_id", s);
                                    object1.put("is_service", "1");//1为服务  2为服务下边的商品 3为独立商品
                                    object1.put("g_num", "1");
                                    jsonArray.put(object1);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            if (jsonArray.length() > 0) {
                                showProgress(true, "正在加入购物车，请稍候...");
                                Map<String, String> params = new HashMap<>();
                                params.put("u_token", localUserInfo.getToken());
                                params.put("jsonstr", jsonArray.toString());
                                RequestXiaDan(params, list.get(i).getYStoreId());
                            } else {
                                Bundle bundle = new Bundle();
                                bundle.putString("id", list.get(i).getYStoreId());
                                bundle.putString("longitude", longitude);
                                bundle.putString("latitude", latitude);
                                CommonUtil.gotoActivityWithData(getActivity(), StoreDetailActivity.class, bundle, false);
                            }*/

                            if (list.get(i).getStoreserverMatchingList().size() > 0) {
                                store_item = i;
                                mAdapter.notifyDataSetChanged();
                            } else {
                                Bundle bundle = new Bundle();
                                bundle.putString("id", list.get(i).getYStoreId());
                                bundle.putString("longitude", longitude);
                                bundle.putString("latitude", latitude);
                                bundle.putString("keys", v_strs);
                                CommonUtil.gotoActivityWithData(getActivity(), StoreDetailActivity.class, bundle, false);
                            }

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
    int item_service4 = -1;

    private void RequestService(HashMap<String, String> params, int type) {
        //初始化数据
        service_name = "";
        i1 = 0;
        isXiuGai = true;
        yServiceId = "";
        list_sv.clear();
        list_tab1.clear();
        list_tab2.clear();
        list_tab3.clear();
        rv_tab2.setVisibility(View.INVISIBLE);
        rv_tab3.setVisibility(View.GONE);
        if (mAdapter_sv != null) {
            mAdapter_sv.notifyDataSetChanged();
        }
        if (ca_tab1 != null) {
            ca_tab1.notifyDataSetChanged();
        }
        if (ca_tab2 != null) {
            ca_tab2.notifyDataSetChanged();
        }
        if (ca_tab3 != null) {
            ca_tab3.notifyDataSetChanged();
        }

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
                /*for (int i = 0; i < list_sv.size(); i++) {
                    if (yServiceId.equals(list_sv.get(i).getYServiceId())) {
                        i1 = i;
                        yServiceId = "";
//                        mAdapter_sv.notifyDataSetChanged();

                        if (list_sv.get(i).getIsSheet() == 1) {//是喷漆
                            ll_tab.setVisibility(View.GONE);
                            ll_penqi.setVisibility(View.VISIBLE);
                        } else {
                            ll_tab.setVisibility(View.VISIBLE);
                            ll_penqi.setVisibility(View.GONE);
                        }
                    }
                }*/

                if (isSheet == 1) {//是喷漆
                    ll_tab.setVisibility(View.GONE);
                    ll_penqi.setVisibility(View.VISIBLE);
                    recyclerView_sv.setVisibility(View.GONE);
                    isSheet = 0;
                    tv_pipeimendian.setVisibility(View.GONE);

                    //喷漆
                    ca_penqi1 = new CommonAdapter<ServiceListModel_All.ListBean>
                            (getActivity(), R.layout.item_fragment2_penqi, list_sv) {
                        @Override
                        protected void convert(ViewHolder holder, ServiceListModel_All.ListBean listBean, int item) {
                            TextView textView = holder.getView(R.id.textView);
                            textView.setText(listBean.getVName());
                            if (listBean.isIsgouxuan()) {
                                textView.setBackgroundResource(R.drawable.yuanjiao_5_lanse);
                                textView.setTextColor(getResources().getColor(R.color.white));
                            } else {
                                textView.setBackgroundResource(R.drawable.yuanjiaobiankuang_5_huise);
                                textView.setTextColor(getResources().getColor(R.color.black));
                            }
                        }
                    };
                    ca_penqi1.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int item) {
                            if (isXiuGai) {
                                //选择
                                if (!list_sv.get(item).isIsgouxuan())
                                    list_sv.get(item).setIsgouxuan(true);
                                else list_sv.get(item).setIsgouxuan(false);


                                showSelectService();//显示选择的服务

                                ca_penqi1.notifyDataSetChanged();
                            }

                        }

                        @Override
                        public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            return false;
                        }
                    });
                    rv_penqi.setAdapter(ca_penqi1);

                } else {
                    if (list_sv.size() > 0)
                        ll_tab.setVisibility(View.VISIBLE);
                    else ll_tab.setVisibility(View.GONE);
                    ll_penqi.setVisibility(View.GONE);
                    recyclerView_sv.setVisibility(View.VISIBLE);
                    tv_pipeimendian.setVisibility(View.VISIBLE);
                }


                /**
                 * 第一级
                 */
                mAdapter_sv = new CommonAdapter<ServiceListModel_All.ListBean>
                        (getActivity(), R.layout.item_fragment2_sv, list_sv) {
                    @Override
                    protected void convert(ViewHolder holder, ServiceListModel_All.ListBean model, int position) {
//                        holder.setText(R.id.tv_tab, model.getVName());
                        TextView tv_tab = holder.getView(R.id.tv_tab);
                        tv_tab.setText(model.getVName());

                        if (model.isIsgouxuan()){//如果已勾选
//                            i1 = position;
                            tv_tab.setTextColor(getResources().getColor(R.color.blue));
                        }else {
                            tv_tab.setTextColor(getResources().getColor(R.color.black));
                        }

                        if (i1 == position) {
//                            tv_tab.setBackgroundResource(R.drawable.yuanjiaobiankuang_5_lanse);
                            holder.getView(R.id.view_tab).setVisibility(View.VISIBLE);
                        } else {
                            holder.getView(R.id.view_tab).setVisibility(View.INVISIBLE);
//                            tv_tab.setBackgroundResource(R.color.transparent);
                        }

                        showContentPage();

//                        service_name = list_sv.get(i1).getVName();
                        /**
                         * 第二级
                         */

                        if (i1 >= 0) {
                            list_tab1 = list_sv.get(i1).getV_list();

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
                                    if (isXiuGai) {
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


                                                /**
                                                 * 第四级
                                                 */
                                                //如果有4级列表
                                                if (item_service4 == item) {
                                                    list_tab3 = listBean.getV_list();
                                                    if (list_tab3.size() > 0 && listBean.isIsgouxuan()) {
                                                        rv_tab3.setVisibility(View.VISIBLE);
                                                        ca_tab3 = new CommonAdapter<ServiceListModel_All.ListBean.VListBeanXX.VListBeanX.VListBean>
                                                                (getActivity(), R.layout.item_fragment2_sv_tab2, list_tab3) {
                                                            @Override
                                                            protected void convert(ViewHolder holder, ServiceListModel_All.ListBean.VListBeanXX.VListBeanX.VListBean model, int position) {
                                                                holder.setText(R.id.textView, model.getVName());
                                                                TextView tianjia = holder.getView(R.id.tianjia);
                                                                if (model.isIsgouxuan()) {
                                                                    tianjia.setTextColor(getResources().getColor(R.color.black3));
                                                                    tianjia.setText("已添加");
                                                                } else {
                                                                    tianjia.setTextColor(getResources().getColor(R.color.blue));
                                                                    tianjia.setText("添加");
                                                                }

                                                            }
                                                        };
                                                        ca_tab3.setOnItemClickListener(new OnItemClickListener() {
                                                            @Override
                                                            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                                                if (isXiuGai) {
                                                                    if (!list_tab3.get(i).isIsgouxuan())
                                                                        list_tab3.get(i).setIsgouxuan(true);
                                                                    else
                                                                        list_tab3.get(i).setIsgouxuan(false);

                                                                    showSelectService();//显示选择的服务
                                                                    ca_tab3.notifyDataSetChanged();
                                                                }
                                                            }

                                                            @Override
                                                            public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                                                return false;
                                                            }
                                                        });
                                                        rv_tab3.setAdapter(ca_tab3);

                                                    } else {
                                                        rv_tab3.setVisibility(View.GONE);
                                                    }
                                                }


                                            }
                                        };
                                        ca_tab2.setOnItemClickListener(new OnItemClickListener() {
                                            @Override
                                            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                                if (isXiuGai) {
                                                    item_service4 = i;

                                                    if (!list_tab2.get(i).isIsgouxuan())
                                                        list_tab2.get(i).setIsgouxuan(true);
                                                    else
                                                        list_tab2.get(i).setIsgouxuan(false);

                                                    rv_tab3.setVisibility(View.GONE);//先隐藏

                                                    showSelectService();//显示选择的服务
                                                    ca_tab2.notifyDataSetChanged();
                                                }
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

                                        rv_tab3.setVisibility(View.GONE);//先隐藏

                                        ca_tab1.notifyDataSetChanged();
                                    }
                                }

                                @Override
                                public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                    return false;
                                }
                            });
                            rv_tab1.setAdapter(ca_tab1);

                            //喷漆
                            ca_penqi = new CommonAdapter<ServiceListModel_All.ListBean.VListBeanXX>
                                    (getActivity(), R.layout.item_fragment2_penqi, list_tab1) {
                                @Override
                                protected void convert(ViewHolder holder, ServiceListModel_All.ListBean.VListBeanXX listBean, int item) {
                                    TextView textView = holder.getView(R.id.textView);
                                    textView.setText(listBean.getVName());
                                    if (listBean.isIsgouxuan()) {
                                        textView.setBackgroundResource(R.drawable.yuanjiao_5_lanse);
                                        textView.setTextColor(getResources().getColor(R.color.white));
                                    } else {
                                        textView.setBackgroundResource(R.drawable.yuanjiaobiankuang_5_huise);
                                        textView.setTextColor(getResources().getColor(R.color.black));
                                    }
                                }
                            };
                            ca_penqi.setOnItemClickListener(new OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int item) {
                                    if (isXiuGai) {
                                        //选择
                                        if (!list_tab1.get(item).isIsgouxuan())
                                            list_tab1.get(item).setIsgouxuan(true);
                                        else list_tab1.get(item).setIsgouxuan(false);


                                        showSelectService();//显示选择的服务

                                        ca_penqi.notifyDataSetChanged();
                                    }

                                }

                                @Override
                                public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                    return false;
                                }
                            });
                            rv_penqi.setAdapter(ca_penqi);
                        }
                    }

                };
                mAdapter_sv.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        if (isXiuGai) {
                            if (!list_sv.get(i).isIsgouxuan())
                                list_sv.get(i).setIsgouxuan(true);
                            else list_sv.get(i).setIsgouxuan(false);

                            if (list_sv.get(i).getIsSheet() == 1) {//是喷漆
                                ll_tab.setVisibility(View.GONE);
                                ll_penqi.setVisibility(View.VISIBLE);
                                tv_pipeimendian.setVisibility(View.GONE);
                            } else {
                                ll_tab.setVisibility(View.VISIBLE);
                                ll_penqi.setVisibility(View.GONE);
                                tv_pipeimendian.setVisibility(View.VISIBLE);
                            }


                            i1 = i;
                            mAdapter_sv.notifyDataSetChanged();
                            showSelectService();//显示选择的服务
                        }
                    }

                    @Override
                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        return false;
                    }
                });
                recyclerView_sv.setAdapter(mAdapter_sv);

                showSelectService();//显示选择的服务
            }
        });
    }

    /**
     * 显示选择的服务
     */
    private void showSelectService() {
        v_strs = "";
        int count = 0;
        /*if (i1 >= 0) {
            //单独加一个一级
            v_strs += list_sv.get(i1).getVName() + "||";
            count++;

            *//*if (list_sv.get(i1).getIsSheet() == 1) {//是喷漆
                count++;
                if (isleft) {
                    v_strs += "喷漆||";
                } else {
                    count++;
                    v_strs += "喷漆||钣金||";
                }
            }*//*
        } else {
            ll_xuanfu.setVisibility(View.GONE);
        }*/

        if (ll_penqi.getVisibility() == View.VISIBLE) {//是喷漆
            count++;
            if (isleft) {
                v_strs += "喷漆||";
            } else {
                count++;
                v_strs += "喷漆||钣金||";
            }
        }

//        jsonArray = new JSONArray();
        for (ServiceListModel_All.ListBean bean1 : list_sv) {//第一级
            if (bean1.isIsgouxuan()) {
                count++;
                v_strs += bean1.getVName() + "||";
            }
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

                    for (ServiceListModel_All.ListBean.VListBeanXX.VListBeanX.VListBean bean4 : bean3.getV_list()) {//第四级
                        if (bean4.isIsgouxuan()) {
                            count++;
                            v_strs += bean4.getVName() + "||";

                        }

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
//                    tv_pipei.setClickable(false);
                tv_pipei.setText("下单");
                tv_pipei.setBackgroundResource(R.drawable.yuanjiao_5_heise);
                //显示修改
                isXiuGai = false;
                tv_xiugai.setVisibility(View.VISIBLE);
            } else {
                tv_pipei.setClickable(true);
                tv_pipei.setText("匹配");
                tv_pipei.setBackgroundResource(R.drawable.yuanjiao_5_lanse);

                //隐藏修改
                isXiuGai = true;
                tv_xiugai.setVisibility(View.GONE);
            }

            //列表
            String[] strArr = v_strs.split("\\|\\|");
            List arrList = new ArrayList();
            arrList.clear();
            for (String s:strArr){
                arrList.add(s);
            }
            ca_tabs = new CommonAdapter<String>
                    (getActivity(), R.layout.item_fragment2_tabs_item, arrList) {
                @Override
                protected void convert(ViewHolder holder, String s, int i) {
                    holder.setText(R.id.title, s);
                }
            };
            rv_tabs.setAdapter(ca_tabs);

        } else {
            ll_xuanfu.setVisibility(View.GONE);
        }





    }

    /**
     * 下单-添加购物车
     *
     * @param params
     */
    private void RequestXiaDan(Map<String, String> params, String id) {
        OkhttpUtil.okHttpPost(URLs.ADDShop, params, headerMap, new CallBackUtil<Object>() {
            @Override
            public Object onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
            }

            @Override
            public void onResponse(Object response) {
                hideProgress();
                myToast("下单成功");
                Bundle bundle = new Bundle();
//                bundle.putString("id", id);
                bundle.putString("longitude", longitude);
                bundle.putString("latitude", latitude);
//                CommonUtil.gotoActivityWithData(getActivity(), StoreDetailActivity.class, bundle, false);
                bundle.putString("y_store_id", id);
                CommonUtil.gotoActivityWithData(getActivity(), ConfirmOrderActivity.class, bundle, false);
            }
        });
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
                    if (bean1.isIsgouxuan()) {
                        bean1.setIsgouxuan(false);
                    }
                    for (ServiceListModel_All.ListBean.VListBeanXX bean2 : bean1.getV_list()) {//第二级
                        if (bean2.isIsgouxuan()) {
                            bean2.setIsgouxuan(false);
                        }
                        for (ServiceListModel_All.ListBean.VListBeanXX.VListBeanX bean3 : bean2.getV_list()) {//第二级
                            if (bean3.isIsgouxuan()) {
                                bean3.setIsgouxuan(false);
                            }
                            for (ServiceListModel_All.ListBean.VListBeanXX.VListBeanX.VListBean bean4 : bean3.getV_list()) {//第四级
                                if (bean4.isIsgouxuan()) {
                                    bean4.setIsgouxuan(false);
                                }

                            }
                        }
                    }
                }
                if (mAdapter_sv != null) {
                    mAdapter_sv.notifyDataSetChanged();
                }
                if (ca_tab1 != null) {
                    ca_tab1.notifyDataSetChanged();
                }
                if (ca_tab2 != null) {
                    ca_tab2.notifyDataSetChanged();
                }
                if (ca_tab3 != null) {
                    ca_tab3.notifyDataSetChanged();
                }
                showSelectService();
            }
        });
    }

    /**
     * 获取banner
     *
     * @param params
     */
    private void RequestBanner(HashMap<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Fragment2_Banner, params, headerMap, new CallBackUtil<Fragment2BannerModel>() {
            @Override
            public Fragment2BannerModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
//                myToast(err);
            }

            @Override
            public void onResponse(Fragment2BannerModel response) {
                //banner
                /*images.add("http://file02.16sucai.com/d/file/2014/0825/dcb017b51479798f6c60b7b9bd340728.jpg");
                images.add("http://file02.16sucai.com/d/file/2014/0825/dcb017b51479798f6c60b7b9bd340728.jpg");
                images.add("http://file02.16sucai.com/d/file/2014/0825/dcb017b51479798f6c60b7b9bd340728.jpg");
                images.add("http://file02.16sucai.com/d/file/2014/0825/dcb017b51479798f6c60b7b9bd340728.jpg");*/
                images.clear();
                /*for (int i = 0; i < response.getBanner_list().size(); i++) {
                    images.add(URLs.IMGHOST + response.getBanner_list().get(i).getImgurl());
                }*/
                images.add(URLs.IMGHOST + response.getImg());
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
            case 10002:
                //选择的草稿
                if (data != null) {
                    Bundle bundle2 = data.getExtras();
                    String msg = bundle2.getString("msg");
                    y_draft_id = bundle2.getString("y_draft_id");
                    HashMap<String, String> params1 = new HashMap<>();
                    params1.put("parent_id", "0");
                    params1.put("keys", msg);
                    RequestService(params1, 0);
                }
                break;
        }

    }

}
