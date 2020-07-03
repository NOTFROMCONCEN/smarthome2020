package com.example.shengsaid07032019.fragment;

import android.app.AlertDialog;
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
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.example.shengsaid07032019.R;
import com.example.shengsaid07032019.tools.AppTools;
import com.example.shengsaid07032019.tools.DiyToast;

public class LinkFragment extends Fragment {

	private Spinner sp_1, sp_2;
	private Button btn_chose;
	private CheckBox cb_link_state;
	private EditText et_number;
	private boolean shebei_cur = false;
	private boolean shebei_fan = false;
	private boolean shebei_warm = false;
	private boolean shebei_lamp = false;
	private boolean link_state = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_link, null, false);
		initView(view);
		cb_link_state.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					hand.post(timeRunnable);
					link_state = true;
				} else {
					link_state = false;
					hand.removeCallbacks(timeRunnable);
					shebei(false);
				}
			}
		});
		btn_chose.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				show_dialog();
			}
		});
		return view;
	}

	private void show_dialog() {
		final AlertDialog builder = new AlertDialog.Builder(getActivity())
				.create();
		builder.show();
		View view = LayoutInflater.from(getActivity()).inflate(
				R.layout.diaolog_chose_shebei, null, false);
		Window window = builder.getWindow();
		window.setContentView(view);
		Button btn_con = (Button) view.findViewById(R.id.btn_chose_con);
		CheckBox cb_lamp, cb_warm, cb_cur, cb_fan;
		cb_cur = (CheckBox) view.findViewById(R.id.cb_cur);
		cb_fan = (CheckBox) view.findViewById(R.id.cb_fan);
		cb_lamp = (CheckBox) view.findViewById(R.id.cb_lamp);
		cb_warm = (CheckBox) view.findViewById(R.id.cb_warm);
		if (shebei_cur) {
			cb_cur.setChecked(true);
		}
		if (shebei_fan) {
			cb_fan.setChecked(true);
		}
		if (shebei_lamp) {
			cb_lamp.setChecked(true);
		}
		if (shebei_warm) {
			cb_warm.setChecked(true);
		}
		cb_cur.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					shebei_cur = true;
				} else {
					shebei_cur = false;
				}
			}
		});
		cb_fan.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					shebei_fan = true;
				} else {
					shebei_fan = false;
				}
			}
		});
		cb_lamp.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					shebei_lamp = true;
				} else {
					shebei_lamp = false;
				}
			}
		});
		cb_warm.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					shebei_warm = true;
				} else {
					shebei_warm = false;
				}
			}
		});
		btn_con.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (shebei_cur || shebei_fan || shebei_lamp || shebei_warm) {
					builder.dismiss();
					DiyToast.showToast(getActivity(), "设置成功");
				} else {
					DiyToast.showToast(getActivity(), "请选择设备");
				}
			}
		});
	}

	private void shebei(final boolean is_open_close) {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (is_open_close) {
					if (shebei_cur) {
						ControlUtils.control(ConstantUtil.Curtain,
								ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
					}
					try {
						Thread.sleep(50);
					} catch (Exception e) {
						// TODO: handle exception
					}
					if (shebei_fan) {
						ControlUtils.control(ConstantUtil.Fan,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					}
					try {
						Thread.sleep(50);
					} catch (Exception e) {
						// TODO: handle exception
					}
					if (shebei_lamp) {
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					}
					try {
						Thread.sleep(50);
					} catch (Exception e) {
						// TODO: handle exception
					}
					if (shebei_warm) {
						ControlUtils.control(ConstantUtil.WarningLight,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					}
				} else {
					if (shebei_cur) {
						ControlUtils.control(ConstantUtil.Curtain,
								ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
					}
					try {
						Thread.sleep(50);
					} catch (Exception e) {
						// TODO: handle exception
					}
					if (shebei_fan) {
						ControlUtils.control(ConstantUtil.Fan,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					}
					try {
						Thread.sleep(50);
					} catch (Exception e) {
						// TODO: handle exception
					}
					if (shebei_lamp) {
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					}
					try {
						Thread.sleep(50);
					} catch (Exception e) {
						// TODO: handle exception
					}
					if (shebei_warm) {
						ControlUtils.control(ConstantUtil.WarningLight,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					}
				}
			}
		}).start();
	}

	Handler hand = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (link_state) {
				if (et_number.getText().toString().isEmpty()) {
					DiyToast.showToast(getActivity(), "请输入数值");
					hand.removeCallbacks(timeRunnable);
					cb_link_state.setChecked(false);
					link_state = false;
				} else {
					if (sp_1.getSelectedItem().toString().equals("温度")) {
						if (sp_2.getSelectedItem().toString().equals("大于")) {
							if (AppTools.temp > Integer.valueOf(et_number
									.getText().toString())) {
								shebei(true);
							} else {
								shebei(false);
								DiyToast.showToast(getActivity(), "条件不满足");
								hand.removeCallbacks(timeRunnable);
								cb_link_state.setChecked(false);
								link_state = false;
							}
						}
						if (sp_2.getSelectedItem().toString().equals("小于")) {
							if (AppTools.temp < Integer.valueOf(et_number
									.getText().toString())) {
								shebei(true);
							} else {
								shebei(false);
								DiyToast.showToast(getActivity(), "条件不满足");
								hand.removeCallbacks(timeRunnable);
								cb_link_state.setChecked(false);
								link_state = false;
							}
						}
					}
					if (sp_1.getSelectedItem().toString().equals("湿度")) {
						if (sp_2.getSelectedItem().toString().equals("大于")) {
							if (AppTools.hum > Integer.valueOf(et_number
									.getText().toString())) {
								shebei(true);
							} else {
								shebei(false);
								DiyToast.showToast(getActivity(), "条件不满足");
								hand.removeCallbacks(timeRunnable);
								cb_link_state.setChecked(false);
								link_state = false;
							}
						}
						if (sp_2.getSelectedItem().toString().equals("小于")) {
							if (AppTools.hum < Integer.valueOf(et_number
									.getText().toString())) {
								shebei(true);
							} else {
								shebei(false);
								DiyToast.showToast(getActivity(), "条件不满足");
								hand.removeCallbacks(timeRunnable);
								cb_link_state.setChecked(false);
								link_state = false;
							}
						}
					}
					if (sp_1.getSelectedItem().toString().equals("光照")) {
						if (sp_2.getSelectedItem().toString().equals("大于")) {
							if (AppTools.ill > Integer.valueOf(et_number
									.getText().toString())) {
								shebei(true);
							} else {
								shebei(false);
								DiyToast.showToast(getActivity(), "条件不满足");
								hand.removeCallbacks(timeRunnable);
								cb_link_state.setChecked(false);
								link_state = false;
							}
						}
						if (sp_2.getSelectedItem().toString().equals("小于")) {
							if (AppTools.ill < Integer.valueOf(et_number
									.getText().toString())) {
								shebei(true);
							} else {
								shebei(false);
								DiyToast.showToast(getActivity(), "条件不满足");
								hand.removeCallbacks(timeRunnable);
								cb_link_state.setChecked(false);
								link_state = false;
							}
						}
					}
					if (sp_1.getSelectedItem().toString().equals("气压")) {
						if (sp_2.getSelectedItem().toString().equals("大于")) {
							if (AppTools.press > Integer.valueOf(et_number
									.getText().toString())) {
								shebei(true);
							} else {
								shebei(false);
								DiyToast.showToast(getActivity(), "条件不满足");
								hand.removeCallbacks(timeRunnable);
								cb_link_state.setChecked(false);
								link_state = false;
							}
						}
						if (sp_2.getSelectedItem().toString().equals("小于")) {
							if (AppTools.press < Integer.valueOf(et_number
									.getText().toString())) {
								shebei(true);
							} else {
								shebei(false);
								DiyToast.showToast(getActivity(), "条件不满足");
								hand.removeCallbacks(timeRunnable);
								cb_link_state.setChecked(false);
								link_state = false;
							}
						}
					}
				}
			}
			hand.postDelayed(timeRunnable, 5000);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Message msg = hand.obtainMessage();
			hand.sendMessage(msg);
		}
	};

	private void initView(View view) {
		// TODO Auto-generated method stub
		sp_1 = (Spinner) view.findViewById(R.id.spinner1);
		sp_2 = (Spinner) view.findViewById(R.id.spinner2);
		btn_chose = (Button) view.findViewById(R.id.btn_chose);
		et_number = (EditText) view.findViewById(R.id.et_number);
		cb_link_state = (CheckBox) view.findViewById(R.id.cb_link_state);
	}
}