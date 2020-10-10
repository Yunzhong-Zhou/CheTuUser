package com.chetu.user.model;

import java.util.List;

/**
 * Created by zyz on 2020/6/13.
 */
public class Fragment1ServiceListModel {
    private String daiban;

    public String getDaiban() {
        return daiban;
    }

    public void setDaiban(String daiban) {
        this.daiban = daiban;
    }

    private List<BannerListBean> banner_list;
    private List<IndexCustomListBean> index_custom_list;
    private List<IndexServiceListBean> index_service_list;

    public List<BannerListBean> getBanner_list() {
        return banner_list;
    }

    public void setBanner_list(List<BannerListBean> banner_list) {
        this.banner_list = banner_list;
    }

    public List<IndexCustomListBean> getIndex_custom_list() {
        return index_custom_list;
    }

    public void setIndex_custom_list(List<IndexCustomListBean> index_custom_list) {
        this.index_custom_list = index_custom_list;
    }

    public List<IndexServiceListBean> getIndex_service_list() {
        return index_service_list;
    }

    public void setIndex_service_list(List<IndexServiceListBean> index_service_list) {
        this.index_service_list = index_service_list;
    }

    public static class BannerListBean {
        /**
         * id : 167
         * yPhotoId : 692341585785913344
         * yServiceId : 692341585785913344
         * category : 1
         * msg : 首页数据
         * imgurl : /upload/banner.png
         * createDate : 2019-09-27 10:31:19
         */

        private String id;
        private String yPhotoId;
        private String yServiceId;
        private int category;
        private String msg;
        private String imgurl;
        private String createDate;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYPhotoId() {
            return yPhotoId;
        }

        public void setYPhotoId(String yPhotoId) {
            this.yPhotoId = yPhotoId;
        }

        public String getYServiceId() {
            return yServiceId;
        }

        public void setYServiceId(String yServiceId) {
            this.yServiceId = yServiceId;
        }

        public int getCategory() {
            return category;
        }

        public void setCategory(int category) {
            this.category = category;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }
    }

    public static class IndexCustomListBean {
        /**
         * id : 170
         * yPhotoId : 692341585785913347
         * yServiceId : 692341585785913344
         * category : 2
         * msg : 汽车用品
         * imgurl : /upload/index/index4.png
         * createDate : 2019-09-27 10:31:19
         */

        private String id;
        private String yPhotoId;
        private String yServiceId;
        private int category;
        private String msg;
        private String imgurl;
        private String createDate;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYPhotoId() {
            return yPhotoId;
        }

        public void setYPhotoId(String yPhotoId) {
            this.yPhotoId = yPhotoId;
        }

        public String getYServiceId() {
            return yServiceId;
        }

        public void setYServiceId(String yServiceId) {
            this.yServiceId = yServiceId;
        }

        public int getCategory() {
            return category;
        }

        public void setCategory(int category) {
            this.category = category;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }
    }

    public static class IndexServiceListBean {
        /**
         * id : 1017
         * yServiceId : 692341585785913344
         * yParentId : 0
         * vName : 洗车美容
         * vImg : /upload/index/index1.png
         * isIndex : 1
         * isSheet : 0
         * createDate : 2020-03-25 11:58:17
         */

        private String id;
        private String yServiceId;
        private String yParentId;
        private String vName;
        private String vImg;
        private int isIndex;
        private int isSheet;
        private String createDate;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYServiceId() {
            return yServiceId;
        }

        public void setYServiceId(String yServiceId) {
            this.yServiceId = yServiceId;
        }

        public String getYParentId() {
            return yParentId;
        }

        public void setYParentId(String yParentId) {
            this.yParentId = yParentId;
        }

        public String getVName() {
            return vName;
        }

        public void setVName(String vName) {
            this.vName = vName;
        }

        public String getVImg() {
            return vImg;
        }

        public void setVImg(String vImg) {
            this.vImg = vImg;
        }

        public int getIsIndex() {
            return isIndex;
        }

        public void setIsIndex(int isIndex) {
            this.isIndex = isIndex;
        }

        public int getIsSheet() {
            return isSheet;
        }

        public void setIsSheet(int isSheet) {
            this.isSheet = isSheet;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }
    }
}
