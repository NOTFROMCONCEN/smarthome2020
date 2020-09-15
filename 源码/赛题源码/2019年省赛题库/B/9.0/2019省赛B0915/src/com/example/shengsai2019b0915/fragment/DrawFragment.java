package com.example.shengsai2019b0915.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shengsai2019b0915.R;
import com.example.shengsai2019b0915.tools.TimeThread;

public class DrawFragment extends Fragment {
	public static TextView tv_time;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_draw, null, false);
		tv_time = (TextView) view.findViewById(R.id.tv_draw_time);
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
		return view;
	}
}
