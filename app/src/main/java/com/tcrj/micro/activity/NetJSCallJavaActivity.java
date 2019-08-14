package com.tcrj.micro.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tcrj.micro.ApiConstants;
import com.tcrj.micro.R;


public class NetJSCallJavaActivity extends AppCompatActivity implements View.OnClickListener {

	private WebView webView = null;
	private TextView title;
	private ImageView back;
	private ProgressBar mProgressBar = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_zt);

		mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
		webView = (WebView) findViewById(R.id.webview);
		title = (TextView) findViewById(R.id.txtTitle);
		back = (ImageView) findViewById(R.id.btnback);
		back.setOnClickListener(this);
		title.setText("小微信贷服务");
		showWebView();
	}

	private void showWebView() {
		//设置支持JavaScript脚本
		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		//设置可以访问文件
		webSettings.setAllowFileAccess(true);
		//设置可以支持缩放
		webSettings.setSupportZoom(true);
		//设置默认缩放方式尺寸是far
		webSettings.setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
		//设置出现缩放工具
		webSettings.setBuiltInZoomControls(false);
		webSettings.setDefaultFontSize(20);


		//访问地址设置
		webView.loadUrl(ApiConstants.JR);
		// 设置WebViewClient
		webView.setWebViewClient(new WebViewClient() {
			//url拦截
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				//使用自己的WebView组件来响应Url加载事件，而不是使用默认浏览器器加载页面
				view.loadUrl(url);
				// 相应完成返回true
				return true;
				//return super.shouldOverrideUrlLoading(view, url);
			}

			//页面开始加载
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				mProgressBar.setVisibility(View.VISIBLE);
				super.onPageStarted(view, url, favicon);
			}

			//页面加载完成
			@Override
			public void onPageFinished(WebView view, String url) {
				mProgressBar.setVisibility(View.GONE);
				super.onPageFinished(view, url);
			}

			// WebView加载的所有资源url
			@Override
			public void onLoadResource(WebView view, String url) {
				super.onLoadResource(view, url);
			}

			@Override
			public void onReceivedError(WebView view, int errorCode,
										String description, String failingUrl) {
				super.onReceivedError(view, errorCode, description, failingUrl);
			}

		});



//		//如果不设置WebViewClient，请求会跳转系统浏览器
//		webView.setWebViewClient(new WebViewClient() {
//
//			@Override
//			public boolean shouldOverrideUrlLoading(WebView view, String url) {
//				//该方法在Build.VERSION_CODES.LOLLIPOP以前有效，从Build.VERSION_CODES.LOLLIPOP起，建议使用shouldOverrideUrlLoading(WebView, WebResourceRequest)} instead
//				//返回false，意味着请求过程里，不管有多少次的跳转请求（即新的请求地址），均交给webView自己处理，这也是此方法的默认处理
//				//返回true，说明你自己想根据url，做新的跳转，比如在判断url符合条件的情况下，我想让webView加载http://ask.csdn.net/questions/178242
//
//				return false;
//			}
//
//			@Override
//			public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//				//返回false，意味着请求过程里，不管有多少次的跳转请求（即新的请求地址），均交给webView自己处理，这也是此方法的默认处理
//				//返回true，说明你自己想根据url，做新的跳转，比如在判断url符合条件的情况下，我想让webView加载http://ask.csdn.net/questions/178242
////				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
////					if (request.getUrl().toString().contains("sina.cn")) {
////						view.loadUrl("http://ask.csdn.net/questions/178242");
////						return true;
////					}
////				}
//
//				return false;
//			}
//
//
//			// 页面开始加载
//			@Override
//			public void onPageStarted(WebView view, String url, Bitmap favicon) {
//				mProgressBar.setVisibility(View.VISIBLE);
//				super.onPageStarted(view, url, favicon);
//			}
//
//			// 页面加载完成
//			@Override
//			public void onPageFinished(WebView view, String url) {
//				mProgressBar.setVisibility(View.GONE);
//				super.onPageFinished(view, url);
//			}
//
//		});

		// 设置WebChromeClient
		webView.setWebChromeClient(new WebChromeClient() {
			@Override
			// 处理javascript中的alert
			public boolean onJsAlert(WebView view, String url, final String message,
									 final JsResult result) {
				return true;

			}


			@Override
			// 处理javascript中的confirm
			public boolean onJsConfirm(WebView view, String url,
									   String message, final JsResult result) {
				return super.onJsConfirm(view, url, message, result);
			}


			@Override
			// 处理javascript中的prompt
			public boolean onJsPrompt(WebView view, String url, String message,
									  String defaultValue, final JsPromptResult result) {
				return super.onJsPrompt(view, url, message, defaultValue, result);
			}

			//设置网页加载的进度条
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				mProgressBar.setProgress(newProgress);
				super.onProgressChanged(view, newProgress);
			}

			//设置程序的Title
			@Override
			public void onReceivedTitle(WebView view, String title) {
				setTitle(title);
				super.onReceivedTitle(view, title);
			}
		});

	}

	@Override
	public void onClick(View v) {
		finish();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode==KeyEvent.KEYCODE_BACK&&webView.canGoBack()){
			webView.goBack();
			return true;
		}else{
			onBackPressed();
		}
		return super.onKeyDown(keyCode, event);
	}

}
