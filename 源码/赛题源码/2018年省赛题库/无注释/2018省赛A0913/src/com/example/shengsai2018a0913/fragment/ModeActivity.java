package com.example.shengsai2018a0913.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.example.shengsai2018a0913.R;

public class ModeActivity extends Fragment {

	private CheckBox cb_dance, cb_fd, cb_day;
	private int number = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_mode, null, false);
		initView(view);
		cb_day.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					cb_dance.setChecked(false);
					cb_fd.setChecked(false);
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "2",
							ConstantUtil.OPEN);
					try {
						Thread.sleep(500);
					} catch (Exception e) {
						// TODO: handle exception
					}
					ControlUtils.control(ConstantUtil.Curtain,
							ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
					new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							while (cb_day.isChecked()) {
								try {
									Thread.sleep(1000);
								} catch (Exception e) {
									// TODO: handle exception
								}
								if (BaseActivity.pm > 75) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								} else {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
								}
							}
						}
					}).start();
				} else {

				}
			}
		});
		cb_fd.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					cb_dance.setChecked(false);
					cb_day.setChecked(false);
					new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							while (cb_fd.isChecked()) {
								try {
									Thread.sleep(1000);
								} catch (Exception e) {
									// TODO: handle exception
								}
								if (BaseActivity.per == 1) {
									ControlUtils.control(
											ConstantUtil.WarningLight,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								} else {
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
									ControlUtils.control(
											ConstantUtil.WarningLight,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
							}
						}
					}).start();
				} else {

				}
			}
		});
		cb_dance.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					number = 0;
					cb_day.setChecked(false);
					cb_fd.setChecked(false);
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "2",
							ConstantUtil.OPEN);
					new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							while (cb_dance.isChecked()) {
								try {
									Thread.sleep(2000);
								} catch (Exception e) {
									// TODO: handle exception
								}
								number++;
								if (number % 2 == 0) {
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								} else {
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
								}
							}
						}
					}).start();
				} else {

				}
			}
		});
		return view;
	}

	private void initView(View view) {
		// TODO Auto-generated method stub
		cb_dance = (CheckBox) view.findViewById(R.id.cb_dance_mode);
		cb_day = (CheckBox) view.findViewById(R.id.cb_day_mode);
		cb_fd = (CheckBox) view.findViewById(R.id.cb_fangdao_mode);
	}

}
