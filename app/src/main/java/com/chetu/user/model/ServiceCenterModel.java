package com.chetu.user.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2020/6/6.
 */
public class ServiceCenterModel implements Serializable {
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 0
         * userId : 0
         * userBalance : 0.0
         * userName : 龙肆
         * isKf : 1
         * userIntegral : 0
         * yStoreId : 0
         * userHash : 3B2372646663FDC7A81EA8E241CB7946AC74E4C0C9CFD31F750FEE2831528FF5
         * headPortrait : /upload/2020-07-12/20200712101732_309412.jpg
         * isOnline : 1
         */

        private String id;
        private String userId;
        private double userBalance;
        private String userName;
        private int isKf;
        private String userIntegral;
        private String yStoreId;
        private String userHash;
        private String headPortrait;
        private int isOnline;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public double getUserBalance() {
            return userBalance;
        }

        public void setUserBalance(double userBalance) {
            this.userBalance = userBalance;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getIsKf() {
            return isKf;
        }

        public void setIsKf(int isKf) {
            this.isKf = isKf;
        }

        public String getUserIntegral() {
            return userIntegral;
        }

        public void setUserIntegral(String userIntegral) {
            this.userIntegral = userIntegral;
        }

        public String getYStoreId() {
            return yStoreId;
        }

        public void setYStoreId(String yStoreId) {
            this.yStoreId = yStoreId;
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

        public int getIsOnline() {
            return isOnline;
        }

        public void setIsOnline(int isOnline) {
            this.isOnline = isOnline;
        }
    }
}
