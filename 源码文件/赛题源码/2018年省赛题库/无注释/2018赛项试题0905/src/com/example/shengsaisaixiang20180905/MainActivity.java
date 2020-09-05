package com.example.shengsaisaixiang20180905;

import com.bizideal.smarthome.socket.SocketClient;
import com.example.shengsaisaixiang20180905.fragment.BarActivity;
import com.example.shengsaisaixiang20180905.tools.UserConfig;

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

	private Button btn_login, btn_reg;
	private ImageView iv_show_logo;
	private ScaleAnimation scaleAnimation;
	private SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		if (sharedPreferences.getBoolean("autologin", false) == true) {
			SocketClient.ip = sharedPreferences.getString("ip", null);
			startActivity(new Intent(getApplicationContext(), BarActivity.class));
			if (sharedPreferences.getString("user", null).equals("bizideal")) {
				UserConfig.now_user = "bizideal";
				UserConfig.op_state = true;
			} else {
				UserConfig.now_user = "null";
				UserConfig.op_state = false;
			}
		}
		btn_login.setVisibility(View.INVISIBLE);
		btn_reg.setVisibility(View.INVISIBLE);
		scaleAnimation.setDuration(2000);
		scaleAnimation.setFillAfter(true);
		iv_show_logo.startAnimation(scaleAnimation);
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
		iv_show_logo = (ImageView) findViewById(R.id.imageView1);
		sharedPreferences = getSharedPreferences("rember", MODE_PRIVATE);
		scaleAnimation = new ScaleAnimation(0, 1f, 0, 1f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
	}
}
