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
		setTitle("�¶�����");// ����ActionBar��title�ı�
		((TextView) findViewById(R.id.tv_draw_roomnumber)).setText("����ţ�"
				+ AppTools.room_number);// ���÷����
	}
}
