package com.example.shengsaia06092019;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class DrawActivity extends Activity {

	private TextView tv_draw_roomnumber;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_draw);
		tv_draw_roomnumber = (TextView) findViewById(R.id.tv_draw_roomnumber);
		tv_draw_roomnumber.setText(AppConfig.room_number);
	}
}
