package com.chetu.user.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2020/5/25.
 */
public class Fragment3Model implements Serializable {
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 1017
         * yStoreId : 692341585785913333
         * vName : 龙肆商店测试
         * review : 4.5分
         * keywsr :
         * address : 深圳市南山区
         * longitude : 116.49798
         * latitude : 39.916485
         * picture : /static/img/20200528151327.png
         * distance : 12246.42
         * phone :  400-2333-1123
         * vLevel :  A级
         * introduce :  门店介绍
         * charactStr : /static/img/20200528151327.png||/static/img/20200528151327.png
         * pictureStr : /static/img/20200528151327.png||/static/img/20200528151327.png
         * orderSum : 0
         * store_service_list : [{"id":"1024","yStoreServiceId":"692341585785913345","yStoreId":"692341585785913333","yState":1,"lineupSum":10,"isSheet":0,"yStateValue":"洗车","sPrice":100,"pictureStr":"/static/img/20200528151327.png||/static/img/20200528151327.png"}]
         */

        private String id;
        private String yStoreId;
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
        private int orderSum;
        private List<StoreServiceListBean> store_service_list;

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

        public int getOrderSum() {
            return orderSum;
        }

        public void setOrderSum(int orderSum) {
            this.orderSum = orderSum;
        }

        public List<StoreServiceListBean> getStore_service_list() {
            return store_service_list;
        }

        public void setStore_service_list(List<StoreServiceListBean> store_service_list) {
            this.store_service_list = store_service_list;
        }

        public static class StoreServiceListBean {
            /**
             * id : 1024
             * yStoreServiceId : 692341585785913345
             * yStoreId : 692341585785913333
             * yState : 1
             * lineupSum : 10
             * isSheet : 0
             * yStateValue : 洗车
             * sPrice : 100.0
             * pictureStr : /static/img/20200528151327.png||/static/img/20200528151327.png
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
