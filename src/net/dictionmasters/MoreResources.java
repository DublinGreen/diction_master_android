package net.dictionmasters;

import net.dictionmasters.config.Config;
import net.dictionmasters.utility.Utility;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MoreResources extends Activity {

	private WebView searchWeb;
	private ProgressDialog pd;
	private boolean wifi = false;

	private void stayAwake() {
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	}

	private void noInternetDialog() {
		Dialog dialog = new Dialog(this);
		dialog.setContentView(R.layout.activity_no_internet_dialog);
		dialog.setTitle("No Internet Connection");
		dialog.show();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			finish();
		}
		return super.onOptionsItemSelected(item);
	}
	
	@SuppressLint("SetJavaScriptEnabled")
	public void initApp() throws Exception {
		wifi = Utility.checkWifiState(this);

		pd = new ProgressDialog(this);
		pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		pd.setMessage(getResources().getString(R.string.pleaseWait));
		pd.setIndeterminate(true);
		pd.setCancelable(true);
		pd.show();

		searchWeb = (WebView) findViewById(R.id.webView1);
		searchWeb.clearCache(true);
		searchWeb.getSettings().setJavaScriptEnabled(true);

		searchWeb.loadUrl(Config.SERVER + "resources/");
		
		searchWeb.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				pd.dismiss();
			}
		});
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_more_resources);

		getActionBar().setDisplayHomeAsUpEnabled(true);
		stayAwake();
		wifi = Utility.checkWifiState(this);
		
		try {
			if (wifi) {
				initApp();
			} else {
				noInternetDialog();
			}
		} catch (Exception e) {
		}
	}

}
