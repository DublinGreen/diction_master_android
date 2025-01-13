package net.dictionmasters.helpers;

import net.dictionmasters.R;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ApplicationSubClasses extends Activity {

	/**
	 * Main Fragment (Side drawer options)
	 */
	public static class MainDrawerFragment extends Fragment {
		public static final String ARG_SIDE_OPTIONS = "side_options_number";

		public MainDrawerFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.item_dashboard_drawer,
					container, false);
			return rootView;
		}
	}

}
