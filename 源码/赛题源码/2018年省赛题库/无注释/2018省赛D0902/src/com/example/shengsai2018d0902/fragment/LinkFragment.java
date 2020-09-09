package com.example.shengsai2018d0902.fragment;

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
import android.widget.Spinner;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.example.shengsai2018d0902.R;
import com.example.shengsai2018d0902.tools.DiyToast;

public class LinkFragment extends Fragment {

	private CheckBox cb_link1, cb_link2;
	private Spinner sp_1, sp_2, sp_3, sp_4;
	private EditText et_1, et_2;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_link, null, false);
		initView(view);
		cb_link1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (et_1.getText().toString().isEmpty()) {
						DiyToast.showToast(getActivity(), "请输入数值");
						cb_link1.setChecked(false);
					}
				}
			}
		});
		cb_link2.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (et_2.getText().toString().isEmpty()) {
						DiyToast.showToast(getActivity(), "请输入数值");
						cb_link2.setChecked(false);
					}
				}
			}
		});
		handler.post(timeRunnable);
		return view;
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (cb_link1.isChecked()) {
				if (et_1.getText().toString().isEmpty()) {
					DiyToast.showToast(getActivity(), "请输入数值");
					cb_link1.setChecked(false);
				} else {
					if (sp_1.getSelectedItem().toString().equals("温度")) {
						if (sp_2.getSelectedItem().toString().equals(">")) {
							if (BaseFragment.temp > Integer.valueOf(et_1
									.getText().toString())) {
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							} else {
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.CLOSE);
							}
						}
						if (sp_2.getSelectedItem().toString().equals("<=")) {
							if (BaseFragment.temp <= Integer.valueOf(et_1
									.getText().toString())) {
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							} else {
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.CLOSE);
							}

						}
					}
					if (sp_1.getSelectedItem().toString().equals("光照")) {
						if (sp_2.getSelectedItem().toString().equals(">")) {
							if (BaseFragment.ill > Integer.valueOf(et_1
									.getText().toString())) {
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							} else {
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.CLOSE);
							}
						}
						if (sp_2.getSelectedItem().toString().equals("<=")) {
							if (BaseFragment.ill <= Integer.valueOf(et_1
									.getText().toString())) {
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							} else {
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.CLOSE);
							}

						}
					}
				}
			}
			if (cb_link1.isChecked()) {
				if (et_1.getText().toString().isEmpty()) {
					DiyToast.showToast(getActivity(), "请输入数值");
					cb_link1.setChecked(false);
				} else {
					if (sp_3.getSelectedItem().toString().equals("<=")) {
						if (sp_4.getSelectedItem().toString().equals("报警灯开")) {
							if (BaseFragment.ill <= Integer.valueOf(et_2
									.getText().toString())) {
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							} else {
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.CLOSE);
							}
						}
						if (sp_4.getSelectedItem().toString().equals("射灯全开")) {
							if (BaseFragment.ill <= Integer.valueOf(et_2
									.getText().toString())) {
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							} else {
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.CLOSE);
							}
						}
					}
					if (sp_3.getSelectedItem().toString().equals(">")) {

						if (sp_4.getSelectedItem().toString().equals("报警灯开")) {
							if (BaseFragment.ill > Integer.valueOf(et_2
									.getText().toString())) {
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							} else {
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.CLOSE);
							}
						}
						if (sp_4.getSelectedItem().toString().equals("射灯全开")) {
							if (BaseFragment.ill > Integer.valueOf(et_2
									.getText().toString())) {
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							} else {
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.CLOSE);
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
		cb_link1 = (CheckBox) view.findViewById(R.id.cb_link_1);
		cb_link2 = (CheckBox) view.findViewById(R.id.cb_link_2);
		sp_1 = (Spinner) view.findViewById(R.id.sp_1);
		sp_2 = (Spinner) view.findViewById(R.id.sp_2);
		sp_3 = (Spinner) view.findViewById(R.id.sp_3);
		sp_4 = (Spinner) view.findViewById(R.id.sp_4);
		et_1 = (EditText) view.findViewById(R.id.et_number_get_1);
		et_2 = (EditText) view.findViewById(R.id.et_number_get_2);
	}
}
