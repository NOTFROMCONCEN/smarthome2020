package com.example.shengsai2019a0914;

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
import com.example.shengsai2019a0914.tools.AppConfig;
import com.example.shengsai2019a0914.tools.DiyToast;

public class LinkActivity extends Activity {

	private Spinner sp_1;
	private Spinner sp_2;
	private Spinner sp_3;
	private Spinner sp_4;
	private EditText et_1;
	private Switch sw_1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_link);
		setTitle("联动管理");
		initView();
		sw_1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (et_1.getText().toString().isEmpty()) {
						DiyToast.showToast(getApplicationContext(), "请输入数值");
						sw_1.setChecked(false);
					}
				}
			}
		});
		handler.post(timeRunnable);
	}

	private void open() {
		// TODO Auto-generated method stub
		System.out.println("打开");
		if (sp_3.getSelectedItem().toString().equals("风扇")) {
			ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL,
					ConstantUtil.OPEN);
		}
		if (sp_3.getSelectedItem().toString().equals("射灯")) {
			ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL,
					ConstantUtil.OPEN);
		}
		if (sp_3.getSelectedItem().toString().equals("窗帘")) {
			ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_1,
					ConstantUtil.OPEN);
		}
	}

	private void close() {
		// TODO Auto-generated method stub
		System.out.println("关闭");
		if (sp_3.getSelectedItem().toString().equals("风扇")) {
			ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL,
					ConstantUtil.CLOSE);
		}
		if (sp_3.getSelectedItem().toString().equals("射灯")) {
			ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL,
					ConstantUtil.CLOSE);
		}
		if (sp_3.getSelectedItem().toString().equals("窗帘")) {
			ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_2,
					ConstantUtil.OPEN);
		}
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (sw_1.isChecked()) {
				if (et_1.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "请输入数值");
					sw_1.setChecked(false);
				} else {
					if (sp_2.getSelectedItem().toString().equals("<")) {
						if (sp_1.getSelectedItem().toString().equals("照度")) {
							if (AppConfig.ill < Integer.valueOf(et_1.getText()
									.toString())) {
								if (sp_4.getSelectedItem().toString()
										.equals("开")) {
									open();
								} else {
									close();
								}
							} else {
								close();
							}
						}
						if (sp_1.getSelectedItem().toString().equals("湿度")) {
							if (AppConfig.hum < Integer.valueOf(et_1.getText()
									.toString())) {
								if (sp_4.getSelectedItem().toString()
										.equals("开")) {
									open();
								} else {
									close();
								}
							} else {
								close();
							}
						}
						if (sp_1.getSelectedItem().toString().equals("温度")) {
							if (AppConfig.temp < Integer.valueOf(et_1.getText()
									.toString())) {
								if (sp_4.getSelectedItem().toString()
										.equals("开")) {
									open();
								} else {
									close();
								}
							} else {
								close();
							}
						}
					}
					if (sp_2.getSelectedItem().toString().equals(">")) {
						if (sp_1.getSelectedItem().toString().equals("照度")) {
							if (AppConfig.ill > Integer.valueOf(et_1.getText()
									.toString())) {
								if (sp_4.getSelectedItem().toString()
										.equals("开")) {
									open();
								} else {
									close();
								}
							} else {
								close();
							}
						}
						if (sp_1.getSelectedItem().toString().equals("湿度")) {
							if (AppConfig.hum > Integer.valueOf(et_1.getText()
									.toString())) {
								if (sp_4.getSelectedItem().toString()
										.equals("开")) {
									open();
								} else {
									close();
								}
							} else {
								close();
							}
						}
						if (sp_1.getSelectedItem().toString().equals("温度")) {
							if (AppConfig.temp > Integer.valueOf(et_1.getText()
									.toString())) {
								if (sp_4.getSelectedItem().toString()
										.equals("开")) {
									open();
								} else {
									close();
								}
							} else {
								close();
							}
						}
					}
				}
			}
			handler.postDelayed(timeRunnable, 3000);
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
		((TextView) findViewById(R.id.tv_link_roomnumber)).setText("房间号："
				+ AppConfig.room_number);
		sp_1 = (Spinner) findViewById(R.id.spinner1);
		sp_2 = (Spinner) findViewById(R.id.spinner2);
		sp_3 = (Spinner) findViewById(R.id.spinner3);
		sp_4 = (Spinner) findViewById(R.id.spinner4);
		et_1 = (EditText) findViewById(R.id.et_number);
		sw_1 = (Switch) findViewById(R.id.switch1);
	}
}
