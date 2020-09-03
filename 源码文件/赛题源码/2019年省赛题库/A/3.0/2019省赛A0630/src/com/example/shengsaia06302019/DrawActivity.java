package com.example.shengsaia06302019;

import com.example.shengsaia06302019.tools.AppTools;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;


public class DrawActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_draw);
		setTitle("温度趋势");// 设置ActionBar的title文本
		((TextView) findViewById(R.id.tv_draw_roomnumber)).setText("房间号："
				+ AppTools.room_number);// 设置房间号
	}
}
