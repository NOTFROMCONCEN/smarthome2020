package com.example.shengsai2019b0915;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bizideal.smarthome.socket.SocketClient;
import com.example.shengsai2019b0915.tools.DiyToast;
import com.example.shengsai2019b0915.tools.TimeThread;

public class LoginActivity extends Activity {

	private TextView tv_time, tv_loign_text;
	private int number = 0;
	private Button btn_login;
	private EditText et_user, et_pass, et_port, et_ip;
	private SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();
		TimeThread.start(getApplicationContext(), tv_time);
		et_ip.setText(sharedPreferences.getString("ip", null));
		et_pass.setText(sharedPreferences.getString("pass", null));
		et_port.setText(sharedPreferences.getString("port", null));
		et_user.setText(sharedPreferences.getString("user", null));
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_ip.getText().toString().isEmpty()
						|| et_pass.getText().toString().isEmpty()
						|| et_port.getText().toString().isEmpty()
						|| et_user.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "不能有空白项");
				} else {
					if (et_user.getText().toString().equals("bizideal")
							&& et_pass.getText().toString().equals("123456")) {
						sharedPreferences
								.edit()
								.putString("user", et_user.getText().toString())
								.putString("pass", et_pass.getText().toString())
								.putString("ip", et_ip.getText().toString())
								.putString("port", et_port.getText().toString())
								.commit();
						SocketClient.ip = et_ip.getText().toString();
						startActivity(new Intent(getApplicationContext(),
								UnLockActivity.class));
						finish();
					} else {
						new AlertDialog.Builder(LoginActivity.this)
								.setTitle("登录失败").setMessage("密码或用户名错误")
								.setPositiveButton("确定", null).show();
					}
				}
			}
		});
		handler.post(tiemRunnable);
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (number % 2 == 0) {
				tv_loign_text.setVisibility(View.INVISIBLE);
			} else {
				tv_loign_text.setVisibility(View.VISIBLE);
			}
			handler.postDelayed(tiemRunnable, 1000);
		}
	};
	Runnable tiemRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			number++;
			Message msgMessage = handler.obtainMessage();
			handler.sendMessage(msgMessage);
		}
	};

	private void initView() {
		// TODO Auto-generated method stub
		sharedPreferences = getSharedPreferences("rember", MODE_PRIVATE);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_port = (EditText) findViewById(R.id.et_port);
		et_user = (EditText) findViewById(R.id.et_user);
		btn_login = (Button) findViewById(R.id.btn_login);
		tv_time = (TextView) findViewById(R.id.tv_login_time);
		tv_loign_text = (TextView) findViewById(R.id.tv_loign_text);
	}
}
