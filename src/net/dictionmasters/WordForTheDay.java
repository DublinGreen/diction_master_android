package net.dictionmasters;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import net.dictionmasters.config.Config;
import net.dictionmasters.helpers.WordListCollection;
import net.dictionmasters.utility.Utility;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class WordForTheDay extends Activity {

	private WordListCollection wordCollection = new WordListCollection();
	private MediaPlayer wordForTodayMediaPlayer;
	private boolean wifi;
	private String wordForToday;
	private TextView wordForTodayButton;
	private SharedPreferences settings;
	private SharedPreferences.Editor editor;
	private String lastWordTimeStamp;
	private String nowWord;
	private int nowWordTimeStamp;
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

	private int getNewWorForToday() {
		Random random = new Random();
		return random.nextInt(wordCollection.audioName.length - 0 + 1) + 0;
	}

	private boolean checkWordForToday(String newWordForToday,
			int newWordForTodayTimestamp) {
		lastWordTimeStamp = settings.getString("last_word_for_today_timestamp",
				"");

		String newWordForTodayTimestampString = Integer.valueOf(
				newWordForTodayTimestamp).toString();

		if (newWordForTodayTimestampString.equalsIgnoreCase(lastWordTimeStamp)) {
			return false;
		} else {
			return true;
		}
	}

	public void playWordForToday(View v) {
		try {
			String playWord = wordForToday;

			String cleanAudioName = cleanAudioName(playWord);
			wifi = Utility.checkWifiState(this);
			pd.show();
			
			if (wifi) {
				String url = Config.SERVER
						+ Config.ONLINE_RESOURCES_UPLOADS_FOLDER
						+ Config.ONLINE_RESOURCES_AUDIO_FOLDER + cleanAudioName
						+ ".mp3";

				wordForTodayMediaPlayer = new MediaPlayer();
				wordForTodayMediaPlayer
						.setAudioStreamType(AudioManager.STREAM_MUSIC);
				wordForTodayMediaPlayer.setDataSource(url);
				wordForTodayMediaPlayer.prepare();
				wordForTodayMediaPlayer.start();
				
				wordForTodayMediaPlayer.setOnPreparedListener(new OnPreparedListener() {
			        @Override
			        public void onPrepared(MediaPlayer mp) {
			               if (pd != null && pd.isShowing()){
			                  pd.dismiss();
			               }
			               wordForTodayMediaPlayer.start();
			         }
			     });

				double seconds = Math.round(wordForTodayMediaPlayer
						.getDuration() / 1000.0);

				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setTitle(playWord.toUpperCase(Locale.ENGLISH));
				builder.setMessage(playWord + " is playing and will play for "
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
				}, wordForTodayMediaPlayer.getDuration());

			} else {
				wordForTodayMediaPlayer = MediaPlayer.create(
						getApplicationContext(),
						Uri.parse("android.resource://" + getPackageName()
								+ "/raw/" + cleanAudioName));
				wordForTodayMediaPlayer.start();
				pd.dismiss();
			}
		} catch (Exception e) {
			wordForTodayMediaPlayer = MediaPlayer.create(
					getApplicationContext(),
					Uri.parse("android.resource://" + getPackageName()
							+ "/raw/error"));
			wordForTodayMediaPlayer.start();
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
		setContentView(R.layout.activity_word_for_today);
		stayAwake();
		getActionBar().setDisplayHomeAsUpEnabled(true);
		wifi = Utility.checkWifiState(this);
		settings = getSharedPreferences(Config.SHARED_PREF_STRING_NAME, 0);
		wordForTodayButton = (TextView) findViewById(R.id.button1);

		pd = new ProgressDialog(this);
		pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		pd.setMessage(getResources().getString(R.string.pleaseWait));
		pd.setIndeterminate(true);
		pd.setCancelable(true);
		
		Date d = new Date();
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTimeInMillis(d.getTime());

		int nowYear = Calendar.YEAR;
		int nowMonth = Calendar.MONTH;
		int nowDay = Calendar.DAY_OF_MONTH;
		nowWordTimeStamp = nowYear + nowMonth + nowDay;

		int randomInt = getNewWorForToday();
		nowWord = wordCollection.audioName[randomInt];

		if (checkWordForToday(nowWord, nowWordTimeStamp)) {
			wordForToday = nowWord;
			editor = settings.edit();
			editor.putString("last_word_for_today", wordForToday);
			editor.putString("last_word_for_today_timestamp",
					Integer.valueOf(nowWordTimeStamp).toString());
			editor.commit();
		} else {
			wordForToday = settings.getString("last_word_for_today", "");
		}

		wordForTodayButton.setText(wordForToday);
	}
}
