package com.example.shengsaib06192019;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity {

	private TextView tv_login_text, tv_logoin_time;
	private Button btn_login;
	private EditText et_user, et_pass, et_port, et_ip;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();

	}

	private void initView() {
		// TODO Auto-generated method stub
		btn_login = (Button) findViewById(R.id.btn_login);
		tv_login_text = (TextView) findViewById(R.id.tv_loading_text);
		tv_logoin_time = (TextView) findViewById(R.id.tv_login_time);
		et_ip = (EditText) findViewById(R.id.et_ip);
	}
}
