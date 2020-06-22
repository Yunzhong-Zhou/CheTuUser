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
public class Fragment2 extends BaseFragment {
    RelativeLayout rl_search;
    EditText et_search;

    int page = 0;
    String longitude = "", latitude = "",service_name = "";
    private RecyclerView recyclerView;
    List<Fragment3Model.ListBean> list = new ArrayList<>();
    CommonAdapter<Fragment3Model.ListBean> mAdapter;

    TextView tv_caogao;

    //车辆信息
    LinearLayout ll_car;
    TextView tv_carname,tv_carnum;
    ImageView iv_carlogo;

    /**
     * 服务内容
     */
    RecyclerView recyclerView_sv;
    CommonAdapter<ServiceListModel_All.ListBean> mAdapter_sv;
    List<ServiceListModel_All.ListBean> list_sv = new ArrayList<>();
    int i1 = 0;
    //定位
    //声明AMapLocationClient类对象
    private AMapLocationClient mLocationClient = null;
    //城市选择
    TextView tv_addr;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, container, false);
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
            if (!localUserInfo.getCarname().equals("")){
//                y_user_sedan_id = localUserInfo.getCarid();
                tv_carname.setText(localUserInfo.getCarname());
                tv_carnum.setText(localUserInfo.getCarnum());
                Glide.with(getActivity()).load(URLs.IMGHOST + localUserInfo.getCarlogo())
                        .centerCrop()
                        .into(iv_carlogo);//加载图片
            }
            /*requestServer();
            tv_addr.setText(localUserInfo.getCityname());*/
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (MainActivity.item == 1) {
            if (!localUserInfo.getCarname().equals("")){
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
    }

    @Override
    protected void initData() {
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

                        MyLogger.i("定位信息","纬度：" + aMapLocation.getLatitude()
                                + "\n经度:" + aMapLocation.getLongitude()
                                + "\n地址:" + aMapLocation.getAddress());
//                        register_addr = aMapLocation.getAddress();
                        longitude = aMapLocation.getLongitude() + "";
                        latitude = aMapLocation.getLatitude() + "";

                        localUserInfo.setCityname(aMapLocation.getCity());

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

        //获取服务tab
        HashMap<String, String> params2 = new HashMap<>();
        params2.put("y_parent_id", "0");
        RequestService(params2, 0);

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

        }
    }

    @Override
    protected void updateView() {

    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        //        if (!longitude.equals("")) {
        page = 0;
        Map<String, String> params = new HashMap<>();
        params.put("service_name", service_name);
        params.put("page", page + "");
        params.put("longitude", longitude);
        params.put("latitude", latitude);
        params.put("is_review", "1");
        params.put("is_index", "0");//1为首页数据
        Request(params);
//        } else {
//            mLocationClient.startLocation();
//        }
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

                            MyLogger.i(">>>>>>"+ model.getStore_service_list().size());
                            if (model.getStore_service_list().size() > 0) {
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
//                myToast(err);
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
                mAdapter_sv = new CommonAdapter<ServiceListModel_All.ListBean>
                        (getActivity(), R.layout.item_fragment2_sv, list_sv) {
                    @Override
                    protected void convert(ViewHolder holder, ServiceListModel_All.ListBean model, int position) {
//                        holder.setText(R.id.tv_tab, model.getVName());
                        TextView tv_tab = holder.getView(R.id.tv_tab);
                        tv_tab.setText(model.getVName());

                       /* if (i1 ==0){
                            service_name = model.getVName();
                            requestServer();
                        }*/

                        if (i1 == position) {
                            tv_tab.setTextColor(getResources().getColor(R.color.blue));
                        } else {
                            tv_tab.setTextColor(getResources().getColor(R.color.black));
                        }
                    }
                };
                mAdapter_sv.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        if (i != i1) {
                            i1 = i;
                            service_name = list_sv.get(i).getVName();
                            mAdapter_sv.notifyDataSetChanged();
                            requestServer();
                        }
                    }

                    @Override
                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        return false;
                    }
                });
                recyclerView_sv.setAdapter(mAdapter_sv);

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
