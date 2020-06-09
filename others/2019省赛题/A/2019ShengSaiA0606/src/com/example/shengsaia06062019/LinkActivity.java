package com.example.shengsaia06062019;

import java.io.Console;

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

	private Spinner sp_1;
	private Spinner sp_2;
	private Spinner sp_3;
	private Spinner sp_4;
	private EditText et_number;
	private Switch sw_link;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_link);
		setTitle("联动管理");
		((TextView) findViewById(R.id.tv_link_roomnumber)).setText("房间号："
				+ AppConfig.room_number);
		initView();
		sw_link.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (et_number.getText().toString().isEmpty()) {
						DiyToast.showToast(getApplicationContext(), "请输入数值");
						sw_link.setChecked(false);
					} else {
						handler.post(timeRunnable);
					}
				} else {
					handler.removeCallbacks(timeRunnable);
				}
			}
		});
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (et_number.getText().toString().isEmpty()) {
				sw_link.setChecked(false);
				DiyToast.showToast(getApplicationContext(), "请输入数值");
				handler.removeCallbacks(timeRunnable);
			} else {

				if (sp_1.getSelectedItem().toString().equals("湿度")
						&& sp_2.getSelectedItem().toString().equals(">")) {
					if (AppConfig.hum > Float.valueOf(et_number.getText()
							.toString())) {
						if (sp_3.getSelectedItem().toString().equals("风扇")
								&& sp_4.getSelectedItem().toString()
										.equals("开")) {
							ControlUtils
									.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
						}
						if (sp_3.getSelectedItem().toString().equals("风扇")
								&& sp_4.getSelectedItem().toString()
										.equals("关")) {
							ControlUtils.control(ConstantUtil.Fan,
									ConstantUtil.CHANNEL_ALL,
									ConstantUtil.CLOSE);
						}
						if (sp_3.getSelectedItem().toString().equals("射灯")
								&& sp_4.getSelectedItem().toString()
										.equals("开")) {
							ControlUtils
									.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
						}
						if (sp_3.getSelectedItem().toString().equals("射灯")
								&& sp_4.getSelectedItem().toString()
										.equals("关")) {
							ControlUtils.control(ConstantUtil.Lamp,
									ConstantUtil.CHANNEL_ALL,
									ConstantUtil.CLOSE);
						}
						if (sp_3.getSelectedItem().toString().equals("窗帘")
								&& sp_4.getSelectedItem().toString()
										.equals("开")) {
							ControlUtils.control(ConstantUtil.Curtain,
									ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
						}
						if (sp_3.getSelectedItem().toString().equals("窗帘")
								&& sp_4.getSelectedItem().toString()
										.equals("关")) {
							ControlUtils.control(ConstantUtil.Curtain,
									ConstantUtil.CHANNEL_2, ConstantUtil.CLOSE);
						}
					} else {
						if (sp_3.getSelectedItem().toString().equals("风扇")
								&& sp_4.getSelectedItem().toString()
										.equals("开")) {
							ControlUtils
									.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
						}
						if (sp_3.getSelectedItem().toString().equals("风扇")
								&& sp_4.getSelectedItem().toString()
										.equals("关")) {
							ControlUtils.control(ConstantUtil.Fan,
									ConstantUtil.CHANNEL_ALL,
									ConstantUtil.CLOSE);
						}
						if (sp_3.getSelectedItem().toString().equals("射灯")
								&& sp_4.getSelectedItem().toString()
										.equals("开")) {
							ControlUtils
									.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
						}
						if (sp_3.getSelectedItem().toString().equals("射灯")
								&& sp_4.getSelectedItem().toString()
										.equals("关")) {
							ControlUtils.control(ConstantUtil.Lamp,
									ConstantUtil.CHANNEL_ALL,
									ConstantUtil.CLOSE);
						}
						if (sp_3.getSelectedItem().toString().equals("窗帘")
								&& sp_4.getSelectedItem().toString()
										.equals("开")) {
							ControlUtils.control(ConstantUtil.Curtain,
									ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
						}
						if (sp_3.getSelectedItem().toString().equals("窗帘")
								&& sp_4.getSelectedItem().toString()
										.equals("关")) {
							ControlUtils.control(ConstantUtil.Curtain,
									ConstantUtil.CHANNEL_2, ConstantUtil.CLOSE);
						}
					}
				}

				if (sp_1.getSelectedItem().toString().equals("温度")
						&& sp_2.getSelectedItem().toString().equals(">")) {
					if (AppConfig.temp > Float.valueOf(et_number.getText()
							.toString())) {
						if (sp_3.getSelectedItem().toString().equals("风扇")
								&& sp_4.getSelectedItem().toString()
										.equals("开")) {
							ControlUtils
									.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
						}
						if (sp_3.getSelectedItem().toString().equals("风扇")
								&& sp_4.getSelectedItem().toString()
										.equals("关")) {
							ControlUtils.control(ConstantUtil.Fan,
									ConstantUtil.CHANNEL_ALL,
									ConstantUtil.CLOSE);
						}
						if (sp_3.getSelectedItem().toString().equals("射灯")
								&& sp_4.getSelectedItem().toString()
										.equals("开")) {
							ControlUtils
									.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
						}
						if (sp_3.getSelectedItem().toString().equals("射灯")
								&& sp_4.getSelectedItem().toString()
										.equals("关")) {
							ControlUtils.control(ConstantUtil.Lamp,
									ConstantUtil.CHANNEL_ALL,
									ConstantUtil.CLOSE);
						}
						if (sp_3.getSelectedItem().toString().equals("窗帘")
								&& sp_4.getSelectedItem().toString()
										.equals("开")) {
							ControlUtils.control(ConstantUtil.Curtain,
									ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
						}
						if (sp_3.getSelectedItem().toString().equals("窗帘")
								&& sp_4.getSelectedItem().toString()
										.equals("关")) {
							ControlUtils.control(ConstantUtil.Curtain,
									ConstantUtil.CHANNEL_2, ConstantUtil.CLOSE);
						}
					} else {
						if (sp_3.getSelectedItem().toString().equals("风扇")
								&& sp_4.getSelectedItem().toString()
										.equals("开")) {
							ControlUtils
									.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
						}
						if (sp_3.getSelectedItem().toString().equals("风扇")
								&& sp_4.getSelectedItem().toString()
										.equals("关")) {
							ControlUtils.control(ConstantUtil.Fan,
									ConstantUtil.CHANNEL_ALL,
									ConstantUtil.CLOSE);
						}
						if (sp_3.getSelectedItem().toString().equals("射灯")
								&& sp_4.getSelectedItem().toString()
										.equals("开")) {
							ControlUtils
									.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
						}
						if (sp_3.getSelectedItem().toString().equals("射灯")
								&& sp_4.getSelectedItem().toString()
										.equals("关")) {
							ControlUtils.control(ConstantUtil.Lamp,
									ConstantUtil.CHANNEL_ALL,
									ConstantUtil.CLOSE);
						}
						if (sp_3.getSelectedItem().toString().equals("窗帘")
								&& sp_4.getSelectedItem().toString()
										.equals("开")) {
							ControlUtils.control(ConstantUtil.Curtain,
									ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
						}
						if (sp_3.getSelectedItem().toString().equals("窗帘")
								&& sp_4.getSelectedItem().toString()
										.equals("关")) {
							ControlUtils.control(ConstantUtil.Curtain,
									ConstantUtil.CHANNEL_2, ConstantUtil.CLOSE);
						}
					}
				}

				if (sp_1.getSelectedItem().toString().equals("照度")
						&& sp_2.getSelectedItem().toString().equals(">")) {
					if (AppConfig.ill > Float.valueOf(et_number.getText()
							.toString())) {
						if (sp_3.getSelectedItem().toString().equals("风扇")
								&& sp_4.getSelectedItem().toString()
										.equals("开")) {
							ControlUtils
									.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
						}
						if (sp_3.getSelectedItem().toString().equals("风扇")
								&& sp_4.getSelectedItem().toString()
										.equals("关")) {
							ControlUtils.control(ConstantUtil.Fan,
									ConstantUtil.CHANNEL_ALL,
									ConstantUtil.CLOSE);
						}
						if (sp_3.getSelectedItem().toString().equals("射灯")
								&& sp_4.getSelectedItem().toString()
										.equals("开")) {
							ControlUtils
									.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
						}
						if (sp_3.getSelectedItem().toString().equals("射灯")
								&& sp_4.getSelectedItem().toString()
										.equals("关")) {
							ControlUtils.control(ConstantUtil.Lamp,
									ConstantUtil.CHANNEL_ALL,
									ConstantUtil.CLOSE);
						}
						if (sp_3.getSelectedItem().toString().equals("窗帘")
								&& sp_4.getSelectedItem().toString()
										.equals("开")) {
							ControlUtils.control(ConstantUtil.Curtain,
									ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
						}
						if (sp_3.getSelectedItem().toString().equals("窗帘")
								&& sp_4.getSelectedItem().toString()
										.equals("关")) {
							ControlUtils.control(ConstantUtil.Curtain,
									ConstantUtil.CHANNEL_2, ConstantUtil.CLOSE);
						}
					} else {
						if (sp_3.getSelectedItem().toString().equals("风扇")
								&& sp_4.getSelectedItem().toString()
										.equals("开")) {
							ControlUtils
									.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
						}
						if (sp_3.getSelectedItem().toString().equals("风扇")
								&& sp_4.getSelectedItem().toString()
										.equals("关")) {
							ControlUtils.control(ConstantUtil.Fan,
									ConstantUtil.CHANNEL_ALL,
									ConstantUtil.CLOSE);
						}
						if (sp_3.getSelectedItem().toString().equals("射灯")
								&& sp_4.getSelectedItem().toString()
										.equals("开")) {
							ControlUtils
									.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
						}
						if (sp_3.getSelectedItem().toString().equals("射灯")
								&& sp_4.getSelectedItem().toString()
										.equals("关")) {
							ControlUtils.control(ConstantUtil.Lamp,
									ConstantUtil.CHANNEL_ALL,
									ConstantUtil.CLOSE);
						}
						if (sp_3.getSelectedItem().toString().equals("窗帘")
								&& sp_4.getSelectedItem().toString()
										.equals("开")) {
							ControlUtils.control(ConstantUtil.Curtain,
									ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
						}
						if (sp_3.getSelectedItem().toString().equals("窗帘")
								&& sp_4.getSelectedItem().toString()
										.equals("关")) {
							ControlUtils.control(ConstantUtil.Curtain,
									ConstantUtil.CHANNEL_2, ConstantUtil.CLOSE);
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
		sw_link = (Switch) findViewById(R.id.switch1);
	}
}
