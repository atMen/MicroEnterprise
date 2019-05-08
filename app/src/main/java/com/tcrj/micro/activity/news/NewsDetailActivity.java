package com.tcrj.micro.activity.news;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.toolbox.VolleyUtil;
import com.tcrj.micro.JsonParse.JsonParse;
import com.tcrj.micro.R;
import com.tcrj.micro.activity.LeftFragment;
import com.tcrj.micro.adpater.NewsListAdapter;
import com.tcrj.micro.application.BaseActivity;
import com.tcrj.micro.constant.Constant;
import com.tcrj.micro.entity.InfoEntity;
import com.tcrj.micro.until.DateUtil;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Map;

import static com.newui.waterlistview.WaterDropListView.OnClickListener;

public class NewsDetailActivity extends BaseActivity {

    private String id;
    private TextView tvtitle;
    private TextView title;
    private ImageView backBtn;
    private WebView mWebView = null;
    private TextView sub_title;
    private TextView source;
    private TextView number;
    private TextView date;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        initView();
        getData();
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        tvtitle = (TextView) findViewById(R.id.txtTitle);
        backBtn = (ImageView) findViewById(R.id.btnback);
        backBtn.setVisibility(View.VISIBLE);
        tvtitle.setText("要闻内容");
        mWebView = (WebView) findViewById(R.id.webView);
        backBtn.setOnClickListener(new OnClick());

        title = (TextView) findViewById(R.id.title);
        sub_title = (TextView) findViewById(R.id.sub_title);
        source = (TextView) findViewById(R.id.source);
        number = (TextView) findViewById(R.id.number);
        date = (TextView) findViewById(R.id.date);

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
    }

    /**
     * "<html>\n" +
     " <head></head>\n" +
     " <body>\n" +
     " <p style=\"TEXT-ALIGN: center\">" +
     "<img src=\"http://123.139.46.180:8020/web.files/uploadfile/nEFVji/ue/image/20190410/1554859247494038700.jpg\" width=\"100%\" height=\"auto\"></p><br>\n" +
     "  <p style=\"padding: 0px; margin-top: 15px; margin-bottom: 0px; color: rgb(43, 43, 43); line-height: 30px; font-size: 12pt; white-space: normal; background-color: rgb(255, 255, 255); text-indent: 2em; font-family: 宋体; text-align: center;\"><span style=\"font-weight: bold; color: rgb(0, 0, 255);\">四月九日，全省科技创新大会暨科学技术奖励大会在西安召开。省委书记<br>胡和平出席会议并讲话。</span></p>\n" +
     "  <p style=\"padding: 0px; margin-top: 15px; margin-bottom: 0px; color: rgb(43, 43, 43); line-height: 30px; font-size: 12pt; white-space: normal; background-color: rgb(255, 255, 255); text-indent: 2em; font-family: 宋体;\">4月9日，全省科技创新大会暨科学技术奖励大会在西安召开。省委书记胡和平出席会议并讲话。他强调，要深入学习贯彻习近平总书记关于科技创新的重要论述，深入实施创新驱动发展战略，努力在科技创新上走在前列，加快创新型省份建设步伐，持续为新时代追赶超越注入强大动力。</p>\n" +
     "  <p style=\"padding: 0px; margin-top: 15px; margin-bottom: 0px; color: rgb(43, 43, 43); line-height: 30px; font-size: 12pt; white-space: normal; background-color: rgb(255, 255, 255); text-indent: 2em; font-family: 宋体;\">省长刘国中主持会议。省委副书记贺荣，省委常委张广智、牛一兵，省人大常委会副主任朱静芝，省政协副主席李晓东出席会议，副省长赵刚宣读省政府2018年度科学技术奖励决定。</p>\n" +
     "  <p style=\"padding: 0px; margin-top: 15px; margin-bottom: 0px; color: rgb(43, 43, 43); line-height: 30px; font-size: 12pt; white-space: normal; background-color: rgb(255, 255, 255); text-indent: 2em; font-family: 宋体;\">胡和平强调，十八大以来，以习近平同志为核心的党中央高度重视科技创新，提出一系列新思想、新论断、新要求，为我们加强科技创新指明了方向。要深入学习贯彻习近平总书记重要论述，把握党中央对推动科技创新的要求部署，牢记习近平总书记在创新驱动发展方面走在前列的谆谆嘱托，深刻认识科技创新对推动追赶超越的重要作用，切实增强推进科技创新的责任感和使命感。</p>\n" +
     "  <p style=\"padding: 0px; margin-top: 15px; margin-bottom: 0px; color: rgb(43, 43, 43); line-height: 30px; font-size: 12pt; white-space: normal; background-color: rgb(255, 255, 255); text-indent: 2em; font-family: 宋体;\">胡和平指出，要强化基础研究，加强关键核心技术攻关，推动区域协同创新，努力抢占科技创新制高点。要优化科技资源配置，促进科技成果转化，打造创新引领的现代产业体系，加强金融对科技创新的支持，持续推动军民融合、部省融合、央地融合，积极服务新时代追赶超越发展。</p>\n" +
     "  <p style=\"padding: 0px; margin-top: 15px; margin-bottom: 0px; color: rgb(43, 43, 43); line-height: 30px; font-size: 12pt; white-space: normal; background-color: rgb(255, 255, 255); text-indent: 2em; font-family: 宋体;\">胡和平强调，要加强组织领导，深化科技体制改革，建设高水平人才队伍，为全省创新驱动发展提供坚强保障。希望广大科技工作者弘扬“两弹一星”精神和“西迁”精神，深入开展“弘扬爱国奋斗精神、建功立业新时代”活动，潜心钻研、勇攀高峰，为创新型省份建设作出新贡献。</p>\n" +
     "  <p style=\"padding: 0px; margin-top: 15px; margin-bottom: 0px; color: rgb(43, 43, 43); line-height: 30px; font-size: 12pt; white-space: normal; background-color: rgb(255, 255, 255); text-indent: 2em; font-family: 宋体;\">刘国中强调，要牢固树立“四个意识”，坚决践行“两个维护”，学习领会、贯彻落实好习近平总书记关于科技创新的重要论述。要充分发挥我省科技资源优势，加强科技领域的基础创新和应用创新。要强化企业创新主体地位，促进科技成果转化，推动科技和经济紧密结合。要深化科技体制改革，营造公平包容的创新创业环境，使各类创新创业主体享有良好服务、公平机会和法律保障。</p>\n" +
     "  <p style=\"padding: 0px; margin-top: 15px; margin-bottom: 0px; color: rgb(43, 43, 43); line-height: 30px; font-size: 12pt; white-space: normal; background-color: rgb(255, 255, 255); text-indent: 2em; font-family: 宋体;\">会议为省科学技术奖获奖代表颁发奖励证书。省科学技术最高成就奖获得者陶文铨、宓传龙等发言。（摄影：宋红梅）</p>\n" +
     "  <p><br></p>\n" +
     " </body>\n" +
     "</html>"
     */
    @Override
    public void getData() {
        showProgressDialog();
        VolleyUtil volleyUtil = new VolleyUtil(this, handler);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);

        VolleyUtil.VolleyJsonCallback callback2 = new VolleyUtil.VolleyJsonCallback() {

            @Override
            public void onSuccess(JSONObject jsonObject) {
                dismisProgressDialog();
                Log.d("aa", jsonObject.toString());
                if (JsonParse.getMsgByKey(jsonObject, "state").equals("1")) {
                    InfoEntity entity = JsonParse.getInfoDetail(jsonObject);
                    title.setText(entity.getTitle());
                    source.setText("来源:"+entity.getSource());
                    date.setText("发表时间:"+ DateUtil.formatToDateString(entity.getShowTime()));
                    String newContent = getNewContent(entity.getInfoContent());
                    mWebView.loadDataWithBaseURL(null, newContent, "text/html", "UTF-8", null);
                }
            }

            @Override
            public void onFailed(String result) {
                dismisProgressDialog();
                handler.sendEmptyMessage(11);
            }
        };
        volleyUtil.getJsonDataFromServer(Constant.findInfoDetails, params, callback2);
    }

    class OnClick implements OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnback:
                    finish();
                    break;

            }
        }
    }

    public static String getNewContent(String htmltext){

        try {
            Document doc= Jsoup.parse(htmltext);
            Elements element =doc.getElementsByTag("img");
            for (Element elementimg : element) {
                elementimg.attr("style","border: 0px; display: block; margin: auto; width:100%; height:222px;");
            }

            return doc.toString();
        } catch (Exception e) {
            return htmltext;
        }
    }

    /**
     * 拦截返回键
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 是否触发按键为back键
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            back();
            return true;
            // 如果不是back键正常响应
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    private void back() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            finish();
        }
    }
}
