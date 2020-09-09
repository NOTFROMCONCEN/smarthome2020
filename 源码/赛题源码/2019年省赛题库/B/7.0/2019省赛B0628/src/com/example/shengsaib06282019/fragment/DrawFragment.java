package com.example.shengsaib06282019.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shengsaib06282019.R;

public class DrawFragment extends Fragment {
	public static TextView tv_time;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_draw, null, false);
		// °ó¶¨
		tv_time = (TextView) view.findViewById(R.id.tv_draw_time);
		return view;
	}
}
