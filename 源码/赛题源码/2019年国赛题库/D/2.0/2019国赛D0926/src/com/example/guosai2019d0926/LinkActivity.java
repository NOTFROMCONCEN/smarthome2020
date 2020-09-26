package com.example.guosai2019d0926;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.example.guosai2019d0926.tools.AppConfig;
import com.example.guosai2019d0926.tools.DiyToast;

public class LinkActivity extends Activity {

	private TextView tv_now_runstate;
	private RadioButton ra_temp, ra_hum, ra_smo, ra_gas, ra_ill, ra_press,
			ra_per, ra_pm, ra_co, radio_big, radio_small, ra_open, ra_cls;
	private RadioButton ra_lamp, ra_door, ra_fan, ra_warm, ra_tv, ra_dvd,
			ra_kt, ra_jiashi, ra_chazuo;
	private boolean lamp_state = false;
	private boolean door_state = false;
	private boolean fan_state = false;
	private boolean warm_state = false;
	private boolean tv_state = false;
	private boolean chazuo_state = false;
	private boolean dvd_state = false;
	private boolean kt_state = false;
	private boolean jiashi_state = false;
	private boolean link_state = false;
	private EditText et_number;
	private StringBuffer open_shebei = new StringBuffer();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_link);
		initView();
		handler.post(timeRunnable);
		ra_lamp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (lamp_state) {
					ra_lamp.setChecked(false);
					lamp_state = false;
				} else {
					lamp_state = true;
				}
			}
		});
		ra_door.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (door_state) {
					ra_door.setChecked(false);
					door_state = false;
				} else {
					door_state = true;
				}
			}
		});
		ra_fan.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (fan_state) {
					ra_fan.setChecked(false);
					fan_state = false;
				} else {
					fan_state = true;
				}
			}
		});
		ra_chazuo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (chazuo_state) {
					ra_chazuo.setChecked(false);
					chazuo_state = false;
				} else {
					chazuo_state = true;
				}
			}
		});
		ra_warm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (warm_state) {
					ra_warm.setChecked(false);
					warm_state = false;
				} else {
					warm_state = true;
				}
			}
		});
		ra_tv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (tv_state) {
					ra_tv.setChecked(false);
					tv_state = false;
				} else {
					tv_state = true;
				}
			}
		});
		ra_dvd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (dvd_state) {
					ra_dvd.setChecked(false);
					dvd_state = false;
				} else {
					dvd_state = true;
				}
			}
		});
		ra_kt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (kt_state) {
					ra_kt.setChecked(false);
					kt_state = false;
				} else {
					kt_state = true;
				}
			}
		});
		ra_jiashi.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (jiashi_state) {
					ra_jiashi.setChecked(false);
					jiashi_state = false;
				} else {
					jiashi_state = true;
				}
			}
		});
	}

	public void link_back(View view) {
		// TODO Auto-generated method stub
		finish();
	}

	private void open() {
		// TODO Auto-generated method stub
		if (!ra_chazuo.isChecked() && !ra_jiashi.isChecked()
				&& !ra_lamp.isChecked() && !ra_warm.isChecked()
				&& !ra_kt.isChecked() && !ra_tv.isChecked()
				&& !ra_dvd.isChecked() && !ra_door.isChecked()
				&& !ra_fan.isChecked()) {
			open_shebei.delete(0, open_shebei.length());
			open_shebei.append("全开");
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
			ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL,
					ConstantUtil.OPEN);
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
			ControlUtils.control(ConstantUtil.WarningLight,
					ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
			ControlUtils.control(ConstantUtil.RFID_Open_Door,
					ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
			ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL,
					ConstantUtil.OPEN);
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
			ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "1",
					ConstantUtil.OPEN);
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
			ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "5",
					ConstantUtil.OPEN);
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
			ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "8",
					ConstantUtil.OPEN);
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
		} else {
			open_shebei.delete(0, open_shebei.length());
			if (ra_lamp.isChecked()) {
				open_shebei.append(" 射灯 ");
				ControlUtils.control(ConstantUtil.Lamp,
						ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
			}
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (ra_warm.isChecked()) {
				open_shebei.append(" 报警灯 ");
				ControlUtils.control(ConstantUtil.WarningLight,
						ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
			}
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (ra_door.isChecked()) {
				open_shebei.append(" 门禁 ");
				ControlUtils.control(ConstantUtil.RFID_Open_Door,
						ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
			}
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (ra_fan.isChecked()) {
				open_shebei.append(" 风扇 ");
				ControlUtils.control(ConstantUtil.Fan,
						ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
			}
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (ra_tv.isChecked()) {
				open_shebei.append(" 电视 ");
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "1",
						ConstantUtil.OPEN);
			}
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (ra_dvd.isChecked()) {
				open_shebei.append(" DVD ");
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "5",
						ConstantUtil.OPEN);
			}
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (ra_kt.isChecked()) {
				open_shebei.append(" 空调 ");
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "8",
						ConstantUtil.OPEN);
			}
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	private void cls() {
		// TODO Auto-generated method stub
		if (!ra_chazuo.isChecked() && !ra_jiashi.isChecked()
				&& !ra_lamp.isChecked() && !ra_warm.isChecked()
				&& !ra_kt.isChecked() && !ra_tv.isChecked()
				&& !ra_dvd.isChecked() && !ra_door.isChecked()
				&& !ra_fan.isChecked()) {
			open_shebei.delete(0, open_shebei.length());
			open_shebei.append("全关");
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
			ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL,
					ConstantUtil.CLOSE);
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
			ControlUtils.control(ConstantUtil.WarningLight,
					ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
			ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL,
					ConstantUtil.CLOSE);
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
			ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "1",
					ConstantUtil.OPEN);
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
			ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "5",
					ConstantUtil.OPEN);
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
			ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "8",
					ConstantUtil.OPEN);
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
		} else {
			open_shebei.delete(0, open_shebei.length());
			if (ra_lamp.isChecked()) {
				open_shebei.append(" 射灯 ");
				ControlUtils.control(ConstantUtil.Lamp,
						ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
			}
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (ra_warm.isChecked()) {
				open_shebei.append(" 报警灯 ");
				ControlUtils.control(ConstantUtil.WarningLight,
						ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
			}
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (ra_door.isChecked()) {
				open_shebei.append(" 门禁 ");
			}
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (ra_fan.isChecked()) {
				open_shebei.append(" 风扇 ");
				ControlUtils.control(ConstantUtil.Fan,
						ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
			}
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (ra_tv.isChecked()) {
				open_shebei.append(" 电视 ");
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "1",
						ConstantUtil.OPEN);
			}
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (ra_dvd.isChecked()) {
				open_shebei.append(" DVD ");
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "5",
						ConstantUtil.OPEN);
			}
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (ra_kt.isChecked()) {
				open_shebei.append(" 空调 ");
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "8",
						ConstantUtil.OPEN);
			}
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (link_state) {
				if (et_number.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "请输入阈值");
					link_state = false;
				} else {
					if (ra_co.isChecked() || ra_gas.isChecked()
							|| ra_hum.isChecked() || ra_ill.isChecked()
							|| ra_per.isChecked() || ra_pm.isChecked()
							|| ra_press.isChecked() || ra_smo.isChecked()
							|| ra_temp.isChecked()) {
						if (radio_big.isChecked() || radio_small.isChecked()) {
							if (radio_big.isChecked()) {
								if (ra_open.isChecked()) {
									if (ra_temp.isChecked()) {
										if (AppConfig.temp > Integer
												.valueOf(et_number.getText()
														.toString())) {
											open();
											set_text("温度", "大于", et_number
													.getText().toString(), "开",
													open_shebei.toString());
										}
									}
									if (ra_hum.isChecked()) {
										if (AppConfig.hum > Integer
												.valueOf(et_number.getText()
														.toString())) {
											open();
											set_text("湿度", "大于", et_number
													.getText().toString(), "开",
													open_shebei.toString());
										}
									}
									if (ra_gas.isChecked()) {
										if (AppConfig.gas > Integer
												.valueOf(et_number.getText()
														.toString())) {
											open();
											set_text("燃气", "大于", et_number
													.getText().toString(), "开",
													open_shebei.toString());
										}
									}
									if (ra_ill.isChecked()) {
										if (AppConfig.ill > Integer
												.valueOf(et_number.getText()
														.toString())) {
											open();
											set_text("光照度", "大于", et_number
													.getText().toString(), "开",
													open_shebei.toString());
										}
									}
									if (ra_pm.isChecked()) {
										if (AppConfig.pm > Integer
												.valueOf(et_number.getText()
														.toString())) {
											open();
											set_text("PM2.5", "大于", et_number
													.getText().toString(), "开",
													open_shebei.toString());
										}
									}
									if (ra_press.isChecked()) {
										if (AppConfig.press > Integer
												.valueOf(et_number.getText()
														.toString())) {
											open();
											set_text("气压", "大于", et_number
													.getText().toString(), "开",
													open_shebei.toString());
										}
									}
									if (ra_smo.isChecked()) {
										if (AppConfig.smo > Integer
												.valueOf(et_number.getText()
														.toString())) {
											open();
											set_text("烟雾", "大于", et_number
													.getText().toString(), "开",
													open_shebei.toString());
										}
									}
									if (ra_co.isChecked()) {
										if (AppConfig.co > Integer
												.valueOf(et_number.getText()
														.toString())) {
											open();
											set_text("Co2", "大于", et_number
													.getText().toString(), "开",
													open_shebei.toString());
										}
									}
									if (ra_per.isChecked()) {
										if (AppConfig.per > Integer
												.valueOf(et_number.getText()
														.toString())) {
											open();
											set_text("人体红外", "大于", et_number
													.getText().toString(), "开",
													open_shebei.toString());
										}
									}
								}

								if (ra_cls.isChecked()) {
									if (ra_temp.isChecked()) {
										if (AppConfig.temp > Integer
												.valueOf(et_number.getText()
														.toString())) {
											cls();
											set_text("温度", "大于", et_number
													.getText().toString(), "关",
													open_shebei.toString());
										}
									}
									if (ra_hum.isChecked()) {
										if (AppConfig.hum > Integer
												.valueOf(et_number.getText()
														.toString())) {
											cls();
											set_text("湿度", "大于", et_number
													.getText().toString(), "关",
													open_shebei.toString());
										}
									}
									if (ra_gas.isChecked()) {
										if (AppConfig.gas > Integer
												.valueOf(et_number.getText()
														.toString())) {
											cls();
											set_text("燃气", "大于", et_number
													.getText().toString(), "关",
													open_shebei.toString());
										}
									}
									if (ra_ill.isChecked()) {
										if (AppConfig.ill > Integer
												.valueOf(et_number.getText()
														.toString())) {
											cls();
											set_text("光照度", "大于", et_number
													.getText().toString(), "关",
													open_shebei.toString());
										}
									}
									if (ra_pm.isChecked()) {
										if (AppConfig.pm > Integer
												.valueOf(et_number.getText()
														.toString())) {
											cls();
											set_text("PM2.5", "大于", et_number
													.getText().toString(), "关",
													open_shebei.toString());
										}
									}
									if (ra_press.isChecked()) {
										if (AppConfig.press > Integer
												.valueOf(et_number.getText()
														.toString())) {
											cls();
											set_text("气压", "大于", et_number
													.getText().toString(), "关",
													open_shebei.toString());
										}
									}
									if (ra_smo.isChecked()) {
										if (AppConfig.smo > Integer
												.valueOf(et_number.getText()
														.toString())) {
											cls();
											set_text("烟雾", "大于", et_number
													.getText().toString(), "关",
													open_shebei.toString());
										}
									}
									if (ra_co.isChecked()) {
										if (AppConfig.co > Integer
												.valueOf(et_number.getText()
														.toString())) {
											cls();
											set_text("Co2", "大于", et_number
													.getText().toString(), "关",
													open_shebei.toString());
										}
									}
									if (ra_per.isChecked()) {
										if (AppConfig.per > Integer
												.valueOf(et_number.getText()
														.toString())) {
											cls();
											set_text("人体红外", "大于", et_number
													.getText().toString(), "关",
													open_shebei.toString());
										}
									}

								}
							}

							if (radio_small.isChecked()) {

								if (ra_open.isChecked()) {
									if (ra_temp.isChecked()) {
										if (AppConfig.temp < Integer
												.valueOf(et_number.getText()
														.toString())) {
											open();
											set_text("温度", "小于", et_number
													.getText().toString(), "开",
													open_shebei.toString());
										}
									}
									if (ra_hum.isChecked()) {
										if (AppConfig.hum < Integer
												.valueOf(et_number.getText()
														.toString())) {
											open();
											set_text("湿度", "小于", et_number
													.getText().toString(), "开",
													open_shebei.toString());
										}
									}
									if (ra_gas.isChecked()) {
										if (AppConfig.gas < Integer
												.valueOf(et_number.getText()
														.toString())) {
											open();
											set_text("燃气", "小于", et_number
													.getText().toString(), "开",
													open_shebei.toString());
										}
									}
									if (ra_ill.isChecked()) {
										if (AppConfig.ill < Integer
												.valueOf(et_number.getText()
														.toString())) {
											open();
											set_text("光照度", "小于", et_number
													.getText().toString(), "开",
													open_shebei.toString());
										}
									}
									if (ra_pm.isChecked()) {
										if (AppConfig.pm < Integer
												.valueOf(et_number.getText()
														.toString())) {
											open();
											set_text("PM2.5", "小于", et_number
													.getText().toString(), "开",
													open_shebei.toString());
										}
									}
									if (ra_press.isChecked()) {
										if (AppConfig.press < Integer
												.valueOf(et_number.getText()
														.toString())) {
											open();
											set_text("气压", "小于", et_number
													.getText().toString(), "开",
													open_shebei.toString());
										}
									}
									if (ra_smo.isChecked()) {
										if (AppConfig.smo < Integer
												.valueOf(et_number.getText()
														.toString())) {
											open();
											set_text("烟雾", "小于", et_number
													.getText().toString(), "开",
													open_shebei.toString());
										}
									}
									if (ra_co.isChecked()) {
										if (AppConfig.co < Integer
												.valueOf(et_number.getText()
														.toString())) {
											open();
											set_text("Co2", "小于", et_number
													.getText().toString(), "开",
													open_shebei.toString());
										}
									}
									if (ra_per.isChecked()) {
										if (AppConfig.per < Integer
												.valueOf(et_number.getText()
														.toString())) {
											open();
											set_text("人体红外", "小于", et_number
													.getText().toString(), "开",
													open_shebei.toString());
										}
									}
								}

								if (ra_cls.isChecked()) {
									if (ra_temp.isChecked()) {
										if (AppConfig.temp < Integer
												.valueOf(et_number.getText()
														.toString())) {
											cls();
											set_text("温度", "小于", et_number
													.getText().toString(), "关",
													open_shebei.toString());
										}
									}
									if (ra_hum.isChecked()) {
										if (AppConfig.hum < Integer
												.valueOf(et_number.getText()
														.toString())) {
											cls();
											set_text("湿度", "小于", et_number
													.getText().toString(), "关",
													open_shebei.toString());
										}
									}
									if (ra_gas.isChecked()) {
										if (AppConfig.gas < Integer
												.valueOf(et_number.getText()
														.toString())) {
											cls();
											set_text("燃气", "小于", et_number
													.getText().toString(), "关",
													open_shebei.toString());
										}
									}
									if (ra_ill.isChecked()) {
										if (AppConfig.ill < Integer
												.valueOf(et_number.getText()
														.toString())) {
											cls();
											set_text("光照度", "小于", et_number
													.getText().toString(), "关",
													open_shebei.toString());
										}
									}
									if (ra_pm.isChecked()) {
										if (AppConfig.pm < Integer
												.valueOf(et_number.getText()
														.toString())) {
											cls();
											set_text("PM2.5", "小于", et_number
													.getText().toString(), "关",
													open_shebei.toString());
										}
									}
									if (ra_press.isChecked()) {
										if (AppConfig.press < Integer
												.valueOf(et_number.getText()
														.toString())) {
											cls();
											set_text("气压", "小于", et_number
													.getText().toString(), "关",
													open_shebei.toString());
										}
									}
									if (ra_smo.isChecked()) {
										if (AppConfig.smo < Integer
												.valueOf(et_number.getText()
														.toString())) {
											cls();
											set_text("烟雾", "小于", et_number
													.getText().toString(), "关",
													open_shebei.toString());
										}
									}
									if (ra_co.isChecked()) {
										if (AppConfig.co < Integer
												.valueOf(et_number.getText()
														.toString())) {
											cls();
											set_text("Co2", "小于", et_number
													.getText().toString(), "关",
													open_shebei.toString());
										}
									}
									if (ra_per.isChecked()) {
										if (AppConfig.per < Integer
												.valueOf(et_number.getText()
														.toString())) {
											cls();
											set_text("人体红外", "小于", et_number
													.getText().toString(), "关",
													open_shebei.toString());
										}
									}

								}

							}

						} else {
							DiyToast.showToast(getApplicationContext(), "请选择条件");
							link_state = false;
						}
					} else {
						DiyToast.showToast(getApplicationContext(), "请选择传感器");
						link_state = false;
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

	public void tg_link_state(View view) {
		// TODO Auto-generated method stub
		if (((ToggleButton) view).isChecked()) {
			if (et_number.getText().toString().isEmpty()) {
				DiyToast.showToast(getApplicationContext(), "请输入阈值");
				link_state = false;
			} else {
				if (ra_co.isChecked() || ra_gas.isChecked()
						|| ra_hum.isChecked() || ra_ill.isChecked()
						|| ra_per.isChecked() || ra_pm.isChecked()
						|| ra_press.isChecked() || ra_smo.isChecked()) {
					if (radio_big.isChecked() || radio_small.isChecked()) {
						link_state = true;
					} else {
						DiyToast.showToast(getApplicationContext(), "请选择条件");
						link_state = false;
					}
				} else {
					DiyToast.showToast(getApplicationContext(), "请选择传感器");
					link_state = false;
				}
			}
		} else {
			link_state = false;
		}
	}

	private void set_text(String chuanganqi, String tiaojian, String yuzhi,
			String dongzuo, String shebei) {
		tv_now_runstate.setText("当" + chuanganqi + tiaojian + yuzhi + ","
				+ dongzuo + shebei);
	}

	private void initView() {
		// TODO Auto-generated method stub
		et_number = (EditText) findViewById(R.id.et_number_get);
		ra_co = (RadioButton) findViewById(R.id.ra_co);
		ra_gas = (RadioButton) findViewById(R.id.ra_gas);
		ra_hum = (RadioButton) findViewById(R.id.ra_hum);
		ra_ill = (RadioButton) findViewById(R.id.ra_ill);
		ra_per = (RadioButton) findViewById(R.id.ra_per);
		ra_pm = (RadioButton) findViewById(R.id.ra_pm);
		ra_press = (RadioButton) findViewById(R.id.ra_press);
		ra_smo = (RadioButton) findViewById(R.id.ra_smo);
		ra_temp = (RadioButton) findViewById(R.id.ra_temp);
		radio_big = (RadioButton) findViewById(R.id.radio_big);
		radio_small = (RadioButton) findViewById(R.id.radio_small);
		tv_now_runstate = (TextView) findViewById(R.id.tv_now_runstate);
		ra_open = (RadioButton) findViewById(R.id.ra_open);
		ra_cls = (RadioButton) findViewById(R.id.ra_cls);
		ra_lamp = (RadioButton) findViewById(R.id.ra_lamp);
		ra_door = (RadioButton) findViewById(R.id.ra_door);
		ra_fan = (RadioButton) findViewById(R.id.ra_fan);
		ra_warm = (RadioButton) findViewById(R.id.ra_warm);
		ra_tv = (RadioButton) findViewById(R.id.ra_tv);
		ra_dvd = (RadioButton) findViewById(R.id.ra_dvd);
		ra_kt = (RadioButton) findViewById(R.id.ra_kongtiao);
		ra_jiashi = (RadioButton) findViewById(R.id.ra_jiashi);
		ra_chazuo = (RadioButton) findViewById(R.id.ra_chazuo);

	}
}
