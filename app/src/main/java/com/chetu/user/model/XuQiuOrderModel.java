package com.chetu.user.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2020/7/30.
 */
public class XuQiuOrderModel implements Serializable {
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * yStoreId : 692341585785913344
         * store_info : {"id":"1017","yStoreId":"692341585785913344","userId":"719539276219416576","kfUserId":"716658563904176128","vName":"龙肆商店测试","review":"4.5","keywsr":"技术 修车 实","address":"深圳市光明新区公明街道东环大道松白工业区C区2栋一层106号","longitude":"116.49798","latitude":"39.916485","picture":"/upload/2020-07-09/20200709110836_560635.jpg","phone":" 400-2333-1123","vLevel":"A级","introduce":"听广告几年了，终于体验了特色服务。轮胎是其专长。听说11年开始，线上业务。16年开始线下。现在全国1500多家店。","charactStr":"||/upload/2020-07-09/20200709110807_317759.jpg||/upload/2020-07-09/20200709110817_957557.jpg||/upload/2020-07-09/20200709110819_726210.jpg","pictureStr":"||/upload/2020-07-09/20200709110836_560635.jpg||/upload/2020-07-09/20200709110838_972720.jpg||/upload/2020-07-09/20200709110842_192873.jpg","slogan":"商店测试  洗车首单10","isIndex":0}
         * vPrice : 0.0
         * service_list : [{"id":"1353","yCartId":"738422422658613248","yStoreId":"692341585785913344","userId":"714547022807433216","isService":1,"yStoreServiceId":"692341585785913352","isIntegral":0,"gIntegral":0,"yGoodsId":"0","store_service_info":{"id":"1031","yStoreServiceId":"692341585785913352","yStoreId":"692341585785913344","yState":1,"lineupSum":10,"isSheet":1,"yStateValue":"在前门","sPrice":100,"pictureStr":"/upload/2020-06-02/20200602102222_621580.png","parentId":"692341585785913348"},"createDate":"2020-07-30 15:47:04","vPrice":100,"gNum":1,"goods_cart_list":[{"id":"1360","yCartId":"738440236404572160","yStoreId":"692341585785913344","userId":"714547022807433216","isService":2,"isInstall":1,"yStoreServiceId":"692341585785913352","isIntegral":0,"gIntegral":0,"yGoodsId":"692341585785913345","goodsValue":"黑色||xxl","goods_info":{"id":"1018","yGoodsId":"692341585785913345","yClassifyId":"692341585785913344","yStoreId":"692341585785913344","gName":"百魅 汽车遮阳挡6件套防晒隔热遮阳板挡阳板加厚前档太阳后挡侧挡","gDesc":"适用捷渡凌度360小米家盯盯拍70迈正际行车记录仪降压线停车监控","gPrice":1000,"orPrice":3000,"imgStr":"||/upload/2020-07-09/20200709100934_642027.png||/upload/2020-07-09/20200709100937_335866.png","gImg":"/upload/2020-07-09/20200709100934_642027.png","gState":1,"isPopular":1,"isIntegral":0,"gIntegral":0,"createDate":"2019-09-27 10:31:19","isSoffer":1},"createDate":"2020-07-30 16:57:51","vPrice":1180,"gNum":1}],"levelStr":"钣喷"}]
         */

        private String yStoreId;
        private StoreInfoBean store_info;
        private String vPrice;
        private List<ServiceListBean> service_list;

        public String getYStoreId() {
            return yStoreId;
        }

        public void setYStoreId(String yStoreId) {
            this.yStoreId = yStoreId;
        }

        public StoreInfoBean getStore_info() {
            return store_info;
        }

        public void setStore_info(StoreInfoBean store_info) {
            this.store_info = store_info;
        }

        public String getVPrice() {
            return vPrice;
        }

        public void setVPrice(String vPrice) {
            this.vPrice = vPrice;
        }

        public List<ServiceListBean> getService_list() {
            return service_list;
        }

        public void setService_list(List<ServiceListBean> service_list) {
            this.service_list = service_list;
        }

        public static class StoreInfoBean {
            /**
             * id : 1017
             * yStoreId : 692341585785913344
             * userId : 719539276219416576
             * kfUserId : 716658563904176128
             * vName : 龙肆商店测试
             * review : 4.5
             * keywsr : 技术 修车 实
             * address : 深圳市光明新区公明街道东环大道松白工业区C区2栋一层106号
             * longitude : 116.49798
             * latitude : 39.916485
             * picture : /upload/2020-07-09/20200709110836_560635.jpg
             * phone :  400-2333-1123
             * vLevel : A级
             * introduce : 听广告几年了，终于体验了特色服务。轮胎是其专长。听说11年开始，线上业务。16年开始线下。现在全国1500多家店。
             * charactStr : ||/upload/2020-07-09/20200709110807_317759.jpg||/upload/2020-07-09/20200709110817_957557.jpg||/upload/2020-07-09/20200709110819_726210.jpg
             * pictureStr : ||/upload/2020-07-09/20200709110836_560635.jpg||/upload/2020-07-09/20200709110838_972720.jpg||/upload/2020-07-09/20200709110842_192873.jpg
             * slogan : 商店测试  洗车首单10
             * isIndex : 0
             */

            private String id;
            private String yStoreId;
            private String userId;
            private String kfUserId;
            private String vName;
            private String review;
            private String keywsr;
            private String address;
            private String longitude;
            private String latitude;
            private String picture;
            private String phone;
            private String vLevel;
            private String introduce;
            private String charactStr;
            private String pictureStr;
            private String slogan;
            private int isIndex;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getYStoreId() {
                return yStoreId;
            }

            public void setYStoreId(String yStoreId) {
                this.yStoreId = yStoreId;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getKfUserId() {
                return kfUserId;
            }

            public void setKfUserId(String kfUserId) {
                this.kfUserId = kfUserId;
            }

            public String getVName() {
                return vName;
            }

            public void setVName(String vName) {
                this.vName = vName;
            }

            public String getReview() {
                return review;
            }

            public void setReview(String review) {
                this.review = review;
            }

            public String getKeywsr() {
                return keywsr;
            }

            public void setKeywsr(String keywsr) {
                this.keywsr = keywsr;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getLongitude() {
                return longitude;
            }

            public void setLongitude(String longitude) {
                this.longitude = longitude;
            }

            public String getLatitude() {
                return latitude;
            }

            public void setLatitude(String latitude) {
                this.latitude = latitude;
            }

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getVLevel() {
                return vLevel;
            }

            public void setVLevel(String vLevel) {
                this.vLevel = vLevel;
            }

            public String getIntroduce() {
                return introduce;
            }

            public void setIntroduce(String introduce) {
                this.introduce = introduce;
            }

            public String getCharactStr() {
                return charactStr;
            }

            public void setCharactStr(String charactStr) {
                this.charactStr = charactStr;
            }

            public String getPictureStr() {
                return pictureStr;
            }

            public void setPictureStr(String pictureStr) {
                this.pictureStr = pictureStr;
            }

            public String getSlogan() {
                return slogan;
            }

            public void setSlogan(String slogan) {
                this.slogan = slogan;
            }

            public int getIsIndex() {
                return isIndex;
            }

            public void setIsIndex(int isIndex) {
                this.isIndex = isIndex;
            }
        }

        public static class ServiceListBean {
            /**
             * id : 1353
             * yCartId : 738422422658613248
             * yStoreId : 692341585785913344
             * userId : 714547022807433216
             * isService : 1
             * yStoreServiceId : 692341585785913352
             * isIntegral : 0
             * gIntegral : 0
             * yGoodsId : 0
             * store_service_info : {"id":"1031","yStoreServiceId":"692341585785913352","yStoreId":"692341585785913344","yState":1,"lineupSum":10,"isSheet":1,"yStateValue":"在前门","sPrice":100,"pictureStr":"/upload/2020-06-02/20200602102222_621580.png","parentId":"692341585785913348"}
             * createDate : 2020-07-30 15:47:04
             * vPrice : 100.0
             * gNum : 1
             * goods_cart_list : [{"id":"1360","yCartId":"738440236404572160","yStoreId":"692341585785913344","userId":"714547022807433216","isService":2,"isInstall":1,"yStoreServiceId":"692341585785913352","isIntegral":0,"gIntegral":0,"yGoodsId":"692341585785913345","goodsValue":"黑色||xxl","goods_info":{"id":"1018","yGoodsId":"692341585785913345","yClassifyId":"692341585785913344","yStoreId":"692341585785913344","gName":"百魅 汽车遮阳挡6件套防晒隔热遮阳板挡阳板加厚前档太阳后挡侧挡","gDesc":"适用捷渡凌度360小米家盯盯拍70迈正际行车记录仪降压线停车监控","gPrice":1000,"orPrice":3000,"imgStr":"||/upload/2020-07-09/20200709100934_642027.png||/upload/2020-07-09/20200709100937_335866.png","gImg":"/upload/2020-07-09/20200709100934_642027.png","gState":1,"isPopular":1,"isIntegral":0,"gIntegral":0,"createDate":"2019-09-27 10:31:19","isSoffer":1},"createDate":"2020-07-30 16:57:51","vPrice":1180,"gNum":1}]
             * levelStr : 钣喷
             */

            private String id;
            private String yCartId;
            private String yStoreId;
            private String userId;
            private int isService;
            private String yStoreServiceId;
            private int isIntegral;
            private int gIntegral;
            private String yGoodsId;
            private StoreServiceInfoBean store_service_info;
            private String createDate;
            private String vPrice;
            private int gNum;
            private String levelStr;
            private List<GoodsCartListBean> goods_cart_list;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getYCartId() {
                return yCartId;
            }

            public void setYCartId(String yCartId) {
                this.yCartId = yCartId;
            }

            public String getYStoreId() {
                return yStoreId;
            }

            public void setYStoreId(String yStoreId) {
                this.yStoreId = yStoreId;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public int getIsService() {
                return isService;
            }

            public void setIsService(int isService) {
                this.isService = isService;
            }

            public String getYStoreServiceId() {
                return yStoreServiceId;
            }

            public void setYStoreServiceId(String yStoreServiceId) {
                this.yStoreServiceId = yStoreServiceId;
            }

            public int getIsIntegral() {
                return isIntegral;
            }

            public void setIsIntegral(int isIntegral) {
                this.isIntegral = isIntegral;
            }

            public int getGIntegral() {
                return gIntegral;
            }

            public void setGIntegral(int gIntegral) {
                this.gIntegral = gIntegral;
            }

            public String getYGoodsId() {
                return yGoodsId;
            }

            public void setYGoodsId(String yGoodsId) {
                this.yGoodsId = yGoodsId;
            }

            public StoreServiceInfoBean getStore_service_info() {
                return store_service_info;
            }

            public void setStore_service_info(StoreServiceInfoBean store_service_info) {
                this.store_service_info = store_service_info;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public String getVPrice() {
                return vPrice;
            }

            public void setVPrice(String vPrice) {
                this.vPrice = vPrice;
            }

            public int getGNum() {
                return gNum;
            }

            public void setGNum(int gNum) {
                this.gNum = gNum;
            }

            public String getLevelStr() {
                return levelStr;
            }

            public void setLevelStr(String levelStr) {
                this.levelStr = levelStr;
            }

            public List<GoodsCartListBean> getGoods_cart_list() {
                return goods_cart_list;
            }

            public void setGoods_cart_list(List<GoodsCartListBean> goods_cart_list) {
                this.goods_cart_list = goods_cart_list;
            }

            public static class StoreServiceInfoBean {
                /**
                 * id : 1031
                 * yStoreServiceId : 692341585785913352
                 * yStoreId : 692341585785913344
                 * yState : 1
                 * lineupSum : 10
                 * isSheet : 1
                 * yStateValue : 在前门
                 * sPrice : 100.0
                 * pictureStr : /upload/2020-06-02/20200602102222_621580.png
                 * parentId : 692341585785913348
                 */

                private String id;
                private String yStoreServiceId;
                private String yStoreId;
                private int yState;
                private int lineupSum;
                private int isSheet;
                private String yStateValue;
                private String sPrice;
                private String pictureStr;
                private String parentId;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getYStoreServiceId() {
                    return yStoreServiceId;
                }

                public void setYStoreServiceId(String yStoreServiceId) {
                    this.yStoreServiceId = yStoreServiceId;
                }

                public String getYStoreId() {
                    return yStoreId;
                }

                public void setYStoreId(String yStoreId) {
                    this.yStoreId = yStoreId;
                }

                public int getYState() {
                    return yState;
                }

                public void setYState(int yState) {
                    this.yState = yState;
                }

                public int getLineupSum() {
                    return lineupSum;
                }

                public void setLineupSum(int lineupSum) {
                    this.lineupSum = lineupSum;
                }

                public int getIsSheet() {
                    return isSheet;
                }

                public void setIsSheet(int isSheet) {
                    this.isSheet = isSheet;
                }

                public String getYStateValue() {
                    return yStateValue;
                }

                public void setYStateValue(String yStateValue) {
                    this.yStateValue = yStateValue;
                }

                public String getSPrice() {
                    return sPrice;
                }

                public void setSPrice(String sPrice) {
                    this.sPrice = sPrice;
                }

                public String getPictureStr() {
                    return pictureStr;
                }

                public void setPictureStr(String pictureStr) {
                    this.pictureStr = pictureStr;
                }

                public String getParentId() {
                    return parentId;
                }

                public void setParentId(String parentId) {
                    this.parentId = parentId;
                }
            }

            public static class GoodsCartListBean {
                /**
                 * id : 1360
                 * yCartId : 738440236404572160
                 * yStoreId : 692341585785913344
                 * userId : 714547022807433216
                 * isService : 2
                 * isInstall : 1
                 * yStoreServiceId : 692341585785913352
                 * isIntegral : 0
                 * gIntegral : 0
                 * yGoodsId : 692341585785913345
                 * goodsValue : 黑色||xxl
                 * goods_info : {"id":"1018","yGoodsId":"692341585785913345","yClassifyId":"692341585785913344","yStoreId":"692341585785913344","gName":"百魅 汽车遮阳挡6件套防晒隔热遮阳板挡阳板加厚前档太阳后挡侧挡","gDesc":"适用捷渡凌度360小米家盯盯拍70迈正际行车记录仪降压线停车监控","gPrice":1000,"orPrice":3000,"imgStr":"||/upload/2020-07-09/20200709100934_642027.png||/upload/2020-07-09/20200709100937_335866.png","gImg":"/upload/2020-07-09/20200709100934_642027.png","gState":1,"isPopular":1,"isIntegral":0,"gIntegral":0,"createDate":"2019-09-27 10:31:19","isSoffer":1}
                 * createDate : 2020-07-30 16:57:51
                 * vPrice : 1180.0
                 * gNum : 1
                 */

                private String id;
                private String yCartId;
                private String yStoreId;
                private String userId;
                private int isService;
                private int isInstall;
                private String yStoreServiceId;
                private int isIntegral;
                private int gIntegral;
                private String yGoodsId;
                private String goodsValue;
                private GoodsInfoBean goods_info;
                private String createDate;
                private String vPrice;
                private int gNum;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getYCartId() {
                    return yCartId;
                }

                public void setYCartId(String yCartId) {
                    this.yCartId = yCartId;
                }

                public String getYStoreId() {
                    return yStoreId;
                }

                public void setYStoreId(String yStoreId) {
                    this.yStoreId = yStoreId;
                }

                public String getUserId() {
                    return userId;
                }

                public void setUserId(String userId) {
                    this.userId = userId;
                }

                public int getIsService() {
                    return isService;
                }

                public void setIsService(int isService) {
                    this.isService = isService;
                }

                public int getIsInstall() {
                    return isInstall;
                }

                public void setIsInstall(int isInstall) {
                    this.isInstall = isInstall;
                }

                public String getYStoreServiceId() {
                    return yStoreServiceId;
                }

                public void setYStoreServiceId(String yStoreServiceId) {
                    this.yStoreServiceId = yStoreServiceId;
                }

                public int getIsIntegral() {
                    return isIntegral;
                }

                public void setIsIntegral(int isIntegral) {
                    this.isIntegral = isIntegral;
                }

                public int getGIntegral() {
                    return gIntegral;
                }

                public void setGIntegral(int gIntegral) {
                    this.gIntegral = gIntegral;
                }

                public String getYGoodsId() {
                    return yGoodsId;
                }

                public void setYGoodsId(String yGoodsId) {
                    this.yGoodsId = yGoodsId;
                }

                public String getGoodsValue() {
                    return goodsValue;
                }

                public void setGoodsValue(String goodsValue) {
                    this.goodsValue = goodsValue;
                }

                public GoodsInfoBean getGoods_info() {
                    return goods_info;
                }

                public void setGoods_info(GoodsInfoBean goods_info) {
                    this.goods_info = goods_info;
                }

                public String getCreateDate() {
                    return createDate;
                }

                public void setCreateDate(String createDate) {
                    this.createDate = createDate;
                }

                public String getVPrice() {
                    return vPrice;
                }

                public void setVPrice(String vPrice) {
                    this.vPrice = vPrice;
                }

                public int getGNum() {
                    return gNum;
                }

                public void setGNum(int gNum) {
                    this.gNum = gNum;
                }

                public static class GoodsInfoBean {
                    /**
                     * id : 1018
                     * yGoodsId : 692341585785913345
                     * yClassifyId : 692341585785913344
                     * yStoreId : 692341585785913344
                     * gName : 百魅 汽车遮阳挡6件套防晒隔热遮阳板挡阳板加厚前档太阳后挡侧挡
                     * gDesc : 适用捷渡凌度360小米家盯盯拍70迈正际行车记录仪降压线停车监控
                     * gPrice : 1000.0
                     * orPrice : 3000.0
                     * imgStr : ||/upload/2020-07-09/20200709100934_642027.png||/upload/2020-07-09/20200709100937_335866.png
                     * gImg : /upload/2020-07-09/20200709100934_642027.png
                     * gState : 1
                     * isPopular : 1
                     * isIntegral : 0
                     * gIntegral : 0
                     * createDate : 2019-09-27 10:31:19
                     * isSoffer : 1
                     */

                    private String id;
                    private String yGoodsId;
                    private String yClassifyId;
                    private String yStoreId;
                    private String gName;
                    private String gDesc;
                    private String gPrice;
                    private String orPrice;
                    private String imgStr;
                    private String gImg;
                    private int gState;
                    private int isPopular;
                    private int isIntegral;
                    private int gIntegral;
                    private String createDate;
                    private int isSoffer;

                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }

                    public String getYGoodsId() {
                        return yGoodsId;
                    }

                    public void setYGoodsId(String yGoodsId) {
                        this.yGoodsId = yGoodsId;
                    }

                    public String getYClassifyId() {
                        return yClassifyId;
                    }

                    public void setYClassifyId(String yClassifyId) {
                        this.yClassifyId = yClassifyId;
                    }

                    public String getYStoreId() {
                        return yStoreId;
                    }

                    public void setYStoreId(String yStoreId) {
                        this.yStoreId = yStoreId;
                    }

                    public String getGName() {
                        return gName;
                    }

                    public void setGName(String gName) {
                        this.gName = gName;
                    }

                    public String getGDesc() {
                        return gDesc;
                    }

                    public void setGDesc(String gDesc) {
                        this.gDesc = gDesc;
                    }

                    public String getGPrice() {
                        return gPrice;
                    }

                    public void setGPrice(String gPrice) {
                        this.gPrice = gPrice;
                    }

                    public String getOrPrice() {
                        return orPrice;
                    }

                    public void setOrPrice(String orPrice) {
                        this.orPrice = orPrice;
                    }

                    public String getImgStr() {
                        return imgStr;
                    }

                    public void setImgStr(String imgStr) {
                        this.imgStr = imgStr;
                    }

                    public String getGImg() {
                        return gImg;
                    }

                    public void setGImg(String gImg) {
                        this.gImg = gImg;
                    }

                    public int getGState() {
                        return gState;
                    }

                    public void setGState(int gState) {
                        this.gState = gState;
                    }

                    public int getIsPopular() {
                        return isPopular;
                    }

                    public void setIsPopular(int isPopular) {
                        this.isPopular = isPopular;
                    }

                    public int getIsIntegral() {
                        return isIntegral;
                    }

                    public void setIsIntegral(int isIntegral) {
                        this.isIntegral = isIntegral;
                    }

                    public int getGIntegral() {
                        return gIntegral;
                    }

                    public void setGIntegral(int gIntegral) {
                        this.gIntegral = gIntegral;
                    }

                    public String getCreateDate() {
                        return createDate;
                    }

                    public void setCreateDate(String createDate) {
                        this.createDate = createDate;
                    }

                    public int getIsSoffer() {
                        return isSoffer;
                    }

                    public void setIsSoffer(int isSoffer) {
                        this.isSoffer = isSoffer;
                    }
                }
            }
        }
    }
}
