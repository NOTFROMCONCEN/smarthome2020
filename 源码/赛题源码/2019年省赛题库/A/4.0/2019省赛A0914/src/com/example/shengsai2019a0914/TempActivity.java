package com.example.shengsai2019a0914;

import com.example.shengsai2019a0914.tools.AppConfig;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class TempActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_temp);
		setTitle("温度趋势");
		((TextView) findViewById(R.id.tv_draw_roomnumber)).setText("房间号："
				+ AppConfig.room_number);
	}
}
