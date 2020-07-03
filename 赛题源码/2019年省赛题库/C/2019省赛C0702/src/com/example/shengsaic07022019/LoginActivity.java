package com.example.shengsaic07022019;

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

import com.example.shengsaic07022019.tools.AppTools;
import com.example.shengsaic07022019.tools.DiyToast;

public class LoginActivity extends Activity {

	private Button btn_login;
	private EditText et_ip;
	private SeekBar sk_1;
	private SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		setTitle("");
		initView();
		et_ip.setText(sharedPreferences.getString("ip", null));
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_ip.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "请输入IP地址");
				} else {
					if (sk_1.getProgress() == 100) {
						sharedPreferences.edit()
								.putString("ip", et_ip.getText().toString())
								.commit();
						AppTools.IP = et_ip.getText().toString();
						startActivity(new Intent(getApplicationContext(),
								IndexActivity.class));
						finish();
					} else {
						DiyToast.showToast(getApplicationContext(), "请完成滑动解锁");
					}
				}
			}
		});
		sk_1.setMax(100);
		sk_1.setProgress(0);
		sk_1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				if (seekBar.getProgress() == 100) {
					if (et_ip.getText().toString().isEmpty()) {
						DiyToast.showToast(getApplicationContext(), "请输入IP地址");
						sk_1.setProgress(0);
					} else {
						sharedPreferences.edit()
								.putString("ip", et_ip.getText().toString())
								.commit();
						AppTools.IP = et_ip.getText().toString();
						startActivity(new Intent(getApplicationContext(),
								IndexActivity.class));
						finish();
					}
				} else {
					DiyToast.showToast(getApplicationContext(), "请完成滑动解锁");
					sk_1.setProgress(0);
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
	}

	private void initView() {
		// TODO Auto-generated method stub
		btn_login = (Button) findViewById(R.id.btn_login);
		et_ip = (EditText) findViewById(R.id.et_ip);
		sk_1 = (SeekBar) findViewById(R.id.seekBar1);
		sharedPreferences = getSharedPreferences("rember", MODE_PRIVATE);
	}
}
