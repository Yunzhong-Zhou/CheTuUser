package com.chetu.user.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2020/7/3.
 */
public class ConfirmOrderModel implements Serializable {
    /**
     * service_list : [{"id":"1166","yCartId":"728581339992817664","yStoreId":"692341585785913344","userId":"714547022807433216","isService":1,"yStoreServiceId":"692341585785913353","isIntegral":0,"gIntegral":0,"yGoodsId":"0","store_service_info":{"id":"1032","yStoreServiceId":"692341585785913353","yStoreId":"692341585785913344","yState":1,"lineupSum":10,"isSheet":1,"yStateValue":"右前门","sPrice":100,"pictureStr":"/upload/2020-06-02/20200602102222_621580.png"},"createDate":"2020-07-03 12:02:07","vPrice":100,"gNum":1,"goods_cart_list":[]},{"id":"1167","yCartId":"728581340026372096","yStoreId":"692341585785913344","userId":"714547022807433216","isService":1,"yStoreServiceId":"692341585785913352","isIntegral":0,"gIntegral":0,"yGoodsId":"0","store_service_info":{"id":"1031","yStoreServiceId":"692341585785913352","yStoreId":"692341585785913344","yState":1,"lineupSum":10,"isSheet":1,"yStateValue":"在前门","sPrice":100,"pictureStr":"/upload/2020-06-02/20200602102222_621580.png"},"createDate":"2020-07-03 12:02:07","vPrice":100,"gNum":1,"goods_cart_list":[]},{"id":"1168","yCartId":"728581340064120832","yStoreId":"692341585785913344","userId":"714547022807433216","isService":1,"yStoreServiceId":"692341585785913351","isIntegral":0,"gIntegral":0,"yGoodsId":"0","store_service_info":{"id":"1030","yStoreServiceId":"692341585785913351","yStoreId":"692341585785913344","yState":1,"lineupSum":10,"isSheet":1,"yStateValue":"右叶板子","sPrice":100,"pictureStr":"/upload/2020-06-02/20200602102222_621580.png"},"createDate":"2020-07-03 12:02:07","vPrice":100,"gNum":1,"goods_cart_list":[]},{"id":"1169","yCartId":"728581340089286656","yStoreId":"692341585785913344","userId":"714547022807433216","isService":1,"yStoreServiceId":"692341585785913350","isIntegral":0,"gIntegral":0,"yGoodsId":"0","store_service_info":{"id":"1029","yStoreServiceId":"692341585785913350","yStoreId":"692341585785913344","yState":1,"lineupSum":10,"isSheet":0,"yStateValue":"机盖","sPrice":100,"pictureStr":"/upload/2020-06-02/20200602102222_621580.png"},"createDate":"2020-07-03 12:02:07","vPrice":100,"gNum":1,"goods_cart_list":[]},{"id":"1170","yCartId":"728581340131229696","yStoreId":"692341585785913344","userId":"714547022807433216","isService":1,"yStoreServiceId":"692341585785913349","isIntegral":0,"gIntegral":0,"yGoodsId":"0","store_service_info":{"id":"1028","yStoreServiceId":"692341585785913349","yStoreId":"692341585785913344","yState":1,"lineupSum":10,"isSheet":1,"yStateValue":"左叶板子","sPrice":100,"pictureStr":"/upload/2020-06-02/20200602102222_621580.png"},"createDate":"2020-07-03 12:02:07","vPrice":100,"gNum":1,"goods_cart_list":[]},{"id":"1171","yCartId":"728581340160589824","yStoreId":"692341585785913344","userId":"714547022807433216","isService":1,"yStoreServiceId":"692341585785913101","isIntegral":0,"gIntegral":0,"yGoodsId":"0","store_service_info":{"id":"1035","yStoreServiceId":"692341585785913101","yStoreId":"692341585785913344","yState":1,"lineupSum":10,"isSheet":1,"yStateValue":"维修二级2","sPrice":100,"pictureStr":"/upload/2020-06-02/20200602102222_621580.png"},"createDate":"2020-07-03 12:02:07","vPrice":100,"gNum":1,"goods_cart_list":[]},{"id":"1172","yCartId":"728581340185755648","yStoreId":"692341585785913344","userId":"714547022807433216","isService":1,"yStoreServiceId":"6923415857859139100","isIntegral":0,"gIntegral":0,"yGoodsId":"0","store_service_info":{"id":"1034","yStoreServiceId":"6923415857859139100","yStoreId":"692341585785913344","yState":1,"lineupSum":10,"isSheet":1,"yStateValue":"维修二级2","sPrice":100,"pictureStr":"/upload/2020-06-02/20200602102222_621580.png"},"createDate":"2020-07-03 12:02:07","vPrice":100,"gNum":1,"goods_cart_list":[]},{"id":"1173","yCartId":"728581340210921472","yStoreId":"692341585785913344","userId":"714547022807433216","isService":1,"yStoreServiceId":"692341585785913999","isIntegral":0,"gIntegral":0,"yGoodsId":"0","store_service_info":{"id":"1033","yStoreServiceId":"692341585785913999","yStoreId":"692341585785913344","yState":1,"lineupSum":10,"isSheet":1,"yStateValue":"维修二级1","sPrice":100,"pictureStr":"/upload/2020-06-02/20200602102222_621580.png"},"createDate":"2020-07-03 12:02:07","vPrice":100,"gNum":1,"goods_cart_list":[]}]
     * goods_cart_list : []
     * store_info : {"id":"1017","yStoreId":"692341585785913344","userId":"20180531104813","vName":"龙肆商店测试","review":"4.5","keywsr":"保养 修车 服务","address":"深圳市光明新区公明街道东环大道松白工业区C区2栋一层106号","longitude":"116.49798","latitude":"39.916485","picture":"/upload/store/222.png","distance":"12246.42","phone":" 400-2333-1123","vLevel":"A级","introduce":"听广告几年了，终于体验了特色服务。轮胎是其专长。听说11年开始，线上业务。16年开始线下。现在全国1500多家店。","charactStr":"/upload/store/222.png||/upload/store/222.png","pictureStr":"/upload/store/222.png||/upload/store/222.png","slogan":"商店测试  洗车首单10","isIndex":1}
     */

    private StoreInfoBean store_info;
    private List<ServiceListBean> service_list;
    private List<?> goods_cart_list;

    public StoreInfoBean getStore_info() {
        return store_info;
    }

    public void setStore_info(StoreInfoBean store_info) {
        this.store_info = store_info;
    }

    public List<ServiceListBean> getService_list() {
        return service_list;
    }

    public void setService_list(List<ServiceListBean> service_list) {
        this.service_list = service_list;
    }

    public List<?> getGoods_cart_list() {
        return goods_cart_list;
    }

    public void setGoods_cart_list(List<?> goods_cart_list) {
        this.goods_cart_list = goods_cart_list;
    }

    public static class StoreInfoBean {
        /**
         * id : 1017
         * yStoreId : 692341585785913344
         * userId : 20180531104813
         * vName : 龙肆商店测试
         * review : 4.5
         * keywsr : 保养 修车 服务
         * address : 深圳市光明新区公明街道东环大道松白工业区C区2栋一层106号
         * longitude : 116.49798
         * latitude : 39.916485
         * picture : /upload/store/222.png
         * distance : 12246.42
         * phone :  400-2333-1123
         * vLevel : A级
         * introduce : 听广告几年了，终于体验了特色服务。轮胎是其专长。听说11年开始，线上业务。16年开始线下。现在全国1500多家店。
         * charactStr : /upload/store/222.png||/upload/store/222.png
         * pictureStr : /upload/store/222.png||/upload/store/222.png
         * slogan : 商店测试  洗车首单10
         * isIndex : 1
         */

        private String id;
        private String yStoreId;
        private String userId;
        private String vName;
        private String review;
        private String keywsr;
        private String address;
        private String longitude;
        private String latitude;
        private String picture;
        private String distance;
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

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
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

    public static class ServiceListBean {
        /**
         * id : 1166
         * yCartId : 728581339992817664
         * yStoreId : 692341585785913344
         * userId : 714547022807433216
         * isService : 1
         * yStoreServiceId : 692341585785913353
         * isIntegral : 0
         * gIntegral : 0
         * yGoodsId : 0
         * store_service_info : {"id":"1032","yStoreServiceId":"692341585785913353","yStoreId":"692341585785913344","yState":1,"lineupSum":10,"isSheet":1,"yStateValue":"右前门","sPrice":100,"pictureStr":"/upload/2020-06-02/20200602102222_621580.png"}
         * createDate : 2020-07-03 12:02:07
         * vPrice : 100.0
         * gNum : 1
         * goods_cart_list : []
         */

        private String id;
        private String yCartId;
        private String yStoreId;
        private String userId;
        private int isService;
        private String yStoreServiceId;
        private int isIntegral;
        private int gIntegral;
        private String yGoodsId;
        private StoreServiceInfoBean store_service_info;
        private String createDate;
        private double vPrice;
        private int gNum;
        private List<?> goods_cart_list;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYCartId() {
            return yCartId;
        }

        public void setYCartId(String yCartId) {
            this.yCartId = yCartId;
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

        public int getIsService() {
            return isService;
        }

        public void setIsService(int isService) {
            this.isService = isService;
        }

        public String getYStoreServiceId() {
            return yStoreServiceId;
        }

        public void setYStoreServiceId(String yStoreServiceId) {
            this.yStoreServiceId = yStoreServiceId;
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

        public String getYGoodsId() {
            return yGoodsId;
        }

        public void setYGoodsId(String yGoodsId) {
            this.yGoodsId = yGoodsId;
        }

        public StoreServiceInfoBean getStore_service_info() {
            return store_service_info;
        }

        public void setStore_service_info(StoreServiceInfoBean store_service_info) {
            this.store_service_info = store_service_info;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public double getVPrice() {
            return vPrice;
        }

        public void setVPrice(double vPrice) {
            this.vPrice = vPrice;
        }

        public int getGNum() {
            return gNum;
        }

        public void setGNum(int gNum) {
            this.gNum = gNum;
        }

        public List<?> getGoods_cart_list() {
            return goods_cart_list;
        }

        public void setGoods_cart_list(List<?> goods_cart_list) {
            this.goods_cart_list = goods_cart_list;
        }

        public static class StoreServiceInfoBean {
            /**
             * id : 1032
             * yStoreServiceId : 692341585785913353
             * yStoreId : 692341585785913344
             * yState : 1
             * lineupSum : 10
             * isSheet : 1
             * yStateValue : 右前门
             * sPrice : 100.0
             * pictureStr : /upload/2020-06-02/20200602102222_621580.png
             */

            private String id;
            private String yStoreServiceId;
            private String yStoreId;
            private int yState;
            private int lineupSum;
            private int isSheet;
            private String yStateValue;
            private double sPrice;
            private String pictureStr;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getYStoreServiceId() {
                return yStoreServiceId;
            }

            public void setYStoreServiceId(String yStoreServiceId) {
                this.yStoreServiceId = yStoreServiceId;
            }

            public String getYStoreId() {
                return yStoreId;
            }

            public void setYStoreId(String yStoreId) {
                this.yStoreId = yStoreId;
            }

            public int getYState() {
                return yState;
            }

            public void setYState(int yState) {
                this.yState = yState;
            }

            public int getLineupSum() {
                return lineupSum;
            }

            public void setLineupSum(int lineupSum) {
                this.lineupSum = lineupSum;
            }

            public int getIsSheet() {
                return isSheet;
            }

            public void setIsSheet(int isSheet) {
                this.isSheet = isSheet;
            }

            public String getYStateValue() {
                return yStateValue;
            }

            public void setYStateValue(String yStateValue) {
                this.yStateValue = yStateValue;
            }

            public double getSPrice() {
                return sPrice;
            }

            public void setSPrice(double sPrice) {
                this.sPrice = sPrice;
            }

            public String getPictureStr() {
                return pictureStr;
            }

            public void setPictureStr(String pictureStr) {
                this.pictureStr = pictureStr;
            }
        }
    }
}
