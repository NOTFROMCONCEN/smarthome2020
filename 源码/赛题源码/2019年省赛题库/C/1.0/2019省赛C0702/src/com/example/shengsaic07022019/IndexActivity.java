package com.example.shengsaic07022019;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CheckBox;
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
import com.example.shengsaic07022019.tools.AppTools;
import com.example.shengsaic07022019.tools.DiyToast;
import com.example.shengsaic07022019.tools.LinkAdapterHelper;

public class IndexActivity extends Activity {

	private Button btn_link_state, btn_cur_open, btn_cur_stop, btn_cur_cls,
			btn_tv, btn_kt, btn_dvd;
	private ToggleButton tg_lamp, tg_warm, tg_door, tg_fan;
	private TextView tv_temp, tv_hum, tv_press, tv_per, tv_gas, tv_smo, tv_ill,
			tv_pm, tv_co;
	private float temp, hum, press, per, gas, smo, ill, pm, co;
	private Spinner sp_1, sp_2, sp_3;
	private LinkAdapterHelper adapterHelper;
	private CheckBox cb_lv, cb_temp, cb_af, cb_diy;
	private EditText et_number_get;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index);
		initView();
		start_link();
		set_spinner();
		btn_link_state.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getApplicationContext(),
						LinkStateActivity.class));
				finish();
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
		btn_dvd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "3",
						ConstantUtil.OPEN);
			}
		});
		btn_kt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "1",
						ConstantUtil.OPEN);
			}
		});
		btn_tv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "2",
						ConstantUtil.OPEN);
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
		cb_af.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					handler_af.post(timeRunnable_af);
				} else {
					handler_af.removeCallbacks(timeRunnable_af);
				}
			}
		});
		cb_lv.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					handler_lv.post(timeRunnable_lv);
				} else {
					handler_lv.removeCallbacks(timeRunnable_lv);
				}
			}
		});
		cb_temp.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					handler_temp.post(timeRunnable_temp);
				} else {
					handler_temp.removeCallbacks(timeRunnable_temp);
				}
			}
		});
		cb_diy.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					handler_diy.post(timeRunnable_diy);
				} else {
					handler_diy.removeCallbacks(timeRunnable_diy);
				}
			}
		});
	}

	Runnable timeRunnable_diy = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Message message = handler_diy.obtainMessage();
			handler_diy.sendMessage(message);
		}
	};

	Handler handler_diy = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (et_number_get.getText().toString().isEmpty()) {
				DiyToast.showToast(getApplicationContext(), "请输入数值");
			} else {
				String spinner_1 = sp_1.getSelectedItem().toString();
				String spinner_2 = sp_2.getSelectedItem().toString();
				String spinner_3 = sp_3.getSelectedItem().toString();
				int number = Integer
						.valueOf(et_number_get.getText().toString());
				if (spinner_1.equals("温度")) {
					if (spinner_2.equals(">")) {
						if (temp > number) {
							if (spinner_3.equals("射灯")) {
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
								tg_lamp.setChecked(true);
							}
							if (spinner_3.equals("风扇")) {
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
								tg_fan.setChecked(true);
							}
						} else {
							DiyToast.showToast(getApplicationContext(), "条件不满足");
							cb_diy.setChecked(false);
						}
					}
					if (spinner_2.equals("<=")) {
						if (temp <= number) {
							if (spinner_3.equals("射灯")) {
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
								tg_lamp.setChecked(true);
							}
							if (spinner_3.equals("风扇")) {
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
								tg_fan.setChecked(true);
							}
						} else {
							DiyToast.showToast(getApplicationContext(), "条件不满足");
							cb_diy.setChecked(false);
							tg_warm.setChecked(false);
							tg_lamp.setChecked(false);
						}

					}
				}
				if (spinner_1.equals("光照")) {

					if (spinner_2.equals(">")) {
						if (ill > number) {
							if (spinner_3.equals("射灯")) {
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (spinner_3.equals("风扇")) {
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
						} else {
							DiyToast.showToast(getApplicationContext(), "条件不满足");
							cb_diy.setChecked(false);
						}
					}
					if (spinner_2.equals("<=")) {
						if (ill <= number) {
							if (spinner_3.equals("射灯")) {
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (spinner_3.equals("风扇")) {
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
						} else {
							DiyToast.showToast(getApplicationContext(), "条件不满足");
							cb_diy.setChecked(false);
						}

					}

				}
			}
		}
	};

	Handler handler_temp = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			System.out.println("温度");
			ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "1",
					ConstantUtil.OPEN);
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
			ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "2",
					ConstantUtil.OPEN);
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
			ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "3",
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
			ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.OPEN,
					ConstantUtil.OPEN);
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
			ControlUtils.control(ConstantUtil.Fan, ConstantUtil.OPEN,
					ConstantUtil.OPEN);
			handler_temp.postDelayed(timeRunnable_temp, 1000);
		}
	};
	Handler handler_lv = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			System.out.println("旅游");
			ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL,
					ConstantUtil.OPEN);
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
			ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_1,
					ConstantUtil.OPEN);
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (pm > 75) {
				ControlUtils.control(ConstantUtil.Fan,
						ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
			} else {
				ControlUtils.control(ConstantUtil.Fan,
						ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
			}
			handler_lv.postDelayed(timeRunnable_lv, 1000);
		}
	};

	Handler handler_af = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			System.out.println("安防");
			if (per == 1) {
				ControlUtils.control(ConstantUtil.WarningLight,
						ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				try {
					Thread.sleep(100);
				} catch (Exception e) {
					// TODO: handle exception
				}
				ControlUtils.control(ConstantUtil.Lamp,
						ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
			} else {
				ControlUtils.control(ConstantUtil.WarningLight,
						ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				try {
					Thread.sleep(100);
				} catch (Exception e) {
					// TODO: handle exception
				}
				ControlUtils.control(ConstantUtil.Lamp,
						ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
			}
			handler_af.postDelayed(timeRunnable_af, 1000);
		}
	};
	Runnable timeRunnable_temp = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Message msg_temp = handler_temp.obtainMessage();
			handler_temp.sendMessage(msg_temp);
		}
	};
	Runnable timeRunnable_lv = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Message msg_lv = handler_lv.obtainMessage();
			handler_lv.sendMessage(msg_lv);
		}
	};
	Runnable timeRunnable_af = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Message msg_af = handler_af.obtainMessage();
			handler_af.sendMessage(msg_af);
		}
	};

	private void set_spinner() {
		// TODO Auto-generated method stub\
		String[] mStrings1 = { "温度", "光照" };
		adapterHelper = new LinkAdapterHelper(getApplicationContext(),
				mStrings1);
		sp_1.setAdapter(adapterHelper);
		String[] mStrings2 = { ">", "<=" };
		adapterHelper = new LinkAdapterHelper(getApplicationContext(),
				mStrings2);
		sp_2.setAdapter(adapterHelper);
		String[] mStrings3 = { "风扇打开", "射灯打开" };
		adapterHelper = new LinkAdapterHelper(getApplicationContext(),
				mStrings3);
		sp_3.setAdapter(adapterHelper);
	}

	private void getdata() {
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
							press = Float.valueOf(getdata.getAirPressure());
							tv_press.setText(String.valueOf(press));
						}
						if (!TextUtils.isEmpty(getdata.getCo2())) {
							co = Float.valueOf(getdata.getCo2());
							tv_co.setText(String.valueOf(co));
						}
						if (!TextUtils.isEmpty(getdata.getGas())) {
							gas = Float.valueOf(getdata.getGas());
							tv_gas.setText(String.valueOf(gas));
						}
						if (!TextUtils.isEmpty(getdata.getHumidity())) {
							hum = Float.valueOf(getdata.getHumidity());
							tv_hum.setText(String.valueOf(hum));
						}
						if (!TextUtils.isEmpty(getdata.getIllumination())) {
							ill = Float.valueOf(getdata.getIllumination());
							tv_ill.setText(String.valueOf(ill));
						}
						if (!TextUtils.isEmpty(getdata.getSmoke())) {
							smo = Float.valueOf(getdata.getSmoke());
							tv_smo.setText(String.valueOf(smo));
						}
						if (!TextUtils.isEmpty(getdata.getTemperature())) {
							temp = Float.valueOf(getdata.getTemperature());
							tv_temp.setText(String.valueOf(temp));
						}
						if (!TextUtils.isEmpty(getdata.getPM25())) {
							pm = Float.valueOf(getdata.getPM25());
							tv_pm.setText(String.valueOf(pm));
						}
						if (!TextUtils.isEmpty(getdata.getStateHumanInfrared())) {
							per = Float.valueOf(getdata.getStateHumanInfrared());
							if (per == 1) {
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

	private void start_link() {
		ControlUtils.setUser("bizideal", "123456", AppTools.IP);
		SocketClient.getInstance().creatConnect();
		SocketClient.getInstance().login(new LoginCallback() {

			@Override
			public void onEvent(final String arg0) {
				// TODO Auto-generated method stub
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (arg0.toString().equals(ConstantUtil.SUCCESS)) {
							DiyToast.showToast(getApplicationContext(),
									"网络连接成功");
							getdata();
						} else {
							DiyToast.showToast(getApplicationContext(),
									"网络连接失败");
							getdata();
						}
					}
				});
			}
		});
	}

	private void initView() {
		// TODO Auto-generated method stub
		sp_1 = (Spinner) findViewById(R.id.spinner1);
		sp_2 = (Spinner) findViewById(R.id.spinner2);
		sp_3 = (Spinner) findViewById(R.id.spinner3);
		btn_cur_cls = (Button) findViewById(R.id.btn_cur_cls);
		btn_cur_open = (Button) findViewById(R.id.btn_cur_open);
		btn_cur_stop = (Button) findViewById(R.id.btn_cur_stop);
		btn_dvd = (Button) findViewById(R.id.btn_dvd);
		btn_kt = (Button) findViewById(R.id.btn_kt);
		btn_link_state = (Button) findViewById(R.id.btn_link_state);
		btn_tv = (Button) findViewById(R.id.btn_tv);
		tg_door = (ToggleButton) findViewById(R.id.tg_door);
		tg_fan = (ToggleButton) findViewById(R.id.tg_fan);
		tg_lamp = (ToggleButton) findViewById(R.id.tg_lamp);
		tg_warm = (ToggleButton) findViewById(R.id.tg_warm);
		tv_co = (TextView) findViewById(R.id.tv_co);
		tv_gas = (TextView) findViewById(R.id.tv_gas);
		tv_hum = (TextView) findViewById(R.id.tv_hum);
		tv_ill = (TextView) findViewById(R.id.tv_ill);
		tv_per = (TextView) findViewById(R.id.tv_per);
		tv_pm = (TextView) findViewById(R.id.tv_pm);
		tv_press = (TextView) findViewById(R.id.tv_press);
		tv_smo = (TextView) findViewById(R.id.tv_smo);
		tv_temp = (TextView) findViewById(R.id.tv_temp);
		cb_af = (CheckBox) findViewById(R.id.cb_af_mode);
		cb_temp = (CheckBox) findViewById(R.id.cb_temp_mode);
		cb_lv = (CheckBox) findViewById(R.id.cb_ly_mode);
		cb_diy = (CheckBox) findViewById(R.id.cb_diy_mode);
		et_number_get = (EditText) findViewById(R.id.et_number_get);
	}
}
