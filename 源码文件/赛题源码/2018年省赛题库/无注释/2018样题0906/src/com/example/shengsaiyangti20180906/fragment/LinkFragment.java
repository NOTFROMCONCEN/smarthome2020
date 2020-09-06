package com.example.shengsaiyangti20180906.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RadioButton;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.example.shengsaiyangti20180906.R;

public class LinkFragment extends Fragment {

	private RadioButton ra_huike_mode, ra_seelp_mode, ra_fangdao_mode, ra_temp,
			ra_hum, ra_ill, ra_big, ra_small;
	private EditText et_get_number;
	private CheckBox cb_fan, cb_warm, cb_lamp;
	private boolean link_state = false, mode_state = false;
	private int i = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_link, null, false);
		initView(view);
		// 重置次数
		ra_fangdao_mode.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				cb_fan.setChecked(false);
				cb_lamp.setChecked(false);
				cb_warm.setChecked(false);
				i = 0;
			}
		});
		ra_huike_mode.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				cb_fan.setChecked(false);
				cb_lamp.setChecked(false);
				cb_warm.setChecked(false);
				i = 0;
			}
		});
		ra_seelp_mode.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				cb_fan.setChecked(false);
				cb_lamp.setChecked(false);
				cb_warm.setChecked(false);
				i = 0;
			}
		});
		cb_fan.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ra_fangdao_mode.setChecked(false);
					ra_huike_mode.setChecked(false);
					ra_seelp_mode.setChecked(false);
				}
			}
		});
		cb_lamp.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ra_fangdao_mode.setChecked(false);
					ra_huike_mode.setChecked(false);
					ra_seelp_mode.setChecked(false);
				}
			}
		});
		cb_warm.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ra_fangdao_mode.setChecked(false);
					ra_huike_mode.setChecked(false);
					ra_seelp_mode.setChecked(false);
				}
			}
		});
		handler.post(timeRunnable);
		return view;
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (cb_fan.isChecked() || cb_lamp.isChecked()
					|| cb_warm.isChecked()) {
				if (ra_small.isChecked()) {

					if (ra_temp.isChecked()) {
						if (BaseFragment.temp < Integer.valueOf(et_get_number
								.getText().toString())) {
							if (cb_fan.isChecked()) {
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_lamp.isChecked()) {
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_warm.isChecked()) {
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
						} else {
							if (cb_fan.isChecked()) {
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.CLOSE);
							}
							if (cb_lamp.isChecked()) {
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.CLOSE);
							}
							if (cb_warm.isChecked()) {
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.CLOSE);
							}
						}
					}
					if (ra_ill.isChecked()) {
						if (BaseFragment.ill < Integer.valueOf(et_get_number
								.getText().toString())) {
							if (cb_fan.isChecked()) {
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_lamp.isChecked()) {
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_warm.isChecked()) {
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
						} else {
							if (cb_fan.isChecked()) {
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.CLOSE);
							}
							if (cb_lamp.isChecked()) {
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.CLOSE);
							}
							if (cb_warm.isChecked()) {
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.CLOSE);
							}
						}

					}
					if (ra_hum.isChecked()) {
						if (BaseFragment.hum < Integer.valueOf(et_get_number
								.getText().toString())) {
							if (cb_fan.isChecked()) {
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_lamp.isChecked()) {
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_warm.isChecked()) {
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
						} else {
							if (cb_fan.isChecked()) {
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.CLOSE);
							}
							if (cb_lamp.isChecked()) {
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.CLOSE);
							}
							if (cb_warm.isChecked()) {
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.CLOSE);
							}
						}

					}

				}
				if (ra_big.isChecked()) {
					if (ra_temp.isChecked()) {
						if (BaseFragment.temp > Integer.valueOf(et_get_number
								.getText().toString())) {
							if (cb_fan.isChecked()) {
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_lamp.isChecked()) {
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_warm.isChecked()) {
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
						} else {
							if (cb_fan.isChecked()) {
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.CLOSE);
							}
							if (cb_lamp.isChecked()) {
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.CLOSE);
							}
							if (cb_warm.isChecked()) {
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.CLOSE);
							}
						}
					}
					if (ra_ill.isChecked()) {
						if (BaseFragment.ill > Integer.valueOf(et_get_number
								.getText().toString())) {
							if (cb_fan.isChecked()) {
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_lamp.isChecked()) {
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_warm.isChecked()) {
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
						} else {
							if (cb_fan.isChecked()) {
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.CLOSE);
							}
							if (cb_lamp.isChecked()) {
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.CLOSE);
							}
							if (cb_warm.isChecked()) {
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.CLOSE);
							}
						}

					}
					if (ra_hum.isChecked()) {
						if (BaseFragment.hum > Integer.valueOf(et_get_number
								.getText().toString())) {
							if (cb_fan.isChecked()) {
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_lamp.isChecked()) {
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_warm.isChecked()) {
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
						} else {
							if (cb_fan.isChecked()) {
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.CLOSE);
							}
							if (cb_lamp.isChecked()) {
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.CLOSE);
							}
							if (cb_warm.isChecked()) {
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.CLOSE);
							}
						}

					}
				}
			}
			if (ra_fangdao_mode.isChecked() || ra_huike_mode.isChecked()
					|| ra_seelp_mode.isChecked()) {
				if (ra_seelp_mode.isChecked()) {
					if (BaseFragment.co > 5) {
						ControlUtils.control(ConstantUtil.Fan,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					}
					if (BaseFragment.co > 10) {
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					}
					i++;
					if (i == 2) {
						ControlUtils.control(ConstantUtil.Curtain,
								ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
						System.out.println("窗帘开");
					}
				}
				if (ra_huike_mode.isChecked()) {
					if (BaseFragment.pm > 75) {
						ControlUtils.control(ConstantUtil.Fan,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					}
					i++;
					if (i == 1) {
						ControlUtils.control(ConstantUtil.Fan,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
						System.out.println("风扇关");
					}
					if (i == 2) {
						ControlUtils.control(ConstantUtil.Curtain,
								ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
						System.out.println("窗帘开");
					}
				}
				if (ra_fangdao_mode.isChecked()) {
					i++;
					if (BaseFragment.per == 1) {
						ControlUtils.control(ConstantUtil.WarningLight,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
						if (i < 1) {
							ControlUtils.control(ConstantUtil.Curtain,
									ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
						}
					} else {
						i = 0;
						ControlUtils.control(ConstantUtil.WarningLight,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
						if (i < 1) {
							ControlUtils.control(ConstantUtil.Curtain,
									ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
						}
					}
				}
			}
			if (cb_fan.isChecked() || cb_lamp.isChecked()
					|| cb_warm.isChecked()) {
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

	private void initView(View view) {
		et_get_number = (EditText) view.findViewById(R.id.et_number_get);
		cb_fan = (CheckBox) view.findViewById(R.id.cb_fan);
		cb_lamp = (CheckBox) view.findViewById(R.id.cb_lamp);
		cb_warm = (CheckBox) view.findViewById(R.id.cb_warm);
		ra_big = (RadioButton) view.findViewById(R.id.ra_big);
		ra_fangdao_mode = (RadioButton) view.findViewById(R.id.ra_fangdao_mode);
		ra_huike_mode = (RadioButton) view.findViewById(R.id.ra_huike_mode);
		ra_hum = (RadioButton) view.findViewById(R.id.ra_hum);
		ra_ill = (RadioButton) view.findViewById(R.id.ra_ill);
		ra_seelp_mode = (RadioButton) view.findViewById(R.id.ra_seelp_mode);
		ra_small = (RadioButton) view.findViewById(R.id.ra_small);
		ra_temp = (RadioButton) view.findViewById(R.id.ra_temp);
	}
}
