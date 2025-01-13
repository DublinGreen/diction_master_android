package net.dictionmasters;

import net.dictionmasters.config.Config;
import net.dictionmasters.utility.Utility;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

public class ShareAppActivity extends Activity {
	private EditText mobile;
	private EditText email;
	private static String message = null;
	private boolean wifi;

	private void showSelectedNumber(int type, String number) {
		mobile.setText(number);
	}

	private void noInternetDialog() {
		Dialog dialog = new Dialog(this);
		dialog.setContentView(R.layout.activity_no_internet_dialog);
		dialog.setTitle("No Internet Connection");
		dialog.show();
	}

	private void stayAwake() {
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	}

	public void pickContact(View v) {
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
		startActivityForResult(intent, 1);
	}

	public void sendSms(View v) {
		if (!mobile.getText().toString().isEmpty()) {
			message = Config.SMS_MESSAGE;
			sendSMSMessage(mobile.getText().toString(), message);
		} else {
			Toast.makeText(getApplicationContext(), "No number selected.",
					Toast.LENGTH_LONG).show();
		}
	}

	public void sendEmail(View v) {
		if (!email.getText().toString().isEmpty()) {
			if (wifi) {
				message = Config.EMAIL_MESSAGE;
				sendEmailMessage(email.getText().toString(), message);
			} else {
				noInternetDialog();
			}
		} else {
			Toast.makeText(getApplicationContext(), "No email selected.",
					Toast.LENGTH_LONG).show();
		}
	}

	public void sendSMSMessage(String phoneNo, String message) {
		try {
			SmsManager smsManager = SmsManager.getDefault();
			smsManager.sendTextMessage(phoneNo, null, message, null, null);
			Toast.makeText(getApplicationContext(), "SMS sent.",
					Toast.LENGTH_LONG).show();
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(),
					"SMS faild, please try again.", Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
	}

	public void sendEmailMessage(String emailAddress, String message) {
		String[] TO = { emailAddress };
		String[] CC = { Config.DOMAIN_EMAIL };
		Intent emailIntent = new Intent(Intent.ACTION_SEND);
		emailIntent.setData(Uri.parse("mailto:"));
		emailIntent.setType("text/plain");

		emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
		emailIntent.putExtra(Intent.EXTRA_CC, CC);
		emailIntent.putExtra(Intent.EXTRA_SUBJECT, Config.APP_NAME);
		emailIntent.putExtra(Intent.EXTRA_TEXT, message);

		try {
			startActivity(Intent.createChooser(emailIntent, "Send mail..."));
			finish();
		} catch (android.content.ActivityNotFoundException ex) {
		}
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
		setContentView(R.layout.activity_share_app);
		wifi = Utility.checkWifiState(this);
		stayAwake();
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		mobile = (EditText) findViewById(R.id.editText1);
		email = (EditText) findViewById(R.id.editText2);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (data != null) {
			Uri uri = data.getData();

			if (uri != null) {
				Cursor c = null;
				try {
					c = getContentResolver()
							.query(uri,
									new String[] {
											ContactsContract.CommonDataKinds.Phone.NUMBER,
											ContactsContract.CommonDataKinds.Phone.TYPE },
									null, null, null);

					if (c != null && c.moveToFirst()) {
						String number = c.getString(0);
						int type = c.getInt(1);
						showSelectedNumber(type, number);
					}
				} finally {
					if (c != null) {
						c.close();
					}
				}
			}
		}
	}

}
