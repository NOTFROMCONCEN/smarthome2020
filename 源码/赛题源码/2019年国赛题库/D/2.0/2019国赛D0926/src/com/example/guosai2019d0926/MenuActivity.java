package com.example.guosai2019d0926;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.example.guosai2019d0926.tools.AppConfig;

public class MenuActivity extends Activity {
	private ImageView iv_base;// 数据采集
	private ImageView iv_link;// 情景
	private ImageView iv_control;// 控制

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		iv_base = (ImageView) findViewById(R.id.iv_chuanganqi);
		iv_control = (ImageView) findViewById(R.id.iv_shebeikongzhi);
		iv_link = (ImageView) findViewById(R.id.iv_qingjingmoshi);
		AppConfig.web_server(MenuActivity.this);
		iv_base.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getApplicationContext(),
						BaseActivity.class));
			}
		});
		iv_control.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getApplicationContext(),
						ControlActivity.class));
			}
		});
		iv_link.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getApplicationContext(),
						LinkActivity.class));
			}
		});
	}
}