package com.example.shengsaia06302019;

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

import com.example.shengsaia06302019.tools.AppTools;
import com.example.shengsaia06302019.tools.DiyToast;
import com.example.shengsaia06302019.tools.MySQL;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO 登录
 * @package_name com.example.shengsaia06302019
 * @project_name 2019省赛A0630
 * @file_name MainActivity.java
 * @我的博客 https://naiyouhuameitang.club/
 */
public class MainActivity extends Activity {
	private Button btn_login;// 登录按钮
	private EditText et_ip;// IP文本框
	private SeekBar sk_1;// 滑动栏
	private SharedPreferences sharedPreferences;// sharedPreferences存储
	// 数据库
	private MySQL mySQL;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		/**
		 * 绑定控件
		 */
		initView();
		// 设置“记住IP地址”
		et_ip.setText(sharedPreferences.getString("ip", null));
		// 设置最大进度
		sk_1.setMax(100);
		// 设置默认进度
		sk_1.setProgress(0);
		sk_1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				if (seekBar.getProgress() != 100) {
					DiyToast.showToast(getApplicationContext(), "请完成滑动验证");
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
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_ip.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "IP非法");
				} else {
					if (sk_1.getProgress() > 99) {
						sharedPreferences.edit()
								.putString("ip", et_ip.getText().toString())
								.commit();
						AppTools.IP = et_ip.getText().toString();
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

	/**
	 * 绑定控件
	 */
	private void initView() {
		// TODO Auto-generated method stub
		btn_login = (Button) findViewById(R.id.btn_login);
		et_ip = (EditText) findViewById(R.id.et_ip);
		sk_1 = (SeekBar) findViewById(R.id.seekBar1);
		sharedPreferences = getSharedPreferences("rember", MODE_PRIVATE);
		// 初始化数据库
		mySQL = new MySQL(getApplicationContext(), "info.db", null, 2);
		db = mySQL.getWritableDatabase();
	}
}
