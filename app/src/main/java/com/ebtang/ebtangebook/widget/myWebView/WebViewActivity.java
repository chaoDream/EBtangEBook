package com.ebtang.ebtangebook.widget.myWebView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.app.BaseFragmentActivity;
import com.ebtang.ebtangebook.constants.Constants;

import butterknife.Bind;


public class WebViewActivity extends BaseFragmentActivity {
	@Bind(R.id.top_title_left)
	ImageView imageView_back;
	@Bind(R.id.top_title_text)
	TextView textView_title;
	WebView webView;
	private ProgressBar progressBar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.app_webview);

        initView();
		initData();

	}

    @Override
	public void initView() {
		// TODO Auto-generated method stub
		imageView_back.setVisibility(View.VISIBLE);
		imageView_back.setImageResource(R.drawable.back);


		webView =  (WebView) findViewById(R.id.webview);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setSaveFormData(false);
		webView.getSettings().setSupportZoom(false);
		progressBar = (ProgressBar)findViewById(R.id.webviewactivity_web_pb);
		webView.setWebChromeClient(new CaptureWebClient());
		webView.setWebViewClient(new MyWebViewClient(this, progressBar));

		webView.clearCache(true);
		webView.clearHistory();

		imageView_back.setOnClickListener(this);
	}

	private final class CaptureWebClient extends WebChromeClient {

		@Override
		public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
			new AlertDialog.Builder(WebViewActivity.this)
					.setTitle("提示")
					.setMessage(message)
					.setPositiveButton("确定", new DialogInterface.OnClickListener(){
						public void onClick(DialogInterface dialoginterface, int i){}
					}).show();
			return true;
		}
	}


	@Override
	public void initData() {
		Intent intent = getIntent();
		String title = intent.getStringExtra(Constants.APP_WEBVIEW_TITLE);
		String url = intent.getStringExtra(Constants.APP_WEBVIEW_URL);
		textView_title.setText(title);
		webView.loadUrl(url.toString());

	}

	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case  R.id.top_title_left:
				onBackPressed();
				break;
			default:
				break;
		}
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		setResult(100000,getIntent());
		this.finish();
		super.onBackPressed();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		webView.destroy();
	}
}
