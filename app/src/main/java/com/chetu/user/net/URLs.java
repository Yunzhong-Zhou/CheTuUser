package com.chetu.user.net;


/**
 * URL路径处理类
 */
public class URLs {
    //测试地址
    public static String IMGHOST = "http://img.leqi.cool/";//图片地址
    public static String HOST = "http://120.24.110.237:1000";//接口地址
    public static String KFHOST = "http://chat.leqi.cool";//客服地址
    //正式地址
   /* public static String IMGHOST = "http://img.leqi.cool/";//图片地址
    public static String HOST = "http://120.24.110.237:1000";//接口地址
    public static String KFHOST = "http://chat.leqi.cool";//客服地址*/


    public static final String PROJECT_NAME = "";
    public static final String API = "";

    //headrs验证信息
    public static final String APIKEY = "773EDB6D2715FACF9C93354CAC5B1A3372872DC4D5AC085867C7490E9984D33E";
    public static final String HVERSION = "1.0";

    //第一次加载
    public static final String FristApp = HOST+"/api/v1/public/get_conf_info";
    //更新
    public static final String Upgrade = HOST+"/api/article/sys-upgrade";
    //引导页
    public static final String Guide = HOST+"/api/app-banner/index";
    //发送验证码
    public static final String Code = HOST+"/api/v1/public/get_auth_code";
    //登录
    public static final String Login = HOST+"/api/v1/user/landing";
    //一键登录
    public static final String Login1 = HOST+"/api/member/quick-login";
    //绑定手机号
    public static final String BindingPhone = HOST + "/api/v1/user/binding_phone";
    //图片上传
    public static final String UpFile = HOST+"/apiupload/picture";
    //热门搜索
    public static final String SearchHot = HOST+"/api/v1/public/hot_list_all";
    //搜索
    public static final String Search = HOST+"/api/v1/public/get_store_search_list";
    //保险
    public static final String BaoXian = HOST+"/api/v1/public/user_report_police_list";
    //收藏
    public static final String ShouChang = HOST+"/api/v1/user/user_collection_increase";
    //取消收藏
    public static final String QuXiaoShouChang = HOST+"/api/v1/user/user_collection_del";
    //加入购物车
    public static final String ADDShop = HOST+"/api/v1/public/user_cart_add";
    //提交订单
    public static final String ADDOrder = HOST+"/api/v1/public/increase_order_service";
    //提交询价
    public static final String ADDXunJia = HOST+"/api/v1/user/user_cart_conversion_demand_increase";
    //支付
    public static final String Pay = HOST+"/api/v1/pay/online_payment";
    //评价列表
    public static final String PingJiaList = HOST+"/api/v1/public/get_goods_eval_list";
    //门店评价列表
    public static final String PingJiaList_Store = HOST+"/api/v1/public/get_store_eval_list";
    //发布评价
    public static final String AddPingJia = HOST+"/api/v1/user/user_add_eval";
    //回复问答
    public static final String HuiDa = HOST+"/api/v1/public/user_store_ques_ans_add";
    /**
     * *******************************首页****************************************
     * */
    public static final String Fragment1 = HOST+"/api/v1/public/get_store_list";
    //服务和banner数据
    public static final String Fragment1_Service = HOST+"/api/v1/public/get_inddex_data";
    //车险询价（发布）
    public static final String CarInsurance = HOST+"/api/v1/public/user_inquiry_add";
    //违章查询
    public static final String CarIllegal = HOST+"/api/v1/public/user_vio_regulat_add";
    //发布询价（发布）
    public static final String XunJia_Add = HOST+"/api/v1/user/user_inquiry_demand_increase";
    //车辆救援（发布）
    public static final String CarJiuYuan = HOST+"/api/v1/public/user_rescue_add";
    //商品列表
    public static final String ProductList = HOST+"/api/v1/public/get_goods_list";
    //商品详情
    public static final String ProductDetail= HOST+"/api/v1/public/get_goods_details";
    /**
     * *******************************养车****************************************
     * */
    //保存草稿
    public static final String SaveDraft = HOST+"/api/v1/user/draft_add";
    //草稿列表
    public static final String DraftList = HOST+"/api/v1/user/draft_list";
    //删除草稿
    public static final String DeleteDraft = HOST+"/api/v1/user/draft_del";
    /**
     * *******************************门店****************************************
     * */
    public static final String Fragment3 = HOST+"/api/v1/public/get_store_list";
    //门店详情
    public static final String StoreDetail = HOST+"/api/v1/public/get_store_details";
    //问答
    public static final String StoreDetail_WenDa = HOST+"/api/v1/public/user_store_ques_ans_list";
    //服务分类列表
    public static final String ServiceList = HOST+"/api/v1/public/get_service_list";
    //服务分类列表
    public static final String ServiceList_all = HOST+"/api/v1/public/service_list_all";
    //门店服务分类列表
    public static final String ServiceList_Store = HOST+"/api/v1/public/get_store_service_content";
    //确认订单信息
    public static final String ConfirmOrder = HOST+"/api/v1/public/confirm_order_service";
    //店铺商品列表-选择商品
    public static final String SelectGoods = HOST+"/api/v1/public/get_store_goods";
    //删除服务或商品
    public static final String DeleteServiceOrGoods = HOST+"/api/v1/public/user_cart_del";
    //修改商品数量
    public static final String ChageGoodsNum = HOST+"/api/v1/public/user_cart_save_num";
    //选择预约时间
    public static final String SelectTime= HOST+"/api/v1/public/store_free_time";
    /**
     * *******************************我的****************************************
     * */
    public static final String Fragment4 = HOST+"/api/v1/user/get_user_info";
    //修改个人信息
    public static final String ChageProfile = HOST+"/api/v1/user/user_setup";
    //意见反馈分类
    public static final String FeedBack_List = HOST+"/api/v1/public/get_feedback_classify_list";
    //版本说明
    public static final String Version = HOST+"/api/v1/h5/version";
    //积分列表
    public static final String Integral = HOST+"/api/v1/user/get_user_detailed_list";
    //意见反馈提交
    public static final String FeedBack = HOST+"/api/v1/public/increase_user_feedback";
    //我的订单
    public static final String MyOrder = HOST+"/api/v1/user/user_order_list";
    //优惠券
    public static final String Coupon = HOST+"/api/v1/user/user_coupon_list";
    //优惠券二维码
    public static final String Coupon_QRCode = HOST+"/api/v1/user/user_coupon_qrcode";
    //待发布
    public static final String WaitingRelease = HOST+"/api/v1/user/inquiry_demand_list";
    //删除询价
    public static final String DeleteXunJia = HOST+"/api/v1/user/inquiry_demand_del";
    //发布询价
    public static final String FaBuXunJia = HOST+"/api/v1/user/inquiry_demand_start";
    //我的车辆
    public static final String MyCar = HOST+"/api/v1/user/user_sedan_list";
    //我的车辆详情
    public static final String MyCarDetail = HOST+"/api/v1/user/user_sedan_details";
    //获取汽车品牌
    public static final String CarNameList = HOST+"/api/v1/public/user_sedan_brand_list";
    //添加车辆
    public static final String AddCar = HOST+"/api/v1/user/user_sedan_add";
    //添加记录
    public static final String AddNotebook = HOST+"/api/v1/user/user_notepad_increase";
    //记录列表
    public static final String Notebook = HOST+"/api/v1/user/user_notepad_list_page";
    //修改车辆
    public static final String ChageCar = HOST+"/api/v1/user/user_sedan_edit";
    //删除车辆
    public static final String DeleteCar = HOST+"/api/v1/user/user_sedan_del";
    //足迹列表
    public static final String Footprint = HOST+"/api/v1/user/user_footprint_list_page";
    //删除足迹
    public static final String DeleteFootprint = HOST+"/api/v1/user/user_footprint_del";
    //收藏列表
    public static final String Collect = HOST+"/api/v1/user/user_collection_list_page";
    //删除收藏
    public static final String DeleteCollect = HOST+"/api/v1/user/user_collection_del";
    //订单详情
    public static final String OrderDetail = HOST+"/api/v1/user/user_order_details";
    //确认检测项目
    public static final String ConfirmProject = HOST+"/api/v1/techn/techn_testing_details_confirme";
    //商家加盟
    public static final String AddMerchant = HOST+"/api/v1/user/user_apply_store_info";
    //商家加盟1
    public static final String AddMerchant1 = HOST+"/api/v1/user/user_apply_store_one";
    //商家加盟2
    public static final String AddMerchant2 = HOST+"/api/v1/user/user_apply_store_tow";
    //商家加盟3
    public static final String AddMerchant3 = HOST+"/api/v1/user/user_apply_store_three";
    //客服列表
    public static final String ServiceCenter = HOST+"/api/v1/user/get_kf_list";
    //需求订单
    public static final String XuQiuOrder = HOST+"/api/v1/public/user_cart_list";
    //取消订单
    public static final String DeleteOrder = HOST+"/api/v1/user/user_cart_store_del";
    /**拼接请求路径
     *@PARAM URI
     * @RETURN
     */
    public static String getURL(String uri) {
        return HOST + PROJECT_NAME + API + uri;
    }


}
