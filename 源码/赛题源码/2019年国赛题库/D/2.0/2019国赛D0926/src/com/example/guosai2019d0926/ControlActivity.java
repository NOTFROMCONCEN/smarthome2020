package com.example.guosai2019d0926;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ToggleButton;

public class ControlActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_control);
	}

	public void con_back(View view) {
		// TODO Auto-generated method stub
		finish();
	}

	public void tg_fan(View view) {
		// TODO Auto-generated method stub
		if (((ToggleButton) view).isChecked()) {
			ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL,
					ConstantUtil.OPEN);
		} else {
			ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL,
					ConstantUtil.CLOSE);
		}
	}

	public void tg_door(View view) {
		// TODO Auto-generated method stub
		if (((ToggleButton) view).isChecked()) {
			ControlUtils.control(ConstantUtil.RFID_Open_Door,
					ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
		} else {
		}
	}

	public void tg_warm(View view) {
		// TODO Auto-generated method stub
		if (((ToggleButton) view).isChecked()) {
			ControlUtils.control(ConstantUtil.WarningLight,
					ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
		} else {
			ControlUtils.control(ConstantUtil.WarningLight,
					ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
		}
	}

	public void tg_lamp(View view) {
		// TODO Auto-generated method stub
		if (((ToggleButton) view).isChecked()) {
			ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL,
					ConstantUtil.OPEN);
		} else {
			ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL,
					ConstantUtil.CLOSE);
		}
	}

	public void cur_open(View view) {
		// TODO Auto-generated method stub
		ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_1,
				ConstantUtil.OPEN);
	}

	public void cur_stop(View view) {
		// TODO Auto-generated method stub
		ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_3,
				ConstantUtil.OPEN);
	}

	public void cur_cls(View view) {
		// TODO Auto-generated method stub
		ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_2,
				ConstantUtil.OPEN);
	}

	public void btn_kt_open(View view) {
		// TODO Auto-generated method stub
		ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "1",
				ConstantUtil.OPEN);
	}

	public void btn_kt_play(View view) {
		// TODO Auto-generated method stub
		ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "1",
				ConstantUtil.OPEN);
	}

	public void btn_tv_open(View view) {
		// TODO Auto-generated method stub
		ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "5",
				ConstantUtil.OPEN);
	}

	public void btn_tv_play(View view) {
		// TODO Auto-generated method stub
		ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "5",
				ConstantUtil.OPEN);
	}

	public void btn_dvd_open(View view) {
		// TODO Auto-generated method stub
		ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "8",
				ConstantUtil.OPEN);
	}

	public void btn_dvd_play(View view) {
		// TODO Auto-generated method stub
		ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "8",
				ConstantUtil.OPEN);
	}
}
