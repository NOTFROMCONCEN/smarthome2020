package com.example.codeforpass3;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		((ImageView) findViewById(R.id.iv_repass_show)).setImageBitmap(CodeView
				.createRandomBitMap());
		((TextView) findViewById(R.id.tv_repass_update))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						((ImageView) findViewById(R.id.iv_repass_show))
								.setImageBitmap(CodeView.createRandomBitMap());
						Log.e("TAG", CodeView.code);
						((EditText) findViewById(R.id.et_repass))
								.setText(CodeView.code);
					}
				});

		((Button) findViewById(R.id.btn_login))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if (((EditText) findViewById(R.id.et_repass)).getText()
								.toString().equals(CodeView.code)) {
							Toast.makeText(getApplicationContext(), "³É¹¦",
									Toast.LENGTH_SHORT).show();
						} else {
							Toast.makeText(getApplicationContext(), "Ê§°Ü",
									Toast.LENGTH_SHORT).show();
						}
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
