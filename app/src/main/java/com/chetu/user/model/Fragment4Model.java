package com.chetu.user.model;

import java.io.Serializable;

/**
 * Created by zyz on 2019-09-30.
 */
public class Fragment4Model implements Serializable {
    /**
     * user_info : {"userBalance":"0","userName":"","userAccount":"Y11P3D","uToken":"878FFBB91EB36C913E76875CFB338E035AC9046CF8BD6A4D568B2A1A8A79ACDE","isTechn":0,"userHash":"36635C17B0E19DD1E14A92D8895B47CD","headPortrait":"/upload/head/716658569751035904.jpg","userPhone":"18306043086","user_json":"{\"birthday\":\"1985.12.48\",\"u_gender\":\"男\"}","tech_json":" {\"star\":\"4\",\"working\":\"1\"}","setup_info":{"u_gender":"男","birthday":"1985.12.48"},"tech_info":{"star":4,"working":1}}
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
         * userBalance : 0
         * userName :
         * userAccount : Y11P3D
         * uToken : 878FFBB91EB36C913E76875CFB338E035AC9046CF8BD6A4D568B2A1A8A79ACDE
         * isTechn : 0
         * userHash : 36635C17B0E19DD1E14A92D8895B47CD
         * headPortrait : /upload/head/716658569751035904.jpg
         * userPhone : 18306043086
         * user_json : {"birthday":"1985.12.48","u_gender":"男"}
         * tech_json :  {"star":"4","working":"1"}
         * setup_info : {"u_gender":"男","birthday":"1985.12.48"}
         * tech_info : {"star":4,"working":1}
         */

        private String userBalance;
        private String userName;
        private String userAccount;
        private String uToken;
        private int isTechn;
        private String userHash;
        private String headPortrait;
        private String userPhone;
        private String user_json;
        private String tech_json;
        private SetupInfoBean setup_info;
        private TechInfoBean tech_info;

        public String getUserBalance() {
            return userBalance;
        }

        public void setUserBalance(String userBalance) {
            this.userBalance = userBalance;
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

        public String getUser_json() {
            return user_json;
        }

        public void setUser_json(String user_json) {
            this.user_json = user_json;
        }

        public String getTech_json() {
            return tech_json;
        }

        public void setTech_json(String tech_json) {
            this.tech_json = tech_json;
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

        public static class SetupInfoBean {
            /**
             * u_gender : 男
             * birthday : 1985.12.48
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
            /**
             * star : 4
             * working : 1
             */

            private int star;
            private int working;

            public int getStar() {
                return star;
            }

            public void setStar(int star) {
                this.star = star;
            }

            public int getWorking() {
                return working;
            }

            public void setWorking(int working) {
                this.working = working;
            }
        }
    }
}
