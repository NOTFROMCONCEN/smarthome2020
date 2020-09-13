package com.example.shengsai2018a0913;

import com.bizideal.smarthome.socket.SocketClient;
import com.example.shengsai2018a0913.fragment.BarActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {

	private ProgressBar pg_1;
	private TextView tv_loading_text;
	private int number = 0;
	private SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		if (sharedPreferences.getBoolean("autologin", false) == true) {
			SocketClient.ip = sharedPreferences.getString("ip", null);
			startActivity(new Intent(getApplicationContext(), BarActivity.class));
			finish();
		} else {
			handler.post(timeRunnable);
		}
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			pg_1.setProgress(number);
			tv_loading_text.setText("ÕýÔÚ¼ÓÔØ...." + String.valueOf(number) + "%");
			handler.postDelayed(timeRunnable, 10);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			number++;
			Message msg = handler.obtainMessage();
			if (number < 100) {
				handler.sendMessage(msg);
			} else {
				handler.removeCallbacks(timeRunnable);
				startActivity(new Intent(getApplicationContext(),
						LoginActivity.class));
				finish();
			}
		}
	};

	private void initView() {
		// TODO Auto-generated method stub
		sharedPreferences = getSharedPreferences("rember", MODE_PRIVATE);
		pg_1 = (ProgressBar) findViewById(R.id.progressBar1);
		tv_loading_text = (TextView) findViewById(R.id.tv_loading_text);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
