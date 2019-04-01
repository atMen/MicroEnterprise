package com.spring.chanba.bean;

import java.io.Serializable;

public class NoticeDetailEntity implements Serializable {

    /**
     * message : 操作成功
     * data : {"content":"<p sty
     * state : 1
     */

    private String message;
    private DataBean data;
    private int state;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public static class DataBean {
        /**
         * content : <p style="text-align:center"><img src='https://api.madeinchanba.com/web.files/uploadfile//ueditor/jsp/upload/image/20180703/1530596821700024532.jpg'  title="1530596821700024532.jpg" alt="timg (2).jpg"/></p><p style="margin-top: 15px; line-height: 1.75em; text-align: justify; text-indent: 2em;">公募基金中考成绩单已出炉，数据显示，随着余额宝在2个月之内新接入5只货币基金，天弘余额宝规模分流显著，单季度下降2351亿元，降幅达13.92%。</p><p style="margin-top: 15px; line-height: 1.75em; text-align: justify; text-indent: 2em;">自2018年5月初开始，为了分流天弘余额宝因单只基金规模过快增长的压力，并从整体上降低单一货币基金集中度高的风险，提高用户体验，蚂蚁金服开放余额宝，陆续接入5家基金公司的货币基金。</p><p style="margin-top: 15px; line-height: 1.75em; text-align: justify; text-indent: 2em;">到目前为止，已经接入了博时基金、中欧基金、华安基金、国泰基金、景顺长城这5家基金公司的货币基金。</p><p style="margin-top: 15px; line-height: 1.75em; text-align: justify; text-indent: 2em;">数据显示，自余额宝进一步开放以来，天弘余额宝基金规模已经从2018年一季度的16891亿元下降到14540亿元，总规模下降了2351亿元。</p><p style="margin-top: 15px; line-height: 1.75em; text-align: justify; text-indent: 2em;">不过，对于5只新接入余额宝的货币基金来说，规模却迎来了显著增长。</p><p style="margin-top: 15px; line-height: 1.75em; text-align: justify; text-indent: 2em;">数据显示，除了6月下旬才接入余额宝的景顺景益货币A之外，其他基金规模均超过600亿元，其中，博时现金收益A规模最大，达到了1468亿元；华安日日鑫A的规模次之，达到了938亿元；国泰利是宝和中欧滚钱宝A的规模则在700亿元左右。即使扣除这5只货币基金2018年3月底合计的100亿元规模，余额宝给这些货币基金带来的增量在4000亿元左右。</p><p style="margin-top: 15px; line-height: 1.75em; text-align: justify; text-indent: 2em;">市场人士指出，余额宝的用户需求强烈，分流动作既降低了单一货币基金集中度风险，也缓解了用户需求难题。</p><p style="margin-top: 15px; line-height: 1.75em; text-align: justify; text-indent: 2em;">作为一只里程碑式的产品，余额宝业务自2013年上线以来，5年时间收获4亿投资人的青睐，同时也成就了全球规模最大的货币基金产品，到2017年初已经突破1万亿元规模。</p><p style="margin-top: 15px; line-height: 1.75em; text-align: justify; text-indent: 2em;">此前，天弘基金旗下货币基金为余额宝账户惟一对接的基金产品。据天弘基金公布的数据显示，截至2017年底，余额宝的持有人户数为4.74亿人，其中超过99%的持有人是个人投资者，人均持有额度仅为3329.57元。</p><p style="margin-top: 15px; line-height: 1.75em; text-align: justify; text-indent: 2em;">不过，规模增长过快也成为天弘余额宝“成长”的烦恼。</p><p style="margin-top: 15px; line-height: 1.75em; text-align: justify; text-indent: 2em;">自2017年5月起，天弘基金开启了一系列针对余额宝申购及保有额度的主动调整。目前，余额宝个人交易账户持有额度上限为10万元，单日购买额度为2万元。</p><p style="margin-top: 15px; line-height: 1.75em; text-align: justify; text-indent: 2em;">此外，自2018年2月1日起，余额宝设置每日申购总量，即单日实际申购达到设定额度时，当日不再受理申购申请，每日申购额度根据基金申购、赎回情况动态设定，实施期限根据基金运行情况进行阶段性调整。</p><p style="margin-top: 15px; line-height: 1.75em; text-align: justify; text-indent: 2em;">伴随着余额宝的持续“瘦身”，抢购每日限额变得越来越难。不过，接入5只新的货币基金后，每日“半小时”秒抢的局面正式终结。</p><p style="margin-top: 15px; line-height: 1.75em; text-align: justify; text-indent: 2em;">基金业内人士评价道，“分流”之后，用户购买余额宝再也没有了限额“抢购”的苦恼。而余额宝由于接入更多基金，整体风险更加可控，运行也会更加稳健。</p><p><br/></p>
         * pubTime : 2018-07-03
         * id : 01838e7a286842c08995757e7206ee4a
         * author : 浐灞智造
         * title : 余额宝集中接入五只基金后，天弘余额宝规模下降13.92%
         * astitle : 自2018年5月初开始，为了分流天弘余额宝因单只基金规模过快增长的压力，并从整体上降低单一货币基金集中度高的风险，提高用户体验，蚂蚁金服开放余额宝，陆续接入5家基金公司的货币基金
         */

        private String content;
        private String pubTime;
        private String id;
        private String author;
        private String title;
        private String astitle;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getPubTime() {
            return pubTime;
        }

        public void setPubTime(String pubTime) {
            this.pubTime = pubTime;
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

        public String getAstitle() {
            return astitle;
        }

        public void setAstitle(String astitle) {
            this.astitle = astitle;
        }
    }
}
