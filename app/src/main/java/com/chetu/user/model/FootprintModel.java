package com.chetu.user.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2020/5/26.
 */
public class FootprintModel implements Serializable {
    private List<ListBeanX> list;

    public List<ListBeanX> getList() {
        return list;
    }

    public void setList(List<ListBeanX> list) {
        this.list = list;
    }

    public static class ListBeanX {
        /**
         * year : 2020
         * list : [{"id":"1051","yUserNotepadId":"718797004750192640","userId":"714547022807433216","yTitle":"好尴尬好好干","yTag":"","vMoney":"5655","iMsg":"还有哈哈哈那就好","year":"2020","createDate":"2020-06-06 12:02:40"},{"id":"1050","yUserNotepadId":"718769677488095232","userId":"714547022807433216","year":"2020","createDate":"2020-06-06 10:14:05"}]
         */

        private String year;
        private List<ListBeanX.ListBean> list;

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public List<ListBeanX.ListBean> getList() {
            return list;
        }

        public void setList(List<ListBeanX.ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 1051
             * yUserNotepadId : 718797004750192640
             * userId : 714547022807433216
             * yTitle : 好尴尬好好干
             * yTag :
             * vMoney : 5655
             * iMsg : 还有哈哈哈那就好
             * year : 2020
             * createDate : 2020-06-06 12:02:40
             */

            private String id;
            private String yUserNotepadId;
            private String userId;
            private String yTitle;
            private String yTag;
            private String vMoney;
            private String iMsg;
            private String year;
            private String createDate;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getYUserNotepadId() {
                return yUserNotepadId;
            }

            public void setYUserNotepadId(String yUserNotepadId) {
                this.yUserNotepadId = yUserNotepadId;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getYTitle() {
                return yTitle;
            }

            public void setYTitle(String yTitle) {
                this.yTitle = yTitle;
            }

            public String getYTag() {
                return yTag;
            }

            public void setYTag(String yTag) {
                this.yTag = yTag;
            }

            public String getVMoney() {
                return vMoney;
            }

            public void setVMoney(String vMoney) {
                this.vMoney = vMoney;
            }

            public String getIMsg() {
                return iMsg;
            }

            public void setIMsg(String iMsg) {
                this.iMsg = iMsg;
            }

            public String getYear() {
                return year;
            }

            public void setYear(String year) {
                this.year = year;
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
