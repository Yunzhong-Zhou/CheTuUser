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
         * id : 1307
         * yInquiryDemandId : 787731493102288896
         * userId : 776203170115223552
         * yUserSedanId : 782247596679757824
         * serviceName : 普洗
         * serviceIdStr : 692341585785913355
         * storeIdStr : 692341585785913347,692341585785913346,692341585785913344
         * userSedanJson : {"annualReviewTime":"","brandJson":"{\"brandName\":\"奥迪\",\"groupName\":\"一汽-大众奥迪\",\"iGrade\":4,\"id\":89813,\"parentId\":716704211135365120,\"sLogo\":\"/upload/logo/716723977224978432.jpg\",\"sName\":\"2018款 40 e-tron\",\"seriesName\":\"奥迪A6L新能源\",\"vDispla\":\"4T\",\"vYear\":\"2015\",\"ySedanBrandId\":716704993729576960}","comInsuranceTime":"","compInsuranceTime":"","createDate":"2020-11-28 14:12:40","id":1131,"isDel":0,"isF":1,"jReportPoliceJson":"{\"createDate\":\"2020-04-15 08:44:18\",\"iCy\":1,\"id\":1047,\"telephone\":\"400-2123-2211\",\"vName\":\"人寿保险公司\",\"vNameNature\":\"普通保险\",\"yReportPoliceId\":699902914180677632}","maintainTime":"2021-01-16","reportPoliceJson":"{\"createDate\":\"2020-04-15 08:44:18\",\"iCy\":1,\"id\":1047,\"telephone\":\"400-2123-2211\",\"vName\":\"人寿保险公司\",\"vNameNature\":\"普通保险\",\"yReportPoliceId\":699902914180677632}","sCy":1,"sLogo":"/upload/logo/716723977224978432.jpg","sName":"2018款 40 e-tron","sNumber":"粤BRJJJY","userId":776203170115223552,"userPhone":"18306043086","yUserSedanId":782247596679757824}
         * isOk : 3
         * vMsg : 二发个
         * createDate : 2020-12-13 17:23:43
         * user_sedan_info : {"id":"1131","yUserSedanId":"782247596679757824","userId":"776203170115223552","sName":"2018款 40 e-tron","sLogo":"/upload/logo/716723977224978432.jpg","sCy":1,"isF":1,"isDel":0,"reportPoliceJson":"{\"createDate\":\"2020-04-15 08:44:18\",\"iCy\":1,\"id\":1047,\"telephone\":\"400-2123-2211\",\"vName\":\"人寿保险公司\",\"vNameNature\":\"普通保险\",\"yReportPoliceId\":699902914180677632}","jReportPoliceJson":"{\"createDate\":\"2020-04-15 08:44:18\",\"iCy\":1,\"id\":1047,\"telephone\":\"400-2123-2211\",\"vName\":\"人寿保险公司\",\"vNameNature\":\"普通保险\",\"yReportPoliceId\":699902914180677632}","brandJson":"{\"brandName\":\"奥迪\",\"groupName\":\"一汽-大众奥迪\",\"iGrade\":4,\"id\":89813,\"parentId\":716704211135365120,\"sLogo\":\"/upload/logo/716723977224978432.jpg\",\"sName\":\"2018款 40 e-tron\",\"seriesName\":\"奥迪A6L新能源\",\"vDispla\":\"4T\",\"vYear\":\"2015\",\"ySedanBrandId\":716704993729576960}","userPhone":"18306043086","sNumber":"粤BRJJJY","brandInfo":{"id":"89813","ySedanBrandId":"716704993729576960","parentId":"716704211135365120","sName":"2018款 40 e-tron","sLogo":"/upload/logo/716723977224978432.jpg","seriesName":"奥迪A6L新能源","groupName":"一汽-大众奥迪","brandName":"奥迪","vYear":"2015","vDispla":"4T","iGrade":4},"compInsuranceTime":"","comInsuranceTime":"","annualReviewTime":"","maintainTime":"2021-01-16","createDate":"2020-11-28 14:12:40"}
         * selectStorelist : [{"yStoreId":"692341585785913346","store_info":{"id":"1041","yStoreId":"692341585785913346","userId":"715606113168392192","kfUserId":"715606113168392192","vName":"龙伍商店测试","review":"4.5","keywsr":"技术 修车 实","address":"深圳市光明新区公明街道东环大道松白工业区C区2栋一层106号","longitude":"116.49798","latitude":"39.916485","phone":" 400-2333-1123","vLevel":"B级","introduce":"听广告几年了，终于体验了特色服务。轮胎是其专长。听说11年开始，线上业务。16年开始线下。现在全国1500多家店。","charactStr":"||/upload/2020-07-09/20200709110807_317759.jpg||/upload/2020-07-09/20200709110817_957557.jpg||/upload/2020-07-09/20200709110819_726210.jpg","busHours":"早上9点至晚上7点","pictureStr":"||/upload/2020-07-09/20200709110836_560635.jpg||/upload/2020-07-09/20200709110838_972720.jpg||/upload/2020-07-09/20200709110842_192873.jpg","slogan":"商店测试  洗车首单10","isIndex":1},"project_offer_list":[{"id":"1162","yInquiryDemandProjectOfferId":"787731576212422656","yInquiryDemandProjectId":"787731493106483200","yInquiryDemandId":"787731493102288896","userId":"819539276219416586","yStoreId":"692341585785913346","isService":0,"storeJson":"{\"address\":\"深圳市光明新区公明街道东环大道松白工业区C区2栋一层106号\",\"busHours\":\"早上9点至晚上7点\",\"charactStr\":\"||/upload/2020-07-09/20200709110807_317759.jpg||/upload/2020-07-09/20200709110817_957557.jpg||/upload/2020-07-09/20200709110819_726210.jpg\",\"id\":1041,\"introduce\":\"听广告几年了，终于体验了特色服务。轮胎是其专长。听说11年开始，线上业务。16年开始线下。现在全国1500多家店。\",\"isIndex\":1,\"keywsr\":\"技术 修车 实\",\"kfUserId\":715606113168392192,\"latitude\":\"39.916485\",\"longitude\":\"116.49798\",\"phone\":\" 400-2333-1123\",\"pictureStr\":\"||/upload/2020-07-09/20200709110836_560635.jpg||/upload/2020-07-09/20200709110838_972720.jpg||/upload/2020-07-09/20200709110842_192873.jpg\",\"review\":\"4.5\",\"slogan\":\"商店测试  洗车首单10\",\"userId\":715606113168392192,\"vLevel\":\"B级\",\"vName\":\"龙伍商店测试\",\"yStoreId\":692341585785913346}","userJson":"{\"headPortrait\":\"/upload/2020-06-11/20200611090404_650532.png\",\"id\":0,\"userAccount\":\"THANU8\",\"userBalance\":0.0,\"userHash\":\"843B1049032A7E7C9F7D2E2CBE6D37A6\",\"userId\":0,\"userIntegral\":0,\"userName\":\"龙伍店铺技师\",\"userPhone\":13530039447,\"yStoreId\":0}","vPrice":"46775","createDate":"2020-12-13 17:24:03","store_info":{"id":"1041","yStoreId":"692341585785913346","userId":"715606113168392192","kfUserId":"715606113168392192","vName":"龙伍商店测试","review":"4.5","keywsr":"技术 修车 实","address":"深圳市光明新区公明街道东环大道松白工业区C区2栋一层106号","longitude":"116.49798","latitude":"39.916485","phone":" 400-2333-1123","vLevel":"B级","introduce":"听广告几年了，终于体验了特色服务。轮胎是其专长。听说11年开始，线上业务。16年开始线下。现在全国1500多家店。","charactStr":"||/upload/2020-07-09/20200709110807_317759.jpg||/upload/2020-07-09/20200709110817_957557.jpg||/upload/2020-07-09/20200709110819_726210.jpg","busHours":"早上9点至晚上7点","pictureStr":"||/upload/2020-07-09/20200709110836_560635.jpg||/upload/2020-07-09/20200709110838_972720.jpg||/upload/2020-07-09/20200709110842_192873.jpg","slogan":"商店测试  洗车首单10","isIndex":1},"udemand_project_info":{"id":"1862","yInquiryDemandProjectId":"787731493106483200","yInquiryDemandId":"787731493102288896","vTitle":"啊水电费","imgsrt":"/upload/2020-12-13/20201213172335_602733.jpg||/upload/2020-12-13/20201213172335_37831.jpg","imgArr":["/upload/2020-12-13/20201213172335_602733.jpg","/upload/2020-12-13/20201213172335_37831.jpg"],"createDate":"2020-12-13 17:23:43","isService":0}}]},{"yStoreId":"692341585785913344","store_info":{"id":"1017","yStoreId":"692341585785913344","userId":"714547022807433218","kfUserId":"714547022807433218","vName":"龙肆商店测试","review":"4.4","keywsr":"技术 修车 实","address":"深圳市光明新区公明街道东环大道松白工业区C区2栋一层106号","longitude":"116.49798","latitude":"39.916485","phone":" 400-2333-1123","vLevel":"A级","introduce":"听广告几年了，终于体验了特色服务。轮胎是其专长。听说11年开始，线上业务。16年开始线下。现在全国1500多家店。","charactStr":"||/upload/2020-07-09/20200709110807_317759.jpg||/upload/2020-07-09/20200709110817_957557.jpg||/upload/2020-07-09/20200709110819_726210.jpg","busHours":"早上9点至晚上7点","pictureStr":"||/upload/2020-07-09/20200709110836_560635.jpg||/upload/2020-07-09/20200709110838_972720.jpg||/upload/2020-07-09/20200709110842_192873.jpg","slogan":"商店测试  洗车首单10","isIndex":1},"project_offer_list":[{"id":"1163","yInquiryDemandProjectOfferId":"787732728140267520","yInquiryDemandProjectId":"787731493135843328","yInquiryDemandId":"787731493102288896","userId":"714547022807433218","yStoreId":"692341585785913344","isService":0,"storeJson":"{\"address\":\"深圳市光明新区公明街道东环大道松白工业区C区2栋一层106号\",\"busHours\":\"早上9点至晚上7点\",\"charactStr\":\"||/upload/2020-07-09/20200709110807_317759.jpg||/upload/2020-07-09/20200709110817_957557.jpg||/upload/2020-07-09/20200709110819_726210.jpg\",\"id\":1017,\"introduce\":\"听广告几年了，终于体验了特色服务。轮胎是其专长。听说11年开始，线上业务。16年开始线下。现在全国1500多家店。\",\"isIndex\":1,\"keywsr\":\"技术 修车 实\",\"kfUserId\":714547022807433218,\"latitude\":\"39.916485\",\"longitude\":\"116.49798\",\"phone\":\" 400-2333-1123\",\"pictureStr\":\"||/upload/2020-07-09/20200709110836_560635.jpg||/upload/2020-07-09/20200709110838_972720.jpg||/upload/2020-07-09/20200709110842_192873.jpg\",\"review\":\"4.4\",\"slogan\":\"商店测试  洗车首单10\",\"userId\":714547022807433218,\"vLevel\":\"A级\",\"vName\":\"龙肆商店测试\",\"yStoreId\":692341585785913344}","userJson":"{\"headPortrait\":\"/upload/2020-10-15/20201015175931_273079.jpg\",\"id\":0,\"userAccount\":\"6545764\",\"userBalance\":0.0,\"userHash\":\"36635C17B0E19DD1E14A92D8895B47C9\",\"userId\":0,\"userIntegral\":0,\"userName\":\"龙肆\",\"userPhone\":18203048656,\"yStoreId\":0}","vPrice":"5他干活","createDate":"2020-12-13 17:28:37","store_info":{"id":"1017","yStoreId":"692341585785913344","userId":"714547022807433218","kfUserId":"714547022807433218","vName":"龙肆商店测试","review":"4.4","keywsr":"技术 修车 实","address":"深圳市光明新区公明街道东环大道松白工业区C区2栋一层106号","longitude":"116.49798","latitude":"39.916485","phone":" 400-2333-1123","vLevel":"A级","introduce":"听广告几年了，终于体验了特色服务。轮胎是其专长。听说11年开始，线上业务。16年开始线下。现在全国1500多家店。","charactStr":"||/upload/2020-07-09/20200709110807_317759.jpg||/upload/2020-07-09/20200709110817_957557.jpg||/upload/2020-07-09/20200709110819_726210.jpg","busHours":"早上9点至晚上7点","pictureStr":"||/upload/2020-07-09/20200709110836_560635.jpg||/upload/2020-07-09/20200709110838_972720.jpg||/upload/2020-07-09/20200709110842_192873.jpg","slogan":"商店测试  洗车首单10","isIndex":1},"udemand_project_info":{"id":"1863","yInquiryDemandProjectId":"787731493135843328","yInquiryDemandId":"787731493102288896","vTitle":"普洗","createDate":"2020-12-13 17:23:43","isService":1,"vMsg":"是的方法"}},{"id":"1164","yInquiryDemandProjectOfferId":"787732728190599168","yInquiryDemandProjectId":"787731493106483200","yInquiryDemandId":"787731493102288896","userId":"714547022807433218","yStoreId":"692341585785913344","isService":0,"storeJson":"{\"address\":\"深圳市光明新区公明街道东环大道松白工业区C区2栋一层106号\",\"busHours\":\"早上9点至晚上7点\",\"charactStr\":\"||/upload/2020-07-09/20200709110807_317759.jpg||/upload/2020-07-09/20200709110817_957557.jpg||/upload/2020-07-09/20200709110819_726210.jpg\",\"id\":1017,\"introduce\":\"听广告几年了，终于体验了特色服务。轮胎是其专长。听说11年开始，线上业务。16年开始线下。现在全国1500多家店。\",\"isIndex\":1,\"keywsr\":\"技术 修车 实\",\"kfUserId\":714547022807433218,\"latitude\":\"39.916485\",\"longitude\":\"116.49798\",\"phone\":\" 400-2333-1123\",\"pictureStr\":\"||/upload/2020-07-09/20200709110836_560635.jpg||/upload/2020-07-09/20200709110838_972720.jpg||/upload/2020-07-09/20200709110842_192873.jpg\",\"review\":\"4.4\",\"slogan\":\"商店测试  洗车首单10\",\"userId\":714547022807433218,\"vLevel\":\"A级\",\"vName\":\"龙肆商店测试\",\"yStoreId\":692341585785913344}","userJson":"{\"headPortrait\":\"/upload/2020-10-15/20201015175931_273079.jpg\",\"id\":0,\"userAccount\":\"6545764\",\"userBalance\":0.0,\"userHash\":\"36635C17B0E19DD1E14A92D8895B47C9\",\"userId\":0,\"userIntegral\":0,\"userName\":\"龙肆\",\"userPhone\":18203048656,\"yStoreId\":0}","vPrice":"4他换个","createDate":"2020-12-13 17:28:37","store_info":{"id":"1017","yStoreId":"692341585785913344","userId":"714547022807433218","kfUserId":"714547022807433218","vName":"龙肆商店测试","review":"4.4","keywsr":"技术 修车 实","address":"深圳市光明新区公明街道东环大道松白工业区C区2栋一层106号","longitude":"116.49798","latitude":"39.916485","phone":" 400-2333-1123","vLevel":"A级","introduce":"听广告几年了，终于体验了特色服务。轮胎是其专长。听说11年开始，线上业务。16年开始线下。现在全国1500多家店。","charactStr":"||/upload/2020-07-09/20200709110807_317759.jpg||/upload/2020-07-09/20200709110817_957557.jpg||/upload/2020-07-09/20200709110819_726210.jpg","busHours":"早上9点至晚上7点","pictureStr":"||/upload/2020-07-09/20200709110836_560635.jpg||/upload/2020-07-09/20200709110838_972720.jpg||/upload/2020-07-09/20200709110842_192873.jpg","slogan":"商店测试  洗车首单10","isIndex":1},"udemand_project_info":{"id":"1862","yInquiryDemandProjectId":"787731493106483200","yInquiryDemandId":"787731493102288896","vTitle":"啊水电费","imgsrt":"/upload/2020-12-13/20201213172335_602733.jpg||/upload/2020-12-13/20201213172335_37831.jpg","imgArr":["/upload/2020-12-13/20201213172335_602733.jpg","/upload/2020-12-13/20201213172335_37831.jpg"],"createDate":"2020-12-13 17:23:43","isService":0}}]}]
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
        private String vMsg;
        private String createDate;
        private UserSedanInfoBean user_sedan_info;
        private List<SelectStorelistBean> selectStorelist;

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

        public String getVMsg() {
            return vMsg;
        }

        public void setVMsg(String vMsg) {
            this.vMsg = vMsg;
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

        public List<SelectStorelistBean> getSelectStorelist() {
            return selectStorelist;
        }

        public void setSelectStorelist(List<SelectStorelistBean> selectStorelist) {
            this.selectStorelist = selectStorelist;
        }

        public static class UserSedanInfoBean implements Serializable{
            /**
             * id : 1131
             * yUserSedanId : 782247596679757824
             * userId : 776203170115223552
             * sName : 2018款 40 e-tron
             * sLogo : /upload/logo/716723977224978432.jpg
             * sCy : 1
             * isF : 1
             * isDel : 0
             * reportPoliceJson : {"createDate":"2020-04-15 08:44:18","iCy":1,"id":1047,"telephone":"400-2123-2211","vName":"人寿保险公司","vNameNature":"普通保险","yReportPoliceId":699902914180677632}
             * jReportPoliceJson : {"createDate":"2020-04-15 08:44:18","iCy":1,"id":1047,"telephone":"400-2123-2211","vName":"人寿保险公司","vNameNature":"普通保险","yReportPoliceId":699902914180677632}
             * brandJson : {"brandName":"奥迪","groupName":"一汽-大众奥迪","iGrade":4,"id":89813,"parentId":716704211135365120,"sLogo":"/upload/logo/716723977224978432.jpg","sName":"2018款 40 e-tron","seriesName":"奥迪A6L新能源","vDispla":"4T","vYear":"2015","ySedanBrandId":716704993729576960}
             * userPhone : 18306043086
             * sNumber : 粤BRJJJY
             * brandInfo : {"id":"89813","ySedanBrandId":"716704993729576960","parentId":"716704211135365120","sName":"2018款 40 e-tron","sLogo":"/upload/logo/716723977224978432.jpg","seriesName":"奥迪A6L新能源","groupName":"一汽-大众奥迪","brandName":"奥迪","vYear":"2015","vDispla":"4T","iGrade":4}
             * compInsuranceTime :
             * comInsuranceTime :
             * annualReviewTime :
             * maintainTime : 2021-01-16
             * createDate : 2020-11-28 14:12:40
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
            private BrandInfoBean brandInfo;
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

            public static class BrandInfoBean implements Serializable{
                /**
                 * id : 89813
                 * ySedanBrandId : 716704993729576960
                 * parentId : 716704211135365120
                 * sName : 2018款 40 e-tron
                 * sLogo : /upload/logo/716723977224978432.jpg
                 * seriesName : 奥迪A6L新能源
                 * groupName : 一汽-大众奥迪
                 * brandName : 奥迪
                 * vYear : 2015
                 * vDispla : 4T
                 * iGrade : 4
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
                private int iGrade;

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

                public int getIGrade() {
                    return iGrade;
                }

                public void setIGrade(int iGrade) {
                    this.iGrade = iGrade;
                }
            }
        }

        public static class SelectStorelistBean implements Serializable{
            /**
             * yStoreId : 692341585785913346
             * store_info : {"id":"1041","yStoreId":"692341585785913346","userId":"715606113168392192","kfUserId":"715606113168392192","vName":"龙伍商店测试","review":"4.5","keywsr":"技术 修车 实","address":"深圳市光明新区公明街道东环大道松白工业区C区2栋一层106号","longitude":"116.49798","latitude":"39.916485","phone":" 400-2333-1123","vLevel":"B级","introduce":"听广告几年了，终于体验了特色服务。轮胎是其专长。听说11年开始，线上业务。16年开始线下。现在全国1500多家店。","charactStr":"||/upload/2020-07-09/20200709110807_317759.jpg||/upload/2020-07-09/20200709110817_957557.jpg||/upload/2020-07-09/20200709110819_726210.jpg","busHours":"早上9点至晚上7点","pictureStr":"||/upload/2020-07-09/20200709110836_560635.jpg||/upload/2020-07-09/20200709110838_972720.jpg||/upload/2020-07-09/20200709110842_192873.jpg","slogan":"商店测试  洗车首单10","isIndex":1}
             * project_offer_list : [{"id":"1162","yInquiryDemandProjectOfferId":"787731576212422656","yInquiryDemandProjectId":"787731493106483200","yInquiryDemandId":"787731493102288896","userId":"819539276219416586","yStoreId":"692341585785913346","isService":0,"storeJson":"{\"address\":\"深圳市光明新区公明街道东环大道松白工业区C区2栋一层106号\",\"busHours\":\"早上9点至晚上7点\",\"charactStr\":\"||/upload/2020-07-09/20200709110807_317759.jpg||/upload/2020-07-09/20200709110817_957557.jpg||/upload/2020-07-09/20200709110819_726210.jpg\",\"id\":1041,\"introduce\":\"听广告几年了，终于体验了特色服务。轮胎是其专长。听说11年开始，线上业务。16年开始线下。现在全国1500多家店。\",\"isIndex\":1,\"keywsr\":\"技术 修车 实\",\"kfUserId\":715606113168392192,\"latitude\":\"39.916485\",\"longitude\":\"116.49798\",\"phone\":\" 400-2333-1123\",\"pictureStr\":\"||/upload/2020-07-09/20200709110836_560635.jpg||/upload/2020-07-09/20200709110838_972720.jpg||/upload/2020-07-09/20200709110842_192873.jpg\",\"review\":\"4.5\",\"slogan\":\"商店测试  洗车首单10\",\"userId\":715606113168392192,\"vLevel\":\"B级\",\"vName\":\"龙伍商店测试\",\"yStoreId\":692341585785913346}","userJson":"{\"headPortrait\":\"/upload/2020-06-11/20200611090404_650532.png\",\"id\":0,\"userAccount\":\"THANU8\",\"userBalance\":0.0,\"userHash\":\"843B1049032A7E7C9F7D2E2CBE6D37A6\",\"userId\":0,\"userIntegral\":0,\"userName\":\"龙伍店铺技师\",\"userPhone\":13530039447,\"yStoreId\":0}","vPrice":"46775","createDate":"2020-12-13 17:24:03","store_info":{"id":"1041","yStoreId":"692341585785913346","userId":"715606113168392192","kfUserId":"715606113168392192","vName":"龙伍商店测试","review":"4.5","keywsr":"技术 修车 实","address":"深圳市光明新区公明街道东环大道松白工业区C区2栋一层106号","longitude":"116.49798","latitude":"39.916485","phone":" 400-2333-1123","vLevel":"B级","introduce":"听广告几年了，终于体验了特色服务。轮胎是其专长。听说11年开始，线上业务。16年开始线下。现在全国1500多家店。","charactStr":"||/upload/2020-07-09/20200709110807_317759.jpg||/upload/2020-07-09/20200709110817_957557.jpg||/upload/2020-07-09/20200709110819_726210.jpg","busHours":"早上9点至晚上7点","pictureStr":"||/upload/2020-07-09/20200709110836_560635.jpg||/upload/2020-07-09/20200709110838_972720.jpg||/upload/2020-07-09/20200709110842_192873.jpg","slogan":"商店测试  洗车首单10","isIndex":1},"udemand_project_info":{"id":"1862","yInquiryDemandProjectId":"787731493106483200","yInquiryDemandId":"787731493102288896","vTitle":"啊水电费","imgsrt":"/upload/2020-12-13/20201213172335_602733.jpg||/upload/2020-12-13/20201213172335_37831.jpg","imgArr":["/upload/2020-12-13/20201213172335_602733.jpg","/upload/2020-12-13/20201213172335_37831.jpg"],"createDate":"2020-12-13 17:23:43","isService":0}}]
             */

            private String yStoreId;
            private StoreInfoBean store_info;
            private List<ProjectOfferListBean> project_offer_list;

            public String getYStoreId() {
                return yStoreId;
            }

            public void setYStoreId(String yStoreId) {
                this.yStoreId = yStoreId;
            }

            public StoreInfoBean getStore_info() {
                return store_info;
            }

            public void setStore_info(StoreInfoBean store_info) {
                this.store_info = store_info;
            }

            public List<ProjectOfferListBean> getProject_offer_list() {
                return project_offer_list;
            }

            public void setProject_offer_list(List<ProjectOfferListBean> project_offer_list) {
                this.project_offer_list = project_offer_list;
            }

            public static class StoreInfoBean implements Serializable{
                /**
                 * id : 1041
                 * yStoreId : 692341585785913346
                 * userId : 715606113168392192
                 * kfUserId : 715606113168392192
                 * vName : 龙伍商店测试
                 * review : 4.5
                 * keywsr : 技术 修车 实
                 * address : 深圳市光明新区公明街道东环大道松白工业区C区2栋一层106号
                 * longitude : 116.49798
                 * latitude : 39.916485
                 * phone :  400-2333-1123
                 * vLevel : B级
                 * introduce : 听广告几年了，终于体验了特色服务。轮胎是其专长。听说11年开始，线上业务。16年开始线下。现在全国1500多家店。
                 * charactStr : ||/upload/2020-07-09/20200709110807_317759.jpg||/upload/2020-07-09/20200709110817_957557.jpg||/upload/2020-07-09/20200709110819_726210.jpg
                 * busHours : 早上9点至晚上7点
                 * pictureStr : ||/upload/2020-07-09/20200709110836_560635.jpg||/upload/2020-07-09/20200709110838_972720.jpg||/upload/2020-07-09/20200709110842_192873.jpg
                 * slogan : 商店测试  洗车首单10
                 * isIndex : 1
                 */

                private String id;
                private String yStoreId;
                private String userId;
                private String kfUserId;
                private String vName;
                private String review;
                private String keywsr;
                private String address;
                private String longitude;
                private String latitude;
                private String phone;
                private String vLevel;
                private String introduce;
                private String charactStr;
                private String busHours;
                private String pictureStr;
                private String slogan;
                private int isIndex;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getYStoreId() {
                    return yStoreId;
                }

                public void setYStoreId(String yStoreId) {
                    this.yStoreId = yStoreId;
                }

                public String getUserId() {
                    return userId;
                }

                public void setUserId(String userId) {
                    this.userId = userId;
                }

                public String getKfUserId() {
                    return kfUserId;
                }

                public void setKfUserId(String kfUserId) {
                    this.kfUserId = kfUserId;
                }

                public String getVName() {
                    return vName;
                }

                public void setVName(String vName) {
                    this.vName = vName;
                }

                public String getReview() {
                    return review;
                }

                public void setReview(String review) {
                    this.review = review;
                }

                public String getKeywsr() {
                    return keywsr;
                }

                public void setKeywsr(String keywsr) {
                    this.keywsr = keywsr;
                }

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
                }

                public String getLongitude() {
                    return longitude;
                }

                public void setLongitude(String longitude) {
                    this.longitude = longitude;
                }

                public String getLatitude() {
                    return latitude;
                }

                public void setLatitude(String latitude) {
                    this.latitude = latitude;
                }

                public String getPhone() {
                    return phone;
                }

                public void setPhone(String phone) {
                    this.phone = phone;
                }

                public String getVLevel() {
                    return vLevel;
                }

                public void setVLevel(String vLevel) {
                    this.vLevel = vLevel;
                }

                public String getIntroduce() {
                    return introduce;
                }

                public void setIntroduce(String introduce) {
                    this.introduce = introduce;
                }

                public String getCharactStr() {
                    return charactStr;
                }

                public void setCharactStr(String charactStr) {
                    this.charactStr = charactStr;
                }

                public String getBusHours() {
                    return busHours;
                }

                public void setBusHours(String busHours) {
                    this.busHours = busHours;
                }

                public String getPictureStr() {
                    return pictureStr;
                }

                public void setPictureStr(String pictureStr) {
                    this.pictureStr = pictureStr;
                }

                public String getSlogan() {
                    return slogan;
                }

                public void setSlogan(String slogan) {
                    this.slogan = slogan;
                }

                public int getIsIndex() {
                    return isIndex;
                }

                public void setIsIndex(int isIndex) {
                    this.isIndex = isIndex;
                }
            }

            public static class ProjectOfferListBean implements Serializable{
                /**
                 * id : 1162
                 * yInquiryDemandProjectOfferId : 787731576212422656
                 * yInquiryDemandProjectId : 787731493106483200
                 * yInquiryDemandId : 787731493102288896
                 * userId : 819539276219416586
                 * yStoreId : 692341585785913346
                 * isService : 0
                 * storeJson : {"address":"深圳市光明新区公明街道东环大道松白工业区C区2栋一层106号","busHours":"早上9点至晚上7点","charactStr":"||/upload/2020-07-09/20200709110807_317759.jpg||/upload/2020-07-09/20200709110817_957557.jpg||/upload/2020-07-09/20200709110819_726210.jpg","id":1041,"introduce":"听广告几年了，终于体验了特色服务。轮胎是其专长。听说11年开始，线上业务。16年开始线下。现在全国1500多家店。","isIndex":1,"keywsr":"技术 修车 实","kfUserId":715606113168392192,"latitude":"39.916485","longitude":"116.49798","phone":" 400-2333-1123","pictureStr":"||/upload/2020-07-09/20200709110836_560635.jpg||/upload/2020-07-09/20200709110838_972720.jpg||/upload/2020-07-09/20200709110842_192873.jpg","review":"4.5","slogan":"商店测试  洗车首单10","userId":715606113168392192,"vLevel":"B级","vName":"龙伍商店测试","yStoreId":692341585785913346}
                 * userJson : {"headPortrait":"/upload/2020-06-11/20200611090404_650532.png","id":0,"userAccount":"THANU8","userBalance":0.0,"userHash":"843B1049032A7E7C9F7D2E2CBE6D37A6","userId":0,"userIntegral":0,"userName":"龙伍店铺技师","userPhone":13530039447,"yStoreId":0}
                 * vPrice : 46775
                 * createDate : 2020-12-13 17:24:03
                 * store_info : {"id":"1041","yStoreId":"692341585785913346","userId":"715606113168392192","kfUserId":"715606113168392192","vName":"龙伍商店测试","review":"4.5","keywsr":"技术 修车 实","address":"深圳市光明新区公明街道东环大道松白工业区C区2栋一层106号","longitude":"116.49798","latitude":"39.916485","phone":" 400-2333-1123","vLevel":"B级","introduce":"听广告几年了，终于体验了特色服务。轮胎是其专长。听说11年开始，线上业务。16年开始线下。现在全国1500多家店。","charactStr":"||/upload/2020-07-09/20200709110807_317759.jpg||/upload/2020-07-09/20200709110817_957557.jpg||/upload/2020-07-09/20200709110819_726210.jpg","busHours":"早上9点至晚上7点","pictureStr":"||/upload/2020-07-09/20200709110836_560635.jpg||/upload/2020-07-09/20200709110838_972720.jpg||/upload/2020-07-09/20200709110842_192873.jpg","slogan":"商店测试  洗车首单10","isIndex":1}
                 * udemand_project_info : {"id":"1862","yInquiryDemandProjectId":"787731493106483200","yInquiryDemandId":"787731493102288896","vTitle":"啊水电费","imgsrt":"/upload/2020-12-13/20201213172335_602733.jpg||/upload/2020-12-13/20201213172335_37831.jpg","imgArr":["/upload/2020-12-13/20201213172335_602733.jpg","/upload/2020-12-13/20201213172335_37831.jpg"],"createDate":"2020-12-13 17:23:43","isService":0}
                 */

                private String id;
                private String yInquiryDemandProjectOfferId;
                private String yInquiryDemandProjectId;
                private String yInquiryDemandId;
                private String userId;
                private String yStoreId;
                private int isService;
                private String storeJson;
                private String userJson;
                private String vPrice;
                private String createDate;
                private StoreInfoBeanX store_info;
                private UdemandProjectInfoBean udemand_project_info;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getYInquiryDemandProjectOfferId() {
                    return yInquiryDemandProjectOfferId;
                }

                public void setYInquiryDemandProjectOfferId(String yInquiryDemandProjectOfferId) {
                    this.yInquiryDemandProjectOfferId = yInquiryDemandProjectOfferId;
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

                public String getUserId() {
                    return userId;
                }

                public void setUserId(String userId) {
                    this.userId = userId;
                }

                public String getYStoreId() {
                    return yStoreId;
                }

                public void setYStoreId(String yStoreId) {
                    this.yStoreId = yStoreId;
                }

                public int getIsService() {
                    return isService;
                }

                public void setIsService(int isService) {
                    this.isService = isService;
                }

                public String getStoreJson() {
                    return storeJson;
                }

                public void setStoreJson(String storeJson) {
                    this.storeJson = storeJson;
                }

                public String getUserJson() {
                    return userJson;
                }

                public void setUserJson(String userJson) {
                    this.userJson = userJson;
                }

                public String getVPrice() {
                    return vPrice;
                }

                public void setVPrice(String vPrice) {
                    this.vPrice = vPrice;
                }

                public String getCreateDate() {
                    return createDate;
                }

                public void setCreateDate(String createDate) {
                    this.createDate = createDate;
                }

                public StoreInfoBeanX getStore_info() {
                    return store_info;
                }

                public void setStore_info(StoreInfoBeanX store_info) {
                    this.store_info = store_info;
                }

                public UdemandProjectInfoBean getUdemand_project_info() {
                    return udemand_project_info;
                }

                public void setUdemand_project_info(UdemandProjectInfoBean udemand_project_info) {
                    this.udemand_project_info = udemand_project_info;
                }

                public static class StoreInfoBeanX implements Serializable{
                    /**
                     * id : 1041
                     * yStoreId : 692341585785913346
                     * userId : 715606113168392192
                     * kfUserId : 715606113168392192
                     * vName : 龙伍商店测试
                     * review : 4.5
                     * keywsr : 技术 修车 实
                     * address : 深圳市光明新区公明街道东环大道松白工业区C区2栋一层106号
                     * longitude : 116.49798
                     * latitude : 39.916485
                     * phone :  400-2333-1123
                     * vLevel : B级
                     * introduce : 听广告几年了，终于体验了特色服务。轮胎是其专长。听说11年开始，线上业务。16年开始线下。现在全国1500多家店。
                     * charactStr : ||/upload/2020-07-09/20200709110807_317759.jpg||/upload/2020-07-09/20200709110817_957557.jpg||/upload/2020-07-09/20200709110819_726210.jpg
                     * busHours : 早上9点至晚上7点
                     * pictureStr : ||/upload/2020-07-09/20200709110836_560635.jpg||/upload/2020-07-09/20200709110838_972720.jpg||/upload/2020-07-09/20200709110842_192873.jpg
                     * slogan : 商店测试  洗车首单10
                     * isIndex : 1
                     */

                    private String id;
                    private String yStoreId;
                    private String userId;
                    private String kfUserId;
                    private String vName;
                    private String review;
                    private String keywsr;
                    private String address;
                    private String longitude;
                    private String latitude;
                    private String phone;
                    private String vLevel;
                    private String introduce;
                    private String charactStr;
                    private String busHours;
                    private String pictureStr;
                    private String slogan;
                    private int isIndex;

                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }

                    public String getYStoreId() {
                        return yStoreId;
                    }

                    public void setYStoreId(String yStoreId) {
                        this.yStoreId = yStoreId;
                    }

                    public String getUserId() {
                        return userId;
                    }

                    public void setUserId(String userId) {
                        this.userId = userId;
                    }

                    public String getKfUserId() {
                        return kfUserId;
                    }

                    public void setKfUserId(String kfUserId) {
                        this.kfUserId = kfUserId;
                    }

                    public String getVName() {
                        return vName;
                    }

                    public void setVName(String vName) {
                        this.vName = vName;
                    }

                    public String getReview() {
                        return review;
                    }

                    public void setReview(String review) {
                        this.review = review;
                    }

                    public String getKeywsr() {
                        return keywsr;
                    }

                    public void setKeywsr(String keywsr) {
                        this.keywsr = keywsr;
                    }

                    public String getAddress() {
                        return address;
                    }

                    public void setAddress(String address) {
                        this.address = address;
                    }

                    public String getLongitude() {
                        return longitude;
                    }

                    public void setLongitude(String longitude) {
                        this.longitude = longitude;
                    }

                    public String getLatitude() {
                        return latitude;
                    }

                    public void setLatitude(String latitude) {
                        this.latitude = latitude;
                    }

                    public String getPhone() {
                        return phone;
                    }

                    public void setPhone(String phone) {
                        this.phone = phone;
                    }

                    public String getVLevel() {
                        return vLevel;
                    }

                    public void setVLevel(String vLevel) {
                        this.vLevel = vLevel;
                    }

                    public String getIntroduce() {
                        return introduce;
                    }

                    public void setIntroduce(String introduce) {
                        this.introduce = introduce;
                    }

                    public String getCharactStr() {
                        return charactStr;
                    }

                    public void setCharactStr(String charactStr) {
                        this.charactStr = charactStr;
                    }

                    public String getBusHours() {
                        return busHours;
                    }

                    public void setBusHours(String busHours) {
                        this.busHours = busHours;
                    }

                    public String getPictureStr() {
                        return pictureStr;
                    }

                    public void setPictureStr(String pictureStr) {
                        this.pictureStr = pictureStr;
                    }

                    public String getSlogan() {
                        return slogan;
                    }

                    public void setSlogan(String slogan) {
                        this.slogan = slogan;
                    }

                    public int getIsIndex() {
                        return isIndex;
                    }

                    public void setIsIndex(int isIndex) {
                        this.isIndex = isIndex;
                    }
                }

                public static class UdemandProjectInfoBean implements Serializable{
                    /**
                     * id : 1862
                     * yInquiryDemandProjectId : 787731493106483200
                     * yInquiryDemandId : 787731493102288896
                     * vTitle : 啊水电费
                     * imgsrt : /upload/2020-12-13/20201213172335_602733.jpg||/upload/2020-12-13/20201213172335_37831.jpg
                     * imgArr : ["/upload/2020-12-13/20201213172335_602733.jpg","/upload/2020-12-13/20201213172335_37831.jpg"]
                     * createDate : 2020-12-13 17:23:43
                     * isService : 0
                     */

                    private boolean isxuanze = true;

                    public boolean isIsxuanze() {
                        return isxuanze;
                    }

                    public void setIsxuanze(boolean isxuanze) {
                        this.isxuanze = isxuanze;
                    }

                    private String id;
                    private String yInquiryDemandProjectId;
                    private String yInquiryDemandId;
                    private String vTitle;
                    private String imgsrt;
                    private String createDate;
                    private int isService;
                    private List<String> imgArr;
                    private String vMsg;

                    public String getVMsg() {
                        return vMsg;
                    }

                    public void setVMsg(String vMsg) {
                        this.vMsg = vMsg;
                    }

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
                }
            }
        }
    }
}
