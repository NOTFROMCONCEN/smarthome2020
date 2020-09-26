package com.example.guosaic20190925.fragment;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.example.guosaic20190925.R;
import com.example.guosaic20190925.tools.DiyToast;

public class LinkFragment extends Fragment {

	private EditText et_number, et_time;
	private Button btn_link_state;
	private Switch sw_warm, sw_door, sw_lamp, sw_fan;
	private Spinner sp_1, sp_2;
	private TextView tv_donw_time;
	private CountDownTimer countDownTimer;
	private boolean link_state = false, is_link = false;
	private long min = 0, sec = 0, num = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_link, null, false);
		initView(view);
		sw_door.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (link_state) {
						ControlUtils.control(ConstantUtil.RFID_Open_Door,
								ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
					}
				} else {

				}
			}
		});
		sw_fan.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (link_state) {
						ControlUtils.control(ConstantUtil.Fan,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					}
				} else {
					ControlUtils.control(ConstantUtil.Fan,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
		});
		sw_lamp.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (link_state) {
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					}
				} else {
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
		});
		sw_warm.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (link_state) {
						ControlUtils.control(ConstantUtil.WarningLight,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					}
				} else {
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
		});
		btn_link_state.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (et_number.getText().toString().isEmpty()) {
					DiyToast.showToast(getActivity(), "请输入数值");
					link_state = false;
					is_link = false;
				} else {
					if (et_number.getText().toString().isEmpty()
							&& et_time.getText().toString().isEmpty()) {
						DiyToast.showToast(getActivity(), "不能有空项目");
						is_link = false;
					} else {
						if (btn_link_state.getText().toString().equals("开启联动")) {
							check_data();
							btn_link_state.setText("关闭联动");
							is_link = true;
						} else if (btn_link_state.getText().toString()
								.equals("关闭联动")) {
							if (countDownTimer != null) {
								countDownTimer.cancel();
							}
							link_state = false;
							is_link = false;
							btn_link_state.setText("开启联动");
							tv_donw_time.setText("联动模式还有X分X秒");
							close();
						}
					}
				}

			}
		});
		return view;
	}

	private void open() {
		// TODO Auto-generated method stub
		if (sw_door.isChecked()) {
			ControlUtils.control(ConstantUtil.RFID_Open_Door,
					ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
		}
		if (sw_lamp.isChecked()) {
			ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL,
					ConstantUtil.OPEN);
		}
		if (sw_fan.isChecked()) {
			ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL,
					ConstantUtil.OPEN);
		}
		if (sw_warm.isChecked()) {
			ControlUtils.control(ConstantUtil.WarningLight,
					ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
		}
	}

	private void close() {
		// TODO Auto-generated method stub
		if (sw_door.isChecked()) {
			sw_door.setChecked(false);
		}
		if (sw_lamp.isChecked()) {
			sw_lamp.setChecked(false);
			ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL,
					ConstantUtil.CLOSE);
		}
		if (sw_fan.isChecked()) {
			sw_fan.setChecked(false);
			ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL,
					ConstantUtil.CLOSE);
		}
		if (sw_warm.isChecked()) {
			sw_warm.setChecked(false);
			ControlUtils.control(ConstantUtil.WarningLight,
					ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
		}
	}

	private void initView(View view) {
		// TODO Auto-generated method stub
		et_number = (EditText) view.findViewById(R.id.et_number);
		et_time = (EditText) view.findViewById(R.id.et_time);
		btn_link_state = (Button) view.findViewById(R.id.btn_link_state);
		sp_1 = (Spinner) view.findViewById(R.id.sp_1);
		sp_2 = (Spinner) view.findViewById(R.id.sp_2);
		sw_door = (Switch) view.findViewById(R.id.sw_door);
		sw_fan = (Switch) view.findViewById(R.id.sw_fan);
		sw_warm = (Switch) view.findViewById(R.id.sw_warm);
		sw_lamp = (Switch) view.findViewById(R.id.sw_lamp);
		tv_donw_time = (TextView) view.findViewById(R.id.tv_link_downtime);
	}

	private void check_data() {
		final String spinner_1 = sp_1.getSelectedItem().toString();
		final String spinner_2 = sp_2.getSelectedItem().toString();
		final int number_get = Integer.valueOf(et_number.getText().toString());
		if (spinner_1.equals("温度")) {
			if (spinner_2.equals(">")) {
				if (BaseFragment.temp > number_get) {
					link_state = true;
					btn_link_state.setText("关闭联动");
				} else {
					link_state = false;
					btn_link_state.setText("开启联动");
				}
			}
			if (spinner_2.equals("<=")) {
				if (BaseFragment.temp <= number_get) {
					link_state = true;
					btn_link_state.setText("关闭联动");
				} else {
					link_state = false;
					btn_link_state.setText("开启联动");
				}
			}
		}
		if (spinner_1.equals("光照")) {
			if (spinner_2.equals(">")) {
				if (BaseFragment.ill > number_get) {
					link_state = true;
					btn_link_state.setText("关闭联动");
				} else {
					link_state = false;
					btn_link_state.setText("开启联动");
				}
			}
			if (spinner_2.equals("<=")) {
				if (BaseFragment.ill <= number_get) {
					link_state = true;
					btn_link_state.setText("关闭联动");
				} else {
					link_state = false;
					btn_link_state.setText("开启联动");
				}
			}
		}
		if (spinner_1.equals("湿度")) {
			if (spinner_2.equals(">")) {
				if (BaseFragment.hum > number_get) {
					link_state = true;
					btn_link_state.setText("关闭联动");
				} else {
					link_state = false;
					btn_link_state.setText("开启联动");
				}
			}
			if (spinner_2.equals("<=")) {
				if (BaseFragment.hum <= number_get) {
					link_state = true;
					btn_link_state.setText("关闭联动");
				} else {
					link_state = false;
					btn_link_state.setText("开启联动");
				}
			}
		}
		if (link_state) {
			open();
			num = Integer.valueOf(et_time.getText().toString()) * 1000 * 60;
			countDownTimer = new CountDownTimer(num, 1000) {

				@Override
				public void onTick(long millisUntilFinished) {
					min = millisUntilFinished / 1000 / 60;
					sec = millisUntilFinished / 1000 % 60;
					tv_donw_time.setText("联动模式还有" + min + "分" + sec + "秒");
				}

				@Override
				public void onFinish() {
					if (countDownTimer != null) {
						countDownTimer.cancel();
					}
					tv_donw_time.setText("联动模式还有X分X秒");
					btn_link_state.setText("开启联动");
					link_state = false;
					close();
				}
			}.start();
		}
	}
}
