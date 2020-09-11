package com.example.shengsaic20180911;

import com.bizideal.smarthome.socket.SocketClient;
import com.example.shengsaic20180911.fragment.BarActivity;
import com.example.shengsaic20180911.tools.AppConfig;
import com.example.shengsaic20180911.tools.SQLHelper;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private ImageView iv_show;
	private Button btn_login, btn_reg;
	private ScaleAnimation scaleAnimation;
	private SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();

		sharedPreferences = getSharedPreferences("rember", MODE_PRIVATE);
		if (sharedPreferences.getBoolean("autologin", false) == true) {
			if (sharedPreferences.getString("user", null).equals("bizideal")) {
				AppConfig.op = true;
			}
			SocketClient.ip = sharedPreferences.getString("ip", null);
			startActivity(new Intent(getApplicationContext(), BarActivity.class));
			finish();
		}
		btn_login.setVisibility(View.INVISIBLE);
		btn_reg.setVisibility(View.INVISIBLE);
		SQLHelper.jihuo(getApplicationContext());
		scaleAnimation = new ScaleAnimation(0f, 1f, 0f, 1f,
				ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
				ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
		scaleAnimation.setDuration(2000);
		scaleAnimation.setFillAfter(true);
		iv_show.setAnimation(scaleAnimation);
		scaleAnimation.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				btn_login.setVisibility(View.VISIBLE);
				btn_reg.setVisibility(View.VISIBLE);
			}
		});
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getApplicationContext(),
						LoginActivity.class));
			}
		});
		btn_reg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getApplicationContext(),
						RegActivity.class));
			}
		});
	}

	private void initView() {
		// TODO Auto-generated method stub
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		iv_show = (ImageView) findViewById(R.id.iv_logo_show);
	}
}
