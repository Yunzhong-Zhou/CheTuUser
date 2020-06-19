package com.chetu.user.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2020/6/9.
 */
public class StoreDetailModel implements Serializable {
    /**
     * store_tech_list : []
     * store_service_list : [{"id":"1026","yStoreServiceId":"692341585785913347","yStoreId":"692341585785913346","yState":1,"lineupSum":10,"isSheet":0,"yStateValue":"美容","sPrice":100,"pictureStr":"/static/img/20200528151327.png"}]
     * info : {"id":"1019","yStoreId":"692341585785913346","userId":"663341570698772481","vName":"洗车商店测试","review":"5分","keywsr":"技术 修车 实","address":"观澜观光路与新丹路交叉口附近","longitude":"116.49798","latitude":"39.916485","distance":" ","phone":"  400-2333-1123","vLevel":"A级","introduce":"听广告几年了，终于体验了特色服务。轮胎是其专长。听说11年开始，线上业务。16年开始线下。现在全国1500多家店。","charactStr":"/upload/store/222.png||/upload/store/222.png","pictureStr":"/upload/store/222.png||/upload/store/222.png","slogan":"打腊 首单50","pictureArr":["/upload/store/222.png","/upload/store/222.png"],"charactArr":["/upload/store/222.png","/upload/store/222.png"],"orderSum":0,"isIndex":1,"isCollection":1,"colle_info":{"id":"25","yUserCollectionId":"723480951170007040","userId":"714547022807433216","yId":"692341585785913346","category":2,"createDate":"2020-06-19 10:15:00"}}
     */

    private InfoBean info;
    private List<?> store_tech_list;
    private List<StoreServiceListBean> store_service_list;

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public List<?> getStore_tech_list() {
        return store_tech_list;
    }

    public void setStore_tech_list(List<?> store_tech_list) {
        this.store_tech_list = store_tech_list;
    }

    public List<StoreServiceListBean> getStore_service_list() {
        return store_service_list;
    }

    public void setStore_service_list(List<StoreServiceListBean> store_service_list) {
        this.store_service_list = store_service_list;
    }

    public static class InfoBean {
        /**
         * id : 1019
         * yStoreId : 692341585785913346
         * userId : 663341570698772481
         * vName : 洗车商店测试
         * review : 5分
         * keywsr : 技术 修车 实
         * address : 观澜观光路与新丹路交叉口附近
         * longitude : 116.49798
         * latitude : 39.916485
         * distance :
         * phone :   400-2333-1123
         * vLevel : A级
         * introduce : 听广告几年了，终于体验了特色服务。轮胎是其专长。听说11年开始，线上业务。16年开始线下。现在全国1500多家店。
         * charactStr : /upload/store/222.png||/upload/store/222.png
         * pictureStr : /upload/store/222.png||/upload/store/222.png
         * slogan : 打腊 首单50
         * pictureArr : ["/upload/store/222.png","/upload/store/222.png"]
         * charactArr : ["/upload/store/222.png","/upload/store/222.png"]
         * orderSum : 0
         * isIndex : 1
         * isCollection : 1
         * colle_info : {"id":"25","yUserCollectionId":"723480951170007040","userId":"714547022807433216","yId":"692341585785913346","category":2,"createDate":"2020-06-19 10:15:00"}
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
        private String distance;
        private String phone;
        private String vLevel;
        private String introduce;
        private String charactStr;
        private String pictureStr;
        private String slogan;
        private int orderSum;
        private int isIndex;
        private int isCollection;
        private ColleInfoBean colle_info;
        private List<String> pictureArr;
        private List<String> charactArr;

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

        public int getOrderSum() {
            return orderSum;
        }

        public void setOrderSum(int orderSum) {
            this.orderSum = orderSum;
        }

        public int getIsIndex() {
            return isIndex;
        }

        public void setIsIndex(int isIndex) {
            this.isIndex = isIndex;
        }

        public int getIsCollection() {
            return isCollection;
        }

        public void setIsCollection(int isCollection) {
            this.isCollection = isCollection;
        }

        public ColleInfoBean getColle_info() {
            return colle_info;
        }

        public void setColle_info(ColleInfoBean colle_info) {
            this.colle_info = colle_info;
        }

        public List<String> getPictureArr() {
            return pictureArr;
        }

        public void setPictureArr(List<String> pictureArr) {
            this.pictureArr = pictureArr;
        }

        public List<String> getCharactArr() {
            return charactArr;
        }

        public void setCharactArr(List<String> charactArr) {
            this.charactArr = charactArr;
        }

        public static class ColleInfoBean {
            /**
             * id : 25
             * yUserCollectionId : 723480951170007040
             * userId : 714547022807433216
             * yId : 692341585785913346
             * category : 2
             * createDate : 2020-06-19 10:15:00
             */

            private String id;
            private String yUserCollectionId;
            private String userId;
            private String yId;
            private int category;
            private String createDate;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getYUserCollectionId() {
                return yUserCollectionId;
            }

            public void setYUserCollectionId(String yUserCollectionId) {
                this.yUserCollectionId = yUserCollectionId;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getYId() {
                return yId;
            }

            public void setYId(String yId) {
                this.yId = yId;
            }

            public int getCategory() {
                return category;
            }

            public void setCategory(int category) {
                this.category = category;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }
        }
    }

    public static class StoreServiceListBean {
        /**
         * id : 1026
         * yStoreServiceId : 692341585785913347
         * yStoreId : 692341585785913346
         * yState : 1
         * lineupSum : 10
         * isSheet : 0
         * yStateValue : 美容
         * sPrice : 100.0
         * pictureStr : /static/img/20200528151327.png
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
