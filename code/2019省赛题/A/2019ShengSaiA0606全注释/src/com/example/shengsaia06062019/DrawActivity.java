package com.example.shengsaia06062019;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

/**
 * @author Administrator
 * @year 2020
 * @Todo TODO 绘制折线图
 * @package_name com.example.shengsaia06062019
 * @project_name 2019ShengSaiA0606
 * @file_name DrawActivity.java
 * @我的博客 https://naiyouhuameitang.club/
 */
public class DrawActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_draw);
		setTitle("温度趋势");// 设置ActionBar的title文本
		((TextView) findViewById(R.id.tv_draw_roomnumber)).setText("房间号："
				+ AppConfig.room_number);// 设置房间号
	}
}
