package com.example.codeforpass4;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		((ImageView) findViewById(R.id.iv_code)).setImageBitmap(Code
				.createRandomBitMap());
		((ImageView) findViewById(R.id.iv_code))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						((ImageView) findViewById(R.id.iv_code))
								.setImageBitmap(Code.createRandomBitMap());
					}
				});
		final TaiJi taiJi = new TaiJi(this);
		setContentView(taiJi);
		final Handler handler = new Handler() {
			private float degrees = 0;

			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				taiJi.setRotate(degrees += 5);
				this.sendEmptyMessageDelayed(0, 1);
			}
		};

		handler.sendEmptyMessageDelayed(0, 1);
	}
}
