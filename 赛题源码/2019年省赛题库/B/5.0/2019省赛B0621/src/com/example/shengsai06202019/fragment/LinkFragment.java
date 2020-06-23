package com.example.shengsai06202019.fragment;

import java.text.SimpleDateFormat;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.example.shengsai06202019.R;
import com.example.shengsai06202019.tools.AppConfig;
import com.example.shengsai06202019.tools.DiyToast;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO ����ģʽ
 * @package_name com.example.shengsai06202019.fragment
 * @project_name 2019ʡ��0620
 * @file_name LinkFragment.java
 * @�ҵĲ��� https://naiyouhuameitang.club/
 */
public class LinkFragment extends Fragment {
	private Spinner sp_1, sp_2;
	private EditText et_number_get, et_time_get;
	private Switch sw_warm, sw_fan, sw_lamp, sw_door;
	private CountDownTimer timer;
	private long num, min, sec;
	private TextView tv_down_time, tv_time;
	private Button btn_link_state;
	private boolean link_state = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_link, container, false);
		initView(view);
		handler.post(timeRunnable);
		/**
		 * �豸����
		 */
		// �Ž�
		sw_door.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (link_state) {
						ControlUtils.control(ConstantUtil.RFID_Open_Door,
								ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
					}
				} else {

				}
			}
		});
		// ����
		sw_fan.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (link_state) {
						ControlUtils.control(ConstantUtil.Fan,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					}
				} else {
					ControlUtils.control(ConstantUtil.Fan,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
		});
		// ���
		sw_lamp.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (link_state) {
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					}
				} else {
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
		});
		// ������
		sw_warm.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (link_state) {
						ControlUtils.control(ConstantUtil.WarningLight,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					}
				} else {
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
		});

		/**
		 * ����
		 */
		btn_link_state.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_number_get.getText().toString().isEmpty()) {
					DiyToast.showToast(getActivity(), "��������ֵ");
					link_state = false;
				} else {
					if (et_number_get.getText().toString().isEmpty()
							&& et_time_get.getText().toString().isEmpty()) {
						DiyToast.showToast(getActivity(), "�����п���Ŀ");
					} else {
						if (btn_link_state.getText().toString().equals("��������")) {
							link_state = true;
							String spinner_1 = sp_1.getSelectedItem()
									.toString();
							String spinner_2 = sp_2.getSelectedItem()
									.toString();
							int number_get = Integer.valueOf(et_number_get
									.getText().toString());
							if (spinner_1.equals("�¶�")) {
								if (spinner_2.equals(">")) {
									if (AppConfig.temp > number_get) {
										link_state = true;
										btn_link_state.setText("�ر�����");
									} else {
										link_state = false;
										DiyToast.showToast(getActivity(),
												"����������");
										btn_link_state.setText("��������");
									}
								}
								if (spinner_2.equals("<=")) {
									if (AppConfig.temp <= number_get) {
										link_state = true;
										btn_link_state.setText("�ر�����");
									} else {
										link_state = false;
										DiyToast.showToast(getActivity(),
												"����������");
										btn_link_state.setText("��������");
									}

								}
							}
							if (spinner_1.equals("����")) {
								if (spinner_2.equals(">")) {
									if (AppConfig.ill > number_get) {
										link_state = true;
									} else {
										link_state = false;
										DiyToast.showToast(getActivity(),
												"����������");
										set_sw_state();
										btn_link_state.setText("��������");
									}
								}
								if (spinner_2.equals("<=")) {
									if (AppConfig.ill <= number_get) {
										link_state = true;
									} else {
										link_state = false;
										DiyToast.showToast(getActivity(),
												"����������");
										set_sw_state();
										btn_link_state.setText("��������");
									}
								}
							}
							if (spinner_1.equals("ʪ��")) {
								if (spinner_2.equals(">")) {
									if (AppConfig.hum > number_get) {
										link_state = true;
									} else {
										link_state = false;
										DiyToast.showToast(getActivity(),
												"����������");
										set_sw_state();
										btn_link_state.setText("��������");
									}
								}
								if (spinner_2.equals("<=")) {
									if (AppConfig.hum <= number_get) {
										link_state = true;
									} else {
										link_state = false;
										DiyToast.showToast(getActivity(),
												"����������");
										set_sw_state();
										btn_link_state.setText("��������");
									}
								}
							}
							if (link_state) {
								num = Integer.valueOf(et_time_get.getText()
										.toString()) * 1000 * 60;
								timer = new CountDownTimer(num, 1000) {

									@Override
									public void onTick(long millisUntilFinished) {
										// TODO Auto-generated method stub
										min = millisUntilFinished / 1000 / 60;
										sec = millisUntilFinished / 1000 % 60;
										tv_down_time.setText("����ģʽ����" + min
												+ "��" + sec + "��");
									}

									@Override
									public void onFinish() {
										// TODO Auto-generated method stub
										if (timer != null) {
											timer.cancel();
										}
										tv_down_time.setText("����ģʽ����X��X��");
										btn_link_state.setText("��������");
										link_state = false;
										set_sw_state();
									}
								}.start();
							}
						} else if (btn_link_state.getText().toString()
								.equals("�ر�����")) {
							if (timer != null) {
								timer.cancel();
							}
							link_state = false;
							btn_link_state.setText("��������");
							tv_down_time.setText("����ģʽ����X��X��");
							set_sw_state();
						}
					}
				}
			}
		});
		return view;
	}

	/**
	 * ��
	 * 
	 * @param view
	 */
	private void initView(View view) {
		btn_link_state = (Button) view.findViewById(R.id.btn_link_state);
		et_number_get = (EditText) view.findViewById(R.id.et_number);
		et_time_get = (EditText) view.findViewById(R.id.et_data);
		sw_door = (Switch) view.findViewById(R.id.sw_door);
		sw_fan = (Switch) view.findViewById(R.id.sw_fan);
		sw_lamp = (Switch) view.findViewById(R.id.sw_lamp);
		sw_warm = (Switch) view.findViewById(R.id.sw_warm);
		sp_1 = (Spinner) view.findViewById(R.id.spinner1);
		sp_2 = (Spinner) view.findViewById(R.id.spinner2);
		tv_down_time = (TextView) view.findViewById(R.id.tv_link_counttime);
		tv_time = (TextView) view.findViewById(R.id.tv_link_time);
	}

	/**
	 * ��ȡʱ����߳�
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"yyyy��MM��dd�� HH:mm:ss");
			tv_time.setText(simpleDateFormat.format(new java.util.Date()));
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

	private void set_sw_state() {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				getActivity().runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						// �Ž�
						if (sw_door.isChecked()) {
							sw_door.setChecked(false);
						}
						// �߳�����
						try {
							Thread.sleep(100);
						} catch (Exception e) {
							// TODO: handle exception
						}
						// ����
						if (sw_fan.isChecked()) {
							sw_fan.setChecked(false);
							ControlUtils.control(ConstantUtil.Fan,
									ConstantUtil.CHANNEL_ALL,
									ConstantUtil.CLOSE);
						}
						// �߳�����
						try {
							Thread.sleep(100);
						} catch (Exception e) {
							// TODO: handle exception
						}
						// ���
						if (sw_lamp.isChecked()) {
							sw_lamp.setChecked(false);
							ControlUtils.control(ConstantUtil.Lamp,
									ConstantUtil.CHANNEL_ALL,
									ConstantUtil.CLOSE);
						}
						// �߳�����
						try {
							Thread.sleep(100);
						} catch (Exception e) {
							// TODO: handle exception
						}
						// ������
						if (sw_warm.isChecked()) {
							sw_warm.setChecked(false);
							ControlUtils.control(ConstantUtil.WarningLight,
									ConstantUtil.CHANNEL_ALL,
									ConstantUtil.CLOSE);
						}
					}
				});
			}
		}).start();
	}
}