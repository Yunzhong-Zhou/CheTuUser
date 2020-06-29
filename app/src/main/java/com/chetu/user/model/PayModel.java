package com.chetu.user.model;

import java.io.Serializable;

/**
 * Created by zyz on 2020/6/29.
 */
public class PayModel implements Serializable {
    /**
     * orderStr : alipay_sdk=alipay-sdk-java-3.3.1&app_id=2021001164691212&biz_content=%7B%22body%22%3A%22%22%2C%22out_trade_no%22%3A%22727279657203793920%22%2C%22passback_params%22%3A%22%22%2C%22product_code%22%3A%22QUICK_WAP_PAY%22%2C%22seller_id%22%3A%22%22%2C%22subject%22%3A%22%E8%BD%A6%E9%80%94%E6%94%AF%E4%BB%98%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%220.0%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=%2Fapi%2Fv1%2Fpay%2Fnotify&return_url=%2Fapi%2Fv1%2Fpay%2Fnotify&sign=C9oJx%2Bz4cXuDESrejPj9p8bckiJCcNMp7%2FZ0hqkTKp%2BmmcgCuzWDjZb%2BTfsAZI%2Fz40NZBUHxDBQBPDFaGPHkgAVUCiG7zAxzpjcg%2Fa3NVLQ2ZoxWoq39P1AOvlTvP%2BwA1XLXVAotDFHudhlWwWZSWR1mPmeODtjINOkQ8E7XOSqHSIpW6EbuuHGy8lPh8WjzrGS5vK5hCMy7f4YTOE%2FuFAvg%2B%2FDzVaSsSC10idMIWRVmvvlHFaN9iR2jyVtCh%2FWwnGdpKty0qSUuRQiLEwqd6AzZzh%2Bngb3VPHuG6k0t7UuKvLidLRUPZz1YzFIOUMIna5DKogkzUtL%2BqqRIej%2B%2FpA%3D%3D&sign_type=RSA2&timestamp=2020-06-29+21%3A49%3A42&version=1.0
     * pay_notify_url : /api/v1/pay/notify
     * money : 0.0
     * pay : {"payId":"727279657157656576","paySign":"TCJB83N6Y9IQ5ADLL307FFWP0YKTS4TM816ZJT9CJYMMT39T67W07G8PJLPYR8US1N53HAETPH7UV0RKLC7J6YQDL2QYERZ3W1LK","payment":1,"userId":"714547022807433216","codeStr":"41338C46FA8D4938A55C6450F29BEED3","start":0,"iClassify":1,"money":"0.0","createDate":"2020-06-29 21:49:42"}
     */

    private String orderStr;
    private String pay_notify_url;
    private String money;
    private PayBean pay;

    public String getOrderStr() {
        return orderStr;
    }

    public void setOrderStr(String orderStr) {
        this.orderStr = orderStr;
    }

    public String getPay_notify_url() {
        return pay_notify_url;
    }

    public void setPay_notify_url(String pay_notify_url) {
        this.pay_notify_url = pay_notify_url;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public PayBean getPay() {
        return pay;
    }

    public void setPay(PayBean pay) {
        this.pay = pay;
    }

    public static class PayBean {
        /**
         * payId : 727279657157656576
         * paySign : TCJB83N6Y9IQ5ADLL307FFWP0YKTS4TM816ZJT9CJYMMT39T67W07G8PJLPYR8US1N53HAETPH7UV0RKLC7J6YQDL2QYERZ3W1LK
         * payment : 1
         * userId : 714547022807433216
         * codeStr : 41338C46FA8D4938A55C6450F29BEED3
         * start : 0
         * iClassify : 1
         * money : 0.0
         * createDate : 2020-06-29 21:49:42
         */

        private String payId;
        private String paySign;
        private int payment;
        private String userId;
        private String codeStr;
        private int start;
        private int iClassify;
        private String money;
        private String createDate;

        public String getPayId() {
            return payId;
        }

        public void setPayId(String payId) {
            this.payId = payId;
        }

        public String getPaySign() {
            return paySign;
        }

        public void setPaySign(String paySign) {
            this.paySign = paySign;
        }

        public int getPayment() {
            return payment;
        }

        public void setPayment(int payment) {
            this.payment = payment;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getCodeStr() {
            return codeStr;
        }

        public void setCodeStr(String codeStr) {
            this.codeStr = codeStr;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getIClassify() {
            return iClassify;
        }

        public void setIClassify(int iClassify) {
            this.iClassify = iClassify;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }
    }
}
