package com.example.shengsai2019d0920.fragment;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Spinner;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.example.shengsai2019d0920.R;
import com.example.shengsai2019d0920.tools.DiyToast;

public class LinkFragment extends Fragment {
	private CheckBox cb_link_state;
	private Spinner sp_1, sp_2;
	private boolean link_mode = false;
	private EditText et_number;
	private SharedPreferences sharedPreferences;
	private Button btn_chose;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_link, null, false);
		initView(view);
		cb_link_state.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (et_number.getText().toString().isEmpty()) {
						DiyToast.showToast(getActivity(), "数据输入有误");
						link_mode = false;
						cb_link_state.setChecked(false);
					} else {
						link_mode = true;
					}
				} else {
					link_mode = false;
					close();
				}
			}
		});
		btn_chose.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				chose_control();
			}
		});
		handler.post(timeRunnable);
		return view;
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (link_mode) {
				if (et_number.getText().toString().isEmpty()) {
					DiyToast.showToast(getActivity(), "数据输入有误");
					link_mode = false;
					cb_link_state.setChecked(false);
				} else {
					String sp1 = sp_1.getSelectedItem().toString();
					String sp2 = sp_2.getSelectedItem().toString();
					int number = Integer
							.valueOf(et_number.getText().toString());
					if (sp2.equals("大于")) {
						if (sp1.equals("温度")) {
							if (BaseFragment.temp > number) {
								open();
							} else {
								close();
							}
						}
						if (sp1.equals("湿度")) {
							if (BaseFragment.hum > number) {
								open();
							} else {
								close();
							}
						}
						if (sp1.equals("光照")) {
							if (BaseFragment.ill > number) {
								open();
							} else {
								close();
							}
						}
						if (sp1.equals("气压")) {
							if (BaseFragment.press > number) {
								open();
							} else {
								close();
							}
						}
					}
					if (sp1.equals("小于")) {
						if (sp1.equals("温度")) {
							if (BaseFragment.temp < number) {
								open();
							} else {
								close();
							}
						}
						if (sp1.equals("湿度")) {
							if (BaseFragment.hum < number) {
								open();
							} else {
								close();
							}
						}
						if (sp1.equals("光照")) {
							if (BaseFragment.ill < number) {
								open();
							} else {
								close();
							}
						}
						if (sp1.equals("气压")) {
							if (BaseFragment.press < number) {
								open();
							} else {
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

	private void open() {
		// TODO Auto-generated method stub
		if (sharedPreferences.getBoolean("warm", false) == true) {
			ControlUtils.control(ConstantUtil.WarningLight,
					ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
		}
		try {
			Thread.sleep(100);
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (sharedPreferences.getBoolean("fan", false) == true) {
			ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL,
					ConstantUtil.OPEN);
		}
		try {
			Thread.sleep(100);
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (sharedPreferences.getBoolean("lamp", false) == true) {
			ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL,
					ConstantUtil.OPEN);
		}
		try {
			Thread.sleep(100);
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (sharedPreferences.getBoolean("cur", false) == true) {
			ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_1,
					ConstantUtil.OPEN);
		}
	}

	private void close() {
		// TODO Auto-generated method stub
		if (sharedPreferences.getBoolean("warm", false) == true) {
			ControlUtils.control(ConstantUtil.WarningLight,
					ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
		}
		try {
			Thread.sleep(100);
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (sharedPreferences.getBoolean("fan", false) == true) {
			ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL,
					ConstantUtil.CLOSE);
		}
		try {
			Thread.sleep(100);
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (sharedPreferences.getBoolean("lamp", false) == true) {
			ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL,
					ConstantUtil.CLOSE);
		}
		try {
			Thread.sleep(100);
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (sharedPreferences.getBoolean("cur", false) == true) {
			ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_2,
					ConstantUtil.OPEN);
		}
	}

	private void initView(View view) {
		// TODO Auto-generated method stub
		cb_link_state = (CheckBox) view.findViewById(R.id.cb_link_state);
		sp_1 = (Spinner) view.findViewById(R.id.spinner1);
		sp_2 = (Spinner) view.findViewById(R.id.spinner2);
		et_number = (EditText) view.findViewById(R.id.et_number);
		sharedPreferences = getActivity().getSharedPreferences("rember",
				getActivity().MODE_PRIVATE);
		btn_chose = (Button) view.findViewById(R.id.btn_chose);
	}

	private void chose_control() {
		final AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
				.create();
		alertDialog.show();
		View view2 = LayoutInflater.from(getActivity()).inflate(
				R.layout.diaolog_chose_shebei, null, false);
		Window window = alertDialog.getWindow();
		window.setContentView(view2);
		CheckBox cb_lamp = (CheckBox) view2.findViewById(R.id.cb_lamp);
		CheckBox cb_fan = (CheckBox) view2.findViewById(R.id.cb_fan);
		CheckBox cb_warm = (CheckBox) view2.findViewById(R.id.cb_warm);
		CheckBox cb_cur = (CheckBox) view2.findViewById(R.id.cb_cur);
		Button btn_cls = (Button) view2.findViewById(R.id.btn_chose_con);
		btn_cls.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				alertDialog.dismiss();
			}
		});
		if (sharedPreferences.getBoolean("lamp", false) == true) {
			cb_lamp.setChecked(true);
		}
		if (sharedPreferences.getBoolean("fan", false) == true) {
			cb_fan.setChecked(true);
		}
		if (sharedPreferences.getBoolean("warm", false) == true) {
			cb_warm.setChecked(true);
		}
		if (sharedPreferences.getBoolean("cur", false) == true) {
			cb_cur.setChecked(true);
		}
		cb_cur.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					sharedPreferences.edit().putBoolean("cur", true).commit();
				} else {
					sharedPreferences.edit().putBoolean("cur", false).commit();
				}
			}
		});
		cb_fan.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					sharedPreferences.edit().putBoolean("fan", true).commit();
				} else {
					sharedPreferences.edit().putBoolean("fan", false).commit();
				}
			}
		});
		cb_lamp.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					sharedPreferences.edit().putBoolean("lamp", true).commit();
				} else {
					sharedPreferences.edit().putBoolean("lamp", false).commit();
				}
			}
		});
		cb_warm.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					sharedPreferences.edit().putBoolean("warm", true).commit();
				} else {
					sharedPreferences.edit().putBoolean("warm", false).commit();
				}
			}
		});
	}
}
