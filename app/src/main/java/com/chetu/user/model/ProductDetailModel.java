package com.chetu.user.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2020/6/10.
 */
public class ProductDetailModel implements Serializable {
    /**
     * collection_info : {"id":"18","yUserCollectionId":"721420298234101760","userId":"714547022807433216","yId":"692341585785913346","category":1,"createDate":"2020-06-13 17:46:42"}
     * specific_list : [{"id":"1028","yGoodsSpecificId":"692341585785913353","yGoodsId":"692341585785913346","sName":"颜色","sValue":"红色||黑色||蓝色||白色","gPrice":200},{"id":"1029","yGoodsSpecificId":"692341585785913351","yGoodsId":"692341585785913346","sName":"尺寸","sValue":"XXL||XL","gPrice":200}]
     * info : {"id":"1019","yGoodsId":"692341585785913346","yClassifyId":"692341585785913344","yStoreId":"692341585785913344","gName":"车载香水","gPrice":1000,"orPrice":0,"gDetails":"","imgStr":"/static/img/20200528151327.png||/static/img/20200528151327.png","imgArr":["/static/img/20200528151327.png","/static/img/20200528151327.png"],"gImg":"/static/img/20200528151327.png","gState":1,"createDate":"2019-09-27 10:31:19"}
     */

    private CollectionInfoBean collection_info;
    private InfoBean info;
    private List<SpecificListBean> specific_list;

    public CollectionInfoBean getCollection_info() {
        return collection_info;
    }

    public void setCollection_info(CollectionInfoBean collection_info) {
        this.collection_info = collection_info;
    }

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

    public static class CollectionInfoBean {
        /**
         * id : 18
         * yUserCollectionId : 721420298234101760
         * userId : 714547022807433216
         * yId : 692341585785913346
         * category : 1
         * createDate : 2020-06-13 17:46:42
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

    public static class InfoBean {
        /**
         * id : 1019
         * yGoodsId : 692341585785913346
         * yClassifyId : 692341585785913344
         * yStoreId : 692341585785913344
         * gName : 车载香水
         * gPrice : 1000.0
         * orPrice : 0.0
         * gDetails :
         * imgStr : /static/img/20200528151327.png||/static/img/20200528151327.png
         * imgArr : ["/static/img/20200528151327.png","/static/img/20200528151327.png"]
         * gImg : /static/img/20200528151327.png
         * gState : 1
         * createDate : 2019-09-27 10:31:19
         */

        private String id;
        private String yGoodsId;
        private String yClassifyId;
        private String yStoreId;
        private String gName;
        private double gPrice;
        private double orPrice;
        private String gDetails;
        private String imgStr;
        private String gImg;
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
         * id : 1028
         * yGoodsSpecificId : 692341585785913353
         * yGoodsId : 692341585785913346
         * sName : 颜色
         * sValue : 红色||黑色||蓝色||白色
         * gPrice : 200.0
         */

        private String id;
        private String yGoodsSpecificId;
        private String yGoodsId;
        private String sName;
        private String sValue;
        private double gPrice;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYGoodsSpecificId() {
            return yGoodsSpecificId;
        }

        public void setYGoodsSpecificId(String yGoodsSpecificId) {
            this.yGoodsSpecificId = yGoodsSpecificId;
        }

        public String getYGoodsId() {
            return yGoodsId;
        }

        public void setYGoodsId(String yGoodsId) {
            this.yGoodsId = yGoodsId;
        }

        public String getSName() {
            return sName;
        }

        public void setSName(String sName) {
            this.sName = sName;
        }

        public String getSValue() {
            return sValue;
        }

        public void setSValue(String sValue) {
            this.sValue = sValue;
        }

        public double getGPrice() {
            return gPrice;
        }

        public void setGPrice(double gPrice) {
            this.gPrice = gPrice;
        }
    }
}
