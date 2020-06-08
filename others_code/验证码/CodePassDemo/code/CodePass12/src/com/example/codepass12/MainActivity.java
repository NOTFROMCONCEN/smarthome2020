package com.example.codepass12;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private ImageView iv_code_show;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv_code_show = (ImageView) findViewById(R.id.imageView1);
		iv_code_show.setImageBitmap(CodePass.createBitmap());
		iv_code_show.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				iv_code_show.setImageBitmap(CodePass.createBitmap());
			}
		});
	}
}
