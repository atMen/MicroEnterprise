package com.tcrj.micro.constant;

public class Constant {
	//public static String URLROOT = "http://192.168.20.57:8080/xwqy";
//	http://123.139.46.180:8020/drosin_cms/baseRest/getHotInfoList



	public static String URLROOT = "http://123.139.46.180:8020/drosin_cms";

	public static String OLDURLROOT = "http://111.20.61.194:8080/xwqy";

	public static String BASEURL = "http://123.139.46.180:8021";

	//获取信息列表
	public static String findInfoList = URLROOT + "/baseRest/findInfoList";

	//获取信息详情
	public static String findInfoDetails = URLROOT + "/baseRest/findInfoDetails";

	//获取城市列表
	public static String getNodeByParentId = URLROOT + "/baseRest/getNodeByParentId";

	//（新）获取城市列表
	public static String getNewNodeByParentId = URLROOT + "/baseRest/findCategoryListByPid";

	//全站搜索
	public static String searchAll = URLROOT + "/baseRest/searchAll";

	//扶持信息列表
	public static String findXwqyXsfcList = URLROOT + "/baseRest/findXwqyXsfcList";

	//扶持信息详情
	public static String findXwqyXsfcInfoDetails = URLROOT + "/baseRest/findXwqyXsfcInfoDetails";

	//申请扶持列表
	public static String findGkInfoList = URLROOT + "/baseRest/findGkInfoList";

	//申请扶持详请
	public static String findGkInfoDetails = URLROOT + "/baseRest/findGkInfoDetails";

	//获取首页行业热点
	public static String HotInfoList = URLROOT + "/baseRest/getHotInfoList";

	//获取首页政策推荐
	public static String NewInfoList = URLROOT + "/baseRest/getNewInfoList";

	//小微企业列表
	public static String findXwqyList = OLDURLROOT + "/baseRest/findXwqyList";

	//小微企业详请
	public static String findXwqyInfoDetails = OLDURLROOT + "/baseRest/findXwqyInfoDetails";

	//注册
	public static String registe = BASEURL+"/sxipept/personnel/register";

	//登录
	public static String login = BASEURL+"/sxipept/personnel/login";

	//获取短信验证码
	public static String getdxyzm = BASEURL+"/sxipept/personnel/sendSMSVerifyCode";


	//验证手机号
	public static String yzphone = BASEURL+"/sxipept/personnel/checkVerifyCode";

	//验证手机号
	public static String czpwd = BASEURL+"/sxipept/personnel/resetUserPwd";

}
