package com.example.shengsai06202019;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.shengsai06202019.tools.AppConfig;
import com.example.shengsai06202019.tools.DiyToast;
import com.example.shengsai06202019.tools.TextChanger;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO 登录
 * @package_name com.example.shengsai06202019
 * @project_name 2019省赛0620
 * @file_name LoginActivity.java
 * @我的博客 https://naiyouhuameitang.club/
 */
public class LoginActivity extends Activity {

	private Button btn_login;
	private TextView tv_login_time, tv_login_text;
	private EditText et_user, et_pass, et_port, et_ip;
	private int number = 0;
	private SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();// 绑定控件
		handler.post(timeRunnable);// 启动线程
		// 如果sharedPreferences不为空，则设置在文本框上
		et_ip.setText(sharedPreferences.getString("ip", null));
		et_pass.setText(sharedPreferences.getString("pass", null));
		et_port.setText(sharedPreferences.getString("port", null));
		et_user.setText(sharedPreferences.getString("user", null));
		// 登录按钮
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 判断文本框是不是空
				if (et_ip.getText().toString().isEmpty()
						|| et_pass.getText().toString().isEmpty()
						|| et_port.getText().toString().isEmpty()
						|| et_user.getText().toString().isEmpty()) {
					// 弹出提示
					DiyToast.showToast(getApplicationContext(), "不能有空白项");
				} else {
					// 判断用户名和密码是否正确
					if (et_user.getText().toString().equals("bizideal")
							&& et_pass.getText().toString().equals("123456")) {
						// 保存到sharedPreferences
						sharedPreferences
								.edit()
								.putString("ip", et_ip.getText().toString())
								.putString("pass", et_pass.getText().toString())
								.putString("port", et_port.getText().toString())
								.putString("user", et_user.getText().toString())
								.commit();
						// 跳转
						startActivity(new Intent(getApplicationContext(),
								UnLockActivity.class));
						finish();
					} else {
						// 登录失败弹出提示
						AlertDialog.Builder builder = new AlertDialog.Builder(
								LoginActivity.this);
						builder.setTitle("登录失败");
						builder.setMessage("密码或用户名错误");
						builder.setPositiveButton("Ok", null);
						builder.show();
					}
				}
			}
		});
		// 设置密码文本框转 *
		et_pass.setTransformationMethod(new TextChanger());
	}

	/**
	 * 时间线程
	 */
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (msg.what % 2 == 0) {
				// 隐藏
				tv_login_text.setVisibility(View.INVISIBLE);
			} else {
				// 显示
				tv_login_text.setVisibility(View.VISIBLE);
			}
			// 设置时间
			tv_login_time.setText(AppConfig.time);
			handler.postDelayed(timeRunnable, 1000);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			number++;
			Message msg = handler.obtainMessage();
			msg.what = number;
			handler.sendMessage(msg);
		}
	};

	/**
	 * 绑定控件
	 */
	private void initView() {
		// TODO Auto-generated method stub
		sharedPreferences = getSharedPreferences("rember", MODE_PRIVATE);
		btn_login = (Button) findViewById(R.id.btn_login);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_port = (EditText) findViewById(R.id.et_port);
		et_user = (EditText) findViewById(R.id.et_user);
		tv_login_text = (TextView) findViewById(R.id.tv_login_ojbk);
		tv_login_time = (TextView) findViewById(R.id.tv_login_time);
	}
}
