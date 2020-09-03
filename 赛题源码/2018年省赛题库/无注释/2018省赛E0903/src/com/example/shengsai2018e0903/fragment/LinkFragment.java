package com.example.shengsai2018e0903.fragment;

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
import com.example.shengsai2018e0903.R;
import com.example.shengsai2018e0903.tools.DiyToast;

public class LinkFragment extends Fragment {

	private CheckBox cb_af_mode, cb_lj_mode, cb_diy_mode;
	private boolean link_state = false, af_mode = false, lj_mode = false;
	private Spinner sp_1;
	private EditText et_number;
	private Switch sw_link_state;
	private CheckBox cb_lamp, cb_fan, cb_td1, cb_td2, cb_td3, cb_warm, cb_door,
			cb_cur;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_link, null, false);
		initView(view);
		cb_af_mode.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					af_mode = true;
				} else {
					af_mode = false;
				}
			}
		});
		cb_lj_mode.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					lj_mode = true;
				} else {
					lj_mode = false;
				}
			}
		});
		sw_link_state.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (et_number.getText().toString().isEmpty()) {
						DiyToast.showToast(getActivity(), "请输入数值");
						sw_link_state.setChecked(false);
						link_state = false;
					} else {
						if (cb_diy_mode.isChecked()) {
							link_state = true;
						} else {
							DiyToast.showToast(getActivity(), "请勾选自定义模式");
							link_state = false;
							sw_link_state.setChecked(false);
						}
					}
				} else {
					link_state = false;
				}
			}
		});
		handler.post(timeRunnable);
		return view;
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (af_mode) {
				if (BaseFragment.per == 1) {
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				} else {
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
			if (lj_mode) {
				if (BaseFragment.per == 1 || BaseFragment.gas >= 800) {
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				} else {
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
			if (link_state) {
				if (sp_1.getSelectedItem().toString().equals(">")) {
					if (BaseFragment.temp > Integer.valueOf(et_number.getText()
							.toString())) {
						if (cb_cur.isChecked()) {
							ControlUtils.control(ConstantUtil.Curtain,
									ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
						}
						if (cb_door.isChecked()) {
							ControlUtils.control(ConstantUtil.RFID_Open_Door,
									ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
						}
						if (cb_fan.isChecked()) {
							ControlUtils
									.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
						}
						if (cb_lamp.isChecked()) {
							ControlUtils
									.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
						}
						if (cb_td1.isChecked()) {
							ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
									ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
						}
						if (cb_td2.isChecked()) {
							ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
									ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
						}
						if (cb_td3.isChecked()) {
							ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
									ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
						}
						if (cb_warm.isChecked()) {
							ControlUtils
									.control(ConstantUtil.WarningLight,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
						}
					} else {
						if (cb_cur.isChecked()) {
							ControlUtils.control(ConstantUtil.Curtain,
									ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
						}
						if (cb_door.isChecked()) {
						}
						if (cb_fan.isChecked()) {
							ControlUtils.control(ConstantUtil.Fan,
									ConstantUtil.CHANNEL_ALL,
									ConstantUtil.CLOSE);
						}
						if (cb_lamp.isChecked()) {
							ControlUtils.control(ConstantUtil.Lamp,
									ConstantUtil.CHANNEL_ALL,
									ConstantUtil.CLOSE);
						}
						if (cb_td1.isChecked()) {
							ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
									ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
						}
						if (cb_td2.isChecked()) {
							ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
									ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
						}
						if (cb_td3.isChecked()) {
							ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
									ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
						}
						if (cb_warm.isChecked()) {
							ControlUtils.control(ConstantUtil.WarningLight,
									ConstantUtil.CHANNEL_ALL,
									ConstantUtil.CLOSE);
						}
					}
				}
				if (sp_1.getSelectedItem().toString().equals("<=")) {
					if (BaseFragment.temp <= Integer.valueOf(et_number
							.getText().toString())) {
						if (cb_cur.isChecked()) {
							ControlUtils.control(ConstantUtil.Curtain,
									ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
						}
						if (cb_door.isChecked()) {
							ControlUtils.control(ConstantUtil.RFID_Open_Door,
									ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
						}
						if (cb_fan.isChecked()) {
							ControlUtils
									.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
						}
						if (cb_lamp.isChecked()) {
							ControlUtils
									.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
						}
						if (cb_td1.isChecked()) {
							ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
									ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
						}
						if (cb_td2.isChecked()) {
							ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
									ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
						}
						if (cb_td3.isChecked()) {
							ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
									ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
						}
						if (cb_warm.isChecked()) {
							ControlUtils
									.control(ConstantUtil.WarningLight,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
						}
					} else {
						if (cb_cur.isChecked()) {
							ControlUtils.control(ConstantUtil.Curtain,
									ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
						}
						if (cb_door.isChecked()) {
						}
						if (cb_fan.isChecked()) {
							ControlUtils.control(ConstantUtil.Fan,
									ConstantUtil.CHANNEL_ALL,
									ConstantUtil.CLOSE);
						}
						if (cb_lamp.isChecked()) {
							ControlUtils.control(ConstantUtil.Lamp,
									ConstantUtil.CHANNEL_ALL,
									ConstantUtil.CLOSE);
						}
						if (cb_td1.isChecked()) {
							ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
									ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
						}
						if (cb_td2.isChecked()) {
							ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
									ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
						}
						if (cb_td3.isChecked()) {
							ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
									ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
						}
						if (cb_warm.isChecked()) {
							ControlUtils.control(ConstantUtil.WarningLight,
									ConstantUtil.CHANNEL_ALL,
									ConstantUtil.CLOSE);
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
		sp_1 = (Spinner) view.findViewById(R.id.spinner1);
		et_number = (EditText) view.findViewById(R.id.eet_number_get);
		sw_link_state = (Switch) view.findViewById(R.id.sw_link_state);
		cb_af_mode = (CheckBox) view.findViewById(R.id.cb_anfang_mode);
		cb_cur = (CheckBox) view.findViewById(R.id.cb_cur);
		cb_diy_mode = (CheckBox) view.findViewById(R.id.cb_diy_mode);
		cb_door = (CheckBox) view.findViewById(R.id.cb_door);
		cb_fan = (CheckBox) view.findViewById(R.id.cb_fan);
		cb_lamp = (CheckBox) view.findViewById(R.id.cb_lamp);
		cb_lj_mode = (CheckBox) view.findViewById(R.id.cb_lijia_mode);
		cb_td1 = (CheckBox) view.findViewById(R.id.cb_td_1);
		cb_td2 = (CheckBox) view.findViewById(R.id.cb_td_2);
		cb_td3 = (CheckBox) view.findViewById(R.id.cb_td_3);
		cb_warm = (CheckBox) view.findViewById(R.id.cb_warm);
	}
}
