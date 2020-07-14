package com.chetu.user.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.chetu.user.R;
import com.chetu.user.base.BaseActivity;
import com.chetu.user.model.IntegralModel;
import com.chetu.user.model.PayModel;
import com.chetu.user.net.URLs;
import com.chetu.user.okhttp.CallBackUtil;
import com.chetu.user.okhttp.OkhttpUtil;
import com.chetu.user.utils.MyLogger;
import com.chetu.user.utils.alipay.PayResult;
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
 * Created by zyz on 2020/5/26.
 * 积分
 */
public class IntegralActivity extends BaseActivity {
    int page = 0;
    private RecyclerView recyclerView;
    List<IntegralModel.ListBean> list = new ArrayList<>();
    CommonAdapter<IntegralModel.ListBean> mAdapter;
    TextView tv_jifen;

    private static final int SDK_PAY_FLAG = 1;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        MyLogger.i("支付成功" + payResult);
                        showToast("支付成功");
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        MyLogger.i("支付失败" + payResult);
                        showToast("支付失败");
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integral);
        mImmersionBar.reset()
                .statusBarColor(R.color.blue)
                .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题
//                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
    }

    @Override
    protected void initView() {
        //刷新
        setSpringViewMore(true);//不需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                page = 0;
                Map<String, String> params = new HashMap<>();
                params.put("page", page + "");
                params.put("is_integral", "2");//1为资金明细 2为积分明细
                params.put("u_token", localUserInfo.getToken());
                Request(params);
            }

            @Override
            public void onLoadmore() {
                page++;
                Map<String, String> params = new HashMap<>();
                params.put("u_token", localUserInfo.getToken());
                params.put("page", page + "");
                params.put("is_integral", "2");//1为资金明细 2为积分明细
                RequestMore(params);
            }
        });

        recyclerView = findViewByID_My(R.id.recyclerView);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        tv_jifen = findViewByID_My(R.id.tv_jifen);
    }

    @Override
    protected void initData() {
        tv_jifen.setText(getIntent().getStringExtra("jifen"));
        requestServer();
    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        page = 0;
        Map<String, String> params = new HashMap<>();
        params.put("page", page + "");
        params.put("is_integral", "2");//1为资金明细 2为积分明细
        params.put("u_token", localUserInfo.getToken());
        Request(params);
    }

    private void Request(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Integral, params, headerMap, new CallBackUtil<IntegralModel>() {
            @Override
            public IntegralModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                showEmptyPage();
                myToast(err);
            }

            @Override
            public void onResponse(IntegralModel response) {
                hideProgress();
                list = response.getList();
                if (list.size() > 0) {
                    showContentPage();
                    mAdapter = new CommonAdapter<IntegralModel.ListBean>
                            (IntegralActivity.this, R.layout.item_integral, list) {
                        @Override
                        protected void convert(ViewHolder holder, IntegralModel.ListBean model, int position) {
                            ImageView imageView = holder.getView(R.id.imageView);
                            if (model.getNature() == 1) {//收入
                                imageView.setImageResource(R.mipmap.ic_add_blue1);
                                holder.setText(R.id.tv_money, "+" + model.getMoney());
                            } else {
                                imageView.setImageResource(R.mipmap.ic_list_gray);
                                holder.setText(R.id.tv_money, "-" + model.getMoney());
                            }
                            holder.setText(R.id.tv_title, model.getMsg());
                            holder.setText(R.id.tv_time, model.getCreateDate());
                            holder.setText(R.id.tv_shengyu, "剩余积分：" + model.getReMoney());

                        }
                    };
                    mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {

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
        OkhttpUtil.okHttpPost(URLs.Integral, params, headerMap, new CallBackUtil<IntegralModel>() {
            @Override
            public IntegralModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
                page--;
            }

            @Override
            public void onResponse(IntegralModel response) {
                hideProgress();
                List<IntegralModel.ListBean> list1 = new ArrayList<>();
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

    @Override
    protected void updateView() {
        titleView.setTitle("积分");
        titleView.setTitleColor(R.color.white);
        titleView.setLeftBtn(R.mipmap.ic_return_white, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleView.setBackground(R.color.blue);

        titleView.showRightTextview("测试支付", R.color.white, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> params = new HashMap<>();
                params.put("payment", "1");
                params.put("i_classify", "1");
                params.put("money", "10");
                params.put("code_str", "41338C46FA8D4938A55C6450F29BEED3");
                params.put("u_token", localUserInfo.getToken());
                RequestPay(params);
            }
        });

    }

    private void RequestPay(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Pay, params, headerMap, new CallBackUtil<PayModel>() {
            @Override
            public PayModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(PayModel response) {
                hideProgress();
                //微信
                        /*IWXAPI api= WXAPIFactory.createWXAPI(RechargeActivity.this, "wx79d0350178a9ff3a",false);//填写自己的APPID
                        api.registerApp("wx79d0350178a9ff3a");//填写自己的APPID，注册本身APP
                        PayReq req = new PayReq();//PayReq就是订单信息对象
                        //给req对象赋值
                        req.appId = appid;//APPID
                        req.partnerId = partnerid;//    商户号
                        req.prepayId = prepayid;//  预付款ID
                        req.nonceStr = getRoundString();//随机数
                        req.timeStamp = getTimeStamp();//时间戳
                        req.packageValue = "Sign=WXPay";//固定值Sign=WXPay
                        req.sign = sign;//签名
                        api.sendReq(req);//将订单信息对象发送给微信服务器，即发送支付请求*/

                //弹出支付宝
                Runnable payRunnable = new Runnable() {
                    @Override
                    public void run() {
                        PayTask alipay = new PayTask(IntegralActivity.this);
                        Map<String, String> result = alipay.payV2(response.getOrderStr(), true);
                        Message msg = new Message();
                        msg.what = SDK_PAY_FLAG;
                        msg.obj = result;
                        mHandler.sendMessage(msg);
                    }
                };
                // 必须异步调用
                Thread payThread = new Thread(payRunnable);
                payThread.start();
            }
        });
    }
}
