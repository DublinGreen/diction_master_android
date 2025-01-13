package net.dictionmasters;

import java.util.List;

import net.dictionmasters.helpers.Video;
import net.dictionmasters.helpers.VideoAdapter;
import net.dictionmasters.helpers.VideoData;
import android.app.ListActivity;
import android.os.Bundle;

public class VideoListActivity extends ListActivity {

	List<Video> videos = new VideoData().getVideos();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.play_video_list);

		VideoAdapter adapter = new VideoAdapter(this,R.layout.play_item_video, videos);
		setListAdapter(adapter);
		
	}
}
