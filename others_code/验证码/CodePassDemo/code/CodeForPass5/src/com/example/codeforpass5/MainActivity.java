package com.example.codeforpass5;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		((ImageView) findViewById(R.id.iv_code_show)).setImageBitmap(CodeView
				.createBitmap());
		((ImageView) findViewById(R.id.iv_code_show))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						((ImageView) findViewById(R.id.iv_code_show))
								.setImageBitmap(CodeView.createBitmap());
					}
				});
	}
}
