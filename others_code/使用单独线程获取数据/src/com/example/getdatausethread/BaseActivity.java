package com.example.getdatausethread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;

public class BaseActivity extends Activity {

	private TextView tv_all_data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base);
		SocketLinkGetData.start_link_server(BaseActivity.this, AppConfig.ip);
		tv_all_data = (TextView) findViewById(R.id.tv_all_data);
		handler.post(timeRunnable);
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			tv_all_data.setText("ÎÂ¶È£º" + String.valueOf(AppConfig.temp) + "\n"
					+ "¹âÕÕ£º" + String.valueOf(AppConfig.ill) + "\n" + "ÑÌÎí£º"
					+ String.valueOf(AppConfig.smo) + "\n" + "ÆøÑ¹£º"
					+ String.valueOf(AppConfig.press) + "\n" + "Co2£º"
					+ String.valueOf(AppConfig.co));
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

}
