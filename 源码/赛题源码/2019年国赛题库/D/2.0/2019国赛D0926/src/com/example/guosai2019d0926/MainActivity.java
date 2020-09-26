package com.example.guosai2019d0926;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bizideal.smarthome.socket.SocketClient;
import com.example.guosai2019d0926.tools.DiyToast;
import com.example.guosai2019d0926.tools.MyDataBaseHelper;
import com.example.guosai2019d0926.tools.TextChanger;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

	private RelativeLayout re_login, re_reg;
	private EditText et_user, et_pass, et_ip, et_euser, et_epass, et_repass;
	private SharedPreferences sharedPreferences;
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;
	private CheckBox cb_rember;
	private boolean text_size = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		initOpen();
		check_textsize();
		if (sharedPreferences.getBoolean("rember", false) == true) {
			et_user.setText(sharedPreferences.getString("user", null));
			et_ip.setText(sharedPreferences.getString("ip", null));
			et_pass.setText(sharedPreferences.getString("pass", null));
			cb_rember.setChecked(true);
		}
		et_pass.setTransformationMethod(new TextChanger());
		et_epass.setTransformationMethod(new TextChanger());
		et_repass.setTransformationMethod(new TextChanger());
	}

	private void initOpen() {
		// TODO Auto-generated method stub
		if (sharedPreferences.getBoolean("IS_FIRST", false) == true) {
		} else {
			cb_rember.setChecked(true);
			sharedPreferences.edit().putBoolean("IS_FIRST", true)
					.putBoolean("rember", true).commit();
		}
	}

	private void initView() {
		// TODO Auto-generated method stub
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
		sharedPreferences = getSharedPreferences("rember", MODE_PRIVATE);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
		re_login = (RelativeLayout) findViewById(R.id.re_login);
		re_reg = (RelativeLayout) findViewById(R.id.re_reg);
		et_epass = (EditText) findViewById(R.id.et_epass);
		et_euser = (EditText) findViewById(R.id.et_euser);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_repass = (EditText) findViewById(R.id.et_repass);
		et_user = (EditText) findViewById(R.id.et_user);
	}

	public void reg_con(View view) {
		// TODO Auto-generated method stub

		String euser, epass, repass;
		euser = et_euser.getText().toString();
		epass = et_epass.getText().toString();
		repass = et_repass.getText().toString();
		if (euser.isEmpty() || epass.isEmpty() || repass.isEmpty()) {
			DiyToast.showToast(getApplicationContext(), "不能有空白项");
		} else {
			Cursor cursor = db.rawQuery("select * from user where username =?",
					new String[] { euser });
			if (cursor.moveToNext()) {
				DiyToast.showToast(getApplicationContext(), "用户名已存在");
			} else {
				if (epass.equals(repass)) {
					check_textsize();
					if (text_size) {
						Pattern pattern = Pattern
								.compile("^[a-zA-Z].*[0-9]|.*[0-9].*[a-zA-Z]");
						Matcher matcher = pattern.matcher(et_epass.getText()
								.toString());
						if (!matcher.matches()) {
							DiyToast.showToast(getApplicationContext(),
									"密码格式为：数字+字母");
						} else {
							db.execSQL(
									"insert into user (username,passward)values(?,?)",
									new String[] { euser, epass });
							DiyToast.showToast(getApplicationContext(), "注册成功");
							re_login.setVisibility(View.VISIBLE);
							re_reg.setVisibility(View.INVISIBLE);
						}
					} else {
						DiyToast.showToast(getApplicationContext(), "密码长度不足6位");
					}
				} else {
					DiyToast.showToast(getApplicationContext(), "密码和确认密码不一致");
				}
			}
		}
	}

	public void reg_login(View view) {
		// TODO Auto-generated method stub
		re_login.setVisibility(View.VISIBLE);
		re_reg.setVisibility(View.GONE);
	}

	public void login_reg(View view) {
		// TODO Auto-generated method stub
		re_login.setVisibility(View.GONE);
		re_reg.setVisibility(View.VISIBLE);
	}

	private void check_textsize() {
		et_epass.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				if (start >= 5) {
					text_size = true;
				} else {
					text_size = false;
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
			}
		});
	}

	public void login_con(View view) {
		// TODO Auto-generated method stub
		String user, pass, ip;
		user = et_user.getText().toString();
		pass = et_pass.getText().toString();
		ip = et_ip.getText().toString();
		if (user.isEmpty() || pass.isEmpty() || ip.isEmpty()) {
			DiyToast.showToast(getApplicationContext(), "不能有空白项");
		} else {
			Cursor cursor = db.rawQuery(
					"select * from user where username = ? and passward = ?",
					new String[] { user, pass });
			if (cursor.moveToNext()) {
				if (cb_rember.isChecked()) {
					sharedPreferences.edit().putBoolean("rember", true)
							.putString("user", user).putString("pass", pass)
							.putString("ip", ip).commit();
				} else {
					sharedPreferences.edit().putBoolean("rember", false)
							.putString("user", user).putString("pass", pass)
							.putString("ip", ip).commit();
				}
				SocketClient.ip = ip;
				startActivity(new Intent(getApplicationContext(),
						MenuActivity.class));
				finish();
			} else {
				DiyToast.showToast(getApplicationContext(), "用户名或密码输入错误");
			}
		}
	}
}
