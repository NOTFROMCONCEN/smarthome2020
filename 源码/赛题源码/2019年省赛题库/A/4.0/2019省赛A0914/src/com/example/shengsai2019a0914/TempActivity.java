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
		setTitle("�¶�����");
		((TextView) findViewById(R.id.tv_draw_roomnumber)).setText("����ţ�"
				+ AppConfig.room_number);
	}
}
