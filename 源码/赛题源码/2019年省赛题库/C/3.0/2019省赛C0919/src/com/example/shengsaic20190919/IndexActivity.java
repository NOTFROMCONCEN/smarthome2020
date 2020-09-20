package com.example.shengsaic20190919;

import android.app.Activity;
import android.os.Bundle;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.DataCallback;
import com.bizideal.smarthome.socket.DeviceBean;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;
import com.example.shengsaic20190919.toools.DiyToast;
import com.example.shengsaic20190919.toools.LinkAdapterHelper;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO 基本界面、数据采集、设备控制、联动模式
 * @package_name com.example.shengsaic20190919
 * @project_name 2019省赛C0919
 * @file_name IndexActivity.java
 * @我的博客 https://naiyouhuameitang.club/
 */
public class IndexActivity extends Activity {

	private TextView tv_temp, tv_hum, tv_press, tv_smo, tv_gas, tv_per, tv_ill,
			tv_co, tv_pm;
	public static float temp, hum, press, gas, smo, per, ill, co, pm;
	private Button btn_server_link, btn_tv, btn_dvd, btn_kt, btn_cur_open,
			btn_cur_cls, btn_cur_stop;
	private ToggleButton tg_lamp, tg_warm, tg_fan, tg_door;
	private CheckBox cb_temp, cb_lv, cb_af, cb_diy;
	private boolean lv_mode = false;
	private boolean af_mode = false;
	private boolean diy_mode = false;
	private LinkAdapterHelper adapterHelper;
	private Spinner sp_1, sp_2, sp_3;
	private EditText et_link_number;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index);
		initView();// 控件绑定
		initWeb();// 联网
		set_spinner();// 设置Spinner样式
		// 温度模式
		cb_temp.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "1",
							ConstantUtil.OPEN);
					try {
						Thread.sleep(500);
					} catch (Exception e) {
						// TODO: handle exception
					}
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "5",
							ConstantUtil.OPEN);
					try {
						Thread.sleep(500);
					} catch (Exception e) {
						// TODO: handle exception
					}
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "8",
							ConstantUtil.OPEN);
					try {
						Thread.sleep(500);
					} catch (Exception e) {
						// TODO: handle exception
					}
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					tg_warm.setChecked(true);
					try {
						Thread.sleep(500);
					} catch (Exception e) {
						// TODO: handle exception
					}
					ControlUtils.control(ConstantUtil.Fan,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					tg_fan.setChecked(true);
					try {
						Thread.sleep(500);
					} catch (Exception e) {
						// TODO: handle exception
					}
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					tg_lamp.setChecked(true);
					try {
						Thread.sleep(500);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
		});
		// 旅游模式
		cb_lv.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					lv_mode = true;
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					try {
						Thread.sleep(500);
					} catch (Exception e) {
						// TODO: handle exception
					}
					ControlUtils.control(ConstantUtil.Curtain,
							ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
					new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							while (lv_mode) {
								try {
									Thread.sleep(500);
								} catch (Exception e) {
									// TODO: handle exception
								}
								if (pm == 75) {
									runOnUiThread(new Runnable() {
										public void run() {
											tg_fan.setChecked(true);
										}
									});
								} else {
									runOnUiThread(new Runnable() {
										public void run() {
											tg_fan.setChecked(false);
										}
									});
								}
							}
						}
					}).start();
				} else {
					lv_mode = false;
				}
			}
		});
		// 安防模式
		cb_af.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					af_mode = true;
					new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							while (af_mode) {
								try {
									Thread.sleep(500);
								} catch (Exception e) {
									// TODO: handle exception
								}
								if (per == 1) {
									runOnUiThread(new Runnable() {
										public void run() {
											tg_lamp.setChecked(true);
											tg_warm.setChecked(true);
										}
									});
								} else {
									runOnUiThread(new Runnable() {
										public void run() {
											tg_lamp.setChecked(false);
											tg_warm.setChecked(false);
										}
									});
								}
							}
						}
					}).start();
				} else {
					af_mode = false;
				}
			}
		});
		// 开启设备连接状态界面
		btn_server_link.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
		// dvd
		btn_dvd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "5",
						ConstantUtil.OPEN);
			}
		});
		// 空调
		btn_kt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "1",
						ConstantUtil.OPEN);
			}
		});
		// 电视
		btn_tv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "8",
						ConstantUtil.OPEN);
			}
		});
		// 窗帘关
		btn_cur_cls.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.Curtain,
						ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
			}
		});
		// 窗帘开
		btn_cur_open.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.Curtain,
						ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
			}
		});
		// 窗帘停止
		btn_cur_stop.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.Curtain,
						ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
			}
		});
		// 门禁
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
		// 风扇
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
		// 报警灯
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
		// 射灯
		tg_lamp.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					System.out.println(11111);
				} else {
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
		});
		// 自定义模式
		cb_diy.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					diy_mode = true;
				} else {
					diy_mode = false;
				}
			}
		});
		// 开启线程
		handler_diy.post(timeRunnable_diy);
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
			if (diy_mode) {
				if (et_link_number.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "请输入数值");
					diy_mode = false;
					cb_diy.setChecked(false);
				} else {
					String spinner_1 = sp_1.getSelectedItem().toString();
					String spinner_2 = sp_2.getSelectedItem().toString();
					String spinner_3 = sp_3.getSelectedItem().toString();
					int number = Integer.valueOf(et_link_number.getText()
							.toString());
					if (spinner_1.equals("温度")) {
						if (spinner_2.equals(">")) {
							if (temp > number) {
								if (spinner_3.equals("射灯")) {
									tg_lamp.setChecked(true);
								}
								if (spinner_3.equals("风扇")) {
									tg_fan.setChecked(true);
								}
							} else {
								DiyToast.showToast(getApplicationContext(),
										"条件不满足");
								diy_mode = false;
								cb_diy.setChecked(false);
								tg_warm.setChecked(false);
								tg_lamp.setChecked(false);
							}
						}
						if (spinner_2.equals("<=")) {
							if (temp < number) {
								if (spinner_3.equals("射灯")) {
									tg_lamp.setChecked(true);
								}
								if (spinner_3.equals("风扇")) {
									tg_fan.setChecked(true);
								}
							} else {
								DiyToast.showToast(getApplicationContext(),
										"条件不满足");
								diy_mode = false;
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
									tg_lamp.setChecked(true);
								}
								if (spinner_3.equals("风扇")) {
									tg_fan.setChecked(true);
								}
							} else {
								DiyToast.showToast(getApplicationContext(),
										"条件不满足");
								diy_mode = false;
								cb_diy.setChecked(false);
								tg_warm.setChecked(false);
								tg_lamp.setChecked(false);
							}
						}
						if (spinner_2.equals("<=")) {
							if (ill < number) {
								if (spinner_3.equals("射灯")) {
									tg_lamp.setChecked(true);
								}
								if (spinner_3.equals("风扇")) {
									tg_lamp.setChecked(true);
								}
							} else {
								DiyToast.showToast(getApplicationContext(),
										"条件不满足");
								diy_mode = false;
								cb_diy.setChecked(false);
								tg_warm.setChecked(false);
								tg_lamp.setChecked(false);

							}

						}
					}
				}
			}
			handler_diy.post(timeRunnable_diy);
		}
	};

	private void initData() {
		// TODO Auto-generated method stub
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
					}
				});
			}
		});
	}

	private void initView() {
		// TODO Auto-generated method stub
		tv_co = (TextView) findViewById(R.id.tv_co);
		tv_gas = (TextView) findViewById(R.id.tv_gas);
		tv_hum = (TextView) findViewById(R.id.tv_hum);
		tv_ill = (TextView) findViewById(R.id.tv_ill);
		tv_per = (TextView) findViewById(R.id.tv_per);
		tv_pm = (TextView) findViewById(R.id.tv_pm);
		tv_press = (TextView) findViewById(R.id.tv_press);
		tv_smo = (TextView) findViewById(R.id.tv_smo);
		tv_temp = (TextView) findViewById(R.id.tv_temp);
		btn_cur_cls = (Button) findViewById(R.id.btn_cur_cls);
		btn_cur_open = (Button) findViewById(R.id.btn_cur_open);
		btn_cur_stop = (Button) findViewById(R.id.btn_cur_stop);
		btn_dvd = (Button) findViewById(R.id.btn_dvd);
		btn_kt = (Button) findViewById(R.id.btn_kt);
		btn_server_link = (Button) findViewById(R.id.btn_server_link);
		btn_tv = (Button) findViewById(R.id.btn_tv);
		tg_door = (ToggleButton) findViewById(R.id.tg_door);
		tg_fan = (ToggleButton) findViewById(R.id.tg_fan);
		tg_lamp = (ToggleButton) findViewById(R.id.tg_lamp);
		tg_warm = (ToggleButton) findViewById(R.id.tg_warm);
		cb_af = (CheckBox) findViewById(R.id.cb_af);
		cb_lv = (CheckBox) findViewById(R.id.cb_lv);
		cb_diy = (CheckBox) findViewById(R.id.cb_diy);
		cb_temp = (CheckBox) findViewById(R.id.cb_temp);
		et_link_number = (EditText) findViewById(R.id.et_link_number);
		sp_1 = (Spinner) findViewById(R.id.spinner1);
		sp_2 = (Spinner) findViewById(R.id.spinner2);
		sp_3 = (Spinner) findViewById(R.id.spinner3);
	}

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

	private void initWeb() {
		// TODO Auto-generated method stub
		ControlUtils.setUser("bizideal", "123465", SocketClient.ip);
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
							DiyToast.showToast(getApplicationContext(), "组网成功");
							initData();
						} else {
							DiyToast.showToast(getApplicationContext(), "组网失败");
							initData();
						}
					}
				});
			}
		});
	}
}
