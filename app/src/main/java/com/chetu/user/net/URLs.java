package com.chetu.user.net;


/**
 * URL路径处理类
 */
public class URLs {
    //测试地址
    public static String IMGHOST = "http://chetuapi.yxy5g.com";//图片地址
    public static String HOST = "http://chetuapi.yxy5g.com";//接口地址

    //正式地址
//    public static String IMGHOST = "http://wuliu.xianxianla.com";//图片地址
//    public static String HOST = "http://wuliu.xianxianla.com";//接口地址

    public static final String PROJECT_NAME = "";
    public static final String API = "";

    //headrs验证信息
    public static final String APIKEY = "773EDB6D2715FACF9C93354CAC5B1A3372872DC4D5AC085867C7490E9984D33E";
    public static final String HVERSION = "1.0";

    /**
     * 更新
     */
    public static final String Upgrade = HOST+"/api/article/sys-upgrade";
    /**
     * 引导页
     */
    public static final String Guide = HOST+"/api/app-banner/index";
    /**
     * 发送验证码
     */
    public static final String Code = HOST+"/api/v1/public/get_auth_code";
    /**
     * 登录
     */
    public static final String Login = HOST+"/api/v1/user/landing";
    /**
     * 一键登录
     */
    public static final String Login1 = HOST+"/api/member/quick-login";
    /**
     * 图片上传
     */
    public static final String UpFile = HOST+"/upload/picture";
    /**
     * *********************************门店*****************************************
     */
    public static final String Fragment3 = HOST+"/api/v1/public/get_store_list";
    /**
     * *********************************我的*****************************************
     */
    public static final String Fragment4 = HOST+"/api/v1/user/get_user_info";
    /**
     * 足迹列表
     */
    public static final String Footprint = HOST+"/api/v1/user/user_footprint_list_page";
    /**
     * 待接车
     */
    public static final String DaiJieChe = HOST+"/api/v1/user/user_collection_list_page";
    /**
     * 收藏列表
     */
    public static final String Collect = HOST+"/api/v1/user/user_collection_list_page";


    /**
     * 拼接请求路径
     *
     * @PARAM URI
     * @RETURN
     */
  /*  public static String getURL(String uri) {
        return HOST + PROJECT_NAME + API + uri;
    }*/

}
