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
 * @Todo TODO ��¼
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
		initView();// �󶨿ؼ�
		// ��ס����
		if (sharedPreferences != null) {
			if (sharedPreferences.getBoolean("rember", false) == true) {
				cb_rember.setChecked(true);
				et_pass.setText(sharedPreferences.getString("pass", null));
				et_user.setText(sharedPreferences.getString("user", null));
			}
		}
		// ������֤��
		tv_repass_update.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				im_repass_show.setImageBitmap(CodeView.createRandomBitMap());
			}
		});
		// ��¼
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_user.getText().toString().isEmpty()) {// �˺�Ϊ��
					DiyToast.showToast(getApplicationContext(), "�˻�����Ϊ��");
				} else if (et_pass.getText().toString().isEmpty()) {// ����Ϊ��
					DiyToast.showToast(getApplicationContext(), "���벻��Ϊ��");
				} else if (et_repass.getText().toString().isEmpty()) {// ��֤����Ϊ��
					DiyToast.showToast(getApplicationContext(), "��֤�벻��Ϊ��");
				} else {
					if (et_pass.getText().toString().equals("123456")
							&& et_user.getText().toString().equals("bizideal")) {// ��֤�û���������
						if (et_repass.getText().toString()
								.equals(CodeView.code)) {// ��֤���Ƿ���ȷ
							startActivity(new Intent(getApplicationContext(),
									IndexActivity.class));// ��½�ɹ���ת
							// ��ס����
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
							DiyToast.showToast(getApplicationContext(), "��֤�����");
						}
					} else {
						DiyToast.showToast(getApplicationContext(), "�û������������");
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
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
		btn_login = (Button) findViewById(R.id.btn_login);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_repass = (EditText) findViewById(R.id.et_repass);
		et_user = (EditText) findViewById(R.id.et_user);
		tv_repass_update = (TextView) findViewById(R.id.tv_repass_update);
		im_repass_show = (ImageView) findViewById(R.id.im_repass_show);
		im_repass_show.setImageBitmap(CodeView.createRandomBitMap());// ������֤��
		et_repass.setText(CodeView.code);// ������֤��
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);// ��ʼ����ס����
	}
}
