package net.dictionmasters;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class VideoDetails extends Activity {

	private String videoName;
	private String videoNet;
	private String videoDescription;
	private String videoSize;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.item_video_list);

		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		Intent intent = getIntent();
		
		videoName = intent.getStringExtra("name");
		int imageResource = intent.getIntExtra("imageResource", 0);
		videoDescription = intent.getStringExtra("description");
		videoSize = intent.getStringExtra("size");
		videoNet = intent.getStringExtra("net");
		
		TextView tv = (TextView) findViewById(R.id.textView1);
		tv.setText(videoName);
		
		tv = (TextView) findViewById(R.id.textView2);
		tv.setText(videoDescription);
		
		tv = (TextView) findViewById(R.id.textView3);
		tv.setText(videoSize);
		
		ImageView image = (ImageView) findViewById(R.id.imageView1);
		image.setImageResource(imageResource);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			finish();
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.details, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	public void playVideo(MenuItem item) {
		Intent intent = new Intent(this, VideoActivity.class);
		intent.putExtra("net", videoNet);
		intent.putExtra("name", videoName);
		intent.putExtra("description", videoDescription);
		intent.putExtra("size", videoSize);
		startActivity(intent);
	}
}
