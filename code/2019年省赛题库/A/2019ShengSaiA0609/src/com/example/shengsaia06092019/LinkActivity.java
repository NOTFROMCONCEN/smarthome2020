package com.example.shengsaia06092019;

import javax.xml.transform.Templates;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class LinkActivity extends Activity {

	private TextView tv_link_number;
	private Spinner sp_1, sp_2, sp_3, sp_4;
	private EditText et_link_number;
	private Switch sw_link_state;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_link);
		setTitle("联动管理");
		initView();
		tv_link_number.setText(AppConfig.room_number);
		sw_link_state.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (et_link_number.getText().toString().isEmpty()) {
						DiyToast.showToast(getApplicationContext(), "请输入数值");
						sw_link_state.setChecked(false);
						handler.removeCallbacks(timeRunnable);
					} else {
						handler.post(timeRunnable);
					}
				} else {
					handler.removeCallbacks(timeRunnable);
				}
			}
		});
	}

	private void open() {
		// TODO Auto-generated method stub
		String spinner_3 = sp_3.getSelectedItem().toString();
		if (spinner_3.equals("射灯")) {
			ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL,
					ConstantUtil.OPEN);
		}
		if (spinner_3.equals("风扇")) {
			ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL,
					ConstantUtil.OPEN);
		}
		if (spinner_3.equals("窗帘")) {
			ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_1,
					ConstantUtil.OPEN);
		}
	}

	private void close() {
		// TODO Auto-generated method stub
		String spinner_3 = sp_3.getSelectedItem().toString();
		if (spinner_3.equals("射灯")) {
			ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL,
					ConstantUtil.CLOSE);
		}
		if (spinner_3.equals("风扇")) {
			ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL,
					ConstantUtil.CLOSE);
		}
		if (spinner_3.equals("窗帘")) {
			ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_2,
					ConstantUtil.OPEN);
		}
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (et_link_number.getText().toString().isEmpty()) {
				DiyToast.showToast(getApplicationContext(), "请输入数值");
				handler.removeCallbacks(timeRunnable);
				sw_link_state.setChecked(false);
			} else {
				String spinner_1 = sp_1.getSelectedItem().toString();
				String spinner_2 = sp_2.getSelectedItem().toString();
				String spinner_4 = sp_4.getSelectedItem().toString();
				if (spinner_1.equals("照度")) {
					if (spinner_2.equals(">")) {
						if (AppConfig.ill > Float.valueOf(et_link_number
								.getText().toString())) {
							if (spinner_4.equals("开")) {
								open();
							}
							if (spinner_4.equals("关")) {
								close();
							}
						} else {
							DiyToast.showToast(getApplicationContext(), "条件不满足");
							handler.removeCallbacks(timeRunnable);
							sw_link_state.setChecked(false);
						}
					}
					if (spinner_2.equals("<")) {
						if (AppConfig.ill < Float.valueOf(et_link_number
								.getText().toString())) {
							if (spinner_4.equals("开")) {
								open();
							}
							if (spinner_4.equals("关")) {
								close();
							}
						} else {
							DiyToast.showToast(getApplicationContext(), "条件不满足");
							handler.removeCallbacks(timeRunnable);
							sw_link_state.setChecked(false);
						}
					}
				}
				if (spinner_1.equals("湿度")) {
					if (spinner_2.equals(">")) {
						if (AppConfig.hum > Float.valueOf(et_link_number
								.getText().toString())) {
							if (spinner_4.equals("开")) {
								open();
							}
							if (spinner_4.equals("关")) {
								close();
							}
						} else {
							DiyToast.showToast(getApplicationContext(), "条件不满足");
							handler.removeCallbacks(timeRunnable);
							sw_link_state.setChecked(false);
						}
					}
					if (spinner_2.equals("<")) {
						if (AppConfig.hum < Float.valueOf(et_link_number
								.getText().toString())) {
							if (spinner_4.equals("开")) {
								open();
							}
							if (spinner_4.equals("关")) {
								close();
							}
						} else {
							DiyToast.showToast(getApplicationContext(), "条件不满足");
							handler.removeCallbacks(timeRunnable);
							sw_link_state.setChecked(false);
						}
					}
				}
				if (spinner_1.equals("温度")) {
					if (spinner_2.equals(">")) {
						if (AppConfig.temp > Float.valueOf(et_link_number
								.getText().toString())) {
							if (spinner_4.equals("开")) {
								open();
							}
							if (spinner_4.equals("关")) {
								close();
							}
						} else {
							DiyToast.showToast(getApplicationContext(), "条件不满足");
							handler.removeCallbacks(timeRunnable);
							sw_link_state.setChecked(false);
						}
					}
					if (spinner_2.equals("<")) {
						if (AppConfig.temp < Float.valueOf(et_link_number
								.getText().toString())) {
							if (spinner_4.equals("开")) {
								open();
							}
							if (spinner_4.equals("关")) {
								close();
							}
						} else {
							DiyToast.showToast(getApplicationContext(), "条件不满足");
							handler.removeCallbacks(timeRunnable);
							sw_link_state.setChecked(false);
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

	private void initView() {
		// TODO Auto-generated method stub
		tv_link_number = (TextView) findViewById(R.id.tv_link_roomnumber);
		sp_1 = (Spinner) findViewById(R.id.sp_1);
		sp_2 = (Spinner) findViewById(R.id.sp_2);
		sp_3 = (Spinner) findViewById(R.id.sp_3);
		sp_4 = (Spinner) findViewById(R.id.sp_4);
		et_link_number = (EditText) findViewById(R.id.et_link_number);
		sw_link_state = (Switch) findViewById(R.id.sw_link_state);
	}
}