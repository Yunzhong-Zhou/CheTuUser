package com.chetu.user.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2020/6/5.
 */
public class BaoXianModel implements Serializable {
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 1047
         * yReportPoliceId : 699902914180677632
         * vName : 人寿保险公司
         * vNameNature : 普通保险
         * telephone : 400-2123-2211
         * createDate : 2020-04-15 08:44:18
         */

        private String id;
        private String yReportPoliceId;
        private String vName;
        private String vNameNature;
        private String telephone;
        private String createDate;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYReportPoliceId() {
            return yReportPoliceId;
        }

        public void setYReportPoliceId(String yReportPoliceId) {
            this.yReportPoliceId = yReportPoliceId;
        }

        public String getVName() {
            return vName;
        }

        public void setVName(String vName) {
            this.vName = vName;
        }

        public String getVNameNature() {
            return vNameNature;
        }

        public void setVNameNature(String vNameNature) {
            this.vNameNature = vNameNature;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }
    }
}
