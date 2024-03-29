package com.chetu.user.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2020/5/28.
 */
public class OrderDetailModel_DaiJieChe implements Serializable {
    /**
     * order_service_total_price : 5280.0
     * v_order_goods_total_price : 0.0
     * distance : 12246.42
     * order_info : {"id":"1087","yOrderId":"730059153300520960","userId":"714547022807433216","yStoreId":"692341585785913344","yUserSedanId":"724585460164198400","iClassify":1,"gPrice":5280,"longitude":"106.642048","latitude":"29.513177","appoinTime":"2020-07-08 16","gState":0,"createDate":"2020-07-07 13:54:26","isDelivery":1,"isPick":1}
     * order_service_list : [{"id":"90","yOrderServiceId":"730059153384407040","yStoreId":"692341585785913344","yStoreServiceId":"692341585785913353","yOrderId":"730059153300520960","iClassify":1,"gPrice":100,"gState":1,"serviceStr":"右前门","servicesJson":"{\"id\":1032,\"isSheet\":1,\"lineupSum\":10,\"pictureStr\":\"/upload/2020-06-02/20200602102222_621580.png\",\"sPrice\":100.0,\"yState\":1,\"yStateValue\":\"右前门\",\"yStoreId\":692341585785913344,\"yStoreServiceId\":692341585785913353}","createDate":"2020-07-07 13:54:26","order_goods_list":[{"id":"108","yOrderGoodsId":"730059153325686784","yStoreServiceId":"692341585785913353","userId":"714547022807433216","isInstall":1,"isService":2,"goodsValue":"","goodsJson":"{\"createDate\":\"2019-09-27 10:31:19\",\"gDesc\":\"适用对象: 汽车跟踪器 附加功能: 录音功能  颜色分类: 以下套餐都有：10重精准定\",\"gIntegral\":500,\"gName\":\"gps定位器小型车载牛追跟定仪器远程录音订位汽车辆跟踪追踪神器\",\"gPrice\":1000.0,\"gState\":1,\"id\":1017,\"imgStr\":\"/upload/pro.png||/upload/pro.png\",\"isIntegral\":1,\"isPopular\":1,\"isSoffer\":1,\"orPrice\":3000.0,\"yClassifyId\":692341585785913344,\"yGoodsId\":692341585785913344,\"yStoreId\":692341585785913344}","yGoodsId":"692341585785913344","yOrderId":"730059153300520960","gNum":4,"gPrice":4000,"createDate":"2020-07-07 13:54:25","goods_info":{"id":"1017","yGoodsId":"692341585785913344","yClassifyId":"692341585785913344","yStoreId":"692341585785913344","gName":"gps定位器小型车载牛追跟定仪器远程录音订位汽车辆跟踪追踪神器","gDesc":"适用对象: 汽车跟踪器 附加功能: 录音功能  颜色分类: 以下套餐都有：10重精准定","gPrice":1000,"orPrice":3000,"imgStr":"/upload/pro.png||/upload/pro.png","imgArr":["/upload/pro.png","/upload/pro.png"],"gImg":"/upload/pro.png","gState":1,"isPopular":1,"isIntegral":1,"gIntegral":500,"createDate":"2019-09-27 10:31:19","isSoffer":1}},{"id":"109","yOrderGoodsId":"730059153355046912","yStoreServiceId":"692341585785913353","userId":"714547022807433216","isInstall":1,"isService":2,"goodsValue":"绿色||ml","goodsJson":"{\"createDate\":\"2019-09-27 10:31:19\",\"gDesc\":\"适用捷渡凌度360小米家盯盯拍70迈正际行车记录仪降压线停车监控\",\"gIntegral\":0,\"gName\":\"百魅 汽车遮阳挡6件套防晒隔热遮阳板挡阳板加厚前档太阳后挡侧挡\",\"gPrice\":1000.0,\"gState\":1,\"id\":1018,\"imgStr\":\"/upload/pro.png||/upload/pro.png\",\"isIntegral\":0,\"isPopular\":1,\"isSoffer\":1,\"orPrice\":3000.0,\"yClassifyId\":692341585785913344,\"yGoodsId\":692341585785913345,\"yStoreId\":692341585785913344}","yGoodsId":"692341585785913345","yOrderId":"730059153300520960","gNum":1,"gPrice":1180,"createDate":"2020-07-07 13:54:26","goods_info":{"id":"1018","yGoodsId":"692341585785913345","yClassifyId":"692341585785913344","yStoreId":"692341585785913344","gName":"百魅 汽车遮阳挡6件套防晒隔热遮阳板挡阳板加厚前档太阳后挡侧挡","gDesc":"适用捷渡凌度360小米家盯盯拍70迈正际行车记录仪降压线停车监控","gPrice":1000,"orPrice":3000,"imgStr":"/upload/pro.png||/upload/pro.png","imgArr":["/upload/pro.png","/upload/pro.png"],"gImg":"/upload/pro.png","gState":1,"isPopular":1,"isIntegral":0,"gIntegral":0,"createDate":"2019-09-27 10:31:19","isSoffer":1}}],"order_service_price":5280}]
     * v_order_goods_list : []
     * store_info : {"id":"1017","yStoreId":"692341585785913344","userId":"20180531104813","kfUserId":"716658563904176128","vName":"龙肆商店测试","review":"4.5","keywsr":"null","address":"深圳市光明新区公明街道东环大道松白工业区C区2栋一层106号","longitude":"116.49798","latitude":"39.916485","picture":"/upload/2020-07-09/20200709110836_560635.jpg","phone":" 400-2333-1123","vLevel":"A级","introduce":"听广告几年了，终于体验了特色服务。轮胎是其专长。听说11年开始，线上业务。16年开始线下。现在全国1500多家店。","charactStr":"||/upload/2020-07-09/20200709110807_317759.jpg||/upload/2020-07-09/20200709110817_957557.jpg||/upload/2020-07-09/20200709110819_726210.jpg","pictureStr":"||/upload/2020-07-09/20200709110836_560635.jpg||/upload/2020-07-09/20200709110838_972720.jpg||/upload/2020-07-09/20200709110842_192873.jpg","slogan":"商店测试  洗车首单10","isIndex":0}
     * user_sedan_info : {"id":"1088","yUserSedanId":"724585460164198400","userId":"714547022807433216","sName":"2016款 5.2T 设计师定制版","sLogo":"/upload/logo/716723976784576512.jpg","sCy":1,"isF":1,"isDel":0,"reportPoliceJson":"{\"createDate\":\"2020-04-15 08:44:18\",\"iCy\":1,\"id\":1047,\"telephone\":\"400-2123-2211\",\"vName\":\"人寿保险公司\",\"vNameNature\":\"普通保险\",\"yReportPoliceId\":699902914180677632}","jReportPoliceJson":"{\"createDate\":\"2020-04-15 08:44:18\",\"iCy\":1,\"id\":1047,\"telephone\":\"400-2123-2211\",\"vName\":\"人寿保险公司\",\"vNameNature\":\"普通保险\",\"yReportPoliceId\":699902914180677632}","brandJson":"{\"brandName\":\"阿斯顿・马丁\",\"groupName\":\"阿斯顿・马丁\",\"id\":87667,\"parentId\":716704210728517632,\"sLogo\":\"/upload/logo/716723976784576512.jpg\",\"sName\":\"2016款 5.2T 设计师定制版\",\"seriesName\":\"阿斯顿・马丁DB11\",\"vDispla\":\"4T\",\"vYear\":\"2015\",\"ySedanBrandId\":716704933323210752}","userPhone":"18306043086","sNumber":"粤A12345","brandInfo":{"id":"87667","ySedanBrandId":"716704933323210752","parentId":"716704210728517632","sName":"2016款 5.2T 设计师定制版","sLogo":"/upload/logo/716723976784576512.jpg","seriesName":"阿斯顿・马丁DB11","groupName":"阿斯顿・马丁","brandName":"阿斯顿・马丁","vYear":"2015","vDispla":"4T"},"compInsuranceTime":"2021-07-09","comInsuranceTime":"2022-07-09","annualReviewTime":"2022-07-09","createDate":"2020-06-22 11:23:55"}
     * order_price : 5280.0
     */

    private double order_service_total_price;
    private double v_order_goods_total_price;
    private double distance;
    private OrderInfoBean order_info;
    private StoreInfoBean store_info;
    private UserSedanInfoBean user_sedan_info;
    private double order_price;
    private List<OrderServiceListBean> order_service_list;
    private List<?> v_order_goods_list;

    public double getOrder_service_total_price() {
        return order_service_total_price;
    }

    public void setOrder_service_total_price(double order_service_total_price) {
        this.order_service_total_price = order_service_total_price;
    }

    public double getV_order_goods_total_price() {
        return v_order_goods_total_price;
    }

    public void setV_order_goods_total_price(double v_order_goods_total_price) {
        this.v_order_goods_total_price = v_order_goods_total_price;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public OrderInfoBean getOrder_info() {
        return order_info;
    }

    public void setOrder_info(OrderInfoBean order_info) {
        this.order_info = order_info;
    }

    public StoreInfoBean getStore_info() {
        return store_info;
    }

    public void setStore_info(StoreInfoBean store_info) {
        this.store_info = store_info;
    }

    public UserSedanInfoBean getUser_sedan_info() {
        return user_sedan_info;
    }

    public void setUser_sedan_info(UserSedanInfoBean user_sedan_info) {
        this.user_sedan_info = user_sedan_info;
    }

    public double getOrder_price() {
        return order_price;
    }

    public void setOrder_price(double order_price) {
        this.order_price = order_price;
    }

    public List<OrderServiceListBean> getOrder_service_list() {
        return order_service_list;
    }

    public void setOrder_service_list(List<OrderServiceListBean> order_service_list) {
        this.order_service_list = order_service_list;
    }

    public List<?> getV_order_goods_list() {
        return v_order_goods_list;
    }

    public void setV_order_goods_list(List<?> v_order_goods_list) {
        this.v_order_goods_list = v_order_goods_list;
    }

    public static class OrderInfoBean {
        /**
         * id : 1087
         * yOrderId : 730059153300520960
         * userId : 714547022807433216
         * yStoreId : 692341585785913344
         * yUserSedanId : 724585460164198400
         * iClassify : 1
         * gPrice : 5280.0
         * longitude : 106.642048
         * latitude : 29.513177
         * appoinTime : 2020-07-08 16
         * gState : 0
         * createDate : 2020-07-07 13:54:26
         * isDelivery : 1
         * isPick : 1
         */

        private String id;
        private String yOrderId;
        private String userId;
        private String yStoreId;
        private String yUserSedanId;
        private int iClassify;
        private double gPrice;
        private String longitude;
        private String latitude;
        private String appoinTime;
        private int gState;
        private String createDate;
        private int isDelivery;
        private int isPick;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYOrderId() {
            return yOrderId;
        }

        public void setYOrderId(String yOrderId) {
            this.yOrderId = yOrderId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getYStoreId() {
            return yStoreId;
        }

        public void setYStoreId(String yStoreId) {
            this.yStoreId = yStoreId;
        }

        public String getYUserSedanId() {
            return yUserSedanId;
        }

        public void setYUserSedanId(String yUserSedanId) {
            this.yUserSedanId = yUserSedanId;
        }

        public int getIClassify() {
            return iClassify;
        }

        public void setIClassify(int iClassify) {
            this.iClassify = iClassify;
        }

        public double getGPrice() {
            return gPrice;
        }

        public void setGPrice(double gPrice) {
            this.gPrice = gPrice;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getAppoinTime() {
            return appoinTime;
        }

        public void setAppoinTime(String appoinTime) {
            this.appoinTime = appoinTime;
        }

        public int getGState() {
            return gState;
        }

        public void setGState(int gState) {
            this.gState = gState;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public int getIsDelivery() {
            return isDelivery;
        }

        public void setIsDelivery(int isDelivery) {
            this.isDelivery = isDelivery;
        }

        public int getIsPick() {
            return isPick;
        }

        public void setIsPick(int isPick) {
            this.isPick = isPick;
        }
    }

    public static class StoreInfoBean {
        /**
         * id : 1017
         * yStoreId : 692341585785913344
         * userId : 20180531104813
         * kfUserId : 716658563904176128
         * vName : 龙肆商店测试
         * review : 4.5
         * keywsr : null
         * address : 深圳市光明新区公明街道东环大道松白工业区C区2栋一层106号
         * longitude : 116.49798
         * latitude : 39.916485
         * picture : /upload/2020-07-09/20200709110836_560635.jpg
         * phone :  400-2333-1123
         * vLevel : A级
         * introduce : 听广告几年了，终于体验了特色服务。轮胎是其专长。听说11年开始，线上业务。16年开始线下。现在全国1500多家店。
         * charactStr : ||/upload/2020-07-09/20200709110807_317759.jpg||/upload/2020-07-09/20200709110817_957557.jpg||/upload/2020-07-09/20200709110819_726210.jpg
         * pictureStr : ||/upload/2020-07-09/20200709110836_560635.jpg||/upload/2020-07-09/20200709110838_972720.jpg||/upload/2020-07-09/20200709110842_192873.jpg
         * slogan : 商店测试  洗车首单10
         * isIndex : 0
         */

        private String id;
        private String yStoreId;
        private String userId;
        private String kfUserId;
        private String vName;
        private String review;
        private String keywsr;
        private String address;
        private String longitude;
        private String latitude;
        private String picture;
        private String phone;
        private String vLevel;
        private String introduce;
        private String charactStr;
        private String pictureStr;
        private String slogan;
        private int isIndex;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYStoreId() {
            return yStoreId;
        }

        public void setYStoreId(String yStoreId) {
            this.yStoreId = yStoreId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getKfUserId() {
            return kfUserId;
        }

        public void setKfUserId(String kfUserId) {
            this.kfUserId = kfUserId;
        }

        public String getVName() {
            return vName;
        }

        public void setVName(String vName) {
            this.vName = vName;
        }

        public String getReview() {
            return review;
        }

        public void setReview(String review) {
            this.review = review;
        }

        public String getKeywsr() {
            return keywsr;
        }

        public void setKeywsr(String keywsr) {
            this.keywsr = keywsr;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getVLevel() {
            return vLevel;
        }

        public void setVLevel(String vLevel) {
            this.vLevel = vLevel;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public String getCharactStr() {
            return charactStr;
        }

        public void setCharactStr(String charactStr) {
            this.charactStr = charactStr;
        }

        public String getPictureStr() {
            return pictureStr;
        }

        public void setPictureStr(String pictureStr) {
            this.pictureStr = pictureStr;
        }

        public String getSlogan() {
            return slogan;
        }

        public void setSlogan(String slogan) {
            this.slogan = slogan;
        }

        public int getIsIndex() {
            return isIndex;
        }

        public void setIsIndex(int isIndex) {
            this.isIndex = isIndex;
        }
    }

    public static class UserSedanInfoBean {
        /**
         * id : 1088
         * yUserSedanId : 724585460164198400
         * userId : 714547022807433216
         * sName : 2016款 5.2T 设计师定制版
         * sLogo : /upload/logo/716723976784576512.jpg
         * sCy : 1
         * isF : 1
         * isDel : 0
         * reportPoliceJson : {"createDate":"2020-04-15 08:44:18","iCy":1,"id":1047,"telephone":"400-2123-2211","vName":"人寿保险公司","vNameNature":"普通保险","yReportPoliceId":699902914180677632}
         * jReportPoliceJson : {"createDate":"2020-04-15 08:44:18","iCy":1,"id":1047,"telephone":"400-2123-2211","vName":"人寿保险公司","vNameNature":"普通保险","yReportPoliceId":699902914180677632}
         * brandJson : {"brandName":"阿斯顿・马丁","groupName":"阿斯顿・马丁","id":87667,"parentId":716704210728517632,"sLogo":"/upload/logo/716723976784576512.jpg","sName":"2016款 5.2T 设计师定制版","seriesName":"阿斯顿・马丁DB11","vDispla":"4T","vYear":"2015","ySedanBrandId":716704933323210752}
         * userPhone : 18306043086
         * sNumber : 粤A12345
         * brandInfo : {"id":"87667","ySedanBrandId":"716704933323210752","parentId":"716704210728517632","sName":"2016款 5.2T 设计师定制版","sLogo":"/upload/logo/716723976784576512.jpg","seriesName":"阿斯顿・马丁DB11","groupName":"阿斯顿・马丁","brandName":"阿斯顿・马丁","vYear":"2015","vDispla":"4T"}
         * compInsuranceTime : 2021-07-09
         * comInsuranceTime : 2022-07-09
         * annualReviewTime : 2022-07-09
         * createDate : 2020-06-22 11:23:55
         */

        private String id;
        private String yUserSedanId;
        private String userId;
        private String sName;
        private String sLogo;
        private int sCy;
        private int isF;
        private int isDel;
        private String reportPoliceJson;
        private String jReportPoliceJson;
        private String brandJson;
        private String userPhone;
        private String sNumber;
        private BrandInfoBean brandInfo;
        private String compInsuranceTime;
        private String comInsuranceTime;
        private String annualReviewTime;
        private String createDate;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYUserSedanId() {
            return yUserSedanId;
        }

        public void setYUserSedanId(String yUserSedanId) {
            this.yUserSedanId = yUserSedanId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getSName() {
            return sName;
        }

        public void setSName(String sName) {
            this.sName = sName;
        }

        public String getSLogo() {
            return sLogo;
        }

        public void setSLogo(String sLogo) {
            this.sLogo = sLogo;
        }

        public int getSCy() {
            return sCy;
        }

        public void setSCy(int sCy) {
            this.sCy = sCy;
        }

        public int getIsF() {
            return isF;
        }

        public void setIsF(int isF) {
            this.isF = isF;
        }

        public int getIsDel() {
            return isDel;
        }

        public void setIsDel(int isDel) {
            this.isDel = isDel;
        }

        public String getReportPoliceJson() {
            return reportPoliceJson;
        }

        public void setReportPoliceJson(String reportPoliceJson) {
            this.reportPoliceJson = reportPoliceJson;
        }

        public String getJReportPoliceJson() {
            return jReportPoliceJson;
        }

        public void setJReportPoliceJson(String jReportPoliceJson) {
            this.jReportPoliceJson = jReportPoliceJson;
        }

        public String getBrandJson() {
            return brandJson;
        }

        public void setBrandJson(String brandJson) {
            this.brandJson = brandJson;
        }

        public String getUserPhone() {
            return userPhone;
        }

        public void setUserPhone(String userPhone) {
            this.userPhone = userPhone;
        }

        public String getSNumber() {
            return sNumber;
        }

        public void setSNumber(String sNumber) {
            this.sNumber = sNumber;
        }

        public BrandInfoBean getBrandInfo() {
            return brandInfo;
        }

        public void setBrandInfo(BrandInfoBean brandInfo) {
            this.brandInfo = brandInfo;
        }

        public String getCompInsuranceTime() {
            return compInsuranceTime;
        }

        public void setCompInsuranceTime(String compInsuranceTime) {
            this.compInsuranceTime = compInsuranceTime;
        }

        public String getComInsuranceTime() {
            return comInsuranceTime;
        }

        public void setComInsuranceTime(String comInsuranceTime) {
            this.comInsuranceTime = comInsuranceTime;
        }

        public String getAnnualReviewTime() {
            return annualReviewTime;
        }

        public void setAnnualReviewTime(String annualReviewTime) {
            this.annualReviewTime = annualReviewTime;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public static class BrandInfoBean {
            /**
             * id : 87667
             * ySedanBrandId : 716704933323210752
             * parentId : 716704210728517632
             * sName : 2016款 5.2T 设计师定制版
             * sLogo : /upload/logo/716723976784576512.jpg
             * seriesName : 阿斯顿・马丁DB11
             * groupName : 阿斯顿・马丁
             * brandName : 阿斯顿・马丁
             * vYear : 2015
             * vDispla : 4T
             */

            private String id;
            private String ySedanBrandId;
            private String parentId;
            private String sName;
            private String sLogo;
            private String seriesName;
            private String groupName;
            private String brandName;
            private String vYear;
            private String vDispla;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getYSedanBrandId() {
                return ySedanBrandId;
            }

            public void setYSedanBrandId(String ySedanBrandId) {
                this.ySedanBrandId = ySedanBrandId;
            }

            public String getParentId() {
                return parentId;
            }

            public void setParentId(String parentId) {
                this.parentId = parentId;
            }

            public String getSName() {
                return sName;
            }

            public void setSName(String sName) {
                this.sName = sName;
            }

            public String getSLogo() {
                return sLogo;
            }

            public void setSLogo(String sLogo) {
                this.sLogo = sLogo;
            }

            public String getSeriesName() {
                return seriesName;
            }

            public void setSeriesName(String seriesName) {
                this.seriesName = seriesName;
            }

            public String getGroupName() {
                return groupName;
            }

            public void setGroupName(String groupName) {
                this.groupName = groupName;
            }

            public String getBrandName() {
                return brandName;
            }

            public void setBrandName(String brandName) {
                this.brandName = brandName;
            }

            public String getVYear() {
                return vYear;
            }

            public void setVYear(String vYear) {
                this.vYear = vYear;
            }

            public String getVDispla() {
                return vDispla;
            }

            public void setVDispla(String vDispla) {
                this.vDispla = vDispla;
            }
        }
    }

    public static class OrderServiceListBean {
        /**
         * id : 90
         * yOrderServiceId : 730059153384407040
         * yStoreId : 692341585785913344
         * yStoreServiceId : 692341585785913353
         * yOrderId : 730059153300520960
         * iClassify : 1
         * gPrice : 100.0
         * gState : 1
         * serviceStr : 右前门
         * servicesJson : {"id":1032,"isSheet":1,"lineupSum":10,"pictureStr":"/upload/2020-06-02/20200602102222_621580.png","sPrice":100.0,"yState":1,"yStateValue":"右前门","yStoreId":692341585785913344,"yStoreServiceId":692341585785913353}
         * createDate : 2020-07-07 13:54:26
         * order_goods_list : [{"id":"108","yOrderGoodsId":"730059153325686784","yStoreServiceId":"692341585785913353","userId":"714547022807433216","isInstall":1,"isService":2,"goodsValue":"","goodsJson":"{\"createDate\":\"2019-09-27 10:31:19\",\"gDesc\":\"适用对象: 汽车跟踪器 附加功能: 录音功能  颜色分类: 以下套餐都有：10重精准定\",\"gIntegral\":500,\"gName\":\"gps定位器小型车载牛追跟定仪器远程录音订位汽车辆跟踪追踪神器\",\"gPrice\":1000.0,\"gState\":1,\"id\":1017,\"imgStr\":\"/upload/pro.png||/upload/pro.png\",\"isIntegral\":1,\"isPopular\":1,\"isSoffer\":1,\"orPrice\":3000.0,\"yClassifyId\":692341585785913344,\"yGoodsId\":692341585785913344,\"yStoreId\":692341585785913344}","yGoodsId":"692341585785913344","yOrderId":"730059153300520960","gNum":4,"gPrice":4000,"createDate":"2020-07-07 13:54:25","goods_info":{"id":"1017","yGoodsId":"692341585785913344","yClassifyId":"692341585785913344","yStoreId":"692341585785913344","gName":"gps定位器小型车载牛追跟定仪器远程录音订位汽车辆跟踪追踪神器","gDesc":"适用对象: 汽车跟踪器 附加功能: 录音功能  颜色分类: 以下套餐都有：10重精准定","gPrice":1000,"orPrice":3000,"imgStr":"/upload/pro.png||/upload/pro.png","imgArr":["/upload/pro.png","/upload/pro.png"],"gImg":"/upload/pro.png","gState":1,"isPopular":1,"isIntegral":1,"gIntegral":500,"createDate":"2019-09-27 10:31:19","isSoffer":1}},{"id":"109","yOrderGoodsId":"730059153355046912","yStoreServiceId":"692341585785913353","userId":"714547022807433216","isInstall":1,"isService":2,"goodsValue":"绿色||ml","goodsJson":"{\"createDate\":\"2019-09-27 10:31:19\",\"gDesc\":\"适用捷渡凌度360小米家盯盯拍70迈正际行车记录仪降压线停车监控\",\"gIntegral\":0,\"gName\":\"百魅 汽车遮阳挡6件套防晒隔热遮阳板挡阳板加厚前档太阳后挡侧挡\",\"gPrice\":1000.0,\"gState\":1,\"id\":1018,\"imgStr\":\"/upload/pro.png||/upload/pro.png\",\"isIntegral\":0,\"isPopular\":1,\"isSoffer\":1,\"orPrice\":3000.0,\"yClassifyId\":692341585785913344,\"yGoodsId\":692341585785913345,\"yStoreId\":692341585785913344}","yGoodsId":"692341585785913345","yOrderId":"730059153300520960","gNum":1,"gPrice":1180,"createDate":"2020-07-07 13:54:26","goods_info":{"id":"1018","yGoodsId":"692341585785913345","yClassifyId":"692341585785913344","yStoreId":"692341585785913344","gName":"百魅 汽车遮阳挡6件套防晒隔热遮阳板挡阳板加厚前档太阳后挡侧挡","gDesc":"适用捷渡凌度360小米家盯盯拍70迈正际行车记录仪降压线停车监控","gPrice":1000,"orPrice":3000,"imgStr":"/upload/pro.png||/upload/pro.png","imgArr":["/upload/pro.png","/upload/pro.png"],"gImg":"/upload/pro.png","gState":1,"isPopular":1,"isIntegral":0,"gIntegral":0,"createDate":"2019-09-27 10:31:19","isSoffer":1}}]
         * order_service_price : 5280.0
         */

        private String id;
        private String yOrderServiceId;
        private String yStoreId;
        private String yStoreServiceId;
        private String yOrderId;
        private int iClassify;
        private double gPrice;
        private int gState;
        private String serviceStr;
        private String servicesJson;
        private String createDate;
        private double order_service_price;
        private List<OrderGoodsListBean> order_goods_list;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYOrderServiceId() {
            return yOrderServiceId;
        }

        public void setYOrderServiceId(String yOrderServiceId) {
            this.yOrderServiceId = yOrderServiceId;
        }

        public String getYStoreId() {
            return yStoreId;
        }

        public void setYStoreId(String yStoreId) {
            this.yStoreId = yStoreId;
        }

        public String getYStoreServiceId() {
            return yStoreServiceId;
        }

        public void setYStoreServiceId(String yStoreServiceId) {
            this.yStoreServiceId = yStoreServiceId;
        }

        public String getYOrderId() {
            return yOrderId;
        }

        public void setYOrderId(String yOrderId) {
            this.yOrderId = yOrderId;
        }

        public int getIClassify() {
            return iClassify;
        }

        public void setIClassify(int iClassify) {
            this.iClassify = iClassify;
        }

        public double getGPrice() {
            return gPrice;
        }

        public void setGPrice(double gPrice) {
            this.gPrice = gPrice;
        }

        public int getGState() {
            return gState;
        }

        public void setGState(int gState) {
            this.gState = gState;
        }

        public String getServiceStr() {
            return serviceStr;
        }

        public void setServiceStr(String serviceStr) {
            this.serviceStr = serviceStr;
        }

        public String getServicesJson() {
            return servicesJson;
        }

        public void setServicesJson(String servicesJson) {
            this.servicesJson = servicesJson;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public double getOrder_service_price() {
            return order_service_price;
        }

        public void setOrder_service_price(double order_service_price) {
            this.order_service_price = order_service_price;
        }

        public List<OrderGoodsListBean> getOrder_goods_list() {
            return order_goods_list;
        }

        public void setOrder_goods_list(List<OrderGoodsListBean> order_goods_list) {
            this.order_goods_list = order_goods_list;
        }

        public static class OrderGoodsListBean {
            /**
             * id : 108
             * yOrderGoodsId : 730059153325686784
             * yStoreServiceId : 692341585785913353
             * userId : 714547022807433216
             * isInstall : 1
             * isService : 2
             * goodsValue :
             * goodsJson : {"createDate":"2019-09-27 10:31:19","gDesc":"适用对象: 汽车跟踪器 附加功能: 录音功能  颜色分类: 以下套餐都有：10重精准定","gIntegral":500,"gName":"gps定位器小型车载牛追跟定仪器远程录音订位汽车辆跟踪追踪神器","gPrice":1000.0,"gState":1,"id":1017,"imgStr":"/upload/pro.png||/upload/pro.png","isIntegral":1,"isPopular":1,"isSoffer":1,"orPrice":3000.0,"yClassifyId":692341585785913344,"yGoodsId":692341585785913344,"yStoreId":692341585785913344}
             * yGoodsId : 692341585785913344
             * yOrderId : 730059153300520960
             * gNum : 4
             * gPrice : 4000.0
             * createDate : 2020-07-07 13:54:25
             * goods_info : {"id":"1017","yGoodsId":"692341585785913344","yClassifyId":"692341585785913344","yStoreId":"692341585785913344","gName":"gps定位器小型车载牛追跟定仪器远程录音订位汽车辆跟踪追踪神器","gDesc":"适用对象: 汽车跟踪器 附加功能: 录音功能  颜色分类: 以下套餐都有：10重精准定","gPrice":1000,"orPrice":3000,"imgStr":"/upload/pro.png||/upload/pro.png","imgArr":["/upload/pro.png","/upload/pro.png"],"gImg":"/upload/pro.png","gState":1,"isPopular":1,"isIntegral":1,"gIntegral":500,"createDate":"2019-09-27 10:31:19","isSoffer":1}
             */

            private String id;
            private String yOrderGoodsId;
            private String yStoreServiceId;
            private String userId;
            private int isInstall;
            private int isService;
            private String goodsValue;
            private String goodsJson;
            private String yGoodsId;
            private String yOrderId;
            private int gNum;
            private double gPrice;
            private String createDate;
            private GoodsInfoBean goods_info;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getYOrderGoodsId() {
                return yOrderGoodsId;
            }

            public void setYOrderGoodsId(String yOrderGoodsId) {
                this.yOrderGoodsId = yOrderGoodsId;
            }

            public String getYStoreServiceId() {
                return yStoreServiceId;
            }

            public void setYStoreServiceId(String yStoreServiceId) {
                this.yStoreServiceId = yStoreServiceId;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public int getIsInstall() {
                return isInstall;
            }

            public void setIsInstall(int isInstall) {
                this.isInstall = isInstall;
            }

            public int getIsService() {
                return isService;
            }

            public void setIsService(int isService) {
                this.isService = isService;
            }

            public String getGoodsValue() {
                return goodsValue;
            }

            public void setGoodsValue(String goodsValue) {
                this.goodsValue = goodsValue;
            }

            public String getGoodsJson() {
                return goodsJson;
            }

            public void setGoodsJson(String goodsJson) {
                this.goodsJson = goodsJson;
            }

            public String getYGoodsId() {
                return yGoodsId;
            }

            public void setYGoodsId(String yGoodsId) {
                this.yGoodsId = yGoodsId;
            }

            public String getYOrderId() {
                return yOrderId;
            }

            public void setYOrderId(String yOrderId) {
                this.yOrderId = yOrderId;
            }

            public int getGNum() {
                return gNum;
            }

            public void setGNum(int gNum) {
                this.gNum = gNum;
            }

            public double getGPrice() {
                return gPrice;
            }

            public void setGPrice(double gPrice) {
                this.gPrice = gPrice;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public GoodsInfoBean getGoods_info() {
                return goods_info;
            }

            public void setGoods_info(GoodsInfoBean goods_info) {
                this.goods_info = goods_info;
            }

            public static class GoodsInfoBean {
                /**
                 * id : 1017
                 * yGoodsId : 692341585785913344
                 * yClassifyId : 692341585785913344
                 * yStoreId : 692341585785913344
                 * gName : gps定位器小型车载牛追跟定仪器远程录音订位汽车辆跟踪追踪神器
                 * gDesc : 适用对象: 汽车跟踪器 附加功能: 录音功能  颜色分类: 以下套餐都有：10重精准定
                 * gPrice : 1000.0
                 * orPrice : 3000.0
                 * imgStr : /upload/pro.png||/upload/pro.png
                 * imgArr : ["/upload/pro.png","/upload/pro.png"]
                 * gImg : /upload/pro.png
                 * gState : 1
                 * isPopular : 1
                 * isIntegral : 1
                 * gIntegral : 500
                 * createDate : 2019-09-27 10:31:19
                 * isSoffer : 1
                 */

                private String id;
                private String yGoodsId;
                private String yClassifyId;
                private String yStoreId;
                private String gName;
                private String gDesc;
                private double gPrice;
                private double orPrice;
                private String imgStr;
                private String gImg;
                private int gState;
                private int isPopular;
                private int isIntegral;
                private int gIntegral;
                private String createDate;
                private int isSoffer;
                private List<String> imgArr;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getYGoodsId() {
                    return yGoodsId;
                }

                public void setYGoodsId(String yGoodsId) {
                    this.yGoodsId = yGoodsId;
                }

                public String getYClassifyId() {
                    return yClassifyId;
                }

                public void setYClassifyId(String yClassifyId) {
                    this.yClassifyId = yClassifyId;
                }

                public String getYStoreId() {
                    return yStoreId;
                }

                public void setYStoreId(String yStoreId) {
                    this.yStoreId = yStoreId;
                }

                public String getGName() {
                    return gName;
                }

                public void setGName(String gName) {
                    this.gName = gName;
                }

                public String getGDesc() {
                    return gDesc;
                }

                public void setGDesc(String gDesc) {
                    this.gDesc = gDesc;
                }

                public double getGPrice() {
                    return gPrice;
                }

                public void setGPrice(double gPrice) {
                    this.gPrice = gPrice;
                }

                public double getOrPrice() {
                    return orPrice;
                }

                public void setOrPrice(double orPrice) {
                    this.orPrice = orPrice;
                }

                public String getImgStr() {
                    return imgStr;
                }

                public void setImgStr(String imgStr) {
                    this.imgStr = imgStr;
                }

                public String getGImg() {
                    return gImg;
                }

                public void setGImg(String gImg) {
                    this.gImg = gImg;
                }

                public int getGState() {
                    return gState;
                }

                public void setGState(int gState) {
                    this.gState = gState;
                }

                public int getIsPopular() {
                    return isPopular;
                }

                public void setIsPopular(int isPopular) {
                    this.isPopular = isPopular;
                }

                public int getIsIntegral() {
                    return isIntegral;
                }

                public void setIsIntegral(int isIntegral) {
                    this.isIntegral = isIntegral;
                }

                public int getGIntegral() {
                    return gIntegral;
                }

                public void setGIntegral(int gIntegral) {
                    this.gIntegral = gIntegral;
                }

                public String getCreateDate() {
                    return createDate;
                }

                public void setCreateDate(String createDate) {
                    this.createDate = createDate;
                }

                public int getIsSoffer() {
                    return isSoffer;
                }

                public void setIsSoffer(int isSoffer) {
                    this.isSoffer = isSoffer;
                }

                public List<String> getImgArr() {
                    return imgArr;
                }

                public void setImgArr(List<String> imgArr) {
                    this.imgArr = imgArr;
                }
            }
        }
    }
}
