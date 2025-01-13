package net.dictionmasters;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

public class SettingsOptions extends Activity {

	private Intent intent;

	/**
	 * Keeps the screen on while app is active
	 */
	private void stayAwake() {
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	}

	public void shareApp(View v) {
		// Intent intent = new
		// Intent(this,TransactionMenuNGTransactionStatusActivity.class);
		// startActivity(intent);
	}

	public void rateApp(View v) {
		intent = new Intent(this, VideoListActivity.class);
		startActivity(intent);
	}

	public void playAll(View v) {
		// Intent intent = new
		// Intent(this,TransactionMenuNGTransactionStatusActivity.class);
		// startActivity(intent);
	}

	public void lastPlayed(View v) {
		// Intent intent = new
		// Intent(this,TransactionMenuNGTransactionStatusActivity.class);
		// startActivity(intent);
	}

	public void scheduler(View v) {
		// Intent intent = new
		// Intent(this,TransactionMenuNGTransactionStatusActivity.class);
		// startActivity(intent);
	}

	public void wordForTheDay(View v) {
		intent = new Intent(this, WordForTheDay.class);
		startActivity(intent);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			finish();
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting_options);
		stayAwake();
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}
}
