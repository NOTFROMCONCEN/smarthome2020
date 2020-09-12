package com.example.shengsai2018b0912;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.shengsai2018b0912.tools.AppConfig;

public class MenuActivity extends Activity {

	private ImageView iv_base, iv_mode, iv_link, iv_op;
	private LinearLayout ll_base, ll_mode, ll_link, ll_op;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		initView();
		AppConfig.web_start(MenuActivity.this);
		iv_base.setVisibility(View.VISIBLE);
		iv_link.setVisibility(View.INVISIBLE);
		iv_mode.setVisibility(View.INVISIBLE);
		iv_op.setVisibility(View.INVISIBLE);
		ll_base.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				iv_base.setVisibility(View.VISIBLE);
				iv_link.setVisibility(View.INVISIBLE);
				iv_mode.setVisibility(View.INVISIBLE);
				iv_op.setVisibility(View.INVISIBLE);
				startActivity(new Intent(getApplicationContext(),
						BaseActivity.class));
			}
		});
		ll_link.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				iv_base.setVisibility(View.INVISIBLE);
				iv_link.setVisibility(View.VISIBLE);
				iv_mode.setVisibility(View.INVISIBLE);
				iv_op.setVisibility(View.INVISIBLE);
				startActivity(new Intent(getApplicationContext(),
						LinkActivity.class));
			}
		});
		ll_mode.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				iv_base.setVisibility(View.INVISIBLE);
				iv_link.setVisibility(View.INVISIBLE);
				iv_mode.setVisibility(View.VISIBLE);
				iv_op.setVisibility(View.INVISIBLE);
				startActivity(new Intent(getApplicationContext(),
						ModeActivity.class));
			}
		});
		ll_op.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				iv_base.setVisibility(View.INVISIBLE);
				iv_link.setVisibility(View.INVISIBLE);
				iv_mode.setVisibility(View.INVISIBLE);
				iv_op.setVisibility(View.VISIBLE);
				startActivity(new Intent(getApplicationContext(),
						OpActivity.class));
			}
		});
	}

	private void initView() {
		// TODO Auto-generated method stub
		iv_base = (ImageView) findViewById(R.id.iv_base);
		iv_link = (ImageView) findViewById(R.id.iv_link);
		iv_mode = (ImageView) findViewById(R.id.iv_mode);
		iv_op = (ImageView) findViewById(R.id.iv_op);
		ll_base = (LinearLayout) findViewById(R.id.line_base);
		ll_link = (LinearLayout) findViewById(R.id.line_link);
		ll_mode = (LinearLayout) findViewById(R.id.line_mode);
		ll_op = (LinearLayout) findViewById(R.id.line_op);
	}
}
