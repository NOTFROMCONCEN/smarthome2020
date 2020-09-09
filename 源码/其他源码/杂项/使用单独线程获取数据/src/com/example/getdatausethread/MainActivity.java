package com.example.getdatausethread;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	private SharedPreferences sharedPreferences;
	private Button btn_login;
	private EditText et_ip;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		et_ip.setText(sharedPreferences.getString("ip", null));
		// 连接按钮
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_ip.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "请输入IP地址");
				} else {
					AppConfig.ip = et_ip.getText().toString();
					startActivity(new Intent(getApplicationContext(),
							BaseActivity.class));
					sharedPreferences.edit()
							.putString("ip", et_ip.getText().toString())
							.apply();

					finish();
				}
			}
		});
	}

	private void initView() {
		// TODO Auto-generated method stub
		sharedPreferences = getSharedPreferences("rember_ip", MODE_PRIVATE);
		btn_login = (Button) findViewById(R.id.btn_login);
		et_ip = (EditText) findViewById(R.id.et_ip);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
