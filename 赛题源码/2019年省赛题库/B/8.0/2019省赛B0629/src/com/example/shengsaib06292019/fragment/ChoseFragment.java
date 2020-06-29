package com.example.shengsaib06292019.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shengsaib06292019.R;

public class ChoseFragment extends Fragment {
	private LinearLayout line_base;
	private LinearLayout line_draw;
	private LinearLayout line_mode;
	private LinearLayout line_link;
	private ImageView iv_base;
	private ImageView iv_draw;
	private ImageView iv_mode;
	private ImageView iv_link;
	public static TextView tv_time;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_chose, null, false);
		initView(view);
		iv_base.setVisibility(View.VISIBLE);
		iv_draw.setVisibility(View.INVISIBLE);
		iv_link.setVisibility(View.INVISIBLE);
		iv_mode.setVisibility(View.INVISIBLE);
		line_base.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				iv_base.setVisibility(View.VISIBLE);
				iv_draw.setVisibility(View.INVISIBLE);
				iv_link.setVisibility(View.INVISIBLE);
				iv_mode.setVisibility(View.INVISIBLE);
				BarActivity.mViewPager.setCurrentItem(1);
			}
		});
		line_link.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				iv_base.setVisibility(View.INVISIBLE);
				iv_draw.setVisibility(View.INVISIBLE);
				iv_link.setVisibility(View.VISIBLE);
				iv_mode.setVisibility(View.INVISIBLE);
				BarActivity.mViewPager.setCurrentItem(2);
			}
		});
		line_mode.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				iv_base.setVisibility(View.INVISIBLE);
				iv_draw.setVisibility(View.INVISIBLE);
				iv_link.setVisibility(View.INVISIBLE);
				iv_mode.setVisibility(View.VISIBLE);
				BarActivity.mViewPager.setCurrentItem(3);
			}
		});
		line_draw.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				iv_base.setVisibility(View.INVISIBLE);
				iv_draw.setVisibility(View.VISIBLE);
				iv_link.setVisibility(View.INVISIBLE);
				iv_mode.setVisibility(View.INVISIBLE);
				BarActivity.mViewPager.setCurrentItem(4);
			}
		});
		return view;
	}

	private void initView(View view) {
		// TODO Auto-generated method stub
		tv_time = (TextView) view.findViewById(R.id.tv_chose_time);
		line_base = (LinearLayout) view.findViewById(R.id.line_base);
		line_draw = (LinearLayout) view.findViewById(R.id.line_draw);
		line_link = (LinearLayout) view.findViewById(R.id.line_link);
		line_mode = (LinearLayout) view.findViewById(R.id.line_mode);
		iv_base = (ImageView) view.findViewById(R.id.iv_base);
		iv_draw = (ImageView) view.findViewById(R.id.iv_draw);
		iv_link = (ImageView) view.findViewById(R.id.iv_link);
		iv_mode = (ImageView) view.findViewById(R.id.iv_mode);
	}
}
