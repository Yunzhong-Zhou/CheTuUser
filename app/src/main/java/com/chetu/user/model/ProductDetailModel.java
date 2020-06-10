package com.chetu.user.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2020/6/10.
 */
public class ProductDetailModel implements Serializable {
    /**
     * specific_list : [{"id":"1022","yGoodsId":"692341585785913350","gName":"滑垫卡扣式座","gPrice":1000,"orPrice":0,"gDetails":"","imgStr":"/static/img/20200528151327.png||/static/img/20200528151327.png","imgArr":["/static/img/20200528151327.png","/static/img/20200528151327.png"],"gState":1,"createDate":"2019-09-27 10:31:19"}]
     * info : {"id":"1022","yGoodsId":"692341585785913350","gName":"滑垫卡扣式座","gPrice":1000,"orPrice":0,"gDetails":"","imgStr":"/static/img/20200528151327.png||/static/img/20200528151327.png","imgArr":["/static/img/20200528151327.png","/static/img/20200528151327.png"],"gState":1,"createDate":"2019-09-27 10:31:19"}
     */

    private InfoBean info;
    private List<SpecificListBean> specific_list;

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public List<SpecificListBean> getSpecific_list() {
        return specific_list;
    }

    public void setSpecific_list(List<SpecificListBean> specific_list) {
        this.specific_list = specific_list;
    }

    public static class InfoBean {
        /**
         * id : 1022
         * yGoodsId : 692341585785913350
         * gName : 滑垫卡扣式座
         * gPrice : 1000.0
         * orPrice : 0.0
         * gDetails :
         * imgStr : /static/img/20200528151327.png||/static/img/20200528151327.png
         * imgArr : ["/static/img/20200528151327.png","/static/img/20200528151327.png"]
         * gState : 1
         * createDate : 2019-09-27 10:31:19
         */

        private String id;
        private String yGoodsId;
        private String gName;
        private double gPrice;
        private double orPrice;
        private String gDetails;
        private String imgStr;
        private int gState;
        private String createDate;
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

        public String getGName() {
            return gName;
        }

        public void setGName(String gName) {
            this.gName = gName;
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

        public String getGDetails() {
            return gDetails;
        }

        public void setGDetails(String gDetails) {
            this.gDetails = gDetails;
        }

        public String getImgStr() {
            return imgStr;
        }

        public void setImgStr(String imgStr) {
            this.imgStr = imgStr;
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

        public List<String> getImgArr() {
            return imgArr;
        }

        public void setImgArr(List<String> imgArr) {
            this.imgArr = imgArr;
        }
    }

    public static class SpecificListBean {
        /**
         * id : 1022
         * yGoodsId : 692341585785913350
         * gName : 滑垫卡扣式座
         * gPrice : 1000.0
         * orPrice : 0.0
         * gDetails :
         * imgStr : /static/img/20200528151327.png||/static/img/20200528151327.png
         * imgArr : ["/static/img/20200528151327.png","/static/img/20200528151327.png"]
         * gState : 1
         * createDate : 2019-09-27 10:31:19
         */

        private String id;
        private String yGoodsId;
        private String gName;
        private double gPrice;
        private double orPrice;
        private String gDetails;
        private String imgStr;
        private int gState;
        private String createDate;
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

        public String getGName() {
            return gName;
        }

        public void setGName(String gName) {
            this.gName = gName;
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

        public String getGDetails() {
            return gDetails;
        }

        public void setGDetails(String gDetails) {
            this.gDetails = gDetails;
        }

        public String getImgStr() {
            return imgStr;
        }

        public void setImgStr(String imgStr) {
            this.imgStr = imgStr;
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

        public List<String> getImgArr() {
            return imgArr;
        }

        public void setImgArr(List<String> imgArr) {
            this.imgArr = imgArr;
        }
    }
}
