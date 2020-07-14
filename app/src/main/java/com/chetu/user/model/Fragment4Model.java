package com.chetu.user.model;

import java.io.Serializable;

/**
 * Created by zyz on 2019-09-30.
 */
public class Fragment4Model implements Serializable {
    /**
     * user_info : {"userId":"714547022807433216","userBalance":0,"yStoreId":"692341585785913344","userName":"阿斯顿马丁","userAccount":"Y11P3D","uToken":"878FFBB91EB36C913E76875CFB338E035AC9046CF8BD6A4D568B2A1A8A79ACDE","isTechn":0,"userHash":"36635C17B0E19DD1E14A92D8895B47CD","headPortrait":"/upload/2020-06-13/20200613085845_638405.jpeg","userPhone":"18306043086","userJson":"{\"birthday\":\"2002-07-13\",\"u_gender\":\"男\"}","techJson":" {\"star\":\"4\",\"working\":\"1\"}","isAuth":1,"isKf":0,"auth_info":{"real_name":" ","identity_card":" ","identity_positive":" ","identity_back":" "},"setup_info":{"u_gender":"男","birthday":"2002-07-13"},"tech_info":{"star":4,"working":1},"store_info":{},"user_sedan_info":{"id":"1077","yUserSedanId":"718485269891776512","userId":"714547022807433216","sName":"2020款 55 TFSIe quattro","sLogo":"/upload/head/716723977224978432.jpg","sCy":2,"isF":1,"isDel":0,"iViolation":1,"reportPoliceJson":"{\"createDate\":\"2020-04-15 08:44:18\",\"id\":1047,\"telephone\":\"400-2123-2211\",\"vName\":\"人寿保险公司\",\"vNameNature\":\"普通保险\",\"yReportPoliceId\":699902914180677632}","jReportPoliceJson":"{\"createDate\":\"2020-04-15 08:44:18\",\"id\":1047,\"telephone\":\"400-2123-2211\",\"vName\":\"人寿保险公司\",\"vNameNature\":\"普通保险\",\"yReportPoliceId\":699902914180677632}","brandJson":"{\"brandName\":\"奥迪\",\"groupName\":\"一汽-大众奥迪\",\"id\":99479,\"parentId\":716704211135365120,\"sLogo\":\"/upload/head/716723977224978432.jpg\",\"sName\":\"2020款 55 TFSIe quattro\",\"seriesName\":\"奥迪A6L新能源\",\"ySedanBrandId\":716705259468095488}","userPhone":"18306043086","sNumber":"渝A12345","brandInfo":{"id":"99479","ySedanBrandId":"716705259468095488","parentId":"716704211135365120","sName":"一汽-大众奥迪 奥迪A6L新能源 2020款 55 TFSIe quattro","sLogo":"/upload/head/716723977224978432.jpg","seriesName":"奥迪A6L新能源","groupName":"一汽-大众奥迪","brandName":"奥迪"},"compInsuranceTime":"2020-12-04","comInsuranceTime":"2020-12-04","annualReviewTime":"2020-12-04","maintainTime":"2020-12-04","createDate":"2020-06-05 15:23:57","compTime":174,"comTime":174,"maintIime":174,"annualTime":174}}
     */

    private UserInfoBean user_info;

    public UserInfoBean getUser_info() {
        return user_info;
    }

    public void setUser_info(UserInfoBean user_info) {
        this.user_info = user_info;
    }

    public static class UserInfoBean {
        /**
         * userId : 714547022807433216
         * userBalance : 0.0
         * yStoreId : 692341585785913344
         * userName : 阿斯顿马丁
         * userAccount : Y11P3D
         * uToken : 878FFBB91EB36C913E76875CFB338E035AC9046CF8BD6A4D568B2A1A8A79ACDE
         * isTechn : 0
         * userHash : 36635C17B0E19DD1E14A92D8895B47CD
         * headPortrait : /upload/2020-06-13/20200613085845_638405.jpeg
         * userPhone : 18306043086
         * userJson : {"birthday":"2002-07-13","u_gender":"男"}
         * techJson :  {"star":"4","working":"1"}
         * isAuth : 1
         * isKf : 0
         * auth_info : {"real_name":" ","identity_card":" ","identity_positive":" ","identity_back":" "}
         * setup_info : {"u_gender":"男","birthday":"2002-07-13"}
         * tech_info : {"star":4,"working":1}
         * store_info : {}
         * user_sedan_info : {"id":"1077","yUserSedanId":"718485269891776512","userId":"714547022807433216","sName":"2020款 55 TFSIe quattro","sLogo":"/upload/head/716723977224978432.jpg","sCy":2,"isF":1,"isDel":0,"iViolation":1,"reportPoliceJson":"{\"createDate\":\"2020-04-15 08:44:18\",\"id\":1047,\"telephone\":\"400-2123-2211\",\"vName\":\"人寿保险公司\",\"vNameNature\":\"普通保险\",\"yReportPoliceId\":699902914180677632}","jReportPoliceJson":"{\"createDate\":\"2020-04-15 08:44:18\",\"id\":1047,\"telephone\":\"400-2123-2211\",\"vName\":\"人寿保险公司\",\"vNameNature\":\"普通保险\",\"yReportPoliceId\":699902914180677632}","brandJson":"{\"brandName\":\"奥迪\",\"groupName\":\"一汽-大众奥迪\",\"id\":99479,\"parentId\":716704211135365120,\"sLogo\":\"/upload/head/716723977224978432.jpg\",\"sName\":\"2020款 55 TFSIe quattro\",\"seriesName\":\"奥迪A6L新能源\",\"ySedanBrandId\":716705259468095488}","userPhone":"18306043086","sNumber":"渝A12345","brandInfo":{"id":"99479","ySedanBrandId":"716705259468095488","parentId":"716704211135365120","sName":"一汽-大众奥迪 奥迪A6L新能源 2020款 55 TFSIe quattro","sLogo":"/upload/head/716723977224978432.jpg","seriesName":"奥迪A6L新能源","groupName":"一汽-大众奥迪","brandName":"奥迪"},"compInsuranceTime":"2020-12-04","comInsuranceTime":"2020-12-04","annualReviewTime":"2020-12-04","maintainTime":"2020-12-04","createDate":"2020-06-05 15:23:57","compTime":174,"comTime":174,"maintIime":174,"annualTime":174}
         */

        private String userId;
        private String userBalance;
        private String yStoreId;
        private String userName;
        private String userAccount;
        private String uToken;
        private int isTechn;
        private String userHash;
        private String headPortrait;
        private String userPhone;
        private String userJson;
        private String techJson;
        private int isAuth;
        private int isKf;
        private AuthInfoBean auth_info;
        private SetupInfoBean setup_info;
        private TechInfoBean tech_info;
        private StoreInfoBean store_info;
        private UserSedanInfoBean user_sedan_info;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserBalance() {
            return userBalance;
        }

        public void setUserBalance(String userBalance) {
            this.userBalance = userBalance;
        }

        public String getYStoreId() {
            return yStoreId;
        }

        public void setYStoreId(String yStoreId) {
            this.yStoreId = yStoreId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserAccount() {
            return userAccount;
        }

        public void setUserAccount(String userAccount) {
            this.userAccount = userAccount;
        }

        public String getUToken() {
            return uToken;
        }

        public void setUToken(String uToken) {
            this.uToken = uToken;
        }

        public int getIsTechn() {
            return isTechn;
        }

        public void setIsTechn(int isTechn) {
            this.isTechn = isTechn;
        }

        public String getUserHash() {
            return userHash;
        }

        public void setUserHash(String userHash) {
            this.userHash = userHash;
        }

        public String getHeadPortrait() {
            return headPortrait;
        }

        public void setHeadPortrait(String headPortrait) {
            this.headPortrait = headPortrait;
        }

        public String getUserPhone() {
            return userPhone;
        }

        public void setUserPhone(String userPhone) {
            this.userPhone = userPhone;
        }

        public String getUserJson() {
            return userJson;
        }

        public void setUserJson(String userJson) {
            this.userJson = userJson;
        }

        public String getTechJson() {
            return techJson;
        }

        public void setTechJson(String techJson) {
            this.techJson = techJson;
        }

        public int getIsAuth() {
            return isAuth;
        }

        public void setIsAuth(int isAuth) {
            this.isAuth = isAuth;
        }

        public int getIsKf() {
            return isKf;
        }

        public void setIsKf(int isKf) {
            this.isKf = isKf;
        }

        public AuthInfoBean getAuth_info() {
            return auth_info;
        }

        public void setAuth_info(AuthInfoBean auth_info) {
            this.auth_info = auth_info;
        }

        public SetupInfoBean getSetup_info() {
            return setup_info;
        }

        public void setSetup_info(SetupInfoBean setup_info) {
            this.setup_info = setup_info;
        }

        public TechInfoBean getTech_info() {
            return tech_info;
        }

        public void setTech_info(TechInfoBean tech_info) {
            this.tech_info = tech_info;
        }

        public StoreInfoBean getStore_info() {
            return store_info;
        }

        public void setStore_info(StoreInfoBean store_info) {
            this.store_info = store_info;
        }

        public UserSedanInfoBean getUser_sedan_info() {
            return user_sedan_info;
        }

        public void setUser_sedan_info(UserSedanInfoBean user_sedan_info) {
            this.user_sedan_info = user_sedan_info;
        }

        public static class AuthInfoBean {
            /**
             * real_name :
             * identity_card :
             * identity_positive :
             * identity_back :
             */

            private String real_name;
            private String identity_card;
            private String identity_positive;
            private String identity_back;

            public String getReal_name() {
                return real_name;
            }

            public void setReal_name(String real_name) {
                this.real_name = real_name;
            }

            public String getIdentity_card() {
                return identity_card;
            }

            public void setIdentity_card(String identity_card) {
                this.identity_card = identity_card;
            }

            public String getIdentity_positive() {
                return identity_positive;
            }

            public void setIdentity_positive(String identity_positive) {
                this.identity_positive = identity_positive;
            }

            public String getIdentity_back() {
                return identity_back;
            }

            public void setIdentity_back(String identity_back) {
                this.identity_back = identity_back;
            }
        }

        public static class SetupInfoBean {
            /**
             * u_gender : 男
             * birthday : 2002-07-13
             */

            private String u_gender;
            private String birthday;

            public String getU_gender() {
                return u_gender;
            }

            public void setU_gender(String u_gender) {
                this.u_gender = u_gender;
            }

            public String getBirthday() {
                return birthday;
            }

            public void setBirthday(String birthday) {
                this.birthday = birthday;
            }
        }

        public static class TechInfoBean {
        }

        public static class StoreInfoBean {
        }

        public static class UserSedanInfoBean {
            /**
             * id : 1077
             * yUserSedanId : 718485269891776512
             * userId : 714547022807433216
             * sName : 2020款 55 TFSIe quattro
             * sLogo : /upload/head/716723977224978432.jpg
             * sCy : 2
             * isF : 1
             * isDel : 0
             * iViolation : 1
             * reportPoliceJson : {"createDate":"2020-04-15 08:44:18","id":1047,"telephone":"400-2123-2211","vName":"人寿保险公司","vNameNature":"普通保险","yReportPoliceId":699902914180677632}
             * jReportPoliceJson : {"createDate":"2020-04-15 08:44:18","id":1047,"telephone":"400-2123-2211","vName":"人寿保险公司","vNameNature":"普通保险","yReportPoliceId":699902914180677632}
             * brandJson : {"brandName":"奥迪","groupName":"一汽-大众奥迪","id":99479,"parentId":716704211135365120,"sLogo":"/upload/head/716723977224978432.jpg","sName":"2020款 55 TFSIe quattro","seriesName":"奥迪A6L新能源","ySedanBrandId":716705259468095488}
             * userPhone : 18306043086
             * sNumber : 渝A12345
             * brandInfo : {"id":"99479","ySedanBrandId":"716705259468095488","parentId":"716704211135365120","sName":"一汽-大众奥迪 奥迪A6L新能源 2020款 55 TFSIe quattro","sLogo":"/upload/head/716723977224978432.jpg","seriesName":"奥迪A6L新能源","groupName":"一汽-大众奥迪","brandName":"奥迪"}
             * compInsuranceTime : 2020-12-04
             * comInsuranceTime : 2020-12-04
             * annualReviewTime : 2020-12-04
             * maintainTime : 2020-12-04
             * createDate : 2020-06-05 15:23:57
             * compTime : 174
             * comTime : 174
             * maintIime : 174
             * annualTime : 174
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
            private int compTime;
            private int comTime;
            private int maintIime;
            private int annualTime;

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

            public int getCompTime() {
                return compTime;
            }

            public void setCompTime(int compTime) {
                this.compTime = compTime;
            }

            public int getComTime() {
                return comTime;
            }

            public void setComTime(int comTime) {
                this.comTime = comTime;
            }

            public int getMaintIime() {
                return maintIime;
            }

            public void setMaintIime(int maintIime) {
                this.maintIime = maintIime;
            }

            public int getAnnualTime() {
                return annualTime;
            }

            public void setAnnualTime(int annualTime) {
                this.annualTime = annualTime;
            }

            public static class BrandInfoBean {
                /**
                 * id : 99479
                 * ySedanBrandId : 716705259468095488
                 * parentId : 716704211135365120
                 * sName : 一汽-大众奥迪 奥迪A6L新能源 2020款 55 TFSIe quattro
                 * sLogo : /upload/head/716723977224978432.jpg
                 * seriesName : 奥迪A6L新能源
                 * groupName : 一汽-大众奥迪
                 * brandName : 奥迪
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
}
