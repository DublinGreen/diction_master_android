package net.dictionmasters;

import java.util.List;

import net.dictionmasters.helpers.DictionMasterVideo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DictionMasterVideoAdapter extends ArrayAdapter<DictionMasterVideo>{

	private Context context;
	private List<DictionMasterVideo> objects;
	
	public DictionMasterVideoAdapter(Context context, int resource, List<DictionMasterVideo> objects) {
		super(context, resource, objects);
		this.context = context;
		this.objects = objects;
	}

	@SuppressLint({ "ViewHolder", "InflateParams" })
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		DictionMasterVideo video = objects.get(position);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.play_item_video, null);
		
		ImageView image = (ImageView) view.findViewById(R.id.imageView1);
		image.setImageResource(video.imageResource);
		
		TextView tv = (TextView) view.findViewById(R.id.textView1);
		tv.setText(video.name);
		
		return view;
	}
	
}
