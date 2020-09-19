package com.chetu.user.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2020/6/9.
 */
public class ServiceListModel_All implements Serializable {
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 1017
         * yServiceId : 692341585785913344
         * yParentId : 0
         * vName : 洗车美容
         * vImg : /upload/index/index1.png
         * isIndex : 1
         * isSheet : 0
         * createDate : 2020-03-25 11:58:17
         * v_list : [{"id":"1034","yServiceId":"692341585785913356","yParentId":"692341585785913344","vName":"精洗","createDate":"2020-03-25 11:58:17","v_list":[{"id":"1041","yServiceId":"692341585785913362","yParentId":"692341585785913356","vName":"外洗30","createDate":"2020-03-25 11:58:17","v_list":[{"id":"1047","yServiceId":"692341585785913368","yParentId":"692341585785913362","vName":"外洗30四级","createDate":"2020-03-25 11:58:17"}]},{"id":"1042","yServiceId":"692341585785913363","yParentId":"692341585785913356","vName":"普洗35","createDate":"2020-03-25 11:58:17","v_list":[]},{"id":"1043","yServiceId":"692341585785913364","yParentId":"692341585785913356","vName":"普洗40","createDate":"2020-03-25 11:58:17","v_list":[]},{"id":"1044","yServiceId":"692341585785913365","yParentId":"692341585785913356","vName":"精洗60","createDate":"2020-03-25 11:58:17","v_list":[]},{"id":"1045","yServiceId":"692341585785913366","yParentId":"692341585785913356","vName":"精洗70","createDate":"2020-03-25 11:58:17","v_list":[]},{"id":"1046","yServiceId":"692341585785913367","yParentId":"692341585785913356","vName":"深度精洗168","createDate":"2020-03-25 11:58:17","v_list":[]}]},{"id":"1035","yServiceId":"692341585785913357","yParentId":"692341585785913344","vName":"打腊","createDate":"2020-03-25 11:58:17","v_list":[]},{"id":"1036","yServiceId":"692341585785913358","yParentId":"692341585785913344","vName":"内饰","createDate":"2020-03-25 11:58:17","v_list":[]},{"id":"1037","yServiceId":"692341585785913359","yParentId":"692341585785913344","vName":"镀晶","createDate":"2020-03-25 11:58:17","v_list":[]},{"id":"1038","yServiceId":"692341585785913360","yParentId":"692341585785913344","vName":"贴膜","createDate":"2020-03-25 11:58:17","v_list":[]},{"id":"1039","yServiceId":"692341585785913361","yParentId":"692341585785913344","vName":"消毒","createDate":"2020-03-25 11:58:17","v_list":[]}]
         */
        private boolean isgouxuan = false;

        public boolean isIsgouxuan() {
            return isgouxuan;
        }

        public void setIsgouxuan(boolean isgouxuan) {
            this.isgouxuan = isgouxuan;
        }

        private String id;
        private String yServiceId;
        private String yParentId;
        private String vName;
        private String vImg;
        private int isIndex;
        private int isSheet;
        private String createDate;
        private List<VListBeanXX> v_list;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYServiceId() {
            return yServiceId;
        }

        public void setYServiceId(String yServiceId) {
            this.yServiceId = yServiceId;
        }

        public String getYParentId() {
            return yParentId;
        }

        public void setYParentId(String yParentId) {
            this.yParentId = yParentId;
        }

        public String getVName() {
            return vName;
        }

        public void setVName(String vName) {
            this.vName = vName;
        }

        public String getVImg() {
            return vImg;
        }

        public void setVImg(String vImg) {
            this.vImg = vImg;
        }

        public int getIsIndex() {
            return isIndex;
        }

        public void setIsIndex(int isIndex) {
            this.isIndex = isIndex;
        }

        public int getIsSheet() {
            return isSheet;
        }

        public void setIsSheet(int isSheet) {
            this.isSheet = isSheet;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public List<VListBeanXX> getV_list() {
            return v_list;
        }

        public void setV_list(List<VListBeanXX> v_list) {
            this.v_list = v_list;
        }

        public static class VListBeanXX {
            /**
             * id : 1034
             * yServiceId : 692341585785913356
             * yParentId : 692341585785913344
             * vName : 精洗
             * createDate : 2020-03-25 11:58:17
             * v_list : [{"id":"1041","yServiceId":"692341585785913362","yParentId":"692341585785913356","vName":"外洗30","createDate":"2020-03-25 11:58:17","v_list":[{"id":"1047","yServiceId":"692341585785913368","yParentId":"692341585785913362","vName":"外洗30四级","createDate":"2020-03-25 11:58:17"}]},{"id":"1042","yServiceId":"692341585785913363","yParentId":"692341585785913356","vName":"普洗35","createDate":"2020-03-25 11:58:17","v_list":[]},{"id":"1043","yServiceId":"692341585785913364","yParentId":"692341585785913356","vName":"普洗40","createDate":"2020-03-25 11:58:17","v_list":[]},{"id":"1044","yServiceId":"692341585785913365","yParentId":"692341585785913356","vName":"精洗60","createDate":"2020-03-25 11:58:17","v_list":[]},{"id":"1045","yServiceId":"692341585785913366","yParentId":"692341585785913356","vName":"精洗70","createDate":"2020-03-25 11:58:17","v_list":[]},{"id":"1046","yServiceId":"692341585785913367","yParentId":"692341585785913356","vName":"深度精洗168","createDate":"2020-03-25 11:58:17","v_list":[]}]
             */

            private boolean isgouxuan = false;

            public boolean isIsgouxuan() {
                return isgouxuan;
            }

            public void setIsgouxuan(boolean isgouxuan) {
                this.isgouxuan = isgouxuan;
            }

            private String content = "";

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            private String id;
            private String yServiceId;
            private String yParentId;
            private String vName;
            private String createDate;
            private List<VListBeanX> v_list;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getYServiceId() {
                return yServiceId;
            }

            public void setYServiceId(String yServiceId) {
                this.yServiceId = yServiceId;
            }

            public String getYParentId() {
                return yParentId;
            }

            public void setYParentId(String yParentId) {
                this.yParentId = yParentId;
            }

            public String getVName() {
                return vName;
            }

            public void setVName(String vName) {
                this.vName = vName;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public List<VListBeanX> getV_list() {
                return v_list;
            }

            public void setV_list(List<VListBeanX> v_list) {
                this.v_list = v_list;
            }

            public static class VListBeanX {
                /**
                 * id : 1041
                 * yServiceId : 692341585785913362
                 * yParentId : 692341585785913356
                 * vName : 外洗30
                 * createDate : 2020-03-25 11:58:17
                 * v_list : [{"id":"1047","yServiceId":"692341585785913368","yParentId":"692341585785913362","vName":"外洗30四级","createDate":"2020-03-25 11:58:17"}]
                 */

                private boolean isgouxuan = false;

                public boolean isIsgouxuan() {
                    return isgouxuan;
                }

                public void setIsgouxuan(boolean isgouxuan) {
                    this.isgouxuan = isgouxuan;
                }
                private String id;
                private String yServiceId;
                private String yParentId;
                private String vName;
                private String createDate;
                private List<VListBean> v_list;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getYServiceId() {
                    return yServiceId;
                }

                public void setYServiceId(String yServiceId) {
                    this.yServiceId = yServiceId;
                }

                public String getYParentId() {
                    return yParentId;
                }

                public void setYParentId(String yParentId) {
                    this.yParentId = yParentId;
                }

                public String getVName() {
                    return vName;
                }

                public void setVName(String vName) {
                    this.vName = vName;
                }

                public String getCreateDate() {
                    return createDate;
                }

                public void setCreateDate(String createDate) {
                    this.createDate = createDate;
                }

                public List<VListBean> getV_list() {
                    return v_list;
                }

                public void setV_list(List<VListBean> v_list) {
                    this.v_list = v_list;
                }

                public static class VListBean {
                    /**
                     * id : 1047
                     * yServiceId : 692341585785913368
                     * yParentId : 692341585785913362
                     * vName : 外洗30四级
                     * createDate : 2020-03-25 11:58:17
                     */
                    private boolean isgouxuan = false;

                    public boolean isIsgouxuan() {
                        return isgouxuan;
                    }

                    public void setIsgouxuan(boolean isgouxuan) {
                        this.isgouxuan = isgouxuan;
                    }
                    private String id;
                    private String yServiceId;
                    private String yParentId;
                    private String vName;
                    private String createDate;

                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }

                    public String getYServiceId() {
                        return yServiceId;
                    }

                    public void setYServiceId(String yServiceId) {
                        this.yServiceId = yServiceId;
                    }

                    public String getYParentId() {
                        return yParentId;
                    }

                    public void setYParentId(String yParentId) {
                        this.yParentId = yParentId;
                    }

                    public String getVName() {
                        return vName;
                    }

                    public void setVName(String vName) {
                        this.vName = vName;
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
    }
}
