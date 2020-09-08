package com.example.shengsaishiti20170908.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.ToggleButton;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.example.shengsaishiti20170908.R;
import com.example.shengsaishiti20170908.tools.DiyToast;

public class ModeFragment extends Fragment {

	private RadioButton ra_mode_af, ra_mode_hj;
	private ToggleButton tg_mode_state, tg_link_state;
	private CheckBox cb_link_state;
	private Spinner sp_1, sp_2, sp_3;
	private EditText et_number;
	private boolean is_mode = false, is_link = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_mode, null, false);
		initView(view);
		tg_link_state.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (et_number.getText().toString().isEmpty()) {
						DiyToast.showToast(getActivity(), "«Î ‰»Î ˝÷µ", 1);
						tg_link_state.setChecked(false);
						is_link = false;
						cb_link_state.setChecked(false);
					} else {
						is_link = true;
						handler.post(timeRunnable);
					}
				} else {
					is_link = false;
					handler.removeCallbacks(timeRunnable);
					cb_link_state.setChecked(false);
				}
			}
		});
		tg_mode_state.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					final boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					is_mode = true;
					new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							while (is_mode) {
								try {
									Thread.sleep(1000);
								} catch (Exception e) {
									// TODO: handle exception
								}
								if (ra_mode_af.isChecked()) {
									if (BaseFragment.per == 1) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									} else {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
								}
								if (ra_mode_hj.isChecked()) {
									if (BaseFragment.per == 1
											&& BaseFragment.co > 200) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									} else {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (BaseFragment.per == 1
											&& BaseFragment.ill > 600) {
										ControlUtils.control(
												ConstantUtil.Curtain,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.OPEN);
									} else {
										ControlUtils.control(
												ConstantUtil.Curtain,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
								}
							}
						}
					}).start();
				} else {
					is_mode = false;
					ra_mode_af.setChecked(false);
					ra_mode_hj.setChecked(false);
				}
			}
		});
		return view;
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (is_link && cb_link_state.isChecked()) {
				if (et_number.getText().toString().isEmpty()) {
					DiyToast.showToast(getActivity(), "«Î ‰»Î ˝÷µ", 1);
					tg_link_state.setChecked(false);
					is_link = false;
					cb_link_state.setChecked(false);
					handler.removeCallbacks(timeRunnable);
				} else {
					if (sp_2.getSelectedItem().toString().equals(">")) {
						if (sp_1.getSelectedItem().toString().equals("—ÃŒÌ")) {
							if (BaseFragment.smo > Integer.valueOf(et_number
									.getText().toString())) {
								if (sp_3.getSelectedItem().toString()
										.equals("∑Á…»")) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
								if (sp_3.getSelectedItem().toString()
										.equals("±®æØµ∆")) {
									ControlUtils.control(
											ConstantUtil.WarningLight,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
								if (sp_3.getSelectedItem().toString()
										.equals("…‰µ∆")) {
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
							} else {
								if (sp_3.getSelectedItem().toString()
										.equals("∑Á…»")) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
								}
								if (sp_3.getSelectedItem().toString()
										.equals("±®æØµ∆")) {
									ControlUtils.control(
											ConstantUtil.WarningLight,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
								}
								if (sp_3.getSelectedItem().toString()
										.equals("…‰µ∆")) {
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
								}
							}
						}
						if (sp_1.getSelectedItem().toString().equals("Œ¬∂»")) {
							if (BaseFragment.temp > Integer.valueOf(et_number
									.getText().toString())) {
								if (sp_3.getSelectedItem().toString()
										.equals("∑Á…»")) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
								if (sp_3.getSelectedItem().toString()
										.equals("±®æØµ∆")) {
									ControlUtils.control(
											ConstantUtil.WarningLight,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
								if (sp_3.getSelectedItem().toString()
										.equals("…‰µ∆")) {
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
							} else {
								if (sp_3.getSelectedItem().toString()
										.equals("∑Á…»")) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
								}
								if (sp_3.getSelectedItem().toString()
										.equals("±®æØµ∆")) {
									ControlUtils.control(
											ConstantUtil.WarningLight,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
								}
								if (sp_3.getSelectedItem().toString()
										.equals("…‰µ∆")) {
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
								}
							}
						}
					}
					if (sp_2.getSelectedItem().toString().equals("<")) {
						if (sp_1.getSelectedItem().toString().equals("—ÃŒÌ")) {
							if (BaseFragment.smo < Integer.valueOf(et_number
									.getText().toString())) {
								if (sp_3.getSelectedItem().toString()
										.equals("∑Á…»")) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
								if (sp_3.getSelectedItem().toString()
										.equals("±®æØµ∆")) {
									ControlUtils.control(
											ConstantUtil.WarningLight,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
								if (sp_3.getSelectedItem().toString()
										.equals("…‰µ∆")) {
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
							} else {
								if (sp_3.getSelectedItem().toString()
										.equals("∑Á…»")) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
								}
								if (sp_3.getSelectedItem().toString()
										.equals("±®æØµ∆")) {
									ControlUtils.control(
											ConstantUtil.WarningLight,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
								}
								if (sp_3.getSelectedItem().toString()
										.equals("…‰µ∆")) {
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
								}
							}
						}
						if (sp_1.getSelectedItem().toString().equals("Œ¬∂»")) {
							if (BaseFragment.temp < Integer.valueOf(et_number
									.getText().toString())) {
								if (sp_3.getSelectedItem().toString()
										.equals("∑Á…»")) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
								if (sp_3.getSelectedItem().toString()
										.equals("±®æØµ∆")) {
									ControlUtils.control(
											ConstantUtil.WarningLight,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
								if (sp_3.getSelectedItem().toString()
										.equals("…‰µ∆")) {
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
							} else {
								if (sp_3.getSelectedItem().toString()
										.equals("∑Á…»")) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
								}
								if (sp_3.getSelectedItem().toString()
										.equals("±®æØµ∆")) {
									ControlUtils.control(
											ConstantUtil.WarningLight,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
								}
								if (sp_3.getSelectedItem().toString()
										.equals("…‰µ∆")) {
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
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

	private void initView(View view) {
		// TODO Auto-generated method stub
		ra_mode_af = (RadioButton) view.findViewById(R.id.ra_mode_af);
		ra_mode_hj = (RadioButton) view.findViewById(R.id.ra_mode_hj);
		cb_link_state = (CheckBox) view.findViewById(R.id.cb_link_state);
		sp_1 = (Spinner) view.findViewById(R.id.spinner1);
		sp_2 = (Spinner) view.findViewById(R.id.spinner2);
		sp_3 = (Spinner) view.findViewById(R.id.spinner3);
		tg_link_state = (ToggleButton) view.findViewById(R.id.tg_link_state);
		tg_mode_state = (ToggleButton) view.findViewById(R.id.tg_mode_state);
		et_number = (EditText) view.findViewById(R.id.et_getdata);
	}
}