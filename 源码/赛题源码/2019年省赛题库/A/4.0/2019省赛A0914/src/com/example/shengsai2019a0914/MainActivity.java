package com.example.shengsai2019a0914;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.bizideal.smarthome.socket.SocketClient;
import com.example.shengsai2019a0914.tools.AppConfig;
import com.example.shengsai2019a0914.tools.DiyToast;
import com.example.shengsai2019a0914.tools.MyDataBaseHelper;

public class MainActivity extends Activity {

	private SeekBar sk_1;
	private Button btn_login;
	private EditText et_ip;
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;
	private SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		setTitle("");
		sharedPreferences = getSharedPreferences("rember", MODE_PRIVATE);
		et_ip.setText(sharedPreferences.getString("ip", null));
		sk_1.setMax(100);
		sk_1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				if (seekBar.getProgress() != 100) {
					sk_1.setProgress(0);
					DiyToast.showToast(getApplicationContext(), "请完成滑动验证");
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
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_ip.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "IP非法");
				} else {
					if (sk_1.getProgress() == 100) {
						SocketClient.ip = et_ip.getText().toString();
						sharedPreferences.edit()
								.putString("ip", et_ip.getText().toString())
								.commit();
						AppConfig.ip = et_ip.getText().toString();
						startActivity(new Intent(getApplicationContext(),
								LoadingActivity.class));
						finish();
					} else {
						DiyToast.showToast(getApplicationContext(), "请完成滑动验证");
					}
				}
			}
		});
	}

	private void initView() {
		// TODO Auto-generated method stub
		sk_1 = (SeekBar) findViewById(R.id.seekBar1);
		btn_login = (Button) findViewById(R.id.btn_login);
		et_ip = (EditText) findViewById(R.id.et_ip);
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
	}
}
