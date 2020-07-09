package com.chetu.user.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2020/7/7.
 */
public class SelectTimeModel implements Serializable {
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 4
         * yStoreFreeTimeId : 721398813184491520
         * yStoreId : 692341585785913344
         * vNum : 10
         * vState : 1
         * tTime : 09
         * vDate : 2020-07-08
         */

        private String id;
        private String yStoreFreeTimeId;
        private String yStoreId;
        private int vNum;
        private int vState;
        private String tTime;
        private String vDate;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYStoreFreeTimeId() {
            return yStoreFreeTimeId;
        }

        public void setYStoreFreeTimeId(String yStoreFreeTimeId) {
            this.yStoreFreeTimeId = yStoreFreeTimeId;
        }

        public String getYStoreId() {
            return yStoreId;
        }

        public void setYStoreId(String yStoreId) {
            this.yStoreId = yStoreId;
        }

        public int getVNum() {
            return vNum;
        }

        public void setVNum(int vNum) {
            this.vNum = vNum;
        }

        public int getVState() {
            return vState;
        }

        public void setVState(int vState) {
            this.vState = vState;
        }

        public String getTTime() {
            return tTime;
        }

        public void setTTime(String tTime) {
            this.tTime = tTime;
        }

        public String getVDate() {
            return vDate;
        }

        public void setVDate(String vDate) {
            this.vDate = vDate;
        }
    }
}
