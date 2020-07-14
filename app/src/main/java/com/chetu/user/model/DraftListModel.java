package com.chetu.user.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.X on 2020/7/14.
 */
public class DraftListModel implements Serializable {
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 1177
         * yDraftId : 732594430787190784
         * userId : 714547022807433216
         * vStrs : 小保养套餐||大保养||空调滤清器||节气门清洗||发动机清洗
         * createDate : 2020-07-14 13:48:43
         */

        private String id;
        private String yDraftId;
        private String userId;
        private String vStrs;
        private String createDate;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYDraftId() {
            return yDraftId;
        }

        public void setYDraftId(String yDraftId) {
            this.yDraftId = yDraftId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getVStrs() {
            return vStrs;
        }

        public void setVStrs(String vStrs) {
            this.vStrs = vStrs;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }
    }
}
