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
 * �¶����ƽ���
 * 
 * @author 10976
 * 
 */
public class TempActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_temp);
		// ���÷����
		((TextView) findViewById(R.id.tv_draw_roomnumber)).setText("����ţ�"
				+ AppConfig.room_number);
	}
}
