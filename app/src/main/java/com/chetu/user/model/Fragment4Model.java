package com.chetu.user.model;

import java.io.Serializable;

/**
 * Created by zyz on 2019-09-30.
 */
public class Fragment4Model implements Serializable {
    /**
     * user_info : {"userBalance":"0","userName":"","userAccount":"Y11P3D","uToken":"878FFBB91EB36C913E76875CFB338E035AC9046CF8BD6A4D568B2A1A8A79ACDE","userHash":"36635C17B0E19DD1E14A92D8895B47CD"}
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
         * userHash : 36635C17B0E19DD1E14A92D8895B47CD
         */

        private String userBalance;
        private String userName;
        private String userAccount;
        private String uToken;
        private String userHash;

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

        public String getUserHash() {
            return userHash;
        }

        public void setUserHash(String userHash) {
            this.userHash = userHash;
        }
    }
}
