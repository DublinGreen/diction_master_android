package net.dictionmasters;

import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import net.dictionmasters.config.Config;
import net.dictionmasters.helpers.ApplicationSubClasses;
import net.dictionmasters.helpers.DictionMasterVideo;
import net.dictionmasters.helpers.DictionMasterVideoData;
import net.dictionmasters.helpers.WordListCollection;
import net.dictionmasters.utility.Utility;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class VideoListPlayActivity extends ListActivity {

	List<DictionMasterVideo> flowers = new DictionMasterVideoData().getVideos();
	
	private static final int REQUEST_CODE = 100;
	private List<DictionMasterVideo> videos = new DictionMasterVideoData().getVideos();
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private String[] settingsOptions;
	private MediaPlayer randomMediaPlayer;
	private SharedPreferences settings;
	private SharedPreferences.Editor editor;
	private Intent intent;
	private String lastPlayedAudio;
	private String lastPlayedVideo;
	private String lastPlayedVideoName;
	private boolean wifi;
	private MediaPlayer mPlayer;
	private WordListCollection wordCollection;
	private ListView videoListView;

	private void rateAppDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Let's rate diction masters");
		builder.setCancelable(false);
		builder.setPositiveButton("Sure",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						final String appPackageName = getPackageName();
						try {
							startActivity(new Intent(Intent.ACTION_VIEW, Uri
									.parse(Config.PLAY_STORE_MARKET_ID
											+ appPackageName)));
						} catch (android.content.ActivityNotFoundException anfe) {
							startActivity(new Intent(Intent.ACTION_VIEW, Uri
									.parse(Config.PLAY_STORE_LINK
											+ appPackageName)));
						}
					}
				});

		builder.setNegativeButton("Not Now",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});

		AlertDialog rateAlert = builder.create();
		rateAlert.show();
	}

	private void randomAudio() throws Exception {
		Random random = new Random();
		int randomInt = random.nextInt(wordCollection.audioName.length - 0 + 1) + 0;
		String cleanAudioName = cleanAudioName(wordCollection.audioName[randomInt]);
		wifi = Utility.checkWifiState(this);

		editor = settings.edit();
		editor.putString("lastPlayedAudio", cleanAudioName);
		editor.commit();
		lastPlayedAudio = settings.getString("lastPlayedAudio", "");

		if (wifi) {
			String url = Config.SERVER + Config.ONLINE_RESOURCES_UPLOADS_FOLDER
					+ Config.ONLINE_RESOURCES_AUDIO_FOLDER + cleanAudioName
					+ ".mp3";

			randomMediaPlayer = new MediaPlayer();
			randomMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			randomMediaPlayer.setDataSource(url);
			randomMediaPlayer.prepare();
			randomMediaPlayer.start();

			double seconds = Math
					.round(randomMediaPlayer.getDuration() / 1000.0);
			wordDialog(this, wordCollection.audioName[randomInt],
					wordCollection.audioName[randomInt], seconds,
					randomMediaPlayer.getDuration());
		} else {
			randomMediaPlayer = MediaPlayer.create(
					getApplicationContext(),
					Uri.parse("android.resource://" + getPackageName()
							+ "/raw/" + cleanAudioName));
			randomMediaPlayer.start();
		}
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

	private void playLastAudio() throws Exception {
		wifi = Utility.checkWifiState(this);

		String cleanAudioName = cleanAudioName(lastPlayedAudio);

		if (wifi) {
			String url = Config.SERVER + Config.ONLINE_RESOURCES_UPLOADS_FOLDER
					+ Config.ONLINE_RESOURCES_AUDIO_FOLDER + cleanAudioName
					+ ".mp3";
			mPlayer = null;
			mPlayer = new MediaPlayer();
			mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mPlayer.setDataSource(url);
			mPlayer.prepare();
			mPlayer.start();

			double seconds = Math
					.round(randomMediaPlayer.getDuration() / 1000.0);
			wordDialog(this, cleanAudioName, cleanAudioName, seconds,
					mPlayer.getDuration());
		}
	}

	private void wordDialog(Context context, String title, String word,
			double seconds, int duration) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(title.toUpperCase(Locale.ENGLISH));
		builder.setMessage(word + " is playing and will play for " + seconds
				+ " seconds.");
		builder.setCancelable(false);
		final AlertDialog dlg = builder.create();
		dlg.show();

		final Timer t = new Timer();
		t.schedule(new TimerTask() {
			public void run() {
				dlg.dismiss();
				t.cancel();
			}
		}, duration);
	}

	private void selectDrawerItem(int position) {
		Fragment fragment = new ApplicationSubClasses.MainDrawerFragment();
		Bundle args = new Bundle();
		args.putInt(ApplicationSubClasses.MainDrawerFragment.ARG_SIDE_OPTIONS,
				position);
		fragment.setArguments(args);

		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.content_frame, fragment).commit();

		mDrawerList.setItemChecked(position, true);

		if (position == 0) {
			if (!lastPlayedVideo.isEmpty() && !lastPlayedVideoName.isEmpty()) {
				int a = Integer.parseInt(lastPlayedVideo);
				List<DictionMasterVideo> b =  new DictionMasterVideoData().getVideos();		
				DictionMasterVideo video = b.get(a + 1);
								
				Intent intent = new Intent(this, VideoActivity.class);
				intent.putExtra("name", video.name);
				intent.putExtra("imageResource", video.imageResource);
				intent.putExtra("description", video.description);
				intent.putExtra("size", video.size);
				intent.putExtra("net", video.net);
				startActivity(intent);
			}
		} else if (position == 1) {
			try {
				playLastAudio();
			} catch (Exception e) {
				mPlayer = MediaPlayer.create(
						getApplicationContext(),
						Uri.parse("android.resource://" + getPackageName()
								+ "/raw/error"));
				mPlayer.start();
			}
		} else if (position == 2) {
			try {
				randomAudio();
			} catch (Exception e) {
				randomMediaPlayer = MediaPlayer.create(
						getApplicationContext(),
						Uri.parse("android.resource://" + getPackageName()
								+ "/raw/error"));
				randomMediaPlayer.start();
			}
		} else if (position == 3) {
			intent = new Intent(this, WordForTheDay.class);
			startActivity(intent);
		} else if (position == 4) {
			intent = new Intent(this, ShareAppActivity.class);
			startActivity(intent);
		} else if (position == 5) {
			rateAppDialog();
		}

		mDrawerLayout.closeDrawer(mDrawerList);
	}

	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			selectDrawerItem(position);
		}
	}

	private void stayAwake() {
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	}

	public void openSettingsOptions(View v) {
		mDrawerLayout.openDrawer(mDrawerList);
	}

	public void prevVideo(View v) {
		String lastPlayedVideo = settings.getString("lastPlayedVideo", "1");
		int lastPlayedVideoInt = Integer.parseInt(lastPlayedVideo);
		
		int prevVideoInt = 1;
		if(lastPlayedVideoInt != 1){
			prevVideoInt = lastPlayedVideoInt - 1;	
		} 
		
		List<DictionMasterVideo> i =  new DictionMasterVideoData().getVideos();		
		DictionMasterVideo video = i.get(prevVideoInt - 1);

		Intent intent = new Intent(this, VideoDetails.class);
		intent.putExtra("name", video.name);
		intent.putExtra("imageResource", video.imageResource);
		intent.putExtra("description", video.description);
		intent.putExtra("size", video.size);
		intent.putExtra("net", video.net);
		startActivityForResult(intent, REQUEST_CODE);
	}
	
	public void nextVideo(View v) {
		String lastPlayedVideo = settings.getString("lastPlayedVideo", "1");
		int lastPlayedVideoInt = Integer.parseInt(lastPlayedVideo);
		
		int nextVideoInt = lastPlayedVideoInt + 1;
		
		List<DictionMasterVideo> i =  new DictionMasterVideoData().getVideos();		
		DictionMasterVideo video = i.get(nextVideoInt - 1);

		Intent intent = new Intent(this, VideoDetails.class);
		intent.putExtra("name", video.name);
		intent.putExtra("imageResource", video.imageResource);
		intent.putExtra("description", video.description);
		intent.putExtra("size", video.size);
		intent.putExtra("net", video.net);
		startActivityForResult(intent, REQUEST_CODE);
	}
	
	public void openVideos(View v) {
		Intent intent = new Intent(this, VideoListActivity.class);
		startActivity(intent);
	}

	public void openAudios(View v) {
		Intent intent = new Intent(this, DashboardActivity.class);
		startActivity(intent);
	}

	public void openOnlineResource(View v) {
		Intent intent = new Intent(this, MoreResources.class);
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
		
		setContentView(R.layout.item_video_list_group);
		DictionMasterVideoAdapter adapter = new DictionMasterVideoAdapter(this,R.layout.play_item_video, flowers);
		setListAdapter(adapter);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
				
		this.getListView().setSelector(R.drawable.selector);
		
		wifi = Utility.checkWifiState(this);

		settings = getSharedPreferences(Config.SHARED_PREF_STRING_NAME, 0);
		lastPlayedAudio = settings.getString("lastPlayedAudio", "");
		lastPlayedVideo = settings.getString("lastPlayedVideo", "");
		lastPlayedVideoName = settings.getString("lastPlayedVideoName", "");
		stayAwake();
		settingsOptions = getResources()
				.getStringArray(R.array.settings_option);

		wifi = Utility.checkWifiState(this);
		wordCollection = new WordListCollection();

		try {
			mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
			mDrawerList = (ListView) findViewById(R.id.left_drawer);

			mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
					GravityCompat.START);
			mDrawerList.setAdapter(new ArrayAdapter<String>(this,
					R.layout.drawer_list_item, settingsOptions));
			mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
			videoListView = (ListView) findViewById(android.R.id.list);
		} catch (Exception e) {
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
			String videoName = data.getStringExtra("name");
			String action = data.getStringExtra("action");

			if (action.equals("add")) {
				Toast.makeText(this, "Play " + videoName, Toast.LENGTH_LONG)
						.show();
			}
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		try {
			int index = Integer.parseInt(settings.getString("lastClicked", ""));
			int totalCount = videoListView.getChildCount();
			for (int i = 0; i <= totalCount; i++) {
				if (i == index) {
					View view = videoListView.getChildAt(index);
					view.setBackgroundColor(getResources().getInteger(
							R.color.main_color_2));
				} else {
					View view = videoListView.getChildAt(i);
					view.setBackgroundColor(getResources().getInteger(
							R.color.white_color));
				}
			}
		} catch (Exception e) {
		}
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		v.setSelected(true);

		DictionMasterVideo video = videos.get(position);

//		Toast.makeText(getApplicationContext(),
//				"You clicked " + l.getChildAt(position) + "------" + position,
//				Toast.LENGTH_SHORT).show();

		editor = settings.edit();
		editor.putString("lastClicked", Integer.toString(position));
		editor.commit();

		// try{
		// String lastClicked = settings.getString("lastClicked", "0");
		// if(!lastClicked.equalsIgnoreCase("0")){
		// l.getChildAt(position).setBackgroundColor(getResources().getColor(R.color.main_color_2));
		// l.getChildAt(Integer.parseInt(lastClicked)).setBackgroundColor(getResources().getColor(R.color.white_color));
		// }
		// editor = settings.edit();
		// editor.putString("lastClicked", Integer.toString(position));
		// editor.commit();
		// }catch(Exception e){
		// editor = settings.edit();
		// editor.putString("lastClicked", Integer.toString(position));
		// editor.commit();
		// }

		Intent intent = new Intent(this, VideoDetails.class);
		intent.putExtra("name", video.name);
		intent.putExtra("imageResource", video.imageResource);
		intent.putExtra("description", video.description);
		intent.putExtra("size", video.size);
		intent.putExtra("net", video.net);
		startActivityForResult(intent, REQUEST_CODE);

	}
	
}

