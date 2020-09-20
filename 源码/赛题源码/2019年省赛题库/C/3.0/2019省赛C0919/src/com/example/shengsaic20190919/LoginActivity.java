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
 * @Todo TODO 登录界面
 * @package_name com.example.shengsaic20190919
 * @project_name 2019省赛C0919
 * @file_name LoginActivity.java
 * @我的博客 https://naiyouhuameitang.club/
 */
public class LoginActivity extends Activity {

	private Button btn_login;// 登录按钮
	private EditText et_ip;// IP文本框
	private SeekBar sk_1;// SeekBar滑动条

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();// 绑定控件
		// 设置最高值
		sk_1.setMax(100);
		// 设置onseekbar滑动事件
		sk_1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				// 判断当前进度
				if (sk_1.getProgress() != 100) {
					sk_1.setProgress(0);
					DiyToast.showToast(getApplicationContext(), "请完成滑动验证");
				} else {
					if (et_ip.getText().toString().isEmpty()) {
						DiyToast.showToast(getApplicationContext(), "请输入IP地址");
						sk_1.setProgress(0);
					} else {
						if (sk_1.getProgress() != 100) {
							DiyToast.showToast(getApplicationContext(),
									"请完成滑动验证");
						} else {
							// 记录IP，登录
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
		// 登录按钮
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_ip.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "请输入IP地址");
				} else {
					if (sk_1.getProgress() != 100) {
						DiyToast.showToast(getApplicationContext(), "请完成滑动验证");
					} else {
						// 记录IP，登录
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
	 * 绑定控件
	 */
	private void initView() {
		// TODO Auto-generated method stub
		btn_login = (Button) findViewById(R.id.btn_login);
		et_ip = (EditText) findViewById(R.id.et_ip);
		sk_1 = (SeekBar) findViewById(R.id.seekBar1);
	}
}
