package com.example.guosai2019b0924.room;

import com.example.guosai2019b0924.R;
import com.example.guosai2019b0924.R.layout;
import com.example.guosai2019b0924.R.menu;
import com.example.guosai2019b0924.tools.AppConfig;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

/**
 * 温度趋势界面
 * 
 * @author 10976
 * 
 */
public class TempActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_temp);
		// 设置房间号
		((TextView) findViewById(R.id.tv_draw_roomnumber)).setText("房间号："
				+ AppConfig.room_number);
	}
}
