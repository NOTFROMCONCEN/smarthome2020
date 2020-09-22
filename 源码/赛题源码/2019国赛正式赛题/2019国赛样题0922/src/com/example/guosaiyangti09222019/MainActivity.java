package com.example.guosaiyangti09222019;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.bizideal.smarthome.socket.SocketClient;
import com.example.guosaiyangti09222019.sql.MyDataBaseHelper;
import com.example.guosaiyangti09222019.sql.tools.DiyToast;

public class MainActivity extends Activity {

	private Button btn_login;
	private EditText et_port, et_ip;
	private SeekBar sk_1;
	private TextView tv_login_time;
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;
	private SharedPreferences sharedPreferences;
	private int login_number;
	private String login_time;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		getTimeInfo();
		btn_login.setVisibility(View.INVISIBLE);
		et_port.setText(sharedPreferences.getString("port", null));
		et_ip.setText(sharedPreferences.getString("ip", null));
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub\
				if (et_ip.getText().toString().isEmpty()
						|| et_port.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "不能有空白项");
				} else {
					login_number++;
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
							"HH:mm");
					login_time = simpleDateFormat.format(new Date());
					db.execSQL(
							"insert into login_time (login_number,login_time)values(?,?)",
							new String[] { String.valueOf(login_number),
									login_time });
					SocketClient.ip = et_ip.getText().toString();
					sharedPreferences.edit()
							.putString("port", et_port.getText().toString())
							.putString("ip", et_ip.getText().toString())
							.commit();
					startActivity(new Intent(getApplicationContext(),
							BaseActivity.class));
					finish();
				}
			}
		});
		sk_1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				if (seekBar.getProgress() > 68 && seekBar.getProgress() < 75) {
					btn_login.setVisibility(View.VISIBLE);
				} else {
					btn_login.setVisibility(View.INVISIBLE);
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
				if (seekBar.getProgress() > 68 && seekBar.getProgress() < 75) {
					btn_login.setVisibility(View.VISIBLE);
				} else {
					btn_login.setVisibility(View.INVISIBLE);
				}
			}
		});
	}

	private void getTimeInfo() {
		// TODO Auto-generated method stub
		Cursor cursor = db.rawQuery("select * from login_time", null);
		if (cursor.getCount() != 0) {
			cursor.moveToLast();
			if (cursor.getString(cursor.getColumnIndex("login_number")).equals(
					"0")) {
				tv_login_time.setVisibility(View.GONE);
			} else {
				login_number = Integer.valueOf(cursor.getString(cursor
						.getColumnIndex("login_number")));
				login_time = cursor.getString(cursor
						.getColumnIndex("login_time"));
				tv_login_time.setText("之前已有" + String.valueOf(login_number)
						+ "，上次登录时间为" + login_time);
			}
		}
	}

	private void initView() {
		// TODO Auto-generated method stub
		sharedPreferences = getSharedPreferences("rember", MODE_PRIVATE);
		sk_1 = (SeekBar) findViewById(R.id.seekBar1);
		tv_login_time = (TextView) findViewById(R.id.tv_login_number);
		btn_login = (Button) findViewById(R.id.btn_login);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_port = (EditText) findViewById(R.id.et_port);
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
	}
}
