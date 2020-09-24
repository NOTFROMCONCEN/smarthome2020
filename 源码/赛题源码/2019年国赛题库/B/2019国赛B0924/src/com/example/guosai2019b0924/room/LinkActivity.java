package com.example.guosai2019b0924.room;

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

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.example.guosai2019b0924.R;
import com.example.guosai2019b0924.tools.AppConfig;
import com.example.guosai2019b0924.tools.DiyToast;

/**
 * 联动模式界面
 * 
 * @author 10976
 * 
 */
public class LinkActivity extends Activity {

	private Spinner sp_1, sp_2, sp_3, sp_4;
	private Switch sw_link_state;
	private EditText et_number;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_link);
		((TextView) findViewById(R.id.tv_link_roomnumber)).setText("房间号："
				+ AppConfig.room_number);
		initView();
		sw_link_state.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (et_number.getText().toString().isEmpty()) {
						DiyToast.showToast(getApplicationContext(), "请输入数值");
						sw_link_state.setChecked(false);
					}
				}
			}
		});
		handler.post(timeRunnable);
	}

	private void open() {
		// TODO Auto-generated method stub
		int i = 0;
		if (sp_3.getSelectedItem().toString().equals("风扇")) {
			ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL,
					ConstantUtil.OPEN);
		}
		if (sp_3.getSelectedItem().toString().equals("射灯")) {
			ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL,
					ConstantUtil.OPEN);
		}
		if (sp_3.getSelectedItem().toString().equals("窗帘")) {
			i++;
			if (i < 2) {
				ControlUtils.control(ConstantUtil.Curtain,
						ConstantUtil.CHANNEL_1, ConstantUtil.CLOSE);
			}
		}
	}

	private void close() {
		// TODO Auto-generated method stub
		int i = 0;
		if (sp_3.getSelectedItem().toString().equals("风扇")) {
			ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL,
					ConstantUtil.CLOSE);
		}
		if (sp_3.getSelectedItem().toString().equals("射灯")) {
			ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL,
					ConstantUtil.CLOSE);
		}
		if (sp_3.getSelectedItem().toString().equals("窗帘")) {
			i++;
			if (i < 2) {
				ControlUtils.control(ConstantUtil.Curtain,
						ConstantUtil.CHANNEL_2, ConstantUtil.CLOSE);
			}
		}
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (sw_link_state.isChecked()) {
				if (et_number.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "请输入数值");
					sw_link_state.setChecked(false);
				} else {
					if (sp_2.getSelectedItem().toString().equals(">")) {
						if (sp_1.getSelectedItem().toString().equals("照度")) {
							if (AppConfig.ill > Integer.valueOf(et_number
									.getText().toString())) {
								if (sp_4.getSelectedItem().toString()
										.equals("开")) {
									open();
								} else {
									close();
								}
							}
						}
						if (sp_1.getSelectedItem().toString().equals("湿度")) {
							if (AppConfig.hum > Integer.valueOf(et_number
									.getText().toString())) {
								if (sp_4.getSelectedItem().toString()
										.equals("开")) {
									open();
								} else {
									close();
								}
							}
						}
						if (sp_1.getSelectedItem().toString().equals("温度")) {
							if (AppConfig.temp > Integer.valueOf(et_number
									.getText().toString())) {
								if (sp_4.getSelectedItem().toString()
										.equals("开")) {
									open();
								} else {
									close();
								}
							}
						}
					}
					if (sp_2.getSelectedItem().toString().equals("<")) {
						if (sp_1.getSelectedItem().toString().equals("照度")) {
							if (AppConfig.ill < Integer.valueOf(et_number
									.getText().toString())) {
								if (sp_4.getSelectedItem().toString()
										.equals("开")) {
									open();
								} else {
									close();
								}
							}
						}
						if (sp_1.getSelectedItem().toString().equals("湿度")) {
							if (AppConfig.hum < Integer.valueOf(et_number
									.getText().toString())) {
								if (sp_4.getSelectedItem().toString()
										.equals("开")) {
									open();
								} else {
									close();
								}
							}
						}
						if (sp_1.getSelectedItem().toString().equals("温度")) {
							if (AppConfig.temp < Integer.valueOf(et_number
									.getText().toString())) {
								if (sp_4.getSelectedItem().toString()
										.equals("开")) {
									open();
								} else {
									close();
								}
							}
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
		sp_1 = (Spinner) findViewById(R.id.spinner1);
		sp_2 = (Spinner) findViewById(R.id.spinner2);
		sp_3 = (Spinner) findViewById(R.id.spinner3);
		sp_4 = (Spinner) findViewById(R.id.spinner4);
		et_number = (EditText) findViewById(R.id.et_number);
		sw_link_state = (Switch) findViewById(R.id.switch1);
	}
}
