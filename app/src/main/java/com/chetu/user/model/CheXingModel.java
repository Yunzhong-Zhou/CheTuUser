package com.chetu.user.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2020/8/1.
 */
public class CheXingModel implements Serializable {
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 1030
         * yVioRegulatTypeId : 715582453690925056
         * vName : 大型汽车
         * vCode : 01
         */

        private String id;
        private String yVioRegulatTypeId;
        private String vName;
        private String vCode;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYVioRegulatTypeId() {
            return yVioRegulatTypeId;
        }

        public void setYVioRegulatTypeId(String yVioRegulatTypeId) {
            this.yVioRegulatTypeId = yVioRegulatTypeId;
        }

        public String getVName() {
            return vName;
        }

        public void setVName(String vName) {
            this.vName = vName;
        }

        public String getVCode() {
            return vCode;
        }

        public void setVCode(String vCode) {
            this.vCode = vCode;
        }
    }
}
