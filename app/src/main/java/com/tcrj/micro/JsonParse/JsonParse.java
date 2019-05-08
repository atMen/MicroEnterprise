package com.tcrj.micro.JsonParse;

import android.util.Log;

import com.tcrj.micro.entity.CityEntity;
import com.tcrj.micro.entity.EnterpriseEntity;
import com.tcrj.micro.entity.FuchiEntity;
import com.tcrj.micro.entity.InfoEntity;
import com.tcrj.micro.entity.SupportEntity;
import com.tcrj.micro.entity.leftListInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class JsonParse {




    //获取信息列表
    public static ArrayList<InfoEntity> getInfoList(JSONObject jsonObject) {
        ArrayList<InfoEntity> list = new ArrayList<InfoEntity>();
        try {
            JSONArray array = jsonObject.getJSONArray("Listinfo");

            for (int i = 0; i < array.length(); i++) {
                JSONObject value = array.getJSONObject(i);
                InfoEntity entity = new InfoEntity();
                entity.setId(getStringNodeValue(value, "id"));
                entity.setTitle(getStringNodeValue(value, "title"));
                entity.setThumbUrl(getStringNodeValue(value, "thumbUrl"));
                entity.setShowTime(getStringNodeValue(value, "showTime"));
                entity.setTime(getStringNodeValue(value, "time"));
                list.add(entity);
            }
        } catch (Exception e) {
            Log.d("error", e.getMessage());
        }
        return list;
    }

    //获取信息列表
    public static ArrayList<InfoEntity> getJushInfoList(JSONObject jsonObject) {
        ArrayList<InfoEntity> list = new ArrayList<InfoEntity>();
        try {
            JSONArray array = jsonObject.getJSONArray("data");

            for (int i = 0; i < array.length(); i++) {
                JSONObject value = array.getJSONObject(i);
                InfoEntity entity = new InfoEntity();
                entity.setId(getStringNodeValue(value, "id"));
                entity.setTitle(getStringNodeValue(value, "title"));
                entity.setThumbUrl(getStringNodeValue(value, "thumbUrl"));
                entity.setShowTime(getStringNodeValue(value, "showTime"));
                entity.setTime(getStringNodeValue(value, "time"));
                list.add(entity);
            }
        } catch (Exception e) {
            Log.d("error", e.getMessage());
        }
        return list;
    }


    //获取信息列表
    public static ArrayList<leftListInfo> getLeftInfoList(JSONObject jsonObject) {
        ArrayList<leftListInfo> list = new ArrayList<leftListInfo>();
        try {
            JSONArray array = jsonObject.getJSONArray("Listinfo");

            for (int i = 0; i < array.length(); i++) {
                JSONObject value = array.getJSONObject(i);
                leftListInfo entity = new leftListInfo();
                entity.setId(getStringNodeValue(value, "id"));
                entity.setTitle(getStringNodeValue(value, "title"));
                entity.setThumbUrl(getStringNodeValue(value, "thumbUrl"));
                entity.setShowTime(getStringNodeValue(value, "showTime"));
                list.add(entity);
            }
        } catch (Exception e) {
            Log.d("error", e.getMessage());
        }
        return list;
    }


    //获取信息详细信息
    public static InfoEntity getInfoDetail(JSONObject jsonObject) {
        InfoEntity entity = new InfoEntity();
        try {
            JSONObject object = jsonObject.getJSONObject("Entity");
            entity.setId(getStringNodeValue(object, "id"));
            entity.setTitle(getStringNodeValue(object, "title"));
            entity.setSource(getStringNodeValue(object, "source"));
            entity.setShowTime(getStringNodeValue(object, "showTime"));
            entity.setInfoContent(getStringNodeValue(object, "infoContent"));
        } catch (Exception e) {
            Log.d("error", e.getMessage());
        }
        return entity;
    }

    //获城市列表
    public static ArrayList<CityEntity> getCityList(JSONObject jsonObject) {
        ArrayList<CityEntity> list = new ArrayList<CityEntity>();
        try {
            JSONArray array = jsonObject.getJSONArray("Listinfo");

            for (int i = 0; i < array.length(); i++) {
                JSONObject value = array.getJSONObject(i);
                CityEntity entity = new CityEntity();
                entity.setId(getStringNodeValue(value, "id"));
                entity.setName(getStringNodeValue(value, "name"));
                list.add(entity);
            }
        } catch (Exception e) {
            Log.d("error", e.getMessage());
        }
        return list;
    }


    //获取扶持信息列表
    public static ArrayList<FuchiEntity> getFuchiList(JSONObject jsonObject) {
        ArrayList<FuchiEntity> list = new ArrayList<FuchiEntity>();
        try {
            JSONArray array = jsonObject.getJSONArray("Listinfo");

            for (int i = 0; i < array.length(); i++) {
                JSONObject value = array.getJSONObject(i);
                FuchiEntity entity = new FuchiEntity();
                entity.setId(getStringNodeValue(value, "id"));
                entity.setEntName(getStringNodeValue(value, "entName"));
                entity.setShowTime(getStringNodeValue(value, "showTime"));
                list.add(entity);
            }
        } catch (Exception e) {
            Log.d("error", e.getMessage());
        }
        return list;
    }

    //获取扶持详细信息
    public static FuchiEntity getFuchiDetail(JSONObject jsonObject) {
        FuchiEntity entity = new FuchiEntity();
        try {
            JSONObject object = jsonObject.getJSONObject("Entity");
            entity.setId(getStringNodeValue(object, "id"));
            entity.setEntName(getStringNodeValue(object, "entName"));
            entity.setRegNo(getStringNodeValue(object, "regNo"));
            entity.setYiju(getStringNodeValue(object, "yuju"));
            entity.setNeirong(getStringNodeValue(object, "neirong"));
            entity.setShue(getStringNodeValue(object, "shue"));
            entity.setBumenName(getStringNodeValue(object, "bumenName"));
            entity.setSsDate(getStringNodeValue(object, "ssDate"));
        } catch (Exception e) {
            Log.d("error", e.getMessage());
        }
        return entity;
    }

    //获取申请扶持信息列表
    public static ArrayList<SupportEntity> getSupportList(JSONObject jsonObject) {
        ArrayList<SupportEntity> list = new ArrayList<SupportEntity>();
        try {
            JSONArray array = jsonObject.getJSONArray("Listinfo");

            for (int i = 0; i < array.length(); i++) {
                JSONObject value = array.getJSONObject(i);
                SupportEntity entity = new SupportEntity();
                entity.setId(getStringNodeValue(value, "id"));
                entity.setTitle(getStringNodeValue(value, "title"));
                entity.setShowTime(getStringNodeValue(value, "showTime"));
                list.add(entity);
            }
        } catch (Exception e) {
            Log.d("error", e.getMessage());
        }
        return list;
    }

    //获取申请扶持详细信息
    public static SupportEntity getSupportDetail(JSONObject jsonObject) {
        SupportEntity entity = new SupportEntity();
        try {
            JSONObject object = jsonObject.getJSONObject("Entity");
            entity.setId(getStringNodeValue(object, "id"));
            entity.setTitle(getStringNodeValue(object, "title"));
            entity.setMatterGist(getStringNodeValue(object, "matterGist"));
            entity.setGkCondition(getStringNodeValue(object, "gkCondition"));
            entity.setGkProcedure(getStringNodeValue(object, "gkProcedure"));
            entity.setFlow(getStringNodeValue(object, "flow"));
            entity.setTimeLimit(getStringNodeValue(object, "timeLimit"));
            entity.setChargeStandard(getStringNodeValue(object, "chargeStandard"));
        } catch (Exception e) {
            Log.d("error", e.getMessage());
        }
        return entity;
    }

    //获取小微企业列表
    public static ArrayList<EnterpriseEntity> getEnterpriseList(JSONObject jsonObject) {
        ArrayList<EnterpriseEntity> list = new ArrayList<EnterpriseEntity>();
        try {
            JSONArray array = jsonObject.getJSONArray("Listinfo");

            for (int i = 0; i < array.length(); i++) {
                JSONObject value = array.getJSONObject(i);
                EnterpriseEntity entity = new EnterpriseEntity();
                entity.setId(getStringNodeValue(value, "id"));
                entity.setEntName(getStringNodeValue(value, "entName"));
                entity.setRegNo(getStringNodeValue(value, "regNo"));
                entity.setRegCap(getStringNodeValue(value, "regCap"));
                list.add(entity);
            }
        } catch (Exception e) {
            Log.d("error", e.getMessage());
        }
        return list;
    }

    //获取小微企业详细信息
    public static EnterpriseEntity getEnterpriseDetail(JSONObject jsonObject) {
        EnterpriseEntity entity = new EnterpriseEntity();
        try {
            JSONObject object = jsonObject.getJSONObject("Entity");
            entity.setId(getStringNodeValue(object, "id"));
            entity.setEntName(getStringNodeValue(object, "entName"));
            entity.setRegNo(getStringNodeValue(object, "regNo"));
            entity.setTypeName(getStringNodeValue(object, "typeName"));
            entity.setEstDate(getStringNodeValue(object, "estDate"));
            entity.setRegCap(getStringNodeValue(object, "regCap"));
            entity.setBizhong(getStringNodeValue(object, "bizhong"));
            entity.setRegOrgName(getStringNodeValue(object, "regOrgName"));
            entity.setMenleiName(getStringNodeValue(object, "menleiName"));
            entity.setIndustryName(getStringNodeValue(object, "industryName"));
        } catch (Exception e) {
            Log.d("error", e.getMessage());
        }
        return entity;
    }

    /**
     * 返回服务器信息
     *
     * @param array
     * @return
     */
    public static String returnMessage(JSONArray array) {
        String value = "";
        try {
            value = getStringNodeValue(array.getJSONObject(0), "msg");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return value;
    }

    public static String getStringNodeValue(JSONObject o, String name) {
        boolean isHas = o.has(name) && (!o.isNull(name));
        try {
            return isHas ? o.getString(name) : "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getMsgByKey(JSONObject jsonObject, String key) {
        try {
            String result = jsonObject.getString(key);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static JSONObject getObjByKey(JSONObject jsonObject, String key) {
        try {
            JSONObject result = jsonObject.getJSONObject(key);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int getIntNodeValue(JSONObject o, String name) {
        boolean isHas = o.has(name) && (!o.isNull(name));
        try {
            return isHas ? o.getInt(name) : 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static boolean getState(JSONObject jsonObject) {
        int result = getIntNodeValue(jsonObject, "state");
        return result == 1 ? true : false;
    }

    public static JSONObject getResultEntity(JSONObject jsonObject) {
        JSONObject msg = null;
        try {
            msg = jsonObject.getJSONObject("Entity");
        } catch (JSONException e) {
        }
        return msg;
    }

}
