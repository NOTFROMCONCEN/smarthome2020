package com.example.shengsaiyangti20180906;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.PopupWindow;

import com.bizideal.smarthome.socket.SocketClient;
import com.example.shengsaiyangti20180906.fragment.BarActivity;
import com.example.shengsaiyangti20180906.tools.DiyToast;
import com.example.shengsaiyangti20180906.tools.MyDataBaseHelper;

public class LoginActivity extends Activity {

	private Button btn_login, btn_web, btn_reg;
	private EditText et_user, et_pass;
	private SharedPreferences sharedPreferences;
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();
		try {
			if (sharedPreferences.getString("ip", null).isEmpty()) {
				sharedPreferences.edit().putString("ip", "18.1.10.2").commit();
			}
		} catch (Exception e) {
			// TODO: handle exception
			sharedPreferences.edit().putString("ip", "18.1.10.2").commit();
		}
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_user.getText().toString().isEmpty()
						|| et_pass.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "不能有空白项");
				} else {
					Cursor cursor = db
							.rawQuery(
									"select * from user where username = ? and passward = ?",
									new String[] {
											et_user.getText().toString(),
											et_pass.getText().toString() });
					if (cursor.moveToNext()) {
						SocketClient.ip = sharedPreferences.getString("ip",
								null);
						startActivity(new Intent(getApplicationContext(),
								BarActivity.class));
						finish();
					} else {
						DiyToast.showToast(getApplicationContext(), "用户名或密码错误");
					}
				}
			}
		});
		btn_reg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				pop_reg();
			}
		});
		btn_web.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				pop_web();
			}
		});
	}

	private void pop_reg() {
		// TODO Auto-generated method stub
		View view = LayoutInflater.from(getApplicationContext()).inflate(
				R.layout.popwindow_reg, null, false);
		final PopupWindow popupWindow = new PopupWindow(view, 250, 300, true);
		popupWindow.setTouchable(true);
		popupWindow.setOutsideTouchable(true);
		popupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(),
				(Bitmap) null));
		popupWindow.showAsDropDown(btn_reg);
		Button btn_reg_con = (Button) view.findViewById(R.id.btn_reg_con);
		final EditText et_euser, et_epass, et_repass;
		et_euser = (EditText) view.findViewById(R.id.et_euser);
		et_epass = (EditText) view.findViewById(R.id.et_epass);
		et_repass = (EditText) view.findViewById(R.id.et_repass);
		btn_reg_con.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_euser.getText().toString().isEmpty()
						|| et_euser.getText().toString().isEmpty()
						|| et_repass.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "不能有空白项");
				} else {
					Cursor cursor = db.rawQuery(
							"select * from user where username = ?",
							new String[] { et_euser.getText().toString() });
					if (cursor.moveToNext()) {
						DiyToast.showToast(getApplicationContext(), "用户名已存在");
					} else {
						if (et_epass.getText().toString()
								.equals(et_repass.getText().toString())) {
							db.execSQL(
									"insert into user (username,passward)values(?,?)",
									new String[] {
											et_euser.getText().toString(),
											et_epass.getText().toString() });
							popupWindow.dismiss();
						} else {
							DiyToast.showToast(getApplicationContext(),
									"密码和确认密码不一致");
						}
					}
				}
			}
		});
	}

	private void pop_web() {
		// TODO Auto-generated method stub
		View view = LayoutInflater.from(getApplicationContext()).inflate(
				R.layout.popwindow_web, null, false);
		final PopupWindow popupWindow = new PopupWindow(view, 250, 300, true);
		popupWindow.setTouchable(true);
		popupWindow.setOutsideTouchable(true);
		popupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(),
				(Bitmap) null));
		popupWindow.showAsDropDown(btn_web);
		Button btn_web_con = (Button) view.findViewById(R.id.btn_web_con);
		final EditText et_ip, et_port;
		et_ip = (EditText) view.findViewById(R.id.et_ip);
		et_port = (EditText) view.findViewById(R.id.et_port);
		btn_web_con.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_ip.getText().toString().isEmpty()
						|| et_port.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "不能有空白项");
				} else {
					sharedPreferences.edit()
							.putString("ip", et_ip.getText().toString())
							.commit();
					popupWindow.dismiss();
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
		et_user = (EditText) findViewById(R.id.et_user);
		et_pass = (EditText) findViewById(R.id.et_pass);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		btn_web = (Button) findViewById(R.id.btn_web);
	}
}
