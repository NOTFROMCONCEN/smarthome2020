package com.example.guosaia2019a0923;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class LinkStateActivity extends Activity {

	private TextView tv_a8, tv_temp, tv_hum, tv_press, tv_smo, tv_gas, tv_ill,
			tv_pm, tv_co, tv_per;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_link_state);
		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		tv_a8 = (TextView) findViewById(R.id.tv_link_a8);
		tv_co = (TextView) findViewById(R.id.tv_link_co);
		tv_hum = (TextView) findViewById(R.id.tv_link_hum);
		tv_ill = (TextView) findViewById(R.id.tv_link_ill);
		tv_per = (TextView) findViewById(R.id.tv_link_per);
		tv_pm = (TextView) findViewById(R.id.tv_link_pm);
		tv_press = (TextView) findViewById(R.id.tv_link_press);
		tv_smo = (TextView) findViewById(R.id.tv_link_smo);
		tv_temp = (TextView) findViewById(R.id.tv_link_temp);
		tv_gas = (TextView) findViewById(R.id.tv_link_gas);
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (IndexActivity.web_state) {
				tv_a8.setText("在线");
			} else {
				tv_a8.setText("离线");
			}

			if (IndexActivity.co != 0) {
				tv_co.setText("在线");
			} else {
				tv_co.setText("离线");
			}

			if (IndexActivity.hum != 0) {
				tv_hum.setText("在线");
			} else {
				tv_hum.setText("离线");
			}

			if (IndexActivity.ill != 0) {
				tv_ill.setText("在线");
			} else {
				tv_ill.setText("离线");
			}

			if (IndexActivity.per != 0) {
				tv_per.setText("在线");
			} else {
				tv_per.setText("离线");
			}

			if (IndexActivity.pm != 0) {
				tv_pm.setText("在线");
			} else {
				tv_pm.setText("离线");
			}

			if (IndexActivity.press != 0) {
				tv_press.setText("在线");
			} else {
				tv_press.setText("离线");
			}

			if (IndexActivity.smo != 0) {
				tv_smo.setText("在线");
			} else {
				tv_smo.setText("离线");
			}

			if (IndexActivity.temp != 0) {
				tv_temp.setText("在线");
			} else {
				tv_temp.setText("离线");
			}

			if (IndexActivity.gas != 0) {
				tv_gas.setText("在线");
			} else {
				tv_gas.setText("离线");
			}
			handler.postDelayed(timeRunnable, 500);
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
}
