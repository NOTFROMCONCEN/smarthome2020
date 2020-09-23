package com.example.guosaia2019a0923.tools;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SpinnerBaseAdapter extends ArrayAdapter<String> {

	String[] mString;
	Context mContext;

	public SpinnerBaseAdapter(Context context, String[] strings) {
		super(context, android.R.layout.simple_spinner_item, strings);
		// TODO Auto-generated constructor stub
		this.mContext = context;
		this.mString = strings;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					android.R.layout.simple_spinner_item, null, false);
		}
		TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
		tv.setText(mString[position]);
		tv.setTextColor(Color.WHITE);
		return convertView;
	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					android.R.layout.simple_spinner_dropdown_item, null, false);
		}
		TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
		tv.setText(mString[position]);
		tv.setTextColor(Color.BLACK);
		return convertView;
	}

}
