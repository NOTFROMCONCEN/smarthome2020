package com.example.shengsaia06062019;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO ��������ͼ
 * @package_name com.example.shengsaia06062019
 * @project_name 2019ShengSaiA0606
 * @file_name DrawActivity.java
 * @�ҵĲ��� https://naiyouhuameitang.club/
 */
public class DrawActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_draw);
		setTitle("�¶�����");// ����ActionBar��title�ı�
		((TextView) findViewById(R.id.tv_draw_roomnumber)).setText("����ţ�"
				+ AppConfig.room_number);// ���÷����
	}
}
