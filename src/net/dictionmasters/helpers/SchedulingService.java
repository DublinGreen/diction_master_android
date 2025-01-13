package net.dictionmasters.helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import net.dictionmasters.R;
import net.dictionmasters.WordForTheDay;
import net.dictionmasters.config.Config;
import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

public class SchedulingService extends IntentService {

	private String notificationMessage;
	private MediaPlayer mPlayer;

	public SchedulingService() {
		super("SchedulingService");
	}

	public static final String TAG = Config.APP_NAME;
	public static final int NOTIFICATION_ID = 1;
	public static final String SEARCH_STRING = "abandon";
	public static final String URL = Config.SERVER
			+ Config.ONLINE_RESOURCES_UPLOADS_FOLDER
			+ Config.ONLINE_RESOURCES_FOLDER + "words.xml";
	private NotificationManager mNotificationManager;
	NotificationCompat.Builder builder;

	@Override
	protected void onHandleIntent(Intent intent) {
		String urlString = URL;
		String result = "";

		try {
			result = loadFromNetwork(urlString);
		} catch (IOException e) {
		}

		notificationMessage = Config.APP_NAME + " word for the day.";
		
		if (result.indexOf(SEARCH_STRING) != -1) {
			sendNotification(notificationMessage);
		} else {
			sendNotification(notificationMessage);
		}
		// Release the wake lock provided by the BroadcastReceiver.
		AlarmReceiver.completeWakefulIntent(intent);
	}

	private void sendNotification(String msg) {
		mNotificationManager = (NotificationManager) this
				.getSystemService(Context.NOTIFICATION_SERVICE);

		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
				new Intent(this, WordForTheDay.class), 0);

		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
				this).setSmallIcon(R.drawable.ic_launcher)
				.setContentTitle(getString(R.string.app_name))
				.setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
				.setContentText(msg);

		mBuilder.setContentIntent(contentIntent);
		mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());

		try {
			mPlayer = MediaPlayer.create(
					getApplicationContext(),
					Uri.parse("android.resource://" + getPackageName()
							+ "/raw/notification"));
			mPlayer.start();
		} catch (Exception e) {
		}
	}

	private String loadFromNetwork(String urlString) throws IOException {
		InputStream stream = null;
		String str = "";

		try {
			stream = downloadUrl(urlString);
			str = readIt(stream);
		} finally {
			if (stream != null) {
				stream.close();
			}
		}
		return str;
	}

	private InputStream downloadUrl(String urlString) throws IOException {

		URL url = new URL(urlString);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setReadTimeout(10000 /* milliseconds */);
		conn.setConnectTimeout(15000 /* milliseconds */);
		conn.setRequestMethod("GET");
		conn.setDoInput(true);
		// Start the query
		conn.connect();
		InputStream stream = conn.getInputStream();
		return stream;
	}

	private String readIt(InputStream stream) throws IOException {

		StringBuilder builder = new StringBuilder();
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(stream));
		for (String line = reader.readLine(); line != null; line = reader
				.readLine())
			builder.append(line);
		reader.close();
		return builder.toString();
	}
}
