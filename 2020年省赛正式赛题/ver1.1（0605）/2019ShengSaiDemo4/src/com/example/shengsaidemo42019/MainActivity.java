package com.example.shengsaidemo42019;

import com.example.shengsaidemo42019.toast.DiyToast;

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

/**
 * @author Administrator
 * @year 2019
 * @Todo TODO 登录
 * @package_name com.example.shengsaidemo42019
 * @project_name 2019ShengSaiDemo4
 * @file_name MainActivity.java
 */
public class MainActivity extends Activity {
	private Button btn_login;
	private CheckBox cb_rember;
	private EditText et_user, et_pass, et_repass;
	private TextView tv_repass_update;
	private ImageView im_repass_show;
	private SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();// 绑定控件
		// 记住密码
		if (sharedPreferences != null) {
			if (sharedPreferences.getBoolean("rember", false) == true) {
				cb_rember.setChecked(true);
				et_pass.setText(sharedPreferences.getString("pass", null));
				et_user.setText(sharedPreferences.getString("user", null));
			}
		}
		// 更新验证码
		tv_repass_update.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				im_repass_show.setImageBitmap(CodeView.createRandomBitMap());
			}
		});
		// 登录
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_user.getText().toString().isEmpty()) {// 账号为空
					DiyToast.showToast(getApplicationContext(), "账户不能为空");
				} else if (et_pass.getText().toString().isEmpty()) {// 密码为空
					DiyToast.showToast(getApplicationContext(), "密码不能为空");
				} else if (et_repass.getText().toString().isEmpty()) {// 验证密码为空
					DiyToast.showToast(getApplicationContext(), "验证码不能为空");
				} else {
					if (et_pass.getText().toString().equals("123456")
							&& et_user.getText().toString().equals("bizideal")) {// 验证用户名和密码
						if (et_repass.getText().toString()
								.equals(CodeView.code)) {// 验证码是否正确
							startActivity(new Intent(getApplicationContext(),
									IndexActivity.class));// 登陆成功跳转
							// 记住密码
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
										.putBoolean("rember", false)
										.putString("user",
												et_user.getText().toString())
										.putString("pass",
												et_pass.getText().toString())
										.commit();
							}
						} else {
							DiyToast.showToast(getApplicationContext(), "验证码错误");
						}
					} else {
						DiyToast.showToast(getApplicationContext(), "用户名或密码错误");
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
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
		btn_login = (Button) findViewById(R.id.btn_login);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_repass = (EditText) findViewById(R.id.et_repass);
		et_user = (EditText) findViewById(R.id.et_user);
		tv_repass_update = (TextView) findViewById(R.id.tv_repass_update);
		im_repass_show = (ImageView) findViewById(R.id.im_repass_show);
		im_repass_show.setImageBitmap(CodeView.createRandomBitMap());// 创建验证码
		et_repass.setText(CodeView.code);// 设置验证码
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);// 初始化记住密码
	}
}
