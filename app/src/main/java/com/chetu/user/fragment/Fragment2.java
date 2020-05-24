package com.chetu.user.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chetu.user.R;
import com.chetu.user.activity.MainActivity;
import com.chetu.user.base.BaseFragment;
import com.chetu.user.model.Fragment2Model;
import com.chetu.user.net.OkHttpClientManager;
import com.chetu.user.net.URLs;
import com.chetu.user.utils.MyLogger;
import com.squareup.okhttp.Request;


/**
 * Created by fafukeji01 on 2016/1/6.
 * 订单
 */
public class Fragment2 extends BaseFragment {
    /*int page1 = 1, page2 = 1, page3 = 1, status = 1;
    private RecyclerView recyclerView;
    List<Fragment2Model1.TindentListBean> list = new ArrayList<>();
    CommonAdapter<Fragment2Model1.TindentListBean> mAdapter;

    private LinearLayout linearLayout1, linearLayout2, linearLayout3;
    private TextView textView1, textView2, textView3;
    private View view1, view2, view3;

    //定位
    //声明AMapLocationClient类对象
    private AMapLocationClient mLocationClient = null;
    double lat = 0, lng = 0, juli = 0;
    private DPoint mStartPoint = null;
    private DPoint mEndPoint = null;

    int scale = 20;*/

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
            requestServer();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (MainActivity.item == 1) {
            requestServer();
        }
    }

    @Override
    protected void initView(View view) {
        /*//刷新
        setSpringViewMore(true);//不需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                String string = "";
                switch (status) {
                    case 1:
                        page1 = 1;
                        string = "?page=" + page1//当前页号
                                + "&count=" + "10"//页面行数
                                + "&status=" + status//1进行中2已完成3已取消
                                + "&token=" + localUserInfo.getToken();
                        break;
                    case 2:
                        page2 = 1;
                        string = "?page=" + page2//当前页号
                                + "&count=" + "10"//页面行数
                                + "&status=" + status//1进行中2已完成3已取消
                                + "&token=" + localUserInfo.getToken();
                        break;
                    case 3:
                        page3 = 1;
                        string = "?page=" + page3//当前页号
                                + "&count=" + "10"//页面行数
                                + "&status=" + status//1进行中2已完成3已取消
                                + "&token=" + localUserInfo.getToken();
                        break;
                }
                Request(string);
            }

            @Override
            public void onLoadmore() {
                String string = "";
                switch (status) {
                    case 1:
                        page1++;
                        string = "?page=" + page1//当前页号
                                + "&count=" + "10"//页面行数
                                + "&status=" + status//1进行中2已完成3已取消
                                + "&token=" + localUserInfo.getToken();
                        break;
                    case 2:
                        page2++;
                        string = "?page=" + page2//当前页号
                                + "&count=" + "10"//页面行数
                                + "&status=" + status//1进行中2已完成3已取消
                                + "&token=" + localUserInfo.getToken();
                        break;
                    case 3:
                        page3++;
                        string = "?page=" + page3//当前页号
                                + "&count=" + "10"//页面行数
                                + "&status=" + status//1进行中2已完成3已取消
                                + "&token=" + localUserInfo.getToken();
                        break;
                }
                RequestMore(string);
            }
        });

        recyclerView = findViewByID_My(R.id.recyclerView);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLinearLayoutManager);

        linearLayout1 = findViewByID_My(R.id.linearLayout1);
        linearLayout2 = findViewByID_My(R.id.linearLayout2);
        linearLayout3 = findViewByID_My(R.id.linearLayout3);

        linearLayout1.setOnClickListener(this);
        linearLayout2.setOnClickListener(this);
        linearLayout3.setOnClickListener(this);

        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);

        view1 = findViewByID_My(R.id.view1);
        view2 = findViewByID_My(R.id.view2);
        view3 = findViewByID_My(R.id.view3);*/

    }

    @Override
    protected void initData() {
//        requestServer();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /*case R.id.linearLayout1:
                status = 1;
                changeUI();
                requestServer();
                break;
            case R.id.linearLayout2:
                status = 2;
                changeUI();
                requestServer();
                break;
            case R.id.linearLayout3:
                status = 3;
                changeUI();
                requestServer();
                break;*/
        }
    }

    private void changeUI() {
        /*switch (status) {
            case 1:
                textView1.setTextColor(getResources().getColor(R.color.blue));
                textView2.setTextColor(getResources().getColor(R.color.black2));
                textView3.setTextColor(getResources().getColor(R.color.black2));
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.INVISIBLE);
                view3.setVisibility(View.INVISIBLE);

                break;
            case 2:
                textView1.setTextColor(getResources().getColor(R.color.black2));
                textView2.setTextColor(getResources().getColor(R.color.blue));
                textView3.setTextColor(getResources().getColor(R.color.black2));
                view1.setVisibility(View.INVISIBLE);
                view2.setVisibility(View.VISIBLE);
                view3.setVisibility(View.INVISIBLE);

                break;
            case 3:
                textView1.setTextColor(getResources().getColor(R.color.black2));
                textView2.setTextColor(getResources().getColor(R.color.black2));
                textView3.setTextColor(getResources().getColor(R.color.blue));
                view1.setVisibility(View.INVISIBLE);
                view2.setVisibility(View.INVISIBLE);
                view3.setVisibility(View.VISIBLE);

                break;
            default:
                break;
        }*/
//        requestServer();
    }

    @Override
    protected void updateView() {

    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();

    }

    private void Request(String string) {
        OkHttpClientManager.getAsyn(getActivity(), URLs.Fragment2 + string,
                new OkHttpClientManager.ResultCallback<Fragment2Model>() {
                    @Override
                    public void onError(Request request, String info, Exception e) {
                        showErrorPage();
                        hideProgress();
                        if (!info.equals("")) {
                            myToast(info);
                        }
                    }

                    @Override
                    public void onResponse(Fragment2Model response) {
                        showContentPage();
                        MyLogger.i(">>>>>>>>>订单" + response);
                        /*list = response.getTindent_list();
                        mAdapter = new CommonAdapter<Fragment2Model1.TindentListBean>
                                (getActivity(), R.layout.item_orderlist, list) {
                            @Override
                            protected void convert(ViewHolder holder, Fragment2Model1.TindentListBean model, int position) {
                                //订单号
                                holder.setText(R.id.tv_ordernum, "订单号：" + model.getSn());
                                //车型
                                TextView textView1 = holder.getView(R.id.textView1);
                                textView1.setText(model.getUse_type());
                                switch (model.getUse_type_id()) {
                                    case 1:
                                        //专车
                                        textView1.setBackgroundResource(R.drawable.yuanjiao_50_juse_right);
                                        break;
                                    case 2:
                                        //顺风车
                                        textView1.setBackgroundResource(R.drawable.yuanjiao_50_lanse_right);
                                        break;
                                    case 3:
                                        //快递
                                        textView1.setBackgroundResource(R.drawable.yuanjiao_50_hongse_right);
                                        break;
                                }
                                holder.setText(R.id.textView2, "时间：" + model.getCreated_at());//时间

                                //状态
                                TextView textView3 = holder.getView(R.id.textView3);
                                textView3.setText(model.getStatus_text());
                                switch (model.getStatus()) {
                                    case 0:
                                        //匹配中
                                        textView3.setTextColor(getResources().getColor(R.color.blue));
                                        break;
                                    case 1:
                                        //待发货
                                        textView3.setTextColor(getResources().getColor(R.color.red));
                                        break;
                                    default:
                                        //其他
                                        textView3.setTextColor(getResources().getColor(R.color.orange));
                                        break;
                                }

                                holder.setText(R.id.textView4, "车型：" + model.getCar_type());//车型
                                //温层
                                if (model.getTemperature().equals("")) {
                                    holder.setText(R.id.textView5, "");
                                } else {
                                    holder.setText(R.id.textView5, "温层：" + model.getTemperature());
                                }
                                //地址列表
                                LinearLayout ll_add = holder.getView(R.id.ll_add);
                                ll_add.removeAllViews();
                                for (int i = 0; i < model.getAddr_list().size(); i++) {
                                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                                            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                    LayoutInflater inflater = LayoutInflater.from(getActivity());
                                    View view = inflater.inflate(R.layout.item_add_fragment2_1, null, false);
                                    view.setLayoutParams(lp);
                                    TextView tv1 = (TextView) view.findViewById(R.id.tv1);
                                    TextView tv2 = (TextView) view.findViewById(R.id.tv2);

                                    if (i == 0) {
                                        tv1.setText("发");
                                        tv1.setBackgroundResource(R.drawable.yuanxing_lanse);

                                    } else if (i == (model.getAddr_list().size() - 1)) {
                                        tv1.setText("收");
                                        tv1.setBackgroundResource(R.drawable.yuanxing_juse);

                                    } else {
                                        tv1.setText("途");
                                        tv1.setBackgroundResource(R.drawable.yuanxing_huise);
                                    }
                                    tv2.setText(model.getAddr_list().get(i).getAddr());//地址

                                    ll_add.addView(view);
                                }

                                if (model.getIs_plan() == 1) {//是预约订单
                                    holder.setText(R.id.textView8, "预约：" + model.getPlan_time());//预约
                                }
                                holder.setText(R.id.textView9, "货运价格：" + model.getPrice());//货运价格
                                //附加费
                                TextView tv_fujiafei = holder.getView(R.id.tv_fujiafei);
                                if (status == 3) {//已取消
                                    tv_fujiafei.setVisibility(View.GONE);
                                } else {
                                    tv_fujiafei.setVisibility(View.VISIBLE);
                                    tv_fujiafei.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Bundle bundle = new Bundle();
                                            bundle.putString("id", model.getId());
                                            CommonUtil.gotoActivityWithData(getActivity(), AddSurchargeActivity.class, bundle, false);
                                        }
                                    });
                                }
                                //行驶轨迹
                                TextView tv_guiji = holder.getView(R.id.tv_guiji);
                                if (status == 2) {//已完成
                                    tv_guiji.setVisibility(View.VISIBLE);
                                    tv_guiji.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Bundle bundle = new Bundle();
                                            bundle.putSerializable("Fragment2Model1", model);
                                            CommonUtil.gotoActivityWithData(getActivity(), TrackSearchActivity.class, bundle, false);
                                        }
                                    });
                                } else {
                                    tv_guiji.setVisibility(View.GONE);
                                }
                                //转单
                                TextView tv_zhuandan = holder.getView(R.id.tv_zhuandan);
                                if (status == 1) {
                                    tv_zhuandan.setVisibility(View.VISIBLE);
                                    tv_zhuandan.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            if (model.getStatus() == 6) {
                                                //正在转单
                                                Bundle bundle = new Bundle();
                                                bundle.putString("id", model.getId());
                                                bundle.putString("lat", lat + "");
                                                bundle.putString("lng", lng + "");
                                                bundle.putString("scale", "20");
                                                CommonUtil.gotoActivityWithData(getActivity(), ZhuanDanActivity.class, bundle, false);
                                            } else {
                                                BaseDialog dialog1 = new BaseDialog(getActivity());
                                                dialog1.contentView(R.layout.dialog_zhuandan)
                                                        .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                                ViewGroup.LayoutParams.WRAP_CONTENT))
                                                        .animType(BaseDialog.AnimInType.CENTER)
                                                        .canceledOnTouchOutside(true)
                                                        .dimAmount(0.8f)
                                                        .show();
                                                TextView tv_bili = dialog1.findViewById(R.id.tv_bili);
                                                SeekBar seekBar = dialog1.findViewById(R.id.seekBar);
                                                dialog1.findViewById(R.id.jianhao).setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        if (scale > 1 && scale <= 100) {
                                                            scale = scale - 1;
                                                            tv_bili.setText("金额比例：" + scale + "%");
                                                            seekBar.setProgress(scale);
                                                        }
                                                    }
                                                });
                                                dialog1.findViewById(R.id.jiahao).setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        if (scale >= 0 && scale < 99) {
                                                            scale = scale + 1;
                                                            tv_bili.setText("金额比例：" + scale + "%");
                                                            seekBar.setProgress(scale);

                                                        }
                                                    }
                                                });
                                                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                                                    @Override
                                                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                                        if (progress > 0 && progress < 100) {
                                                            scale = progress;
                                                            tv_bili.setText("金额比例：" + scale + "%");
                                                        }
                                                    }

                                                    @Override
                                                    public void onStartTrackingTouch(SeekBar seekBar) {

                                                    }

                                                    @Override
                                                    public void onStopTrackingTouch(SeekBar seekBar) {

                                                    }
                                                });
                                                dialog1.findViewById(R.id.textView3).setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        dialog1.dismiss();
                                                        Bundle bundle = new Bundle();
                                                        bundle.putString("id", model.getId());
                                                        bundle.putString("lat", lat + "");
                                                        bundle.putString("lng", lng + "");
                                                        bundle.putString("scale", scale + "");
                                                        CommonUtil.gotoActivityWithData(getActivity(), ZhuanDanActivity.class, bundle, false);
                                                    }
                                                });
                                                dialog1.findViewById(R.id.textView4).setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        dialog1.dismiss();
                                                    }
                                                });
                                                dialog1.findViewById(R.id.dismiss).setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        dialog1.dismiss();
                                                    }
                                                });
                                            }

                                        }
                                    });
                                } else {
                                    tv_zhuandan.setVisibility(View.GONE);
                                }


                            }
                        };
                        mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                *//*if (status == 3) {
                                    //跳转取消订单详情
                                    Bundle bundle = new Bundle();
                                    bundle.putString("id", list.get(i).getId());
                                    CommonUtil.gotoActivityWithData(getActivity(), OrderDetailsActivity.class, bundle);
                                } else {*//*
                                Bundle bundle = new Bundle();
                                bundle.putString("id", list.get(i).getId());
                                CommonUtil.gotoActivityWithData(getActivity(), OrderDetailsActivity.class, bundle);
//                                }
                            }

                            @Override
                            public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                return false;
                            }
                        });

                        if (list.size() > 0) {
                            showContentPage();
                            recyclerView.setAdapter(mAdapter);
                            mAdapter.notifyDataSetChanged();
                        } else {
                            showEmptyPage();//空数据
                        }*/

                        hideProgress();
                    }
                });
    }

    private void RequestMore(String string) {
        OkHttpClientManager.getAsyn(getActivity(), URLs.Fragment2 + string,
                new OkHttpClientManager.ResultCallback<Fragment2Model>() {
                    @Override
                    public void onError(Request request, String info, Exception e) {
                        /*switch (status) {
                            case 1:
                                page1--;
                                break;
                            case 2:
                                page2--;
                                break;
                            case 3:
                                page3--;
                                break;
                        }
                        showErrorPage();
                        hideProgress();
                        if (!info.equals("")) {
                            showToast(info);
                        }*/
                    }

                    @Override
                    public void onResponse(Fragment2Model response) {
                        showContentPage();
                        hideProgress();
                        MyLogger.i(">>>>>>>>>订单列表更多" + response);

                        /*List<Fragment2Model1.TindentListBean> list1_1 = new ArrayList<>();
                        list1_1 = response.getTindent_list();
                        if (list1_1.size() == 0) {
                            switch (status) {
                                case 1:
                                    page1--;
                                    break;
                                case 2:
                                    page2--;
                                    break;
                                case 3:
                                    page3--;
                                    break;
                            }
                            myToast(getString(R.string.app_nomore));
                        } else {
                            list.addAll(list1_1);
                            mAdapter.notifyDataSetChanged();
                        }*/

                    }
                });

    }

}
