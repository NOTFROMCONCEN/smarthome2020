package com.example.shengsaisaixiang20180905;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

import com.bizideal.smarthome.socket.SocketClient;
import com.example.shengsaisaixiang20180905.fragment.BarActivity;
import com.example.shengsaisaixiang20180905.tools.DiyToast;
import com.example.shengsaisaixiang20180905.tools.MyDataBaseHelper;
import com.example.shengsaisaixiang20180905.tools.UserConfig;

public class LoginActivity extends Activity {

	private Button btn_login_con;
	private EditText et_user, et_pass, et_ip;
	private SharedPreferences sharedPreferences;
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;
	private CheckBox cb_rember, cb_autologin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();
		if (sharedPreferences.getBoolean("rember", false) == true) {
			cb_rember.setChecked(true);
			et_ip.setText(sharedPreferences.getString("ip", null));
			et_pass.setText(sharedPreferences.getString("pass", null));
			et_user.setText(sharedPreferences.getString("user", null));
		}
		cb_autologin.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					cb_rember.setChecked(true);
				} else {
					cb_rember.setChecked(false);
				}
			}
		});
		cb_rember.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
				} else {
					cb_autologin.setChecked(false);
				}
			}
		});
		btn_login_con.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_ip.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "IP地址不能为空");
				} else if (et_user.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "用户名不能为空");
				} else if (et_pass.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "密码不能为空");
				} else {
					Cursor cursor = db
							.rawQuery(
									"select * from user where username = ? and passward = ?",
									new String[] {
											et_user.getText().toString(),
											et_pass.getText().toString() });
					if (cursor.moveToNext()) {
						if (cb_autologin.isChecked()) {
							sharedPreferences
									.edit()
									.putBoolean("autologin", true)
									.putBoolean("rember", true)
									.putString("user",
											et_user.getText().toString())
									.putString("pass",
											et_pass.getText().toString())
									.putString("ip", et_ip.getText().toString())
									.commit();
						} else if (cb_rember.isChecked()) {
							sharedPreferences
									.edit()
									.putBoolean("autologin", false)
									.putBoolean("rember", true)
									.putString("user",
											et_user.getText().toString())
									.putString("pass",
											et_pass.getText().toString())
									.putString("ip", et_ip.getText().toString())
									.commit();
						} else {
							sharedPreferences
									.edit()
									.putBoolean("autologin", false)
									.putBoolean("rember", false)
									.putString("user",
											et_user.getText().toString())
									.putString("pass",
											et_pass.getText().toString())
									.putString("ip", et_ip.getText().toString())
									.commit();
						}
						if (et_user.getText().toString().equals("bizideal")) {
							UserConfig.now_user = "bizideal";
							UserConfig.op_state = true;
						} else {
							UserConfig.now_user = "null";
							UserConfig.op_state = false;
						}
						SocketClient.ip = et_ip.getText().toString();
						startActivity(new Intent(getApplicationContext(),
								BarActivity.class));
						finish();
					} else {
						DiyToast.showToast(getApplicationContext(),
								"用户名或密码输入错误");
					}
				}
			}
		});
	}

	private void initView() {
		// TODO Auto-generated method stub
		cb_autologin = (CheckBox) findViewById(R.id.cb_autologin);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
		btn_login_con = (Button) findViewById(R.id.btn_login_con);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_user = (EditText) findViewById(R.id.et_user);
		et_pass = (EditText) findViewById(R.id.et_pass);
		sharedPreferences = getSharedPreferences("rember", MODE_PRIVATE);
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
	}
}
