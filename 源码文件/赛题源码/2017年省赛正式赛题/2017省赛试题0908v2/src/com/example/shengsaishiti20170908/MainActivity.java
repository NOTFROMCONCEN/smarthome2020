package com.example.shengsaishiti20170908;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.shengsaishiti20170908.fragment.BarActivity;
import com.example.shengsaishiti20170908.tools.AppConfig;
import com.example.shengsaishiti20170908.tools.DiyToast;
import com.example.shengsaishiti20170908.tools.MyDataBaseHelper;
import com.example.shengsaishiti20170908.tools.TitleThread;

public class MainActivity extends Activity {

	private LinearLayout line_login, line_reg;
	private Button btn_login, btn_reg, btn_exit, btn_con, btn_cls, btn_resert;
	private EditText et_user, et_pass, et_repass, et_port, et_ip, et_euser,
			et_epass;
	private Spinner sp_user;
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;
	private Handler handler;
	private Runnable timeRunnable;
	private int number = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TitleThread.start(MainActivity.this);
		initView();
		line_login.setVisibility(View.VISIBLE);
		line_reg.setVisibility(View.GONE);
		btn_cls.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				line_login.setVisibility(View.VISIBLE);
				line_reg.setVisibility(View.GONE);
			}
		});
		btn_reg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				line_login.setVisibility(View.GONE);
				line_reg.setVisibility(View.VISIBLE);
			}
		});
		btn_exit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		btn_resert.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				et_euser.setText("");
				et_epass.setText("");
				et_repass.setText("");
			}
		});
		btn_con.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_euser.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "用户名不能为空", 1);
				} else if (et_epass.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "密码不能为空", 1);
				} else if (et_repass.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "确认密码不能为空", 1);
				} else {
					Cursor cursor = db.rawQuery(
							"select * from user where username = ?",
							new String[] { et_euser.getText().toString() });
					if (cursor.moveToNext()) {
						DiyToast.showToast(getApplicationContext(),
								"用户名已存在，请重新输入", 1);
						et_euser.setText("");
					} else {
						if (sp_user.getSelectedItem().toString().equals("家长")) {
							db.execSQL(
									"insert into user (username,passward,op)values(?,?,?)",
									new String[] {
											et_euser.getText().toString(),
											et_epass.getText().toString(), "op" });
						} else {
							db.execSQL(
									"insert into user (username,passward,op)values(?,?,?)",
									new String[] {
											et_euser.getText().toString(),
											et_epass.getText().toString(),
											"user" });
						}
						DiyToast.showToast(getApplicationContext(), "注册成功", 2);
						line_login.setVisibility(View.VISIBLE);
						line_reg.setVisibility(View.GONE);
					}
				}
			}
		});
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_user.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "用户名不能为空", 1);
				} else if (et_pass.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "密码不能为空", 1);
				} else if (et_ip.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "IP地址不能为空", 1);
				} else if (et_port.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "端口号不能为空", 1);
				} else {
					Cursor cursor = db
							.rawQuery(
									"select * from user where username = ? and passward = ?",
									new String[] {
											et_user.getText().toString(),
											et_pass.getText().toString() });
					if (cursor.moveToNext()) {
						AppConfig.ip = et_ip.getText().toString();
						AppConfig.user = cursor.getString(cursor
								.getColumnIndex("op"));
						DiyToast.showToast(getApplicationContext(),
								"登录成功，当前状态：" + AppConfig.user, 2);
						fuck_bizideal();
					} else {
						Cursor cursor_user = db.rawQuery(
								"select * from user where username = ?",
								new String[] { et_user.getText().toString() });
						if (cursor_user.moveToNext()) {
							Cursor cursor_pass = db
									.rawQuery(
											"select * from user where username = ?",
											new String[] { et_pass.getText()
													.toString() });
							if (cursor_pass.moveToNext()) {
							} else {
								DiyToast.showToast(getApplicationContext(),
										"密码输入错误，请重新输入", 1);
							}
						} else {
							DiyToast.showToast(getApplicationContext(),
									"用户名输入错误或不存在，请重新输入", 1);
							et_user.setText("");
						}
					}
				}
			}
		});
	}

	private void fuck_bizideal() {
		AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
				.create();
		View view = LayoutInflater.from(MainActivity.this).inflate(
				R.layout.activity_dialogloading, null, false);
		dialog.setView(view);
		dialog.show();
		dialog.setCanceledOnTouchOutside(false);
		dialog.getWindow().setDimAmount(0);
		dialog.getWindow().setLayout(250, 250);
		start(view);
	}

	private void start(final View view) {
		handler = new Handler() {
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				TextView tv_number = (TextView) view
						.findViewById(R.id.tv_loading_number);
				tv_number.setText("亲，正在玩命加载哦" + "\n" + String.valueOf(msg.what)
						+ "%");
				handler.postDelayed(timeRunnable, 1);
			}
		};
		timeRunnable = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				number++;
				Message msg = handler.obtainMessage();
				msg.what = number;
				if (number <= 100) {
					handler.sendMessage(msg);
				} else {
					handler.removeCallbacks(timeRunnable);
					startActivity(new Intent(getApplicationContext(),
							BarActivity.class));
					finish();
				}
			}
		};
		handler.post(timeRunnable);
	}

	private void initView() {
		// TODO Auto-generated method stub
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
		line_login = (LinearLayout) findViewById(R.id.line_login);
		line_reg = (LinearLayout) findViewById(R.id.line_reg);
		btn_cls = (Button) findViewById(R.id.btn_back);
		btn_con = (Button) findViewById(R.id.btn_con);
		btn_exit = (Button) findViewById(R.id.btn_exit);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		btn_resert = (Button) findViewById(R.id.btn_resert);
		sp_user = (Spinner) findViewById(R.id.sp_user_op);
		et_epass = (EditText) findViewById(R.id.et_epass);
		et_euser = (EditText) findViewById(R.id.et_euser);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_port = (EditText) findViewById(R.id.et_port);
		et_repass = (EditText) findViewById(R.id.et_repass);
		et_user = (EditText) findViewById(R.id.et_user);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
