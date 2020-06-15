package com.chetu.user.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2020/6/10.
 */
public class ProductDetailModel implements Serializable {
    /**
     * collection_info : {"id":"19","yUserCollectionId":"721422164145733632","userId":"714547022807433216","yId":"692341585785913346","category":1,"createDate":"2020-06-13 17:54:07"}
     * specific_list : [{"id":"1017","yGoodsSpecificTypeId":"692341585785913344","yGoodsId":"692341585785913346","sName":"颜色","specific_List":[{"yGoodsSpecificId":"692341585785913344","yGoodsSpecificTypeId":"692341585785913344","yGoodsId":"692341585785913346","pName":"红色","sPrice":30},{"yGoodsSpecificId":"692341585785913345","yGoodsSpecificTypeId":"692341585785913344","yGoodsId":"692341585785913346","pName":"黑色","sPrice":20},{"yGoodsSpecificId":"692341585785913346","yGoodsSpecificTypeId":"692341585785913344","yGoodsId":"692341585785913346","pName":"绿色","sPrice":50}]},{"id":"1018","yGoodsSpecificTypeId":"692341585785913345","yGoodsId":"692341585785913346","sName":"尺寸","specific_List":[{"yGoodsSpecificId":"692341585785913347","yGoodsSpecificTypeId":"692341585785913345","yGoodsId":"692341585785913346","pName":"xl","sPrice":70},{"yGoodsSpecificId":"692341585785913348","yGoodsSpecificTypeId":"692341585785913345","yGoodsId":"692341585785913346","pName":"ml","sPrice":80},{"yGoodsSpecificId":"692341585785913349","yGoodsSpecificTypeId":"692341585785913345","yGoodsId":"692341585785913346","pName":"xxl","sPrice":90}]}]
     * info : {"id":"1019","yGoodsId":"692341585785913346","yClassifyId":"692341585785913344","yStoreId":"692341585785913344","gName":"汽车行车记录仪降压线通用小米70迈360专用停车监控车载usb电源线","gDesc":"现代名图后备箱垫全包围专用防水定制装饰垫现代名图汽车尾箱垫子","gPrice":1000,"orPrice":0,"gDetails":"<div id=\"attributes\" class=\"attributes\" style=\"margin:0px;padding:0px;color:#404040;font-family:tahoma, arial, 微软雅黑, sans-serif;background-color:#FFFFFF;\">\r\n\t<div class=\"attributes-list\" id=\"J_AttrList\" style=\"margin:0px 0px 10px;padding:0px;\">\r\n\t\t<ul id=\"J_AttrUL\">\r\n\t\t\t<li style=\"vertical-align:top;color:#666666;\">\r\n\t\t\t\t附加功能:&nbsp;录音功能\r\n\t\t\t<\/li>\r\n\t\t\t<li style=\"vertical-align:top;color:#666666;\">\r\n\t\t\t\t适用对象:&nbsp;汽车跟踪器\r\n\t\t\t<\/li>\r\n\t\t\t<li style=\"vertical-align:top;color:#666666;\">\r\n\t\t\t\t上网/语音套餐:&nbsp;无\r\n\t\t\t<\/li>\r\n\t\t<\/ul>\r\n\t<\/div>\r\n<\/div>\r\n<div id=\"mall-banner\" style=\"margin:0px;padding:0px;color:#404040;font-family:tahoma, arial, 微软雅黑, sans-serif;background-color:#FFFFFF;\">\r\n\t<div style=\"margin:0px;padding:0px;\">\r\n\t<\/div>\r\n\t<div id=\"J_DescTMS1\" style=\"margin:0px;padding:0px;\">\r\n\t<\/div>\r\n<\/div>\r\n<div id=\"J_TmpActBanner\" style=\"margin:0px;padding:0px;color:#404040;font-family:tahoma, arial, 微软雅黑, sans-serif;background-color:#FFFFFF;\">\r\n<\/div>\r\n<div id=\"J_DcTopRightWrap\" style=\"margin:0px;padding:0px;color:#404040;font-family:tahoma, arial, 微软雅黑, sans-serif;background-color:#FFFFFF;\">\r\n<\/div>\r\n<div id=\"description\" class=\"J_DetailSection tshop-psm tshop-psm-bdetaildes\" style=\"margin:0px;padding:0px;color:#404040;font-family:tahoma, arial, 微软雅黑, sans-serif;background-color:#FFFFFF;\">\r\n\t<div class=\"content ke-post\" style=\"margin:10px 0px 0px;padding:0px;font-size:14px;font-family:tahoma, arial, 宋体, sans-serif;\">\r\n\t\t<img class=\"desc_anchor img-ks-lazyload\" id=\"desc-module-1\" src=\"https://assets.alicdn.com/kissy/1.0.0/build/imglazyload/spaceball.gif\" style=\"height:1px;\" />\r\n\t\t<p>\r\n\t\t\t<img src=\"https://img.alicdn.com/imgextra/i4/2578756826/O1CN019tixUe20IM9MDrXRE_!!2578756826.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\" /><img src=\"https://img.alicdn.com/imgextra/i4/2578756826/O1CN01R1LDRY20IM9JrZO9k_!!2578756826.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\" /><img src=\"https://img.alicdn.com/imgextra/i2/2578756826/O1CN01YIcFZ420IM9KhOokH_!!2578756826.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\" /><img src=\"https://img.alicdn.com/imgextra/i3/2578756826/O1CN01Tm7wgT20IM9APjsnP_!!2578756826.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\" /><img src=\"https://img.alicdn.com/imgextra/i4/2578756826/O1CN01r2FG1p20IM9IJT4Ir_!!2578756826.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\" /><img src=\"https://img.alicdn.com/imgextra/i1/2578756826/O1CN01aUaXUu20IM95idLDt_!!2578756826.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\" /><img src=\"https://img.alicdn.com/imgextra/i1/2578756826/O1CN01bQ6bw220IM9Ft8Nhl_!!2578756826.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\" /><img src=\"https://img.alicdn.com/imgextra/i1/2578756826/O1CN01JYHuNC20IM9Ft6AYa_!!2578756826.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\" /><img src=\"https://img.alicdn.com/imgextra/i3/2578756826/O1CN01cqwWYC20IM9DKfiUb_!!2578756826.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\" /><img src=\"https://img.alicdn.com/imgextra/i3/2578756826/O1CN01vHE3t320IM9FHVavS_!!2578756826.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\" /><img src=\"https://img.alicdn.com/imgextra/i1/2578756826/O1CN01Q6fkCN20IM9GxGQBM_!!2578756826.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\" /><img src=\"https://img.alicdn.com/imgextra/i2/2578756826/O1CN01sK4Mzn20IM7Wfh5SS_!!2578756826.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\" /><img src=\"https://img.alicdn.com/imgextra/i2/2578756826/O1CN01UeX5q020IM7Wfi1iX_!!2578756826.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\" /><img src=\"https://img.alicdn.com/imgextra/i2/2578756826/O1CN01CICQDu20IM7SGcy8b_!!2578756826.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\" /><img src=\"https://img.alicdn.com/imgextra/i1/2578756826/O1CN01JsICgY20IM7abIHvY_!!2578756826.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\" /><img src=\"https://img.alicdn.com/imgextra/i4/2578756826/O1CN019SqnU820IM7TYSSrk_!!2578756826.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\" /><img src=\"https://img.alicdn.com/imgextra/i4/2578756826/O1CN01A6nVYD20IM7SmaeRP_!!2578756826.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\" /><img src=\"https://img.alicdn.com/imgextra/i2/2578756826/O1CN01J9e0Rk20IM7UCm0op_!!2578756826.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\" /><img src=\"https://img.alicdn.com/imgextra/i1/2578756826/O1CN01Ajmb9v20IM7SmRz6a_!!2578756826.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\" /><img src=\"https://img.alicdn.com/imgextra/i1/2578756826/O1CN018Cqfan20IM7VakNPm_!!2578756826.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\" /><img src=\"https://img.alicdn.com/imgextra/i4/2578756826/O1CN01FsFCuI20IM7QaskF1_!!2578756826.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\" /><img src=\"https://img.alicdn.com/imgextra/i4/2578756826/O1CN01QZj3VP20IM7QasLHX_!!2578756826.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\" /><img src=\"https://img.alicdn.com/imgextra/i1/2578756826/O1CN01fqudc020IM7Wpmzxb_!!2578756826.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\" /><img src=\"https://img.alicdn.com/imgextra/i4/2578756826/O1CN01rbzD9o20IM7WZ7BpO_!!2578756826.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\" /><img src=\"https://img.alicdn.com/imgextra/i3/2578756826/O1CN01d4Xxf020IM7QaqrpU_!!2578756826.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\" /><img src=\"https://img.alicdn.com/imgextra/i2/2578756826/O1CN01RjXy6G20IM7SmSO1y_!!2578756826.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\" /><img src=\"https://img.alicdn.com/imgextra/i4/2578756826/O1CN01DvIzDx20IM7SmcnTq_!!2578756826.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\" /><img src=\"https://img.alicdn.com/imgextra/i2/2578756826/O1CN01EyVd8j20IM7SmRJYR_!!2578756826.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\" /><img src=\"https://img.alicdn.com/imgextra/i2/2578756826/O1CN01ayzKJp20IM7QatTxe_!!2578756826.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\" /><img src=\"https://img.alicdn.com/imgextra/i2/2578756826/O1CN019CnNbX20IM4jOseCt_!!2578756826.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\" /><img src=\"https://img.alicdn.com/imgextra/i2/2578756826/O1CN01HH0sfi20IM8cf4rTb_!!2578756826.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\" /><img src=\"https://img.alicdn.com/imgextra/i4/2578756826/O1CN01XZsjcH20IM3Z0UWxg_!!2578756826.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\" /><img src=\"https://img.alicdn.com/imgextra/i1/2578756826/O1CN01zgNmd320IM6dhu365_!!2578756826.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\" /><img src=\"https://img.alicdn.com/imgextra/i2/2578756826/O1CN012eJaP520IM6ey3LoX_!!2578756826.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\" /><img src=\"https://img.alicdn.com/imgextra/i2/2578756826/O1CN01D8oVZF20IM6gv2YZT_!!2578756826.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\" /><img src=\"https://img.alicdn.com/imgextra/i1/2578756826/O1CN01RIMI4y20IM7RAdpWn_!!2578756826.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\" /><img src=\"https://img.alicdn.com/imgextra/i3/2578756826/O1CN01Oi4ALZ20IM3Z8bIxv_!!2578756826.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\" />\r\n\t\t<\/p>\r\n\t<\/div>\r\n<\/div>\r\n<div id=\"J_DcBottomRightWrap\" style=\"margin:0px;padding:0px;color:#404040;font-family:tahoma, arial, 微软雅黑, sans-serif;background-color:#FFFFFF;\">\r\n\t<div id=\"J_ZebraPriceDesc\" class=\"j-mdv\" style=\"margin:0px;padding:0px;\">\r\n\t\t<img width=\"790\" src=\"https://img.alicdn.com/tfs/TB1.CUdsY9YBuNjy0FgXXcxcXXa-1572-394.png\" />\r\n\t<\/div>\r\n<\/div>\r\n<div id=\"J_Detail\" style=\"margin:0px;padding:0px;color:#404040;font-family:tahoma, arial, 微软雅黑, sans-serif;background-color:#FFFFFF;\">\r\n\t<div id=\"J_Reviews\" class=\"J_DetailSection\" style=\"margin:0px;padding:0px;\">\r\n\t\t<h4 class=\"hd\" style=\"background-color:#FF0036;color:#FFFFFF;\">\r\n\t\t\t累计评价&nbsp;<span class=\"J_ReviewsCount\">120844<\/span>\r\n\t\t<\/h4>\r\n\t\t<div class=\"tm-rate\" style=\"margin:0px;padding:0px;\">\r\n\t\t\t<div class=\"rate-header rate-header-tags\" style=\"margin:0px;padding:10px 0px;border:1px solid #C8C8C8;\">\r\n\t\t\t\t<div class=\"rate-score\" style=\"margin:0px;padding:0px 20px;vertical-align:top;text-align:center;\">\r\n\t\t\t\t\t<h4 style=\"font-weight:100;\">\r\n\t\t\t\t\t\t与描述相符\r\n\t\t\t\t\t<\/h4>\r\n<strong>4.7<\/strong>\r\n\t\t\t\t\t<p style=\"text-align:left;\">\r\n\t\t\t\t\t\t<span class=\"score-value-no score-value-4d7\" style=\"background:url(&quot;\"><span style=\"background:url(&quot;\"><\/span><\/span>\r\n\t\t\t\t\t<\/p>\r\n\t\t\t\t<\/div>\r\n\t\t\t\t<div class=\"rate-tag-box\" style=\"margin:0px;padding:0px;vertical-align:top;\">\r\n\t\t\t\t\t<div class=\"rate-tag-label\" style=\"margin:4px 0px 0px;padding:0px 12px 0px 25px;vertical-align:top;color:#CCCCCC;font-size:11px;background:url(&quot;\">\r\n\t\t\t\t\t\t<span>大家都写到<\/span>\r\n\t\t\t\t\t<\/div>\r\n\t\t\t\t\t<div class=\"rate-tag-list\" style=\"margin:0px;padding:0px;vertical-align:top;\">\r\n\t\t\t\t\t\t<div class=\"rate-tag-inner\" style=\"margin:0px;padding:0px;\">\r\n\t\t\t\t\t\t\t<span class=\"tag-posi\"><a id=\"ratetag618_731a\" href=\"https://detail.tmall.com/item.htm?id=573870605610&amp;ali_refid=a3_430673_1006:1122738397:N:cyaFL23emIrsmcdRMUfn1Q==:31a3ed4503c69dc8194c66567469c253&amp;ali_trackid=1_31a3ed4503c69dc8194c66567469c253&amp;spm=a2e15.8261149.07626516002.2&amp;sku_properties=211022001:10010#\">功能正面(4611)<s><\/s><\/a><\/span><span class=\"tag-posi\"><a id=\"ratetag618_420a\" href=\"https://detail.tmall.com/item.htm?id=573870605610&amp;ali_refid=a3_430673_1006:1122738397:N:cyaFL23emIrsmcdRMUfn1Q==:31a3ed4503c69dc8194c66567469c253&amp;ali_trackid=1_31a3ed4503c69dc8194c66567469c253&amp;spm=a2e15.8261149.07626516002.2&amp;sku_properties=211022001:10010#\">物流快(1843)<\/a><\/span>\r\n\t\t\t\t\t\t<\/div>\r\n\t\t\t\t\t<\/div>\r\n\t\t\t\t<\/div>\r\n\t\t\t<\/div>\r\n\t\t<\/div>\r\n\t<\/div>\r\n<\/div>","imgStr":"/upload/pro.png||/upload/pro.png","imgArr":["/upload/pro.png","/upload/pro.png"],"gImg":"/upload/pro.png","gState":1,"createDate":"2019-09-27 10:31:19"}
     */

    private CollectionInfoBean collection_info;
    private InfoBean info;
    private List<SpecificListBeanX> specific_list;

    public CollectionInfoBean getCollection_info() {
        return collection_info;
    }

    public void setCollection_info(CollectionInfoBean collection_info) {
        this.collection_info = collection_info;
    }

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public List<SpecificListBeanX> getSpecific_list() {
        return specific_list;
    }

    public void setSpecific_list(List<SpecificListBeanX> specific_list) {
        this.specific_list = specific_list;
    }

    public static class CollectionInfoBean {
        /**
         * id : 19
         * yUserCollectionId : 721422164145733632
         * userId : 714547022807433216
         * yId : 692341585785913346
         * category : 1
         * createDate : 2020-06-13 17:54:07
         */

        private String id;
        private String yUserCollectionId;
        private String userId;
        private String yId;
        private int category;
        private String createDate;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYUserCollectionId() {
            return yUserCollectionId;
        }

        public void setYUserCollectionId(String yUserCollectionId) {
            this.yUserCollectionId = yUserCollectionId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getYId() {
            return yId;
        }

        public void setYId(String yId) {
            this.yId = yId;
        }

        public int getCategory() {
            return category;
        }

        public void setCategory(int category) {
            this.category = category;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }
    }

    public static class InfoBean {
        /**
         * id : 1019
         * yGoodsId : 692341585785913346
         * yClassifyId : 692341585785913344
         * yStoreId : 692341585785913344
         * gName : 汽车行车记录仪降压线通用小米70迈360专用停车监控车载usb电源线
         * gDesc : 现代名图后备箱垫全包围专用防水定制装饰垫现代名图汽车尾箱垫子
         * gPrice : 1000.0
         * orPrice : 0.0
         * gDetails : <div id="attributes" class="attributes" style="margin:0px;padding:0px;color:#404040;font-family:tahoma, arial, 微软雅黑, sans-serif;background-color:#FFFFFF;">
         * <div class="attributes-list" id="J_AttrList" style="margin:0px 0px 10px;padding:0px;">
         * <ul id="J_AttrUL">
         * <li style="vertical-align:top;color:#666666;">
         * 附加功能:&nbsp;录音功能
         * </li>
         * <li style="vertical-align:top;color:#666666;">
         * 适用对象:&nbsp;汽车跟踪器
         * </li>
         * <li style="vertical-align:top;color:#666666;">
         * 上网/语音套餐:&nbsp;无
         * </li>
         * </ul>
         * </div>
         * </div>
         * <div id="mall-banner" style="margin:0px;padding:0px;color:#404040;font-family:tahoma, arial, 微软雅黑, sans-serif;background-color:#FFFFFF;">
         * <div style="margin:0px;padding:0px;">
         * </div>
         * <div id="J_DescTMS1" style="margin:0px;padding:0px;">
         * </div>
         * </div>
         * <div id="J_TmpActBanner" style="margin:0px;padding:0px;color:#404040;font-family:tahoma, arial, 微软雅黑, sans-serif;background-color:#FFFFFF;">
         * </div>
         * <div id="J_DcTopRightWrap" style="margin:0px;padding:0px;color:#404040;font-family:tahoma, arial, 微软雅黑, sans-serif;background-color:#FFFFFF;">
         * </div>
         * <div id="description" class="J_DetailSection tshop-psm tshop-psm-bdetaildes" style="margin:0px;padding:0px;color:#404040;font-family:tahoma, arial, 微软雅黑, sans-serif;background-color:#FFFFFF;">
         * <div class="content ke-post" style="margin:10px 0px 0px;padding:0px;font-size:14px;font-family:tahoma, arial, 宋体, sans-serif;">
         * <img class="desc_anchor img-ks-lazyload" id="desc-module-1" src="https://assets.alicdn.com/kissy/1.0.0/build/imglazyload/spaceball.gif" style="height:1px;" />
         * <p>
         * <img src="https://img.alicdn.com/imgextra/i4/2578756826/O1CN019tixUe20IM9MDrXRE_!!2578756826.jpg" align="absmiddle" class="img-ks-lazyload" /><img src="https://img.alicdn.com/imgextra/i4/2578756826/O1CN01R1LDRY20IM9JrZO9k_!!2578756826.jpg" align="absmiddle" class="img-ks-lazyload" /><img src="https://img.alicdn.com/imgextra/i2/2578756826/O1CN01YIcFZ420IM9KhOokH_!!2578756826.jpg" align="absmiddle" class="img-ks-lazyload" /><img src="https://img.alicdn.com/imgextra/i3/2578756826/O1CN01Tm7wgT20IM9APjsnP_!!2578756826.jpg" align="absmiddle" class="img-ks-lazyload" /><img src="https://img.alicdn.com/imgextra/i4/2578756826/O1CN01r2FG1p20IM9IJT4Ir_!!2578756826.jpg" align="absmiddle" class="img-ks-lazyload" /><img src="https://img.alicdn.com/imgextra/i1/2578756826/O1CN01aUaXUu20IM95idLDt_!!2578756826.jpg" align="absmiddle" class="img-ks-lazyload" /><img src="https://img.alicdn.com/imgextra/i1/2578756826/O1CN01bQ6bw220IM9Ft8Nhl_!!2578756826.jpg" align="absmiddle" class="img-ks-lazyload" /><img src="https://img.alicdn.com/imgextra/i1/2578756826/O1CN01JYHuNC20IM9Ft6AYa_!!2578756826.jpg" align="absmiddle" class="img-ks-lazyload" /><img src="https://img.alicdn.com/imgextra/i3/2578756826/O1CN01cqwWYC20IM9DKfiUb_!!2578756826.jpg" align="absmiddle" class="img-ks-lazyload" /><img src="https://img.alicdn.com/imgextra/i3/2578756826/O1CN01vHE3t320IM9FHVavS_!!2578756826.jpg" align="absmiddle" class="img-ks-lazyload" /><img src="https://img.alicdn.com/imgextra/i1/2578756826/O1CN01Q6fkCN20IM9GxGQBM_!!2578756826.jpg" align="absmiddle" class="img-ks-lazyload" /><img src="https://img.alicdn.com/imgextra/i2/2578756826/O1CN01sK4Mzn20IM7Wfh5SS_!!2578756826.jpg" align="absmiddle" class="img-ks-lazyload" /><img src="https://img.alicdn.com/imgextra/i2/2578756826/O1CN01UeX5q020IM7Wfi1iX_!!2578756826.jpg" align="absmiddle" class="img-ks-lazyload" /><img src="https://img.alicdn.com/imgextra/i2/2578756826/O1CN01CICQDu20IM7SGcy8b_!!2578756826.jpg" align="absmiddle" class="img-ks-lazyload" /><img src="https://img.alicdn.com/imgextra/i1/2578756826/O1CN01JsICgY20IM7abIHvY_!!2578756826.jpg" align="absmiddle" class="img-ks-lazyload" /><img src="https://img.alicdn.com/imgextra/i4/2578756826/O1CN019SqnU820IM7TYSSrk_!!2578756826.jpg" align="absmiddle" class="img-ks-lazyload" /><img src="https://img.alicdn.com/imgextra/i4/2578756826/O1CN01A6nVYD20IM7SmaeRP_!!2578756826.jpg" align="absmiddle" class="img-ks-lazyload" /><img src="https://img.alicdn.com/imgextra/i2/2578756826/O1CN01J9e0Rk20IM7UCm0op_!!2578756826.jpg" align="absmiddle" class="img-ks-lazyload" /><img src="https://img.alicdn.com/imgextra/i1/2578756826/O1CN01Ajmb9v20IM7SmRz6a_!!2578756826.jpg" align="absmiddle" class="img-ks-lazyload" /><img src="https://img.alicdn.com/imgextra/i1/2578756826/O1CN018Cqfan20IM7VakNPm_!!2578756826.jpg" align="absmiddle" class="img-ks-lazyload" /><img src="https://img.alicdn.com/imgextra/i4/2578756826/O1CN01FsFCuI20IM7QaskF1_!!2578756826.jpg" align="absmiddle" class="img-ks-lazyload" /><img src="https://img.alicdn.com/imgextra/i4/2578756826/O1CN01QZj3VP20IM7QasLHX_!!2578756826.jpg" align="absmiddle" class="img-ks-lazyload" /><img src="https://img.alicdn.com/imgextra/i1/2578756826/O1CN01fqudc020IM7Wpmzxb_!!2578756826.jpg" align="absmiddle" class="img-ks-lazyload" /><img src="https://img.alicdn.com/imgextra/i4/2578756826/O1CN01rbzD9o20IM7WZ7BpO_!!2578756826.jpg" align="absmiddle" class="img-ks-lazyload" /><img src="https://img.alicdn.com/imgextra/i3/2578756826/O1CN01d4Xxf020IM7QaqrpU_!!2578756826.jpg" align="absmiddle" class="img-ks-lazyload" /><img src="https://img.alicdn.com/imgextra/i2/2578756826/O1CN01RjXy6G20IM7SmSO1y_!!2578756826.jpg" align="absmiddle" class="img-ks-lazyload" /><img src="https://img.alicdn.com/imgextra/i4/2578756826/O1CN01DvIzDx20IM7SmcnTq_!!2578756826.jpg" align="absmiddle" class="img-ks-lazyload" /><img src="https://img.alicdn.com/imgextra/i2/2578756826/O1CN01EyVd8j20IM7SmRJYR_!!2578756826.jpg" align="absmiddle" class="img-ks-lazyload" /><img src="https://img.alicdn.com/imgextra/i2/2578756826/O1CN01ayzKJp20IM7QatTxe_!!2578756826.jpg" align="absmiddle" class="img-ks-lazyload" /><img src="https://img.alicdn.com/imgextra/i2/2578756826/O1CN019CnNbX20IM4jOseCt_!!2578756826.jpg" align="absmiddle" class="img-ks-lazyload" /><img src="https://img.alicdn.com/imgextra/i2/2578756826/O1CN01HH0sfi20IM8cf4rTb_!!2578756826.jpg" align="absmiddle" class="img-ks-lazyload" /><img src="https://img.alicdn.com/imgextra/i4/2578756826/O1CN01XZsjcH20IM3Z0UWxg_!!2578756826.jpg" align="absmiddle" class="img-ks-lazyload" /><img src="https://img.alicdn.com/imgextra/i1/2578756826/O1CN01zgNmd320IM6dhu365_!!2578756826.jpg" align="absmiddle" class="img-ks-lazyload" /><img src="https://img.alicdn.com/imgextra/i2/2578756826/O1CN012eJaP520IM6ey3LoX_!!2578756826.jpg" align="absmiddle" class="img-ks-lazyload" /><img src="https://img.alicdn.com/imgextra/i2/2578756826/O1CN01D8oVZF20IM6gv2YZT_!!2578756826.jpg" align="absmiddle" class="img-ks-lazyload" /><img src="https://img.alicdn.com/imgextra/i1/2578756826/O1CN01RIMI4y20IM7RAdpWn_!!2578756826.jpg" align="absmiddle" class="img-ks-lazyload" /><img src="https://img.alicdn.com/imgextra/i3/2578756826/O1CN01Oi4ALZ20IM3Z8bIxv_!!2578756826.jpg" align="absmiddle" class="img-ks-lazyload" />
         * </p>
         * </div>
         * </div>
         * <div id="J_DcBottomRightWrap" style="margin:0px;padding:0px;color:#404040;font-family:tahoma, arial, 微软雅黑, sans-serif;background-color:#FFFFFF;">
         * <div id="J_ZebraPriceDesc" class="j-mdv" style="margin:0px;padding:0px;">
         * <img width="790" src="https://img.alicdn.com/tfs/TB1.CUdsY9YBuNjy0FgXXcxcXXa-1572-394.png" />
         * </div>
         * </div>
         * <div id="J_Detail" style="margin:0px;padding:0px;color:#404040;font-family:tahoma, arial, 微软雅黑, sans-serif;background-color:#FFFFFF;">
         * <div id="J_Reviews" class="J_DetailSection" style="margin:0px;padding:0px;">
         * <h4 class="hd" style="background-color:#FF0036;color:#FFFFFF;">
         * 累计评价&nbsp;<span class="J_ReviewsCount">120844</span>
         * </h4>
         * <div class="tm-rate" style="margin:0px;padding:0px;">
         * <div class="rate-header rate-header-tags" style="margin:0px;padding:10px 0px;border:1px solid #C8C8C8;">
         * <div class="rate-score" style="margin:0px;padding:0px 20px;vertical-align:top;text-align:center;">
         * <h4 style="font-weight:100;">
         * 与描述相符
         * </h4>
         * <strong>4.7</strong>
         * <p style="text-align:left;">
         * <span class="score-value-no score-value-4d7" style="background:url(&quot;"><span style="background:url(&quot;"></span></span>
         * </p>
         * </div>
         * <div class="rate-tag-box" style="margin:0px;padding:0px;vertical-align:top;">
         * <div class="rate-tag-label" style="margin:4px 0px 0px;padding:0px 12px 0px 25px;vertical-align:top;color:#CCCCCC;font-size:11px;background:url(&quot;">
         * <span>大家都写到</span>
         * </div>
         * <div class="rate-tag-list" style="margin:0px;padding:0px;vertical-align:top;">
         * <div class="rate-tag-inner" style="margin:0px;padding:0px;">
         * <span class="tag-posi"><a id="ratetag618_731a" href="https://detail.tmall.com/item.htm?id=573870605610&amp;ali_refid=a3_430673_1006:1122738397:N:cyaFL23emIrsmcdRMUfn1Q==:31a3ed4503c69dc8194c66567469c253&amp;ali_trackid=1_31a3ed4503c69dc8194c66567469c253&amp;spm=a2e15.8261149.07626516002.2&amp;sku_properties=211022001:10010#">功能正面(4611)<s></s></a></span><span class="tag-posi"><a id="ratetag618_420a" href="https://detail.tmall.com/item.htm?id=573870605610&amp;ali_refid=a3_430673_1006:1122738397:N:cyaFL23emIrsmcdRMUfn1Q==:31a3ed4503c69dc8194c66567469c253&amp;ali_trackid=1_31a3ed4503c69dc8194c66567469c253&amp;spm=a2e15.8261149.07626516002.2&amp;sku_properties=211022001:10010#">物流快(1843)</a></span>
         * </div>
         * </div>
         * </div>
         * </div>
         * </div>
         * </div>
         * </div>
         * imgStr : /upload/pro.png||/upload/pro.png
         * imgArr : ["/upload/pro.png","/upload/pro.png"]
         * gImg : /upload/pro.png
         * gState : 1
         * createDate : 2019-09-27 10:31:19
         */

        private String id;
        private String yGoodsId;
        private String yClassifyId;
        private String yStoreId;
        private String gName;
        private String gDesc;
        private double gPrice;
        private double orPrice;
        private String gDetails;
        private String imgStr;
        private String gImg;
        private int gState;
        private String createDate;
        private List<String> imgArr;

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

        public double getGPrice() {
            return gPrice;
        }

        public void setGPrice(double gPrice) {
            this.gPrice = gPrice;
        }

        public double getOrPrice() {
            return orPrice;
        }

        public void setOrPrice(double orPrice) {
            this.orPrice = orPrice;
        }

        public String getGDetails() {
            return gDetails;
        }

        public void setGDetails(String gDetails) {
            this.gDetails = gDetails;
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

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public List<String> getImgArr() {
            return imgArr;
        }

        public void setImgArr(List<String> imgArr) {
            this.imgArr = imgArr;
        }
    }

    public static class SpecificListBeanX {
        /**
         * id : 1017
         * yGoodsSpecificTypeId : 692341585785913344
         * yGoodsId : 692341585785913346
         * sName : 颜色
         * specific_List : [{"yGoodsSpecificId":"692341585785913344","yGoodsSpecificTypeId":"692341585785913344","yGoodsId":"692341585785913346","pName":"红色","sPrice":30},{"yGoodsSpecificId":"692341585785913345","yGoodsSpecificTypeId":"692341585785913344","yGoodsId":"692341585785913346","pName":"黑色","sPrice":20},{"yGoodsSpecificId":"692341585785913346","yGoodsSpecificTypeId":"692341585785913344","yGoodsId":"692341585785913346","pName":"绿色","sPrice":50}]
         */

        private String id;
        private String yGoodsSpecificTypeId;
        private String yGoodsId;
        private String sName;
        private List<SpecificListBean> specific_List;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYGoodsSpecificTypeId() {
            return yGoodsSpecificTypeId;
        }

        public void setYGoodsSpecificTypeId(String yGoodsSpecificTypeId) {
            this.yGoodsSpecificTypeId = yGoodsSpecificTypeId;
        }

        public String getYGoodsId() {
            return yGoodsId;
        }

        public void setYGoodsId(String yGoodsId) {
            this.yGoodsId = yGoodsId;
        }

        public String getSName() {
            return sName;
        }

        public void setSName(String sName) {
            this.sName = sName;
        }

        public List<SpecificListBean> getSpecific_List() {
            return specific_List;
        }

        public void setSpecific_List(List<SpecificListBean> specific_List) {
            this.specific_List = specific_List;
        }

        public static class SpecificListBean {
            /**
             * yGoodsSpecificId : 692341585785913344
             * yGoodsSpecificTypeId : 692341585785913344
             * yGoodsId : 692341585785913346
             * pName : 红色
             * sPrice : 30.0
             */

            private String yGoodsSpecificId;
            private String yGoodsSpecificTypeId;
            private String yGoodsId;
            private String pName;
            private double sPrice;

            public String getYGoodsSpecificId() {
                return yGoodsSpecificId;
            }

            public void setYGoodsSpecificId(String yGoodsSpecificId) {
                this.yGoodsSpecificId = yGoodsSpecificId;
            }

            public String getYGoodsSpecificTypeId() {
                return yGoodsSpecificTypeId;
            }

            public void setYGoodsSpecificTypeId(String yGoodsSpecificTypeId) {
                this.yGoodsSpecificTypeId = yGoodsSpecificTypeId;
            }

            public String getYGoodsId() {
                return yGoodsId;
            }

            public void setYGoodsId(String yGoodsId) {
                this.yGoodsId = yGoodsId;
            }

            public String getPName() {
                return pName;
            }

            public void setPName(String pName) {
                this.pName = pName;
            }

            public double getSPrice() {
                return sPrice;
            }

            public void setSPrice(double sPrice) {
                this.sPrice = sPrice;
            }
        }
    }
}
