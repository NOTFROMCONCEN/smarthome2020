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
 * @Todo TODO ��¼
 * @package_name com.example.shengsai07012019
 * @project_name 2019ʡ��0701
 * @file_name MainActivity.java
 * @�ҵĲ��� https://naiyouhuameitang.club/
 */
public class MainActivity extends Activity {

	// sharedPreferences�洢
	private SharedPreferences sharedPreferences;
	// ��¼��ť
	private Button btn_login;
	// �ı���
	private EditText et_user, et_pass, et_code_pass;
	// ��ס���븴ѡ��
	private CheckBox cb_rember;
	// ����һ�����ı�
	private TextView tv_code_update;
	// ������ʾ��֤���ͼƬ
	private ImageView iv_show_code;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();// �󶨿ؼ�
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
					DiyToast.showToast(getApplicationContext(), "�û�������Ϊ��");
				} else if (et_pass.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "���벻��Ϊ��");
				} else if (et_code_pass.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "��֤�벻��Ϊ��");
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
									"��֤���������");
						}
					} else {
						DiyToast.showToast(getApplicationContext(),
								"�û����������������");
					}
				}
			}
		});
	}

	/**
	 * �󶨿ؼ�
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
