package com.example.shengsai07012019;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shengsai07012019.tools.DiyToast;
import com.example.shengsai07012019.view.CodeView;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO 登录
 * @package_name com.example.shengsai07012019
 * @project_name 2019省赛0701
 * @file_name MainActivity.java
 * @我的博客 https://naiyouhuameitang.club/
 */
public class MainActivity extends Activity {

	// sharedPreferences存储
	private SharedPreferences sharedPreferences;
	// 登录按钮
	private Button btn_login;
	// 文本框
	private EditText et_user, et_pass, et_code_pass;
	// 记住密码复选框
	private CheckBox cb_rember;
	// “换一个”文本
	private TextView tv_code_update;
	// 用于显示验证码的图片
	private ImageView iv_show_code;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();// 绑定控件
		iv_show_code.setImageBitmap(CodeView.createBitmap());
		et_code_pass.setText(CodeView.code);
		if (sharedPreferences.getBoolean("rember", false) == true) {
			et_user.setText(sharedPreferences.getString("user", null));
			et_pass.setText(sharedPreferences.getString("pass", null));
			cb_rember.setChecked(true);
		}
		tv_code_update.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				iv_show_code.setImageBitmap(CodeView.createBitmap());
				et_code_pass.setText(CodeView.code);
			}
		});
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_user.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "用户名不能为空");
				} else if (et_pass.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "密码不能为空");
				} else if (et_code_pass.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "验证码不能为空");
				} else {
					if (et_user.getText().toString().equals("bizideal")
							&& et_pass.getText().toString().equals("123456")) {
						if (et_code_pass.getText().toString()
								.equalsIgnoreCase(CodeView.code)) {
							if (cb_rember.isChecked()) {
								sharedPreferences
										.edit()
										.putBoolean("rember", true)
										.putString("user",
												et_user.getText().toString())
										.putString("pass",
												et_pass.getText().toString())
										.commit();
							} else {
								sharedPreferences
										.edit()
										.putBoolean("rember", true)
										.putString("user",
												et_user.getText().toString())
										.putString("pass",
												et_pass.getText().toString())
										.commit();
							}
							startActivity(new Intent(getApplicationContext(),
									IndexActivity.class));
							finish();
						} else {
							DiyToast.showToast(getApplicationContext(),
									"验证码输入错误");
						}
					} else {
						DiyToast.showToast(getApplicationContext(),
								"用户名或密码输入错误");
					}
				}
			}
		});
	}

	/**
	 * 绑定控件
	 */
	private void initView() {
		// TODO Auto-generated method stub
		btn_login = (Button) findViewById(R.id.btn_login);
		et_code_pass = (EditText) findViewById(R.id.et_repass);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_user = (EditText) findViewById(R.id.et_user);
		tv_code_update = (TextView) findViewById(R.id.tv_repass_update);
		iv_show_code = (ImageView) findViewById(R.id.im_repass_show);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
		sharedPreferences = getSharedPreferences("rember", MODE_PRIVATE);
	}
}
