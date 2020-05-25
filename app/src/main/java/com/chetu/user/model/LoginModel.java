package com.chetu.user.model;

import java.io.Serializable;

/**
 * Created by zyz on 2020-05-23.
 */
public class LoginModel implements Serializable {
    /**
     * user_info : {"userBalance":"0","userName":"","userAccount":"DF4TPS","uToken":"232CEAD19E9673CC12FB551416FCC533248B3F4A517491940C4F41F88DA4DA32","userHash":"923EC65D067A06348343AA07EAAD230E","headPortrait":"/timg.jpg"}
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
         * userAccount : DF4TPS
         * uToken : 232CEAD19E9673CC12FB551416FCC533248B3F4A517491940C4F41F88DA4DA32
         * userHash : 923EC65D067A06348343AA07EAAD230E
         * headPortrait : /timg.jpg
         */

        private String userBalance;
        private String userName;
        private String userAccount;
        private String uToken;
        private String userHash;
        private String headPortrait;

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

        public String getHeadPortrait() {
            return headPortrait;
        }

        public void setHeadPortrait(String headPortrait) {
            this.headPortrait = headPortrait;
        }
    }
}
