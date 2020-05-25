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
     * *********************************我的*****************************************8
     */
    public static final String Fragment4 = HOST+"/api/v1/user/get_user_info";



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
