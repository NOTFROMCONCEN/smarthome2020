package com.example.shengsai07012019;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.DataCallback;
import com.bizideal.smarthome.socket.DeviceBean;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;
import com.example.shengsai07012019.tools.DiyToast;

public class IndexActivity extends Activity {

	private TextView tv_temp;
	private TextView tv_hum;
	private TextView tv_smo;
	private TextView tv_gas;
	private TextView tv_ill;
	private TextView tv_pm;
	private TextView tv_press;
	private TextView tv_co;
	private TextView tv_per;

	private float temp = 0;
	private float hum = 0;
	private float smo = 0;
	private float gas = 0;
	private float ill = 0;
	private float pm = 0;
	private float press = 0;
	private float co = 0;
	private float per = 0;

	private Button btn_tv_open;
	private Button btn_tv_cls;
	private Button btn_kt_open;
	private Button btn_kt_cls;
	private Button btn_dvd_open;
	private Button btn_dvd_cls;
	private Button btn_cur_open;
	private Button btn_cur_cls;
	private Button btn_cur_stop;

	private CheckBox cb_lamp;
	private CheckBox cb_warm;
	private CheckBox cb_door;
	private CheckBox cb_fan;

	private RadioButton ra_open;
	private RadioButton ra_cls;
	private RadioButton ra_big;
	private RadioButton ra_small;
	private RadioButton ra_temp;
	private RadioButton ra_hum;
	private RadioButton ra_ill;
	private RadioButton ra_per;
	private RadioButton ra_smo;
	private RadioButton ra_gas;
	private RadioButton ra_co;
	private RadioButton ra_lamp;
	private RadioButton ra_fan;
	private RadioButton ra_warm;
	private RadioButton ra_cur;
	private RadioButton ra_tv;
	private RadioButton ra_dvd;
	private RadioButton ra_kt;
	private EditText et_number_get;
	private CheckBox cb_link_state;
	private boolean is_check_lamp;
	private boolean is_check_fan;
	private boolean is_check_warm;
	private boolean is_check_cur;
	private boolean is_check_tv;
	private boolean is_check_dvd;
	private boolean is_check_kt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index);
		initView();
		start_weblink();
		is_check_lamp = ra_lamp.isChecked();
		ra_lamp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (is_check_lamp == false) {
					is_check_lamp = true;
				} else {
					ra_lamp.setChecked(false);
					is_check_lamp = false;
				}
			}
		});
		is_check_cur = ra_cur.isChecked();
		ra_cur.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (is_check_cur == false) {
					is_check_cur = true;
				} else {
					ra_cur.setChecked(false);
					is_check_cur = false;
				}
			}
		});
		is_check_dvd = ra_dvd.isChecked();
		ra_dvd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (is_check_dvd == false) {
					is_check_dvd = true;
				} else {
					ra_dvd.setChecked(false);
					is_check_dvd = false;
				}
			}
		});
		is_check_fan = ra_fan.isChecked();
		ra_fan.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (is_check_fan == false) {
					is_check_fan = true;
				} else {
					ra_fan.setChecked(false);
					is_check_fan = false;
				}
			}
		});
		is_check_kt = ra_kt.isChecked();
		ra_kt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (is_check_kt == false) {
					is_check_kt = true;
				} else {
					ra_kt.setChecked(false);
					is_check_kt = false;
				}
			}
		});
		is_check_tv = ra_tv.isChecked();
		ra_tv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (is_check_tv == false) {
					is_check_tv = true;
				} else {
					ra_tv.setChecked(false);
					is_check_tv = false;
				}
			}
		});
		is_check_warm = ra_warm.isChecked();
		ra_warm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (is_check_warm == false) {
					is_check_warm = true;
				} else {
					ra_warm.setChecked(false);
					is_check_warm = false;
				}
			}
		});

		btn_cur_cls.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.Curtain,
						ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
			}
		});
		btn_cur_open.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.Curtain,
						ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
			}
		});
		btn_cur_stop.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.Curtain,
						ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
			}
		});
		btn_dvd_cls.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "3",
						ConstantUtil.OPEN);
			}
		});
		btn_dvd_open.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "3",
						ConstantUtil.OPEN);
			}
		});
		btn_tv_cls.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "2",
						ConstantUtil.OPEN);
			}
		});
		btn_tv_open.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "2",
						ConstantUtil.OPEN);
			}
		});
		btn_kt_cls.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "1",
						ConstantUtil.OPEN);
			}
		});
		btn_kt_open.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "1",
						ConstantUtil.OPEN);
			}
		});
		cb_fan.setOnCheckedChangeListener(new OnCheckedChangeListener() {

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
		cb_lamp.setOnCheckedChangeListener(new OnCheckedChangeListener() {

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
		cb_warm.setOnCheckedChangeListener(new OnCheckedChangeListener() {

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
		cb_door.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.RFID_Open_Door,
							ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
					new CountDownTimer(3000, 1000) {

						@Override
						public void onTick(long millisUntilFinished) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onFinish() {
							// TODO Auto-generated method stub
							cb_door.setChecked(false);
						}
					}.start();
				}
			}
		});

		cb_link_state.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (et_number_get.getText().toString().isEmpty()) {
						DiyToast.showToast(getApplicationContext(), "请输入数值");
						cb_link_state.setChecked(false);
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
		if (ra_lamp.isChecked()) {
			ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL,
					ConstantUtil.OPEN);
		}
		if (ra_fan.isChecked()) {
			ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL,
					ConstantUtil.OPEN);
		}
		if (ra_warm.isChecked()) {
			ControlUtils.control(ConstantUtil.WarningLight,
					ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
		}
		if (ra_cur.isChecked()) {
			ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_1,
					ConstantUtil.OPEN);
		}
		if (ra_tv.isChecked()) {
			ControlUtils.control(ConstantUtil.Lamp, "2", ConstantUtil.OPEN);
		}
		if (ra_dvd.isChecked()) {
			ControlUtils.control(ConstantUtil.Lamp, "3", ConstantUtil.OPEN);
		}
		if (ra_kt.isChecked()) {
			ControlUtils.control(ConstantUtil.Lamp, "1", ConstantUtil.OPEN);
		}
	}

	private void close() {
		// TODO Auto-generated method stub
		if (ra_lamp.isChecked()) {
			ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL,
					ConstantUtil.CLOSE);
		}
		if (ra_fan.isChecked()) {
			ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL,
					ConstantUtil.CLOSE);
		}
		if (ra_warm.isChecked()) {
			ControlUtils.control(ConstantUtil.WarningLight,
					ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
		}
		if (ra_cur.isChecked()) {
			ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_2,
					ConstantUtil.OPEN);
		}
		if (ra_tv.isChecked()) {
			ControlUtils.control(ConstantUtil.Lamp, "2", ConstantUtil.OPEN);
		}
		if (ra_dvd.isChecked()) {
			ControlUtils.control(ConstantUtil.Lamp, "3", ConstantUtil.OPEN);
		}
		if (ra_kt.isChecked()) {
			ControlUtils.control(ConstantUtil.Lamp, "1", ConstantUtil.OPEN);
		}
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (et_number_get.getText().toString().isEmpty()) {
				DiyToast.showToast(getApplicationContext(), "请输入数值");
				handler.removeCallbacks(timeRunnable);
				cb_link_state.setChecked(false);
			} else {
				int number = Integer
						.valueOf(et_number_get.getText().toString());
				if (ra_big.isChecked()) {
					if (ra_temp.isChecked()) {
						if (temp > number) {
							if (ra_open.isChecked()) {
								open();
							}
							if (ra_cls.isChecked()) {
								close();
							}
						}
					}
					if (ra_hum.isChecked()) {
						if (hum > number) {
							if (ra_open.isChecked()) {
								open();
							}
							if (ra_cls.isChecked()) {
								close();
							}
						}
					}
					if (ra_ill.isChecked()) {
						if (ill > number) {
							if (ra_open.isChecked()) {
								open();
							}
							if (ra_cls.isChecked()) {
								close();
							}
						}
					}
					if (ra_per.isChecked()) {
						if (per > number) {
							if (ra_open.isChecked()) {
								open();
							}
							if (ra_cls.isChecked()) {
								close();
							}
						}
					}
					if (ra_smo.isChecked()) {
						if (smo > number) {
							if (ra_open.isChecked()) {
								open();
							}
							if (ra_cls.isChecked()) {
								close();
							}
						}
					}
					if (ra_gas.isChecked()) {
						if (gas > number) {
							if (ra_open.isChecked()) {
								open();
							}
							if (ra_cls.isChecked()) {
								close();
							}
						}
					}
					if (ra_co.isChecked()) {
						if (co > number) {
							if (ra_open.isChecked()) {
								open();
							}
							if (ra_cls.isChecked()) {
								close();
							}
						}
					}
				}

				if (ra_small.isChecked()) {
					if (ra_temp.isChecked()) {
						if (temp < number) {
							if (ra_open.isChecked()) {
								open();
							}
							if (ra_cls.isChecked()) {
								close();
							}
						}
					}
					if (ra_hum.isChecked()) {
						if (hum < number) {
							if (ra_open.isChecked()) {
								open();
							}
							if (ra_cls.isChecked()) {
								close();
							}
						}
					}
					if (ra_ill.isChecked()) {
						if (ill < number) {
							if (ra_open.isChecked()) {
								open();
							}
							if (ra_cls.isChecked()) {
								close();
							}
						}
					}
					if (ra_per.isChecked()) {
						if (per < number) {
							if (ra_open.isChecked()) {
								open();
							}
							if (ra_cls.isChecked()) {
								close();
							}
						}
					}
					if (ra_smo.isChecked()) {
						if (smo < number) {
							if (ra_open.isChecked()) {
								open();
							}
							if (ra_cls.isChecked()) {
								close();
							}
						}
					}
					if (ra_gas.isChecked()) {
						if (gas < number) {
							if (ra_open.isChecked()) {
								open();
							}
							if (ra_cls.isChecked()) {
								close();
							}
						}
					}
					if (ra_co.isChecked()) {
						if (co < number) {
							if (ra_open.isChecked()) {
								open();
							}
							if (ra_cls.isChecked()) {
								close();
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
		btn_cur_cls = (Button) findViewById(R.id.btn_cur_cls);
		btn_cur_open = (Button) findViewById(R.id.btn_cur_open);
		btn_cur_stop = (Button) findViewById(R.id.btn_cur_stop);
		btn_dvd_cls = (Button) findViewById(R.id.btn_dvd_cls);
		btn_dvd_open = (Button) findViewById(R.id.btn_dvd_open);
		btn_kt_cls = (Button) findViewById(R.id.btn_kt_cls);
		btn_kt_open = (Button) findViewById(R.id.btn_kt_open);
		btn_tv_cls = (Button) findViewById(R.id.btn_tv_cls);
		btn_tv_open = (Button) findViewById(R.id.btn_tv_open);
		tv_co = (TextView) findViewById(R.id.tv_co);
		tv_gas = (TextView) findViewById(R.id.tv_gas);
		tv_hum = (TextView) findViewById(R.id.tv_hum);
		tv_ill = (TextView) findViewById(R.id.tv_ill);
		tv_per = (TextView) findViewById(R.id.tv_per);
		tv_pm = (TextView) findViewById(R.id.tv_pm);
		tv_press = (TextView) findViewById(R.id.tv_press);
		tv_smo = (TextView) findViewById(R.id.tv_smo);
		tv_temp = (TextView) findViewById(R.id.tv_temp);
		cb_door = (CheckBox) findViewById(R.id.cb_door);
		cb_fan = (CheckBox) findViewById(R.id.cb_fan);
		cb_lamp = (CheckBox) findViewById(R.id.cb_lamp);
		cb_warm = (CheckBox) findViewById(R.id.cb_warm);
		et_number_get = (EditText) findViewById(R.id.et_number_get);
		cb_link_state = (CheckBox) findViewById(R.id.cb_link_state);
		ra_big = (RadioButton) findViewById(R.id.ra_big);
		ra_cls = (RadioButton) findViewById(R.id.ra_cls);
		ra_co = (RadioButton) findViewById(R.id.ra_co);
		ra_cur = (RadioButton) findViewById(R.id.ra_cur);
		ra_dvd = (RadioButton) findViewById(R.id.ra_dvd);
		ra_fan = (RadioButton) findViewById(R.id.ra_fan);
		ra_gas = (RadioButton) findViewById(R.id.ra_gas);
		ra_hum = (RadioButton) findViewById(R.id.ra_hum);
		ra_ill = (RadioButton) findViewById(R.id.ra_ill);
		ra_kt = (RadioButton) findViewById(R.id.ra_kt);
		ra_lamp = (RadioButton) findViewById(R.id.ra_lamp);
		ra_open = (RadioButton) findViewById(R.id.ra_open);
		ra_per = (RadioButton) findViewById(R.id.ra_per);
		ra_small = (RadioButton) findViewById(R.id.ra_small);
		ra_smo = (RadioButton) findViewById(R.id.ra_smo);
		ra_temp = (RadioButton) findViewById(R.id.ra_temp);
		ra_tv = (RadioButton) findViewById(R.id.ra_tv);
		ra_warm = (RadioButton) findViewById(R.id.ra_warm);
	}

	private void start_getdata() {
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
							per = Float.valueOf(getdata.getStateHumanInfrared());
							if (per == 1) {
								tv_per.setText("有人");
							} else {
								tv_per.setText("无人");
							}
						}
						System.out.println("LAMP：" + getdata.getLamp());
						System.out.println("DOOR："
								+ getdata.getRFID_Open_Door());
						System.out.println("WARM：" + getdata.getWarningLight());
						System.out.println("FAN：" + getdata.getFan());
					}
				});
			}
		});
	}

	private void start_weblink() {
		// TODO Auto-generated method stub
		ControlUtils.setUser("bizideal", "123456", "18.1.10.7");
		SocketClient.getInstance().creatConnect();
		SocketClient.getInstance().login(new LoginCallback() {

			@Override
			public void onEvent(final String server) {
				// TODO Auto-generated method stub
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (server.equals(ConstantUtil.SUCCESS)) {
							DiyToast.showToast(getApplicationContext(), "组网成功");
							start_getdata();
						} else {
							DiyToast.showToast(getApplicationContext(), "组网失败");
							start_getdata();
						}
					}
				});
			}
		});
	}
}
