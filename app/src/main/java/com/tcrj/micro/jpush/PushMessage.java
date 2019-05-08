package com.tcrj.micro.jpush;

/**
 * @author vision
 * @function 极光推送消息实体，包含所有的数据字段。
 */
public class PushMessage extends BaseModel {



    // 消息类型
    public String messageType = null;
    // 连接
    public String messageUrl = null;
    // 详情内容
    public String messageContent = null;


    public String getResumeId() {
        return resumeId;
    }

    public void setResumeId(String resumeId) {
        this.resumeId = resumeId;
    }

    /**
     * id : quEfQn
     * type : CATEGORY_INFO_PUSH
     */

    private String resumeId;
    private String id;
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
