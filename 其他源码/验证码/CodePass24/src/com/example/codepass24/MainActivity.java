package com.example.codepass24;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private ImageView iv_show_code;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv_show_code = (ImageView) findViewById(R.id.iv_show_code);
		iv_show_code.setImageBitmap(CodePass.createBitmap());
		iv_show_code.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				iv_show_code.setImageBitmap(CodePass.createBitmap());
			}
		});
	}
}
