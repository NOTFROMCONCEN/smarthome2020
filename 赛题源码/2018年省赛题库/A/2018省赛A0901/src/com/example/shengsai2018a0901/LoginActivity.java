package com.example.shengsai2018a0901;

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

import com.example.shengsai2018a0901.fragment.BarActivity;
import com.example.shengsai2018a0901.tools.AppConfig;
import com.example.shengsai2018a0901.tools.DiyToast;
import com.example.shengsai2018a0901.tools.MyDataBaseHelper;

public class LoginActivity extends Activity {
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;
	private Button btn_login, btn_reg;
	private EditText et_user, et_ip, et_pass;
	private CheckBox cb_rember, cb_autologin;
	public static SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		init();
		if (sharedPreferences.getBoolean("rember", false) == true) {
			et_ip.setText(sharedPreferences.getString("ip", null));
			et_pass.setText(sharedPreferences.getString("pass", null));
			et_user.setText(sharedPreferences.getString("user", null));
			cb_rember.setChecked(true);
		}
		if (sharedPreferences.getBoolean("autologin", false) == true) {
			et_ip.setText(sharedPreferences.getString("ip", null));
			et_pass.setText(sharedPreferences.getString("pass", null));
			et_user.setText(sharedPreferences.getString("user", null));
			cb_rember.setChecked(true);
			cb_autologin.setChecked(true);
			DiyToast.showToast(getApplicationContext(), "正在自动登录");
			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						Thread.sleep(2000);
					} catch (Exception e) {
						// TODO: handle exception
					}
					AppConfig.IP = et_ip.getText().toString();
					startActivity(new Intent(getApplicationContext(),
							BarActivity.class));
					finish();
				}
			}).start();
		}
		btn_reg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getApplicationContext(),
						RegActivity.class));
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
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_ip.getText().toString().isEmpty()
						|| et_pass.getText().toString().isEmpty()
						|| et_user.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "参数不能为空");
				} else {
					Cursor cursor = db
							.rawQuery(
									"select * from user where username = ? and passward = ?",
									new String[] {
											et_user.getText().toString(),
											et_pass.getText().toString() });
					if (cursor.moveToNext()) {
						if (cb_autologin.isChecked() && cb_rember.isChecked()) {
							sharedPreferences
									.edit()
									.putBoolean("autologin", true)
									.putString("ip", et_ip.getText().toString())
									.putString("user",
											et_user.getText().toString())
									.putString("pass",
											et_pass.getText().toString())
									.putBoolean("rember", true).commit();
						} else if (cb_autologin.isChecked()) {
							sharedPreferences
									.edit()
									.putBoolean("autologin", true)
									.putString("ip", et_ip.getText().toString())
									.putString("user",
											et_user.getText().toString())
									.putString("pass",
											et_pass.getText().toString())
									.putBoolean("rember", true).commit();
						} else if (cb_rember.isChecked()) {
							sharedPreferences
									.edit()
									.putBoolean("autologin", false)
									.putString("ip", et_ip.getText().toString())
									.putString("user",
											et_user.getText().toString())
									.putString("pass",
											et_pass.getText().toString())
									.putBoolean("rember", true).commit();
						} else {
							sharedPreferences
									.edit()
									.putBoolean("autologin", false)
									.putString("ip", et_ip.getText().toString())
									.putString("user",
											et_user.getText().toString())
									.putString("pass",
											et_pass.getText().toString())
									.putBoolean("rember", false).commit();
						}
						AppConfig.IP = et_ip.getText().toString();
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

	private void init() {
		// TODO Auto-generated method stub
		sharedPreferences = getSharedPreferences("rember", MODE_PRIVATE);
		dbHelper = new MyDataBaseHelper(LoginActivity.this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_user = (EditText) findViewById(R.id.et_user);
		cb_autologin = (CheckBox) findViewById(R.id.cb_autologin);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
	}
}
