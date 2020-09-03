package com.example.shengsai2018e0903;

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
import android.widget.EditText;

import com.bizideal.smarthome.socket.SocketClient;
import com.example.shengsai2018e0903.fragment.BbarActivity;
import com.example.shengsai2018e0903.tools.DiyToast;
import com.example.shengsai2018e0903.tools.MyDataBaseHelper;
import com.example.shengsai2018e0903.tools.UuserOp;

public class LoginActivity extends Activity {

	private Button btn_login_con;
	private EditText et_user, et_pass, et_ip;
	private CheckBox cb_rember, cb_autologin;
	private SharedPreferences sharedPreferences;
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;

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
		btn_login_con.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_user.getText().toString().isEmpty()
						|| et_pass.getText().toString().isEmpty()
						|| et_user.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "不能有空白项");
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
									.putBoolean("rember", true)
									.putBoolean("autologin", true)
									.putString("ip", et_ip.getText().toString())
									.putString("user",
											et_user.getText().toString())
									.putString("pass",
											et_pass.getText().toString())
									.commit();
						} else if (cb_rember.isChecked()) {
							sharedPreferences
									.edit()
									.putBoolean("rember", true)
									.putBoolean("autologin", false)
									.putString("ip", et_ip.getText().toString())
									.putString("user",
											et_user.getText().toString())
									.putString("pass",
											et_pass.getText().toString())
									.commit();
						} else {
							sharedPreferences
									.edit()
									.putBoolean("rember", false)
									.putBoolean("autologin", false)
									.putString("ip", et_ip.getText().toString())
									.putString("user",
											et_user.getText().toString())
									.putString("pass",
											et_pass.getText().toString())
									.commit();
						}
						if (et_user.getText().toString().equals("bizideal")) {
							UuserOp.user_op = "op";
						} else {
							UuserOp.user_op = "user";
						}
						SocketClient.ip = et_ip.getText().toString();
						startActivity(new Intent(getApplicationContext(),
								BbarActivity.class));
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
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
		sharedPreferences = getSharedPreferences("rember", MODE_PRIVATE);
		btn_login_con = (Button) findViewById(R.id.btn_login_con);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_user = (EditText) findViewById(R.id.et_user);
		cb_autologin = (CheckBox) findViewById(R.id.cb_autologin);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
	}
}
