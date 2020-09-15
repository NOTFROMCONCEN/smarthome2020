package com.example.shengsai2019b0915.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shengsai2019b0915.R;
import com.example.shengsai2019b0915.tools.TimeThread;

public class MenuFragment extends Fragment {

	private LinearLayout ll_base;
	private LinearLayout ll_mode;
	private LinearLayout ll_link;
	private LinearLayout ll_draw;
	private ImageView iv_base;
	private ImageView iv_mode;
	private ImageView iv_link;
	private ImageView iv_draw;
	public static TextView tv_time;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_menu, null, false);
		initView(view);
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					try {
						Thread.sleep(500);
					} catch (Exception e) {
						// TODO: handle exception
					}
					getActivity().runOnUiThread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							tv_time.setText(TimeThread.time);
						}
					});
				}
			}
		}).start();
		iv_base.setVisibility(View.VISIBLE);
		iv_draw.setVisibility(View.INVISIBLE);
		iv_link.setVisibility(View.INVISIBLE);
		iv_mode.setVisibility(View.INVISIBLE);
		ll_base.setOnClickListener(new OnClickListener() {

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
		ll_draw.setOnClickListener(new OnClickListener() {

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
		ll_link.setOnClickListener(new OnClickListener() {

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
		ll_mode.setOnClickListener(new OnClickListener() {

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
		return view;
	}

	private void initView(View view) {
		// TODO Auto-generated method stub
		tv_time = (TextView) view.findViewById(R.id.tv_menu_time);
		ll_base = (LinearLayout) view.findViewById(R.id.ll_base);
		ll_draw = (LinearLayout) view.findViewById(R.id.ll_draw);
		ll_link = (LinearLayout) view.findViewById(R.id.ll_link);
		ll_mode = (LinearLayout) view.findViewById(R.id.ll_mode);
		iv_base = (ImageView) view.findViewById(R.id.iv_base);
		iv_draw = (ImageView) view.findViewById(R.id.iv_draw);
		iv_link = (ImageView) view.findViewById(R.id.iv_link);
		iv_mode = (ImageView) view.findViewById(R.id.iv_mode);
	}
}
