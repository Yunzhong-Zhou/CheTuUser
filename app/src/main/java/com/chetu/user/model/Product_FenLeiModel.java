package com.chetu.user.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2020/8/27.
 */
public class Product_FenLeiModel implements Serializable {
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
         * yClassifyId : 692341585785913344
         * yName : 记录仪
         * imgStr : /static/img/20200528151327.png
         */

        private String id;
        private String yClassifyId;
        private String yName;
        private String imgStr;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYClassifyId() {
            return yClassifyId;
        }

        public void setYClassifyId(String yClassifyId) {
            this.yClassifyId = yClassifyId;
        }

        public String getYName() {
            return yName;
        }

        public void setYName(String yName) {
            this.yName = yName;
        }

        public String getImgStr() {
            return imgStr;
        }

        public void setImgStr(String imgStr) {
            this.imgStr = imgStr;
        }
    }
}
