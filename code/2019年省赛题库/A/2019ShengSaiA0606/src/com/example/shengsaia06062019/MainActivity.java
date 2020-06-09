package com.example.shengsaia06062019;

import com.bizideal.smarthome.socket.SocketClient;
import com.example.shengsaia06062019.sql.MySQL;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO 登录界面
 * @package_name com.example.shengsaia06062019
 * @project_name 2019ShengSaiA0606
 * @file_name MainActivity.java
 * @我的博客 https://naiyouhuameitang.club/
 */
public class MainActivity extends Activity {
	private EditText et_ip;// IP地址文本框
	private SeekBar sk_1;// SeekBar
	private Button btn_login;// 登录按钮
	// 数据库
	private MySQL mySQL;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();// 绑定控件
		setTitle("");// 设置ActionBar的title文本
		// 滑动
		sk_1.setProgress(0);// 设置默认进度
		sk_1.setMax(101);// 设置最大进度
		// 设置SeekBar的OnSeekBar事件
		sk_1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {// 停止滑动时
				// TODO Auto-generated method stub
				if (seekBar.getProgress() < 100) {
					DiyToast.showToast(getApplicationContext(), "请滑动完成验证");
					sk_1.setProgress(0);
				}
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {// 开始滑动时
				// TODO Auto-generated method stub
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {// 进度改变时
				// TODO Auto-generated method stub
			}
		});
		// 登录按钮
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_ip.getText().toString().isEmpty()) {// 如果IP地址为空
					DiyToast.showToast(getApplicationContext(), "IP地址非法");
				} else {
					if (sk_1.getProgress() < 100) {// 如果SeekBar进度小于100
						DiyToast.showToast(getApplicationContext(), "请滑动完成验证");
						sk_1.setProgress(0);
					} else {
						AppConfig.ip = et_ip.getText().toString();
						startActivity(new Intent(getApplicationContext(),
								LoadingActivity.class));
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
		sk_1 = (SeekBar) findViewById(R.id.seekBar1);
		et_ip = (EditText) findViewById(R.id.et_ip);
		// 初始化数据库
		mySQL = new MySQL(getApplicationContext(), "info.db", null, 2);
		db = mySQL.getWritableDatabase();
	}
}
