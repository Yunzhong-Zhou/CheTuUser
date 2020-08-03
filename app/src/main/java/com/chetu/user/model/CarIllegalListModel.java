package com.chetu.user.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2020/8/2.
 */
public class CarIllegalListModel implements Serializable {
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 1031
         * yVioRegulatId : 719229457692360704
         * userId : 714547022807433216
         * yUserSedanId : 718106888251637760
         * licensePlate : 渝G34589
         * frameNo : asfdds123455533
         * engineNo : adds2466433
         * address : 54855
         * createDate : 2020-06-07 16:41:05
         * user_sedan_info : {"yUserSedanId":"718106888251637760","userId":"714547022807433216","sName":"2018款 530Le 豪华套装","sLogo":"/upload/logo/716723978600710144.jpg","sCy":1,"isF":0,"isDel":1,"iViolation":1,"reportPoliceJson":"null","jReportPoliceJson":"null","brandJson":"{\"brandName\":\"保时捷\",\"groupName\":\"保时捷\",\"id\":89395,\"parentId\":716704215585521664,\"sLogo\":\"/upload/logo/716723979414405120.jpg\",\"sName\":\"2016款 Cayenne S E-Hybrid 3.0T\",\"seriesName\":\"Cayenne新能源\",\"vDispla\":\"4T\",\"vYear\":\"2015\",\"ySedanBrandId\":716704981389934592}","userPhone":"13530039446","sNumber":"川D88888","compInsuranceTime":"2022-08-10","comInsuranceTime":"2022-08-10","annualReviewTime":"2022-08-10","maintainTime":"2020-12-04","createDate":"2020-06-07 16:41:05"}
         * msgJson : {"msg":"发动机号错误","success":false,"code":401,"data":{}}
         */

        private String id;
        private String yVioRegulatId;
        private String userId;
        private String yUserSedanId;
        private String licensePlate;
        private String frameNo;
        private String engineNo;
        private String address;
        private String createDate;
        private UserSedanInfoBean user_sedan_info;
        private String msgJson;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYVioRegulatId() {
            return yVioRegulatId;
        }

        public void setYVioRegulatId(String yVioRegulatId) {
            this.yVioRegulatId = yVioRegulatId;
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

        public String getLicensePlate() {
            return licensePlate;
        }

        public void setLicensePlate(String licensePlate) {
            this.licensePlate = licensePlate;
        }

        public String getFrameNo() {
            return frameNo;
        }

        public void setFrameNo(String frameNo) {
            this.frameNo = frameNo;
        }

        public String getEngineNo() {
            return engineNo;
        }

        public void setEngineNo(String engineNo) {
            this.engineNo = engineNo;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
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

        public String getMsgJson() {
            return msgJson;
        }

        public void setMsgJson(String msgJson) {
            this.msgJson = msgJson;
        }

        public static class UserSedanInfoBean {
            /**
             * yUserSedanId : 718106888251637760
             * userId : 714547022807433216
             * sName : 2018款 530Le 豪华套装
             * sLogo : /upload/logo/716723978600710144.jpg
             * sCy : 1
             * isF : 0
             * isDel : 1
             * iViolation : 1
             * reportPoliceJson : null
             * jReportPoliceJson : null
             * brandJson : {"brandName":"保时捷","groupName":"保时捷","id":89395,"parentId":716704215585521664,"sLogo":"/upload/logo/716723979414405120.jpg","sName":"2016款 Cayenne S E-Hybrid 3.0T","seriesName":"Cayenne新能源","vDispla":"4T","vYear":"2015","ySedanBrandId":716704981389934592}
             * userPhone : 13530039446
             * sNumber : 川D88888
             * compInsuranceTime : 2022-08-10
             * comInsuranceTime : 2022-08-10
             * annualReviewTime : 2022-08-10
             * maintainTime : 2020-12-04
             * createDate : 2020-06-07 16:41:05
             */

            private String yUserSedanId;
            private String userId;
            private String sName;
            private String sLogo;
            private int sCy;
            private int isF;
            private int isDel;
            private int iViolation;
            private String reportPoliceJson;
            private String jReportPoliceJson;
            private String brandJson;
            private String userPhone;
            private String sNumber;
            private String compInsuranceTime;
            private String comInsuranceTime;
            private String annualReviewTime;
            private String maintainTime;
            private String createDate;

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
    }
}
