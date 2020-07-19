package com.chetu.user.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by zyz on 2020/6/29.
 */
public class PayModel implements Serializable {
    /**
     * Pay_notify_url : http://frb.yxy5g.com//api/v1/pay/notify
     * wechat : {"package":"Sign=WXPay","appid":"wxf3169961af7c5e80","sign":"BAE4234E16C26A61C1F29DAF061941BA","partnerid":"1574690931","prepayid":"wx19172749749606da30e859f11290217200","noncestr":"fts1mwu5bitfqh41pxs1a28gy44vm3c4","timestamp":"1595150869"}
     * money : 10.0
     * pay : {"payId":"734461509152800768","yTechnSedanId":"732555996450258944","paySign":"2VXTJLDQ7AZ9PV3NMPUNFAFPRS2GS432ULGSCAMX5GBAWXS4QC0XD1SAEDB2NV3RHWVBYGT05EZ1N87EILZ7VEE3UZTCVVRJ2H9D","payment":2,"userId":"714547022807433216","codeStr":"17BB61B9C4B3398C96D683CCB731DC4F","start":0,"iClassify":1,"money":10,"createDate":"2020-07-19 17:27:49"}
     */

    private String Pay_notify_url;
    private String orderStr;
    private WechatBean wechat;
    private String money;
    private PayBean pay;

    public String getOrderStr() {
        return orderStr;
    }

    public void setOrderStr(String orderStr) {
        this.orderStr = orderStr;
    }

    public String getPay_notify_url() {
        return Pay_notify_url;
    }

    public void setPay_notify_url(String Pay_notify_url) {
        this.Pay_notify_url = Pay_notify_url;
    }

    public WechatBean getWechat() {
        return wechat;
    }

    public void setWechat(WechatBean wechat) {
        this.wechat = wechat;
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

    public static class WechatBean {
        /**
         * package : Sign=WXPay
         * appid : wxf3169961af7c5e80
         * sign : BAE4234E16C26A61C1F29DAF061941BA
         * partnerid : 1574690931
         * prepayid : wx19172749749606da30e859f11290217200
         * noncestr : fts1mwu5bitfqh41pxs1a28gy44vm3c4
         * timestamp : 1595150869
         */

        @SerializedName("package")
        private String packageX;
        private String appid;
        private String sign;
        private String partnerid;
        private String prepayid;
        private String noncestr;
        private String timestamp;

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }
    }

    public static class PayBean {
        /**
         * payId : 734461509152800768
         * yTechnSedanId : 732555996450258944
         * paySign : 2VXTJLDQ7AZ9PV3NMPUNFAFPRS2GS432ULGSCAMX5GBAWXS4QC0XD1SAEDB2NV3RHWVBYGT05EZ1N87EILZ7VEE3UZTCVVRJ2H9D
         * payment : 2
         * userId : 714547022807433216
         * codeStr : 17BB61B9C4B3398C96D683CCB731DC4F
         * start : 0
         * iClassify : 1
         * money : 10.0
         * createDate : 2020-07-19 17:27:49
         */

        private String payId;
        private String yTechnSedanId;
        private String paySign;
        private int payment;
        private String userId;
        private String codeStr;
        private int start;
        private int iClassify;
        private double money;
        private String createDate;

        public String getPayId() {
            return payId;
        }

        public void setPayId(String payId) {
            this.payId = payId;
        }

        public String getYTechnSedanId() {
            return yTechnSedanId;
        }

        public void setYTechnSedanId(String yTechnSedanId) {
            this.yTechnSedanId = yTechnSedanId;
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

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
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
