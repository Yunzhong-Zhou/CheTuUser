package com.chetu.user.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2020/5/30.
 */
public class AddCarModel implements Serializable {
    private List<ListBean> list;
    private List<HostListBean> host_list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public List<HostListBean> getHost_list() {
        return host_list;
    }

    public void setHost_list(List<HostListBean> host_list) {
        this.host_list = host_list;
    }

    public static class ListBean {
        /**
         * id : 801
         * ySedanBrandId : 20180531104813
         * parentId : 0
         * sName : 奥迪
         * sBrand : 奥迪
         * sLogo : /timg.jpg
         * sLetter : A
         */



        private String id;
        private String ySedanBrandId;
        private String parentId;
        private String sName;
        private String sBrand;
        private String sLogo;
        private String sLetter;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYSedanBrandId() {
            return ySedanBrandId;
        }

        public void setYSedanBrandId(String ySedanBrandId) {
            this.ySedanBrandId = ySedanBrandId;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public String getSName() {
            return sName;
        }

        public void setSName(String sName) {
            this.sName = sName;
        }

        public String getSBrand() {
            return sBrand;
        }

        public void setSBrand(String sBrand) {
            this.sBrand = sBrand;
        }

        public String getSLogo() {
            return sLogo;
        }

        public void setSLogo(String sLogo) {
            this.sLogo = sLogo;
        }

        public String getSLetter() {
            return sLetter;
        }

        public void setSLetter(String sLetter) {
            this.sLetter = sLetter;
        }
    }

    public static class HostListBean {
        /**
         * id : 801
         * ySedanBrandId : 20180531104813
         * parentId : 0
         * sName : 奥迪
         * sBrand : 奥迪
         * sLogo : /timg.jpg
         * sLetter : A
         */

        private String id;
        private String ySedanBrandId;
        private String parentId;
        private String sName;
        private String sBrand;
        private String sLogo;
        private String sLetter;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYSedanBrandId() {
            return ySedanBrandId;
        }

        public void setYSedanBrandId(String ySedanBrandId) {
            this.ySedanBrandId = ySedanBrandId;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public String getSName() {
            return sName;
        }

        public void setSName(String sName) {
            this.sName = sName;
        }

        public String getSBrand() {
            return sBrand;
        }

        public void setSBrand(String sBrand) {
            this.sBrand = sBrand;
        }

        public String getSLogo() {
            return sLogo;
        }

        public void setSLogo(String sLogo) {
            this.sLogo = sLogo;
        }

        public String getSLetter() {
            return sLetter;
        }

        public void setSLetter(String sLetter) {
            this.sLetter = sLetter;
        }
    }
}
