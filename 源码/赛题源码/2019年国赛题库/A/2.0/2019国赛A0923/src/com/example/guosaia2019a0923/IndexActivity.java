package com.example.guosaia2019a0923;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.DataCallback;
import com.bizideal.smarthome.socket.DeviceBean;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;
import com.example.guosaia2019a0923.tools.DiyToast;
import com.example.guosaia2019a0923.tools.SpinnerBaseAdapter;

public class IndexActivity extends Activity {

	private Spinner sp_1, sp_2, sp_3;
	private String[] mString1 = { "温度", "光照" };
	private String[] mString2 = { ">", "<=" };
	private String[] mString3 = { "风扇打开", "射灯打开" };
	public static boolean web_state = false;
	private TextView tv_temp, tv_hum, tv_press, tv_smo, tv_gas, tv_ill, tv_per,
			tv_co, tv_pm;
	public static float temp, hum, press, smo, gas, ill, per, co, pm;
	private ToggleButton tg_lamp, tg_door, tg_warm, tg_fan;
	private CheckBox cb_link_af, cb_link_temp, cb_link_lv, cb_link_diy;
	private boolean af_link = false;
	private boolean lv_link = false;
	private EditText et_number_get;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index);
		initView();
		set_spinner();
		start_webserver();

		cb_link_lv.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					lv_link = true;
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					try {
						Thread.sleep(100);
					} catch (Exception e) {
						// TODO: handle exception
					}
					ControlUtils.control(ConstantUtil.Curtain,
							ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
					link_lv();
				} else {
					lv_link = false;
				}
			}
		});

		cb_link_temp.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					link_temp();
				}
			}
		});

		cb_link_af.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					af_link = true;
					link_af();
				} else {
					af_link = false;
				}
			}
		});
		tg_door.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.RFID_Open_Door,
							ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
				} else {

				}
			}
		});
		tg_fan.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.Fan,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				} else {
					ControlUtils.control(ConstantUtil.Fan,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
		});
		tg_lamp.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				} else {
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
		});
		tg_warm.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				} else {
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
		});
		cb_link_diy.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (et_number_get.getText().toString().isEmpty()) {
						DiyToast.showToast(getApplicationContext(), "请输入数值");
						cb_link_diy.setChecked(false);
					}
				} else {
					try {
						Thread.sleep(100);
					} catch (Exception e) {
						// TODO: handle exception
					}
					if (sp_3.getSelectedItem().toString().equals("风扇打开")) {
						tg_fan.setChecked(false);
					}
					if (sp_3.getSelectedItem().toString().equals("射灯打开")) {
						tg_lamp.setChecked(false);
					}
				}
			}
		});
		handler.post(timeRunnable);
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (cb_link_diy.isChecked()) {
				if (et_number_get.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "请输入数值");
					cb_link_diy.setChecked(false);
				} else {
					if (sp_3.getSelectedItem().toString().equals("射灯打开")) {
						if (sp_2.getSelectedItem().toString().equals(">")) {
							if (sp_1.getSelectedItem().toString().equals("温度")) {
								if (temp > Integer.valueOf(et_number_get
										.getText().toString())) {
									tg_lamp.setChecked(true);
								} else {
									tg_lamp.setChecked(false);
								}
							}
							if (sp_1.getSelectedItem().toString().equals("光照")) {
								if (ill > Integer.valueOf(et_number_get
										.getText().toString())) {
									tg_lamp.setChecked(true);
								} else {
									tg_lamp.setChecked(false);
								}
							}
						}
						if (sp_2.getSelectedItem().toString().equals("<=")) {
							if (sp_1.getSelectedItem().toString().equals("温度")) {
								if (temp < Integer.valueOf(et_number_get
										.getText().toString())) {
									tg_lamp.setChecked(true);
								} else {
									tg_lamp.setChecked(false);
								}
							}
							if (sp_1.getSelectedItem().toString().equals("光照")) {
								if (ill < Integer.valueOf(et_number_get
										.getText().toString())) {
									tg_lamp.setChecked(true);
								} else {
									tg_lamp.setChecked(false);
								}
							}
						}
					}
					if (sp_3.getSelectedItem().toString().equals("风扇打开")) {
						if (sp_2.getSelectedItem().toString().equals(">")) {
							if (sp_1.getSelectedItem().toString().equals("温度")) {
								if (temp > Integer.valueOf(et_number_get
										.getText().toString())) {
									tg_fan.setChecked(true);
								} else {
									tg_fan.setChecked(false);
								}
							}
							if (sp_1.getSelectedItem().toString().equals("光照")) {
								if (ill > Integer.valueOf(et_number_get
										.getText().toString())) {
									tg_fan.setChecked(true);
								} else {
									tg_fan.setChecked(false);
								}
							}
						}
						if (sp_2.getSelectedItem().toString().equals("<=")) {
							if (sp_1.getSelectedItem().toString().equals("温度")) {
								if (temp < Integer.valueOf(et_number_get
										.getText().toString())) {
									tg_fan.setChecked(true);
								} else {
									tg_fan.setChecked(false);
								}
							}
							if (sp_1.getSelectedItem().toString().equals("光照")) {
								if (ill < Integer.valueOf(et_number_get
										.getText().toString())) {
									tg_fan.setChecked(true);
								} else {
									tg_fan.setChecked(false);
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

	private void getdata_server() {
		ControlUtils.getData();
		SocketClient.getInstance().getData(new DataCallback<DeviceBean>() {

			@Override
			public void onResult(final DeviceBean getdata) {
				// TODO Auto-generated method stub
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (!TextUtils.isEmpty(getdata.getAirPressure())) {
							tv_press.setText(getdata.getAirPressure());
							press = Float.valueOf(getdata.getAirPressure());
						}
						if (!TextUtils.isEmpty(getdata.getCo2())) {
							tv_co.setText(getdata.getCo2());
							co = Float.valueOf(getdata.getCo2());
						}
						if (!TextUtils.isEmpty(getdata.getGas())) {
							tv_gas.setText(getdata.getGas());
							gas = Float.valueOf(getdata.getGas());
						}
						if (!TextUtils.isEmpty(getdata.getHumidity())) {
							tv_hum.setText(getdata.getHumidity());
							hum = Float.valueOf(getdata.getHumidity());
						}
						if (!TextUtils.isEmpty(getdata.getIllumination())) {
							tv_ill.setText(getdata.getIllumination());
							ill = Float.valueOf(getdata.getIllumination());
						}
						if (!TextUtils.isEmpty(getdata.getPM25())) {
							tv_pm.setText(getdata.getPM25());
							pm = Float.valueOf(getdata.getPM25());
						}
						if (!TextUtils.isEmpty(getdata.getSmoke())) {
							tv_smo.setText(getdata.getSmoke());
							smo = Float.valueOf(getdata.getSmoke());
						}
						if (!TextUtils.isEmpty(getdata.getTemperature())) {
							tv_temp.setText(getdata.getTemperature());
							temp = Float.valueOf(getdata.getTemperature());
						}
						if (!TextUtils.isEmpty(getdata.getStateHumanInfrared())) {
							press = Float.valueOf(getdata
									.getStateHumanInfrared());
							if (press == 1) {
								tv_per.setText("有人");
							} else {
								tv_per.setText("无人");
							}
						}
					}
				});
			}
		});
	}

	private void start_webserver() {
		// TODO Auto-generated method stub
		ControlUtils.setUser("bizideal", "123456", SocketClient.ip);
		SocketClient.getInstance().creatConnect();
		SocketClient.getInstance().login(new LoginCallback() {

			@Override
			public void onEvent(final String arg0) {
				// TODO Auto-generated method stub
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (arg0.equals(ConstantUtil.SUCCESS)) {
							DiyToast.showToast(getApplicationContext(),
									"网络连接成功");
							web_state = true;
							getdata_server();
						} else {
							DiyToast.showToast(getApplicationContext(),
									"网络连接失败");
							web_state = false;
							getdata_server();
						}
					}
				});
			}
		});
	}

	private void set_spinner() {
		// TODO Auto-generated method stub
		SpinnerBaseAdapter spinnerBaseAdapter;
		spinnerBaseAdapter = new SpinnerBaseAdapter(getApplicationContext(),
				mString1);
		sp_1.setAdapter(spinnerBaseAdapter);
		spinnerBaseAdapter = new SpinnerBaseAdapter(getApplicationContext(),
				mString2);
		sp_2.setAdapter(spinnerBaseAdapter);
		spinnerBaseAdapter = new SpinnerBaseAdapter(getApplicationContext(),
				mString3);
		sp_3.setAdapter(spinnerBaseAdapter);
	}

	private void initView() {
		// TODO Auto-generated method stub
		sp_1 = (Spinner) findViewById(R.id.spinner1);
		sp_2 = (Spinner) findViewById(R.id.spinner2);
		sp_3 = (Spinner) findViewById(R.id.spinner3);
		tg_door = (ToggleButton) findViewById(R.id.tg_door);
		tg_fan = (ToggleButton) findViewById(R.id.tg_fan);
		tg_lamp = (ToggleButton) findViewById(R.id.tg_lamp);
		tg_warm = (ToggleButton) findViewById(R.id.tg_warm);
		cb_link_af = (CheckBox) findViewById(R.id.cb_af_mode);
		cb_link_diy = (CheckBox) findViewById(R.id.cb_diy_mode);
		cb_link_lv = (CheckBox) findViewById(R.id.cb_ly_mode);
		cb_link_temp = (CheckBox) findViewById(R.id.cb_temp_mode);
		et_number_get = (EditText) findViewById(R.id.et_number_get);
	}

	public void link_state(View view) {
		startActivity(new Intent(getApplicationContext(),
				LinkStateActivity.class));
	}

	public void open(View view) {
		ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_1,
				ConstantUtil.OPEN);
	}

	public void cls(View view) {
		ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_2,
				ConstantUtil.OPEN);
	}

	public void stop(View view) {
		ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_3,
				ConstantUtil.OPEN);
	}

	public void kongtiao(View view) {
		ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "1",
				ConstantUtil.OPEN);
	}

	public void dvd(View view) {
		ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "8",
				ConstantUtil.OPEN);
	}

	public void tv(View view) {
		ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "5",
				ConstantUtil.OPEN);
	}

	private void link_lv() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (lv_link) {
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						// TODO: handle exception
					}
					if (pm > 75) {
						runOnUiThread(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								tg_fan.setChecked(true);
							}
						});
					} else {
						runOnUiThread(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								tg_fan.setChecked(false);
							}
						});
					}
				}
			}
		}).start();
	}

	private void link_temp() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "1",
						ConstantUtil.OPEN);
				try {
					Thread.sleep(300);
				} catch (Exception e) {
					// TODO: handle exception
				}
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "5",
						ConstantUtil.OPEN);
				try {
					Thread.sleep(300);
				} catch (Exception e) {
					// TODO: handle exception
				}
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "8",
						ConstantUtil.OPEN);
				try {
					Thread.sleep(300);
				} catch (Exception e) {
					// TODO: handle exception
				}
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						tg_warm.setChecked(true);
					}
				});
				try {
					Thread.sleep(300);
				} catch (Exception e) {
					// TODO: handle exception
				}
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						tg_fan.setChecked(true);
					}
				});
				try {
					Thread.sleep(300);
				} catch (Exception e) {
					// TODO: handle exception
				}
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						tg_lamp.setChecked(true);
					}
				});
			}
		}).start();
	}

	private void link_af() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (af_link) {
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						// TODO: handle exception
					}
					if (per == 1) {
						runOnUiThread(new Runnable() {
							public void run() {
								tg_lamp.setChecked(true);
							}
						});
						try {
							Thread.sleep(500);
						} catch (Exception e) {
							// TODO: handle exception
						}
						runOnUiThread(new Runnable() {
							public void run() {
								tg_warm.setChecked(true);
							}
						});
					} else {
						runOnUiThread(new Runnable() {
							public void run() {
								tg_lamp.setChecked(false);
							}
						});
						try {
							Thread.sleep(500);
						} catch (Exception e) {
							// TODO: handle exception
						}
						runOnUiThread(new Runnable() {
							public void run() {
								tg_warm.setChecked(false);
							}
						});
					}
				}
			}
		}).start();
	}
}
