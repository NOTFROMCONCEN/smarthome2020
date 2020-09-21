package com.example.shengsai2019e0921;

import com.bizideal.smarthome.socket.SocketClient;
import com.example.shengsai2019e0921.fragment.BarActivity;
import com.example.shengsai2019e0921.tools.DiyToast;
import com.example.shengsai2019e0921.tools.MyDataBaseHelper;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends Activity {

	private String chose_user = "chose";
	private Spinner sp_user;
	private EditText et_user, et_pass, et_ip, et_port;
	private CheckBox cb_rember;
	private SharedPreferences sharedPreferences;
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		initUser();
		initSave();
	}

	private void initSave() {
		// TODO Auto-generated method stub
		if (sharedPreferences.getBoolean("rember", false) == true) {
			et_ip.setText(sharedPreferences.getString("ip", null));
			et_pass.setText(sharedPreferences.getString("pass", null));
			et_port.setText(sharedPreferences.getString("port", null));
			et_user.setText(sharedPreferences.getString("user", null));
			cb_rember.setChecked(true);
		}
	}

	private void initUser() {
		// TODO Auto-generated method stub
		Cursor cursor = db.rawQuery("select * from user", null);
		if (cursor.getCount() != 0) {
			SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(
					getApplicationContext(), R.layout.item, cursor,
					new String[] { "username" },
					new int[] { R.id.tv_show_user });
			sp_user.setAdapter(simpleCursorAdapter);
		}
	}

	private void initView() {
		// TODO Auto-generated method stub
		sharedPreferences = getSharedPreferences("rember", MODE_PRIVATE);
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
		sp_user = (Spinner) findViewById(R.id.sp_user);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_port = (EditText) findViewById(R.id.et_port);
		et_user = (EditText) findViewById(R.id.et_user);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
	}

	public void reback(final View view) {
		// TODO Auto-generated method stub
		View view2 = LayoutInflater.from(MainActivity.this).inflate(
				R.layout.dialog_reback_pass, null, false);
		final EditText et_user, et_twopass;
		et_user = (EditText) view2.findViewById(R.id.et_reback_user);
		et_twopass = (EditText) view2.findViewById(R.id.et_reback_twopass);
		new AlertDialog.Builder(MainActivity.this).setTitle("").setView(view2)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						if (et_twopass.getText().toString().isEmpty()
								|| et_user.getText().toString().isEmpty()) {
							DiyToast.showToast(getApplicationContext(),
									"不能有空白项");
							reback(view);
						} else {
							Cursor cursor = db
									.rawQuery(
											"select * from user where username = ?",
											new String[] { et_user.getText()
													.toString() });
							if (!cursor.moveToNext()) {
								DiyToast.showToast(getApplicationContext(),
										"用户名不存在");
								reback(view);
							} else {
								if (et_twopass
										.getText()
										.toString()
										.equals(cursor.getString(cursor
												.getColumnIndex("twopass")))) {
									DiyToast.showToast(
											getApplicationContext(),
											"密码是："
													+ cursor.getString(cursor
															.getColumnIndex("passward")));
									initUser();
								} else {
									DiyToast.showToast(getApplicationContext(),
											"二级密码错误");
									reback(view);
								}
							}
						}
					}
				}).show();
	}

	public void uppass(final View view) {
		// TODO Auto-generated method stub
		View view2 = LayoutInflater.from(MainActivity.this).inflate(
				R.layout.dialog_updata_pass, null, false);
		final EditText et_user, et_newpass, et_oldpass;
		et_oldpass = (EditText) view2.findViewById(R.id.et_updata_oldpass);
		et_newpass = (EditText) view2.findViewById(R.id.et_updata_newpass);
		et_user = (EditText) view2.findViewById(R.id.et_updata_user);
		new AlertDialog.Builder(MainActivity.this).setTitle("").setView(view2)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						if (et_oldpass.getText().toString().isEmpty()
								|| et_newpass.getText().toString().isEmpty()
								|| et_user.getText().toString().isEmpty()) {
							DiyToast.showToast(getApplicationContext(),
									"不能有空白项");
							uppass(view);
						} else {
							Cursor cursor = db
									.rawQuery(
											"select * from user where username = ?",
											new String[] { et_user.getText()
													.toString() });
							if (!cursor.moveToNext()) {
								DiyToast.showToast(getApplicationContext(),
										"用户名不存在");
								uppass(view);
							} else {
								if (et_oldpass
										.getText()
										.toString()
										.equals(cursor.getString(cursor
												.getColumnIndex("passward")))) {
									if (et_newpass
											.getText()
											.toString()
											.equals(et_oldpass.getText()
													.toString())) {
										DiyToast.showToast(
												getApplicationContext(),
												"新旧密码不能一致");
										uppass(view);
									} else {
										db.execSQL(
												"update user set passward = ? where username = ?",
												new String[] {
														et_newpass.getText()
																.toString(),
														et_user.getText()
																.toString() });
										DiyToast.showToast(
												getApplicationContext(),
												"密码修改成功");
										initUser();
									}
								} else {
									DiyToast.showToast(getApplicationContext(),
											"旧密码错误");
									uppass(view);
								}
							}
						}
					}
				}).show();
	}

	public void reg(final View view) {
		// TODO Auto-generated method stub
		View view2 = LayoutInflater.from(MainActivity.this).inflate(
				R.layout.dialog_reg, null, false);
		final EditText et_user, et_pass, et_repass, et_twopass;
		et_pass = (EditText) view2.findViewById(R.id.et_reg_pass);
		et_repass = (EditText) view2.findViewById(R.id.et_reg_repass);
		et_twopass = (EditText) view2.findViewById(R.id.et_reg_twopass);
		et_user = (EditText) view2.findViewById(R.id.et_reg_user);
		new AlertDialog.Builder(MainActivity.this).setTitle("").setView(view2)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						if (et_pass.getText().toString().isEmpty()
								|| et_repass.getText().toString().isEmpty()
								|| et_twopass.getText().toString().isEmpty()
								|| et_user.getText().toString().isEmpty()) {
							DiyToast.showToast(getApplicationContext(),
									"不能有空白项");
							reg(view);
						} else {
							Cursor cursor = db
									.rawQuery(
											"select * from user where username = ?",
											new String[] { et_user.getText()
													.toString() });
							if (cursor.moveToNext()) {
								DiyToast.showToast(getApplicationContext(),
										"用户名已存在，请重新输入");
								reg(view);
							} else {
								if (et_pass.getText().toString()
										.equals(et_repass.getText().toString())) {
									db.execSQL(
											"insert into user (username,passward,twopass)values(?,?,?)",
											new String[] {
													et_user.getText()
															.toString(),
													et_pass.getText()
															.toString(),
													et_twopass.getText()
															.toString() });
									DiyToast.showToast(getApplicationContext(),
											"注册成功");
									initUser();
								} else {
									DiyToast.showToast(getApplicationContext(),
											"重复密码不一致");
									reg(view);
								}
							}
						}
					}
				}).show();
	}

	public void login(View view) {
		// TODO Auto-generated method stub
		String pass = et_pass.getText().toString();
		String ip = et_ip.getText().toString();
		String port = et_port.getText().toString();
		if (chose_user.equals("chose")) {
			Cursor cursor_user = (Cursor) sp_user.getItemAtPosition(sp_user
					.getSelectedItemPosition());
			String user = cursor_user.getString(1);
			Cursor cursor = db.rawQuery(
					"select * from user where username = ? and passward = ?",
					new String[] { user, et_pass.getText().toString() });
			if (cursor.moveToNext()) {
				if (cb_rember.isChecked()) {
					sharedPreferences.edit().putBoolean("rember", true)
							.putString("user", user).putString("pass", pass)
							.putString("port", port).putString("ip", ip)
							.commit();
				} else {
					sharedPreferences.edit().putBoolean("rember", false)
							.putString("user", user).putString("pass", pass)
							.putString("port", port).putString("ip", ip)
							.commit();
				}
				SocketClient.ip = ip;
				startActivity(new Intent(getApplicationContext(),
						BarActivity.class));
				finish();
			} else {
				DiyToast.showToast(getApplicationContext(), "密码输入错误或用户名选择错误");
			}
		} else {
			String user = et_user.getText().toString();
			if (pass.isEmpty() || user.isEmpty() || port.isEmpty()
					|| ip.isEmpty()) {
				DiyToast.showToast(getApplicationContext(), "不能有空白项");
			} else {
				Cursor cursor = db
						.rawQuery(
								"select * from user where username = ? and passward = ?",
								new String[] { user, pass });
				if (cursor.moveToNext()) {
					if (cb_rember.isChecked()) {
						sharedPreferences.edit().putBoolean("rember", true)
								.putString("user", user)
								.putString("pass", pass)
								.putString("port", port).putString("ip", ip)
								.commit();
					} else {
						sharedPreferences.edit().putBoolean("rember", false)
								.putString("user", user)
								.putString("pass", pass)
								.putString("port", port).putString("ip", ip)
								.commit();
					}
					SocketClient.ip = ip;
					startActivity(new Intent(getApplicationContext(),
							BarActivity.class));
					finish();
				} else {
					DiyToast.showToast(getApplicationContext(), "用户名或密码输入错误");
				}

			}
		}
	}

	public void chose_user(View view) {
		// TODO Auto-generated method stub
		chose_user = "chose";
		sp_user.setVisibility(View.VISIBLE);
		et_user.setVisibility(View.GONE);
		initUser();
	}

	public void edit_user(View view) {
		// TODO Auto-generated method stub
		chose_user = "edit";
		sp_user.setVisibility(View.GONE);
		et_user.setVisibility(View.VISIBLE);
		initUser();
	}
}
