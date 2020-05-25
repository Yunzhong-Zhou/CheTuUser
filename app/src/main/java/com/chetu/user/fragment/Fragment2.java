package com.chetu.user.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chetu.user.R;
import com.chetu.user.activity.MainActivity;
import com.chetu.user.base.BaseFragment;


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
        /*OkHttpClientManager.getAsyn(getActivity(), URLs.Fragment2 + string,
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

                        hideProgress();
                    }
                });*/
    }

    private void RequestMore(String string) {
        /*OkHttpClientManager.getAsyn(getActivity(), URLs.Fragment2 + string,
                new OkHttpClientManager.ResultCallback<Fragment2Model>() {
                    @Override
                    public void onError(Request request, String info, Exception e) {

                    }

                    @Override
                    public void onResponse(Fragment2Model response) {
                        showContentPage();
                        hideProgress();
                        MyLogger.i(">>>>>>>>>订单列表更多" + response);


                    }
                });*/

    }

}
