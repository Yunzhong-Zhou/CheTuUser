package com.chetu.user.model;

import java.io.Serializable;

/**
 * Created by zyz on 2020/6/6.
 */
public class AddMerchantModel implements Serializable {
    /**
     * info : {"id":"1037","yApplyStoreId":"718854914201092096","userId":"714547022807433216","stepOneJson":"{\"com_website\":\"www.123455.com\",\"contact_num\":\"18306043086\",\"cor_address\":\"请问儿童医院阿斯顿\",\"cor_name\":\"阿斯顿马丁\",\"nickname\":\"Asdddsds\",\"v_contacts\":\"阿斯顿\",\"v_mailbox\":\"1233456774@qq.com\"}","stepTwoJson":"{\"d_type\":0,\"ident_number\":\" \",\"img_positive\":\" \",\"img_side\":\" \",\"is_long\":0,\"legal_person\":\" \",\"num_end_time\":\" \",\"num_start_time\":\" \",\"phone_number\":\" \"}","stepThreeJson":"{\"business_license\":\" \",\"business_scope\":\" \",\"cor_address\":\" \",\"credit_ode\":\" \",\"ent_introduction\":\" \",\"ex_time\":\" \",\"organization_code\":\" \",\"tax_registration\":\" \",\"tcor_name\":\" \"}","vStep":1,"store_step_one":{"nickname":"Asdddsds","cor_name":"阿斯顿马丁","cor_address":"请问儿童医院阿斯顿","com_website":"www.123455.com","v_contacts":"阿斯顿","contact_num":"18306043086","v_mailbox":"1233456774@qq.com"},"store_step_two":{"legal_person":" ","d_type":0,"is_long":0,"ident_number":" ","num_start_time":" ","num_end_time":" ","phone_number":" ","img_positive":" ","img_side":" "},"store_step_three":{"tcor_name":" ","cor_address":" ","credit_ode":" ","ex_time":" ","business_scope":" ","business_license":" ","organization_code":" ","tax_registration":" ","ent_introduction":" "}}
     */

    private InfoBean info;

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public static class InfoBean {
        /**
         * id : 1037
         * yApplyStoreId : 718854914201092096
         * userId : 714547022807433216
         * stepOneJson : {"com_website":"www.123455.com","contact_num":"18306043086","cor_address":"请问儿童医院阿斯顿","cor_name":"阿斯顿马丁","nickname":"Asdddsds","v_contacts":"阿斯顿","v_mailbox":"1233456774@qq.com"}
         * stepTwoJson : {"d_type":0,"ident_number":" ","img_positive":" ","img_side":" ","is_long":0,"legal_person":" ","num_end_time":" ","num_start_time":" ","phone_number":" "}
         * stepThreeJson : {"business_license":" ","business_scope":" ","cor_address":" ","credit_ode":" ","ent_introduction":" ","ex_time":" ","organization_code":" ","tax_registration":" ","tcor_name":" "}
         * vStep : 1
         * store_step_one : {"nickname":"Asdddsds","cor_name":"阿斯顿马丁","cor_address":"请问儿童医院阿斯顿","com_website":"www.123455.com","v_contacts":"阿斯顿","contact_num":"18306043086","v_mailbox":"1233456774@qq.com"}
         * store_step_two : {"legal_person":" ","d_type":0,"is_long":0,"ident_number":" ","num_start_time":" ","num_end_time":" ","phone_number":" ","img_positive":" ","img_side":" "}
         * store_step_three : {"tcor_name":" ","cor_address":" ","credit_ode":" ","ex_time":" ","business_scope":" ","business_license":" ","organization_code":" ","tax_registration":" ","ent_introduction":" "}
         */

        private String id;
        private String yApplyStoreId;
        private String userId;
        private String stepOneJson;
        private String stepTwoJson;
        private String stepThreeJson;
        private int vStep;
        private StoreStepOneBean store_step_one;
        private StoreStepTwoBean store_step_two;
        private StoreStepThreeBean store_step_three;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYApplyStoreId() {
            return yApplyStoreId;
        }

        public void setYApplyStoreId(String yApplyStoreId) {
            this.yApplyStoreId = yApplyStoreId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getStepOneJson() {
            return stepOneJson;
        }

        public void setStepOneJson(String stepOneJson) {
            this.stepOneJson = stepOneJson;
        }

        public String getStepTwoJson() {
            return stepTwoJson;
        }

        public void setStepTwoJson(String stepTwoJson) {
            this.stepTwoJson = stepTwoJson;
        }

        public String getStepThreeJson() {
            return stepThreeJson;
        }

        public void setStepThreeJson(String stepThreeJson) {
            this.stepThreeJson = stepThreeJson;
        }

        public int getVStep() {
            return vStep;
        }

        public void setVStep(int vStep) {
            this.vStep = vStep;
        }

        public StoreStepOneBean getStore_step_one() {
            return store_step_one;
        }

        public void setStore_step_one(StoreStepOneBean store_step_one) {
            this.store_step_one = store_step_one;
        }

        public StoreStepTwoBean getStore_step_two() {
            return store_step_two;
        }

        public void setStore_step_two(StoreStepTwoBean store_step_two) {
            this.store_step_two = store_step_two;
        }

        public StoreStepThreeBean getStore_step_three() {
            return store_step_three;
        }

        public void setStore_step_three(StoreStepThreeBean store_step_three) {
            this.store_step_three = store_step_three;
        }

        public static class StoreStepOneBean {
            /**
             * nickname : Asdddsds
             * cor_name : 阿斯顿马丁
             * cor_address : 请问儿童医院阿斯顿
             * com_website : www.123455.com
             * v_contacts : 阿斯顿
             * contact_num : 18306043086
             * v_mailbox : 1233456774@qq.com
             */

            private String nickname;
            private String cor_name;
            private String cor_address;
            private String com_website;
            private String v_contacts;
            private String contact_num;
            private String v_mailbox;

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getCor_name() {
                return cor_name;
            }

            public void setCor_name(String cor_name) {
                this.cor_name = cor_name;
            }

            public String getCor_address() {
                return cor_address;
            }

            public void setCor_address(String cor_address) {
                this.cor_address = cor_address;
            }

            public String getCom_website() {
                return com_website;
            }

            public void setCom_website(String com_website) {
                this.com_website = com_website;
            }

            public String getV_contacts() {
                return v_contacts;
            }

            public void setV_contacts(String v_contacts) {
                this.v_contacts = v_contacts;
            }

            public String getContact_num() {
                return contact_num;
            }

            public void setContact_num(String contact_num) {
                this.contact_num = contact_num;
            }

            public String getV_mailbox() {
                return v_mailbox;
            }

            public void setV_mailbox(String v_mailbox) {
                this.v_mailbox = v_mailbox;
            }
        }

        public static class StoreStepTwoBean {
            /**
             * legal_person :
             * d_type : 0
             * is_long : 0
             * ident_number :
             * num_start_time :
             * num_end_time :
             * phone_number :
             * img_positive :
             * img_side :
             */

            private String legal_person;
            private int d_type;
            private int is_long;
            private String ident_number;
            private String num_start_time;
            private String num_end_time;
            private String phone_number;
            private String img_positive;
            private String img_side;

            public String getLegal_person() {
                return legal_person;
            }

            public void setLegal_person(String legal_person) {
                this.legal_person = legal_person;
            }

            public int getD_type() {
                return d_type;
            }

            public void setD_type(int d_type) {
                this.d_type = d_type;
            }

            public int getIs_long() {
                return is_long;
            }

            public void setIs_long(int is_long) {
                this.is_long = is_long;
            }

            public String getIdent_number() {
                return ident_number;
            }

            public void setIdent_number(String ident_number) {
                this.ident_number = ident_number;
            }

            public String getNum_start_time() {
                return num_start_time;
            }

            public void setNum_start_time(String num_start_time) {
                this.num_start_time = num_start_time;
            }

            public String getNum_end_time() {
                return num_end_time;
            }

            public void setNum_end_time(String num_end_time) {
                this.num_end_time = num_end_time;
            }

            public String getPhone_number() {
                return phone_number;
            }

            public void setPhone_number(String phone_number) {
                this.phone_number = phone_number;
            }

            public String getImg_positive() {
                return img_positive;
            }

            public void setImg_positive(String img_positive) {
                this.img_positive = img_positive;
            }

            public String getImg_side() {
                return img_side;
            }

            public void setImg_side(String img_side) {
                this.img_side = img_side;
            }
        }

        public static class StoreStepThreeBean {
            /**
             * tcor_name :
             * cor_address :
             * credit_ode :
             * ex_time :
             * business_scope :
             * business_license :
             * organization_code :
             * tax_registration :
             * ent_introduction :
             */

            private String tcor_name;
            private String cor_address;
            private String credit_ode;
            private String ex_time;
            private String business_scope;
            private String business_license;
            private String organization_code;
            private String organization_img;
            private String tax_registration;
            private String ent_introduction;

            public String getOrganization_img() {
                return organization_img;
            }

            public void setOrganization_img(String organization_img) {
                this.organization_img = organization_img;
            }

            public String getTcor_name() {
                return tcor_name;
            }

            public void setTcor_name(String tcor_name) {
                this.tcor_name = tcor_name;
            }

            public String getCor_address() {
                return cor_address;
            }

            public void setCor_address(String cor_address) {
                this.cor_address = cor_address;
            }

            public String getCredit_ode() {
                return credit_ode;
            }

            public void setCredit_ode(String credit_ode) {
                this.credit_ode = credit_ode;
            }

            public String getEx_time() {
                return ex_time;
            }

            public void setEx_time(String ex_time) {
                this.ex_time = ex_time;
            }

            public String getBusiness_scope() {
                return business_scope;
            }

            public void setBusiness_scope(String business_scope) {
                this.business_scope = business_scope;
            }

            public String getBusiness_license() {
                return business_license;
            }

            public void setBusiness_license(String business_license) {
                this.business_license = business_license;
            }

            public String getOrganization_code() {
                return organization_code;
            }

            public void setOrganization_code(String organization_code) {
                this.organization_code = organization_code;
            }

            public String getTax_registration() {
                return tax_registration;
            }

            public void setTax_registration(String tax_registration) {
                this.tax_registration = tax_registration;
            }

            public String getEnt_introduction() {
                return ent_introduction;
            }

            public void setEnt_introduction(String ent_introduction) {
                this.ent_introduction = ent_introduction;
            }
        }
    }
}
