package com.chetu.user.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bumptech.glide.Glide;
import com.chetu.user.R;
import com.chetu.user.base.BaseActivity;
import com.chetu.user.model.AddXunJiaModel;
import com.chetu.user.model.Fragment3Model;
import com.chetu.user.model.ServiceListModel_All;
import com.chetu.user.model.UpFileModel;
import com.chetu.user.net.URLs;
import com.chetu.user.okhttp.CallBackUtil;
import com.chetu.user.okhttp.OkhttpUtil;
import com.chetu.user.utils.CommonUtil;
import com.chetu.user.utils.MyLogger;
import com.chetu.user.view.pictureselector.FullyGridLayoutManager;
import com.chetu.user.view.pictureselector.GlideCacheEngine;
import com.chetu.user.view.pictureselector.GlideEngine;
import com.chetu.user.view.pictureselector.GridImageAdapter;
import com.cy.cyflowlayoutlibrary.FlowLayout;
import com.cy.cyflowlayoutlibrary.FlowLayoutAdapter;
import com.cy.dialog.BaseDialog;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.decoration.GridSpacingItemDecoration;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;
import com.luck.picture.lib.tools.ScreenUtils;
import com.luck.picture.lib.tools.SdkVersionUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.lang.ref.WeakReference;
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
 * Created by zyz on 2020/6/7.
 * 发布需求
 */
public class CarServiceActivity extends BaseActivity {
    int type = 1, isZiXuan = 0;
    //tab
    LinearLayout ll_tab1, ll_tab2;
    View view1, view2;
    //车辆信息
    String y_user_sedan_id = "";
    ImageView iv_carlogo;
    TextView tv_carname, tv_carnum, tv_cardetail;

    //添加询价项目
    JSONArray jsonArray = new JSONArray();
    RecyclerView recyclerView1;
    CommonAdapter<AddXunJiaModel> mAdapter1;
    List<AddXunJiaModel> list1 = new ArrayList<>();

    //发布询价
    String service_name = "", y_service_id_str = "", y_service_id_msg = "", y_store_id_str = "", v_list_str = "", v_msg = "";
    EditText et_qingkuang;
    TextView tv_upload2;
    /**
     * 服务内容
     */
    RecyclerView recyclerView_sv;
    CommonAdapter<ServiceListModel_All.ListBean> mAdapter_sv;
    List<ServiceListModel_All.ListBean> list_sv = new ArrayList<>();
    int i1 = 0, i2 = 0;

   /* //第一级
    List<ServiceListModel.ListBean> list_sv1 = new ArrayList<>();
    //    List<String> stringList1 = new ArrayList<>();
    //第二级
    List<ServiceListModel.ListBean> list_sv2 = new ArrayList<>();
    List<GouXuanModel> stringList2 = new ArrayList<>();*/


    /**
     * //选择店铺
     **/
    RecyclerView recyclerView2;
    List<Fragment3Model.ListBean> list2 = new ArrayList<>();
    CommonAdapter<Fragment3Model.ListBean> mAdapter2;
    FlowLayoutAdapter<Fragment3Model.ListBean.StoreServiceListBean> flowLayoutAdapter1;

    //发布救援
    EditText editText1, editText2, editText3, editText4, editText5;
    String y_store_id = "", full_name = "", telephone = "", address = "", m_type = "",
            car_condition = "", car_img = "", longitude = "", latitude = "";
    /**
     * 选择图片
     */
    RecyclerView rv_addimg;
    GridImageAdapter mAdapter;
    int maxSelectNum = 6;//选择最多图片数量
    int spanCount = 3;//一行显示张数
    ArrayList<File> listFiles = new ArrayList<>();

    //定位
    //声明AMapLocationClient类对象
    private AMapLocationClient mLocationClient = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carservice);
        mImmersionBar.reset()
                .statusBarColor(R.color.background)
                .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();

        /**
         * 选择图片
         */
        rv_addimg = findViewByID_My(R.id.rv_addimg);
        FullyGridLayoutManager manager = new FullyGridLayoutManager(this,
                spanCount, GridLayoutManager.VERTICAL, false);
        rv_addimg.setLayoutManager(manager);
        rv_addimg.addItemDecoration(new GridSpacingItemDecoration(spanCount,
                ScreenUtils.dip2px(this, 5), false));//中间值是设置间距
        mAdapter = new GridImageAdapter(CarServiceActivity.this, onAddPicClickListener);
        if (savedInstanceState != null && savedInstanceState.getParcelableArrayList("selectorList") != null) {
            mAdapter.setList(savedInstanceState.getParcelableArrayList("selectorList"));
        }
        mAdapter.setSelectMax(maxSelectNum);
        rv_addimg.setAdapter(mAdapter);
    }

    @Override
    protected void initView() {
        ll_tab1 = findViewByID_My(R.id.ll_tab1);
        ll_tab2 = findViewByID_My(R.id.ll_tab2);
        view1 = findViewByID_My(R.id.view1);
        view2 = findViewByID_My(R.id.view2);
        //服务列表
        recyclerView_sv = findViewByID_My(R.id.recyclerView_sv);
        recyclerView_sv.setLayoutManager(new LinearLayoutManager(this));
        //询价项目
        recyclerView1 = findViewByID_My(R.id.recyclerView1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));

        //车辆信息
        iv_carlogo = findViewByID_My(R.id.iv_carlogo);
        tv_carname = findViewByID_My(R.id.tv_carname);
        tv_carnum = findViewByID_My(R.id.tv_carnum);
        tv_cardetail = findViewByID_My(R.id.tv_cardetail);

        if (!localUserInfo.getCarname().equals("")) {
            y_user_sedan_id = localUserInfo.getCarid();
            tv_carname.setText(localUserInfo.getCarname());
            tv_carnum.setText(localUserInfo.getCarnum());
            tv_cardetail.setText(localUserInfo.getCardetail());
            Glide.with(this).load(URLs.IMGHOST + localUserInfo.getCarlogo())
                    .centerCrop()
                    .into(iv_carlogo);//加载图片
        }

        et_qingkuang = findViewByID_My(R.id.et_qingkuang);

        recyclerView2 = findViewByID_My(R.id.recyclerView2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(CarServiceActivity.this));

        tv_upload2 = findViewByID_My(R.id.tv_upload2);
        //发布救援
        editText1 = findViewByID_My(R.id.editText1);
        editText1.setText(localUserInfo.getNickname());
        editText2 = findViewByID_My(R.id.editText2);
        editText2.setText(localUserInfo.getPhonenumber());
        editText3 = findViewByID_My(R.id.editText3);
        editText4 = findViewByID_My(R.id.editText4);
        editText5 = findViewByID_My(R.id.editText5);
    }

    @Override
    protected void initData() {
        requestServer();
        /*HashMap<String, String> params2 = new HashMap<>();
        params2.put("i_cy", "2");//1为普通保险 2为交强险
        RequestBaoXian(params2, 2);

        HashMap<String, String> params1 = new HashMap<>();
        params1.put("i_cy", "1");//1为普通保险 2为交强险
        RequestBaoXian(params1, 1);*/
        //初始化定位
        mLocationClient = new AMapLocationClient(this);
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

                        localUserInfo.setCityname(aMapLocation.getCity());
                        localUserInfo.setLongitude(aMapLocation.getLongitude() + "");
                        localUserInfo.setLatitude(aMapLocation.getLatitude() + "");

                        editText3.setText(aMapLocation.getAddress() + "");

                        longitude = aMapLocation.getLatitude() + "";
                        latitude = aMapLocation.getLatitude() + "";


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
        mLocationClient.startLocation();
//        }
    }

    @Override
    public void requestServer() {
        super.requestServer();
//        this.showLoadingPage();
        showProgress(true, getString(R.string.app_loading));
        //获取服务项目
        HashMap<String, String> params2 = new HashMap<>();
        params2.put("parent_id", "0");
        params2.put("keys", "");
        RequestService(params2, 0);

        //获取商家列表
        Map<String, String> params = new HashMap<>();
        params.put("service_name", "");
        params.put("page", "0");
        params.put("longitude", "");
        params.put("latitude", "");
        params.put("is_review", "1");
        params.put("is_index", "1");
        RequestList(params);
    }

    /**
     * 获取筛选列表
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
                        (CarServiceActivity.this, R.layout.item_carservice_sv, list_sv) {
                    @Override
                    protected void convert(ViewHolder holder, ServiceListModel_All.ListBean model, int position) {
                        holder.setText(R.id.title, model.getVName());

                        RecyclerView rv = holder.getView(R.id.rv);
                        rv.setLayoutManager(new LinearLayoutManager(CarServiceActivity.this));
                        ImageView iv = holder.getView(R.id.iv);

                        if (i1 == position) {
                            iv.setImageResource(R.mipmap.ic_down_black);
                            if (list_sv.size() > 0) {
                                rv.setVisibility(View.VISIBLE);
                            }


                            CommonAdapter<ServiceListModel_All.ListBean.VListBeanXX> ca = new CommonAdapter<ServiceListModel_All.ListBean.VListBeanXX>
                                    (CarServiceActivity.this, R.layout.item_carservice_sv_child, model.getV_list()) {
                                @Override
                                protected void convert(ViewHolder holder, ServiceListModel_All.ListBean.VListBeanXX listBean, int item) {
                                    //        强行关闭复用
                                    holder.setIsRecyclable(false);

                                    holder.setText(R.id.textView, listBean.getVName());

                                    EditText et_content = holder.getView(R.id.et_content);
                                    et_content.setText(listBean.getContent());

                                    ImageView imageView = holder.getView(R.id.imageView);
                                    if (listBean.isIsgouxuan()) {
                                        imageView.setImageResource(R.mipmap.ic_xuanzhong);
                                        et_content.setVisibility(View.VISIBLE);
                                    } else {
                                        imageView.setImageResource(R.mipmap.ic_weixuan);
                                        et_content.setVisibility(View.GONE);
                                    }

                                    //提交
                                    /*et_content.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                                        @Override
                                        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                                            if (actionId == EditorInfo.IME_ACTION_DONE) {
                                                if (!et_content.getText().toString().trim().equals("")) {
                                                    listBean.setContent(et_content.getText().toString().trim());
                                                } else {
                                                    myToast("请输入项目说明");
                                                }
                                            }
                                            return true;
                                        }
                                    });*/
                                    et_content.addTextChangedListener(new TextWatcher() {
                                        @Override
                                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                                        }

                                        @Override
                                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                                        }

                                        @Override
                                        public void afterTextChanged(Editable editable) {
                                            if (!editable.toString().trim().equals("")) {
                                                listBean.setContent(editable.toString().trim());
                                                MyLogger.i(">>>>>>>" + listBean.getContent());
                                            }
                                        }
                                    });

                                }
                            };
                            ca.setOnItemClickListener(new OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                    if (!model.getV_list().get(i).isIsgouxuan())
                                        model.getV_list().get(i).setIsgouxuan(true);
                                    else model.getV_list().get(i).setIsgouxuan(false);

                                    ca.notifyDataSetChanged();


                                    //选择的服务
                                    service_name = "";
                                    for (ServiceListModel_All.ListBean listBean : list_sv) {
                                        for (ServiceListModel_All.ListBean.VListBeanXX vListBean : listBean.getV_list()) {
                                            if (vListBean.isIsgouxuan()) {
                                                service_name += vListBean.getVName() + "||";
                                            }
                                        }
                                    }
                                    if (!service_name.equals("")) {
                                        service_name = service_name.substring(0, service_name.length() - 2);
                                        MyLogger.i(">>>>>>>>服务名称：" + service_name);
                                    }
                                    showProgress(true, "正在匹配门店，请稍候...");
                                    //匹配店铺列表
                                    Map<String, String> params = new HashMap<>();
                                    params.put("service_name", service_name);
                                    params.put("page", "0");
                                    params.put("longitude", "");
                                    params.put("latitude", "");
                                    params.put("is_review", "1");
                                    params.put("is_index", "1");
                                    RequestList(params);
                                }

                                @Override
                                public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                    return false;
                                }
                            });
                            rv.setAdapter(ca);
                        } else {
                            iv.setImageResource(R.mipmap.ic_next_black);
                            rv.setVisibility(View.GONE);
                        }
                    }
                };
                mAdapter_sv.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        if (i != i1) {
                            i1 = i;
                            mAdapter_sv.notifyDataSetChanged();
                        }else {
                            i1 = -1;
                            mAdapter_sv.notifyDataSetChanged();
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

    /**
     * 获取商家列表
     *
     * @param params
     */
    private void RequestList(Map<String, String> params) {
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
                list2 = response.getList();
                if (list2.size() > 0) {
                    showContentPage();
                    mAdapter2 = new CommonAdapter<Fragment3Model.ListBean>
                            (CarServiceActivity.this, R.layout.item_carservice, list2) {
                        @Override
                        protected void convert(ViewHolder holder, Fragment3Model.ListBean model, int position) {
                            ImageView imageView1 = holder.getView(R.id.imageView1);
                            Glide.with(CarServiceActivity.this)
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

                            FlowLayout flowLayout1 = holder.getView(R.id.flowLayout1);
                            if (model.getStore_service_list().size() > 0) {
                                flowLayout1.setVisibility(View.VISIBLE);
                                //标签
                                flowLayoutAdapter1 = new FlowLayoutAdapter<Fragment3Model.ListBean.StoreServiceListBean>
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
                                flowLayout1.setAdapter(flowLayoutAdapter1);
                            } else {
                                flowLayout1.setVisibility(View.GONE);
                            }

                            LinearLayout ll = holder.getView(R.id.linearLayout);
                            if (model.isIsgouxuan()) {
                                ll.setBackgroundResource(R.drawable.yuanjiaobiankuang_5_lanse_2);
                            } else {
                                ll.setBackgroundResource(R.drawable.yuanjiao_5_baise);
                            }

                        }
                    };

                    mAdapter2.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            if (!list2.get(i).isIsgouxuan()) {
                                list2.get(i).setIsgouxuan(true);
                                int num = 0;
                                for (Fragment3Model.ListBean listBean : list2) {
                                    if (listBean.isIsgouxuan()) {
                                        num++;
                                    }
                                }
                                if (num>0){
                                    tv_upload2.setText("发布");
                                }else {
                                    tv_upload2.setText("选择门店");
                                }

                                if (num > 3) {
                                    list2.get(i).setIsgouxuan(false);
                                    num--;
                                    myToast("抱歉，您最多只能选择3家门店");
                                }

                            } else {
                                list2.get(i).setIsgouxuan(false);
                            }
                            mAdapter2.notifyDataSetChanged();

                        }

                        @Override
                        public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            return false;
                        }
                    });
                    recyclerView2.setAdapter(mAdapter2);
                } else {
                    showEmptyPage();
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.ll_car:
                //选择车辆
                Intent intent1 = new Intent(CarServiceActivity.this, MyGarageActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putInt("type", 10001);
                intent1.putExtras(bundle1);
                startActivityForResult(intent1, 10001, bundle1);
                break;
            case R.id.editText3:
                //选择地址
                Intent intent3 = new Intent(CarServiceActivity.this, SelectAddressActivity.class);
                Bundle bundle3 = new Bundle();
                bundle3.putInt("type", 10003);
                intent3.putExtras(bundle3);
                startActivityForResult(intent3, 10003, bundle3);
                break;
            case R.id.editText4:
                //类型
                List<String> arrayList = new ArrayList<>();
                arrayList.add("搭电");
                arrayList.add("换胎");
                arrayList.add("拖车");
                arrayList.add("送油");
                arrayList.add("故障");
                BaseDialog dialog1 = new BaseDialog(CarServiceActivity.this);
                dialog1.contentView(R.layout.dialog_list)
                        .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT))
                        .animType(BaseDialog.AnimInType.BOTTOM)
                        .canceledOnTouchOutside(true)
                        .gravity(Gravity.BOTTOM)
                        .dimAmount(0.7f)
                        .show();
                TextView title = dialog1.findViewById(R.id.textView1);
                title.setText("请选择救援类型");
                RecyclerView rv = dialog1.findViewById(R.id.recyclerView);
                rv.setLayoutManager(new LinearLayoutManager(CarServiceActivity.this));

                CommonAdapter<String> adapter = new CommonAdapter<String>
                        (CarServiceActivity.this, R.layout.item_dialog_list, arrayList) {
                    @Override
                    protected void convert(ViewHolder holder, String model, int position) {
                        TextView tv = holder.getView(R.id.textView);
                        ImageView iv = holder.getView(R.id.imageView);
                        tv.setText(model);
                        if (position == i2) {
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
                        i2 = i;
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
                        editText4.setText(arrayList.get(i2));
                        dialog1.dismiss();
                    }
                });
                break;
            case R.id.tv_add:
                //添加询价项目
                Intent intent2 = new Intent(CarServiceActivity.this, AddXunJiaActivity.class);
                Bundle bundle2 = new Bundle();
                bundle2.putInt("type", 10002);
                intent2.putExtras(bundle2);
                startActivityForResult(intent2, 10002, bundle2);
                break;
            case R.id.linearLayout1:
                //发布询价
                type = 1;
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.INVISIBLE);
                ll_tab1.setVisibility(View.VISIBLE);
                ll_tab2.setVisibility(View.GONE);
                break;
            case R.id.linearLayout2:
                //发布救援
                type = 2;
                view1.setVisibility(View.INVISIBLE);
                view2.setVisibility(View.VISIBLE);
                ll_tab1.setVisibility(View.GONE);
                ll_tab2.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_baocun:
                //保存
                //选择的服务
                service_name = "";
                y_service_id_str="";
                y_service_id_msg = "";
                v_msg = et_qingkuang.getText().toString().trim();
                for (ServiceListModel_All.ListBean listBean : list_sv) {
                    for (ServiceListModel_All.ListBean.VListBeanXX vListBean : listBean.getV_list()) {
                        if (vListBean.isIsgouxuan()) {
                            service_name += vListBean.getVName() + "||";
                            y_service_id_str += vListBean.getYServiceId() + ",";
                            if (!vListBean.getContent().equals("")) {
                                y_service_id_msg += vListBean.getContent() + "||";
                            } else {
                                y_service_id_msg += "#" + "||";
                            }
                        }
                    }
                }
                if (!service_name.equals("")) {
                    service_name = service_name.substring(0, service_name.length() - 2);
                    MyLogger.i(">>>>>>>>服务名称：" + service_name);
                    y_service_id_str = y_service_id_str.substring(0, y_service_id_str.length() - 1);
                    MyLogger.i(">>>>>>>>服务ID：" + y_service_id_str);

                    y_service_id_msg = y_service_id_msg.substring(0, y_service_id_msg.length() - 2);
                    MyLogger.i(">>>>>>>>服务ID描述：" + y_service_id_msg);
                }
                //选择的门店
                for (Fragment3Model.ListBean listBean : list2) {
                    if (listBean.isIsgouxuan()) {
                        y_store_id_str = listBean.getYStoreId() + ",";
                    }
                }
                if (!y_store_id_str.equals("")) {
                    y_store_id_str = y_store_id_str.substring(0, y_store_id_str.length() - 1);
                    MyLogger.i(">>>>>>>>店铺ID：" + y_store_id_str);
                }
                //添加项目
                v_list_str = jsonArray.toString();

                //保存
                if (!y_user_sedan_id.equals("") && !service_name.equals("") && !v_list_str.equals("")) {
                    showToast("是否保存到待发布？", "是", "否", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            showProgress(true, getString(R.string.app_loading1));
                            Map<String, String> params = new HashMap<>();
                            params.put("u_token", localUserInfo.getToken());
                            params.put("y_user_sedan_id", y_user_sedan_id);
                            params.put("service_name", service_name);
                            params.put("y_service_id_str", y_service_id_str);
                            params.put("y_service_id_msg", y_service_id_msg);
                            params.put("y_store_id_str", y_store_id_str);
                            params.put("v_list_str", v_list_str);
                            params.put("is_ok", "0");//1是发布 0保存
                            params.put("v_msg", v_msg);
                            RequestUpData1(params, 0);
                        }
                    }, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            finish();
                        }
                    });
                } else {
                    myToast("请选择了服务或项目才可以保存");
                }
                break;
            case R.id.tv_upload1:
                //一键发布
                isZiXuan = 1;
                upData();
                break;
            case R.id.tv_upload2:
                //选择门店
                isZiXuan = 0;
                upData();
                break;
        }
    }

    /**
     * 提交数据
     */
    private void upData() {
        if (type == 1) {
            //发布询价
            if (match()) {
                showProgress(true, getString(R.string.app_loading1));
                Map<String, String> params = new HashMap<>();
                params.put("u_token", localUserInfo.getToken());
                params.put("y_user_sedan_id", y_user_sedan_id);
                params.put("service_name", service_name);
                params.put("y_service_id_str", y_service_id_str);
                params.put("y_service_id_msg", y_service_id_msg);
                params.put("y_store_id_str", y_store_id_str);
                params.put("v_list_str", v_list_str);
                params.put("is_ok", "1");//1是发布 0保存
                params.put("v_msg", v_msg);
                RequestUpData1(params, 1);
            }
        } else {
            //发布救援
            if (match1()) {
                showProgress(true, getString(R.string.app_loading1));
                if (listFiles.size() > 0) {
                    Map<String, String> params = new HashMap<>();
                    params.put("sn", "773EDB6D2715FACF9C93354CAC5B1A3372872DC4D5AC085867C7490E9984D33E");
                    RequestUpFile(params, listFiles, "picture");
                } else {
                    Map<String, String> params = new HashMap<>();
                    params.put("u_token", localUserInfo.getToken());
                    params.put("y_store_id", y_store_id);
                    params.put("y_user_sedan_id", y_user_sedan_id);
                    params.put("full_name", full_name);
                    params.put("telephone", telephone);
                    params.put("address", address);
                    params.put("m_type", m_type);
                    params.put("car_condition", car_condition);
                    params.put("car_img", car_img);
                    params.put("longitude", longitude);
                    params.put("latitude", latitude);
                    RequestUpData2(params);
                }
            }
        }
    }

    private boolean match() {
        if (y_user_sedan_id.equals("")) {
            myToast("请选择车辆");
            return false;
        }
        //选择的服务
        service_name = "";
        y_service_id_str="";
        y_service_id_msg = "";
        for (ServiceListModel_All.ListBean listBean : list_sv) {
            for (ServiceListModel_All.ListBean.VListBeanXX vListBean : listBean.getV_list()) {
                if (vListBean.isIsgouxuan()) {
                    service_name += vListBean.getVName() + "||";
                    y_service_id_str += vListBean.getYServiceId() + ",";

                    if (!vListBean.getContent().equals("")) {
                        y_service_id_msg += vListBean.getContent() + "||";
                    } else {
                        y_service_id_msg += "#" + "||";
                    }

                }
            }
        }
        if (!service_name.equals("")) {
            service_name = service_name.substring(0, service_name.length() - 2);
            MyLogger.i(">>>>>>>>服务名称：" + service_name);
            y_service_id_str = y_service_id_str.substring(0, y_service_id_str.length() - 1);
            MyLogger.i(">>>>>>>>服务ID：" + y_service_id_str);

            y_service_id_msg = y_service_id_msg.substring(0, y_service_id_msg.length() - 2);
            MyLogger.i(">>>>>>>>服务ID描述：" + y_service_id_msg);

        } else {
            myToast("请选择服务");
            return false;
        }

        //选择的门店
        if (isZiXuan != 1) {
            for (Fragment3Model.ListBean listBean : list2) {
                if (listBean.isIsgouxuan()) {
                    y_store_id_str += listBean.getYStoreId() + ",";
                }
            }
            if (!y_store_id_str.equals("")) {
                y_store_id_str = y_store_id_str.substring(0, y_store_id_str.length() - 1);
                MyLogger.i(">>>>>>>>店铺ID：" + y_store_id_str);
            } else {
                myToast("请选择门店");
                return false;
            }
        } else {
            y_store_id_str = "";
        }

        //添加项目
        v_list_str = jsonArray.toString();
        /*if (v_list_str.equals("")){
            myToast("请添加项目");
        }*/

        v_msg = et_qingkuang.getText().toString().trim();
        /*if (TextUtils.isEmpty(v_msg)) {
            myToast("请输入情况说明");
            return false;
        }*/
        return true;
    }

    private boolean match1() {
        //选择的门店
        if (isZiXuan != 1) {
            for (Fragment3Model.ListBean listBean : list2) {
                if (listBean.isIsgouxuan()) {
                    y_store_id += listBean.getYStoreId() + ",";
                }
            }
            if (!y_store_id.equals("")) {
                y_store_id = y_store_id.substring(0, y_store_id.length() - 1);
                MyLogger.i(">>>>>>>>店铺ID：" + y_store_id);
            } else {
                myToast("请选择门店");
                return false;
            }
        } else {
            y_store_id = "";
        }

        if (y_user_sedan_id.equals("")) {
            myToast("请选择车辆");
            return false;
        }
        full_name = editText1.getText().toString().trim();
        if (TextUtils.isEmpty(full_name)) {
            myToast("请输入姓名");
            return false;
        }
        telephone = editText2.getText().toString().trim();
        if (TextUtils.isEmpty(telephone)) {
            myToast("请输入电话");
            return false;
        }
        address = editText3.getText().toString().trim();
        if (TextUtils.isEmpty(address)) {
            myToast("请输入地址");
            return false;
        }
        /*if (TextUtils.isEmpty(latitude)) {
            myToast("请打开定位");
            return false;
        }*/
        m_type = editText4.getText().toString().trim();
        if (TextUtils.isEmpty(m_type)) {
            myToast("请选择救援类型");
            return false;
        }
        car_condition = editText5.getText().toString().trim();
        /*if (TextUtils.isEmpty(car_condition)) {
            myToast("请输入您当前的情况");
            return false;
        }*/

        return true;
    }

    @Override
    protected void updateView() {
        titleView.setTitle("发布");
        titleView.setBackground(R.color.background);
        titleView.setLeftBtn(R.mipmap.ic_return_black, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //选择的服务
                service_name = "";
                y_service_id_str="";
                y_service_id_msg = "";
                v_msg = et_qingkuang.getText().toString().trim();
                for (ServiceListModel_All.ListBean listBean : list_sv) {
                    for (ServiceListModel_All.ListBean.VListBeanXX vListBean : listBean.getV_list()) {
                        if (vListBean.isIsgouxuan()) {
                            service_name += vListBean.getVName() + "||";
                            y_service_id_str += vListBean.getYServiceId() + ",";
                            if (!vListBean.getContent().equals("")) {
                                y_service_id_msg += vListBean.getContent() + "||";
                            } else {
                                y_service_id_msg += "#" + "||";
                            }
                        }
                    }
                }
                if (!service_name.equals("")) {
                    service_name = service_name.substring(0, service_name.length() - 2);
                    MyLogger.i(">>>>>>>>服务名称：" + service_name);
                    y_service_id_str = y_service_id_str.substring(0, y_service_id_str.length() - 1);
                    MyLogger.i(">>>>>>>>服务ID：" + y_service_id_str);

                    y_service_id_msg = y_service_id_msg.substring(0, y_service_id_msg.length() - 2);
                    MyLogger.i(">>>>>>>>服务ID描述：" + y_service_id_msg);
                }
                //选择的门店
                for (Fragment3Model.ListBean listBean : list2) {
                    if (listBean.isIsgouxuan()) {
                        y_store_id_str = listBean.getYStoreId() + ",";
                    }
                }
                if (!y_store_id_str.equals("")) {
                    y_store_id_str = y_store_id_str.substring(0, y_store_id_str.length() - 1);
                    MyLogger.i(">>>>>>>>店铺ID：" + y_store_id_str);
                }
                //添加项目
                v_list_str = jsonArray.toString();

                //保存
                if (!y_user_sedan_id.equals("") && !service_name.equals("")&& !v_list_str.equals("")) {
                    showToast("是否保存到待发布？", "是", "否", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            showProgress(true, getString(R.string.app_loading1));
                            Map<String, String> params = new HashMap<>();
                            params.put("u_token", localUserInfo.getToken());
                            params.put("y_user_sedan_id", y_user_sedan_id);
                            params.put("service_name", service_name);
                            params.put("y_service_id_str", y_service_id_str);
                            params.put("y_service_id_msg", y_service_id_msg);
                            params.put("y_store_id_str", y_store_id_str);
                            params.put("v_list_str", v_list_str);
                            params.put("is_ok", "0");//1是发布 0保存
                            params.put("v_msg", v_msg);
                            RequestUpData1(params, 0);
                        }
                    }, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            finish();
                        }
                    });
                } else {
                    finish();
                }
            }
        });

        titleView.showRightTextview("询价记录", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtil.gotoActivity(CarServiceActivity.this, WaitingReleaseActivity.class);
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
                    tv_carname.setText(bundle1.getString("carname"));
                    tv_carnum.setText(bundle1.getString("carnum"));
                    tv_cardetail.setText(bundle1.getString("cardetail"));
                    Glide.with(CarServiceActivity.this).load(URLs.IMGHOST + bundle1.getString("carlogo"))
                            .centerCrop()
                            .into(iv_carlogo);//加载图片
                }
                break;
            case 10002:
                //添加询价项目
                if (data != null) {
                    Bundle bundle2 = data.getExtras();
                    try {
                        JSONObject object1 = new JSONObject();
                        object1.put("v_title", bundle2.getString("v_title"));
                        object1.put("imgStr", bundle2.getString("imgStr"));
                        jsonArray.put(object1);
                        list1 = JSON.parseArray(jsonArray.toString(), AddXunJiaModel.class);

                        showXunJia();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                break;
            case 10003:
                //选择地址
                if (data != null) {
                    Bundle bundle3 = data.getExtras();
//                    editText3 = bundle3.getString("addr");
                    editText3.setText(bundle3.getString("addr"));
                   /* tv_carnum.setText(bundle1.getString("carnum"));
                    tv_cardetail.setText(bundle1.getString("cardetail"));*/
                }
                break;
           /* case PictureConfig.REQUEST_CAMERA:
                // onResult Callback
                List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                listFiles.clear();
                for (LocalMedia media : selectList) {
                    MyLogger.i(">>>>>>压缩地址：" + media.getCompressPath());
                    File file = new File(media.getCompressPath());
                    listFiles.add(file);
                    // TODO 可以通过PictureSelectorExternalUtils.getExifInterface();方法获取一些额外的资源信息，如旋转角度、经纬度等信息

                }
                break;*/

        }

    }

    /**
     * 展示询价项目
     */
    private void showXunJia() {
        mAdapter1 = new CommonAdapter<AddXunJiaModel>
                (CarServiceActivity.this, R.layout.item_xunjia, list1) {
            @Override
            protected void convert(ViewHolder holder, AddXunJiaModel model, int item) {
                holder.setText(R.id.tv_title, model.getV_title());
                String[] strArr = model.getImgStr().split("\\|\\|");
                List<String> imgs = new ArrayList<>();
                for (String s : strArr) {
                    imgs.add(s);
                }
                RecyclerView rv = holder.getView(R.id.rv);
                rv.setLayoutManager(new GridLayoutManager(CarServiceActivity.this, 3));
                CommonAdapter<String> ca = new CommonAdapter<String>
                        (CarServiceActivity.this, R.layout.item_img_110_110, imgs) {
                    @Override
                    protected void convert(ViewHolder holder, String s, int item) {
                        ImageView iv = holder.getView(R.id.iv);
                        Glide.with(CarServiceActivity.this).load(URLs.IMGHOST + s)
//                            .centerCrop()
//                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                                .placeholder(R.mipmap.loading)//加载站位图
                                .error(R.mipmap.zanwutupian)//加载失败
                                .into(iv);//加载图片
                    }
                };
                rv.setAdapter(ca);

                //删除
                holder.getView(R.id.tv_delete).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showToast("确认删除该项目吗？", "确认", "取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                list1.remove(item);
                                jsonArray.remove(item);
                                mAdapter1.notifyDataSetChanged();
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
        mAdapter1.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                /*Intent intent2 = new Intent(CarServiceActivity.this, AddXunJiaActivity.class);
                Bundle bundle2 = new Bundle();
                bundle2.putInt("type", 10002);
                bundle2.putString("v_title", list1.get(i).getV_title());
                bundle2.putString("imgStr", list1.get(i).getImgStr());
                intent2.putExtras(bundle2);
                startActivityForResult(intent2, 10002, bundle2);*/
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                return false;
            }
        });
        recyclerView1.setAdapter(mAdapter1);
    }

    /**
     * 发布询价
     *
     * @param params
     */
    private void RequestUpData1(Map<String, String> params, int save) {
        OkhttpUtil.okHttpPost(URLs.XunJia_Add, params, headerMap, new CallBackUtil<Object>() {
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
                if (save == 0) {
                    //保存
                    showToast("保存成功\n您可在【我的】-【我的发布】进行发布", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            finish();
                        }
                    });
                } else {
                    showToast("发布成功,请耐心等待结果\n您可在【我的】-【我的发布】查看报价", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            finish();
                        }
                    });
                }

            }
        });
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
//                hideProgress();
                car_img = "";
                for (String s : response.getList()) {
                    car_img += s + "||";
                }
                if (!car_img.equals("")) {
                    car_img = car_img.substring(0, car_img.length() - 2);
                }
                Map<String, String> params = new HashMap<>();
                params.put("u_token", localUserInfo.getToken());
                params.put("y_store_id", y_store_id);
                params.put("y_user_sedan_id", y_user_sedan_id);
                params.put("full_name", full_name);
                params.put("telephone", telephone);
                params.put("address", address);
                params.put("m_type", m_type);
                params.put("car_condition", car_condition);
                params.put("car_img", car_img);
                RequestUpData2(params);
            }
        });
    }

    /**
     * 车辆救援（发布）
     *
     * @param params
     */
    private void RequestUpData2(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.CarJiuYuan, params, headerMap, new CallBackUtil<Object>() {
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
                showToast("救援发布成功,请耐心等待", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        finish();
                    }
                });
            }
        });
    }

    /**
     * *****************************************选择图片********************************************
     */
    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            // 进入相册
            PictureSelector.create(CarServiceActivity.this)
//                    .openCamera(PictureMimeType.ofImage())//打开相机
                    .openGallery(PictureMimeType.ofImage())//进入相册 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
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
                    .selectionData(mAdapter.getData())// 是否传入已选图片
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
                    .forResult(new MyResultCallback(mAdapter));
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
            listFiles.clear();
            for (LocalMedia media : result) {
                MyLogger.i(">>>>>>压缩地址：" + media.getCompressPath());
                File file = new File(media.getCompressPath());
                listFiles.add(file);
                // TODO 可以通过PictureSelectorExternalUtils.getExifInterface();方法获取一些额外的资源信息，如旋转角度、经纬度等信息

            }
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
