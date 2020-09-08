package com.example.shengsaishiti20170908.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ToggleButton;

import com.example.shengsaishiti20170908.R;
import com.example.shengsaishiti20170908.tools.AppConfig;

public class DrawFragment extends Fragment {

	private ToggleButton tg_draw_state;
	private CheckBox cb_temp, cb_smo, cb_hum, cb_gas;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_draw, null, false);
		tg_draw_state = (ToggleButton) view.findViewById(R.id.tg_draw_state);
		cb_gas = (CheckBox) view.findViewById(R.id.cb_gas);
		cb_hum = (CheckBox) view.findViewById(R.id.cb_hum);
		cb_smo = (CheckBox) view.findViewById(R.id.cb_smo);
		cb_temp = (CheckBox) view.findViewById(R.id.cb_temp);
		tg_draw_state.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					AppConfig.draw_state = true;
				} else {
					AppConfig.draw_state = false;
				}
			}
		});
		cb_gas.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					AppConfig.gas_state = true;
				} else {
					AppConfig.gas_state = false;
				}
			}
		});
		cb_hum.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					AppConfig.hum_state = true;
				} else {
					AppConfig.hum_state = false;
				}
			}
		});
		cb_smo.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					AppConfig.smo_state = true;
				} else {
					AppConfig.smo_state = false;
				}
			}
		});
		cb_temp.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					AppConfig.temp_state = true;
				} else {
					AppConfig.temp_state = false;
				}
			}
		});
		return view;
	}
}
