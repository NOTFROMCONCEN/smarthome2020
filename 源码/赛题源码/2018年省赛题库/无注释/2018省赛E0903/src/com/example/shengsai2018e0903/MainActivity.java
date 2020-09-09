package com.example.shengsai2018e0903;

import com.bizideal.smarthome.socket.SocketClient;
import com.example.shengsai2018e0903.fragment.BbarActivity;
import com.example.shengsai2018e0903.tools.UuserOp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private Button btn_login, btn_reg;
	private ImageView iv_loading_logo;
	private ScaleAnimation scaleAnimation;
	private SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		if (sharedPreferences.getBoolean("rember", false) == true) {
			String now_user = sharedPreferences.getString("user", null);
			if (now_user.equals("bizideal")) {
				UuserOp.user_op = "op";
			} else {
				UuserOp.user_op = "user";
			}
			SocketClient.ip = sharedPreferences.getString("ip", null);
			startActivity(new Intent(getApplicationContext(),
					BbarActivity.class));
			finish();
		}
		btn_login.setVisibility(View.INVISIBLE);
		btn_reg.setVisibility(View.INVISIBLE);
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
		scaleAnimation = new ScaleAnimation(0f, 1f, 0f, 1f,
				ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
				ScaleAnimation.RELATIVE_TO_SELF, 0.5f);// 设置参数
		scaleAnimation.setDuration(2000);// 时长
		scaleAnimation.setFillAfter(true);// 是否重置
		iv_loading_logo.setAnimation(scaleAnimation);// 设置动画
		// 设置动画监听
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
	}

	private void initView() {
		// TODO Auto-generated method stub
		sharedPreferences = getSharedPreferences("rember", MODE_PRIVATE);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		iv_loading_logo = (ImageView) findViewById(R.id.iv_loading_logo);
	}
}
