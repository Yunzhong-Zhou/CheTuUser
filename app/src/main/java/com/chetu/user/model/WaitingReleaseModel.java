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
         * id : 1152
         * yInquiryDemandId : 724704690561875968
         * userId : 714547022807433216
         * yUserSedanId : 724585460164198400
         * serviceName : 发动机清洗
         * serviceIdStr : 692341585785913353
         * storeIdStr : 692341585785913348,692341585785913344,692341585785913345
         * userSedanJson : {"brandJson":"{\"brandName\":\"阿斯顿・马丁\",\"groupName\":\"阿斯顿・马丁\",\"id\":91506,\"parentId\":716704210728517632,\"sLogo\":\"/upload/logo/716723976784576512.jpg\",\"sName\":\"2019款 AMR 荣耀限量版\",\"seriesName\":\"阿斯顿・马丁DB11\",\"ySedanBrandId\":716705042324783104}","createDate":"2020-06-22 11:23:55","id":1088,"isDel":0,"isF":1,"jReportPoliceJson":"{\"createDate\":\"2020-04-15 08:44:18\",\"id\":1047,\"telephone\":\"400-2123-2211\",\"vName\":\"人寿保险公司\",\"vNameNature\":\"普通保险\",\"yReportPoliceId\":699902914180677632}","reportPoliceJson":"{\"createDate\":\"2020-04-15 08:44:18\",\"id\":1047,\"telephone\":\"400-2123-2211\",\"vName\":\"人寿保险公司\",\"vNameNature\":\"普通保险\",\"yReportPoliceId\":699902914180677632}","sCy":1,"sLogo":"/upload/logo/716723976784576512.jpg","sName":"2019款 AMR 荣耀限量版","sNumber":"粤A12345","userId":714547022807433216,"userPhone":"18306043086","yUserSedanId":724585460164198400}
         * isOk : 1
         * createDate : 2020-06-22 19:17:42
         * user_sedan_info : {"id":"1088","yUserSedanId":"724585460164198400","userId":"714547022807433216","sName":"2019款 AMR 荣耀限量版","sLogo":"/upload/logo/716723976784576512.jpg","sCy":1,"isF":1,"isDel":0,"reportPoliceJson":"{\"createDate\":\"2020-04-15 08:44:18\",\"id\":1047,\"telephone\":\"400-2123-2211\",\"vName\":\"人寿保险公司\",\"vNameNature\":\"普通保险\",\"yReportPoliceId\":699902914180677632}","jReportPoliceJson":"{\"createDate\":\"2020-04-15 08:44:18\",\"id\":1047,\"telephone\":\"400-2123-2211\",\"vName\":\"人寿保险公司\",\"vNameNature\":\"普通保险\",\"yReportPoliceId\":699902914180677632}","brandJson":"{\"brandName\":\"阿斯顿・马丁\",\"groupName\":\"阿斯顿・马丁\",\"id\":91506,\"parentId\":716704210728517632,\"sLogo\":\"/upload/logo/716723976784576512.jpg\",\"sName\":\"2019款 AMR 荣耀限量版\",\"seriesName\":\"阿斯顿・马丁DB11\",\"ySedanBrandId\":716705042324783104}","userPhone":"18306043086","sNumber":"粤A12345","brandInfo":{"id":"91506","ySedanBrandId":"716705042324783104","parentId":"716704210728517632","sName":"2019款 AMR 荣耀限量版","sLogo":"/upload/logo/716723976784576512.jpg","seriesName":"阿斯顿・马丁DB11","groupName":"阿斯顿・马丁","brandName":"阿斯顿・马丁"},"createDate":"2020-06-22 11:23:55"}
         * project_list : [{"id":"1517","yInquiryDemandProjectId":"724704690578653184","yInquiryDemandId":"724704690561875968","vTitle":"发动机清洗","imgsrt":"/upload/2020-06-22/20200622191736_678404.jpg","imgArr":["/upload/2020-06-22/20200622191736_678404.jpg"],"createDate":"2020-06-22 19:17:42","isService":1,"offer_list":[{"yInquiryDemandProjectOfferId":"724704955360870400","yInquiryDemandProjectId":"724704690578653184","userId":"719539276219416576","yStoreId":"692341585785913345","isService":1,"storeJson":"{\"address\":\"凤凰山附近\",\"charactStr\":\"/upload/store/222.png||/upload/store/222.png\",\"id\":1018,\"introduce\":\"听广告几年了，终于体验了特色服务。轮胎是其专长。听说11年开始，线上业务。16年开始线下。现在全国1500多家店。\",\"isIndex\":1,\"keywsr\":\"技术 修车 实\",\"latitude\":\"39.916485\",\"longitude\":\"116.49798\",\"phone\":\"  400-2333-1123\",\"pictureStr\":\"/upload/store/222.png||/upload/store/222.png\",\"review\":\"4.5\",\"slogan\":\"维修商店测试 美容首单50\",\"userId\":719539276219416576,\"vLevel\":\"A级\",\"vName\":\"维修商店测试\",\"yStoreId\":692341585785913345}","userJson":"{\"headPortrait\":\"/upload/2020-06-18/20200618164348_110881.jpg\",\"id\":0,\"userAccount\":\"THANU4\",\"userBalance\":0.0,\"userHash\":\"843B1049032A7E7C9F7D2E2CBE6D37A6\",\"userId\":0,\"userIntegral\":0,\"userName\":\"阿斯顿马丁\",\"yStoreId\":0}","vPrice":200,"store_info":{"id":"1018","yStoreId":"692341585785913345","userId":"719539276219416576","vName":"维修商店测试","review":"4.5","keywsr":"技术 修车 实","address":"凤凰山附近","longitude":"116.49798","latitude":"39.916485","phone":"  400-2333-1123","vLevel":"A级","introduce":"听广告几年了，终于体验了特色服务。轮胎是其专长。听说11年开始，线上业务。16年开始线下。现在全国1500多家店。","charactStr":"/upload/store/222.png||/upload/store/222.png","pictureStr":"/upload/store/222.png||/upload/store/222.png","slogan":"维修商店测试 美容首单50","isIndex":1}}]},{"id":"1516","yInquiryDemandProjectId":"724704690566070272","yInquiryDemandId":"724704690561875968","vTitle":"我的项目","imgsrt":"/upload/2020-06-22/20200622191736_678404.jpg","imgArr":["/upload/2020-06-22/20200622191736_678404.jpg"],"createDate":"2020-06-22 19:17:42","isService":0,"offer_list":[{"yInquiryDemandProjectOfferId":"724705009240899584","yInquiryDemandProjectId":"724704690566070272","userId":"719539276219416576","yStoreId":"692341585785913345","isService":0,"storeJson":"{\"address\":\"凤凰山附近\",\"charactStr\":\"/upload/store/222.png||/upload/store/222.png\",\"id\":1018,\"introduce\":\"听广告几年了，终于体验了特色服务。轮胎是其专长。听说11年开始，线上业务。16年开始线下。现在全国1500多家店。\",\"isIndex\":1,\"keywsr\":\"技术 修车 实\",\"latitude\":\"39.916485\",\"longitude\":\"116.49798\",\"phone\":\"  400-2333-1123\",\"pictureStr\":\"/upload/store/222.png||/upload/store/222.png\",\"review\":\"4.5\",\"slogan\":\"维修商店测试 美容首单50\",\"userId\":719539276219416576,\"vLevel\":\"A级\",\"vName\":\"维修商店测试\",\"yStoreId\":692341585785913345}","userJson":"{\"headPortrait\":\"/upload/2020-06-18/20200618164348_110881.jpg\",\"id\":0,\"userAccount\":\"THANU4\",\"userBalance\":0.0,\"userHash\":\"843B1049032A7E7C9F7D2E2CBE6D37A6\",\"userId\":0,\"userIntegral\":0,\"userName\":\"阿斯顿马丁\",\"yStoreId\":0}","vPrice":500,"store_info":{"id":"1018","yStoreId":"692341585785913345","userId":"719539276219416576","vName":"维修商店测试","review":"4.5","keywsr":"技术 修车 实","address":"凤凰山附近","longitude":"116.49798","latitude":"39.916485","phone":"  400-2333-1123","vLevel":"A级","introduce":"听广告几年了，终于体验了特色服务。轮胎是其专长。听说11年开始，线上业务。16年开始线下。现在全国1500多家店。","charactStr":"/upload/store/222.png||/upload/store/222.png","pictureStr":"/upload/store/222.png||/upload/store/222.png","slogan":"维修商店测试 美容首单50","isIndex":1}}]}]
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

        public static class UserSedanInfoBean implements Serializable{
            /**
             * id : 1088
             * yUserSedanId : 724585460164198400
             * userId : 714547022807433216
             * sName : 2019款 AMR 荣耀限量版
             * sLogo : /upload/logo/716723976784576512.jpg
             * sCy : 1
             * isF : 1
             * isDel : 0
             * reportPoliceJson : {"createDate":"2020-04-15 08:44:18","id":1047,"telephone":"400-2123-2211","vName":"人寿保险公司","vNameNature":"普通保险","yReportPoliceId":699902914180677632}
             * jReportPoliceJson : {"createDate":"2020-04-15 08:44:18","id":1047,"telephone":"400-2123-2211","vName":"人寿保险公司","vNameNature":"普通保险","yReportPoliceId":699902914180677632}
             * brandJson : {"brandName":"阿斯顿・马丁","groupName":"阿斯顿・马丁","id":91506,"parentId":716704210728517632,"sLogo":"/upload/logo/716723976784576512.jpg","sName":"2019款 AMR 荣耀限量版","seriesName":"阿斯顿・马丁DB11","ySedanBrandId":716705042324783104}
             * userPhone : 18306043086
             * sNumber : 粤A12345
             * brandInfo : {"id":"91506","ySedanBrandId":"716705042324783104","parentId":"716704210728517632","sName":"2019款 AMR 荣耀限量版","sLogo":"/upload/logo/716723976784576512.jpg","seriesName":"阿斯顿・马丁DB11","groupName":"阿斯顿・马丁","brandName":"阿斯顿・马丁"}
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

            public static class BrandInfoBean implements Serializable{
                /**
                 * id : 91506
                 * ySedanBrandId : 716705042324783104
                 * parentId : 716704210728517632
                 * sName : 2019款 AMR 荣耀限量版
                 * sLogo : /upload/logo/716723976784576512.jpg
                 * seriesName : 阿斯顿・马丁DB11
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

        public static class ProjectListBean implements Serializable{
            /**
             * id : 1517
             * yInquiryDemandProjectId : 724704690578653184
             * yInquiryDemandId : 724704690561875968
             * vTitle : 发动机清洗
             * imgsrt : /upload/2020-06-22/20200622191736_678404.jpg
             * imgArr : ["/upload/2020-06-22/20200622191736_678404.jpg"]
             * createDate : 2020-06-22 19:17:42
             * isService : 1
             * offer_list : [{"yInquiryDemandProjectOfferId":"724704955360870400","yInquiryDemandProjectId":"724704690578653184","userId":"719539276219416576","yStoreId":"692341585785913345","isService":1,"storeJson":"{\"address\":\"凤凰山附近\",\"charactStr\":\"/upload/store/222.png||/upload/store/222.png\",\"id\":1018,\"introduce\":\"听广告几年了，终于体验了特色服务。轮胎是其专长。听说11年开始，线上业务。16年开始线下。现在全国1500多家店。\",\"isIndex\":1,\"keywsr\":\"技术 修车 实\",\"latitude\":\"39.916485\",\"longitude\":\"116.49798\",\"phone\":\"  400-2333-1123\",\"pictureStr\":\"/upload/store/222.png||/upload/store/222.png\",\"review\":\"4.5\",\"slogan\":\"维修商店测试 美容首单50\",\"userId\":719539276219416576,\"vLevel\":\"A级\",\"vName\":\"维修商店测试\",\"yStoreId\":692341585785913345}","userJson":"{\"headPortrait\":\"/upload/2020-06-18/20200618164348_110881.jpg\",\"id\":0,\"userAccount\":\"THANU4\",\"userBalance\":0.0,\"userHash\":\"843B1049032A7E7C9F7D2E2CBE6D37A6\",\"userId\":0,\"userIntegral\":0,\"userName\":\"阿斯顿马丁\",\"yStoreId\":0}","vPrice":200,"store_info":{"id":"1018","yStoreId":"692341585785913345","userId":"719539276219416576","vName":"维修商店测试","review":"4.5","keywsr":"技术 修车 实","address":"凤凰山附近","longitude":"116.49798","latitude":"39.916485","phone":"  400-2333-1123","vLevel":"A级","introduce":"听广告几年了，终于体验了特色服务。轮胎是其专长。听说11年开始，线上业务。16年开始线下。现在全国1500多家店。","charactStr":"/upload/store/222.png||/upload/store/222.png","pictureStr":"/upload/store/222.png||/upload/store/222.png","slogan":"维修商店测试 美容首单50","isIndex":1}}]
             */

            private String id;
            private String yInquiryDemandProjectId;
            private String yInquiryDemandId;
            private String vTitle;
            private String imgsrt;
            private String createDate;
            private int isService;
            private List<String> imgArr;
            private List<OfferListBean> offer_list;

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

            public List<OfferListBean> getOffer_list() {
                return offer_list;
            }

            public void setOffer_list(List<OfferListBean> offer_list) {
                this.offer_list = offer_list;
            }

            public static class OfferListBean implements Serializable{
                /**
                 * yInquiryDemandProjectOfferId : 724704955360870400
                 * yInquiryDemandProjectId : 724704690578653184
                 * userId : 719539276219416576
                 * yStoreId : 692341585785913345
                 * isService : 1
                 * storeJson : {"address":"凤凰山附近","charactStr":"/upload/store/222.png||/upload/store/222.png","id":1018,"introduce":"听广告几年了，终于体验了特色服务。轮胎是其专长。听说11年开始，线上业务。16年开始线下。现在全国1500多家店。","isIndex":1,"keywsr":"技术 修车 实","latitude":"39.916485","longitude":"116.49798","phone":"  400-2333-1123","pictureStr":"/upload/store/222.png||/upload/store/222.png","review":"4.5","slogan":"维修商店测试 美容首单50","userId":719539276219416576,"vLevel":"A级","vName":"维修商店测试","yStoreId":692341585785913345}
                 * userJson : {"headPortrait":"/upload/2020-06-18/20200618164348_110881.jpg","id":0,"userAccount":"THANU4","userBalance":0.0,"userHash":"843B1049032A7E7C9F7D2E2CBE6D37A6","userId":0,"userIntegral":0,"userName":"阿斯顿马丁","yStoreId":0}
                 * vPrice : 200.0
                 * store_info : {"id":"1018","yStoreId":"692341585785913345","userId":"719539276219416576","vName":"维修商店测试","review":"4.5","keywsr":"技术 修车 实","address":"凤凰山附近","longitude":"116.49798","latitude":"39.916485","phone":"  400-2333-1123","vLevel":"A级","introduce":"听广告几年了，终于体验了特色服务。轮胎是其专长。听说11年开始，线上业务。16年开始线下。现在全国1500多家店。","charactStr":"/upload/store/222.png||/upload/store/222.png","pictureStr":"/upload/store/222.png||/upload/store/222.png","slogan":"维修商店测试 美容首单50","isIndex":1}
                 */

                private String yInquiryDemandProjectOfferId;
                private String yInquiryDemandProjectId;
                private String userId;
                private String yStoreId;
                private int isService;
                private String storeJson;
                private String userJson;
                private String vPrice;
                private StoreInfoBean store_info;

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

                public StoreInfoBean getStore_info() {
                    return store_info;
                }

                public void setStore_info(StoreInfoBean store_info) {
                    this.store_info = store_info;
                }

                public static class StoreInfoBean implements Serializable{
                    /**
                     * id : 1018
                     * yStoreId : 692341585785913345
                     * userId : 719539276219416576
                     * vName : 维修商店测试
                     * review : 4.5
                     * keywsr : 技术 修车 实
                     * address : 凤凰山附近
                     * longitude : 116.49798
                     * latitude : 39.916485
                     * phone :   400-2333-1123
                     * vLevel : A级
                     * introduce : 听广告几年了，终于体验了特色服务。轮胎是其专长。听说11年开始，线上业务。16年开始线下。现在全国1500多家店。
                     * charactStr : /upload/store/222.png||/upload/store/222.png
                     * pictureStr : /upload/store/222.png||/upload/store/222.png
                     * slogan : 维修商店测试 美容首单50
                     * isIndex : 1
                     */

                    private String id;
                    private String yStoreId;
                    private String userId;
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
            }
        }
    }
}
