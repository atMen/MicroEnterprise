package com.spring.chanba.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 法律案例
 */
public class LawServiceGridEntity implements Serializable {

    /**
     * message : 操作成功
     * data : [{"picPath":"https://api.madeinchanba.com/web.files/2018-08-24/201808241644261535100266401_h5g1u.jpg","content":"<p style=\"text-indent: 2em;\"><span style=\"text-indent: 2em;\">2015年11月14日，福银高速陕西咸阳段发生重大交通事故，造成十余车辆连撞，一人遇难，多人受伤。<\/span><br/><\/p><p style=\"text-indent: 2em;\">事故发生后，陕西泾渭律师事务所接受中国东方航空股份有限公司西北分公司及数名受害飞行员、空乘员的委托，作为刑事附带民事诉讼原告人的诉讼代理人，以维护其合法权益。<\/p><p style=\"text-indent: 2em;\">陕西泾渭律师事务所接受委托后，指派任青娜、罗飞、雷俊华律师组成律师团队，作为一、二审刑事附带民事诉讼的诉讼代理人参诉。<br/><\/p><p style=\"text-indent: 2em;\">该起案件涉案当事人多达26名，案件程序复杂，经过近两年的审理，该案于2017年3月作出生效判决，陕西泾渭律师事务所受托刑事附带民事诉讼原告人的权益得到了最大限度的维护。<br/><\/p><p><br/><\/p>","id":"2c9f4a87656ad5b401656b14782c0002","author":"廖群律师","title":"案件当事人多达26名 泾渭律师成功维权","ftitle":"民事纠纷","digest":"","addTime":"2018-09-24"},{"picPath":"https://api.madeinchanba.com/web.files/2018-08-24/201808241644261535100266401_h5g1u.jpg","content":"<p style=\"text-indent: 2em; margin-top: 5px;\"><span style=\"font-family: 宋体;\">胜诉判决难执行，令许多案件胜诉一方的当事人非常无奈。陕西泾渭律师事务所律师任青娜、罗飞律师在为当事人成功维权后，又帮助当事人成功执行全部款项。<\/span><\/p><p style=\"text-indent: 2em;\">2014年12月6日，华商报报道的《跟团旅游时参加自费项目，57岁女游客车祸受伤》中的受害人委托陕西泾渭律师事务所任青娜、罗飞律师诉讼维权。<\/p><p style=\"text-indent: 2em;\">2016年11月17日，西安市雁塔区人民法院审结此案，判决两名被告连带向受害人李某支付赔偿金47万余元。判决生效后，两被告拒不履行判决内容，该受害人再次委托陕西泾渭律师事务所任青娜、罗飞律师代理执行程序。<\/p><p style=\"text-indent: 2em;\">因两被告均位于内蒙古自治区额济纳旗，执行难度较大，陕西泾渭律师事务所任青娜、罗飞律师通过最高人民法院的裁判文书公示记录、国家企业信用信息公示系统等多种信息途径，协助西安市雁塔区人民法院锁定了两被告名下的资产。雁塔区人民法院执行局法官第一时间予以冻结，并快速前往内蒙古自治区额济纳旗办理执行划转，最终成功执行全部赔偿款，切实维护了当事人的合法权益。<\/p><p><br/><\/p>","id":"2c9f4a87656ad5b401656b0325640001","author":"寥群律师","title":"泾渭律师任青娜、罗飞成功化解执行难 为受害人追回47万赔偿金","ftitle":"民事纠纷","digest":"","addTime":"2018-09-24"}]
     * state : 1
     * subData : 2
     */

    private String message;
    private int state;
    private int subData;
    private List<DataBean> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getSubData() {
        return subData;
    }

    public void setSubData(int subData) {
        this.subData = subData;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * picPath : https://api.madeinchanba.com/web.files/2018-08-24/201808241644261535100266401_h5g1u.jpg
         * content : <p style="text-indent: 2em;"><span style="text-indent: 2em;">2015年11月14日，福银高速陕西咸阳段发生重大交通事故，造成十余车辆连撞，一人遇难，多人受伤。</span><br/></p><p style="text-indent: 2em;">事故发生后，陕西泾渭律师事务所接受中国东方航空股份有限公司西北分公司及数名受害飞行员、空乘员的委托，作为刑事附带民事诉讼原告人的诉讼代理人，以维护其合法权益。</p><p style="text-indent: 2em;">陕西泾渭律师事务所接受委托后，指派任青娜、罗飞、雷俊华律师组成律师团队，作为一、二审刑事附带民事诉讼的诉讼代理人参诉。<br/></p><p style="text-indent: 2em;">该起案件涉案当事人多达26名，案件程序复杂，经过近两年的审理，该案于2017年3月作出生效判决，陕西泾渭律师事务所受托刑事附带民事诉讼原告人的权益得到了最大限度的维护。<br/></p><p><br/></p>
         * id : 2c9f4a87656ad5b401656b14782c0002
         * author : 廖群律师
         * title : 案件当事人多达26名 泾渭律师成功维权
         * ftitle : 民事纠纷
         * digest :
         * addTime : 2018-09-24
         */

        private String picPath;
        private String content;
        private String id;
        private String author;
        private String title;
        private String ftitle;
        private String digest;
        private String addTime;

        public String getPicPath() {
            return picPath;
        }

        public void setPicPath(String picPath) {
            this.picPath = picPath;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getFtitle() {
            return ftitle;
        }

        public void setFtitle(String ftitle) {
            this.ftitle = ftitle;
        }

        public String getDigest() {
            return digest;
        }

        public void setDigest(String digest) {
            this.digest = digest;
        }

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }
    }
}
