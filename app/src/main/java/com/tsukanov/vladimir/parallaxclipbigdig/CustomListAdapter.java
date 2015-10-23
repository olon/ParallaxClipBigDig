package com.tsukanov.vladimir.parallaxclipbigdig;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import loader.image.ImageLoader;


public class CustomListAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater inflater;
	private ArrayList<HashMap<String, String>> data;
	private ImageLoader imageLoader;
	private HashMap<String, String> resultp = new HashMap<String, String>();

	public CustomListAdapter(LayoutInflater inflater) {
		this.inflater = inflater;
	}

	public CustomListAdapter(Context context,
						   ArrayList<HashMap<String, String>> arrayList) {
		this.context = context;
		data = arrayList;
		imageLoader = new ImageLoader(context);
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView mTitleTextView, mNameTextView, mViewCountTextView;
		LinearLayout mPictureLinearLayout;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View itemView = inflater.inflate(R.layout.listview_item, parent, false);
		// Get the position
		resultp = data.get(position);

		// Locate the TextViews in listview_item.xml
		mTitleTextView = (TextView) itemView.findViewById(R.id.titleTextView);
		mNameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
		mViewCountTextView = (TextView) itemView.findViewById(R.id.viewCountTextView);

		// Locate the ImageView in listview_item.xml
		mPictureLinearLayout = (LinearLayout) itemView.findViewById(R.id.pictureLinearLayout);

		// Capture position and set results to the TextViews
		mTitleTextView.setText(resultp.get(MainActivity.TITLE));
		mNameTextView.setText(resultp.get(MainActivity.NAME));
		mViewCountTextView.setText(resultp.get(MainActivity.VIEW_COUNT));

		// Capture position and set results to the LinearLayout
		// Parses picture images URL into ImageLoader.class
		imageLoader.DisplayImage(resultp.get(MainActivity.PICTURE), mPictureLinearLayout);

		return itemView;
	}
}
