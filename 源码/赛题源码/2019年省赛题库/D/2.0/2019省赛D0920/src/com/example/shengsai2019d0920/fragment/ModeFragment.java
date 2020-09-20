package com.example.shengsai2019d0920.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ToggleButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.example.shengsai2019d0920.R;

public class ModeFragment extends Fragment {

	private RadioButton ra_day, ra_night, ra_fd;
	private ToggleButton tg_mode_state;
	private boolean day_mode = false, night_mode = false, fd_mode = false,
			mode_state = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_mode, null, false);
		initView(view);
		ra_night.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					night_mode = true;
					ControlUtils.control(ConstantUtil.Curtain,
							ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
					try {
						Thread.sleep(100);
					} catch (Exception e) {
						// TODO: handle exception
					}
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							while (night_mode && mode_state) {
								try {
									Thread.sleep(1000);
								} catch (Exception e) {
									// TODO: handle exception
								}
								if (BaseFragment.ill < 50) {
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
					night_mode = false;
				}
			}
		});
		ra_fd.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					fd_mode = true;
					new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							while (fd_mode && mode_state) {
								try {
									Thread.sleep(100);
								} catch (Exception e) {
									// TODO: handle exception
								}
								if (BaseFragment.per == 1) {
									ControlUtils.control(
											ConstantUtil.WarningLight,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								} else {
									ControlUtils.control(
											ConstantUtil.WarningLight,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
							}
						}
					}).start();
				} else {
					fd_mode = false;
				}
			}
		});
		ra_day.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					day_mode = true;
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							while (day_mode && mode_state) {
								try {
									Thread.sleep(5000);
								} catch (Exception e) {
									// TODO: handle exception
								}
								if (BaseFragment.ill > 150) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);
								} else {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_2,
											ConstantUtil.OPEN);
								}
							}
						}
					}).start();
				} else {
					day_mode = false;
				}
			}
		});
		tg_mode_state.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					mode_state = true;
				} else {
					mode_state = false;
				}
			}
		});
		return view;
	}

	private void initView(View view) {
		// TODO Auto-generated method stub
		tg_mode_state = (ToggleButton) view.findViewById(R.id.tg_mode_state);
		ra_day = (RadioButton) view.findViewById(R.id.ra_day_mode);
		ra_fd = (RadioButton) view.findViewById(R.id.ra_fd_mode);
		ra_night = (RadioButton) view.findViewById(R.id.ra_night_mode);
	}
}
