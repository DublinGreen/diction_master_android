package net.dictionmasters;

import net.dictionmasters.config.Config;
import net.dictionmasters.utility.Utility;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class VideoActivity extends Activity {

	private VideoView video;
	private ProgressDialog pd;
	private String videoName;
	private String videoNet;
	private String videoDescription;
	private String videoSize;
	private SharedPreferences settings;
	private SharedPreferences.Editor editor;
	private boolean wifi;

	private String checkVideo(String videoName) {
		String watchVideoArray = videoName;
		return watchVideoArray;
	}

	private boolean storeLastPlayedVideo(String systemName, String urlName) {
		if (!urlName.isEmpty() && !systemName.isEmpty()) {
			editor = settings.edit();
			editor.putString("lastPlayedVideoName", systemName);
			editor.putString("lastPlayedVideo", urlName);
			editor.commit();
			return true;
		} else {
			return false;
		}
	}

	private void noInternetDialog() {
		Dialog dialog = new Dialog(this);
		dialog.setContentView(R.layout.activity_no_internet_dialog);
		dialog.setTitle("No Internet Connection");
		dialog.show();
	}

	public void openSettingsOptions(View v) {
		Intent intent = new Intent(this, SettingsOptions.class);
		startActivity(intent);
	}

	public void openAudios(View v) {
		Intent intent = new Intent(this, DashboardActivity.class);
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
		setContentView(R.layout.activity_video);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		settings = getSharedPreferences(Config.SHARED_PREF_STRING_NAME, 0);

		videoName = getIntent().getExtras().getString("name");
		videoNet = getIntent().getExtras().getString("net");
		videoDescription = getIntent().getExtras().getString("description");
		videoSize = getIntent().getExtras().getString("size");
		wifi = Utility.checkWifiState(this);

		TextView textDescription = (TextView) findViewById(R.id.textView1);
		TextView textSize = (TextView) findViewById(R.id.textView2);
		
		textDescription.setText(videoDescription);
		textSize.setText(videoSize);	
		
		checkVideo(videoName);
		storeLastPlayedVideo(videoName, videoNet);

		video = (VideoView) findViewById(R.id.videoView1);

		pd = new ProgressDialog(this);
		pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		pd.setMessage(getResources().getString(R.string.pleaseWait));
		pd.setIndeterminate(true);
		pd.setCancelable(true);
		pd.show();

		if (videoNet.equalsIgnoreCase("3") || videoNet.equalsIgnoreCase("2")) {
			Uri networkVideoPath;
			
			if (videoNet.equalsIgnoreCase("3")) {
				networkVideoPath =  Uri.parse("android.resource://"
						+ getPackageName() + "/raw/" + "a");

				video.setVideoURI(networkVideoPath);
				video.setMediaController(new MediaController(this));
				video.requestFocus();

				video.setOnPreparedListener(new OnPreparedListener() {
					@Override
					public void onPrepared(MediaPlayer mp) {
						pd.dismiss();
						video.start();
					}
				});
			} else {
				networkVideoPath = Uri.parse("android.resource://"
						+ getPackageName() + "/raw/" + "b");

				video.setVideoURI(networkVideoPath);
				video.setMediaController(new MediaController(this));
				video.requestFocus();

				video.setOnPreparedListener(new OnPreparedListener() {
					@Override
					public void onPrepared(MediaPlayer mp) {
						pd.dismiss();
						video.start();
					}
				});
			}
		} else {
			if (wifi) {
				Uri networkVideoPath = Uri.parse(Config.SERVER
						+ Config.ONLINE_RESOURCES_UPLOADS_FOLDER
						+ Config.ONLINE_RESOURCES_VIDEO_FOLDER + videoNet
						+ ".mp4");

				video.setVideoURI(networkVideoPath);
				video.setMediaController(new MediaController(this));
				video.requestFocus();

				video.setOnPreparedListener(new OnPreparedListener() {
					@Override
					public void onPrepared(MediaPlayer mp) {
						pd.dismiss();
						video.start();
					}
				});
			} else {
				noInternetDialog();
			}
		}
	}
}
