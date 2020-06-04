package com.example.shengsaidemo42019;

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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.DataCallback;
import com.bizideal.smarthome.socket.DeviceBean;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;
import com.example.shengsaidemo42019.toast.DiyToast;

/**
 * @author Administrator
 * @year 2019
 * @Todo TODO 主界面 （数据采集、时间、设备控制、联动模式）
 * @package_name com.example.shengsaidemo42019
 * @project_name 2019ShengSaiDemo4
 * @file_name IndexActivity.java
 */
public class IndexActivity extends Activity {
	// 设备控制
	private Button btn_tv_open, btn_tv_cls, btn_kt_open, btn_kt_cls,
			btn_dvd_open, btn_dvd_cls, btn_cur_open, btn_cur_cls, btn_cur_stop;
	private CheckBox cb_lamp;
	private CheckBox cb_warm, cb_door, cb_fan;
	// 数据采集
	private TextView tv_temp, tv_hum, tv_gas, tv_press, tv_smo, tv_ill, tv_co,
			tv_pm, tv_per;
	// 数据传输\格式转换
	private float temp, hum, gas, smo, ill, co, per;
	// 联动模式
	private RadioButton ra_open, ra_cls, ra_big, ra_small, ra_temp, ra_hum,
			ra_ill, ra_per, ra_smo, ra_gas, ra_co, ra_lamp, ra_fan, ra_warm,
			ra_cur, ra_tv, ra_dvd, ra_kt;
	private EditText et_number_get;
	private CheckBox cb_link_state;
	private boolean link_stet = false;
	private int number = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index);
		initView();// 绑定控件
		web_server();// 网络连接
		get_data();// 获取数据
		handler.post(timeRunnable);// 启用进程
		/**
		 * 开启联动模式
		 */
		cb_link_state.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (et_number_get.getText().toString().isEmpty()) {
						DiyToast.showToast(getApplicationContext(), "数值不能为空");
						link_stet = false;
						cb_link_state.setChecked(false);
						number = 0;
					} else {
						number = 0;
						link_stet = true;
					}
				} else {
					number = 0;
					link_stet = false;
				}
			}
		});
		/** 重置计数器 **/
		ra_open.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				number = 0;
			}
		});
		/** 重置计数器 **/
		ra_cls.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				number = 0;
			}
		});
		/** 重置计数器 **/
		ra_big.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				number = 0;
			}
		});
		/** 重置计数器 **/
		ra_small.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				number = 0;
			}
		});
		/** 重置计数器 **/
		ra_kt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				number = 0;
			}
		});
		/** 重置计数器 **/
		ra_tv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				number = 0;
			}
		});
		/** 重置计数器 **/
		ra_dvd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				number = 0;
			}
		});
		// 设备控制
		/**
		 * 门禁
		 */
		cb_door.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.RFID_Open_Door,
							ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
					CountDownTimer timer = new CountDownTimer(1000, 3000) {

						@Override
						public void onTick(long millisUntilFinished) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onFinish() {
							// TODO Auto-generated method stub
							cb_door.setChecked(false);
							DiyToast.showToast(getApplicationContext(), "门禁关闭");
						}
					}.start();
				}
			}
		});
		/**
		 * 风扇
		 */
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
		/**
		 * 射灯
		 */
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
		/**
		 * 报警灯
		 */
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
		/**
		 * 窗帘关
		 */
		btn_cur_cls.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.Curtain,
						ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
			}
		});
		/**
		 * 窗帘停
		 */
		btn_cur_stop.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.Curtain,
						ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
			}
		});
		/**
		 * 窗帘开
		 */
		btn_cur_open.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.Curtain,
						ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
			}
		});
		/**
		 * DVD关
		 */
		btn_dvd_cls.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "8",
						ConstantUtil.OPEN);
			}
		});
		/**
		 * DVD开
		 */
		btn_dvd_open.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "8",
						ConstantUtil.OPEN);
			}
		});
		/**
		 * 空调关
		 */
		btn_kt_cls.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "1",
						ConstantUtil.OPEN);
			}
		});
		/**
		 * 空调开
		 */
		btn_kt_open.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "1",
						ConstantUtil.OPEN);
			}
		});
		/**
		 * 电视关
		 */
		btn_tv_cls.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "5",
						ConstantUtil.OPEN);
			}
		});
		/**
		 * 电视开
		 */
		btn_tv_open.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "5",
						ConstantUtil.OPEN);
			}
		});
	}

	/**
	 * 绑定控件
	 */
	private void initView() {
		// TODO Auto-generated method stub
		cb_door = (CheckBox) findViewById(R.id.cb_door);
		cb_fan = (CheckBox) findViewById(R.id.cb_fan);
		cb_lamp = (CheckBox) findViewById(R.id.cb_lamp);
		cb_warm = (CheckBox) findViewById(R.id.cb_warm);
		btn_cur_cls = (Button) findViewById(R.id.btn_cur_cls);
		btn_cur_open = (Button) findViewById(R.id.btn_cur_open);
		btn_cur_stop = (Button) findViewById(R.id.btn_cur_stop);
		btn_dvd_cls = (Button) findViewById(R.id.btn_dvd_cls);
		btn_dvd_open = (Button) findViewById(R.id.btn_dvd_open);
		btn_kt_cls = (Button) findViewById(R.id.btn_kt_cls);
		btn_kt_open = (Button) findViewById(R.id.btn_kt_open);
		btn_tv_open = (Button) findViewById(R.id.btn_tv_open);
		btn_tv_cls = (Button) findViewById(R.id.btn_tv_cls);
		tv_co = (TextView) findViewById(R.id.tv_co);
		tv_gas = (TextView) findViewById(R.id.tv_gas);
		tv_hum = (TextView) findViewById(R.id.tv_hum);
		tv_ill = (TextView) findViewById(R.id.tv_ill);
		tv_per = (TextView) findViewById(R.id.tv_per);
		tv_pm = (TextView) findViewById(R.id.tv_pm);
		tv_press = (TextView) findViewById(R.id.tv_press);
		tv_smo = (TextView) findViewById(R.id.tv_smo);
		tv_temp = (TextView) findViewById(R.id.tv_temp);
		ra_big = (RadioButton) findViewById(R.id.ra_big);
		ra_small = (RadioButton) findViewById(R.id.ra_small);
		ra_open = (RadioButton) findViewById(R.id.ra_open);
		ra_cls = (RadioButton) findViewById(R.id.ra_cls);
		ra_temp = (RadioButton) findViewById(R.id.ra_temp);
		ra_hum = (RadioButton) findViewById(R.id.ra_hum);
		ra_ill = (RadioButton) findViewById(R.id.ra_ill);
		ra_per = (RadioButton) findViewById(R.id.ra_per);
		ra_smo = (RadioButton) findViewById(R.id.ra_smo);
		ra_gas = (RadioButton) findViewById(R.id.ra_gas);
		ra_co = (RadioButton) findViewById(R.id.ra_co);
		ra_lamp = (RadioButton) findViewById(R.id.ra_temp);
		ra_fan = (RadioButton) findViewById(R.id.ra_fan);
		ra_warm = (RadioButton) findViewById(R.id.ra_warm);
		ra_cur = (RadioButton) findViewById(R.id.ra_cur);
		ra_tv = (RadioButton) findViewById(R.id.ra_tv);
		ra_kt = (RadioButton) findViewById(R.id.ra_kt);
		ra_dvd = (RadioButton) findViewById(R.id.ra_dvd);
		cb_link_state = (CheckBox) findViewById(R.id.cb_link_state);
		et_number_get = (EditText) findViewById(R.id.et_number_get);

	}

	/**
	 * 数据采集
	 */
	private void get_data() {
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
						if (!TextUtils.isEmpty(getdata.getSmoke())) {
							tv_smo.setText(getdata.getSmoke());
							smo = Float.valueOf(getdata.getSmoke());
						}
						if (!TextUtils.isEmpty(getdata.getTemperature())) {
							tv_temp.setText(getdata.getTemperature());
							temp = Float.valueOf(getdata.getTemperature());
						}
						if (!TextUtils.isEmpty(getdata.getPM25())) {
							tv_pm.setText(getdata.getPM25());
						}
						if (!TextUtils.isEmpty(getdata.getStateHumanInfrared())) {
							if (getdata.getStateHumanInfrared().toString()
									.equals(ConstantUtil.CLOSE)) {
								tv_per.setText("无人");
								per = 0;
							} else {
								tv_per.setText("有人");
								per = 1;
							}
						}
					}
				});
			}
		});
	}

	/**
	 * 网络连接
	 */
	private void web_server() {
		// TODO Auto-generated method stub
		ControlUtils.setUser("bizideal", "123456", "18.1.10.7");
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
						} else {
							DiyToast.showToast(getApplicationContext(),
									"网络连接失败");
						}
					}
				});
			}
		});
	}

	/**
	 * 联动模式进程
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (link_stet) {
				if (et_number_get.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "数值不能为空");
					link_stet = false;
					cb_link_state.setChecked(false);
				} else {
					int number_get = Integer.valueOf(et_number_get.getText()
							.toString());
					if (ra_big.isChecked()) {
						if (ra_temp.isChecked()) {
							if (ra_open.isChecked()) {
								if (temp > number_get) {
									number++;
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_cur.isChecked()) {
										if (number == 2) {
											ControlUtils.control(
													ConstantUtil.Curtain,
													ConstantUtil.CHANNEL_3,
													ConstantUtil.OPEN);
										}
									}
									if (ra_tv.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"5",
															ConstantUtil.OPEN);
										}
									}
									if (ra_dvd.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"8",
															ConstantUtil.OPEN);
										}
									}
									if (ra_kt.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"1",
															ConstantUtil.OPEN);
										}
									}
								} else {
									DiyToast.showToast(getApplicationContext(),
											"条件不满足");
									link_stet = false;
									cb_link_state.setChecked(false);
								}
							}
							if (ra_cls.isChecked()) {
								if (temp > number_get) {
									number++;
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_cur.isChecked()) {
										if (number == 2) {
											ControlUtils.control(
													ConstantUtil.Curtain,
													ConstantUtil.CHANNEL_1,
													ConstantUtil.OPEN);
										}
									}
									if (ra_tv.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"5",
															ConstantUtil.OPEN);
										}
									}
									if (ra_dvd.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"8",
															ConstantUtil.OPEN);
										}
									}
									if (ra_kt.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"1",
															ConstantUtil.OPEN);
										}
									}
								} else {
									DiyToast.showToast(getApplicationContext(),
											"条件不满足");
									link_stet = false;
									cb_link_state.setChecked(false);
								}
							}
						}
						if (ra_hum.isChecked()) {
							if (ra_open.isChecked()) {
								if (hum > number_get) {
									number++;
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_cur.isChecked()) {
										if (number == 2) {
											ControlUtils.control(
													ConstantUtil.Curtain,
													ConstantUtil.CHANNEL_3,
													ConstantUtil.OPEN);
										}
									}
									if (ra_tv.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"5",
															ConstantUtil.OPEN);
										}
									}
									if (ra_dvd.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"8",
															ConstantUtil.OPEN);
										}
									}
									if (ra_kt.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"1",
															ConstantUtil.OPEN);
										}
									}
								} else {
									DiyToast.showToast(getApplicationContext(),
											"条件不满足");
									link_stet = false;
									cb_link_state.setChecked(false);
								}
							}
							if (ra_cls.isChecked()) {
								if (hum > number_get) {
									number++;
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_cur.isChecked()) {
										if (number == 2) {
											ControlUtils.control(
													ConstantUtil.Curtain,
													ConstantUtil.CHANNEL_1,
													ConstantUtil.OPEN);
										}
									}
									if (ra_tv.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"5",
															ConstantUtil.OPEN);
										}
									}
									if (ra_dvd.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"8",
															ConstantUtil.OPEN);
										}
									}
									if (ra_kt.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"1",
															ConstantUtil.OPEN);
										}
									}
								} else {
									DiyToast.showToast(getApplicationContext(),
											"条件不满足");
									link_stet = false;
									cb_link_state.setChecked(false);
								}
							}
						}

						if (ra_ill.isChecked()) {

							if (ra_open.isChecked()) {
								if (ill > number_get) {
									number++;
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_cur.isChecked()) {
										if (number == 2) {
											ControlUtils.control(
													ConstantUtil.Curtain,
													ConstantUtil.CHANNEL_3,
													ConstantUtil.OPEN);
										}
									}
									if (ra_tv.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"5",
															ConstantUtil.OPEN);
										}
									}
									if (ra_dvd.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"8",
															ConstantUtil.OPEN);
										}
									}
									if (ra_kt.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"1",
															ConstantUtil.OPEN);
										}
									}
								} else {
									DiyToast.showToast(getApplicationContext(),
											"条件不满足");
									link_stet = false;
									cb_link_state.setChecked(false);
								}
							}
							if (ra_cls.isChecked()) {
								if (ill > number_get) {
									number++;
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_cur.isChecked()) {
										if (number == 2) {
											ControlUtils.control(
													ConstantUtil.Curtain,
													ConstantUtil.CHANNEL_1,
													ConstantUtil.OPEN);
										}
									}
									if (ra_tv.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"5",
															ConstantUtil.OPEN);
										}
									}
									if (ra_dvd.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"8",
															ConstantUtil.OPEN);
										}
									}
									if (ra_kt.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"1",
															ConstantUtil.OPEN);
										}
									}
								} else {
									DiyToast.showToast(getApplicationContext(),
											"条件不满足");
									link_stet = false;
									cb_link_state.setChecked(false);
								}
							}

						}

						if (ra_gas.isChecked()) {

							if (ra_open.isChecked()) {
								if (gas > number_get) {
									number++;
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_cur.isChecked()) {
										if (number == 2) {
											ControlUtils.control(
													ConstantUtil.Curtain,
													ConstantUtil.CHANNEL_3,
													ConstantUtil.OPEN);
										}
									}
									if (ra_tv.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"5",
															ConstantUtil.OPEN);
										}
									}
									if (ra_dvd.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"8",
															ConstantUtil.OPEN);
										}
									}
									if (ra_kt.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"1",
															ConstantUtil.OPEN);
										}
									}
								} else {
									DiyToast.showToast(getApplicationContext(),
											"条件不满足");
									link_stet = false;
									cb_link_state.setChecked(false);
								}
							}
							if (ra_cls.isChecked()) {
								if (gas > number_get) {
									number++;
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_cur.isChecked()) {
										if (number == 2) {
											ControlUtils.control(
													ConstantUtil.Curtain,
													ConstantUtil.CHANNEL_1,
													ConstantUtil.OPEN);
										}
									}
									if (ra_tv.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"5",
															ConstantUtil.OPEN);
										}
									}
									if (ra_dvd.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"8",
															ConstantUtil.OPEN);
										}
									}
									if (ra_kt.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"1",
															ConstantUtil.OPEN);
										}
									}
								} else {
									DiyToast.showToast(getApplicationContext(),
											"条件不满足");
									link_stet = false;
									cb_link_state.setChecked(false);
								}
							}

						}

						if (ra_smo.isChecked()) {

							if (ra_open.isChecked()) {
								if (smo > number_get) {
									number++;
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_cur.isChecked()) {
										if (number == 2) {
											ControlUtils.control(
													ConstantUtil.Curtain,
													ConstantUtil.CHANNEL_3,
													ConstantUtil.OPEN);
										}
									}
									if (ra_tv.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"5",
															ConstantUtil.OPEN);
										}
									}
									if (ra_dvd.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"8",
															ConstantUtil.OPEN);
										}
									}
									if (ra_kt.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"1",
															ConstantUtil.OPEN);
										}
									}
								} else {
									DiyToast.showToast(getApplicationContext(),
											"条件不满足");
									link_stet = false;
									cb_link_state.setChecked(false);
								}
							}
							if (ra_cls.isChecked()) {
								if (smo > number_get) {
									number++;
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_cur.isChecked()) {
										if (number == 2) {
											ControlUtils.control(
													ConstantUtil.Curtain,
													ConstantUtil.CHANNEL_1,
													ConstantUtil.OPEN);
										}
									}
									if (ra_tv.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"5",
															ConstantUtil.OPEN);
										}
									}
									if (ra_dvd.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"8",
															ConstantUtil.OPEN);
										}
									}
									if (ra_kt.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"1",
															ConstantUtil.OPEN);
										}
									}
								} else {
									DiyToast.showToast(getApplicationContext(),
											"条件不满足");
									link_stet = false;
									cb_link_state.setChecked(false);
								}
							}

						}

						if (ra_per.isChecked()) {

							if (ra_open.isChecked()) {
								if (per > number_get) {
									number++;
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_cur.isChecked()) {
										if (number == 2) {
											ControlUtils.control(
													ConstantUtil.Curtain,
													ConstantUtil.CHANNEL_3,
													ConstantUtil.OPEN);
										}
									}
									if (ra_tv.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"5",
															ConstantUtil.OPEN);
										}
									}
									if (ra_dvd.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"8",
															ConstantUtil.OPEN);
										}
									}
									if (ra_kt.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"1",
															ConstantUtil.OPEN);
										}
									}
								} else {
									DiyToast.showToast(getApplicationContext(),
											"条件不满足");
									link_stet = false;
									cb_link_state.setChecked(false);
								}
							}
							if (ra_cls.isChecked()) {
								if (per > number_get) {
									number++;
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_cur.isChecked()) {
										if (number == 2) {
											ControlUtils.control(
													ConstantUtil.Curtain,
													ConstantUtil.CHANNEL_1,
													ConstantUtil.OPEN);
										}
									}
									if (ra_tv.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"5",
															ConstantUtil.OPEN);
										}
									}
									if (ra_dvd.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"8",
															ConstantUtil.OPEN);
										}
									}
									if (ra_kt.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"1",
															ConstantUtil.OPEN);
										}
									}
								} else {
									DiyToast.showToast(getApplicationContext(),
											"条件不满足");
									link_stet = false;
									cb_link_state.setChecked(false);
								}
							}

						}

						if (ra_co.isChecked()) {

							if (ra_open.isChecked()) {
								if (co > number_get) {
									number++;
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_cur.isChecked()) {
										if (number == 2) {
											ControlUtils.control(
													ConstantUtil.Curtain,
													ConstantUtil.CHANNEL_3,
													ConstantUtil.OPEN);
										}
									}
									if (ra_tv.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"5",
															ConstantUtil.OPEN);
										}
									}
									if (ra_dvd.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"8",
															ConstantUtil.OPEN);
										}
									}
									if (ra_kt.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"1",
															ConstantUtil.OPEN);
										}
									}
								} else {
									DiyToast.showToast(getApplicationContext(),
											"条件不满足");
									link_stet = false;
									cb_link_state.setChecked(false);
								}
							}
							if (ra_cls.isChecked()) {
								if (co > number_get) {
									number++;
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_cur.isChecked()) {
										if (number == 2) {
											ControlUtils.control(
													ConstantUtil.Curtain,
													ConstantUtil.CHANNEL_1,
													ConstantUtil.OPEN);
										}
									}
									if (ra_tv.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"5",
															ConstantUtil.OPEN);
										}
									}
									if (ra_dvd.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"8",
															ConstantUtil.OPEN);
										}
									}
									if (ra_kt.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"1",
															ConstantUtil.OPEN);
										}
									}
								} else {
									DiyToast.showToast(getApplicationContext(),
											"条件不满足");
									link_stet = false;
									cb_link_state.setChecked(false);
								}
							}

						}

					}

					if (ra_small.isChecked()) {

						if (ra_temp.isChecked()) {
							if (ra_open.isChecked()) {
								if (temp < number_get) {
									number++;
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_cur.isChecked()) {
										if (number == 2) {
											ControlUtils.control(
													ConstantUtil.Curtain,
													ConstantUtil.CHANNEL_3,
													ConstantUtil.OPEN);
										}
									}
									if (ra_tv.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"5",
															ConstantUtil.OPEN);
										}
									}
									if (ra_dvd.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"8",
															ConstantUtil.OPEN);
										}
									}
									if (ra_kt.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"1",
															ConstantUtil.OPEN);
										}
									}
								} else {
									DiyToast.showToast(getApplicationContext(),
											"条件不满足");
									link_stet = false;
									cb_link_state.setChecked(false);
								}
							}
							if (ra_cls.isChecked()) {
								if (temp < number_get) {
									number++;
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_cur.isChecked()) {
										if (number == 2) {
											ControlUtils.control(
													ConstantUtil.Curtain,
													ConstantUtil.CHANNEL_1,
													ConstantUtil.OPEN);
										}
									}
									if (ra_tv.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"5",
															ConstantUtil.OPEN);
										}
									}
									if (ra_dvd.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"8",
															ConstantUtil.OPEN);
										}
									}
									if (ra_kt.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"1",
															ConstantUtil.OPEN);
										}
									}
								} else {
									DiyToast.showToast(getApplicationContext(),
											"条件不满足");
									link_stet = false;
									cb_link_state.setChecked(false);
								}
							}
						}
						if (ra_hum.isChecked()) {
							if (ra_open.isChecked()) {
								if (hum < number_get) {
									number++;
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_cur.isChecked()) {
										if (number == 2) {
											ControlUtils.control(
													ConstantUtil.Curtain,
													ConstantUtil.CHANNEL_3,
													ConstantUtil.OPEN);
										}
									}
									if (ra_tv.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"5",
															ConstantUtil.OPEN);
										}
									}
									if (ra_dvd.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"8",
															ConstantUtil.OPEN);
										}
									}
									if (ra_kt.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"1",
															ConstantUtil.OPEN);
										}
									}
								} else {
									DiyToast.showToast(getApplicationContext(),
											"条件不满足");
									link_stet = false;
									cb_link_state.setChecked(false);
								}
							}
							if (ra_cls.isChecked()) {
								if (hum < number_get) {
									number++;
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_cur.isChecked()) {
										if (number == 2) {
											ControlUtils.control(
													ConstantUtil.Curtain,
													ConstantUtil.CHANNEL_1,
													ConstantUtil.OPEN);
										}
									}
									if (ra_tv.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"5",
															ConstantUtil.OPEN);
										}
									}
									if (ra_dvd.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"8",
															ConstantUtil.OPEN);
										}
									}
									if (ra_kt.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"1",
															ConstantUtil.OPEN);
										}
									}
								} else {
									DiyToast.showToast(getApplicationContext(),
											"条件不满足");
									link_stet = false;
									cb_link_state.setChecked(false);
								}
							}
						}

						if (ra_ill.isChecked()) {

							if (ra_open.isChecked()) {
								if (ill < number_get) {
									number++;
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_cur.isChecked()) {
										if (number == 2) {
											ControlUtils.control(
													ConstantUtil.Curtain,
													ConstantUtil.CHANNEL_3,
													ConstantUtil.OPEN);
										}
									}
									if (ra_tv.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"5",
															ConstantUtil.OPEN);
										}
									}
									if (ra_dvd.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"8",
															ConstantUtil.OPEN);
										}
									}
									if (ra_kt.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"1",
															ConstantUtil.OPEN);
										}
									}
								} else {
									DiyToast.showToast(getApplicationContext(),
											"条件不满足");
									link_stet = false;
									cb_link_state.setChecked(false);
								}
							}
							if (ra_cls.isChecked()) {
								if (ill < number_get) {
									number++;
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_cur.isChecked()) {
										if (number == 2) {
											ControlUtils.control(
													ConstantUtil.Curtain,
													ConstantUtil.CHANNEL_1,
													ConstantUtil.OPEN);
										}
									}
									if (ra_tv.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"5",
															ConstantUtil.OPEN);
										}
									}
									if (ra_dvd.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"8",
															ConstantUtil.OPEN);
										}
									}
									if (ra_kt.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"1",
															ConstantUtil.OPEN);
										}
									}
								} else {
									DiyToast.showToast(getApplicationContext(),
											"条件不满足");
									link_stet = false;
									cb_link_state.setChecked(false);
								}
							}

						}

						if (ra_gas.isChecked()) {

							if (ra_open.isChecked()) {
								if (gas < number_get) {
									number++;
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_cur.isChecked()) {
										if (number == 2) {
											ControlUtils.control(
													ConstantUtil.Curtain,
													ConstantUtil.CHANNEL_3,
													ConstantUtil.OPEN);
										}
									}
									if (ra_tv.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"5",
															ConstantUtil.OPEN);
										}
									}
									if (ra_dvd.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"8",
															ConstantUtil.OPEN);
										}
									}
									if (ra_kt.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"1",
															ConstantUtil.OPEN);
										}
									}
								} else {
									DiyToast.showToast(getApplicationContext(),
											"条件不满足");
									link_stet = false;
									cb_link_state.setChecked(false);
								}
							}
							if (ra_cls.isChecked()) {
								if (gas < number_get) {
									number++;
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_cur.isChecked()) {
										if (number == 2) {
											ControlUtils.control(
													ConstantUtil.Curtain,
													ConstantUtil.CHANNEL_1,
													ConstantUtil.OPEN);
										}
									}
									if (ra_tv.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"5",
															ConstantUtil.OPEN);
										}
									}
									if (ra_dvd.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"8",
															ConstantUtil.OPEN);
										}
									}
									if (ra_kt.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"1",
															ConstantUtil.OPEN);
										}
									}
								} else {
									DiyToast.showToast(getApplicationContext(),
											"条件不满足");
									link_stet = false;
									cb_link_state.setChecked(false);
								}
							}

						}

						if (ra_smo.isChecked()) {

							if (ra_open.isChecked()) {
								if (smo < number_get) {
									number++;
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_cur.isChecked()) {
										if (number == 2) {
											ControlUtils.control(
													ConstantUtil.Curtain,
													ConstantUtil.CHANNEL_3,
													ConstantUtil.OPEN);
										}
									}
									if (ra_tv.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"5",
															ConstantUtil.OPEN);
										}
									}
									if (ra_dvd.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"8",
															ConstantUtil.OPEN);
										}
									}
									if (ra_kt.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"1",
															ConstantUtil.OPEN);
										}
									}
								} else {
									DiyToast.showToast(getApplicationContext(),
											"条件不满足");
									link_stet = false;
									cb_link_state.setChecked(false);
								}
							}
							if (ra_cls.isChecked()) {
								if (smo < number_get) {
									number++;
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_cur.isChecked()) {
										if (number == 2) {
											ControlUtils.control(
													ConstantUtil.Curtain,
													ConstantUtil.CHANNEL_1,
													ConstantUtil.OPEN);
										}
									}
									if (ra_tv.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"5",
															ConstantUtil.OPEN);
										}
									}
									if (ra_dvd.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"8",
															ConstantUtil.OPEN);
										}
									}
									if (ra_kt.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"1",
															ConstantUtil.OPEN);
										}
									}
								} else {
									DiyToast.showToast(getApplicationContext(),
											"条件不满足");
									link_stet = false;
									cb_link_state.setChecked(false);
								}
							}

						}

						if (ra_per.isChecked()) {

							if (ra_open.isChecked()) {
								if (per < number_get) {
									number++;
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_cur.isChecked()) {
										if (number == 2) {
											ControlUtils.control(
													ConstantUtil.Curtain,
													ConstantUtil.CHANNEL_3,
													ConstantUtil.OPEN);
										}
									}
									if (ra_tv.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"5",
															ConstantUtil.OPEN);
										}
									}
									if (ra_dvd.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"8",
															ConstantUtil.OPEN);
										}
									}
									if (ra_kt.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"1",
															ConstantUtil.OPEN);
										}
									}
								} else {
									DiyToast.showToast(getApplicationContext(),
											"条件不满足");
									link_stet = false;
									cb_link_state.setChecked(false);
								}
							}
							if (ra_cls.isChecked()) {
								if (per < number_get) {
									number++;
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_cur.isChecked()) {
										if (number == 2) {
											ControlUtils.control(
													ConstantUtil.Curtain,
													ConstantUtil.CHANNEL_1,
													ConstantUtil.OPEN);
										}
									}
									if (ra_tv.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"5",
															ConstantUtil.OPEN);
										}
									}
									if (ra_dvd.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"8",
															ConstantUtil.OPEN);
										}
									}
									if (ra_kt.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"1",
															ConstantUtil.OPEN);
										}
									}
								} else {
									DiyToast.showToast(getApplicationContext(),
											"条件不满足");
									link_stet = false;
									cb_link_state.setChecked(false);
								}
							}

						}

						if (ra_co.isChecked()) {

							if (ra_open.isChecked()) {
								if (co < number_get) {
									number++;
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_cur.isChecked()) {
										if (number == 2) {
											ControlUtils.control(
													ConstantUtil.Curtain,
													ConstantUtil.CHANNEL_3,
													ConstantUtil.OPEN);
										}
									}
									if (ra_tv.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"5",
															ConstantUtil.OPEN);
										}
									}
									if (ra_dvd.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"8",
															ConstantUtil.OPEN);
										}
									}
									if (ra_kt.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"1",
															ConstantUtil.OPEN);
										}
									}
								} else {
									DiyToast.showToast(getApplicationContext(),
											"条件不满足");
									link_stet = false;
									cb_link_state.setChecked(false);
								}
							}
							if (ra_cls.isChecked()) {
								if (co < number_get) {
									number++;
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_cur.isChecked()) {
										if (number == 2) {
											ControlUtils.control(
													ConstantUtil.Curtain,
													ConstantUtil.CHANNEL_1,
													ConstantUtil.OPEN);
										}
									}
									if (ra_tv.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"5",
															ConstantUtil.OPEN);
										}
									}
									if (ra_dvd.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"8",
															ConstantUtil.OPEN);
										}
									}
									if (ra_kt.isChecked()) {
										if (number == 2) {
											ControlUtils
													.control(
															ConstantUtil.INFRARED_1_SERVE,
															"1",
															ConstantUtil.OPEN);
										}
									}
								} else {
									DiyToast.showToast(getApplicationContext(),
											"条件不满足");
									link_stet = false;
									cb_link_state.setChecked(false);
								}
							}

						}

					}

				}
			}
			handler.postDelayed(timeRunnable, 1200);
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
}
