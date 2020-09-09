package com.example.shengsaib06282019.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.example.shengsaib06282019.R;
import com.example.shengsaib06282019.tools.AppConfig;

public class ModeFragment extends Fragment {

	private ToggleButton tg_mode_state;
	private boolean mode_state = false;
	public static TextView tv_mode_time;
	private int number = 0;
	private RadioButton ra_day, ra_night, ra_dance, ra_fd;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_mode, null, false);
		initView(view);
		tg_mode_state.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					mode_state = true;
					check_mode();
				} else {
					mode_state = false;
				}
			}
		});
		ra_dance.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (mode_state) {
					check_mode();
				}
			}
		});
		ra_day.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (mode_state) {
					check_mode();
				}
			}
		});
		ra_fd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (mode_state) {
					check_mode();
				}
			}
		});
		ra_night.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (mode_state) {
					check_mode();
				}
			}
		});
		return view;
	}

	private void check_mode() {
		// TODO Auto-generated method stub
		if (ra_night.isChecked()) {
			ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_2,
					ConstantUtil.OPEN);
			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					while (ra_night.isChecked()) {
						try {
							Thread.sleep(500);
						} catch (Exception e) {
							// TODO: handle exception
						}
						if (AppConfig.ill > 500) {
							ControlUtils.control(ConstantUtil.Lamp,
									ConstantUtil.CHANNEL_ALL,
									ConstantUtil.CLOSE);
						}
						if (AppConfig.ill < 200) {
							ControlUtils.control(ConstantUtil.Lamp,
									ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
						}
					}
				}
			}).start();
		}
		if (ra_dance.isChecked()) {
			ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
					ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
			number = 0;
			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					while (ra_dance.isChecked()) {
						number++;
						try {
							Thread.sleep(2000);
						} catch (Exception e) {
							// TODO: handle exception
						}
						if (number % 2 == 0) {
							ControlUtils.control(ConstantUtil.Lamp,
									ConstantUtil.Lamp, ConstantUtil.OPEN);
							System.out.println("1");
						} else {
							ControlUtils.control(ConstantUtil.Lamp,
									ConstantUtil.Lamp, ConstantUtil.CLOSE);
							System.out.println("0");
						}
					}
				}
			}).start();
		}
		if (ra_fd.isChecked()) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					while (ra_fd.isChecked()) {
						try {
							Thread.sleep(500);
						} catch (Exception e) {
							// TODO: handle exception
						}
						if (AppConfig.per == 1) {
							ControlUtils
									.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							ControlUtils
									.control(ConstantUtil.WarningLight,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
						} else {
							ControlUtils.control(ConstantUtil.Lamp,
									ConstantUtil.CHANNEL_ALL,
									ConstantUtil.CLOSE);
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							ControlUtils.control(ConstantUtil.WarningLight,
									ConstantUtil.CHANNEL_ALL,
									ConstantUtil.CLOSE);
						}
					}
				}
			}).start();
		}
		if (ra_day.isChecked()) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					try {
						Thread.sleep(500);
					} catch (Exception e) {
						// TODO: handle exception
					}
					ControlUtils.control(ConstantUtil.Curtain,
							ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
					while (ra_day.isChecked()) {
						try {
							Thread.sleep(1000);
						} catch (Exception e) {
							// TODO: handle exception
						}
						System.out.println("111");
						if (AppConfig.smo > 400) {
							ControlUtils
									.control(ConstantUtil.Fan,
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
		}
	}

	private void initView(View view) {
		// TODO Auto-generated method stub
		tv_mode_time = (TextView) view.findViewById(R.id.tv_mode_time);
		tg_mode_state = (ToggleButton) view.findViewById(R.id.tg_mode_state);
		ra_dance = (RadioButton) view.findViewById(R.id.ra_mode_dance);
		ra_day = (RadioButton) view.findViewById(R.id.ra_mode_day);
		ra_fd = (RadioButton) view.findViewById(R.id.ra_mode_fd);
		ra_night = (RadioButton) view.findViewById(R.id.ra_mode_night);
	}
}
