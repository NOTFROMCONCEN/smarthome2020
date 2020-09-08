package com.example.shengsaishiti20170908.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.shengsaishiti20170908.R;
import com.example.shengsaishiti20170908.tools.DiyToast;

public class OpFragment extends Fragment {

	private SharedPreferences sharedPreferences;
	private CheckBox cb_op_door, cb_op_tv, cb_op_kt, cb_op_dvd;
	private Button btn_con;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_op, null, false);
		initView(view);
		if (sharedPreferences.getBoolean("door", false) == true) {
			cb_op_door.setChecked(true);
		}
		if (sharedPreferences.getBoolean("kt", false) == true) {
			cb_op_kt.setChecked(true);
		}
		if (sharedPreferences.getBoolean("dvd", false) == true) {
			cb_op_dvd.setChecked(true);
		}
		if (sharedPreferences.getBoolean("tv", false) == true) {
			cb_op_tv.setChecked(true);
		}
		btn_con.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (cb_op_door.isChecked()) {
					sharedPreferences.edit().putBoolean("door", true).commit();
				} else {
					sharedPreferences.edit().putBoolean("door", false).commit();
				}
				if (cb_op_dvd.isChecked()) {
					sharedPreferences.edit().putBoolean("dvd", true).commit();
				} else {
					sharedPreferences.edit().putBoolean("dvd", false).commit();
				}
				if (cb_op_kt.isChecked()) {
					sharedPreferences.edit().putBoolean("kt", true).commit();
				} else {
					sharedPreferences.edit().putBoolean("kt", false).commit();
				}
				if (cb_op_tv.isChecked()) {
					sharedPreferences.edit().putBoolean("tv", true).commit();
				} else {
					sharedPreferences.edit().putBoolean("tv", false).commit();
				}
				DiyToast.showToast(getActivity(), "…Ë÷√≥…π¶", 2);
			}
		});
		return view;
	}

	private void initView(View view) {
		// TODO Auto-generated method stub
		sharedPreferences = getActivity().getSharedPreferences("rember",
				getActivity().MODE_PRIVATE);
		btn_con = (Button) view.findViewById(R.id.btn_op_con);
		cb_op_door = (CheckBox) view.findViewById(R.id.cb_op_door);
		cb_op_kt = (CheckBox) view.findViewById(R.id.cb_op_kt);
		cb_op_tv = (CheckBox) view.findViewById(R.id.cb_op_tv);
		cb_op_dvd = (CheckBox) view.findViewById(R.id.cb_op_dvd);
	}
}