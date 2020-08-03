package com.chetu.user.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2020/8/2.
 */
public class CarInsuranceListModel implements Serializable {
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 1030
         * yInquiryId : 719209115515092992
         * userId : 714547022807433216
         * licensePlate : 渝A12345
         * frameNo : a12345123i3828
         * fullName : 阿斯顿马丁
         * telephone : 18306043086
         * tNumber : 36262727362io3i27288373737
         * iCompany : 人寿保险
         * vInsureCity : 重庆
         * licenseImg : /upload/2020-06-07/20200607151944_675768.jpg||/upload/2020-06-07/20200607151950_666753.jpg
         * numberImg : /upload/2020-06-07/20200607151957_744478.jpeg||/upload/2020-06-07/20200607152003_207058.jpg
         * replyContent : 后台回复内容
         * createDate : 2020-06-07 15:20:15
         */

        private String id;
        private String yInquiryId;
        private String userId;
        private String licensePlate;
        private String frameNo;
        private String fullName;
        private String telephone;
        private String tNumber;
        private String iCompany;
        private String vInsureCity;
        private String licenseImg;
        private String numberImg;
        private String replyContent;
        private String createDate;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYInquiryId() {
            return yInquiryId;
        }

        public void setYInquiryId(String yInquiryId) {
            this.yInquiryId = yInquiryId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getLicensePlate() {
            return licensePlate;
        }

        public void setLicensePlate(String licensePlate) {
            this.licensePlate = licensePlate;
        }

        public String getFrameNo() {
            return frameNo;
        }

        public void setFrameNo(String frameNo) {
            this.frameNo = frameNo;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getTNumber() {
            return tNumber;
        }

        public void setTNumber(String tNumber) {
            this.tNumber = tNumber;
        }

        public String getICompany() {
            return iCompany;
        }

        public void setICompany(String iCompany) {
            this.iCompany = iCompany;
        }

        public String getVInsureCity() {
            return vInsureCity;
        }

        public void setVInsureCity(String vInsureCity) {
            this.vInsureCity = vInsureCity;
        }

        public String getLicenseImg() {
            return licenseImg;
        }

        public void setLicenseImg(String licenseImg) {
            this.licenseImg = licenseImg;
        }

        public String getNumberImg() {
            return numberImg;
        }

        public void setNumberImg(String numberImg) {
            this.numberImg = numberImg;
        }

        public String getReplyContent() {
            return replyContent;
        }

        public void setReplyContent(String replyContent) {
            this.replyContent = replyContent;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }
    }
}
