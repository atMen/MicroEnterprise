package com.tcrj.micro;

/**
 * desc:
 * author: Will .
 * date: 2017/9/2 .
 */
public class ApiConstants {


  public static final int cityId = 100;
  public static final int countId = 101;
  public static final int qyzp = 102;
  public static final int jpushfp = 103;


  public static final String siteId = "nEFVji";

  public static final String TPXW = "YnEFZv";
  public static final String YWDT = "qQZbUf";
  public static final String ZXWJ1 = "iuy63u";
  public static final String ZXWJ2 = "63YfUj";
  public static final String TZGG = "NzqQNn";
  public static final String LDJH = "na6jAv";
  public static final String ZYHD = "a6r2Ez";
  public static final String MTGZ = "BZjmqi";


  // http://192.168.20.240:8080/  内
  // http://123.139.46.180:8021/ 外

  public static final String URLBASE8080 = "http://192.168.20.240:8080/";
  public static final String URLBASE = "http://192.168.20.240:8181/";
  public static final String BASEIMAGE = "http://192.168.20.240:8080/web.files";


    /**
     * 新闻动态
     */
    public static final String xwdt_tpxwlistApi = URLBASE+"drosin_cms/baseRest/findInfoList";


  /**
   * 新闻动态
   */
  public static final String xwdt_xq_listApi = URLBASE+"drosin_cms/baseRest/findInfoDetails";


  /**
   * sxipept/courseWare/getCourseWareList
   */
  public static final String fwzx_pxlistApi = URLBASE8080+"sxipept/courseWare/getCourseWareList";


  /**
   * /sxipept/aidpoor/getDeptInfoList
   */
  public static final String zcgs_listApi = URLBASE8080+"sxipept/aidpoor/getDeptInfoList";

  /**
   * 借贷信息
   */
  public static final String fwzx_jr_listApi = URLBASE8080+"sxipept/serviceCentre/getProductList";

  /**
   * 银行信息
   */
  public static final String jr_bank_listApi = URLBASE8080+"sxipept/serviceCentre/getBankList";

  /**
   * 法律服务
   */
  public static final String fwzx_fl_listApi = URLBASE8080+"sxipept/serviceCentre/findLawyerInfo";


  /**
   * 栏目获取
   */
  public static final String zcgs_s_listApi = URLBASE+"drosin_cms/baseRest/findCategoryListByPid";

  /**
   * 栏目获取
   */
  public static final String sjfp_tz_listApi = URLBASE8080+"sxipept/aidpoor/getAidPoorStatisticalList";

  /**
   * 扶贫邀请他人推送消息发送
   */
  public static final String sendyqtrApi = URLBASE8080+"sxipept/aidpoor/aidPoorJpushMsg";

  /**
   * 面试邀请推送消息发送
   */
  public static final String sendmsyqApi = URLBASE8080+"sxipept/job/inviteUser";

  /**
   * 邀请他人
   */
//  public static final String yqtr_listApi = URLBASE8080+"sxipept/personnel/findEnterprise";

    /**
     * （新）邀请他人
     */
    public static final String yqtr_listApi = URLBASE8080+"sxipept/aidpoor/findAidUsers";


  //扶贫人员列表
  public static final String fpry_listApi = URLBASE8080+"sxipept/aidpoor/getAidPoorObjectList";

  //扶贫记录列表
  public static final String fpjl_listApi = URLBASE8080+"sxipept/aidpoor/getAidPoorRecordList";

  //我要扶贫
  public static final String wyfp_Api = URLBASE8080+"sxipept/aidpoor/takePartInAidPoor";

  //招聘信息列表
  public static final String zp_list_Api = URLBASE8080+"sxipept/job/searchJob";

  //应聘信息列表
  public static final String yp_list_Api = URLBASE8080+"sxipept/job/receiveResumeList";

  //职位列表
  public static final String zw_Api = URLBASE8080+"sxipept/serviceCentre/getDicListByParentCode";

  //取消扶贫
  public static final String qxfp_Api = URLBASE8080+"sxipept/aidpoor/cancelAidPoor";

  //检验token
  public static final String checktoken_Api = URLBASE8080+"sxipept/personnel/checkToken";

  //获取职位列表
  public static final String zwcode_Api = URLBASE8080+"sxipept/serviceCentre/getDicListByParentCode";

  //获取简历详情
  public static final String resumeDetail_Api = URLBASE8080+"sxipept/job/resumeDetail";

  //获取工作详情
  public static final String jobDetail_Api = URLBASE8080+"sxipept/job/jobDetail";

  //获取简历列表
  public static final String jl_Api = URLBASE8080+"sxipept/job/resumeList";


  //公司审批简历
  public static final String sp_Api = URLBASE8080+"sxipept/job/auditResume";


  //个人投递记录
  public static final String tdjllist_Api = URLBASE8080+"sxipept/job/sendResumeList";

  //个人投递记录详情
  public static final String tdjl_Api = URLBASE8080+"sxipept/job/sendRecruitInfo";

  //个人投递简历
  public static final String sendjllist_Api = URLBASE8080+"sxipept/job/send";


  //图片上传
  public static final String sendpic_Api = URLBASE8080+"sxipept/aidpoor/uploadImg";

  //获取扶贫动态
  public static final String fpdtlist_Api = URLBASE8080+"sxipept/aidpoor/getAidPoorRecordPage";

  //获取金融公司
  public static final String jrztbanklist_Api = URLBASE8080+"sxipept/serviceCentre/getBankList";

  //获取金融案例列表
  public static final String jrztbankallist_Api = URLBASE8080+"sxipept/serviceCentre/getExampleList";

  //获取金融案例详情
  public static final String jrztbankalinfo_Api = URLBASE8080+"sxipept/serviceCentre/getExample";

  //获取金融产品
  public static final String jrztbankcplist_Api = URLBASE8080+"sxipept/serviceCentre/getProductList";

  //获取金融产品
  public static final String jrztbankcpinfo_Api = URLBASE8080+"sxipept/serviceCentre/getProduct";
}
