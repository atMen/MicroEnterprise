package com.tcrj.micro.activity.zxzp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airsaid.pickerviewlibrary.OptionsPickerView;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.tcrj.micro.ApiConstants;
import com.tcrj.micro.R;
import com.tcrj.micro.activity.LoginActivity;
import com.tcrj.micro.activity.NewsFragment;
import com.tcrj.micro.application.BaseActivity;
import com.tcrj.micro.application.MyApplication;
import com.tcrj.micro.constant.Constant;
import com.tcrj.micro.entity.JlInfo;
import com.tcrj.micro.entity.QyDataInfo;
import com.tcrj.micro.entity.fpStringInfo;
import com.tcrj.micro.entity.grjlInfo;
import com.tcrj.micro.entity.zwCodeList;
import com.tcrj.micro.until.ACache;
import com.tcrj.micro.view.MyTextViewZH;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;
import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import java.util.ArrayList;
import java.util.List;

public class zwItemInfoActivity extends BaseActivity implements View.OnClickListener {


    private MyOkHttp mMyOkhttp;
    private String id;

    private LinearLayout llhyrd;
    private LinearLayout llzcjd;
    private TextView tvhyrd;
    private TextView tvzcjd;
    private View hyrd;
    private View zcjd;

    private LinearLayout ll_zw;
    private LinearLayout ll_gs;

    //职位
    private TextView tv_zw;
    private TextView tv_gsname;
    private TextView tv_xz;
    private TextView tv_dz;
    private TextView tv_gzjy;
    private TextView tv_zdxl;
    private TextView tv_zprs;
    private TextView tv_zwlb;
    private TextView tv_fbrq;

    //企业
    private TextView qyname;
    private TextView gsgm;
    private TextView gsxz;
    private TextView gshy;
    private TextView gszy;
    private TextView gsdz;

    HtmlTextView htmlTextView;
    HtmlTextView htmlGsInfo;
    MyTextViewZH txtTitle;
    private Button btn_td;
    private ImageView btnback;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zw_item_info);

        mMyOkhttp = MyApplication.getInstance().getMyOkHttp();
        Bundle bundle = this.getIntent().getExtras();
        id  = bundle.getString("ID");
        type = getIntent().getStringExtra("type");

        initView();
        getData();
    }

    @Override
    public void initView() {

        ll_zw = findViewById(R.id.ll_zw);
        ll_gs = findViewById(R.id.ll_gs);
        llhyrd = (LinearLayout) findViewById(R.id.ll_hyrd);
        llzcjd = (LinearLayout) findViewById(R.id.ll_zcjd);
        tvhyrd = (TextView) findViewById(R.id.tv_hyrd);
        tvzcjd = (TextView) findViewById(R.id.tv_zcjd);
        hyrd = (View) findViewById(R.id.hyrd);
        zcjd = (View) findViewById(R.id.zcjd);

        //职位
        tv_zw = findViewById(R.id.tv_zw);
        tv_gsname = findViewById(R.id.tv_gsname);
        tv_xz = findViewById(R.id.tv_xz);
        tv_dz = findViewById(R.id.tv_dz);
        tv_gzjy = findViewById(R.id.tv_gzjy);
        tv_zdxl = findViewById(R.id.tv_zdxl);
        tv_zprs = findViewById(R.id.tv_zprs);
        tv_zwlb = findViewById(R.id.tv_zwlb);
        tv_fbrq = findViewById(R.id.tv_fbrq);

        //企业
        qyname = findViewById(R.id.qyname);
        gsgm = findViewById(R.id.gsgm);
        gsxz = findViewById(R.id.gsxz);
        gshy = findViewById(R.id.gshy);
        gszy = findViewById(R.id.gszy);
        gsdz = findViewById(R.id.gsdz);

        llhyrd.setOnClickListener(this);
        llzcjd.setOnClickListener(this);

        btnback = findViewById(R.id.btnback);
        btn_td = findViewById(R.id.btn_td);
        txtTitle = findViewById(R.id.txtTitle);
        htmlTextView = findViewById(R.id.html_text);
        htmlGsInfo = findViewById(R.id.html_gsinfo);

        //消息推送，去掉投递按钮
        if("1".equals(type)){
            btn_td.setVisibility(View.GONE);
        }

        btn_td.setOnClickListener(this);
        btnback.setOnClickListener(this);
        txtTitle.setText("职位详情");

    }

    private QyDataInfo response;
    @Override
    public void getData() {
        JSONObject jsonObject = new JSONObject();
        try {

            jsonObject.put("jobId", id);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        mMyOkhttp.post()
                .url(ApiConstants.jobDetail_Api)
                .jsonParams(jsonObject.toString())
                .enqueue(new GsonResponseHandler<fpStringInfo>() {
                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                    }

                    @Override
                    public void onSuccess(int statusCode, fpStringInfo data) {



                        if("9999".equals(data.getErrorcode())) {

                            response = new Gson().fromJson(data.getData(), QyDataInfo.class);
                            setUserInfo(response);

                        }

                    }
                });

    }

    /**
     * ageRequire : 不限
     * auditStatus : 1
     * createTime : 2019-03-20 09:39:49
     * educationBackgroundRequire : 本科
     * enterpriseId : ff8080816488a9e1016488ae7a450003
     * enterpriseName : 陕西蓝马广告实业有限公司
     * enterpriseRecruitId : ff8080816998acd5016998bc79fe0002
     * hasLike :
     * hasSend :
     * id : ff8080816998acd5016998c128bb0003
     * industry : 10408
     * jobCity : 3abb1c09ea4d4da29e0e431ad2d6f76c
     * jobCityName :
     * jobCounty : d24954eed19b403aaebbfc20a8a6c039
     * jobCountyName :
     * jobName : .NET开发工程师
     * jobTimeRequire : 2
     * lastHandleTime : 2019-03-20 09:49:43
     * optime : 2019-03-20 09:39:04
     * publishStatus : 1
     * publishTime : -1
     * recruitNum : 1
     * remainingTime : -1
     * requireDeatil : <p><span style="color: rgb(51, 51, 51);font-family: &#39;microsoft yahei&#39;;font-size: 14px;background-color: rgb(255, 255, 255)">岗位职责：</span><br style="color: rgb(51, 51, 51);font-family: &#39;microsoft yahei&#39;;font-size: 14px;white-space: normal;background-color: rgb(255, 255, 255)"/><span style="color: rgb(51, 51, 51);font-family: &#39;microsoft yahei&#39;;font-size: 14px;background-color: rgb(255, 255, 255)">1、贯彻本部门岗位责任制，密切与公司其他部门的工作关系，加强与公司各部门的配合，遵循公司开发流程做好软件开发的服务工作；&nbsp;</span><br style="color: rgb(51, 51, 51);font-family: &#39;microsoft yahei&#39;;font-size: 14px;white-space: normal;background-color: rgb(255, 255, 255)"/><span style="color: rgb(51, 51, 51);font-family: &#39;microsoft yahei&#39;;font-size: 14px;background-color: rgb(255, 255, 255)">2、负责公司产品的设计、开发与维护；&nbsp;</span><br style="color: rgb(51, 51, 51);font-family: &#39;microsoft yahei&#39;;font-size: 14px;white-space: normal;background-color: rgb(255, 255, 255)"/><span style="color: rgb(51, 51, 51);font-family: &#39;microsoft yahei&#39;;font-size: 14px;background-color: rgb(255, 255, 255)">3、负责产品文档、软件版本等管理；&nbsp;</span><br style="color: rgb(51, 51, 51);font-family: &#39;microsoft yahei&#39;;font-size: 14px;white-space: normal;background-color: rgb(255, 255, 255)"/><span style="color: rgb(51, 51, 51);font-family: &#39;microsoft yahei&#39;;font-size: 14px;background-color: rgb(255, 255, 255)">4、参与部门建设，制作与完善相关制度，并严格遵守公司相关制度和部门制度；&nbsp;</span><br style="color: rgb(51, 51, 51);font-family: &#39;microsoft yahei&#39;;font-size: 14px;white-space: normal;background-color: rgb(255, 255, 255)"/><span style="color: rgb(51, 51, 51);font-family: &#39;microsoft yahei&#39;;font-size: 14px;background-color: rgb(255, 255, 255)">5、负责完成上级主管安排的其它工作。&nbsp;</span><br style="color: rgb(51, 51, 51);font-family: &#39;microsoft yahei&#39;;font-size: 14px;white-space: normal;background-color: rgb(255, 255, 255)"/><span style="color: rgb(51, 51, 51);font-family: &#39;microsoft yahei&#39;;font-size: 14px;background-color: rgb(255, 255, 255)">任职要求：&nbsp;</span><br style="color: rgb(51, 51, 51);font-family: &#39;microsoft yahei&#39;;font-size: 14px;white-space: normal;background-color: rgb(255, 255, 255)"/><span style="color: rgb(51, 51, 51);font-family: &#39;microsoft yahei&#39;;font-size: 14px;background-color: rgb(255, 255, 255)">1、统招本科及以上学历，计算机相关专业，熟悉.NET3.5以上平台，并有两年以上B/S架构开发经验。</span><br style="color: rgb(51, 51, 51);font-family: &#39;microsoft yahei&#39;;font-size: 14px;white-space: normal;background-color: rgb(255, 255, 255)"/><span style="color: rgb(51, 51, 51);font-family: &#39;microsoft yahei&#39;;font-size: 14px;background-color: rgb(255, 255, 255)">2、熟悉Javascript、AJAX、jquery、CSS、HTML/XML等相关WEB技术；了解bootstrap、jQuery UI、easyui等框架。</span><br style="color: rgb(51, 51, 51);font-family: &#39;microsoft yahei&#39;;font-size: 14px;white-space: normal;background-color: rgb(255, 255, 255)"/><span style="color: rgb(51, 51, 51);font-family: &#39;microsoft yahei&#39;;font-size: 14px;background-color: rgb(255, 255, 255)">3、熟悉C#, ASP.NET MVC、EF、WCF、LINQ、Web Form；</span><br style="color: rgb(51, 51, 51);font-family: &#39;microsoft yahei&#39;;font-size: 14px;white-space: normal;background-color: rgb(255, 255, 255)"/><span style="color: rgb(51, 51, 51);font-family: &#39;microsoft yahei&#39;;font-size: 14px;background-color: rgb(255, 255, 255)">4、能够熟练应用SQL Server进行开发，有较强的 SQL脚本编写、调优能力；</span><br style="color: rgb(51, 51, 51);font-family: &#39;microsoft yahei&#39;;font-size: 14px;white-space: normal;background-color: rgb(255, 255, 255)"/><span style="color: rgb(51, 51, 51);font-family: &#39;microsoft yahei&#39;;font-size: 14px;background-color: rgb(255, 255, 255)">5、熟练使用IIS、SVN、PD、EA等开发工具；</span><br style="color: rgb(51, 51, 51);font-family: &#39;microsoft yahei&#39;;font-size: 14px;white-space: normal;background-color: rgb(255, 255, 255)"/><span style="color: rgb(51, 51, 51);font-family: &#39;microsoft yahei&#39;;font-size: 14px;background-color: rgb(255, 255, 255)">6、曾参与开发过业务管理类软件、行政审批类软件，了解工作流相关知识。</span><br style="color: rgb(51, 51, 51);font-family: &#39;microsoft yahei&#39;;font-size: 14px;white-space: normal;background-color: rgb(255, 255, 255)"/><span style="color: rgb(51, 51, 51);font-family: &#39;microsoft yahei&#39;;font-size: 14px;background-color: rgb(255, 255, 255)">7、理解能力好，逻辑能力强，能够有较强的学习能力和独立解决问题能力。</span></p><p><br/></p>
     * salarRange : 8K-10K
     * sexRequire : 不限
     * targetIndustry : 软件工程师
     * targetIndustryId : 107040102
     */
    private void setUserInfo(QyDataInfo response) {

        QyDataInfo.EnterpriseBean enterprise = response.getEnterprise();
        QyDataInfo.JobBean job = response.getJob();

        //职位
        if(job != null){
            tv_zw.setText(job.getJobName());
            tv_gsname.setText(job.getEnterpriseName());
            tv_xz.setText("职位月薪："+job.getSalarRange());
            tv_dz.setText("工作地点："+job.getJobCityName()+"-"+job.getJobCountyName());
            tv_gzjy.setText("工作经验："+job.getJobTimeRequire());
            tv_zdxl.setText("最低学历："+job.getEducationBackgroundRequire());
            tv_zprs.setText("招聘人数："+job.getRecruitNum());
            tv_zwlb.setText("职位类别："+job.getTargetIndustry());

            String createTime = job.getCreateTime();
            if(createTime != null && !"".equals(createTime)){
                tv_fbrq.setText("发布日期："+job.getCreateTime().substring(0,10));
            }else {
                tv_fbrq.setText("发布日期：");
            }

            htmlTextView.setHtml(response.getJob().getRequireDeatil(),
                    new HtmlHttpImageGetter(htmlTextView));
        }

        if(enterprise != null){
            //        企业
            qyname.setText(enterprise.getEnterpriseName());
            gsgm.setText(enterprise.getEnterpriseScale()+"人");
            gsxz.setText(enterprise.getEnterpriseType());
            gshy.setText(enterprise.getEnterpriseIndustryName());
            gszy.setText(enterprise.getEnterpriseSite());
            gsdz.setText(enterprise.getAddress());

            htmlGsInfo.setHtml(response.getEnterprise().getEnterpriseDescribe(),
                    new HtmlHttpImageGetter(htmlGsInfo));
        }


        /**
         * address : 西安市高新区锦业一路与丈八四路十字嘉昱大厦A座13F (邮编：710065)
         * enterpriseDescribe : <p><span style="color: rgb(51, 51, 51); font-family: &quot;microsoft yahei&quot;; font-size: 14px; background-color: rgb(255, 255, 255);">弥尔，是致力于面对全国、走向国际，集设计、研发、生产、销售为一体的高新技术集团化企业，总部设于北京市海淀区永定路24号弥尔大厦。软件研发是弥尔其中的一项重要产业。</span><br style="color: rgb(51, 51, 51); font-family: &quot;microsoft yahei&quot;; font-size: 14px; white-space: normal; background-color: rgb(255, 255, 255);"/><span style="color: rgb(51, 51, 51); font-family: &quot;microsoft yahei&quot;; font-size: 14px; background-color: rgb(255, 255, 255);">弥尔?天诚——陕西天诚软件有限公司，是专注于空间业务集成管理软件研发的高新技术企业，是弥尔重要的产业研发基地之一。公司起步于2007年，2011年正式注册登记，位于西安市高新技术开发区，拥有集办公、食宿、健身、停车为一体的综合办公大楼，及技术研发、咨询服务、销售管理为专业的精英团队。</span><br style="color: rgb(51, 51, 51); font-family: &quot;microsoft yahei&quot;; font-size: 14px; white-space: normal; background-color: rgb(255, 255, 255);"/><span style="color: rgb(51, 51, 51); font-family: &quot;microsoft yahei&quot;; font-size: 14px; background-color: rgb(255, 255, 255);">公司自成立以来，企业建设和科技创新齐头并进，成绩斐然，先后荣获百余项计算机软件著作权及专利证书，并通过CMMI五级认证、ISO9001质量管理体系认证、ISO27001信息安全管理体系认证、计算机信息系统集成、涉密信息系统集成等多项资质认证，被评为国家高新技术企业、“双软”认证企业。</span><br style="color: rgb(51, 51, 51); font-family: &quot;microsoft yahei&quot;; font-size: 14px; white-space: normal; background-color: rgb(255, 255, 255);"/><span style="color: rgb(51, 51, 51); font-family: &quot;microsoft yahei&quot;; font-size: 14px; background-color: rgb(255, 255, 255);">公司秉承“责任、规范、创新、匠心、感恩、分享”的企业核心文化，“热情精准、无微不至”的服务理念，为众多的政府部门、事业单位和各类企业，提供业务管理系统、个性化定制服务的研发，让用户充分享受及时、高效、便捷的工作服务。</span><br style="color: rgb(51, 51, 51); font-family: &quot;microsoft yahei&quot;; font-size: 14px; white-space: normal; background-color: rgb(255, 255, 255);"/><span style="color: rgb(51, 51, 51); font-family: &quot;microsoft yahei&quot;; font-size: 14px; background-color: rgb(255, 255, 255);">近年来，随着国际互联网技术的飞速发展，以及建设智慧城市脚步的加快。弥尔积极调整旗下产业结构，优化管理架构体系，加快战略发展步伐，形成了以北京总部为技术创新中心，西安为产业研发基地，上海为对外贸易窗口，西北、西南、华中、华南、东北等地为销售网点，覆盖全国的产业大格局；同时，随着“一带一路”国家战略构想的实施，弥尔?天诚紧跟时代步伐，稳步拓展海外事业，基本实现了弥尔?天诚“挺进中南亚，筑梦一带一路”的发展目标；完成了以点带面、点面结合、示范带动、整体推进的发展布局，为最终打造民族科技品牌“中国?弥尔”夯实了基础。</span></p>
         * enterpriseId : ff8080816488a9e1016488ae7a450003
         * enterpriseIndustry : 10401
         * enterpriseName : 陕西蓝马广告实业有限公司
         * enterpriseScale : 150
         * enterpriseSite : http://www.tcrj.com.cn
         * enterpriseType : 民营
         * id : ff8080816998acd5016998bc79fe0002
         * logo : /uploadfile//2019-03-20/20190320093402091.png
         * optime : 2019-03-20 09:34:04
         */



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btn_td:

                String token = ACache.get(this).getAsString("token");

                if(token != null){
                    //弹出简历选择框
                    getCountList(token);
                }else {
                    toClass(this, LoginActivity.class,null);
                }


                break;

            case R.id.btnback:
                finish();
                break;
            case R.id.ll_hyrd:
                hyrd.setVisibility(View.VISIBLE);
                tvhyrd.setTextColor(getResources().getColor(R.color.app_blue));

                zcjd.setVisibility(View.GONE);
                tvzcjd.setTextColor(getResources().getColor(R.color.black));

                //显示职位信息
                ll_zw.setVisibility(View.VISIBLE);
                ll_gs.setVisibility(View.GONE);
                break;

            case R.id.ll_zcjd:
                hyrd.setVisibility(View.GONE);
                tvhyrd.setTextColor(getResources().getColor(R.color.black));

                zcjd.setVisibility(View.VISIBLE);
                tvzcjd.setTextColor(getResources().getColor(R.color.app_blue));

                //显示公司详情
                ll_zw.setVisibility(View.GONE);
                ll_gs.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }
    private void getCountList(final String token){

        showProgressDialog();

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("token", token);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        mMyOkhttp.post()
                .url(ApiConstants.jl_Api)
                .jsonParams(jsonObject.toString())
                .enqueue(new GsonResponseHandler<fpStringInfo>() {
                    @Override
                    public void onFailure(int statusCode, String error_msg) {
                        dismisProgressDialog();
                    }

                    @Override
                    public void onSuccess(int statusCode, fpStringInfo response) {
                        dismisProgressDialog();

                        String errorcode = response.getErrorcode();
                        if("9999".equals(errorcode)){
                            String fpjlListInfos = response.getData();
                            List<grjlInfo> zcgsinfo = parseNoHeaderJArray(fpjlListInfos);

                            if(zcgsinfo.size() <= 0){

                                Toast.makeText(zwItemInfoActivity.this, "未发现简历信息", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            showjldialog(zcgsinfo,token);
                        }else if("204".equals(errorcode)){
                            toClass(zwItemInfoActivity.this, LoginActivity.class,null);
                        }

                    }
                });
    }

    OptionsPickerView<String> zwOptionsPickerView;
    private void showjldialog(final List<grjlInfo> zcgsInfo, final String token){

        if(zwOptionsPickerView != null){
            zwOptionsPickerView.show();
        }else {
            zwOptionsPickerView = new OptionsPickerView<>(this);

            zwOptionsPickerView.setTitle("选择简历");
            final ArrayList<String> list = new ArrayList<>();

            for(int i = 0;i < zcgsInfo.size();i++){
                list.add(zcgsInfo.get(i).getResumeName());
            }

            // 设置数据
            zwOptionsPickerView.setPicker(list);
            // 设置选项单位
            zwOptionsPickerView.setOnOptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int option1, int option2, int option3) {

                    String id = zcgsInfo.get(option1).getId();

                    //投递简历
                   send(id,token);

                }
            });
            zwOptionsPickerView.show();

        }
    }

    private void send(String resumeId, String token){

        showProgressDialog();

        JSONObject jsonObject = new JSONObject();

        try {

            jsonObject.put("token", token);
            jsonObject.put("jobId", id);
            jsonObject.put("resumeId", resumeId);

            if(response.getEnterprise() != null){
                jsonObject.put("enterpriseId", response.getEnterprise().getEnterpriseId()); //企业ID
            }else {
                dismisProgressDialog();
                Toast.makeText(this, "没有企业信息，无法投递", Toast.LENGTH_SHORT).show();
                return;
            }

            jsonObject.put("type", "TDXX_ZHAOPIN_PUSH");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        mMyOkhttp.post()
                .url(ApiConstants.sendjllist_Api)
                .jsonParams(jsonObject.toString())
                .enqueue(new GsonResponseHandler<fpStringInfo>() {
                    @Override
                    public void onFailure(int statusCode, String error_msg) {
                        dismisProgressDialog();
                    }

                    @Override
                    public void onSuccess(int statusCode, fpStringInfo response) {
                        dismisProgressDialog();

                        Toast.makeText(zwItemInfoActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
                        String fpjlListInfos = response.getErrorcode();

                        if("9999".equals(fpjlListInfos)){

                        }else if("204".equals(fpjlListInfos)){
                            toClass(zwItemInfoActivity.this, LoginActivity.class,null);
                        }

                    }
                });
    }


    private List<grjlInfo> parseNoHeaderJArray(String strByJson) {

        //Json的解析类对象
        JsonParser parser = new JsonParser();
        //将JSON的String 转成一个JsonArray对象
        JsonArray jsonArray = parser.parse(strByJson).getAsJsonArray();

        Gson gson = new Gson();
        List<grjlInfo> userBeanList = new ArrayList<>();

        //加强for循环遍历JsonArray
        for (JsonElement user : jsonArray) {
            //使用GSON，直接转成Bean对象
            grjlInfo userBean = gson.fromJson(user, grjlInfo.class);
            userBeanList.add(userBean);
        }
        return userBeanList;
    }


}
