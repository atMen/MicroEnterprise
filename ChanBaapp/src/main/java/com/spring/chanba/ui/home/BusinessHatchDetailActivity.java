package com.spring.chanba.ui.home;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.spring.chanba.R;
import com.spring.chanba.application.BaseActivity;
import com.spring.chanba.bean.LawServiceDetailEntity;
import com.spring.chanba.contract.HatchDetailContract;
import com.spring.chanba.presenter.HatchDetailPresenter;
import com.spring.chanba.utils.Utils;
import com.spring.chanba.views.ObservableScrollView;

import java.util.HashMap;

/**
 * 创业孵化知识/案例详情
 */
public class BusinessHatchDetailActivity extends BaseActivity implements HatchDetailContract.View {
    private ImageButton imgTitleBack;
    private TextView tvTitleHead;
    private ObservableScrollView scrollView;
    private ProgressBar progressBar;
    private TextView tvWebTitle;
    private TextView tvWebCompany;
    private TextView tvWebIssuetime;
    private WebView webview;
    private String id;
    private HatchDetailContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        initView();
        loadData();
    }

    /**
     * 请求数据
     */
    private void loadData() {
        HashMap<String, String> map = new HashMap<>();
        map.put("id", id);
        presenter = new HatchDetailPresenter(this);
        presenter.getData(map);
    }

    /**
     * 获取页面控件
     */
    @Override
    public void initView() {
        id = getIntent().getStringExtra("id");
        imgTitleBack = (ImageButton) findViewById(R.id.img_title_back);
        tvTitleHead = (TextView) findViewById(R.id.tv_title_head);
        scrollView = (ObservableScrollView) findViewById(R.id.scrollview_webview);
        progressBar = (ProgressBar) findViewById(R.id.progress_bars);
        tvWebTitle = (TextView) findViewById(R.id.tv_web_title);
        tvWebCompany = (TextView) findViewById(R.id.tv_web_company);
        tvWebIssuetime = (TextView) findViewById(R.id.tv_web_issuetime);
        webview = (WebView) findViewById(R.id.webview);
        WebSettings webset = webview.getSettings();
        webset.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webset.setJavaScriptEnabled(true);
        webset.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        tvTitleHead.setText("创业孵化详情");
        imgTitleBack.setVisibility(View.VISIBLE);
        imgTitleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                if (url.contains("action")) {
                    webview.goBack();
                } else {
                    view.loadUrl(url);
                }
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //这个是一定要加上那个的,配合scrollView和WebView的height=wrap_content属性使用
                int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                //重新测量
                webview.measure(w, h);
            }
        });

        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);
                } else {
                    if (View.GONE == progressBar.getVisibility()) {
                        progressBar.setVisibility(View.VISIBLE);
                    }
                    progressBar.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
        scrollView.smoothScrollTo(0, 0);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {
            webview.goBack();
            return true;
        }
        finish();
        return false;
    }

    /**
     * 返回服务端消息
     *
     * @param message
     */
    @Override
    public void failedMessage(String message) {
        displayToast(message);
    }

    @Override
    public void initData(LawServiceDetailEntity entity) {
        if (entity != null) {
            if (entity.getState() == 1) {
                tvWebTitle.setText(entity.getData().getTitle());
                tvWebCompany.setText(entity.getData().getAuthor());
                tvWebIssuetime.setText(entity.getData().getAddTime());
                if (!Utils.isStringEmpty(entity.getData().getContent())) {
                    webview.loadDataWithBaseURL(null, Utils.transHtmltext(entity.getData().getContent()), "text/html", "utf-8", null);
                }
            }
        }
    }

    @Override
    public void setPresenter(HatchDetailContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
