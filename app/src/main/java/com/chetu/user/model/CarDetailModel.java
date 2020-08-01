package com.chetu.user.model;

import java.io.Serializable;

/**
 * Created by zyz on 2020/6/5.
 */
public class CarDetailModel implements Serializable {
    /**
     * info : {"id":"1088","yUserSedanId":"724585460164198400","userId":"714547022807433216","sName":"2016款 5.2T 设计师定制版","sLogo":"/upload/logo/716723976784576512.jpg","sCy":1,"isF":1,"isDel":0,"reportPoliceJson":"{\"createDate\":\"2020-04-15 08:44:18\",\"iCy\":1,\"id\":1047,\"telephone\":\"400-2123-2211\",\"vName\":\"人寿保险公司\",\"vNameNature\":\"普通保险\",\"yReportPoliceId\":699902914180677632}","jReportPoliceJson":"{\"createDate\":\"2020-04-15 08:44:18\",\"iCy\":1,\"id\":1047,\"telephone\":\"400-2123-2211\",\"vName\":\"人寿保险公司\",\"vNameNature\":\"普通保险\",\"yReportPoliceId\":699902914180677632}","brandJson":"{\"brandName\":\"阿斯顿・马丁\",\"groupName\":\"阿斯顿・马丁\",\"id\":87667,\"parentId\":716704210728517632,\"sLogo\":\"/upload/logo/716723976784576512.jpg\",\"sName\":\"2016款 5.2T 设计师定制版\",\"seriesName\":\"阿斯顿・马丁DB11\",\"vDispla\":\"4T\",\"vYear\":\"2015\",\"ySedanBrandId\":716704933323210752}","userPhone":"18306043086","sNumber":"粤A12345","policeInfo":{"id":"1047","yReportPoliceId":"699902914180677632","vName":"人寿保险公司","vNameNature":"普通保险","telephone":"400-2123-2211","createDate":"2020-04-15 08:44:18","iCy":1},"jpoliceInfo":{"id":"1047","yReportPoliceId":"699902914180677632","vName":"人寿保险公司","vNameNature":"普通保险","telephone":"400-2123-2211","createDate":"2020-04-15 08:44:18","iCy":1},"brandInfo":{"id":"87667","ySedanBrandId":"716704933323210752","parentId":"716704210728517632","sName":"2016款 5.2T 设计师定制版","sLogo":"/upload/logo/716723976784576512.jpg","seriesName":"阿斯顿・马丁DB11","groupName":"阿斯顿・马丁","brandName":"阿斯顿・马丁","vYear":"2015","vDispla":"4T"},"compInsuranceTime":"2022-07-14","comInsuranceTime":"2023-07-14","annualReviewTime":"2021-07-14","createDate":"2020-06-22 11:23:55"}
     */

    private InfoBean info;

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public static class InfoBean {
        /**
         * id : 1088
         * yUserSedanId : 724585460164198400
         * userId : 714547022807433216
         * sName : 2016款 5.2T 设计师定制版
         * sLogo : /upload/logo/716723976784576512.jpg
         * sCy : 1
         * isF : 1
         * isDel : 0
         * reportPoliceJson : {"createDate":"2020-04-15 08:44:18","iCy":1,"id":1047,"telephone":"400-2123-2211","vName":"人寿保险公司","vNameNature":"普通保险","yReportPoliceId":699902914180677632}
         * jReportPoliceJson : {"createDate":"2020-04-15 08:44:18","iCy":1,"id":1047,"telephone":"400-2123-2211","vName":"人寿保险公司","vNameNature":"普通保险","yReportPoliceId":699902914180677632}
         * brandJson : {"brandName":"阿斯顿・马丁","groupName":"阿斯顿・马丁","id":87667,"parentId":716704210728517632,"sLogo":"/upload/logo/716723976784576512.jpg","sName":"2016款 5.2T 设计师定制版","seriesName":"阿斯顿・马丁DB11","vDispla":"4T","vYear":"2015","ySedanBrandId":716704933323210752}
         * userPhone : 18306043086
         * sNumber : 粤A12345
         * policeInfo : {"id":"1047","yReportPoliceId":"699902914180677632","vName":"人寿保险公司","vNameNature":"普通保险","telephone":"400-2123-2211","createDate":"2020-04-15 08:44:18","iCy":1}
         * jpoliceInfo : {"id":"1047","yReportPoliceId":"699902914180677632","vName":"人寿保险公司","vNameNature":"普通保险","telephone":"400-2123-2211","createDate":"2020-04-15 08:44:18","iCy":1}
         * brandInfo : {"id":"87667","ySedanBrandId":"716704933323210752","parentId":"716704210728517632","sName":"2016款 5.2T 设计师定制版","sLogo":"/upload/logo/716723976784576512.jpg","seriesName":"阿斯顿・马丁DB11","groupName":"阿斯顿・马丁","brandName":"阿斯顿・马丁","vYear":"2015","vDispla":"4T"}
         * compInsuranceTime : 2022-07-14
         * comInsuranceTime : 2023-07-14
         * annualReviewTime : 2021-07-14
         * createDate : 2020-06-22 11:23:55
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
        private String compInsuranceTime;
        private String comInsuranceTime;
        private String annualReviewTime;
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

        public String getCompInsuranceTime() {
            return compInsuranceTime;
        }

        public void setCompInsuranceTime(String compInsuranceTime) {
            this.compInsuranceTime = compInsuranceTime;
        }

        public String getComInsuranceTime() {
            return comInsuranceTime;
        }

        public void setComInsuranceTime(String comInsuranceTime) {
            this.comInsuranceTime = comInsuranceTime;
        }

        public String getAnnualReviewTime() {
            return annualReviewTime;
        }

        public void setAnnualReviewTime(String annualReviewTime) {
            this.annualReviewTime = annualReviewTime;
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
             * iCy : 1
             */

            private String id;
            private String yReportPoliceId;
            private String vName;
            private String vNameNature;
            private String telephone;
            private String createDate;
            private int iCy;

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

            public int getICy() {
                return iCy;
            }

            public void setICy(int iCy) {
                this.iCy = iCy;
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
             * iCy : 1
             */

            private String id;
            private String yReportPoliceId;
            private String vName;
            private String vNameNature;
            private String telephone;
            private String createDate;
            private int iCy;

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

            public int getICy() {
                return iCy;
            }

            public void setICy(int iCy) {
                this.iCy = iCy;
            }
        }

        public static class BrandInfoBean {
            /**
             * id : 87667
             * ySedanBrandId : 716704933323210752
             * parentId : 716704210728517632
             * sName : 2016款 5.2T 设计师定制版
             * sLogo : /upload/logo/716723976784576512.jpg
             * seriesName : 阿斯顿・马丁DB11
             * groupName : 阿斯顿・马丁
             * brandName : 阿斯顿・马丁
             * vYear : 2015
             * vDispla : 4T
             */

            private String id;
            private String ySedanBrandId;
            private String parentId;
            private String sName;
            private String sLogo;
            private String seriesName;
            private String groupName;
            private String brandName;
            private String vYear;
            private String vDispla;

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

            public String getVYear() {
                return vYear;
            }

            public void setVYear(String vYear) {
                this.vYear = vYear;
            }

            public String getVDispla() {
                return vDispla;
            }

            public void setVDispla(String vDispla) {
                this.vDispla = vDispla;
            }
        }
    }
}
