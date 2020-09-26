package com.example.guosaic20190925;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bizideal.smarthome.socket.SocketClient;
import com.example.guosaic20190925.tools.DiyToast;
import com.example.guosaic20190925.tools.TextChanger;
import com.example.guosaic20190925.tools.TimeTherad;

/**
 * 登录
 * 
 * @author 10976
 * 
 */
public class LoginActivity extends Activity {

	private EditText et_user, et_pass, et_port, et_ip;
	private String user, pass, port, ip;
	private SharedPreferences sharedPreferences;
	private TextView tv_login_text, tv_login_time;
	private int number = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();
		handler.post(tiemRunnable);
		TimeTherad.start(tv_login_time);
		et_ip.setText(sharedPreferences.getString("ip", null));
		et_pass.setText(sharedPreferences.getString("pass", null));
		et_port.setText(sharedPreferences.getString("port", null));
		et_user.setText(sharedPreferences.getString("user", null));
		et_pass.setTransformationMethod(new TextChanger());
	}

	public void login(View view) {
		// TODO Auto-generated method stub
		user = et_user.getText().toString();
		pass = et_pass.getText().toString();
		port = et_port.getText().toString();
		ip = et_ip.getText().toString();
		if (user.isEmpty() || pass.isEmpty() || port.isEmpty() || ip.isEmpty()) {
			DiyToast.showToast(getApplicationContext(), "不能有空白项");
		} else {
			if (user.equals("bizideal") && pass.equals("123456")) {
				sharedPreferences.edit().putString("user", user)
						.putString("pass", pass).putString("port", port)
						.putString("ip", ip).commit();
				SocketClient.ip = ip;
				startActivity(new Intent(getApplicationContext(),
						UnLockActivity.class));
				finish();
			} else {
				new AlertDialog.Builder(LoginActivity.this).setTitle("登录失败")
						.setMessage("密码或用户名错误").setPositiveButton("Ok", null)
						.show();
			}
		}
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (number % 2 == 0) {
				tv_login_text.setVisibility(View.INVISIBLE);
			} else {
				tv_login_text.setVisibility(View.VISIBLE);
			}
			handler.postDelayed(tiemRunnable, 1000);
		}
	};
	Runnable tiemRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			number++;
			Message msg = handler.obtainMessage();
			handler.sendMessage(msg);
		}
	};

	private void initView() {
		// TODO Auto-generated method stub
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_port = (EditText) findViewById(R.id.et_port);
		et_user = (EditText) findViewById(R.id.et_user);
		sharedPreferences = getSharedPreferences("rember", MODE_PRIVATE);
		tv_login_text = (TextView) findViewById(R.id.tv_login_text);
		tv_login_time = (TextView) findViewById(R.id.tv_login_time);
	}
}
