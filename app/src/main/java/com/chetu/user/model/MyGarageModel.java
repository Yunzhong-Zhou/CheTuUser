package com.chetu.user.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2020/6/5.
 */
public class MyGarageModel implements Serializable {
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 1070
         * yUserSedanId : 718457876498087936
         * userId : 714547022807433216
         * sName : 阿斯顿・马丁 阿斯顿・马丁DB9 2016款 6.0L GT邦德限量版
         * sLogo : /upload/head/716723976784576512.jpg
         * sCy : 1
         * isF : 1
         * isDel : 0
         * reportPoliceJson : {"createDate":"2020-04-15 08:44:18","id":1047,"telephone":"400-2123-2211","vName":"人寿保险公司","vNameNature":"普通保险","yReportPoliceId":699902914180677632}
         * jReportPoliceJson : {"createDate":"2020-04-15 08:44:18","id":1047,"telephone":"400-2123-2211","vName":"人寿保险公司","vNameNature":"普通保险","yReportPoliceId":699902914180677632}
         * brandJson : {"brandName":"阿斯顿・马丁","groupName":"阿斯顿・马丁","id":65668,"parentId":716704210623660032,"sLogo":"/upload/head/716723976784576512.jpg","sName":"2016款 6.0L GT邦德限量版","seriesName":"阿斯顿・马丁DB9","ySedanBrandId":716704364751749120}
         * userPhone : 18306043086
         * sNumber : 粤A12345
         * policeInfo : {"id":"1047","yReportPoliceId":"699902914180677632","vName":"人寿保险公司","vNameNature":"普通保险","telephone":"400-2123-2211","createDate":"2020-04-15 08:44:18"}
         * jpoliceInfo : {"id":"1047","yReportPoliceId":"699902914180677632","vName":"人寿保险公司","vNameNature":"普通保险","telephone":"400-2123-2211","createDate":"2020-04-15 08:44:18"}
         * brandInfo : {"id":"65668","ySedanBrandId":"716704364751749120","parentId":"716704210623660032","sName":"2016款 6.0L GT邦德限量版","sLogo":"/upload/head/716723976784576512.jpg","seriesName":"阿斯顿・马丁DB9","groupName":"阿斯顿・马丁","brandName":"阿斯顿・马丁"}
         * createDate : 2020-06-05 13:35:06
         */

        private String id;
        private String yUserSedanId;
        private String userId;
        private String sName;
        private String sLogo;
        private int sCy;
        private int isF;
        private int isDel;
        private String reportPoliceJson;
        private String jReportPoliceJson;
        private String brandJson;
        private String userPhone;
        private String sNumber;
        private PoliceInfoBean policeInfo;
        private JpoliceInfoBean jpoliceInfo;
        private BrandInfoBean brandInfo;
        private String createDate;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYUserSedanId() {
            return yUserSedanId;
        }

        public void setYUserSedanId(String yUserSedanId) {
            this.yUserSedanId = yUserSedanId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getSName() {
            return sName;
        }

        public void setSName(String sName) {
            this.sName = sName;
        }

        public String getSLogo() {
            return sLogo;
        }

        public void setSLogo(String sLogo) {
            this.sLogo = sLogo;
        }

        public int getSCy() {
            return sCy;
        }

        public void setSCy(int sCy) {
            this.sCy = sCy;
        }

        public int getIsF() {
            return isF;
        }

        public void setIsF(int isF) {
            this.isF = isF;
        }

        public int getIsDel() {
            return isDel;
        }

        public void setIsDel(int isDel) {
            this.isDel = isDel;
        }

        public String getReportPoliceJson() {
            return reportPoliceJson;
        }

        public void setReportPoliceJson(String reportPoliceJson) {
            this.reportPoliceJson = reportPoliceJson;
        }

        public String getJReportPoliceJson() {
            return jReportPoliceJson;
        }

        public void setJReportPoliceJson(String jReportPoliceJson) {
            this.jReportPoliceJson = jReportPoliceJson;
        }

        public String getBrandJson() {
            return brandJson;
        }

        public void setBrandJson(String brandJson) {
            this.brandJson = brandJson;
        }

        public String getUserPhone() {
            return userPhone;
        }

        public void setUserPhone(String userPhone) {
            this.userPhone = userPhone;
        }

        public String getSNumber() {
            return sNumber;
        }

        public void setSNumber(String sNumber) {
            this.sNumber = sNumber;
        }

        public PoliceInfoBean getPoliceInfo() {
            return policeInfo;
        }

        public void setPoliceInfo(PoliceInfoBean policeInfo) {
            this.policeInfo = policeInfo;
        }

        public JpoliceInfoBean getJpoliceInfo() {
            return jpoliceInfo;
        }

        public void setJpoliceInfo(JpoliceInfoBean jpoliceInfo) {
            this.jpoliceInfo = jpoliceInfo;
        }

        public BrandInfoBean getBrandInfo() {
            return brandInfo;
        }

        public void setBrandInfo(BrandInfoBean brandInfo) {
            this.brandInfo = brandInfo;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public static class PoliceInfoBean {
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

        public static class JpoliceInfoBean {
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

        public static class BrandInfoBean {
            /**
             * id : 65668
             * ySedanBrandId : 716704364751749120
             * parentId : 716704210623660032
             * sName : 2016款 6.0L GT邦德限量版
             * sLogo : /upload/head/716723976784576512.jpg
             * seriesName : 阿斯顿・马丁DB9
             * groupName : 阿斯顿・马丁
             * brandName : 阿斯顿・马丁
             */

            private String id;
            private String ySedanBrandId;
            private String parentId;
            private String sName;
            private String sLogo;
            private String seriesName;
            private String groupName;
            private String brandName;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getYSedanBrandId() {
                return ySedanBrandId;
            }

            public void setYSedanBrandId(String ySedanBrandId) {
                this.ySedanBrandId = ySedanBrandId;
            }

            public String getParentId() {
                return parentId;
            }

            public void setParentId(String parentId) {
                this.parentId = parentId;
            }

            public String getSName() {
                return sName;
            }

            public void setSName(String sName) {
                this.sName = sName;
            }

            public String getSLogo() {
                return sLogo;
            }

            public void setSLogo(String sLogo) {
                this.sLogo = sLogo;
            }

            public String getSeriesName() {
                return seriesName;
            }

            public void setSeriesName(String seriesName) {
                this.seriesName = seriesName;
            }

            public String getGroupName() {
                return groupName;
            }

            public void setGroupName(String groupName) {
                this.groupName = groupName;
            }

            public String getBrandName() {
                return brandName;
            }

            public void setBrandName(String brandName) {
                this.brandName = brandName;
            }
        }
    }
}
