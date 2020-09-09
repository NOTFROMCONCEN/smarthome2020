package com.example.shengsai2018b0901.index;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;
import com.example.shengsai2018b0901.DiyToast;
import com.example.shengsai2018b0901.R;

public class IndexActivity extends Activity implements OnClickListener {

	private LinearLayout line_base;
	private LinearLayout line_link;
	private LinearLayout line_mode;
	private LinearLayout line_op;
	private ImageView iv_base;
	private ImageView iv_link;
	private ImageView iv_mode;
	private ImageView iv_op;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index);
		webserver();
		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		line_base = (LinearLayout) findViewById(R.id.line_base);
		line_link = (LinearLayout) findViewById(R.id.line_link);
		line_mode = (LinearLayout) findViewById(R.id.line_mode);
		line_op = (LinearLayout) findViewById(R.id.line_op);
		iv_base = (ImageView) findViewById(R.id.iv_base);
		iv_link = (ImageView) findViewById(R.id.iv_link);
		iv_mode = (ImageView) findViewById(R.id.iv_mode);
		iv_op = (ImageView) findViewById(R.id.iv_op);
		line_base.setOnClickListener(this);
		line_link.setOnClickListener(this);
		line_mode.setOnClickListener(this);
		line_op.setOnClickListener(this);
		iv_base.setVisibility(View.VISIBLE);
		iv_link.setVisibility(View.INVISIBLE);
		iv_mode.setVisibility(View.INVISIBLE);
		iv_op.setVisibility(View.INVISIBLE);
	}

	private void webserver() {
		// TODO Auto-generated method stub
		ControlUtils.setUser("bizideal", "123456", AppConfig.IP);
		SocketClient.getInstance().creatConnect();
		SocketClient.getInstance().login(new LoginCallback() {

			@Override
			public void onEvent(final String arg0) {
				// TODO Auto-generated method stub
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (arg0.equals(ConstantUtil.SUCCESS)) {
							DiyToast.showToast(getApplicationContext(),
									"网络连接成功");
							AppConfig.getdata(IndexActivity.this);
						} else {
							DiyToast.showToast(getApplicationContext(),
									"网络连接失败");
							AppConfig.getdata(IndexActivity.this);
						}
					}
				});
			}
		});
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.line_base:
			iv_base.setVisibility(View.VISIBLE);
			iv_link.setVisibility(View.INVISIBLE);
			iv_mode.setVisibility(View.INVISIBLE);
			iv_op.setVisibility(View.INVISIBLE);
			startActivity(new Intent(getApplicationContext(),
					BaseActivity.class));
			break;
		case R.id.line_link:
			iv_base.setVisibility(View.INVISIBLE);
			iv_link.setVisibility(View.VISIBLE);
			iv_mode.setVisibility(View.INVISIBLE);
			iv_op.setVisibility(View.INVISIBLE);
			startActivity(new Intent(getApplicationContext(),
					LinkActivity.class));
			break;
		case R.id.line_mode:
			iv_base.setVisibility(View.INVISIBLE);
			iv_link.setVisibility(View.INVISIBLE);
			iv_mode.setVisibility(View.VISIBLE);
			iv_op.setVisibility(View.INVISIBLE);
			startActivity(new Intent(getApplicationContext(),
					ModeActivity.class));
			break;
		case R.id.line_op:
			iv_base.setVisibility(View.INVISIBLE);
			iv_link.setVisibility(View.INVISIBLE);
			iv_mode.setVisibility(View.INVISIBLE);
			iv_op.setVisibility(View.VISIBLE);
			startActivity(new Intent(getApplicationContext(), OpActivity.class));
			break;

		default:
			break;
		}
	}
}
