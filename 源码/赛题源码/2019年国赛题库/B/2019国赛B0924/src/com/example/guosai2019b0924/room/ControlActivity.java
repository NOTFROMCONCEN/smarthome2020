package com.example.guosai2019b0924.room;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.example.guosai2019b0924.R;
import com.example.guosai2019b0924.tools.AppConfig;

/**
 * 房间控制界面
 * 
 * @author 10976
 * 
 */
public class ControlActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_control);
		// 设置房间号
		((TextView) findViewById(R.id.tv_con_number)).setText("房间号："
				+ AppConfig.room_number);
	}

	// 报警灯
	public void warm(View view) {
		// TODO Auto-generated method stub
		if (((ToggleButton) view).isChecked()) {
			ControlUtils.control(ConstantUtil.WarningLight,
					ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
		} else {
			ControlUtils.control(ConstantUtil.WarningLight,
					ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
		}
	}

	// 射灯
	public void lamp(View view) {
		// TODO Auto-generated method stub
		if (((ToggleButton) view).isChecked()) {
			ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL,
					ConstantUtil.OPEN);
		} else {
			ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL,
					ConstantUtil.CLOSE);
		}
	}

	// 风扇
	public void fan(View view) {
		// TODO Auto-generated method stub
		if (((ToggleButton) view).isChecked()) {
			ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL,
					ConstantUtil.OPEN);
		} else {
			ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL,
					ConstantUtil.CLOSE);
		}
	}

	// 窗帘开
	public void open(View view) {
		// TODO Auto-generated method stub
		ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_1,
				ConstantUtil.OPEN);
	}

	// 窗帘关
	public void cls(View view) {
		// TODO Auto-generated method stub
		ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_2,
				ConstantUtil.OPEN);
	}

	// 窗帘停
	public void stop(View view) {
		// TODO Auto-generated method stub
		ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_3,
				ConstantUtil.OPEN);
	}

	// DVD
	public void dvd(View view) {
		// TODO Auto-generated method stub
		ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "8",
				ConstantUtil.OPEN);
	}

	// 电视
	public void tv(View view) {
		// TODO Auto-generated method stub
		ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "5",
				ConstantUtil.OPEN);
	}

	// 空调
	public void kt(View view) {
		// TODO Auto-generated method stub
		ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "1",
				ConstantUtil.OPEN);
	}

	// 门禁
	public void door(View view) {
		// TODO Auto-generated method stub
		ControlUtils.control(ConstantUtil.RFID_Open_Door,
				ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
	}
}
