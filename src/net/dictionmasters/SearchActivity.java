package net.dictionmasters;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import net.dictionmasters.config.Config;
import net.dictionmasters.helpers.WordListCollection;
import net.dictionmasters.utility.Utility;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class SearchActivity extends Activity {
	private Intent intent;
	private MediaPlayer mPlayer;
	private boolean wifi;
	private String search = null;
	private TextView searchDescription;
	private Button audioButton;
	private ProgressDialog pd;

	private void stayAwake() {
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	}

	private String cleanAudioName(String v) {
		String value = v;
		value = value.replace("'", "");
		value = value.replace("\"", "");
		value = value.replace("-", "");
		value = value.replace("_", "");
		value = value.replace(" ", "");
		value = value.replace("(", "");
		value = value.replace(")", "");
		value = value.replace("finally", "finallyword");
		value = value.replace("continue", "finallyword");

		return value;
	}

	public void playAudio(View v) {
		String audioNameInApp = search.toLowerCase(Locale.ENGLISH);
		pd.show();

		try {
			String cleanAudioName = cleanAudioName(audioNameInApp);

			if (wifi) {
				String url = Config.SERVER
						+ Config.ONLINE_RESOURCES_UPLOADS_FOLDER
						+ Config.ONLINE_RESOURCES_AUDIO_FOLDER + cleanAudioName
						+ ".mp3";
				mPlayer = new MediaPlayer();
				mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
				mPlayer.setDataSource(url);
				mPlayer.prepare();
				mPlayer.start();
				pd.dismiss();

				double seconds = Math.round(mPlayer.getDuration() / 1000.0);

				AlertDialog.Builder builder = new AlertDialog.Builder(
						v.getContext());
				builder.setTitle(search.toUpperCase(Locale.ENGLISH));
				builder.setMessage(search + " is playing and will play for "
						+ seconds + " seconds.");
				builder.setCancelable(false);

				final AlertDialog dlg = builder.create();
				dlg.show();

				final Timer t = new Timer();
				t.schedule(new TimerTask() {
					public void run() {
						dlg.dismiss();
						t.cancel();
					}
				}, mPlayer.getDuration());

			} else {
				mPlayer = MediaPlayer.create(
						getApplicationContext(),
						Uri.parse("android.resource://" + getPackageName()
								+ "/raw/" + cleanAudioName));
				mPlayer.start();

				double seconds = Math.round(mPlayer.getDuration() / 1000.0);

				AlertDialog.Builder builder = new AlertDialog.Builder(
						v.getContext());
				builder.setTitle(search.toUpperCase(Locale.ENGLISH));
				builder.setMessage(search + " is playing and will play for "
						+ seconds + " seconds.");
				builder.setCancelable(false);

				final AlertDialog dlg = builder.create();
				dlg.show();

				final Timer t = new Timer();
				t.schedule(new TimerTask() {
					public void run() {
						dlg.dismiss();
						t.cancel();
					}
				}, mPlayer.getDuration());
			}
		} catch (Exception e) {
			mPlayer = MediaPlayer.create(
					getApplicationContext(),
					Uri.parse("android.resource://" + getPackageName()
							+ "/raw/error"));
			mPlayer.start();
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
		setContentView(R.layout.activity_search);
		stayAwake();

		searchDescription = (TextView) findViewById(R.id.textView1);
		audioButton = (Button) findViewById(R.id.button2);

		getActionBar().setDisplayHomeAsUpEnabled(true);
		wifi = Utility.checkWifiState(this);
		WordListCollection wordsCollection = new WordListCollection();
		audioButton.setVisibility(View.INVISIBLE);

		search = getIntent().getExtras().getString("search");
		String[] allWords = wordsCollection.audioName;

		pd = new ProgressDialog(this);
		pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		pd.setMessage(getResources().getString(R.string.pleaseWait));
		pd.setIndeterminate(true);
		pd.setCancelable(true);
		
		try {
			search = search.trim().toLowerCase(Locale.ENGLISH);
			if (search.isEmpty()) {
				intent = new Intent(this, DashboardActivity.class);
				startActivity(intent);
			} else {
				for (String string : allWords) {
					if (search.equalsIgnoreCase(string)) {
						searchDescription.setText(search
								.toUpperCase(Locale.ENGLISH)
								+ " found. Click the button below for audio");
						audioButton.setVisibility(View.VISIBLE);
						audioButton.setText("Play " + search.toUpperCase(Locale.ENGLISH));
						break;
					} else {
						searchDescription
								.setText("No result found. Search another word or go back to dashboard");
					}
				}
			}
		} catch (Exception e) {
			intent = new Intent(this, DashboardActivity.class);
			startActivity(intent);
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		
//		mPlayer.release();
		mPlayer = null;
	}
}
