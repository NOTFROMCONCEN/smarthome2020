package com.example.shengsai2018b0912;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ToggleButton;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.example.shengsai2018b0912.tools.AppConfig;

public class BaseActivity extends Activity {

	private EditText et_temp, et_hum, et_press, et_ill, et_pm, et_per, et_smo,
			et_gas, et_co;
	private ToggleButton tg_lamp1, tg_lamp2, tg_warm, tg_door, tg_tv, tg_kt,
			tg_dvd, tg_cur, tg_fan;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base);
		initView();
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
							ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
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
		tg_dvd.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "3",
							ConstantUtil.OPEN);
				} else {
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "3",
							ConstantUtil.OPEN);
				}
			}
		});
		tg_fan.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.Fan, "7",
							ConstantUtil.OPEN);
				} else {
					ControlUtils.control(ConstantUtil.Fan, "7",
							ConstantUtil.CLOSE);
				}
			}
		});
		tg_kt.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "2",
							ConstantUtil.OPEN);
				} else {
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "2",
							ConstantUtil.OPEN);
				}
			}
		});
		tg_lamp1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
				} else {
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_1, ConstantUtil.CLOSE);
				}
			}
		});
		tg_lamp2.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
				} else {
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_2, ConstantUtil.CLOSE);
				}
			}
		});
		tg_tv.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "1",
							ConstantUtil.OPEN);
				} else {
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "1",
							ConstantUtil.OPEN);
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
		handler.post(timeRunnable);
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			et_co.setText(String.valueOf(AppConfig.co));
			et_gas.setText(String.valueOf(AppConfig.gas));
			et_hum.setText(String.valueOf(AppConfig.hum));
			et_ill.setText(String.valueOf(AppConfig.ill));
			et_pm.setText(String.valueOf(AppConfig.pm));
			et_press.setText(String.valueOf(AppConfig.press));
			et_smo.setText(String.valueOf(AppConfig.smo));
			et_temp.setText(String.valueOf(AppConfig.temp));
			if (AppConfig.per == 1) {
				et_per.setText("有人");
			} else {
				et_per.setText("无人");
			}
			handler.postDelayed(timeRunnable, 5000);
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
		et_co = (EditText) findViewById(R.id.et_co);
		et_gas = (EditText) findViewById(R.id.et_gas);
		et_hum = (EditText) findViewById(R.id.et_hum);
		et_ill = (EditText) findViewById(R.id.et_ill);
		et_per = (EditText) findViewById(R.id.et_per);
		et_pm = (EditText) findViewById(R.id.et_pm);
		et_press = (EditText) findViewById(R.id.et_press);
		et_smo = (EditText) findViewById(R.id.et_smo);
		et_temp = (EditText) findViewById(R.id.et_temp);
		tg_door = (ToggleButton) findViewById(R.id.tg_door);
		tg_dvd = (ToggleButton) findViewById(R.id.tg_dvd);
		tg_kt = (ToggleButton) findViewById(R.id.tg_kt);
		tg_lamp1 = (ToggleButton) findViewById(R.id.tg_lamp1);
		tg_lamp2 = (ToggleButton) findViewById(R.id.tg_lamp2);
		tg_tv = (ToggleButton) findViewById(R.id.tg_tv);
		tg_warm = (ToggleButton) findViewById(R.id.tg_warm);
		tg_cur = (ToggleButton) findViewById(R.id.tg_cur);
		tg_fan = (ToggleButton) findViewById(R.id.tg_fan);
	}
}
