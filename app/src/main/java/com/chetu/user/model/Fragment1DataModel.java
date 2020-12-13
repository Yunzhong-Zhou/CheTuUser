package com.chetu.user.model;

import java.util.List;

/**
 * Created by zyz on 2020/6/13.
 */
public class Fragment1DataModel {
    private String daiban;
    /**
     * order_info : {"id":"1251","yOrderId":"784048962330951680","userId":"776203170115223552","yStoreId":"692341585785913344","yTechnSedanId":"786166950815334400","yUserSedanId":"782247596679757824","iClassify":1,"gPrice":100,"longitude":"116.40737841066304","latitude":"39.90541291846198","appoinTime":"2020-12-03 13:30-14:30","sendAddress":"北京市东城区东华门街道东长安街14号中华人民共和国公安部","gState":2,"createDate":"2020-12-03 13:30:39","user_sedan_info":{"id":"1131","yUserSedanId":"782247596679757824","userId":"776203170115223552","sName":"2018款 40 e-tron","sLogo":"/upload/logo/716723977224978432.jpg","sCy":1,"isF":1,"isDel":0,"reportPoliceJson":"{\"createDate\":\"2020-04-15 08:44:18\",\"iCy\":1,\"id\":1047,\"telephone\":\"400-2123-2211\",\"vName\":\"人寿保险公司\",\"vNameNature\":\"普通保险\",\"yReportPoliceId\":699902914180677632}","jReportPoliceJson":"{\"createDate\":\"2020-04-15 08:44:18\",\"iCy\":1,\"id\":1047,\"telephone\":\"400-2123-2211\",\"vName\":\"人寿保险公司\",\"vNameNature\":\"普通保险\",\"yReportPoliceId\":699902914180677632}","brandJson":"{\"brandName\":\"奥迪\",\"groupName\":\"一汽-大众奥迪\",\"iGrade\":4,\"id\":89813,\"parentId\":716704211135365120,\"sLogo\":\"/upload/logo/716723977224978432.jpg\",\"sName\":\"2018款 40 e-tron\",\"seriesName\":\"奥迪A6L新能源\",\"vDispla\":\"4T\",\"vYear\":\"2015\",\"ySedanBrandId\":716704993729576960}","userPhone":"18306043086","sNumber":"粤BRJJJY","brandInfo":{"id":"89813","ySedanBrandId":"716704993729576960","parentId":"716704211135365120","sName":"2018款 40 e-tron","sLogo":"/upload/logo/716723977224978432.jpg","seriesName":"奥迪A6L新能源","groupName":"一汽-大众奥迪","brandName":"奥迪","vYear":"2015","vDispla":"4T","iGrade":4},"compInsuranceTime":"","comInsuranceTime":"","annualReviewTime":"","maintainTime":"2021-01-16","createDate":"2020-11-28 14:12:40"},"techn_sedan_info":{"id":"1161","yTechnSedanId":"786166950815334400","yOrderId":"784048962330951680","ySedanBrandId":"716704993729576960","yStoreId":"692341585785913344","userId":"714547022807433218","licenseNumber":"粤BRJJJY","ownerName":"用户1","ownerPhone":"18306043086","frameNumber":"","vehicleMileage":"","compInsuranceTime":"","comInsuranceTime":"","annualReviewTime":"","vRemarks":"备注备注阿斯顿阿斯顿阿斯顿阿斯顿","sedanBrandJson":"{\"brandName\":\"奥迪\",\"groupName\":\"一汽-大众奥迪\",\"iGrade\":4,\"id\":89813,\"parentId\":716704211135365120,\"sLogo\":\"/upload/logo/716723977224978432.jpg\",\"sName\":\"2018款 40 e-tron\",\"seriesName\":\"奥迪A6L新能源\",\"vDispla\":\"4T\",\"vYear\":\"2015\",\"ySedanBrandId\":716704993729576960}","gPrice":100,"isOrder":1,"createDate":"2020-12-09 09:46:47","gState":2,"isPay":0,"distributeJson":"[{\"headPortrait\":\"/upload/2020-10-15/20201015175931_273079.jpg\",\"id\":0,\"userBalance\":0.0,\"userHash\":\"36635C17B0E19DD1E14A92D8895B47C9\",\"userId\":0,\"userIntegral\":0,\"userName\":\"龙肆\",\"yStoreId\":692341585785913344}]","appeImgstr":"","estimateTime":"  ","rewardMoney":0},"isDelivery":1,"isPick":1,"deliveryAddress":"北京市东城区东华门街道肃亲王府","cMsg":"备注备注阿斯顿阿斯顿阿斯顿阿斯顿"}
     */

    private OrderInfoBean order_info;

    public String getDaiban() {
        return daiban;
    }

    public void setDaiban(String daiban) {
        this.daiban = daiban;
    }

    private List<BannerListBean> banner_list;
    private List<IndexCustomListBean> index_custom_list;
    private List<IndexServiceListBean> index_service_list;

    public List<BannerListBean> getBanner_list() {
        return banner_list;
    }

    public void setBanner_list(List<BannerListBean> banner_list) {
        this.banner_list = banner_list;
    }

    public List<IndexCustomListBean> getIndex_custom_list() {
        return index_custom_list;
    }

    public void setIndex_custom_list(List<IndexCustomListBean> index_custom_list) {
        this.index_custom_list = index_custom_list;
    }

    public List<IndexServiceListBean> getIndex_service_list() {
        return index_service_list;
    }

    public void setIndex_service_list(List<IndexServiceListBean> index_service_list) {
        this.index_service_list = index_service_list;
    }

    public OrderInfoBean getOrder_info() {
        return order_info;
    }

    public void setOrder_info(OrderInfoBean order_info) {
        this.order_info = order_info;
    }

    public static class BannerListBean {
        /**
         * id : 167
         * yPhotoId : 692341585785913344
         * yServiceId : 692341585785913344
         * category : 1
         * msg : 首页数据
         * imgurl : /upload/banner.png
         * createDate : 2019-09-27 10:31:19
         */

        private String id;
        private String yPhotoId;
        private String yServiceId;
        private int category;
        private String msg;
        private String imgurl;
        private String createDate;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYPhotoId() {
            return yPhotoId;
        }

        public void setYPhotoId(String yPhotoId) {
            this.yPhotoId = yPhotoId;
        }

        public String getYServiceId() {
            return yServiceId;
        }

        public void setYServiceId(String yServiceId) {
            this.yServiceId = yServiceId;
        }

        public int getCategory() {
            return category;
        }

        public void setCategory(int category) {
            this.category = category;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }
    }

    public static class IndexCustomListBean {
        /**
         * id : 170
         * yPhotoId : 692341585785913347
         * yServiceId : 692341585785913344
         * category : 2
         * msg : 汽车用品
         * imgurl : /upload/index/index4.png
         * createDate : 2019-09-27 10:31:19
         */

        private String id;
        private String yPhotoId;
        private String yServiceId;
        private int category;
        private String msg;
        private String imgurl;
        private String createDate;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYPhotoId() {
            return yPhotoId;
        }

        public void setYPhotoId(String yPhotoId) {
            this.yPhotoId = yPhotoId;
        }

        public String getYServiceId() {
            return yServiceId;
        }

        public void setYServiceId(String yServiceId) {
            this.yServiceId = yServiceId;
        }

        public int getCategory() {
            return category;
        }

        public void setCategory(int category) {
            this.category = category;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }
    }

    public static class IndexServiceListBean {
        /**
         * id : 1017
         * yServiceId : 692341585785913344
         * yParentId : 0
         * vName : 洗车美容
         * vImg : /upload/index/index1.png
         * isIndex : 1
         * isSheet : 0
         * createDate : 2020-03-25 11:58:17
         */

        private String id;
        private String yServiceId;
        private String yParentId;
        private String vName;
        private String vImg;
        private int isIndex;
        private int isSheet;
        private String createDate;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYServiceId() {
            return yServiceId;
        }

        public void setYServiceId(String yServiceId) {
            this.yServiceId = yServiceId;
        }

        public String getYParentId() {
            return yParentId;
        }

        public void setYParentId(String yParentId) {
            this.yParentId = yParentId;
        }

        public String getVName() {
            return vName;
        }

        public void setVName(String vName) {
            this.vName = vName;
        }

        public String getVImg() {
            return vImg;
        }

        public void setVImg(String vImg) {
            this.vImg = vImg;
        }

        public int getIsIndex() {
            return isIndex;
        }

        public void setIsIndex(int isIndex) {
            this.isIndex = isIndex;
        }

        public int getIsSheet() {
            return isSheet;
        }

        public void setIsSheet(int isSheet) {
            this.isSheet = isSheet;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }
    }

    public static class OrderInfoBean {
        /**
         * id : 1251
         * yOrderId : 784048962330951680
         * userId : 776203170115223552
         * yStoreId : 692341585785913344
         * yTechnSedanId : 786166950815334400
         * yUserSedanId : 782247596679757824
         * iClassify : 1
         * gPrice : 100.0
         * longitude : 116.40737841066304
         * latitude : 39.90541291846198
         * appoinTime : 2020-12-03 13:30-14:30
         * sendAddress : 北京市东城区东华门街道东长安街14号中华人民共和国公安部
         * gState : 2
         * createDate : 2020-12-03 13:30:39
         * user_sedan_info : {"id":"1131","yUserSedanId":"782247596679757824","userId":"776203170115223552","sName":"2018款 40 e-tron","sLogo":"/upload/logo/716723977224978432.jpg","sCy":1,"isF":1,"isDel":0,"reportPoliceJson":"{\"createDate\":\"2020-04-15 08:44:18\",\"iCy\":1,\"id\":1047,\"telephone\":\"400-2123-2211\",\"vName\":\"人寿保险公司\",\"vNameNature\":\"普通保险\",\"yReportPoliceId\":699902914180677632}","jReportPoliceJson":"{\"createDate\":\"2020-04-15 08:44:18\",\"iCy\":1,\"id\":1047,\"telephone\":\"400-2123-2211\",\"vName\":\"人寿保险公司\",\"vNameNature\":\"普通保险\",\"yReportPoliceId\":699902914180677632}","brandJson":"{\"brandName\":\"奥迪\",\"groupName\":\"一汽-大众奥迪\",\"iGrade\":4,\"id\":89813,\"parentId\":716704211135365120,\"sLogo\":\"/upload/logo/716723977224978432.jpg\",\"sName\":\"2018款 40 e-tron\",\"seriesName\":\"奥迪A6L新能源\",\"vDispla\":\"4T\",\"vYear\":\"2015\",\"ySedanBrandId\":716704993729576960}","userPhone":"18306043086","sNumber":"粤BRJJJY","brandInfo":{"id":"89813","ySedanBrandId":"716704993729576960","parentId":"716704211135365120","sName":"2018款 40 e-tron","sLogo":"/upload/logo/716723977224978432.jpg","seriesName":"奥迪A6L新能源","groupName":"一汽-大众奥迪","brandName":"奥迪","vYear":"2015","vDispla":"4T","iGrade":4},"compInsuranceTime":"","comInsuranceTime":"","annualReviewTime":"","maintainTime":"2021-01-16","createDate":"2020-11-28 14:12:40"}
         * techn_sedan_info : {"id":"1161","yTechnSedanId":"786166950815334400","yOrderId":"784048962330951680","ySedanBrandId":"716704993729576960","yStoreId":"692341585785913344","userId":"714547022807433218","licenseNumber":"粤BRJJJY","ownerName":"用户1","ownerPhone":"18306043086","frameNumber":"","vehicleMileage":"","compInsuranceTime":"","comInsuranceTime":"","annualReviewTime":"","vRemarks":"备注备注阿斯顿阿斯顿阿斯顿阿斯顿","sedanBrandJson":"{\"brandName\":\"奥迪\",\"groupName\":\"一汽-大众奥迪\",\"iGrade\":4,\"id\":89813,\"parentId\":716704211135365120,\"sLogo\":\"/upload/logo/716723977224978432.jpg\",\"sName\":\"2018款 40 e-tron\",\"seriesName\":\"奥迪A6L新能源\",\"vDispla\":\"4T\",\"vYear\":\"2015\",\"ySedanBrandId\":716704993729576960}","gPrice":100,"isOrder":1,"createDate":"2020-12-09 09:46:47","gState":2,"isPay":0,"distributeJson":"[{\"headPortrait\":\"/upload/2020-10-15/20201015175931_273079.jpg\",\"id\":0,\"userBalance\":0.0,\"userHash\":\"36635C17B0E19DD1E14A92D8895B47C9\",\"userId\":0,\"userIntegral\":0,\"userName\":\"龙肆\",\"yStoreId\":692341585785913344}]","appeImgstr":"","estimateTime":"  ","rewardMoney":0}
         * isDelivery : 1
         * isPick : 1
         * deliveryAddress : 北京市东城区东华门街道肃亲王府
         * cMsg : 备注备注阿斯顿阿斯顿阿斯顿阿斯顿
         */

        private String id;
        private String yOrderId;
        private String userId;
        private String yStoreId;
        private String yTechnSedanId;
        private String yUserSedanId;
        private int iClassify;
        private double gPrice;
        private String longitude;
        private String latitude;
        private String appoinTime;
        private String sendAddress;
        private int gState;
        private String createDate;
        private UserSedanInfoBean user_sedan_info;
        private TechnSedanInfoBean techn_sedan_info;
        private int isDelivery;
        private int isPick;
        private String deliveryAddress;
        private String cMsg;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYOrderId() {
            return yOrderId;
        }

        public void setYOrderId(String yOrderId) {
            this.yOrderId = yOrderId;
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

        public String getYTechnSedanId() {
            return yTechnSedanId;
        }

        public void setYTechnSedanId(String yTechnSedanId) {
            this.yTechnSedanId = yTechnSedanId;
        }

        public String getYUserSedanId() {
            return yUserSedanId;
        }

        public void setYUserSedanId(String yUserSedanId) {
            this.yUserSedanId = yUserSedanId;
        }

        public int getIClassify() {
            return iClassify;
        }

        public void setIClassify(int iClassify) {
            this.iClassify = iClassify;
        }

        public double getGPrice() {
            return gPrice;
        }

        public void setGPrice(double gPrice) {
            this.gPrice = gPrice;
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

        public String getAppoinTime() {
            return appoinTime;
        }

        public void setAppoinTime(String appoinTime) {
            this.appoinTime = appoinTime;
        }

        public String getSendAddress() {
            return sendAddress;
        }

        public void setSendAddress(String sendAddress) {
            this.sendAddress = sendAddress;
        }

        public int getGState() {
            return gState;
        }

        public void setGState(int gState) {
            this.gState = gState;
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

        public TechnSedanInfoBean getTechn_sedan_info() {
            return techn_sedan_info;
        }

        public void setTechn_sedan_info(TechnSedanInfoBean techn_sedan_info) {
            this.techn_sedan_info = techn_sedan_info;
        }

        public int getIsDelivery() {
            return isDelivery;
        }

        public void setIsDelivery(int isDelivery) {
            this.isDelivery = isDelivery;
        }

        public int getIsPick() {
            return isPick;
        }

        public void setIsPick(int isPick) {
            this.isPick = isPick;
        }

        public String getDeliveryAddress() {
            return deliveryAddress;
        }

        public void setDeliveryAddress(String deliveryAddress) {
            this.deliveryAddress = deliveryAddress;
        }

        public String getCMsg() {
            return cMsg;
        }

        public void setCMsg(String cMsg) {
            this.cMsg = cMsg;
        }

        public static class UserSedanInfoBean {
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

            public static class BrandInfoBean {
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

        public static class TechnSedanInfoBean {
            /**
             * id : 1161
             * yTechnSedanId : 786166950815334400
             * yOrderId : 784048962330951680
             * ySedanBrandId : 716704993729576960
             * yStoreId : 692341585785913344
             * userId : 714547022807433218
             * licenseNumber : 粤BRJJJY
             * ownerName : 用户1
             * ownerPhone : 18306043086
             * frameNumber :
             * vehicleMileage :
             * compInsuranceTime :
             * comInsuranceTime :
             * annualReviewTime :
             * vRemarks : 备注备注阿斯顿阿斯顿阿斯顿阿斯顿
             * sedanBrandJson : {"brandName":"奥迪","groupName":"一汽-大众奥迪","iGrade":4,"id":89813,"parentId":716704211135365120,"sLogo":"/upload/logo/716723977224978432.jpg","sName":"2018款 40 e-tron","seriesName":"奥迪A6L新能源","vDispla":"4T","vYear":"2015","ySedanBrandId":716704993729576960}
             * gPrice : 100.0
             * isOrder : 1
             * createDate : 2020-12-09 09:46:47
             * gState : 2
             * isPay : 0
             * distributeJson : [{"headPortrait":"/upload/2020-10-15/20201015175931_273079.jpg","id":0,"userBalance":0.0,"userHash":"36635C17B0E19DD1E14A92D8895B47C9","userId":0,"userIntegral":0,"userName":"龙肆","yStoreId":692341585785913344}]
             * appeImgstr :
             * estimateTime :
             * rewardMoney : 0.0
             */

            private String id;
            private String yTechnSedanId;
            private String yOrderId;
            private String ySedanBrandId;
            private String yStoreId;
            private String userId;
            private String licenseNumber;
            private String ownerName;
            private String ownerPhone;
            private String frameNumber;
            private String vehicleMileage;
            private String compInsuranceTime;
            private String comInsuranceTime;
            private String annualReviewTime;
            private String vRemarks;
            private String sedanBrandJson;
            private double gPrice;
            private int isOrder;
            private String createDate;
            private int gState;
            private int isPay;
            private String distributeJson;
            private String appeImgstr;
            private String estimateTime;
            private double rewardMoney;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getYTechnSedanId() {
                return yTechnSedanId;
            }

            public void setYTechnSedanId(String yTechnSedanId) {
                this.yTechnSedanId = yTechnSedanId;
            }

            public String getYOrderId() {
                return yOrderId;
            }

            public void setYOrderId(String yOrderId) {
                this.yOrderId = yOrderId;
            }

            public String getYSedanBrandId() {
                return ySedanBrandId;
            }

            public void setYSedanBrandId(String ySedanBrandId) {
                this.ySedanBrandId = ySedanBrandId;
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

            public String getLicenseNumber() {
                return licenseNumber;
            }

            public void setLicenseNumber(String licenseNumber) {
                this.licenseNumber = licenseNumber;
            }

            public String getOwnerName() {
                return ownerName;
            }

            public void setOwnerName(String ownerName) {
                this.ownerName = ownerName;
            }

            public String getOwnerPhone() {
                return ownerPhone;
            }

            public void setOwnerPhone(String ownerPhone) {
                this.ownerPhone = ownerPhone;
            }

            public String getFrameNumber() {
                return frameNumber;
            }

            public void setFrameNumber(String frameNumber) {
                this.frameNumber = frameNumber;
            }

            public String getVehicleMileage() {
                return vehicleMileage;
            }

            public void setVehicleMileage(String vehicleMileage) {
                this.vehicleMileage = vehicleMileage;
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

            public String getVRemarks() {
                return vRemarks;
            }

            public void setVRemarks(String vRemarks) {
                this.vRemarks = vRemarks;
            }

            public String getSedanBrandJson() {
                return sedanBrandJson;
            }

            public void setSedanBrandJson(String sedanBrandJson) {
                this.sedanBrandJson = sedanBrandJson;
            }

            public double getGPrice() {
                return gPrice;
            }

            public void setGPrice(double gPrice) {
                this.gPrice = gPrice;
            }

            public int getIsOrder() {
                return isOrder;
            }

            public void setIsOrder(int isOrder) {
                this.isOrder = isOrder;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public int getGState() {
                return gState;
            }

            public void setGState(int gState) {
                this.gState = gState;
            }

            public int getIsPay() {
                return isPay;
            }

            public void setIsPay(int isPay) {
                this.isPay = isPay;
            }

            public String getDistributeJson() {
                return distributeJson;
            }

            public void setDistributeJson(String distributeJson) {
                this.distributeJson = distributeJson;
            }

            public String getAppeImgstr() {
                return appeImgstr;
            }

            public void setAppeImgstr(String appeImgstr) {
                this.appeImgstr = appeImgstr;
            }

            public String getEstimateTime() {
                return estimateTime;
            }

            public void setEstimateTime(String estimateTime) {
                this.estimateTime = estimateTime;
            }

            public double getRewardMoney() {
                return rewardMoney;
            }

            public void setRewardMoney(double rewardMoney) {
                this.rewardMoney = rewardMoney;
            }
        }
    }
}
