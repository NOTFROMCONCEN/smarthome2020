package com.example.guosaia2019a0923;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.bizideal.smarthome.socket.SocketClient;
import com.example.guosaia2019a0923.tools.DiyToast;

public class LoginActivity extends Activity {

	private EditText et_ip;
	private SeekBar sk_1;
	private SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		et_ip = (EditText) findViewById(R.id.et_ip);
		sk_1 = (SeekBar) findViewById(R.id.seekBar1);
		sharedPreferences = getSharedPreferences("rember", MODE_PRIVATE);
		et_ip.setText(sharedPreferences.getString("ip", null));
		sk_1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				if (sk_1.getProgress() != 100) {
					sk_1.setProgress(0);
					DiyToast.showToast(getApplicationContext(), "请完成滑动验证");
				} else {
					if (et_ip.getText().toString().isEmpty()) {
						sk_1.setProgress(0);
						DiyToast.showToast(getApplicationContext(), "请输入IP地址");
					} else {
						SocketClient.ip = et_ip.getText().toString();
						sharedPreferences.edit()
								.putString("ip", et_ip.getText().toString())
								.commit();
						startActivity(new Intent(getApplicationContext(),
								IndexActivity.class));
						finish();
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
	}

	public void login(View view) {
		if (et_ip.getText().toString().isEmpty()) {
			DiyToast.showToast(getApplicationContext(), "请输入IP地址");
		} else {
			if (sk_1.getProgress() == 100) {
				SocketClient.ip = et_ip.getText().toString();
				sharedPreferences.edit()
						.putString("ip", et_ip.getText().toString()).commit();
				startActivity(new Intent(getApplicationContext(),
						IndexActivity.class));
				finish();
			} else {
				DiyToast.showToast(getApplicationContext(), "请完成滑动验证");
			}
		}
	}
}
