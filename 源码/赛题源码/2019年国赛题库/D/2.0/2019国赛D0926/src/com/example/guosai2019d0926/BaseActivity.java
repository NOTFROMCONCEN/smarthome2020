package com.example.guosai2019d0926;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.guosai2019d0926.tools.AppConfig;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class BaseActivity extends Activity {

	private TextView tv_temp, tv_hum, tv_smo, tv_gas, tv_ill, tv_press, tv_per,
			tv_pm, tv_co, tv_time;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base);
		initView();
		handler.post(timeRunnable);
	}

	private void initView() {
		// TODO Auto-generated method stub
		tv_co = (TextView) findViewById(R.id.tv_co);
		tv_gas = (TextView) findViewById(R.id.tv_gas);
		tv_hum = (TextView) findViewById(R.id.tv_hum);
		tv_ill = (TextView) findViewById(R.id.tv_ill);
		tv_per = (TextView) findViewById(R.id.tv_per);
		tv_pm = (TextView) findViewById(R.id.tv_pm);
		tv_press = (TextView) findViewById(R.id.tv_press);
		tv_smo = (TextView) findViewById(R.id.tv_smo);
		tv_temp = (TextView) findViewById(R.id.tv_temp);
		tv_time = (TextView) findViewById(R.id.tv_chuanganqi_time);
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			tv_time.setText(simpleDateFormat.format(new Date()));
			tv_co.setText(String.valueOf(AppConfig.co));
			tv_gas.setText(String.valueOf(AppConfig.gas));
			tv_hum.setText(String.valueOf(AppConfig.hum));
			tv_ill.setText(String.valueOf(AppConfig.ill));
			tv_per.setText(String.valueOf(AppConfig.per));
			tv_pm.setText(String.valueOf(AppConfig.pm));
			tv_press.setText(String.valueOf(AppConfig.press));
			tv_smo.setText(String.valueOf(AppConfig.smo));
			tv_temp.setText(String.valueOf(AppConfig.temp));
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

	public void base_back(View view) {
		// TODO Auto-generated method stub
		finish();
	}
}
