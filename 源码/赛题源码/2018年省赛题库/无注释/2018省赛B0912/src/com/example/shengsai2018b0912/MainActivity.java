package com.example.shengsai2018b0912;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.bizideal.smarthome.socket.SocketClient;
import com.example.shengsai2018b0912.tools.DiyToast;
import com.example.shengsai2018b0912.tools.MyDataBaseHelper;
import com.example.shengsai2018b0912.tools.TextChanger;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO 程序主入口 登录界面
 * @package_name com.example.shengsai2018b0912
 * @project_name 2018省赛B0912
 * @file_name MainActivity.java
 * @我的博客 https://naiyouhuameitang.club/
 */
public class MainActivity extends Activity {

	// 按钮
	private Button btn_login, btn_reg;
	// 文本框
	private EditText et_user, et_pass, et_port, et_ip;
	// sharedPreferences存储
	private SharedPreferences sharedPreferences;
	// 数据库
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();// 绑定控件
		// 如果sharedPreferences中有存储的信息，则填充（记住密码）
		et_user.setText(sharedPreferences.getString("user", null));// 用户名
		et_ip.setText(sharedPreferences.getString("ip", null));// IP地址
		et_pass.setText(sharedPreferences.getString("pass", null));// 密码
		et_port.setText(sharedPreferences.getString("port", null));// 端口号
		// 注册按钮
		btn_reg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 跳转至注册界面
				startActivity(new Intent(getApplicationContext(),
						RegActivity.class));
			}
		});
		// 登录按钮
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 判断用户名、密码、IP地址和端口号是否为空
				if (et_user.getText().toString().isEmpty()
						|| et_pass.getText().toString().isEmpty()
						|| et_port.getText().toString().isEmpty()
						|| et_ip.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "不能有空白项");
				} else {
					// 新建数据库游标
					Cursor cursor = db
							.rawQuery(
									"select * from user where username = ? and passward = ?",
									new String[] {
											et_user.getText().toString(),
											et_pass.getText().toString() });
					// 游标移动（数据库有匹配数据）
					if (cursor.moveToNext()) {
						// 设置网络连接的IP
						SocketClient.ip = et_ip.getText().toString();
						// 向sharedPreferences中存储信息
						sharedPreferences
								.edit()
								.putString("user", et_user.getText().toString())
								.putString("pass", et_pass.getText().toString())
								.putString("ip", et_ip.getText().toString())
								.putString("port", et_port.getText().toString())
								.commit();
						// 跳转至菜单选择界面
						startActivity(new Intent(getApplicationContext(),
								MenuActivity.class));
						finish();
					} else {
						// 游标未移动（无法匹配到相关数据）
						DiyToast.showToast(getApplicationContext(),
								"用户名或密码输入错误");
					}
				}
			}
		});
		// 设置字符转换为*
		et_pass.setTransformationMethod(new TextChanger());
	}

	/**
	 * 绑定控件
	 */
	private void initView() {
		// TODO Auto-generated method stub
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_port = (EditText) findViewById(R.id.et_port);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_user = (EditText) findViewById(R.id.et_user);
		sharedPreferences = getSharedPreferences("rember", MODE_PRIVATE);
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
	}
}
