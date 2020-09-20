package com.example.shengsaic20190919.toools;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO ��Spinner�õ��Զ���Adapter
 * @package_name com.example.shengsaic20190919.toools
 * @project_name 2019ʡ��C0919
 * @file_name LinkAdapterHelper.java
 * @�ҵĲ��� https://naiyouhuameitang.club/
 */
public class LinkAdapterHelper extends ArrayAdapter<String> {

	// ����
	String[] mStrings;
	// Context
	Context mContext;

	/**
	 * ���췽��
	 * 
	 * @param context
	 * @param strings
	 */
	public LinkAdapterHelper(Context context, String[] strings) {
		super(context, android.R.layout.simple_spinner_item, strings);
		// TODO Auto-generated constructor stub
		mStrings = strings;
		mContext = context;
	}

	/**
	 * ������ͨView
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					android.R.layout.simple_spinner_item, parent, false);
		}
		TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
		tv.setText(mStrings[position]);
		tv.setTextColor(Color.WHITE);
		return convertView;
	}

	/**
	 * ����������View
	 */
	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					android.R.layout.simple_spinner_dropdown_item, parent,
					false);
		}
		TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
		tv.setText(mStrings[position]);
		tv.setTextColor(Color.BLACK);
		return convertView;
	}
}