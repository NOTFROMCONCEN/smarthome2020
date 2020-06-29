package com.example.shengsaib06292019.fragment;

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
import com.example.shengsaib06292019.R;
import com.example.shengsaib06292019.tools.AppConfig;
import com.example.shengsaib06292019.tools.DiyToast;

public class LinkFragment extends Fragment {
	private Switch sw_warm, sw_lamp, sw_door, sw_fan;
	private boolean link_state = false;
	private CountDownTimer timer;
	private Button btn_link_state;
	private EditText et_link_data, et_link_time;
	private Spinner sp_1, sp_2;
	private long num, min, sec;
	public static TextView tv_link_counttime, tv_time;

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
				// TODO Auto-generated method stub

				// TODO Auto-generated method stub
				if (et_link_data.getText().toString().isEmpty()) {
					DiyToast.showToast(getActivity(), "请输入数值");
					link_state = false;
				} else {
					if (et_link_data.getText().toString().isEmpty()
							&& et_link_time.getText().toString().isEmpty()) {
						DiyToast.showToast(getActivity(), "不能有空项目");
					} else {
						if (btn_link_state.getText().toString().equals("开启联动")) {
							check_data();
						} else if (btn_link_state.getText().toString()
								.equals("关闭联动")) {
							if (timer != null) {
								timer.cancel();
							}
							link_state = false;
							btn_link_state.setText("开启联动");
							tv_link_counttime.setText("联动模式还有X分X秒");
							close();
						}
					}
				}

			}
		});
		return view;
	}

	private void check_data() {
		// TODO Auto-generated method stub
		final String spinner_1 = sp_1.getSelectedItem().toString();
		final String spinner_2 = sp_2.getSelectedItem().toString();
		final int number_get = Integer.valueOf(et_link_data.getText()
				.toString());
		// TODO Auto-generated method stub
		// TODO Auto-generated
		// method stub
		if (spinner_1.equals("温度")) {
			if (spinner_2.equals(">")) {
				if (AppConfig.temp > number_get) {
					link_state = true;
					getActivity().runOnUiThread(new Runnable() {

						@Override
						public void run() {
							btn_link_state.setText("关闭联动");
						}
					});
				} else {
					link_state = false;
					getActivity().runOnUiThread(new Runnable() {

						@Override
						public void run() {
							// TODO
							// Auto-generated
							// method
							// stub
							DiyToast.showToast(getActivity(), "条件不满足");
							close();
							btn_link_state.setText("开启联动");
						}
					});

				}
			}
			if (spinner_2.equals("<=")) {
				if (AppConfig.temp <= number_get) {
					link_state = true;
					getActivity().runOnUiThread(new Runnable() {

						@Override
						public void run() {
							// TODO
							// Auto-generated
							// method
							// stub
							btn_link_state.setText("关闭联动");
						}
					});
				} else {
					link_state = false;
					getActivity().runOnUiThread(new Runnable() {

						@Override
						public void run() {
							// TODO
							// Auto-generated
							// method
							// stub
							DiyToast.showToast(getActivity(), "条件不满足");
							close();
							btn_link_state.setText("开启联动");
						}
					});

				}
			}
		}
		if (spinner_1.equals("光照")) {
			if (spinner_2.equals(">")) {
				if (AppConfig.ill > number_get) {
					link_state = true;
					getActivity().runOnUiThread(new Runnable() {

						@Override
						public void run() {
							// TODO
							// Auto-generated
							// method
							// stub
							btn_link_state.setText("关闭联动");
						}
					});
				} else {
					link_state = false;
					getActivity().runOnUiThread(new Runnable() {

						@Override
						public void run() {
							// TODO
							// Auto-generated
							// method
							// stub
							DiyToast.showToast(getActivity(), "条件不满足");
							close();
							btn_link_state.setText("开启联动");
						}
					});

				}
			}
			if (spinner_2.equals("<=")) {
				if (AppConfig.ill <= number_get) {
					link_state = true;
					getActivity().runOnUiThread(new Runnable() {

						@Override
						public void run() {
							// TODO
							// Auto-generated
							// method
							// stub
							btn_link_state.setText("关闭联动");
						}
					});
				} else {
					link_state = false;
					getActivity().runOnUiThread(new Runnable() {

						@Override
						public void run() {
							// TODO
							// Auto-generated
							// method
							// stub
							DiyToast.showToast(getActivity(), "条件不满足");
							close();
							btn_link_state.setText("开启联动");
						}
					});

				}
			}
		}
		if (spinner_1.equals("湿度")) {
			if (spinner_2.equals(">")) {
				if (AppConfig.hum > number_get) {
					link_state = true;
					getActivity().runOnUiThread(new Runnable() {

						@Override
						public void run() {
							// TODO
							// Auto-generated
							// method
							// stub
							btn_link_state.setText("关闭联动");
						}
					});
				} else {
					link_state = false;
					getActivity().runOnUiThread(new Runnable() {

						@Override
						public void run() {
							// TODO
							// Auto-generated
							// method
							// stub
							DiyToast.showToast(getActivity(), "条件不满足");
							close();
							btn_link_state.setText("开启联动");
						}
					});
				}
			}
			if (spinner_2.equals("<=")) {
				if (AppConfig.hum <= number_get) {
					link_state = true;
					getActivity().runOnUiThread(new Runnable() {

						@Override
						public void run() {
							// TODO
							// Auto-generated
							// method
							// stub
							btn_link_state.setText("关闭联动");
						}
					});
				} else {
					link_state = false;
					getActivity().runOnUiThread(new Runnable() {

						@Override
						public void run() {
							// TODO
							// Auto-generated
							// method
							// stub
							DiyToast.showToast(getActivity(), "条件不满足");
							close();
							btn_link_state.setText("开启联动");
						}
					});
				}
			}
		}
		if (link_state) {
			open();
			num = Integer.valueOf(et_link_time.getText().toString()) * 1000 * 60;
			timer = new CountDownTimer(num, 1000) {

				@Override
				public void onTick(long millisUntilFinished) {
					// TODO Auto-generated method stub
					min = millisUntilFinished / 1000 / 60;
					sec = millisUntilFinished / 1000 % 60;
					tv_link_counttime.setText("联动模式还有" + min + "分" + sec + "秒");
				}

				@Override
				public void onFinish() {
					// TODO Auto-generated method stub
					if (timer != null) {
						timer.cancel();
					}
					tv_link_counttime.setText("联动模式还有X分X秒");
					btn_link_state.setText("开启联动");
					link_state = false;
					close();
				}
			}.start();
		}
	}

	private void open() {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(100);
				} catch (Exception e) {
					// TODO: handle exception
				}
				if (sw_door.isChecked()) {
					ControlUtils.control(ConstantUtil.RFID_Open_Door,
							ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
				}
				try {
					Thread.sleep(100);
				} catch (Exception e) {
					// TODO: handle exception
				}
				if (sw_fan.isChecked()) {
					ControlUtils.control(ConstantUtil.Fan,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				}
				try {
					Thread.sleep(100);
				} catch (Exception e) {
					// TODO: handle exception
				}
				if (sw_lamp.isChecked()) {
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				}
				try {
					Thread.sleep(100);
				} catch (Exception e) {
					// TODO: handle exception
				}
				if (sw_warm.isChecked()) {
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				}
			}
		}).start();
	}

	private void close() {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(100);
				} catch (Exception e) {
					// TODO: handle exception
				}
				if (sw_fan.isChecked()) {
					ControlUtils.control(ConstantUtil.Fan,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
				try {
					Thread.sleep(100);
				} catch (Exception e) {
					// TODO: handle exception
				}
				if (sw_lamp.isChecked()) {
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
				try {
					Thread.sleep(100);
				} catch (Exception e) {
					// TODO: handle exception
				}
				if (sw_warm.isChecked()) {
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
				getActivity().runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (sw_door.isChecked()) {
							sw_door.setChecked(false);
						}
						if (sw_fan.isChecked()) {
							sw_fan.setChecked(false);
						}
						if (sw_lamp.isChecked()) {
							sw_lamp.setChecked(false);
						}
						if (sw_warm.isChecked()) {
							sw_warm.setChecked(false);
						}
					}
				});
			}
		}).start();
	}

	private void initView(View view) {
		// TODO Auto-generated method stub
		sp_1 = (Spinner) view.findViewById(R.id.spinner1);
		sp_2 = (Spinner) view.findViewById(R.id.spinner2);
		et_link_data = (EditText) view.findViewById(R.id.et_link_data);
		et_link_time = (EditText) view.findViewById(R.id.et_link_time);
		btn_link_state = (Button) view.findViewById(R.id.btn_link_state);
		sw_door = (Switch) view.findViewById(R.id.sw_door);
		sw_fan = (Switch) view.findViewById(R.id.sw_fan);
		sw_lamp = (Switch) view.findViewById(R.id.sw_lamp);
		sw_warm = (Switch) view.findViewById(R.id.sw_warm);
		tv_link_counttime = (TextView) view
				.findViewById(R.id.tv_link_counttime);
		tv_time = (TextView) view.findViewById(R.id.tv_link_time);
	}
}