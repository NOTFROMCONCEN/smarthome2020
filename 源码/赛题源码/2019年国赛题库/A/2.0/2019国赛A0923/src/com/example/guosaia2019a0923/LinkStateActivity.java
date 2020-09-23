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
				tv_a8.setText("����");
			} else {
				tv_a8.setText("����");
			}

			if (IndexActivity.co != 0) {
				tv_co.setText("����");
			} else {
				tv_co.setText("����");
			}

			if (IndexActivity.hum != 0) {
				tv_hum.setText("����");
			} else {
				tv_hum.setText("����");
			}

			if (IndexActivity.ill != 0) {
				tv_ill.setText("����");
			} else {
				tv_ill.setText("����");
			}

			if (IndexActivity.per != 0) {
				tv_per.setText("����");
			} else {
				tv_per.setText("����");
			}

			if (IndexActivity.pm != 0) {
				tv_pm.setText("����");
			} else {
				tv_pm.setText("����");
			}

			if (IndexActivity.press != 0) {
				tv_press.setText("����");
			} else {
				tv_press.setText("����");
			}

			if (IndexActivity.smo != 0) {
				tv_smo.setText("����");
			} else {
				tv_smo.setText("����");
			}

			if (IndexActivity.temp != 0) {
				tv_temp.setText("����");
			} else {
				tv_temp.setText("����");
			}

			if (IndexActivity.gas != 0) {
				tv_gas.setText("����");
			} else {
				tv_gas.setText("����");
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
