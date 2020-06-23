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
 * @Todo TODO ��¼
 * @package_name com.example.shengsai06202019
 * @project_name 2019ʡ��0620
 * @file_name LoginActivity.java
 * @�ҵĲ��� https://naiyouhuameitang.club/
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
		initView();// �󶨿ؼ�
		handler.post(timeRunnable);// �����߳�
		// ���sharedPreferences��Ϊ�գ����������ı�����
		et_ip.setText(sharedPreferences.getString("ip", null));
		et_pass.setText(sharedPreferences.getString("pass", null));
		et_port.setText(sharedPreferences.getString("port", null));
		et_user.setText(sharedPreferences.getString("user", null));
		// ��¼��ť
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// �ж��ı����ǲ��ǿ�
				if (et_ip.getText().toString().isEmpty()
						|| et_pass.getText().toString().isEmpty()
						|| et_port.getText().toString().isEmpty()
						|| et_user.getText().toString().isEmpty()) {
					// ������ʾ
					DiyToast.showToast(getApplicationContext(), "�����пհ���");
				} else {
					// �ж��û����������Ƿ���ȷ
					if (et_user.getText().toString().equals("bizideal")
							&& et_pass.getText().toString().equals("123456")) {
						// ���浽sharedPreferences
						sharedPreferences
								.edit()
								.putString("ip", et_ip.getText().toString())
								.putString("pass", et_pass.getText().toString())
								.putString("port", et_port.getText().toString())
								.putString("user", et_user.getText().toString())
								.commit();
						// ��ת
						startActivity(new Intent(getApplicationContext(),
								UnLockActivity.class));
						finish();
					} else {
						// ��¼ʧ�ܵ�����ʾ
						AlertDialog.Builder builder = new AlertDialog.Builder(
								LoginActivity.this);
						builder.setTitle("��¼ʧ��");
						builder.setMessage("������û�������");
						builder.setPositiveButton("Ok", null);
						builder.show();
					}
				}
			}
		});
		// ���������ı���ת *
		et_pass.setTransformationMethod(new TextChanger());
	}

	/**
	 * ʱ���߳�
	 */
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (msg.what % 2 == 0) {
				// ����
				tv_login_text.setVisibility(View.INVISIBLE);
			} else {
				// ��ʾ
				tv_login_text.setVisibility(View.VISIBLE);
			}
			// ����ʱ��
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
	 * �󶨿ؼ�
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
