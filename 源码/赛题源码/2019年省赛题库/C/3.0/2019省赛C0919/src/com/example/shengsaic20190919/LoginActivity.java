package com.example.shengsaic20190919;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.bizideal.smarthome.socket.SocketClient;
import com.example.shengsaic20190919.toools.DiyToast;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO ��¼����
 * @package_name com.example.shengsaic20190919
 * @project_name 2019ʡ��C0919
 * @file_name LoginActivity.java
 * @�ҵĲ��� https://naiyouhuameitang.club/
 */
public class LoginActivity extends Activity {

	private Button btn_login;// ��¼��ť
	private EditText et_ip;// IP�ı���
	private SeekBar sk_1;// SeekBar������

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();// �󶨿ؼ�
		// �������ֵ
		sk_1.setMax(100);
		// ����onseekbar�����¼�
		sk_1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				// �жϵ�ǰ����
				if (sk_1.getProgress() != 100) {
					sk_1.setProgress(0);
					DiyToast.showToast(getApplicationContext(), "����ɻ�����֤");
				} else {
					if (et_ip.getText().toString().isEmpty()) {
						DiyToast.showToast(getApplicationContext(), "������IP��ַ");
						sk_1.setProgress(0);
					} else {
						if (sk_1.getProgress() != 100) {
							DiyToast.showToast(getApplicationContext(),
									"����ɻ�����֤");
						} else {
							// ��¼IP����¼
							SocketClient.ip = et_ip.getText().toString();
							startActivity(new Intent(getApplicationContext(),
									IndexActivity.class));
							finish();
						}
					}
				}
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub

			}
		});
		// ��¼��ť
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_ip.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "������IP��ַ");
				} else {
					if (sk_1.getProgress() != 100) {
						DiyToast.showToast(getApplicationContext(), "����ɻ�����֤");
					} else {
						// ��¼IP����¼
						SocketClient.ip = et_ip.getText().toString();
						startActivity(new Intent(getApplicationContext(),
								IndexActivity.class));
						finish();
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
		et_ip = (EditText) findViewById(R.id.et_ip);
		sk_1 = (SeekBar) findViewById(R.id.seekBar1);
	}
}
