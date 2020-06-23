package com.example.shengsaib06232019;

import com.example.shengsaib06232019.tools.AppConfig;
import com.example.shengsaib06232019.tools.DiyToast;

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

public class LoginActivity extends Activity {

	private EditText et_user, et_pass, et_port, et_ip;
	private Button btn_login;
	private TextView tv_login_time, tv_login_text;
	private SharedPreferences sharedPreferences;
	private int number = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();
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
								.putString("ip", et_ip.getText().toString())
								.putString("pass", et_pass.getText().toString())
								.putString("port", et_port.getText().toString())
								.putString("user", et_user.getText().toString())
								.commit();
						startActivity(new Intent(LoginActivity.this,
								UnLockActivity.class));
						finish();
					} else {
						AlertDialog.Builder builder = new AlertDialog.Builder(
								LoginActivity.this);
						builder.setTitle("登录失败");
						builder.setMessage("用户名或密码输入错误");
						builder.setPositiveButton("Ok", null);
						builder.show();
					}
				}
			}
		});
		handler.post(timeRunnable);
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (msg.what % 2 == 0) {
				tv_login_text.setVisibility(View.INVISIBLE);
			} else {
				tv_login_text.setVisibility(View.VISIBLE);
			}
			tv_login_time.setText(AppConfig.time);
			handler.postDelayed(timeRunnable, 1000);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			number++;
			Message msg = handler.obtainMessage();
			msg.what = number;
			handler.sendMessage(msg);
		}
	};

	private void initView() {
		// TODO Auto-generated method stub
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_port = (EditText) findViewById(R.id.et_port);
		et_user = (EditText) findViewById(R.id.et_user);
		btn_login = (Button) findViewById(R.id.btn_login);
		tv_login_text = (TextView) findViewById(R.id.tv_login_ojbk);
		tv_login_time = (TextView) findViewById(R.id.tv_login_time);
		sharedPreferences = getSharedPreferences("rember", MODE_PRIVATE);
	}
}