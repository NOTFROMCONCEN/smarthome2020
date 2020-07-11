package com.example.shengsaie07112019;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.example.shengsaie07112019.tools.AppConfig;
import com.example.shengsaie07112019.tools.DiyToast;
import com.example.shengsaie07112019.tools.MySQLiteHelper;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO 程序主入口、登录、注册、修改密码
 * @package_name com.example.shengsaie07112019
 * @project_name 2019省赛E0711
 * @file_name MainActivity.java
 * @我的博客 https://naiyouhuameitang.club/
 */
public class MainActivity extends Activity {

	private Button btn_login, btn_reg, btn_update, btn_reback;
	private EditText et_user, et_pass, et_ip, et_port;
	private Spinner sp_user;
	private CheckBox cb_rember;
	private RadioButton ra_chose, ra_edit;
	private SQLiteDatabase db;
	private MySQLiteHelper dbHelper;
	ArrayList<String> user_aArrayList = new ArrayList<String>();
	private SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		if (sharedPreferences.getBoolean("edit_spinner", false) == true) {
			cb_rember.setChecked(true);
			sp_user.setVisibility(View.GONE);
			et_user.setVisibility(View.VISIBLE);
			et_user.setText(sharedPreferences.getString("user", null));
			et_pass.setText(sharedPreferences.getString("pass", null));
			et_port.setText(sharedPreferences.getString("port", null));
			et_ip.setText(sharedPreferences.getString("ip", null));
		} else {
			cb_rember.setChecked(true);
			sp_user.setVisibility(View.VISIBLE);
			et_user.setVisibility(View.GONE);
			et_pass.setText(sharedPreferences.getString("pass", null));
			et_port.setText(sharedPreferences.getString("port", null));
			et_ip.setText(sharedPreferences.getString("ip", null));
		}
		btn_reg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				show_reg_dialog();
			}
		});
		btn_reback.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				show_reback_dialog();
			}
		});
		btn_update.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				show_update_dialog();
			}
		});
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (ra_chose.isChecked()) {
					if (et_ip.getText().toString().isEmpty()
							|| et_pass.getText().toString().isEmpty()
							|| et_port.getText().toString().isEmpty()) {
						DiyToast.showToast(getApplicationContext(), "不能有空白项");
					} else {
						Cursor cursor_user = (Cursor) sp_user
								.getItemAtPosition(sp_user
										.getSelectedItemPosition());
						Cursor cursor = db
								.rawQuery(
										"select * from user where username = ? and passward = ?",
										new String[] {
												cursor_user.getString(1),
												et_pass.getText().toString() });
						if (cursor.moveToNext()) {
							if (cb_rember.isChecked()) {
								sharedPreferences
										.edit()
										.putBoolean("rember", true)
										.putBoolean("edit_spinner", false)
										.putString("ip",
												et_ip.getText().toString())
										.putString("port",
												et_port.getText().toString())
										.putString("pass",
												et_pass.getText().toString())
										.commit();
							} else {
								sharedPreferences
										.edit()
										.putBoolean("rember", false)
										.putBoolean("edit_spinner", false)
										.putString("ip",
												et_ip.getText().toString())
										.putString("port",
												et_port.getText().toString())
										.putString("pass",
												et_pass.getText().toString())
										.commit();
							}
							AppConfig.ipString = et_ip.getText().toString();
							startActivity(new Intent(getApplicationContext(),
									BarActivity.class));
							finish();
						} else {
							DiyToast.showToast(getApplicationContext(),
									"用户名或密码输入错误");
						}
					}
				}
				if (ra_edit.isChecked()) {
					if (et_ip.getText().toString().isEmpty()
							|| et_pass.getText().toString().isEmpty()
							|| et_port.getText().toString().isEmpty()
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
							if (cb_rember.isChecked()) {
								sharedPreferences
										.edit()
										.putBoolean("rember", true)
										.putBoolean("edit_spinner", false)
										.putString("ip",
												et_ip.getText().toString())
										.putString("port",
												et_port.getText().toString())
										.putString("pass",
												et_pass.getText().toString())
										.commit();
							} else {
								sharedPreferences
										.edit()
										.putBoolean("rember", false)
										.putBoolean("edit_spinner", false)
										.putString("ip",
												et_ip.getText().toString())
										.putString("port",
												et_port.getText().toString())
										.putString("pass",
												et_pass.getText().toString())
										.commit();
							}
							AppConfig.ipString = et_ip.getText().toString();
							startActivity(new Intent(getApplicationContext(),
									BarActivity.class));
							finish();
						} else {
							DiyToast.showToast(getApplicationContext(),
									"用户名或密码输入错误");
						}
					}
				}
			}
		});
		ra_chose.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				sp_user.setVisibility(View.VISIBLE);
				et_user.setVisibility(View.GONE);
			}
		});
		ra_edit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				sp_user.setVisibility(View.GONE);
				et_user.setVisibility(View.VISIBLE);
			}
		});
		sp_user.setVisibility(View.VISIBLE);
		et_user.setVisibility(View.GONE);
		set_user();
	}

	private void set_user() {
		// TODO Auto-generated method stub
		Cursor cursor = db.rawQuery("Select * from user", null);
		if (cursor.getCount() != 0) {
			SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(
					getApplicationContext(), R.layout.item, cursor,
					new String[] { "username" },
					new int[] { R.id.tv_show_user });
			sp_user.setAdapter(simpleCursorAdapter);
		}
	}

	private void show_update_dialog() {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		View view = LayoutInflater.from(MainActivity.this).inflate(
				R.layout.dialog_updata_pass, null, false);
		final EditText et_update_user = (EditText) view
				.findViewById(R.id.et_updata_user);
		final EditText et_update_newpass = (EditText) view
				.findViewById(R.id.et_updata_newpass);
		final EditText et_update_oldpass = (EditText) view
				.findViewById(R.id.et_updata_oldpass);
		builder.setPositiveButton("修改密码",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						if (et_update_newpass.getText().toString().isEmpty()
								|| et_update_oldpass.getText().toString()
										.isEmpty()
								|| et_update_user.getText().toString()
										.isEmpty()) {
							DiyToast.showToast(getApplicationContext(),
									"不能有空白项");
							show_update_dialog();
						} else {
							Cursor cursor = db.rawQuery(
									"select * from user where username = ?",
									new String[] { et_update_user.getText()
											.toString() });
							if (cursor.moveToNext()) {
								if (et_update_newpass
										.getText()
										.toString()
										.equals(et_update_oldpass.getText()
												.toString())) {
									DiyToast.showToast(getApplicationContext(),
											"新旧密码不能一致");
									show_update_dialog();
								} else {
									if (cursor.getString(
											cursor.getColumnIndex("passward"))
											.equals(et_update_oldpass.getText()
													.toString())) {

										db.execSQL(
												"update user set passward = ? where username = ?",
												new String[] {
														et_update_newpass
																.getText()
																.toString(),
														et_update_user
																.getText()
																.toString() });
										DiyToast.showToast(
												getApplicationContext(), "修改完成");

									} else {
										DiyToast.showToast(
												getApplicationContext(),
												"旧密码错误");
										show_update_dialog();
									}
								}
							} else {
								DiyToast.showToast(getApplicationContext(),
										"用户名不存在");
								show_update_dialog();
							}
						}
					}
				});
		builder.setView(view);
		builder.show();
	}

	private void show_reback_dialog() {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		View view = LayoutInflater.from(MainActivity.this).inflate(
				R.layout.dialog_reback_pass, null, false);
		builder.setView(view);
		final EditText et_reback_user = (EditText) view
				.findViewById(R.id.et_reback_user);
		final EditText et_reback_twopass = (EditText) view
				.findViewById(R.id.et_reback_twopass);
		builder.setPositiveButton("找回密码",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						if (et_reback_twopass.getText().toString().isEmpty()
								|| et_reback_user.getText().toString()
										.isEmpty()) {
							DiyToast.showToast(getApplicationContext(),
									"不能用空白项");
							show_reback_dialog();
						} else {
							Cursor cursor = db.rawQuery(
									"select * from user where username = ?",
									new String[] { et_reback_user.getText()
											.toString() });
							if (cursor.moveToNext()) {
								if (cursor.getString(
										cursor.getColumnIndex("two_pass"))
										.equals(et_reback_twopass.getText()
												.toString())) {
									DiyToast.showToast(
											getApplicationContext(),
											"你的密码是："
													+ cursor.getString(cursor
															.getColumnIndex("passward")));
								} else {
									DiyToast.showToast(getApplicationContext(),
											"二级密码错误");
									show_reback_dialog();
								}
							} else {
								DiyToast.showToast(getApplicationContext(),
										"用户名输入错误");
								show_reback_dialog();
							}
						}
					}
				});
		builder.show();
	}

	private void show_reg_dialog() {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		View view = LayoutInflater.from(MainActivity.this).inflate(
				R.layout.dialog_reg, null, false);
		builder.setView(view);
		final EditText et_reg_user = (EditText) view
				.findViewById(R.id.et_reg_user);
		final EditText et_reg_pass = (EditText) view
				.findViewById(R.id.et_reg_pass);
		final EditText et_reg_repass = (EditText) view
				.findViewById(R.id.et_reg_repass);
		final EditText et_reg_twopass = (EditText) view
				.findViewById(R.id.et_reg_twopass);
		builder.setPositiveButton("注册", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				if (et_reg_pass.getText().toString().isEmpty()
						|| et_reg_repass.getText().toString().isEmpty()
						|| et_reg_twopass.getText().toString().isEmpty()
						|| et_reg_user.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "不能有空白项");
					show_reg_dialog();
				} else {
					if (et_reg_pass.getText().toString()
							.equals(et_reg_repass.getText().toString())) {
						Cursor cursor = db
								.rawQuery(
										"select * from user where username = ?",
										new String[] { et_reg_user.getText()
												.toString() });
						if (cursor.moveToNext()) {
							DiyToast.showToast(getApplicationContext(),
									"用户名已存在");
							show_reg_dialog();
						} else {
							db.execSQL(
									"insert into user (username,passward,two_pass)values(?,?,?)",
									new String[] {
											et_reg_user.getText().toString(),
											et_reg_pass.getText().toString(),
											et_reg_twopass.getText().toString() });
							DiyToast.showToast(getApplicationContext(), "注册成功");
							set_user();
						}
					} else {
						DiyToast.showToast(getApplicationContext(), "重复密码不一致");
						show_reg_dialog();
					}
				}
			}
		});
		builder.show();
	}

	private void initView() {
		// TODO Auto-generated method stub
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reback = (Button) findViewById(R.id.btn_reback_pass);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		btn_update = (Button) findViewById(R.id.btn_updata_pass);
		sp_user = (Spinner) findViewById(R.id.sp_user);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
		ra_chose = (RadioButton) findViewById(R.id.ra_chose_user);
		ra_edit = (RadioButton) findViewById(R.id.ra_set_user);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_port = (EditText) findViewById(R.id.et_port);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_user = (EditText) findViewById(R.id.et_user);
		dbHelper = new MySQLiteHelper(getApplicationContext(), "info.db", null,
				2);
		db = dbHelper.getWritableDatabase();
		sharedPreferences = getSharedPreferences("rember", MODE_PRIVATE);
	}
}
