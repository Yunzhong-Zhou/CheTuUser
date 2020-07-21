package com.chetu.user.fragment;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bumptech.glide.Glide;
import com.chetu.user.R;
import com.chetu.user.activity.SearchActivity;
import com.chetu.user.activity.StoreDetailActivity;
import com.chetu.user.adapter.Pop_ListAdapter;
import com.chetu.user.adapter.Pop_ListAdapter1;
import com.chetu.user.base.BaseFragment;
import com.chetu.user.model.Fragment3Model;
import com.chetu.user.model.GouXuanModel;
import com.chetu.user.model.ServiceListModel;
import com.chetu.user.net.URLs;
import com.chetu.user.okhttp.CallBackUtil;
import com.chetu.user.okhttp.OkhttpUtil;
import com.chetu.user.utils.CommonUtil;
import com.chetu.user.utils.MyLogger;
import com.chetu.user.view.FixedPopupWindow;
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
 * 门店
 */
public class Fragment3 extends BaseFragment {
    //搜索
    RelativeLayout rl_search;
    EditText et_search;

    //筛选
    TextView tv_pingfen, tv_juli, tv_shaixuan;
    int i1 = -1, i2 = -1;

    private LinearLayout pop_view;
    //第一级
    List<ServiceListModel.ListBean> list_sv1 = new ArrayList<>();
    List<String> stringList1 = new ArrayList<>();
    //第二级
    List<ServiceListModel.ListBean> list_sv2 = new ArrayList<>();
    List<GouXuanModel> stringList2 = new ArrayList<>();

    //数据
    int page = 0;
    String longitude = "", latitude = "", is_review = "1", service_name = "", is_index = "0";
    private RecyclerView recyclerView;
    List<Fragment3Model.ListBean> list = new ArrayList<>();
    CommonAdapter<Fragment3Model.ListBean> mAdapter;


    //定位
    //声明AMapLocationClient类对象
    private AMapLocationClient mLocationClient = null;
    //城市选择
    TextView tv_addr;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment3, container, false);
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
    public void onResume() {
        super.onResume();
        /*if (MainActivity.item == 2) {
            requestServer();
            tv_addr.setText(localUserInfo.getCityname());
        }*/
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        /*if (MainActivity.item == 2) {
            requestServer();
        }*/
    }

    @Override
    protected void initView(View view) {
//        CommonUtil.setMargins(findViewByID_My(R.id.headView), 0, (int) CommonUtil.getStatusBarHeight(getActivity()), 0, 0);
        findViewByID_My(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(getActivity()), 0, 0);
        //刷新
        setSpringViewMore(true);//不需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                page = 0;
                Map<String, String> params = new HashMap<>();
//                params.put("y_parent_id", y_parent_id);
//                params.put("y_service_id", y_service_id);
                params.put("service_name", service_name);
                params.put("page", page + "");
                params.put("longitude", longitude);
                params.put("latitude", latitude);
                params.put("is_review", is_review);
                params.put("is_index", is_index);
                Request(params);
            }

            @Override
            public void onLoadmore() {
                page++;
                Map<String, String> params = new HashMap<>();
//                params.put("y_parent_id", y_parent_id);
//                params.put("y_service_id", y_service_id);
                params.put("service_name", service_name);
                params.put("page", page + "");
                params.put("longitude", longitude);
                params.put("latitude", latitude);
                params.put("is_review", is_review);
                params.put("is_index", is_index);
                RequestMore(params);
            }
        });

        recyclerView = findViewByID_My(R.id.recyclerView);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLinearLayoutManager);

        tv_addr = findViewByID_My(R.id.tv_addr);
        tv_addr.setOnClickListener(this);
        rl_search = findViewByID_My(R.id.rl_search);
        rl_search.setOnClickListener(this);
        et_search = findViewByID_My(R.id.et_search);
        et_search.setOnClickListener(this);

        tv_pingfen = findViewByID_My(R.id.tv_pingfen);
        tv_pingfen.setOnClickListener(this);
        tv_juli = findViewByID_My(R.id.tv_juli);
        tv_juli.setOnClickListener(this);
        tv_shaixuan = findViewByID_My(R.id.tv_shaixuan);
        tv_shaixuan.setOnClickListener(this);

        pop_view = findViewByID_My(R.id.pop_view);

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
                        params.put("is_review", is_review);
                        params.put("is_index", is_index);//1为首页数据
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

        requestServer();
        //设置场景模式后最好调用一次stop，再调用start以保证场景模式生效
//        mLocationClient.stopLocation();
        // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
//        if (localUserInfo.getCityname().equals("")) {
//        mLocationClient.startLocation();
//        }
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

            case R.id.tv_pingfen:
                //评分
                if (is_review.equals("0")) {
                    is_review = "1";
                    Drawable drawable = getResources().getDrawable(R.mipmap.ic_xia);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tv_pingfen.setCompoundDrawables(null, null, drawable, null);
                } else {
                    is_review = "0";
                    Drawable drawable = getResources().getDrawable(R.mipmap.ic_shangxia);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tv_pingfen.setCompoundDrawables(null, null, drawable, null);
                }
                requestServer();
                break;
            case R.id.tv_juli:
                //距离
                if (is_review.equals("0")) {
                    is_review = "1";
                    Drawable drawable = getResources().getDrawable(R.mipmap.ic_xia);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tv_juli.setCompoundDrawables(null, null, drawable, null);
                } else {
                    is_review = "0";
                    Drawable drawable = getResources().getDrawable(R.mipmap.ic_shangxia);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tv_juli.setCompoundDrawables(null, null, drawable, null);
                }
                requestServer();
                break;
            case R.id.tv_shaixuan:
                //筛选
                if (stringList1.size() > 0) {
                    showPopupWindow1(pop_view);
                } else {
                    HashMap<String, String> params2 = new HashMap<>();
                    params2.put("y_parent_id", "0");
                    RequestService(params2, 0);
                }
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
            params.put("is_review", is_review);
            params.put("is_index", is_index);
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
                                                Bundle bundle = new Bundle();
                                                bundle.putString("id", model.getYStoreId());
                                                bundle.putString("longitude", longitude);
                                                bundle.putString("latitude", latitude);
                                                CommonUtil.gotoActivityWithData(getActivity(), StoreDetailActivity.class, bundle, false);
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
                                                Bundle bundle = new Bundle();
                                                bundle.putString("id", model.getYStoreId());
                                                bundle.putString("longitude", longitude);
                                                bundle.putString("latitude", latitude);
                                                CommonUtil.gotoActivityWithData(getActivity(), StoreDetailActivity.class, bundle, false);
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
     * 获取筛选列表
     *
     * @param params
     * @param type
     */
    private void RequestService(HashMap<String, String> params, int type) {
        OkhttpUtil.okHttpPost(URLs.ServiceList, params, headerMap, new CallBackUtil<ServiceListModel>() {
            @Override
            public ServiceListModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();

                if (type != 0) {
                    i2 = -1;
                    stringList2.clear();
                    showPopupWindow1(pop_view);
                } else {
                    myToast(err);
                }

            }

            @Override
            public void onResponse(ServiceListModel response) {
                hideProgress();
                if (type == 0) {
                    //第一级
                    list_sv1 = response.getList();
                    for (ServiceListModel.ListBean bean : list_sv1) {
                        stringList1.add(bean.getVName());
                    }
                    //请求第二级
                    if (list_sv1.size() > 0) {
                        i1 = 0;
                        HashMap<String, String> params2 = new HashMap<>();
                        params2.put("y_parent_id", list_sv1.get(0).getYServiceId());
                        RequestService(params2, 1);
                    }
                } else {
                    //第二级
                    list_sv2 = response.getList();
                    stringList2.clear();
                    for (ServiceListModel.ListBean bean : list_sv2) {
                        stringList2.add(new GouXuanModel(bean.getVName(), false));
                    }
                    i2 = -1;
                    showPopupWindow1(pop_view);
                }

            }
        });
    }

    /**
     * 筛选弹窗
     */
    FixedPopupWindow popupWindow = null;
    Pop_ListAdapter1 adapter2;

    private void showPopupWindow1(View v) {
        // 一个自定义的布局，作为显示的内容
        final View contentView = LayoutInflater.from(getActivity()).inflate(
                R.layout.pop_fragment3, null);
        if (popupWindow == null) {
            popupWindow = new FixedPopupWindow(contentView,
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
            // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
            contentView.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    int height = contentView.findViewById(R.id.pop_listView).getTop();
                    int height1 = contentView.findViewById(R.id.pop_listView).getBottom();
                    int y = (int) event.getY();
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        if (y < height) {
                            popupWindow.dismiss();
                        }
                        if (y > height1) {
                            popupWindow.dismiss();
                        }
                    }
                    return true;
                }
            });
            // 左边列表
            ListView pop_listView1 = (ListView) contentView.findViewById(R.id.pop_listView1);
            final Pop_ListAdapter adapter1 = new Pop_ListAdapter(getActivity(), stringList1);
            adapter1.setSelectItem(i1);
            pop_listView1.setAdapter(adapter1);
            pop_listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    adapter1.setSelectItem(i);
                    adapter1.notifyDataSetChanged();
                    i1 = i;
                    HashMap<String, String> params2 = new HashMap<>();
                    params2.put("y_parent_id", list_sv1.get(i).getYServiceId());
                    RequestService(params2, 1);

//                popupWindow.dismiss();
                }
            });

            // 由边列表
            ListView pop_listView2 = (ListView) contentView.findViewById(R.id.pop_listView2);
            adapter2 = new Pop_ListAdapter1(getActivity(), stringList2);
//            adapter2.setSelectItem(i2);
            pop_listView2.setAdapter(adapter2);
            pop_listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    if (!stringList2.get(i).isIsgouxuan())
                        stringList2.get(i).setIsgouxuan(true);
                    else stringList2.get(i).setIsgouxuan(false);
//                    adapter2.setSelectItem(i);
                    adapter2.notifyDataSetChanged();
//                popupWindow.dismiss();
                }
            });

            TextView pop_confirm = contentView.findViewById(R.id.pop_confirm);
            pop_confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    service_name = "";
                    for (GouXuanModel bean : stringList2) {
                        if (bean.isIsgouxuan()) {
                            service_name += bean.getTitle() + "||";
                        }
                    }
                    if (!service_name.equals("")) {
                        service_name = service_name.substring(0, service_name.length() - 2);
                        MyLogger.i(">>>>>>>>" + service_name);
                        popupWindow.dismiss();
                        requestServer();
                    } else {
//                        myToast("请选择具体的服务项目");
                        popupWindow.dismiss();
                        requestServer();
                    }
                }
            });

            popupWindow.setTouchable(true);
            popupWindow.setTouchInterceptor(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return false;
                    // 这里如果返回true的话，touch事件将被拦截
                    // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
                }
            });
        }

        if (!popupWindow.isShowing()) {
            ColorDrawable dw = new ColorDrawable(this.getResources().getColor(R.color.transparentblack2));
            // 设置弹出窗体的背景
            popupWindow.setBackgroundDrawable(dw);
            // 设置好参数之后再show
            popupWindow.showAsDropDown(v);
        } else {
            adapter2.setSelectItem(i2);
            adapter2.notifyDataSetChanged();
        }

    }
}
