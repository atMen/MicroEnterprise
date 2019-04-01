package com.spring.chanba.utils;

import com.spring.chanba.bean.AptitudeDetailEntity;
import com.spring.chanba.bean.BannerListEntity;
import com.spring.chanba.bean.DictionaryTypeEntity;
import com.spring.chanba.bean.FinanceServiceDetailEntity;
import com.spring.chanba.bean.HandleEntity;
import com.spring.chanba.bean.InformationEntity;
import com.spring.chanba.bean.LawServiceDetailEntity;
import com.spring.chanba.bean.LawServiceGridEntity;
import com.spring.chanba.bean.LawServiceListEntity;
import com.spring.chanba.bean.NoticeBannerEntity;
import com.spring.chanba.bean.NoticeDetailEntity;
import com.spring.chanba.bean.ProjectApplyEntity;
import com.spring.chanba.bean.PromptlyConsultEntity;
import com.spring.chanba.bean.ServiceApplyListEntity;
import com.spring.chanba.bean.ServiceMenuEntity;
import com.spring.chanba.bean.TalentLoanListEntity;
import com.spring.chanba.bean.TrainEnrolmentEntity;
import com.spring.chanba.bean.TrainServiceDetailEntity;
import com.spring.chanba.bean.UserInfoEntity;
import com.spring.chanba.bean.WeChatAccessEntity;
import com.spring.chanba.bean.WeChatUserInfoEntity;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 接口类
 */
public interface ApiService {
    /**
     * 微信-通过code获取access_token
     *
     * @param
     * @return
     */
    @GET("https://api.weixin.qq.com/sns/oauth2/access_token")
    Call<WeChatAccessEntity> access_token(@Query("appid") String appid, @Query("secret") String secret, @Query("code") String code, @Query("grant_type") String grant_type);

    /**
     * 微信-获取用户个人信息
     *
     * @param
     * @return
     */
    @GET("https://api.weixin.qq.com/sns/userinfo")
    Call<WeChatUserInfoEntity> userinfo(@Query("access_token") String access_token, @Query("openid") String openid, @Query("lang") String lang);

    /**
     * 登录
     *
     * @param map
     * @return
     */
    @POST("login")
    Call<UserInfoEntity> getLogin(@Body HashMap<String, String> map);

    /**
     * 资讯
     *
     * @param map
     * @return
     */
    @POST("getCmsList")
    Call<InformationEntity> getInfomationList(@Body HashMap<String, String> map);

    /**
     * 法律知识:详情
     *
     * @param map
     * @return
     */
    @POST("getLawInfoDetail")
    Call<LawServiceDetailEntity> getLawInfoDetail(@Body HashMap<String, String> map);

    /**
     * 法律案例：详情
     *
     * @param map
     * @return
     */
    @POST("getLawCaseDetail")
    Call<LawServiceDetailEntity> getLawCaseDetail(@Body HashMap<String, String> map);

    /**
     * 创业孵化知识/案例详情
     *
     * @param map
     * @return
     */
    @POST("getHatchDetail")
    Call<LawServiceDetailEntity> getHatchDetail(@Body HashMap<String, String> map);

    /**
     * 项目详情
     *
     * @param map
     * @return
     */
    @POST("getProjectServerDetail")
    Call<LawServiceDetailEntity> getProjectServerDetail(@Body HashMap<String, String> map);

    /**
     * 申请项目
     *
     * @param map
     * @return
     */
    @POST("applyProjectServer")
    Call<HandleEntity> getApplyProjectServer(@Body HashMap<String, String> map);

    /**
     * 立即咨询
     *
     * @param map
     * @return
     */
    @POST("getLawConsultList")
    Call<PromptlyConsultEntity> getLawConsultList(@Body HashMap<String, String> map);

    /**
     * 查询企业信息详情
     *
     * @param map
     * @return
     */
    @POST("getCompanyInfoDetail")
    Call<ResponseBody> getCompanyInfoDetail(@Body HashMap<String, String> map);

    /**
     * 提交咨询
     *
     * @param map
     * @return
     */
    @POST("lawConsult")
    Call<HandleEntity> getLawConsult(@Body HashMap<String, String> map);

    /**
     * 法律案例
     *
     * @param map
     * @return
     */
    @POST("getLawCaseList")
    Call<LawServiceGridEntity> getLawCaseList(@Body HashMap<String, String> map);

    /**
     * 轮播图
     *
     * @param map
     * @return
     */
    @POST("getCmsList")
    Call<BannerListEntity> getCmsList(@Body HashMap<String, String> map);

    /**
     * 工商服务：菜单
     *
     * @param map
     * @return
     */
    @POST("getServerList")
    Call<ServiceMenuEntity> getServerList(@Body HashMap<String, String> map);

    /**
     * 工商知识/经典案例
     *
     * @param map
     * @return
     */
    @POST("getBusinessList")
    Call<LawServiceListEntity> getBusinessList(@Body HashMap<String, String> map);

    /**
     * 金融案例
     *
     * @param map
     * @return
     */
    @POST("getFinanceCaseList")
    Call<LawServiceListEntity> getFinanceCaseList(@Body HashMap<String, String> map);

    /**
     * 金融知识
     *
     * @param map
     * @return
     */
    @POST("getFinanceInfoList")
    Call<LawServiceListEntity> getFinanceInfoList(@Body HashMap<String, String> map);

    /**
     * 财税知识
     *
     * @param map
     * @return
     */
    @POST("getFiscalInfoList")
    Call<LawServiceListEntity> getFiscalInfoList(@Body HashMap<String, String> map);

    /**
     * 财税案例
     *
     * @param map
     * @return
     */
    @POST("getCaseList")
    Call<LawServiceListEntity> getCaseList(@Body HashMap<String, String> map);

    /**
     * 法律知识
     *
     * @param map
     * @return
     */
    @POST("getLawInfoList")
    Call<LawServiceListEntity> getLawInfoList(@Body HashMap<String, String> map);

    /**
     * 企业认证
     *
     * @param map
     * @return
     */
    @POST("saveCompanyInfo")
    Call<HandleEntity> getSaveCompanyInfo(@Body HashMap<String, String> map);

    /**
     * 创业孵化
     *
     * @param map
     * @return
     */
    @POST("getHatchList")
    Call<LawServiceListEntity> getHatchList(@Body HashMap<String, String> map);

    /**
     * 培训服务
     *
     * @param map
     * @return
     */
    @POST("getTrainingList")
    Call<LawServiceListEntity> getTrainingList(@Body HashMap<String, String> map);

    /**
     * 培训详情
     *
     * @param map
     * @return
     */
    @POST("getTrainingDetail")
    Call<TrainServiceDetailEntity> getTrainingDetail(@Body HashMap<String, String> map);

    /**
     * 报名申请
     *
     * @param map
     * @return
     */
    @POST("applyTraining")
    Call<HandleEntity> getApplyTraining(@Body HashMap<String, String> map);

    /**
     * 判断会员基本信息是否完善 完善返回bean未完善返回0
     *
     * @param map
     * @return
     */
    @POST("isMemBasicInfoCompleted")
    Call<ResponseBody> isMemBasicInfoCompleted(@Body HashMap<String, String> map);

    /**
     * 项目列表
     *
     * @param map
     * @return
     */
    @POST("getProjectServerList")
    Call<LawServiceListEntity> getProjectServerList(@Body HashMap<String, String> map);

    /**
     * 分类
     *
     * @param map
     * @return
     */
    @POST("getDictionaryList")
    Call<DictionaryTypeEntity> getDictionaryList(@Body HashMap<String, String> map);

    /**
     * 编辑个人信息
     *
     * @param map
     * @return
     */
    @POST("editMemberBasicInfo")
    Call<HandleEntity> getEditMemberBasicInfo(@Body HashMap<String, String> map);

    /**
     * 轮播
     *
     * @param map
     * @return
     */
    @POST("index")
    Call<NoticeBannerEntity> getIndexList(@Body HashMap<String, String> map);

    /**
     * 申请产品
     *
     * @param map
     * @return
     */
    @POST("applyBankProduct")
    Call<HandleEntity> getApplyBankProduct(@Body HashMap<String, String> map);

    /**
     * 申请产品列表
     *
     * @param map
     * @return
     */
    @POST("applyBankProductList")
    Call<TalentLoanListEntity> getApplyBankProductList(@Body HashMap<String, String> map);

    /**
     * 服务申请
     *
     * @param map
     * @return
     */
    @POST("getApplyServerList")
    Call<ServiceApplyListEntity> getApplyServerList(@Body HashMap<String, String> map);

    /**
     * @param map
     * @return
     */
    @POST("getServerDetail")
    Call<AptitudeDetailEntity> getServerDetail(@Body HashMap<String, String> map);

    /**
     * 服务申请
     *
     * @param map
     * @return
     */
    @POST("applyServer")
    Call<HandleEntity> getApplyServer(@Body HashMap<String, String> map);

    /**
     * 项目申请
     *
     * @param map
     * @return
     */
    @POST("getApplyProjectServerList")
    Call<ProjectApplyEntity> getApplyProjectServerList(@Body HashMap<String, String> map);

    /**
     * 财税案例：详情
     *
     * @param map
     * @return
     */
    @POST("getCaseDetail")
    Call<FinanceServiceDetailEntity> getCaseDetail(@Body HashMap<String, String> map);

    /**
     * 工商服务：详情
     *
     * @param map
     * @return
     */
    @POST("getBusinessDetail")
    Call<FinanceServiceDetailEntity> getBusinessDetail(@Body HashMap<String, String> map);

    /**
     * 财税知识：详情
     *
     * @param map
     * @return
     */
    @POST("getFiscalInfoDetail")
    Call<FinanceServiceDetailEntity> getFiscalInfoDetail(@Body HashMap<String, String> map);

    /**
     * 金融案例：详情
     *
     * @param map
     * @return
     */
    @POST("getFinanceCaseDetail")
    Call<FinanceServiceDetailEntity> getFinanceCaseDetail(@Body HashMap<String, String> map);

    /**
     * 金融：金融知识
     *
     * @param map
     * @return
     */
    @POST("getFinanceInfoDetail")
    Call<FinanceServiceDetailEntity> getFinanceInfoDetail(@Body HashMap<String, String> map);

    /**
     * 培训报名
     *
     * @param map
     * @return
     */
    @POST("getApplyTrainingList")
    Call<TrainEnrolmentEntity> getApplyTrainingList(@Body HashMap<String, String> map);

    /**
     * 通知详情
     *
     * @param map
     * @return
     */
    @POST("getCmsDetail")
    Call<NoticeDetailEntity> getCmsDetails(@Body HashMap<String, String> map);
}
