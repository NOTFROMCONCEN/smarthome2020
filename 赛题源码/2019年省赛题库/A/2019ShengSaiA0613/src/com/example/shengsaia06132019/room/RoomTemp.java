package com.example.shengsaia06132019.room;

import com.example.shengsaia06132019.R;
import com.example.shengsaia06132019.R.layout;
import com.example.shengsaia06132019.R.menu;
import com.example.shengsaia06132019.tools.AppConfig;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO 温度趋势
 * @package_name com.example.shengsaia06132019.room
 * @project_name 2019ShengSaiA0613
 * @file_name RoomTemp.java
 * @我的博客 https://naiyouhuameitang.club/
 */
public class RoomTemp extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_room_temp);
		setTitle("温度趋势");// 设置title
		// 设置房间号
		((TextView) findViewById(R.id.tv_temp_roommnumber)).setText("房间号："
				+ AppConfig.roomnumber);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_room_temp, menu);
		return true;
	}

}
