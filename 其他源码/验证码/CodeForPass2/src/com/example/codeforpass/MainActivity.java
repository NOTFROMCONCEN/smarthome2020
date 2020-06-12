package com.example.codeforpass;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.codeforpass.toast.DiyToast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		((ImageView) findViewById(R.id.myImage)).setImageBitmap(createCode
				.getInstance().createBitmap());
		((TextView) findViewById(R.id.tv_repass_update))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						((ImageView) findViewById(R.id.myImage))
								.setImageBitmap(createCode.getInstance()
										.createBitmap());
						DiyToast.showToast(getApplicationContext(),
								createCode.code);
					}
				});
		findViewById(R.id.btn_con).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (((EditText) findViewById(R.id.et_repass)).getText()
						.toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "请输入验证码");
				} else {
					if (((EditText) findViewById(R.id.et_repass)).getText()
							.toString().equals(createCode.code)) {
						DiyToast.showToast(getApplicationContext(), "验证成功");
					} else {
						DiyToast.showToast(getApplicationContext(), "验证码输入错误");
					}
				}
			}
		});
	}
}
