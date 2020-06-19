package com.chetu.user.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2020/6/19.
 */
public class WaitingReleaseModel implements Serializable {
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean implements Serializable{
        /**
         * id : 1125
         * yInquiryDemandId : 723630732731219968
         * userId : 714547022807433216
         * yUserSedanId : 718487184990011392
         * serviceName : 小保养套餐/大保养套餐/空调滤清器/节气门清洗/发动机清洗
         * serviceIdStr : 692341585785913349,692341585785913350,692341585785913351,692341585785913352,692341585785913353
         * storeIdStr : 692341585785913347
         * userSedanJson :
         * isOk : 1
         * createDate : 2020-06-19 20:10:11
         * user_sedan_info : {"id":"1079","yUserSedanId":"718487184990011392","userId":"714547022807433216","sName":"2004款 2.0 AT","sLogo":"/upload/logo/716723975845052416.jpg","sCy":2,"isF":0,"isDel":0,"iViolation":1,"reportPoliceJson":"","userPhone":"18306043086","sNumber":"鲁S5555","compInsuranceTime":"2020-12-04","comInsuranceTime":"2020-12-04","annualReviewTime":"2020-12-04","maintainTime":"2020-12-04","createDate":"2020-06-05 15:31:33"}
         * project_list : [{"id":"1422","yInquiryDemandProjectId":"723630732747997184","yInquiryDemandId":"723630732731219968","vTitle":"啊水电费","imgsrt":"/upload/2020-06-19/20200619201002_143138.png||/upload/2020-06-19/20200619201002_988798.png||/upload/2020-06-19/20200619201002_743689.png||/upload/2020-06-19/20200619201002_729092.png||/upload/2020-06-19/20200619201002_992218.png","imgArr":["/upload/2020-06-19/20200619201002_143138.png","/upload/2020-06-19/20200619201002_988798.png","/upload/2020-06-19/20200619201002_743689.png","/upload/2020-06-19/20200619201002_729092.png","/upload/2020-06-19/20200619201002_992218.png"],"createDate":"2020-06-19 20:10:11","isService":0,"offer_list":[]}]
         */

        private String id;
        private String yInquiryDemandId;
        private String userId;
        private String yUserSedanId;
        private String serviceName;
        private String serviceIdStr;
        private String storeIdStr;
        private String userSedanJson;
        private int isOk;
        private String createDate;
        private UserSedanInfoBean user_sedan_info;
        private List<ProjectListBean> project_list;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYInquiryDemandId() {
            return yInquiryDemandId;
        }

        public void setYInquiryDemandId(String yInquiryDemandId) {
            this.yInquiryDemandId = yInquiryDemandId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getYUserSedanId() {
            return yUserSedanId;
        }

        public void setYUserSedanId(String yUserSedanId) {
            this.yUserSedanId = yUserSedanId;
        }

        public String getServiceName() {
            return serviceName;
        }

        public void setServiceName(String serviceName) {
            this.serviceName = serviceName;
        }

        public String getServiceIdStr() {
            return serviceIdStr;
        }

        public void setServiceIdStr(String serviceIdStr) {
            this.serviceIdStr = serviceIdStr;
        }

        public String getStoreIdStr() {
            return storeIdStr;
        }

        public void setStoreIdStr(String storeIdStr) {
            this.storeIdStr = storeIdStr;
        }

        public String getUserSedanJson() {
            return userSedanJson;
        }

        public void setUserSedanJson(String userSedanJson) {
            this.userSedanJson = userSedanJson;
        }

        public int getIsOk() {
            return isOk;
        }

        public void setIsOk(int isOk) {
            this.isOk = isOk;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public UserSedanInfoBean getUser_sedan_info() {
            return user_sedan_info;
        }

        public void setUser_sedan_info(UserSedanInfoBean user_sedan_info) {
            this.user_sedan_info = user_sedan_info;
        }

        public List<ProjectListBean> getProject_list() {
            return project_list;
        }

        public void setProject_list(List<ProjectListBean> project_list) {
            this.project_list = project_list;
        }

        public static class UserSedanInfoBean {
            /**
             * id : 1079
             * yUserSedanId : 718487184990011392
             * userId : 714547022807433216
             * sName : 2004款 2.0 AT
             * sLogo : /upload/logo/716723975845052416.jpg
             * sCy : 2
             * isF : 0
             * isDel : 0
             * iViolation : 1
             * reportPoliceJson :
             * userPhone : 18306043086
             * sNumber : 鲁S5555
             * compInsuranceTime : 2020-12-04
             * comInsuranceTime : 2020-12-04
             * annualReviewTime : 2020-12-04
             * maintainTime : 2020-12-04
             * createDate : 2020-06-05 15:31:33
             */

            private String id;
            private String yUserSedanId;
            private String userId;
            private String sName;
            private String sLogo;
            private int sCy;
            private int isF;
            private int isDel;
            private int iViolation;
            private String reportPoliceJson;
            private String userPhone;
            private String sNumber;
            private String compInsuranceTime;
            private String comInsuranceTime;
            private String annualReviewTime;
            private String maintainTime;
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

            public int getIViolation() {
                return iViolation;
            }

            public void setIViolation(int iViolation) {
                this.iViolation = iViolation;
            }

            public String getReportPoliceJson() {
                return reportPoliceJson;
            }

            public void setReportPoliceJson(String reportPoliceJson) {
                this.reportPoliceJson = reportPoliceJson;
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

            public String getMaintainTime() {
                return maintainTime;
            }

            public void setMaintainTime(String maintainTime) {
                this.maintainTime = maintainTime;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }
        }

        public static class ProjectListBean {
            /**
             * id : 1422
             * yInquiryDemandProjectId : 723630732747997184
             * yInquiryDemandId : 723630732731219968
             * vTitle : 啊水电费
             * imgsrt : /upload/2020-06-19/20200619201002_143138.png||/upload/2020-06-19/20200619201002_988798.png||/upload/2020-06-19/20200619201002_743689.png||/upload/2020-06-19/20200619201002_729092.png||/upload/2020-06-19/20200619201002_992218.png
             * imgArr : ["/upload/2020-06-19/20200619201002_143138.png","/upload/2020-06-19/20200619201002_988798.png","/upload/2020-06-19/20200619201002_743689.png","/upload/2020-06-19/20200619201002_729092.png","/upload/2020-06-19/20200619201002_992218.png"]
             * createDate : 2020-06-19 20:10:11
             * isService : 0
             * offer_list : []
             */

            private String id;
            private String yInquiryDemandProjectId;
            private String yInquiryDemandId;
            private String vTitle;
            private String imgsrt;
            private String createDate;
            private int isService;
            private List<String> imgArr;
            private List<?> offer_list;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getYInquiryDemandProjectId() {
                return yInquiryDemandProjectId;
            }

            public void setYInquiryDemandProjectId(String yInquiryDemandProjectId) {
                this.yInquiryDemandProjectId = yInquiryDemandProjectId;
            }

            public String getYInquiryDemandId() {
                return yInquiryDemandId;
            }

            public void setYInquiryDemandId(String yInquiryDemandId) {
                this.yInquiryDemandId = yInquiryDemandId;
            }

            public String getVTitle() {
                return vTitle;
            }

            public void setVTitle(String vTitle) {
                this.vTitle = vTitle;
            }

            public String getImgsrt() {
                return imgsrt;
            }

            public void setImgsrt(String imgsrt) {
                this.imgsrt = imgsrt;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public int getIsService() {
                return isService;
            }

            public void setIsService(int isService) {
                this.isService = isService;
            }

            public List<String> getImgArr() {
                return imgArr;
            }

            public void setImgArr(List<String> imgArr) {
                this.imgArr = imgArr;
            }

            public List<?> getOffer_list() {
                return offer_list;
            }

            public void setOffer_list(List<?> offer_list) {
                this.offer_list = offer_list;
            }
        }
    }
}
