package com.example.shengsaid07032019.fragment;

import java.util.Random;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shengsaid07032019.R;
import com.example.shengsaid07032019.tools.AppTools;

public class BaseFragment extends Fragment {
	private ImageView iv_temp, iv_hum;
	private TextView tv_temp, tv_hum, tv_smo, tv_gas, tv_ill, tv_press, tv_pm,
			tv_co, tv_per;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_base, null, false);
		initView(view);
		handler.post(timeRunnable);
		return view;
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			tv_co.setText(String.valueOf(AppTools.co));
			tv_gas.setText(String.valueOf(AppTools.gas));
			tv_hum.setText(String.valueOf(AppTools.hum));
			tv_ill.setText(String.valueOf(AppTools.ill));
			tv_per.setText(String.valueOf(AppTools.per));
			tv_pm.setText(String.valueOf(AppTools.pm));
			tv_press.setText(String.valueOf(AppTools.press));
			tv_smo.setText(String.valueOf(AppTools.smo));
			tv_temp.setText(String.valueOf(AppTools.temp));
			iv_hum.setRotation(AppTools.hum);
			iv_temp.setRotation(AppTools.temp);
			if (AppTools.gas > 100) {
				handler.removeCallbacks(timeRunnable);
				System.exit(0);
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
		iv_hum = (ImageView) view.findViewById(R.id.iv_hum);
		iv_temp = (ImageView) view.findViewById(R.id.iv_temp);
		tv_co = (TextView) view.findViewById(R.id.tv_co);
		tv_gas = (TextView) view.findViewById(R.id.tv_gas);
		tv_hum = (TextView) view.findViewById(R.id.tv_hum);
		tv_ill = (TextView) view.findViewById(R.id.tv_ill);
		tv_per = (TextView) view.findViewById(R.id.tv_per);
		tv_pm = (TextView) view.findViewById(R.id.tv_pm);
		tv_press = (TextView) view.findViewById(R.id.tv_press);
		tv_smo = (TextView) view.findViewById(R.id.tv_smo);
		tv_temp = (TextView) view.findViewById(R.id.tv_temp);
	}
}
