package com.example.shengsai2019b0915.fragment;

import android.os.Bundle;
import android.os.CountDownTimer;
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
import com.example.shengsai2019b0915.R;
import com.example.shengsai2019b0915.tools.DiyToast;
import com.example.shengsai2019b0915.tools.TimeThread;

public class LinkFragment extends Fragment {
	private Switch sw_warm, sw_lamp, sw_door, sw_fan;
	private boolean link_state = false;
	private CountDownTimer timer;
	private Button btn_link_state;
	private EditText et_number, et_time;
	private Spinner sp_1, sp_2;
	private long num, min, sec;
	public static TextView tv_link_downtime, tv_time;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_link, null, false);
		initView(view);
		sw_door.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {

				if (isChecked) {
					if (link_state) {
						ControlUtils.control(ConstantUtil.RFID_Open_Door,
								ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
					} else {
						sw_door.setChecked(false);
						DiyToast.showToast(getActivity(), "�뿪������");
					}
				} else {
				}
			}
		});
		sw_fan.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {

				if (isChecked) {
					if (link_state) {
						ControlUtils.control(ConstantUtil.Fan,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					} else {
						sw_fan.setChecked(false);
						DiyToast.showToast(getActivity(), "�뿪������");
					}

				} else {
					ControlUtils.control(ConstantUtil.Fan,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
		});
		sw_lamp.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {

				if (isChecked) {
					if (link_state) {
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					} else {
						sw_lamp.setChecked(false);
						DiyToast.showToast(getActivity(), "�뿪������");
					}

				} else {
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
		});
		sw_warm.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {

				if (isChecked) {
					if (link_state) {
						ControlUtils.control(ConstantUtil.WarningLight,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					} else {
						sw_warm.setChecked(false);
						DiyToast.showToast(getActivity(), "�뿪������");
					}
				} else {
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
		});
		btn_link_state.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (et_number.getText().toString().isEmpty()) {
					DiyToast.showToast(getActivity(), "��������ֵ");
					link_state = false;
				} else {
					if (et_number.getText().toString().isEmpty()
							&& et_time.getText().toString().isEmpty()) {
						DiyToast.showToast(getActivity(), "�����п���Ŀ");
					} else {
						if (btn_link_state.getText().toString().equals("��������")) {
							check_data();
						} else if (btn_link_state.getText().toString()
								.equals("�ر�����")) {
							if (timer != null) {
								timer.cancel();
							}
							link_state = false;
							btn_link_state.setText("��������");
							tv_link_downtime.setText("����ģʽ����X��X��");
							close();
						}
					}
				}

			}
		});
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					try {
						Thread.sleep(500);
					} catch (Exception e) {
						// TODO: handle exception
					}
					getActivity().runOnUiThread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							tv_time.setText(TimeThread.time);
						}
					});
				}
			}
		}).start();
		return view;
	}

	private void check_data() {
		final String spinner_1 = sp_1.getSelectedItem().toString();
		final String spinner_2 = sp_2.getSelectedItem().toString();
		final int number_get = Integer.valueOf(et_number.getText().toString());
		if (spinner_1.equals("�¶�")) {
			if (spinner_2.equals(">")) {
				if (BaseFragment.temp > number_get) {
					link_state = true;
					getActivity().runOnUiThread(new Runnable() {

						@Override
						public void run() {
							btn_link_state.setText("�ر�����");
						}
					});
				} else {
					link_state = false;
					getActivity().runOnUiThread(new Runnable() {

						@Override
						public void run() {

							DiyToast.showToast(getActivity(), "����������");
							close();
							btn_link_state.setText("��������");
						}
					});

				}
			}
			if (spinner_2.equals("<=")) {
				if (BaseFragment.temp <= number_get) {
					link_state = true;
					getActivity().runOnUiThread(new Runnable() {

						@Override
						public void run() {

							btn_link_state.setText("�ر�����");
						}
					});
				} else {
					link_state = false;
					getActivity().runOnUiThread(new Runnable() {

						@Override
						public void run() {

							DiyToast.showToast(getActivity(), "����������");
							close();
							btn_link_state.setText("��������");
						}
					});

				}
			}
		}
		if (spinner_1.equals("����")) {
			if (spinner_2.equals(">")) {
				if (BaseFragment.ill > number_get) {
					link_state = true;
					getActivity().runOnUiThread(new Runnable() {

						@Override
						public void run() {

							btn_link_state.setText("�ر�����");
						}
					});
				} else {
					link_state = false;
					getActivity().runOnUiThread(new Runnable() {

						@Override
						public void run() {

							DiyToast.showToast(getActivity(), "����������");
							close();
							btn_link_state.setText("��������");
						}
					});

				}
			}
			if (spinner_2.equals("<=")) {
				if (BaseFragment.ill <= number_get) {
					link_state = true;
					getActivity().runOnUiThread(new Runnable() {

						@Override
						public void run() {

							btn_link_state.setText("�ر�����");
						}
					});
				} else {
					link_state = false;
					getActivity().runOnUiThread(new Runnable() {

						@Override
						public void run() {

							DiyToast.showToast(getActivity(), "����������");
							close();
							btn_link_state.setText("��������");
						}
					});

				}
			}
		}
		if (spinner_1.equals("ʪ��")) {
			if (spinner_2.equals(">")) {
				if (BaseFragment.hum > number_get) {
					link_state = true;
					getActivity().runOnUiThread(new Runnable() {

						@Override
						public void run() {

							btn_link_state.setText("�ر�����");
						}
					});
				} else {
					link_state = false;
					getActivity().runOnUiThread(new Runnable() {

						@Override
						public void run() {

							DiyToast.showToast(getActivity(), "����������");
							close();
							btn_link_state.setText("��������");
						}
					});
				}
			}
			if (spinner_2.equals("<=")) {
				if (BaseFragment.hum <= number_get) {
					link_state = true;
					getActivity().runOnUiThread(new Runnable() {

						@Override
						public void run() {
							btn_link_state.setText("�ر�����");
						}
					});
				} else {
					link_state = false;
					getActivity().runOnUiThread(new Runnable() {

						@Override
						public void run() {
							DiyToast.showToast(getActivity(), "����������");
							close();
							btn_link_state.setText("��������");
						}
					});
				}
			}
		}
		if (link_state) {
			open();
			num = Integer.valueOf(et_time.getText().toString()) * 1000 * 60;
			timer = new CountDownTimer(num, 1000) {

				@Override
				public void onTick(long millisUntilFinished) {
					if (link_state) {
						min = millisUntilFinished / 1000 / 60;
						sec = millisUntilFinished / 1000 % 60;
						tv_link_downtime.setText("����ģʽ����" + min + "��" + sec
								+ "��");
					} else {
						DiyToast.showToast(getActivity(), "����������");
						if (timer != null) {
							timer.cancel();
						}
						tv_link_downtime.setText("����ģʽ����X��X��");
						btn_link_state.setText("��������");
						link_state = false;
						close();
					}
				}

				@Override
				public void onFinish() {
					if (timer != null) {
						timer.cancel();
					}
					tv_link_downtime.setText("����ģʽ����X��X��");
					btn_link_state.setText("��������");
					link_state = false;
					close();
				}
			}.start();
		}
	}

	private void open() {

		new Thread(new Runnable() {

			@Override
			public void run() {

				try {
					Thread.sleep(100);
				} catch (Exception e) {

				}
				if (sw_door.isChecked()) {
					ControlUtils.control(ConstantUtil.RFID_Open_Door,
							ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
				}
				try {
					Thread.sleep(100);
				} catch (Exception e) {

				}
				if (sw_fan.isChecked()) {
					ControlUtils.control(ConstantUtil.Fan,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				}
				try {
					Thread.sleep(100);
				} catch (Exception e) {

				}
				if (sw_lamp.isChecked()) {
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				}
				try {
					Thread.sleep(100);
				} catch (Exception e) {

				}
				if (sw_warm.isChecked()) {
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				}
			}
		}).start();
	}

	private void close() {

		new Thread(new Runnable() {

			@Override
			public void run() {

				try {
					Thread.sleep(100);
				} catch (Exception e) {

				}
				if (sw_fan.isChecked()) {
					ControlUtils.control(ConstantUtil.Fan,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
				try {
					Thread.sleep(100);
				} catch (Exception e) {

				}
				if (sw_lamp.isChecked()) {
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
				try {
					Thread.sleep(100);
				} catch (Exception e) {

				}
				if (sw_warm.isChecked()) {
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
				getActivity().runOnUiThread(new Runnable() {

					@Override
					public void run() {

						if (sw_door.isChecked()) {
							sw_door.setChecked(false);
						}
						if (sw_fan.isChecked()) {
							sw_fan.setChecked(false);
						}
						if (sw_lamp.isChecked()) {
							sw_lamp.setChecked(false);
						}
						if (sw_warm.isChecked()) {
							sw_warm.setChecked(false);
						}
					}
				});
			}
		}).start();

	}

	private void initView(View view) {

		sp_1 = (Spinner) view.findViewById(R.id.sp_1);
		sp_2 = (Spinner) view.findViewById(R.id.sp_2);
		et_number = (EditText) view.findViewById(R.id.et_number);
		et_time = (EditText) view.findViewById(R.id.et_time);
		btn_link_state = (Button) view.findViewById(R.id.btn_link_state);
		sw_door = (Switch) view.findViewById(R.id.sw_door);
		sw_fan = (Switch) view.findViewById(R.id.sw_fan);
		sw_lamp = (Switch) view.findViewById(R.id.sw_lamp);
		sw_warm = (Switch) view.findViewById(R.id.sw_warm);
		tv_link_downtime = (TextView) view.findViewById(R.id.tv_link_downtime);
		tv_time = (TextView) view.findViewById(R.id.tv_link_time);
	}
}