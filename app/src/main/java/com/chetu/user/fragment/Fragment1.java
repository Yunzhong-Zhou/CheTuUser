package com.chetu.user.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chetu.user.R;
import com.chetu.user.activity.MainActivity;
import com.chetu.user.base.BaseFragment;
import com.chetu.user.model.Fragment1Model;
import com.chetu.user.net.OkHttpClientManager;
import com.chetu.user.net.URLs;
import com.chetu.user.utils.MyLogger;
import com.chetu.user.view.zxing.CaptureActivity;
import com.chetu.user.view.zxing.Constant;
import com.squareup.okhttp.Request;

import java.util.Map;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;



/**
 * Created by fafukeji01 on 2016/1/6.
 * 首页
 */

public class Fragment1 extends BaseFragment {
    /*ImageView imageView1;
    TextView textView1, textView2, textView3, textView4, textView5, textView6;

    String indent_use_type = "", distance = "", temperature = "", time_start = "", time_end = "",
            addr = "";
    double lat = 0, lng = 0, juli = 0;
    long time = 0;
    Fragment1Model model;
    private RecyclerView recyclerView;
    LinearLayoutManager mLinearLayoutManager;
    List<Fragment1ListModel> list = new ArrayList<>();
    CommonAdapter<Fragment1ListModel> mAdapter;

    RollingView rollingView;//消息滚动
    List<String> xiaoxiArray = new ArrayList<>();

    ImageView btn_right;
    LinearLayout ll_xiaoxi, ll_pingfen;
    TextView tv_zijintongji, tv_type, tv_distance, tv_temperature, tv_timestart, tv_timestop,
            tv_kaishijiedan, tv_hint,tv_chongzhi;
    int i1 = 0, i2 = 0, i3 = 0, i4 = 0, i5 = 0;

    Boolean isStartJieDan = false;//是否开始接单
    //定位
    //声明AMapLocationClient类对象
    private AMapLocationClient mLocationClient = null;
    TimeCount time1 = null;

    private DPoint mStartPoint = null;
    private DPoint mEndPoint = null;

    Handler startTimehandler = new Handler() {
        public void handleMessage(Message msg) {
            textView4.setText("" + (String) msg.obj);
        }
    };

    TimerTask timerTask = null;*/

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
        if (MainActivity.item == 0) {
            requestServer();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected void initView(View view) {
        /*setSpringViewMore(false);//需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                String string = "?token=" + localUserInfo.getToken();
                Request(string);
            }

            @Override
            public void onLoadmore() {

            }
        });
        recyclerView = findViewByID_My(R.id.recyclerView);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLinearLayoutManager);

        //公告消息
        rollingView = findViewByID_My(R.id.rollingView);

        btn_right = findViewByID_My(R.id.btn_right);
        btn_right.setOnClickListener(this);
        ll_xiaoxi = findViewByID_My(R.id.ll_xiaoxi);
        ll_xiaoxi.setOnClickListener(this);
        tv_zijintongji = findViewByID_My(R.id.tv_zijintongji);
        tv_zijintongji.setOnClickListener(this);
        ll_pingfen = findViewByID_My(R.id.ll_pingfen);
        ll_pingfen.setOnClickListener(this);

        tv_type = findViewByID_My(R.id.tv_type);
        tv_type.setOnClickListener(this);
        tv_distance = findViewByID_My(R.id.tv_distance);
        tv_distance.setOnClickListener(this);
        tv_temperature = findViewByID_My(R.id.tv_temperature);
        tv_temperature.setOnClickListener(this);
        tv_timestart = findViewByID_My(R.id.tv_timestart);
        tv_timestart.setOnClickListener(this);
        tv_timestop = findViewByID_My(R.id.tv_timestop);
        tv_timestop.setOnClickListener(this);
        tv_kaishijiedan = findViewByID_My(R.id.tv_kaishijiedan);
        tv_kaishijiedan.setOnClickListener(this);
        tv_hint = findViewByID_My(R.id.tv_hint);
        tv_chongzhi = findViewByID_My(R.id.tv_chongzhi);
        tv_chongzhi.setOnClickListener(this);

        imageView1 = findViewByID_My(R.id.imageView1);
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
        textView4 = findViewByID_My(R.id.textView4);
        textView5 = findViewByID_My(R.id.textView5);
        textView6 = findViewByID_My(R.id.textView6);*/

    }

    @Override
    protected void initData() {
//        requestServer();
    }

    private void Request(String string) {
        OkHttpClientManager.getAsyn(getActivity(), URLs.Fragment1 + string, new OkHttpClientManager.ResultCallback<Fragment1Model>() {
            @Override
            public void onError(Request request, String info, Exception e) {
//                showErrorPage();
                hideProgress();
                if (!info.equals("")) {
                    myToast(info);
                }
            }

            @Override
            public void onResponse(Fragment1Model response) {
                showContentPage();
                hideProgress();
                MyLogger.i(">>>>>>>>>首页" + response);
                /*//保存是否认证
                localUserInfo.setIsVerified(response.getIs_certification()+"");//1 认证 2 未认证

                model = response;
                textView1.setText(response.getNickname());//昵称
                textView2.setText("¥ " + response.getToday_money());//今日流水
                textView3.setText(response.getMoney());//账户余额
//                textView4.setText(response.getOnline_time());//在线时长
                time = response.getOnline_time() * 1000;

                if (timerTask == null) {
                    timerTask = new TimerTask() {
                        @Override
                        public void run() {
                            time = time + 1000;
//                        String dd = new DecimalFormat("00").format(time / 3600 / 24);
//                        String hh = new DecimalFormat("00").format(time / 3600);
//                        String mm = new DecimalFormat("00").format(time % 3600 / 60);
//                        String ss = new DecimalFormat("00").format(time % 60);
//                        String timeFormat = new String(hh + "小时" + mm + "分" + ss + "秒");
                            String timeFormat = CommonUtil.timedate5(time);//格式化时间

                            Message msg = new Message();
                            msg.obj = timeFormat;
                            startTimehandler.sendMessage(msg);
                        }

                    };
                    new Timer("在线计时器").scheduleAtFixedRate(timerTask, 0, 1000L);
                }

                textView5.setText(response.getIndent_count());//今日单量
                textView6.setText(response.getComment_score());//当前评分
                if (!response.getHead().equals(""))
                    Glide.with(getActivity())
                            .load(IMGHOST + response.getHead())
                            .centerCrop()
//                    .placeholder(R.mipmap.headimg)//加载站位图
//                    .error(R.mipmap.headimg)//加载失败
                            .into(imageView1);//加载图片

                //倒计时
                if (response.getFresh_second() > 0) {
                    if (isStartJieDan)
                        startTime();//开始倒计时
                }

                // 公告消息
                if (response.getNotice_list().size() > 0) {
                    ll_xiaoxi.setVisibility(View.VISIBLE);
                    xiaoxiArray.clear();
                    for (int i = 0; i < response.getNotice_list().size(); i++) {
                        xiaoxiArray.add(response.getNotice_list().get(i).getTitle());
                    }
                    rollingView.setPageSize(1);
                    rollingView.setClickColor(0xff888888);
//        rollingView.setLeftDrawable(R.drawable.drawable_red_dot);
                    rollingView.setRollingText(xiaoxiArray);// 绑定数据
                    rollingView.setOnItemClickListener(new RollingView.onItemClickListener() {
                        @Override
                        public void onItemClick(TextView v) {
//                        MyLogger.i(">>>>"+v.getText());
                            CommonUtil.gotoActivity(getActivity(), NoticeListActivity.class);
                        }
                    });
                } else {
                    ll_xiaoxi.setVisibility(View.GONE);
                }

                //订单类型
                indent_use_type = response.getIndent_use_type_list().get(0).getKey() + "";
                //距离
                distance = response.getDistance_list().get(0).getKey() + "";
                //温度
                temperature = response.getTemperature_list().get(0).getKey() + "";
                //开始
                time_start = response.getTime_start_list().get(0).getKey() + "";
                //结束
                time_end = response.getTime_end_list().get(0).getKey() + "";*/
            }
        });
    }

    private void RequestList(Map<String, String> params) {
        OkHttpClientManager.postAsyn(getActivity(), URLs.Fragment1List, params, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, String info, Exception e) {
//                showErrorPage();
                hideProgress();

            }

            @Override
            public void onResponse(String response) {
                showContentPage();
                hideProgress();
                MyLogger.i(">>>>>>>>>接单列表" + response);

            }
        }, false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

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
    public void requestServer() {
        super.requestServer();
//        this.showLoadingPage();
        /*showProgress(true, getString(R.string.app_loading));
        String string = "?token=" + localUserInfo.getToken();
        Request(string);*/
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
