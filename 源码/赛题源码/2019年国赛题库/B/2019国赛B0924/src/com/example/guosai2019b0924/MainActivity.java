package com.example.guosai2019b0924;

import com.bizideal.smarthome.socket.SocketClient;
import com.example.guosai2019b0924.tools.DiyToast;
import com.example.guosai2019b0924.tools.MyDataBaseHelper;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

/**
 * APP主入口、解锁登录界面
 * 
 * @author 10976
 * 
 */
public class MainActivity extends Activity {

	private SeekBar sk_1;
	private EditText et_ip;
	private SharedPreferences sharedPreferences;
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		sk_1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				if (sk_1.getProgress() != 100) {
					sk_1.setProgress(0);
					DiyToast.showToast(getApplicationContext(), "请完成滑动验证");
				} else {
					if (et_ip.getText().toString().isEmpty()) {
						DiyToast.showToast(getApplicationContext(), "IP非法");
					} else {
						sharedPreferences.edit()
								.putString("ip", et_ip.getText().toString())
								.commit();
						SocketClient.ip = et_ip.getText().toString();
						startActivity(new Intent(getApplicationContext(),
								LoadingActivity.class));
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

	private void initView() {
		// TODO Auto-generated method stub
		sk_1 = (SeekBar) findViewById(R.id.seekBar1);
		et_ip = (EditText) findViewById(R.id.et_ip);
		sharedPreferences = getSharedPreferences("rember", MODE_PRIVATE);
		et_ip.setText(sharedPreferences.getString("ip", null));
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
	}

	public void login(View view) {
		// TODO Auto-generated method stub
		if (et_ip.getText().toString().isEmpty()) {
			DiyToast.showToast(getApplicationContext(), "IP非法");
		} else {
			if (sk_1.getProgress() != 100) {
				sk_1.setProgress(0);
				DiyToast.showToast(getApplicationContext(), "请完成滑动验证");
			}
		}
	}
}
