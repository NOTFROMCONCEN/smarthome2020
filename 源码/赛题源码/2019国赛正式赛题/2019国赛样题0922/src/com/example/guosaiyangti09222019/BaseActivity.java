package com.example.guosaiyangti09222019;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.example.guosaiyangti09222019.sql.tools.AppConfig;

public class BaseActivity extends Activity {

	private ToggleButton tg_warm, tg_cur, tg_lamp, tg_door, tg_fan;
	private EditText et_temp, et_hum, et_smo, et_press, et_gas, et_ill, et_pm,
			et_co, et_per;
	private Button btn_1, btn_2, btn_3;
	private LinearLayout ll_link;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base);
		initView();
		ll_link.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getApplicationContext(),
						LinkActivity.class));
				return true;
			}
		});
		btn_1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
						ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
			}
		});
		btn_3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
						ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
			}
		});
		btn_2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
						ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
			}
		});
		tg_cur.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.Curtain,
							ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
				} else {
					ControlUtils.control(ConstantUtil.Curtain,
							ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
				}
			}
		});
		tg_door.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.RFID_Open_Door,
							ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
				} else {
				}
			}
		});
		tg_fan.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.Fan,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				} else {
					ControlUtils.control(ConstantUtil.Fan,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
		});
		tg_lamp.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					
				} else {
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
		});
		tg_warm.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				} else {
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
		});
		AppConfig.web_server(BaseActivity.this);
		handler.post(timeRunnable);
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			et_co.setText(String.valueOf(AppConfig.co));
			et_gas.setText(String.valueOf(AppConfig.gas));
			et_hum.setText(String.valueOf(AppConfig.hum));
			et_ill.setText(String.valueOf(AppConfig.ill));
			et_per.setText(String.valueOf(AppConfig.per));
			et_pm.setText(String.valueOf(AppConfig.pm));
			et_press.setText(String.valueOf(AppConfig.press));
			et_smo.setText(String.valueOf(AppConfig.smo));
			et_temp.setText(String.valueOf(AppConfig.temp));
			handler.postDelayed(timeRunnable, 500);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Message msg = handler.obtainMessage();
			handler.sendMessage(msg);
		}
	};

	private void initView() {
		// TODO Auto-generated method stub
		ll_link = (LinearLayout) findViewById(R.id.line_intent);
		tg_cur = (ToggleButton) findViewById(R.id.tg_cur);
		tg_door = (ToggleButton) findViewById(R.id.tg_door);
		tg_fan = (ToggleButton) findViewById(R.id.tg_fan);
		tg_lamp = (ToggleButton) findViewById(R.id.tg_lamp);
		tg_warm = (ToggleButton) findViewById(R.id.tg_warm);
		et_co = (EditText) findViewById(R.id.et_co);
		et_gas = (EditText) findViewById(R.id.et_gas);
		et_hum = (EditText) findViewById(R.id.et_hum);
		et_ill = (EditText) findViewById(R.id.et_ill);
		et_per = (EditText) findViewById(R.id.et_per);
		et_press = (EditText) findViewById(R.id.et_press);
		et_pm = (EditText) findViewById(R.id.et_pm);
		et_smo = (EditText) findViewById(R.id.et_smo);
		et_temp = (EditText) findViewById(R.id.et_temp);
		btn_1 = (Button) findViewById(R.id.btn_td_1);
		btn_2 = (Button) findViewById(R.id.btn_td_2);
		btn_3 = (Button) findViewById(R.id.btn_td_3);
	}
}
