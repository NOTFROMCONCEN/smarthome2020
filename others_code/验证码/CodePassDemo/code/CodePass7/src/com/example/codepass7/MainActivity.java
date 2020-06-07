package com.example.codepass7;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private ImageView iv_code_show;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv_code_show = (ImageView) findViewById(R.id.iv_code_show);
		iv_code_show.setImageBitmap(CodePass.createBitmap());
		Toast.makeText(getApplicationContext(), CodePass.code,
				Toast.LENGTH_SHORT).show();
		iv_code_show.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				iv_code_show.setImageBitmap(CodePass.createBitmap());
				Toast.makeText(getApplicationContext(), CodePass.code,
						Toast.LENGTH_SHORT).show();
			}
		});
	}
}
