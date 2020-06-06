package com.chetu.user.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2020/6/6.
 */
public class NotebookModel implements Serializable {
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 1048
         * yUserNotepadId : 718760938370498560
         * userId : 714547022807433216
         * yTitle : 阿斯顿马丁阿斯顿马丁宝
         * yTag :
         * vMoney : 123456.789
         * iMsg : 发货电饭锅刚发的更丰富哈哈哈加工费
         * createDate : 2020-06-06 09:39:21
         */

        private String id;
        private String yUserNotepadId;
        private String userId;
        private String yTitle;
        private String yTag;
        private String vMoney;
        private String iMsg;
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

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }
    }
}
