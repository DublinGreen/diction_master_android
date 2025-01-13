package net.dictionmasters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import net.dictionmasters.config.Config;
import net.dictionmasters.helpers.ApplicationSubClasses;
import net.dictionmasters.helpers.DictionMasterVideo;
import net.dictionmasters.helpers.DictionMasterVideoData;
import net.dictionmasters.helpers.ExpandableListAdapter;
import net.dictionmasters.helpers.WordListCollection;
import net.dictionmasters.utility.Utility;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.ListView;
import android.widget.SearchView;

public class DashboardActivity extends Activity {
	private ExpandableListAdapter listAdapter;
	private ExpandableListView expListView;
	private List<String> listDataHeader;
	private HashMap<String, List<String>> listDataChild;
	private MediaPlayer randomMediaPlayer;
	private SharedPreferences settings;
	private SharedPreferences.Editor editor;
	private boolean wifi = false;
	private MediaPlayer mPlayer;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private String[] settingsOptions;
	private WordListCollection wordCollection;
	private Intent intent;
	private String lastPlayedAudio;
	private String lastPlayedVideo;
	private String lastPlayedVideoName;
	private String onalreadyRated;

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

	private void handleIntent(Intent intent) {
		if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
			String query = intent.getStringExtra(SearchManager.QUERY);

			intent = new Intent(this, SearchActivity.class);
			intent.putExtra("search", query.trim());
			startActivity(intent);
		}
	}

	private void prepareListData() {
		listDataHeader = new ArrayList<String>();
		listDataChild = new HashMap<String, List<String>>();

		WordListCollection.initCollection();
		listDataHeader = WordListCollection.listDataHeaderNG;
		listDataChild = WordListCollection.listDataChildNG;
	}

	private void tutorial() {
		Dialog dialog = new Dialog(this);
		dialog.setContentView(R.layout.activity_dashboard_tutorial_dialog);
		dialog.setTitle("Dashboard Tutorial");
//		dialog.show();
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

	private void initAudioWords() {
		expListView = (ExpandableListView) findViewById(R.id.lvExp);
		prepareListData();

		listAdapter = new ExpandableListAdapter(this, listDataHeader,
				listDataChild);

		expListView.setAdapter(listAdapter);

		expListView.setOnGroupClickListener(new OnGroupClickListener() {
			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				return false;
			}
		});

		expListView.setOnGroupExpandListener(new OnGroupExpandListener() {
			@Override
			public void onGroupExpand(int groupPosition) {
				// Toast.makeText(getApplicationContext(),
				// listDataHeader.get(groupPosition) + " Expanded",
				// Toast.LENGTH_SHORT).show();
			}
		});

		expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {
			@Override
			public void onGroupCollapse(int groupPosition) {
				// Toast.makeText(getApplicationContext(),
				// listDataHeader.get(groupPosition) + " Collapsed",
				// Toast.LENGTH_SHORT).show();

			}
		});

		expListView.setOnChildClickListener(new OnChildClickListener() {
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {

				Animation anim = AnimationUtils.loadAnimation(
						getApplicationContext(), R.anim.splash_anim);
				v.startAnimation(anim);

				String audioNameInApp = listDataChild
						.get(listDataHeader.get(groupPosition))
						.get(childPosition).toLowerCase(Locale.ENGLISH);

				try {
					String cleanAudioName = cleanAudioName(audioNameInApp);
					editor = settings.edit();
					editor.putString("lastPlayedAudio", cleanAudioName);
					editor.commit();
					lastPlayedAudio = settings.getString("lastPlayedAudio", "");

					if (wifi) {
						String url = Config.SERVER
								+ Config.ONLINE_RESOURCES_UPLOADS_FOLDER
								+ Config.ONLINE_RESOURCES_AUDIO_FOLDER
								+ cleanAudioName + ".mp3";
						mPlayer = new MediaPlayer();
						mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
						mPlayer.setDataSource(url);
						mPlayer.prepare();

						if (!mPlayer.isPlaying()) {
							mPlayer.start();
							double seconds = Math.round(mPlayer.getDuration() / 1000.0);
							wordDialog(
									v.getContext(),
									listDataChild.get(
											listDataHeader.get(groupPosition))
											.get(childPosition),
									listDataChild.get(
											listDataHeader.get(groupPosition))
											.get(childPosition), seconds,
									mPlayer.getDuration());

						}
					} else {
						mPlayer = MediaPlayer.create(
								getApplicationContext(),
								Uri.parse("android.resource://"
										+ getPackageName() + "/raw/"
										+ cleanAudioName));
						mPlayer.prepare();

						if (!mPlayer.isPlaying()) {
							mPlayer.start();

							double seconds = Math.round(mPlayer.getDuration() / 1000.0);
							wordDialog(
									v.getContext(),
									listDataChild.get(
											listDataHeader.get(groupPosition))
											.get(childPosition),
									listDataChild.get(
											listDataHeader.get(groupPosition))
											.get(childPosition), seconds,
									mPlayer.getDuration());
						}

					}
				} catch (Exception e) {
					mPlayer = MediaPlayer.create(
							getApplicationContext(),
							Uri.parse("android.resource://" + getPackageName()
									+ "/raw/error"));
					mPlayer.start();
				}
				return false;
			}
		});

	}

	private void rateAppCheck() {
		onalreadyRated = settings.getString("rate_app", "no");

		if (onalreadyRated.equalsIgnoreCase("no")) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Let's rate diction masters");
			builder.setCancelable(false);
			builder.setPositiveButton("Sure",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							final String appPackageName = getPackageName();
							try {
								editor = settings.edit();
								editor.putString("rate_app", "yes");
								editor.commit();

								startActivity(new Intent(Intent.ACTION_VIEW,
										Uri.parse(Config.PLAY_STORE_MARKET_ID
												+ appPackageName)));
							} catch (android.content.ActivityNotFoundException anfe) {
								editor = settings.edit();
								editor.putString("rate_app", "yes");
								editor.commit();

								startActivity(new Intent(Intent.ACTION_VIEW,
										Uri.parse(Config.PLAY_STORE_LINK
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

			builder.setNeutralButton("Never",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							editor = settings.edit();
							editor.putString("rate_app", "yes");
							editor.commit();
							dialog.cancel();
						}
					});

			AlertDialog rateAlert = builder.create();
			rateAlert.show();
		}
	}

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

	private void playLastAudio() throws Exception {
		wifi = Utility.checkWifiState(this);

		String cleanAudioName = cleanAudioName(lastPlayedAudio);

		String url = Config.SERVER + Config.ONLINE_RESOURCES_UPLOADS_FOLDER
				+ Config.ONLINE_RESOURCES_AUDIO_FOLDER + cleanAudioName
				+ ".mp3";
		mPlayer = null;
		mPlayer = new MediaPlayer();
		mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		mPlayer.setDataSource(url);
		mPlayer.prepare();
		mPlayer.start();

		double seconds = Math.round(mPlayer.getDuration() / 1000.0);
		wordDialog(this, cleanAudioName, cleanAudioName, seconds,
				mPlayer.getDuration());
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

	public void openSettingsOptions(View v) {
		mDrawerLayout.openDrawer(mDrawerList);
	}

	public void openVideos(View v) {
//		Intent intent = new Intent(this, VideoListActivity.class);
//		startActivity(intent);
		
		Intent intent = new Intent(this, VideoListPlayActivity.class);
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
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.main, menu);

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.options_menu, menu);

		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		SearchView searchView = (SearchView) menu.findItem(R.id.search)
				.getActionView();
		searchView.setSearchableInfo(searchManager
				.getSearchableInfo(getComponentName()));

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case R.id.action_settings_1:
			try {
				randomAudio();
			} catch (Exception e) {
				randomMediaPlayer = MediaPlayer.create(
						getApplicationContext(),
						Uri.parse("android.resource://" + getPackageName()
								+ "/raw/error"));
				randomMediaPlayer.start();
			}
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		handleIntent(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dashboard);
		stayAwake();
		handleIntent(getIntent());
		settings = getSharedPreferences(Config.SHARED_PREF_STRING_NAME, 0);
		String firstTimeDashboard = settings.getString("first_time_dashboard",
				"");
		onalreadyRated = settings.getString("rate_app", "no");
		lastPlayedAudio = settings.getString("lastPlayedAudio", "");
		lastPlayedVideo = settings.getString("lastPlayedVideo", "");
		lastPlayedVideoName = settings.getString("lastPlayedVideoName", "");

		wifi = Utility.checkWifiState(this);
		wordCollection = new WordListCollection();

		try {
			settingsOptions = getResources().getStringArray(
					R.array.settings_option);
			mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
			mDrawerList = (ListView) findViewById(R.id.left_drawer);

			mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
					GravityCompat.START);
			mDrawerList.setAdapter(new ArrayAdapter<String>(this,
					R.layout.drawer_list_item, settingsOptions));
			mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
			initAudioWords();

			if (firstTimeDashboard.equalsIgnoreCase("")) {
				tutorial();
				editor = settings.edit();
				editor.putString("first_time_dashboard", "true");
				editor.commit();
			}

			new CountDownTimer(10000, 1000) {
				public void onTick(long millisUntilFinished) {
				}

				public void onFinish() {
					rateAppCheck();
				}
			}.start();

		} catch (Exception e) {
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mPlayer = null;
		randomMediaPlayer = null;
	}

}