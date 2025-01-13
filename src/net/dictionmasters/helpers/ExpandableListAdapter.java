package net.dictionmasters.helpers;

import java.util.HashMap;
import java.util.List;

import net.dictionmasters.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

	private Context _context;
	private List<String> _listDataHeader; // header titles
	// child data in format of header title, child title
	private HashMap<String, List<String>> _listDataChild;

	public ExpandableListAdapter(Context context, List<String> listDataHeader,
			HashMap<String, List<String>> listChildData) {
		this._context = context;
		this._listDataHeader = listDataHeader;
		this._listDataChild = listChildData;
	}

	@Override
	public Object getChild(int groupPosition, int childPosititon) {
		return this._listDataChild.get(this._listDataHeader.get(groupPosition))
				.get(childPosititon);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getChildView(int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

		final String childText = (String) getChild(groupPosition, childPosition);

		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(
					R.layout.item_words_expanded_list_item, null);
		}

		TextView txtListChild = (TextView) convertView
				.findViewById(R.id.lblListItem);

		txtListChild.setText(childText);
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return this._listDataChild.get(this._listDataHeader.get(groupPosition))
				.size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return this._listDataHeader.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return this._listDataHeader.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		String headerTitle = (String) getGroup(groupPosition);
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(
					R.layout.item_words_expanded_list_group, null);
		}

		TextView lblListHeader = (TextView) convertView
				.findViewById(R.id.lblListHeader);

		int wordIcon = R.drawable.a;
		try {
			switch (groupPosition) {
			case 0:
				wordIcon = R.drawable.a;
				break;
			case 1:
				wordIcon = R.drawable.b;
				break;
			case 2:
				wordIcon = R.drawable.c;
				break;
			case 3:
				wordIcon = R.drawable.d;
				break;
			case 4:
				wordIcon = R.drawable.e;
				break;
			case 5:
				wordIcon = R.drawable.f;
				break;
			case 6:
				wordIcon = R.drawable.g;
				break;
			case 7:
				wordIcon = R.drawable.h;
				break;
			case 8:
				wordIcon = R.drawable.i;
				break;
			case 9:
				wordIcon = R.drawable.j;
				break;
			case 10:
				wordIcon = R.drawable.k;
				break;
			case 11:
				wordIcon = R.drawable.l;
				break;
			case 12:
				wordIcon = R.drawable.m;
				break;
			case 13:
				wordIcon = R.drawable.n;
				break;
			case 14:
				wordIcon = R.drawable.o;
				break;
			case 15:
				wordIcon = R.drawable.p;
				break;
			case 16:
				wordIcon = R.drawable.q;
				break;
			case 17:
				wordIcon = R.drawable.r;
				break;
			case 18:
				wordIcon = R.drawable.s;
				break;
			case 19:
				wordIcon = R.drawable.t;
				break;
			case 20:
				wordIcon = R.drawable.u;
				break;
			case 21:
				wordIcon = R.drawable.v;
				break;
			case 22:
				wordIcon = R.drawable.w;
				break;
			case 23:
				wordIcon = R.drawable.x;
				break;
			case 24:
				wordIcon = R.drawable.y;
				break;
			case 25:
				wordIcon = R.drawable.z;
				break;
			}
		} catch (Exception e) {
		}
		lblListHeader
				.setCompoundDrawablesWithIntrinsicBounds(wordIcon, 0, 0, 0);

		lblListHeader.setText(headerTitle);
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
}