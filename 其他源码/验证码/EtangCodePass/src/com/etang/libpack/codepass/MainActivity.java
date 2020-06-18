package com.etang.libpack.codepass;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final ImageView iv_show_code = (ImageView) findViewById(R.id.iv_show_code);
		iv_show_code.setImageBitmap(CodePass.createBitmap(MainActivity.this,
				140, 50, 4, 20));
		iv_show_code.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				iv_show_code.setImageBitmap(CodePass.createBitmap(
						MainActivity.this, 140, 50, 4, 20));
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
