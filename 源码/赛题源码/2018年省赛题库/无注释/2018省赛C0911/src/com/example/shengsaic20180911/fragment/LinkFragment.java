package com.example.shengsaic20180911.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.example.shengsaic20180911.R;
import com.example.shengsaic20180911.tools.DiyToast;

public class LinkFragment extends Fragment {

	private CheckBox cb_af, cb_lj, cb_diy, cb_td_1, cb_td_2, cb_td_3, cb_lamp,
			cb_warm, cb_door, cb_cur, cb_fan;
	private Spinner sp_1;
	private Switch sw_link_statw;
	private EditText et_number;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_link, null, false);
		initView(view);
		cb_af.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							while (cb_af.isChecked()) {
								try {
									Thread.sleep(500);
								} catch (Exception e) {
									// TODO: handle exception
								}
								System.out.println("fd");
								if (BaseFragment.per == 1) {
									ControlUtils.control(
											ConstantUtil.WarningLight,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								} else {
									ControlUtils.control(
											ConstantUtil.WarningLight,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
								}
							}
						}
					}).start();
				}
			}
		});
		cb_lj.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							while (cb_lj.isChecked()) {
								try {
									Thread.sleep(500);
								} catch (Exception e) {
									// TODO: handle exception
								}
								System.out.println("lj");
								if (BaseFragment.per == 1
										|| BaseFragment.gas > 800) {
									ControlUtils.control(
											ConstantUtil.WarningLight,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								} else {
									ControlUtils.control(
											ConstantUtil.WarningLight,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
								}
							}
						}
					}).start();
				}
			}
		});
		sw_link_statw.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (cb_diy.isChecked()) {
						if (et_number.getText().toString().isEmpty()) {
							DiyToast.showToast(getActivity(), "请输入数值");
							sw_link_statw.setChecked(false);
						}
					} else {
						DiyToast.showToast(getActivity(), "请勾选自定义模式");
						sw_link_statw.setChecked(false);
					}
				}
			}
		});
		handler.post(timeRunnable);
		return view;
	}

	private void open() {
		// TODO Auto-generated method stub
		if (cb_cur.isChecked()) {
			ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_1,
					ConstantUtil.OPEN);
		}
		if (cb_fan.isChecked()) {
			ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL,
					ConstantUtil.OPEN);
		}
		if (cb_warm.isChecked()) {
			ControlUtils.control(ConstantUtil.WarningLight,
					ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
		}
		if (cb_lamp.isChecked()) {
			ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL,
					ConstantUtil.OPEN);
		}
		if (cb_door.isChecked()) {
			ControlUtils.control(ConstantUtil.RFID_Open_Door,
					ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
		}
		if (cb_td_1.isChecked()) {
			ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
					ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
		}
		if (cb_td_2.isChecked()) {
			ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
					ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
		}
		if (cb_td_3.isChecked()) {
			ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
					ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
		}
	}

	private void close() {
		// TODO Auto-generated method stub
		if (cb_cur.isChecked()) {
			ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_2,
					ConstantUtil.OPEN);
		}
		if (cb_fan.isChecked()) {
			ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL,
					ConstantUtil.CLOSE);
		}
		if (cb_warm.isChecked()) {
			ControlUtils.control(ConstantUtil.WarningLight,
					ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
		}
		if (cb_lamp.isChecked()) {
			ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL,
					ConstantUtil.CLOSE);
		}
		if (cb_door.isChecked()) {
		}
		if (cb_td_1.isChecked()) {
			ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
					ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
		}
		if (cb_td_2.isChecked()) {
			ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
					ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
		}
		if (cb_td_3.isChecked()) {
			ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
					ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
		}
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (sw_link_statw.isChecked() && cb_diy.isChecked()) {
				if (et_number.getText().toString().isEmpty()) {
					DiyToast.showToast(getActivity(), "请输入数值");
					sw_link_statw.setChecked(false);
				} else {
					if (sp_1.getSelectedItem().toString().equals("<")) {
						if (BaseFragment.temp < Integer.valueOf(et_number
								.getText().toString())) {
							open();
						} else {
							close();
						}
					}
					if (sp_1.getSelectedItem().toString().equals(">")) {
						if (BaseFragment.temp > Integer.valueOf(et_number
								.getText().toString())) {
							open();
						} else {
							close();
						}
					}
					if (sp_1.getSelectedItem().toString().equals("=")) {
						if (BaseFragment.temp == Integer.valueOf(et_number
								.getText().toString())) {
							open();
						} else {
							close();
						}
					}
				}
			}
			handler.postDelayed(timeRunnable, 1000);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Message msg = handler.obtainMessage();
			handler.sendMessage(msg);
		}
	};

	private void initView(View view) {
		// TODO Auto-generated method stub
		sw_link_statw = (Switch) view.findViewById(R.id.sw_link_state);
		et_number = (EditText) view.findViewById(R.id.et_number_get);
		sp_1 = (Spinner) view.findViewById(R.id.spinner1);
		cb_af = (CheckBox) view.findViewById(R.id.cb_anfang_mode);
		cb_cur = (CheckBox) view.findViewById(R.id.cb_cur);
		cb_diy = (CheckBox) view.findViewById(R.id.cb_diy_mode);
		cb_door = (CheckBox) view.findViewById(R.id.cb_door);
		cb_fan = (CheckBox) view.findViewById(R.id.cb_fan);
		cb_lamp = (CheckBox) view.findViewById(R.id.cb_lamp);
		cb_lj = (CheckBox) view.findViewById(R.id.cb_lijia_mode);
		cb_td_1 = (CheckBox) view.findViewById(R.id.cb_td_1);
		cb_td_2 = (CheckBox) view.findViewById(R.id.cb_td_2);
		cb_td_3 = (CheckBox) view.findViewById(R.id.cb_td_3);
		cb_warm = (CheckBox) view.findViewById(R.id.cb_warm);
	}
}
