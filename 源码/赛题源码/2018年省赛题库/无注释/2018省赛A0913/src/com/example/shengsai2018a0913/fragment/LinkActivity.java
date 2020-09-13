package com.example.shengsai2018a0913.fragment;

import android.app.AlertDialog;
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
import android.widget.ToggleButton;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.example.shengsai2018a0913.R;

public class LinkActivity extends Fragment {

	private CheckBox cb_lamp, cb_fan, cb_warm, cb_door, cb_tv, cb_dvd, cb_kt,
			cb_cur;
	private ToggleButton tg_link_state;
	private EditText et_link_number;
	private int number = 0;
	private Spinner sp_1, sp_2;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_link, null, false);
		initView(view);
		tg_link_state.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (et_link_number.getText().toString().isEmpty()) {
						new AlertDialog.Builder(getActivity()).setTitle("提示")
								.setMessage("请输入正确的阈值")
								.setPositiveButton("确定", null).show();
						tg_link_state.setChecked(false);
					} else {
						if (cb_cur.isChecked() || cb_door.isChecked()
								|| cb_dvd.isChecked() || cb_fan.isChecked()
								|| cb_kt.isChecked() || cb_lamp.isChecked()
								|| cb_tv.isChecked() || cb_warm.isChecked()) {
						} else {
							new AlertDialog.Builder(getActivity())
									.setTitle("提示").setMessage("请选择响应传感器")
									.setPositiveButton("确定", null).show();
							tg_link_state.setChecked(false);
						}
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
		if (cb_door.isChecked()) {
			ControlUtils.control(ConstantUtil.RFID_Open_Door,
					ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
		}
		if (cb_dvd.isChecked()) {
			ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "3",
					ConstantUtil.OPEN);
		}
		if (cb_fan.isChecked()) {
			ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL,
					ConstantUtil.OPEN);
		}
		if (cb_kt.isChecked()) {
			ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "2",
					ConstantUtil.OPEN);
		}
		if (cb_lamp.isChecked()) {
			ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL,
					ConstantUtil.OPEN);
		}
		if (cb_tv.isChecked()) {
			ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "1",
					ConstantUtil.OPEN);
		}
		if (cb_warm.isChecked()) {
			ControlUtils.control(ConstantUtil.WarningLight,
					ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
		}
	}

	private void close() {
		// TODO Auto-generated method stub
		if (cb_cur.isChecked()) {
			ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_2,
					ConstantUtil.OPEN);
		}
		if (cb_door.isChecked()) {
		}
		if (cb_dvd.isChecked()) {
			ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "3",
					ConstantUtil.OPEN);
		}
		if (cb_fan.isChecked()) {
			ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL,
					ConstantUtil.CLOSE);
		}
		if (cb_kt.isChecked()) {
			ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "2",
					ConstantUtil.OPEN);
		}
		if (cb_lamp.isChecked()) {
			ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL,
					ConstantUtil.CLOSE);
		}
		if (cb_tv.isChecked()) {
			ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "1",
					ConstantUtil.OPEN);
		}
		if (cb_warm.isChecked()) {
			ControlUtils.control(ConstantUtil.WarningLight,
					ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
		}
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (tg_link_state.isChecked()) {

				if (et_link_number.getText().toString().isEmpty()) {
					new AlertDialog.Builder(getActivity()).setTitle("提示")
							.setMessage("请输入正确的阈值")
							.setPositiveButton("确定", null).show();
					tg_link_state.setChecked(false);
				} else {
					if (cb_cur.isChecked() || cb_door.isChecked()
							|| cb_dvd.isChecked() || cb_fan.isChecked()
							|| cb_kt.isChecked() || cb_lamp.isChecked()
							|| cb_tv.isChecked() || cb_warm.isChecked()) {

						if (sp_2.getSelectedItem().toString().equals(">")) {
							if (sp_1.getSelectedItem().toString().equals("温度")) {
								if (BaseActivity.temp > Integer
										.valueOf(et_link_number.getText()
												.toString())) {
									open();
								} else {
									close();
								}
							}
							if (sp_1.getSelectedItem().toString().equals("湿度")) {
								if (BaseActivity.hum > Integer
										.valueOf(et_link_number.getText()
												.toString())) {
									open();
								} else {
									close();
								}
							}
							if (sp_1.getSelectedItem().toString().equals("光照")) {
								if (BaseActivity.ill > Integer
										.valueOf(et_link_number.getText()
												.toString())) {
									open();
								} else {
									close();
								}
							}
							if (sp_1.getSelectedItem().toString()
									.equals("PM2.5")) {
								if (BaseActivity.pm > Integer
										.valueOf(et_link_number.getText()
												.toString())) {
									open();
								} else {
									close();
								}
							}
						}
						if (sp_2.getSelectedItem().toString().equals("<")) {
							if (sp_1.getSelectedItem().toString().equals("温度")) {
								if (BaseActivity.temp < Integer
										.valueOf(et_link_number.getText()
												.toString())) {
									open();
								} else {
									close();
								}
							}
							if (sp_1.getSelectedItem().toString().equals("湿度")) {
								if (BaseActivity.hum < Integer
										.valueOf(et_link_number.getText()
												.toString())) {
									open();
								} else {
									close();
								}
							}
							if (sp_1.getSelectedItem().toString().equals("光照")) {
								if (BaseActivity.ill < Integer
										.valueOf(et_link_number.getText()
												.toString())) {
									open();
								} else {
									close();
								}
							}
							if (sp_1.getSelectedItem().toString()
									.equals("PM2.5")) {
								if (BaseActivity.pm < Integer
										.valueOf(et_link_number.getText()
												.toString())) {
									open();
								} else {
									close();
								}
							}
						}
						if (sp_2.getSelectedItem().toString().equals("=")) {
							if (sp_1.getSelectedItem().toString().equals("温度")) {
								if (BaseActivity.temp == Integer
										.valueOf(et_link_number.getText()
												.toString())) {
									open();
								} else {
									close();
								}
							}
							if (sp_1.getSelectedItem().toString().equals("湿度")) {
								if (BaseActivity.hum == Integer
										.valueOf(et_link_number.getText()
												.toString())) {
									open();
								} else {
									close();
								}
							}
							if (sp_1.getSelectedItem().toString().equals("光照")) {
								if (BaseActivity.ill == Integer
										.valueOf(et_link_number.getText()
												.toString())) {
									open();
								} else {
									close();
								}
							}
							if (sp_1.getSelectedItem().toString()
									.equals("PM2.5")) {
								if (BaseActivity.pm == Integer
										.valueOf(et_link_number.getText()
												.toString())) {
									open();
								} else {
									close();
								}
							}
						}

					} else {
						new AlertDialog.Builder(getActivity()).setTitle("提示")
								.setMessage("请选择响应传感器")
								.setPositiveButton("确定", null).show();
						tg_link_state.setChecked(false);
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
			number++;
			Message msg = handler.obtainMessage();
			handler.sendMessage(msg);
		}
	};

	private void initView(View view) {
		// TODO Auto-generated method stub
		sp_1 = (Spinner) view.findViewById(R.id.spinner1);
		sp_2 = (Spinner) view.findViewById(R.id.spinner2);
		et_link_number = (EditText) view.findViewById(R.id.et_number_get);
		tg_link_state = (ToggleButton) view.findViewById(R.id.tg_link_state);
		cb_cur = (CheckBox) view.findViewById(R.id.cb_cur);
		cb_door = (CheckBox) view.findViewById(R.id.cb_door);
		cb_dvd = (CheckBox) view.findViewById(R.id.cb_dvd);
		cb_fan = (CheckBox) view.findViewById(R.id.cb_fan);
		cb_kt = (CheckBox) view.findViewById(R.id.cb_kongtiao);
		cb_lamp = (CheckBox) view.findViewById(R.id.cb_lamp);
		cb_tv = (CheckBox) view.findViewById(R.id.cb_tv);
		cb_warm = (CheckBox) view.findViewById(R.id.cb_warm);
	}
}
