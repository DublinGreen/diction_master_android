package net.dictionmasters.helpers;

import java.util.List;

import net.dictionmasters.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class VideoAdapter extends ArrayAdapter<Video> {
	
	private Context context;
	private List<Video> objects;

	public VideoAdapter(Context context, int resource,List<Video> objects) {
		super(context, resource);
		this.context = context;
		this.objects = objects; 
	}

	@SuppressLint({ "ViewHolder", "InflateParams" })
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		Video video = objects.get(position);
		LayoutInflater inflater = (LayoutInflater)
				context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.item_video, null);
		
		ImageView image = (ImageView) view.findViewById(R.id.imageView1);
		image.setImageResource(video.imageResource);
		
		TextView tv = (TextView) view.findViewById(R.id.textView1);
		tv.setText(video.videoName);
		
		return view;
	}
	
}
