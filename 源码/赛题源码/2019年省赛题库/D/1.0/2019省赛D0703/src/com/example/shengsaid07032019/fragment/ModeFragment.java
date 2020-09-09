package com.example.shengsaid07032019.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.ToggleButton;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.example.shengsaid07032019.R;
import com.example.shengsaid07032019.tools.AppTools;

public class ModeFragment extends Fragment {

	private ToggleButton tg_mode_state;
	private RadioButton ra_day, ra_night, ra_fd;
	private boolean is_mode = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_mode, null, false);
		initView(view);
		tg_mode_state.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					is_mode = true;
				} else {
					is_mode = false;
				}
			}
		});
		night_mode();
		day_mode();
		fd_mode();
		return view;
	}

	private void night_mode() {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.Lamp,
						ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				try {
					Thread.sleep(500);
				} catch (Exception e) {
					// TODO: handle exception
				}
				ControlUtils.control(ConstantUtil.Curtain,
						ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
				while (is_mode && ra_night.isChecked()) {
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						// TODO: handle exception
					}
					if (AppTools.ill < 50) {
						ControlUtils.control(ConstantUtil.Fan,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					} else {
						ControlUtils.control(ConstantUtil.Fan,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					}
				}
			}
		}).start();
	}

	private void day_mode() {
		// TODO Auto-generated method stub
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
				while (is_mode && ra_day.isChecked()) {
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						// TODO: handle exception
					}
					if (AppTools.ill > 150) {
						ControlUtils.control(ConstantUtil.Curtain,
								ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
					} else {
						ControlUtils.control(ConstantUtil.Curtain,
								ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
					}
				}
			}
		}).start();
	}

	private void fd_mode() {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (is_mode && ra_fd.isChecked()) {
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						// TODO: handle exception
					}
					if (AppTools.per == 1) {
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
						ControlUtils.control(ConstantUtil.WarningLight,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					} else {
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
						ControlUtils.control(ConstantUtil.WarningLight,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					}
				}
			}
		}).start();
	}

	private void initView(View view) {
		// TODO Auto-generated method stub
		tg_mode_state = (ToggleButton) view.findViewById(R.id.tg_mode_state);
		ra_day = (RadioButton) view.findViewById(R.id.ra_day_mode);
		ra_fd = (RadioButton) view.findViewById(R.id.ra_fd_mode);
		ra_night = (RadioButton) view.findViewById(R.id.ra_night_mode);
	}
}