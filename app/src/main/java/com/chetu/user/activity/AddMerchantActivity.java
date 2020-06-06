package com.chetu.user.activity;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bumptech.glide.Glide;
import com.chetu.user.R;
import com.chetu.user.base.BaseActivity;
import com.chetu.user.model.AddMerchantModel;
import com.chetu.user.model.UpFileModel;
import com.chetu.user.net.URLs;
import com.chetu.user.okhttp.CallBackUtil;
import com.chetu.user.okhttp.OkhttpUtil;
import com.chetu.user.utils.CommonUtil;
import com.chetu.user.utils.MyChooseImages;
import com.chetu.user.utils.MyLogger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import id.zelory.compressor.Compressor;
import okhttp3.Call;
import okhttp3.Response;

import static com.chetu.user.utils.MyChooseImages.REQUEST_CODE_CAPTURE_CAMEIA;
import static com.chetu.user.utils.MyChooseImages.REQUEST_CODE_PICK_IMAGE;

/**
 * Created by zyz on 2020/5/26.
 * 添加商户
 */
public class AddMerchantActivity extends BaseActivity {
    int type = 1, progress = 1;//进度
    String imgType = "";
    ArrayList<File> listFiles = new ArrayList<>();

    LinearLayout linearLayout1, linearLayout2, linearLayout3, ll_type1, ll_type2, ll_type3;
    TextView textView1, textView2, textView3;
    View view1, view2, view3;

    //第一步
    EditText et1_nick, et1_firmname, et1_firmaddr, et1_firmweb, et1_name, et1_phonenum, et1_email;
    TextView tv1_next;
    String nickname = "", cor_name = "", com_website = "", cor_address = "", v_contacts = "", contact_num = "", v_mailbox = "";

    //第二步
    EditText et2_name, et2_num, et2_phone;
    TextView tv2_cardtype, tv2_date1, tv2_date2, tv2_next;
    LinearLayout ll2_shi, ll2_fou, ll2_up1load1, ll2_up1load2;
    ImageView iv2_shi, iv2_fou, iv2_card1, iv2_card2;
    String legal_person = "", d_type = "1", ident_number = "", is_long = "1", num_start_time = "", num_end_time = "",
            phone_number = "", img_positive = "", img_side = "";
    TimePickerView pvTime1;
    TimePickerView pvTime2;


    //第三步
    EditText et3_creditcode, et3_yinyefanwei, et3_organizationcode, et3_qiyejieshao;
    TextView tv3_frimname, tv3_frimaddr, tv3_date, tv3_save, tv3_upload;
    LinearLayout ll3_up1load1, ll3_up1load2, ll3_up1load3;
    ImageView iv3_photo1, iv3_photo2, iv3_photo3;
    String tcor_name = "", credit_ode = "", ex_time = "", business_scope = "", business_license = "", organization_code = "",
            tax_registration = "", ent_introduction = "", organization_img = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmerchant);
    }

    @Override
    protected void initView() {
        linearLayout1 = findViewByID_My(R.id.linearLayout1);
        linearLayout2 = findViewByID_My(R.id.linearLayout2);
        linearLayout3 = findViewByID_My(R.id.linearLayout3);
        ll_type1 = findViewByID_My(R.id.ll_type1);
        ll_type2 = findViewByID_My(R.id.ll_type2);
        ll_type3 = findViewByID_My(R.id.ll_type3);
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
        view1 = findViewByID_My(R.id.view1);
        view2 = findViewByID_My(R.id.view2);
        view3 = findViewByID_My(R.id.view3);

        //第一步
        et1_nick = findViewByID_My(R.id.et1_nick);
        et1_firmname = findViewByID_My(R.id.et1_firmname);
        et1_firmaddr = findViewByID_My(R.id.et1_firmaddr);
        et1_firmweb = findViewByID_My(R.id.et1_firmweb);
        et1_name = findViewByID_My(R.id.et1_name);
        et1_phonenum = findViewByID_My(R.id.et1_phonenum);
        et1_email = findViewByID_My(R.id.et1_email);
        tv1_next = findViewByID_My(R.id.tv1_next);

        //第二步
        et2_name = findViewByID_My(R.id.et2_name);
        et2_num = findViewByID_My(R.id.et2_num);
        et2_phone = findViewByID_My(R.id.et2_phone);
        tv2_cardtype = findViewByID_My(R.id.tv2_cardtype);
        tv2_date1 = findViewByID_My(R.id.tv2_date1);
        tv2_date2 = findViewByID_My(R.id.tv2_date2);
        tv2_next = findViewByID_My(R.id.tv2_next);
        ll2_shi = findViewByID_My(R.id.ll2_shi);
        ll2_fou = findViewByID_My(R.id.ll2_fou);
        ll2_up1load1 = findViewByID_My(R.id.ll2_up1load1);
        ll2_up1load2 = findViewByID_My(R.id.ll2_up1load2);
        iv2_shi = findViewByID_My(R.id.iv2_shi);
        iv2_fou = findViewByID_My(R.id.iv2_fou);
        iv2_card1 = findViewByID_My(R.id.iv2_card1);
        iv2_card2 = findViewByID_My(R.id.iv2_card2);
        //第三步
        et3_creditcode = findViewByID_My(R.id.et3_creditcode);
        et3_yinyefanwei = findViewByID_My(R.id.et3_yinyefanwei);
        et3_organizationcode = findViewByID_My(R.id.et3_organizationcode);
        et3_qiyejieshao = findViewByID_My(R.id.et3_qiyejieshao);
        tv3_frimname = findViewByID_My(R.id.tv3_frimname);
        tv3_frimaddr = findViewByID_My(R.id.tv3_frimaddr);
        tv3_date = findViewByID_My(R.id.tv3_date);
        tv3_save = findViewByID_My(R.id.tv3_save);
        tv3_upload = findViewByID_My(R.id.tv3_upload);
        ll3_up1load1 = findViewByID_My(R.id.ll3_up1load1);
        ll3_up1load2 = findViewByID_My(R.id.ll3_up1load2);
        ll3_up1load3 = findViewByID_My(R.id.ll3_up1load3);
        iv3_photo1 = findViewByID_My(R.id.iv3_photo1);
        iv3_photo2 = findViewByID_My(R.id.iv3_photo2);
        iv3_photo3 = findViewByID_My(R.id.iv3_photo3);

        changeUI();

    }

    @Override
    protected void initData() {
        showProgress(true, getString(R.string.app_loading1));
        Map<String, String> params = new HashMap<>();
        params.put("u_token", localUserInfo.getToken());
        Request(params);
    }

    private void Request(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.AddMerchant, params, headerMap, new CallBackUtil<AddMerchantModel>() {
            @Override
            public AddMerchantModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(AddMerchantModel response) {
                hideProgress();
                progress = response.getInfo().getVStep();//进度
                type = response.getInfo().getVStep();
                changeUI();

                if (response.getInfo().getVStep() == 3){
                    tv1_next.setVisibility(View.GONE);
                    tv2_next.setVisibility(View.GONE);
                    tv3_upload.setVisibility(View.GONE);
                }
                /**
                 * 第一步
                 */
                if (response.getInfo().getStore_step_one().getNickname() != null && !response.getInfo().getStore_step_one().getNickname().trim().equals("")) {
                    et1_nick.setText(response.getInfo().getStore_step_one().getNickname());//昵称
                    et1_firmname.setText(response.getInfo().getStore_step_one().getCor_name());//公司名称
                    et1_firmaddr.setText(response.getInfo().getStore_step_one().getCor_address());//公司地址
                    et1_firmweb.setText(response.getInfo().getStore_step_one().getCom_website());//公司网址
                    et1_name.setText(response.getInfo().getStore_step_one().getV_contacts());//联系人
                    et1_phonenum.setText(response.getInfo().getStore_step_one().getContact_num());//联系电话
                    et1_email.setText(response.getInfo().getStore_step_one().getV_mailbox());//邮箱
                }

                /**
                 * 第二步
                 */
                if (response.getInfo().getStore_step_two().getLegal_person() != null && !response.getInfo().getStore_step_two().getLegal_person().trim().equals("")) {
                    et2_name.setText(response.getInfo().getStore_step_two().getLegal_person());//企业法人
                    et2_num.setText(response.getInfo().getStore_step_two().getIdent_number());//法人证件号
                    tv2_date1.setText(response.getInfo().getStore_step_two().getNum_start_time());//证件生效时间
                    tv2_date2.setText(response.getInfo().getStore_step_two().getNum_end_time());//证件失效时间
                    et2_phone.setText(response.getInfo().getStore_step_two().getPhone_number());//法人手机号

                    //是否长期
                    is_long = response.getInfo().getStore_step_two().getIs_long() + "";
                    if (response.getInfo().getStore_step_two().getIs_long() == 1) {
                        iv2_shi.setImageResource(R.mipmap.ic_xuanzhong);
                        iv2_fou.setImageResource(R.mipmap.ic_weixuan);
                    } else {
                        iv2_shi.setImageResource(R.mipmap.ic_weixuan);
                        iv2_fou.setImageResource(R.mipmap.ic_xuanzhong);
                    }

                    //上传法人手持身份证照片
                    img_positive = response.getInfo().getStore_step_two().getImg_positive();
                    ll2_up1load1.setVisibility(View.GONE);
                    iv2_card1.setVisibility(View.VISIBLE);
                    Glide.with(AddMerchantActivity.this)
                            .load(URLs.IMGHOST + response.getInfo().getStore_step_two().getImg_positive())
                            .centerCrop()
                            .into(iv2_card1);

                    //上传法定代表人身份证复印件
                    img_side = response.getInfo().getStore_step_two().getImg_side();
                    ll2_up1load2.setVisibility(View.GONE);
                    iv2_card2.setVisibility(View.VISIBLE);
                    Glide.with(AddMerchantActivity.this)
                            .load(URLs.IMGHOST + response.getInfo().getStore_step_two().getImg_side())
                            .centerCrop()
                            .into(iv2_card2);
                }
                /**
                 * 第三步
                 */
                if (response.getInfo().getStore_step_three().getTcor_name() != null && !response.getInfo().getStore_step_three().getTcor_name().trim().equals("")) {
                    tv3_frimname.setText(response.getInfo().getStore_step_three().getTcor_name());//公司名称
                    tv3_frimaddr.setText(response.getInfo().getStore_step_three().getCor_address());//公司地址
                    et3_creditcode.setText(response.getInfo().getStore_step_three().getCredit_ode());//信用代码
                    tv3_date.setText(response.getInfo().getStore_step_three().getEx_time());//执照过期日
                    et3_yinyefanwei.setText(response.getInfo().getStore_step_three().getBusiness_scope());//营业范围
                    et3_organizationcode.setText(response.getInfo().getStore_step_three().getOrganization_code());//组织机构代码
                    et3_qiyejieshao.setText(response.getInfo().getStore_step_three().getEnt_introduction());//企业介绍

                    //上传营业执照照片
                    business_license = response.getInfo().getStore_step_three().getBusiness_license();
                    ll3_up1load1.setVisibility(View.GONE);
                    iv3_photo1.setVisibility(View.VISIBLE);
                    Glide.with(AddMerchantActivity.this)
                            .load(URLs.IMGHOST + response.getInfo().getStore_step_three().getBusiness_license())
                            .centerCrop()
                            .into(iv3_photo1);
                    //上传组织机构代码证照片
                    organization_img = response.getInfo().getStore_step_three().getOrganization_img();
                    ll3_up1load2.setVisibility(View.GONE);
                    iv3_photo2.setVisibility(View.VISIBLE);
                    Glide.with(AddMerchantActivity.this)
                            .load(URLs.IMGHOST + response.getInfo().getStore_step_three().getOrganization_img())
                            .centerCrop()
                            .into(iv3_photo2);
                    //上传税务登记证照片
                    tax_registration = response.getInfo().getStore_step_three().getTax_registration();
                    ll3_up1load3.setVisibility(View.GONE);
                    iv3_photo3.setVisibility(View.VISIBLE);
                    Glide.with(AddMerchantActivity.this)
                            .load(URLs.IMGHOST + response.getInfo().getStore_step_three().getTax_registration())
                            .centerCrop()
                            .into(iv3_photo3);
                }

            }
        });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            /**
             * 第一步
             */
            case R.id.ll_type1:
                //第一页
                type = 1;
                changeUI();
                break;
            case R.id.tv1_next:
                //第一页  下一步
                if (match1()) {
                    showProgress(true, getString(R.string.app_loading1));
                    Map<String, String> params = new HashMap<>();
                    params.put("nickname", nickname);
                    params.put("cor_name", cor_name);
                    params.put("com_website", com_website);
                    params.put("cor_address", cor_address);
                    params.put("v_contacts", v_contacts);
                    params.put("contact_num", contact_num);
                    params.put("v_mailbox", v_mailbox);
                    params.put("u_token", localUserInfo.getToken());
                    RequestUpData1(params);
                }
                break;
            /**
             * 第二步
             */
            case R.id.ll2_shi:
                //是
                is_long = "1";
                iv2_shi.setImageResource(R.mipmap.ic_xuanzhong);
                iv2_fou.setImageResource(R.mipmap.ic_weixuan);
                break;
            case R.id.ll2_fou:
                //否
                is_long = "0";
                iv2_shi.setImageResource(R.mipmap.ic_weixuan);
                iv2_fou.setImageResource(R.mipmap.ic_xuanzhong);
                break;
            case R.id.tv2_date1:
                //生效日期
                setDate1("请选择生效日期", tv2_date1);
                break;
            case R.id.tv2_date2:
                //生效日期
                setDate1("请选择失效日期", tv2_date2);
                break;
            case R.id.ll2_up1load1:
            case R.id.iv2_card1:
                //法人手持身份证照片
                imgType = "img_positive";
                MyChooseImages.showPhotoDialog(this);
                break;
            case R.id.ll2_up1load2:
            case R.id.iv2_card2:
                //法定代表人身份证复印件
                imgType = "img_side";
                MyChooseImages.showPhotoDialog(this);
                break;
            case R.id.ll_type2:
                //第二页
                if (progress >= 2) {
                    type = 2;
                    changeUI();
                }
                break;
            case R.id.tv2_next:
                //第二页  下一步

                if (match2()) {
                    showProgress(true, getString(R.string.app_loading1));
                    Map<String, String> params = new HashMap<>();
                    params.put("legal_person", legal_person);
                    params.put("d_type", d_type);
                    params.put("ident_number", ident_number);
                    params.put("is_long", is_long);
                    params.put("num_start_time", num_start_time);
                    params.put("num_end_time", num_end_time);
                    params.put("phone_number", phone_number);
                    params.put("img_positive", img_positive);
                    params.put("img_side", img_side);
                    params.put("u_token", localUserInfo.getToken());
                    RequestUpData2(params);
                }
                break;
            /**
             * 第三步
             */
            case R.id.tv3_date:
                //执照过期日
                setDate1("请选择执照过期日", tv3_date);
                break;
            case R.id.ll3_up1load1:
            case R.id.iv3_photo1:
                //上传营业执照照片
                imgType = "business_license";
                MyChooseImages.showPhotoDialog(this);
                break;
            case R.id.ll3_up1load2:
            case R.id.iv3_photo2:
                //上传组织机构代码证照片
                imgType = "organization";
                MyChooseImages.showPhotoDialog(this);
                break;
            case R.id.ll3_up1load3:
            case R.id.iv3_photo3:
                //上传税务登记证照片
                imgType = "tax_registration";
                MyChooseImages.showPhotoDialog(this);
                break;
            case R.id.ll_type3:
                //第三步
                if (progress >= 3) {
                    type = 3;
                    changeUI();
                }
                break;
            case R.id.tv3_upload:
                //提交
                if (match3()) {
                    showProgress(true, getString(R.string.app_loading1));
                    Map<String, String> params = new HashMap<>();
                    params.put("tcor_name", tcor_name);
                    params.put("cor_address", cor_address);
                    params.put("credit_ode", credit_ode);
                    params.put("ex_time", ex_time);
                    params.put("business_scope", business_scope);
                    params.put("business_license", business_license);
                    params.put("organization_code", organization_code);
                    params.put("organization_img", organization_img);
                    params.put("tax_registration", tax_registration);
                    params.put("ent_introduction", ent_introduction);
                    params.put("u_token", localUserInfo.getToken());
                    RequestUpData3(params);
                }
                break;

        }
    }

    /**
     * 提交第一步数据
     *
     * @return
     */
    private boolean match1() {
        nickname = et1_nick.getText().toString().trim();
        if (TextUtils.isEmpty(nickname)) {
            myToast("请输入昵称");
            return false;
        }
        cor_name = et1_firmname.getText().toString().trim();
        if (TextUtils.isEmpty(cor_name)) {
            myToast("请输入公司名称");
            return false;
        }
        cor_address = et1_firmaddr.getText().toString().trim();
        if (TextUtils.isEmpty(cor_address)) {
            myToast("请输入公司地址");
            return false;
        }
        com_website = et1_firmweb.getText().toString().trim();
        if (TextUtils.isEmpty(com_website)) {
            myToast("请输入公司网址");
            return false;
        }
        v_contacts = et1_name.getText().toString().trim();
        if (TextUtils.isEmpty(v_contacts)) {
            myToast("请输入联系人");
            return false;
        }
        contact_num = et1_phonenum.getText().toString().trim();
        if (TextUtils.isEmpty(contact_num)) {
            myToast("请输入联系电话");
            return false;
        }
        v_mailbox = et1_email.getText().toString().trim();
        if (TextUtils.isEmpty(v_mailbox)) {
            myToast("请输入邮箱");
            return false;
        }
        return true;
    }

    private void RequestUpData1(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.AddMerchant1, params, headerMap, new CallBackUtil<Object>() {
            @Override
            public Object onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(Object response) {
                hideProgress();
                type = 2;
                progress = 2;
                changeUI();
//                myToast("添加成功");
//                finish();
            }
        });
    }

    /**
     * 提交第二步数据
     *
     * @return
     */
    private boolean match2() {
        legal_person = et2_name.getText().toString().trim();
        if (TextUtils.isEmpty(legal_person)) {
            myToast("请输入法人姓名");
            return false;
        }
        /*d_type = tv2_cardtype.getText().toString().trim();
        if (TextUtils.isEmpty(d_type)) {
            myToast("请选择证件类型");
            return false;
        }*/
        ident_number = et2_num.getText().toString().trim();
        if (TextUtils.isEmpty(ident_number)) {
            myToast("请输入证件号码");
            return false;
        }
        num_start_time = tv2_date1.getText().toString().trim();
        if (TextUtils.isEmpty(num_start_time)) {
            myToast("请选择证件生效日期");
            return false;
        }
        num_end_time = tv2_date2.getText().toString().trim();
        if (TextUtils.isEmpty(num_end_time)) {
            myToast("请选择证件失效日期");
            return false;
        }
        phone_number = et2_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone_number)) {
            myToast("请输入法人手机号");
            return false;
        }
        if (TextUtils.isEmpty(img_positive)) {
            myToast("请上传法人手持身份证照片");
            return false;
        }
        if (TextUtils.isEmpty(img_side)) {
            myToast("请上传法定代表人身份证复印件");
            return false;
        }
        return true;
    }

    private void RequestUpData2(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.AddMerchant2, params, headerMap, new CallBackUtil<Object>() {
            @Override
            public Object onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(Object response) {
                hideProgress();
                type = 3;
                progress = 3;
                changeUI();
//                myToast("添加成功");
//                finish();
            }
        });
    }

    /**
     * 提交第三步数据
     *
     * @return
     */
    private boolean match3() {
        tcor_name = tv3_frimname.getText().toString().trim();
        /*if (TextUtils.isEmpty(tcor_name)) {
            myToast("请输入公司名称");
            return false;
        }*/
        cor_address = tv3_frimaddr.getText().toString().trim();
        /*if (TextUtils.isEmpty(d_type)) {
            myToast("请输入公司地址");
            return false;
        }*/
        credit_ode = et3_creditcode.getText().toString().trim();
        if (TextUtils.isEmpty(credit_ode)) {
            myToast("请输入信用代码");
            return false;
        }
        ex_time = tv3_date.getText().toString().trim();
        if (TextUtils.isEmpty(ex_time)) {
            myToast("请选择执照过期日");
            return false;
        }
        business_scope = et3_yinyefanwei.getText().toString().trim();
        if (TextUtils.isEmpty(business_scope)) {
            myToast("请填写营业范围");
            return false;
        }
        organization_code = et3_organizationcode.getText().toString().trim();
        if (TextUtils.isEmpty(organization_code)) {
            myToast("请输入组织机构代码");
            return false;
        }
        ent_introduction = et3_qiyejieshao.getText().toString().trim();
        if (TextUtils.isEmpty(ent_introduction)) {
            myToast("请填写企业介绍");
            return false;
        }
        if (TextUtils.isEmpty(business_license)) {
            myToast("请上传营业执照照片");
            return false;
        }
        if (TextUtils.isEmpty(organization_img)) {
            myToast("请上传组织机构代码证照片");
            return false;
        }
        if (TextUtils.isEmpty(organization_code)) {
            myToast("请上传组织机构代码证照片");
            return false;
        }
        return true;
    }

    private void RequestUpData3(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.AddMerchant3, params, headerMap, new CallBackUtil<Object>() {
            @Override
            public Object onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(Object response) {
                hideProgress();
                finish();
//                myToast("添加成功");
//                finish();
            }
        });
    }

    private void changeUI() {
        switch (type) {
            case 1:
                //第一步
                textView1.setTextColor(getResources().getColor(R.color.white));
                textView1.setBackgroundResource(R.drawable.yuanjiao_15_lanse);
                view1.setBackgroundResource(R.color.orange);
                textView2.setTextColor(getResources().getColor(R.color.grey));
                textView2.setBackgroundResource(R.color.transparent);
                view2.setBackgroundResource(R.color.grey);
                textView3.setTextColor(getResources().getColor(R.color.grey));
                textView3.setBackgroundResource(R.color.transparent);
                view3.setBackgroundResource(R.color.grey);

                linearLayout1.setVisibility(View.VISIBLE);
                linearLayout2.setVisibility(View.GONE);
                linearLayout3.setVisibility(View.GONE);

                break;
            case 2:
                //第二步
                textView1.setTextColor(getResources().getColor(R.color.white));
                textView1.setBackgroundResource(R.drawable.yuanjiao_15_lanse);
                view1.setBackgroundResource(R.color.orange);
                textView2.setTextColor(getResources().getColor(R.color.white));
                textView2.setBackgroundResource(R.drawable.yuanjiao_15_lanse);
                view2.setBackgroundResource(R.color.orange);
                textView3.setTextColor(getResources().getColor(R.color.grey));
                textView3.setBackgroundResource(R.color.transparent);
                view3.setBackgroundResource(R.color.grey);

                linearLayout1.setVisibility(View.GONE);
                linearLayout2.setVisibility(View.VISIBLE);
                linearLayout3.setVisibility(View.GONE);
                break;
            case 3:
                //第三步
                textView1.setTextColor(getResources().getColor(R.color.white));
                textView1.setBackgroundResource(R.drawable.yuanjiao_15_lanse);
                view1.setBackgroundResource(R.color.orange);
                textView2.setTextColor(getResources().getColor(R.color.white));
                textView2.setBackgroundResource(R.drawable.yuanjiao_15_lanse);
                view2.setBackgroundResource(R.color.orange);
                textView3.setTextColor(getResources().getColor(R.color.white));
                textView3.setBackgroundResource(R.drawable.yuanjiao_15_lanse);
                view3.setBackgroundResource(R.color.orange);

                linearLayout1.setVisibility(View.GONE);
                linearLayout2.setVisibility(View.GONE);
                linearLayout3.setVisibility(View.VISIBLE);

                //公司名称
                tv3_frimname.setText(et1_firmname.getText().toString().trim());
                //公司地址
                tv3_frimaddr.setText(et1_firmaddr.getText().toString().trim());

                break;
        }
    }

    @Override
    protected void updateView() {

    }

    private void setDate1(String string, TextView textView) {
        //获取当前时间
        Calendar calendar = Calendar.getInstance();
        //年
        int year = calendar.get(Calendar.YEAR);
        //月
        int month = calendar.get(Calendar.MONTH);
        //日
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        //小时
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        //分钟
        int minute = calendar.get(Calendar.MINUTE);
        //秒
        int second = calendar.get(Calendar.SECOND);

        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();

        //正确设置方式 原因：注意事项有说明
//        startDate.set(year, month, day);
        startDate.set(1900, 0, 1);

        //当前时间加3天
//        calendar.add(Calendar.YEAR, 100);
        endDate.set(2100, 11, 1);
        /*endDate.set(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));*/


        pvTime1 = new TimePickerBuilder(AddMerchantActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                textView.setText(CommonUtil.getTime(date));
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确定")//确认按钮文字
                .setContentTextSize(15)//滚轮文字大小
                .setTitleSize(16)//标题文字大小
                .setTitleText(string)//标题文字
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(false)//是否循环滚动
                .setTitleColor(getResources().getColor(R.color.black2))//标题文字颜色
                .setSubmitColor(getResources().getColor(R.color.blue))//确定按钮文字颜色
                .setCancelColor(getResources().getColor(R.color.blue))//取消按钮文字颜色
                .setTitleBgColor(getResources().getColor(R.color.black5))//标题背景颜色 Night mode
                .setBgColor(getResources().getColor(R.color.white))//滚轮背景颜色 Night mode
                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                .setRangDate(startDate, endDate)//起始终止年月日设定
                .setLabel("年", "月", "日", "时", "分", "秒")//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(true)//是否显示为对话框样式
                .build();

        Dialog mDialog = pvTime1.getDialog();
        if (mDialog != null) {

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);
            params.leftMargin = 0;
            params.rightMargin = 0;
            pvTime1.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
                dialogWindow.setDimAmount(0.1f);
            }
        }
        pvTime1.show();
    }


    /**
     * *****************************************选择图片********************************************
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Uri uri = null;
            String imagePath = null;
            switch (requestCode) {
                case REQUEST_CODE_CAPTURE_CAMEIA:
                    //相机
                    uri = Uri.parse("");
                    uri = Uri.fromFile(new File(MyChooseImages.imagepath));
                    imagePath = uri.getPath();
                    break;
                case REQUEST_CODE_PICK_IMAGE:
                    //相册
                    uri = data.getData();
                    //处理得到的url
                    ContentResolver cr = this.getContentResolver();
                    Cursor cursor = null;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        cursor = cr.query(uri, null, null, null, null, null);
                    }
                    if (cursor != null) {
                        cursor.moveToFirst();
                        imagePath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                    } else {
                        imagePath = uri.getPath();
                    }
                    break;
            }
            if (uri != null) {
                MyLogger.i(">>>>>>>>>>获取到的图片路径1：" + imagePath);

                //图片过大解决方法
                /*BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 2;
                Bitmap bitmap = BitmapFactory.decodeFile(imagePath, options);
                imageView1.setImageBitmap(bitmap);
                imageView1.setScaleType(ImageView.ScaleType.CENTER_CROP);*/

                File file1 = new File(imagePath);
                listFiles = new ArrayList<>();
                File newFile = null;
                try {
                    newFile = new Compressor(this).compressToFile(file1);
                    listFiles.add(newFile);
//                    MyLogger.i(">>>>>选择图片结果>>>>>>>>>" + listFileNames.toString() + ">>>>>>" + listFiles.toString());
                    showProgress(true, "正在上传图片，请稍候...");
//                    Map<String, File> fileMap = new HashMap<>();
//                    fileMap.put("picture", newFile);
                    Map<String, String> params = new HashMap<>();
                    params.put("sn", "773EDB6D2715FACF9C93354CAC5B1A3372872DC4D5AC085867C7490E9984D33E");
//                    RequestUpFile(fileMap, params);
                    RequestUpFile(params, listFiles, "picture");//key不需要变

                } catch (IOException e) {
                    e.printStackTrace();
                    myToast(getString(R.string.app_imgerr));
                }
            }
        }

    }

    /**
     * 上传文件 list 方式
     *
     * @param params
     * @param fileList
     * @param fileKey
     */
    private void RequestUpFile(Map<String, String> params, List<File> fileList, String fileKey) {
        OkhttpUtil.okHttpUploadListFile(URLs.UpFile, params, fileList, fileKey, "image", headerMap, new CallBackUtil<UpFileModel>() {
            @Override
            public UpFileModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                if (!err.equals("")) {
                    showToast(err);
                }
            }

            @Override
            public void onResponse(UpFileModel response) {
                hideProgress();
//                myToast("头像修改成功");
                for (String s : response.getList()) {
                    switch (imgType) {
                        case "img_positive":
                            img_positive = s;
                            ll2_up1load1.setVisibility(View.GONE);
                            iv2_card1.setVisibility(View.VISIBLE);
                            Glide.with(AddMerchantActivity.this).load(URLs.IMGHOST + s)
                                    .centerCrop()
                                    .into(iv2_card1);//加载图片
                            break;
                        case "img_side":
                            img_side = s;
                            ll2_up1load2.setVisibility(View.GONE);
                            iv2_card2.setVisibility(View.VISIBLE);
                            Glide.with(AddMerchantActivity.this).load(URLs.IMGHOST + s)
                                    .centerCrop()
                                    .into(iv2_card2);//加载图片
                            break;
                        case "business_license":
                            business_license = s;
                            ll3_up1load1.setVisibility(View.GONE);
                            iv3_photo1.setVisibility(View.VISIBLE);
                            Glide.with(AddMerchantActivity.this).load(URLs.IMGHOST + s)
                                    .centerCrop()
                                    .into(iv3_photo1);//加载图片
                            break;
                        case "organization":
                            organization_img = s;
                            ll3_up1load2.setVisibility(View.GONE);
                            iv3_photo2.setVisibility(View.VISIBLE);
                            Glide.with(AddMerchantActivity.this).load(URLs.IMGHOST + s)
                                    .centerCrop()
                                    .into(iv3_photo2);//加载图片
                            break;
                        case "tax_registration":
                            tax_registration = s;
                            ll3_up1load3.setVisibility(View.GONE);
                            iv3_photo3.setVisibility(View.VISIBLE);
                            Glide.with(AddMerchantActivity.this).load(URLs.IMGHOST + s)
                                    .centerCrop()
                                    .into(iv3_photo3);//加载图片
                            break;
                    }
                }
            }
        });
    }
}
